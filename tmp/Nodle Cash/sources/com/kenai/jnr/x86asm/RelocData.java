package com.kenai.jnr.x86asm;

@Deprecated
class RelocData {
    final long destination;
    final int offset;
    final int size;
    final Type type;

    public enum Type {
        ABSOLUTE_TO_ABSOLUTE,
        RELATIVE_TO_ABSOLUTE,
        ABSOLUTE_TO_RELATIVE,
        ABSOLUTE_TO_RELATIVE_TRAMPOLINE
    }

    public RelocData(Type type2, int i3, int i4, long j2) {
        this.type = type2;
        this.size = i3;
        this.offset = i4;
        this.destination = j2;
    }
}
