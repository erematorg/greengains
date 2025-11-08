package com.kenai.jnr.x86asm;

@Deprecated
public enum HINT {
    HINT_NONE(0),
    HINT_TAKEN(62),
    HINT_NOT_TAKEN(46);
    
    private final int value;

    private HINT(int i3) {
        this.value = i3;
    }

    public final int value() {
        return this.value;
    }

    public static final HINT valueOf(int i3) {
        if (i3 == 46) {
            return HINT_NOT_TAKEN;
        }
        if (i3 != 62) {
            return HINT_NONE;
        }
        return HINT_TAKEN;
    }
}
