package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.v4.media.session.a;
import com.google.common.base.Function;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

public abstract class zzir<T> {
    private static final Object zza = new Object();
    @Nullable
    private static volatile zziy zzb = null;
    private static volatile boolean zzc = false;
    private static zzjc zzd = new zzjc(new zzis());
    private static final AtomicInteger zze = new AtomicInteger();
    private final zziz zzf;
    private final String zzg;
    private Object zzh;
    private volatile int zzi;
    private volatile T zzj;
    private final boolean zzk;
    private volatile boolean zzl;

    static {
        new AtomicReference();
    }

    public static /* synthetic */ zzir zza(zziz zziz, String str, Boolean bool, boolean z2) {
        return new zziu(zziz, str, bool, true);
    }

    @Nullable
    private final T zzb(zziy zziy) {
        zzif zzif;
        Object zza2;
        if (this.zzf.zzb == null) {
            zzif = zzja.zza(zziy.zza(), this.zzf.zza, new zziq());
        } else if (zzip.zza(zziy.zza(), this.zzf.zzb)) {
            zzif = this.zzf.zzg ? zzic.zza(zziy.zza().getContentResolver(), zzio.zza(zzio.zza(zziy.zza(), this.zzf.zzb.getLastPathSegment())), new zziq()) : zzic.zza(zziy.zza().getContentResolver(), this.zzf.zzb, new zziq());
        } else {
            zzif = null;
        }
        if (zzif == null || (zza2 = zzif.zza(zzb())) == null) {
            return null;
        }
        return zza(zza2);
    }

    public static void zzc() {
        zze.incrementAndGet();
    }

    public static /* synthetic */ boolean zzd() {
        return true;
    }

    private final T zze() {
        return this.zzh;
    }

    public abstract T zza(Object obj);

