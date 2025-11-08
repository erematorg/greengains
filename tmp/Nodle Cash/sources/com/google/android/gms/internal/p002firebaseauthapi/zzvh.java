package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvh  reason: invalid package */
public final class zzvh extends zzaje<zzvh, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvh zzc;
    private static volatile zzaky<zzvh> zzd;
    private int zze;
    private zzajl<zzb> zzf = zzaje.zzp();

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvh$zza */
    public static final class zza extends zzaje.zza<zzvh, zza> implements zzakr {
        private zza() {
            super(zzvh.zzc);
        }

        public final zza zza(zzb zzb) {
            zzi();
            ((zzvh) this.zza).zza(zzb);
            return this;
        }

        public /* synthetic */ zza(zzvi zzvi) {
            this();
        }

        public final zza zza(int i3) {
            zzi();
            ((zzvh) this.zza).zzc(i3);
            return this;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvh$zzb */
    public static final class zzb extends zzaje<zzb, zza> implements zzakr {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzaky<zzb> zzd;
        private String zze = "";
        private int zzf;
        private int zzg;
        private int zzh;

        /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvh$zzb$zza */
        public static final class zza extends zzaje.zza<zzb, zza> implements zzakr {
            private zza() {
                super(zzb.zzc);
            }

            public final zza zza(int i3) {
                zzi();
                ((zzb) this.zza).zza(i3);
                return this;
            }

            public /* synthetic */ zza(zzvi zzvi) {
                this();
            }

            public final zza zza(zzvs zzvs) {
                zzi();
                ((zzb) this.zza).zza(zzvs);
                return this;
            }

            public final zza zza(zzva zzva) {
                zzi();
                ((zzb) this.zza).zza(zzva);
                return this;
            }

            public final zza zza(String str) {
                zzi();
                ((zzb) this.zza).zza(str);
                return this;
            }
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzaje.zza(zzb.class, zzb);
        }

        private zzb() {
        }

        public static zza zzb() {
            return (zza) zzc.zzm();
        }

        public final int zza() {
            return this.zzg;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzvi.zza[i3 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza((zzvi) null);
                case 3:
                    return zzaje.zza((zzakp) zzc, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzc;
                case 5:
                    zzaky<zzb> zzaky = zzd;
                    if (zzaky == null) {
                        synchronized (zzb.class) {
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
            this.zzg = i3;
        }

        /* access modifiers changed from: private */
        public final void zza(zzvs zzvs) {
            this.zzh = zzvs.zza();
        }

        /* access modifiers changed from: private */
        public final void zza(zzva zzva) {
            this.zzf = zzva.zza();
        }

        /* access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zze = str;
        }
    }

    static {
        zzvh zzvh = new zzvh();
        zzc = zzvh;
        zzaje.zza(zzvh.class, zzvh);
    }

    private zzvh() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    /* access modifiers changed from: private */
    public final void zzc(int i3) {
        this.zze = i3;
    }

    public final zzb zza(int i3) {
        return this.zzf.get(0);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvi.zza[i3 - 1]) {
            case 1:
                return new zzvh();
            case 2:
                return new zza((zzvi) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zzb.class});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvh> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvh.class) {
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
    public final void zza(zzb zzb2) {
        zzb2.getClass();
        zzajl<zzb> zzajl = this.zzf;
        if (!zzajl.zzc()) {
            this.zzf = zzaje.zza(zzajl);
        }
        this.zzf.add(zzb2);
    }
}
