package com.caverock.androidsvg;

import androidx.collection.SieveCacheKt;

class IntegerParser {
    private int pos;
    private long value;

    public IntegerParser(long j2, int i3) {
        this.value = j2;
        this.pos = i3;
    }

    public static IntegerParser parseHex(String str, int i3, int i4) {
        long j2;
        int i5;
        if (i3 >= i4) {
            return null;
        }
        long j3 = 0;
        int i6 = i3;
        while (i6 < i4) {
            char charAt = str.charAt(i6);
            if (charAt < '0' || charAt > '9') {
                if (charAt < 'A' || charAt > 'F') {
                    if (charAt < 'a' || charAt > 'f') {
                        break;
                    }
                    j2 = j3 * 16;
                    i5 = charAt - 'a';
                } else {
                    j2 = j3 * 16;
                    i5 = charAt - 'A';
                }
                j3 = j2 + ((long) i5) + 10;
            } else {
                j3 = (j3 * 16) + ((long) (charAt - '0'));
            }
            if (j3 > 4294967295L) {
                return null;
            }
            i6++;
        }
        if (i6 == i3) {
            return null;
        }
        return new IntegerParser(j3, i6);
    }

    public static IntegerParser parseInt(String str, int i3, int i4, boolean z2) {
        if (i3 >= i4) {
            return null;
        }
        boolean z3 = false;
        if (z2) {
            char charAt = str.charAt(i3);
            if (charAt != '+') {
                if (charAt == '-') {
                    z3 = true;
                }
            }
            i3++;
        }
        long j2 = 0;
        int i5 = i3;
        while (i5 < i4) {
            char charAt2 = str.charAt(i5);
            if (charAt2 < '0' || charAt2 > '9') {
                break;
            }
            if (z3) {
                j2 = (j2 * 10) - ((long) (charAt2 - '0'));
                if (j2 < SieveCacheKt.NodeMetaAndPreviousMask) {
                    return null;
                }
            } else {
                j2 = (j2 * 10) + ((long) (charAt2 - '0'));
                if (j2 > SieveCacheKt.NodeLinkMask) {
                    return null;
                }
            }
            i5++;
        }
        if (i5 == i3) {
            return null;
        }
        return new IntegerParser(j2, i5);
    }

    public int getEndPos() {
        return this.pos;
    }

    public int value() {
        return (int) this.value;
    }
}
