package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztv  reason: invalid package */
public final class zztv extends zzaje<zztv, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zztv zzc;
    private static volatile zzaky<zztv> zzd;
    private int zze;
    private int zzf;
    private zzaho zzg = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zztv$zza */
    public static final class zza extends zzaje.zza<zztv, zza> implements zzakr {
        private zza() {
            super(zztv.zzc);
        }

        public final zza zza(zztw zztw) {
            zzi();
            ((zztv) this.zza).zza(zztw);
            return this;
        }

        public /* synthetic */ zza(zztu zztu) {
            this();
        }

        public final zza zza(zzub zzub) {
            zzi();
            ((zztv) this.zza).zza(zzub);
            return this;
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zztv) this.zza).zza(zzaho);
            return this;
        }
    }

    static {
        zztv zztv = new zztv();
        zzc = zztv;
        zzaje.zza(zztv.class, zztv);
    }

    private zztv() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zztv zzc() {
        return zzc;
    }

    public final zztw zzd() {
        zztw zza2 = zztw.zza(this.zze);
        return zza2 == null ? zztw.UNRECOGNIZED : zza2;
    }

    public final zzub zze() {
        zzub zza2 = zzub.zza(this.zzf);
        return zza2 == null ? zzub.UNRECOGNIZED : zza2;
    }

    public final zzaho zzf() {
        return this.zzg;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztu.zza[i3 - 1]) {
            case 1:
                return new zztv();
            case 2:
                return new zza((zztu) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zztv> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zztv.class) {
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
    public final void zza(zztw zztw) {
        this.zze = zztw.zza();
    }

    /* access modifiers changed from: private */
    public final void zza(zzub zzub) {
        this.zzf = zzub.zza();
    }

    /* access modifiers changed from: private */
    public final void zza(zzaho zzaho) {
        zzaho.getClass();
        this.zzg = zzaho;
    }
}
