package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakz  reason: invalid package */
final class zzakz {
    private static final zzakx zza = zzc();
    private static final zzakx zzb = new zzakw();

    public static zzakx zza() {
        return zza;
    }

    public static zzakx zzb() {
        return zzb;
    }

    private static zzakx zzc() {
        try {
            return (zzakx) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }
}
