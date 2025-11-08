package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzlc;
import java.util.Collections;
import java.util.List;
import kotlin.text.Typography;

public final class zzgn {

    public static final class zza extends zzlc<zza, C0064zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zza zzc;
        private static volatile zzmu<zza> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private long zzi;
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private long zzm;

        /* renamed from: com.google.android.gms.internal.measurement.zzgn$zza$zza  reason: collision with other inner class name */
        public static final class C0064zza extends zzlc.zzb<zza, C0064zza> implements zzmn {
            public final long zza() {
                return ((zza) this.zza).zza();
            }

            public final long zzb() {
                return ((zza) this.zza).zzb();
            }

            public final C0064zza zzc() {
                zzak();
                zza.zza((zza) this.zza);
                return this;
            }

            public final C0064zza zzd() {
                zzak();
                zza.zzb((zza) this.zza);
                return this;
            }

            public final C0064zza zze() {
                zzak();
                zza.zzc((zza) this.zza);
                return this;
            }

            public final C0064zza zzf() {
                zzak();
                zza.zzd((zza) this.zza);
                return this;
            }

            public final C0064zza zzg() {
                zzak();
                zza.zze((zza) this.zza);
                return this;
            }

            public final C0064zza zzh() {
                zzak();
                zza.zzf((zza) this.zza);
                return this;
            }

            private C0064zza() {
                super(zza.zzc);
            }

            public final C0064zza zza(String str) {
                zzak();
                zza.zza((zza) this.zza, str);
                return this;
            }

            public final C0064zza zzb(String str) {
                zzak();
                zza.zzb((zza) this.zza, str);
                return this;
            }

            public final C0064zza zzc(String str) {
                zzak();
                zza.zzc((zza) this.zza, str);
                return this;
            }

            public final C0064zza zzd(String str) {
                zzak();
                zza.zzd((zza) this.zza, str);
                return this;
            }

            public final C0064zza zze(String str) {
                zzak();
                zza.zze((zza) this.zza, str);
                return this;
            }

            public final C0064zza zzf(String str) {
                zzak();
                zza.zzf((zza) this.zza, str);
                return this;
            }

            public final C0064zza zza(long j2) {
                zzak();
                zza.zza((zza) this.zza, j2);
                return this;
            }

            public final C0064zza zzb(long j2) {
                zzak();
                zza.zzb((zza) this.zza, j2);
                return this;
            }
        }

        static {
            zza zza = new zza();
            zzc = zza;
            zzlc.zza(zza.class, zza);
        }

        private zza() {
        }

        public static C0064zza zzc() {
            return (C0064zza) zzc.zzcc();
        }

        public static zza zze() {
            return zzc;
        }

        public final long zza() {
            return this.zzi;
        }

        public final long zzb() {
            return this.zzm;
        }

        public final String zzf() {
            return this.zzh;
        }

        public final String zzg() {
            return this.zzg;
        }

        public final String zzh() {
            return this.zzf;
        }

        public final String zzi() {
            return this.zzl;
        }

        public final String zzj() {
            return this.zzk;
        }

        public final String zzk() {
            return this.zzj;
        }

        public final boolean zzl() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzn() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzo() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzp() {
            return (this.zze & 128) != 0;
        }

        public final boolean zzq() {
            return (this.zze & 64) != 0;
        }

        public final boolean zzr() {
            return (this.zze & 32) != 0;
        }

        public final boolean zzs() {
            return (this.zze & 16) != 0;
        }

        public static /* synthetic */ void zzb(zza zza) {
            zza.zze &= -3;
            zza.zzg = zzc.zzg;
        }

        public static /* synthetic */ void zzc(zza zza) {
            zza.zze &= -2;
            zza.zzf = zzc.zzf;
        }

        public static /* synthetic */ void zzd(zza zza) {
            zza.zze &= -65;
            zza.zzl = zzc.zzl;
        }

        public static /* synthetic */ void zze(zza zza) {
            zza.zze &= -33;
            zza.zzk = zzc.zzk;
        }

        public static /* synthetic */ void zzf(zza zza) {
            zza.zze &= -17;
            zza.zzj = zzc.zzj;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0064zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဂ\u0007", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
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

        public static /* synthetic */ void zzb(zza zza, String str) {
            str.getClass();
            zza.zze |= 2;
            zza.zzg = str;
        }

        public static /* synthetic */ void zzc(zza zza, String str) {
            str.getClass();
            zza.zze |= 1;
            zza.zzf = str;
        }

        public static /* synthetic */ void zzd(zza zza, String str) {
            str.getClass();
            zza.zze |= 64;
            zza.zzl = str;
        }

        public static /* synthetic */ void zze(zza zza, String str) {
            str.getClass();
            zza.zze |= 32;
            zza.zzk = str;
        }

        public static /* synthetic */ void zzf(zza zza, String str) {
            str.getClass();
            zza.zze |= 16;
            zza.zzj = str;
        }

        public static /* synthetic */ void zzb(zza zza, long j2) {
            zza.zze |= 128;
            zza.zzm = j2;
        }

        public static /* synthetic */ void zza(zza zza) {
            zza.zze &= -5;
            zza.zzh = zzc.zzh;
        }

        public static /* synthetic */ void zza(zza zza, String str) {
            str.getClass();
            zza.zze |= 4;
            zza.zzh = str;
        }

        public static /* synthetic */ void zza(zza zza, long j2) {
            zza.zze |= 8;
            zza.zzi = j2;
        }
    }

    public static final class zzb extends zzlc<zzb, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzb zzc;
        private static volatile zzmu<zzb> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";

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

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
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
        private boolean zzf;
        private boolean zzg;
        private boolean zzh;
        private boolean zzi;
        private boolean zzj;
        private boolean zzk;
        private boolean zzl;

        public static final class zza extends zzlc.zzb<zzc, zza> implements zzmn {
            public final zza zza(boolean z2) {
                zzak();
                zzc.zza((zzc) this.zza, z2);
                return this;
            }

            public final zza zzb(boolean z2) {
                zzak();
                zzc.zzb((zzc) this.zza, z2);
                return this;
            }

            public final zza zzc(boolean z2) {
                zzak();
                zzc.zzc((zzc) this.zza, z2);
                return this;
            }

            public final zza zzd(boolean z2) {
                zzak();
                zzc.zzd((zzc) this.zza, z2);
                return this;
            }

            public final zza zze(boolean z2) {
                zzak();
                zzc.zze((zzc) this.zza, z2);
                return this;
            }

            public final zza zzf(boolean z2) {
                zzak();
                zzc.zzf((zzc) this.zza, z2);
                return this;
            }

            public final zza zzg(boolean z2) {
                zzak();
                zzc.zzg((zzc) this.zza, z2);
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

        public static zza zza() {
            return (zza) zzc.zzcc();
        }

        public static zzc zzc() {
            return zzc;
        }

        public static /* synthetic */ void zzd(zzc zzc2, boolean z2) {
            zzc2.zze |= 64;
            zzc2.zzl = z2;
        }

        public static /* synthetic */ void zze(zzc zzc2, boolean z2) {
            zzc2.zze |= 2;
            zzc2.zzg = z2;
        }

        public static /* synthetic */ void zzf(zzc zzc2, boolean z2) {
            zzc2.zze |= 4;
            zzc2.zzh = z2;
        }

        public static /* synthetic */ void zzg(zzc zzc2, boolean z2) {
            zzc2.zze |= 8;
            zzc2.zzi = z2;
        }

        public final boolean zzh() {
            return this.zzg;
        }

        public final boolean zzi() {
            return this.zzh;
        }

        public final boolean zzj() {
            return this.zzi;
        }

        public static /* synthetic */ void zzb(zzc zzc2, boolean z2) {
            zzc2.zze |= 16;
            zzc2.zzj = z2;
        }

        public static /* synthetic */ void zzc(zzc zzc2, boolean z2) {
            zzc2.zze |= 1;
            zzc2.zzf = z2;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
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

        public final boolean zzd() {
            return this.zzk;
        }

        public final boolean zze() {
            return this.zzj;
        }

        public final boolean zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return this.zzl;
        }

        public static /* synthetic */ void zza(zzc zzc2, boolean z2) {
            zzc2.zze |= 32;
            zzc2.zzk = z2;
        }
    }

