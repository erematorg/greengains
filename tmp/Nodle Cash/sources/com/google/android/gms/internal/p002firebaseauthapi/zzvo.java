package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvo  reason: invalid package */
public final class zzvo extends zzaje<zzvo, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvo zzc;
    private static volatile zzaky<zzvo> zzd;
    private int zze;
    private int zzf;
    private zzvp zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvo$zza */
    public static final class zza extends zzaje.zza<zzvo, zza> implements zzakr {
        private zza() {
            super(zzvo.zzc);
        }

        public final zza zza(zzvp zzvp) {
            zzi();
            ((zzvo) this.zza).zza(zzvp);
            return this;
        }

        public /* synthetic */ zza(zzvn zzvn) {
            this();
        }
    }

    static {
        zzvo zzvo = new zzvo();
        zzc = zzvo;
        zzaje.zza(zzvo.class, zzvo);
    }

    private zzvo() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzvo> zze() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzvp zzd() {
        zzvp zzvp = this.zzg;
        return zzvp == null ? zzvp.zzd() : zzvp;
    }

    public static zzvo zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzvo) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvn.zza[i3 - 1]) {
            case 1:
                return new zzvo();
            case 2:
                return new zza((zzvn) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvo> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvo.class) {
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
    public final void zza(zzvp zzvp) {
        zzvp.getClass();
        this.zzg = zzvp;
        this.zze |= 1;
    }
}
