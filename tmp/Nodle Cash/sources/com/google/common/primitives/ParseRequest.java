package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import io.zksync.wrappers.ZkSyncContract;

@GwtCompatible
@ElementTypesAreNonnullByDefault
final class ParseRequest {
    final int radix;
    final String rawValue;

    private ParseRequest(String str, int i3) {
        this.rawValue = str;
        this.radix = i3;
    }

    public static ParseRequest fromString(String str) {
        if (str.length() != 0) {
            char charAt = str.charAt(0);
            int i3 = 16;
            if (str.startsWith(ZkSyncContract.BINARY) || str.startsWith("0X")) {
                str = str.substring(2);
            } else if (charAt == '#') {
                str = str.substring(1);
            } else if (charAt != '0' || str.length() <= 1) {
                i3 = 10;
            } else {
                str = str.substring(1);
                i3 = 8;
            }
            return new ParseRequest(str, i3);
        }
        throw new NumberFormatException("empty string");
    }
}
