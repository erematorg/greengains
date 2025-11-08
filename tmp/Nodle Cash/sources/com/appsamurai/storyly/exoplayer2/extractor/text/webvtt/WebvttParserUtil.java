package com.appsamurai.storyly.exoplayer2.extractor.text.webvtt;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.ParserException;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttParserUtil {
    private static final Pattern COMMENT = Pattern.compile("^NOTE([ \t].*)?$");
    private static final String WEBVTT_HEADER = "WEBVTT";

    private WebvttParserUtil() {
    }

    @Nullable
    public static Matcher findNextCueHeader(ParsableByteArray parsableByteArray) {
        String readLine;
        while (true) {
            String readLine2 = parsableByteArray.readLine();
            if (readLine2 == null) {
                return null;
            }
            if (COMMENT.matcher(readLine2).matches()) {
                do {
                    readLine = parsableByteArray.readLine();
                    if (readLine == null) {
                        break;
                    }
                } while (readLine.isEmpty());
            } else {
                Matcher matcher = WebvttCueParser.CUE_HEADER_PATTERN.matcher(readLine2);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }

    public static boolean isWebvttHeaderLine(ParsableByteArray parsableByteArray) {
        String readLine = parsableByteArray.readLine();
        return readLine != null && readLine.startsWith(WEBVTT_HEADER);
    }

    public static float parsePercentage(String str) throws NumberFormatException {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }

    public static long parseTimestampUs(String str) throws NumberFormatException {
        String[] splitAtFirst = Util.splitAtFirst(str, "\\.");
        long j2 = 0;
        for (String parseLong : Util.split(splitAtFirst[0], ":")) {
            j2 = (j2 * 60) + Long.parseLong(parseLong);
        }
        long j3 = j2 * 1000;
        if (splitAtFirst.length == 2) {
            j3 += Long.parseLong(splitAtFirst[1]);
        }
        return j3 * 1000;
    }

    public static void validateWebvttHeaderLine(ParsableByteArray parsableByteArray) throws ParserException {
        int position = parsableByteArray.getPosition();
        if (!isWebvttHeaderLine(parsableByteArray)) {
            parsableByteArray.setPosition(position);
            throw ParserException.createForMalformedContainer("Expected WEBVTT. Got " + parsableByteArray.readLine(), (Throwable) null);
        }
    }
}
