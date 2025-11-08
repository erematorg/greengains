package com.google.zxing.client.result;

import com.appsamurai.storyly.exoplayer2.core.drm.b;
import com.google.zxing.Result;

public final class TelResultParser extends ResultParser {
    public TelParsedResult parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        if (!massagedText.startsWith("tel:") && !massagedText.startsWith("TEL:")) {
            return null;
        }
        String b3 = massagedText.startsWith("TEL:") ? b.b(new StringBuilder("tel:"), massagedText, 4) : massagedText;
        int indexOf = massagedText.indexOf(63, 4);
        return new TelParsedResult(indexOf < 0 ? massagedText.substring(4) : massagedText.substring(4, indexOf), b3, (String) null);
    }
}
