package com.google.zxing.common.reedsolomon;

import android.support.v4.media.session.a;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class GenericGF {
    public static final GenericGF AZTEC_DATA_10 = new GenericGF(1033, 1024, 1);
    public static final GenericGF AZTEC_DATA_12 = new GenericGF(4201, 4096, 1);
    public static final GenericGF AZTEC_DATA_6;
    public static final GenericGF AZTEC_DATA_8;
    public static final GenericGF AZTEC_PARAM = new GenericGF(19, 16, 1);
    public static final GenericGF DATA_MATRIX_FIELD_256;
    public static final GenericGF MAXICODE_FIELD_64;
    public static final GenericGF QR_CODE_FIELD_256 = new GenericGF(285, 256, 0);
    private final int[] expTable;
    private final int generatorBase;
    private final int[] logTable;
    private final GenericGFPoly one;
    private final int primitive;
    private final int size;
    private final GenericGFPoly zero;

    static {
        GenericGF genericGF = new GenericGF(67, 64, 1);
        AZTEC_DATA_6 = genericGF;
        GenericGF genericGF2 = new GenericGF(301, 256, 1);
        DATA_MATRIX_FIELD_256 = genericGF2;
        AZTEC_DATA_8 = genericGF2;
        MAXICODE_FIELD_64 = genericGF;
    }

    public GenericGF(int i3, int i4, int i5) {
        this.primitive = i3;
        this.size = i4;
        this.generatorBase = i5;
        this.expTable = new int[i4];
        this.logTable = new int[i4];
        int i6 = 1;
        for (int i7 = 0; i7 < i4; i7++) {
            this.expTable[i7] = i6;
            i6 *= 2;
            if (i6 >= i4) {
                i6 = (i6 ^ i3) & (i4 - 1);
            }
        }
        for (int i8 = 0; i8 < i4 - 1; i8++) {
            this.logTable[this.expTable[i8]] = i8;
        }
        this.zero = new GenericGFPoly(this, new int[]{0});
        this.one = new GenericGFPoly(this, new int[]{1});
    }

    public static int addOrSubtract(int i3, int i4) {
        return i3 ^ i4;
    }

    public GenericGFPoly buildMonomial(int i3, int i4) {
        if (i3 < 0) {
            throw new IllegalArgumentException();
        } else if (i4 == 0) {
            return this.zero;
        } else {
            int[] iArr = new int[(i3 + 1)];
            iArr[0] = i4;
            return new GenericGFPoly(this, iArr);
        }
    }

    public int exp(int i3) {
        return this.expTable[i3];
    }

    public int getGeneratorBase() {
        return this.generatorBase;
    }

    public GenericGFPoly getOne() {
        return this.one;
    }

    public int getSize() {
        return this.size;
    }

    public GenericGFPoly getZero() {
        return this.zero;
    }

    public int inverse(int i3) {
        if (i3 != 0) {
            return this.expTable[(this.size - this.logTable[i3]) - 1];
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
        return iArr[(iArr2[i3] + iArr2[i4]) % (this.size - 1)];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GF(0x");
        sb.append(Integer.toHexString(this.primitive));
        sb.append(AbstractJsonLexerKt.COMMA);
        return a.p(sb, this.size, ')');
    }
}
