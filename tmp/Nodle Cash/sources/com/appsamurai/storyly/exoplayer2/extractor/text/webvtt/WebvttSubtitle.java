package com.appsamurai.storyly.exoplayer2.extractor.text.webvtt;

import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class WebvttSubtitle implements Subtitle {
    private final List<WebvttCueInfo> cueInfos;
    private final long[] cueTimesUs;
    private final long[] sortedCueTimesUs;

    public WebvttSubtitle(List<WebvttCueInfo> list) {
        this.cueInfos = Collections.unmodifiableList(new ArrayList(list));
        this.cueTimesUs = new long[(list.size() * 2)];
        for (int i3 = 0; i3 < list.size(); i3++) {
            WebvttCueInfo webvttCueInfo = list.get(i3);
            int i4 = i3 * 2;
            long[] jArr = this.cueTimesUs;
            jArr[i4] = webvttCueInfo.startTimeUs;
            jArr[i4 + 1] = webvttCueInfo.endTimeUs;
        }
        long[] jArr2 = this.cueTimesUs;
        long[] copyOf = Arrays.copyOf(jArr2, jArr2.length);
        this.sortedCueTimesUs = copyOf;
        Arrays.sort(copyOf);
    }

    public List<Cue> getCues(long j2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < this.cueInfos.size(); i3++) {
            long[] jArr = this.cueTimesUs;
            int i4 = i3 * 2;
            if (jArr[i4] <= j2 && j2 < jArr[i4 + 1]) {
                WebvttCueInfo webvttCueInfo = this.cueInfos.get(i3);
                Cue cue = webvttCueInfo.cue;
                if (cue.line == -3.4028235E38f) {
                    arrayList2.add(webvttCueInfo);
                } else {
                    arrayList.add(cue);
                }
            }
        }
        Collections.sort(arrayList2, new a(1));
        for (int i5 = 0; i5 < arrayList2.size(); i5++) {
            arrayList.add(((WebvttCueInfo) arrayList2.get(i5)).cue.buildUpon().setLine((float) (-1 - i5), 1).build());
        }
        return arrayList;
    }

    public long getEventTime(int i3) {
        boolean z2 = false;
        Assertions.checkArgument(i3 >= 0);
        if (i3 < this.sortedCueTimesUs.length) {
            z2 = true;
        }
        Assertions.checkArgument(z2);
        return this.sortedCueTimesUs[i3];
    }

    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    public int getNextEventTimeIndex(long j2) {
        int binarySearchCeil = Util.binarySearchCeil(this.sortedCueTimesUs, j2, false, false);
        if (binarySearchCeil < this.sortedCueTimesUs.length) {
            return binarySearchCeil;
        }
        return -1;
    }
}
