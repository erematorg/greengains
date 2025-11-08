package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.Locale;
import java.util.MissingResourceException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class zzbf {
    @NotNull
    public static final zzbe zza = new zzbe((DefaultConstructorMarker) null);
    @Nullable
    private static zzmo zzb;
    @NotNull
    private final String zzc;
    @NotNull
    private final zzac zzd;
    @NotNull
    private final zznc zze;
    private final long zzf = System.currentTimeMillis();

    public zzbf(@NotNull zzbb zzbb, @NotNull String str, @NotNull zzac zzac) {
        this.zzc = str;
        this.zzd = zzac;
        zznc zzi = zznf.zzi();
        this.zze = zzi;
        zzi.zzp(zzbb.zza());
        zzi.zzd(zzbb.zzb());
        zzi.zzr(zzbb.zzc());
        if (zzbb.zzd() != null) {
            zzi.zzu(zzbb.zzd());
        }
        zzi.zzt(zzmg.zzc(zzmg.zzb(System.currentTimeMillis())));
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049 A[Catch:{ NameNotFoundException -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062 A[Catch:{ NameNotFoundException -> 0x0077 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final com.google.android.recaptcha.internal.zzmo zzb(android.content.Context r7) {
        /*
            java.lang.String r0 = "unknown"
            r1 = 33
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ NameNotFoundException -> 0x0023 }
            java.lang.String r3 = "com.google.android.gms.version"
            r4 = -1
            if (r2 < r1) goto L_0x002a
            android.content.pm.PackageManager r2 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0023 }
            java.lang.String r5 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0023 }
            android.content.pm.PackageManager$ApplicationInfoFlags r6 = android.content.pm.PackageManager.ApplicationInfoFlags.of(128)     // Catch:{ NameNotFoundException -> 0x0023 }
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r5, r6)     // Catch:{ NameNotFoundException -> 0x0023 }
            android.os.Bundle r2 = r2.metaData     // Catch:{ NameNotFoundException -> 0x0023 }
            int r2 = r2.getInt(r3, r4)     // Catch:{ NameNotFoundException -> 0x0023 }
            if (r2 != r4) goto L_0x0025
        L_0x0023:
            r2 = r0
            goto L_0x0045
        L_0x0025:
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ NameNotFoundException -> 0x0023 }
            goto L_0x0045
        L_0x002a:
            android.content.pm.PackageManager r2 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0023 }
            java.lang.String r5 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0023 }
            r6 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r5, r6)     // Catch:{ NameNotFoundException -> 0x0023 }
            android.os.Bundle r2 = r2.metaData     // Catch:{ NameNotFoundException -> 0x0023 }
            int r2 = r2.getInt(r3, r4)     // Catch:{ NameNotFoundException -> 0x0023 }
            if (r2 != r4) goto L_0x0041
            goto L_0x0023
        L_0x0041:
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ NameNotFoundException -> 0x0023 }
        L_0x0045:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ NameNotFoundException -> 0x0077 }
            if (r3 < r1) goto L_0x0062
            android.content.pm.PackageManager r1 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0077 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0077 }
            android.content.pm.PackageManager$PackageInfoFlags r3 = android.content.pm.PackageManager.PackageInfoFlags.of(0)     // Catch:{ NameNotFoundException -> 0x0077 }
            android.content.pm.PackageInfo r7 = r1.getPackageInfo(r7, r3)     // Catch:{ NameNotFoundException -> 0x0077 }
            long r3 = r7.getLongVersionCode()     // Catch:{ NameNotFoundException -> 0x0077 }
            java.lang.String r0 = java.lang.String.valueOf(r3)     // Catch:{ NameNotFoundException -> 0x0077 }
            goto L_0x0077
        L_0x0062:
            android.content.pm.PackageManager r1 = r7.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0077 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0077 }
            r3 = 0
            android.content.pm.PackageInfo r7 = r1.getPackageInfo(r7, r3)     // Catch:{ NameNotFoundException -> 0x0077 }
            long r3 = r7.getLongVersionCode()     // Catch:{ NameNotFoundException -> 0x0077 }
            java.lang.String r0 = java.lang.String.valueOf(r3)     // Catch:{ NameNotFoundException -> 0x0077 }
        L_0x0077:
            com.google.android.recaptcha.internal.zzmn r7 = com.google.android.recaptcha.internal.zzmo.zzf()
            int r1 = android.os.Build.VERSION.SDK_INT
            r7.zzd(r1)
            r7.zzq(r2)
            java.lang.String r1 = "18.4.0"
            r7.zzs(r1)
            java.lang.String r1 = android.os.Build.MODEL
            r7.zzp(r1)
            java.lang.String r1 = android.os.Build.MANUFACTURER
            r7.zzr(r1)
            r7.zze(r0)
            com.google.android.recaptcha.internal.zzit r7 = r7.zzj()
            com.google.android.recaptcha.internal.zzmo r7 = (com.google.android.recaptcha.internal.zzmo) r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzbf.zzb(android.content.Context):com.google.android.recaptcha.internal.zzmo");
    }

    @NotNull
    public final zznf zza(@NotNull int i3, @Nullable zzmr zzmr, @NotNull Context context) {
        String str;
        String str2 = "";
        long currentTimeMillis = System.currentTimeMillis() - this.zzf;
        zznc zznc = this.zze;
        zznc.zze(currentTimeMillis);
        zznc.zzv(i3);
        if (zzmr != null) {
            this.zze.zzq(zzmr);
        }
        if (zzb == null) {
            zzb = zzb(context);
        }
        try {
            str = Locale.getDefault().getISO3Language();
        } catch (MissingResourceException unused) {
            str = str2;
        }
        try {
            str2 = Locale.getDefault().getISO3Country();
        } catch (MissingResourceException unused2) {
        }
        zznc zznc2 = this.zze;
        String str3 = this.zzc;
        zznq zzf2 = zznr.zzf();
        zzf2.zzq(str3);
        zzmo zzmo = zzb;
        if (zzmo == null) {
            zzmo = zzb(context);
        }
        zzf2.zzd(zzmo);
        zzf2.zzp(str);
        zzf2.zze(str2);
        zznc2.zzs((zznr) zzf2.zzj());
        return (zznf) this.zze.zzj();
    }
}
