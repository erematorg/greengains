package com.google.android.recaptcha.internal;

final class zzim implements zzkc {
    private static final zzim zza = new zzim();

    private zzim() {
    }

    public static zzim zza() {
        return zza;
    }

    public final zzkb zzb(Class cls) {
        Class<zzit> cls2 = zzit.class;
        if (cls2.isAssignableFrom(cls)) {
            try {
                return (zzkb) zzit.zzr(cls.asSubclass(cls2)).zzh(3, (Object) null, (Object) null);
            } catch (Exception e3) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e3);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
    }

    public final boolean zzc(Class cls) {
        return zzit.class.isAssignableFrom(cls);
    }
}
