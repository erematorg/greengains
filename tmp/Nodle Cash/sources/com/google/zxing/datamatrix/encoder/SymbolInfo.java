package com.google.zxing.datamatrix.encoder;

import A.a;
import com.google.zxing.Dimension;
import org.objectweb.asm.signature.SignatureVisitor;

public class SymbolInfo {
    static final SymbolInfo[] PROD_SYMBOLS;
    private static SymbolInfo[] symbols;
    private final int dataCapacity;
    private final int dataRegions;
    private final int errorCodewords;
    public final int matrixHeight;
    public final int matrixWidth;
    private final boolean rectangular;
    private final int rsBlockData;
    private final int rsBlockError;

    static {
        SymbolInfo[] symbolInfoArr = {new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, 174, 68, 22, 22, 4), new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, 368, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, 456, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, 576, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, 696, 272, 22, 22, 16, 174, 68), new SymbolInfo(false, 816, 336, 24, 24, 16, 136, 56), new SymbolInfo(false, 1050, 408, 18, 18, 36, 175, 68), new SymbolInfo(false, 1304, 496, 20, 20, 36, 163, 62), new DataMatrixSymbolInfo144()};
        PROD_SYMBOLS = symbolInfoArr;
        symbols = symbolInfoArr;
    }

    public SymbolInfo(boolean z2, int i3, int i4, int i5, int i6, int i7) {
        this(z2, i3, i4, i5, i6, i7, i3, i4);
    }

    private int getHorizontalDataRegions() {
        int i3 = this.dataRegions;
        int i4 = 1;
        if (i3 != 1) {
            i4 = 2;
            if (!(i3 == 2 || i3 == 4)) {
                if (i3 == 16) {
                    return 4;
                }
                if (i3 == 36) {
                    return 6;
                }
                throw new IllegalStateException("Cannot handle this number of data regions");
            }
        }
        return i4;
    }

    private int getVerticalDataRegions() {
        int i3 = this.dataRegions;
        if (i3 == 1 || i3 == 2) {
            return 1;
        }
        if (i3 == 4) {
            return 2;
        }
        if (i3 == 16) {
            return 4;
        }
        if (i3 == 36) {
            return 6;
        }
        throw new IllegalStateException("Cannot handle this number of data regions");
    }

    public static SymbolInfo lookup(int i3) {
        return lookup(i3, SymbolShapeHint.FORCE_NONE, true);
    }

    public static void overrideSymbolSet(SymbolInfo[] symbolInfoArr) {
        symbols = symbolInfoArr;
    }

    public int getCodewordCount() {
        return this.dataCapacity + this.errorCodewords;
    }

    public final int getDataCapacity() {
        return this.dataCapacity;
    }

    public int getDataLengthForInterleavedBlock(int i3) {
        return this.rsBlockData;
    }

    public final int getErrorCodewords() {
        return this.errorCodewords;
    }

    public final int getErrorLengthForInterleavedBlock(int i3) {
        return this.rsBlockError;
    }

    public int getInterleavedBlockCount() {
        return this.dataCapacity / this.rsBlockData;
    }

    public final int getSymbolDataHeight() {
        return getVerticalDataRegions() * this.matrixHeight;
    }

    public final int getSymbolDataWidth() {
        return getHorizontalDataRegions() * this.matrixWidth;
    }

    public final int getSymbolHeight() {
        return (getVerticalDataRegions() * 2) + getSymbolDataHeight();
    }

    public final int getSymbolWidth() {
        return (getHorizontalDataRegions() * 2) + getSymbolDataWidth();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.rectangular ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.matrixWidth);
        sb.append('x');
        sb.append(this.matrixHeight);
        sb.append(", symbol size ");
        sb.append(getSymbolWidth());
        sb.append('x');
        sb.append(getSymbolHeight());
        sb.append(", symbol data size ");
        sb.append(getSymbolDataWidth());
        sb.append('x');
        sb.append(getSymbolDataHeight());
        sb.append(", codewords ");
        sb.append(this.dataCapacity);
        sb.append(SignatureVisitor.EXTENDS);
        sb.append(this.errorCodewords);
        return sb.toString();
    }

    public SymbolInfo(boolean z2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.rectangular = z2;
        this.dataCapacity = i3;
        this.errorCodewords = i4;
        this.matrixWidth = i5;
        this.matrixHeight = i6;
        this.dataRegions = i7;
        this.rsBlockData = i8;
        this.rsBlockError = i9;
    }

    public static SymbolInfo lookup(int i3, SymbolShapeHint symbolShapeHint) {
        return lookup(i3, symbolShapeHint, true);
    }

    public static SymbolInfo lookup(int i3, boolean z2, boolean z3) {
        return lookup(i3, z2 ? SymbolShapeHint.FORCE_NONE : SymbolShapeHint.FORCE_SQUARE, z3);
    }

    private static SymbolInfo lookup(int i3, SymbolShapeHint symbolShapeHint, boolean z2) {
        return lookup(i3, symbolShapeHint, (Dimension) null, (Dimension) null, z2);
    }

    public static SymbolInfo lookup(int i3, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2, boolean z2) {
        for (SymbolInfo symbolInfo : symbols) {
            if ((symbolShapeHint != SymbolShapeHint.FORCE_SQUARE || !symbolInfo.rectangular) && ((symbolShapeHint != SymbolShapeHint.FORCE_RECTANGLE || symbolInfo.rectangular) && ((dimension == null || (symbolInfo.getSymbolWidth() >= dimension.getWidth() && symbolInfo.getSymbolHeight() >= dimension.getHeight())) && ((dimension2 == null || (symbolInfo.getSymbolWidth() <= dimension2.getWidth() && symbolInfo.getSymbolHeight() <= dimension2.getHeight())) && i3 <= symbolInfo.dataCapacity)))) {
                return symbolInfo;
            }
        }
        if (!z2) {
            return null;
        }
        throw new IllegalArgumentException(a.k("Can't find a symbol arrangement that matches the message. Data codewords: ", i3));
    }
}
