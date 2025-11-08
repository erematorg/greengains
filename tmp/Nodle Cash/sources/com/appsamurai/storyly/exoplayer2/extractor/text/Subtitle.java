package com.appsamurai.storyly.exoplayer2.extractor.text;

import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import java.util.List;

public interface Subtitle {
    List<Cue> getCues(long j2);

    long getEventTime(int i3);

    int getEventTimeCount();

    int getNextEventTimeIndex(long j2);
}
