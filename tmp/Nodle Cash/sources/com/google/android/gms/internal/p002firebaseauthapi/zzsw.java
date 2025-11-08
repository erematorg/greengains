package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsw  reason: invalid package */
public final class zzsw extends zzaje<zzsw, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsw zzc;
    private static volatile zzaky<zzsw> zzd;
    private int zze;
    private zzaho zzf = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsw$zza */
    public static final class zza extends zzaje.zza<zzsw, zza> implements zzakr {
        private zza() {
            super(zzsw.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzsw) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zzsx zzsx) {
            this();
        }
    }

    static {
        zzsw zzsw = new zzsw();
        zzc = zzsw;
        zzaje.zza(zzsw.class, zzsw);
    }

    private zzsw() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzsw> zze() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zze;
    }

    public final zzaho zzd() {
        return this.zzf;
    }

    public static zzsw zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzsw) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsx.zza[i3 - 1]) {
            case 1:
                return new zzsw();
            case 2:
                return new zza((zzsx) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsw> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsw.class) {
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
