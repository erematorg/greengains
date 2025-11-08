package com.appsamurai.storyly.exoplayer2.core;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.core.source.ClippingMediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.EmptySampleStream;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.SampleStream;
import com.appsamurai.storyly.exoplayer2.core.source.TrackGroupArray;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelector;
import com.appsamurai.storyly.exoplayer2.core.trackselection.TrackSelectorResult;
import com.appsamurai.storyly.exoplayer2.core.upstream.Allocator;

final class MediaPeriodHolder {
    private static final String TAG = "MediaPeriodHolder";
    public boolean allRenderersInCorrectState;
    public boolean hasEnabledTracks;
    public MediaPeriodInfo info;
    private final boolean[] mayRetainStreamFlags;
    public final MediaPeriod mediaPeriod;
    private final MediaSourceList mediaSourceList;
    @Nullable
    private MediaPeriodHolder next;
    public boolean prepared;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionOffsetUs;
    public final SampleStream[] sampleStreams;
    private TrackGroupArray trackGroups = TrackGroupArray.EMPTY;
    private final TrackSelector trackSelector;
    private TrackSelectorResult trackSelectorResult;
    public final Object uid;

    public MediaPeriodHolder(RendererCapabilities[] rendererCapabilitiesArr, long j2, TrackSelector trackSelector2, Allocator allocator, MediaSourceList mediaSourceList2, MediaPeriodInfo mediaPeriodInfo, TrackSelectorResult trackSelectorResult2) {
        this.rendererCapabilities = rendererCapabilitiesArr;
        this.rendererPositionOffsetUs = j2;
        this.trackSelector = trackSelector2;
        this.mediaSourceList = mediaSourceList2;
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.id;
        this.uid = mediaPeriodId.periodUid;
        this.info = mediaPeriodInfo;
        this.trackSelectorResult = trackSelectorResult2;
        this.sampleStreams = new SampleStream[rendererCapabilitiesArr.length];
        this.mayRetainStreamFlags = new boolean[rendererCapabilitiesArr.length];
        this.mediaPeriod = createMediaPeriod(mediaPeriodId, mediaSourceList2, allocator, mediaPeriodInfo.startPositionUs, mediaPeriodInfo.endPositionUs);
    }

    private void associateNoSampleRenderersWithEmptySampleStream(SampleStream[] sampleStreamArr) {
        int i3 = 0;
        while (true) {
            RendererCapabilities[] rendererCapabilitiesArr = this.rendererCapabilities;
            if (i3 < rendererCapabilitiesArr.length) {
                if (rendererCapabilitiesArr[i3].getTrackType() == -2 && this.trackSelectorResult.isRendererEnabled(i3)) {
                    sampleStreamArr[i3] = new EmptySampleStream();
                }
                i3++;
            } else {
                return;
            }
        }
    }

    private static MediaPeriod createMediaPeriod(MediaSource.MediaPeriodId mediaPeriodId, MediaSourceList mediaSourceList2, Allocator allocator, long j2, long j3) {
        MediaPeriod createPeriod = mediaSourceList2.createPeriod(mediaPeriodId, allocator, j2);
        return j3 != C.TIME_UNSET ? new ClippingMediaPeriod(createPeriod, true, 0, j3) : createPeriod;
    }

