package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsg  reason: invalid package */
public final class zzsg extends zzaje<zzsg, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsg zzc;
    private static volatile zzaky<zzsg> zzd;
    private int zze;
    private int zzf;
    private zzsk zzg;
    private zzaho zzh = zzaho.zza;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsg$zza */
    public static final class zza extends zzaje.zza<zzsg, zza> implements zzakr {
        private zza() {
            super(zzsg.zzc);
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzsg) this.zza).zza(zzaho);
            return this;
        }

        public /* synthetic */ zza(zzsh zzsh) {
            this();
        }

        public final zza zza(zzsk zzsk) {
            zzi();
            ((zzsg) this.zza).zza(zzsk);
            return this;
        }
    }

    static {
        zzsg zzsg = new zzsg();
        zzc = zzsg;
        zzaje.zza(zzsg.class, zzsg);
    }

    private zzsg() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzsg zzd() {
        return zzc;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzsk zze() {
        zzsk zzsk = this.zzg;
        return zzsk == null ? zzsk.zzd() : zzsk;
    }

    public final zzaho zzf() {
        return this.zzh;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsh.zza[i3 - 1]) {
            case 1:
                return new zzsg();
            case 2:
                return new zza((zzsh) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsg> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsg.class) {
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
    public final void zza(zzsk zzsk) {
        zzsk.getClass();
        this.zzg = zzsk;
        this.zze |= 1;
    }
}