    public static final class zzd extends zzlc<zzd, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzd zzc;
        private static volatile zzmu<zzd> zzd;
        private int zze;
        private int zzf;
        private zzm zzg;
        private zzm zzh;
        private boolean zzi;

        public static final class zza extends zzlc.zzb<zzd, zza> implements zzmn {
            public final zza zza(int i3) {
                zzak();
                zzd.zza((zzd) this.zza, i3);
                return this;
            }

            private zza() {
                super(zzd.zzc);
            }

            public final zza zza(zzm.zza zza) {
                zzak();
                zzd.zza((zzd) this.zza, (zzm) ((zzlc) zza.zzai()));
                return this;
            }

            public final zza zza(boolean z2) {
                zzak();
                zzd.zza((zzd) this.zza, z2);
                return this;
            }

            public final zza zza(zzm zzm) {
                zzak();
                zzd.zzb((zzd) this.zza, zzm);
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

        public static zza zzb() {
            return (zza) zzc.zzcc();
        }

        public final int zza() {
            return this.zzf;
        }

        public final zzm zzd() {
            zzm zzm = this.zzg;
            return zzm == null ? zzm.zzg() : zzm;
        }

        public final zzm zze() {
            zzm zzm = this.zzh;
            return zzm == null ? zzm.zzg() : zzm;
        }

        public final boolean zzf() {
            return this.zzi;
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzi() {
            return (this.zze & 4) != 0;
        }

        public static /* synthetic */ void zzb(zzd zzd2, zzm zzm) {
            zzm.getClass();
            zzd2.zzh = zzm;
            zzd2.zze |= 4;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
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

        public static /* synthetic */ void zza(zzd zzd2, int i3) {
            zzd2.zze |= 1;
            zzd2.zzf = i3;
        }

        public static /* synthetic */ void zza(zzd zzd2, zzm zzm) {
            zzm.getClass();
            zzd2.zzg = zzm;
            zzd2.zze |= 2;
        }

        public static /* synthetic */ void zza(zzd zzd2, boolean z2) {
            zzd2.zze |= 8;
            zzd2.zzi = z2;
        }
    }

    public static final class zze extends zzlc<zze, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zze zzc;
        private static volatile zzmu<zze> zzd;
        private int zze;
        private int zzf;
        private long zzg;

        public static final class zza extends zzlc.zzb<zze, zza> implements zzmn {
            public final zza zza(long j2) {
                zzak();
                zze.zza((zze) this.zza, j2);
                return this;
            }

            private zza() {
                super(zze.zzc);
            }

            public final zza zza(int i3) {
                zzak();
                zze.zza((zze) this.zza, i3);
                return this;
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

        public final long zzb() {
            return this.zzg;
        }

        public final boolean zze() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzf() {
            return (this.zze & 1) != 0;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

        public static /* synthetic */ void zza(zze zze2, long j2) {
            zze2.zze |= 2;
            zze2.zzg = j2;
        }

        public static /* synthetic */ void zza(zze zze2, int i3) {
            zze2.zze |= 1;
            zze2.zzf = i3;
        }
    }

    public static final class zzf extends zzlc<zzf, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzf zzc;
        private static volatile zzmu<zzf> zzd;
        private int zze;
        /* access modifiers changed from: private */
        public zzll<zzh> zzf = zzlc.zzch();
        private String zzg = "";
        private long zzh;
        private long zzi;
        private int zzj;

        public static final class zza extends zzlc.zzb<zzf, zza> implements zzmn {
            public final int zza() {
                return ((zzf) this.zza).zzb();
            }

            public final long zzb() {
                return ((zzf) this.zza).zzc();
            }

            public final long zzc() {
                return ((zzf) this.zza).zzd();
            }

            public final zza zzd() {
                zzak();
                ((zzf) this.zza).zzf = zzlc.zzch();
                return this;
            }

            public final String zze() {
                return ((zzf) this.zza).zzg();
            }

            public final List<zzh> zzf() {
                return Collections.unmodifiableList(((zzf) this.zza).zzh());
            }

            public final boolean zzg() {
                return ((zzf) this.zza).zzk();
            }

            private zza() {
                super(zzf.zzc);
            }

            public final zza zza(Iterable<? extends zzh> iterable) {
                zzak();
                zzf.zza((zzf) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzb(long j2) {
                zzak();
                zzf.zzb((zzf) this.zza, j2);
                return this;
            }

            public final zza zza(zzh.zza zza) {
                zzak();
                zzf.zza((zzf) this.zza, (zzh) ((zzlc) zza.zzai()));
                return this;
            }

            public final zzh zzb(int i3) {
                return ((zzf) this.zza).zza(i3);
            }

            public final zza zza(zzh zzh) {
                zzak();
                zzf.zza((zzf) this.zza, zzh);
                return this;
            }

            public final zza zza(int i3) {
                zzak();
                zzf.zza((zzf) this.zza, i3);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzf.zza((zzf) this.zza, str);
                return this;
            }

            public final zza zza(int i3, zzh.zza zza) {
                zzak();
                zzf.zza((zzf) this.zza, i3, (zzh) ((zzlc) zza.zzai()));
                return this;
            }

            public final zza zza(int i3, zzh zzh) {
                zzak();
                zzf.zza((zzf) this.zza, i3, zzh);
                return this;
            }

            public final zza zza(long j2) {
                zzak();
                zzf.zza((zzf) this.zza, j2);
                return this;
            }
        }

        static {
            zzf zzf2 = new zzf();
            zzc = zzf2;
            zzlc.zza(zzf.class, zzf2);
        }

        private zzf() {
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        private final void zzl() {
            zzll<zzh> zzll = this.zzf;
            if (!zzll.zzc()) {
                this.zzf = zzlc.zza(zzll);
            }
        }

        public final int zza() {
            return this.zzj;
        }

        public final int zzb() {
            return this.zzf.size();
        }

        public final long zzc() {
            return this.zzi;
        }

        public final long zzd() {
            return this.zzh;
        }

        public final String zzg() {
            return this.zzg;
        }

        public final List<zzh> zzh() {
            return this.zzf;
        }

        public final boolean zzi() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 2) != 0;
        }

        public static /* synthetic */ void zzb(zzf zzf2, long j2) {
            zzf2.zze |= 2;
            zzf2.zzh = j2;
        }

        public final zzh zza(int i3) {
            return this.zzf.get(i3);
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zze", "zzf", zzh.class, "zzg", "zzh", "zzi", "zzj"});
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

        public static /* synthetic */ void zza(zzf zzf2, Iterable iterable) {
            zzf2.zzl();
            zzji.zza(iterable, zzf2.zzf);
        }

        public static /* synthetic */ void zza(zzf zzf2, zzh zzh2) {
            zzh2.getClass();
            zzf2.zzl();
            zzf2.zzf.add(zzh2);
        }

        public static /* synthetic */ void zza(zzf zzf2, int i3) {
            zzf2.zzl();
            zzf2.zzf.remove(i3);
        }

        public static /* synthetic */ void zza(zzf zzf2, String str) {
            str.getClass();
            zzf2.zze |= 1;
            zzf2.zzg = str;
        }

        public static /* synthetic */ void zza(zzf zzf2, int i3, zzh zzh2) {
            zzh2.getClass();
            zzf2.zzl();
            zzf2.zzf.set(i3, zzh2);
        }

        public static /* synthetic */ void zza(zzf zzf2, long j2) {
            zzf2.zze |= 4;
            zzf2.zzi = j2;
        }
    }

    public static final class zzg extends zzlc<zzg, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzg zzc;
        private static volatile zzmu<zzg> zzd;
        private int zze;
        private String zzf = "";
        private long zzg;

        public static final class zza extends zzlc.zzb<zzg, zza> implements zzmn {
            public final zza zza(long j2) {
                zzak();
                zzg.zza((zzg) this.zza, j2);
                return this;
            }

            private zza() {
                super(zzg.zzc);
            }

            public final zza zza(String str) {
                zzak();
                zzg.zza((zzg) this.zza, str);
                return this;
            }
        }

        static {
            zzg zzg2 = new zzg();
            zzc = zzg2;
            zzlc.zza(zzg.class, zzg2);
        }

        private zzg() {
        }

        public static zza zza() {
            return (zza) zzc.zzcc();
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
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

        public static /* synthetic */ void zza(zzg zzg2, long j2) {
            zzg2.zze |= 2;
            zzg2.zzg = j2;
        }

        public static /* synthetic */ void zza(zzg zzg2, String str) {
            str.getClass();
            zzg2.zze |= 1;
            zzg2.zzf = str;
        }
    }

    public static final class zzh extends zzlc<zzh, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzh zzc;
        private static volatile zzmu<zzh> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private long zzh;
        private float zzi;
        private double zzj;
        /* access modifiers changed from: private */
        public zzll<zzh> zzk = zzlc.zzch();

        public static final class zza extends zzlc.zzb<zzh, zza> implements zzmn {
            public final int zza() {
                return ((zzh) this.zza).zzc();
            }

            public final zza zzb() {
                zzak();
                zzh.zza((zzh) this.zza);
                return this;
            }

            public final zza zzc() {
                zzak();
                zzh.zzb((zzh) this.zza);
                return this;
            }

            public final zza zzd() {
                zzak();
                ((zzh) this.zza).zzk = zzlc.zzch();
                return this;
            }

            public final zza zze() {
                zzak();
                zzh.zzd((zzh) this.zza);
                return this;
            }

            public final String zzf() {
                return ((zzh) this.zza).zzg();
            }

            public final String zzg() {
                return ((zzh) this.zza).zzh();
            }

            private zza() {
                super(zzh.zzc);
            }

            public final zza zza(Iterable<? extends zzh> iterable) {
                zzak();
                zzh.zza((zzh) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                zzh.zzb((zzh) this.zza, str);
                return this;
            }

            public final zza zza(zza zza) {
                zzak();
                zzh.zza((zzh) this.zza, (zzh) ((zzlc) zza.zzai()));
                return this;
            }

            public final zza zza(double d2) {
                zzak();
                zzh.zza((zzh) this.zza, d2);
                return this;
            }

            public final zza zza(long j2) {
                zzak();
                zzh.zza((zzh) this.zza, j2);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzh.zza((zzh) this.zza, str);
                return this;
            }
        }

        static {
            zzh zzh2 = new zzh();
            zzc = zzh2;
            zzlc.zza(zzh.class, zzh2);
        }

        private zzh() {
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        private final void zzo() {
            zzll<zzh> zzll = this.zzk;
            if (!zzll.zzc()) {
                this.zzk = zzlc.zza(zzll);
            }
        }

        public final double zza() {
            return this.zzj;
        }

        public final float zzb() {
            return this.zzi;
        }

        public final int zzc() {
            return this.zzk.size();
        }

        public final long zzd() {
            return this.zzh;
        }

        public final String zzg() {
            return this.zzf;
        }

        public final String zzh() {
            return this.zzg;
        }

        public final List<zzh> zzi() {
            return this.zzk;
        }

        public final boolean zzj() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzn() {
            return (this.zze & 2) != 0;
        }

        public static /* synthetic */ void zzb(zzh zzh2) {
            zzh2.zze &= -5;
            zzh2.zzh = 0;
        }

        public static /* synthetic */ void zzd(zzh zzh2) {
            zzh2.zze &= -3;
            zzh2.zzg = zzc.zzg;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzh.class});
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

        public static /* synthetic */ void zzb(zzh zzh2, String str) {
            str.getClass();
            zzh2.zze |= 2;
            zzh2.zzg = str;
        }

        public static /* synthetic */ void zza(zzh zzh2, Iterable iterable) {
            zzh2.zzo();
            zzji.zza(iterable, zzh2.zzk);
        }

        public static /* synthetic */ void zza(zzh zzh2, zzh zzh3) {
            zzh3.getClass();
            zzh2.zzo();
            zzh2.zzk.add(zzh3);
        }

        public static /* synthetic */ void zza(zzh zzh2) {
            zzh2.zze &= -17;
            zzh2.zzj = 0.0d;
        }

        public static /* synthetic */ void zza(zzh zzh2, double d2) {
            zzh2.zze |= 16;
            zzh2.zzj = d2;
        }

        public static /* synthetic */ void zza(zzh zzh2, long j2) {
            zzh2.zze |= 4;
            zzh2.zzh = j2;
        }

        public static /* synthetic */ void zza(zzh zzh2, String str) {
            str.getClass();
            zzh2.zze |= 1;
            zzh2.zzf = str;
        }
    }

    public static final class zzi extends zzlc<zzi, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzi zzc;
        private static volatile zzmu<zzi> zzd;
        private int zze;
        private String zzf = "";
        private String zzg = "";
        private zzb zzh;

        public static final class zza extends zzlc.zzb<zzi, zza> implements zzmn {
            private zza() {
                super(zzi.zzc);
            }
        }

        static {
            zzi zzi = new zzi();
            zzc = zzi;
            zzlc.zza(zzi.class, zzi);
        }

        private zzi() {
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
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

    public static final class zzj extends zzlc<zzj, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzj zzc;
        private static volatile zzmu<zzj> zzd;
        private int zze;
        /* access modifiers changed from: private */
        public zzll<zzk> zzf = zzlc.zzch();
        private String zzg = "";
        private String zzh = "";
        private int zzi;

        public static final class zza extends zzlc.zzb<zzj, zza> implements zzmn {
            public final int zza() {
                return ((zzj) this.zza).zza();
            }

            public final zza zzb() {
                zzak();
                ((zzj) this.zza).zzf = zzlc.zzch();
                return this;
            }

            public final String zzc() {
                return ((zzj) this.zza).zzd();
            }

            public final List<zzk> zzd() {
                return Collections.unmodifiableList(((zzj) this.zza).zzf());
            }

            private zza() {
                super(zzj.zzc);
            }

            public final zza zza(Iterable<? extends zzk> iterable) {
                zzak();
                zzj.zza((zzj) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                zzj.zzb((zzj) this.zza, str);
                return this;
            }

            public final zza zza(zzk.zza zza) {
                zzak();
                zzj.zza((zzj) this.zza, (zzk) ((zzlc) zza.zzai()));
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzj.zza((zzj) this.zza, str);
                return this;
            }

            public final zzk zza(int i3) {
                return ((zzj) this.zza).zza(0);
            }
        }

        public enum zzb implements zzlh {
            SDK(0),
            SGTM(1);
            
            private final int zzd;

            private zzb(int i3) {
                this.zzd = i3;
            }

            public static zzlg zzb() {
                return zzha.zza;
            }

            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + Typography.greater;
            }

            public final int zza() {
                return this.zzd;
            }

            public static zzb zza(int i3) {
                if (i3 == 0) {
                    return SDK;
                }
                if (i3 != 1) {
                    return null;
                }
                return SGTM;
            }
        }

        static {
            zzj zzj = new zzj();
            zzc = zzj;
            zzlc.zza(zzj.class, zzj);
        }

        private zzj() {
        }

        public static zza zzb() {
            return (zza) zzc.zzcc();
        }

        private final void zzi() {
            zzll<zzk> zzll = this.zzf;
            if (!zzll.zzc()) {
                this.zzf = zzlc.zza(zzll);
            }
        }

        public final int zza() {
            return this.zzf.size();
        }

        public final String zzd() {
            return this.zzg;
        }

        public final String zze() {
            return this.zzh;
        }

        public final List<zzk> zzf() {
            return this.zzf;
        }

        public final boolean zzg() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzh() {
            return (this.zze & 2) != 0;
        }

        public static zza zza(zzj zzj) {
            return (zza) zzc.zza(zzj);
        }

        public static /* synthetic */ void zzb(zzj zzj, String str) {
            str.getClass();
            zzj.zze |= 2;
            zzj.zzh = str;
        }

        public final zzk zza(int i3) {
            return this.zzf.get(0);
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0004\u0000\u0001\u0001\t\u0004\u0000\u0001\u0000\u0001\u001b\u0007ဈ\u0000\bဈ\u0001\t᠌\u0002", new Object[]{"zze", "zzf", zzk.class, "zzg", "zzh", "zzi", zzb.zzb()});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzj> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzj.class) {
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

        public static /* synthetic */ void zza(zzj zzj, Iterable iterable) {
            zzj.zzi();
            zzji.zza(iterable, zzj.zzf);
        }

        public static /* synthetic */ void zza(zzj zzj, zzk zzk) {
            zzk.getClass();
            zzj.zzi();
            zzj.zzf.add(zzk);
        }

        public static /* synthetic */ void zza(zzj zzj, String str) {
            str.getClass();
            zzj.zze |= 1;
            zzj.zzg = str;
        }
    }

    public static final class zzk extends zzlc<zzk, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzk zzc;
        private static volatile zzmu<zzk> zzd;
        private String zzaa = "";
        private long zzab;
        private int zzac;
        private String zzad = "";
        private String zzae = "";
        private boolean zzaf;
        /* access modifiers changed from: private */
        public zzll<zzd> zzag = zzlc.zzch();
        private String zzah = "";
        private int zzai;
        private int zzaj;
        private int zzak;
        private String zzal = "";
        private long zzam;
        private long zzan;
        private String zzao = "";
        private String zzap = "";
        private int zzaq;
        private String zzar = "";
        private zzl zzas;
        private zzlj zzat = zzlc.zzcf();
        private long zzau;
        private long zzav;
        private String zzaw = "";
        private String zzax = "";
        private int zzay;
        private boolean zzaz;
        private String zzba = "";
        private boolean zzbb;
        private zzi zzbc;
        private String zzbd = "";
        private zzll<String> zzbe = zzlc.zzch();
        private String zzbf = "";
        private long zzbg;
        private boolean zzbh;
        private String zzbi = "";
        private boolean zzbj;
        private String zzbk = "";
        private int zzbl;
        private String zzbm = "";
        private zzc zzbn;
        private int zzbo;
        private zza zzbp;
        private int zze;
        private int zzf;
        private int zzg;
        /* access modifiers changed from: private */
        public zzll<zzf> zzh = zzlc.zzch();
        private zzll<zzo> zzi = zzlc.zzch();
        private long zzj;
        private long zzk;
        private long zzl;
        private long zzm;
        private long zzn;
        private String zzo = "";
        private String zzp = "";
        private String zzq = "";
        private String zzr = "";
        private int zzs;
        private String zzt = "";
        private String zzu = "";
        private String zzv = "";
        private long zzw;
        private long zzx;
        private String zzy = "";
        private boolean zzz;

        public static final class zza extends zzlc.zzb<zzk, zza> implements zzmn {
            public final int zza() {
                return ((zzk) this.zza).zza();
            }

            public final List<zzf> zzaa() {
                return Collections.unmodifiableList(((zzk) this.zza).zzar());
            }

            public final List<zzo> zzab() {
                return Collections.unmodifiableList(((zzk) this.zza).zzas());
            }

            public final boolean zzac() {
                return ((zzk) this.zza).zzau();
            }

            public final boolean zzad() {
                return ((zzk) this.zza).zzav();
            }

            public final boolean zzae() {
                return ((zzk) this.zza).zzax();
            }

            public final int zzb() {
                return ((zzk) this.zza).zzb();
            }

            public final int zzc() {
                return ((zzk) this.zza).zze();
            }

            public final int zzd() {
                return ((zzk) this.zza).zzi();
            }

            public final long zze() {
                return ((zzk) this.zza).zzm();
            }

            public final long zzf() {
                return ((zzk) this.zza).zzq();
            }

            public final zza zzg() {
                return ((zzk) this.zza).zzu();
            }

            public final zza zzh() {
                zzak();
                zzk.zzb((zzk) this.zza);
                return this;
            }

            public final zza zzi() {
                zzak();
                ((zzk) this.zza).zzag = zzlc.zzch();
                return this;
            }

            public final zza zzj() {
                zzak();
                zzk.zzd((zzk) this.zza);
                return this;
            }

            public final zza zzk() {
                zzak();
                zzk.zze((zzk) this.zza);
                return this;
            }

            public final zza zzl() {
                zzak();
                ((zzk) this.zza).zzh = zzlc.zzch();
                return this;
            }

            public final zza zzm() {
                zzak();
                zzk.zzg((zzk) this.zza);
                return this;
            }

            public final zza zzn() {
                zzak();
                zzk.zzh((zzk) this.zza);
                return this;
            }

            public final zza zzo() {
                zzak();
                zzk.zzi((zzk) this.zza);
                return this;
            }

            public final zza zzp() {
                zzak();
                zzk.zzj((zzk) this.zza);
                return this;
            }

            public final zza zzq() {
                zzak();
                zzk.zzk((zzk) this.zza);
                return this;
            }

            public final zza zzr() {
                zzak();
                zzk.zzl((zzk) this.zza);
                return this;
            }

            public final zza zzs() {
                zzak();
                zzk.zzm((zzk) this.zza);
                return this;
            }

            public final String zzt() {
                return ((zzk) this.zza).zzz();
            }

            public final String zzu() {
                return ((zzk) this.zza).zzaa();
            }

            public final String zzv() {
                return ((zzk) this.zza).zzad();
            }

            public final String zzw() {
                return ((zzk) this.zza).zzaf();
            }

            public final String zzx() {
                return ((zzk) this.zza).zzaj();
            }

            public final String zzy() {
                return ((zzk) this.zza).zzal();
            }

            public final String zzz() {
                return ((zzk) this.zza).zzan();
            }

            private zza() {
                super(zzk.zzc);
            }

            public final zzf zza(int i3) {
                return ((zzk) this.zza).zza(i3);
            }

            public final zza zzb(Iterable<? extends zzf> iterable) {
                zzak();
                zzk.zzb((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzc(Iterable<? extends Integer> iterable) {
                zzak();
                zzk.zzc((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzd(Iterable<String> iterable) {
                zzak();
                zzk.zzd((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zze(Iterable<? extends zzo> iterable) {
                zzak();
                zzk.zze((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzf(int i3) {
                zzak();
                zzk.zze((zzk) this.zza, i3);
                return this;
            }

            public final zza zzg(String str) {
                zzak();
                zzk.zzg((zzk) this.zza, str);
                return this;
            }

            public final zza zza(Iterable<? extends zzd> iterable) {
                zzak();
                zzk.zza((zzk) this.zza, (Iterable) iterable);
                return this;
            }

            public final zza zzh(String str) {
                zzak();
                zzk.zzh((zzk) this.zza, str);
                return this;
            }

            public final zza zzi(String str) {
                zzak();
                zzk.zzi((zzk) this.zza, str);
                return this;
            }

            public final zza zzj(String str) {
                zzak();
                zzk.zzj((zzk) this.zza, str);
                return this;
            }

            public final zza zzk(String str) {
                zzak();
                zzk.zzk((zzk) this.zza, str);
                return this;
            }

            public final zza zzl(String str) {
                zzak();
                zzk.zzl((zzk) this.zza, str);
                return this;
            }

            public final zza zzm(String str) {
                zzak();
                zzk.zzm((zzk) this.zza, str);
                return this;
            }

            public final zza zzn(String str) {
                zzak();
                zzk.zzn((zzk) this.zza, str);
                return this;
            }

            public final zza zzo(String str) {
                zzak();
                zzk.zzo((zzk) this.zza, str);
                return this;
            }

            public final zza zzp(String str) {
                zzak();
                zzk.zzp((zzk) this.zza, str);
                return this;
            }

            public final zza zzq(String str) {
                zzak();
                zzk.zzq((zzk) this.zza, str);
                return this;
            }

            public final zza zzr(String str) {
                zzak();
                zzk.zzr((zzk) this.zza, str);
                return this;
            }

            public final zza zzs(String str) {
                zzak();
                zzk.zzs((zzk) this.zza, str);
                return this;
            }

            public final zza zzb(int i3) {
                zzak();
                zzk.zza((zzk) this.zza, i3);
                return this;
            }

            public final zza zzc(int i3) {
                zzak();
                zzk.zzb((zzk) this.zza, i3);
                return this;
            }

            public final zza zzd(int i3) {
                zzak();
                zzk.zzc((zzk) this.zza, i3);
                return this;
            }

            public final zza zze(String str) {
                zzak();
                zzk.zze((zzk) this.zza, str);
                return this;
            }

            public final zza zzf(String str) {
                zzak();
                zzk.zzf((zzk) this.zza, str);
                return this;
            }

            public final zza zzg(int i3) {
                zzak();
                zzk.zzf((zzk) this.zza, i3);
                return this;
            }

            public final zza zza(zzf.zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, (zzf) ((zzlc) zza.zzai()));
                return this;
            }

            public final zza zzh(long j2) {
                zzak();
                zzk.zzh((zzk) this.zza, j2);
                return this;
            }

            public final zza zzi(int i3) {
                zzak();
                zzk.zzh((zzk) this.zza, i3);
                return this;
            }

            public final zza zzj(long j2) {
                zzak();
                zzk.zzj((zzk) this.zza, j2);
                return this;
            }

            public final zza zzk(long j2) {
                zzak();
                zzk.zzk((zzk) this.zza, j2);
                return this;
            }

            public final zza zzl(long j2) {
                zzak();
                zzk.zzl((zzk) this.zza, j2);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                zzk.zzb((zzk) this.zza, str);
                return this;
            }

            public final zza zzc(String str) {
                zzak();
                zzk.zzc((zzk) this.zza, str);
                return this;
            }

            public final zza zzd(String str) {
                zzak();
                zzk.zzd((zzk) this.zza, str);
                return this;
            }

            public final zza zze(int i3) {
                zzak();
                zzk.zzd((zzk) this.zza, i3);
                return this;
            }

            public final zza zzf(long j2) {
                zzak();
                zzk.zzf((zzk) this.zza, j2);
                return this;
            }

            public final zza zzg(long j2) {
                zzak();
                zzk.zzg((zzk) this.zza, j2);
                return this;
            }

            public final zza zza(zzo.zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, (zzo) ((zzlc) zza.zzai()));
                return this;
            }

            public final zza zzh(int i3) {
                zzak();
                zzk.zzg((zzk) this.zza, 1);
                return this;
            }

            public final zza zzi(long j2) {
                zzak();
                zzk.zzi((zzk) this.zza, j2);
                return this;
            }

            public final zza zzj(int i3) {
                zzak();
                zzk.zzi((zzk) this.zza, i3);
                return this;
            }

            public final zzo zzk(int i3) {
                return ((zzk) this.zza).zzb(i3);
            }

            public final zza zzb(long j2) {
                zzak();
                zzk.zzb((zzk) this.zza, j2);
                return this;
            }

            public final zza zzc(long j2) {
                zzak();
                zzk.zzc((zzk) this.zza, j2);
                return this;
            }

            public final zza zzd(long j2) {
                zzak();
                zzk.zzd((zzk) this.zza, j2);
                return this;
            }

            public final zza zze(long j2) {
                zzak();
                zzk.zze((zzk) this.zza, j2);
                return this;
            }

            public final zza zza(zzo zzo) {
                zzak();
                zzk.zza((zzk) this.zza, zzo);
                return this;
            }

            public final zza zzb(boolean z2) {
                zzak();
                zzk.zzb((zzk) this.zza, z2);
                return this;
            }

            public final zza zzc(boolean z2) {
                zzak();
                zzk.zzc((zzk) this.zza, z2);
                return this;
            }

            public final zza zzd(boolean z2) {
                zzak();
                zzk.zzd((zzk) this.zza, z2);
                return this;
            }

            public final zza zza(zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, zza);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzk.zza((zzk) this.zza, str);
                return this;
            }

            public final zza zza(zzc zzc) {
                zzak();
                zzk.zza((zzk) this.zza, zzc);
                return this;
            }

            public final zza zza(long j2) {
                zzak();
                zzk.zza((zzk) this.zza, j2);
                return this;
            }

            public final zza zza(boolean z2) {
                zzak();
                zzk.zza((zzk) this.zza, z2);
                return this;
            }

            public final zza zza(int i3, zzf.zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, i3, (zzf) ((zzlc) zza.zzai()));
                return this;
            }

            public final zza zza(int i3, zzf zzf) {
                zzak();
                zzk.zza((zzk) this.zza, i3, zzf);
                return this;
            }

            public final zza zza(zzl.zza zza) {
                zzak();
                zzk.zza((zzk) this.zza, (zzl) ((zzlc) zza.zzai()));
                return this;
            }

            public final zza zza(int i3, zzo zzo) {
                zzak();
                zzk.zza((zzk) this.zza, i3, zzo);
                return this;
            }
        }

        static {
            zzk zzk2 = new zzk();
            zzc = zzk2;
            zzlc.zza(zzk.class, zzk2);
        }

        private zzk() {
        }

        private final void zzcp() {
            zzll<zzf> zzll = this.zzh;
            if (!zzll.zzc()) {
                this.zzh = zzlc.zza(zzll);
            }
        }

        private final void zzcq() {
            zzll<zzo> zzll = this.zzi;
            if (!zzll.zzc()) {
                this.zzi = zzlc.zza(zzll);
            }
        }

        public static zza zzw() {
            return (zza) zzc.zzcc();
        }

        public final int zza() {
            return this.zzbl;
        }

        public final String zzaa() {
            return this.zzaa;
        }

        public final String zzab() {
            return this.zzt;
        }

        public final String zzac() {
            return this.zzv;
        }

        public final String zzad() {
            return this.zzbi;
        }

        public final String zzae() {
            return this.zzax;
        }

        public final String zzaf() {
            return this.zzbk;
        }

        public final String zzag() {
            return this.zzq;
        }

        public final String zzah() {
            return this.zzao;
        }

        public final String zzai() {
            return this.zzah;
        }

        public final String zzaj() {
            return this.zzae;
        }

        public final String zzak() {
            return this.zzad;
        }

        public final String zzal() {
            return this.zzp;
        }

        public final String zzam() {
            return this.zzo;
        }

        public final String zzan() {
            return this.zzy;
        }

        public final String zzao() {
            return this.zzbd;
        }

        public final String zzap() {
            return this.zzr;
        }

        public final List<zzd> zzaq() {
            return this.zzag;
        }

        public final List<zzf> zzar() {
            return this.zzh;
        }

        public final List<zzo> zzas() {
            return this.zzi;
        }

        public final boolean zzat() {
            return this.zzbh;
        }

        public final boolean zzau() {
            return this.zzbj;
        }

        public final boolean zzav() {
            return this.zzz;
        }

        public final boolean zzaw() {
            return this.zzaf;
        }

        public final boolean zzax() {
            return (this.zzf & 16777216) != 0;
        }

        public final boolean zzay() {
            return (this.zze & 33554432) != 0;
        }

        public final boolean zzaz() {
            return (this.zzf & 4194304) != 0;
        }

        public final int zzb() {
            return this.zzai;
        }

        public final boolean zzba() {
            return (this.zze & 1048576) != 0;
        }

        public final boolean zzbb() {
            return (this.zze & 536870912) != 0;
        }

        public final boolean zzbc() {
            return (this.zzf & 131072) != 0;
        }

        public final boolean zzbd() {
            return (this.zzf & 128) != 0;
        }

        public final boolean zzbe() {
            return (this.zzf & 524288) != 0;
        }

        public final boolean zzbf() {
            return (this.zzf & 8388608) != 0;
        }

        public final boolean zzbg() {
            return (this.zze & 524288) != 0;
        }

        public final boolean zzbh() {
            return (this.zze & Integer.MIN_VALUE) != 0;
        }

        public final boolean zzbi() {
            return (this.zzf & 16) != 0;
        }

        public final boolean zzbj() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzbk() {
            return (this.zze & 16384) != 0;
        }

        public final boolean zzbl() {
            return (this.zzf & 262144) != 0;
        }

        public final boolean zzbm() {
            return (this.zze & 131072) != 0;
        }

        public final boolean zzbn() {
            return (this.zze & 32) != 0;
        }

        public final boolean zzbo() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzbp() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzbq() {
            return (this.zzf & 2) != 0;
        }

        public final boolean zzbr() {
            return (this.zze & 8388608) != 0;
        }

        public final boolean zzbs() {
            return (this.zzf & 8192) != 0;
        }

        public final boolean zzbt() {
            return (this.zze & 4) != 0;
        }

        public final boolean zzbu() {
            return (this.zzf & 32768) != 0;
        }

        public final boolean zzbv() {
            return (this.zze & 1024) != 0;
        }

        public final boolean zzbw() {
            return (this.zze & 2) != 0;
        }

        public final boolean zzbx() {
            return (this.zze & 32768) != 0;
        }

        public final int zzc() {
            return this.zzac;
        }

        public final int zzd() {
            return this.zzbo;
        }

        public final int zze() {
            return this.zzh.size();
        }

        public final int zzf() {
            return this.zzg;
        }

        public final int zzg() {
            return this.zzaq;
        }

        public final int zzh() {
            return this.zzs;
        }

        public final int zzi() {
            return this.zzi.size();
        }

        public final long zzj() {
            return this.zzam;
        }

        public final long zzk() {
            return this.zzab;
        }

        public final long zzl() {
            return this.zzau;
        }

        public final long zzm() {
            return this.zzl;
        }

        public final long zzn() {
            return this.zzw;
        }

        public final long zzo() {
            return this.zzn;
        }

        public final long zzp() {
            return this.zzm;
        }

        public final long zzq() {
            return this.zzk;
        }

        public final long zzr() {
            return this.zzbg;
        }

        public final long zzs() {
            return this.zzj;
        }

        public final long zzt() {
            return this.zzx;
        }

        public final zza zzu() {
            zza zza2 = this.zzbp;
            return zza2 == null ? zza.zze() : zza2;
        }

        public final zzc zzv() {
            zzc zzc2 = this.zzbn;
            return zzc2 == null ? zzc.zzc() : zzc2;
        }

        public final String zzy() {
            return this.zzar;
        }

        public final String zzz() {
            return this.zzu;
        }

        public static /* synthetic */ void zzc(zzk zzk2, Iterable iterable) {
            zzlj zzlj = zzk2.zzat;
            if (!zzlj.zzc()) {
                int size = zzlj.size();
                zzk2.zzat = zzlj.zzc(size == 0 ? 10 : size << 1);
            }
            zzji.zza(iterable, zzk2.zzat);
        }

        public static /* synthetic */ void zzd(zzk zzk2, Iterable iterable) {
            zzll<String> zzll = zzk2.zzbe;
            if (!zzll.zzc()) {
                zzk2.zzbe = zzlc.zza(zzll);
            }
            zzji.zza(iterable, zzk2.zzbe);
        }

        public static /* synthetic */ void zze(zzk zzk2, Iterable iterable) {
            zzk2.zzcq();
            zzji.zza(iterable, zzk2.zzi);
        }

        public static /* synthetic */ void zzg(zzk zzk2) {
            zzk2.zze &= -2097153;
            zzk2.zzad = zzc.zzad;
        }

        public static /* synthetic */ void zzh(zzk zzk2) {
            zzk2.zze &= -131073;
            zzk2.zzz = false;
        }

        public static /* synthetic */ void zzi(zzk zzk2) {
            zzk2.zze &= -33;
            zzk2.zzn = 0;
        }

        public static /* synthetic */ void zzj(zzk zzk2) {
            zzk2.zze &= -17;
            zzk2.zzm = 0;
        }

        public static /* synthetic */ void zzk(zzk zzk2) {
            zzk2.zze &= -65537;
            zzk2.zzy = zzc.zzy;
        }

        public static /* synthetic */ void zzl(zzk zzk2) {
            zzk2.zzf &= -8193;
            zzk2.zzbd = zzc.zzbd;
        }

        public static /* synthetic */ void zzm(zzk zzk2) {
            zzk2.zze &= -268435457;
            zzk2.zzal = zzc.zzal;
        }

        public static /* synthetic */ void zzn(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 2097152;
            zzk2.zzad = str;
        }

        public static /* synthetic */ void zzo(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 128;
            zzk2.zzp = str;
        }

        public static /* synthetic */ void zzp(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 64;
            zzk2.zzo = str;
        }

        public static /* synthetic */ void zzq(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 65536;
            zzk2.zzy = str;
        }

        public static /* synthetic */ void zzr(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 8192;
            zzk2.zzbd = str;
        }

        public static /* synthetic */ void zzs(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 512;
            zzk2.zzr = str;
        }

        public final zzf zza(int i3) {
            return this.zzh.get(i3);
        }

        public final zzo zzb(int i3) {
            return this.zzi.get(i3);
        }

        public static zza zza(zzk zzk2) {
            return (zza) zzc.zza(zzk2);
        }

        public static /* synthetic */ void zzb(zzk zzk2, Iterable iterable) {
            zzk2.zzcp();
            zzji.zza(iterable, zzk2.zzh);
        }

        public static /* synthetic */ void zzf(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 131072;
            zzk2.zzbi = str;
        }

        public static /* synthetic */ void zze(zzk zzk2) {
            zzk2.zze &= Integer.MAX_VALUE;
            zzk2.zzao = zzc.zzao;
        }

        public static /* synthetic */ void zzh(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 524288;
            zzk2.zzbk = str;
        }

        public static /* synthetic */ void zzi(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 256;
            zzk2.zzq = str;
        }

        public static /* synthetic */ void zzj(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= Integer.MIN_VALUE;
            zzk2.zzao = str;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004>\u0000\u0002\u0001O>\u0000\u0005\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&4ဈ'5᠌(7ဇ)9ဈ*:ဇ+;ဉ,?ဈ-@\u001aAဈ.Cဂ/Dဇ0Gဈ1Hဇ2Iဈ3Jင4Kဈ5Lဉ6Mင7Oဉ8", new Object[]{"zze", "zzf", "zzg", "zzh", zzf.class, "zzi", zzo.class, "zzj", "zzk", "zzl", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzm", "zzaf", "zzag", zzd.class, "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", "zzau", "zzav", "zzaw", "zzax", "zzay", zzgp.zzb(), "zzaz", "zzba", "zzbb", "zzbc", "zzbd", "zzbe", "zzbf", "zzbg", "zzbh", "zzbi", "zzbj", "zzbk", "zzbl", "zzbm", "zzbn", "zzbo", "zzbp"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzk> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzk.class) {
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

        public static /* synthetic */ void zzb(zzk zzk2) {
            zzk2.zze &= -262145;
            zzk2.zzaa = zzc.zzaa;
        }

        public static /* synthetic */ void zzd(zzk zzk2) {
            zzk2.zze &= -257;
            zzk2.zzq = zzc.zzq;
        }

        public static /* synthetic */ void zzf(zzk zzk2, int i3) {
            zzk2.zzf |= 8388608;
            zzk2.zzbo = i3;
        }

        public static /* synthetic */ void zzg(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 128;
            zzk2.zzax = str;
        }

        public static /* synthetic */ void zzk(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 16384;
            zzk2.zzbf = str;
        }

        public static /* synthetic */ void zzl(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 16777216;
            zzk2.zzah = str;
        }

        public static /* synthetic */ void zzm(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 4194304;
            zzk2.zzae = str;
        }

        public static /* synthetic */ void zzh(zzk zzk2, long j2) {
            zzk2.zze |= 16;
            zzk2.zzm = j2;
        }

        public static /* synthetic */ void zzi(zzk zzk2, long j2) {
            zzk2.zze |= 4;
            zzk2.zzk = j2;
        }

        public static /* synthetic */ void zzj(zzk zzk2, long j2) {
            zzk2.zzf |= 32768;
            zzk2.zzbg = j2;
        }

        public static /* synthetic */ void zze(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 8192;
            zzk2.zzv = str;
        }

        public static /* synthetic */ void zzf(zzk zzk2, long j2) {
            zzk2.zze |= 16384;
            zzk2.zzw = j2;
        }

        public static /* synthetic */ void zzb(zzk zzk2, int i3) {
            zzk2.zzcq();
            zzk2.zzi.remove(i3);
        }

        public static /* synthetic */ void zzc(zzk zzk2, int i3) {
            zzk2.zzf |= 1048576;
            zzk2.zzbl = i3;
        }

        public static /* synthetic */ void zzg(zzk zzk2, long j2) {
            zzk2.zze |= 32;
            zzk2.zzn = j2;
        }

        public static /* synthetic */ void zzh(zzk zzk2, int i3) {
            zzk2.zzf |= 2;
            zzk2.zzaq = i3;
        }

        public static /* synthetic */ void zzi(zzk zzk2, int i3) {
            zzk2.zze |= 1024;
            zzk2.zzs = i3;
        }

        public static /* synthetic */ void zzk(zzk zzk2, long j2) {
            zzk2.zze |= 2;
            zzk2.zzj = j2;
        }

        public static /* synthetic */ void zzl(zzk zzk2, long j2) {
            zzk2.zze |= 32768;
            zzk2.zzx = j2;
        }

        public static /* synthetic */ void zzd(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 2048;
            zzk2.zzt = str;
        }

        public static /* synthetic */ void zzb(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 4096;
            zzk2.zzu = str;
        }

        public static /* synthetic */ void zzc(zzk zzk2, String str) {
            str.getClass();
            zzk2.zze |= 262144;
            zzk2.zzaa = str;
        }

        public static /* synthetic */ void zze(zzk zzk2, int i3) {
            zzk2.zze |= 1048576;
            zzk2.zzac = i3;
        }

        public static /* synthetic */ void zzg(zzk zzk2, int i3) {
            zzk2.zze |= 1;
            zzk2.zzg = 1;
        }

        public static /* synthetic */ void zzd(zzk zzk2, int i3) {
            zzk2.zze |= 33554432;
            zzk2.zzai = i3;
        }

        public static /* synthetic */ void zze(zzk zzk2, long j2) {
            zzk2.zze |= 8;
            zzk2.zzl = j2;
        }

        public static /* synthetic */ void zzb(zzk zzk2, long j2) {
            zzk2.zze |= 536870912;
            zzk2.zzam = j2;
        }

        public static /* synthetic */ void zzc(zzk zzk2, long j2) {
            zzk2.zze |= 524288;
            zzk2.zzab = j2;
        }

        public static /* synthetic */ void zzd(zzk zzk2, long j2) {
            zzk2.zzf |= 16;
            zzk2.zzau = j2;
        }

        public static /* synthetic */ void zzb(zzk zzk2, boolean z2) {
            zzk2.zzf |= 262144;
            zzk2.zzbj = z2;
        }

        public static /* synthetic */ void zzc(zzk zzk2, boolean z2) {
            zzk2.zze |= 131072;
            zzk2.zzz = z2;
        }

        public static /* synthetic */ void zzd(zzk zzk2, boolean z2) {
            zzk2.zze |= 8388608;
            zzk2.zzaf = z2;
        }

        public static /* synthetic */ void zza(zzk zzk2, Iterable iterable) {
            zzll<zzd> zzll = zzk2.zzag;
            if (!zzll.zzc()) {
                zzk2.zzag = zzlc.zza(zzll);
            }
            zzji.zza(iterable, zzk2.zzag);
        }

        public static /* synthetic */ void zza(zzk zzk2, zzf zzf2) {
            zzf2.getClass();
            zzk2.zzcp();
            zzk2.zzh.add(zzf2);
        }

        public static /* synthetic */ void zza(zzk zzk2, zzo zzo2) {
            zzo2.getClass();
            zzk2.zzcq();
            zzk2.zzi.add(zzo2);
        }

        public static /* synthetic */ void zza(zzk zzk2, int i3) {
            zzk2.zzcp();
            zzk2.zzh.remove(i3);
        }

        public static /* synthetic */ void zza(zzk zzk2, zza zza2) {
            zza2.getClass();
            zzk2.zzbp = zza2;
            zzk2.zzf |= 16777216;
        }

        public static /* synthetic */ void zza(zzk zzk2, String str) {
            str.getClass();
            zzk2.zzf |= 4;
            zzk2.zzar = str;
        }

        public static /* synthetic */ void zza(zzk zzk2, zzc zzc2) {
            zzc2.getClass();
            zzk2.zzbn = zzc2;
            zzk2.zzf |= 4194304;
        }

        public static /* synthetic */ void zza(zzk zzk2, long j2) {
            zzk2.zzf |= 32;
            zzk2.zzav = j2;
        }

        public static /* synthetic */ void zza(zzk zzk2, boolean z2) {
            zzk2.zzf |= 65536;
            zzk2.zzbh = z2;
        }

        public static /* synthetic */ void zza(zzk zzk2, int i3, zzf zzf2) {
            zzf2.getClass();
            zzk2.zzcp();
            zzk2.zzh.set(i3, zzf2);
        }

        public static /* synthetic */ void zza(zzk zzk2, zzl zzl2) {
            zzl2.getClass();
            zzk2.zzas = zzl2;
            zzk2.zzf |= 8;
        }

        public static /* synthetic */ void zza(zzk zzk2, int i3, zzo zzo2) {
            zzo2.getClass();
            zzk2.zzcq();
            zzk2.zzi.set(i3, zzo2);
        }
    }

    public static final class zzl extends zzlc<zzl, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzl zzc;
        private static volatile zzmu<zzl> zzd;
        private int zze;
        private int zzf = 1;
        private zzll<zzg> zzg = zzlc.zzch();

        public static final class zza extends zzlc.zzb<zzl, zza> implements zzmn {
            public final zza zza(zzg.zza zza) {
                zzak();
                zzl.zza((zzl) this.zza, (zzg) ((zzlc) zza.zzai()));
                return this;
            }

            private zza() {
                super(zzl.zzc);
            }
        }

        public enum zzb implements zzlh {
            RADS(1),
            PROVISIONING(2);
            
            private final int zzd;

            private zzb(int i3) {
                this.zzd = i3;
            }

            public static zzlg zzb() {
                return zzhd.zza;
            }

            public final String toString() {
                return "<" + zzb.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + Typography.greater;
            }

            public final int zza() {
                return this.zzd;
            }

            public static zzb zza(int i3) {
                if (i3 == 1) {
                    return RADS;
                }
                if (i3 != 2) {
                    return null;
                }
                return PROVISIONING;
            }
        }

        static {
            zzl zzl = new zzl();
            zzc = zzl;
            zzlc.zza(zzl.class, zzl);
        }

        private zzl() {
        }

        public static zza zza() {
            return (zza) zzc.zzcc();
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b", new Object[]{"zze", "zzf", zzb.zzb(), "zzg", zzg.class});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzl> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzl.class) {
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

        public static /* synthetic */ void zza(zzl zzl, zzg zzg2) {
            zzg2.getClass();
            zzll<zzg> zzll = zzl.zzg;
            if (!zzll.zzc()) {
                zzl.zzg = zzlc.zza(zzll);
            }
            zzl.zzg.add(zzg2);
        }
    }

    public static final class zzm extends zzlc<zzm, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzm zzc;
        private static volatile zzmu<zzm> zzd;
        /* access modifiers changed from: private */
        public zzli zze = zzlc.zzcg();
        /* access modifiers changed from: private */
        public zzli zzf = zzlc.zzcg();
        /* access modifiers changed from: private */
        public zzll<zze> zzg = zzlc.zzch();
        /* access modifiers changed from: private */
        public zzll<zzn> zzh = zzlc.zzch();

        public static final class zza extends zzlc.zzb<zzm, zza> implements zzmn {
            public final zza zza(Iterable<? extends zze> iterable) {
                zzak();
                zzm.zza((zzm) this.zza, iterable);
                return this;
            }

            public final zza zzb(Iterable<? extends Long> iterable) {
                zzak();
                zzm.zzb((zzm) this.zza, iterable);
                return this;
            }

            public final zza zzc(Iterable<? extends zzn> iterable) {
                zzak();
                zzm.zzc((zzm) this.zza, iterable);
                return this;
            }

            public final zza zzd(Iterable<? extends Long> iterable) {
                zzak();
                zzm.zzd((zzm) this.zza, iterable);
                return this;
            }

            private zza() {
                super(zzm.zzc);
            }

            public final zza zza() {
                zzak();
                ((zzm) this.zza).zzg = zzlc.zzch();
                return this;
            }

            public final zza zzb() {
                zzak();
                ((zzm) this.zza).zzf = zzlc.zzcg();
                return this;
            }

            public final zza zzc() {
                zzak();
                ((zzm) this.zza).zzh = zzlc.zzch();
                return this;
            }

            public final zza zzd() {
                zzak();
                ((zzm) this.zza).zze = zzlc.zzcg();
                return this;
            }
        }

        static {
            zzm zzm = new zzm();
            zzc = zzm;
            zzlc.zza(zzm.class, zzm);
        }

        private zzm() {
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        public static zzm zzg() {
            return zzc;
        }

        public final int zza() {
            return this.zzg.size();
        }

        public final int zzb() {
            return this.zzf.size();
        }

        public final int zzc() {
            return this.zzh.size();
        }

        public final int zzd() {
            return this.zze.size();
        }

        public final List<zze> zzh() {
            return this.zzg;
        }

        public final List<Long> zzi() {
            return this.zzf;
        }

        public final List<zzn> zzj() {
            return this.zzh;
        }

        public final List<Long> zzk() {
            return this.zze;
        }

        public static /* synthetic */ void zzb(zzm zzm, Iterable iterable) {
            zzli zzli = zzm.zzf;
            if (!zzli.zzc()) {
                zzm.zzf = zzlc.zza(zzli);
            }
            zzji.zza(iterable, zzm.zzf);
        }

        public static /* synthetic */ void zzc(zzm zzm, Iterable iterable) {
            zzll<zzn> zzll = zzm.zzh;
            if (!zzll.zzc()) {
                zzm.zzh = zzlc.zza(zzll);
            }
            zzji.zza(iterable, zzm.zzh);
        }

        public static /* synthetic */ void zzd(zzm zzm, Iterable iterable) {
            zzli zzli = zzm.zze;
            if (!zzli.zzc()) {
                zzm.zze = zzlc.zza(zzli);
            }
            zzji.zza(iterable, zzm.zze);
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zze", "zzf", "zzg", zze.class, "zzh", zzn.class});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzm> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzm.class) {
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

        public static /* synthetic */ void zza(zzm zzm, Iterable iterable) {
            zzll<zze> zzll = zzm.zzg;
            if (!zzll.zzc()) {
                zzm.zzg = zzlc.zza(zzll);
            }
            zzji.zza(iterable, zzm.zzg);
        }
    }

    public static final class zzn extends zzlc<zzn, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzn zzc;
        private static volatile zzmu<zzn> zzd;
        private int zze;
        private int zzf;
        private zzli zzg = zzlc.zzcg();

        public static final class zza extends zzlc.zzb<zzn, zza> implements zzmn {
            public final zza zza(Iterable<? extends Long> iterable) {
                zzak();
                zzn.zza((zzn) this.zza, (Iterable) iterable);
                return this;
            }

            private zza() {
                super(zzn.zzc);
            }

            public final zza zza(int i3) {
                zzak();
                zzn.zza((zzn) this.zza, i3);
                return this;
            }
        }

        static {
            zzn zzn = new zzn();
            zzc = zzn;
            zzlc.zza(zzn.class, zzn);
        }

        private zzn() {
        }

        public static zza zzc() {
            return (zza) zzc.zzcc();
        }

        public final int zza() {
            return this.zzg.size();
        }

        public final int zzb() {
            return this.zzf;
        }

        public final List<Long> zze() {
            return this.zzg;
        }

        public final boolean zzf() {
            return (this.zze & 1) != 0;
        }

        public final long zza(int i3) {
            return this.zzg.zzb(i3);
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zze", "zzf", "zzg"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzn> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzn.class) {
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

        public static /* synthetic */ void zza(zzn zzn, Iterable iterable) {
            zzli zzli = zzn.zzg;
            if (!zzli.zzc()) {
                zzn.zzg = zzlc.zza(zzli);
            }
            zzji.zza(iterable, zzn.zzg);
        }

        public static /* synthetic */ void zza(zzn zzn, int i3) {
            zzn.zze |= 1;
            zzn.zzf = i3;
        }
    }

    public static final class zzo extends zzlc<zzo, zza> implements zzmn {
        /* access modifiers changed from: private */
        public static final zzo zzc;
        private static volatile zzmu<zzo> zzd;
        private int zze;
        private long zzf;
        private String zzg = "";
        private String zzh = "";
        private long zzi;
        private float zzj;
        private double zzk;

        public static final class zza extends zzlc.zzb<zzo, zza> implements zzmn {
            public final zza zza() {
                zzak();
                zzo.zza((zzo) this.zza);
                return this;
            }

            public final zza zzb() {
                zzak();
                zzo.zzb((zzo) this.zza);
                return this;
            }

            public final zza zzc() {
                zzak();
                zzo.zzc((zzo) this.zza);
                return this;
            }

            private zza() {
                super(zzo.zzc);
            }

            public final zza zza(double d2) {
                zzak();
                zzo.zza((zzo) this.zza, d2);
                return this;
            }

            public final zza zzb(long j2) {
                zzak();
                zzo.zzb((zzo) this.zza, j2);
                return this;
            }

            public final zza zza(long j2) {
                zzak();
                zzo.zza((zzo) this.zza, j2);
                return this;
            }

            public final zza zzb(String str) {
                zzak();
                zzo.zzb((zzo) this.zza, str);
                return this;
            }

            public final zza zza(String str) {
                zzak();
                zzo.zza((zzo) this.zza, str);
                return this;
            }
        }

        static {
            zzo zzo = new zzo();
            zzc = zzo;
            zzlc.zza(zzo.class, zzo);
        }

        private zzo() {
        }

        public static zza zze() {
            return (zza) zzc.zzcc();
        }

        public final double zza() {
            return this.zzk;
        }

        public final float zzb() {
            return this.zzj;
        }

        public final long zzc() {
            return this.zzi;
        }

        public final long zzd() {
            return this.zzf;
        }

        public final String zzg() {
            return this.zzg;
        }

        public final String zzh() {
            return this.zzh;
        }

        public final boolean zzi() {
            return (this.zze & 32) != 0;
        }

        public final boolean zzj() {
            return (this.zze & 16) != 0;
        }

        public final boolean zzk() {
            return (this.zze & 8) != 0;
        }

        public final boolean zzl() {
            return (this.zze & 1) != 0;
        }

        public final boolean zzm() {
            return (this.zze & 4) != 0;
        }

        public static /* synthetic */ void zzb(zzo zzo) {
            zzo.zze &= -9;
            zzo.zzi = 0;
        }

        public static /* synthetic */ void zzc(zzo zzo) {
            zzo.zze &= -5;
            zzo.zzh = zzc.zzh;
        }

        public final Object zza(int i3, Object obj, Object obj2) {
            switch (zzgm.zza[i3 - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza();
                case 3:
                    return zzlc.zza((zzml) zzc, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzc;
                case 5:
                    zzmu<zzo> zzmu = zzd;
                    if (zzmu == null) {
                        synchronized (zzo.class) {
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

        public static /* synthetic */ void zzb(zzo zzo, long j2) {
            zzo.zze |= 1;
            zzo.zzf = j2;
        }

        public static /* synthetic */ void zzb(zzo zzo, String str) {
            str.getClass();
            zzo.zze |= 4;
            zzo.zzh = str;
        }

        public static /* synthetic */ void zza(zzo zzo) {
            zzo.zze &= -33;
            zzo.zzk = 0.0d;
        }

        public static /* synthetic */ void zza(zzo zzo, double d2) {
            zzo.zze |= 32;
            zzo.zzk = d2;
        }

        public static /* synthetic */ void zza(zzo zzo, long j2) {
            zzo.zze |= 8;
            zzo.zzi = j2;
        }

        public static /* synthetic */ void zza(zzo zzo, String str) {
            str.getClass();
            zzo.zze |= 2;
            zzo.zzg = str;
        }
    }
}
