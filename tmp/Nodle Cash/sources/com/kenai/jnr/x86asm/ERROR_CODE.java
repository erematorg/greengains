package com.kenai.jnr.x86asm;

@Deprecated
public enum ERROR_CODE {
    ERROR_NONE,
    ERROR_NO_HEAP_MEMORY,
    ERROR_NO_VIRTUAL_MEMORY,
    ERROR_UNKNOWN_INSTRUCTION,
    ERROR_ILLEGAL_INSTRUCTION,
    ERROR_ILLEGAL_ADDRESING,
    ERROR_ILLEGAL_SHORT_JUMP,
    _ERROR_COUNT;

    public final int intValue() {
        return ordinal();
    }
}
