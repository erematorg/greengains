package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzlc;
import java.util.List;
import kotlin.text.Typography;

public final class zzhg {

    public static final class zza extends zzlc<zza, C0065zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzmu<zza> zzd;
        private zzll<zzb> zze = zzlc.zzch();

        /* renamed from: com.google.android.gms.internal.measurement.zzhg$zza$zza  reason: collision with other inner class name */
        public static final class C0065zza extends zzlc.zzb<zza, C0065zza> implements zzmn {
            private C0065zza() {
                super(zza.zzc);
            }
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzlc.zza(zza.class, zza);
        }

        private zza() {
        }

        public static zza zzc() {
            return zzc;
        }

        public final int zza() {
            return this.zze.size();
        }

        public final List<zzb> zzd() {
            return this.zze;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzhi.zza[i3 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0065zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzb.class});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zza> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zza.class) {
                            try {
                                zzmu = zzd;
                                if (zzmu == null) {
                                    zzmu = new zzlc.zza<>(zzc);
                                    zzd = zzmu;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    return zzmu;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzb extends zzlc<zzb, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzmu<zzb> zzd;
        private int zze;
        private String zzf = "";
        private zzll<zzd> zzg = zzlc.zzch();

        public static final class zza extends zzlc.zzb<zzb, zza> implements zzmn {
            private zza() {
                super(zzb.zzc);
            }
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzlc.zza(zzb.class, zzb);
        }

        private zzb() {
        }

        public final String zzb() {
            return this.zzf;
        }

        public final List<zzd> zzc() {
            return this.zzg;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzhi.zza[i3 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zze", "zzf", "zzg", zzd.class});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzb> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzb.class) {
                            try {
                                zzmu = zzd;
                                if (zzmu == null) {
                                    zzmu = new zzlc.zza<>(zzc);
                                    zzd = zzmu;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    return zzmu;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzc extends zzlc<zzc, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzc zzc;
        private static volatile zzmu<zzc> zzd;
        private int zze;
        private zzll<zzd> zzf = zzlc.zzch();
        private zza zzg;

        public static final class zza extends zzlc.zzb<zzc, zza> implements zzmn {
            private zza() {
                super(zzc.zzc);
            }
        }

        static {
            zzc zzc2 = new zzc();
            zzc = zzc2;
            zzlc.zza(zzc.class, zzc2);
        }

        private zzc() {
        }

        public final zza zza() {
            zza zza2 = this.zzg;
            return zza2 == null ? zza.zzc() : zza2;
        }

        public final List<zzd> zzc() {
            return this.zzf;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzhi.zza[i3 - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zze", "zzf", zzd.class, "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzc> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzc.class) {
                            try {
                                zzmu = zzd;
                                if (zzmu == null) {
                                    zzmu = new zzlc.zza<>(zzc);
                                    zzd = zzmu;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    return zzmu;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    public static final class zzd extends zzlc<zzd, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzd zzc;
        private static volatile zzmu<zzd> zzd;
        private int zze;
        private int zzf;
        private zzll<zzd> zzg = zzlc.zzch();
        private String zzh = "";
        private String zzi = "";
        private boolean zzj;
        private double zzk;

        public static final class zza extends zzlc.zzb<zzd, zza> implements zzmn {
            private zza() {
                super(zzd.zzc);
            }
        }

        public enum zzb implements zzlh {
            UNKNOWN(0),
            STRING(1),
            NUMBER(2),
            BOOLEAN(3),
            STATEMENT(4);
            
            private final int zzg;

            private zzb(int i3) {
                this.zzg = i3;
            }

            public static zzlg zzb() {
                return zzho.zza;
            }

            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            public final int zza() {
                return this.zzg;
            }

            public static zzb zza(int i3) {
                if (i3 == 0) {
                    return UNKNOWN;
                }
                if (i3 == 1) {
                    return STRING;
                }
                if (i3 == 2) {
                    return NUMBER;
                }
                if (i3 == 3) {
                    return BOOLEAN;
                }
                if (i3 != 4) {
                    return null;
                }
                return STATEMENT;
            }
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzlc.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        public final double zza() {
            return this.zzk;
        }

        public final zzb zzb() {
            zzb zza2 = zzb.zza(this.zzf);
            return zza2 == null ? zzb.UNKNOWN : zza2;
        }

        public final String zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzi;
        }

        public final List<zzd> zzf() {
            return this.zzg;
        }

        public final boolean zzg() {
            return this.zzj;
        }

        public final boolean zzh() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 4) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzhi.zza[i3 - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006က\u0004", new Object[]{"zze", "zzf", zzb.zzb(), "zzg", zzd.class, "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzd> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzd.class) {
                            try {
                                zzmu = zzd;
                                if (zzmu == null) {
                                    zzmu = new zzlc.zza<>(zzc);
                                    zzd = zzmu;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    return zzmu;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
