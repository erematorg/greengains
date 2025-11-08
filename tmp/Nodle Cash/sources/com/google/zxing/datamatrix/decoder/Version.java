package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;

public final class Version {
    private static final Version[] VERSIONS = buildVersions();
    private final int dataRegionSizeColumns;
    private final int dataRegionSizeRows;
    private final ECBlocks ecBlocks;
    private final int symbolSizeColumns;
    private final int symbolSizeRows;
    private final int totalCodewords;
    private final int versionNumber;

    public static final class ECB {
        private final int count;
        private final int dataCodewords;

        public int getCount() {
            return this.count;
        }

        public int getDataCodewords() {
            return this.dataCodewords;
        }

        private ECB(int i3, int i4) {
            this.count = i3;
            this.dataCodewords = i4;
        }
    }

    private Version(int i3, int i4, int i5, int i6, int i7, ECBlocks eCBlocks) {
        this.versionNumber = i3;
        this.symbolSizeRows = i4;
        this.symbolSizeColumns = i5;
        this.dataRegionSizeRows = i6;
        this.dataRegionSizeColumns = i7;
        this.ecBlocks = eCBlocks;
        int eCCodewords = eCBlocks.getECCodewords();
        int i8 = 0;
        for (ECB ecb : eCBlocks.getECBlocks()) {
            i8 += (ecb.getDataCodewords() + eCCodewords) * ecb.getCount();
        }
        this.totalCodewords = i8;
    }

    private static Version[] buildVersions() {
        Version version = r0;
        Version version2 = new Version(1, 10, 10, 8, 8, new ECBlocks(5, new ECB(1, 3)));
        Version version3 = r16;
        Version version4 = new Version(2, 12, 12, 10, 10, new ECBlocks(7, new ECB(1, 5)));
        Version version5 = r22;
        Version version6 = new Version(3, 14, 14, 12, 12, new ECBlocks(10, new ECB(1, 8)));
        Version version7 = r28;
        Version version8 = new Version(4, 16, 16, 14, 14, new ECBlocks(12, new ECB(1, 12)));
        Version version9 = r16;
        Version version10 = new Version(5, 18, 18, 16, 16, new ECBlocks(14, new ECB(1, 18)));
        Version version11 = r22;
        Version version12 = new Version(6, 20, 20, 18, 18, new ECBlocks(18, new ECB(1, 22)));
        Version version13 = r35;
        Version version14 = new Version(7, 22, 22, 20, 20, new ECBlocks(20, new ECB(1, 30)));
        Version version15 = r16;
        Version version16 = new Version(8, 24, 24, 22, 22, new ECBlocks(24, new ECB(1, 36)));
        Version version17 = r22;
        Version version18 = new Version(9, 26, 26, 24, 24, new ECBlocks(28, new ECB(1, 44)));
        Version version19 = r48;
        Version version20 = new Version(10, 32, 32, 14, 14, new ECBlocks(36, new ECB(1, 62)));
        Version version21 = r18;
        Version version22 = new Version(11, 36, 36, 16, 16, new ECBlocks(42, new ECB(1, 86)));
        Version version23 = r24;
        Version version24 = new Version(12, 40, 40, 18, 18, new ECBlocks(48, new ECB(1, 114)));
        Version version25 = r48;
        Version version26 = new Version(13, 44, 44, 20, 20, new ECBlocks(56, new ECB(1, 144)));
        Version version27 = r21;
        Version version28 = new Version(14, 48, 48, 22, 22, new ECBlocks(68, new ECB(1, 174)));
        Version version29 = r56;
        Version version30 = new Version(15, 52, 52, 24, 24, new ECBlocks(42, new ECB(2, 102)));
        Version version31 = r63;
        Version version32 = new Version(16, 64, 64, 14, 14, new ECBlocks(56, new ECB(2, 140)));
        Version version33 = r56;
        Version version34 = new Version(17, 72, 72, 16, 16, new ECBlocks(36, new ECB(4, 92)));
        Version version35 = r63;
        Version version36 = new Version(18, 80, 80, 18, 18, new ECBlocks(48, new ECB(4, 114)));
        Version version37 = r56;
        Version version38 = new Version(19, 88, 88, 20, 20, new ECBlocks(56, new ECB(4, 144)));
        Version version39 = r63;
        Version version40 = new Version(20, 96, 96, 22, 22, new ECBlocks(68, new ECB(4, 174)));
        Version version41 = r56;
        Version version42 = new Version(21, 104, 104, 24, 24, new ECBlocks(56, new ECB(6, 136)));
        Version version43 = r63;
        Version version44 = new Version(22, 120, 120, 18, 18, new ECBlocks(68, new ECB(6, 175)));
        Version version45 = r56;
        Version version46 = new Version(23, 132, 132, 20, 20, new ECBlocks(62, new ECB(8, 163)));
        Version version47 = r63;
        Version version48 = new Version(24, 144, 144, 22, 22, new ECBlocks(62, new ECB(8, 156), new ECB(2, 155)));
        Version version49 = r56;
        Version version50 = new Version(25, 8, 18, 6, 16, new ECBlocks(7, new ECB(1, 5)));
        Version version51 = r63;
        Version version52 = new Version(26, 8, 32, 6, 14, new ECBlocks(11, new ECB(1, 10)));
        Version version53 = r56;
        Version version54 = new Version(27, 12, 26, 10, 24, new ECBlocks(14, new ECB(1, 16)));
        Version version55 = r63;
        Version version56 = new Version(28, 12, 36, 10, 16, new ECBlocks(18, new ECB(1, 22)));
        Version version57 = r56;
        Version version58 = new Version(29, 16, 36, 14, 16, new ECBlocks(24, new ECB(1, 32)));
        Version version59 = r63;
        Version version60 = new Version(30, 16, 48, 14, 22, new ECBlocks(28, new ECB(1, 49)));
        Version version61 = r56;
        Version version62 = new Version(31, 8, 48, 6, 22, new ECBlocks(15, new ECB(1, 18)));
        Version version63 = r63;
        Version version64 = new Version(32, 8, 64, 6, 14, new ECBlocks(18, new ECB(1, 24)));
        Version version65 = r56;
        Version version66 = new Version(33, 8, 80, 6, 18, new ECBlocks(22, new ECB(1, 32)));
        Version version67 = r63;
        Version version68 = new Version(34, 8, 96, 6, 22, new ECBlocks(28, new ECB(1, 38)));
        Version version69 = r56;
        Version version70 = new Version(35, 8, 120, 6, 18, new ECBlocks(32, new ECB(1, 49)));
        Version version71 = r63;
        Version version72 = new Version(36, 8, 144, 6, 22, new ECBlocks(36, new ECB(1, 63)));
        Version version73 = r56;
        Version version74 = new Version(37, 12, 64, 10, 14, new ECBlocks(27, new ECB(1, 43)));
        Version version75 = r63;
        Version version76 = new Version(38, 12, 88, 10, 20, new ECBlocks(36, new ECB(1, 64)));
        Version version77 = r56;
        Version version78 = new Version(39, 16, 64, 14, 14, new ECBlocks(36, new ECB(1, 62)));
        Version version79 = r63;
        Version version80 = new Version(40, 20, 36, 18, 16, new ECBlocks(28, new ECB(1, 44)));
        Version version81 = r55;
        Version version82 = new Version(41, 20, 44, 18, 20, new ECBlocks(34, new ECB(1, 56)));
        Version version83 = r63;
        Version version84 = new Version(42, 20, 64, 18, 14, new ECBlocks(42, new ECB(1, 84)));
        Version version85 = r55;
        Version version86 = new Version(43, 22, 48, 20, 22, new ECBlocks(38, new ECB(1, 72)));
        Version version87 = r63;
        Version version88 = new Version(44, 24, 48, 22, 22, new ECBlocks(41, new ECB(1, 80)));
        Version version89 = r55;
        Version version90 = new Version(45, 24, 64, 22, 14, new ECBlocks(46, new ECB(1, 108)));
        Version version91 = r63;
        Version version92 = new Version(46, 26, 40, 24, 18, new ECBlocks(38, new ECB(1, 70)));
        Version version93 = r55;
        Version version94 = new Version(47, 26, 48, 24, 22, new ECBlocks(42, new ECB(1, 90)));
        Version version95 = r63;
        Version version96 = new Version(48, 26, 64, 24, 14, new ECBlocks(50, new ECB(1, 118)));
        return new Version[]{version, version3, version5, version7, version9, version11, version13, version15, version17, version19, version21, version23, version25, version27, version29, version31, version33, version35, version37, version39, version41, version43, version45, version47, version49, version51, version53, version55, version57, version59, version61, version63, version65, version67, version69, version71, version73, version75, version77, version79, version81, version83, version85, version87, version89, version91, version93, version95};
    }

