package com.google.zxing.qrcode.decoder;

public enum Mode {
    TERMINATOR(new int[]{0, 0, 0}, 0),
    NUMERIC(new int[]{10, 12, 14}, 1),
    ALPHANUMERIC(new int[]{9, 11, 13}, 2),
    STRUCTURED_APPEND(new int[]{0, 0, 0}, 3),
    BYTE(new int[]{8, 16, 16}, 4),
    ECI(new int[]{0, 0, 0}, 7),
    KANJI(new int[]{8, 10, 12}, 8),
    FNC1_FIRST_POSITION(new int[]{0, 0, 0}, 5),
    FNC1_SECOND_POSITION(new int[]{0, 0, 0}, 9),
    HANZI(new int[]{8, 10, 12}, 13);
    
    private final int bits;
    private final int[] characterCountBitsForVersions;

    private Mode(int[] iArr, int i3) {
        this.characterCountBitsForVersions = iArr;
        this.bits = i3;
    }

    public static Mode forBits(int i3) {
        if (i3 == 0) {
            return TERMINATOR;
        }
        if (i3 == 1) {
            return NUMERIC;
        }
        if (i3 == 2) {
            return ALPHANUMERIC;
        }
        if (i3 == 3) {
            return STRUCTURED_APPEND;
        }
        if (i3 == 4) {
            return BYTE;
        }
        if (i3 == 5) {
            return FNC1_FIRST_POSITION;
        }
        if (i3 == 7) {
            return ECI;
        }
        if (i3 == 8) {
            return KANJI;
        }
        if (i3 == 9) {
            return FNC1_SECOND_POSITION;
        }
        if (i3 == 13) {
            return HANZI;
        }
        throw new IllegalArgumentException();
    }

    public int getBits() {
        return this.bits;
    }

    public int getCharacterCountBits(Version version) {
        int versionNumber = version.getVersionNumber();
        return this.characterCountBitsForVersions[versionNumber <= 9 ? 0 : versionNumber <= 26 ? (char) 1 : 2];
    }
}
