package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.common.base.Optional;

public final class zzip {
    private static volatile Optional<Boolean> zza = Optional.absent();
    private static final Object zzb = new Object();

    private static boolean zza(Context context) {
        try {
            if ((context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0077, code lost:
        if ("com.google.android.gms".equals(r0.packageName) != false) goto L_0x0079;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zza(android.content.Context r4, android.net.Uri r5) {
        /*
            java.lang.String r5 = r5.getAuthority()
            java.lang.String r0 = "com.google.android.gms.phenotype"
            boolean r0 = r0.equals(r5)
            r1 = 0
            if (r0 != 0) goto L_0x0024
            java.lang.String r4 = "PhenotypeClientHelper"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.String r5 = " is an unsupported authority. Only com.google.android.gms.phenotype authority is supported."
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            android.util.Log.e(r4, r5)
            return r1
        L_0x0024:
            com.google.common.base.Optional<java.lang.Boolean> r5 = zza
            boolean r5 = r5.isPresent()
            if (r5 == 0) goto L_0x0039
            com.google.common.base.Optional<java.lang.Boolean> r4 = zza
            java.lang.Object r4 = r4.get()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            return r4
        L_0x0039:
            java.lang.Object r5 = zzb
            monitor-enter(r5)
            com.google.common.base.Optional<java.lang.Boolean> r0 = zza     // Catch:{ all -> 0x0052 }
            boolean r0 = r0.isPresent()     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0054
            com.google.common.base.Optional<java.lang.Boolean> r4 = zza     // Catch:{ all -> 0x0052 }
            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x0052 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x0052 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0052 }
            monitor-exit(r5)     // Catch:{ all -> 0x0052 }
            return r4
        L_0x0052:
            r4 = move-exception
            goto L_0x0098
        L_0x0054:
            java.lang.String r0 = "com.google.android.gms"
            java.lang.String r2 = r4.getPackageName()     // Catch:{ all -> 0x0052 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0061
            goto L_0x0079
        L_0x0061:
            android.content.pm.PackageManager r0 = r4.getPackageManager()     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = "com.google.android.gms.phenotype"
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            android.content.pm.ProviderInfo r0 = r0.resolveContentProvider(r2, r3)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0080
            java.lang.String r2 = "com.google.android.gms"
            java.lang.String r0 = r0.packageName     // Catch:{ all -> 0x0052 }
            boolean r0 = r2.equals(r0)     // Catch:{ all -> 0x0052 }
            if (r0 == 0) goto L_0x0080
        L_0x0079:
            boolean r4 = zza(r4)     // Catch:{ all -> 0x0052 }
            if (r4 == 0) goto L_0x0080
            r1 = 1
        L_0x0080:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x0052 }
            com.google.common.base.Optional r4 = com.google.common.base.Optional.of(r4)     // Catch:{ all -> 0x0052 }
            zza = r4     // Catch:{ all -> 0x0052 }
            monitor-exit(r5)     // Catch:{ all -> 0x0052 }
            com.google.common.base.Optional<java.lang.Boolean> r4 = zza
            java.lang.Object r4 = r4.get()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            return r4
        L_0x0098:
            monitor-exit(r5)     // Catch:{ all -> 0x0052 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzip.zza(android.content.Context, android.net.Uri):boolean");
    }
}
