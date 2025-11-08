package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsr  reason: invalid package */
public final class zzsr extends zzaje<zzsr, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsr zzc;
    private static volatile zzaky<zzsr> zzd;
    private int zze;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsr$zza */
    public static final class zza extends zzaje.zza<zzsr, zza> implements zzakr {
        private zza() {
            super(zzsr.zzc);
        }

        public final zza zza(int i3) {
            zzi();
            ((zzsr) this.zza).zza(i3);
            return this;
        }

        public /* synthetic */ zza(zzsq zzsq) {
            this();
        }
    }

    static {
        zzsr zzsr = new zzsr();
        zzc = zzsr;
        zzaje.zza(zzsr.class, zzsr);
    }

    private zzsr() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzsr zzd() {
        return zzc;
    }

    public final int zza() {
        return this.zze;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsq.zza[i3 - 1]) {
            case 1:
                return new zzsr();
            case 2:
                return new zza((zzsq) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsr> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsr.class) {
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
    public final void zza(int i3) {
        this.zze = i3;
    }
}
