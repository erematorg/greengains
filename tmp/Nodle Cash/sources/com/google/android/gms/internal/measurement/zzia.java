package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Process;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class zzia {
    @GuardedBy("DirectBootUtils.class")
    @Nullable
    private static UserManager zza;
    private static volatile boolean zzb = (!zza());

    private zzia() {
    }

    @ChecksSdkIntAtLeast(api = 24)
    public static boolean zza() {
        return true;
    }

    public static boolean zzb(Context context) {
        return !zza() || zzc(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001a, code lost:
        return r3;
     */
    @androidx.annotation.RequiresApi(24)
    @android.annotation.TargetApi(24)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zzc(android.content.Context r3) {
        /*
            boolean r0 = zzb
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.Class<com.google.android.gms.internal.measurement.zzia> r0 = com.google.android.gms.internal.measurement.zzia.class
            monitor-enter(r0)
            boolean r2 = zzb     // Catch:{ all -> 0x000f }
            if (r2 == 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return r1
        L_0x000f:
            r3 = move-exception
            goto L_0x001b
        L_0x0011:
            boolean r3 = zzd(r3)     // Catch:{ all -> 0x000f }
            if (r3 == 0) goto L_0x0019
            zzb = r3     // Catch:{ all -> 0x000f }
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return r3
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzia.zzc(android.content.Context):boolean");
    }

    @RequiresApi(24)
    @TargetApi(24)
    @GuardedBy("DirectBootUtils.class")
    private static boolean zzd(Context context) {
        boolean z2;
        boolean z3 = true;
        int i3 = 1;
        while (true) {
            z2 = false;
            if (i3 > 2) {
                break;
            }
            if (zza == null) {
                zza = (UserManager) context.getSystemService(UserManager.class);
            }
            UserManager userManager = zza;
            if (userManager == null) {
                return true;
            }
            try {
                if (!userManager.isUserUnlocked() && userManager.isUserRunning(Process.myUserHandle())) {
                    z3 = false;
                }
                z2 = z3;
            } catch (NullPointerException e3) {
                Log.w("DirectBootUtils", "Failed to check if user is unlocked.", e3);
                zza = null;
                i3++;
            }
        }
        if (z2) {
            zza = null;
        }
        return z2;
    }

    public static boolean zza(Context context) {
        return zza() && !zzc(context);
    }
}
