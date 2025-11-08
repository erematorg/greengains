package com.google.android.gms.maps;

import android.content.Context;
import androidx.annotation.NonNull;

public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(@NonNull Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, (Renderer) null, (OnMapsSdkInitializedCallback) null);
        }
        return initialize;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A[Catch:{ RemoteException -> 0x007f, RemoteException -> 0x0054 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0078 A[Catch:{ RemoteException -> 0x007f, RemoteException -> 0x0054 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized int initialize(@androidx.annotation.NonNull android.content.Context r5, @androidx.annotation.Nullable com.google.android.gms.maps.MapsInitializer.Renderer r6, @androidx.annotation.Nullable com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = zza     // Catch:{ all -> 0x0024 }
            java.lang.String r2 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0024 }
            java.lang.String r3 = "preferredRenderer: "
            java.lang.String r2 = r3.concat(r2)     // Catch:{ all -> 0x0024 }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x0024 }
            boolean r1 = zzb     // Catch:{ all -> 0x0024 }
            r2 = 0
            if (r1 == 0) goto L_0x0026
            if (r7 == 0) goto L_0x007d
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = zzc     // Catch:{ all -> 0x0024 }
            r7.onMapsSdkInitialized(r5)     // Catch:{ all -> 0x0024 }
            goto L_0x007d
        L_0x0024:
            r5 = move-exception
            goto L_0x008b
        L_0x0026:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzcc.zza(r5, r6)     // Catch:{ GooglePlayServicesNotAvailableException -> 0x0086 }
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch:{ RemoteException -> 0x007f }
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch:{ RemoteException -> 0x007f }
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch:{ RemoteException -> 0x007f }
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch:{ RemoteException -> 0x007f }
            r3 = 1
            zzb = r3     // Catch:{ all -> 0x0024 }
            r4 = 2
            if (r6 == 0) goto L_0x0046
            int r6 = r6.ordinal()     // Catch:{ all -> 0x0024 }
            if (r6 == 0) goto L_0x0049
            if (r6 == r3) goto L_0x0048
        L_0x0046:
            r3 = r2
            goto L_0x0049
        L_0x0048:
            r3 = r4
        L_0x0049:
            int r6 = r1.zzd()     // Catch:{ RemoteException -> 0x0054 }
            if (r6 != r4) goto L_0x0056
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch:{ RemoteException -> 0x0054 }
            zzc = r6     // Catch:{ RemoteException -> 0x0054 }
            goto L_0x0056
        L_0x0054:
            r5 = move-exception
            goto L_0x005e
        L_0x0056:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x0054 }
            r1.zzl(r5, r3)     // Catch:{ RemoteException -> 0x0054 }
            goto L_0x0065
        L_0x005e:
            java.lang.String r6 = zza     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch:{ all -> 0x0024 }
        L_0x0065:
            java.lang.String r5 = zza     // Catch:{ all -> 0x0024 }
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = zzc     // Catch:{ all -> 0x0024 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = "loadedRenderer: "
            java.lang.String r6 = r1.concat(r6)     // Catch:{ all -> 0x0024 }
            android.util.Log.d(r5, r6)     // Catch:{ all -> 0x0024 }
            if (r7 == 0) goto L_0x007d
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = zzc     // Catch:{ all -> 0x0024 }
            r7.onMapsSdkInitialized(r5)     // Catch:{ all -> 0x0024 }
        L_0x007d:
            monitor-exit(r0)
            return r2
        L_0x007f:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch:{ all -> 0x0024 }
            r6.<init>(r5)     // Catch:{ all -> 0x0024 }
            throw r6     // Catch:{ all -> 0x0024 }
        L_0x0086:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch:{ all -> 0x0024 }
            monitor-exit(r0)
            return r5
        L_0x008b:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
