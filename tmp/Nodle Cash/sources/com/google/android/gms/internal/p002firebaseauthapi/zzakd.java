package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakd  reason: invalid package */
final class zzakd implements zzalg {
    private static final zzakm zza = new zzakc();
    private final zzakm zzb;

    public zzakd() {
        this(new zzake(zzajc.zza(), zza()));
    }

    private static zzakm zza() {
        try {
            return (zzakm) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            return zza;
        }
    }

    private zzakd(zzakm zzakm) {
        this.zzb = (zzakm) zzajh.zza(zzakm, "messageInfoFactory");
    }

    public final <T> zzalh<T> zza(Class<T> cls) {
        zzalj.zza((Class<?>) cls);
        zzakn zza2 = this.zzb.zza(cls);
        Class<zzaje> cls2 = zzaje.class;
        if (zza2.zzc()) {
            if (cls2.isAssignableFrom(cls)) {
                return zzakv.zza(zzalj.zzb(), zzaiu.zzb(), zza2.zza());
            }
            return zzakv.zza(zzalj.zza(), zzaiu.zza(), zza2.zza());
        } else if (cls2.isAssignableFrom(cls)) {
            if (zza(zza2)) {
                return zzakt.zza(cls, zza2, zzakz.zzb(), zzaju.zzb(), zzalj.zzb(), zzaiu.zzb(), zzakk.zzb());
            }
            return zzakt.zza(cls, zza2, zzakz.zzb(), zzaju.zzb(), zzalj.zzb(), (zzais<?>) null, zzakk.zzb());
        } else if (zza(zza2)) {
            return zzakt.zza(cls, zza2, zzakz.zza(), zzaju.zza(), zzalj.zza(), zzaiu.zza(), zzakk.zza());
        } else {
            return zzakt.zza(cls, zza2, zzakz.zza(), zzaju.zza(), zzalj.zza(), (zzais<?>) null, zzakk.zza());
        }
    }

    private static boolean zza(zzakn zzakn) {
        return zzakf.zza[zzakn.zzb().ordinal()] != 1;
    }
}
