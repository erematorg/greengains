package com.google.zxing.maxicode.decoder;

import com.google.common.base.Ascii;
import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import io.nodle.cash.substrate.SubstrateHelper;
import java.text.DecimalFormat;
import java.util.List;

final class DecodedBitStreamParser {
    private static final byte[] COUNTRY_BYTES = {53, 54, 43, 44, 45, 46, 47, 48, SubstrateHelper.NODLE_SUBSTRATE_ID, 38};
    private static final char ECI = '￺';
    private static final char FS = '\u001c';
    private static final char GS = '\u001d';
    private static final char LATCHA = '￷';
    private static final char LATCHB = '￸';
    private static final char LOCK = '￹';
    private static final char NS = '￻';
    private static final char PAD = '￼';
    private static final byte[] POSTCODE_2_BYTES = {33, 34, 35, 36, Ascii.EM, Ascii.SUB, Ascii.ESC, Ascii.FS, Ascii.GS, 30, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.CR, Ascii.SO, Ascii.SI, 16, 17, Ascii.DC2, 7, 8, 9, 10, Ascii.VT, Ascii.FF, 1, 2};
    private static final byte[] POSTCODE_2_LENGTH_BYTES = {39, 40, 41, 42, 31, 32};
    private static final byte[][] POSTCODE_3_BYTES = {new byte[]{39, 40, 41, 42, 31, 32}, new byte[]{33, 34, 35, 36, Ascii.EM, Ascii.SUB}, new byte[]{Ascii.ESC, Ascii.FS, Ascii.GS, 30, 19, Ascii.DC4}, new byte[]{Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.CR, Ascii.SO}, new byte[]{Ascii.SI, 16, 17, Ascii.DC2, 7, 8}, new byte[]{9, 10, Ascii.VT, Ascii.FF, 1, 2}};
    private static final char RS = '\u001e';
    private static final byte[] SERVICE_CLASS_BYTES = {55, 56, 57, 58, 59, 60, 49, 50, 51, 52};
    private static final String[] SETS = {"\rABCDEFGHIJKLMNOPQRSTUVWXYZ￺\u001c\u001d\u001e￻ ￼\"#$%&'()*+,-./0123456789:￱￲￳￴￸", "`abcdefghijklmnopqrstuvwxyz￺\u001c\u001d\u001e￻{￼}~;<=>?[\\]^_ ,./:@!|￼￵￶￼￰￲￳￴￷", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ￺\u001c\u001d\u001e￻ÛÜÝÞßª¬±²³µ¹º¼½¾￷ ￹￳￴￸", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú￺\u001c\u001d\u001e￻ûüýþÿ¡¨«¯°´·¸»¿￷ ￲￹￴￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a￺￼￼\u001b￻\u001c\u001d\u001e\u001f ¢£¤¥¦§©­®¶￷ ￲￳￹￸"};
    private static final char SHIFTA = '￰';
    private static final char SHIFTB = '￱';
    private static final char SHIFTC = '￲';
    private static final char SHIFTD = '￳';
    private static final char SHIFTE = '￴';
    private static final char THREESHIFTA = '￶';
    private static final char TWOSHIFTA = '￵';

    private DecodedBitStreamParser() {
    }

    public static DecoderResult decode(byte[] bArr, int i3) throws FormatException {
        String str;
        StringBuilder sb = new StringBuilder(144);
        if (i3 == 2 || i3 == 3) {
            if (i3 == 2) {
                int postCode2 = getPostCode2(bArr);
                int postCode2Length = getPostCode2Length(bArr);
                if (postCode2Length <= 10) {
                    str = new DecimalFormat("0000000000".substring(0, postCode2Length)).format((long) postCode2);
                } else {
                    throw FormatException.getFormatInstance();
                }
            } else {
                str = getPostCode3(bArr);
            }
            DecimalFormat decimalFormat = new DecimalFormat("000");
            String format = decimalFormat.format((long) getCountry(bArr));
            String format2 = decimalFormat.format((long) getServiceClass(bArr));
            sb.append(getMessage(bArr, 10, 84));
            if (sb.toString().startsWith("[)>\u001e01\u001d")) {
                sb.insert(9, str + GS + format + GS + format2 + GS);
            } else {
                sb.insert(0, str + GS + format + GS + format2 + GS);
            }
        } else if (i3 == 4) {
            sb.append(getMessage(bArr, 1, 93));
        } else if (i3 == 5) {
            sb.append(getMessage(bArr, 1, 77));
        }
        return new DecoderResult(bArr, sb.toString(), (List<byte[]>) null, String.valueOf(i3));
    }

    private static int getBit(int i3, byte[] bArr) {
        int i4 = i3 - 1;
        return ((1 << (5 - (i4 % 6))) & bArr[i4 / 6]) == 0 ? 0 : 1;
    }

    private static int getCountry(byte[] bArr) {
        return getInt(bArr, COUNTRY_BYTES);
    }

    private static int getInt(byte[] bArr, byte[] bArr2) {
        int i3 = 0;
        for (int i4 = 0; i4 < bArr2.length; i4++) {
            i3 += getBit(bArr2[i4], bArr) << ((bArr2.length - i4) - 1);
        }
        return i3;
    }

    private static String getMessage(byte[] bArr, int i3, int i4) {
        int i5;
        StringBuilder sb = new StringBuilder();
        int i6 = i3;
        int i7 = -1;
        int i8 = 0;
        int i9 = 0;
        while (i6 < i3 + i4) {
            char charAt = SETS[i8].charAt(bArr[i6]);
            switch (charAt) {
                case 65520:
                case 65521:
                case 65522:
                case 65523:
                case 65524:
                    i9 = i8;
                    i8 = charAt - SHIFTA;
                    i5 = 1;
                    break;
                case 65525:
                    i5 = 2;
                    break;
                case 65526:
                    i5 = 3;
                    break;
                case 65527:
                    i5 = -1;
                    break;
                case 65528:
                    i5 = -1;
                    i8 = 1;
                    break;
                case 65529:
                    i5 = -1;
                    break;
                case 65531:
                    i6 += 5;
                    sb.append(new DecimalFormat("000000000").format((long) ((bArr[i6 + 1] << Ascii.CAN) + (bArr[i6 + 2] << Ascii.DC2) + (bArr[i6 + 3] << Ascii.FF) + (bArr[i6 + 4] << 6) + bArr[i6])));
                    break;
                default:
                    sb.append(charAt);
                    break;
            }
            i9 = i8;
            i8 = 0;
            int i10 = i5 - 1;
            if (i5 == 0) {
                i8 = i9;
            }
            i6++;
            i7 = i10;
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == 65532) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private static int getPostCode2(byte[] bArr) {
        return getInt(bArr, POSTCODE_2_BYTES);
    }

    private static int getPostCode2Length(byte[] bArr) {
        return getInt(bArr, POSTCODE_2_LENGTH_BYTES);
    }

    private static String getPostCode3(byte[] bArr) {
        byte[][] bArr2 = POSTCODE_3_BYTES;
        StringBuilder sb = new StringBuilder(bArr2.length);
        for (byte[] bArr3 : bArr2) {
            sb.append(SETS[0].charAt(getInt(bArr, bArr3)));
        }
        return sb.toString();
    }

    private static int getServiceClass(byte[] bArr) {
        return getInt(bArr, SERVICE_CLASS_BYTES);
    }
}
