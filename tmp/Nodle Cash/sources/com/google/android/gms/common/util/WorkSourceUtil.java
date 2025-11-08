package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@KeepForSdk
public class WorkSourceUtil {
    private static final int zza = Process.myUid();
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;
    private static final Method zze;
    private static final Method zzf;
    private static final Method zzg;
    private static final Method zzh;
    private static final Method zzi;
    @GuardedBy("WorkSourceUtil.class")
    private static Boolean zzj = null;

    /* JADX WARNING: Removed duplicated region for block: B:23:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x009f  */
    static {
        /*
            java.lang.String r0 = "add"
            java.lang.Class<android.os.WorkSource> r1 = android.os.WorkSource.class
            int r2 = android.os.Process.myUid()
            zza = r2
            r2 = 0
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0016 }
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch:{ Exception -> 0x0016 }
            java.lang.reflect.Method r3 = r1.getMethod(r0, r3)     // Catch:{ Exception -> 0x0016 }
            goto L_0x0017
        L_0x0016:
            r3 = r2
        L_0x0017:
            zzb = r3
            boolean r3 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR2()
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r3 == 0) goto L_0x002c
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x002c }
            java.lang.Class[] r3 = new java.lang.Class[]{r3, r4}     // Catch:{ Exception -> 0x002c }
            java.lang.reflect.Method r0 = r1.getMethod(r0, r3)     // Catch:{ Exception -> 0x002c }
            goto L_0x002d
        L_0x002c:
            r0 = r2
        L_0x002d:
            zzc = r0
            java.lang.String r0 = "size"
            java.lang.reflect.Method r0 = r1.getMethod(r0, r2)     // Catch:{ Exception -> 0x0036 }
            goto L_0x0037
        L_0x0036:
            r0 = r2
        L_0x0037:
            zzd = r0
            java.lang.String r0 = "get"
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0046 }
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch:{ Exception -> 0x0046 }
            java.lang.reflect.Method r0 = r1.getMethod(r0, r3)     // Catch:{ Exception -> 0x0046 }
            goto L_0x0047
        L_0x0046:
            r0 = r2
        L_0x0047:
            zze = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR2()
            if (r0 == 0) goto L_0x005c
            java.lang.String r0 = "getName"
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x005c }
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch:{ Exception -> 0x005c }
            java.lang.reflect.Method r0 = r1.getMethod(r0, r3)     // Catch:{ Exception -> 0x005c }
            goto L_0x005d
        L_0x005c:
            r0 = r2
        L_0x005d:
            zzf = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            java.lang.String r3 = "WorkSourceUtil"
            if (r0 == 0) goto L_0x0074
            java.lang.String r0 = "createWorkChain"
            java.lang.reflect.Method r0 = r1.getMethod(r0, r2)     // Catch:{ Exception -> 0x006e }
            goto L_0x0075
        L_0x006e:
            r0 = move-exception
            java.lang.String r5 = "Missing WorkChain API createWorkChain"
            android.util.Log.w(r3, r5, r0)
        L_0x0074:
            r0 = r2
        L_0x0075:
            zzg = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            if (r0 == 0) goto L_0x0096
            java.lang.String r0 = "android.os.WorkSource$WorkChain"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r5 = "addNode"
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0090 }
            java.lang.Class[] r4 = new java.lang.Class[]{r6, r4}     // Catch:{ Exception -> 0x0090 }
            java.lang.reflect.Method r0 = r0.getMethod(r5, r4)     // Catch:{ Exception -> 0x0090 }
            goto L_0x0097
        L_0x0090:
            r0 = move-exception
            java.lang.String r4 = "Missing WorkChain class"
            android.util.Log.w(r3, r4, r0)
        L_0x0096:
            r0 = r2
        L_0x0097:
            zzh = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            if (r0 == 0) goto L_0x00aa
            java.lang.String r0 = "isEmpty"
            java.lang.reflect.Method r0 = r1.getMethod(r0, r2)     // Catch:{ Exception -> 0x00aa }
            r1 = 1
            r0.setAccessible(r1)     // Catch:{ Exception -> 0x00ab }
            goto L_0x00ab
        L_0x00aa:
            r0 = r2
        L_0x00ab:
            zzi = r0
            zzj = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.WorkSourceUtil.<clinit>():void");
    }

    private WorkSourceUtil() {
    }

    @KeepForSdk
    public static void add(@NonNull WorkSource workSource, int i3, @NonNull String str) {
        Method method = zzc;
        if (method != null) {
            if (str == null) {
                str = "";
            }
            try {
                method.invoke(workSource, new Object[]{Integer.valueOf(i3), str});
            } catch (Exception e3) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e3);
            }
        } else {
            Method method2 = zzb;
            if (method2 != null) {
                try {
                    method2.invoke(workSource, new Object[]{Integer.valueOf(i3)});
                } catch (Exception e4) {
                    Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e4);
                }
            }
        }
    }

    @NonNull
    @KeepForSdk
    public static WorkSource fromPackage(@NonNull Context context, @NonNull String str) {
        if (!(context == null || context.getPackageManager() == null || str == null)) {
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
                    return null;
                }
                int i3 = applicationInfo.uid;
                WorkSource workSource = new WorkSource();
                add(workSource, i3, str);
                return workSource;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
            }
        }
        return null;
    }

    @NonNull
    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        Method method;
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int i3 = -1;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
            } else {
                i3 = applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
        }
        if (i3 < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        Method method2 = zzg;
        if (method2 == null || (method = zzh) == null) {
            add(workSource, i3, str);
        } else {
            try {
                Object invoke = method2.invoke(workSource, (Object[]) null);
                int i4 = zza;
                if (i3 != i4) {
                    method.invoke(invoke, new Object[]{Integer.valueOf(i3), str});
                }
                method.invoke(invoke, new Object[]{Integer.valueOf(i4), str2});
            } catch (Exception e3) {
                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e3);
            }
        }
        return workSource;
    }

    @KeepForSdk
    public static int get(@NonNull WorkSource workSource, int i3) {
        Method method = zze;
        if (method == null) {
            return 0;
        }
        try {
            Object invoke = method.invoke(workSource, new Object[]{Integer.valueOf(i3)});
            Preconditions.checkNotNull(invoke);
            return ((Integer) invoke).intValue();
        } catch (Exception e3) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e3);
            return 0;
        }
    }

    @NonNull
    @KeepForSdk
    public static String getName(@NonNull WorkSource workSource, int i3) {
        Method method = zzf;
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke(workSource, new Object[]{Integer.valueOf(i3)});
        } catch (Exception e3) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e3);
            return null;
        }
    }

    @NonNull
    @KeepForSdk
    public static List<String> getNames(@NonNull WorkSource workSource) {
        ArrayList arrayList = new ArrayList();
        int size = workSource == null ? 0 : size(workSource);
        if (size != 0) {
            for (int i3 = 0; i3 < size; i3++) {
                String name = getName(workSource, i3);
                if (!Strings.isEmptyOrWhitespace(name)) {
                    Preconditions.checkNotNull(name);
                    arrayList.add(name);
                }
            }
        }
        return arrayList;
    }

    @KeepForSdk
    public static synchronized boolean hasWorkSourcePermission(@NonNull Context context) {
        synchronized (WorkSourceUtil.class) {
            Boolean bool = zzj;
            if (bool != null) {
                boolean booleanValue = bool.booleanValue();
                return booleanValue;
            }
            boolean z2 = false;
            if (context == null) {
                return false;
            }
            if (ContextCompat.checkSelfPermission(context, "android.permission.UPDATE_DEVICE_STATS") == 0) {
                z2 = true;
            }
            zzj = Boolean.valueOf(z2);
            return z2;
        }
    }

    @KeepForSdk
    public static boolean isEmpty(@NonNull WorkSource workSource) {
        Method method = zzi;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, (Object[]) null);
                Preconditions.checkNotNull(invoke);
                return ((Boolean) invoke).booleanValue();
            } catch (Exception e3) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e3);
            }
        }
        return size(workSource) == 0;
    }

    @KeepForSdk
    public static int size(@NonNull WorkSource workSource) {
        Method method = zzd;
        if (method == null) {
            return 0;
        }
        try {
            Object invoke = method.invoke(workSource, (Object[]) null);
            Preconditions.checkNotNull(invoke);
            return ((Integer) invoke).intValue();
        } catch (Exception e3) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e3);
            return 0;
        }
    }
}
