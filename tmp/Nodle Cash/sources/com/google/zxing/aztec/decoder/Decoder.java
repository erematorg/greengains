package com.google.zxing.aztec.decoder;

import androidx.constraintlayout.core.state.b;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import androidx.exifinterface.media.ExifInterface;
import com.appsamurai.storyly.util.ui.blur.c;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xml.serialize.LineSeparator;
import org.komputing.kbip44.BIP44Kt;
import org.slf4j.Marker;

public final class Decoder {
    private static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    private static final String[] DIGIT_TABLE = {"CTRL_PS", StringUtils.SPACE, SchemaSymbols.ATTVAL_FALSE_0, "1", "2", ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", ",", JwtUtilsKt.JWT_DELIMITER, "CTRL_UL", "CTRL_US"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", StringUtils.SPACE, "a", "b", c.f6372c, "d", "e", "f", "g", "h", "i", "j", "k", "l", BIP44Kt.BIP44_PREFIX, "n", "o", TtmlNode.TAG_P, "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", StringUtils.SPACE, "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", StickyVariantProvider.ENTRY_DELIMITER, "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"FLG(n)", "\r", LineSeparator.Windows, ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", Marker.ANY_MARKER, Marker.ANY_NON_NULL_MARKER, ",", "-", JwtUtilsKt.JWT_DELIMITER, "/", ":", ";", "<", StickyVariantProvider.KEY_VALUE_DELIMITER, ">", "?", "[", "]", "{", StringSubstitutor.DEFAULT_VAR_END, "CTRL_UL"};
    private static final String[] UPPER_TABLE = {"CTRL_PS", StringUtils.SPACE, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private AztecDetectorResult ddata;

    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.zxing.aztec.decoder.Decoder$Table[] r0 = com.google.zxing.aztec.decoder.Decoder.Table.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = r0
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.UPPER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.LOWER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.MIXED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.PUNCT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.DIGIT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class CorrectedBitsResult {
        /* access modifiers changed from: private */
        public final boolean[] correctBits;
        /* access modifiers changed from: private */
        public final int ecLevel;
        /* access modifiers changed from: private */
        public final int errorsCorrected;

        public CorrectedBitsResult(boolean[] zArr, int i3, int i4) {
            this.correctBits = zArr;
            this.errorsCorrected = i3;
            this.ecLevel = i4;
        }
    }

    public enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public static byte[] convertBoolArrayToByteArray(boolean[] zArr) {
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            bArr[i3] = readByte(zArr, i3 * 8);
        }
        return bArr;
    }

