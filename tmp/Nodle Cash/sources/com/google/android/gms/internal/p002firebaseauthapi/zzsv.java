package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsv  reason: invalid package */
public final class zzsv extends zzaje<zzsv, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsv zzc;
    private static volatile zzaky<zzsv> zzd;
    private int zze;
    private int zzf;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsv$zza */
    public static final class zza extends zzaje.zza<zzsv, zza> implements zzakr {
        private zza() {
            super(zzsv.zzc);
        }

        public final zza zza(int i3) {
            zzi();
            ((zzsv) this.zza).zza(i3);
            return this;
        }

        public /* synthetic */ zza(zzsu zzsu) {
            this();
        }
    }

    static {
        zzsv zzsv = new zzsv();
        zzc = zzsv;
        zzaje.zza(zzsv.class, zzsv);
    }

    private zzsv() {
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzf;
    }

    public static zzsv zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzsv) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsu.zza[i3 - 1]) {
            case 1:
                return new zzsv();
            case 2:
                return new zza((zzsu) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsv> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsv.class) {
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
