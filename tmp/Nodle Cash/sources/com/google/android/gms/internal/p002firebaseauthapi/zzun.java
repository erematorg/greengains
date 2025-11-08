package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzun  reason: invalid package */
public final class zzun extends zzaje<zzun, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzun zzc;
    private static volatile zzaky<zzun> zzd;
    private int zze;
    private zzur zzf;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzun$zza */
    public static final class zza extends zzaje.zza<zzun, zza> implements zzakr {
        private zza() {
            super(zzun.zzc);
        }

        public final zza zza(zzur zzur) {
            zzi();
            ((zzun) this.zza).zza(zzur);
            return this;
        }

        public /* synthetic */ zza(zzup zzup) {
            this();
        }
    }

    static {
        zzun zzun = new zzun();
        zzc = zzun;
        zzaje.zza(zzun.class, zzun);
    }

    private zzun() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public final zzur zzc() {
        zzur zzur = this.zzf;
        return zzur == null ? zzur.zzf() : zzur;
    }

    public static zzun zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzun) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzup.zza[i3 - 1]) {
            case 1:
                return new zzun();
            case 2:
                return new zza((zzup) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzun> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzun.class) {
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
    public final void zza(zzur zzur) {
        zzur.getClass();
        this.zzf = zzur;
        this.zze |= 1;
    }
}
