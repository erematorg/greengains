package com.appsamurai.storyly.exoplayer2.decoder;

import com.appsamurai.storyly.exoplayer2.common.C;

public abstract class Buffer {
    private int flags;

    public final void addFlag(int i3) {
        this.flags = i3 | this.flags;
    }

    public void clear() {
        this.flags = 0;
    }

    public final void clearFlag(int i3) {
        this.flags = (~i3) & this.flags;
    }

    public final boolean getFlag(int i3) {
        return (this.flags & i3) == i3;
    }

    public final boolean hasSupplementalData() {
        return getFlag(268435456);
    }

    public final boolean isDecodeOnly() {
        return getFlag(Integer.MIN_VALUE);
    }

    public final boolean isEndOfStream() {
        return getFlag(4);
    }

    public final boolean isFirstSample() {
        return getFlag(C.BUFFER_FLAG_FIRST_SAMPLE);
    }

    public final boolean isKeyFrame() {
        return getFlag(1);
    }

    public final void setFlags(int i3) {
        this.flags = i3;
    }
}
