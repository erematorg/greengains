package com.google.crypto.tink.subtle;

public final class Hex {
    private Hex() {
    }

    public static byte[] decode(String str) {
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i3 = 0; i3 < length; i3++) {
                int i4 = i3 * 2;
                int digit = Character.digit(str.charAt(i4), 16);
                int digit2 = Character.digit(str.charAt(i4 + 1), 16);
                if (digit == -1 || digit2 == -1) {
                    throw new IllegalArgumentException("input is not hexadecimal");
                }
                bArr[i3] = (byte) ((digit * 16) + digit2);
            }
            return bArr;
        }
        throw new IllegalArgumentException("Expected a string of even length");
    }

    public static String encode(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b3 : bArr) {
            byte b4 = b3 & 255;
            sb.append("0123456789abcdef".charAt(b4 / 16));
            sb.append("0123456789abcdef".charAt(b4 % 16));
        }
        return sb.toString();
    }
}
