package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.browser.trusted.c;
import java.lang.reflect.Type;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacs  reason: invalid package */
public class zzacs {
    private static final String zza = "com.google.android.gms.internal.firebase-auth-api.zzacs";

    private zzacs() {
    }

    public static Object zza(String str, Type type) throws zzaah {
        if (type == String.class) {
            try {
                zzaek zzaek = (zzaek) new zzaek().zza(str);
                if (zzaek.zzb()) {
                    return zzaek.zza();
                }
                throw new zzaah("No error message: " + str);
            } catch (Exception e3) {
                throw new zzaah(c.a("Json conversion failed! ", e3.getMessage()), e3);
            }
        } else if (type == Void.class) {
            return null;
        } else {
            try {
                try {
                    return ((zzacu) ((Class) type).getConstructor((Class[]) null).newInstance((Object[]) null)).zza(str);
                } catch (Exception e4) {
                    throw new zzaah(c.a("Json conversion failed! ", e4.getMessage()), e4);
                }
            } catch (Exception e5) {
                throw new zzaah("Instantiation of JsonResponse failed! ".concat(String.valueOf(type)), e5);
            }
        }
    }
}
