package com.appsamurai.storyly.exoplayer2.core.source;

import android.net.Uri;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.source.IcyDataSource;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;
import com.appsamurai.storyly.exoplayer2.core.source.SampleQueue;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.core.upstream.Loader;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceUtil;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.StatsDataSource;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.icy.IcyHeaders;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

final class ProgressiveMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback, SampleQueue.UpstreamFormatChangedListener {
    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    /* access modifiers changed from: private */
    public static final Format ICY_FORMAT = new Format.Builder().setId("icy").setSampleMimeType(MimeTypes.APPLICATION_ICY).build();
    /* access modifiers changed from: private */
    public static final Map<String, String> ICY_METADATA_HEADERS = createIcyMetadataHeaders();
    private final Allocator allocator;
    @Nullable
    private MediaPeriod.Callback callback;
    /* access modifiers changed from: private */
    public final long continueLoadingCheckIntervalBytes;
    /* access modifiers changed from: private */
    @Nullable
    public final String customCacheKey;
    private final DataSource dataSource;
    private int dataType;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private long durationUs;
    private int enabledTrackCount;
    private int extractedSamplesCountAtStartOfLoad;
    /* access modifiers changed from: private */
    public final Handler handler;
    private boolean haveAudioVideoTracks;
    /* access modifiers changed from: private */
    @Nullable
    public IcyHeaders icyHeaders;
    private boolean isLive;
    private long lastSeekPositionUs;
    private long length;
    private final Listener listener;
    private final ConditionVariable loadCondition;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader = new Loader("ProgressiveMediaPeriod");
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private boolean notifyDiscontinuity;
    /* access modifiers changed from: private */
    public final Runnable onContinueLoadingRequestedRunnable;
    private boolean pendingDeferredRetry;
    private long pendingResetPositionUs;
    private boolean prepared;
    private final ProgressiveMediaExtractor progressiveMediaExtractor;
    private boolean released;
    private TrackId[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private TrackState trackState;
    private final Uri uri;

    public final class ExtractingLoadable implements Loader.Loadable, IcyDataSource.Listener {
        /* access modifiers changed from: private */
        public final StatsDataSource dataSource;
        /* access modifiers changed from: private */
        public DataSpec dataSpec = buildDataSpec(0);
        private final ExtractorOutput extractorOutput;
        @Nullable
        private TrackOutput icyTrackOutput;
        /* access modifiers changed from: private */
        public long length = -1;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        /* access modifiers changed from: private */
        public final long loadTaskId = LoadEventInfo.getNewId();
        private boolean pendingExtractorSeek = true;
        private final PositionHolder positionHolder = new PositionHolder();
        private final ProgressiveMediaExtractor progressiveMediaExtractor;
        /* access modifiers changed from: private */
        public long seekTimeUs;
        private boolean seenIcyMetadata;
        private final Uri uri;

        public ExtractingLoadable(Uri uri2, DataSource dataSource2, ProgressiveMediaExtractor progressiveMediaExtractor2, ExtractorOutput extractorOutput2, ConditionVariable conditionVariable) {
            this.uri = uri2;
            this.dataSource = new StatsDataSource(dataSource2);
            this.progressiveMediaExtractor = progressiveMediaExtractor2;
            this.extractorOutput = extractorOutput2;
            this.loadCondition = conditionVariable;
        }

        private DataSpec buildDataSpec(long j2) {
            return new DataSpec.Builder().setUri(this.uri).setPosition(j2).setKey(ProgressiveMediaPeriod.this.customCacheKey).setFlags(6).setHttpRequestHeaders(ProgressiveMediaPeriod.ICY_METADATA_HEADERS).build();
        }

        /* access modifiers changed from: private */
        public void setLoadPosition(long j2, long j3) {
            this.positionHolder.position = j2;
            this.seekTimeUs = j3;
            this.pendingExtractorSeek = true;
            this.seenIcyMetadata = false;
        }

        public void cancelLoad() {
            this.loadCanceled = true;
        }

        public void load() throws IOException {
            int i3 = 0;
            while (i3 == 0 && !this.loadCanceled) {
                try {
                    long j2 = this.positionHolder.position;
                    DataSpec buildDataSpec = buildDataSpec(j2);
                    this.dataSpec = buildDataSpec;
                    long open = this.dataSource.open(buildDataSpec);
                    this.length = open;
                    if (open != -1) {
                        this.length = open + j2;
                    }
                    IcyHeaders unused = ProgressiveMediaPeriod.this.icyHeaders = IcyHeaders.parse(this.dataSource.getResponseHeaders());
                    DataReader dataReader = this.dataSource;
                    if (!(ProgressiveMediaPeriod.this.icyHeaders == null || ProgressiveMediaPeriod.this.icyHeaders.metadataInterval == -1)) {
                        dataReader = new IcyDataSource(this.dataSource, ProgressiveMediaPeriod.this.icyHeaders.metadataInterval, this);
                        TrackOutput icyTrack = ProgressiveMediaPeriod.this.icyTrack();
                        this.icyTrackOutput = icyTrack;
                        icyTrack.format(ProgressiveMediaPeriod.ICY_FORMAT);
                    }
                    long j3 = j2;
                    this.progressiveMediaExtractor.init(dataReader, this.uri, this.dataSource.getResponseHeaders(), j2, this.length, this.extractorOutput);
                    if (ProgressiveMediaPeriod.this.icyHeaders != null) {
                        this.progressiveMediaExtractor.disableSeekingOnMp3Streams();
                    }
                    if (this.pendingExtractorSeek) {
                        this.progressiveMediaExtractor.seek(j3, this.seekTimeUs);
                        this.pendingExtractorSeek = false;
                    }
                    while (true) {
                        long j4 = j3;
                        while (i3 == 0 && !this.loadCanceled) {
                            this.loadCondition.block();
                            i3 = this.progressiveMediaExtractor.read(this.positionHolder);
                            j3 = this.progressiveMediaExtractor.getCurrentInputPosition();
                            if (j3 > ProgressiveMediaPeriod.this.continueLoadingCheckIntervalBytes + j4) {
                                this.loadCondition.close();
                                ProgressiveMediaPeriod.this.handler.post(ProgressiveMediaPeriod.this.onContinueLoadingRequestedRunnable);
                            }
                        }
                    }
                    if (i3 == 1) {
                        i3 = 0;
                    } else if (this.progressiveMediaExtractor.getCurrentInputPosition() != -1) {
                        this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                    }
                    DataSourceUtil.closeQuietly(this.dataSource);
                } catch (InterruptedException unused2) {
                    throw new InterruptedIOException();
                } catch (Throwable th) {
                    if (!(i3 == 1 || this.progressiveMediaExtractor.getCurrentInputPosition() == -1)) {
                        this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
                    }
                    DataSourceUtil.closeQuietly(this.dataSource);
                    throw th;
                }
            }
        }

        public void onIcyMetadata(ParsableByteArray parsableByteArray) {
            long max = !this.seenIcyMetadata ? this.seekTimeUs : Math.max(ProgressiveMediaPeriod.this.getLargestQueuedTimestampUs(), this.seekTimeUs);
            int bytesLeft = parsableByteArray.bytesLeft();
            TrackOutput trackOutput = (TrackOutput) Assertions.checkNotNull(this.icyTrackOutput);
            trackOutput.sampleData(parsableByteArray, bytesLeft);
            trackOutput.sampleMetadata(max, 1, bytesLeft, 0, (TrackOutput.CryptoData) null);
            this.seenIcyMetadata = true;
        }
    }

    public interface Listener {
        void onSourceInfoRefreshed(long j2, boolean z2, boolean z3);
    }

    public final class SampleStreamImpl implements SampleStream {
        /* access modifiers changed from: private */
        public final int track;

        public SampleStreamImpl(int i3) {
            this.track = i3;
        }

        public boolean isReady() {
            return ProgressiveMediaPeriod.this.isReady(this.track);
        }

        public void maybeThrowError() throws IOException {
            ProgressiveMediaPeriod.this.maybeThrowError(this.track);
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3) {
            return ProgressiveMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, i3);
        }

        public int skipData(long j2) {
            return ProgressiveMediaPeriod.this.skipData(this.track, j2);
        }
    }

