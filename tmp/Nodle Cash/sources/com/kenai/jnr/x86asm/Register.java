package com.kenai.jnr.x86asm;

import android.support.v4.media.session.a;

@Deprecated
public final class Register extends BaseReg {
    private static final Register[] gpb = new Register[16];
    private static final Register[] gpd = new Register[16];
    private static final Register[] gpq = new Register[16];
    private static final Register[] gpw = new Register[16];

    static {
        for (int i3 = 0; i3 < 16; i3++) {
            gpb[i3] = new Register(i3, 1);
            gpw[i3] = new Register(i3 | 16, 2);
            gpd[i3] = new Register(i3 | 32, 4);
            gpq[i3] = new Register(i3 | 48, 8);
        }
    }

    public Register(int i3, int i4) {
        super(i3, i4);
    }

    public static final Register gpb(int i3) {
        return gpr(gpb, i3);
    }

    public static final Register gpd(int i3) {
        return gpr(gpd, i3);
    }

    public static final Register gpq(int i3) {
        return gpr(gpq, i3);
    }

    public static final Register gpr(int i3) {
        int i4 = i3 & 240;
        if (i4 == 0) {
            return gpb[i3 & 15];
        }
        if (i4 == 16) {
            return gpw[i3 & 15];
        }
        if (i4 == 32) {
            return gpd[i3 & 15];
        }
        if (i4 == 48) {
            return gpq[i3 & 15];
        }
        throw new IllegalArgumentException(a.j(i3, new StringBuilder("invalid register 0x")));
    }

    public static final Register gpw(int i3) {
        return gpr(gpw, i3);
    }

    private static final Register gpr(Register[] registerArr, int i3) {
        if (i3 >= 0 && i3 < 16) {
            return registerArr[i3];
        }
        throw new IllegalArgumentException(A.a.k("invalid register index ", i3));
    }
}
