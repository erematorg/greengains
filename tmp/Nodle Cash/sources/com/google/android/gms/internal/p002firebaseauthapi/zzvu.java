package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvu  reason: invalid package */
public final class zzvu extends zzaje<zzvu, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvu zzc;
    private static volatile zzaky<zzvu> zzd;
    private String zze = "";
    private zzajl<zzvd> zzf = zzaje.zzp();

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvu$zza */
    public static final class zza extends zzaje.zza<zzvu, zza> implements zzakr {
        private zza() {
            super(zzvu.zzc);
        }

        public /* synthetic */ zza(zzvt zzvt) {
            this();
        }
    }

    static {
        zzvu zzvu = new zzvu();
        zzc = zzvu;
        zzaje.zza(zzvu.class, zzvu);
    }

    private zzvu() {
    }

    public static zzvu zzb() {
        return zzc;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvt.zza[i3 - 1]) {
            case 1:
                return new zzvu();
            case 2:
                return new zza((zzvt) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zze", "zzf", zzvd.class});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvu> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvu.class) {
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
}
