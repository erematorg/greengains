package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzrx;
import com.google.android.gms.measurement.internal.zzjc;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.dataflow.qual.Pure;

public final class zzgc extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private long zze;
    private long zzf;
    private List<String> zzg;
    private String zzh;
    private int zzi;
    private String zzj;
    private String zzk;
    private String zzl;
    private long zzm = 0;
    private String zzn = null;

    public zzgc(zzhw zzhw, long j2) {
        super(zzhw);
        this.zzf = j2;
    }

    @VisibleForTesting
    @WorkerThread
    private final String zzah() {
        if (!zzrx.zza() || !zze().zza(zzbj.zzbq)) {
            try {
                Class<?> loadClass = zza().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                if (loadClass == null) {
                    return null;
                }
                try {
                    Object invoke = loadClass.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{zza()});
                    if (invoke == null) {
                        return null;
                    }
                    try {
                        return (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", (Class[]) null).invoke(invoke, (Object[]) null);
                    } catch (Exception unused) {
                        zzj().zzv().zza("Failed to retrieve Firebase Instance Id");
                        return null;
                    }
                } catch (Exception unused2) {
                    zzj().zzw().zza("Failed to obtain Firebase Analytics instance");
                    return null;
                }
            } catch (ClassNotFoundException unused3) {
                return null;
            }
        } else {
            zzj().zzp().zza("Disabled IID for tests.");
            return null;
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @WorkerThread
    public final int zzaa() {
        zzu();
        return this.zzi;
    }

    @WorkerThread
    public final int zzab() {
        zzu();
        return this.zzc;
    }

    @WorkerThread
    public final String zzac() {
        zzu();
        return this.zzk;
    }

    @WorkerThread
    public final String zzad() {
        zzu();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    @WorkerThread
    public final String zzae() {
        zzt();
        zzu();
        Preconditions.checkNotNull(this.zzj);
        return this.zzj;
    }

    @WorkerThread
    public final List<String> zzaf() {
        return this.zzg;
    }

    @WorkerThread
    public final void zzag() {
        String str;
        zzt();
        if (!zzk().zzn().zza(zzjc.zza.ANALYTICS_STORAGE)) {
            zzj().zzc().zza("Analytics Storage consent is not granted");
            str = null;
        } else {
            byte[] bArr = new byte[16];
            zzq().zzv().nextBytes(bArr);
            str = String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
        }
        zzj().zzc().zza("Resetting session stitching token to ".concat(str == null ? AbstractJsonLexerKt.NULL : "not null"));
        this.zzl = str;
        this.zzm = zzb().currentTimeMillis();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zza zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzgc zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzjk zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlg zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzlp zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0186 A[Catch:{ IllegalStateException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0187 A[Catch:{ IllegalStateException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0190 A[Catch:{ IllegalStateException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01ac A[Catch:{ IllegalStateException -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0229  */
    @androidx.annotation.WorkerThread
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzx() {
        /*
            r11 = this;
            android.content.Context r0 = r11.zza()
            java.lang.String r0 = r0.getPackageName()
            android.content.Context r1 = r11.zza()
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            r2 = 0
            java.lang.String r3 = ""
            java.lang.String r4 = "unknown"
            java.lang.String r5 = "Unknown"
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 != 0) goto L_0x002d
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzg()
            java.lang.String r8 = "PackageManager is null, app identity information might be inaccurate. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r0)
            r7.zza(r8, r9)
            goto L_0x008c
        L_0x002d:
            java.lang.String r4 = r1.getInstallerPackageName(r0)     // Catch:{ IllegalArgumentException -> 0x0032 }
            goto L_0x0043
        L_0x0032:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzg()
            java.lang.String r8 = "Error retrieving app installer package name. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r0)
            r7.zza(r8, r9)
        L_0x0043:
            if (r4 != 0) goto L_0x0048
            java.lang.String r4 = "manual_install"
            goto L_0x0051
        L_0x0048:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r4)
            if (r7 == 0) goto L_0x0051
            r4 = r3
        L_0x0051:
            android.content.Context r7 = r11.zza()     // Catch:{ NameNotFoundException -> 0x0079 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0079 }
            android.content.pm.PackageInfo r7 = r1.getPackageInfo(r7, r2)     // Catch:{ NameNotFoundException -> 0x0079 }
            if (r7 == 0) goto L_0x008c
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x0079 }
            java.lang.CharSequence r8 = r1.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x0079 }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ NameNotFoundException -> 0x0079 }
            if (r9 != 0) goto L_0x0070
            java.lang.String r8 = r8.toString()     // Catch:{ NameNotFoundException -> 0x0079 }
            goto L_0x0071
        L_0x0070:
            r8 = r5
        L_0x0071:
            java.lang.String r5 = r7.versionName     // Catch:{ NameNotFoundException -> 0x0076 }
            int r6 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x0076 }
            goto L_0x008c
        L_0x0076:
            r7 = r5
            r5 = r8
            goto L_0x007a
        L_0x0079:
            r7 = r5
        L_0x007a:
            com.google.android.gms.measurement.internal.zzgi r8 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzg()
            java.lang.String r9 = "Error retrieving package info. appId, appName"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r0)
            r8.zza(r9, r10, r5)
            r5 = r7
        L_0x008c:
            r11.zza = r0
            r11.zzd = r4
            r11.zzb = r5
            r11.zzc = r6
            r4 = 0
            r11.zze = r4
            com.google.android.gms.measurement.internal.zzhw r4 = r11.zzu
            java.lang.String r4 = r4.zzu()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            r5 = 1
            if (r4 != 0) goto L_0x00b5
            com.google.android.gms.measurement.internal.zzhw r4 = r11.zzu
            java.lang.String r4 = r4.zzv()
            java.lang.String r6 = "am"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L_0x00b5
            r4 = r5
            goto L_0x00b6
        L_0x00b5:
            r4 = r2
        L_0x00b6:
            com.google.android.gms.measurement.internal.zzhw r6 = r11.zzu
            int r6 = r6.zzc()
            switch(r6) {
                case 0: goto L_0x014c;
                case 1: goto L_0x013e;
                case 2: goto L_0x0130;
                case 3: goto L_0x0122;
                case 4: goto L_0x0114;
                case 5: goto L_0x0106;
                case 6: goto L_0x00f8;
                case 7: goto L_0x00ea;
                case 8: goto L_0x00db;
                default: goto L_0x00bf;
            }
        L_0x00bf:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzn()
            java.lang.String r8 = "App measurement disabled"
            r7.zza(r8)
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzm()
            java.lang.String r8 = "Invalid scion state in identity"
            r7.zza(r8)
            goto L_0x0159
        L_0x00db:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzn()
            java.lang.String r8 = "App measurement disabled due to denied storage consent"
            r7.zza(r8)
            goto L_0x0159
        L_0x00ea:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzn()
            java.lang.String r8 = "App measurement disabled via the global data collection setting"
            r7.zza(r8)
            goto L_0x0159
        L_0x00f8:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzv()
            java.lang.String r8 = "App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics"
            r7.zza(r8)
            goto L_0x0159
        L_0x0106:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzp()
            java.lang.String r8 = "App measurement disabled via the init parameters"
            r7.zza(r8)
            goto L_0x0159
        L_0x0114:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzn()
            java.lang.String r8 = "App measurement disabled via the manifest"
            r7.zza(r8)
            goto L_0x0159
        L_0x0122:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzn()
            java.lang.String r8 = "App measurement disabled by setAnalyticsCollectionEnabled(false)"
            r7.zza(r8)
            goto L_0x0159
        L_0x0130:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzp()
            java.lang.String r8 = "App measurement deactivated via the init parameters"
            r7.zza(r8)
            goto L_0x0159
        L_0x013e:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzn()
            java.lang.String r8 = "App measurement deactivated via the manifest"
            r7.zza(r8)
            goto L_0x0159
        L_0x014c:
            com.google.android.gms.measurement.internal.zzgi r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzp()
            java.lang.String r8 = "App measurement collection enabled"
            r7.zza(r8)
        L_0x0159:
            if (r6 != 0) goto L_0x015c
            goto L_0x015d
        L_0x015c:
            r5 = r2
        L_0x015d:
            r11.zzj = r3
            r11.zzk = r3
            if (r4 == 0) goto L_0x016b
            com.google.android.gms.measurement.internal.zzhw r4 = r11.zzu
            java.lang.String r4 = r4.zzu()
            r11.zzk = r4
        L_0x016b:
            android.content.Context r4 = r11.zza()     // Catch:{ IllegalStateException -> 0x01a8 }
            com.google.android.gms.measurement.internal.zzhw r6 = r11.zzu     // Catch:{ IllegalStateException -> 0x01a8 }
            java.lang.String r6 = r6.zzx()     // Catch:{ IllegalStateException -> 0x01a8 }
            java.lang.String r7 = "google_app_id"
            com.google.android.gms.measurement.internal.zzhq r8 = new com.google.android.gms.measurement.internal.zzhq     // Catch:{ IllegalStateException -> 0x01a8 }
            r8.<init>(r4, r6)     // Catch:{ IllegalStateException -> 0x01a8 }
            java.lang.String r4 = r8.zza((java.lang.String) r7)     // Catch:{ IllegalStateException -> 0x01a8 }
            boolean r6 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IllegalStateException -> 0x01a8 }
            if (r6 == 0) goto L_0x0187
            goto L_0x0188
        L_0x0187:
            r3 = r4
        L_0x0188:
            r11.zzj = r3     // Catch:{ IllegalStateException -> 0x01a8 }
            boolean r3 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IllegalStateException -> 0x01a8 }
            if (r3 != 0) goto L_0x01aa
            com.google.android.gms.measurement.internal.zzhq r3 = new com.google.android.gms.measurement.internal.zzhq     // Catch:{ IllegalStateException -> 0x01a8 }
            android.content.Context r4 = r11.zza()     // Catch:{ IllegalStateException -> 0x01a8 }
            com.google.android.gms.measurement.internal.zzhw r6 = r11.zzu     // Catch:{ IllegalStateException -> 0x01a8 }
            java.lang.String r6 = r6.zzx()     // Catch:{ IllegalStateException -> 0x01a8 }
            r3.<init>(r4, r6)     // Catch:{ IllegalStateException -> 0x01a8 }
            java.lang.String r4 = "admob_app_id"
            java.lang.String r3 = r3.zza((java.lang.String) r4)     // Catch:{ IllegalStateException -> 0x01a8 }
            r11.zzk = r3     // Catch:{ IllegalStateException -> 0x01a8 }
            goto L_0x01aa
        L_0x01a8:
            r3 = move-exception
            goto L_0x01c9
        L_0x01aa:
            if (r5 == 0) goto L_0x01da
            com.google.android.gms.measurement.internal.zzgi r3 = r11.zzj()     // Catch:{ IllegalStateException -> 0x01a8 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzp()     // Catch:{ IllegalStateException -> 0x01a8 }
            java.lang.String r4 = "App measurement enabled for app package, google app id"
            java.lang.String r5 = r11.zza     // Catch:{ IllegalStateException -> 0x01a8 }
            java.lang.String r6 = r11.zzj     // Catch:{ IllegalStateException -> 0x01a8 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ IllegalStateException -> 0x01a8 }
            if (r6 == 0) goto L_0x01c3
            java.lang.String r6 = r11.zzk     // Catch:{ IllegalStateException -> 0x01a8 }
            goto L_0x01c5
        L_0x01c3:
            java.lang.String r6 = r11.zzj     // Catch:{ IllegalStateException -> 0x01a8 }
        L_0x01c5:
            r3.zza(r4, r5, r6)     // Catch:{ IllegalStateException -> 0x01a8 }
            goto L_0x01da
        L_0x01c9:
            com.google.android.gms.measurement.internal.zzgi r4 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()
            java.lang.String r5 = "Fetching Google App Id failed with exception. appId"
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r0)
            r4.zza(r5, r0, r3)
        L_0x01da:
            r0 = 0
            r11.zzg = r0
            com.google.android.gms.measurement.internal.zzah r0 = r11.zze()
            java.lang.String r3 = "analytics.safelisted_events"
            java.util.List r0 = r0.zzg(r3)
            if (r0 == 0) goto L_0x021a
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L_0x01fd
            com.google.android.gms.measurement.internal.zzgi r0 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzv()
            java.lang.String r3 = "Safelisted event list is empty. Ignoring"
            r0.zza(r3)
            goto L_0x021c
        L_0x01fd:
            java.util.Iterator r3 = r0.iterator()
        L_0x0201:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x021a
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            com.google.android.gms.measurement.internal.zzop r5 = r11.zzq()
            java.lang.String r6 = "safelisted event"
            boolean r4 = r5.zzb((java.lang.String) r6, (java.lang.String) r4)
            if (r4 != 0) goto L_0x0201
            goto L_0x021c
        L_0x021a:
            r11.zzg = r0
        L_0x021c:
            if (r1 == 0) goto L_0x0229
            android.content.Context r0 = r11.zza()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r11.zzi = r0
            return
        L_0x0229:
            r11.zzi = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgc.zzx():void");
    }

    public final boolean zzz() {
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01f3  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzp zza(java.lang.String r47) {
        /*
            r46 = this;
            r0 = r46
            r46.zzt()
            com.google.android.gms.measurement.internal.zzp r41 = new com.google.android.gms.measurement.internal.zzp
            java.lang.String r1 = r46.zzad()
            java.lang.String r2 = r46.zzae()
            r46.zzu()
            java.lang.String r3 = r0.zzb
            int r4 = r46.zzab()
            long r4 = (long) r4
            r46.zzu()
            java.lang.String r6 = r0.zzd
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)
            java.lang.String r6 = r0.zzd
            r46.zzu()
            r46.zzt()
            long r7 = r0.zze
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 != 0) goto L_0x0049
            com.google.android.gms.measurement.internal.zzhw r7 = r0.zzu
            com.google.android.gms.measurement.internal.zzop r7 = r7.zzt()
            android.content.Context r8 = r46.zza()
            android.content.Context r11 = r46.zza()
            java.lang.String r11 = r11.getPackageName()
            long r7 = r7.zza((android.content.Context) r8, (java.lang.String) r11)
            r0.zze = r7
        L_0x0049:
            long r11 = r0.zze
            com.google.android.gms.measurement.internal.zzhw r7 = r0.zzu
            boolean r13 = r7.zzac()
            com.google.android.gms.measurement.internal.zzgu r7 = r46.zzk()
            boolean r7 = r7.zzm
            r8 = 1
            r14 = r7 ^ 1
            r46.zzt()
            com.google.android.gms.measurement.internal.zzhw r7 = r0.zzu
            boolean r7 = r7.zzac()
            if (r7 != 0) goto L_0x0068
            r17 = 0
            goto L_0x006e
        L_0x0068:
            java.lang.String r7 = r46.zzah()
            r17 = r7
        L_0x006e:
            com.google.android.gms.measurement.internal.zzhw r7 = r0.zzu
            com.google.android.gms.measurement.internal.zzgu r15 = r7.zzn()
            com.google.android.gms.measurement.internal.zzgz r15 = r15.zzc
            r18 = r14
            long r14 = r15.zza()
            int r19 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r19 != 0) goto L_0x0085
            long r14 = r7.zza
            r21 = r14
            goto L_0x008d
        L_0x0085:
            long r9 = r7.zza
            long r9 = java.lang.Math.min(r9, r14)
            r21 = r9
        L_0x008d:
            int r23 = r46.zzaa()
            com.google.android.gms.measurement.internal.zzah r7 = r46.zze()
            boolean r24 = r7.zzx()
            com.google.android.gms.measurement.internal.zzgu r7 = r46.zzk()
            r7.zzt()
            android.content.SharedPreferences r7 = r7.zzg()
            java.lang.String r9 = "deferred_analytics_collection"
            r10 = 0
            boolean r25 = r7.getBoolean(r9, r10)
            java.lang.String r26 = r46.zzac()
            com.google.android.gms.measurement.internal.zzah r7 = r46.zze()
            java.lang.String r9 = "google_analytics_default_allow_ad_personalization_signals"
            java.lang.Boolean r7 = r7.zze(r9)
            if (r7 != 0) goto L_0x00be
            r28 = 0
            goto L_0x00c9
        L_0x00be:
            boolean r7 = r7.booleanValue()
            r7 = r7 ^ r8
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r28 = r7
        L_0x00c9:
            long r14 = r0.zzf
            java.util.List<java.lang.String> r7 = r0.zzg
            com.google.android.gms.measurement.internal.zzgu r27 = r46.zzk()
            com.google.android.gms.measurement.internal.zzjc r27 = r27.zzn()
            java.lang.String r29 = r27.zzh()
            java.lang.String r10 = r0.zzh
            if (r10 != 0) goto L_0x00e7
            com.google.android.gms.measurement.internal.zzop r10 = r46.zzq()
            java.lang.String r10 = r10.zzp()
            r0.zzh = r10
        L_0x00e7:
            java.lang.String r10 = r0.zzh
            boolean r30 = com.google.android.gms.internal.measurement.zzpd.zza()
            if (r30 == 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzah r8 = r46.zze()
            r31 = r7
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbj.zzdb
            boolean r7 = r8.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)
            if (r7 == 0) goto L_0x0116
            com.google.android.gms.measurement.internal.zzgu r7 = r46.zzk()
            com.google.android.gms.measurement.internal.zzjc r7 = r7.zzn()
            com.google.android.gms.measurement.internal.zzjc$zza r8 = com.google.android.gms.measurement.internal.zzjc.zza.ANALYTICS_STORAGE
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzjc.zza) r8)
            if (r7 != 0) goto L_0x0116
            r32 = r14
            r19 = 0
            r34 = 0
            goto L_0x014e
        L_0x0114:
            r31 = r7
        L_0x0116:
            r46.zzt()
            long r7 = r0.zzm
            r19 = 0
            int r7 = (r7 > r19 ? 1 : (r7 == r19 ? 0 : -1))
            if (r7 == 0) goto L_0x0141
            com.google.android.gms.common.util.Clock r7 = r46.zzb()
            long r7 = r7.currentTimeMillis()
            r32 = r14
            long r14 = r0.zzm
            long r7 = r7 - r14
            java.lang.String r14 = r0.zzl
            if (r14 == 0) goto L_0x0143
            r14 = 86400000(0x5265c00, double:4.2687272E-316)
            int r7 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r7 <= 0) goto L_0x0143
            java.lang.String r7 = r0.zzn
            if (r7 != 0) goto L_0x0143
            r46.zzag()
            goto L_0x0143
        L_0x0141:
            r32 = r14
        L_0x0143:
            java.lang.String r7 = r0.zzl
            if (r7 != 0) goto L_0x014a
            r46.zzag()
        L_0x014a:
            java.lang.String r7 = r0.zzl
            r34 = r7
        L_0x014e:
            com.google.android.gms.measurement.internal.zzah r7 = r46.zze()
            java.lang.String r8 = "google_analytics_sgtm_upload_enabled"
            java.lang.Boolean r7 = r7.zze(r8)
            if (r7 != 0) goto L_0x015d
            r35 = 0
            goto L_0x0163
        L_0x015d:
            boolean r7 = r7.booleanValue()
            r35 = r7
        L_0x0163:
            com.google.android.gms.measurement.internal.zzop r7 = r46.zzq()
            java.lang.String r8 = r46.zzad()
            long r36 = r7.zzc(r8)
            com.google.android.gms.measurement.internal.zzgu r7 = r46.zzk()
            com.google.android.gms.measurement.internal.zzjc r7 = r7.zzn()
            int r38 = r7.zza()
            com.google.android.gms.measurement.internal.zzgu r7 = r46.zzk()
            com.google.android.gms.measurement.internal.zzaz r7 = r7.zzm()
            java.lang.String r39 = r7.zzf()
            boolean r7 = com.google.android.gms.internal.measurement.zzrl.zza()
            if (r7 == 0) goto L_0x01a3
            com.google.android.gms.measurement.internal.zzah r7 = r46.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzch
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)
            if (r7 == 0) goto L_0x01a3
            r46.zzq()
            int r7 = com.google.android.gms.measurement.internal.zzop.zzc()
            r40 = r7
            goto L_0x01a5
        L_0x01a3:
            r40 = 0
        L_0x01a5:
            boolean r7 = com.google.android.gms.internal.measurement.zzrl.zza()
            if (r7 == 0) goto L_0x01c2
            com.google.android.gms.measurement.internal.zzah r7 = r46.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzch
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)
            if (r7 == 0) goto L_0x01c2
            com.google.android.gms.measurement.internal.zzop r7 = r46.zzq()
            long r7 = r7.zzh()
            r42 = r7
            goto L_0x01c4
        L_0x01c2:
            r42 = r19
        L_0x01c4:
            com.google.android.gms.measurement.internal.zzah r7 = r46.zze()
            java.lang.String r44 = r7.zzw()
            boolean r7 = com.google.android.gms.internal.measurement.zzox.zza()
            if (r7 == 0) goto L_0x01f3
            com.google.android.gms.measurement.internal.zzah r7 = r46.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzcx
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)
            if (r7 == 0) goto L_0x01f3
            com.google.android.gms.measurement.internal.zzah r0 = r46.zze()
            r7 = 1
            com.google.android.gms.measurement.internal.zzjb r0 = r0.zzc((java.lang.String) r9, (boolean) r7)
            com.google.android.gms.measurement.internal.zzd r7 = new com.google.android.gms.measurement.internal.zzd
            r7.<init>(r0)
            java.lang.String r0 = r7.zzb()
        L_0x01f0:
            r45 = r0
            goto L_0x01f6
        L_0x01f3:
            java.lang.String r0 = ""
            goto L_0x01f0
        L_0x01f6:
            r7 = 102001(0x18e71, double:5.0395E-319)
            r30 = r31
            r15 = 0
            r31 = r32
            r27 = 0
            r0 = r41
            r33 = r10
            r9 = r11
            r11 = r47
            r12 = r13
            r13 = r18
            r14 = r17
            r17 = r21
            r19 = r23
            r20 = r24
            r21 = r25
            r22 = r26
            r23 = r28
            r24 = r31
            r26 = r30
            r28 = r29
            r29 = r33
            r30 = r34
            r31 = r35
            r32 = r36
            r34 = r38
            r35 = r39
            r36 = r40
            r37 = r42
            r39 = r44
            r40 = r45
            r0.<init>((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (long) r4, (java.lang.String) r6, (long) r7, (long) r9, (java.lang.String) r11, (boolean) r12, (boolean) r13, (java.lang.String) r14, (long) r15, (long) r17, (int) r19, (boolean) r20, (boolean) r21, (java.lang.String) r22, (java.lang.Boolean) r23, (long) r24, (java.util.List<java.lang.String>) r26, (java.lang.String) r27, (java.lang.String) r28, (java.lang.String) r29, (java.lang.String) r30, (boolean) r31, (long) r32, (int) r34, (java.lang.String) r35, (int) r36, (long) r37, (java.lang.String) r39, (java.lang.String) r40)
            return r41
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgc.zza(java.lang.String):com.google.android.gms.measurement.internal.zzp");
    }

    public final boolean zzb(String str) {
        String str2 = this.zzn;
        boolean z2 = str2 != null && !str2.equals(str);
        this.zzn = str;
        return z2;
    }
}
