package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzts  reason: invalid package */
public final class zzts extends zzaje<zzts, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzts zzc;
    private static volatile zzaky<zzts> zzd;
    private int zze;
    private int zzf;
    private zzto zzg;
    private zzaho zzh;
    private zzaho zzi;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzts$zza */
    public static final class zza extends zzaje.zza<zzts, zza> implements zzakr {
        private zza() {
            super(zzts.zzc);
        }

        public final zza zza(zzto zzto) {
            zzi();
            ((zzts) this.zza).zza(zzto);
            return this;
        }

        public final zza zzb(zzaho zzaho) {
            zzi();
            ((zzts) this.zza).zzb(zzaho);
            return this;
        }

        public /* synthetic */ zza(zztt zztt) {
            this();
        }

        public final zza zza(int i3) {
            zzi();
            ((zzts) this.zza).zza(0);
            return this;
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzts) this.zza).zza(zzaho);
            return this;
        }
    }

    static {
        zzts zzts = new zzts();
        zzc = zzts;
        zzaje.zza(zzts.class, zzts);
    }

    private zzts() {
        zzaho zzaho = zzaho.zza;
        this.zzh = zzaho;
        this.zzi = zzaho;
    }

    public static zzaky<zzts> b_() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzts zze() {
        return zzc;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzto zzb() {
        zzto zzto = this.zzg;
        return zzto == null ? zzto.zze() : zzto;
    }

    public final zzaho zzf() {
        return this.zzh;
    }

    public final zzaho zzg() {
        return this.zzi;
    }

    public static zzts zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzts) zzaje.zza(zzc, zzaho, zzaiq);
    }

    /* access modifiers changed from: private */
    public final void zzb(zzaho zzaho) {
        zzaho.getClass();
        this.zzi = zzaho;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztt.zza[i3 - 1]) {
            case 1:
                return new zzts();
            case 2:
                return new zza((zztt) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n\u0004\n", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzts> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzts.class) {
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
    public final void zza(zzto zzto) {
        zzto.getClass();
        this.zzg = zzto;
        this.zze |= 1;
    }

    /* access modifiers changed from: private */
    public final void zza(int i3) {
        this.zzf = 0;
    }

    /* access modifiers changed from: private */
    public final void zza(zzaho zzaho) {
        zzaho.getClass();
        this.zzh = zzaho;
    }
}
