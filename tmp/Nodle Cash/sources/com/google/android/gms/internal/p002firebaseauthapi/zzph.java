package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Build;
import androidx.compose.animation.core.a;
import com.google.common.base.Ascii;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzph  reason: invalid package */
public final class zzph {
    public static final Charset zza = Charset.forName("UTF-8");

    public static int zza() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        byte b3 = 0;
        while (b3 == 0) {
            secureRandom.nextBytes(bArr);
            b3 = ((bArr[0] & 255) << Ascii.CAN) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return b3;
    }

    public static final zzxv zzb(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (charAt < '!' || charAt > '~') {
                throw new zzpf(a.p("Not a printable ASCII character: ", charAt));
            }
            bArr[i3] = (byte) charAt;
        }
        return zzxv.zza(bArr);
    }

    public static final zzxv zza(String str) throws GeneralSecurityException {
        byte[] bArr = new byte[str.length()];
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (charAt < '!' || charAt > '~') {
                throw new GeneralSecurityException(a.p("Not a printable ASCII character: ", charAt));
            }
            bArr[i3] = (byte) charAt;
        }
        return zzxv.zza(bArr);
    }

    @Nullable
    public static Integer zzb() {
        if (!Objects.equals(System.getProperty("java.vendor"), "The Android Project")) {
            return null;
        }
        return Integer.valueOf(Build.VERSION.SDK_INT);
    }

    public static boolean zza(byte[] bArr, byte[] bArr2) {
        if (bArr2.length < bArr.length) {
            return false;
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (bArr2[i3] != bArr[i3]) {
                return false;
            }
        }
        return true;
    }
}
