package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuv  reason: invalid package */
public final class zzuv extends zzaje<zzuv, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzuv zzc;
    private static volatile zzaky<zzuv> zzd;
    private int zze;
    private int zzf;
    private zzur zzg;
    private zzaho zzh = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuv$zza */
    public static final class zza extends zzaje.zza<zzuv, zza> implements zzakr {
        private zza() {
            super(zzuv.zzc);
        }

        public final zza zza(zzur zzur) {
            zzi();
            ((zzuv) this.zza).zza(zzur);
            return this;
        }

        public /* synthetic */ zza(zzuu zzuu) {
            this();
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzuv) this.zza).zza(zzaho);
            return this;
        }

        public final zza zza(int i3) {
            zzi();
            ((zzuv) this.zza).zza(0);
            return this;
        }
    }

    static {
        zzuv zzuv = new zzuv();
        zzc = zzuv;
        zzaje.zza(zzuv.class, zzuv);
    }

    private zzuv() {
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzuv zze() {
        return zzc;
    }

    public static zzaky<zzuv> zzg() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzur zzb() {
        zzur zzur = this.zzg;
        return zzur == null ? zzur.zzf() : zzur;
    }

    public final zzaho zzf() {
        return this.zzh;
    }

    public static zzuv zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzuv) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzuu.zza[i3 - 1]) {
            case 1:
                return new zzuv();
            case 2:
                return new zza((zzuu) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzuv> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzuv.class) {
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
    public final void zza(zzur zzur) {
        zzur.getClass();
        this.zzg = zzur;
        this.zze |= 1;
    }

    /* access modifiers changed from: private */
    public final void zza(zzaho zzaho) {
        zzaho.getClass();
        this.zzh = zzaho;
    }

    /* access modifiers changed from: private */
    public final void zza(int i3) {
        this.zzf = 0;
    }
}
