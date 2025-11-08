package com.appsamurai.storyly.exoplayer2.extractor.text.subrip;

import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;

final class SubripSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final Cue[] cues;

    public SubripSubtitle(Cue[] cueArr, long[] jArr) {
        this.cues = cueArr;
        this.cueTimesUs = jArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r3 = r3.cues[r4];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.appsamurai.storyly.exoplayer2.common.text.Cue> getCues(long r4) {
        /*
            r3 = this;
            long[] r0 = r3.cueTimesUs
            r1 = 1
            r2 = 0
            int r4 = com.appsamurai.storyly.exoplayer2.common.util.Util.binarySearchFloor((long[]) r0, (long) r4, (boolean) r1, (boolean) r2)
            r5 = -1
            if (r4 == r5) goto L_0x0019
            com.appsamurai.storyly.exoplayer2.common.text.Cue[] r3 = r3.cues
            r3 = r3[r4]
            com.appsamurai.storyly.exoplayer2.common.text.Cue r4 = com.appsamurai.storyly.exoplayer2.common.text.Cue.EMPTY
            if (r3 != r4) goto L_0x0014
            goto L_0x0019
        L_0x0014:
            java.util.List r3 = java.util.Collections.singletonList(r3)
            return r3
        L_0x0019:
            java.util.List r3 = java.util.Collections.emptyList()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.text.subrip.SubripSubtitle.getCues(long):java.util.List");
    }

    public long getEventTime(int i3) {
        boolean z2 = false;
        Assertions.checkArgument(i3 >= 0);
        if (i3 < this.cueTimesUs.length) {
            z2 = true;
        }
        Assertions.checkArgument(z2);
        return this.cueTimesUs[i3];
    }

    public int getEventTimeCount() {
        return this.cueTimesUs.length;
    }

    public int getNextEventTimeIndex(long j2) {
        int binarySearchCeil = Util.binarySearchCeil(this.cueTimesUs, j2, false, false);
        if (binarySearchCeil < this.cueTimesUs.length) {
            return binarySearchCeil;
        }
        return -1;
    }
}
