package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

@Deprecated
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvd  reason: invalid package */
public final class zzvd extends zzaje<zzvd, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvd zzc;
    private static volatile zzaky<zzvd> zzd;
    private String zze = "";
    private String zzf = "";
    private int zzg;
    private boolean zzh;
    private String zzi = "";

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvd$zza */
    public static final class zza extends zzaje.zza<zzvd, zza> implements zzakr {
        private zza() {
            super(zzvd.zzc);
        }

        public /* synthetic */ zza(zzve zzve) {
            this();
        }
    }

    static {
        zzvd zzvd = new zzvd();
        zzc = zzvd;
        zzaje.zza(zzvd.class, zzvd);
    }

    private zzvd() {
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzve.zza[i3 - 1]) {
            case 1:
                return new zzvd();
            case 2:
                return new zza((zzve) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvd> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvd.class) {
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
