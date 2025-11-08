package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakk  reason: invalid package */
final class zzakk {
    private static final zzaki zza = zzc();
    private static final zzaki zzb = new zzakl();

    public static zzaki zza() {
        return zza;
    }

    public static zzaki zzb() {
        return zzb;
    }

    private static zzaki zzc() {
        try {
            return (zzaki) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }
}
