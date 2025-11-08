package com.kenai.jnr.x86asm;

@Deprecated
final class LinkData {
    long displacement;
    final int offset;
    int relocId;

    public LinkData(int i3, long j2, int i4) {
        this.offset = i3;
        this.displacement = j2;
        this.relocId = i4;
    }
}
