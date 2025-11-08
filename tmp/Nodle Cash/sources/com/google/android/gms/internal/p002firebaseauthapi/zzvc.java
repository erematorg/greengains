package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvc  reason: invalid package */
public final class zzvc extends zzaje<zzvc, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvc zzc;
    private static volatile zzaky<zzvc> zzd;
    private String zze = "";
    private zzaho zzf = zzaho.zza;
    private int zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvc$zza */
    public static final class zza extends zzaje.zza<zzvc, zza> implements zzakr {
        private zza() {
            super(zzvc.zzc);
        }

        public final zza zza(zzvs zzvs) {
            zzi();
            ((zzvc) this.zza).zza(zzvs);
            return this;
        }

        public /* synthetic */ zza(zzvb zzvb) {
            this();
        }

        public final zza zza(String str) {
            zzi();
            ((zzvc) this.zza).zza(str);
            return this;
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzvc) this.zza).zza(zzaho);
            return this;
        }
    }

    static {
        zzvc zzvc = new zzvc();
        zzc = zzvc;
        zzaje.zza(zzvc.class, zzvc);
    }

    private zzvc() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzvc zzc() {
        return zzc;
    }

    public final zzvs zzd() {
        zzvs zza2 = zzvs.zza(this.zzg);
        return zza2 == null ? zzvs.UNRECOGNIZED : zza2;
    }

    public final zzaho zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zze;
    }

    public static zza zza(zzvc zzvc) {
        return (zza) zzc.zzm().zza(zzvc);
    }

    public static zzvc zza(byte[] bArr, zzaiq zzaiq) throws zzajk {
        return (zzvc) zzaje.zza(zzc, bArr, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvb.zza[i3 - 1]) {
            case 1:
                return new zzvc();
            case 2:
                return new zza((zzvb) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvc> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvc.class) {
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
    public final void zza(zzvs zzvs) {
        this.zzg = zzvs.zza();
    }

    /* access modifiers changed from: private */
    public final void zza(String str) {
        str.getClass();
        this.zze = str;
    }

    /* access modifiers changed from: private */
    public final void zza(zzaho zzaho) {
        zzaho.getClass();
        this.zzf = zzaho;
    }
}
