package com.appsamurai.storyly.exoplayer2.core.trackselection;

import A1.C0219a;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Timeline;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunk;
import com.appsamurai.storyly.exoplayer2.core.source.chunk.MediaChunkIterator;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.upstream.BandwidthMeter;
import java.util.List;
import java.util.Random;

public final class RandomTrackSelection extends BaseTrackSelection {
    private final Random random;
    private int selectedIndex;

    public RandomTrackSelection(TrackGroup trackGroup, int[] iArr, int i3, Random random2) {
        super(trackGroup, iArr, i3);
        this.random = random2;
        this.selectedIndex = random2.nextInt(this.length);
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Nullable
    public Object getSelectionData() {
        return null;
    }

    public int getSelectionReason() {
        return 3;
    }

    public void updateSelectedTrack(long j2, long j3, long j4, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int i3 = 0;
        for (int i4 = 0; i4 < this.length; i4++) {
            if (!isBlacklisted(i4, elapsedRealtime)) {
                i3++;
            }
        }
        this.selectedIndex = this.random.nextInt(i3);
        if (i3 != this.length) {
            int i5 = 0;
            for (int i6 = 0; i6 < this.length; i6++) {
                if (!isBlacklisted(i6, elapsedRealtime)) {
                    int i7 = i5 + 1;
                    if (this.selectedIndex == i5) {
                        this.selectedIndex = i6;
                        return;
                    }
                    i5 = i7;
                }
            }
        }
    }

    public static final class Factory implements ExoTrackSelection.Factory {
        private final Random random;

        public Factory() {
            this.random = new Random();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ ExoTrackSelection lambda$createTrackSelections$0(ExoTrackSelection.Definition definition) {
            return new RandomTrackSelection(definition.group, definition.tracks, definition.type, this.random);
        }

        public ExoTrackSelection[] createTrackSelections(ExoTrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
            return TrackSelectionUtil.createTrackSelectionsForDefinitions(definitionArr, new C0219a(this, 12));
        }

        public Factory(int i3) {
            this.random = new Random((long) i3);
        }
    }
}
