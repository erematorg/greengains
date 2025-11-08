package com.google.android.recaptcha.internal;

final class zzjw implements zzks {
    private static final zzkc zza = new zzju();
    private final zzkc zzb;

    public zzjw() {
        zzkc zzkc;
        zzim zza2 = zzim.zza();
        try {
            zzkc = (zzkc) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            zzkc = zza;
        }
        zzjv zzjv = new zzjv(zza2, zzkc);
        byte[] bArr = zzjc.zzd;
        this.zzb = zzjv;
    }

    private static boolean zzb(zzkb zzkb) {
        return zzkb.zzc() + -1 != 1;
    }

    public final zzkr zza(Class cls) {
        zzkt.zzs(cls);
        zzkb zzb2 = this.zzb.zzb(cls);
        Class<zzit> cls2 = zzit.class;
        if (zzb2.zzb()) {
            return cls2.isAssignableFrom(cls) ? zzki.zzc(zzkt.zzn(), zzih.zzb(), zzb2.zza()) : zzki.zzc(zzkt.zzm(), zzih.zza(), zzb2.zza());
        }
        if (cls2.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzkh.zzm(cls, zzb2, zzkl.zzb(), zzjs.zze(), zzkt.zzn(), zzih.zzb(), zzka.zzb());
            }
            return zzkh.zzm(cls, zzb2, zzkl.zzb(), zzjs.zze(), zzkt.zzn(), (zzif) null, zzka.zzb());
        } else if (zzb(zzb2)) {
            return zzkh.zzm(cls, zzb2, zzkl.zza(), zzjs.zzd(), zzkt.zzm(), zzih.zza(), zzka.zza());
        } else {
            return zzkh.zzm(cls, zzb2, zzkl.zza(), zzjs.zzd(), zzkt.zzm(), (zzif) null, zzka.zza());
        }
    }
}
