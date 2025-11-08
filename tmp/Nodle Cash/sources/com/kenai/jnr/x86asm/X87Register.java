package com.kenai.jnr.x86asm;

@Deprecated
public final class X87Register extends BaseReg {
    static final X87Register[] cache = new X87Register[16];

    static {
        int i3 = 0;
        while (true) {
            X87Register[] x87RegisterArr = cache;
            if (i3 < x87RegisterArr.length) {
                x87RegisterArr[i3] = new X87Register(i3 | 80, 10);
                i3++;
            } else {
                return;
            }
        }
    }

    private X87Register(int i3, int i4) {
        super(i3, i4);
    }

    public static final X87Register st(int i3) {
        return x87(i3);
    }

    public static final X87Register x87(int i3) {
        if (i3 >= 0) {
            X87Register[] x87RegisterArr = cache;
            if (i3 < x87RegisterArr.length) {
                return x87RegisterArr[i3];
            }
        }
        throw new IllegalArgumentException("invalid x87 register");
    }
}
