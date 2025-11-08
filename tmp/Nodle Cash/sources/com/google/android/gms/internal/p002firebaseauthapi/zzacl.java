package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacl  reason: invalid package */
public final /* synthetic */ class zzacl {
    public static String zza(zzacm zzacm, String str) {
        try {
            String str2 = new String(MessageDigest.getInstance("SHA-256").digest(str.getBytes()));
            int length = str2.length();
            int i3 = 0;
            while (i3 < length) {
                if (zzk.zza(str2.charAt(i3))) {
                    char[] charArray = str2.toCharArray();
                    while (i3 < length) {
                        char c3 = charArray[i3];
                        if (zzk.zza(c3)) {
                            charArray[i3] = (char) (c3 ^ ' ');
                        }
                        i3++;
                    }
                    return String.valueOf(charArray);
                }
                i3++;
            }
            return str2;
        } catch (NoSuchAlgorithmException unused) {
            zzacm.zza.e("Failed to get SHA-256 MessageDigest", new Object[0]);
            return null;
        }
    }

    public static void zzb(zzacm zzacm, String str) {
        zzacm.zza(str, (Status) null);
    }
}
