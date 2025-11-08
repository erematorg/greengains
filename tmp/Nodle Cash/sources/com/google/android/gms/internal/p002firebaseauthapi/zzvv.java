package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvv  reason: invalid package */
public final class zzvv extends zzaje<zzvv, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvv zzc;
    private static volatile zzaky<zzvv> zzd;
    private int zze;
    private zzaho zzf = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvv$zza */
    public static final class zza extends zzaje.zza<zzvv, zza> implements zzakr {
        private zza() {
            super(zzvv.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzvv) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zzvw zzvw) {
            this();
        }
    }

    static {
        zzvv zzvv = new zzvv();
        zzc = zzvv;
        zzaje.zza(zzvv.class, zzvv);
    }

    private zzvv() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzvv> zze() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zze;
    }

    public final zzaho zzd() {
        return this.zzf;
    }

    public static zzvv zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzvv) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvw.zza[i3 - 1]) {
            case 1:
                return new zzvv();
            case 2:
                return new zza((zzvw) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvv> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvv.class) {
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
        this.zzf = zzaho;
    }
}
