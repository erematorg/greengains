package com.google.android.gms.common.config;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.errorprone.annotations.InlineMe;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

@KeepForSdk
public abstract class GservicesValue<T> {
    private static final Object zzc = new Object();
    @NonNull
    protected final String zza;
    @NonNull
    protected final Object zzb;
    @Nullable
    private Object zzd = null;

    public GservicesValue(@NonNull String str, @NonNull Object obj) {
        this.zza = str;
        this.zzb = obj;
    }

    @ResultIgnorabilityUnspecified
    @KeepForSdk
    public static boolean isInitialized() {
        synchronized (zzc) {
        }
        return false;
    }

    @NonNull
    @KeepForSdk
    public static GservicesValue<Float> value(@NonNull String str, @NonNull Float f2) {
        return new zzd(str, f2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = android.os.Binder.clearCallingIdentity();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r4 = zza(r4.zza);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        android.os.Binder.restoreCallingIdentity(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0029, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        android.os.Binder.restoreCallingIdentity(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002d, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002e, code lost:
        android.os.StrictMode.setThreadPolicy(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0031, code lost:
        throw r4;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0018 */
    @com.google.errorprone.annotations.ResultIgnorabilityUnspecified
    @androidx.annotation.NonNull
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T get() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzd
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            android.os.StrictMode$ThreadPolicy r0 = android.os.StrictMode.allowThreadDiskReads()
            java.lang.Object r1 = zzc
            monitor-enter(r1)
            monitor-exit(r1)     // Catch:{ all -> 0x0035 }
            monitor-enter(r1)
            monitor-exit(r1)     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = r4.zza     // Catch:{ SecurityException -> 0x0018 }
            java.lang.Object r4 = r4.zza(r1)     // Catch:{ SecurityException -> 0x0018 }
            goto L_0x0025
        L_0x0016:
            r4 = move-exception
            goto L_0x002e
        L_0x0018:
            long r1 = android.os.Binder.clearCallingIdentity()     // Catch:{ all -> 0x0016 }
            java.lang.String r3 = r4.zza     // Catch:{ all -> 0x0029 }
            java.lang.Object r4 = r4.zza(r3)     // Catch:{ all -> 0x0029 }
            android.os.Binder.restoreCallingIdentity(r1)     // Catch:{ all -> 0x0016 }
        L_0x0025:
            android.os.StrictMode.setThreadPolicy(r0)
            return r4
        L_0x0029:
            r4 = move-exception
            android.os.Binder.restoreCallingIdentity(r1)     // Catch:{ all -> 0x0016 }
            throw r4     // Catch:{ all -> 0x0016 }
        L_0x002e:
            android.os.StrictMode.setThreadPolicy(r0)
            throw r4
        L_0x0032:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0032 }
            throw r4
        L_0x0035:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0035 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.config.GservicesValue.get():java.lang.Object");
    }

    @InlineMe(replacement = "this.get()")
    @NonNull
    @KeepForSdk
    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    @androidx.annotation.VisibleForTesting
    @com.google.android.gms.common.annotation.KeepForSdk
    public void override(@androidx.annotation.NonNull T r3) {
        /*
            r2 = this;
            java.lang.String r0 = "GservicesValue"
            java.lang.String r1 = "GservicesValue.override(): test should probably call initForTests() first"
            android.util.Log.w(r0, r1)
            r2.zzd = r3
            java.lang.Object r2 = zzc
            monitor-enter(r2)
            monitor-enter(r2)     // Catch:{ all -> 0x0010 }
            monitor-exit(r2)     // Catch:{ all -> 0x0012 }
            monitor-exit(r2)     // Catch:{ all -> 0x0010 }
            return
        L_0x0010:
            r3 = move-exception
            goto L_0x0015
        L_0x0012:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0012 }
            throw r3     // Catch:{ all -> 0x0010 }
        L_0x0015:
            monitor-exit(r2)     // Catch:{ all -> 0x0010 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.config.GservicesValue.override(java.lang.Object):void");
    }

    @VisibleForTesting
    @KeepForSdk
    public void resetOverride() {
        this.zzd = null;
    }

    @NonNull
    public abstract Object zza(@NonNull String str);

    @NonNull
    @KeepForSdk
    public static GservicesValue<Integer> value(@NonNull String str, @NonNull Integer num) {
        return new zzc(str, num);
    }

    @NonNull
    @KeepForSdk
    public static GservicesValue<Long> value(@NonNull String str, @NonNull Long l2) {
        return new zzb(str, l2);
    }

    @NonNull
    @KeepForSdk
    public static GservicesValue<String> value(@NonNull String str, @NonNull String str2) {
        return new zze(str, str2);
    }

    @NonNull
    @KeepForSdk
    public static GservicesValue<Boolean> value(@NonNull String str, boolean z2) {
        return new zza(str, Boolean.valueOf(z2));
    }
}
