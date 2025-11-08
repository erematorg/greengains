package com.appsamurai.storyly.exoplayer2.extractor.text.tx3g;

import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import java.util.Collections;
import java.util.List;

final class Tx3gSubtitle implements Subtitle {
    public static final Tx3gSubtitle EMPTY = new Tx3gSubtitle();
    private final List<Cue> cues;

    public Tx3gSubtitle(Cue cue) {
        this.cues = Collections.singletonList(cue);
    }

    public List<Cue> getCues(long j2) {
        return j2 >= 0 ? this.cues : Collections.emptyList();
    }

    public long getEventTime(int i3) {
        Assertions.checkArgument(i3 == 0);
        return 0;
    }

    public int getEventTimeCount() {
        return 1;
    }

    public int getNextEventTimeIndex(long j2) {
        return j2 < 0 ? 0 : -1;
    }

    private Tx3gSubtitle() {
        this.cues = Collections.emptyList();
    }
}
