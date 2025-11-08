package com.kenai.jnr.x86asm;

@Deprecated
public class Operand {
    private final int op;
    private final int size;

    public Operand(int i3, int i4) {
        this.op = i3;
        this.size = i4;
    }

    public boolean isImm() {
        return op() == 3;
    }

    public boolean isLabel() {
        return op() == 4;
    }

    public boolean isMem() {
        return op() == 2;
    }

    public boolean isNone() {
        return op() == 0;
    }

    public boolean isReg() {
        return op() == 1;
    }

    public final boolean isRegCode(int i3) {
        return (this instanceof BaseReg) && ((BaseReg) this).code() == i3;
    }

    public final boolean isRegIndex(int i3) {
        return (this instanceof BaseReg) && ((BaseReg) this).index() == i3;
    }

    public final boolean isRegMem() {
        return isMem() || isReg();
    }

    public final boolean isRegType(int i3) {
        return (this instanceof BaseReg) && ((BaseReg) this).type() == i3;
    }

    public int op() {
        return this.op;
    }

    public int size() {
        return this.size;
    }

    public final boolean isRegMem(int i3) {
        return isMem() || isRegType(i3);
    }
}
