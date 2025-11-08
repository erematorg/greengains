package com.fasterxml.jackson.core.io;

import androidx.collection.SieveCacheKt;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.io.schubfach.DoubleToDecimal;
import com.fasterxml.jackson.core.io.schubfach.FloatToDecimal;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.objectweb.asm.signature.SignatureVisitor;

public final class NumberOutput {
    private static int BILLION = 1000000000;
    private static long BILLION_L = 1000000000;
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    static final String SMALLEST_INT = String.valueOf(Integer.MIN_VALUE);
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static final int[] TRIPLET_TO_CHARS = new int[1000];
    private static final String[] sSmallIntStrs = {SchemaSymbols.ATTVAL_FALSE_0, "1", "2", ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] sSmallIntStrs2 = {"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};

    static {
        int i3 = 0;
        for (int i4 = 0; i4 < 10; i4++) {
            for (int i5 = 0; i5 < 10; i5++) {
                int i6 = 0;
                while (i6 < 10) {
                    TRIPLET_TO_CHARS[i3] = ((i4 + 48) << 16) | ((i5 + 48) << 8) | (i6 + 48);
                    i6++;
                    i3++;
                }
            }
        }
    }

    private static int _full3(int i3, char[] cArr, int i4) {
        int i5 = TRIPLET_TO_CHARS[i3];
        cArr[i4] = (char) (i5 >> 16);
        int i6 = i4 + 2;
        cArr[i4 + 1] = (char) ((i5 >> 8) & 127);
        int i7 = i4 + 3;
        cArr[i6] = (char) (i5 & 127);
        return i7;
    }

    private static int _leading3(int i3, char[] cArr, int i4) {
        int i5 = TRIPLET_TO_CHARS[i3];
        if (i3 > 9) {
            if (i3 > 99) {
                cArr[i4] = (char) (i5 >> 16);
                i4++;
            }
            cArr[i4] = (char) ((i5 >> 8) & 127);
            i4++;
        }
        int i6 = i4 + 1;
        cArr[i4] = (char) (i5 & 127);
        return i6;
    }

    private static int _outputFullBillion(int i3, char[] cArr, int i4) {
        int i5 = i3 / 1000;
        int i6 = i3 - (i5 * 1000);
        int i7 = i5 / 1000;
        int[] iArr = TRIPLET_TO_CHARS;
        int i8 = iArr[i7];
        cArr[i4] = (char) (i8 >> 16);
        cArr[i4 + 1] = (char) ((i8 >> 8) & 127);
        cArr[i4 + 2] = (char) (i8 & 127);
        int i9 = iArr[i5 - (i7 * 1000)];
        cArr[i4 + 3] = (char) (i9 >> 16);
        cArr[i4 + 4] = (char) ((i9 >> 8) & 127);
        cArr[i4 + 5] = (char) (i9 & 127);
        int i10 = iArr[i6];
        cArr[i4 + 6] = (char) (i10 >> 16);
        int i11 = i4 + 8;
        cArr[i4 + 7] = (char) ((i10 >> 8) & 127);
        int i12 = i4 + 9;
        cArr[i11] = (char) (i10 & 127);
        return i12;
    }

    private static int _outputSmallestI(char[] cArr, int i3) {
        String str = SMALLEST_INT;
        int length = str.length();
        str.getChars(0, length, cArr, i3);
        return length + i3;
    }

    private static int _outputSmallestL(char[] cArr, int i3) {
        String str = SMALLEST_LONG;
        int length = str.length();
        str.getChars(0, length, cArr, i3);
        return length + i3;
    }

    private static int _outputUptoBillion(int i3, char[] cArr, int i4) {
        if (i3 >= MILLION) {
            int i5 = i3 / 1000;
            int i6 = i3 - (i5 * 1000);
            int i7 = i5 / 1000;
            int _leading3 = _leading3(i7, cArr, i4);
            int[] iArr = TRIPLET_TO_CHARS;
            int i8 = iArr[i5 - (i7 * 1000)];
            cArr[_leading3] = (char) (i8 >> 16);
            cArr[_leading3 + 1] = (char) ((i8 >> 8) & 127);
            cArr[_leading3 + 2] = (char) (i8 & 127);
            int i9 = iArr[i6];
            cArr[_leading3 + 3] = (char) (i9 >> 16);
            int i10 = _leading3 + 5;
            cArr[_leading3 + 4] = (char) ((i9 >> 8) & 127);
            int i11 = _leading3 + 6;
            cArr[i10] = (char) (i9 & 127);
            return i11;
        } else if (i3 < 1000) {
            return _leading3(i3, cArr, i4);
        } else {
            int i12 = i3 / 1000;
            return _outputUptoMillion(cArr, i4, i12, i3 - (i12 * 1000));
        }
    }

    private static int _outputUptoMillion(char[] cArr, int i3, int i4, int i5) {
        int[] iArr = TRIPLET_TO_CHARS;
        int i6 = iArr[i4];
        if (i4 > 9) {
            if (i4 > 99) {
                cArr[i3] = (char) (i6 >> 16);
                i3++;
            }
            cArr[i3] = (char) ((i6 >> 8) & 127);
            i3++;
        }
        cArr[i3] = (char) (i6 & 127);
        int i7 = iArr[i5];
        cArr[i3 + 1] = (char) (i7 >> 16);
        int i8 = i3 + 3;
        cArr[i3 + 2] = (char) ((i7 >> 8) & 127);
        int i9 = i3 + 4;
        cArr[i8] = (char) (i7 & 127);
        return i9;
    }

    public static boolean notFinite(double d2) {
        return Double.isNaN(d2) || Double.isInfinite(d2);
    }

    public static int outputInt(int i3, char[] cArr, int i4) {
        int i5;
        if (i3 < 0) {
            if (i3 == Integer.MIN_VALUE) {
                return _outputSmallestI(cArr, i4);
            }
            cArr[i4] = SignatureVisitor.SUPER;
            i3 = -i3;
            i4++;
        }
        if (i3 >= MILLION) {
            int i6 = BILLION;
            if (i3 >= i6) {
                int i7 = i3 - i6;
                if (i7 >= i6) {
                    i7 -= i6;
                    i5 = i4 + 1;
                    cArr[i4] = '2';
                } else {
                    i5 = i4 + 1;
                    cArr[i4] = '1';
                }
                return _outputFullBillion(i7, cArr, i5);
            }
            int i8 = i3 / 1000;
            int i9 = i8 / 1000;
            return _full3(i3 - (i8 * 1000), cArr, _full3(i8 - (i9 * 1000), cArr, _leading3(i9, cArr, i4)));
        } else if (i3 >= 1000) {
            int i10 = i3 / 1000;
            return _full3(i3 - (i10 * 1000), cArr, _leading3(i10, cArr, i4));
        } else if (i3 >= 10) {
            return _leading3(i3, cArr, i4);
        } else {
            cArr[i4] = (char) (i3 + 48);
            return i4 + 1;
        }
    }

    public static int outputLong(long j2, char[] cArr, int i3) {
        int i4;
        if (j2 < 0) {
            if (j2 > MIN_INT_AS_LONG) {
                return outputInt((int) j2, cArr, i3);
            }
            if (j2 == Long.MIN_VALUE) {
                return _outputSmallestL(cArr, i3);
            }
            cArr[i3] = SignatureVisitor.SUPER;
            j2 = -j2;
            i3++;
        } else if (j2 <= MAX_INT_AS_LONG) {
            return outputInt((int) j2, cArr, i3);
        }
        long j3 = BILLION_L;
        long j4 = j2 / j3;
        long j5 = j2 - (j4 * j3);
        if (j4 < j3) {
            i4 = _outputUptoBillion((int) j4, cArr, i3);
        } else {
            long j6 = j4 / j3;
            int _leading3 = _leading3((int) j6, cArr, i3);
            i4 = _outputFullBillion((int) (j4 - (j3 * j6)), cArr, _leading3);
        }
        return _outputFullBillion((int) j5, cArr, i4);
    }

    public static String toString(int i3) {
        String[] strArr = sSmallIntStrs;
        if (i3 < strArr.length) {
            if (i3 >= 0) {
                return strArr[i3];
            }
            int i4 = (-i3) - 1;
            String[] strArr2 = sSmallIntStrs2;
            if (i4 < strArr2.length) {
                return strArr2[i4];
            }
        }
        return Integer.toString(i3);
    }

    public static boolean notFinite(float f2) {
        return Float.isNaN(f2) || Float.isInfinite(f2);
    }

    private static int _outputSmallestI(byte[] bArr, int i3) {
        int length = SMALLEST_INT.length();
        int i4 = 0;
        while (i4 < length) {
            bArr[i3] = (byte) SMALLEST_INT.charAt(i4);
            i4++;
            i3++;
        }
        return i3;
    }

    private static int _outputSmallestL(byte[] bArr, int i3) {
        int length = SMALLEST_LONG.length();
        int i4 = 0;
        while (i4 < length) {
            bArr[i3] = (byte) SMALLEST_LONG.charAt(i4);
            i4++;
            i3++;
        }
        return i3;
    }

    private static int _full3(int i3, byte[] bArr, int i4) {
        int i5 = TRIPLET_TO_CHARS[i3];
        bArr[i4] = (byte) (i5 >> 16);
        int i6 = i4 + 2;
        bArr[i4 + 1] = (byte) (i5 >> 8);
        int i7 = i4 + 3;
        bArr[i6] = (byte) i5;
        return i7;
    }

    private static int _leading3(int i3, byte[] bArr, int i4) {
        int i5 = TRIPLET_TO_CHARS[i3];
        if (i3 > 9) {
            if (i3 > 99) {
                bArr[i4] = (byte) (i5 >> 16);
                i4++;
            }
            bArr[i4] = (byte) (i5 >> 8);
            i4++;
        }
        int i6 = i4 + 1;
        bArr[i4] = (byte) i5;
        return i6;
    }

    public static String toString(long j2) {
        if (j2 > SieveCacheKt.NodeLinkMask || j2 < SieveCacheKt.NodeMetaAndPreviousMask) {
            return Long.toString(j2);
        }
        return toString((int) j2);
    }

    public static String toString(double d2) {
        return toString(d2, false);
    }

    private static int _outputUptoMillion(byte[] bArr, int i3, int i4, int i5) {
        int[] iArr = TRIPLET_TO_CHARS;
        int i6 = iArr[i4];
        if (i4 > 9) {
            if (i4 > 99) {
                bArr[i3] = (byte) (i6 >> 16);
                i3++;
            }
            bArr[i3] = (byte) (i6 >> 8);
            i3++;
        }
        bArr[i3] = (byte) i6;
        int i7 = iArr[i5];
        bArr[i3 + 1] = (byte) (i7 >> 16);
        int i8 = i3 + 3;
        bArr[i3 + 2] = (byte) (i7 >> 8);
        int i9 = i3 + 4;
        bArr[i8] = (byte) i7;
        return i9;
    }

    public static String toString(double d2, boolean z2) {
        return z2 ? DoubleToDecimal.toString(d2) : Double.toString(d2);
    }

    public static String toString(float f2) {
        return toString(f2, false);
    }

    public static String toString(float f2, boolean z2) {
        return z2 ? FloatToDecimal.toString(f2) : Float.toString(f2);
    }

    public static int outputLong(long j2, byte[] bArr, int i3) {
        int i4;
        if (j2 < 0) {
            if (j2 > MIN_INT_AS_LONG) {
                return outputInt((int) j2, bArr, i3);
            }
            if (j2 == Long.MIN_VALUE) {
                return _outputSmallestL(bArr, i3);
            }
            bArr[i3] = 45;
            j2 = -j2;
            i3++;
        } else if (j2 <= MAX_INT_AS_LONG) {
            return outputInt((int) j2, bArr, i3);
        }
        long j3 = BILLION_L;
        long j4 = j2 / j3;
        long j5 = j2 - (j4 * j3);
        if (j4 < j3) {
            i4 = _outputUptoBillion((int) j4, bArr, i3);
        } else {
            long j6 = j4 / j3;
            int _leading3 = _leading3((int) j6, bArr, i3);
            i4 = _outputFullBillion((int) (j4 - (j3 * j6)), bArr, _leading3);
        }
        return _outputFullBillion((int) j5, bArr, i4);
    }

    private static int _outputFullBillion(int i3, byte[] bArr, int i4) {
        int i5 = i3 / 1000;
        int i6 = i3 - (i5 * 1000);
        int i7 = i5 / 1000;
        int i8 = i5 - (i7 * 1000);
        int[] iArr = TRIPLET_TO_CHARS;
        int i9 = iArr[i7];
        bArr[i4] = (byte) (i9 >> 16);
        bArr[i4 + 1] = (byte) (i9 >> 8);
        bArr[i4 + 2] = (byte) i9;
        int i10 = iArr[i8];
        bArr[i4 + 3] = (byte) (i10 >> 16);
        bArr[i4 + 4] = (byte) (i10 >> 8);
        bArr[i4 + 5] = (byte) i10;
        int i11 = iArr[i6];
        bArr[i4 + 6] = (byte) (i11 >> 16);
        int i12 = i4 + 8;
        bArr[i4 + 7] = (byte) (i11 >> 8);
        int i13 = i4 + 9;
        bArr[i12] = (byte) i11;
        return i13;
    }

    private static int _outputUptoBillion(int i3, byte[] bArr, int i4) {
        if (i3 >= MILLION) {
            int i5 = i3 / 1000;
            int i6 = i3 - (i5 * 1000);
            int i7 = i5 / 1000;
            int _leading3 = _leading3(i7, bArr, i4);
            int[] iArr = TRIPLET_TO_CHARS;
            int i8 = iArr[i5 - (i7 * 1000)];
            bArr[_leading3] = (byte) (i8 >> 16);
            bArr[_leading3 + 1] = (byte) (i8 >> 8);
            bArr[_leading3 + 2] = (byte) i8;
            int i9 = iArr[i6];
            bArr[_leading3 + 3] = (byte) (i9 >> 16);
            int i10 = _leading3 + 5;
            bArr[_leading3 + 4] = (byte) (i9 >> 8);
            int i11 = _leading3 + 6;
            bArr[i10] = (byte) i9;
            return i11;
        } else if (i3 < 1000) {
            return _leading3(i3, bArr, i4);
        } else {
            int i12 = i3 / 1000;
            return _outputUptoMillion(bArr, i4, i12, i3 - (i12 * 1000));
        }
    }

    public static int outputInt(int i3, byte[] bArr, int i4) {
        int i5;
        if (i3 < 0) {
            if (i3 == Integer.MIN_VALUE) {
                return _outputSmallestI(bArr, i4);
            }
            bArr[i4] = 45;
            i3 = -i3;
            i4++;
        }
        if (i3 >= MILLION) {
            int i6 = BILLION;
            if (i3 >= i6) {
                int i7 = i3 - i6;
                if (i7 >= i6) {
                    i7 -= i6;
                    i5 = i4 + 1;
                    bArr[i4] = 50;
                } else {
                    i5 = i4 + 1;
                    bArr[i4] = 49;
                }
                return _outputFullBillion(i7, bArr, i5);
            }
            int i8 = i3 / 1000;
            int i9 = i8 / 1000;
            return _full3(i3 - (i8 * 1000), bArr, _full3(i8 - (i9 * 1000), bArr, _leading3(i9, bArr, i4)));
        } else if (i3 >= 1000) {
            int i10 = i3 / 1000;
            return _full3(i3 - (i10 * 1000), bArr, _leading3(i10, bArr, i4));
        } else if (i3 >= 10) {
            return _leading3(i3, bArr, i4);
        } else {
            int i11 = i4 + 1;
            bArr[i4] = (byte) (i3 + 48);
            return i11;
        }
    }
}
