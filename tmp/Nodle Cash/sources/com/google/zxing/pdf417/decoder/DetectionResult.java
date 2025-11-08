package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.Formatter;

final class DetectionResult {
    private static final int ADJUST_ROW_NUMBER_SKIP = 2;
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns;

    public DetectionResult(BarcodeMetadata barcodeMetadata2, BoundingBox boundingBox2) {
        this.barcodeMetadata = barcodeMetadata2;
        int columnCount = barcodeMetadata2.getColumnCount();
        this.barcodeColumnCount = columnCount;
        this.boundingBox = boundingBox2;
        this.detectionResultColumns = new DetectionResultColumn[(columnCount + 2)];
    }

    private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(this.barcodeMetadata);
        }
    }

    private static boolean adjustRowNumber(Codeword codeword, Codeword codeword2) {
        if (codeword2 == null || !codeword2.hasValidRowNumber() || codeword2.getBucket() != codeword.getBucket()) {
            return false;
        }
        codeword.setRowNumber(codeword2.getRowNumber());
        return true;
    }

    private static int adjustRowNumberIfValid(int i3, int i4, Codeword codeword) {
        if (codeword == null || codeword.hasValidRowNumber()) {
            return i4;
        }
        if (!codeword.isValidRowNumber(i3)) {
            return i4 + 1;
        }
        codeword.setRowNumber(i3);
        return 0;
    }

    private int adjustRowNumbers() {
        int adjustRowNumbersByRow = adjustRowNumbersByRow();
        if (adjustRowNumbersByRow == 0) {
            return 0;
        }
        for (int i3 = 1; i3 < this.barcodeColumnCount + 1; i3++) {
            Codeword[] codewords = this.detectionResultColumns[i3].getCodewords();
            for (int i4 = 0; i4 < codewords.length; i4++) {
                Codeword codeword = codewords[i4];
                if (codeword != null && !codeword.hasValidRowNumber()) {
                    adjustRowNumbers(i3, i4, codewords);
                }
            }
        }
        return adjustRowNumbersByRow;
    }

    private int adjustRowNumbersByRow() {
        adjustRowNumbersFromBothRI();
        return adjustRowNumbersFromLRI() + adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        if (detectionResultColumn != null && detectionResultColumnArr[this.barcodeColumnCount + 1] != null) {
            Codeword[] codewords = detectionResultColumn.getCodewords();
            Codeword[] codewords2 = this.detectionResultColumns[this.barcodeColumnCount + 1].getCodewords();
            for (int i3 = 0; i3 < codewords.length; i3++) {
                Codeword codeword = codewords[i3];
                if (!(codeword == null || codewords2[i3] == null || codeword.getRowNumber() != codewords2[i3].getRowNumber())) {
                    for (int i4 = 1; i4 <= this.barcodeColumnCount; i4++) {
                        Codeword codeword2 = this.detectionResultColumns[i4].getCodewords()[i3];
                        if (codeword2 != null) {
                            codeword2.setRowNumber(codewords[i3].getRowNumber());
                            if (!codeword2.hasValidRowNumber()) {
                                this.detectionResultColumns[i4].getCodewords()[i3] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private int adjustRowNumbersFromLRI() {
        DetectionResultColumn detectionResultColumn = this.detectionResultColumns[0];
        if (detectionResultColumn == null) {
            return 0;
        }
        Codeword[] codewords = detectionResultColumn.getCodewords();
        int i3 = 0;
        for (int i4 = 0; i4 < codewords.length; i4++) {
            Codeword codeword = codewords[i4];
            if (codeword != null) {
                int rowNumber = codeword.getRowNumber();
                int i5 = 0;
                for (int i6 = 1; i6 < this.barcodeColumnCount + 1 && i5 < 2; i6++) {
                    Codeword codeword2 = this.detectionResultColumns[i6].getCodewords()[i4];
                    if (codeword2 != null) {
                        i5 = adjustRowNumberIfValid(rowNumber, i5, codeword2);
                        if (!codeword2.hasValidRowNumber()) {
                            i3++;
                        }
                    }
                }
            }
        }
        return i3;
    }

    private int adjustRowNumbersFromRRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i3 = this.barcodeColumnCount;
        if (detectionResultColumnArr[i3 + 1] == null) {
            return 0;
        }
        Codeword[] codewords = detectionResultColumnArr[i3 + 1].getCodewords();
        int i4 = 0;
        for (int i5 = 0; i5 < codewords.length; i5++) {
            Codeword codeword = codewords[i5];
            if (codeword != null) {
                int rowNumber = codeword.getRowNumber();
                int i6 = 0;
                for (int i7 = this.barcodeColumnCount + 1; i7 > 0 && i6 < 2; i7--) {
                    Codeword codeword2 = this.detectionResultColumns[i7].getCodewords()[i5];
                    if (codeword2 != null) {
                        i6 = adjustRowNumberIfValid(rowNumber, i6, codeword2);
                        if (!codeword2.hasValidRowNumber()) {
                            i4++;
                        }
                    }
                }
            }
        }
        return i4;
    }

    public int getBarcodeColumnCount() {
        return this.barcodeColumnCount;
    }

    public int getBarcodeECLevel() {
        return this.barcodeMetadata.getErrorCorrectionLevel();
    }

    public int getBarcodeRowCount() {
        return this.barcodeMetadata.getRowCount();
    }

    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    public DetectionResultColumn getDetectionResultColumn(int i3) {
        return this.detectionResultColumns[i3];
    }

    public DetectionResultColumn[] getDetectionResultColumns() {
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[this.barcodeColumnCount + 1]);
        int i3 = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        while (true) {
            int adjustRowNumbers = adjustRowNumbers();
            if (adjustRowNumbers > 0 && adjustRowNumbers < i3) {
                i3 = adjustRowNumbers;
            }
        }
        return this.detectionResultColumns;
    }

    public void setBoundingBox(BoundingBox boundingBox2) {
        this.boundingBox = boundingBox2;
    }

    public void setDetectionResultColumn(int i3, DetectionResultColumn detectionResultColumn) {
        this.detectionResultColumns[i3] = detectionResultColumn;
    }

    public String toString() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        if (detectionResultColumn == null) {
            detectionResultColumn = detectionResultColumnArr[this.barcodeColumnCount + 1];
        }
        Formatter formatter = new Formatter();
        int i3 = 0;
        while (i3 < detectionResultColumn.getCodewords().length) {
            try {
                formatter.format("CW %3d:", new Object[]{Integer.valueOf(i3)});
                for (int i4 = 0; i4 < this.barcodeColumnCount + 2; i4++) {
                    DetectionResultColumn detectionResultColumn2 = this.detectionResultColumns[i4];
                    if (detectionResultColumn2 == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        Codeword codeword = detectionResultColumn2.getCodewords()[i3];
                        if (codeword == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            formatter.format(" %3d|%3d", new Object[]{Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue())});
                        }
                    }
                }
                formatter.format("%n", new Object[0]);
                i3++;
            } catch (Throwable th) {
                try {
                    formatter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    private void adjustRowNumbers(int i3, int i4, Codeword[] codewordArr) {
        Codeword codeword = codewordArr[i4];
        Codeword[] codewords = this.detectionResultColumns[i3 - 1].getCodewords();
        DetectionResultColumn detectionResultColumn = this.detectionResultColumns[i3 + 1];
        Codeword[] codewords2 = detectionResultColumn != null ? detectionResultColumn.getCodewords() : codewords;
        Codeword[] codewordArr2 = new Codeword[14];
        codewordArr2[2] = codewords[i4];
        codewordArr2[3] = codewords2[i4];
        int i5 = 0;
        if (i4 > 0) {
            int i6 = i4 - 1;
            codewordArr2[0] = codewordArr[i6];
            codewordArr2[4] = codewords[i6];
            codewordArr2[5] = codewords2[i6];
        }
        if (i4 > 1) {
            int i7 = i4 - 2;
            codewordArr2[8] = codewordArr[i7];
            codewordArr2[10] = codewords[i7];
            codewordArr2[11] = codewords2[i7];
        }
        if (i4 < codewordArr.length - 1) {
            int i8 = i4 + 1;
            codewordArr2[1] = codewordArr[i8];
            codewordArr2[6] = codewords[i8];
            codewordArr2[7] = codewords2[i8];
        }
        if (i4 < codewordArr.length - 2) {
            int i9 = i4 + 2;
            codewordArr2[9] = codewordArr[i9];
            codewordArr2[12] = codewords[i9];
            codewordArr2[13] = codewords2[i9];
        }
        while (i5 < 14 && !adjustRowNumber(codeword, codewordArr2[i5])) {
            i5++;
        }
    }
}