    public static Version getVersionForDimensions(int i3, int i4) throws FormatException {
        if ((i3 & 1) == 0 && (i4 & 1) == 0) {
            for (Version version : VERSIONS) {
                if (version.symbolSizeRows == i3 && version.symbolSizeColumns == i4) {
                    return version;
                }
            }
            throw FormatException.getFormatInstance();
        }
        throw FormatException.getFormatInstance();
    }

    public int getDataRegionSizeColumns() {
        return this.dataRegionSizeColumns;
    }

    public int getDataRegionSizeRows() {
        return this.dataRegionSizeRows;
    }

    public ECBlocks getECBlocks() {
        return this.ecBlocks;
    }

    public int getSymbolSizeColumns() {
        return this.symbolSizeColumns;
    }

    public int getSymbolSizeRows() {
        return this.symbolSizeRows;
    }

    public int getTotalCodewords() {
        return this.totalCodewords;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public String toString() {
        return String.valueOf(this.versionNumber);
    }

    public static final class ECBlocks {
        private final ECB[] ecBlocks;
        private final int ecCodewords;

        public ECB[] getECBlocks() {
            return this.ecBlocks;
        }

        public int getECCodewords() {
            return this.ecCodewords;
        }

        private ECBlocks(int i3, ECB ecb) {
            this.ecCodewords = i3;
            this.ecBlocks = new ECB[]{ecb};
        }

        private ECBlocks(int i3, ECB ecb, ECB ecb2) {
            this.ecCodewords = i3;
            this.ecBlocks = new ECB[]{ecb, ecb2};
        }
    }
}
