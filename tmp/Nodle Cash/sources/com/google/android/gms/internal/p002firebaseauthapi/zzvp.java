package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvp  reason: invalid package */
public final class zzvp extends zzaje<zzvp, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvp zzc;
    private static volatile zzaky<zzvp> zzd;
    private int zze;
    private String zzf = "";
    private zzvc zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvp$zza */
    public static final class zza extends zzaje.zza<zzvp, zza> implements zzakr {
        private zza() {
            super(zzvp.zzc);
        }

        public final zza zza(zzvc zzvc) {
            zzi();
            ((zzvp) this.zza).zza(zzvc);
            return this;
        }

        public /* synthetic */ zza(zzvq zzvq) {
            this();
        }

        public final zza zza(String str) {
            zzi();
            ((zzvp) this.zza).zza(str);
            return this;
        }
    }

    static {
        zzvp zzvp = new zzvp();
        zzc = zzvp;
        zzaje.zza(zzvp.class, zzvp);
    }

    private zzvp() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzvp zzd() {
        return zzc;
    }

    public final zzvc zza() {
        zzvc zzvc = this.zzg;
        return zzvc == null ? zzvc.zzc() : zzvc;
    }

    public final String zze() {
        return this.zzf;
    }

    public static zzvp zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzvp) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvq.zza[i3 - 1]) {
            case 1:
                return new zzvp();
            case 2:
                return new zza((zzvq) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvp> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvp.class) {
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
    public final void zza(zzvc zzvc) {
        zzvc.getClass();
        this.zzg = zzvc;
        this.zze |= 1;
    }

    /* access modifiers changed from: private */
    public final void zza(String str) {
        str.getClass();
        this.zzf = str;
    }
}
