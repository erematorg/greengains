package com.appsamurai.storyly.exoplayer2.hls;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.UriUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.source.BehindLiveWindowException;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.BaseMediaChunkIterator;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.Chunk;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.DataChunk;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunkIterator;
import com.appsamurai.storyly.exoplayer2.core.trackselection.BaseTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsMediaPlaylist;
import com.appsamurai.storyly.exoplayer2.hls.playlist.HlsPlaylistTracker;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class HlsChunkSource {
    public static final int CHUNK_PUBLICATION_STATE_PRELOAD = 0;
    public static final int CHUNK_PUBLICATION_STATE_PUBLISHED = 1;
    public static final int CHUNK_PUBLICATION_STATE_REMOVED = 2;
    private static final int KEY_CACHE_SIZE = 4;
    private final DataSource encryptionDataSource;
    @Nullable
    private Uri expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    @Nullable
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private final FullSegmentEncryptionKeyCache keyCache = new FullSegmentEncryptionKeyCache(4);
    private long liveEdgeInPeriodTimeUs = C.TIME_UNSET;
    private final DataSource mediaDataSource;
    @Nullable
    private final List<Format> muxedCaptionFormats;
    private final PlayerId playerId;
    private final Format[] playlistFormats;
    private final HlsPlaylistTracker playlistTracker;
    private final Uri[] playlistUrls;
    private byte[] scratchSpace = Util.EMPTY_BYTE_ARRAY;
    private boolean seenExpectedPlaylistError;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private ExoTrackSelection trackSelection;

    public static final class EncryptionKeyChunk extends DataChunk {
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i3, @Nullable Object obj, byte[] bArr) {
            super(dataSource, dataSpec, 3, format, i3, obj, bArr);
        }

        public void consume(byte[] bArr, int i3) {
            this.result = Arrays.copyOf(bArr, i3);
        }

        @Nullable
        public byte[] getResult() {
            return this.result;
        }
    }

    public static final class HlsChunkHolder {
        @Nullable
        public Chunk chunk;
        public boolean endOfStream;
        @Nullable
        public Uri playlistUrl;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlistUrl = null;
        }
    }

    @VisibleForTesting
    public static final class HlsMediaPlaylistSegmentIterator extends BaseMediaChunkIterator {
        private final String playlistBaseUri;
        private final List<HlsMediaPlaylist.SegmentBase> segmentBases;
        private final long startOfPlaylistInPeriodUs;

        public HlsMediaPlaylistSegmentIterator(String str, long j2, List<HlsMediaPlaylist.SegmentBase> list) {
            super(0, (long) (list.size() - 1));
            this.playlistBaseUri = str;
            this.startOfPlaylistInPeriodUs = j2;
            this.segmentBases = list;
        }

        public long getChunkEndTimeUs() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase segmentBase = this.segmentBases.get((int) getCurrentIndex());
            return this.startOfPlaylistInPeriodUs + segmentBase.relativeStartTimeUs + segmentBase.durationUs;
        }

        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.startOfPlaylistInPeriodUs + this.segmentBases.get((int) getCurrentIndex()).relativeStartTimeUs;
        }

        public DataSpec getDataSpec() {
            checkInBounds();
            HlsMediaPlaylist.SegmentBase segmentBase = this.segmentBases.get((int) getCurrentIndex());
            return new DataSpec(UriUtil.resolveToUri(this.playlistBaseUri, segmentBase.url), segmentBase.byteRangeOffset, segmentBase.byteRangeLength);
        }
    }

    public static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.selectedIndex = indexOf(trackGroup.getFormat(iArr[0]));
        }

        public int getSelectedIndex() {
            return this.selectedIndex;
        }

        @Nullable
        public Object getSelectionData() {
            return null;
        }

        public int getSelectionReason() {
            return 0;
        }

        public void updateSelectedTrack(long j2, long j3, long j4, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (isBlacklisted(this.selectedIndex, elapsedRealtime)) {
                for (int i3 = this.length - 1; i3 >= 0; i3--) {
                    if (!isBlacklisted(i3, elapsedRealtime)) {
                        this.selectedIndex = i3;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }
    }

    public static final class SegmentBaseHolder {
        public final boolean isPreload;
        public final long mediaSequence;
        public final int partIndex;
        public final HlsMediaPlaylist.SegmentBase segmentBase;

        public SegmentBaseHolder(HlsMediaPlaylist.SegmentBase segmentBase2, long j2, int i3) {
            this.segmentBase = segmentBase2;
            this.mediaSequence = j2;
            this.partIndex = i3;
            this.isPreload = (segmentBase2 instanceof HlsMediaPlaylist.Part) && ((HlsMediaPlaylist.Part) segmentBase2).isPreload;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, Uri[] uriArr, Format[] formatArr, HlsDataSourceFactory hlsDataSourceFactory, @Nullable TransferListener transferListener, TimestampAdjusterProvider timestampAdjusterProvider2, @Nullable List<Format> list, PlayerId playerId2) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.playlistUrls = uriArr;
        this.playlistFormats = formatArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider2;
        this.muxedCaptionFormats = list;
        this.playerId = playerId2;
        DataSource createDataSource = hlsDataSourceFactory.createDataSource(1);
        this.mediaDataSource = createDataSource;
        if (transferListener != null) {
            createDataSource.addTransferListener(transferListener);
        }
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        this.trackGroup = new TrackGroup(formatArr);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < uriArr.length; i3++) {
            if ((formatArr[i3].roleFlags & 16384) == 0) {
                arrayList.add(Integer.valueOf(i3));
            }
        }
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, Ints.toArray(arrayList));
    }

    @Nullable
    private static Uri getFullEncryptionKeyUri(HlsMediaPlaylist hlsMediaPlaylist, @Nullable HlsMediaPlaylist.SegmentBase segmentBase) {
        String str;
        if (segmentBase == null || (str = segmentBase.fullSegmentEncryptionKeyUri) == null) {
            return null;
        }
        return UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, str);
    }

    private Pair<Long, Integer> getNextMediaSequenceAndPartIndex(@Nullable HlsMediaChunk hlsMediaChunk, boolean z2, HlsMediaPlaylist hlsMediaPlaylist, long j2, long j3) {
        int i3 = -1;
        if (hlsMediaChunk == null || z2) {
            long j4 = hlsMediaPlaylist.durationUs + j2;
            if (hlsMediaChunk != null && !this.independentSegments) {
                j3 = hlsMediaChunk.startTimeUs;
            }
            if (!hlsMediaPlaylist.hasEndTag && j3 >= j4) {
                return new Pair<>(Long.valueOf(hlsMediaPlaylist.mediaSequence + ((long) hlsMediaPlaylist.segments.size())), -1);
            }
            long j5 = j3 - j2;
            int i4 = 0;
            int binarySearchFloor = Util.binarySearchFloor(hlsMediaPlaylist.segments, Long.valueOf(j5), true, !this.playlistTracker.isLive() || hlsMediaChunk == null);
            long j6 = ((long) binarySearchFloor) + hlsMediaPlaylist.mediaSequence;
            if (binarySearchFloor >= 0) {
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(binarySearchFloor);
                List<HlsMediaPlaylist.Part> list = j5 < segment.relativeStartTimeUs + segment.durationUs ? segment.parts : hlsMediaPlaylist.trailingParts;
                while (true) {
                    if (i4 >= list.size()) {
                        break;
                    }
                    HlsMediaPlaylist.Part part = list.get(i4);
                    if (j5 >= part.relativeStartTimeUs + part.durationUs) {
                        i4++;
                    } else if (part.isIndependent) {
                        j6 += list == hlsMediaPlaylist.trailingParts ? 1 : 0;
                        i3 = i4;
                    }
                }
            }
            return new Pair<>(Long.valueOf(j6), Integer.valueOf(i3));
        } else if (!hlsMediaChunk.isLoadCompleted()) {
            return new Pair<>(Long.valueOf(hlsMediaChunk.chunkIndex), Integer.valueOf(hlsMediaChunk.partIndex));
        } else {
            Long valueOf = Long.valueOf(hlsMediaChunk.partIndex == -1 ? hlsMediaChunk.getNextChunkIndex() : hlsMediaChunk.chunkIndex);
            int i5 = hlsMediaChunk.partIndex;
            if (i5 != -1) {
                i3 = i5 + 1;
            }
            return new Pair<>(valueOf, Integer.valueOf(i3));
        }
    }

    @Nullable
    private static SegmentBaseHolder getNextSegmentHolder(HlsMediaPlaylist hlsMediaPlaylist, long j2, int i3) {
        int i4 = (int) (j2 - hlsMediaPlaylist.mediaSequence);
        if (i4 == hlsMediaPlaylist.segments.size()) {
            if (i3 == -1) {
                i3 = 0;
            }
            if (i3 < hlsMediaPlaylist.trailingParts.size()) {
                return new SegmentBaseHolder(hlsMediaPlaylist.trailingParts.get(i3), j2, i3);
            }
            return null;
        }
        HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i4);
        if (i3 == -1) {
            return new SegmentBaseHolder(segment, j2, -1);
        }
        if (i3 < segment.parts.size()) {
            return new SegmentBaseHolder(segment.parts.get(i3), j2, i3);
        }
        int i5 = i4 + 1;
        if (i5 < hlsMediaPlaylist.segments.size()) {
            return new SegmentBaseHolder(hlsMediaPlaylist.segments.get(i5), j2 + 1, -1);
        }
        if (!hlsMediaPlaylist.trailingParts.isEmpty()) {
            return new SegmentBaseHolder(hlsMediaPlaylist.trailingParts.get(0), j2 + 1, 0);
        }
        return null;
    }

    @VisibleForTesting
    public static List<HlsMediaPlaylist.SegmentBase> getSegmentBaseList(HlsMediaPlaylist hlsMediaPlaylist, long j2, int i3) {
        int i4 = (int) (j2 - hlsMediaPlaylist.mediaSequence);
        if (i4 < 0 || hlsMediaPlaylist.segments.size() < i4) {
            return ImmutableList.of();
        }
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        if (i4 < hlsMediaPlaylist.segments.size()) {
            if (i3 != -1) {
                HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i4);
                if (i3 == 0) {
                    arrayList.add(segment);
                } else if (i3 < segment.parts.size()) {
                    List<HlsMediaPlaylist.Part> list = segment.parts;
                    arrayList.addAll(list.subList(i3, list.size()));
                }
                i4++;
            }
            List<HlsMediaPlaylist.Segment> list2 = hlsMediaPlaylist.segments;
            arrayList.addAll(list2.subList(i4, list2.size()));
            i3 = 0;
        }
        if (hlsMediaPlaylist.partTargetDurationUs != C.TIME_UNSET) {
            if (i3 != -1) {
                i5 = i3;
            }
            if (i5 < hlsMediaPlaylist.trailingParts.size()) {
                List<HlsMediaPlaylist.Part> list3 = hlsMediaPlaylist.trailingParts;
                arrayList.addAll(list3.subList(i5, list3.size()));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Nullable
    private Chunk maybeCreateEncryptionChunkFor(@Nullable Uri uri, int i3) {
        if (uri == null) {
            return null;
        }
        byte[] remove = this.keyCache.remove(uri);
        if (remove != null) {
            this.keyCache.put(uri, remove);
            return null;
        }
        return new EncryptionKeyChunk(this.encryptionDataSource, new DataSpec.Builder().setUri(uri).setFlags(1).build(), this.playlistFormats[i3], this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.scratchSpace);
    }

    private long resolveTimeToLiveEdgeUs(long j2) {
        long j3 = this.liveEdgeInPeriodTimeUs;
        return j3 != C.TIME_UNSET ? j3 - j2 : C.TIME_UNSET;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        this.liveEdgeInPeriodTimeUs = hlsMediaPlaylist.hasEndTag ? C.TIME_UNSET : hlsMediaPlaylist.getEndTimeUs() - this.playlistTracker.getInitialStartTimeUs();
    }

    public MediaChunkIterator[] createMediaChunkIterators(@Nullable HlsMediaChunk hlsMediaChunk, long j2) {
        int i3;
        HlsMediaChunk hlsMediaChunk2 = hlsMediaChunk;
        int indexOf = hlsMediaChunk2 == null ? -1 : this.trackGroup.indexOf(hlsMediaChunk2.trackFormat);
        int length = this.trackSelection.length();
        MediaChunkIterator[] mediaChunkIteratorArr = new MediaChunkIterator[length];
        boolean z2 = false;
        int i4 = 0;
        while (i4 < length) {
            int indexInTrackGroup = this.trackSelection.getIndexInTrackGroup(i4);
            Uri uri = this.playlistUrls[indexInTrackGroup];
            if (!this.playlistTracker.isSnapshotValid(uri)) {
                mediaChunkIteratorArr[i4] = MediaChunkIterator.EMPTY;
                i3 = i4;
            } else {
                HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(uri, z2);
                Assertions.checkNotNull(playlistSnapshot);
                long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
                i3 = i4;
                Pair<Long, Integer> nextMediaSequenceAndPartIndex = getNextMediaSequenceAndPartIndex(hlsMediaChunk, indexInTrackGroup != indexOf ? true : z2, playlistSnapshot, initialStartTimeUs, j2);
                mediaChunkIteratorArr[i3] = new HlsMediaPlaylistSegmentIterator(playlistSnapshot.baseUri, initialStartTimeUs, getSegmentBaseList(playlistSnapshot, ((Long) nextMediaSequenceAndPartIndex.first).longValue(), ((Integer) nextMediaSequenceAndPartIndex.second).intValue()));
            }
            i4 = i3 + 1;
            z2 = false;
        }
        return mediaChunkIteratorArr;
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        int selectedIndex = this.trackSelection.getSelectedIndex();
        Uri[] uriArr = this.playlistUrls;
        HlsMediaPlaylist playlistSnapshot = (selectedIndex >= uriArr.length || selectedIndex == -1) ? null : this.playlistTracker.getPlaylistSnapshot(uriArr[this.trackSelection.getSelectedIndexInTrackGroup()], true);
        if (playlistSnapshot == null || playlistSnapshot.segments.isEmpty() || !playlistSnapshot.hasIndependentSegments) {
            return j2;
        }
        long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        long j3 = j2 - initialStartTimeUs;
        int binarySearchFloor = Util.binarySearchFloor(playlistSnapshot.segments, Long.valueOf(j3), true, true);
        long j4 = playlistSnapshot.segments.get(binarySearchFloor).relativeStartTimeUs;
        return seekParameters.resolveSeekPositionUs(j3, j4, binarySearchFloor != playlistSnapshot.segments.size() - 1 ? playlistSnapshot.segments.get(binarySearchFloor + 1).relativeStartTimeUs : j4) + initialStartTimeUs;
    }

    public int getChunkPublicationState(HlsMediaChunk hlsMediaChunk) {
        if (hlsMediaChunk.partIndex == -1) {
            return 1;
        }
        HlsMediaPlaylist hlsMediaPlaylist = (HlsMediaPlaylist) Assertions.checkNotNull(this.playlistTracker.getPlaylistSnapshot(this.playlistUrls[this.trackGroup.indexOf(hlsMediaChunk.trackFormat)], false));
        int i3 = (int) (hlsMediaChunk.chunkIndex - hlsMediaPlaylist.mediaSequence);
        if (i3 < 0) {
            return 1;
        }
        List<HlsMediaPlaylist.Part> list = i3 < hlsMediaPlaylist.segments.size() ? hlsMediaPlaylist.segments.get(i3).parts : hlsMediaPlaylist.trailingParts;
        if (hlsMediaChunk.partIndex >= list.size()) {
            return 2;
        }
        HlsMediaPlaylist.Part part = list.get(hlsMediaChunk.partIndex);
        if (part.isPreload) {
            return 0;
        }
        return Util.areEqual(Uri.parse(UriUtil.resolve(hlsMediaPlaylist.baseUri, part.url)), hlsMediaChunk.dataSpec.uri) ? 1 : 2;
    }

    public void getNextChunk(long j2, long j3, List<HlsMediaChunk> list, boolean z2, HlsChunkHolder hlsChunkHolder) {
        long j4;
        Uri uri;
        HlsMediaPlaylist hlsMediaPlaylist;
        int i3;
        long j5 = j3;
        HlsChunkHolder hlsChunkHolder2 = hlsChunkHolder;
        HlsMediaChunk hlsMediaChunk = list.isEmpty() ? null : (HlsMediaChunk) Iterables.getLast(list);
        int indexOf = hlsMediaChunk == null ? -1 : this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        long j6 = j5 - j2;
        long resolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j2);
        if (hlsMediaChunk != null && !this.independentSegments) {
            long durationUs = hlsMediaChunk.getDurationUs();
            j6 = Math.max(0, j6 - durationUs);
            if (resolveTimeToLiveEdgeUs != C.TIME_UNSET) {
                resolveTimeToLiveEdgeUs = Math.max(0, resolveTimeToLiveEdgeUs - durationUs);
            }
        }
        this.trackSelection.updateSelectedTrack(j2, j6, resolveTimeToLiveEdgeUs, list, createMediaChunkIterators(hlsMediaChunk, j5));
        int selectedIndexInTrackGroup = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean z3 = indexOf != selectedIndexInTrackGroup;
        Uri uri2 = this.playlistUrls[selectedIndexInTrackGroup];
        if (!this.playlistTracker.isSnapshotValid(uri2)) {
            hlsChunkHolder2.playlistUrl = uri2;
            this.seenExpectedPlaylistError &= uri2.equals(this.expectedPlaylistUrl);
            this.expectedPlaylistUrl = uri2;
            return;
        }
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(uri2, true);
        Assertions.checkNotNull(playlistSnapshot);
        this.independentSegments = playlistSnapshot.hasIndependentSegments;
        updateLiveEdgeTimeUs(playlistSnapshot);
        long initialStartTimeUs = playlistSnapshot.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        HlsMediaPlaylist hlsMediaPlaylist2 = playlistSnapshot;
        Uri uri3 = uri2;
        int i4 = selectedIndexInTrackGroup;
        Pair<Long, Integer> nextMediaSequenceAndPartIndex = getNextMediaSequenceAndPartIndex(hlsMediaChunk, z3, playlistSnapshot, initialStartTimeUs, j3);
        long longValue = ((Long) nextMediaSequenceAndPartIndex.first).longValue();
        int intValue = ((Integer) nextMediaSequenceAndPartIndex.second).intValue();
        if (longValue >= playlistSnapshot.mediaSequence || hlsMediaChunk == null || !z3) {
            hlsMediaPlaylist = playlistSnapshot;
            j4 = initialStartTimeUs;
            uri = uri3;
            i3 = i4;
        } else {
            Uri uri4 = this.playlistUrls[indexOf];
            HlsMediaPlaylist playlistSnapshot2 = this.playlistTracker.getPlaylistSnapshot(uri4, true);
            Assertions.checkNotNull(playlistSnapshot2);
            j4 = playlistSnapshot2.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
            Pair<Long, Integer> nextMediaSequenceAndPartIndex2 = getNextMediaSequenceAndPartIndex(hlsMediaChunk, false, playlistSnapshot2, j4, j3);
            longValue = ((Long) nextMediaSequenceAndPartIndex2.first).longValue();
            intValue = ((Integer) nextMediaSequenceAndPartIndex2.second).intValue();
            i3 = indexOf;
            uri = uri4;
            hlsMediaPlaylist = playlistSnapshot2;
        }
        if (longValue < hlsMediaPlaylist.mediaSequence) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        SegmentBaseHolder nextSegmentHolder = getNextSegmentHolder(hlsMediaPlaylist, longValue, intValue);
        if (nextSegmentHolder == null) {
            if (!hlsMediaPlaylist.hasEndTag) {
                hlsChunkHolder2.playlistUrl = uri;
                this.seenExpectedPlaylistError &= uri.equals(this.expectedPlaylistUrl);
                this.expectedPlaylistUrl = uri;
                return;
            } else if (z2 || hlsMediaPlaylist.segments.isEmpty()) {
                hlsChunkHolder2.endOfStream = true;
                return;
            } else {
                nextSegmentHolder = new SegmentBaseHolder((HlsMediaPlaylist.SegmentBase) Iterables.getLast(hlsMediaPlaylist.segments), (hlsMediaPlaylist.mediaSequence + ((long) hlsMediaPlaylist.segments.size())) - 1, -1);
            }
        }
        this.seenExpectedPlaylistError = false;
        this.expectedPlaylistUrl = null;
        Uri fullEncryptionKeyUri = getFullEncryptionKeyUri(hlsMediaPlaylist, nextSegmentHolder.segmentBase.initializationSegment);
        Chunk maybeCreateEncryptionChunkFor = maybeCreateEncryptionChunkFor(fullEncryptionKeyUri, i3);
        hlsChunkHolder2.chunk = maybeCreateEncryptionChunkFor;
        if (maybeCreateEncryptionChunkFor == null) {
            Uri fullEncryptionKeyUri2 = getFullEncryptionKeyUri(hlsMediaPlaylist, nextSegmentHolder.segmentBase);
            Chunk maybeCreateEncryptionChunkFor2 = maybeCreateEncryptionChunkFor(fullEncryptionKeyUri2, i3);
            hlsChunkHolder2.chunk = maybeCreateEncryptionChunkFor2;
            if (maybeCreateEncryptionChunkFor2 == null) {
                boolean shouldSpliceIn = HlsMediaChunk.shouldSpliceIn(hlsMediaChunk, uri, hlsMediaPlaylist, nextSegmentHolder, j4);
                if (!shouldSpliceIn || !nextSegmentHolder.isPreload) {
                    hlsChunkHolder2.chunk = HlsMediaChunk.createInstance(this.extractorFactory, this.mediaDataSource, this.playlistFormats[i3], j4, hlsMediaPlaylist, nextSegmentHolder, uri, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), this.isTimestampMaster, this.timestampAdjusterProvider, hlsMediaChunk, this.keyCache.get(fullEncryptionKeyUri2), this.keyCache.get(fullEncryptionKeyUri), shouldSpliceIn, this.playerId);
                }
            }
        }
    }

    public int getPreferredQueueSize(long j2, List<? extends MediaChunk> list) {
        return (this.fatalError != null || this.trackSelection.length() < 2) ? list.size() : this.trackSelection.evaluateQueueSize(j2, list);
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public ExoTrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public boolean maybeExcludeTrack(Chunk chunk, long j2) {
        ExoTrackSelection exoTrackSelection = this.trackSelection;
        return exoTrackSelection.blacklist(exoTrackSelection.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), j2);
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            Uri uri = this.expectedPlaylistUrl;
            if (uri != null && this.seenExpectedPlaylistError) {
                this.playlistTracker.maybeThrowPlaylistRefreshError(uri);
                return;
            }
            return;
        }
        throw iOException;
    }

    public boolean obtainsChunksForPlaylist(Uri uri) {
        return Util.contains(this.playlistUrls, uri);
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            this.keyCache.put(encryptionKeyChunk.dataSpec.uri, (byte[]) Assertions.checkNotNull(encryptionKeyChunk.getResult()));
        }
    }

    public boolean onPlaylistError(Uri uri, long j2) {
        int indexOf;
        int i3 = 0;
        while (true) {
            Uri[] uriArr = this.playlistUrls;
            if (i3 >= uriArr.length) {
                i3 = -1;
                break;
            } else if (uriArr[i3].equals(uri)) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 == -1 || (indexOf = this.trackSelection.indexOf(i3)) == -1) {
            return true;
        }
        this.seenExpectedPlaylistError |= uri.equals(this.expectedPlaylistUrl);
        return j2 == C.TIME_UNSET || (this.trackSelection.blacklist(indexOf, j2) && this.playlistTracker.excludeMediaPlaylist(uri, j2));
    }

    public void reset() {
        this.fatalError = null;
    }

    public void setIsTimestampMaster(boolean z2) {
        this.isTimestampMaster = z2;
    }

    public void setTrackSelection(ExoTrackSelection exoTrackSelection) {
        this.trackSelection = exoTrackSelection;
    }

    public boolean shouldCancelLoad(long j2, Chunk chunk, List<? extends MediaChunk> list) {
        if (this.fatalError != null) {
            return false;
        }
        return this.trackSelection.shouldCancelChunkLoad(j2, chunk, list);
    }
}