    private void disableTrackSelectionsInResult() {
        if (isLoadingMediaPeriod()) {
            int i3 = 0;
            while (true) {
                TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
                if (i3 < trackSelectorResult2.length) {
                    boolean isRendererEnabled = trackSelectorResult2.isRendererEnabled(i3);
                    ExoTrackSelection exoTrackSelection = this.trackSelectorResult.selections[i3];
                    if (isRendererEnabled && exoTrackSelection != null) {
                        exoTrackSelection.disable();
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    private void disassociateNoSampleRenderersWithEmptySampleStream(SampleStream[] sampleStreamArr) {
        int i3 = 0;
        while (true) {
            RendererCapabilities[] rendererCapabilitiesArr = this.rendererCapabilities;
            if (i3 < rendererCapabilitiesArr.length) {
                if (rendererCapabilitiesArr[i3].getTrackType() == -2) {
                    sampleStreamArr[i3] = null;
                }
                i3++;
            } else {
                return;
            }
        }
    }

    private void enableTrackSelectionsInResult() {
        if (isLoadingMediaPeriod()) {
            int i3 = 0;
            while (true) {
                TrackSelectorResult trackSelectorResult2 = this.trackSelectorResult;
                if (i3 < trackSelectorResult2.length) {
                    boolean isRendererEnabled = trackSelectorResult2.isRendererEnabled(i3);
                    ExoTrackSelection exoTrackSelection = this.trackSelectorResult.selections[i3];
                    if (isRendererEnabled && exoTrackSelection != null) {
                        exoTrackSelection.enable();
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    private boolean isLoadingMediaPeriod() {
        return this.next == null;
    }

    private static void releaseMediaPeriod(MediaSourceList mediaSourceList2, MediaPeriod mediaPeriod2) {
        try {
            if (mediaPeriod2 instanceof ClippingMediaPeriod) {
                mediaSourceList2.releasePeriod(((ClippingMediaPeriod) mediaPeriod2).mediaPeriod);
            } else {
                mediaSourceList2.releasePeriod(mediaPeriod2);
            }
        } catch (RuntimeException e3) {
            Log.e(TAG, "Period release failed.", e3);
        }
    }

    public long applyTrackSelection(TrackSelectorResult trackSelectorResult2, long j2, boolean z2) {
        return applyTrackSelection(trackSelectorResult2, j2, z2, new boolean[this.rendererCapabilities.length]);
    }

    public void continueLoading(long j2) {
        Assertions.checkState(isLoadingMediaPeriod());
        this.mediaPeriod.continueLoading(toPeriodTime(j2));
    }

    public long getBufferedPositionUs() {
        if (!this.prepared) {
            return this.info.startPositionUs;
        }
        long bufferedPositionUs = this.hasEnabledTracks ? this.mediaPeriod.getBufferedPositionUs() : Long.MIN_VALUE;
        return bufferedPositionUs == Long.MIN_VALUE ? this.info.durationUs : bufferedPositionUs;
    }

    @Nullable
    public MediaPeriodHolder getNext() {
        return this.next;
    }

    public long getNextLoadPositionUs() {
        if (!this.prepared) {
            return 0;
        }
        return this.mediaPeriod.getNextLoadPositionUs();
    }

    public long getRendererOffset() {
        return this.rendererPositionOffsetUs;
    }

    public long getStartPositionRendererTime() {
        return this.info.startPositionUs + this.rendererPositionOffsetUs;
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public TrackSelectorResult getTrackSelectorResult() {
        return this.trackSelectorResult;
    }

    public void handlePrepared(float f2, Timeline timeline) throws ExoPlaybackException {
        this.prepared = true;
        this.trackGroups = this.mediaPeriod.getTrackGroups();
        TrackSelectorResult selectTracks = selectTracks(f2, timeline);
        MediaPeriodInfo mediaPeriodInfo = this.info;
        long j2 = mediaPeriodInfo.startPositionUs;
        long j3 = mediaPeriodInfo.durationUs;
        if (j3 != C.TIME_UNSET && j2 >= j3) {
            j2 = Math.max(0, j3 - 1);
        }
        long applyTrackSelection = applyTrackSelection(selectTracks, j2, false);
        long j4 = this.rendererPositionOffsetUs;
        MediaPeriodInfo mediaPeriodInfo2 = this.info;
        this.rendererPositionOffsetUs = (mediaPeriodInfo2.startPositionUs - applyTrackSelection) + j4;
        this.info = mediaPeriodInfo2.copyWithStartPositionUs(applyTrackSelection);
    }

    public boolean isFullyBuffered() {
        return this.prepared && (!this.hasEnabledTracks || this.mediaPeriod.getBufferedPositionUs() == Long.MIN_VALUE);
    }

    public void reevaluateBuffer(long j2) {
        Assertions.checkState(isLoadingMediaPeriod());
        if (this.prepared) {
            this.mediaPeriod.reevaluateBuffer(toPeriodTime(j2));
        }
    }

    public void release() {
        disableTrackSelectionsInResult();
        releaseMediaPeriod(this.mediaSourceList, this.mediaPeriod);
    }

    public TrackSelectorResult selectTracks(float f2, Timeline timeline) throws ExoPlaybackException {
        TrackSelectorResult selectTracks = this.trackSelector.selectTracks(this.rendererCapabilities, getTrackGroups(), this.info.id, timeline);
        for (ExoTrackSelection exoTrackSelection : selectTracks.selections) {
            if (exoTrackSelection != null) {
                exoTrackSelection.onPlaybackSpeed(f2);
            }
        }
        return selectTracks;
    }

    public void setNext(@Nullable MediaPeriodHolder mediaPeriodHolder) {
        if (mediaPeriodHolder != this.next) {
            disableTrackSelectionsInResult();
            this.next = mediaPeriodHolder;
            enableTrackSelectionsInResult();
        }
    }

    public void setRendererOffset(long j2) {
        this.rendererPositionOffsetUs = j2;
    }

    public long toPeriodTime(long j2) {
        return j2 - getRendererOffset();
    }

    public long toRendererTime(long j2) {
        return j2 + getRendererOffset();
    }

    public void updateClipping() {
        MediaPeriod mediaPeriod2 = this.mediaPeriod;
        if (mediaPeriod2 instanceof ClippingMediaPeriod) {
            long j2 = this.info.endPositionUs;
            if (j2 == C.TIME_UNSET) {
                j2 = Long.MIN_VALUE;
            }
            ((ClippingMediaPeriod) mediaPeriod2).updateClipping(0, j2);
        }
    }

    public long applyTrackSelection(TrackSelectorResult trackSelectorResult2, long j2, boolean z2, boolean[] zArr) {
        TrackSelectorResult trackSelectorResult3 = trackSelectorResult2;
        int i3 = 0;
        while (true) {
            boolean z3 = true;
            if (i3 >= trackSelectorResult3.length) {
                break;
            }
            boolean[] zArr2 = this.mayRetainStreamFlags;
            if (z2 || !trackSelectorResult2.isEquivalent(this.trackSelectorResult, i3)) {
                z3 = false;
            }
            zArr2[i3] = z3;
            i3++;
        }
        disassociateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        disableTrackSelectionsInResult();
        this.trackSelectorResult = trackSelectorResult3;
        enableTrackSelectionsInResult();
        long selectTracks = this.mediaPeriod.selectTracks(trackSelectorResult3.selections, this.mayRetainStreamFlags, this.sampleStreams, zArr, j2);
        associateNoSampleRenderersWithEmptySampleStream(this.sampleStreams);
        this.hasEnabledTracks = false;
        int i4 = 0;
        while (true) {
            SampleStream[] sampleStreamArr = this.sampleStreams;
            if (i4 >= sampleStreamArr.length) {
                return selectTracks;
            }
            if (sampleStreamArr[i4] != null) {
                Assertions.checkState(trackSelectorResult2.isRendererEnabled(i4));
                if (this.rendererCapabilities[i4].getTrackType() != -2) {
                    this.hasEnabledTracks = true;
                }
            } else {
                Assertions.checkState(trackSelectorResult3.selections[i4] == null);
            }
            i4++;
        }
    }
}
