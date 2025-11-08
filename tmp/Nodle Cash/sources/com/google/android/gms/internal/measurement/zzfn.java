package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzlc;
import java.util.List;
import kotlin.text.Typography;

public final class zzfn {

    public static final class zza extends zzlc<zza, C0059zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzmu<zza> zzd;
        private int zze;
        private int zzf;
        private zzll<zze> zzg = zzlc.zzch();
        private zzll<zzb> zzh = zzlc.zzch();
        private boolean zzi;
        private boolean zzj;

        /* renamed from: com.google.android.gms.internal.measurement.zzfn$zza$zza  reason: collision with other inner class name */
        public static final class C0059zza extends zzlc.zzb<zza, C0059zza> implements zzmn {
            public final int zza() {
                return ((zza) this.zza).zzb();
            }

            public final int zzb() {
                return ((zza) this.zza).zzc();
            }

            private C0059zza() {
                super(zza.zzc);
            }

            public final C0059zza zza(int i3, zzb.zza zza) {
                zzak();
                zza.zza((zza) this.zza, i3, (zzb) ((zzlc) zza.zzai()));
                return this;
            }

            public final zze zzb(int i3) {
                return ((zza) this.zza).zzb(i3);
            }

            public final C0059zza zza(int i3, zze.zza zza) {
                zzak();
                zza.zza((zza) this.zza, i3, (zze) ((zzlc) zza.zzai()));
                return this;
            }

            public final zzb zza(int i3) {
                return ((zza) this.zza).zza(i3);
            }
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzlc.zza(zza.class, zza);
        }

        private zza() {
        }

        public final int zza() {
            return this.zzf;
        }

        public final int zzb() {
            return this.zzh.size();
        }

        public final int zzc() {
            return this.zzg.size();
        }

        public final List<zzb> zze() {
            return this.zzh;
        }

        public final List<zze> zzf() {
            return this.zzg;
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }

        public final zzb zza(int i3) {
            return this.zzh.get(i3);
        }

