package com.ionspin.kotlin.bignum.integer.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0010\f\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"toDigit", "", "", "base", "bignum"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class DigitUtilKt {
    public static final int toDigit(char c3, int i3) {
        int i4;
        char c4;
        if ('0' <= c3 && c3 <= '9') {
            i4 = (char) (c3 - '0');
        } else if ('a' <= c3 && c3 <= 'z') {
            i4 = c3 - 'W';
        } else if ('A' > c3 || c3 > 'Z') {
            if (65313 <= c3 && c3 <= 65338) {
                c4 = 65323;
            } else if (65345 <= c3 && c3 <= 65370) {
                c4 = 65355;
            } else if (c3 == '.') {
                throw new NumberFormatException("Invalid digit for radix " + c3 + " (Possibly a decimal value, which is not supported by BigInteger parser");
            } else {
                throw new NumberFormatException(Intrinsics.stringPlus("Invalid digit for radix ", Character.valueOf(c3)));
            }
            i4 = c3 - c4;
        } else {
            i4 = c3 - '7';
        }
        if (i4 >= 0 && i4 < i3) {
            return i4;
        }
        throw new NumberFormatException(c3 + " is not a valid digit for number system with base " + i3);
    }

    public static /* synthetic */ int toDigit$default(char c3, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i3 = 10;
        }
        return toDigit(c3, i3);
    }
}
