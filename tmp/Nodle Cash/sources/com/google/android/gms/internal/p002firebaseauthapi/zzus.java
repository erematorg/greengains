package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzus  reason: invalid package */
public final class zzus extends zzaje<zzus, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzus zzc;
    private static volatile zzaky<zzus> zzd;
    private int zze;
    private int zzf;
    private zzuv zzg;
    private zzaho zzh = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzus$zza */
    public static final class zza extends zzaje.zza<zzus, zza> implements zzakr {
        private zza() {
            super(zzus.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzus) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zzut zzut) {
            this();
        }

        public final zza zza(zzuv zzuv) {
            zzi();
            ((zzus) this.zza).zza(zzuv);
            return this;
        }

        public final zza zza(int i3) {
            zzi();
            ((zzus) this.zza).zza(0);
            return this;
        }
    }

    static {
        zzus zzus = new zzus();
        zzc = zzus;
        zzaje.zza(zzus.class, zzus);
    }

    private zzus() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzus> zzf() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzuv zzd() {
        zzuv zzuv = this.zzg;
        return zzuv == null ? zzuv.zze() : zzuv;
    }

    public final zzaho zze() {
        return this.zzh;
    }

    public static zzus zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzus) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzut.zza[i3 - 1]) {
            case 1:
                return new zzus();
            case 2:
                return new zza((zzut) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzus> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzus.class) {
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
    public final void zza(zzaho zzaho) {
        zzaho.getClass();
        this.zzh = zzaho;
    }

    /* access modifiers changed from: private */
    public final void zza(zzuv zzuv) {
        zzuv.getClass();
        this.zzg = zzuv;
        this.zze |= 1;
    }

    /* access modifiers changed from: private */
    public final void zza(int i3) {
        this.zzf = 0;
    }
}
