package com.appsamurai.storyly.exoplayer2.hls;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseIntArray;
import androidx.annotation.Nullable;
import androidx.compose.ui.autofill.a;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.upstream.DataReader;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionEventListener;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManager;
import com.appsamurai.storyly.exoplayer2.core.source.LoadEventInfo;
import com.appsamurai.storyly.exoplayer2.core.source.MediaLoadData;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;
import com.appsamurai.storyly.exoplayer2.core.source.SampleQueue;
import com.appsamurai.storyly.exoplayer2.core.source.SampleStream;
import com.appsamurai.storyly.exoplayer2.core.source.SequenceableLoader;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.Chunk;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectionUtil;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.core.upstream.Loader;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.HttpDataSource;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DummyTrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.emsg.EventMessage;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.emsg.EventMessageDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.PrivFrame;
import com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

final class HlsSampleStreamWrapper implements Loader.Callback<Chunk>, Loader.ReleaseCallback, SequenceableLoader, ExtractorOutput, SampleQueue.UpstreamFormatChangedListener {
    private static final Set<Integer> MAPPABLE_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Integer[]{1, 2, 5})));
    public static final int SAMPLE_QUEUE_INDEX_NO_MAPPING_FATAL = -2;
    public static final int SAMPLE_QUEUE_INDEX_NO_MAPPING_NON_FATAL = -3;
    public static final int SAMPLE_QUEUE_INDEX_PENDING = -1;
    private static final String TAG = "HlsSampleStreamWrapper";
    private final Allocator allocator;
    private final Callback callback;
    private final HlsChunkSource chunkSource;
    @Nullable
    private Format downstreamTrackFormat;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    @Nullable
    private DrmInitData drmInitData;
    private final DrmSessionManager drmSessionManager;
    private TrackOutput emsgUnwrappingTrackOutput;
    private int enabledTrackGroupCount;
    private final Handler handler;
    private boolean haveAudioVideoSampleQueues;
    private final ArrayList<HlsSampleStream> hlsSampleStreams;
    private long lastSeekPositionUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader = new Loader("Loader:HlsSampleStreamWrapper");
    @Nullable
    private Chunk loadingChunk;
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final ArrayList<HlsMediaChunk> mediaChunks;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private final int metadataType;
    @Nullable
    private final Format muxedAudioFormat;
    private final HlsChunkSource.HlsChunkHolder nextChunkHolder = new HlsChunkSource.HlsChunkHolder();
    private final Runnable onTracksEndedRunnable;
    private Set<TrackGroup> optionalTrackGroups;
    private final Map<String, DrmInitData> overridingDrmInitData;
    private long pendingResetPositionUs;
    private boolean pendingResetUpstreamFormats;
    private boolean prepared;
    private int primarySampleQueueIndex;
    private int primarySampleQueueType;
    private int primaryTrackGroupIndex;
    private final List<HlsMediaChunk> readOnlyMediaChunks;
    private boolean released;
    private long sampleOffsetUs;
    private SparseIntArray sampleQueueIndicesByType;
    private boolean[] sampleQueueIsAudioVideoFlags;
    private Set<Integer> sampleQueueMappingDoneByType;
    private int[] sampleQueueTrackIds = new int[0];
    private HlsSampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private boolean[] sampleQueuesEnabledStates;
    private boolean seenFirstTrackSelection;
    @Nullable
    private HlsMediaChunk sourceChunk;
    private int[] trackGroupToSampleQueueIndex;
    private TrackGroupArray trackGroups;
    private final int trackType;
    private boolean tracksEnded;
    private final String uid;
    private Format upstreamTrackFormat;

    public interface Callback extends SequenceableLoader.Callback<HlsSampleStreamWrapper> {
        void onPlaylistRefreshRequired(Uri uri);

        void onPrepared();
    }

    public static final class HlsSampleQueue extends SampleQueue {
        @Nullable
        private DrmInitData drmInitData;
        private final Map<String, DrmInitData> overridingDrmInitData;

        @Nullable
        private Metadata getAdjustedMetadata(@Nullable Metadata metadata) {
            if (metadata == null) {
                return null;
            }
            int length = metadata.length();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    i4 = -1;
                    break;
                }
                Metadata.Entry entry = metadata.get(i4);
                if ((entry instanceof PrivFrame) && HlsMediaChunk.PRIV_TIMESTAMP_FRAME_OWNER.equals(((PrivFrame) entry).owner)) {
                    break;
                }
                i4++;
            }
            if (i4 == -1) {
                return metadata;
            }
            if (length == 1) {
                return null;
            }
            Metadata.Entry[] entryArr = new Metadata.Entry[(length - 1)];
            while (i3 < length) {
                if (i3 != i4) {
                    entryArr[i3 < i4 ? i3 : i3 - 1] = metadata.get(i3);
                }
                i3++;
            }
            return new Metadata(entryArr);
        }

        public Format getAdjustedUpstreamFormat(Format format) {
            DrmInitData drmInitData2;
            DrmInitData drmInitData3 = this.drmInitData;
            if (drmInitData3 == null) {
                drmInitData3 = format.drmInitData;
            }
            if (!(drmInitData3 == null || (drmInitData2 = this.overridingDrmInitData.get(drmInitData3.schemeType)) == null)) {
                drmInitData3 = drmInitData2;
            }
            Metadata adjustedMetadata = getAdjustedMetadata(format.metadata);
            if (!(drmInitData3 == format.drmInitData && adjustedMetadata == format.metadata)) {
                format = format.buildUpon().setDrmInitData(drmInitData3).setMetadata(adjustedMetadata).build();
            }
            return super.getAdjustedUpstreamFormat(format);
        }

        public void sampleMetadata(long j2, int i3, int i4, int i5, @Nullable TrackOutput.CryptoData cryptoData) {
            super.sampleMetadata(j2, i3, i4, i5, cryptoData);
        }

        public void setDrmInitData(@Nullable DrmInitData drmInitData2) {
            this.drmInitData = drmInitData2;
            invalidateUpstreamFormatAdjustment();
        }

        public void setSourceChunk(HlsMediaChunk hlsMediaChunk) {
            sourceId(hlsMediaChunk.uid);
        }

        private HlsSampleQueue(Allocator allocator, DrmSessionManager drmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher, Map<String, DrmInitData> map) {
            super(allocator, drmSessionManager, eventDispatcher);
            this.overridingDrmInitData = map;
        }
    }

    public HlsSampleStreamWrapper(String str, int i3, Callback callback2, HlsChunkSource hlsChunkSource, Map<String, DrmInitData> map, Allocator allocator2, long j2, @Nullable Format format, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2, int i4) {
        this.uid = str;
        this.trackType = i3;
        this.callback = callback2;
        this.chunkSource = hlsChunkSource;
        this.overridingDrmInitData = map;
        this.allocator = allocator2;
        this.muxedAudioFormat = format;
        this.drmSessionManager = drmSessionManager2;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.metadataType = i4;
        Set<Integer> set = MAPPABLE_TYPES;
        this.sampleQueueMappingDoneByType = new HashSet(set.size());
        this.sampleQueueIndicesByType = new SparseIntArray(set.size());
        this.sampleQueues = new HlsSampleQueue[0];
        this.sampleQueueIsAudioVideoFlags = new boolean[0];
        this.sampleQueuesEnabledStates = new boolean[0];
        ArrayList<HlsMediaChunk> arrayList = new ArrayList<>();
        this.mediaChunks = arrayList;
        this.readOnlyMediaChunks = Collections.unmodifiableList(arrayList);
        this.hlsSampleStreams = new ArrayList<>();
        this.maybeFinishPrepareRunnable = new a(this, 0);
        this.onTracksEndedRunnable = new a(this, 1);
        this.handler = Util.createHandlerForCurrentLooper();
        this.lastSeekPositionUs = j2;
        this.pendingResetPositionUs = j2;
    }

    @EnsuresNonNull({"trackGroups", "optionalTrackGroups"})
    private void assertIsPrepared() {
        Assertions.checkState(this.prepared);
        Assertions.checkNotNull(this.trackGroups);
        Assertions.checkNotNull(this.optionalTrackGroups);
    }

    @EnsuresNonNull({"trackGroups", "optionalTrackGroups", "trackGroupToSampleQueueIndex"})
    private void buildTracksFromSampleStreams() {
        Format format;
        int length = this.sampleQueues.length;
        boolean z2 = false;
        int i3 = -2;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            int i6 = 2;
            if (i5 >= length) {
                break;
            }
            String str = ((Format) Assertions.checkStateNotNull(this.sampleQueues[i5].getUpstreamFormat())).sampleMimeType;
            if (!MimeTypes.isVideo(str)) {
                i6 = MimeTypes.isAudio(str) ? 1 : MimeTypes.isText(str) ? 3 : -2;
            }
            if (getTrackTypeScore(i6) > getTrackTypeScore(i3)) {
                i4 = i5;
                i3 = i6;
            } else if (i6 == i3 && i4 != -1) {
                i4 = -1;
            }
            i5++;
        }
        TrackGroup trackGroup = this.chunkSource.getTrackGroup();
        int i7 = trackGroup.length;
        this.primaryTrackGroupIndex = -1;
        this.trackGroupToSampleQueueIndex = new int[length];
        for (int i8 = 0; i8 < length; i8++) {
            this.trackGroupToSampleQueueIndex[i8] = i8;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        int i9 = 0;
        while (i9 < length) {
            Format format2 = (Format) Assertions.checkStateNotNull(this.sampleQueues[i9].getUpstreamFormat());
            if (i9 == i4) {
                Format[] formatArr = new Format[i7];
                for (int i10 = 0; i10 < i7; i10++) {
                    Format format3 = trackGroup.getFormat(i10);
                    if (i3 == 1 && (format = this.muxedAudioFormat) != null) {
                        format3 = format3.withManifestFormatInfo(format);
                    }
                    formatArr[i10] = i7 == 1 ? format2.withManifestFormatInfo(format3) : deriveFormat(format3, format2, true);
                }
                trackGroupArr[i9] = new TrackGroup(this.uid, formatArr);
                this.primaryTrackGroupIndex = i9;
            } else {
                Format format4 = (i3 != 2 || !MimeTypes.isAudio(format2.sampleMimeType)) ? null : this.muxedAudioFormat;
                StringBuilder sb = new StringBuilder();
                sb.append(this.uid);
                sb.append(":muxed:");
                sb.append(i9 < i4 ? i9 : i9 - 1);
                trackGroupArr[i9] = new TrackGroup(sb.toString(), deriveFormat(format4, format2, false));
            }
            i9++;
        }
        this.trackGroups = createTrackGroupArrayWithDrmInfo(trackGroupArr);
        if (this.optionalTrackGroups == null) {
            z2 = true;
        }
        Assertions.checkState(z2);
        this.optionalTrackGroups = Collections.emptySet();
    }

    private boolean canDiscardUpstreamMediaChunksFromIndex(int i3) {
        for (int i4 = i3; i4 < this.mediaChunks.size(); i4++) {
            if (this.mediaChunks.get(i4).shouldSpliceIn) {
                return false;
            }
        }
        HlsMediaChunk hlsMediaChunk = this.mediaChunks.get(i3);
        for (int i5 = 0; i5 < this.sampleQueues.length; i5++) {
            if (this.sampleQueues[i5].getReadIndex() > hlsMediaChunk.getFirstSampleIndex(i5)) {
                return false;
            }
        }
        return true;
    }

    private static DummyTrackOutput createFakeTrackOutput(int i3, int i4) {
        Log.w(TAG, "Unmapped track with id " + i3 + " of type " + i4);
        return new DummyTrackOutput();
    }

    private SampleQueue createSampleQueue(int i3, int i4) {
        int length = this.sampleQueues.length;
        boolean z2 = true;
        if (!(i4 == 1 || i4 == 2)) {
            z2 = false;
        }
        HlsSampleQueue hlsSampleQueue = new HlsSampleQueue(this.allocator, this.drmSessionManager, this.drmEventDispatcher, this.overridingDrmInitData);
        hlsSampleQueue.setStartTimeUs(this.lastSeekPositionUs);
        if (z2) {
            hlsSampleQueue.setDrmInitData(this.drmInitData);
        }
        hlsSampleQueue.setSampleOffsetUs(this.sampleOffsetUs);
        HlsMediaChunk hlsMediaChunk = this.sourceChunk;
        if (hlsMediaChunk != null) {
            hlsSampleQueue.setSourceChunk(hlsMediaChunk);
        }
        hlsSampleQueue.setUpstreamFormatChangeListener(this);
        int i5 = length + 1;
        int[] copyOf = Arrays.copyOf(this.sampleQueueTrackIds, i5);
        this.sampleQueueTrackIds = copyOf;
        copyOf[length] = i3;
        this.sampleQueues = (HlsSampleQueue[]) Util.nullSafeArrayAppend(this.sampleQueues, hlsSampleQueue);
        boolean[] copyOf2 = Arrays.copyOf(this.sampleQueueIsAudioVideoFlags, i5);
        this.sampleQueueIsAudioVideoFlags = copyOf2;
        copyOf2[length] = z2;
        this.haveAudioVideoSampleQueues |= z2;
        this.sampleQueueMappingDoneByType.add(Integer.valueOf(i4));
        this.sampleQueueIndicesByType.append(i4, length);
        if (getTrackTypeScore(i4) > getTrackTypeScore(this.primarySampleQueueType)) {
            this.primarySampleQueueIndex = length;
            this.primarySampleQueueType = i4;
        }
        this.sampleQueuesEnabledStates = Arrays.copyOf(this.sampleQueuesEnabledStates, i5);
        return hlsSampleQueue;
    }

    private TrackGroupArray createTrackGroupArrayWithDrmInfo(TrackGroup[] trackGroupArr) {
        for (int i3 = 0; i3 < trackGroupArr.length; i3++) {
            TrackGroup trackGroup = trackGroupArr[i3];
            Format[] formatArr = new Format[trackGroup.length];
            for (int i4 = 0; i4 < trackGroup.length; i4++) {
                Format format = trackGroup.getFormat(i4);
                formatArr[i4] = format.copyWithCryptoType(this.drmSessionManager.getCryptoType(format));
            }
            trackGroupArr[i3] = new TrackGroup(trackGroup.id, formatArr);
        }
        return new TrackGroupArray(trackGroupArr);
    }

    private static Format deriveFormat(@Nullable Format format, Format format2, boolean z2) {
        String str;
        String str2;
        if (format == null) {
            return format2;
        }
        int trackType2 = MimeTypes.getTrackType(format2.sampleMimeType);
        if (Util.getCodecCountOfType(format.codecs, trackType2) == 1) {
            str2 = Util.getCodecsOfType(format.codecs, trackType2);
            str = MimeTypes.getMediaMimeType(str2);
        } else {
            str2 = MimeTypes.getCodecsCorrespondingToMimeType(format.codecs, format2.sampleMimeType);
            str = format2.sampleMimeType;
        }
        Format.Builder codecs = format2.buildUpon().setId(format.id).setLabel(format.label).setLanguage(format.language).setSelectionFlags(format.selectionFlags).setRoleFlags(format.roleFlags).setAverageBitrate(z2 ? format.averageBitrate : -1).setPeakBitrate(z2 ? format.peakBitrate : -1).setCodecs(str2);
        if (trackType2 == 2) {
            codecs.setWidth(format.width).setHeight(format.height).setFrameRate(format.frameRate);
        }
        if (str != null) {
            codecs.setSampleMimeType(str);
        }
        int i3 = format.channelCount;
        if (i3 != -1 && trackType2 == 1) {
            codecs.setChannelCount(i3);
        }
        Metadata metadata = format.metadata;
        if (metadata != null) {
            Metadata metadata2 = format2.metadata;
            if (metadata2 != null) {
                metadata = metadata2.copyWithAppendedEntriesFrom(metadata);
            }
            codecs.setMetadata(metadata);
        }
        return codecs.build();
    }

    private void discardUpstream(int i3) {
        Assertions.checkState(!this.loader.isLoading());
        while (true) {
            if (i3 >= this.mediaChunks.size()) {
                i3 = -1;
                break;
            } else if (canDiscardUpstreamMediaChunksFromIndex(i3)) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 != -1) {
            long j2 = getLastMediaChunk().endTimeUs;
            HlsMediaChunk discardUpstreamMediaChunksFromIndex = discardUpstreamMediaChunksFromIndex(i3);
            if (this.mediaChunks.isEmpty()) {
                this.pendingResetPositionUs = this.lastSeekPositionUs;
            } else {
                ((HlsMediaChunk) Iterables.getLast(this.mediaChunks)).invalidateExtractor();
            }
            this.loadingFinished = false;
            this.mediaSourceEventDispatcher.upstreamDiscarded(this.primarySampleQueueType, discardUpstreamMediaChunksFromIndex.startTimeUs, j2);
        }
    }

    private HlsMediaChunk discardUpstreamMediaChunksFromIndex(int i3) {
        HlsMediaChunk hlsMediaChunk = this.mediaChunks.get(i3);
        ArrayList<HlsMediaChunk> arrayList = this.mediaChunks;
        Util.removeRange(arrayList, i3, arrayList.size());
        for (int i4 = 0; i4 < this.sampleQueues.length; i4++) {
            this.sampleQueues[i4].discardUpstreamSamples(hlsMediaChunk.getFirstSampleIndex(i4));
        }
        return hlsMediaChunk;
    }

    private boolean finishedReadingChunk(HlsMediaChunk hlsMediaChunk) {
        int i3 = hlsMediaChunk.uid;
        int length = this.sampleQueues.length;
        for (int i4 = 0; i4 < length; i4++) {
            if (this.sampleQueuesEnabledStates[i4] && this.sampleQueues[i4].peekSourceId() == i3) {
                return false;
            }
        }
        return true;
    }

    private static boolean formatsMatch(Format format, Format format2) {
        String str = format.sampleMimeType;
        String str2 = format2.sampleMimeType;
        int trackType2 = MimeTypes.getTrackType(str);
        if (trackType2 != 3) {
            return trackType2 == MimeTypes.getTrackType(str2);
        }
        if (!Util.areEqual(str, str2)) {
            return false;
        }
        if (MimeTypes.APPLICATION_CEA608.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str)) {
            return format.accessibilityChannel == format2.accessibilityChannel;
        }
        return true;
    }

    private HlsMediaChunk getLastMediaChunk() {
        return (HlsMediaChunk) a.h(this.mediaChunks, 1);
    }

    @Nullable
    private TrackOutput getMappedTrackOutput(int i3, int i4) {
        Assertions.checkArgument(MAPPABLE_TYPES.contains(Integer.valueOf(i4)));
        int i5 = this.sampleQueueIndicesByType.get(i4, -1);
        if (i5 == -1) {
            return null;
        }
        if (this.sampleQueueMappingDoneByType.add(Integer.valueOf(i4))) {
            this.sampleQueueTrackIds[i5] = i3;
        }
        return this.sampleQueueTrackIds[i5] == i3 ? this.sampleQueues[i5] : createFakeTrackOutput(i3, i4);
    }

    private static int getTrackTypeScore(int i3) {
        if (i3 == 1) {
            return 2;
        }
        if (i3 != 2) {
            return i3 != 3 ? 0 : 1;
        }
        return 3;
    }

    private void initMediaChunkLoad(HlsMediaChunk hlsMediaChunk) {
        this.sourceChunk = hlsMediaChunk;
        this.upstreamTrackFormat = hlsMediaChunk.trackFormat;
        this.pendingResetPositionUs = C.TIME_UNSET;
        this.mediaChunks.add(hlsMediaChunk);
        ImmutableList.Builder builder = ImmutableList.builder();
        for (HlsSampleQueue writeIndex : this.sampleQueues) {
            builder.add((Object) Integer.valueOf(writeIndex.getWriteIndex()));
        }
        hlsMediaChunk.init(this, builder.build());
        for (HlsSampleQueue hlsSampleQueue : this.sampleQueues) {
            hlsSampleQueue.setSourceChunk(hlsMediaChunk);
            if (hlsMediaChunk.shouldSpliceIn) {
                hlsSampleQueue.splice();
            }
        }
    }

    private static boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof HlsMediaChunk;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    @RequiresNonNull({"trackGroups"})
    @EnsuresNonNull({"trackGroupToSampleQueueIndex"})
    private void mapSampleQueuesToMatchTrackGroups() {
        int i3 = this.trackGroups.length;
        int[] iArr = new int[i3];
        this.trackGroupToSampleQueueIndex = iArr;
        Arrays.fill(iArr, -1);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            while (true) {
                HlsSampleQueue[] hlsSampleQueueArr = this.sampleQueues;
                if (i5 >= hlsSampleQueueArr.length) {
                    break;
                } else if (formatsMatch((Format) Assertions.checkStateNotNull(hlsSampleQueueArr[i5].getUpstreamFormat()), this.trackGroups.get(i4).getFormat(0))) {
                    this.trackGroupToSampleQueueIndex[i4] = i5;
                    break;
                } else {
                    i5++;
                }
            }
        }
        Iterator<HlsSampleStream> it = this.hlsSampleStreams.iterator();
        while (it.hasNext()) {
            it.next().bindSampleQueue();
        }
    }

    /* access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (!this.released && this.trackGroupToSampleQueueIndex == null && this.sampleQueuesBuilt) {
            HlsSampleQueue[] hlsSampleQueueArr = this.sampleQueues;
            int length = hlsSampleQueueArr.length;
            int i3 = 0;
            while (i3 < length) {
                if (hlsSampleQueueArr[i3].getUpstreamFormat() != null) {
                    i3++;
                } else {
                    return;
                }
            }
            if (this.trackGroups != null) {
                mapSampleQueuesToMatchTrackGroups();
                return;
            }
            buildTracksFromSampleStreams();
            setIsPrepared();
            this.callback.onPrepared();
        }
    }

    /* access modifiers changed from: private */
    public void onTracksEnded() {
        this.sampleQueuesBuilt = true;
        maybeFinishPrepare();
    }

    private void resetSampleQueues() {
        for (HlsSampleQueue reset : this.sampleQueues) {
            reset.reset(this.pendingResetUpstreamFormats);
        }
        this.pendingResetUpstreamFormats = false;
    }

    private boolean seekInsideBufferUs(long j2) {
        int length = this.sampleQueues.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (!this.sampleQueues[i3].seekTo(j2, false) && (this.sampleQueueIsAudioVideoFlags[i3] || !this.haveAudioVideoSampleQueues)) {
                return false;
            }
        }
        return true;
    }

    @RequiresNonNull({"trackGroups", "optionalTrackGroups"})
    private void setIsPrepared() {
        this.prepared = true;
    }

    private void updateSampleStreams(SampleStream[] sampleStreamArr) {
        this.hlsSampleStreams.clear();
        for (HlsSampleStream hlsSampleStream : sampleStreamArr) {
            if (hlsSampleStream != null) {
                this.hlsSampleStreams.add(hlsSampleStream);
            }
        }
    }

    public int bindSampleQueueToSampleStream(int i3) {
        assertIsPrepared();
        Assertions.checkNotNull(this.trackGroupToSampleQueueIndex);
        int i4 = this.trackGroupToSampleQueueIndex[i3];
        if (i4 == -1) {
            return this.optionalTrackGroups.contains(this.trackGroups.get(i3)) ? -3 : -2;
        }
        boolean[] zArr = this.sampleQueuesEnabledStates;
        if (zArr[i4]) {
            return -2;
        }
        zArr[i4] = true;
        return i4;
    }

    public boolean continueLoading(long j2) {
        List<HlsMediaChunk> list;
        long max;
        if (this.loadingFinished || this.loader.isLoading() || this.loader.hasFatalError()) {
            return false;
        }
        if (isPendingReset()) {
            list = Collections.emptyList();
            max = this.pendingResetPositionUs;
            for (HlsSampleQueue startTimeUs : this.sampleQueues) {
                startTimeUs.setStartTimeUs(this.pendingResetPositionUs);
            }
        } else {
            list = this.readOnlyMediaChunks;
            HlsMediaChunk lastMediaChunk = getLastMediaChunk();
            max = lastMediaChunk.isLoadCompleted() ? lastMediaChunk.endTimeUs : Math.max(this.lastSeekPositionUs, lastMediaChunk.startTimeUs);
        }
        List<HlsMediaChunk> list2 = list;
        long j3 = max;
        this.nextChunkHolder.clear();
        this.chunkSource.getNextChunk(j2, j3, list2, this.prepared || !list2.isEmpty(), this.nextChunkHolder);
        HlsChunkSource.HlsChunkHolder hlsChunkHolder = this.nextChunkHolder;
        boolean z2 = hlsChunkHolder.endOfStream;
        Chunk chunk = hlsChunkHolder.chunk;
        Uri uri = hlsChunkHolder.playlistUrl;
        if (z2) {
            this.pendingResetPositionUs = C.TIME_UNSET;
            this.loadingFinished = true;
            return true;
        } else if (chunk == null) {
            if (uri != null) {
                this.callback.onPlaylistRefreshRequired(uri);
            }
            return false;
        } else {
            if (isMediaChunk(chunk)) {
                initMediaChunkLoad((HlsMediaChunk) chunk);
            }
            this.loadingChunk = chunk;
            this.mediaSourceEventDispatcher.loadStarted(new LoadEventInfo(chunk.loadTaskId, chunk.dataSpec, this.loader.startLoading(chunk, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(chunk.type))), chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs);
            return true;
        }
    }

    public void continuePreparing() {
        if (!this.prepared) {
            continueLoading(this.lastSeekPositionUs);
        }
    }

    public void discardBuffer(long j2, boolean z2) {
        if (this.sampleQueuesBuilt && !isPendingReset()) {
            int length = this.sampleQueues.length;
            for (int i3 = 0; i3 < length; i3++) {
                this.sampleQueues[i3].discardTo(j2, z2, this.sampleQueuesEnabledStates[i3]);
            }
        }
    }

    public void endTracks() {
        this.tracksEnded = true;
        this.handler.post(this.onTracksEndedRunnable);
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
        HlsMediaChunk lastMediaChunk = getLastMediaChunk();
        if (!lastMediaChunk.isLoadCompleted()) {
            lastMediaChunk = this.mediaChunks.size() > 1 ? (HlsMediaChunk) a.h(this.mediaChunks, 2) : null;
        }
        if (lastMediaChunk != null) {
            j2 = Math.max(j2, lastMediaChunk.endTimeUs);
        }
        if (this.sampleQueuesBuilt) {
            for (HlsSampleQueue largestQueuedTimestampUs : this.sampleQueues) {
                j2 = Math.max(j2, largestQueuedTimestampUs.getLargestQueuedTimestampUs());
            }
        }
        return j2;
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

    public int getPrimaryTrackGroupIndex() {
        return this.primaryTrackGroupIndex;
    }

    public TrackGroupArray getTrackGroups() {
        assertIsPrepared();
        return this.trackGroups;
    }

    public boolean isLoading() {
        return this.loader.isLoading();
    }

    public boolean isReady(int i3) {
        return !isPendingReset() && this.sampleQueues[i3].isReady(this.loadingFinished);
    }

    public boolean isVideoSampleStream() {
        return this.primarySampleQueueType == 2;
    }

    public void maybeThrowError(int i3) throws IOException {
        maybeThrowError();
        this.sampleQueues[i3].maybeThrowError();
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
        if (this.loadingFinished && !this.prepared) {
            throw ParserException.createForMalformedContainer("Loading finished before preparation is complete.", (Throwable) null);
        }
    }

    public void onLoaderReleased() {
        for (HlsSampleQueue release : this.sampleQueues) {
            release.release();
        }
    }

    public void onNewExtractor() {
        this.sampleQueueMappingDoneByType.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        r6 = r4.loadErrorHandlingPolicy.getFallbackSelectionFor(com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectionUtil.createFallbackOptions(r4.chunkSource.getTrackSelection()), r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPlaylistError(android.net.Uri r5, com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy.LoadErrorInfo r6, boolean r7) {
        /*
            r4 = this;
            com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource r0 = r4.chunkSource
            boolean r0 = r0.obtainsChunksForPlaylist(r5)
            r1 = 1
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r7 != 0) goto L_0x002b
            com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy r7 = r4.loadErrorHandlingPolicy
            com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource r0 = r4.chunkSource
            com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection r0 = r0.getTrackSelection()
            com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy$FallbackOptions r0 = com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectionUtil.createFallbackOptions(r0)
            com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy$FallbackSelection r6 = r7.getFallbackSelectionFor(r0, r6)
            if (r6 == 0) goto L_0x002b
            int r7 = r6.type
            r0 = 2
            if (r7 != r0) goto L_0x002b
            long r6 = r6.exclusionDurationMs
            goto L_0x002c
        L_0x002b:
            r6 = r2
        L_0x002c:
            com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource r4 = r4.chunkSource
            boolean r4 = r4.onPlaylistError(r5, r6)
            if (r4 == 0) goto L_0x0039
            int r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0039
            goto L_0x003a
        L_0x0039:
            r1 = 0
        L_0x003a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.hls.HlsSampleStreamWrapper.onPlaylistError(android.net.Uri, com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy$LoadErrorInfo, boolean):boolean");
    }

    public void onPlaylistUpdated() {
        if (!this.mediaChunks.isEmpty()) {
            HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) Iterables.getLast(this.mediaChunks);
            int chunkPublicationState = this.chunkSource.getChunkPublicationState(hlsMediaChunk);
            if (chunkPublicationState == 1) {
                hlsMediaChunk.publish();
            } else if (chunkPublicationState == 2 && !this.loadingFinished && this.loader.isLoading()) {
                this.loader.cancelLoading();
            }
        }
    }

    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void prepareWithMultivariantPlaylistInfo(TrackGroup[] trackGroupArr, int i3, int... iArr) {
        this.trackGroups = createTrackGroupArrayWithDrmInfo(trackGroupArr);
        this.optionalTrackGroups = new HashSet();
        for (int i4 : iArr) {
            this.optionalTrackGroups.add(this.trackGroups.get(i4));
        }
        this.primaryTrackGroupIndex = i3;
        Handler handler2 = this.handler;
        Callback callback2 = this.callback;
        Objects.requireNonNull(callback2);
        handler2.post(new a(callback2, 2));
        setIsPrepared();
    }

    public int readData(int i3, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i4) {
        if (isPendingReset()) {
            return -3;
        }
        int i5 = 0;
        if (!this.mediaChunks.isEmpty()) {
            int i6 = 0;
            while (i6 < this.mediaChunks.size() - 1 && finishedReadingChunk(this.mediaChunks.get(i6))) {
                i6++;
            }
            Util.removeRange(this.mediaChunks, 0, i6);
            HlsMediaChunk hlsMediaChunk = this.mediaChunks.get(0);
            Format format = hlsMediaChunk.trackFormat;
            if (!format.equals(this.downstreamTrackFormat)) {
                this.mediaSourceEventDispatcher.downstreamFormatChanged(this.trackType, format, hlsMediaChunk.trackSelectionReason, hlsMediaChunk.trackSelectionData, hlsMediaChunk.startTimeUs);
            }
            this.downstreamTrackFormat = format;
        }
        if (!this.mediaChunks.isEmpty() && !this.mediaChunks.get(0).isPublished()) {
            return -3;
        }
        int read = this.sampleQueues[i3].read(formatHolder, decoderInputBuffer, i4, this.loadingFinished);
        if (read == -5) {
            Format format2 = (Format) Assertions.checkNotNull(formatHolder.format);
            if (i3 == this.primarySampleQueueIndex) {
                int peekSourceId = this.sampleQueues[i3].peekSourceId();
                while (i5 < this.mediaChunks.size() && this.mediaChunks.get(i5).uid != peekSourceId) {
                    i5++;
                }
                format2 = format2.withManifestFormatInfo(i5 < this.mediaChunks.size() ? this.mediaChunks.get(i5).trackFormat : (Format) Assertions.checkNotNull(this.upstreamTrackFormat));
            }
            formatHolder.format = format2;
        }
        return read;
    }

    public void reevaluateBuffer(long j2) {
        if (!this.loader.hasFatalError() && !isPendingReset()) {
            if (this.loader.isLoading()) {
                Assertions.checkNotNull(this.loadingChunk);
                if (this.chunkSource.shouldCancelLoad(j2, this.loadingChunk, this.readOnlyMediaChunks)) {
                    this.loader.cancelLoading();
                    return;
                }
                return;
            }
            int size = this.readOnlyMediaChunks.size();
            while (size > 0 && this.chunkSource.getChunkPublicationState(this.readOnlyMediaChunks.get(size - 1)) == 2) {
                size--;
            }
            if (size < this.readOnlyMediaChunks.size()) {
                discardUpstream(size);
            }
            int preferredQueueSize = this.chunkSource.getPreferredQueueSize(j2, this.readOnlyMediaChunks);
            if (preferredQueueSize < this.mediaChunks.size()) {
                discardUpstream(preferredQueueSize);
            }
        }
    }

    public void release() {
        if (this.prepared) {
            for (HlsSampleQueue preRelease : this.sampleQueues) {
                preRelease.preRelease();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages((Object) null);
        this.released = true;
        this.hlsSampleStreams.clear();
    }

    public void seekMap(SeekMap seekMap) {
    }

    public boolean seekToUs(long j2, boolean z2) {
        this.lastSeekPositionUs = j2;
        if (isPendingReset()) {
            this.pendingResetPositionUs = j2;
            return true;
        }
        if (this.sampleQueuesBuilt && !z2 && seekInsideBufferUs(j2)) {
            return false;
        }
        this.pendingResetPositionUs = j2;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        if (this.loader.isLoading()) {
            if (this.sampleQueuesBuilt) {
                for (HlsSampleQueue discardToEnd : this.sampleQueues) {
                    discardToEnd.discardToEnd();
                }
            }
            this.loader.cancelLoading();
        } else {
            this.loader.clearFatalError();
            resetSampleQueues();
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x011a, code lost:
        if (r18.getSelectedIndexInTrackGroup() != r0.chunkSource.getTrackGroup().indexOf(r1.trackFormat)) goto L_0x011c;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean selectTracks(com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection[] r20, boolean[] r21, com.appsamurai.storyly.exoplayer2.core.source.SampleStream[] r22, boolean[] r23, long r24, boolean r26) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r22
            r12 = r24
            r19.assertIsPrepared()
            int r3 = r0.enabledTrackGroupCount
            r14 = 0
            r4 = r14
        L_0x000f:
            int r5 = r1.length
            r6 = 0
            r15 = 1
            if (r4 >= r5) goto L_0x002f
            r5 = r2[r4]
            com.appsamurai.storyly.exoplayer2.hls.HlsSampleStream r5 = (com.appsamurai.storyly.exoplayer2.hls.HlsSampleStream) r5
            if (r5 == 0) goto L_0x002c
            r7 = r1[r4]
            if (r7 == 0) goto L_0x0022
            boolean r7 = r21[r4]
            if (r7 != 0) goto L_0x002c
        L_0x0022:
            int r7 = r0.enabledTrackGroupCount
            int r7 = r7 - r15
            r0.enabledTrackGroupCount = r7
            r5.unbindSampleQueue()
            r2[r4] = r6
        L_0x002c:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x002f:
            if (r26 != 0) goto L_0x0041
            boolean r4 = r0.seenFirstTrackSelection
            if (r4 == 0) goto L_0x0038
            if (r3 != 0) goto L_0x003f
            goto L_0x0041
        L_0x0038:
            long r3 = r0.lastSeekPositionUs
            int r3 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x003f
            goto L_0x0041
        L_0x003f:
            r3 = r14
            goto L_0x0042
        L_0x0041:
            r3 = r15
        L_0x0042:
            com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource r4 = r0.chunkSource
            com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection r4 = r4.getTrackSelection()
            r16 = r3
            r11 = r4
            r3 = r14
        L_0x004c:
            int r5 = r1.length
            if (r3 >= r5) goto L_0x009f
            r5 = r1[r3]
            if (r5 != 0) goto L_0x0054
            goto L_0x009c
        L_0x0054:
            com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray r7 = r0.trackGroups
            com.appsamurai.storyly.exoplayer2.common.source.TrackGroup r8 = r5.getTrackGroup()
            int r7 = r7.indexOf(r8)
            int r8 = r0.primaryTrackGroupIndex
            if (r7 != r8) goto L_0x0068
            com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource r8 = r0.chunkSource
            r8.setTrackSelection(r5)
            r11 = r5
        L_0x0068:
            r5 = r2[r3]
            if (r5 != 0) goto L_0x009c
            int r5 = r0.enabledTrackGroupCount
            int r5 = r5 + r15
            r0.enabledTrackGroupCount = r5
            com.appsamurai.storyly.exoplayer2.hls.HlsSampleStream r5 = new com.appsamurai.storyly.exoplayer2.hls.HlsSampleStream
            r5.<init>(r0, r7)
            r2[r3] = r5
            r23[r3] = r15
            int[] r8 = r0.trackGroupToSampleQueueIndex
            if (r8 == 0) goto L_0x009c
            r5.bindSampleQueue()
            if (r16 != 0) goto L_0x009c
            com.appsamurai.storyly.exoplayer2.hls.HlsSampleStreamWrapper$HlsSampleQueue[] r5 = r0.sampleQueues
            int[] r8 = r0.trackGroupToSampleQueueIndex
            r7 = r8[r7]
            r5 = r5[r7]
            boolean r7 = r5.seekTo(r12, r15)
            if (r7 != 0) goto L_0x0099
            int r5 = r5.getReadIndex()
            if (r5 == 0) goto L_0x0099
            r5 = r15
            goto L_0x009a
        L_0x0099:
            r5 = r14
        L_0x009a:
            r16 = r5
        L_0x009c:
            int r3 = r3 + 1
            goto L_0x004c
        L_0x009f:
            int r1 = r0.enabledTrackGroupCount
            if (r1 != 0) goto L_0x00d5
            com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource r1 = r0.chunkSource
            r1.reset()
            r0.downstreamTrackFormat = r6
            r0.pendingResetUpstreamFormats = r15
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.hls.HlsMediaChunk> r1 = r0.mediaChunks
            r1.clear()
            com.appsamurai.storyly.exoplayer2.core.upstream.Loader r1 = r0.loader
            boolean r1 = r1.isLoading()
            if (r1 == 0) goto L_0x00d1
            boolean r1 = r0.sampleQueuesBuilt
            if (r1 == 0) goto L_0x00ca
            com.appsamurai.storyly.exoplayer2.hls.HlsSampleStreamWrapper$HlsSampleQueue[] r1 = r0.sampleQueues
            int r3 = r1.length
        L_0x00c0:
            if (r14 >= r3) goto L_0x00ca
            r4 = r1[r14]
            r4.discardToEnd()
            int r14 = r14 + 1
            goto L_0x00c0
        L_0x00ca:
            com.appsamurai.storyly.exoplayer2.core.upstream.Loader r1 = r0.loader
            r1.cancelLoading()
            goto L_0x0135
        L_0x00d1:
            r19.resetSampleQueues()
            goto L_0x0135
        L_0x00d5:
            java.util.ArrayList<com.appsamurai.storyly.exoplayer2.hls.HlsMediaChunk> r1 = r0.mediaChunks
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0122
            boolean r1 = com.appsamurai.storyly.exoplayer2.common.util.Util.areEqual(r11, r4)
            if (r1 != 0) goto L_0x0122
            boolean r1 = r0.seenFirstTrackSelection
            if (r1 != 0) goto L_0x011c
            r3 = 0
            int r1 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x00ee
            long r3 = -r12
        L_0x00ee:
            r6 = r3
            com.appsamurai.storyly.exoplayer2.hls.HlsMediaChunk r1 = r19.getLastMediaChunk()
            com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource r3 = r0.chunkSource
            com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunkIterator[] r17 = r3.createMediaChunkIterators(r1, r12)
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.util.List<com.appsamurai.storyly.exoplayer2.hls.HlsMediaChunk> r10 = r0.readOnlyMediaChunks
            r3 = r11
            r4 = r24
            r18 = r11
            r11 = r17
            r3.updateSelectedTrack(r4, r6, r8, r10, r11)
            com.appsamurai.storyly.exoplayer2.hls.HlsChunkSource r3 = r0.chunkSource
            com.appsamurai.storyly.exoplayer2.common.source.TrackGroup r3 = r3.getTrackGroup()
            com.appsamurai.storyly.exoplayer2.common.Format r1 = r1.trackFormat
            int r1 = r3.indexOf(r1)
            int r3 = r18.getSelectedIndexInTrackGroup()
            if (r3 == r1) goto L_0x0122
        L_0x011c:
            r0.pendingResetUpstreamFormats = r15
            r1 = r15
            r16 = r1
            goto L_0x0124
        L_0x0122:
            r1 = r26
        L_0x0124:
            if (r16 == 0) goto L_0x0135
            r0.seekToUs(r12, r1)
        L_0x0129:
            int r1 = r2.length
            if (r14 >= r1) goto L_0x0135
            r1 = r2[r14]
            if (r1 == 0) goto L_0x0132
            r23[r14] = r15
        L_0x0132:
            int r14 = r14 + 1
            goto L_0x0129
        L_0x0135:
            r0.updateSampleStreams(r2)
            r0.seenFirstTrackSelection = r15
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.hls.HlsSampleStreamWrapper.selectTracks(com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection[], boolean[], com.appsamurai.storyly.exoplayer2.core.source.SampleStream[], boolean[], long, boolean):boolean");
    }

    public void setDrmInitData(@Nullable DrmInitData drmInitData2) {
        if (!Util.areEqual(this.drmInitData, drmInitData2)) {
            this.drmInitData = drmInitData2;
            int i3 = 0;
            while (true) {
                HlsSampleQueue[] hlsSampleQueueArr = this.sampleQueues;
                if (i3 < hlsSampleQueueArr.length) {
                    if (this.sampleQueueIsAudioVideoFlags[i3]) {
                        hlsSampleQueueArr[i3].setDrmInitData(drmInitData2);
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public void setIsTimestampMaster(boolean z2) {
        this.chunkSource.setIsTimestampMaster(z2);
    }

    public void setSampleOffsetUs(long j2) {
        if (this.sampleOffsetUs != j2) {
            this.sampleOffsetUs = j2;
            for (HlsSampleQueue sampleOffsetUs2 : this.sampleQueues) {
                sampleOffsetUs2.setSampleOffsetUs(j2);
            }
        }
    }

    public int skipData(int i3, long j2) {
        if (isPendingReset()) {
            return 0;
        }
        HlsSampleQueue hlsSampleQueue = this.sampleQueues[i3];
        int skipCount = hlsSampleQueue.getSkipCount(j2, this.loadingFinished);
        HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) Iterables.getLast(this.mediaChunks, null);
        if (hlsMediaChunk != null && !hlsMediaChunk.isPublished()) {
            skipCount = Math.min(skipCount, hlsMediaChunk.getFirstSampleIndex(i3) - hlsSampleQueue.getReadIndex());
        }
        hlsSampleQueue.skip(skipCount);
        return skipCount;
    }

    public TrackOutput track(int i3, int i4) {
        TrackOutput trackOutput;
        if (!MAPPABLE_TYPES.contains(Integer.valueOf(i4))) {
            int i5 = 0;
            while (true) {
                TrackOutput[] trackOutputArr = this.sampleQueues;
                if (i5 >= trackOutputArr.length) {
                    trackOutput = null;
                    break;
                } else if (this.sampleQueueTrackIds[i5] == i3) {
                    trackOutput = trackOutputArr[i5];
                    break;
                } else {
                    i5++;
                }
            }
        } else {
            trackOutput = getMappedTrackOutput(i3, i4);
        }
        if (trackOutput == null) {
            if (this.tracksEnded) {
                return createFakeTrackOutput(i3, i4);
            }
            trackOutput = createSampleQueue(i3, i4);
        }
        if (i4 != 5) {
            return trackOutput;
        }
        if (this.emsgUnwrappingTrackOutput == null) {
            this.emsgUnwrappingTrackOutput = new EmsgUnwrappingTrackOutput(trackOutput, this.metadataType);
        }
        return this.emsgUnwrappingTrackOutput;
    }

    public void unbindSampleQueue(int i3) {
        assertIsPrepared();
        Assertions.checkNotNull(this.trackGroupToSampleQueueIndex);
        int i4 = this.trackGroupToSampleQueueIndex[i3];
        Assertions.checkState(this.sampleQueuesEnabledStates[i4]);
        this.sampleQueuesEnabledStates[i4] = false;
    }

    public void onLoadCanceled(Chunk chunk, long j2, long j3, boolean z2) {
        Chunk chunk2 = chunk;
        this.loadingChunk = null;
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk2.loadTaskId, chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j2, j3, chunk.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk2.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, chunk2.type, this.trackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs);
        if (!z2) {
            if (isPendingReset() || this.enabledTrackGroupCount == 0) {
                resetSampleQueues();
            }
            if (this.enabledTrackGroupCount > 0) {
                this.callback.onContinueLoadingRequested(this);
            }
        }
    }

    public void onLoadCompleted(Chunk chunk, long j2, long j3) {
        Chunk chunk2 = chunk;
        this.loadingChunk = null;
        this.chunkSource.onChunkLoadCompleted(chunk2);
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk2.loadTaskId, chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j2, j3, chunk.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk2.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, chunk2.type, this.trackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs);
        if (!this.prepared) {
            continueLoading(this.lastSeekPositionUs);
        } else {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public Loader.LoadErrorAction onLoadError(Chunk chunk, long j2, long j3, IOException iOException, int i3) {
        Loader.LoadErrorAction loadErrorAction;
        int i4;
        Chunk chunk2 = chunk;
        IOException iOException2 = iOException;
        boolean isMediaChunk = isMediaChunk(chunk);
        if (isMediaChunk && !((HlsMediaChunk) chunk2).isPublished() && (iOException2 instanceof HttpDataSource.InvalidResponseCodeException) && ((i4 = ((HttpDataSource.InvalidResponseCodeException) iOException2).responseCode) == 410 || i4 == 404)) {
            return Loader.RETRY;
        }
        long bytesLoaded = chunk.bytesLoaded();
        LoadEventInfo loadEventInfo = new LoadEventInfo(chunk2.loadTaskId, chunk2.dataSpec, chunk.getUri(), chunk.getResponseHeaders(), j2, j3, bytesLoaded);
        LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo = new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(chunk2.type, this.trackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, Util.usToMs(chunk2.startTimeUs), Util.usToMs(chunk2.endTimeUs)), iOException2, i3);
        LoadErrorHandlingPolicy.FallbackSelection fallbackSelectionFor = this.loadErrorHandlingPolicy.getFallbackSelectionFor(TrackSelectionUtil.createFallbackOptions(this.chunkSource.getTrackSelection()), loadErrorInfo);
        boolean z2 = false;
        boolean maybeExcludeTrack = (fallbackSelectionFor == null || fallbackSelectionFor.type != 2) ? false : this.chunkSource.maybeExcludeTrack(chunk2, fallbackSelectionFor.exclusionDurationMs);
        if (maybeExcludeTrack) {
            if (isMediaChunk && bytesLoaded == 0) {
                ArrayList<HlsMediaChunk> arrayList = this.mediaChunks;
                if (arrayList.remove(arrayList.size() - 1) == chunk2) {
                    z2 = true;
                }
                Assertions.checkState(z2);
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                } else {
                    ((HlsMediaChunk) Iterables.getLast(this.mediaChunks)).invalidateExtractor();
                }
            }
            loadErrorAction = Loader.DONT_RETRY;
        } else {
            long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(loadErrorInfo);
            if (retryDelayMsFor != C.TIME_UNSET) {
                loadErrorAction = Loader.createRetryAction(false, retryDelayMsFor);
            } else {
                loadErrorAction = Loader.DONT_RETRY_FATAL;
            }
        }
        Loader.LoadErrorAction loadErrorAction2 = loadErrorAction;
        boolean isRetry = loadErrorAction2.isRetry();
        this.mediaSourceEventDispatcher.loadError(loadEventInfo, chunk2.type, this.trackType, chunk2.trackFormat, chunk2.trackSelectionReason, chunk2.trackSelectionData, chunk2.startTimeUs, chunk2.endTimeUs, iOException, !isRetry);
        if (!isRetry) {
            this.loadingChunk = null;
            this.loadErrorHandlingPolicy.onLoadTaskConcluded(chunk2.loadTaskId);
        }
        if (maybeExcludeTrack) {
            if (!this.prepared) {
                continueLoading(this.lastSeekPositionUs);
            } else {
                this.callback.onContinueLoadingRequested(this);
            }
        }
        return loadErrorAction2;
    }

    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        this.chunkSource.maybeThrowError();
    }

    public static class EmsgUnwrappingTrackOutput implements TrackOutput {
        private static final Format EMSG_FORMAT = new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_EMSG).build();
        private static final Format ID3_FORMAT = new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_ID3).build();
        private byte[] buffer;
        private int bufferPosition;
        private final TrackOutput delegate;
        private final Format delegateFormat;
        private final EventMessageDecoder emsgDecoder = new EventMessageDecoder();
        private Format format;

        public EmsgUnwrappingTrackOutput(TrackOutput trackOutput, int i3) {
            this.delegate = trackOutput;
            if (i3 == 1) {
                this.delegateFormat = ID3_FORMAT;
            } else if (i3 == 3) {
                this.delegateFormat = EMSG_FORMAT;
            } else {
                throw new IllegalArgumentException(A.a.k("Unknown metadataType: ", i3));
            }
            this.buffer = new byte[0];
            this.bufferPosition = 0;
        }

        private boolean emsgContainsExpectedWrappedFormat(EventMessage eventMessage) {
            Format wrappedMetadataFormat = eventMessage.getWrappedMetadataFormat();
            return wrappedMetadataFormat != null && Util.areEqual(this.delegateFormat.sampleMimeType, wrappedMetadataFormat.sampleMimeType);
        }

        private void ensureBufferCapacity(int i3) {
            byte[] bArr = this.buffer;
            if (bArr.length < i3) {
                this.buffer = Arrays.copyOf(bArr, (i3 / 2) + i3);
            }
        }

        private ParsableByteArray getSampleAndTrimBuffer(int i3, int i4) {
            int i5 = this.bufferPosition - i4;
            ParsableByteArray parsableByteArray = new ParsableByteArray(Arrays.copyOfRange(this.buffer, i5 - i3, i5));
            byte[] bArr = this.buffer;
            System.arraycopy(bArr, i5, bArr, 0, i4);
            this.bufferPosition = i4;
            return parsableByteArray;
        }

        public void format(Format format2) {
            this.format = format2;
            this.delegate.format(this.delegateFormat);
        }

        public int sampleData(DataReader dataReader, int i3, boolean z2, int i4) throws IOException {
            ensureBufferCapacity(this.bufferPosition + i3);
            int read = dataReader.read(this.buffer, this.bufferPosition, i3);
            if (read != -1) {
                this.bufferPosition += read;
                return read;
            } else if (z2) {
                return -1;
            } else {
                throw new EOFException();
            }
        }

        public void sampleMetadata(long j2, int i3, int i4, int i5, @Nullable TrackOutput.CryptoData cryptoData) {
            Assertions.checkNotNull(this.format);
            ParsableByteArray sampleAndTrimBuffer = getSampleAndTrimBuffer(i4, i5);
            if (!Util.areEqual(this.format.sampleMimeType, this.delegateFormat.sampleMimeType)) {
                if (MimeTypes.APPLICATION_EMSG.equals(this.format.sampleMimeType)) {
                    EventMessage decode = this.emsgDecoder.decode(sampleAndTrimBuffer);
                    if (!emsgContainsExpectedWrappedFormat(decode)) {
                        String str = this.delegateFormat.sampleMimeType;
                        Format wrappedMetadataFormat = decode.getWrappedMetadataFormat();
                        Log.w(HlsSampleStreamWrapper.TAG, "Ignoring EMSG. Expected it to contain wrapped " + str + " but actual wrapped format: " + wrappedMetadataFormat);
                        return;
                    }
                    sampleAndTrimBuffer = new ParsableByteArray((byte[]) Assertions.checkNotNull(decode.getWrappedMetadataBytes()));
                } else {
                    Log.w(HlsSampleStreamWrapper.TAG, "Ignoring sample for unsupported format: " + this.format.sampleMimeType);
                    return;
                }
            }
            int bytesLeft = sampleAndTrimBuffer.bytesLeft();
            this.delegate.sampleData(sampleAndTrimBuffer, bytesLeft);
            this.delegate.sampleMetadata(j2, i3, bytesLeft, i5, cryptoData);
        }

        public void sampleData(ParsableByteArray parsableByteArray, int i3, int i4) {
            ensureBufferCapacity(this.bufferPosition + i3);
            parsableByteArray.readBytes(this.buffer, this.bufferPosition, i3);
            this.bufferPosition += i3;
        }
    }
}
