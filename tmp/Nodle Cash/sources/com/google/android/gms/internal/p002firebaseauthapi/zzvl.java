package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvl  reason: invalid package */
public final class zzvl extends zzaje<zzvl, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvl zzc;
    private static volatile zzaky<zzvl> zzd;
    private String zze = "";

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvl$zza */
    public static final class zza extends zzaje.zza<zzvl, zza> implements zzakr {
        private zza() {
            super(zzvl.zzc);
        }

        public final zza zza(String str) {
            zzi();
            ((zzvl) this.zza).zza(str);
            return this;
        }

        public /* synthetic */ zza(zzvm zzvm) {
            this();
        }
    }

    static {
        zzvl zzvl = new zzvl();
        zzc = zzvl;
        zzaje.zza(zzvl.class, zzvl);
    }

    private zzvl() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzvl zzc() {
        return zzc;
    }

    public final String zzd() {
        return this.zze;
    }

    public static zzvl zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zzvl) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvm.zza[i3 - 1]) {
            case 1:
                return new zzvl();
            case 2:
                return new zza((zzvm) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvl> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvl.class) {
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
    public final void zza(String str) {
        str.getClass();
        this.zze = str;
    }
}
