package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztj  reason: invalid package */
public final class zztj extends zzaje<zztj, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zztj zzc;
    private static volatile zzaky<zztj> zzd;
    private int zze;
    private zzvc zzf;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zztj$zza */
    public static final class zza extends zzaje.zza<zztj, zza> implements zzakr {
        private zza() {
            super(zztj.zzc);
        }

        public final zza zza(zzvc zzvc) {
            zzi();
            ((zztj) this.zza).zza(zzvc);
            return this;
        }

        public /* synthetic */ zza(zztl zztl) {
            this();
        }
    }

    static {
        zztj zztj = new zztj();
        zzc = zztj;
        zzaje.zza(zztj.class, zztj);
    }

    private zztj() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zztj zzc() {
        return zzc;
    }

    public final zzvc zzd() {
        zzvc zzvc = this.zzf;
        return zzvc == null ? zzvc.zzc() : zzvc;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztl.zza[i3 - 1]) {
            case 1:
                return new zztj();
            case 2:
                return new zza((zztl) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0001\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0000\u0002ဉ\u0000", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zztj> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zztj.class) {
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
    public final void zza(zzvc zzvc) {
        zzvc.getClass();
        this.zzf = zzvc;
        this.zze |= 1;
    }
}
