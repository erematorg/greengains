package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Comparator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahq  reason: invalid package */
final class zzahq implements Comparator<zzaho> {
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzaho zzaho = (zzaho) obj;
        zzaho zzaho2 = (zzaho) obj2;
        zzahu zzahu = (zzahu) zzaho.iterator();
        zzahu zzahu2 = (zzahu) zzaho2.iterator();
        while (zzahu.hasNext() && zzahu2.hasNext()) {
            int compareTo = Integer.valueOf(zzaho.zza(zzahu.zza())).compareTo(Integer.valueOf(zzaho.zza(zzahu2.zza())));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzaho.zzb()).compareTo(Integer.valueOf(zzaho2.zzb()));
    }
}
