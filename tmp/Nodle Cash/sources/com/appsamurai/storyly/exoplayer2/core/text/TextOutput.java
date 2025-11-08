package com.appsamurai.storyly.exoplayer2.core.text;

import com.appsamurai.storyly.exoplayer2.common.text.Cue;
import com.appsamurai.storyly.exoplayer2.common.text.CueGroup;
import java.util.List;

public interface TextOutput {
    void onCues(CueGroup cueGroup);

    @Deprecated
    void onCues(List<Cue> list) {
    }
}
