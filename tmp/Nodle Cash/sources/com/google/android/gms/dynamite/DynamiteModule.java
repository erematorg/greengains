package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@KeepForSdk
public final class DynamiteModule {
    @KeepForSdk
    public static final int LOCAL = -1;
    @KeepForSdk
    public static final int NONE = 0;
    @KeepForSdk
    public static final int NO_SELECTION = 0;
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    @NonNull
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    @KeepForSdk
    public static final int REMOTE = 1;
    @NonNull
    public static final VersionPolicy zza = new zzl();
    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzb = null;
    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static String zzc = null;
    @GuardedBy("DynamiteModule.class")
    private static boolean zzd = false;
    @GuardedBy("DynamiteModule.class")
    private static int zze = -1;
    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzf;
    private static final ThreadLocal zzg = new ThreadLocal();
    private static final ThreadLocal zzh = new zzd();
    private static final VersionPolicy.IVersions zzi = new zze();
    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static zzq zzk;
    @Nullable
    @GuardedBy("DynamiteModule.class")
    private static zzr zzl;
    private final Context zzj;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        @Nullable
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        public /* synthetic */ LoadingException(String str, zzp zzp) {
            super(str);
        }

        public /* synthetic */ LoadingException(String str, Throwable th, zzp zzp) {
            super(str, th);
        }
    }

    public interface VersionPolicy {

        @KeepForSdk
        public interface IVersions {
            int zza(@NonNull Context context, @NonNull String str);

            int zzb(@NonNull Context context, @NonNull String str, boolean z2) throws LoadingException;
        }

        @KeepForSdk
        public static class SelectionResult {
            @KeepForSdk
            public int localVersion = 0;
            @KeepForSdk
            public int remoteVersion = 0;
            @KeepForSdk
            public int selection = 0;
        }

        @NonNull
        @KeepForSdk
        SelectionResult selectModule(@NonNull Context context, @NonNull String str, @NonNull IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzj = context;
    }

    @KeepForSdk
    public static int getLocalVersion(@NonNull Context context, @NonNull String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            Class<?> loadClass = classLoader.loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get((Object) null), str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf = String.valueOf(declaredField.get((Object) null));
            Log.e("DynamiteModule", "Module descriptor id '" + valueOf + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e3) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e3.getMessage())));
            return 0;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(@NonNull Context context, @NonNull String str) {
        return zza(context, str, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:153:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x02bd  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02c6  */
    @com.google.errorprone.annotations.ResultIgnorabilityUnspecified
    @androidx.annotation.NonNull
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.dynamite.DynamiteModule load(@androidx.annotation.NonNull android.content.Context r22, @androidx.annotation.NonNull com.google.android.gms.dynamite.DynamiteModule.VersionPolicy r23, @androidx.annotation.NonNull java.lang.String r24) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r1 = r22
            r2 = r23
            r3 = r24
            java.lang.String r0 = "No acceptable module "
            java.lang.String r4 = "VersionPolicy returned invalid code:"
            java.lang.String r5 = "Selected remote version of "
            java.lang.String r6 = "Selected remote version of "
            java.lang.String r7 = "Considering local module "
            android.content.Context r8 = r22.getApplicationContext()
            r9 = 0
            if (r8 == 0) goto L_0x02cf
            java.lang.ThreadLocal r10 = zzg
            java.lang.Object r11 = r10.get()
            com.google.android.gms.dynamite.zzn r11 = (com.google.android.gms.dynamite.zzn) r11
            com.google.android.gms.dynamite.zzn r12 = new com.google.android.gms.dynamite.zzn
            r12.<init>(r9)
            r10.set(r12)
            java.lang.ThreadLocal r13 = zzh
            java.lang.Object r14 = r13.get()
            java.lang.Long r14 = (java.lang.Long) r14
            long r15 = r14.longValue()
            r17 = 0
            long r19 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x026c }
            java.lang.Long r9 = java.lang.Long.valueOf(r19)     // Catch:{ all -> 0x026c }
            r13.set(r9)     // Catch:{ all -> 0x026c }
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$IVersions r9 = zzi     // Catch:{ all -> 0x026c }
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$SelectionResult r9 = r2.selectModule(r1, r3, r9)     // Catch:{ all -> 0x026c }
            java.lang.String r13 = "DynamiteModule"
            r19 = r0
            int r0 = r9.localVersion     // Catch:{ all -> 0x026c }
            r20 = r4
            int r4 = r9.remoteVersion     // Catch:{ all -> 0x026c }
            r21 = r11
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x008a }
            r11.<init>(r7)     // Catch:{ all -> 0x008a }
            r11.append(r3)     // Catch:{ all -> 0x008a }
            java.lang.String r7 = ":"
            r11.append(r7)     // Catch:{ all -> 0x008a }
            r11.append(r0)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = " and remote module "
            r11.append(r0)     // Catch:{ all -> 0x008a }
            r11.append(r3)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = ":"
            r11.append(r0)     // Catch:{ all -> 0x008a }
            r11.append(r4)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r11.toString()     // Catch:{ all -> 0x008a }
            android.util.Log.i(r13, r0)     // Catch:{ all -> 0x008a }
            int r0 = r9.selection     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0086
            r4 = -1
            if (r0 != r4) goto L_0x008f
            int r0 = r9.localVersion     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0086
            r0 = r4
            goto L_0x008f
        L_0x0086:
            r11 = r21
            goto L_0x0285
        L_0x008a:
            r0 = move-exception
            r11 = r21
            goto L_0x02b3
        L_0x008f:
            r7 = 1
            if (r0 != r7) goto L_0x0096
            int r11 = r9.remoteVersion     // Catch:{ all -> 0x008a }
            if (r11 == 0) goto L_0x0086
        L_0x0096:
            if (r0 != r4) goto L_0x009e
            com.google.android.gms.dynamite.DynamiteModule r0 = zzc(r8, r3)     // Catch:{ all -> 0x008a }
            goto L_0x0243
        L_0x009e:
            if (r0 != r7) goto L_0x026e
            r11 = 0
            int r0 = r9.remoteVersion     // Catch:{ LoadingException -> 0x0207 }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r13 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r13)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            boolean r19 = zzf(r22)     // Catch:{ all -> 0x01ee }
            if (r19 == 0) goto L_0x01f0
            java.lang.Boolean r19 = zzb     // Catch:{ all -> 0x01ee }
            monitor-exit(r13)     // Catch:{ all -> 0x01ee }
            if (r19 == 0) goto L_0x01e5
            boolean r13 = r19.booleanValue()     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r7 = 2
            if (r13 == 0) goto L_0x015d
            java.lang.String r5 = "DynamiteModule"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r13.<init>(r6)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r13.append(r3)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r6 = ", version >= "
            r13.append(r6)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r13.append(r0)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r6 = r13.toString()     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            android.util.Log.i(r5, r6)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r5 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamite.zzr r6 = zzl     // Catch:{ all -> 0x015a }
            monitor-exit(r5)     // Catch:{ all -> 0x015a }
            if (r6 == 0) goto L_0x0151
            java.lang.Object r5 = r10.get()     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamite.zzn r5 = (com.google.android.gms.dynamite.zzn) r5     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            if (r5 == 0) goto L_0x0148
            android.database.Cursor r10 = r5.zza     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            if (r10 == 0) goto L_0x0148
            android.content.Context r10 = r22.getApplicationContext()     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            android.database.Cursor r5 = r5.zza     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r13 = 0
            com.google.android.gms.dynamic.ObjectWrapper.wrap(r13)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r13 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r13)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            int r4 = zze     // Catch:{ all -> 0x0145 }
            if (r4 < r7) goto L_0x00f8
            r7 = 1
            goto L_0x00f9
        L_0x00f8:
            r7 = r11
        L_0x00f9:
            monitor-exit(r13)     // Catch:{ all -> 0x0145 }
            if (r7 == 0) goto L_0x0119
            java.lang.String r4 = "DynamiteModule"
            java.lang.String r7 = "Dynamite loader version >= 2, using loadModule2NoCrashUtils"
            android.util.Log.v(r4, r7)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r10)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = r6.zzf(r4, r3, r0, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            goto L_0x012c
        L_0x0110:
            r0 = move-exception
            goto L_0x01fb
        L_0x0113:
            r0 = move-exception
            goto L_0x0209
        L_0x0116:
            r0 = move-exception
            goto L_0x020a
        L_0x0119:
            java.lang.String r4 = "DynamiteModule"
            java.lang.String r7 = "Dynamite loader version < 2, falling back to loadModule2"
            android.util.Log.w(r4, r7)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r10)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = r6.zze(r4, r3, r0, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x012c:
            java.lang.Object r0 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r0)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            if (r0 == 0) goto L_0x013c
            com.google.android.gms.dynamite.DynamiteModule r4 = new com.google.android.gms.dynamite.DynamiteModule     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r4.<init>(r0)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x0139:
            r0 = r4
            goto L_0x0243
        L_0x013c:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r4 = "Failed to get module context"
            r5 = 0
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x0145:
            r0 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x0145 }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x0148:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r4 = "No result cursor"
            r5 = 0
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x0151:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r4 = "DynamiteLoaderV2 was not cached."
            r5 = 0
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x015a:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x015a }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x015d:
            java.lang.String r4 = "DynamiteModule"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r6.<init>(r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r6.append(r3)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r5 = ", version >= "
            r6.append(r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r6.append(r0)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r5 = r6.toString()     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            android.util.Log.i(r4, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamite.zzq r4 = zzg(r22)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            if (r4 == 0) goto L_0x01dc
            int r5 = r4.zze()     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r6 = 3
            if (r5 < r6) goto L_0x01a3
            java.lang.Object r5 = r10.get()     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamite.zzn r5 = (com.google.android.gms.dynamite.zzn) r5     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            if (r5 == 0) goto L_0x019a
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r22)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            android.database.Cursor r5 = r5.zza     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = r4.zzi(r6, r3, r0, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            goto L_0x01c4
        L_0x019a:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r4 = "No cached result cursor holder"
            r5 = 0
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x01a3:
            if (r5 != r7) goto L_0x01b5
            java.lang.String r5 = "DynamiteModule"
            java.lang.String r6 = "IDynamite loader version = 2"
            android.util.Log.w(r5, r6)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r22)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = r4.zzj(r5, r3, r0)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            goto L_0x01c4
        L_0x01b5:
            java.lang.String r5 = "DynamiteModule"
            java.lang.String r6 = "Dynamite loader version < 2, falling back to createModuleContext"
            android.util.Log.w(r5, r6)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r22)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = r4.zzh(r5, r3, r0)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x01c4:
            java.lang.Object r0 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r0)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            if (r0 == 0) goto L_0x01d3
            com.google.android.gms.dynamite.DynamiteModule r4 = new com.google.android.gms.dynamite.DynamiteModule     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            r4.<init>(r0)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            goto L_0x0139
        L_0x01d3:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r4 = "Failed to load remote module."
            r5 = 0
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x01dc:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r4 = "Failed to create IDynamiteLoader."
            r5 = 0
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x01e5:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            java.lang.String r4 = "Failed to determine which loading route to use."
            r5 = 0
            r0.<init>(r4, r5)     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x01ee:
            r0 = move-exception
            goto L_0x01f9
        L_0x01f0:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x01ee }
            java.lang.String r4 = "Remote loading disabled"
            r5 = 0
            r0.<init>(r4, r5)     // Catch:{ all -> 0x01ee }
            throw r0     // Catch:{ all -> 0x01ee }
        L_0x01f9:
            monitor-exit(r13)     // Catch:{ all -> 0x01ee }
            throw r0     // Catch:{ RemoteException -> 0x0116, LoadingException -> 0x0113, all -> 0x0110 }
        L_0x01fb:
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r1, r0)     // Catch:{ LoadingException -> 0x0207 }
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r4 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ LoadingException -> 0x0207 }
            java.lang.String r5 = "Failed to load remote module."
            r6 = 0
            r4.<init>(r5, r0, r6)     // Catch:{ LoadingException -> 0x0207 }
            throw r4     // Catch:{ LoadingException -> 0x0207 }
        L_0x0207:
            r0 = move-exception
            goto L_0x0213
        L_0x0209:
            throw r0     // Catch:{ LoadingException -> 0x0207 }
        L_0x020a:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r4 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ LoadingException -> 0x0207 }
            java.lang.String r5 = "Failed to load remote module."
            r6 = 0
            r4.<init>(r5, r0, r6)     // Catch:{ LoadingException -> 0x0207 }
            throw r4     // Catch:{ LoadingException -> 0x0207 }
        L_0x0213:
            java.lang.String r4 = "DynamiteModule"
            java.lang.String r5 = r0.getMessage()     // Catch:{ all -> 0x008a }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x008a }
            r6.<init>()     // Catch:{ all -> 0x008a }
            java.lang.String r7 = "Failed to load remote module: "
            r6.append(r7)     // Catch:{ all -> 0x008a }
            r6.append(r5)     // Catch:{ all -> 0x008a }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x008a }
            android.util.Log.w(r4, r5)     // Catch:{ all -> 0x008a }
            int r4 = r9.localVersion     // Catch:{ all -> 0x008a }
            if (r4 == 0) goto L_0x0261
            com.google.android.gms.dynamite.zzo r5 = new com.google.android.gms.dynamite.zzo     // Catch:{ all -> 0x008a }
            r5.<init>(r4, r11)     // Catch:{ all -> 0x008a }
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$SelectionResult r1 = r2.selectModule(r1, r3, r5)     // Catch:{ all -> 0x008a }
            int r1 = r1.selection     // Catch:{ all -> 0x008a }
            r2 = -1
            if (r1 != r2) goto L_0x0261
            com.google.android.gms.dynamite.DynamiteModule r0 = zzc(r8, r3)     // Catch:{ all -> 0x008a }
        L_0x0243:
            int r1 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r1 != 0) goto L_0x024d
            java.lang.ThreadLocal r1 = zzh
            r1.remove()
            goto L_0x0252
        L_0x024d:
            java.lang.ThreadLocal r1 = zzh
            r1.set(r14)
        L_0x0252:
            android.database.Cursor r1 = r12.zza
            if (r1 == 0) goto L_0x0259
            r1.close()
        L_0x0259:
            java.lang.ThreadLocal r1 = zzg
            r11 = r21
            r1.set(r11)
            return r0
        L_0x0261:
            r11 = r21
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r1 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x026c }
            java.lang.String r2 = "Remote load failed. No local fallback found."
            r3 = 0
            r1.<init>(r2, r0, r3)     // Catch:{ all -> 0x026c }
            throw r1     // Catch:{ all -> 0x026c }
        L_0x026c:
            r0 = move-exception
            goto L_0x02b3
        L_0x026e:
            r11 = r21
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r1 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x026c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x026c }
            r3 = r20
            r2.<init>(r3)     // Catch:{ all -> 0x026c }
            r2.append(r0)     // Catch:{ all -> 0x026c }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x026c }
            r2 = 0
            r1.<init>(r0, r2)     // Catch:{ all -> 0x026c }
            throw r1     // Catch:{ all -> 0x026c }
        L_0x0285:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x026c }
            int r1 = r9.localVersion     // Catch:{ all -> 0x026c }
            int r2 = r9.remoteVersion     // Catch:{ all -> 0x026c }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x026c }
            r5 = r19
            r4.<init>(r5)     // Catch:{ all -> 0x026c }
            r4.append(r3)     // Catch:{ all -> 0x026c }
            java.lang.String r3 = " found. Local version is "
            r4.append(r3)     // Catch:{ all -> 0x026c }
            r4.append(r1)     // Catch:{ all -> 0x026c }
            java.lang.String r1 = " and remote version is "
            r4.append(r1)     // Catch:{ all -> 0x026c }
            r4.append(r2)     // Catch:{ all -> 0x026c }
            java.lang.String r1 = "."
            r4.append(r1)     // Catch:{ all -> 0x026c }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x026c }
            r2 = 0
            r0.<init>(r1, r2)     // Catch:{ all -> 0x026c }
            throw r0     // Catch:{ all -> 0x026c }
        L_0x02b3:
            int r1 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r1 != 0) goto L_0x02bd
            java.lang.ThreadLocal r1 = zzh
            r1.remove()
            goto L_0x02c2
        L_0x02bd:
            java.lang.ThreadLocal r1 = zzh
            r1.set(r14)
        L_0x02c2:
            android.database.Cursor r1 = r12.zza
            if (r1 == 0) goto L_0x02c9
            r1.close()
        L_0x02c9:
            java.lang.ThreadLocal r1 = zzg
            r1.set(r11)
            throw r0
        L_0x02cf:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r0 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException
            java.lang.String r1 = "null application Context"
            r2 = 0
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.load(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$VersionPolicy, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
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
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:49:0x008a=Splitter:B:49:0x008a, B:33:0x005b=Splitter:B:33:0x005b, B:18:0x003e=Splitter:B:18:0x003e} */
    public static int zza(@androidx.annotation.NonNull android.content.Context r10, @androidx.annotation.NonNull java.lang.String r11, boolean r12) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r0 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r0)     // Catch:{ all -> 0x00cf }
            java.lang.Boolean r1 = zzb     // Catch:{ all -> 0x004a }
            r2 = 0
            r3 = 0
            if (r1 != 0) goto L_0x00c3
            android.content.Context r1 = r10.getApplicationContext()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a4 }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a4 }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r4 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r4 = r4.getName()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a4 }
            java.lang.Class r1 = r1.loadClass(r4)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a4 }
            java.lang.String r4 = "sClassLoader"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r4)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a4 }
            java.lang.Class r4 = r1.getDeclaringClass()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a4 }
            monitor-enter(r4)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a4 }
            java.lang.Object r5 = r1.get(r2)     // Catch:{ all -> 0x0036 }
            java.lang.ClassLoader r5 = (java.lang.ClassLoader) r5     // Catch:{ all -> 0x0036 }
            java.lang.ClassLoader r6 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0036 }
            if (r5 != r6) goto L_0x0039
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0036 }
            goto L_0x00a0
        L_0x0036:
            r1 = move-exception
            goto L_0x00a2
        L_0x0039:
            if (r5 == 0) goto L_0x0041
            zzd(r5)     // Catch:{ LoadingException -> 0x003e }
        L_0x003e:
            java.lang.Boolean r1 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0036 }
            goto L_0x00a0
        L_0x0041:
            boolean r5 = zzf(r10)     // Catch:{ all -> 0x0036 }
            if (r5 != 0) goto L_0x004d
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r3
        L_0x004a:
            r11 = move-exception
            goto L_0x01b1
        L_0x004d:
            boolean r5 = zzd     // Catch:{ all -> 0x0036 }
            if (r5 != 0) goto L_0x0097
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0036 }
            boolean r6 = r5.equals(r2)     // Catch:{ all -> 0x0036 }
            if (r6 == 0) goto L_0x005a
            goto L_0x0097
        L_0x005a:
            r6 = 1
            int r6 = zzb(r10, r11, r12, r6)     // Catch:{ LoadingException -> 0x008d }
            java.lang.String r7 = zzc     // Catch:{ LoadingException -> 0x008d }
            if (r7 == 0) goto L_0x008a
            boolean r7 = r7.isEmpty()     // Catch:{ LoadingException -> 0x008d }
            if (r7 == 0) goto L_0x006a
            goto L_0x008a
        L_0x006a:
            java.lang.ClassLoader r7 = com.google.android.gms.dynamite.zzb.zza()     // Catch:{ LoadingException -> 0x008d }
            if (r7 == 0) goto L_0x0071
            goto L_0x007f
        L_0x0071:
            dalvik.system.DelegateLastClassLoader r7 = new dalvik.system.DelegateLastClassLoader     // Catch:{ LoadingException -> 0x008d }
            java.lang.String r8 = zzc     // Catch:{ LoadingException -> 0x008d }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ LoadingException -> 0x008d }
            java.lang.ClassLoader r9 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ LoadingException -> 0x008d }
            r7.<init>(r8, r9)     // Catch:{ LoadingException -> 0x008d }
        L_0x007f:
            zzd(r7)     // Catch:{ LoadingException -> 0x008d }
            r1.set(r2, r7)     // Catch:{ LoadingException -> 0x008d }
            zzb = r5     // Catch:{ LoadingException -> 0x008d }
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r6
        L_0x008a:
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            return r6
        L_0x008d:
            java.lang.ClassLoader r5 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0036 }
            r1.set(r2, r5)     // Catch:{ all -> 0x0036 }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0036 }
            goto L_0x00a0
        L_0x0097:
            java.lang.ClassLoader r5 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0036 }
            r1.set(r2, r5)     // Catch:{ all -> 0x0036 }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0036 }
        L_0x00a0:
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            goto L_0x00c1
        L_0x00a2:
            monitor-exit(r4)     // Catch:{ all -> 0x0036 }
            throw r1     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x00a4 }
        L_0x00a4:
            r1 = move-exception
            java.lang.String r4 = "DynamiteModule"
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x004a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x004a }
            r5.<init>()     // Catch:{ all -> 0x004a }
            java.lang.String r6 = "Failed to load module via V2: "
            r5.append(r6)     // Catch:{ all -> 0x004a }
            r5.append(r1)     // Catch:{ all -> 0x004a }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x004a }
            android.util.Log.w(r4, r1)     // Catch:{ all -> 0x004a }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x004a }
        L_0x00c1:
            zzb = r1     // Catch:{ all -> 0x004a }
        L_0x00c3:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            boolean r0 = r1.booleanValue()     // Catch:{ all -> 0x00cf }
            if (r0 == 0) goto L_0x00ee
            int r10 = zzb(r10, r11, r12, r3)     // Catch:{ LoadingException -> 0x00d2 }
            return r10
        L_0x00cf:
            r11 = move-exception
            goto L_0x01b3
        L_0x00d2:
            r11 = move-exception
            java.lang.String r12 = "DynamiteModule"
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x00cf }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cf }
            r0.<init>()     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = "Failed to retrieve remote module version: "
            r0.append(r1)     // Catch:{ all -> 0x00cf }
            r0.append(r11)     // Catch:{ all -> 0x00cf }
            java.lang.String r11 = r0.toString()     // Catch:{ all -> 0x00cf }
            android.util.Log.w(r12, r11)     // Catch:{ all -> 0x00cf }
            return r3
        L_0x00ee:
            com.google.android.gms.dynamite.zzq r4 = zzg(r10)     // Catch:{ all -> 0x00cf }
            if (r4 != 0) goto L_0x00f6
            goto L_0x01a8
        L_0x00f6:
            int r0 = r4.zze()     // Catch:{ RemoteException -> 0x0111 }
            r1 = 3
            if (r0 < r1) goto L_0x0163
            java.lang.ThreadLocal r0 = zzg     // Catch:{ RemoteException -> 0x0111 }
            java.lang.Object r0 = r0.get()     // Catch:{ RemoteException -> 0x0111 }
            com.google.android.gms.dynamite.zzn r0 = (com.google.android.gms.dynamite.zzn) r0     // Catch:{ RemoteException -> 0x0111 }
            if (r0 == 0) goto L_0x0114
            android.database.Cursor r0 = r0.zza     // Catch:{ RemoteException -> 0x0111 }
            if (r0 == 0) goto L_0x0114
            int r3 = r0.getInt(r3)     // Catch:{ RemoteException -> 0x0111 }
            goto L_0x01a8
        L_0x0111:
            r11 = move-exception
            goto L_0x0188
        L_0x0114:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r10)     // Catch:{ RemoteException -> 0x0111 }
            java.lang.ThreadLocal r0 = zzh     // Catch:{ RemoteException -> 0x0111 }
            java.lang.Object r0 = r0.get()     // Catch:{ RemoteException -> 0x0111 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ RemoteException -> 0x0111 }
            long r8 = r0.longValue()     // Catch:{ RemoteException -> 0x0111 }
            r6 = r11
            r7 = r12
            com.google.android.gms.dynamic.IObjectWrapper r11 = r4.zzk(r5, r6, r7, r8)     // Catch:{ RemoteException -> 0x0111 }
            java.lang.Object r11 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r11)     // Catch:{ RemoteException -> 0x0111 }
            android.database.Cursor r11 = (android.database.Cursor) r11     // Catch:{ RemoteException -> 0x0111 }
            if (r11 == 0) goto L_0x0152
            boolean r12 = r11.moveToFirst()     // Catch:{ RemoteException -> 0x0148, all -> 0x0146 }
            if (r12 != 0) goto L_0x0139
            goto L_0x0152
        L_0x0139:
            int r12 = r11.getInt(r3)     // Catch:{ RemoteException -> 0x0148, all -> 0x0146 }
            if (r12 <= 0) goto L_0x014a
            boolean r0 = zze(r11)     // Catch:{ RemoteException -> 0x0148, all -> 0x0146 }
            if (r0 == 0) goto L_0x014a
            goto L_0x014b
        L_0x0146:
            r12 = move-exception
            goto L_0x015f
        L_0x0148:
            r12 = move-exception
            goto L_0x0161
        L_0x014a:
            r2 = r11
        L_0x014b:
            if (r2 == 0) goto L_0x0150
            r2.close()     // Catch:{ all -> 0x00cf }
        L_0x0150:
            r3 = r12
            goto L_0x01a8
        L_0x0152:
            java.lang.String r12 = "DynamiteModule"
            java.lang.String r0 = "Failed to retrieve remote module version."
            android.util.Log.w(r12, r0)     // Catch:{ RemoteException -> 0x0148, all -> 0x0146 }
            if (r11 == 0) goto L_0x01a8
            r11.close()     // Catch:{ all -> 0x00cf }
            goto L_0x01a8
        L_0x015f:
            r2 = r11
            goto L_0x01ab
        L_0x0161:
            r2 = r11
            goto L_0x0189
        L_0x0163:
            r1 = 2
            if (r0 != r1) goto L_0x0176
            java.lang.String r0 = "DynamiteModule"
            java.lang.String r1 = "IDynamite loader version = 2, no high precision latency measurement."
            android.util.Log.w(r0, r1)     // Catch:{ RemoteException -> 0x0111 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r10)     // Catch:{ RemoteException -> 0x0111 }
            int r3 = r4.zzg(r0, r11, r12)     // Catch:{ RemoteException -> 0x0111 }
            goto L_0x01a8
        L_0x0176:
            java.lang.String r0 = "DynamiteModule"
            java.lang.String r1 = "IDynamite loader version < 2, falling back to getModuleVersion2"
            android.util.Log.w(r0, r1)     // Catch:{ RemoteException -> 0x0111 }
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r10)     // Catch:{ RemoteException -> 0x0111 }
            int r3 = r4.zzf(r0, r11, r12)     // Catch:{ RemoteException -> 0x0111 }
            goto L_0x01a8
        L_0x0186:
            r12 = r11
            goto L_0x01ab
        L_0x0188:
            r12 = r11
        L_0x0189:
            java.lang.String r11 = "DynamiteModule"
            java.lang.String r12 = r12.getMessage()     // Catch:{ all -> 0x01a9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a9 }
            r0.<init>()     // Catch:{ all -> 0x01a9 }
            java.lang.String r1 = "Failed to retrieve remote module version: "
            r0.append(r1)     // Catch:{ all -> 0x01a9 }
            r0.append(r12)     // Catch:{ all -> 0x01a9 }
            java.lang.String r12 = r0.toString()     // Catch:{ all -> 0x01a9 }
            android.util.Log.w(r11, r12)     // Catch:{ all -> 0x01a9 }
            if (r2 == 0) goto L_0x01a8
            r2.close()     // Catch:{ all -> 0x00cf }
        L_0x01a8:
            return r3
        L_0x01a9:
            r11 = move-exception
            goto L_0x0186
        L_0x01ab:
            if (r2 == 0) goto L_0x01b0
            r2.close()     // Catch:{ all -> 0x00cf }
        L_0x01b0:
            throw r12     // Catch:{ all -> 0x00cf }
        L_0x01b1:
            monitor-exit(r0)     // Catch:{ all -> 0x004a }
            throw r11     // Catch:{ all -> 0x00cf }
        L_0x01b3:
            com.google.android.gms.common.util.CrashUtils.addDynamiteErrorToDropBox(r10, r11)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c2 A[Catch:{ all -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00c3 A[Catch:{ all -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzb(android.content.Context r8, java.lang.String r9, boolean r10, boolean r11) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            java.lang.ThreadLocal r8 = zzh     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            java.lang.Object r8 = r8.get()     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            long r2 = r8.longValue()     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            java.lang.String r8 = "api_force_staging"
            java.lang.String r4 = "api"
            r7 = 1
            if (r7 == r10) goto L_0x0019
            r8 = r4
        L_0x0019:
            android.net.Uri$Builder r10 = new android.net.Uri$Builder     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            r10.<init>()     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            java.lang.String r4 = "content"
            android.net.Uri$Builder r10 = r10.scheme(r4)     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            java.lang.String r4 = "com.google.android.gms.chimera"
            android.net.Uri$Builder r10 = r10.authority(r4)     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            android.net.Uri$Builder r8 = r10.path(r8)     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            android.net.Uri$Builder r8 = r8.appendPath(r9)     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            java.lang.String r9 = "requestStartTime"
            java.lang.String r10 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            android.net.Uri$Builder r8 = r8.appendQueryParameter(r9, r10)     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            android.net.Uri r2 = r8.build()     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            r5 = 0
            r6 = 0
            r3 = 0
            r4 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00bb, all -> 0x00b8 }
            if (r8 == 0) goto L_0x00a9
            boolean r9 = r8.moveToFirst()     // Catch:{ Exception -> 0x009f }
            if (r9 == 0) goto L_0x00a9
            r9 = 0
            int r10 = r8.getInt(r9)     // Catch:{ Exception -> 0x009f }
            if (r10 <= 0) goto L_0x0090
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r1 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r1)     // Catch:{ Exception -> 0x009f }
            r2 = 2
            java.lang.String r2 = r8.getString(r2)     // Catch:{ all -> 0x0070 }
            zzc = r2     // Catch:{ all -> 0x0070 }
            java.lang.String r2 = "loaderVersion"
            int r2 = r8.getColumnIndex(r2)     // Catch:{ all -> 0x0070 }
            if (r2 < 0) goto L_0x0072
            int r2 = r8.getInt(r2)     // Catch:{ all -> 0x0070 }
            zze = r2     // Catch:{ all -> 0x0070 }
            goto L_0x0072
        L_0x0070:
            r9 = move-exception
            goto L_0x008e
        L_0x0072:
            java.lang.String r2 = "disableStandaloneDynamiteLoader2"
            int r2 = r8.getColumnIndex(r2)     // Catch:{ all -> 0x0070 }
            if (r2 < 0) goto L_0x0085
            int r2 = r8.getInt(r2)     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x0081
            goto L_0x0082
        L_0x0081:
            r7 = r9
        L_0x0082:
            zzd = r7     // Catch:{ all -> 0x0070 }
            r9 = r7
        L_0x0085:
            monitor-exit(r1)     // Catch:{ all -> 0x0070 }
            boolean r1 = zze(r8)     // Catch:{ Exception -> 0x009f }
            if (r1 == 0) goto L_0x0090
            r8 = r0
            goto L_0x0090
        L_0x008e:
            monitor-exit(r1)     // Catch:{ all -> 0x0070 }
            throw r9     // Catch:{ Exception -> 0x009f }
        L_0x0090:
            if (r11 == 0) goto L_0x00a3
            if (r9 != 0) goto L_0x0095
            goto L_0x00a3
        L_0x0095:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r9 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x009f }
            java.lang.String r10 = "forcing fallback to container DynamiteLoader impl"
            r9.<init>(r10, r0)     // Catch:{ Exception -> 0x009f }
            throw r9     // Catch:{ Exception -> 0x009f }
        L_0x009d:
            r9 = move-exception
            goto L_0x00a1
        L_0x009f:
            r9 = move-exception
            goto L_0x00be
        L_0x00a1:
            r0 = r8
            goto L_0x00de
        L_0x00a3:
            if (r8 == 0) goto L_0x00a8
            r8.close()
        L_0x00a8:
            return r10
        L_0x00a9:
            java.lang.String r9 = "DynamiteModule"
            java.lang.String r10 = "Failed to retrieve remote module version."
            android.util.Log.w(r9, r10)     // Catch:{ Exception -> 0x009f }
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r9 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ Exception -> 0x009f }
            java.lang.String r10 = "Failed to connect to dynamite module ContentResolver."
            r9.<init>(r10, r0)     // Catch:{ Exception -> 0x009f }
            throw r9     // Catch:{ Exception -> 0x009f }
        L_0x00b8:
            r8 = move-exception
            r9 = r8
            goto L_0x00de
        L_0x00bb:
            r8 = move-exception
            r9 = r8
            r8 = r0
        L_0x00be:
            boolean r10 = r9 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch:{ all -> 0x009d }
            if (r10 == 0) goto L_0x00c3
            throw r9     // Catch:{ all -> 0x009d }
        L_0x00c3:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch:{ all -> 0x009d }
            java.lang.String r11 = r9.getMessage()     // Catch:{ all -> 0x009d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x009d }
            r1.<init>()     // Catch:{ all -> 0x009d }
            java.lang.String r2 = "V2 version check failed: "
            r1.append(r2)     // Catch:{ all -> 0x009d }
            r1.append(r11)     // Catch:{ all -> 0x009d }
            java.lang.String r11 = r1.toString()     // Catch:{ all -> 0x009d }
            r10.<init>(r11, r9, r0)     // Catch:{ all -> 0x009d }
            throw r10     // Catch:{ all -> 0x009d }
        L_0x00de:
            if (r0 == 0) goto L_0x00e3
            r0.close()
        L_0x00e3:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    private static DynamiteModule zzc(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    @GuardedBy("DynamiteModule.class")
    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzr zzr;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor((Class[]) null).newInstance((Object[]) null);
            if (iBinder == null) {
                zzr = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzr = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzr(iBinder);
            }
            zzl = zzr;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e3) {
            throw new LoadingException("Failed to instantiate dynamite loader", e3, (zzp) null);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzn zzn = (zzn) zzg.get();
        if (zzn == null || zzn.zza != null) {
            return false;
        }
        zzn.zza = cursor;
        return true;
    }

    @GuardedBy("DynamiteModule.class")
    private static boolean zzf(Context context) {
        ApplicationInfo applicationInfo;
        Boolean bool = Boolean.TRUE;
        if (bool.equals((Object) null) || bool.equals(zzf)) {
            return true;
        }
        boolean z2 = false;
        if (zzf == null) {
            ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) == 0 && resolveContentProvider != null && "com.google.android.gms".equals(resolveContentProvider.packageName)) {
                z2 = true;
            }
            zzf = Boolean.valueOf(z2);
            if (z2 && (applicationInfo = resolveContentProvider.applicationInfo) != null && (applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                zzd = true;
            }
        }
        if (!z2) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z2;
    }

    @Nullable
    private static zzq zzg(Context context) {
        zzq zzq;
        synchronized (DynamiteModule.class) {
            zzq zzq2 = zzk;
            if (zzq2 != null) {
                return zzq2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzq = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzq = queryLocalInterface instanceof zzq ? (zzq) queryLocalInterface : new zzq(iBinder);
                }
                if (zzq != null) {
                    zzk = zzq;
                    return zzq;
                }
            } catch (Exception e3) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e3.getMessage());
            }
        }
        return null;
    }

    @ResultIgnorabilityUnspecified
    @NonNull
    @KeepForSdk
    public Context getModuleContext() {
        return this.zzj;
    }

    @NonNull
    @KeepForSdk
    public IBinder instantiate(@NonNull String str) throws LoadingException {
        try {
            return (IBinder) this.zzj.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e3) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(str)), e3, (zzp) null);
        }
    }
}
