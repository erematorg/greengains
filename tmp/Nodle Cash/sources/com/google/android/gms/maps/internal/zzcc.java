package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.Objects;

public final class zzcc {
    private static final String zza = "zzcc";
    @SuppressLint({"StaticFieldLeak"})
    @Nullable
    private static Context zzb;
    private static zzf zzc;

    public static zzf zza(Context context, @Nullable MapsInitializer.Renderer renderer) throws GooglePlayServicesNotAvailableException {
        Preconditions.checkNotNull(context);
        Log.d(zza, "preferredRenderer: ".concat(String.valueOf(renderer)));
        zzf zzf = zzc;
        if (zzf != null) {
            return zzf;
        }
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context, 13400000);
        if (isGooglePlayServicesAvailable == 0) {
            zzf zzd = zzd(context, renderer);
            zzc = zzd;
            try {
                if (zzd.zzd() == 2) {
                    try {
                        zzc.zzm(ObjectWrapper.wrap(zzc(context, renderer)));
                    } catch (UnsatisfiedLinkError unused) {
                        Log.w(zza, "Caught UnsatisfiedLinkError attempting to load the LATEST renderer's native library. Attempting to use the LEGACY renderer instead.");
                        zzb = null;
                        zzc = zzd(context, MapsInitializer.Renderer.LEGACY);
                    } catch (RemoteException e3) {
                        throw new RuntimeRemoteException(e3);
                    }
                }
                try {
                    zzf zzf2 = zzc;
                    Context zzc2 = zzc(context, renderer);
                    Objects.requireNonNull(zzc2);
                    zzf2.zzk(ObjectWrapper.wrap(zzc2.getResources()), 19000000);
                    return zzc;
                } catch (RemoteException e4) {
                    throw new RuntimeRemoteException(e4);
                }
            } catch (RemoteException e5) {
                throw new RuntimeRemoteException(e5);
            }
        } else {
            throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
    }

    private static Context zzb(Exception exc, Context context) {
        Log.e(zza, "Failed to load maps module, use pre-Chimera", exc);
        return GooglePlayServicesUtil.getRemoteContext(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        r0 = "com.google.android.gms.maps_core_dynamite";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        if (r5 != com.google.android.gms.maps.MapsInitializer.Renderer.LEGACY) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r5 != 1) goto L_0x001d;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.Context zzc(android.content.Context r4, @androidx.annotation.Nullable com.google.android.gms.maps.MapsInitializer.Renderer r5) {
        /*
            java.lang.String r0 = "com.google.android.gms.maps_legacy_dynamite"
            java.lang.String r1 = "com.google.android.gms.maps_core_dynamite"
            java.lang.String r2 = "com.google.android.gms.maps_dynamite"
            android.content.Context r3 = zzb
            if (r3 != 0) goto L_0x0054
            java.lang.String r3 = "com.google.android.gms.maps.internal.UseLegacyRendererAsDefault"
            java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x001f }
            if (r5 == 0) goto L_0x001d
            int r5 = r5.ordinal()
            if (r5 == 0) goto L_0x0023
            r0 = 1
            if (r5 == r0) goto L_0x001b
            goto L_0x001d
        L_0x001b:
            r0 = r1
            goto L_0x0023
        L_0x001d:
            r0 = r2
            goto L_0x0023
        L_0x001f:
            com.google.android.gms.maps.MapsInitializer$Renderer r3 = com.google.android.gms.maps.MapsInitializer.Renderer.LEGACY
            if (r5 != r3) goto L_0x001b
        L_0x0023:
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy r5 = com.google.android.gms.dynamite.DynamiteModule.PREFER_REMOTE     // Catch:{ Exception -> 0x002e }
            com.google.android.gms.dynamite.DynamiteModule r5 = com.google.android.gms.dynamite.DynamiteModule.load(r4, r5, r0)     // Catch:{ Exception -> 0x002e }
            android.content.Context r4 = r5.getModuleContext()     // Catch:{ Exception -> 0x002e }
            goto L_0x0051
        L_0x002e:
            r5 = move-exception
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x004d
            java.lang.String r5 = zza     // Catch:{ Exception -> 0x0047 }
            java.lang.String r0 = "Attempting to load maps_dynamite again."
            android.util.Log.d(r5, r0)     // Catch:{ Exception -> 0x0047 }
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy r5 = com.google.android.gms.dynamite.DynamiteModule.PREFER_REMOTE     // Catch:{ Exception -> 0x0047 }
            com.google.android.gms.dynamite.DynamiteModule r5 = com.google.android.gms.dynamite.DynamiteModule.load(r4, r5, r2)     // Catch:{ Exception -> 0x0047 }
            android.content.Context r4 = r5.getModuleContext()     // Catch:{ Exception -> 0x0047 }
            goto L_0x0051
        L_0x0047:
            r5 = move-exception
            android.content.Context r4 = zzb(r5, r4)
            goto L_0x0051
        L_0x004d:
            android.content.Context r4 = zzb(r5, r4)
        L_0x0051:
            zzb = r4
            return r4
        L_0x0054:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzcc.zzc(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer):android.content.Context");
    }

    private static zzf zzd(Context context, @Nullable MapsInitializer.Renderer renderer) {
        Log.i(zza, "Making Creator dynamically");
        try {
            IBinder iBinder = (IBinder) zze(((ClassLoader) Preconditions.checkNotNull(zzc(context, renderer).getClassLoader())).loadClass("com.google.android.gms.maps.internal.CreatorImpl"));
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
            return queryLocalInterface instanceof zzf ? (zzf) queryLocalInterface : new zze(iBinder);
        } catch (ClassNotFoundException e3) {
            throw new IllegalStateException("Unable to find dynamic class com.google.android.gms.maps.internal.CreatorImpl", e3);
        }
    }

    private static Object zze(Class cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e3) {
            throw new IllegalStateException("Unable to instantiate the dynamic class ".concat(cls.getName()), e3);
        } catch (IllegalAccessException e4) {
            throw new IllegalStateException("Unable to call the default constructor of ".concat(cls.getName()), e4);
        }
    }
}
