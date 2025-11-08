package com.appsamurai.storyly.exoplayer2.extractor.text.dvb;

import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import java.util.List;

final class DvbSubtitle implements Subtitle {
    private final List<Cue> cues;

    public DvbSubtitle(List<Cue> list) {
        this.cues = list;
    }

    public List<Cue> getCues(long j2) {
        return this.cues;
    }

    public long getEventTime(int i3) {
        return 0;
    }

    public int getEventTimeCount() {
        return 1;
    }

    public int getNextEventTimeIndex(long j2) {
        return -1;
    }
}
