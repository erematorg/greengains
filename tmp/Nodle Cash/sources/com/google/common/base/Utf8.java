package com.google.common.base;

import A.a;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Utf8 {
    private Utf8() {
    }

    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length && charSequence.charAt(i3) < 128) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 < length) {
                char charAt = charSequence.charAt(i3);
                if (charAt >= 2048) {
                    i4 += encodedLengthGeneral(charSequence, i3);
                    break;
                }
                i4 += (127 - charAt) >>> 31;
                i3++;
            } else {
                break;
            }
        }
        if (i4 >= length) {
            return i4;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i4) + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i3) {
        int length = charSequence.length();
        int i4 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i4 += (127 - charAt) >>> 31;
            } else {
                i4 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i3) != charAt) {
                        i3++;
                    } else {
                        throw new IllegalArgumentException(unpairedSurrogateMsg(i3));
                    }
                }
            }
            i3++;
        }
        return i4;
    }

    public static boolean isWellFormed(byte[] bArr) {
        return isWellFormed(bArr, 0, bArr.length);
    }

    private static boolean isWellFormedSlowPath(byte[] bArr, int i3, int i4) {
        byte b3;
        while (i3 < i4) {
            int i5 = i3 + 1;
            byte b4 = bArr[i3];
            if (b4 >= 0) {
                i3 = i5;
            } else if (b4 < -32) {
                if (i5 != i4 && b4 >= -62) {
                    i3 += 2;
                    if (bArr[i5] > -65) {
                    }
                }
                return false;
            } else if (b4 < -16) {
                int i6 = i3 + 2;
                if (i6 < i4 && (b3 = bArr[i5]) <= -65 && ((b4 != -32 || b3 >= -96) && (b4 != -19 || -96 > b3))) {
                    i3 += 3;
                    if (bArr[i6] > -65) {
                    }
                }
                return false;
            } else if (i3 + 3 >= i4) {
                return false;
            } else {
                int i7 = i3 + 2;
                byte b5 = bArr[i5];
                if (b5 <= -65) {
                    if ((((b5 + 112) + (b4 << Ascii.FS)) >> 30) == 0) {
                        int i8 = i3 + 3;
                        if (bArr[i7] <= -65) {
                            i3 += 4;
                            if (bArr[i8] > -65) {
                            }
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static String unpairedSurrogateMsg(int i3) {
        return a.k("Unpaired surrogate at index ", i3);
    }

    public static boolean isWellFormed(byte[] bArr, int i3, int i4) {
        int i5 = i4 + i3;
        Preconditions.checkPositionIndexes(i3, i5, bArr.length);
        while (i3 < i5) {
            if (bArr[i3] < 0) {
                return isWellFormedSlowPath(bArr, i3, i5);
            }
            i3++;
        }
        return true;
    }
}
