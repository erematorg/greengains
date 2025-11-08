package com.kenai.jffi;

public final class ArrayFlags {
    public static final int CLEAR = 16;
    public static final int IN = 1;
    public static final int NULTERMINATE = 4;
    public static final int OUT = 2;
    public static final int PINNED = 8;

    private ArrayFlags() {
    }

    public static final boolean isIn(int i3) {
        return (i3 & 3) != 2;
    }

    public static final boolean isOut(int i3) {
        return (i3 & 3) != 1;
    }
}
