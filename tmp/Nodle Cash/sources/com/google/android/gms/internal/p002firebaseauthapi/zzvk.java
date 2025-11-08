package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvk  reason: invalid package */
public final class zzvk extends zzaje<zzvk, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvk zzc;
    private static volatile zzaky<zzvk> zzd;
    private int zze;
    private int zzf;
    private zzvl zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvk$zza */
    public static final class zza extends zzaje.zza<zzvk, zza> implements zzakr {
        private zza() {
            super(zzvk.zzc);
        }

        public final zza zza(zzvl zzvl) {
            zzi();
            ((zzvk) this.zza).zza(zzvl);
            return this;
        }

        public /* synthetic */ zza(zzvj zzvj) {
            this();
        }
    }

    static {
        zzvk zzvk = new zzvk();
        zzc = zzvk;
        zzaje.zza(zzvk.class, zzvk);
    }

    private zzvk() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzaky<zzvk> zze() {
        return (zzaky) zzc.zza(zzaje.zzf.zzg, (Object) null, (Object) null);
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzvl zzd() {
        zzvl zzvl = this.zzg;
        return zzvl == null ? zzvl.zzc() : zzvl;
    }

    public static zzvk zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzvk) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvj.zza[i3 - 1]) {
            case 1:
                return new zzvk();
            case 2:
                return new zza((zzvj) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvk> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvk.class) {
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
    public final void zza(zzvl zzvl) {
        zzvl.getClass();
        this.zzg = zzvl;
        this.zze |= 1;
    }
}
