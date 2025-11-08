package com.google.zxing.datamatrix.encoder;

import java.util.Arrays;

public class DefaultPlacement {
    private final byte[] bits;
    private final CharSequence codewords;
    private final int numcols;
    private final int numrows;

    public DefaultPlacement(CharSequence charSequence, int i3, int i4) {
        this.codewords = charSequence;
        this.numcols = i3;
        this.numrows = i4;
        byte[] bArr = new byte[(i3 * i4)];
        this.bits = bArr;
        Arrays.fill(bArr, (byte) -1);
    }

    private void corner1(int i3) {
        module(this.numrows - 1, 0, i3, 1);
        module(this.numrows - 1, 1, i3, 2);
        module(this.numrows - 1, 2, i3, 3);
        module(0, this.numcols - 2, i3, 4);
        module(0, this.numcols - 1, i3, 5);
        module(1, this.numcols - 1, i3, 6);
        module(2, this.numcols - 1, i3, 7);
        module(3, this.numcols - 1, i3, 8);
    }

    private void corner2(int i3) {
        module(this.numrows - 3, 0, i3, 1);
        module(this.numrows - 2, 0, i3, 2);
        module(this.numrows - 1, 0, i3, 3);
        module(0, this.numcols - 4, i3, 4);
        module(0, this.numcols - 3, i3, 5);
        module(0, this.numcols - 2, i3, 6);
        module(0, this.numcols - 1, i3, 7);
        module(1, this.numcols - 1, i3, 8);
    }

    private void corner3(int i3) {
        module(this.numrows - 3, 0, i3, 1);
        module(this.numrows - 2, 0, i3, 2);
        module(this.numrows - 1, 0, i3, 3);
        module(0, this.numcols - 2, i3, 4);
        module(0, this.numcols - 1, i3, 5);
        module(1, this.numcols - 1, i3, 6);
        module(2, this.numcols - 1, i3, 7);
        module(3, this.numcols - 1, i3, 8);
    }

    private void corner4(int i3) {
        module(this.numrows - 1, 0, i3, 1);
        module(this.numrows - 1, this.numcols - 1, i3, 2);
        module(0, this.numcols - 3, i3, 3);
        module(0, this.numcols - 2, i3, 4);
        module(0, this.numcols - 1, i3, 5);
        module(1, this.numcols - 3, i3, 6);
        module(1, this.numcols - 2, i3, 7);
        module(1, this.numcols - 1, i3, 8);
    }

    private void module(int i3, int i4, int i5, int i6) {
        if (i3 < 0) {
            int i7 = this.numrows;
            i3 += i7;
            i4 += 4 - ((i7 + 4) % 8);
        }
        if (i4 < 0) {
            int i8 = this.numcols;
            i4 += i8;
            i3 += 4 - ((i8 + 4) % 8);
        }
        boolean z2 = true;
        if ((this.codewords.charAt(i5) & (1 << (8 - i6))) == 0) {
            z2 = false;
        }
        setBit(i4, i3, z2);
    }

    private boolean noBit(int i3, int i4) {
        return this.bits[(i4 * this.numcols) + i3] < 0;
    }

    private void setBit(int i3, int i4, boolean z2) {
        this.bits[(i4 * this.numcols) + i3] = z2 ? (byte) 1 : 0;
    }

    private void utah(int i3, int i4, int i5) {
        int i6 = i3 - 2;
        int i7 = i4 - 2;
        module(i6, i7, i5, 1);
        int i8 = i4 - 1;
        module(i6, i8, i5, 2);
        int i9 = i3 - 1;
        module(i9, i7, i5, 3);
        module(i9, i8, i5, 4);
        module(i9, i4, i5, 5);
        module(i3, i7, i5, 6);
        module(i3, i8, i5, 7);
        module(i3, i4, i5, 8);
    }

    public final boolean getBit(int i3, int i4) {
        return this.bits[(i4 * this.numcols) + i3] == 1;
    }

    public final byte[] getBits() {
        return this.bits;
    }

    public final int getNumcols() {
        return this.numcols;
    }

    public final int getNumrows() {
        return this.numrows;
    }

    public final void place() {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = 0;
        int i7 = 4;
        while (true) {
            if (i7 == this.numrows && i5 == 0) {
                corner1(i6);
                i6++;
            }
            if (i7 == this.numrows - 2 && i5 == 0 && this.numcols % 4 != 0) {
                corner2(i6);
                i6++;
            }
            if (i7 == this.numrows - 2 && i5 == 0 && this.numcols % 8 == 4) {
                corner3(i6);
                i6++;
            }
            if (i7 == this.numrows + 4 && i5 == 2 && this.numcols % 8 == 0) {
                corner4(i6);
                i6++;
            }
            while (true) {
                if (i7 < this.numrows && i5 >= 0 && noBit(i5, i7)) {
                    utah(i7, i5, i6);
                    i6++;
                }
                int i8 = i7 - 2;
                int i9 = i5 + 2;
                if (i8 < 0 || i9 >= this.numcols) {
                    int i10 = i7 - 1;
                    int i11 = i5 + 5;
                } else {
                    i7 = i8;
                    i5 = i9;
                }
            }
            int i102 = i7 - 1;
            int i112 = i5 + 5;
            while (true) {
                if (i102 >= 0 && i112 < this.numcols && noBit(i112, i102)) {
                    utah(i102, i112, i6);
                    i6++;
                }
                int i12 = i102 + 2;
                int i13 = i112 - 2;
                i3 = this.numrows;
                if (i12 >= i3 || i13 < 0) {
                    i7 = i102 + 5;
                    i5 = i112 - 1;
                } else {
                    i102 = i12;
                    i112 = i13;
                }
            }
            i7 = i102 + 5;
            i5 = i112 - 1;
            if (i7 >= i3 && i5 >= (i4 = this.numcols)) {
                break;
            }
        }
        if (noBit(i4 - 1, i3 - 1)) {
            setBit(this.numcols - 1, this.numrows - 1, true);
            setBit(this.numcols - 2, this.numrows - 2, true);
        }
    }
}
