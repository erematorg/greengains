package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser {
    private final BitMatrix mappingBitMatrix;
    private final BitMatrix readMappingMatrix;
    private final Version version;

    public BitMatrixParser(BitMatrix bitMatrix) throws FormatException {
        int height = bitMatrix.getHeight();
        if (height < 8 || height > 144 || (height & 1) != 0) {
            throw FormatException.getFormatInstance();
        }
        this.version = readVersion(bitMatrix);
        BitMatrix extractDataRegion = extractDataRegion(bitMatrix);
        this.mappingBitMatrix = extractDataRegion;
        this.readMappingMatrix = new BitMatrix(extractDataRegion.getWidth(), extractDataRegion.getHeight());
    }

    private BitMatrix extractDataRegion(BitMatrix bitMatrix) {
        int symbolSizeRows = this.version.getSymbolSizeRows();
        int symbolSizeColumns = this.version.getSymbolSizeColumns();
        if (bitMatrix.getHeight() == symbolSizeRows) {
            int dataRegionSizeRows = this.version.getDataRegionSizeRows();
            int dataRegionSizeColumns = this.version.getDataRegionSizeColumns();
            int i3 = symbolSizeRows / dataRegionSizeRows;
            int i4 = symbolSizeColumns / dataRegionSizeColumns;
            BitMatrix bitMatrix2 = new BitMatrix(i4 * dataRegionSizeColumns, i3 * dataRegionSizeRows);
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = i5 * dataRegionSizeRows;
                for (int i7 = 0; i7 < i4; i7++) {
                    int i8 = i7 * dataRegionSizeColumns;
                    for (int i9 = 0; i9 < dataRegionSizeRows; i9++) {
                        int i10 = ((dataRegionSizeRows + 2) * i5) + 1 + i9;
                        int i11 = i6 + i9;
                        for (int i12 = 0; i12 < dataRegionSizeColumns; i12++) {
                            if (bitMatrix.get(((dataRegionSizeColumns + 2) * i7) + 1 + i12, i10)) {
                                bitMatrix2.set(i8 + i12, i11);
                            }
                        }
                    }
                }
            }
            return bitMatrix2;
        }
        throw new IllegalArgumentException("Dimension of bitMatrix must match the version size");
    }

    private int readCorner1(int i3, int i4) {
        int i5 = i3 - 1;
        int i6 = (readModule(i5, 0, i3, i4) ? 1 : 0) << true;
        if (readModule(i5, 1, i3, i4)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (readModule(i5, 2, i3, i4)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        if (readModule(0, i4 - 2, i3, i4)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        int i10 = i4 - 1;
        if (readModule(0, i10, i3, i4)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        if (readModule(1, i10, i3, i4)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        if (readModule(2, i10, i3, i4)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        return readModule(3, i10, i3, i4) ? i13 | 1 : i13;
    }

    private int readCorner2(int i3, int i4) {
        int i5 = (readModule(i3 + -3, 0, i3, i4) ? 1 : 0) << true;
        if (readModule(i3 - 2, 0, i3, i4)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (readModule(i3 - 1, 0, i3, i4)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (readModule(0, i4 - 4, i3, i4)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        if (readModule(0, i4 - 3, i3, i4)) {
            i8 |= 1;
        }
        int i9 = i8 << 1;
        if (readModule(0, i4 - 2, i3, i4)) {
            i9 |= 1;
        }
        int i10 = i9 << 1;
        int i11 = i4 - 1;
        if (readModule(0, i11, i3, i4)) {
            i10 |= 1;
        }
        int i12 = i10 << 1;
        return readModule(1, i11, i3, i4) ? i12 | 1 : i12;
    }

    private int readCorner3(int i3, int i4) {
        int i5 = i3 - 1;
        int i6 = (readModule(i5, 0, i3, i4) ? 1 : 0) << true;
        int i7 = i4 - 1;
        if (readModule(i5, i7, i3, i4)) {
            i6 |= 1;
        }
        int i8 = i6 << 1;
        int i9 = i4 - 3;
        if (readModule(0, i9, i3, i4)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        int i11 = i4 - 2;
        if (readModule(0, i11, i3, i4)) {
            i10 |= 1;
        }
        int i12 = i10 << 1;
        if (readModule(0, i7, i3, i4)) {
            i12 |= 1;
        }
        int i13 = i12 << 1;
        if (readModule(1, i9, i3, i4)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (readModule(1, i11, i3, i4)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        return readModule(1, i7, i3, i4) ? i15 | 1 : i15;
    }

    private int readCorner4(int i3, int i4) {
        int i5 = (readModule(i3 + -3, 0, i3, i4) ? 1 : 0) << true;
        if (readModule(i3 - 2, 0, i3, i4)) {
            i5 |= 1;
        }
        int i6 = i5 << 1;
        if (readModule(i3 - 1, 0, i3, i4)) {
            i6 |= 1;
        }
        int i7 = i6 << 1;
        if (readModule(0, i4 - 2, i3, i4)) {
            i7 |= 1;
        }
        int i8 = i7 << 1;
        int i9 = i4 - 1;
        if (readModule(0, i9, i3, i4)) {
            i8 |= 1;
        }
        int i10 = i8 << 1;
        if (readModule(1, i9, i3, i4)) {
            i10 |= 1;
        }
        int i11 = i10 << 1;
        if (readModule(2, i9, i3, i4)) {
            i11 |= 1;
        }
        int i12 = i11 << 1;
        return readModule(3, i9, i3, i4) ? i12 | 1 : i12;
    }

    private boolean readModule(int i3, int i4, int i5, int i6) {
        if (i3 < 0) {
            i3 += i5;
            i4 += 4 - ((i5 + 4) & 7);
        }
        if (i4 < 0) {
            i4 += i6;
            i3 += 4 - ((i6 + 4) & 7);
        }
        if (i3 >= i5) {
            i3 -= i5;
        }
        this.readMappingMatrix.set(i4, i3);
        return this.mappingBitMatrix.get(i4, i3);
    }

    private int readUtah(int i3, int i4, int i5, int i6) {
        int i7 = i3 - 2;
        int i8 = i4 - 2;
        int i9 = (readModule(i7, i8, i5, i6) ? 1 : 0) << true;
        int i10 = i4 - 1;
        if (readModule(i7, i10, i5, i6)) {
            i9 |= 1;
        }
        int i11 = i9 << 1;
        int i12 = i3 - 1;
        if (readModule(i12, i8, i5, i6)) {
            i11 |= 1;
        }
        int i13 = i11 << 1;
        if (readModule(i12, i10, i5, i6)) {
            i13 |= 1;
        }
        int i14 = i13 << 1;
        if (readModule(i12, i4, i5, i6)) {
            i14 |= 1;
        }
        int i15 = i14 << 1;
        if (readModule(i3, i8, i5, i6)) {
            i15 |= 1;
        }
        int i16 = i15 << 1;
        if (readModule(i3, i10, i5, i6)) {
            i16 |= 1;
        }
        int i17 = i16 << 1;
        return readModule(i3, i4, i5, i6) ? i17 | 1 : i17;
    }

    private static Version readVersion(BitMatrix bitMatrix) throws FormatException {
        return Version.getVersionForDimensions(bitMatrix.getHeight(), bitMatrix.getWidth());
    }

    public Version getVersion() {
        return this.version;
    }

    public byte[] readCodewords() throws FormatException {
        byte[] bArr = new byte[this.version.getTotalCodewords()];
        int height = this.mappingBitMatrix.getHeight();
        int width = this.mappingBitMatrix.getWidth();
        int i3 = 0;
        boolean z2 = false;
        int i4 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        int i5 = 4;
        while (true) {
            if (i5 == height && i3 == 0 && !z2) {
                bArr[i4] = (byte) readCorner1(height, width);
                i5 -= 2;
                i3 += 2;
                i4++;
                z2 = true;
            } else {
                int i6 = height - 2;
                if (i5 == i6 && i3 == 0 && (width & 3) != 0 && !z3) {
                    bArr[i4] = (byte) readCorner2(height, width);
                    i5 -= 2;
                    i3 += 2;
                    i4++;
                    z3 = true;
                } else if (i5 == height + 4 && i3 == 2 && (width & 7) == 0 && !z4) {
                    bArr[i4] = (byte) readCorner3(height, width);
                    i5 -= 2;
                    i3 += 2;
                    i4++;
                    z4 = true;
                } else if (i5 == i6 && i3 == 0 && (width & 7) == 4 && !z5) {
                    bArr[i4] = (byte) readCorner4(height, width);
                    i5 -= 2;
                    i3 += 2;
                    i4++;
                    z5 = true;
                } else {
                    while (true) {
                        if (i5 < height && i3 >= 0 && !this.readMappingMatrix.get(i3, i5)) {
                            bArr[i4] = (byte) readUtah(i5, i3, height, width);
                            i4++;
                        }
                        int i7 = i5 - 2;
                        int i8 = i3 + 2;
                        if (i7 < 0 || i8 >= width) {
                            int i9 = i5 - 1;
                            int i10 = i3 + 5;
                        } else {
                            i5 = i7;
                            i3 = i8;
                        }
                    }
                    int i92 = i5 - 1;
                    int i102 = i3 + 5;
                    while (true) {
                        if (i92 >= 0 && i102 < width && !this.readMappingMatrix.get(i102, i92)) {
                            bArr[i4] = (byte) readUtah(i92, i102, height, width);
                            i4++;
                        }
                        int i11 = i92 + 2;
                        int i12 = i102 - 2;
                        if (i11 >= height || i12 < 0) {
                            i5 = i92 + 5;
                            i3 = i102 - 1;
                        } else {
                            i92 = i11;
                            i102 = i12;
                        }
                    }
                    i5 = i92 + 5;
                    i3 = i102 - 1;
                }
            }
            if (i5 >= height && i3 >= width) {
                break;
            }
        }
        if (i4 == this.version.getTotalCodewords()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }
}