    private CorrectedBitsResult correctBits(boolean[] zArr) throws FormatException {
        int i3;
        GenericGF genericGF;
        boolean[] zArr2 = zArr;
        if (this.ddata.getNbLayers() <= 2) {
            genericGF = GenericGF.AZTEC_DATA_6;
            i3 = 6;
        } else {
            i3 = 8;
            if (this.ddata.getNbLayers() <= 8) {
                genericGF = GenericGF.AZTEC_DATA_8;
            } else if (this.ddata.getNbLayers() <= 22) {
                genericGF = GenericGF.AZTEC_DATA_10;
                i3 = 10;
            } else {
                genericGF = GenericGF.AZTEC_DATA_12;
                i3 = 12;
            }
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr2.length / i3;
        if (length >= nbDatablocks) {
            int length2 = zArr2.length % i3;
            int[] iArr = new int[length];
            int i4 = 0;
            while (i4 < length) {
                iArr[i4] = readCode(zArr2, length2, i3);
                i4++;
                length2 += i3;
            }
            try {
                ReedSolomonDecoder reedSolomonDecoder = new ReedSolomonDecoder(genericGF);
                int i5 = length - nbDatablocks;
                int decodeWithECCount = reedSolomonDecoder.decodeWithECCount(iArr, i5);
                int i6 = 1 << i3;
                int i7 = i6 - 1;
                int i8 = 0;
                for (int i9 = 0; i9 < nbDatablocks; i9++) {
                    int i10 = iArr[i9];
                    if (i10 == 0 || i10 == i7) {
                        throw FormatException.getFormatInstance();
                    }
                    if (i10 == 1 || i10 == i6 - 2) {
                        i8++;
                    }
                }
                boolean[] zArr3 = new boolean[((nbDatablocks * i3) - i8)];
                int i11 = 0;
                for (int i12 = 0; i12 < nbDatablocks; i12++) {
                    int i13 = iArr[i12];
                    if (i13 == 1 || i13 == i6 - 2) {
                        Arrays.fill(zArr3, i11, (i11 + i3) - 1, i13 > 1);
                        i11 = (i3 - 1) + i11;
                    } else {
                        int i14 = i3 - 1;
                        while (i14 >= 0) {
                            int i15 = i11 + 1;
                            zArr3[i11] = (i13 & (1 << i14)) != 0;
                            i14--;
                            i11 = i15;
                        }
                    }
                }
                return new CorrectedBitsResult(zArr3, decodeWithECCount, (i5 * 100) / length);
            } catch (ReedSolomonException e3) {
                throw FormatException.getFormatInstance(e3);
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private boolean[] extractBits(BitMatrix bitMatrix) {
        BitMatrix bitMatrix2 = bitMatrix;
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int i3 = (nbLayers * 4) + (isCompact ? 11 : 14);
        int[] iArr = new int[i3];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int i4 = 2;
        if (isCompact) {
            for (int i5 = 0; i5 < i3; i5++) {
                iArr[i5] = i5;
            }
        } else {
            int i6 = i3 / 2;
            int i7 = ((((i6 - 1) / 15) * 2) + (i3 + 1)) / 2;
            for (int i8 = 0; i8 < i6; i8++) {
                int i9 = (i8 / 15) + i8;
                iArr[(i6 - i8) - 1] = (i7 - i9) - 1;
                iArr[i6 + i8] = i9 + i7 + 1;
            }
        }
        int i10 = 0;
        int i11 = 0;
        while (i10 < nbLayers) {
            int i12 = ((nbLayers - i10) * 4) + (isCompact ? 9 : 12);
            int i13 = i10 * 2;
            int i14 = (i3 - 1) - i13;
            int i15 = 0;
            while (i15 < i12) {
                int i16 = i15 * 2;
                int i17 = 0;
                while (i17 < i4) {
                    int i18 = i13 + i17;
                    int i19 = i13 + i15;
                    zArr[i11 + i16 + i17] = bitMatrix2.get(iArr[i18], iArr[i19]);
                    int i20 = i14 - i17;
                    zArr[(i12 * 2) + i11 + i16 + i17] = bitMatrix2.get(iArr[i19], iArr[i20]);
                    int i21 = i14 - i15;
                    zArr[(i12 * 4) + i11 + i16 + i17] = bitMatrix2.get(iArr[i20], iArr[i21]);
                    zArr[(i12 * 6) + i11 + i16 + i17] = bitMatrix2.get(iArr[i21], iArr[i18]);
                    i17++;
                    isCompact = isCompact;
                    nbLayers = nbLayers;
                    i4 = 2;
                }
                int i22 = nbLayers;
                boolean z2 = isCompact;
                i15++;
                i4 = 2;
            }
            int i23 = nbLayers;
            boolean z3 = isCompact;
            i11 += i12 * 8;
            i10++;
            i4 = 2;
        }
        return zArr;
    }

    private static String getCharacter(Table table, int i3) {
        int i4 = AnonymousClass1.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()];
        if (i4 == 1) {
            return UPPER_TABLE[i3];
        }
        if (i4 == 2) {
            return LOWER_TABLE[i3];
        }
        if (i4 == 3) {
            return MIXED_TABLE[i3];
        }
        if (i4 == 4) {
            return PUNCT_TABLE[i3];
        }
        if (i4 == 5) {
            return DIGIT_TABLE[i3];
        }
        throw new IllegalStateException("Bad table");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b7, code lost:
        throw com.google.zxing.FormatException.getFormatInstance();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getEncodedData(boolean[] r14) throws com.google.zxing.FormatException {
        /*
            int r0 = r14.length
            com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.UPPER
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            int r3 = r14.length
            r4 = 5
            int r3 = r3 - r4
            r5 = 4
            int r3 = r3 / r5
            r2.<init>(r3)
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream
            r3.<init>()
            java.nio.charset.Charset r6 = DEFAULT_ENCODING
            r7 = 0
            r8 = r6
            r9 = r7
            r6 = r1
        L_0x0018:
            if (r9 >= r0) goto L_0x0106
            com.google.zxing.aztec.decoder.Decoder$Table r10 = com.google.zxing.aztec.decoder.Decoder.Table.BINARY
            r11 = 11
            if (r1 != r10) goto L_0x0057
            int r1 = r0 - r9
            if (r1 >= r4) goto L_0x0026
            goto L_0x0106
        L_0x0026:
            int r1 = readCode(r14, r9, r4)
            int r10 = r9 + 5
            if (r1 != 0) goto L_0x003c
            int r1 = r0 - r10
            if (r1 >= r11) goto L_0x0034
            goto L_0x0106
        L_0x0034:
            int r1 = readCode(r14, r10, r11)
            int r1 = r1 + 31
            int r10 = r9 + 16
        L_0x003c:
            r9 = r7
        L_0x003d:
            if (r9 >= r1) goto L_0x0054
            int r11 = r0 - r10
            r12 = 8
            if (r11 >= r12) goto L_0x0047
            r9 = r0
            goto L_0x0055
        L_0x0047:
            int r11 = readCode(r14, r10, r12)
            byte r11 = (byte) r11
            r3.write(r11)
            int r10 = r10 + 8
            int r9 = r9 + 1
            goto L_0x003d
        L_0x0054:
            r9 = r10
        L_0x0055:
            r1 = r6
            goto L_0x0018
        L_0x0057:
            com.google.zxing.aztec.decoder.Decoder$Table r10 = com.google.zxing.aztec.decoder.Decoder.Table.DIGIT
            if (r1 != r10) goto L_0x005d
            r10 = r5
            goto L_0x005e
        L_0x005d:
            r10 = r4
        L_0x005e:
            int r12 = r0 - r9
            if (r12 >= r10) goto L_0x0064
            goto L_0x0106
        L_0x0064:
            int r12 = readCode(r14, r9, r10)
            int r9 = r9 + r10
            java.lang.String r10 = getCharacter(r1, r12)
            java.lang.String r12 = "FLG(n)"
            boolean r12 = r12.equals(r10)
            if (r12 == 0) goto L_0x00da
            int r1 = r0 - r9
            r10 = 3
            if (r1 >= r10) goto L_0x007c
            goto L_0x0106
        L_0x007c:
            int r1 = readCode(r14, r9, r10)
            int r9 = r9 + 3
            java.lang.String r10 = r8.name()     // Catch:{ UnsupportedEncodingException -> 0x00d3 }
            java.lang.String r10 = r3.toString(r10)     // Catch:{ UnsupportedEncodingException -> 0x00d3 }
            r2.append(r10)     // Catch:{ UnsupportedEncodingException -> 0x00d3 }
            r3.reset()
            if (r1 == 0) goto L_0x00cd
            r10 = 7
            if (r1 == r10) goto L_0x00c8
            int r10 = r0 - r9
            int r12 = r1 * 4
            if (r10 >= r12) goto L_0x009c
            goto L_0x0055
        L_0x009c:
            r8 = r7
        L_0x009d:
            int r10 = r1 + -1
            if (r1 <= 0) goto L_0x00b8
            int r1 = readCode(r14, r9, r5)
            int r9 = r9 + 4
            r12 = 2
            if (r1 < r12) goto L_0x00b3
            if (r1 > r11) goto L_0x00b3
            int r8 = r8 * 10
            int r1 = r1 + -2
            int r8 = r8 + r1
            r1 = r10
            goto L_0x009d
        L_0x00b3:
            com.google.zxing.FormatException r14 = com.google.zxing.FormatException.getFormatInstance()
            throw r14
        L_0x00b8:
            com.google.zxing.common.CharacterSetECI r1 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r8)
            if (r1 == 0) goto L_0x00c3
            java.nio.charset.Charset r8 = r1.getCharset()
            goto L_0x0055
        L_0x00c3:
            com.google.zxing.FormatException r14 = com.google.zxing.FormatException.getFormatInstance()
            throw r14
        L_0x00c8:
            com.google.zxing.FormatException r14 = com.google.zxing.FormatException.getFormatInstance()
            throw r14
        L_0x00cd:
            r1 = 29
            r2.append(r1)
            goto L_0x0055
        L_0x00d3:
            r14 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r14)
            throw r0
        L_0x00da:
            java.lang.String r11 = "CTRL_"
            boolean r11 = r10.startsWith(r11)
            if (r11 == 0) goto L_0x00fa
            char r6 = r10.charAt(r4)
            com.google.zxing.aztec.decoder.Decoder$Table r6 = getTable(r6)
            r11 = 6
            char r10 = r10.charAt(r11)
            r11 = 76
            if (r10 != r11) goto L_0x00f5
            goto L_0x0055
        L_0x00f5:
            r13 = r6
            r6 = r1
            r1 = r13
            goto L_0x0018
        L_0x00fa:
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.US_ASCII
            byte[] r1 = r10.getBytes(r1)
            int r10 = r1.length
            r3.write(r1, r7, r10)
            goto L_0x0055
        L_0x0106:
            java.lang.String r14 = r8.name()     // Catch:{ UnsupportedEncodingException -> 0x0116 }
            java.lang.String r14 = r3.toString(r14)     // Catch:{ UnsupportedEncodingException -> 0x0116 }
            r2.append(r14)     // Catch:{ UnsupportedEncodingException -> 0x0116 }
            java.lang.String r14 = r2.toString()
            return r14
        L_0x0116:
            r14 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.getEncodedData(boolean[]):java.lang.String");
    }

    private static Table getTable(char c3) {
        return c3 != 'B' ? c3 != 'D' ? c3 != 'P' ? c3 != 'L' ? c3 != 'M' ? Table.UPPER : Table.MIXED : Table.LOWER : Table.PUNCT : Table.DIGIT : Table.BINARY;
    }

    public static String highLevelDecode(boolean[] zArr) throws FormatException {
        return getEncodedData(zArr);
    }

    private static byte readByte(boolean[] zArr, int i3) {
        int length = zArr.length - i3;
        return (byte) (length >= 8 ? readCode(zArr, i3, 8) : readCode(zArr, i3, length) << (8 - length));
    }

    private static int readCode(boolean[] zArr, int i3, int i4) {
        int i5 = 0;
        for (int i6 = i3; i6 < i3 + i4; i6++) {
            i5 <<= 1;
            if (zArr[i6]) {
                i5 |= 1;
            }
        }
        return i5;
    }

    private static int totalBitsInLayer(int i3, boolean z2) {
        return b.A(i3, 16, z2 ? 88 : 112, i3);
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        CorrectedBitsResult correctBits = correctBits(extractBits(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits.correctBits), getEncodedData(correctBits.correctBits), (List<byte[]>) null, String.format("%d%%", new Object[]{Integer.valueOf(correctBits.ecLevel)}));
        decoderResult.setNumBits(correctBits.correctBits.length);
        decoderResult.setErrorsCorrected(Integer.valueOf(correctBits.errorsCorrected));
        return decoderResult;
    }
}
