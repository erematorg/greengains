package com.google.android.gms.internal.p002firebaseauthapi;

import android.support.v4.media.session.a;
import androidx.annotation.NonNull;
import androidx.browser.trusted.c;
import androidx.collection.ArrayMap;
import com.google.firebase.FirebaseApp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaec  reason: invalid package */
public final class zzaec {
    private static final Map<String, zzaef> zza = new ArrayMap();
    private static final Map<String, List<WeakReference<zzaee>>> zzb = new ArrayMap();

    private static String zza(String str, int i3, boolean z2) {
        if (z2) {
            return "http://[" + str + "]:" + i3 + "/";
        }
        return "http://" + str + ":" + i3 + "/";
    }

    @NonNull
    public static String zzb(String str) {
        zzaef zzaef;
        Map<String, zzaef> map = zza;
        synchronized (map) {
            zzaef = map.get(str);
        }
        return a.m(zzaef != null ? c.a("", zza(zzaef.zzb(), zzaef.zza(), zzaef.zzb().contains(":"))) : "https://", "www.googleapis.com/identitytoolkit/v3/relyingparty");
    }

    @NonNull
    public static String zzc(String str) {
        zzaef zzaef;
        Map<String, zzaef> map = zza;
        synchronized (map) {
            zzaef = map.get(str);
        }
        return a.m(zzaef != null ? c.a("", zza(zzaef.zzb(), zzaef.zza(), zzaef.zzb().contains(":"))) : "https://", "identitytoolkit.googleapis.com/v2");
    }

    @NonNull
    public static String zzd(String str) {
        zzaef zzaef;
        Map<String, zzaef> map = zza;
        synchronized (map) {
            zzaef = map.get(str);
        }
        return a.m(zzaef != null ? c.a("", zza(zzaef.zzb(), zzaef.zza(), zzaef.zzb().contains(":"))) : "https://", "securetoken.googleapis.com/v1");
    }

    @NonNull
    public static String zza(String str) {
        zzaef zzaef;
        Map<String, zzaef> map = zza;
        synchronized (map) {
            zzaef = map.get(str);
        }
        if (zzaef != null) {
            return a.m(zza(zzaef.zzb(), zzaef.zza(), zzaef.zzb().contains(":")), "emulator/auth/handler");
        }
        throw new IllegalStateException("Tried to get the emulator widget endpoint, but no emulator endpoint overrides found.");
    }

    public static void zza(String str, zzaee zzaee) {
        Map<String, List<WeakReference<zzaee>>> map = zzb;
        synchronized (map) {
            try {
                if (map.containsKey(str)) {
                    map.get(str).add(new WeakReference(zzaee));
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new WeakReference(zzaee));
                    map.put(str, arrayList);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, int i3) {
        String apiKey = firebaseApp.getOptions().getApiKey();
        Map<String, zzaef> map = zza;
        synchronized (map) {
            map.put(apiKey, new zzaef(str, i3));
        }
        Map<String, List<WeakReference<zzaee>>> map2 = zzb;
        synchronized (map2) {
            try {
                if (map2.containsKey(apiKey)) {
                    boolean z2 = false;
                    for (WeakReference weakReference : map2.get(apiKey)) {
                        zzaee zzaee = (zzaee) weakReference.get();
                        if (zzaee != null) {
                            zzaee.zza();
                            z2 = true;
                        }
                    }
                    if (!z2) {
                        zza.remove(apiKey);
                    }
                }
            } finally {
            }
        }
    }

    public static boolean zza(@NonNull FirebaseApp firebaseApp) {
        return zza.containsKey(firebaseApp.getOptions().getApiKey());
    }
}
