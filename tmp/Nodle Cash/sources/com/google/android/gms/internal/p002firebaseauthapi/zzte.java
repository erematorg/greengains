package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzte  reason: invalid package */
public final class zzte extends zzaje<zzte, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzte zzc;
    private static volatile zzaky<zzte> zzd;
    private int zze;
    private zzaho zzf = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzte$zza */
    public static final class zza extends zzaje.zza<zzte, zza> implements zzakr {
        private zza() {
            super(zzte.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzte) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zztf zztf) {
            this();
        }
    }

    static {
        zzte zzte = new zzte();
        zzc = zzte;
        zzaje.zza(zzte.class, zzte);
    }

    private zzte() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzte> zze() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zze;
    }

    public final zzaho zzd() {
        return this.zzf;
    }

    public static zzte zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzte) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztf.zza[i3 - 1]) {
            case 1:
                return new zzte();
            case 2:
                return new zza((zztf) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzte> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzte.class) {
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
