package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import android.util.Log;
import androidx.browser.trusted.c;
import com.google.android.gms.common.internal.LibraryVersion;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzact  reason: invalid package */
public final class zzact {
    private final int zza;

    private zzact(String str) {
        this.zza = zza(str);
    }

    private static int zza(String str) {
        try {
            List<String> zza2 = zzac.zza("[.-]").zza((CharSequence) str);
            if (zza2.size() == 1) {
                return Integer.parseInt(str);
            }
            if (zza2.size() < 3) {
                return -1;
            }
            return (Integer.parseInt(zza2.get(1)) * 1000) + (Integer.parseInt(zza2.get(0)) * 1000000) + Integer.parseInt(zza2.get(2));
        } catch (IllegalArgumentException e3) {
            if (!Log.isLoggable("LibraryVersionContainer", 3)) {
                return -1;
            }
            Log.d("LibraryVersionContainer", String.format("Version code parsing failed for: %s with exception %s.", new Object[]{str, e3}));
            return -1;
        }
    }

    public final String zzb() {
        return c.a("X", Integer.toString(this.zza));
    }

    public static zzact zza() {
        String version = LibraryVersion.getInstance().getVersion("firebase-auth");
        if (TextUtils.isEmpty(version) || version.equals("UNKNOWN")) {
            version = "-1";
        }
        return new zzact(version);
    }
}
