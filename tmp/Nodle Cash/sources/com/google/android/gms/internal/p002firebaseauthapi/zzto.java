package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzto  reason: invalid package */
public final class zzto extends zzaje<zzto, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzto zzc;
    private static volatile zzaky<zzto> zzd;
    private int zze;
    private zztv zzf;
    private zztj zzg;
    private int zzh;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzto$zza */
    public static final class zza extends zzaje.zza<zzto, zza> implements zzakr {
        private zza() {
            super(zzto.zzc);
        }

        public final zza zza(zztj zztj) {
            zzi();
            ((zzto) this.zza).zza(zztj);
            return this;
        }

        public /* synthetic */ zza(zztp zztp) {
            this();
        }

        public final zza zza(zzti zzti) {
            zzi();
            ((zzto) this.zza).zza(zzti);
            return this;
        }

        public final zza zza(zztv zztv) {
            zzi();
            ((zzto) this.zza).zza(zztv);
            return this;
        }
    }

    static {
        zzto zzto = new zzto();
        zzc = zzto;
        zzaje.zza(zzto.class, zzto);
    }

    private zzto() {
    }

    public static zza zzc() {
        return (zza) zzc.zzm();
    }

    public static zzto zze() {
        return zzc;
    }

    public final zzti zza() {
        zzti zza2 = zzti.zza(this.zzh);
        return zza2 == null ? zzti.UNRECOGNIZED : zza2;
    }

    public final zztj zzb() {
        zztj zztj = this.zzg;
        return zztj == null ? zztj.zzc() : zztj;
    }

    public final zztv zzf() {
        zztv zztv = this.zzf;
        return zztv == null ? zztv.zzc() : zztv;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztp.zza[i3 - 1]) {
            case 1:
                return new zzto();
            case 2:
                return new zza((zztp) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzto> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzto.class) {
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
    public final void zza(zztj zztj) {
        zztj.getClass();
        this.zzg = zztj;
        this.zze |= 2;
    }

    /* access modifiers changed from: private */
    public final void zza(zzti zzti) {
        this.zzh = zzti.zza();
    }

    /* access modifiers changed from: private */
    public final void zza(zztv zztv) {
        zztv.getClass();
        this.zzf = zztv;
        this.zze |= 1;
    }
}
