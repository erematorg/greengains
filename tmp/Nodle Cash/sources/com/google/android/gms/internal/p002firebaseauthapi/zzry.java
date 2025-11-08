package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzry  reason: invalid package */
public final class zzry extends zzaje<zzry, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzry zzc;
    private static volatile zzaky<zzry> zzd;
    private int zze;
    private int zzf;
    private zzsb zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzry$zza */
    public static final class zza extends zzaje.zza<zzry, zza> implements zzakr {
        private zza() {
            super(zzry.zzc);
        }

        public final zza zza(int i3) {
            zzi();
            ((zzry) this.zza).zza(i3);
            return this;
        }

        public /* synthetic */ zza(zzrz zzrz) {
            this();
        }

        public final zza zza(zzsb zzsb) {
            zzi();
            ((zzry) this.zza).zza(zzsb);
            return this;
        }
    }

    static {
        zzry zzry = new zzry();
        zzc = zzry;
        zzaje.zza(zzry.class, zzry);
    }

    private zzry() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzsb zzd() {
        zzsb zzsb = this.zzg;
        return zzsb == null ? zzsb.zzd() : zzsb;
    }

    public static zzry zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzry) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzrz.zza[i3 - 1]) {
            case 1:
                return new zzry();
            case 2:
                return new zza((zzrz) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzry> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzry.class) {
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
        this.zzf = i3;
    }

    /* access modifiers changed from: private */
    public final void zza(zzsb zzsb) {
        zzsb.getClass();
        this.zzg = zzsb;
        this.zze |= 1;
    }
}