    public static final class TrackId {
        public final int id;
        public final boolean isIcyTrack;

        public TrackId(int i3, boolean z2) {
            this.id = i3;
            this.isIcyTrack = z2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || TrackId.class != obj.getClass()) {
                return false;
            }
            TrackId trackId = (TrackId) obj;
            return this.id == trackId.id && this.isIcyTrack == trackId.isIcyTrack;
        }

        public int hashCode() {
            return (this.id * 31) + (this.isIcyTrack ? 1 : 0);
        }
    }

    public static final class TrackState {
        public final boolean[] trackEnabledStates;
        public final boolean[] trackIsAudioVideoFlags;
        public final boolean[] trackNotifiedDownstreamFormats;
        public final TrackGroupArray tracks;

        public TrackState(TrackGroupArray trackGroupArray, boolean[] zArr) {
            this.tracks = trackGroupArray;
            this.trackIsAudioVideoFlags = zArr;
            int i3 = trackGroupArray.length;
            this.trackEnabledStates = new boolean[i3];
            this.trackNotifiedDownstreamFormats = new boolean[i3];
        }
    }

    public ProgressiveMediaPeriod(Uri uri2, DataSource dataSource2, ProgressiveMediaExtractor progressiveMediaExtractor2, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2, Listener listener2, Allocator allocator2, @Nullable String str, int i3) {
        this.uri = uri2;
        this.dataSource = dataSource2;
        this.drmSessionManager = drmSessionManager2;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.listener = listener2;
        this.allocator = allocator2;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = (long) i3;
        this.progressiveMediaExtractor = progressiveMediaExtractor2;
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new j(this, 0);
        this.onContinueLoadingRequestedRunnable = new j(this, 1);
        this.handler = Util.createHandlerForCurrentLooper();
        this.sampleQueueTrackIds = new TrackId[0];
        this.sampleQueues = new SampleQueue[0];
        this.pendingResetPositionUs = C.TIME_UNSET;
        this.length = -1;
        this.durationUs = C.TIME_UNSET;
        this.dataType = 1;
    }

    @EnsuresNonNull({"trackState", "seekMap"})
    private void assertPrepared() {
        Assertions.checkState(this.prepared);
        Assertions.checkNotNull(this.trackState);
        Assertions.checkNotNull(this.seekMap);
    }

    private boolean configureRetry(ExtractingLoadable extractingLoadable, int i3) {
        SeekMap seekMap2;
        if (this.length == -1 && ((seekMap2 = this.seekMap) == null || seekMap2.getDurationUs() == C.TIME_UNSET)) {
            if (!this.prepared || suppressRead()) {
                this.notifyDiscontinuity = this.prepared;
                this.lastSeekPositionUs = 0;
                this.extractedSamplesCountAtStartOfLoad = 0;
                for (SampleQueue reset : this.sampleQueues) {
                    reset.reset();
                }
                extractingLoadable.setLoadPosition(0, 0);
                return true;
            }
            this.pendingDeferredRetry = true;
            return false;
        }
        this.extractedSamplesCountAtStartOfLoad = i3;
        return true;
    }

    private void copyLengthFromLoader(ExtractingLoadable extractingLoadable) {
        if (this.length == -1) {
            this.length = extractingLoadable.length;
        }
    }

    private static Map<String, String> createIcyMetadataHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_NAME, "1");
        return Collections.unmodifiableMap(hashMap);
    }

    private int getExtractedSamplesCount() {
        int i3 = 0;
        for (SampleQueue writeIndex : this.sampleQueues) {
            i3 += writeIndex.getWriteIndex();
        }
        return i3;
    }

    /* access modifiers changed from: private */
    public long getLargestQueuedTimestampUs() {
        long j2 = Long.MIN_VALUE;
        for (SampleQueue largestQueuedTimestampUs : this.sampleQueues) {
            j2 = Math.max(j2, largestQueuedTimestampUs.getLargestQueuedTimestampUs());
        }
        return j2;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (!this.released) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
        }
    }

    /* access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (!this.released && !this.prepared && this.sampleQueuesBuilt && this.seekMap != null) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            int length2 = sampleQueueArr.length;
            int i3 = 0;
            while (i3 < length2) {
                if (sampleQueueArr[i3].getUpstreamFormat() != null) {
                    i3++;
                } else {
                    return;
                }
            }
            this.loadCondition.close();
            int length3 = this.sampleQueues.length;
            TrackGroup[] trackGroupArr = new TrackGroup[length3];
            boolean[] zArr = new boolean[length3];
            for (int i4 = 0; i4 < length3; i4++) {
                Format format = (Format) Assertions.checkNotNull(this.sampleQueues[i4].getUpstreamFormat());
                String str = format.sampleMimeType;
                boolean isAudio = MimeTypes.isAudio(str);
                boolean z2 = isAudio || MimeTypes.isVideo(str);
                zArr[i4] = z2;
                this.haveAudioVideoTracks = z2 | this.haveAudioVideoTracks;
                IcyHeaders icyHeaders2 = this.icyHeaders;
                if (icyHeaders2 != null) {
                    if (isAudio || this.sampleQueueTrackIds[i4].isIcyTrack) {
                        Metadata metadata = format.metadata;
                        format = format.buildUpon().setMetadata(metadata == null ? new Metadata(icyHeaders2) : metadata.copyWithAppendedEntries(icyHeaders2)).build();
                    }
                    if (isAudio && format.averageBitrate == -1 && format.peakBitrate == -1 && icyHeaders2.bitrate != -1) {
                        format = format.buildUpon().setAverageBitrate(icyHeaders2.bitrate).build();
                    }
                }
                trackGroupArr[i4] = new TrackGroup(Integer.toString(i4), format.copyWithCryptoType(this.drmSessionManager.getCryptoType(format)));
            }
            this.trackState = new TrackState(new TrackGroupArray(trackGroupArr), zArr);
            this.prepared = true;
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
        }
    }

    private void maybeNotifyDownstreamFormat(int i3) {
        assertPrepared();
        TrackState trackState2 = this.trackState;
        boolean[] zArr = trackState2.trackNotifiedDownstreamFormats;
        if (!zArr[i3]) {
            Format format = trackState2.tracks.get(i3).getFormat(0);
            this.mediaSourceEventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(format.sampleMimeType), format, 0, (Object) null, this.lastSeekPositionUs);
            zArr[i3] = true;
        }
    }

    private void maybeStartDeferredRetry(int i3) {
        assertPrepared();
        boolean[] zArr = this.trackState.trackIsAudioVideoFlags;
        if (this.pendingDeferredRetry && zArr[i3]) {
            if (!this.sampleQueues[i3].isReady(false)) {
                this.pendingResetPositionUs = 0;
                this.pendingDeferredRetry = false;
                this.notifyDiscontinuity = true;
                this.lastSeekPositionUs = 0;
                this.extractedSamplesCountAtStartOfLoad = 0;
                for (SampleQueue reset : this.sampleQueues) {
                    reset.reset();
                }
                ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
            }
        }
    }

    private TrackOutput prepareTrackOutput(TrackId trackId) {
        int length2 = this.sampleQueues.length;
        for (int i3 = 0; i3 < length2; i3++) {
            if (trackId.equals(this.sampleQueueTrackIds[i3])) {
                return this.sampleQueues[i3];
            }
        }
        SampleQueue createWithDrm = SampleQueue.createWithDrm(this.allocator, this.drmSessionManager, this.drmEventDispatcher);
        createWithDrm.setUpstreamFormatChangeListener(this);
        int i4 = length2 + 1;
        TrackId[] trackIdArr = (TrackId[]) Arrays.copyOf(this.sampleQueueTrackIds, i4);
        trackIdArr[length2] = trackId;
        this.sampleQueueTrackIds = (TrackId[]) Util.castNonNullTypeArray(trackIdArr);
        SampleQueue[] sampleQueueArr = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i4);
        sampleQueueArr[length2] = createWithDrm;
        this.sampleQueues = (SampleQueue[]) Util.castNonNullTypeArray(sampleQueueArr);
        return createWithDrm;
    }

    private boolean seekInsideBufferUs(boolean[] zArr, long j2) {
        int length2 = this.sampleQueues.length;
        for (int i3 = 0; i3 < length2; i3++) {
            if (!this.sampleQueues[i3].seekTo(j2, false) && (zArr[i3] || !this.haveAudioVideoTracks)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: setSeekMap */
    public void lambda$seekMap$1(SeekMap seekMap2) {
        this.seekMap = this.icyHeaders == null ? seekMap2 : new SeekMap.Unseekable(C.TIME_UNSET);
        this.durationUs = seekMap2.getDurationUs();
        int i3 = 1;
        boolean z2 = this.length == -1 && seekMap2.getDurationUs() == C.TIME_UNSET;
        this.isLive = z2;
        if (z2) {
            i3 = 7;
        }
        this.dataType = i3;
        this.listener.onSourceInfoRefreshed(this.durationUs, seekMap2.isSeekable(), this.isLive);
        if (!this.prepared) {
            maybeFinishPrepare();
        }
    }

    private void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.progressiveMediaExtractor, this, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            long j2 = this.durationUs;
            if (j2 == C.TIME_UNSET || this.pendingResetPositionUs <= j2) {
                extractingLoadable.setLoadPosition(((SeekMap) Assertions.checkNotNull(this.seekMap)).getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
                for (SampleQueue startTimeUs : this.sampleQueues) {
                    startTimeUs.setStartTimeUs(this.pendingResetPositionUs);
                }
                this.pendingResetPositionUs = C.TIME_UNSET;
            } else {
                this.loadingFinished = true;
                this.pendingResetPositionUs = C.TIME_UNSET;
                return;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.mediaSourceEventDispatcher.loadStarted(new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, this.loader.startLoading(extractingLoadable, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType))), 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs);
    }

    private boolean suppressRead() {
        return this.notifyDiscontinuity || isPendingReset();
    }

    public boolean continueLoading(long j2) {
        if (this.loadingFinished || this.loader.hasFatalError() || this.pendingDeferredRetry) {
            return false;
        }
        if (this.prepared && this.enabledTrackCount == 0) {
            return false;
        }
        boolean open = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return open;
        }
        startLoading();
        return true;
    }

    public void discardBuffer(long j2, boolean z2) {
        assertPrepared();
        if (!isPendingReset()) {
            boolean[] zArr = this.trackState.trackEnabledStates;
            int length2 = this.sampleQueues.length;
            for (int i3 = 0; i3 < length2; i3++) {
                this.sampleQueues[i3].discardTo(j2, z2, zArr[i3]);
            }
        }
    }

    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        assertPrepared();
        if (!this.seekMap.isSeekable()) {
            return 0;
        }
        SeekMap.SeekPoints seekPoints = this.seekMap.getSeekPoints(j2);
        return seekParameters.resolveSeekPositionUs(j2, seekPoints.first.timeUs, seekPoints.second.timeUs);
    }

    public long getBufferedPositionUs() {
        long j2;
        assertPrepared();
        boolean[] zArr = this.trackState.trackIsAudioVideoFlags;
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            int length2 = this.sampleQueues.length;
            j2 = Long.MAX_VALUE;
            for (int i3 = 0; i3 < length2; i3++) {
                if (zArr[i3] && !this.sampleQueues[i3].isLastSampleQueued()) {
                    j2 = Math.min(j2, this.sampleQueues[i3].getLargestQueuedTimestampUs());
                }
            }
        } else {
            j2 = Long.MAX_VALUE;
        }
        if (j2 == Long.MAX_VALUE) {
            j2 = getLargestQueuedTimestampUs();
        }
        return j2 == Long.MIN_VALUE ? this.lastSeekPositionUs : j2;
    }

    public long getNextLoadPositionUs() {
        if (this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        return getBufferedPositionUs();
    }

    public TrackGroupArray getTrackGroups() {
        assertPrepared();
        return this.trackState.tracks;
    }

    public TrackOutput icyTrack() {
        return prepareTrackOutput(new TrackId(0, true));
    }

    public boolean isLoading() {
        return this.loader.isLoading() && this.loadCondition.isOpen();
    }

    public boolean isReady(int i3) {
        return !suppressRead() && this.sampleQueues[i3].isReady(this.loadingFinished);
    }

    public void maybeThrowError(int i3) throws IOException {
        this.sampleQueues[i3].maybeThrowError();
        maybeThrowError();
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
        if (this.loadingFinished && !this.prepared) {
            throw ParserException.createForMalformedContainer("Loading finished before preparation is complete.", (Throwable) null);
        }
    }

    public void onLoaderReleased() {
        for (SampleQueue release : this.sampleQueues) {
            release.release();
        }
        this.progressiveMediaExtractor.release();
    }

    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        this.loadCondition.open();
        startLoading();
    }

    public int readData(int i3, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i4) {
        if (suppressRead()) {
            return -3;
        }
        maybeNotifyDownstreamFormat(i3);
        int read = this.sampleQueues[i3].read(formatHolder, decoderInputBuffer, i4, this.loadingFinished);
        if (read == -3) {
            maybeStartDeferredRetry(i3);
        }
        return read;
    }

    public long readDiscontinuity() {
        if (!this.notifyDiscontinuity) {
            return C.TIME_UNSET;
        }
        if (!this.loadingFinished && getExtractedSamplesCount() <= this.extractedSamplesCountAtStartOfLoad) {
            return C.TIME_UNSET;
        }
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    public void reevaluateBuffer(long j2) {
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue preRelease : this.sampleQueues) {
                preRelease.preRelease();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages((Object) null);
        this.callback = null;
        this.released = true;
    }

    public void seekMap(SeekMap seekMap2) {
        this.handler.post(new i(this, seekMap2));
    }

    public long seekToUs(long j2) {
        assertPrepared();
        boolean[] zArr = this.trackState.trackIsAudioVideoFlags;
        if (!this.seekMap.isSeekable()) {
            j2 = 0;
        }
        int i3 = 0;
        this.notifyDiscontinuity = false;
        this.lastSeekPositionUs = j2;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j2;
            return j2;
        } else if (this.dataType != 7 && seekInsideBufferUs(zArr, j2)) {
            return j2;
        } else {
            this.pendingDeferredRetry = false;
            this.pendingResetPositionUs = j2;
            this.loadingFinished = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length2 = sampleQueueArr.length;
                while (i3 < length2) {
                    sampleQueueArr[i3].discardToEnd();
                    i3++;
                }
                this.loader.cancelLoading();
            } else {
                this.loader.clearFatalError();
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length3 = sampleQueueArr2.length;
                while (i3 < length3) {
                    sampleQueueArr2[i3].reset();
                    i3++;
                }
            }
            return j2;
        }
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        ExoTrackSelection exoTrackSelection;
        assertPrepared();
        TrackState trackState2 = this.trackState;
        TrackGroupArray trackGroupArray = trackState2.tracks;
        boolean[] zArr3 = trackState2.trackEnabledStates;
        int i3 = this.enabledTrackCount;
        int i4 = 0;
        for (int i5 = 0; i5 < exoTrackSelectionArr.length; i5++) {
            SampleStreamImpl sampleStreamImpl = sampleStreamArr[i5];
            if (sampleStreamImpl != null && (exoTrackSelectionArr[i5] == null || !zArr[i5])) {
                int access$000 = sampleStreamImpl.track;
                Assertions.checkState(zArr3[access$000]);
                this.enabledTrackCount--;
                zArr3[access$000] = false;
                sampleStreamArr[i5] = null;
            }
        }
        boolean z2 = !this.seenFirstTrackSelection ? j2 != 0 : i3 == 0;
        for (int i6 = 0; i6 < exoTrackSelectionArr.length; i6++) {
            if (sampleStreamArr[i6] == null && (exoTrackSelection = exoTrackSelectionArr[i6]) != null) {
                Assertions.checkState(exoTrackSelection.length() == 1);
                Assertions.checkState(exoTrackSelection.getIndexInTrackGroup(0) == 0);
                int indexOf = trackGroupArray.indexOf(exoTrackSelection.getTrackGroup());
                Assertions.checkState(!zArr3[indexOf]);
                this.enabledTrackCount++;
                zArr3[indexOf] = true;
                sampleStreamArr[i6] = new SampleStreamImpl(indexOf);
                zArr2[i6] = true;
                if (!z2) {
                    SampleQueue sampleQueue = this.sampleQueues[indexOf];
                    z2 = !sampleQueue.seekTo(j2, true) && sampleQueue.getReadIndex() != 0;
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length2 = sampleQueueArr.length;
                while (i4 < length2) {
                    sampleQueueArr[i4].discardToEnd();
                    i4++;
                }
                this.loader.cancelLoading();
            } else {
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length3 = sampleQueueArr2.length;
                while (i4 < length3) {
                    sampleQueueArr2[i4].reset();
                    i4++;
                }
            }
        } else if (z2) {
            j2 = seekToUs(j2);
            while (i4 < sampleStreamArr.length) {
                if (sampleStreamArr[i4] != null) {
                    zArr2[i4] = true;
                }
                i4++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j2;
    }

    public int skipData(int i3, long j2) {
        if (suppressRead()) {
            return 0;
        }
        maybeNotifyDownstreamFormat(i3);
        SampleQueue sampleQueue = this.sampleQueues[i3];
        int skipCount = sampleQueue.getSkipCount(j2, this.loadingFinished);
        sampleQueue.skip(skipCount);
        if (skipCount == 0) {
            maybeStartDeferredRetry(i3);
        }
        return skipCount;
    }

    public TrackOutput track(int i3, int i4) {
        return prepareTrackOutput(new TrackId(i3, false));
    }

    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j2, long j3, boolean z2) {
        StatsDataSource access$100 = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs);
        if (!z2) {
            copyLengthFromLoader(extractingLoadable);
            for (SampleQueue reset : this.sampleQueues) {
                reset.reset();
            }
            if (this.enabledTrackCount > 0) {
                ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
            }
        }
    }

    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j2, long j3) {
        SeekMap seekMap2;
        if (this.durationUs == C.TIME_UNSET && (seekMap2 = this.seekMap) != null) {
            boolean isSeekable = seekMap2.isSeekable();
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs();
            long j4 = largestQueuedTimestampUs == Long.MIN_VALUE ? 0 : largestQueuedTimestampUs + 10000;
            this.durationUs = j4;
            this.listener.onSourceInfoRefreshed(j4, isSeekable, this.isLive);
        }
        StatsDataSource access$100 = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs);
        copyLengthFromLoader(extractingLoadable);
        this.loadingFinished = true;
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    public Loader.LoadErrorAction onLoadError(ExtractingLoadable extractingLoadable, long j2, long j3, IOException iOException, int i3) {
        Loader.LoadErrorAction loadErrorAction;
        copyLengthFromLoader(extractingLoadable);
        StatsDataSource access$100 = extractingLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(extractingLoadable.loadTaskId, extractingLoadable.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(1, -1, (Format) null, 0, (Object) null, Util.usToMs(extractingLoadable.seekTimeUs), Util.usToMs(this.durationUs)), iOException, i3));
        if (retryDelayMsFor == C.TIME_UNSET) {
            loadErrorAction = Loader.DONT_RETRY_FATAL;
            ExtractingLoadable extractingLoadable2 = extractingLoadable;
        } else {
            int extractedSamplesCount = getExtractedSamplesCount();
            boolean z2 = extractedSamplesCount > this.extractedSamplesCountAtStartOfLoad;
            if (configureRetry(extractingLoadable, extractedSamplesCount)) {
                loadErrorAction = Loader.createRetryAction(z2, retryDelayMsFor);
            } else {
                loadErrorAction = Loader.DONT_RETRY;
            }
        }
        boolean isRetry = loadErrorAction.isRetry();
        this.mediaSourceEventDispatcher.loadError(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, extractingLoadable.seekTimeUs, this.durationUs, iOException, !isRetry);
        if (!isRetry) {
            this.loadErrorHandlingPolicy.onLoadTaskConcluded(extractingLoadable.loadTaskId);
        }
        return loadErrorAction;
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError(this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }
}
