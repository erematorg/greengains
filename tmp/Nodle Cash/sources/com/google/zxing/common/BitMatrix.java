package com.google.zxing.common;

import androidx.constraintlayout.core.state.b;
import java.util.Arrays;

public final class BitMatrix implements Cloneable {
    private int[] bits;
    private int height;
    private int rowSize;
    private int width;

    public BitMatrix(int i3) {
        this(i3, i3);
    }

    private String buildToString(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder((this.width + 1) * this.height);
        for (int i3 = 0; i3 < this.height; i3++) {
            for (int i4 = 0; i4 < this.width; i4++) {
                sb.append(get(i4, i3) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    public static BitMatrix parse(boolean[][] zArr) {
        int length = zArr.length;
        int length2 = zArr[0].length;
        BitMatrix bitMatrix = new BitMatrix(length2, length);
        for (int i3 = 0; i3 < length; i3++) {
            boolean[] zArr2 = zArr[i3];
            for (int i4 = 0; i4 < length2; i4++) {
                if (zArr2[i4]) {
                    bitMatrix.set(i4, i3);
                }
            }
        }
        return bitMatrix;
    }

    public void clear() {
        int length = this.bits.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.bits[i3] = 0;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitMatrix)) {
            return false;
        }
        BitMatrix bitMatrix = (BitMatrix) obj;
        return this.width == bitMatrix.width && this.height == bitMatrix.height && this.rowSize == bitMatrix.rowSize && Arrays.equals(this.bits, bitMatrix.bits);
    }

    public void flip(int i3, int i4) {
        int i5 = (i3 / 32) + (i4 * this.rowSize);
        int[] iArr = this.bits;
        iArr[i5] = (1 << (i3 & 31)) ^ iArr[i5];
    }

    public boolean get(int i3, int i4) {
        return ((this.bits[(i3 / 32) + (i4 * this.rowSize)] >>> (i3 & 31)) & 1) != 0;
    }

    public int[] getBottomRightOnBit() {
        int length = this.bits.length - 1;
        while (length >= 0 && this.bits[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i3 = this.rowSize;
        int i4 = length / i3;
        int i5 = (length % i3) * 32;
        int i6 = this.bits[length];
        int i7 = 31;
        while ((i6 >>> i7) == 0) {
            i7--;
        }
        return new int[]{i5 + i7, i4};
    }

    public int[] getEnclosingRectangle() {
        int i3 = this.width;
        int i4 = this.height;
        int i5 = -1;
        int i6 = -1;
        for (int i7 = 0; i7 < this.height; i7++) {
            int i8 = 0;
            while (true) {
                int i9 = this.rowSize;
                if (i8 >= i9) {
                    break;
                }
                int i10 = this.bits[(i9 * i7) + i8];
                if (i10 != 0) {
                    if (i7 < i4) {
                        i4 = i7;
                    }
                    if (i7 > i6) {
                        i6 = i7;
                    }
                    int i11 = i8 * 32;
                    if (i11 < i3) {
                        int i12 = 0;
                        while ((i10 << (31 - i12)) == 0) {
                            i12++;
                        }
                        int i13 = i12 + i11;
                        if (i13 < i3) {
                            i3 = i13;
                        }
                    }
                    if (i11 + 31 > i5) {
                        int i14 = 31;
                        while ((i10 >>> i14) == 0) {
                            i14--;
                        }
                        int i15 = i11 + i14;
                        if (i15 > i5) {
                            i5 = i15;
                        }
                    }
                }
                i8++;
            }
        }
        if (i5 < i3 || i6 < i4) {
            return null;
        }
        return new int[]{i3, i4, (i5 - i3) + 1, (i6 - i4) + 1};
    }

    public int getHeight() {
        return this.height;
    }

    public BitArray getRow(int i3, BitArray bitArray) {
        if (bitArray == null || bitArray.getSize() < this.width) {
            bitArray = new BitArray(this.width);
        } else {
            bitArray.clear();
        }
        int i4 = i3 * this.rowSize;
        for (int i5 = 0; i5 < this.rowSize; i5++) {
            bitArray.setBulk(i5 * 32, this.bits[i4 + i5]);
        }
        return bitArray;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public int[] getTopLeftOnBit() {
        int[] iArr;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            iArr = this.bits;
            if (i4 < iArr.length && iArr[i4] == 0) {
                i4++;
            }
        }
        if (i4 == iArr.length) {
            return null;
        }
        int i5 = this.rowSize;
        int i6 = i4 / i5;
        int i7 = (i4 % i5) * 32;
        while ((iArr[i4] << (31 - i3)) == 0) {
            i3++;
        }
        return new int[]{i7 + i3, i6};
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int i3 = this.width;
        return Arrays.hashCode(this.bits) + ((((b.A(i3, 31, i3, 31) + this.height) * 31) + this.rowSize) * 31);
    }

    public void rotate(int i3) {
        int i4 = i3 % 360;
        if (i4 == 0) {
            return;
        }
        if (i4 == 90) {
            rotate90();
        } else if (i4 == 180) {
            rotate180();
        } else if (i4 == 270) {
            rotate90();
            rotate180();
        } else {
            throw new IllegalArgumentException("degrees must be a multiple of 0, 90, 180, or 270");
        }
    }

    public void rotate180() {
        BitArray bitArray = new BitArray(this.width);
        BitArray bitArray2 = new BitArray(this.width);
        int i3 = (this.height + 1) / 2;
        for (int i4 = 0; i4 < i3; i4++) {
            bitArray = getRow(i4, bitArray);
            int i5 = (this.height - 1) - i4;
            bitArray2 = getRow(i5, bitArray2);
            bitArray.reverse();
            bitArray2.reverse();
            setRow(i4, bitArray2);
            setRow(i5, bitArray);
        }
    }

    public void rotate90() {
        int i3 = this.height;
        int i4 = this.width;
        int i5 = (i3 + 31) / 32;
        int[] iArr = new int[(i5 * i4)];
        for (int i6 = 0; i6 < this.height; i6++) {
            for (int i7 = 0; i7 < this.width; i7++) {
                if (((this.bits[(i7 / 32) + (this.rowSize * i6)] >>> (i7 & 31)) & 1) != 0) {
                    int i8 = (i6 / 32) + (((i4 - 1) - i7) * i5);
                    iArr[i8] = iArr[i8] | (1 << (i6 & 31));
                }
            }
        }
        this.width = i3;
        this.height = i4;
        this.rowSize = i5;
        this.bits = iArr;
    }

    public void set(int i3, int i4) {
        int i5 = (i3 / 32) + (i4 * this.rowSize);
        int[] iArr = this.bits;
        iArr[i5] = (1 << (i3 & 31)) | iArr[i5];
    }

    public void setRegion(int i3, int i4, int i5, int i6) {
        if (i4 < 0 || i3 < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (i6 < 1 || i5 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int i7 = i5 + i3;
            int i8 = i6 + i4;
            if (i8 > this.height || i7 > this.width) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i4 < i8) {
                int i9 = this.rowSize * i4;
                for (int i10 = i3; i10 < i7; i10++) {
                    int[] iArr = this.bits;
                    int i11 = (i10 / 32) + i9;
                    iArr[i11] = iArr[i11] | (1 << (i10 & 31));
                }
                i4++;
            }
        }
    }

    public void setRow(int i3, BitArray bitArray) {
        int[] bitArray2 = bitArray.getBitArray();
        int[] iArr = this.bits;
        int i4 = this.rowSize;
        System.arraycopy(bitArray2, 0, iArr, i3 * i4, i4);
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public void unset(int i3, int i4) {
        int i5 = (i3 / 32) + (i4 * this.rowSize);
        int[] iArr = this.bits;
        iArr[i5] = (~(1 << (i3 & 31))) & iArr[i5];
    }

    public void xor(BitMatrix bitMatrix) {
        int i3 = this.width;
        if (i3 == bitMatrix.width && this.height == bitMatrix.height && this.rowSize == bitMatrix.rowSize) {
            BitArray bitArray = new BitArray(i3);
            for (int i4 = 0; i4 < this.height; i4++) {
                int i5 = this.rowSize * i4;
                int[] bitArray2 = bitMatrix.getRow(i4, bitArray).getBitArray();
                for (int i6 = 0; i6 < this.rowSize; i6++) {
                    int[] iArr = this.bits;
                    int i7 = i5 + i6;
                    iArr[i7] = iArr[i7] ^ bitArray2[i6];
                }
            }
            return;
        }
        throw new IllegalArgumentException("input matrix dimensions do not match");
    }

    public BitMatrix(int i3, int i4) {
        if (i3 < 1 || i4 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.width = i3;
        this.height = i4;
        int i5 = (i3 + 31) / 32;
        this.rowSize = i5;
        this.bits = new int[(i5 * i4)];
    }

    public BitMatrix clone() {
        return new BitMatrix(this.width, this.height, this.rowSize, (int[]) this.bits.clone());
    }

    public String toString(String str, String str2) {
        return buildToString(str, str2, "\n");
    }

    public void flip() {
        int length = this.bits.length;
        for (int i3 = 0; i3 < length; i3++) {
            int[] iArr = this.bits;
            iArr[i3] = ~iArr[i3];
        }
    }

    @Deprecated
    public String toString(String str, String str2, String str3) {
        return buildToString(str, str2, str3);
    }

    public static BitMatrix parse(String str, String str2, String str3) {
        if (str != null) {
            boolean[] zArr = new boolean[str.length()];
            int i3 = -1;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (i4 < str.length()) {
                if (str.charAt(i4) == 10 || str.charAt(i4) == 13) {
                    if (i5 > i6) {
                        if (i3 == -1) {
                            i3 = i5 - i6;
                        } else if (i5 - i6 != i3) {
                            throw new IllegalArgumentException("row lengths do not match");
                        }
                        i7++;
                        i6 = i5;
                    }
                    i4++;
                } else {
                    if (str.startsWith(str2, i4)) {
                        i4 += str2.length();
                        zArr[i5] = true;
                    } else if (str.startsWith(str3, i4)) {
                        i4 += str3.length();
                        zArr[i5] = false;
                    } else {
                        throw new IllegalArgumentException(com.appsamurai.storyly.exoplayer2.core.drm.b.b(new StringBuilder("illegal character encountered: "), str, i4));
                    }
                    i5++;
                }
            }
            if (i5 > i6) {
                if (i3 == -1) {
                    i3 = i5 - i6;
                } else if (i5 - i6 != i3) {
                    throw new IllegalArgumentException("row lengths do not match");
                }
                i7++;
            }
            BitMatrix bitMatrix = new BitMatrix(i3, i7);
            for (int i8 = 0; i8 < i5; i8++) {
                if (zArr[i8]) {
                    bitMatrix.set(i8 % i3, i8 / i3);
                }
            }
            return bitMatrix;
        }
        throw new IllegalArgumentException();
    }

    private BitMatrix(int i3, int i4, int i5, int[] iArr) {
        this.width = i3;
        this.height = i4;
        this.rowSize = i5;
        this.bits = iArr;
    }
}
