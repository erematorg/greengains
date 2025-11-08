package com.appsamurai.storyly.exoplayer2.extractor.text.webvtt;

import android.text.TextUtils;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.extractor.text.SimpleSubtitleDecoder;
import com.appsamurai.storyly.exoplayer2.extractor.text.Subtitle;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleDecoderException;
import java.util.ArrayList;

public final class WebvttDecoder extends SimpleSubtitleDecoder {
    private static final String COMMENT_START = "NOTE";
    private static final int EVENT_COMMENT = 1;
    private static final int EVENT_CUE = 3;
    private static final int EVENT_END_OF_FILE = 0;
    private static final int EVENT_NONE = -1;
    private static final int EVENT_STYLE_BLOCK = 2;
    private static final String STYLE_START = "STYLE";
    private final WebvttCssParser cssParser = new WebvttCssParser();
    private final ParsableByteArray parsableWebvttData = new ParsableByteArray();

    public WebvttDecoder() {
        super("WebvttDecoder");
    }

    private static int getNextEvent(ParsableByteArray parsableByteArray) {
        int i3 = -1;
        int i4 = 0;
        while (i3 == -1) {
            i4 = parsableByteArray.getPosition();
            String readLine = parsableByteArray.readLine();
            i3 = readLine == null ? 0 : STYLE_START.equals(readLine) ? 2 : readLine.startsWith(COMMENT_START) ? 1 : 3;
        }
        parsableByteArray.setPosition(i4);
        return i3;
    }

    private static void skipComment(ParsableByteArray parsableByteArray) {
        do {
        } while (!TextUtils.isEmpty(parsableByteArray.readLine()));
    }

    public Subtitle decode(byte[] bArr, int i3, boolean z2) throws SubtitleDecoderException {
        WebvttCueInfo parseCue;
        this.parsableWebvttData.reset(bArr, i3);
        ArrayList arrayList = new ArrayList();
        try {
            WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);
            do {
            } while (!TextUtils.isEmpty(this.parsableWebvttData.readLine()));
            ArrayList arrayList2 = new ArrayList();
            while (true) {
                int nextEvent = getNextEvent(this.parsableWebvttData);
                if (nextEvent == 0) {
                    return new WebvttSubtitle(arrayList2);
                }
                if (nextEvent == 1) {
                    skipComment(this.parsableWebvttData);
                } else if (nextEvent == 2) {
                    if (arrayList2.isEmpty()) {
                        this.parsableWebvttData.readLine();
                        arrayList.addAll(this.cssParser.parseBlock(this.parsableWebvttData));
                    } else {
                        throw new SubtitleDecoderException("A style block was found after the first cue.");
                    }
                } else if (nextEvent == 3 && (parseCue = WebvttCueParser.parseCue(this.parsableWebvttData, arrayList)) != null) {
                    arrayList2.add(parseCue);
                }
            }
        } catch (ParserException e3) {
            throw new SubtitleDecoderException((Throwable) e3);
        }
    }
}
