package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzta  reason: invalid package */
public final class zzta extends zzaje<zzta, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzta zzc;
    private static volatile zzaky<zzta> zzd;
    private int zze;
    private zzaho zzf = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzta$zza */
    public static final class zza extends zzaje.zza<zzta, zza> implements zzakr {
        private zza() {
            super(zzta.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzta) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zztb zztb) {
            this();
        }
    }

    static {
        zzta zzta = new zzta();
        zzc = zzta;
        zzaje.zza(zzta.class, zzta);
    }

    private zzta() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzta> zze() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zze;
    }

    public final zzaho zzd() {
        return this.zzf;
    }

    public static zzta zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzta) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztb.zza[i3 - 1]) {
            case 1:
                return new zzta();
            case 2:
                return new zza((zztb) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzta> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzta.class) {
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
