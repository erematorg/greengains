package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

final class zzea implements zzfk {
    private static final zzea zza = new zzea();

    private zzea() {
    }

    public static zzea zza() {
        return zza;
    }

    public final zzfj zzb(Class cls) {
        Class<zzeh> cls2 = zzeh.class;
        if (cls2.isAssignableFrom(cls)) {
            try {
                return (zzfj) zzeh.zzJ(cls.asSubclass(cls2)).zzg(3, (Object) null, (Object) null);
            } catch (Exception e3) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e3);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
    }

    public final boolean zzc(Class cls) {
        return zzeh.class.isAssignableFrom(cls);
    }
}
