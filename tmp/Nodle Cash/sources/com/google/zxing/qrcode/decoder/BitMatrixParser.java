package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

final class BitMatrixParser {
    private final BitMatrix bitMatrix;
    private boolean mirror;
    private FormatInformation parsedFormatInfo;
    private Version parsedVersion;

    public BitMatrixParser(BitMatrix bitMatrix2) throws FormatException {
        int height = bitMatrix2.getHeight();
        if (height < 21 || (height & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.bitMatrix = bitMatrix2;
    }

    private int copyBit(int i3, int i4, int i5) {
        boolean z2 = this.mirror;
        BitMatrix bitMatrix2 = this.bitMatrix;
        return z2 ? bitMatrix2.get(i4, i3) : bitMatrix2.get(i3, i4) ? (i5 << 1) | 1 : i5 << 1;
    }

    public void mirror() {
        int i3 = 0;
        while (i3 < this.bitMatrix.getWidth()) {
            int i4 = i3 + 1;
            for (int i5 = i4; i5 < this.bitMatrix.getHeight(); i5++) {
                if (this.bitMatrix.get(i3, i5) != this.bitMatrix.get(i5, i3)) {
                    this.bitMatrix.flip(i5, i3);
                    this.bitMatrix.flip(i3, i5);
                }
            }
            i3 = i4;
        }
    }

    public byte[] readCodewords() throws FormatException {
        FormatInformation readFormatInformation = readFormatInformation();
        Version readVersion = readVersion();
        DataMask dataMask = DataMask.values()[readFormatInformation.getDataMask()];
        int height = this.bitMatrix.getHeight();
        dataMask.unmaskBitMatrix(this.bitMatrix, height);
        BitMatrix buildFunctionPattern = readVersion.buildFunctionPattern();
        byte[] bArr = new byte[readVersion.getTotalCodewords()];
        int i3 = height - 1;
        boolean z2 = true;
        int i4 = i3;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i4 > 0) {
            if (i4 == 6) {
                i4--;
            }
            for (int i8 = 0; i8 < height; i8++) {
                int i9 = z2 ? i3 - i8 : i8;
                for (int i10 = 0; i10 < 2; i10++) {
                    int i11 = i4 - i10;
                    if (!buildFunctionPattern.get(i11, i9)) {
                        i6++;
                        i7 <<= 1;
                        if (this.bitMatrix.get(i11, i9)) {
                            i7 |= 1;
                        }
                        if (i6 == 8) {
                            bArr[i5] = (byte) i7;
                            i5++;
                            i6 = 0;
                            i7 = 0;
                        }
                    }
                }
            }
            z2 = !z2;
            i4 -= 2;
        }
        if (i5 == readVersion.getTotalCodewords()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    public FormatInformation readFormatInformation() throws FormatException {
        FormatInformation formatInformation = this.parsedFormatInfo;
        if (formatInformation != null) {
            return formatInformation;
        }
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < 6; i5++) {
            i4 = copyBit(i5, 8, i4);
        }
        int copyBit = copyBit(8, 7, copyBit(8, 8, copyBit(7, 8, i4)));
        for (int i6 = 5; i6 >= 0; i6--) {
            copyBit = copyBit(8, i6, copyBit);
        }
        int height = this.bitMatrix.getHeight();
        int i7 = height - 7;
        for (int i8 = height - 1; i8 >= i7; i8--) {
            i3 = copyBit(8, i8, i3);
        }
        for (int i9 = height - 8; i9 < height; i9++) {
            i3 = copyBit(i9, 8, i3);
        }
        FormatInformation decodeFormatInformation = FormatInformation.decodeFormatInformation(copyBit, i3);
        this.parsedFormatInfo = decodeFormatInformation;
        if (decodeFormatInformation != null) {
            return decodeFormatInformation;
        }
        throw FormatException.getFormatInstance();
    }

    public Version readVersion() throws FormatException {
        Version version = this.parsedVersion;
        if (version != null) {
            return version;
        }
        int height = this.bitMatrix.getHeight();
        int i3 = (height - 17) / 4;
        if (i3 <= 6) {
            return Version.getVersionForNumber(i3);
        }
        int i4 = height - 11;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 5; i7 >= 0; i7--) {
            for (int i8 = height - 9; i8 >= i4; i8--) {
                i6 = copyBit(i8, i7, i6);
            }
        }
        Version decodeVersionInformation = Version.decodeVersionInformation(i6);
        if (decodeVersionInformation == null || decodeVersionInformation.getDimensionForVersion() != height) {
            for (int i9 = 5; i9 >= 0; i9--) {
                for (int i10 = height - 9; i10 >= i4; i10--) {
                    i5 = copyBit(i9, i10, i5);
                }
            }
            Version decodeVersionInformation2 = Version.decodeVersionInformation(i5);
            if (decodeVersionInformation2 == null || decodeVersionInformation2.getDimensionForVersion() != height) {
                throw FormatException.getFormatInstance();
            }
            this.parsedVersion = decodeVersionInformation2;
            return decodeVersionInformation2;
        }
        this.parsedVersion = decodeVersionInformation;
        return decodeVersionInformation;
    }

    public void remask() {
        if (this.parsedFormatInfo != null) {
            DataMask.values()[this.parsedFormatInfo.getDataMask()].unmaskBitMatrix(this.bitMatrix, this.bitMatrix.getHeight());
        }
    }

    public void setMirror(boolean z2) {
        this.parsedVersion = null;
        this.parsedFormatInfo = null;
        this.mirror = z2;
    }
}
