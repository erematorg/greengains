package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajx  reason: invalid package */
final class zzajx extends zzaju {
    private static final Class<?> zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private static <E> List<E> zzc(Object obj, long j2) {
        return (List) zzamm.zze(obj, j2);
    }

    public final <L> List<L> zza(Object obj, long j2) {
        return zza(obj, j2, 10);
    }

    public final void zzb(Object obj, long j2) {
        Object obj2;
        List list = (List) zzamm.zze(obj, j2);
        if (list instanceof zzajv) {
            obj2 = ((zzajv) list).zzd();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzalb) || !(list instanceof zzajl)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzajl zzajl = (zzajl) list;
                if (zzajl.zzc()) {
                    zzajl.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzamm.zza(obj, j2, obj2);
    }

    private zzajx() {
        super();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.google.android.gms.internal.firebase-auth-api.zzajs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: com.google.android.gms.internal.firebase-auth-api.zzajs} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: com.google.android.gms.internal.firebase-auth-api.zzajs} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <L> java.util.List<L> zza(java.lang.Object r3, long r4, int r6) {
        /*
            java.util.List r0 = zzc(r3, r4)
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x002d
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzajv
            if (r1 == 0) goto L_0x0014
            com.google.android.gms.internal.firebase-auth-api.zzajs r0 = new com.google.android.gms.internal.firebase-auth-api.zzajs
            r0.<init>((int) r6)
            goto L_0x0029
        L_0x0014:
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzalb
            if (r1 == 0) goto L_0x0024
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzajl
            if (r1 == 0) goto L_0x0024
            com.google.android.gms.internal.firebase-auth-api.zzajl r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzajl) r0
            com.google.android.gms.internal.firebase-auth-api.zzajl r6 = r0.zza(r6)
            r0 = r6
            goto L_0x0029
        L_0x0024:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r6)
        L_0x0029:
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r0)
            goto L_0x007f
        L_0x002d:
            java.lang.Class<?> r1 = zza
            java.lang.Class r2 = r0.getClass()
            boolean r1 = r1.isAssignableFrom(r2)
            if (r1 == 0) goto L_0x004b
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            int r2 = r2 + r6
            r1.<init>(r2)
            r1.addAll(r0)
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r1)
        L_0x0049:
            r0 = r1
            goto L_0x007f
        L_0x004b:
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzamh
            if (r1 == 0) goto L_0x0062
            com.google.android.gms.internal.firebase-auth-api.zzajs r1 = new com.google.android.gms.internal.firebase-auth-api.zzajs
            int r2 = r0.size()
            int r2 = r2 + r6
            r1.<init>((int) r2)
            com.google.android.gms.internal.firebase-auth-api.zzamh r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzamh) r0
            r1.addAll(r0)
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r1)
            goto L_0x0049
        L_0x0062:
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzalb
            if (r1 == 0) goto L_0x007f
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzajl
            if (r1 == 0) goto L_0x007f
            r1 = r0
            com.google.android.gms.internal.firebase-auth-api.zzajl r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzajl) r1
            boolean r2 = r1.zzc()
            if (r2 != 0) goto L_0x007f
            int r0 = r0.size()
            int r0 = r0 + r6
            com.google.android.gms.internal.firebase-auth-api.zzajl r0 = r1.zza(r0)
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r3, (long) r4, (java.lang.Object) r0)
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzajx.zza(java.lang.Object, long, int):java.util.List");
    }

    public final <E> void zza(Object obj, Object obj2, long j2) {
        List zzc = zzc(obj2, j2);
        List zza2 = zza(obj, j2, zzc.size());
        int size = zza2.size();
        int size2 = zzc.size();
        if (size > 0 && size2 > 0) {
            zza2.addAll(zzc);
        }
        if (size > 0) {
            zzc = zza2;
        }
        zzamm.zza(obj, j2, (Object) zzc);
    }
}
