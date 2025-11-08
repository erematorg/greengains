package com.google.android.gms.internal.measurement;

import java.io.IOException;

abstract class zznx<T, B> {
    private static volatile int zza = 100;

    public abstract int zza(T t2);

    public abstract B zza();

    public abstract T zza(T t2, T t3);

    public abstract void zza(B b3, int i3, int i4);

    public abstract void zza(B b3, int i3, long j2);

    public abstract void zza(B b3, int i3, zzjs zzjs);

    public abstract void zza(B b3, int i3, T t2);

    public abstract void zza(T t2, zzos zzos) throws IOException;

    public abstract boolean zza(zzna zzna);

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034 A[LOOP:0: B:17:0x0034->B:20:0x0041, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(B r7, com.google.android.gms.internal.measurement.zzna r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            int r0 = r8.zzd()
            int r1 = r0 >>> 3
            r0 = r0 & 7
            r2 = 1
            if (r0 == 0) goto L_0x006b
            if (r0 == r2) goto L_0x0063
            r3 = 2
            if (r0 == r3) goto L_0x005b
            r3 = 4
            r4 = 3
            if (r0 == r4) goto L_0x0028
            if (r0 == r3) goto L_0x0026
            r9 = 5
            if (r0 != r9) goto L_0x0021
            int r8 = r8.zzf()
            r6.zza(r7, (int) r1, (int) r8)
            return r2
        L_0x0021:
            com.google.android.gms.internal.measurement.zzln r6 = com.google.android.gms.internal.measurement.zzlk.zza()
            throw r6
        L_0x0026:
            r6 = 0
            return r6
        L_0x0028:
            java.lang.Object r0 = r6.zza()
            int r4 = r1 << 3
            r3 = r3 | r4
            int r9 = r9 + r2
            int r4 = zza
            if (r9 >= r4) goto L_0x0056
        L_0x0034:
            int r4 = r8.zzc()
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x0043
            boolean r4 = r6.zza(r0, (com.google.android.gms.internal.measurement.zzna) r8, (int) r9)
            if (r4 != 0) goto L_0x0034
        L_0x0043:
            int r8 = r8.zzd()
            if (r3 != r8) goto L_0x0051
            java.lang.Object r8 = r6.zze(r0)
            r6.zza(r7, (int) r1, r8)
            return r2
        L_0x0051:
            com.google.android.gms.internal.measurement.zzlk r6 = com.google.android.gms.internal.measurement.zzlk.zzb()
            throw r6
        L_0x0056:
            com.google.android.gms.internal.measurement.zzlk r6 = com.google.android.gms.internal.measurement.zzlk.zzh()
            throw r6
        L_0x005b:
            com.google.android.gms.internal.measurement.zzjs r8 = r8.zzp()
            r6.zza(r7, (int) r1, (com.google.android.gms.internal.measurement.zzjs) r8)
            return r2
        L_0x0063:
            long r8 = r8.zzk()
            r6.zza(r7, (int) r1, (long) r8)
            return r2
        L_0x006b:
            long r8 = r8.zzl()
            r6.zzb(r7, r1, r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zznx.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzna, int):boolean");
    }

    public abstract int zzb(T t2);

    public abstract void zzb(B b3, int i3, long j2);

    public abstract void zzb(T t2, zzos zzos) throws IOException;

    public abstract void zzb(Object obj, B b3);

    public abstract B zzc(Object obj);

    public abstract void zzc(Object obj, T t2);

    public abstract T zzd(Object obj);

    public abstract T zze(B b3);

    public abstract void zzf(Object obj);
}
