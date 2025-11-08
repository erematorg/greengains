package com.google.android.gms.internal.p002firebaseauthapi;

import android.app.Activity;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzads  reason: invalid package */
public final class zzads {
    private static final Map<String, zzadu> zza = new ArrayMap();

    public static PhoneAuthProvider.OnVerificationStateChangedCallbacks zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable zzacz zzacz) {
        zza(str, zzacz);
        return new zzadv(onVerificationStateChangedCallbacks, str);
    }

    public static void zza() {
        zza.clear();
    }

    private static void zza(String str, @Nullable zzacz zzacz) {
        zza.put(str, new zzadu(zzacz, DefaultClock.getInstance().currentTimeMillis()));
    }

    public static boolean zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, Executor executor) {
        Map<String, zzadu> map = zza;
        if (map.containsKey(str)) {
            zzadu zzadu = map.get(str);
            if (DefaultClock.getInstance().currentTimeMillis() - zzadu.zzb < 120000) {
                zzacz zzacz = zzadu.zza;
                if (zzacz == null) {
                    return true;
                }
                zzacz.zza(onVerificationStateChangedCallbacks, activity, executor, str);
                return true;
            }
            zza(str, (zzacz) null);
            return false;
        }
        zza(str, (zzacz) null);
        return false;
    }
}