    private zzir(zziz zziz, String str, T t2, boolean z2) {
        this.zzi = -1;
        String str2 = zziz.zza;
        if (str2 == null && zziz.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        } else if (str2 == null || zziz.zzb == null) {
            this.zzf = zziz;
            this.zzg = str;
            this.zzh = t2;
            this.zzk = z2;
            this.zzl = false;
        } else {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
    }

    public static /* synthetic */ zzir zza(zziz zziz, String str, Double d2, boolean z2) {
        return new zzix(zziz, str, d2, true);
    }

    public static /* synthetic */ zzir zza(zziz zziz, String str, Long l2, boolean z2) {
        return new zziv(zziz, str, l2, true);
    }

    public static /* synthetic */ zzir zza(zziz zziz, String str, String str2, boolean z2) {
        return new zziw(zziz, str, str2, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0086 A[Catch:{ all -> 0x004e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T zza() {
        /*
            r8 = this;
            boolean r0 = r8.zzk
            if (r0 != 0) goto L_0x0011
            com.google.android.gms.internal.measurement.zzjc r0 = zzd
            java.lang.String r1 = r8.zzg
            boolean r0 = r0.zza(r1)
            java.lang.String r1 = "Attempt to access PhenotypeFlag not via codegen. All new PhenotypeFlags must be accessed through codegen APIs. If you believe you are seeing this error by mistake, you can add your flag to the exemption list located at //java/com/google/android/libraries/phenotype/client/lockdown/flags.textproto. Send the addition CL to ph-reviews@. See go/phenotype-android-codegen for information about generated code. See go/ph-lockdown for more information about this error."
            com.google.common.base.Preconditions.checkState(r0, r1)
        L_0x0011:
            java.util.concurrent.atomic.AtomicInteger r0 = zze
            int r0 = r0.get()
            int r1 = r8.zzi
            if (r1 >= r0) goto L_0x0099
            monitor-enter(r8)
            int r1 = r8.zzi     // Catch:{ all -> 0x004e }
            if (r1 >= r0) goto L_0x0095
            com.google.android.gms.internal.measurement.zziy r1 = zzb     // Catch:{ all -> 0x004e }
            com.google.common.base.Optional r2 = com.google.common.base.Optional.absent()     // Catch:{ all -> 0x004e }
            r3 = 0
            if (r1 == 0) goto L_0x0050
            com.google.common.base.Supplier r2 = r1.zzb()     // Catch:{ all -> 0x004e }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x004e }
            com.google.common.base.Optional r2 = (com.google.common.base.Optional) r2     // Catch:{ all -> 0x004e }
            boolean r4 = r2.isPresent()     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x0050
            java.lang.Object r3 = r2.get()     // Catch:{ all -> 0x004e }
            com.google.android.gms.internal.measurement.zzil r3 = (com.google.android.gms.internal.measurement.zzil) r3     // Catch:{ all -> 0x004e }
            com.google.android.gms.internal.measurement.zziz r4 = r8.zzf     // Catch:{ all -> 0x004e }
            android.net.Uri r5 = r4.zzb     // Catch:{ all -> 0x004e }
            java.lang.String r6 = r4.zza     // Catch:{ all -> 0x004e }
            java.lang.String r4 = r4.zzd     // Catch:{ all -> 0x004e }
            java.lang.String r7 = r8.zzg     // Catch:{ all -> 0x004e }
            java.lang.String r3 = r3.zza(r5, r6, r4, r7)     // Catch:{ all -> 0x004e }
            goto L_0x0050
        L_0x004e:
            r0 = move-exception
            goto L_0x0097
        L_0x0050:
            if (r1 == 0) goto L_0x0054
            r4 = 1
            goto L_0x0055
        L_0x0054:
            r4 = 0
        L_0x0055:
            java.lang.String r5 = "Must call PhenotypeFlagInitializer.maybeInit() first"
            com.google.common.base.Preconditions.checkState(r4, r5)     // Catch:{ all -> 0x004e }
            com.google.android.gms.internal.measurement.zziz r4 = r8.zzf     // Catch:{ all -> 0x004e }
            boolean r4 = r4.zzf     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x006e
            java.lang.Object r4 = r8.zza((com.google.android.gms.internal.measurement.zziy) r1)     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x0067
            goto L_0x0080
        L_0x0067:
            java.lang.Object r4 = r8.zzb((com.google.android.gms.internal.measurement.zziy) r1)     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x007c
            goto L_0x0080
        L_0x006e:
            java.lang.Object r4 = r8.zzb((com.google.android.gms.internal.measurement.zziy) r1)     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x0075
            goto L_0x0080
        L_0x0075:
            java.lang.Object r4 = r8.zza((com.google.android.gms.internal.measurement.zziy) r1)     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x007c
            goto L_0x0080
        L_0x007c:
            java.lang.Object r4 = r8.zze()     // Catch:{ all -> 0x004e }
        L_0x0080:
            boolean r1 = r2.isPresent()     // Catch:{ all -> 0x004e }
            if (r1 == 0) goto L_0x0091
            if (r3 != 0) goto L_0x008d
            java.lang.Object r4 = r8.zze()     // Catch:{ all -> 0x004e }
            goto L_0x0091
        L_0x008d:
            java.lang.Object r4 = r8.zza((java.lang.Object) r3)     // Catch:{ all -> 0x004e }
        L_0x0091:
            r8.zzj = r4     // Catch:{ all -> 0x004e }
            r8.zzi = r0     // Catch:{ all -> 0x004e }
        L_0x0095:
            monitor-exit(r8)     // Catch:{ all -> 0x004e }
            goto L_0x0099
        L_0x0097:
            monitor-exit(r8)     // Catch:{ all -> 0x004e }
            throw r0
        L_0x0099:
            T r8 = r8.zzj
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzir.zza():java.lang.Object");
    }

    public final String zzb() {
        return zza(this.zzf.zzd);
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static void zzb(android.content.Context r3) {
        /*
            com.google.android.gms.internal.measurement.zziy r0 = zzb
            if (r0 != 0) goto L_0x004e
            if (r3 != 0) goto L_0x0007
            goto L_0x004e
        L_0x0007:
            java.lang.Object r0 = zza
            monitor-enter(r0)
            com.google.android.gms.internal.measurement.zziy r1 = zzb     // Catch:{ all -> 0x0048 }
            if (r1 != 0) goto L_0x004a
            monitor-enter(r0)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.measurement.zziy r1 = zzb     // Catch:{ all -> 0x0022 }
            android.content.Context r2 = r3.getApplicationContext()     // Catch:{ all -> 0x0022 }
            if (r2 != 0) goto L_0x0018
            goto L_0x0019
        L_0x0018:
            r3 = r2
        L_0x0019:
            if (r1 == 0) goto L_0x0024
            android.content.Context r2 = r1.zza()     // Catch:{ all -> 0x0022 }
            if (r2 == r3) goto L_0x0044
            goto L_0x0024
        L_0x0022:
            r3 = move-exception
            goto L_0x0046
        L_0x0024:
            if (r1 == 0) goto L_0x002f
            com.google.android.gms.internal.measurement.zzic.zzc()     // Catch:{ all -> 0x0022 }
            com.google.android.gms.internal.measurement.zzja.zza()     // Catch:{ all -> 0x0022 }
            com.google.android.gms.internal.measurement.zzik.zza()     // Catch:{ all -> 0x0022 }
        L_0x002f:
            com.google.android.gms.internal.measurement.zzit r1 = new com.google.android.gms.internal.measurement.zzit     // Catch:{ all -> 0x0022 }
            r1.<init>(r3)     // Catch:{ all -> 0x0022 }
            com.google.common.base.Supplier r1 = com.google.common.base.Suppliers.memoize(r1)     // Catch:{ all -> 0x0022 }
            com.google.android.gms.internal.measurement.zzhz r2 = new com.google.android.gms.internal.measurement.zzhz     // Catch:{ all -> 0x0022 }
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0022 }
            zzb = r2     // Catch:{ all -> 0x0022 }
            java.util.concurrent.atomic.AtomicInteger r3 = zze     // Catch:{ all -> 0x0022 }
            r3.incrementAndGet()     // Catch:{ all -> 0x0022 }
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            goto L_0x004a
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0022 }
            throw r3     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r3 = move-exception
            goto L_0x004c
        L_0x004a:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r3
        L_0x004e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzir.zzb(android.content.Context):void");
    }

    @Nullable
    private final T zza(zziy zziy) {
        Function<Context, Boolean> function;
        zziz zziz = this.zzf;
        if (!zziz.zze && ((function = zziz.zzh) == null || function.apply(zziy.zza()).booleanValue())) {
            zzik zza2 = zzik.zza(zziy.zza());
            zziz zziz2 = this.zzf;
            Object zza3 = zza2.zza(zziz2.zze ? null : zza(zziz2.zzc));
            if (zza3 != null) {
                return zza(zza3);
            }
        }
        return null;
    }

    private final String zza(String str) {
        if (str == null || !str.isEmpty()) {
            return a.m(str, this.zzg);
        }
        return this.zzg;
    }
}
