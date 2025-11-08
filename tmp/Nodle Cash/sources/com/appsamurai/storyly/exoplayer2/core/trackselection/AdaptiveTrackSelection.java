package com.appsamurai.storyly.exoplayer2.core.trackselection;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunkIterator;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdaptiveTrackSelection extends BaseTrackSelection {
    public static final float DEFAULT_BANDWIDTH_FRACTION = 0.7f;
    public static final float DEFAULT_BUFFERED_FRACTION_TO_LIVE_EDGE_FOR_QUALITY_INCREASE = 0.75f;
    public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
    public static final int DEFAULT_MAX_HEIGHT_TO_DISCARD = 719;
    public static final int DEFAULT_MAX_WIDTH_TO_DISCARD = 1279;
    public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
    public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
    private static final long MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS = 1000;
    private static final String TAG = "AdaptiveTrackSelection";
    private final ImmutableList<AdaptationCheckpoint> adaptationCheckpoints;
    private final float bandwidthFraction;
    private final BandwidthMeter bandwidthMeter;
    private final float bufferedFractionToLiveEdgeForQualityIncrease;
    private final Clock clock;
    @Nullable
    private MediaChunk lastBufferEvaluationMediaChunk;
    private long lastBufferEvaluationMs;
    private final long maxDurationForQualityDecreaseUs;
    private final int maxHeightToDiscard;
    private final int maxWidthToDiscard;
    private final long minDurationForQualityIncreaseUs;
    private final long minDurationToRetainAfterDiscardUs;
    private float playbackSpeed;
    private int reason;
    private int selectedIndex;

    public static final class AdaptationCheckpoint {
        public final long allocatedBandwidth;
        public final long totalBandwidth;

        public AdaptationCheckpoint(long j2, long j3) {
            this.totalBandwidth = j2;
            this.allocatedBandwidth = j3;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdaptationCheckpoint)) {
                return false;
            }
            AdaptationCheckpoint adaptationCheckpoint = (AdaptationCheckpoint) obj;
            return this.totalBandwidth == adaptationCheckpoint.totalBandwidth && this.allocatedBandwidth == adaptationCheckpoint.allocatedBandwidth;
        }

        public int hashCode() {
            return (((int) this.totalBandwidth) * 31) + ((int) this.allocatedBandwidth);
        }
    }

    public static class Factory implements ExoTrackSelection.Factory {
        private final float bandwidthFraction;
        private final float bufferedFractionToLiveEdgeForQualityIncrease;
        private final Clock clock;
        private final int maxDurationForQualityDecreaseMs;
        private final int maxHeightToDiscard;
        private final int maxWidthToDiscard;
        private final int minDurationForQualityIncreaseMs;
        private final int minDurationToRetainAfterDiscardMs;

        public Factory() {
            this(10000, 25000, 25000, 0.7f);
        }

        public AdaptiveTrackSelection createAdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, int i3, BandwidthMeter bandwidthMeter, ImmutableList<AdaptationCheckpoint> immutableList) {
            return new AdaptiveTrackSelection(trackGroup, iArr, i3, bandwidthMeter, (long) this.minDurationForQualityIncreaseMs, (long) this.maxDurationForQualityDecreaseMs, (long) this.minDurationToRetainAfterDiscardMs, this.maxWidthToDiscard, this.maxHeightToDiscard, this.bandwidthFraction, this.bufferedFractionToLiveEdgeForQualityIncrease, immutableList, this.clock);
        }

        public final ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
            ExoTrackSelection exoTrackSelection;
            ImmutableList access$000 = AdaptiveTrackSelection.getAdaptationCheckpoints(definitionArr);
            ExoTrackSelection[] exoTrackSelectionArr = new ExoTrackSelection[definitionArr.length];
            for (int i3 = 0; i3 < definitionArr.length; i3++) {
                ExoTrackSelection.Definition definition = definitionArr[i3];
                if (definition != null) {
                    int[] iArr = definition.tracks;
                    if (iArr.length != 0) {
                        if (iArr.length == 1) {
                            exoTrackSelection = new FixedTrackSelection(definition.group, iArr[0], definition.type);
                        } else {
                            exoTrackSelection = createAdaptiveTrackSelection(definition.group, iArr, definition.type, bandwidthMeter, (ImmutableList) access$000.get(i3));
                        }
                        exoTrackSelectionArr[i3] = exoTrackSelection;
                    }
                }
            }
            return exoTrackSelectionArr;
        }

        public Factory(int i3, int i4, int i5, float f2) {
            this(i3, i4, i5, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, f2, 0.75f, Clock.DEFAULT);
        }

        public Factory(int i3, int i4, int i5, int i6, int i7, float f2) {
            this(i3, i4, i5, i6, i7, f2, 0.75f, Clock.DEFAULT);
        }

        public Factory(int i3, int i4, int i5, float f2, float f3, Clock clock2) {
            this(i3, i4, i5, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, f2, f3, clock2);
        }

        public Factory(int i3, int i4, int i5, int i6, int i7, float f2, float f3, Clock clock2) {
            this.minDurationForQualityIncreaseMs = i3;
            this.maxDurationForQualityDecreaseMs = i4;
            this.minDurationToRetainAfterDiscardMs = i5;
            this.maxWidthToDiscard = i6;
            this.maxHeightToDiscard = i7;
            this.bandwidthFraction = f2;
            this.bufferedFractionToLiveEdgeForQualityIncrease = f3;
            this.clock = clock2;
        }
    }

    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, BandwidthMeter bandwidthMeter2) {
        this(trackGroup, iArr, 0, bandwidthMeter2, 10000, 25000, 25000, DEFAULT_MAX_WIDTH_TO_DISCARD, DEFAULT_MAX_HEIGHT_TO_DISCARD, 0.7f, 0.75f, ImmutableList.of(), Clock.DEFAULT);
    }

    private static void addCheckpoint(List<ImmutableList.Builder<AdaptationCheckpoint>> list, long[] jArr) {
        long j2 = 0;
        for (long j3 : jArr) {
            j2 += j3;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            ImmutableList.Builder builder = list.get(i3);
            if (builder != null) {
                builder.add((Object) new AdaptationCheckpoint(j2, jArr[i3]));
            }
        }
    }

    private int determineIdealSelectedIndex(long j2, long j3) {
        long allocatedBandwidth = getAllocatedBandwidth(j3);
        int i3 = 0;
        for (int i4 = 0; i4 < this.length; i4++) {
            if (j2 == Long.MIN_VALUE || !isBlacklisted(i4, j2)) {
                Format format = getFormat(i4);
                if (canSelectFormat(format, format.bitrate, allocatedBandwidth)) {
                    return i4;
                }
                i3 = i4;
            }
        }
        return i3;
    }

    /* access modifiers changed from: private */
    public static ImmutableList<ImmutableList<AdaptationCheckpoint>> getAdaptationCheckpoints(ExoTrackSelection.Definition[] definitionArr) {
        ArrayList arrayList = new ArrayList();
        for (ExoTrackSelection.Definition definition : definitionArr) {
            if (definition == null || definition.tracks.length <= 1) {
                arrayList.add((Object) null);
            } else {
                ImmutableList.Builder builder = ImmutableList.builder();
                builder.add((Object) new AdaptationCheckpoint(0, 0));
                arrayList.add(builder);
            }
        }
        long[][] sortedTrackBitrates = getSortedTrackBitrates(definitionArr);
        int[] iArr = new int[sortedTrackBitrates.length];
        long[] jArr = new long[sortedTrackBitrates.length];
        for (int i3 = 0; i3 < sortedTrackBitrates.length; i3++) {
            long[] jArr2 = sortedTrackBitrates[i3];
            jArr[i3] = jArr2.length == 0 ? 0 : jArr2[0];
        }
        addCheckpoint(arrayList, jArr);
        ImmutableList<Integer> switchOrder = getSwitchOrder(sortedTrackBitrates);
        for (int i4 = 0; i4 < switchOrder.size(); i4++) {
            int intValue = switchOrder.get(i4).intValue();
            int i5 = iArr[intValue] + 1;
            iArr[intValue] = i5;
            jArr[intValue] = sortedTrackBitrates[intValue][i5];
            addCheckpoint(arrayList, jArr);
        }
        for (int i6 = 0; i6 < definitionArr.length; i6++) {
            if (arrayList.get(i6) != null) {
                jArr[i6] = jArr[i6] * 2;
            }
        }
        addCheckpoint(arrayList, jArr);
        ImmutableList.Builder builder2 = ImmutableList.builder();
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            ImmutableList.Builder builder3 = (ImmutableList.Builder) arrayList.get(i7);
            builder2.add((Object) builder3 == null ? ImmutableList.of() : builder3.build());
        }
        return builder2.build();
    }

    private long getAllocatedBandwidth(long j2) {
        long totalAllocatableBandwidth = getTotalAllocatableBandwidth(j2);
        if (this.adaptationCheckpoints.isEmpty()) {
            return totalAllocatableBandwidth;
        }
        int i3 = 1;
        while (i3 < this.adaptationCheckpoints.size() - 1 && this.adaptationCheckpoints.get(i3).totalBandwidth < totalAllocatableBandwidth) {
            i3++;
        }
        AdaptationCheckpoint adaptationCheckpoint = this.adaptationCheckpoints.get(i3 - 1);
        AdaptationCheckpoint adaptationCheckpoint2 = this.adaptationCheckpoints.get(i3);
        long j3 = adaptationCheckpoint.totalBandwidth;
        long j4 = adaptationCheckpoint.allocatedBandwidth;
        return j4 + ((long) ((((float) (totalAllocatableBandwidth - j3)) / ((float) (adaptationCheckpoint2.totalBandwidth - j3))) * ((float) (adaptationCheckpoint2.allocatedBandwidth - j4))));
    }

    private long getLastChunkDurationUs(List<? extends MediaChunk> list) {
        if (list.isEmpty()) {
            return C.TIME_UNSET;
        }
        MediaChunk mediaChunk = (MediaChunk) Iterables.getLast(list);
        long j2 = mediaChunk.startTimeUs;
        if (j2 == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        long j3 = mediaChunk.endTimeUs;
        return j3 != C.TIME_UNSET ? j3 - j2 : C.TIME_UNSET;
    }

    private long getNextChunkDurationUs(MediaChunkIterator[] mediaChunkIteratorArr, List<? extends MediaChunk> list) {
        int i3 = this.selectedIndex;
        if (i3 >= mediaChunkIteratorArr.length || !mediaChunkIteratorArr[i3].next()) {
            for (MediaChunkIterator mediaChunkIterator : mediaChunkIteratorArr) {
                if (mediaChunkIterator.next()) {
                    return mediaChunkIterator.getChunkEndTimeUs() - mediaChunkIterator.getChunkStartTimeUs();
                }
            }
            return getLastChunkDurationUs(list);
        }
        MediaChunkIterator mediaChunkIterator2 = mediaChunkIteratorArr[this.selectedIndex];
        return mediaChunkIterator2.getChunkEndTimeUs() - mediaChunkIterator2.getChunkStartTimeUs();
    }

    private static long[][] getSortedTrackBitrates(ExoTrackSelection.Definition[] definitionArr) {
        long[][] jArr = new long[definitionArr.length][];
        for (int i3 = 0; i3 < definitionArr.length; i3++) {
            ExoTrackSelection.Definition definition = definitionArr[i3];
            if (definition == null) {
                jArr[i3] = new long[0];
            } else {
                jArr[i3] = new long[definition.tracks.length];
                int i4 = 0;
                while (true) {
                    int[] iArr = definition.tracks;
                    if (i4 >= iArr.length) {
                        break;
                    }
                    jArr[i3][i4] = (long) definition.group.getFormat(iArr[i4]).bitrate;
                    i4++;
                }
                Arrays.sort(jArr[i3]);
            }
        }
        return jArr;
    }

    private static ImmutableList<Integer> getSwitchOrder(long[][] jArr) {
        ListMultimap<K, V> build = MultimapBuilder.treeKeys().arrayListValues().build();
        for (int i3 = 0; i3 < jArr.length; i3++) {
            long[] jArr2 = jArr[i3];
            if (jArr2.length > 1) {
                int length = jArr2.length;
                double[] dArr = new double[length];
                int i4 = 0;
                while (true) {
                    long[] jArr3 = jArr[i3];
                    double d2 = 0.0d;
                    if (i4 >= jArr3.length) {
                        break;
                    }
                    long j2 = jArr3[i4];
                    if (j2 != -1) {
                        d2 = Math.log((double) j2);
                    }
                    dArr[i4] = d2;
                    i4++;
                }
                int i5 = length - 1;
                double d3 = dArr[i5] - dArr[0];
                int i6 = 0;
                while (i6 < i5) {
                    double d4 = dArr[i6];
                    i6++;
                    build.put(Double.valueOf(d3 == 0.0d ? 1.0d : (((d4 + dArr[i6]) * 0.5d) - dArr[0]) / d3), Integer.valueOf(i3));
                }
            }
        }
        return ImmutableList.copyOf(build.values());
    }

    private long getTotalAllocatableBandwidth(long j2) {
        long bitrateEstimate = (long) (((float) this.bandwidthMeter.getBitrateEstimate()) * this.bandwidthFraction);
        long timeToFirstByteEstimateUs = this.bandwidthMeter.getTimeToFirstByteEstimateUs();
        if (timeToFirstByteEstimateUs == C.TIME_UNSET || j2 == C.TIME_UNSET) {
            return (long) (((float) bitrateEstimate) / this.playbackSpeed);
        }
        float f2 = (float) j2;
        return (long) ((((float) bitrateEstimate) * Math.max((f2 / this.playbackSpeed) - ((float) timeToFirstByteEstimateUs), 0.0f)) / f2);
    }

    private long minDurationForQualityIncreaseUs(long j2, long j3) {
        if (j2 == C.TIME_UNSET) {
            return this.minDurationForQualityIncreaseUs;
        }
        if (j3 != C.TIME_UNSET) {
            j2 -= j3;
        }
        return Math.min((long) (((float) j2) * this.bufferedFractionToLiveEdgeForQualityIncrease), this.minDurationForQualityIncreaseUs);
    }

    public boolean canSelectFormat(Format format, int i3, long j2) {
        return ((long) i3) <= j2;
    }

    @CallSuper
    public void disable() {
        this.lastBufferEvaluationMediaChunk = null;
    }

    @CallSuper
    public void enable() {
        this.lastBufferEvaluationMs = C.TIME_UNSET;
        this.lastBufferEvaluationMediaChunk = null;
    }

    public int evaluateQueueSize(long j2, List<? extends MediaChunk> list) {
        int i3;
        int i4;
        long elapsedRealtime = this.clock.elapsedRealtime();
        if (!shouldEvaluateQueueSize(elapsedRealtime, list)) {
            return list.size();
        }
        this.lastBufferEvaluationMs = elapsedRealtime;
        this.lastBufferEvaluationMediaChunk = list.isEmpty() ? null : (MediaChunk) Iterables.getLast(list);
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        long playoutDurationForMediaDuration = Util.getPlayoutDurationForMediaDuration(((MediaChunk) list.get(size - 1)).startTimeUs - j2, this.playbackSpeed);
        long minDurationToRetainAfterDiscardUs2 = getMinDurationToRetainAfterDiscardUs();
        if (playoutDurationForMediaDuration < minDurationToRetainAfterDiscardUs2) {
            return size;
        }
        Format format = getFormat(determineIdealSelectedIndex(elapsedRealtime, getLastChunkDurationUs(list)));
        for (int i5 = 0; i5 < size; i5++) {
            MediaChunk mediaChunk = (MediaChunk) list.get(i5);
            Format format2 = mediaChunk.trackFormat;
            if (Util.getPlayoutDurationForMediaDuration(mediaChunk.startTimeUs - j2, this.playbackSpeed) >= minDurationToRetainAfterDiscardUs2 && format2.bitrate < format.bitrate && (i3 = format2.height) != -1 && i3 <= this.maxHeightToDiscard && (i4 = format2.width) != -1 && i4 <= this.maxWidthToDiscard && i3 < format.height) {
                return i5;
            }
        }
        return size;
    }

    public long getMinDurationToRetainAfterDiscardUs() {
        return this.minDurationToRetainAfterDiscardUs;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Nullable
    public Object getSelectionData() {
        return null;
    }

    public int getSelectionReason() {
        return this.reason;
    }

    public void onPlaybackSpeed(float f2) {
        this.playbackSpeed = f2;
    }

    public boolean shouldEvaluateQueueSize(long j2, List<? extends MediaChunk> list) {
        long j3 = this.lastBufferEvaluationMs;
        return j3 == C.TIME_UNSET || j2 - j3 >= 1000 || (!list.isEmpty() && !((MediaChunk) Iterables.getLast(list)).equals(this.lastBufferEvaluationMediaChunk));
    }

    public void updateSelectedTrack(long j2, long j3, long j4, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        long elapsedRealtime = this.clock.elapsedRealtime();
        long nextChunkDurationUs = getNextChunkDurationUs(mediaChunkIteratorArr, list);
        int i3 = this.reason;
        if (i3 == 0) {
            this.reason = 1;
            this.selectedIndex = determineIdealSelectedIndex(elapsedRealtime, nextChunkDurationUs);
            return;
        }
        int i4 = this.selectedIndex;
        int indexOf = list.isEmpty() ? -1 : indexOf(((MediaChunk) Iterables.getLast(list)).trackFormat);
        if (indexOf != -1) {
            i3 = ((MediaChunk) Iterables.getLast(list)).trackSelectionReason;
            i4 = indexOf;
        }
        int determineIdealSelectedIndex = determineIdealSelectedIndex(elapsedRealtime, nextChunkDurationUs);
        if (!isBlacklisted(i4, elapsedRealtime)) {
            Format format = getFormat(i4);
            Format format2 = getFormat(determineIdealSelectedIndex);
            long minDurationForQualityIncreaseUs2 = minDurationForQualityIncreaseUs(j4, nextChunkDurationUs);
            int i5 = format2.bitrate;
            int i6 = format.bitrate;
            if ((i5 > i6 && j3 < minDurationForQualityIncreaseUs2) || (i5 < i6 && j3 >= this.maxDurationForQualityDecreaseUs)) {
                determineIdealSelectedIndex = i4;
            }
        }
        if (determineIdealSelectedIndex != i4) {
            i3 = 3;
        }
        this.reason = i3;
        this.selectedIndex = determineIdealSelectedIndex;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AdaptiveTrackSelection(TrackGroup trackGroup, int[] iArr, int i3, BandwidthMeter bandwidthMeter2, long j2, long j3, long j4, int i4, int i5, float f2, float f3, List<AdaptationCheckpoint> list, Clock clock2) {
        super(trackGroup, iArr, i3);
        BandwidthMeter bandwidthMeter3;
        long j5;
        if (j4 < j2) {
            Log.w(TAG, "Adjusting minDurationToRetainAfterDiscardMs to be at least minDurationForQualityIncreaseMs");
            bandwidthMeter3 = bandwidthMeter2;
            j5 = j2;
        } else {
            bandwidthMeter3 = bandwidthMeter2;
            j5 = j4;
        }
        this.bandwidthMeter = bandwidthMeter3;
        this.minDurationForQualityIncreaseUs = j2 * 1000;
        this.maxDurationForQualityDecreaseUs = j3 * 1000;
        this.minDurationToRetainAfterDiscardUs = j5 * 1000;
        this.maxWidthToDiscard = i4;
        this.maxHeightToDiscard = i5;
        this.bandwidthFraction = f2;
        this.bufferedFractionToLiveEdgeForQualityIncrease = f3;
        this.adaptationCheckpoints = ImmutableList.copyOf(list);
        this.clock = clock2;
        this.playbackSpeed = 1.0f;
        this.reason = 0;
        this.lastBufferEvaluationMs = C.TIME_UNSET;
    }
}
