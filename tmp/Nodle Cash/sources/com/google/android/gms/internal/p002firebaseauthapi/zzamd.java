package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamd  reason: invalid package */
public final class zzamd extends zzaje<zzamd, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzamd zzc;
    private static volatile zzaky<zzamd> zzd;
    private long zze;
    private int zzf;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamd$zza */
    public static final class zza extends zzaje.zza<zzamd, zza> implements zzakr {
        private zza() {
            super(zzamd.zzc);
        }

        public final zza zza(int i3) {
            if (!this.zza.zzu()) {
                zzj();
            }
            ((zzamd) this.zza).zza(i3);
            return this;
        }

        public /* synthetic */ zza(zzamc zzamc) {
            this();
        }

        public final zza zza(long j2) {
            if (!this.zza.zzu()) {
                zzj();
            }
            ((zzamd) this.zza).zza(j2);
            return this;
        }
    }

    static {
        zzamd zzamd = new zzamd();
        zzc = zzamd;
        zzaje.zza(zzamd.class, zzamd);
    }

    private zzamd() {
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public final int zza() {
        return this.zzf;
    }

    public final long zzb() {
        return this.zze;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzamc.zza[i3 - 1]) {
            case 1:
                return new zzamd();
            case 2:
                return new zza((zzamc) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzamd> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzamd.class) {
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
    public final void zza(long j2) {
        this.zze = j2;
    }
}
