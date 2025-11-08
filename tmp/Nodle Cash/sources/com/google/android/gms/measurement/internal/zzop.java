package com.google.android.gms.measurement.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ext.SdkExtensions;
import android.support.v4.media.session.a;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.app.NotificationCompat;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzdl;
import com.google.android.gms.internal.measurement.zzqz;
import com.google.android.gms.internal.measurement.zzrw;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.dataflow.qual.Pure;
import org.slf4j.Marker;

public final class zzop extends zzix {
    private static final String[] zza = {"firebase_", "google_", "ga_"};
    private static final String[] zzb = {"_err"};
    private SecureRandom zzc;
    private final AtomicLong zzd = new AtomicLong(0);
    private int zze;
    private MeasurementManagerFutures zzf;
    private Boolean zzg;
    private Integer zzh = null;

    public zzop(zzhw zzhw) {
        super(zzhw);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b1  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.Object r17, android.os.Bundle r18, java.util.List<java.lang.String> r19, boolean r20, boolean r21) {
        /*
            r13 = this;
            r7 = r13
            r8 = r16
            r0 = r17
            r1 = r18
            r13.zzt()
            boolean r2 = zza((java.lang.Object) r17)
            java.lang.String r3 = "param"
            r4 = 0
            if (r2 == 0) goto L_0x008a
            if (r21 == 0) goto L_0x0087
            java.lang.String[] r2 = com.google.android.gms.measurement.internal.zzje.zzc
            boolean r2 = zza((java.lang.String) r8, (java.lang.String[]) r2)
            if (r2 != 0) goto L_0x0020
            r0 = 20
            return r0
        L_0x0020:
            com.google.android.gms.measurement.internal.zzhw r2 = r7.zzu
            com.google.android.gms.measurement.internal.zzlp r2 = r2.zzr()
            boolean r2 = r2.zzam()
            if (r2 != 0) goto L_0x002f
            r0 = 25
            return r0
        L_0x002f:
            boolean r2 = r0 instanceof android.os.Parcelable[]
            if (r2 == 0) goto L_0x0038
            r5 = r0
            android.os.Parcelable[] r5 = (android.os.Parcelable[]) r5
            int r5 = r5.length
            goto L_0x0043
        L_0x0038:
            boolean r5 = r0 instanceof java.util.ArrayList
            if (r5 == 0) goto L_0x008a
            r5 = r0
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r5 = r5.size()
        L_0x0043:
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 <= r6) goto L_0x008a
            com.google.android.gms.measurement.internal.zzgi r9 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgk r9 = r9.zzv()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r10 = "Parameter array is too long; discarded. Value kind, name, array length"
            r9.zza(r10, r3, r8, r5)
            if (r2 == 0) goto L_0x006a
            r2 = r0
            android.os.Parcelable[] r2 = (android.os.Parcelable[]) r2
            int r5 = r2.length
            if (r5 <= r6) goto L_0x0083
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r6)
            android.os.Parcelable[] r2 = (android.os.Parcelable[]) r2
            r1.putParcelableArray(r8, r2)
            goto L_0x0083
        L_0x006a:
            boolean r2 = r0 instanceof java.util.ArrayList
            if (r2 == 0) goto L_0x0083
            r2 = r0
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            int r5 = r2.size()
            if (r5 <= r6) goto L_0x0083
            java.util.ArrayList r5 = new java.util.ArrayList
            java.util.List r2 = r2.subList(r4, r6)
            r5.<init>(r2)
            r1.putParcelableArrayList(r8, r5)
        L_0x0083:
            r1 = 17
            r9 = r1
            goto L_0x008b
        L_0x0087:
            r0 = 21
            return r0
        L_0x008a:
            r9 = r4
        L_0x008b:
            boolean r1 = zzg(r15)
            r2 = 0
            if (r1 != 0) goto L_0x00a2
            boolean r1 = zzg(r16)
            if (r1 == 0) goto L_0x0099
            goto L_0x00a2
        L_0x0099:
            com.google.android.gms.measurement.internal.zzah r1 = r13.zze()
            int r1 = r1.zza((java.lang.String) r2, (boolean) r4)
            goto L_0x00aa
        L_0x00a2:
            com.google.android.gms.measurement.internal.zzah r1 = r13.zze()
            int r1 = r1.zzb((java.lang.String) r2, (boolean) r4)
        L_0x00aa:
            boolean r1 = r13.zza((java.lang.String) r3, (java.lang.String) r8, (int) r1, (java.lang.Object) r0)
            if (r1 == 0) goto L_0x00b1
            return r9
        L_0x00b1:
            if (r21 == 0) goto L_0x013d
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 == 0) goto L_0x00c8
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r5 = r19
            r6 = r20
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (android.os.Bundle) r4, (java.util.List<java.lang.String>) r5, (boolean) r6)
            goto L_0x013c
        L_0x00c8:
            boolean r1 = r0 instanceof android.os.Parcelable[]
            if (r1 == 0) goto L_0x00fd
            r10 = r0
            android.os.Parcelable[] r10 = (android.os.Parcelable[]) r10
            int r11 = r10.length
            r12 = r4
        L_0x00d1:
            if (r12 >= r11) goto L_0x013c
            r0 = r10[r12]
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 != 0) goto L_0x00eb
            com.google.android.gms.measurement.internal.zzgi r1 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzv()
            java.lang.Class r0 = r0.getClass()
            java.lang.String r2 = "All Parcelable[] elements must be of type Bundle. Value type, name"
            r1.zza(r2, r0, r8)
            goto L_0x013d
        L_0x00eb:
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r5 = r19
            r6 = r20
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (android.os.Bundle) r4, (java.util.List<java.lang.String>) r5, (boolean) r6)
            int r12 = r12 + 1
            goto L_0x00d1
        L_0x00fd:
            boolean r1 = r0 instanceof java.util.ArrayList
            if (r1 == 0) goto L_0x013d
            r10 = r0
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            int r11 = r10.size()
        L_0x0108:
            if (r4 >= r11) goto L_0x013c
            java.lang.Object r0 = r10.get(r4)
            int r12 = r4 + 1
            boolean r1 = r0 instanceof android.os.Bundle
            if (r1 != 0) goto L_0x012b
            com.google.android.gms.measurement.internal.zzgi r1 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzv()
            if (r0 == 0) goto L_0x0123
            java.lang.Class r0 = r0.getClass()
            goto L_0x0125
        L_0x0123:
            java.lang.String r0 = "null"
        L_0x0125:
            java.lang.String r2 = "All ArrayList elements must be of type Bundle. Value type, name"
            r1.zza(r2, r0, r8)
            goto L_0x013d
        L_0x012b:
            r4 = r0
            android.os.Bundle r4 = (android.os.Bundle) r4
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r5 = r19
            r6 = r20
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (android.os.Bundle) r4, (java.util.List<java.lang.String>) r5, (boolean) r6)
            r4 = r12
            goto L_0x0108
        L_0x013c:
            return r9
        L_0x013d:
            r0 = 4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzop.zza(java.lang.String, java.lang.String, java.lang.String, java.lang.Object, android.os.Bundle, java.util.List, boolean, boolean):int");
    }

    public static int zzc() {
        if (Build.VERSION.SDK_INT < 30 || SdkExtensions.getExtensionVersion(30) <= 3) {
            return 0;
        }
        return SdkExtensions.getExtensionVersion(1000000);
    }

    private final int zzk(String str) {
        if (!zzb("event param", str)) {
            return 3;
        }
        if (!zza("event param", (String[]) null, str)) {
            return 14;
        }
        if (!zza("event param", 40, str)) {
            return 3;
        }
        return 0;
    }

    private final int zzl(String str) {
        if (!zzc("event param", str)) {
            return 3;
        }
        if (!zza("event param", (String[]) null, str)) {
            return 14;
        }
        if (!zza("event param", 40, str)) {
            return 3;
        }
        return 0;
    }

    private static int zzm(String str) {
        if ("_ldl".equals(str)) {
            return 2048;
        }
        if ("_id".equals(str)) {
            return 256;
        }
        return "_lgclid".equals(str) ? 100 : 36;
    }

    public static MessageDigest zzu() {
        int i3 = 0;
        while (i3 < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                if (instance != null) {
                    return instance;
                }
                i3++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    @WorkerThread
    @TargetApi(30)
    private final boolean zzy() {
        Integer num;
        if (this.zzg == null) {
            MeasurementManagerFutures zzn = zzn();
            boolean z2 = false;
            if (zzn == null) {
                return false;
            }
            Integer num2 = null;
            try {
                num = zzn.getMeasurementApiStatusAsync().get(10000, TimeUnit.MILLISECONDS);
                if (num != null) {
                    try {
                        if (num.intValue() == 1) {
                            z2 = true;
                        }
                    } catch (InterruptedException | CancellationException | ExecutionException | TimeoutException e3) {
                        e = e3;
                        num2 = num;
                        zzj().zzu().zza("Measurement manager api exception", e);
                        this.zzg = Boolean.FALSE;
                        num = num2;
                        zzj().zzp().zza("Measurement manager api status result", num);
                        return this.zzg.booleanValue();
                    }
                }
                this.zzg = Boolean.valueOf(z2);
            } catch (InterruptedException | CancellationException | ExecutionException | TimeoutException e4) {
                e = e4;
                zzj().zzu().zza("Measurement manager api exception", e);
                this.zzg = Boolean.FALSE;
                num = num2;
                zzj().zzp().zza("Measurement manager api status result", num);
                return this.zzg.booleanValue();
            }
            zzj().zzp().zza("Measurement manager api status result", num);
        }
        return this.zzg.booleanValue();
    }

    @WorkerThread
    public final void zzaa() {
        zzt();
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzj().zzu().zza("Utils falling back to Random for random id");
            }
        }
        this.zzd.set(nextLong);
    }

    public final int zzb(String str) {
        if (!zzb("user property", str)) {
            return 6;
        }
        if (!zza("user property", zzjh.zza, str)) {
            return 15;
        }
        if (!zza("user property", 24, str)) {
            return 6;
        }
        return 0;
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

    @EnsuresNonNull({"this.apkVersion"})
    public final int zzg() {
        if (this.zzh == null) {
            this.zzh = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(zza()) / 1000);
        }
        return this.zzh.intValue();
    }

    @WorkerThread
    public final long zzh() {
        long j2;
        zzt();
        if (!zzd(this.zzu.zzh().zzad())) {
            return 0;
        }
        if (Build.VERSION.SDK_INT < 30) {
            j2 = 4;
        } else if (SdkExtensions.getExtensionVersion(30) < 4) {
            j2 = 8;
        } else {
            j2 = zzc() < zzbj.zzax.zza(null).intValue() ? 16 : 0;
        }
        if (!zze("android.permission.ACCESS_ADSERVICES_ATTRIBUTION")) {
            j2 |= 2;
        }
        if (j2 == 0 && !zzy()) {
            j2 |= 64;
        }
        if (j2 == 0) {
            return 1;
        }
        return j2;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    public final MeasurementManagerFutures zzn() {
        if (this.zzf == null) {
            this.zzf = MeasurementManagerFutures.from(zza());
        }
        return this.zzf;
    }

    public final boolean zzo() {
        return true;
    }

    public final String zzp() {
        byte[] bArr = new byte[16];
        zzv().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
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

    @WorkerThread
    @EnsuresNonNull({"this.secureRandom"})
    public final SecureRandom zzv() {
        zzt();
        if (this.zzc == null) {
            this.zzc = new SecureRandom();
        }
        return this.zzc;
    }

    @WorkerThread
    public final boolean zzw() {
        zzt();
        return zzh() == 1;
    }

    public final boolean zzx() {
        try {
            zza().getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean zzd(String str) {
        return zze(zzbj.zzbd.zza(null), str);
    }

    public static boolean zzf(String str) {
        return zze(zzbj.zzby.zza(null), str);
    }

    public static boolean zzj(String str) {
        for (String equals : zzb) {
            if (equals.equals(str)) {
                return false;
            }
        }
        return true;
    }

    @WorkerThread
    public final boolean zze(String str) {
        zzt();
        if (Wrappers.packageManager(zza()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzj().zzc().zza("Permission not granted", str);
        return false;
    }

    public final boolean zzi(String str) {
        List<ResolveInfo> queryIntentActivities;
        if (!TextUtils.isEmpty(str) && (queryIntentActivities = zza().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(str)), 0)) != null && !queryIntentActivities.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean zzg(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("_");
    }

    @VisibleForTesting
    private static boolean zzn(String str) {
        Preconditions.checkNotNull(str);
        return str.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @WorkerThread
    public final long zzc(String str) {
        if (zza().getPackageManager() == null) {
            return 0;
        }
        int i3 = 0;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(zza()).getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                i3 = applicationInfo.targetSdkVersion;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            zzj().zzn().zza("PackageManager failed to find running app: app_id", str);
        }
        return (long) i3;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final long zzm() {
        long andIncrement;
        long j2;
        if (this.zzd.get() == 0) {
            synchronized (this.zzd) {
                long nextLong = new Random(System.nanoTime() ^ zzb().currentTimeMillis()).nextLong();
                int i3 = this.zze + 1;
                this.zze = i3;
                j2 = nextLong + ((long) i3);
            }
            return j2;
        }
        synchronized (this.zzd) {
            this.zzd.compareAndSet(-1, 1);
            andIncrement = this.zzd.getAndIncrement();
        }
        return andIncrement;
    }

    private static boolean zze(String str, String str2) {
        return str.equals(Marker.ANY_MARKER) || Arrays.asList(str.split(",")).contains(str2);
    }

    public final Object zzb(String str, Object obj) {
        int zza2;
        if ("_ev".equals(str)) {
            return zza(zze().zzb((String) null, false), obj, true, true, (String) null);
        }
        if (zzg(str)) {
            zza2 = zze().zzb((String) null, false);
        } else {
            zza2 = zze().zza((String) null, false);
        }
        return zza(zza2, obj, false, true, (String) null);
    }

    public final boolean zzd(String str, String str2) {
        if (zzrw.zza() && zze().zza(zzbj.zzbx) && !TextUtils.isEmpty(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return zze().zzu().equals(str);
    }

    public final Object zzc(String str, Object obj) {
        if ("_ldl".equals(str)) {
            return zza(zzm(str), obj, true, false, (String) null);
        }
        return zza(zzm(str), obj, false, false, (String) null);
    }

    public static boolean zzh(String str) {
        Preconditions.checkNotEmpty(str);
        if (str.charAt(0) != '_' || str.equals("_ep")) {
            return true;
        }
        return false;
    }

    public final boolean zzc(String str, String str2) {
        if (str2 == null) {
            zzj().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzj().zzh().zza("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzj().zzh().zza("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzj().zzh().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    public static ArrayList<Bundle> zzb(List<zzaf> list) {
        if (list == null) {
            return new ArrayList<>(0);
        }
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (zzaf next : list) {
            Bundle bundle = new Bundle();
            bundle.putString("app_id", next.zza);
            bundle.putString("origin", next.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, next.zzd);
            bundle.putString("name", next.zzc.zza);
            zziz.zza(bundle, Preconditions.checkNotNull(next.zzc.zza()));
            bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, next.zze);
            String str = next.zzf;
            if (str != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str);
            }
            zzbh zzbh = next.zzg;
            if (zzbh != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, zzbh.zza);
                zzbc zzbc = zzbh.zzb;
                if (zzbc != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, zzbc.zzb());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, next.zzh);
            zzbh zzbh2 = next.zzi;
            if (zzbh2 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, zzbh2.zza);
                zzbc zzbc2 = zzbh2.zzb;
                if (zzbc2 != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, zzbc2.zzb());
                }
            }
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, next.zzc.zzb);
            bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, next.zzj);
            zzbh zzbh3 = next.zzk;
            if (zzbh3 != null) {
                bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, zzbh3.zza);
                zzbc zzbc3 = zzbh3.zzb;
                if (zzbc3 != null) {
                    bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, zzbc3.zzb());
                }
            }
            arrayList.add(bundle);
        }
        return arrayList;
    }

    private static boolean zzc(Context context, String str) {
        ServiceInfo serviceInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (serviceInfo = packageManager.getServiceInfo(new ComponentName(context, str), 0)) == null || !serviceInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public final int zza(String str, Object obj) {
        boolean z2;
        if ("_ldl".equals(str)) {
            z2 = zza("user property referrer", str, zzm(str), obj);
        } else {
            z2 = zza("user property", str, zzm(str), obj);
        }
        return z2 ? 0 : 7;
    }

    private static boolean zzb(Bundle bundle, int i3) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i3);
        return true;
    }

    public final boolean zzb(String str, String str2) {
        if (str2 == null) {
            zzj().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzj().zzh().zza("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzj().zzh().zza("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzj().zzh().zza("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    public final int zza(String str) {
        if (!zzb(NotificationCompat.CATEGORY_EVENT, str)) {
            return 2;
        }
        if (!zza(NotificationCompat.CATEGORY_EVENT, zzjf.zza, zzjf.zzb, str)) {
            return 13;
        }
        if (!zza(NotificationCompat.CATEGORY_EVENT, 40, str)) {
            return 2;
        }
        return 0;
    }

    public final int zza(int i3) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(zza(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @WorkerThread
    public final long zza(Context context, String str) {
        zzt();
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzu = zzu();
        if (zzu == null) {
            zzj().zzg().zza("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzb(context, str)) {
                    Signature[] signatureArr = Wrappers.packageManager(context).getPackageInfo(zza().getPackageName(), 64).signatures;
                    if (signatureArr != null && signatureArr.length > 0) {
                        return zza(zzu.digest(signatureArr[0].toByteArray()));
                    }
                    zzj().zzu().zza("Could not get signatures");
                    return -1;
                }
            } catch (PackageManager.NameNotFoundException e3) {
                zzj().zzg().zza("Package name not found", e3);
            }
        }
        return 0;
    }

    @VisibleForTesting
    private final boolean zzb(Context context, String str) {
        Signature[] signatureArr;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (CertificateException e3) {
            zzj().zzg().zza("Error obtaining certificate", e3);
            return true;
        } catch (PackageManager.NameNotFoundException e4) {
            zzj().zzg().zza("Package name not found", e4);
            return true;
        }
    }

    @VisibleForTesting
    public static long zza(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        int i3 = 0;
        Preconditions.checkState(bArr.length > 0);
        int length = bArr.length - 1;
        long j2 = 0;
        while (length >= 0 && length >= bArr.length - 8) {
            j2 += (((long) bArr[length]) & 255) << i3;
            i3 += 8;
            length--;
        }
        return j2;
    }

    public static long zza(zzbc zzbc) {
        long j2 = 0;
        if (zzbc == null) {
            return 0;
        }
        Iterator<String> it = zzbc.iterator();
        while (it.hasNext()) {
            Object zzc2 = zzbc.zzc(it.next());
            if (zzc2 instanceof Parcelable[]) {
                j2 += (long) ((Parcelable[]) zzc2).length;
            }
        }
        return j2;
    }

    public static Bundle[] zzb(Object obj) {
        if (obj instanceof Bundle) {
            return new Bundle[]{(Bundle) obj};
        }
        if (obj instanceof Parcelable[]) {
            Parcelable[] parcelableArr = (Parcelable[]) obj;
            return (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
        } else if (!(obj instanceof ArrayList)) {
            return null;
        } else {
            ArrayList arrayList = (ArrayList) obj;
            return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    public static long zza(long j2, long j3) {
        return ((j3 * 60000) + j2) / 86400000;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    public static Bundle zza(List<zzok> list) {
        Bundle bundle = new Bundle();
        if (list == null) {
            return bundle;
        }
        for (zzok next : list) {
            String str = next.zzd;
            if (str != null) {
                bundle.putString(next.zza, str);
            } else {
                Long l2 = next.zzc;
                if (l2 != null) {
                    bundle.putLong(next.zza, l2.longValue());
                } else {
                    Double d2 = next.zzf;
                    if (d2 != null) {
                        bundle.putDouble(next.zza, d2.doubleValue());
                    }
                }
            }
        }
        return bundle;
    }

    public final Bundle zza(Uri uri, boolean z2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        Uri uri2 = uri;
        if (uri2 == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                str9 = uri2.getQueryParameter("utm_campaign");
                str8 = uri2.getQueryParameter("utm_source");
                str7 = uri2.getQueryParameter("utm_medium");
                str6 = uri2.getQueryParameter("gclid");
                str5 = z2 ? uri2.getQueryParameter("gbraid") : null;
                str4 = uri2.getQueryParameter("utm_id");
                str3 = uri2.getQueryParameter("dclid");
                str2 = uri2.getQueryParameter("srsltid");
                str = uri2.getQueryParameter("sfmc_id");
            } else {
                str9 = null;
                str8 = null;
                str7 = null;
                str6 = null;
                str5 = null;
                str4 = null;
                str3 = null;
                str2 = null;
                str = null;
            }
            if (TextUtils.isEmpty(str9) && TextUtils.isEmpty(str8) && TextUtils.isEmpty(str7) && TextUtils.isEmpty(str6) && ((!z2 || TextUtils.isEmpty(str5)) && TextUtils.isEmpty(str4) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str))) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str9)) {
                str10 = "sfmc_id";
                bundle.putString("campaign", str9);
            } else {
                str10 = "sfmc_id";
            }
            if (!TextUtils.isEmpty(str8)) {
                bundle.putString("source", str8);
            }
            if (!TextUtils.isEmpty(str7)) {
                bundle.putString("medium", str7);
            }
            if (!TextUtils.isEmpty(str6)) {
                bundle.putString("gclid", str6);
            }
            if (z2 && !TextUtils.isEmpty(str5)) {
                bundle.putString("gbraid", str5);
            }
            String queryParameter = uri2.getQueryParameter("gad_source");
            if (z2 && !TextUtils.isEmpty(queryParameter)) {
                bundle.putString("gad_source", queryParameter);
            }
            String queryParameter2 = uri2.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter2);
            }
            String queryParameter3 = uri2.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString(FirebaseAnalytics.Param.CONTENT, queryParameter3);
            }
            String queryParameter4 = uri2.getQueryParameter(FirebaseAnalytics.Param.ACLID);
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter4);
            }
            String queryParameter5 = uri2.getQueryParameter(FirebaseAnalytics.Param.CP1);
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter5);
            }
            String queryParameter6 = uri2.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter6)) {
                bundle.putString("anid", queryParameter6);
            }
            if (!TextUtils.isEmpty(str4)) {
                bundle.putString(FirebaseAnalytics.Param.CAMPAIGN_ID, str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString("dclid", str3);
            }
            String queryParameter7 = uri2.getQueryParameter("utm_source_platform");
            if (!TextUtils.isEmpty(queryParameter7)) {
                bundle.putString(FirebaseAnalytics.Param.SOURCE_PLATFORM, queryParameter7);
            }
            String queryParameter8 = uri2.getQueryParameter("utm_creative_format");
            if (!TextUtils.isEmpty(queryParameter8)) {
                bundle.putString(FirebaseAnalytics.Param.CREATIVE_FORMAT, queryParameter8);
            }
            String queryParameter9 = uri2.getQueryParameter("utm_marketing_tactic");
            if (!TextUtils.isEmpty(queryParameter9)) {
                bundle.putString(FirebaseAnalytics.Param.MARKETING_TACTIC, queryParameter9);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString("srsltid", str2);
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString(str10, str);
            }
            return bundle;
        } catch (UnsupportedOperationException e3) {
            zzj().zzu().zza("Install referrer url isn't a hierarchical URI", e3);
            return null;
        }
    }

    public static Bundle zza(Bundle bundle) {
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = new Bundle(bundle);
        for (String next : bundle2.keySet()) {
            Object obj = bundle2.get(next);
            if (obj instanceof Bundle) {
                bundle2.putBundle(next, new Bundle((Bundle) obj));
            } else {
                int i3 = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i3 < parcelableArr.length) {
                        if (parcelableArr[i3] instanceof Bundle) {
                            parcelableArr[i3] = new Bundle((Bundle) parcelableArr[i3]);
                        }
                        i3++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i3 < list.size()) {
                        Object obj2 = list.get(i3);
                        if (obj2 instanceof Bundle) {
                            list.set(i3, new Bundle((Bundle) obj2));
                        }
                        i3++;
                    }
                }
            }
        }
        return bundle2;
    }

    public final Bundle zza(Bundle bundle, String str) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String next : bundle.keySet()) {
                Object zzb2 = zzb(next, bundle.get(next));
                if (zzb2 == null) {
                    zzj().zzv().zza("Param value can't be null", zzi().zzb(next));
                } else {
                    zza(bundle2, next, zzb2);
                }
            }
        }
        return bundle2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zza(java.lang.String r20, java.lang.String r21, android.os.Bundle r22, java.util.List<java.lang.String> r23, boolean r24) {
        /*
            r19 = this;
            r9 = r19
            r10 = r21
            r11 = r22
            r12 = r23
            java.lang.String[] r0 = com.google.android.gms.measurement.internal.zzjf.zzd
            boolean r13 = zza((java.lang.String) r10, (java.lang.String[]) r0)
            if (r11 == 0) goto L_0x00df
            android.os.Bundle r15 = new android.os.Bundle
            r15.<init>(r11)
            com.google.android.gms.measurement.internal.zzah r0 = r19.zze()
            int r8 = r0.zzg()
            java.util.TreeSet r0 = new java.util.TreeSet
            java.util.Set r1 = r22.keySet()
            r0.<init>(r1)
            java.util.Iterator r16 = r0.iterator()
            r17 = 0
            r18 = r17
        L_0x002e:
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L_0x00dd
            java.lang.Object r0 = r16.next()
            r7 = r0
            java.lang.String r7 = (java.lang.String) r7
            if (r12 == 0) goto L_0x0047
            boolean r0 = r12.contains(r7)
            if (r0 != 0) goto L_0x0044
            goto L_0x0047
        L_0x0044:
            r0 = r17
            goto L_0x0056
        L_0x0047:
            if (r24 != 0) goto L_0x004e
            int r0 = r9.zzl(r7)
            goto L_0x0050
        L_0x004e:
            r0 = r17
        L_0x0050:
            if (r0 != 0) goto L_0x0056
            int r0 = r9.zzk(r7)
        L_0x0056:
            if (r0 == 0) goto L_0x0066
            r1 = 3
            if (r0 != r1) goto L_0x005d
            r1 = r7
            goto L_0x005e
        L_0x005d:
            r1 = 0
        L_0x005e:
            zza((android.os.Bundle) r15, (int) r0, (java.lang.String) r7, (java.lang.Object) r1)
            r15.remove(r7)
            r9 = r8
            goto L_0x00a2
        L_0x0066:
            java.lang.Object r4 = r11.get(r7)
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r7
            r5 = r15
            r6 = r23
            r14 = r7
            r7 = r24
            r9 = r8
            r8 = r13
            int r0 = r0.zza(r1, r2, r3, r4, r5, r6, r7, r8)
            r1 = 17
            if (r0 != r1) goto L_0x0087
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            zza((android.os.Bundle) r15, (int) r0, (java.lang.String) r14, (java.lang.Object) r1)
            goto L_0x00a6
        L_0x0087:
            if (r0 == 0) goto L_0x00a6
            java.lang.String r1 = "_ev"
            boolean r1 = r1.equals(r14)
            if (r1 != 0) goto L_0x00a6
            r1 = 21
            if (r0 != r1) goto L_0x0097
            r7 = r10
            goto L_0x0098
        L_0x0097:
            r7 = r14
        L_0x0098:
            java.lang.Object r1 = r11.get(r14)
            zza((android.os.Bundle) r15, (int) r0, (java.lang.String) r7, (java.lang.Object) r1)
            r15.remove(r14)
        L_0x00a2:
            r8 = r9
            r9 = r19
            goto L_0x002e
        L_0x00a6:
            boolean r0 = zzh(r14)
            if (r0 == 0) goto L_0x00a2
            int r0 = r18 + 1
            if (r0 <= r9) goto L_0x00da
            java.lang.String r1 = "Event can't contain more than "
            java.lang.String r2 = " params"
            java.lang.String r1 = androidx.camera.camera2.internal.C0118y.c(r9, r1, r2)
            com.google.android.gms.measurement.internal.zzgi r2 = r19.zzj()
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzh()
            com.google.android.gms.measurement.internal.zzgh r3 = r19.zzi()
            java.lang.String r3 = r3.zza((java.lang.String) r10)
            com.google.android.gms.measurement.internal.zzgh r4 = r19.zzi()
            java.lang.String r4 = r4.zza((android.os.Bundle) r11)
            r2.zza(r1, r3, r4)
            r1 = 5
            zzb((android.os.Bundle) r15, (int) r1)
            r15.remove(r14)
        L_0x00da:
            r18 = r0
            goto L_0x00a2
        L_0x00dd:
            r14 = r15
            goto L_0x00e0
        L_0x00df:
            r14 = 0
        L_0x00e0:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzop.zza(java.lang.String, java.lang.String, android.os.Bundle, java.util.List, boolean):android.os.Bundle");
    }

    public final zzbh zza(String str, String str2, Bundle bundle, String str3, long j2, boolean z2, boolean z3) {
        Bundle bundle2;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (zza(str2) == 0) {
            if (bundle == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            bundle3.putString("_o", str3);
            Bundle zza2 = zza(str, str2, bundle3, (List<String>) CollectionUtils.listOf("_o"), true);
            if (z2) {
                zza2 = zza(zza2, str);
            }
            Preconditions.checkNotNull(zza2);
            return new zzbh(str2, new zzbc(zza2), str3, j2);
        }
        zzj().zzg().zza("Invalid conditional property event name", zzi().zzc(str2));
        throw new IllegalArgumentException();
    }

    private final Object zza(int i3, Object obj, boolean z2, boolean z3, String str) {
        Bundle zza2;
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i3, z2);
            }
            if (!z3 || (!(obj instanceof Bundle[]) && !(obj instanceof Parcelable[]))) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcelable parcelable : (Parcelable[]) obj) {
                if ((parcelable instanceof Bundle) && (zza2 = zza((Bundle) parcelable, (String) null)) != null && !zza2.isEmpty()) {
                    arrayList.add(zza2);
                }
            }
            return arrayList.toArray(new Bundle[arrayList.size()]);
        }
    }

    public static String zza(String str, int i3, boolean z2) {
        if (str == null) {
            return null;
        }
        if (str.codePointCount(0, str.length()) <= i3) {
            return str;
        }
        if (z2) {
            return a.m(str.substring(0, str.offsetByCodePoints(0, i3)), "...");
        }
        return null;
    }

    public final URL zza(long j2, String str, String str2, long j3, String str3) {
        try {
            Preconditions.checkNotEmpty(str2);
            Preconditions.checkNotEmpty(str);
            String str4 = "https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=" + ("v" + j2 + JwtUtilsKt.JWT_DELIMITER + zzg()) + "&rdid=" + str2 + "&bundleid=" + str + "&retry=" + j3;
            if (str.equals(zze().zzv())) {
                str4 = str4.concat("&ddl_test=1");
            }
            if (!str3.isEmpty()) {
                if (str3.charAt(0) != '&') {
                    str4 = str4.concat("&");
                }
                str4 = str4.concat(str3);
            }
            return new URL(str4);
        } catch (IllegalArgumentException | MalformedURLException e3) {
            zzj().zzg().zza("Failed to create BOW URL for Deferred Deep Link. exception", e3.getMessage());
            return null;
        }
    }

    @WorkerThread
    public final void zza(Bundle bundle, long j2) {
        long j3 = bundle.getLong("_et");
        if (j3 != 0) {
            zzj().zzu().zza("Params already contained engagement", Long.valueOf(j3));
        }
        bundle.putLong("_et", j2 + j3);
    }

    private static void zza(Bundle bundle, int i3, String str, Object obj) {
        if (zzb(bundle, i3)) {
            bundle.putString("_ev", zza(str, 40, true));
            if (obj != null) {
                Preconditions.checkNotNull(bundle);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    bundle.putLong("_el", (long) String.valueOf(obj).length());
                }
            }
        }
    }

    @WorkerThread
    public static void zza(zzlh zzlh, Bundle bundle, boolean z2) {
        if (bundle != null && zzlh != null && (!bundle.containsKey("_sc") || z2)) {
            String str = zzlh.zza;
            if (str != null) {
                bundle.putString("_sn", str);
            } else {
                bundle.remove("_sn");
            }
            String str2 = zzlh.zzb;
            if (str2 != null) {
                bundle.putString("_sc", str2);
            } else {
                bundle.remove("_sc");
            }
            bundle.putLong("_si", zzlh.zzc);
        } else if (bundle != null && zzlh == null && z2) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    public final void zza(Bundle bundle, Bundle bundle2) {
        if (bundle2 != null) {
            for (String next : bundle2.keySet()) {
                if (!bundle.containsKey(next)) {
                    zzq().zza(bundle, next, bundle2.get(next));
                }
            }
        }
    }

    public final void zza(Parcelable[] parcelableArr, int i3, boolean z2) {
        Preconditions.checkNotNull(parcelableArr);
        for (Bundle bundle : parcelableArr) {
            Iterator it = new TreeSet(bundle.keySet()).iterator();
            int i4 = 0;
            while (it.hasNext()) {
                String str = (String) it.next();
                if (zzh(str) && !zza(str, zzje.zzd) && (i4 = i4 + 1) > i3) {
                    if (z2) {
                        zzj().zzh().zza(C0118y.c(i3, "Param can't contain more than ", " item-scoped custom parameters"), zzi().zzb(str), zzi().zza(bundle));
                        zzb(bundle, 28);
                    } else {
                        zzj().zzh().zza("Param cannot contain item-scoped custom parameters", zzi().zzb(str), zzi().zza(bundle));
                        zzb(bundle, 23);
                    }
                    bundle.remove(str);
                }
            }
        }
    }

    public final void zza(zzgm zzgm, int i3) {
        Iterator it = new TreeSet(zzgm.zzd.keySet()).iterator();
        int i4 = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (zzh(str) && (i4 = i4 + 1) > i3) {
                zzj().zzh().zza(C0118y.c(i3, "Event can't contain more than ", " params"), zzi().zza(zzgm.zza), zzi().zza(zzgm.zzd));
                zzb(zzgm.zzd, 5);
                zzgm.zzd.remove(str);
            }
        }
    }

    public static void zza(zzoo zzoo, int i3, String str, String str2, int i4) {
        zza(zzoo, (String) null, i3, str, str2, i4);
    }

    public static void zza(zzoo zzoo, String str, int i3, String str2, String str3, int i4) {
        Bundle bundle = new Bundle();
        zzb(bundle, i3);
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            bundle.putString(str2, str3);
        }
        if (i3 == 6 || i3 == 7 || i3 == 2) {
            bundle.putLong("_el", (long) i4);
        }
        zzoo.zza(str, "_err", bundle);
    }

    public final void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof Bundle[]) {
                bundle.putParcelableArray(str, (Bundle[]) obj);
            } else if (str != null) {
                zzj().zzv().zza("Not putting event parameter. Invalid value type. name, type", zzi().zzb(str), obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public final void zza(zzdl zzdl, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("r", z2);
        try {
            zzdl.zza(bundle);
        } catch (RemoteException e3) {
            this.zzu.zzj().zzu().zza("Error returning boolean value to wrapper", e3);
        }
    }

    public final void zza(zzdl zzdl, ArrayList<Bundle> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("r", arrayList);
        try {
            zzdl.zza(bundle);
        } catch (RemoteException e3) {
            this.zzu.zzj().zzu().zza("Error returning bundle list to wrapper", e3);
        }
    }

    public final void zza(zzdl zzdl, Bundle bundle) {
        try {
            zzdl.zza(bundle);
        } catch (RemoteException e3) {
            this.zzu.zzj().zzu().zza("Error returning bundle value to wrapper", e3);
        }
    }

    public final void zza(zzdl zzdl, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("r", bArr);
        try {
            zzdl.zza(bundle);
        } catch (RemoteException e3) {
            this.zzu.zzj().zzu().zza("Error returning byte array to wrapper", e3);
        }
    }

    public final void zza(zzdl zzdl, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("r", i3);
        try {
            zzdl.zza(bundle);
        } catch (RemoteException e3) {
            this.zzu.zzj().zzu().zza("Error returning int value to wrapper", e3);
        }
    }

    public final void zza(zzdl zzdl, long j2) {
        Bundle bundle = new Bundle();
        bundle.putLong("r", j2);
        try {
            zzdl.zza(bundle);
        } catch (RemoteException e3) {
            this.zzu.zzj().zzu().zza("Error returning long value to wrapper", e3);
        }
    }

    public final void zza(zzdl zzdl, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("r", str);
        try {
            zzdl.zza(bundle);
        } catch (RemoteException e3) {
            this.zzu.zzj().zzu().zza("Error returning string value to wrapper", e3);
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle, List<String> list, boolean z2) {
        int i3;
        String str4;
        int i4;
        String str5 = str2;
        Bundle bundle2 = bundle;
        List<String> list2 = list;
        if (bundle2 != null) {
            int zzc2 = zze().zzc();
            Iterator it = new TreeSet(bundle.keySet()).iterator();
            int i5 = 0;
            while (it.hasNext()) {
                String str6 = (String) it.next();
                if (list2 == null || !list2.contains(str6)) {
                    i3 = !z2 ? zzl(str6) : 0;
                    if (i3 == 0) {
                        i3 = zzk(str6);
                    }
                } else {
                    i3 = 0;
                }
                if (i3 != 0) {
                    zza(bundle2, i3, str6, (Object) i3 == 3 ? str6 : null);
                    bundle2.remove(str6);
                } else {
                    if (zza(bundle2.get(str6))) {
                        zzj().zzv().zza("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", str5, str3, str6);
                        i4 = 22;
                        str4 = str6;
                    } else {
                        String str7 = str3;
                        str4 = str6;
                        i4 = zza(str, str2, str6, bundle2.get(str6), bundle, list, z2, false);
                    }
                    if (i4 != 0 && !"_ev".equals(str4)) {
                        zza(bundle2, i4, str4, bundle2.get(str4));
                        bundle2.remove(str4);
                    } else if (zzh(str4) && !zza(str4, zzje.zzd)) {
                        int i6 = i5 + 1;
                        if (!zza(231100000, true)) {
                            zzj().zzh().zza("Item array not supported on client's version of Google Play Services (Android Only)", zzi().zza(str5), zzi().zza(bundle2));
                            zzb(bundle2, 23);
                            bundle2.remove(str4);
                        } else if (i6 > zzc2) {
                            if (!zzqz.zza() || !zze().zza(zzbj.zzce)) {
                                zzj().zzh().zza("Item cannot contain custom parameters", zzi().zza(str5), zzi().zza(bundle2));
                                zzb(bundle2, 23);
                                bundle2.remove(str4);
                            } else {
                                zzj().zzh().zza(C0118y.c(zzc2, "Item can't contain more than ", " item-scoped custom params"), zzi().zza(str5), zzi().zza(bundle2));
                                zzb(bundle2, 28);
                                bundle2.remove(str4);
                            }
                        }
                        i5 = i6;
                    }
                }
            }
        }
    }

    private static boolean zza(String str, String[] strArr) {
        Preconditions.checkNotNull(strArr);
        for (String equals : strArr) {
            if (Objects.equals(str, equals)) {
                return true;
            }
        }
        return false;
    }

    public final boolean zza(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (zzn(str)) {
                return true;
            }
            if (this.zzu.zzae()) {
                zzj().zzh().zza("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzgi.zza(str));
            }
            return false;
        } else if (TextUtils.isEmpty(str2)) {
            if (this.zzu.zzae()) {
                zzj().zzh().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            }
            return false;
        } else if (zzn(str2)) {
            return true;
        } else {
            zzj().zzh().zza("Invalid admob_app_id. Analytics disabled.", zzgi.zza(str2));
            return false;
        }
    }

    public final boolean zza(String str, int i3, String str2) {
        if (str2 == null) {
            zzj().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.codePointCount(0, str2.length()) <= i3) {
            return true;
        } else {
            zzj().zzh().zza("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i3), str2);
            return false;
        }
    }

    public final boolean zza(String str, String[] strArr, String str2) {
        return zza(str, strArr, (String[]) null, str2);
    }

    public final boolean zza(String str, String[] strArr, String[] strArr2, String str2) {
        if (str2 == null) {
            zzj().zzh().zza("Name is required and can't be null. Type", str);
            return false;
        }
        Preconditions.checkNotNull(str2);
        for (String startsWith : zza) {
            if (str2.startsWith(startsWith)) {
                zzj().zzh().zza("Name starts with reserved prefix. Type, name", str, str2);
                return false;
            }
        }
        if (strArr == null || !zza(str2, strArr)) {
            return true;
        }
        if (strArr2 != null && zza(str2, strArr2)) {
            return true;
        }
        zzj().zzh().zza("Name is reserved. Type, name", str, str2);
        return false;
    }

    public final boolean zza(String str, String str2, int i3, Object obj) {
        if (obj != null && !(obj instanceof Long) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Boolean) && !(obj instanceof Double)) {
            if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
                return false;
            }
            String valueOf = String.valueOf(obj);
            if (valueOf.codePointCount(0, valueOf.length()) > i3) {
                zzj().zzv().zza("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
                return false;
            }
        }
        return true;
    }

    public static boolean zza(Bundle bundle, int i3) {
        int i4 = 0;
        if (bundle.size() <= i3) {
            return false;
        }
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            i4++;
            if (i4 > i3) {
                bundle.remove(str);
            }
        }
        return true;
    }

    public static boolean zza(Object obj) {
        return (obj instanceof Parcelable[]) || (obj instanceof ArrayList) || (obj instanceof Bundle);
    }

    public final boolean zza(int i3, boolean z2) {
        Boolean zzab = this.zzu.zzr().zzab();
        if (zzg() < i3 / 1000) {
            return zzab != null && !zzab.booleanValue();
        }
        return true;
    }

    public static boolean zza(Context context) {
        ActivityInfo receiverInfo;
        Preconditions.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0)) == null || !receiverInfo.enabled) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static boolean zza(Context context, boolean z2) {
        Preconditions.checkNotNull(context);
        return zzc(context, "com.google.android.gms.measurement.AppMeasurementJobService");
    }

    @SuppressLint({"ApplySharedPref"})
    public final boolean zza(String str, double d2) {
        try {
            SharedPreferences.Editor edit = zza().getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
            edit.putString(Constants.DEEPLINK, str);
            edit.putLong("timestamp", Double.doubleToRawLongBits(d2));
            return edit.commit();
        } catch (RuntimeException e3) {
            zzj().zzg().zza("Failed to persist Deferred Deep Link. exception", e3);
            return false;
        }
    }

    public static boolean zza(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    public static boolean zza(String str, String str2, String str3, String str4) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (!isEmpty && !isEmpty2) {
            Preconditions.checkNotNull(str);
            return !str.equals(str2);
        } else if (isEmpty && isEmpty2) {
            return (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) ? !TextUtils.isEmpty(str4) : !str3.equals(str4);
        } else {
            if (isEmpty || !isEmpty2) {
                return TextUtils.isEmpty(str3) || !str3.equals(str4);
            }
            if (TextUtils.isEmpty(str4)) {
                return false;
            }
            return TextUtils.isEmpty(str3) || !str3.equals(str4);
        }
    }

    public static byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }
}
