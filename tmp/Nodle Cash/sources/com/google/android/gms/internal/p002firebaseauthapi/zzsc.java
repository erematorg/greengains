package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsc  reason: invalid package */
public final class zzsc extends zzaje<zzsc, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsc zzc;
    private static volatile zzaky<zzsc> zzd;
    private int zze;
    private int zzf;
    private zzsg zzg;
    private zzud zzh;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsc$zza */
    public static final class zza extends zzaje.zza<zzsc, zza> implements zzakr {
        private zza() {
            super(zzsc.zzc);
        }

        public final zza zza(zzsg zzsg) {
            zzi();
            ((zzsc) this.zza).zza(zzsg);
            return this;
        }

        public /* synthetic */ zza(zzsd zzsd) {
            this();
        }

        public final zza zza(zzud zzud) {
            zzi();
            ((zzsc) this.zza).zza(zzud);
            return this;
        }
    }

    static {
        zzsc zzsc = new zzsc();
        zzc = zzsc;
        zzaje.zza(zzsc.class, zzsc);
    }

    private zzsc() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzsc> zzf() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzsg zzd() {
        zzsg zzsg = this.zzg;
        return zzsg == null ? zzsg.zzd() : zzsg;
    }

    public final zzud zze() {
        zzud zzud = this.zzh;
        return zzud == null ? zzud.zzd() : zzud;
    }

    public static zzsc zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzsc) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsd.zza[i3 - 1]) {
            case 1:
                return new zzsc();
            case 2:
                return new zza((zzsd) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsc> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsc.class) {
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
    public final void zza(zzsg zzsg) {
        zzsg.getClass();
        this.zzg = zzsg;
        this.zze |= 1;
    }

    /* access modifiers changed from: private */
    public final void zza(zzud zzud) {
        zzud.getClass();
        this.zzh = zzud;
        this.zze |= 2;
    }
}
