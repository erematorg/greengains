package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuh  reason: invalid package */
public final class zzuh extends zzaje<zzuh, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzuh zzc;
    private static volatile zzaky<zzuh> zzd;
    private int zze;
    private int zzf;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuh$zza */
    public static final class zza extends zzaje.zza<zzuh, zza> implements zzakr {
        private zza() {
            super(zzuh.zzc);
        }

        public final zza zza(zzub zzub) {
            zzi();
            ((zzuh) this.zza).zza(zzub);
            return this;
        }

        public /* synthetic */ zza(zzug zzug) {
            this();
        }

        public final zza zza(int i3) {
            zzi();
            ((zzuh) this.zza).zza(i3);
            return this;
        }
    }

    static {
        zzuh zzuh = new zzuh();
        zzc = zzuh;
        zzaje.zza(zzuh.class, zzuh);
    }

    private zzuh() {
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzuh zze() {
        return zzc;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzub zzb() {
        zzub zza2 = zzub.zza(this.zze);
        return zza2 == null ? zzub.UNRECOGNIZED : zza2;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzug.zza[i3 - 1]) {
            case 1:
                return new zzuh();
            case 2:
                return new zza((zzug) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzuh> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzuh.class) {
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
    public final void zza(zzub zzub) {
        this.zze = zzub.zza();
    }

    /* access modifiers changed from: private */
    public final void zza(int i3) {
        this.zzf = i3;
    }
}
