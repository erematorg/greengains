package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzur  reason: invalid package */
public final class zzur extends zzaje<zzur, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzur zzc;
    private static volatile zzaky<zzur> zzd;
    private int zze;
    private int zzf;
    private int zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzur$zza */
    public static final class zza extends zzaje.zza<zzur, zza> implements zzakr {
        private zza() {
            super(zzur.zzc);
        }

        public final zza zza(zzui zzui) {
            zzi();
            ((zzur) this.zza).zza(zzui);
            return this;
        }

        public /* synthetic */ zza(zzuq zzuq) {
            this();
        }

        public final zza zza(zzuj zzuj) {
            zzi();
            ((zzur) this.zza).zza(zzuj);
            return this;
        }

        public final zza zza(zzul zzul) {
            zzi();
            ((zzur) this.zza).zza(zzul);
            return this;
        }
    }

    static {
        zzur zzur = new zzur();
        zzc = zzur;
        zzaje.zza(zzur.class, zzur);
    }

    private zzur() {
    }

    public static zza zzd() {
        return (zza) zzc.zzm();
    }

    public static zzur zzf() {
        return zzc;
    }

    public final zzui zza() {
        zzui zza2 = zzui.zza(this.zzg);
        return zza2 == null ? zzui.UNRECOGNIZED : zza2;
    }

    public final zzuj zzb() {
        zzuj zza2 = zzuj.zza(this.zzf);
        return zza2 == null ? zzuj.UNRECOGNIZED : zza2;
    }

    public final zzul zzc() {
        zzul zza2 = zzul.zza(this.zze);
        return zza2 == null ? zzul.UNRECOGNIZED : zza2;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzuq.zza[i3 - 1]) {
            case 1:
                return new zzur();
            case 2:
                return new zza((zzuq) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzur> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzur.class) {
                        try {
                            zzaky = zzd;
                            if (zzaky == null) {
                                zzaky = new zzaje.zzc<>(zzc);
                                zzd = zzaky;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                return zzaky;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzui zzui) {
        this.zzg = zzui.zza();
    }

    /* access modifiers changed from: private */
    public final void zza(zzuj zzuj) {
        this.zzf = zzuj.zza();
    }

    /* access modifiers changed from: private */
    public final void zza(zzul zzul) {
        this.zze = zzul.zza();
    }
}
