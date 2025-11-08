package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsf  reason: invalid package */
public final class zzsf extends zzaje<zzsf, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsf zzc;
    private static volatile zzaky<zzsf> zzd;
    private int zze;
    private zzsj zzf;
    private zzue zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsf$zza */
    public static final class zza extends zzaje.zza<zzsf, zza> implements zzakr {
        private zza() {
            super(zzsf.zzc);
        }

        public final zza zza(zzsj zzsj) {
            zzi();
            ((zzsf) this.zza).zza(zzsj);
            return this;
        }

        public /* synthetic */ zza(zzse zzse) {
            this();
        }

        public final zza zza(zzue zzue) {
            zzi();
            ((zzsf) this.zza).zza(zzue);
            return this;
        }
    }

    static {
        zzsf zzsf = new zzsf();
        zzc = zzsf;
        zzaje.zza(zzsf.class, zzsf);
    }

    private zzsf() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public final zzsj zzc() {
        zzsj zzsj = this.zzf;
        return zzsj == null ? zzsj.zzd() : zzsj;
    }

    public final zzue zzd() {
        zzue zzue = this.zzg;
        return zzue == null ? zzue.zze() : zzue;
    }

    public static zzsf zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzsf) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzse.zza[i3 - 1]) {
            case 1:
                return new zzsf();
            case 2:
                return new zza((zzse) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsf> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsf.class) {
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
    public final void zza(zzsj zzsj) {
        zzsj.getClass();
        this.zzf = zzsj;
        this.zze |= 1;
    }

    /* access modifiers changed from: private */
    public final void zza(zzue zzue) {
        zzue.getClass();
        this.zzg = zzue;
        this.zze |= 2;
    }
}
