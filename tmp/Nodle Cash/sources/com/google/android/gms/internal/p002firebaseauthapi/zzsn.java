package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsn  reason: invalid package */
public final class zzsn extends zzaje<zzsn, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsn zzc;
    private static volatile zzaky<zzsn> zzd;
    private int zze;
    private int zzf;
    private zzsr zzg;
    private zzaho zzh = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsn$zza */
    public static final class zza extends zzaje.zza<zzsn, zza> implements zzakr {
        private zza() {
            super(zzsn.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzsn) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zzsm zzsm) {
            this();
        }

        public final zza zza(zzsr zzsr) {
            zzi();
            ((zzsn) this.zza).zza(zzsr);
            return this;
        }
    }

    static {
        zzsn zzsn = new zzsn();
        zzc = zzsn;
        zzaje.zza(zzsn.class, zzsn);
    }

    private zzsn() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzsn> zzf() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzsr zzd() {
        zzsr zzsr = this.zzg;
        return zzsr == null ? zzsr.zzd() : zzsr;
    }

    public final zzaho zze() {
        return this.zzh;
    }

    public static zzsn zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzsn) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsm.zza[i3 - 1]) {
            case 1:
                return new zzsn();
            case 2:
                return new zza((zzsm) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsn> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsn.class) {
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
    public final void zza(zzsr zzsr) {
        zzsr.getClass();
        this.zzg = zzsr;
        this.zze |= 1;
    }
}
