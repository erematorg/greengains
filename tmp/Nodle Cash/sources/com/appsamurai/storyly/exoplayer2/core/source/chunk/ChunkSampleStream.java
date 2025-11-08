package com.appsamurai.storyly.exoplayer2.core.source.chunk;

import androidx.annotation.Nullable;
import androidx.compose.ui.autofill.a;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;
import com.appsamurai.storyly.exoplayer2.core.source.SampleQueue;
import com.appsamurai.storyly.exoplayer2.core.source.SampleStream;
import com.appsamurai.storyly.exoplayer2.core.source.SequenceableLoader;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkSource;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.core.upstream.Loader;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkSampleStream<T extends ChunkSource> implements SampleStream, SequenceableLoader, Loader.Callback<Chunk>, Loader.ReleaseCallback {
    private static final String TAG = "ChunkSampleStream";
    private final SequenceableLoader.Callback<ChunkSampleStream<T>> callback;
    /* access modifiers changed from: private */
    @Nullable
    public BaseMediaChunk canceledMediaChunk;
    private final BaseMediaChunkOutput chunkOutput;
    private final T chunkSource;
    private final SampleQueue[] embeddedSampleQueues;
    /* access modifiers changed from: private */
    public final Format[] embeddedTrackFormats;
    /* access modifiers changed from: private */
    public final int[] embeddedTrackTypes;
    /* access modifiers changed from: private */
    public final boolean[] embeddedTracksSelected;
    /* access modifiers changed from: private */
    public long lastSeekPositionUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader;
    @Nullable
    private Chunk loadingChunk;
    boolean loadingFinished;
    private final ArrayList<BaseMediaChunk> mediaChunks;
    /* access modifiers changed from: private */
    public final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private final ChunkHolder nextChunkHolder;
    private int nextNotifyPrimaryFormatMediaChunkIndex;
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final SampleQueue primarySampleQueue;
    public final int primaryTrackType;
    private final List<BaseMediaChunk> readOnlyMediaChunks;
    @Nullable
    private ReleaseCallback<T> releaseCallback;

    public final class EmbeddedSampleStream implements SampleStream {
        private final int index;
        private boolean notifiedDownstreamFormat;
        public final ChunkSampleStream<T> parent;
        private final SampleQueue sampleQueue;

        public EmbeddedSampleStream(ChunkSampleStream<T> chunkSampleStream, SampleQueue sampleQueue2, int i3) {
            this.parent = chunkSampleStream;
            this.sampleQueue = sampleQueue2;
            this.index = i3;
        }

        private void maybeNotifyDownstreamFormat() {
            if (!this.notifiedDownstreamFormat) {
                ChunkSampleStream.this.mediaSourceEventDispatcher.downstreamFormatChanged(ChunkSampleStream.this.embeddedTrackTypes[this.index], ChunkSampleStream.this.embeddedTrackFormats[this.index], 0, (Object) null, ChunkSampleStream.this.lastSeekPositionUs);
                this.notifiedDownstreamFormat = true;
            }
        }

        public boolean isReady() {
            return !ChunkSampleStream.this.isPendingReset() && this.sampleQueue.isReady(ChunkSampleStream.this.loadingFinished);
        }

        public void maybeThrowError() {
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return -3;
            }
            if (ChunkSampleStream.this.canceledMediaChunk != null && ChunkSampleStream.this.canceledMediaChunk.getFirstSampleIndex(this.index + 1) <= this.sampleQueue.getReadIndex()) {
                return -3;
            }
            maybeNotifyDownstreamFormat();
            return this.sampleQueue.read(formatHolder, decoderInputBuffer, i3, ChunkSampleStream.this.loadingFinished);
        }

        public void release() {
            Assertions.checkState(ChunkSampleStream.this.embeddedTracksSelected[this.index]);
            ChunkSampleStream.this.embeddedTracksSelected[this.index] = false;
        }

        public int skipData(long j2) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return 0;
            }
            int skipCount = this.sampleQueue.getSkipCount(j2, ChunkSampleStream.this.loadingFinished);
            if (ChunkSampleStream.this.canceledMediaChunk != null) {
                skipCount = Math.min(skipCount, ChunkSampleStream.this.canceledMediaChunk.getFirstSampleIndex(this.index + 1) - this.sampleQueue.getReadIndex());
            }
            this.sampleQueue.skip(skipCount);
            if (skipCount > 0) {
                maybeNotifyDownstreamFormat();
            }
            return skipCount;
        }
    }

    public interface ReleaseCallback<T extends ChunkSource> {
        void onSampleStreamReleased(ChunkSampleStream<T> chunkSampleStream);
    }

    public ChunkSampleStream(int i3, @Nullable int[] iArr, @Nullable Format[] formatArr, T t2, SequenceableLoader.Callback<ChunkSampleStream<T>> callback2, Allocator allocator, long j2, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2) {
        this.primaryTrackType = i3;
        int i4 = 0;
        iArr = iArr == null ? new int[0] : iArr;
        this.embeddedTrackTypes = iArr;
        this.embeddedTrackFormats = formatArr == null ? new Format[0] : formatArr;
        this.chunkSource = t2;
        this.callback = callback2;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.loader = new Loader(TAG);
        this.nextChunkHolder = new ChunkHolder();
        ArrayList<BaseMediaChunk> arrayList = new ArrayList<>();
        this.mediaChunks = arrayList;
        this.readOnlyMediaChunks = Collections.unmodifiableList(arrayList);
        int length = iArr.length;
        this.embeddedSampleQueues = new SampleQueue[length];
        this.embeddedTracksSelected = new boolean[length];
        int i5 = length + 1;
        int[] iArr2 = new int[i5];
        SampleQueue[] sampleQueueArr = new SampleQueue[i5];
        SampleQueue createWithDrm = SampleQueue.createWithDrm(allocator, drmSessionManager, eventDispatcher);
        this.primarySampleQueue = createWithDrm;
        iArr2[0] = i3;
        sampleQueueArr[0] = createWithDrm;
        while (i4 < length) {
            SampleQueue createWithoutDrm = SampleQueue.createWithoutDrm(allocator);
            this.embeddedSampleQueues[i4] = createWithoutDrm;
            int i6 = i4 + 1;
            sampleQueueArr[i6] = createWithoutDrm;
            iArr2[i6] = this.embeddedTrackTypes[i4];
            i4 = i6;
        }
        this.chunkOutput = new BaseMediaChunkOutput(iArr2, sampleQueueArr);
        this.pendingResetPositionUs = j2;
        this.lastSeekPositionUs = j2;
    }

    private void discardDownstreamMediaChunks(int i3) {
        int min = Math.min(primarySampleIndexToMediaChunkIndex(i3, 0), this.nextNotifyPrimaryFormatMediaChunkIndex);
        if (min > 0) {
            Util.removeRange(this.mediaChunks, 0, min);
            this.nextNotifyPrimaryFormatMediaChunkIndex -= min;
        }
    }

    private void discardUpstream(int i3) {
        Assertions.checkState(!this.loader.isLoading());
        int size = this.mediaChunks.size();
        while (true) {
            if (i3 >= size) {
                i3 = -1;
                break;
            } else if (!haveReadFromMediaChunk(i3)) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1) {
            long j2 = getLastMediaChunk().endTimeUs;
            BaseMediaChunk discardUpstreamMediaChunksFromIndex = discardUpstreamMediaChunksFromIndex(i3);
            if (this.mediaChunks.isEmpty()) {
                this.pendingResetPositionUs = this.lastSeekPositionUs;
            }
            this.loadingFinished = false;
            this.mediaSourceEventDispatcher.upstreamDiscarded(this.primaryTrackType, discardUpstreamMediaChunksFromIndex.startTimeUs, j2);
        }
    }

    private BaseMediaChunk discardUpstreamMediaChunksFromIndex(int i3) {
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i3);
        ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
        Util.removeRange(arrayList, i3, arrayList.size());
        this.nextNotifyPrimaryFormatMediaChunkIndex = Math.max(this.nextNotifyPrimaryFormatMediaChunkIndex, this.mediaChunks.size());
        int i4 = 0;
        this.primarySampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(0));
        while (true) {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i4 >= sampleQueueArr.length) {
                return baseMediaChunk;
            }
            SampleQueue sampleQueue = sampleQueueArr[i4];
            i4++;
            sampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(i4));
        }
    }

    private BaseMediaChunk getLastMediaChunk() {
        return (BaseMediaChunk) a.h(this.mediaChunks, 1);
    }

    private boolean haveReadFromMediaChunk(int i3) {
        int readIndex;
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i3);
        if (this.primarySampleQueue.getReadIndex() > baseMediaChunk.getFirstSampleIndex(0)) {
            return true;
        }
        int i4 = 0;
        do {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i4 >= sampleQueueArr.length) {
                return false;
            }
            readIndex = sampleQueueArr[i4].getReadIndex();
            i4++;
        } while (readIndex <= baseMediaChunk.getFirstSampleIndex(i4));
        return true;
    }

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }

    private void maybeNotifyPrimaryTrackFormatChanged() {
        int primarySampleIndexToMediaChunkIndex = primarySampleIndexToMediaChunkIndex(this.primarySampleQueue.getReadIndex(), this.nextNotifyPrimaryFormatMediaChunkIndex - 1);
        while (true) {
            int i3 = this.nextNotifyPrimaryFormatMediaChunkIndex;
            if (i3 <= primarySampleIndexToMediaChunkIndex) {
                this.nextNotifyPrimaryFormatMediaChunkIndex = i3 + 1;
                maybeNotifyPrimaryTrackFormatChanged(i3);
            } else {
                return;
            }
        }
    }

    private int primarySampleIndexToMediaChunkIndex(int i3, int i4) {
        do {
            i4++;
            if (i4 >= this.mediaChunks.size()) {
                return this.mediaChunks.size() - 1;
            }
        } while (this.mediaChunks.get(i4).getFirstSampleIndex(0) <= i3);
        return i4 - 1;
    }

    private void resetSampleQueues() {
        this.primarySampleQueue.reset();
        for (SampleQueue reset : this.embeddedSampleQueues) {
            reset.reset();
        }
    }

    public boolean continueLoading(long j2) {
        List<BaseMediaChunk> list;
        long j3;
        if (this.loadingFinished || this.loader.isLoading() || this.loader.hasFatalError()) {
            return false;
        }
        boolean isPendingReset = isPendingReset();
        if (isPendingReset) {
            list = Collections.emptyList();
            j3 = this.pendingResetPositionUs;
        } else {
            list = this.readOnlyMediaChunks;
            j3 = getLastMediaChunk().endTimeUs;
        }
        this.chunkSource.getNextChunk(j2, j3, list, this.nextChunkHolder);
        ChunkHolder chunkHolder = this.nextChunkHolder;
        boolean z2 = chunkHolder.endOfStream;
        Chunk chunk = chunkHolder.chunk;
        chunkHolder.clear();
        if (z2) {
            this.pendingResetPositionUs = C.TIME_UNSET;
            this.loadingFinished = true;
            return true;
        } else if (chunk == null) {
            return false;
        } else {
            this.loadingChunk = chunk;
            if (isMediaChunk(chunk)) {
                BaseMediaChunk baseMediaChunk = (BaseMediaChunk) chunk;
                if (isPendingReset) {
                    long j4 = baseMediaChunk.startTimeUs;
                    long j5 = this.pendingResetPositionUs;
                    if (j4 != j5) {
                        this.primarySampleQueue.setStartTimeUs(j5);
                        for (SampleQueue startTimeUs : this.embeddedSampleQueues) {
                            startTimeUs.setStartTimeUs(this.pendingResetPositionUs);
                        }
                    }
                    this.pendingResetPositionUs = C.TIME_UNSET;
                }
                baseMediaChunk.init(this.chunkOutput);
                this.mediaChunks.add(baseMediaChunk);
            } else if (chunk instanceof InitializationChunk) {
                ((InitializationChunk) chunk).init(this.chunkOutput);
            }
            this.mediaSourceEventDispatcher.loadStarted(new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, this.loader.startLoading(chunk, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(chunk.type))), chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs);
            return true;
        }
    }

    public void discardBuffer(long j2, boolean z2) {
        if (!isPendingReset()) {
            int firstIndex = this.primarySampleQueue.getFirstIndex();
            this.primarySampleQueue.discardTo(j2, z2, true);
            int firstIndex2 = this.primarySampleQueue.getFirstIndex();
            if (firstIndex2 > firstIndex) {
                long firstTimestampUs = this.primarySampleQueue.getFirstTimestampUs();
                int i3 = 0;
                while (true) {
                    SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
                    if (i3 >= sampleQueueArr.length) {
                        break;
                    }
                    sampleQueueArr[i3].discardTo(firstTimestampUs, z2, this.embeddedTracksSelected[i3]);
                    i3++;
                }
            }
            discardDownstreamMediaChunks(firstIndex2);
        }
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        return this.chunkSource.getAdjustedSeekPositionUs(j2, seekParameters);
    }

    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long j2 = this.lastSeekPositionUs;
        BaseMediaChunk lastMediaChunk = getLastMediaChunk();
        if (!lastMediaChunk.isLoadCompleted()) {
            lastMediaChunk = this.mediaChunks.size() > 1 ? (BaseMediaChunk) a.h(this.mediaChunks, 2) : null;
        }
        if (lastMediaChunk != null) {
            j2 = Math.max(j2, lastMediaChunk.endTimeUs);
        }
        return Math.max(j2, this.primarySampleQueue.getLargestQueuedTimestampUs());
    }

    public T getChunkSource() {
        return this.chunkSource;
    }

    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return getLastMediaChunk().endTimeUs;
    }

    public boolean isLoading() {
        return this.loader.isLoading();
    }

    public boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    public boolean isReady() {
        return !isPendingReset() && this.primarySampleQueue.isReady(this.loadingFinished);
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        this.primarySampleQueue.maybeThrowError();
        if (!this.loader.isLoading()) {
            this.chunkSource.maybeThrowError();
        }
    }

    public void onLoaderReleased() {
        this.primarySampleQueue.release();
        for (SampleQueue release : this.embeddedSampleQueues) {
            release.release();
        }
        this.chunkSource.release();
        ReleaseCallback<T> releaseCallback2 = this.releaseCallback;
        if (releaseCallback2 != null) {
            releaseCallback2.onSampleStreamReleased(this);
        }
    }

    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3) {
        if (isPendingReset()) {
            return -3;
        }
        BaseMediaChunk baseMediaChunk = this.canceledMediaChunk;
        if (baseMediaChunk != null && baseMediaChunk.getFirstSampleIndex(0) <= this.primarySampleQueue.getReadIndex()) {
            return -3;
        }
        maybeNotifyPrimaryTrackFormatChanged();
        return this.primarySampleQueue.read(formatHolder, decoderInputBuffer, i3, this.loadingFinished);
    }

    public void reevaluateBuffer(long j2) {
        if (!this.loader.hasFatalError() && !isPendingReset()) {
            if (this.loader.isLoading()) {
                Chunk chunk = (Chunk) Assertions.checkNotNull(this.loadingChunk);
                if ((!isMediaChunk(chunk) || !haveReadFromMediaChunk(this.mediaChunks.size() - 1)) && this.chunkSource.shouldCancelLoad(j2, chunk, this.readOnlyMediaChunks)) {
                    this.loader.cancelLoading();
                    if (isMediaChunk(chunk)) {
                        this.canceledMediaChunk = (BaseMediaChunk) chunk;
                        return;
                    }
                    return;
                }
                return;
            }
            int preferredQueueSize = this.chunkSource.getPreferredQueueSize(j2, this.readOnlyMediaChunks);
            if (preferredQueueSize < this.mediaChunks.size()) {
                discardUpstream(preferredQueueSize);
            }
        }
    }

    public void release() {
        release((ReleaseCallback) null);
    }

    public void seekToUs(long j2) {
        BaseMediaChunk baseMediaChunk;
        boolean z2;
        this.lastSeekPositionUs = j2;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j2;
            return;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= this.mediaChunks.size()) {
                break;
            }
            baseMediaChunk = this.mediaChunks.get(i4);
            int i5 = (baseMediaChunk.startTimeUs > j2 ? 1 : (baseMediaChunk.startTimeUs == j2 ? 0 : -1));
            if (i5 == 0 && baseMediaChunk.clippedStartTimeUs == C.TIME_UNSET) {
                break;
            } else if (i5 > 0) {
                break;
            } else {
                i4++;
            }
        }
        baseMediaChunk = null;
        if (baseMediaChunk != null) {
            z2 = this.primarySampleQueue.seekTo(baseMediaChunk.getFirstSampleIndex(0));
        } else {
            z2 = this.primarySampleQueue.seekTo(j2, j2 < getNextLoadPositionUs());
        }
        if (z2) {
            this.nextNotifyPrimaryFormatMediaChunkIndex = primarySampleIndexToMediaChunkIndex(this.primarySampleQueue.getReadIndex(), 0);
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            int length = sampleQueueArr.length;
            while (i3 < length) {
                sampleQueueArr[i3].seekTo(j2, true);
                i3++;
            }
            return;
        }
        this.pendingResetPositionUs = j2;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        this.nextNotifyPrimaryFormatMediaChunkIndex = 0;
        if (this.loader.isLoading()) {
            this.primarySampleQueue.discardToEnd();
            SampleQueue[] sampleQueueArr2 = this.embeddedSampleQueues;
            int length2 = sampleQueueArr2.length;
            while (i3 < length2) {
                sampleQueueArr2[i3].discardToEnd();
                i3++;
            }
            this.loader.cancelLoading();
            return;
        }
        this.loader.clearFatalError();
        resetSampleQueues();
    }

    public ChunkSampleStream<T>.EmbeddedSampleStream selectEmbeddedTrack(long j2, int i3) {
        for (int i4 = 0; i4 < this.embeddedSampleQueues.length; i4++) {
            if (this.embeddedTrackTypes[i4] == i3) {
                Assertions.checkState(!this.embeddedTracksSelected[i4]);
                this.embeddedTracksSelected[i4] = true;
                this.embeddedSampleQueues[i4].seekTo(j2, true);
                return new EmbeddedSampleStream(this, this.embeddedSampleQueues[i4], i4);
            }
        }
        throw new IllegalStateException();
    }

    public int skipData(long j2) {
        if (isPendingReset()) {
            return 0;
        }
        int skipCount = this.primarySampleQueue.getSkipCount(j2, this.loadingFinished);
        BaseMediaChunk baseMediaChunk = this.canceledMediaChunk;
        if (baseMediaChunk != null) {
            skipCount = Math.min(skipCount, baseMediaChunk.getFirstSampleIndex(0) - this.primarySampleQueue.getReadIndex());
        }
        this.primarySampleQueue.skip(skipCount);
        maybeNotifyPrimaryTrackFormatChanged();
        return skipCount;
    }

    public void onLoadCanceled(Chunk chunk, long j2, long j3, boolean z2) {
        Chunk chunk2 = chunk;
        this.loadingChunk = null;
        this.canceledMediaChunk = null;
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk2.loadTaskId, chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j2, j3, chunk.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk2.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, chunk2.type, this.primaryTrackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs);
        if (!z2) {
            if (isPendingReset()) {
                resetSampleQueues();
            } else if (isMediaChunk(chunk)) {
                discardUpstreamMediaChunksFromIndex(this.mediaChunks.size() - 1);
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public void onLoadCompleted(Chunk chunk, long j2, long j3) {
        Chunk chunk2 = chunk;
        this.loadingChunk = null;
        this.chunkSource.onChunkLoadCompleted(chunk2);
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk2.loadTaskId, chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j2, j3, chunk.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk2.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, chunk2.type, this.primaryTrackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs);
        this.callback.onContinueLoadingRequested(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ef  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.appsamurai.storyly.exoplayer2.core.upstream.Loader.LoadErrorAction onLoadError(com.appsamurai.storyly.exoplayer2.core.source.chunk.Chunk r31, long r32, long r34, java.io.IOException r36, int r37) {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            long r12 = r31.bytesLoaded()
            boolean r14 = r30.isMediaChunk(r31)
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.source.chunk.BaseMediaChunk> r2 = r0.mediaChunks
            int r2 = r2.size()
            r15 = 1
            int r10 = r2 + -1
            r2 = 0
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            r11 = 0
            if (r2 == 0) goto L_0x0027
            if (r14 == 0) goto L_0x0027
            boolean r2 = r0.haveReadFromMediaChunk(r10)
            if (r2 != 0) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            r8 = r11
            goto L_0x0028
        L_0x0027:
            r8 = r15
        L_0x0028:
            com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo r9 = new com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo
            long r3 = r1.loadTaskId
            com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec r5 = r1.dataSpec
            android.net.Uri r6 = r31.getUri()
            java.util.Map r7 = r31.getResponseHeaders()
            r2 = r9
            r15 = r8
            r17 = r14
            r14 = r9
            r8 = r32
            r29 = r10
            r10 = r34
            r2.<init>(r3, r5, r6, r7, r8, r10, r12)
            com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData r2 = new com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData
            int r3 = r1.type
            int r4 = r0.primaryTrackType
            com.appsamurai.storyly.exoplayer2.common.Format r5 = r1.trackFormat
            int r6 = r1.trackSelectionReason
            java.lang.Object r7 = r1.trackSelectionData
            long r8 = r1.startTimeUs
            long r24 = com.appsamurai.storyly.exoplayer2.common.util.Util.usToMs(r8)
            long r8 = r1.endTimeUs
            long r26 = com.appsamurai.storyly.exoplayer2.common.util.Util.usToMs(r8)
            r18 = r2
            r19 = r3
            r20 = r4
            r21 = r5
            r22 = r6
            r23 = r7
            r18.<init>(r19, r20, r21, r22, r23, r24, r26)
            com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy$LoadErrorInfo r3 = new com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy$LoadErrorInfo
            r4 = r36
            r5 = r37
            r3.<init>(r14, r2, r4, r5)
            T r2 = r0.chunkSource
            com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy r5 = r0.loadErrorHandlingPolicy
            boolean r2 = r2.onChunkLoadError(r1, r15, r3, r5)
            if (r2 == 0) goto L_0x00a6
            if (r15 == 0) goto L_0x009f
            com.appsamurai.storyly.exoplayer2.core.upstream.Loader$LoadErrorAction r2 = com.appsamurai.storyly.exoplayer2.core.upstream.Loader.DONT_RETRY
            if (r17 == 0) goto L_0x00a7
            r6 = r29
            com.appsamurai.storyly.exoplayer2.core.source.chunk.BaseMediaChunk r6 = r0.discardUpstreamMediaChunksFromIndex(r6)
            if (r6 != r1) goto L_0x008e
            r15 = 1
            goto L_0x008f
        L_0x008e:
            r15 = 0
        L_0x008f:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r15)
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.core.source.chunk.BaseMediaChunk> r6 = r0.mediaChunks
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x00a7
            long r6 = r0.lastSeekPositionUs
            r0.pendingResetPositionUs = r6
            goto L_0x00a7
        L_0x009f:
            java.lang.String r2 = "ChunkSampleStream"
            java.lang.String r6 = "Ignoring attempt to cancel non-cancelable load."
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r2, r6)
        L_0x00a6:
            r2 = 0
        L_0x00a7:
            if (r2 != 0) goto L_0x00c0
            com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy r2 = r0.loadErrorHandlingPolicy
            long r2 = r2.getRetryDelayMsFor(r3)
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x00be
            r6 = 0
            com.appsamurai.storyly.exoplayer2.core.upstream.Loader$LoadErrorAction r2 = com.appsamurai.storyly.exoplayer2.core.upstream.Loader.createRetryAction(r6, r2)
            goto L_0x00c0
        L_0x00be:
            com.appsamurai.storyly.exoplayer2.core.upstream.Loader$LoadErrorAction r2 = com.appsamurai.storyly.exoplayer2.core.upstream.Loader.DONT_RETRY_FATAL
        L_0x00c0:
            boolean r3 = r2.isRetry()
            r28 = r3 ^ 1
            com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener$EventDispatcher r6 = r0.mediaSourceEventDispatcher
            int r7 = r1.type
            int r8 = r0.primaryTrackType
            com.appsamurai.storyly.exoplayer2.common.Format r9 = r1.trackFormat
            int r10 = r1.trackSelectionReason
            java.lang.Object r11 = r1.trackSelectionData
            long r12 = r1.startTimeUs
            long r4 = r1.endTimeUs
            r16 = r6
            r17 = r14
            r18 = r7
            r19 = r8
            r20 = r9
            r21 = r10
            r22 = r11
            r23 = r12
            r25 = r4
            r27 = r36
            r16.loadError(r17, r18, r19, r20, r21, r22, r23, r25, r27, r28)
            if (r3 != 0) goto L_0x00fe
            r3 = 0
            r0.loadingChunk = r3
            com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy r3 = r0.loadErrorHandlingPolicy
            long r4 = r1.loadTaskId
            r3.onLoadTaskConcluded(r4)
            com.appsamurai.storyly.exoplayer2.core.source.SequenceableLoader$Callback<com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkSampleStream<T>> r1 = r0.callback
            r1.onContinueLoadingRequested(r0)
        L_0x00fe:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.chunk.ChunkSampleStream.onLoadError(com.appsamurai.storyly.exoplayer2.core.source.chunk.Chunk, long, long, java.io.IOException, int):com.appsamurai.storyly.exoplayer2.core.upstream.Loader$LoadErrorAction");
    }

    public void release(@Nullable ReleaseCallback<T> releaseCallback2) {
        this.releaseCallback = releaseCallback2;
        this.primarySampleQueue.preRelease();
        for (SampleQueue preRelease : this.embeddedSampleQueues) {
            preRelease.preRelease();
        }
        this.loader.release(this);
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int i3) {
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i3);
        Format format = baseMediaChunk.trackFormat;
        if (!format.equals(this.primaryDownstreamTrackFormat)) {
            this.mediaSourceEventDispatcher.downstreamFormatChanged(this.primaryTrackType, format, baseMediaChunk.trackSelectionReason, baseMediaChunk.trackSelectionData, baseMediaChunk.startTimeUs);
        }
        this.primaryDownstreamTrackFormat = format;
    }
}
