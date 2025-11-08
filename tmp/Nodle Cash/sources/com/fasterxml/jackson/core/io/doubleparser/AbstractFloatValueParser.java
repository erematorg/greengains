package com.fasterxml.jackson.core.io.doubleparser;

abstract class AbstractFloatValueParser {
    static final byte[] CHAR_TO_HEX_MAP = new byte[128];
    static final byte DECIMAL_POINT_CLASS = -4;
    static final int MAX_EXPONENT_NUMBER = 1024;
    static final long MINIMAL_NINETEEN_DIGIT_INTEGER = 1000000000000000000L;
    static final byte OTHER_CLASS = -1;
    static final long PARSE_ERROR = -1;

    static {
        char c3 = 0;
        while (true) {
            byte[] bArr = CHAR_TO_HEX_MAP;
            if (c3 >= bArr.length) {
                break;
            }
            bArr[c3] = -1;
            c3 = (char) (c3 + 1);
        }
        for (char c4 = '0'; c4 <= '9'; c4 = (char) (c4 + 1)) {
            CHAR_TO_HEX_MAP[c4] = (byte) (c4 - '0');
        }
        for (char c5 = 'A'; c5 <= 'F'; c5 = (char) (c5 + 1)) {
            CHAR_TO_HEX_MAP[c5] = (byte) (c5 - '7');
        }
        for (char c6 = 'a'; c6 <= 'f'; c6 = (char) (c6 + 1)) {
            CHAR_TO_HEX_MAP[c6] = (byte) (c6 - 'W');
        }
        for (char c7 = '.'; c7 <= '.'; c7 = (char) (c7 + 1)) {
            CHAR_TO_HEX_MAP[c7] = DECIMAL_POINT_CLASS;
        }
    }
}
