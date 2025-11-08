package com.neovisionaries.ws.client;

import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import io.zksync.transaction.type.Transaction712;
import okio.Utf8;
import org.objectweb.asm.signature.SignatureVisitor;

class Base64 {
    private static final byte[] INDEX_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, Transaction712.EIP_712_TX_TYPE, 114, 115, 116, 117, 118, 119, CBORConstants.BYTE_STRING_1BYTE_LEN, CBORConstants.BYTE_STRING_2BYTE_LEN, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        return encode(Misc.getBytesUTF8(str));
    }

    private static int extractBits(byte[] bArr, int i3) {
        int i4 = i3 / 8;
        if (bArr.length <= i4) {
            return -1;
        }
        byte b3 = bArr.length - 1 == i4 ? 0 : bArr[i4 + 1];
        int i5 = (i3 % 24) / 6;
        if (i5 == 0) {
            return (bArr[i4] >> 2) & 63;
        }
        if (i5 == 1) {
            return ((bArr[i4] << 4) & 48) | ((b3 >> 4) & 15);
        }
        if (i5 == 2) {
            return ((bArr[i4] << 2) & 60) | ((b3 >> 6) & 3);
        }
        if (i5 != 3) {
            return 0;
        }
        return bArr[i4] & Utf8.REPLACEMENT_BYTE;
    }

    public static String encode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = (((((bArr.length * 8) + 5) / 6) + 3) / 4) * 4;
        StringBuilder sb = new StringBuilder(length);
        int i3 = 0;
        while (true) {
            int extractBits = extractBits(bArr, i3);
            if (extractBits < 0) {
                break;
            }
            sb.append((char) INDEX_TABLE[extractBits]);
            i3 += 6;
        }
        for (int length2 = sb.length(); length2 < length; length2++) {
            sb.append(SignatureVisitor.INSTANCEOF);
        }
        return sb.toString();
    }
}
