package com.appsamurai.storyly.exoplayer2.extractor.text.cea;

import com.appsamurai.storyly.exoplayer2.extractor.text.cea.Cea708Decoder;
import java.util.Comparator;

public final /* synthetic */ class a implements Comparator {
    public final int compare(Object obj, Object obj2) {
        return Integer.compare(((Cea708Decoder.Cea708CueInfo) obj2).priority, ((Cea708Decoder.Cea708CueInfo) obj).priority);
    }
}
