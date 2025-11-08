package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzso  reason: invalid package */
public final class zzso extends zzaje<zzso, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzso zzc;
    private static volatile zzaky<zzso> zzd;
    private int zze;
    private zzsr zzf;
    private int zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzso$zza */
    public static final class zza extends zzaje.zza<zzso, zza> implements zzakr {
        private zza() {
            super(zzso.zzc);
        }

        public final zza zza(int i3) {
            zzi();
            ((zzso) this.zza).zza(i3);
            return this;
        }

        public /* synthetic */ zza(zzsp zzsp) {
            this();
        }

        public final zza zza(zzsr zzsr) {
            zzi();
            ((zzso) this.zza).zza(zzsr);
            return this;
        }
    }

    static {
        zzso zzso = new zzso();
        zzc = zzso;
        zzaje.zza(zzso.class, zzso);
    }

    private zzso() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public final int zza() {
        return this.zzg;
    }

    public final zzsr zzd() {
        zzsr zzsr = this.zzf;
        return zzsr == null ? zzsr.zzd() : zzsr;
    }

    public static zzso zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzso) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsp.zza[i3 - 1]) {
            case 1:
                return new zzso();
            case 2:
                return new zza((zzsp) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzso> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzso.class) {
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
    public final void zza(zzsr zzsr) {
        zzsr.getClass();
        this.zzf = zzsr;
        this.zze |= 1;
    }
}
