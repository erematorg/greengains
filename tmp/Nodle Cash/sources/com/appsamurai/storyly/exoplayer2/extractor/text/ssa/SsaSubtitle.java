package com.appsamurai.storyly.exoplayer2.extractor.text.ssa;

import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import java.util.Collections;
import java.util.List;

final class SsaSubtitle implements Subtitle {
    private final List<Long> cueTimesUs;
    private final List<List<Cue>> cues;

    public SsaSubtitle(List<List<Cue>> list, List<Long> list2) {
        this.cues = list;
        this.cueTimesUs = list2;
    }

    public List<Cue> getCues(long j2) {
        int binarySearchFloor = Util.binarySearchFloor(this.cueTimesUs, Long.valueOf(j2), true, false);
        return binarySearchFloor == -1 ? Collections.emptyList() : this.cues.get(binarySearchFloor);
    }

    public long getEventTime(int i3) {
        boolean z2 = false;
        Assertions.checkArgument(i3 >= 0);
        if (i3 < this.cueTimesUs.size()) {
            z2 = true;
        }
        Assertions.checkArgument(z2);
        return this.cueTimesUs.get(i3).longValue();
    }

    public int getEventTimeCount() {
        return this.cueTimesUs.size();
    }

    public int getNextEventTimeIndex(long j2) {
        int binarySearchCeil = Util.binarySearchCeil(this.cueTimesUs, Long.valueOf(j2), false, false);
        if (binarySearchCeil < this.cueTimesUs.size()) {
            return binarySearchCeil;
        }
        return -1;
    }
}
