package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvg  reason: invalid package */
public final class zzvg extends zzaje<zzvg, zzb> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzvg zzc;
    private static volatile zzaky<zzvg> zzd;
    private int zze;
    private zzajl<zza> zzf = zzaje.zzp();

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvg$zza */
    public static final class zza extends zzaje<zza, C0058zza> implements zzakr {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzaky<zza> zzd;
        private int zze;
        private zzuw zzf;
        private int zzg;
        private int zzh;
        private int zzi;

        /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvg$zza$zza  reason: collision with other inner class name */
        public static final class C0058zza extends zzaje.zza<zza, C0058zza> implements zzakr {
            private C0058zza() {
                super(zza.zzc);
            }

            public final C0058zza zza(zzuw.zza zza) {
                zzi();
                ((zza) this.zza).zza((zzuw) ((zzaje) zza.zzf()));
                return this;
            }

            public /* synthetic */ C0058zza(zzvf zzvf) {
                this();
            }

            public final C0058zza zza(zzuw zzuw) {
                zzi();
                ((zza) this.zza).zza(zzuw);
                return this;
            }

            public final C0058zza zza(int i3) {
                zzi();
                ((zza) this.zza).zza(i3);
                return this;
            }

            public final C0058zza zza(zzvs zzvs) {
                zzi();
                ((zza) this.zza).zza(zzvs);
                return this;
            }

            public final C0058zza zza(zzva zzva) {
                zzi();
                ((zza) this.zza).zza(zzva);
                return this;
            }
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzaje.zza(zza.class, zza);
        }

        private zza() {
        }

        public static C0058zza zzd() {
            return (C0058zza) zzc.zzm();
        }

        public final int zza() {
            return this.zzh;
        }

        public final zzuw zzb() {
            zzuw zzuw = this.zzf;
            return zzuw == null ? zzuw.zzd() : zzuw;
        }

        public final zzva zzc() {
            zzva zza = zzva.zza(this.zzg);
            return zza == null ? zzva.UNRECOGNIZED : zza;
        }

        public final zzvs zzf() {
            zzvs zza = zzvs.zza(this.zzi);
            return zza == null ? zzvs.UNRECOGNIZED : zza;
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzvf.zza[i3 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0058zza((zzvf) null);
                case 3:
                    return zzaje.zza((zzakp) zzc, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzc;
                case 5:
                    zzaky<zza> zzaky = zzd;
                    if (zzaky == null) {
                        synchronized (zza.class) {
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
        public final void zza(zzuw zzuw) {
            zzuw.getClass();
            this.zzf = zzuw;
            this.zze |= 1;
        }

        /* access modifiers changed from: private */
        public final void zza(int i3) {
            this.zzh = i3;
        }

        /* access modifiers changed from: private */
        public final void zza(zzvs zzvs) {
            this.zzi = zzvs.zza();
        }

        /* access modifiers changed from: private */
        public final void zza(zzva zzva) {
            this.zzg = zzva.zza();
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvg$zzb */
    public static final class zzb extends zzaje.zza<zzvg, zzb> implements zzakr {
        private zzb() {
            super(zzvg.zzc);
        }

        public final int zza() {
            return ((zzvg) this.zza).zza();
        }

        public final zza zzb(int i3) {
            return ((zzvg) this.zza).zza(i3);
        }

        public /* synthetic */ zzb(zzvf zzvf) {
            this();
        }

        public final zzb zza(zza zza) {
            zzi();
            ((zzvg) this.zza).zza(zza);
            return this;
        }

        public final List<zza> zzb() {
            return Collections.unmodifiableList(((zzvg) this.zza).zze());
        }

        public final zzb zza(int i3) {
            zzi();
            ((zzvg) this.zza).zzc(i3);
            return this;
        }
    }

    static {
        zzvg zzvg = new zzvg();
        zzc = zzvg;
        zzaje.zza(zzvg.class, zzvg);
    }

    private zzvg() {
    }

    public static zzb zzc() {
        return (zzb) zzc.zzm();
    }

    public final int zza() {
        return this.zzf.size();
    }

    public final int zzb() {
        return this.zze;
    }

    public final List<zza> zze() {
        return this.zzf;
    }

    /* access modifiers changed from: private */
    public final void zzc(int i3) {
        this.zze = i3;
    }

    public final zza zza(int i3) {
        return this.zzf.get(i3);
    }

    public static zzvg zza(InputStream inputStream, zzaiq zzaiq) throws IOException {
        return (zzvg) zzaje.zza(zzc, inputStream, zzaiq);
    }

    public static zzvg zza(byte[] bArr, zzaiq zzaiq) throws zzajk {
        return (zzvg) zzaje.zza(zzc, bArr, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzvf.zza[i3 - 1]) {
            case 1:
                return new zzvg();
            case 2:
                return new zzb((zzvf) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zze", "zzf", zza.class});
            case 4:
                return zzc;
            case 5:
                zzaky<zzvg> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzvg.class) {
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
    public final void zza(zza zza2) {
        zza2.getClass();
        zzajl<zza> zzajl = this.zzf;
        if (!zzajl.zzc()) {
            this.zzf = zzaje.zza(zzajl);
        }
        this.zzf.add(zza2);
    }
}
