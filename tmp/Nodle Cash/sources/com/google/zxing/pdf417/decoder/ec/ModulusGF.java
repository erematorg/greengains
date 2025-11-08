package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.pdf417.PDF417Common;

public final class ModulusGF {
    public static final ModulusGF PDF417_GF = new ModulusGF(PDF417Common.NUMBER_OF_CODEWORDS, 3);
    private final int[] expTable;
    private final int[] logTable;
    private final int modulus;
    private final ModulusPoly one;
    private final ModulusPoly zero;

    private ModulusGF(int i3, int i4) {
        this.modulus = i3;
        this.expTable = new int[i3];
        this.logTable = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            this.expTable[i6] = i5;
            i5 = (i5 * i4) % i3;
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            this.logTable[this.expTable[i7]] = i7;
        }
        this.zero = new ModulusPoly(this, new int[]{0});
        this.one = new ModulusPoly(this, new int[]{1});
    }

    public int add(int i3, int i4) {
        return (i3 + i4) % this.modulus;
    }

    public ModulusPoly buildMonomial(int i3, int i4) {
        if (i3 < 0) {
            throw new IllegalArgumentException();
        } else if (i4 == 0) {
            return this.zero;
        } else {
            int[] iArr = new int[(i3 + 1)];
            iArr[0] = i4;
            return new ModulusPoly(this, iArr);
        }
    }

    public int exp(int i3) {
        return this.expTable[i3];
    }

    public ModulusPoly getOne() {
        return this.one;
    }

    public int getSize() {
        return this.modulus;
    }

    public ModulusPoly getZero() {
        return this.zero;
    }

    public int inverse(int i3) {
        if (i3 != 0) {
            return this.expTable[(this.modulus - this.logTable[i3]) - 1];
        }
        throw new ArithmeticException();
    }

    public int log(int i3) {
        if (i3 != 0) {
            return this.logTable[i3];
        }
        throw new IllegalArgumentException();
    }

    public int multiply(int i3, int i4) {
        if (i3 == 0 || i4 == 0) {
            return 0;
        }
        int[] iArr = this.expTable;
        int[] iArr2 = this.logTable;
        return iArr[(iArr2[i3] + iArr2[i4]) % (this.modulus - 1)];
    }

    public int subtract(int i3, int i4) {
        int i5 = this.modulus;
        return ((i3 + i5) - i4) % i5;
    }
}
