package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.offline.StreamKey;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.Chunk;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunkIterator;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    @Nullable
    private MediaPeriod.Callback callback;
    private final HashMap<TrackGroup, TrackGroup> childTrackGroupByMergedTrackGroup = new HashMap<>();
    private final ArrayList<MediaPeriod> childrenPendingPreparation = new ArrayList<>();
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private MediaPeriod[] enabledPeriods;
    private final MediaPeriod[] periods;
    private final IdentityHashMap<SampleStream, Integer> streamPeriodIndices;
    @Nullable
    private TrackGroupArray trackGroups;

    public static final class ForwardingTrackSelection implements ExoTrackSelection {
        private final TrackGroup trackGroup;
        private final ExoTrackSelection trackSelection;

        public ForwardingTrackSelection(ExoTrackSelection exoTrackSelection, TrackGroup trackGroup2) {
            this.trackSelection = exoTrackSelection;
            this.trackGroup = trackGroup2;
        }

        public boolean blacklist(int i3, long j2) {
            return this.trackSelection.blacklist(i3, j2);
        }

        public void disable() {
            this.trackSelection.disable();
        }

        public void enable() {
            this.trackSelection.enable();
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ForwardingTrackSelection)) {
                return false;
            }
            ForwardingTrackSelection forwardingTrackSelection = (ForwardingTrackSelection) obj;
            return this.trackSelection.equals(forwardingTrackSelection.trackSelection) && this.trackGroup.equals(forwardingTrackSelection.trackGroup);
        }

        public int evaluateQueueSize(long j2, List<? extends MediaChunk> list) {
            return this.trackSelection.evaluateQueueSize(j2, list);
        }

        public Format getFormat(int i3) {
            return this.trackSelection.getFormat(i3);
        }

        public int getIndexInTrackGroup(int i3) {
            return this.trackSelection.getIndexInTrackGroup(i3);
        }

        public Format getSelectedFormat() {
            return this.trackSelection.getSelectedFormat();
        }

        public int getSelectedIndex() {
            return this.trackSelection.getSelectedIndex();
        }

        public int getSelectedIndexInTrackGroup() {
            return this.trackSelection.getSelectedIndexInTrackGroup();
        }

        @Nullable
        public Object getSelectionData() {
            return this.trackSelection.getSelectionData();
        }

        public int getSelectionReason() {
            return this.trackSelection.getSelectionReason();
        }

        public TrackGroup getTrackGroup() {
            return this.trackGroup;
        }

        public int getType() {
            return this.trackSelection.getType();
        }

        public int hashCode() {
            return this.trackSelection.hashCode() + ((this.trackGroup.hashCode() + 527) * 31);
        }

        public int indexOf(Format format) {
            return this.trackSelection.indexOf(format);
        }

        public boolean isBlacklisted(int i3, long j2) {
            return this.trackSelection.isBlacklisted(i3, j2);
        }

        public int length() {
            return this.trackSelection.length();
        }

        public void onDiscontinuity() {
            this.trackSelection.onDiscontinuity();
        }

        public void onPlayWhenReadyChanged(boolean z2) {
            this.trackSelection.onPlayWhenReadyChanged(z2);
        }

        public void onPlaybackSpeed(float f2) {
            this.trackSelection.onPlaybackSpeed(f2);
        }

        public void onRebuffer() {
            this.trackSelection.onRebuffer();
        }

        public boolean shouldCancelChunkLoad(long j2, Chunk chunk, List<? extends MediaChunk> list) {
            return this.trackSelection.shouldCancelChunkLoad(j2, chunk, list);
        }

        public void updateSelectedTrack(long j2, long j3, long j4, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
            this.trackSelection.updateSelectedTrack(j2, j3, j4, list, mediaChunkIteratorArr);
        }

        public int indexOf(int i3) {
            return this.trackSelection.indexOf(i3);
        }
    }

    public static final class TimeOffsetMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
        private MediaPeriod.Callback callback;
        /* access modifiers changed from: private */
        public final MediaPeriod mediaPeriod;
        private final long timeOffsetUs;

        public TimeOffsetMediaPeriod(MediaPeriod mediaPeriod2, long j2) {
            this.mediaPeriod = mediaPeriod2;
            this.timeOffsetUs = j2;
        }

        public boolean continueLoading(long j2) {
            return this.mediaPeriod.continueLoading(j2 - this.timeOffsetUs);
        }

        public void discardBuffer(long j2, boolean z2) {
            this.mediaPeriod.discardBuffer(j2 - this.timeOffsetUs, z2);
        }

        public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
            return this.mediaPeriod.getAdjustedSeekPositionUs(j2 - this.timeOffsetUs, seekParameters) + this.timeOffsetUs;
        }

        public long getBufferedPositionUs() {
            long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
            if (bufferedPositionUs == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            return this.timeOffsetUs + bufferedPositionUs;
        }

        public long getNextLoadPositionUs() {
            long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
            if (nextLoadPositionUs == Long.MIN_VALUE) {
                return Long.MIN_VALUE;
            }
            return this.timeOffsetUs + nextLoadPositionUs;
        }

        public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
            return this.mediaPeriod.getStreamKeys(list);
        }

        public TrackGroupArray getTrackGroups() {
            return this.mediaPeriod.getTrackGroups();
        }

        public boolean isLoading() {
            return this.mediaPeriod.isLoading();
        }

        public void maybeThrowPrepareError() throws IOException {
            this.mediaPeriod.maybeThrowPrepareError();
        }

        public void onPrepared(MediaPeriod mediaPeriod2) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
        }

        public void prepare(MediaPeriod.Callback callback2, long j2) {
            this.callback = callback2;
            this.mediaPeriod.prepare(this, j2 - this.timeOffsetUs);
        }

        public long readDiscontinuity() {
            long readDiscontinuity = this.mediaPeriod.readDiscontinuity();
            return readDiscontinuity == C.TIME_UNSET ? C.TIME_UNSET : this.timeOffsetUs + readDiscontinuity;
        }

        public void reevaluateBuffer(long j2) {
            this.mediaPeriod.reevaluateBuffer(j2 - this.timeOffsetUs);
        }

        public long seekToUs(long j2) {
            return this.mediaPeriod.seekToUs(j2 - this.timeOffsetUs) + this.timeOffsetUs;
        }

        public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
            SampleStream[] sampleStreamArr2 = sampleStreamArr;
            SampleStream[] sampleStreamArr3 = new SampleStream[sampleStreamArr2.length];
            int i3 = 0;
            while (true) {
                SampleStream sampleStream = null;
                if (i3 >= sampleStreamArr2.length) {
                    break;
                }
                TimeOffsetSampleStream timeOffsetSampleStream = (TimeOffsetSampleStream) sampleStreamArr2[i3];
                if (timeOffsetSampleStream != null) {
                    sampleStream = timeOffsetSampleStream.getChildStream();
                }
                sampleStreamArr3[i3] = sampleStream;
                i3++;
            }
            long selectTracks = this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr3, zArr2, j2 - this.timeOffsetUs);
            for (int i4 = 0; i4 < sampleStreamArr2.length; i4++) {
                SampleStream sampleStream2 = sampleStreamArr3[i4];
                if (sampleStream2 == null) {
                    sampleStreamArr2[i4] = null;
                } else {
                    SampleStream sampleStream3 = sampleStreamArr2[i4];
                    if (sampleStream3 == null || ((TimeOffsetSampleStream) sampleStream3).getChildStream() != sampleStream2) {
                        sampleStreamArr2[i4] = new TimeOffsetSampleStream(sampleStream2, this.timeOffsetUs);
                    }
                }
            }
            return selectTracks + this.timeOffsetUs;
        }

        public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
            ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
        }
    }

    public static final class TimeOffsetSampleStream implements SampleStream {
        private final SampleStream sampleStream;
        private final long timeOffsetUs;

        public TimeOffsetSampleStream(SampleStream sampleStream2, long j2) {
            this.sampleStream = sampleStream2;
            this.timeOffsetUs = j2;
        }

        public SampleStream getChildStream() {
            return this.sampleStream;
        }

        public boolean isReady() {
            return this.sampleStream.isReady();
        }

        public void maybeThrowError() throws IOException {
            this.sampleStream.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3) {
            int readData = this.sampleStream.readData(formatHolder, decoderInputBuffer, i3);
            if (readData == -4) {
                decoderInputBuffer.timeUs = Math.max(0, decoderInputBuffer.timeUs + this.timeOffsetUs);
            }
            return readData;
        }

        public int skipData(long j2) {
            return this.sampleStream.skipData(j2 - this.timeOffsetUs);
        }
    }

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, long[] jArr, MediaPeriod... mediaPeriodArr) {
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.periods = mediaPeriodArr;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory2.createCompositeSequenceableLoader(new SequenceableLoader[0]);
        this.streamPeriodIndices = new IdentityHashMap<>();
        this.enabledPeriods = new MediaPeriod[0];
        for (int i3 = 0; i3 < mediaPeriodArr.length; i3++) {
            long j2 = jArr[i3];
            if (j2 != 0) {
                this.periods[i3] = new TimeOffsetMediaPeriod(mediaPeriodArr[i3], j2);
            }
        }
    }

    public boolean continueLoading(long j2) {
        if (this.childrenPendingPreparation.isEmpty()) {
            return this.compositeSequenceableLoader.continueLoading(j2);
        }
        int size = this.childrenPendingPreparation.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.childrenPendingPreparation.get(i3).continueLoading(j2);
        }
        return false;
    }

    public void discardBuffer(long j2, boolean z2) {
        for (MediaPeriod discardBuffer : this.enabledPeriods) {
            discardBuffer.discardBuffer(j2, z2);
        }
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
        return (mediaPeriodArr.length > 0 ? mediaPeriodArr[0] : this.periods[0]).getAdjustedSeekPositionUs(j2, seekParameters);
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public MediaPeriod getChildPeriod(int i3) {
        MediaPeriod mediaPeriod = this.periods[i3];
        return mediaPeriod instanceof TimeOffsetMediaPeriod ? ((TimeOffsetMediaPeriod) mediaPeriod).mediaPeriod : mediaPeriod;
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    public void maybeThrowPrepareError() throws IOException {
        for (MediaPeriod maybeThrowPrepareError : this.periods) {
            maybeThrowPrepareError.maybeThrowPrepareError();
        }
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.childrenPendingPreparation.remove(mediaPeriod);
        if (this.childrenPendingPreparation.isEmpty()) {
            int i3 = 0;
            for (MediaPeriod trackGroups2 : this.periods) {
                i3 += trackGroups2.getTrackGroups().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i3];
            int i4 = 0;
            int i5 = 0;
            while (true) {
                MediaPeriod[] mediaPeriodArr = this.periods;
                if (i4 < mediaPeriodArr.length) {
                    TrackGroupArray trackGroups3 = mediaPeriodArr[i4].getTrackGroups();
                    int i6 = trackGroups3.length;
                    int i7 = 0;
                    while (i7 < i6) {
                        TrackGroup trackGroup = trackGroups3.get(i7);
                        TrackGroup copyWithId = trackGroup.copyWithId(i4 + ":" + trackGroup.id);
                        this.childTrackGroupByMergedTrackGroup.put(copyWithId, trackGroup);
                        trackGroupArr[i5] = copyWithId;
                        i7++;
                        i5++;
                    }
                    i4++;
                } else {
                    this.trackGroups = new TrackGroupArray(trackGroupArr);
                    ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
                    return;
                }
            }
        }
    }

    public void prepare(MediaPeriod.Callback callback2, long j2) {
        this.callback = callback2;
        Collections.addAll(this.childrenPendingPreparation, this.periods);
        for (MediaPeriod prepare : this.periods) {
            prepare.prepare(this, j2);
        }
    }

    public long readDiscontinuity() {
        long j2 = -9223372036854775807L;
        for (MediaPeriod mediaPeriod : this.enabledPeriods) {
            long readDiscontinuity = mediaPeriod.readDiscontinuity();
            if (readDiscontinuity != C.TIME_UNSET) {
                if (j2 == C.TIME_UNSET) {
                    MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
                    int length = mediaPeriodArr.length;
                    int i3 = 0;
                    while (i3 < length) {
                        MediaPeriod mediaPeriod2 = mediaPeriodArr[i3];
                        if (mediaPeriod2 == mediaPeriod) {
                            break;
                        } else if (mediaPeriod2.seekToUs(readDiscontinuity) == readDiscontinuity) {
                            i3++;
                        } else {
                            throw new IllegalStateException("Unexpected child seekToUs result.");
                        }
                    }
                    j2 = readDiscontinuity;
                } else if (readDiscontinuity != j2) {
                    throw new IllegalStateException("Conflicting discontinuities.");
                }
            } else if (!(j2 == C.TIME_UNSET || mediaPeriod.seekToUs(j2) == j2)) {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
        return j2;
    }

    public void reevaluateBuffer(long j2) {
        this.compositeSequenceableLoader.reevaluateBuffer(j2);
    }

    public long seekToUs(long j2) {
        long seekToUs = this.enabledPeriods[0].seekToUs(j2);
        int i3 = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
            if (i3 >= mediaPeriodArr.length) {
                return seekToUs;
            }
            if (mediaPeriodArr[i3].seekToUs(seekToUs) == seekToUs) {
                i3++;
            } else {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        Integer num;
        ExoTrackSelection[] exoTrackSelectionArr2 = exoTrackSelectionArr;
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        int[] iArr = new int[exoTrackSelectionArr2.length];
        int[] iArr2 = new int[exoTrackSelectionArr2.length];
        int i3 = 0;
        while (true) {
            num = 0;
            if (i3 >= exoTrackSelectionArr2.length) {
                break;
            }
            SampleStream sampleStream = sampleStreamArr2[i3];
            if (sampleStream != null) {
                num = this.streamPeriodIndices.get(sampleStream);
            }
            iArr[i3] = num == null ? -1 : num.intValue();
            iArr2[i3] = -1;
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr2[i3];
            if (exoTrackSelection != null) {
                TrackGroup trackGroup = (TrackGroup) Assertions.checkNotNull(this.childTrackGroupByMergedTrackGroup.get(exoTrackSelection.getTrackGroup()));
                int i4 = 0;
                while (true) {
                    MediaPeriod[] mediaPeriodArr = this.periods;
                    if (i4 >= mediaPeriodArr.length) {
                        break;
                    } else if (mediaPeriodArr[i4].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i3] = i4;
                        break;
                    } else {
                        i4++;
                    }
                }
            }
            i3++;
        }
        this.streamPeriodIndices.clear();
        int length = exoTrackSelectionArr2.length;
        SampleStream[] sampleStreamArr3 = new SampleStream[length];
        SampleStream[] sampleStreamArr4 = new SampleStream[exoTrackSelectionArr2.length];
        ExoTrackSelection[] exoTrackSelectionArr3 = new ExoTrackSelection[exoTrackSelectionArr2.length];
        ArrayList arrayList = new ArrayList(this.periods.length);
        long j3 = j2;
        int i5 = 0;
        while (i5 < this.periods.length) {
            for (int i6 = 0; i6 < exoTrackSelectionArr2.length; i6++) {
                sampleStreamArr4[i6] = iArr[i6] == i5 ? sampleStreamArr2[i6] : num;
                if (iArr2[i6] == i5) {
                    ExoTrackSelection exoTrackSelection2 = (ExoTrackSelection) Assertions.checkNotNull(exoTrackSelectionArr2[i6]);
                    exoTrackSelectionArr3[i6] = new ForwardingTrackSelection(exoTrackSelection2, (TrackGroup) Assertions.checkNotNull(this.childTrackGroupByMergedTrackGroup.get(exoTrackSelection2.getTrackGroup())));
                } else {
                    exoTrackSelectionArr3[i6] = num;
                }
            }
            int i7 = i5;
            ArrayList arrayList2 = arrayList;
            ExoTrackSelection[] exoTrackSelectionArr4 = exoTrackSelectionArr3;
            long selectTracks = this.periods[i5].selectTracks(exoTrackSelectionArr3, zArr, sampleStreamArr4, zArr2, j3);
            if (i7 == 0) {
                j3 = selectTracks;
            } else if (selectTracks != j3) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean z2 = false;
            for (int i8 = 0; i8 < exoTrackSelectionArr2.length; i8++) {
                boolean z3 = true;
                if (iArr2[i8] == i7) {
                    sampleStreamArr3[i8] = sampleStreamArr4[i8];
                    this.streamPeriodIndices.put((SampleStream) Assertions.checkNotNull(sampleStreamArr4[i8]), Integer.valueOf(i7));
                    z2 = true;
                } else if (iArr[i8] == i7) {
                    if (sampleStreamArr4[i8] != null) {
                        z3 = false;
                    }
                    Assertions.checkState(z3);
                }
            }
            if (z2) {
                arrayList2.add(this.periods[i7]);
            }
            i5 = i7 + 1;
            arrayList = arrayList2;
            exoTrackSelectionArr3 = exoTrackSelectionArr4;
            num = null;
        }
        System.arraycopy(sampleStreamArr3, 0, sampleStreamArr2, 0, length);
        MediaPeriod[] mediaPeriodArr2 = (MediaPeriod[]) arrayList.toArray(new MediaPeriod[0]);
        this.enabledPeriods = mediaPeriodArr2;
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(mediaPeriodArr2);
        return j3;
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }
}
