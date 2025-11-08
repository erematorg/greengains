package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsk  reason: invalid package */
public final class zzsk extends zzaje<zzsk, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzsk zzc;
    private static volatile zzaky<zzsk> zzd;
    private int zze;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsk$zza */
    public static final class zza extends zzaje.zza<zzsk, zza> implements zzakr {
        private zza() {
            super(zzsk.zzc);
        }

        public final zza zza(int i3) {
            zzi();
            ((zzsk) this.zza).zza(i3);
            return this;
        }

        public /* synthetic */ zza(zzsl zzsl) {
            this();
        }
    }

    static {
        zzsk zzsk = new zzsk();
        zzc = zzsk;
        zzaje.zza(zzsk.class, zzsk);
    }

    private zzsk() {
    }

    public static zza zzb() {
        return (zza) zzc.zzm();
    }

    public static zzsk zzd() {
        return zzc;
    }

    public final int zza() {
        return this.zze;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzsl.zza[i3 - 1]) {
            case 1:
                return new zzsk();
            case 2:
                return new zza((zzsl) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zze"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzsk> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzsk.class) {
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
    public final void zza(int i3) {
        this.zze = i3;
    }
}
