package com.kenai.jnr.x86asm;

@Deprecated
public final class XMMRegister extends BaseReg {
    static final XMMRegister[] cache = new XMMRegister[16];

    static {
        int i3 = 0;
        while (true) {
            XMMRegister[] xMMRegisterArr = cache;
            if (i3 < xMMRegisterArr.length) {
                xMMRegisterArr[i3] = new XMMRegister(i3 | 112, 16);
                i3++;
            } else {
                return;
            }
        }
    }

    private XMMRegister(int i3, int i4) {
        super(i3, i4);
    }

    public static final XMMRegister xmm(int i3) {
        if (i3 >= 0) {
            XMMRegister[] xMMRegisterArr = cache;
            if (i3 < xMMRegisterArr.length) {
                return xMMRegisterArr[i3];
            }
        }
        throw new IllegalArgumentException("invalid xmm register");
    }
}
