package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzud  reason: invalid package */
public final class zzud extends zzaje<zzud, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzud zzc;
    private static volatile zzaky<zzud> zzd;
    private int zze;
    private int zzf;
    private zzuh zzg;
    private zzaho zzh = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzud$zza */
    public static final class zza extends zzaje.zza<zzud, zza> implements zzakr {
        private zza() {
            super(zzud.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzud) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zzuc zzuc) {
            this();
        }

        public final zza zza(zzuh zzuh) {
            zzi();
            ((zzud) this.zza).zza(zzuh);
            return this;
        }
    }

    static {
        zzud zzud = new zzud();
        zzc = zzud;
        zzaje.zza(zzud.class, zzud);
    }

    private zzud() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzud zzd() {
        return zzc;
    }

    public static zzaky<zzud> zzg() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzuh zze() {
        zzuh zzuh = this.zzg;
        return zzuh == null ? zzuh.zze() : zzuh;
    }

    public final zzaho zzf() {
        return this.zzh;
    }

    public static zzud zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzud) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzuc.zza[i3 - 1]) {
            case 1:
                return new zzud();
            case 2:
                return new zza((zzuc) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzud> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzud.class) {
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
        this.zzh = zzaho;
    }

    /* access modifiers changed from: private */
    public final void zza(zzuh zzuh) {
        zzuh.getClass();
        this.zzg = zzuh;
        this.zze |= 1;
    }
}
