package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzue  reason: invalid package */
public final class zzue extends zzaje<zzue, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzue zzc;
    private static volatile zzaky<zzue> zzd;
    private int zze;
    private zzuh zzf;
    private int zzg;
    private int zzh;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzue$zza */
    public static final class zza extends zzaje.zza<zzue, zza> implements zzakr {
        private zza() {
            super(zzue.zzc);
        }

        public final zza zza(int i3) {
            zzi();
            ((zzue) this.zza).zza(i3);
            return this;
        }

        public /* synthetic */ zza(zzuf zzuf) {
            this();
        }

        public final zza zza(zzuh zzuh) {
            zzi();
            ((zzue) this.zza).zza(zzuh);
            return this;
        }
    }

    static {
        zzue zzue = new zzue();
        zzc = zzue;
        zzaje.zza(zzue.class, zzue);
    }

    private zzue() {
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzue zze() {
        return zzc;
    }

    public final int zza() {
        return this.zzg;
    }

    public final int zzb() {
        return this.zzh;
    }

    public final zzuh zzf() {
        zzuh zzuh = this.zzf;
        return zzuh == null ? zzuh.zze() : zzuh;
    }

    public static zzue zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzue) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzuf.zza[i3 - 1]) {
            case 1:
                return new zzue();
            case 2:
                return new zza((zzuf) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzue> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzue.class) {
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
    public final void zza(zzuh zzuh) {
        zzuh.getClass();
        this.zzf = zzuh;
        this.zze |= 1;
    }
}