        public final zze zzb(int i3) {
            return this.zzg.get(i3);
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfm.zza[i3 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0059zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zze", "zzf", "zzg", zze.class, "zzh", zzb.class, "zzi", "zzj"});
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

        public static /* synthetic */ void zza(zza zza, int i3, zzb zzb) {
            zzb.getClass();
            zzll<zzb> zzll = zza.zzh;
            if (!zzll.zzc()) {
                zza.zzh = zzlc.zza(zzll);
            }
            zza.zzh.set(i3, zzb);
        }

        public static /* synthetic */ void zza(zza zza, int i3, zze zze2) {
            zze2.getClass();
            zzll<zze> zzll = zza.zzg;
            if (!zzll.zzc()) {
                zza.zzg = zzlc.zza(zzll);
            }
            zza.zzg.set(i3, zze2);
        }
    }

    public static final class zzb extends zzlc<zzb, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzmu<zzb> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private zzll<zzc> zzh = zzlc.zzch();
        private boolean zzi;
        private zzd zzj;
        private boolean zzk;
        private boolean zzl;
        private boolean zzm;

        public static final class zza extends zzlc.zzb<zzb, zza> implements zzmn {
            public final int zza() {
                return ((zzb) this.zza).zza();
            }

            public final String zzb() {
                return ((zzb) this.zza).zzf();
            }

            private zza() {
                super(zzb.zzc);
            }

            public final zza zza(String str) {
                zzak();
                zzb.zza((zzb) this.zza, str);
                return this;
            }

            public final zza zza(int i3, zzc zzc) {
                zzak();
                zzb.zza((zzb) this.zza, i3, zzc);
                return this;
            }

            public final zzc zza(int i3) {
                return ((zzb) this.zza).zza(i3);
            }
        }

        static {
            zzb zzb = new zzb();
            zzc = zzb;
            zzlc.zza(zzb.class, zzb);
        }

        private zzb() {
        }

        public static zza zzc() {
            return (zza) zzc.zzcc();
        }

        public final int zza() {
            return this.zzh.size();
        }

        public final int zzb() {
            return this.zzf;
        }

        public final zzd zze() {
            zzd zzd2 = this.zzj;
            return zzd2 == null ? zzd.zzc() : zzd2;
        }

        public final String zzf() {
            return this.zzg;
        }

        public final List<zzc> zzg() {
            return this.zzh;
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public final boolean zzi() {
            return this.zzl;
        }

        public final boolean zzj() {
            return this.zzm;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 64) != 0;
        }

        public final zzc zza(int i3) {
            return this.zzh.get(i3);
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfm.zza[i3 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001င\u0000\u0002ဈ\u0001\u0003\u001b\u0004ဇ\u0002\u0005ဉ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", zzc.class, "zzi", "zzj", "zzk", "zzl", "zzm"});
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

        public static /* synthetic */ void zza(zzb zzb, String str) {
            str.getClass();
            zzb.zze |= 2;
            zzb.zzg = str;
        }

        public static /* synthetic */ void zza(zzb zzb, int i3, zzc zzc2) {
            zzc2.getClass();
            zzll<zzc> zzll = zzb.zzh;
            if (!zzll.zzc()) {
                zzb.zzh = zzlc.zza(zzll);
            }
            zzb.zzh.set(i3, zzc2);
        }
    }

    public static final class zzc extends zzlc<zzc, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzc zzc;
        private static volatile zzmu<zzc> zzd;
        private int zze;
        private zzf zzf;
        private zzd zzg;
        private boolean zzh;
        private String zzi = "";

        public static final class zza extends zzlc.zzb<zzc, zza> implements zzmn {
            public final zza zza(String str) {
                zzak();
                zzc.zza((zzc) this.zza, str);
                return this;
            }

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

        public static zzc zzb() {
            return zzc;
        }

        public final zzd zzc() {
            zzd zzd2 = this.zzg;
            return zzd2 == null ? zzd.zzc() : zzd2;
        }

        public final zzf zzd() {
            zzf zzf2 = this.zzf;
            return zzf2 == null ? zzf.zzd() : zzf2;
        }

        public final String zze() {
            return this.zzi;
        }

        public final boolean zzf() {
            return this.zzh;
        }

        public final boolean zzg() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 1) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfm.zza[i3 - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဇ\u0002\u0004ဈ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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

        public static /* synthetic */ void zza(zzc zzc2, String str) {
            str.getClass();
            zzc2.zze |= 8;
            zzc2.zzi = str;
        }
    }

    public static final class zze extends zzlc<zze, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzmu<zze> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private zzc zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;

        public static final class zza extends zzlc.zzb<zze, zza> implements zzmn {
            public final zza zza(String str) {
                zzak();
                zze.zza((zze) this.zza, str);
                return this;
            }

            private zza() {
                super(zze.zzc);
            }
        }

        static {
            zze zze2 = new zze();
            zzc = zze2;
            zzlc.zza(zze.class, zze2);
        }

        private zze() {
        }

        public static zza zzc() {
            return (zza) zzc.zzcc();
        }

        public final int zza() {
            return this.zzf;
        }

        public final zzc zzb() {
            zzc zzc2 = this.zzh;
            return zzc2 == null ? zzc.zzb() : zzc2;
        }

        public final String zze() {
            return this.zzg;
        }

        public final boolean zzf() {
            return this.zzi;
        }

        public final boolean zzg() {
            return this.zzj;
        }

        public final boolean zzh() {
            return this.zzk;
        }

        public final boolean zzi() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 32) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfm.zza[i3 - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zze> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zze.class) {
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

        public static /* synthetic */ void zza(zze zze2, String str) {
            str.getClass();
            zze2.zze |= 2;
            zze2.zzg = str;
        }
    }

    public static final class zzf extends zzlc<zzf, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzmu<zzf> zzd;
        private int zze;
        private int zzf;
        private String zzg = "";
        private boolean zzh;
        private zzll<String> zzi = zzlc.zzch();

        public static final class zza extends zzlc.zzb<zzf, zza> implements zzmn {
            private zza() {
                super(zzf.zzc);
            }
        }

        public enum zzb implements zzlh {
            UNKNOWN_MATCH_TYPE(0),
            REGEXP(1),
            BEGINS_WITH(2),
            ENDS_WITH(3),
            PARTIAL(4),
            EXACT(5),
            IN_LIST(6);
            
            private final int zzi;

            private zzb(int i3) {
                this.zzi = i3;
            }

            public static zzlg zzb() {
                return zzfv.zza;
            }

            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzi + " name=" + name() + Typography.greater;
            }

            public final int zza() {
                return this.zzi;
            }

            public static zzb zza(int i3) {
                switch (i3) {
                    case 0:
                        return UNKNOWN_MATCH_TYPE;
                    case 1:
                        return REGEXP;
                    case 2:
                        return BEGINS_WITH;
                    case 3:
                        return ENDS_WITH;
                    case 4:
                        return PARTIAL;
                    case 5:
                        return EXACT;
                    case 6:
                        return IN_LIST;
                    default:
                        return null;
                }
            }
        }

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzlc.zza(zzf.class, zzf2);
        }

        private zzf() {
        }

        public static zzf zzd() {
            return zzc;
        }

        public final int zza() {
            return this.zzi.size();
        }

        public final zzb zzb() {
            zzb zza2 = zzb.zza(this.zzf);
            return zza2 == null ? zzb.UNKNOWN_MATCH_TYPE : zza2;
        }

        public final String zze() {
            return this.zzg;
        }

        public final List<String> zzf() {
            return this.zzi;
        }

        public final boolean zzg() {
            return this.zzh;
        }

        public final boolean zzh() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 1) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfm.zza[i3 - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004\u001a", new Object[]{"zze", "zzf", zzb.zzb(), "zzg", "zzh", "zzi"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzf> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzf.class) {
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
        private boolean zzg;
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";

        public static final class zza extends zzlc.zzb<zzd, zza> implements zzmn {
            private zza() {
                super(zzd.zzc);
            }
        }

        public enum zzb implements zzlh {
            UNKNOWN_COMPARISON_TYPE(0),
            LESS_THAN(1),
            GREATER_THAN(2),
            EQUAL(3),
            BETWEEN(4);
            
            private final int zzg;

            private zzb(int i3) {
                this.zzg = i3;
            }

            public static zzlg zzb() {
                return zzfs.zza;
            }

            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            public final int zza() {
                return this.zzg;
            }

            public static zzb zza(int i3) {
                if (i3 == 0) {
                    return UNKNOWN_COMPARISON_TYPE;
                }
                if (i3 == 1) {
                    return LESS_THAN;
                }
                if (i3 == 2) {
                    return GREATER_THAN;
                }
                if (i3 == 3) {
                    return EQUAL;
                }
                if (i3 != 4) {
                    return null;
                }
                return BETWEEN;
            }
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzlc.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        public static zzd zzc() {
            return zzc;
        }

        public final zzb zza() {
            zzb zza2 = zzb.zza(this.zzf);
            return zza2 == null ? zzb.UNKNOWN_COMPARISON_TYPE : zza2;
        }

        public final String zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzj;
        }

        public final String zzf() {
            return this.zzi;
        }

        public final boolean zzg() {
            return this.zzg;
        }

        public final boolean zzh() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 8) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfm.zza[i3 - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004", new Object[]{"zze", "zzf", zzb.zzb(), "zzg", "zzh", "zzi", "zzj"});
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
