package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaiu  reason: invalid package */
final class zzaiu {
    private static final zzais<?> zza = new zzaiv();
    private static final zzais<?> zzb = zzc();

    public static zzais<?> zza() {
        zzais<?> zzais = zzb;
        if (zzais != null) {
            return zzais;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static zzais<?> zzb() {
        return zza;
    }

    private static zzais<?> zzc() {
        try {
            return (zzais) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
            return null;
        }
    }
}
