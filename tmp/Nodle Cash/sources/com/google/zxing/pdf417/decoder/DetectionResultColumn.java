package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    public DetectionResultColumn(BoundingBox boundingBox2) {
        this.boundingBox = new BoundingBox(boundingBox2);
        this.codewords = new Codeword[((boundingBox2.getMaxY() - boundingBox2.getMinY()) + 1)];
    }

    public final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    public final Codeword getCodeword(int i3) {
        return this.codewords[imageRowToCodewordIndex(i3)];
    }

    public final Codeword getCodewordNearby(int i3) {
        Codeword codeword;
        Codeword codeword2;
        Codeword codeword3 = getCodeword(i3);
        if (codeword3 != null) {
            return codeword3;
        }
        for (int i4 = 1; i4 < 5; i4++) {
            int imageRowToCodewordIndex = imageRowToCodewordIndex(i3) - i4;
            if (imageRowToCodewordIndex >= 0 && (codeword2 = this.codewords[imageRowToCodewordIndex]) != null) {
                return codeword2;
            }
            int imageRowToCodewordIndex2 = imageRowToCodewordIndex(i3) + i4;
            Codeword[] codewordArr = this.codewords;
            if (imageRowToCodewordIndex2 < codewordArr.length && (codeword = codewordArr[imageRowToCodewordIndex2]) != null) {
                return codeword;
            }
        }
        return null;
    }

    public final Codeword[] getCodewords() {
        return this.codewords;
    }

    public final int imageRowToCodewordIndex(int i3) {
        return i3 - this.boundingBox.getMinY();
    }

    public final void setCodeword(int i3, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(i3)] = codeword;
    }

    public String toString() {
        Formatter formatter = new Formatter();
        try {
            int i3 = 0;
            for (Codeword codeword : this.codewords) {
                if (codeword == null) {
                    formatter.format("%3d:    |   %n", new Object[]{Integer.valueOf(i3)});
                    i3++;
                } else {
                    formatter.format("%3d: %3d|%3d%n", new Object[]{Integer.valueOf(i3), Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue())});
                    i3++;
                }
            }
            String formatter2 = formatter.toString();
            formatter.close();
            return formatter2;
        } catch (Throwable th) {
            try {
                formatter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
