package com.google.android.gms.internal.measurement;

final class zzlz implements zznc {
    private static final zzmi zza = new zzly();
    private final zzmi zzb;

    public zzlz() {
        this(new zzma(zzld.zza(), zza));
    }

    public final <T> zznd<T> zza(Class<T> cls) {
        zznf.zza((Class<?>) cls);
        zzmj zza2 = this.zzb.zza(cls);
        if (zza2.zzc()) {
            return zzmr.zza(zznf.zza(), zzks.zza(), zza2.zza());
        }
        return zzmp.zza(cls, zza2, zzmv.zza(), zzlx.zza(), zznf.zza(), zzmb.zza[zza2.zzb().ordinal()] != 1 ? zzks.zza() : null, zzmg.zza());
    }

    private zzlz(zzmi zzmi) {
        this.zzb = (zzmi) zzle.zza(zzmi, "messageInfoFactory");
    }
}
