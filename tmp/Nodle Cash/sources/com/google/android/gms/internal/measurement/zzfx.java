package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzhg;
import com.google.android.gms.internal.measurement.zzlc;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;

public final class zzfx {

    public static final class zza extends zzlc<zza, C0060zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzmu<zza> zzd;
        private int zze;
        private zzll<zzb> zzf = zzlc.zzch();
        private zzll<zzc> zzg = zzlc.zzch();
        private zzll<zzf> zzh = zzlc.zzch();
        private boolean zzi;
        private zzll<zzb> zzj = zzlc.zzch();

        /* renamed from: com.google.android.gms.internal.measurement.zzfx$zza$zza  reason: collision with other inner class name */
        public static final class C0060zza extends zzlc.zzb<zza, C0060zza> implements zzmn {
            private C0060zza() {
                super(zza.zzc);
            }
        }

        public static final class zzb extends zzlc<zzb, C0061zza> implements zzmn {
            /* access modifiers changed from: private */
            public static final zzb zzc;
            private static volatile zzmu<zzb> zzd;
            private int zze;
            private int zzf;
            private int zzg;

            /* renamed from: com.google.android.gms.internal.measurement.zzfx$zza$zzb$zza  reason: collision with other inner class name */
            public static final class C0061zza extends zzlc.zzb<zzb, C0061zza> implements zzmn {
                private C0061zza() {
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

            public final zzd zzb() {
                zzd zza = zzd.zza(this.zzg);
                return zza == null ? zzd.CONSENT_STATUS_UNSPECIFIED : zza;
            }

            public final zze zzc() {
                zze zza = zze.zza(this.zzf);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            public final Object zza(int i3, Object obj, Object obj2) {
                switch (zzfw.zza[i3 - 1]) {
                    case 1:
                        return new zzb();
                    case 2:
                        return new C0061zza();
                    case 3:
                        return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zze", "zzf", zze.zzb(), "zzg", zzd.zzb()});
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

        public static final class zzc extends zzlc<zzc, C0062zza> implements zzmn {
            /* access modifiers changed from: private */
            public static final zzc zzc;
            private static volatile zzmu<zzc> zzd;
            private int zze;
            private int zzf;
            private int zzg;

            /* renamed from: com.google.android.gms.internal.measurement.zzfx$zza$zzc$zza  reason: collision with other inner class name */
            public static final class C0062zza extends zzlc.zzb<zzc, C0062zza> implements zzmn {
                private C0062zza() {
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

            public final zze zzb() {
                zze zza = zze.zza(this.zzg);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            public final zze zzc() {
                zze zza = zze.zza(this.zzf);
                return zza == null ? zze.CONSENT_TYPE_UNSPECIFIED : zza;
            }

            public final Object zza(int i3, Object obj, Object obj2) {
                switch (zzfw.zza[i3 - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new C0062zza();
                    case 3:
                        return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zze", "zzf", zze.zzb(), "zzg", zze.zzb()});
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

        public enum zzd implements zzlh {
            CONSENT_STATUS_UNSPECIFIED(0),
            GRANTED(1),
            DENIED(2);
            
            private final int zze;

            private zzd(int i3) {
                this.zze = i3;
            }

            public static zzlg zzb() {
                return zzgb.zza;
            }

            public final String toString() {
                return "<" + zzd.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zze + " name=" + name() + Typography.greater;
            }

            public final int zza() {
                return this.zze;
            }

            public static zzd zza(int i3) {
                if (i3 == 0) {
                    return CONSENT_STATUS_UNSPECIFIED;
                }
                if (i3 == 1) {
                    return GRANTED;
                }
                if (i3 != 2) {
                    return null;
                }
                return DENIED;
            }
        }

        public enum zze implements zzlh {
            CONSENT_TYPE_UNSPECIFIED(0),
            AD_STORAGE(1),
            ANALYTICS_STORAGE(2),
            AD_USER_DATA(3),
            AD_PERSONALIZATION(4);
            
            private final int zzg;

            private zze(int i3) {
                this.zzg = i3;
            }

            public static zzlg zzb() {
                return zzgc.zza;
            }

            public final String toString() {
                return "<" + zze.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + Typography.greater;
            }

            public final int zza() {
                return this.zzg;
            }

            public static zze zza(int i3) {
                if (i3 == 0) {
                    return CONSENT_TYPE_UNSPECIFIED;
                }
                if (i3 == 1) {
                    return AD_STORAGE;
                }
                if (i3 == 2) {
                    return ANALYTICS_STORAGE;
                }
                if (i3 == 3) {
                    return AD_USER_DATA;
                }
                if (i3 != 4) {
                    return null;
                }
                return AD_PERSONALIZATION;
            }
        }

        public static final class zzf extends zzlc<zzf, C0063zza> implements zzmn {
            /* access modifiers changed from: private */
            public static final zzf zzc;
            private static volatile zzmu<zzf> zzd;
            private int zze;
            private String zzf = "";
            private String zzg = "";

            /* renamed from: com.google.android.gms.internal.measurement.zzfx$zza$zzf$zza  reason: collision with other inner class name */
            public static final class C0063zza extends zzlc.zzb<zzf, C0063zza> implements zzmn {
                private C0063zza() {
                    super(zzf.zzc);
                }
            }

            static {
                zzf zzf2 = new zzf();
                zzc = zzf2;
                zzlc.zza(zzf.class, zzf2);
            }

            private zzf() {
            }

            public final String zzb() {
                return this.zzf;
            }

            public final Object zza(int i3, Object obj, Object obj2) {
                switch (zzfw.zza[i3 - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new C0063zza();
                    case 3:
                        return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

        static {
            zza zza = new zza();
            zzc = zza;
            zzlc.zza(zza.class, zza);
        }

        private zza() {
        }

        public static zza zzb() {
            return zzc;
        }

        public final List<zzf> zzc() {
            return this.zzh;
        }

        public final List<zzb> zzd() {
            return this.zzf;
        }

        public final List<zzc> zze() {
            return this.zzg;
        }

        public final List<zzb> zzf() {
            return this.zzj;
        }

        public final boolean zzg() {
            return this.zzi;
        }

        public final boolean zzh() {
            return (this.zze & 1) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0060zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004ဇ\u0000\u0005\u001b", new Object[]{"zze", "zzf", zzb.class, "zzg", zzc.class, "zzh", zzf.class, "zzi", "zzj", zzb.class});
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
        private zzll<zzg> zzg = zzlc.zzch();
        private boolean zzh;

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

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဇ\u0001", new Object[]{"zze", "zzf", "zzg", zzg.class, "zzh"});
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
        private String zzf = "";
        private boolean zzg;
        private boolean zzh;
        private int zzi;

        public static final class zza extends zzlc.zzb<zzc, zza> implements zzmn {
            public final int zza() {
                return ((zzc) this.zza).zza();
            }

            public final String zzb() {
                return ((zzc) this.zza).zzc();
            }

            public final boolean zzc() {
                return ((zzc) this.zza).zzd();
            }

            public final boolean zzd() {
                return ((zzc) this.zza).zze();
            }

            public final boolean zze() {
                return ((zzc) this.zza).zzf();
            }

            public final boolean zzf() {
                return ((zzc) this.zza).zzg();
            }

            public final boolean zzg() {
                return ((zzc) this.zza).zzh();
            }

            private zza() {
                super(zzc.zzc);
            }

            public final zza zza(String str) {
                zzak();
                zzc.zza((zzc) this.zza, str);
                return this;
            }
        }

        static {
            zzc zzc2 = new zzc();
            zzc = zzc2;
            zzlc.zza(zzc.class, zzc2);
        }

        private zzc() {
        }

        public final int zza() {
            return this.zzi;
        }

        public final String zzc() {
            return this.zzf;
        }

        public final boolean zzd() {
            return this.zzg;
        }

        public final boolean zze() {
            return this.zzh;
        }

        public final boolean zzf() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzg() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 8) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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
            zzc2.zze |= 1;
            zzc2.zzf = str;
        }
    }

    public static final class zzd extends zzlc<zzd, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzd zzc;
        private static volatile zzmu<zzd> zzd;
        private int zze;
        private long zzf;
        private String zzg = "";
        private int zzh;
        private zzll<zzh> zzi = zzlc.zzch();
        private zzll<zzc> zzj = zzlc.zzch();
        /* access modifiers changed from: private */
        public zzll<zzfn.zza> zzk = zzlc.zzch();
        private String zzl = "";
        private boolean zzm;
        private zzll<zzhg.zzc> zzn = zzlc.zzch();
        private zzll<zzb> zzo = zzlc.zzch();
        private String zzp = "";
        private String zzq = "";
        private zza zzr;
        private zzf zzs;
        private zzi zzt;
        private zzg zzu;
        private zze zzv;

        public static final class zza extends zzlc.zzb<zzd, zza> implements zzmn {
            public final int zza() {
                return ((zzd) this.zza).zzb();
            }

            public final zza zzb() {
                zzak();
                ((zzd) this.zza).zzk = zzlc.zzch();
                return this;
            }

            public final String zzc() {
                return ((zzd) this.zza).zzj();
            }

            public final List<zzfn.zza> zzd() {
                return Collections.unmodifiableList(((zzd) this.zza).zzk());
            }

            public final List<zzb> zze() {
                return Collections.unmodifiableList(((zzd) this.zza).zzl());
            }

            private zza() {
                super(zzd.zzc);
            }

            public final zzc zza(int i3) {
                return ((zzd) this.zza).zza(i3);
            }

            public final zza zza(int i3, zzc.zza zza) {
                zzak();
                zzd.zza((zzd) this.zza, i3, (zzc) ((zzlc) zza.zzai()));
                return this;
            }
        }

        static {
            zzd zzd2 = new zzd();
            zzc = zzd2;
            zzlc.zza(zzd.class, zzd2);
        }

        private zzd() {
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        public static zzd zzg() {
            return zzc;
        }

        public final int zza() {
            return this.zzn.size();
        }

        public final int zzb() {
            return this.zzj.size();
        }

        public final long zzc() {
            return this.zzf;
        }

        public final zza zzd() {
            zza zza2 = this.zzr;
            return zza2 == null ? zza.zzb() : zza2;
        }

        public final zzi zzh() {
            zzi zzi2 = this.zzt;
            return zzi2 == null ? zzi.zzc() : zzi2;
        }

        public final String zzi() {
            return this.zzg;
        }

        public final String zzj() {
            return this.zzp;
        }

        public final List<zzfn.zza> zzk() {
            return this.zzk;
        }

        public final List<zzb> zzl() {
            return this.zzo;
        }

        public final List<zzhg.zzc> zzm() {
            return this.zzn;
        }

        public final List<zzh> zzn() {
            return this.zzi;
        }

        public final boolean zzo() {
            return this.zzm;
        }

        public final boolean zzp() {
            return (this.zze & 128) != 0;
        }

        public final boolean zzq() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzr() {
            return (this.zze & 512) != 0;
        }

        public final boolean zzs() {
            return (this.zze & 1) != 0;
        }

        public final zzc zza(int i3) {
            return this.zzj.get(i3);
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0011\u0000\u0001\u0001\u0013\u0011\u0000\u0005\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b\n\u001b\u000bဈ\u0005\u000eဈ\u0006\u000fဉ\u0007\u0010ဉ\b\u0011ဉ\t\u0012ဉ\n\u0013ဉ\u000b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzh.class, "zzj", zzc.class, "zzk", zzfn.zza.class, "zzl", "zzm", "zzn", zzhg.zzc.class, "zzo", zzb.class, "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv"});
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

        public static /* synthetic */ void zza(zzd zzd2, int i3, zzc zzc2) {
            zzc2.getClass();
            zzll<zzc> zzll = zzd2.zzj;
            if (!zzll.zzc()) {
                zzd2.zzj = zzlc.zza(zzll);
            }
            zzd2.zzj.set(i3, zzc2);
        }
    }

    public static final class zze extends zzlc<zze, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzmu<zze> zzd;
        private zzll<String> zze = zzlc.zzch();

        public static final class zza extends zzlc.zzb<zze, zza> implements zzmn {
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

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zze"});
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
    }

    public static final class zzf extends zzlc<zzf, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzmu<zzf> zzd;
        private int zze;
        private int zzf = 14;
        private int zzg = 11;
        private int zzh = 60;

        public static final class zza extends zzlc.zzb<zzf, zza> implements zzmn {
            private zza() {
                super(zzf.zzc);
            }
        }

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzlc.zza(zzf.class, zzf2);
        }

        private zzf() {
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
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

    public static final class zzg extends zzlc<zzg, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzg zzc;
        private static volatile zzmu<zzg> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";

        public static final class zza extends zzlc.zzb<zzg, zza> implements zzmn {
            private zza() {
                super(zzg.zzc);
            }
        }

        static {
            zzg zzg2 = new zzg();
            zzc = zzg2;
            zzlc.zza(zzg.class, zzg2);
        }

        private zzg() {
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzg> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzg.class) {
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

    public static final class zzh extends zzlc<zzh, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzh zzc;
        private static volatile zzmu<zzh> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";

        public static final class zza extends zzlc.zzb<zzh, zza> implements zzmn {
            private zza() {
                super(zzh.zzc);
            }
        }

        static {
            zzh zzh = new zzh();
            zzc = zzh;
            zzlc.zza(zzh.class, zzh);
        }

        private zzh() {
        }

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzh> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzh.class) {
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

    public static final class zzi extends zzlc<zzi, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzi zzc;
        private static volatile zzmu<zzi> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private int zzi;

        public static final class zza extends zzlc.zzb<zzi, zza> implements zzmn {
            private zza() {
                super(zzi.zzc);
            }
        }

        static {
            zzi zzi2 = new zzi();
            zzc = zzi2;
            zzlc.zza(zzi.class, zzi2);
        }

        private zzi() {
        }

        public static zzi zzc() {
            return zzc;
        }

        public final int zza() {
            return this.zzi;
        }

        public final String zzd() {
            return this.zzg;
        }

        public final String zze() {
            return this.zzf;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzfw.zza[i3 - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzi> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzi.class) {
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
