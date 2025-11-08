package com.google.zxing.common;

import java.util.Arrays;
import org.apache.commons.lang3.ClassUtils;

public final class BitArray implements Cloneable {
    private static final int[] EMPTY_BITS = new int[0];
    private static final float LOAD_FACTOR = 0.75f;
    private int[] bits;
    private int size;

    public BitArray() {
        this.size = 0;
        this.bits = EMPTY_BITS;
    }

    private void ensureCapacity(int i3) {
        if (i3 > this.bits.length * 32) {
            int[] makeArray = makeArray((int) Math.ceil((double) (((float) i3) / 0.75f)));
            int[] iArr = this.bits;
            System.arraycopy(iArr, 0, makeArray, 0, iArr.length);
            this.bits = makeArray;
        }
    }

    private static int[] makeArray(int i3) {
        return new int[((i3 + 31) / 32)];
    }

    public void appendBit(boolean z2) {
        ensureCapacity(this.size + 1);
        if (z2) {
            int[] iArr = this.bits;
            int i3 = this.size;
            int i4 = i3 / 32;
            iArr[i4] = (1 << (i3 & 31)) | iArr[i4];
        }
        this.size++;
    }

    public void appendBitArray(BitArray bitArray) {
        int i3 = bitArray.size;
        ensureCapacity(this.size + i3);
        for (int i4 = 0; i4 < i3; i4++) {
            appendBit(bitArray.get(i4));
        }
    }

    public void appendBits(int i3, int i4) {
        if (i4 < 0 || i4 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        int i5 = this.size;
        ensureCapacity(i5 + i4);
        for (int i6 = i4 - 1; i6 >= 0; i6--) {
            if (((1 << i6) & i3) != 0) {
                int[] iArr = this.bits;
                int i7 = i5 / 32;
                iArr[i7] = iArr[i7] | (1 << (i5 & 31));
            }
            i5++;
        }
        this.size = i5;
    }

    public void clear() {
        int length = this.bits.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.bits[i3] = 0;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitArray)) {
            return false;
        }
        BitArray bitArray = (BitArray) obj;
        return this.size == bitArray.size && Arrays.equals(this.bits, bitArray.bits);
    }

    public void flip(int i3) {
        int[] iArr = this.bits;
        int i4 = i3 / 32;
        iArr[i4] = (1 << (i3 & 31)) ^ iArr[i4];
    }

    public boolean get(int i3) {
        return (this.bits[i3 / 32] & (1 << (i3 & 31))) != 0;
    }

    public int[] getBitArray() {
        return this.bits;
    }

    public int getNextSet(int i3) {
        int i4 = this.size;
        if (i3 >= i4) {
            return i4;
        }
        int i5 = i3 / 32;
        int i6 = (-(1 << (i3 & 31))) & this.bits[i5];
        while (i6 == 0) {
            i5++;
            int[] iArr = this.bits;
            if (i5 == iArr.length) {
                return this.size;
            }
            i6 = iArr[i5];
        }
        return Math.min(Integer.numberOfTrailingZeros(i6) + (i5 * 32), this.size);
    }

    public int getNextUnset(int i3) {
        int i4 = this.size;
        if (i3 >= i4) {
            return i4;
        }
        int i5 = i3 / 32;
        int i6 = (-(1 << (i3 & 31))) & (~this.bits[i5]);
        while (i6 == 0) {
            i5++;
            int[] iArr = this.bits;
            if (i5 == iArr.length) {
                return this.size;
            }
            i6 = ~iArr[i5];
        }
        return Math.min(Integer.numberOfTrailingZeros(i6) + (i5 * 32), this.size);
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    public int hashCode() {
        return Arrays.hashCode(this.bits) + (this.size * 31);
    }

    public boolean isRange(int i3, int i4, boolean z2) {
        if (i4 < i3 || i3 < 0 || i4 > this.size) {
            throw new IllegalArgumentException();
        } else if (i4 == i3) {
            return true;
        } else {
            int i5 = i4 - 1;
            int i6 = i3 / 32;
            int i7 = i5 / 32;
            int i8 = i6;
            while (i8 <= i7) {
                int i9 = 31;
                int i10 = i8 > i6 ? 0 : i3 & 31;
                if (i8 >= i7) {
                    i9 = 31 & i5;
                }
                int i11 = (2 << i9) - (1 << i10);
                int i12 = this.bits[i8] & i11;
                if (!z2) {
                    i11 = 0;
                }
                if (i12 != i11) {
                    return false;
                }
                i8++;
            }
            return true;
        }
    }

    public void reverse() {
        int[] iArr = new int[this.bits.length];
        int i3 = (this.size - 1) / 32;
        int i4 = i3 + 1;
        for (int i5 = 0; i5 < i4; i5++) {
            iArr[i3 - i5] = Integer.reverse(this.bits[i5]);
        }
        int i6 = this.size;
        int i7 = i4 * 32;
        if (i6 != i7) {
            int i8 = i7 - i6;
            int i9 = iArr[0] >>> i8;
            for (int i10 = 1; i10 < i4; i10++) {
                int i11 = iArr[i10];
                iArr[i10 - 1] = i9 | (i11 << (32 - i8));
                i9 = i11 >>> i8;
            }
            iArr[i3] = i9;
        }
        this.bits = iArr;
    }

    public void set(int i3) {
        int[] iArr = this.bits;
        int i4 = i3 / 32;
        iArr[i4] = (1 << (i3 & 31)) | iArr[i4];
    }

    public void setBulk(int i3, int i4) {
        this.bits[i3 / 32] = i4;
    }

    public void setRange(int i3, int i4) {
        if (i4 < i3 || i3 < 0 || i4 > this.size) {
            throw new IllegalArgumentException();
        } else if (i4 != i3) {
            int i5 = i4 - 1;
            int i6 = i3 / 32;
            int i7 = i5 / 32;
            int i8 = i6;
            while (i8 <= i7) {
                int i9 = 31;
                int i10 = i8 > i6 ? 0 : i3 & 31;
                if (i8 >= i7) {
                    i9 = 31 & i5;
                }
                int i11 = (2 << i9) - (1 << i10);
                int[] iArr = this.bits;
                iArr[i8] = i11 | iArr[i8];
                i8++;
            }
        }
    }

    public void toBytes(int i3, byte[] bArr, int i4, int i5) {
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = 0;
            for (int i8 = 0; i8 < 8; i8++) {
                if (get(i3)) {
                    i7 |= 1 << (7 - i8);
                }
                i3++;
            }
            bArr[i4 + i6] = (byte) i7;
        }
    }

    public String toString() {
        int i3 = this.size;
        StringBuilder sb = new StringBuilder((i3 / 8) + i3 + 1);
        for (int i4 = 0; i4 < this.size; i4++) {
            if ((i4 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(get(i4) ? 'X' : ClassUtils.PACKAGE_SEPARATOR_CHAR);
        }
        return sb.toString();
    }

    public void xor(BitArray bitArray) {
        if (this.size == bitArray.size) {
            int i3 = 0;
            while (true) {
                int[] iArr = this.bits;
                if (i3 < iArr.length) {
                    iArr[i3] = iArr[i3] ^ bitArray.bits[i3];
                    i3++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Sizes don't match");
        }
    }

    public BitArray clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }

    public BitArray(int i3) {
        this.size = i3;
        this.bits = makeArray(i3);
    }

    public BitArray(int[] iArr, int i3) {
        this.bits = iArr;
        this.size = i3;
    }
}
