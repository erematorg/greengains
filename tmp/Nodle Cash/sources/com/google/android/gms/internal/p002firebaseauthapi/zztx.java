package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztx  reason: invalid package */
public final class zztx extends zzaje<zztx, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zztx zzc;
    private static volatile zzaky<zztx> zzd;
    private int zze;
    private zzaho zzf = zzaho.zza;
    private zzvh zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zztx$zza */
    public static final class zza extends zzaje.zza<zztx, zza> implements zzakr {
        private zza() {
            super(zztx.zzc);
        }

        public final zza zza() {
            zzi();
            ((zztx) this.zza).zzd();
            return this;
        }

        public /* synthetic */ zza(zztz zztz) {
            this();
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zztx) this.zza).zza(zzaho);
            return this;
        }

        public final zza zza(zzvh zzvh) {
            zzi();
            ((zztx) this.zza).zza(zzvh);
            return this;
        }
    }

    static {
        zztx zztx = new zztx();
        zzc = zztx;
        zzaje.zza(zztx.class, zztx);
    }

    private zztx() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    /* access modifiers changed from: private */
    public final void zzd() {
        this.zzg = null;
        this.zze &= -2;
    }

    public final zzaho zzc() {
        return this.zzf;
    }

    public static zztx zza(InputStream inputStream, zzaiq zzaiq) throws IOException {
        return (zztx) zzaje.zza(zzc, inputStream, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztz.zza[i3 - 1]) {
            case 1:
                return new zztx();
            case 2:
                return new zza((zztz) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0001\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003ဉ\u0000", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zztx> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zztx.class) {
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
    public final void zza(zzaho zzaho) {
        zzaho.getClass();
        this.zzf = zzaho;
    }

    /* access modifiers changed from: private */
    public final void zza(zzvh zzvh) {
        zzvh.getClass();
        this.zzg = zzvh;
        this.zze |= 1;
    }
}
