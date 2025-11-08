package com.kenai.jnr.x86asm;

@Deprecated
public abstract class BaseReg extends Operand {
    public final int code;

    public BaseReg(int i3, int i4) {
        super(1, i4);
        this.code = i3;
    }

    public final int code() {
        return this.code;
    }

    public final int index() {
        return code() & 15;
    }

    public final int type() {
        return code() & 240;
    }
}
