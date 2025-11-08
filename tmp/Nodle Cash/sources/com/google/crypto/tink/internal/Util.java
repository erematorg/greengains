package com.google.crypto.tink.internal;

import androidx.compose.animation.core.a;
import com.google.common.base.Ascii;
import com.google.crypto.tink.util.Bytes;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Objects;
import javax.annotation.Nullable;

public final class Util {
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private Util() {
    }

    @Nullable
    public static Integer getAndroidApiLevel() {
        if (!isAndroid()) {
            return null;
        }
        return BuildDispatchedCode.getApiLevel();
    }

    public static boolean isAndroid() {
        return Objects.equals(System.getProperty("java.vendor"), "The Android Project");
    }

    public static int randKeyId() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        byte b3 = 0;
        while (b3 == 0) {
            secureRandom.nextBytes(bArr);
            b3 = ((bArr[0] & Byte.MAX_VALUE) << Ascii.CAN) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return b3;
    }

    private static final byte toByteFromPrintableAscii(char c3) {
        if (c3 >= '!' && c3 <= '~') {
            return (byte) c3;
        }
        throw new TinkBugException(a.p("Not a printable ASCII character: ", c3));
    }

    public static final Bytes toBytesFromPrintableAscii(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i3 = 0; i3 < str.length(); i3++) {
            bArr[i3] = toByteFromPrintableAscii(str.charAt(i3));
        }
        return Bytes.copyFrom(bArr);
    }
}
