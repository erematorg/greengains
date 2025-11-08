package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrx  reason: invalid package */
public final class zzrx extends zzaje<zzrx, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzrx zzc;
    private static volatile zzaky<zzrx> zzd;
    private int zze;
    private int zzf;
    private zzaho zzg = zzaho.zza;
    private zzsb zzh;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrx$zza */
    public static final class zza extends zzaje.zza<zzrx, zza> implements zzakr {
        private zza() {
            super(zzrx.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzrx) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zzrw zzrw) {
            this();
        }

        public final zza zza(zzsb zzsb) {
            zzi();
            ((zzrx) this.zza).zza(zzsb);
            return this;
        }
    }

    static {
        zzrx zzrx = new zzrx();
        zzc = zzrx;
        zzaje.zza(zzrx.class, zzrx);
    }

    private zzrx() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzrx> zzf() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzsb zzd() {
        zzsb zzsb = this.zzh;
        return zzsb == null ? zzsb.zzd() : zzsb;
    }

    public final zzaho zze() {
        return this.zzg;
    }

    public static zzrx zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzrx) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzrw.zza[i3 - 1]) {
            case 1:
                return new zzrx();
            case 2:
                return new zza((zzrw) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003ဉ\u0000", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzrx> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzrx.class) {
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
        this.zzg = zzaho;
    }

    /* access modifiers changed from: private */
    public final void zza(zzsb zzsb) {
        zzsb.getClass();
        this.zzh = zzsb;
        this.zze |= 1;
    }
}
