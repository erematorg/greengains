package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamg  reason: invalid package */
abstract class zzamg<T, B> {
    public abstract int zza(T t2);

    public abstract B zza();

    public abstract T zza(T t2, T t3);

    public abstract void zza(B b3, int i3, int i4);

    public abstract void zza(B b3, int i3, long j2);

    public abstract void zza(B b3, int i3, zzaho zzaho);

    public abstract void zza(B b3, int i3, T t2);

    public abstract void zza(T t2, zzanc zzanc) throws IOException;

    public abstract boolean zza(zzale zzale);

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f A[LOOP:0: B:16:0x002f->B:19:0x003c, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(B r7, com.google.android.gms.internal.p002firebaseauthapi.zzale r8) throws java.io.IOException {
        /*
            r6 = this;
            int r0 = r8.zzd()
            int r1 = r0 >>> 3
            r0 = r0 & 7
            r2 = 1
            if (r0 == 0) goto L_0x0061
            if (r0 == r2) goto L_0x0059
            r3 = 2
            if (r0 == r3) goto L_0x0051
            r3 = 4
            r4 = 3
            if (r0 == r4) goto L_0x0028
            if (r0 == r3) goto L_0x0026
            r3 = 5
            if (r0 != r3) goto L_0x0021
            int r8 = r8.zzf()
            r6.zza(r7, (int) r1, (int) r8)
            return r2
        L_0x0021:
            com.google.android.gms.internal.firebase-auth-api.zzajn r6 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zza()
            throw r6
        L_0x0026:
            r6 = 0
            return r6
        L_0x0028:
            java.lang.Object r0 = r6.zza()
            int r4 = r1 << 3
            r3 = r3 | r4
        L_0x002f:
            int r4 = r8.zzc()
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x003e
            boolean r4 = r6.zza(r0, (com.google.android.gms.internal.p002firebaseauthapi.zzale) r8)
            if (r4 != 0) goto L_0x002f
        L_0x003e:
            int r8 = r8.zzd()
            if (r3 != r8) goto L_0x004c
            java.lang.Object r8 = r6.zze(r0)
            r6.zza(r7, (int) r1, r8)
            return r2
        L_0x004c:
            com.google.android.gms.internal.firebase-auth-api.zzajk r6 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzb()
            throw r6
        L_0x0051:
            com.google.android.gms.internal.firebase-auth-api.zzaho r8 = r8.zzp()
            r6.zza(r7, (int) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r8)
            return r2
        L_0x0059:
            long r3 = r8.zzk()
            r6.zza(r7, (int) r1, (long) r3)
            return r2
        L_0x0061:
            long r3 = r8.zzl()
            r6.zzb(r7, r1, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzamg.zza(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzale):boolean");
    }

    public abstract int zzb(T t2);

    public abstract void zzb(B b3, int i3, long j2);

    public abstract void zzb(T t2, zzanc zzanc) throws IOException;

    public abstract void zzb(Object obj, B b3);

    public abstract B zzc(Object obj);

    public abstract void zzc(Object obj, T t2);

    public abstract T zzd(Object obj);

    public abstract T zze(B b3);

    public abstract void zzf(Object obj);
}
