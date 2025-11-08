package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztr  reason: invalid package */
public final class zztr extends zzaje<zztr, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zztr zzc;
    private static volatile zzaky<zztr> zzd;
    private int zze;
    private int zzf;
    private zzts zzg;
    private zzaho zzh = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zztr$zza */
    public static final class zza extends zzaje.zza<zztr, zza> implements zzakr {
        private zza() {
            super(zztr.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zztr) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zztq zztq) {
            this();
        }

        public final zza zza(zzts zzts) {
            zzi();
            ((zztr) this.zza).zza(zzts);
            return this;
        }

        public final zza zza(int i3) {
            zzi();
            ((zztr) this.zza).zza(0);
            return this;
        }
    }

    static {
        zztr zztr = new zztr();
        zzc = zztr;
        zzaje.zza(zztr.class, zztr);
    }

    private zztr() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zztr> zzf() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzts zzd() {
        zzts zzts = this.zzg;
        return zzts == null ? zzts.zze() : zzts;
    }

    public final zzaho zze() {
        return this.zzh;
    }

    public static zztr zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zztr) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztq.zza[i3 - 1]) {
            case 1:
                return new zztr();
            case 2:
                return new zza((zztq) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zztr> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zztr.class) {
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
    public final void zza(zzts zzts) {
        zzts.getClass();
        this.zzg = zzts;
        this.zze |= 1;
    }

    /* access modifiers changed from: private */
    public final void zza(int i3) {
        this.zzf = 0;
    }
}
