package com.kenai.jnr.x86asm;

@Deprecated
public final class MMRegister extends BaseReg {
    static final MMRegister[] cache = new MMRegister[8];

    static {
        int i3 = 0;
        while (true) {
            MMRegister[] mMRegisterArr = cache;
            if (i3 < mMRegisterArr.length) {
                mMRegisterArr[i3] = new MMRegister(i3 | 96, 8);
                i3++;
            } else {
                return;
            }
        }
    }

    private MMRegister(int i3, int i4) {
        super(i3, i4);
    }

    public static final MMRegister mm(int i3) {
        if (i3 >= 0) {
            MMRegister[] mMRegisterArr = cache;
            if (i3 < mMRegisterArr.length) {
                return mMRegisterArr[i3];
            }
        }
        throw new IllegalArgumentException("invalid mm register");
    }
}
