package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.NonNull;
import androidx.browser.trusted.c;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.commons.lang3.ClassUtils;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafo  reason: invalid package */
public final class zzafo {
    public static long zza(String str) {
        zzafr zzb = zzb(str);
        return zzb.zza().longValue() - zzb.zzb().longValue();
    }

    @NonNull
    private static zzafr zzb(String str) {
        Preconditions.checkNotEmpty(str);
        List<String> zza = zzac.zza((char) ClassUtils.PACKAGE_SEPARATOR_CHAR).zza((CharSequence) str);
        if (zza.size() >= 2) {
            try {
                return zzafr.zza(new String(Base64Utils.decodeUrlSafeNoPadding(zza.get(1)), "UTF-8"));
            } catch (UnsupportedEncodingException e3) {
                throw new RuntimeException("Unable to decode token", e3);
            }
        } else {
            throw new RuntimeException(c.a("Invalid idToken ", str));
        }
    }
}
