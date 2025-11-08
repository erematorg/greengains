package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsj  reason: invalid package */
public final class zzsj extends zzaje<zzsj, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsj zzc;
    private static volatile zzaky<zzsj> zzd;
    private int zze;
    private zzsk zzf;
    private int zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsj$zza */
    public static final class zza extends zzaje.zza<zzsj, zza> implements zzakr {
        private zza() {
            super(zzsj.zzc);
        }

        public final zza zza(int i3) {
            zzi();
            ((zzsj) this.zza).zza(i3);
            return this;
        }

        public /* synthetic */ zza(zzsi zzsi) {
            this();
        }

        public final zza zza(zzsk zzsk) {
            zzi();
            ((zzsj) this.zza).zza(zzsk);
            return this;
        }
    }

    static {
        zzsj zzsj = new zzsj();
        zzc = zzsj;
        zzaje.zza(zzsj.class, zzsj);
    }

    private zzsj() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzsj zzd() {
        return zzc;
    }

    public final int zza() {
        return this.zzg;
    }

    public final zzsk zze() {
        zzsk zzsk = this.zzf;
        return zzsk == null ? zzsk.zzd() : zzsk;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsi.zza[i3 - 1]) {
            case 1:
                return new zzsj();
            case 2:
                return new zza((zzsi) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsj> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsj.class) {
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
        this.zzg = i3;
    }

    /* access modifiers changed from: private */
    public final void zza(zzsk zzsk) {
        zzsk.getClass();
        this.zzf = zzsk;
        this.zze |= 1;
    }
}
