package com.google.android.gms.internal.measurement;

public class zzlt {
    private volatile zzml zza;
    private volatile zzjs zzb;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzlt)) {
            return false;
        }
        zzlt zzlt = (zzlt) obj;
        zzml zzml = this.zza;
        zzml zzml2 = zzlt.zza;
        return (zzml == null && zzml2 == null) ? zzb().equals(zzlt.zzb()) : (zzml == null || zzml2 == null) ? zzml != null ? zzml.equals(zzlt.zzb(zzml.zzck())) : zzb(zzml2.zzck()).equals(zzml2) : zzml.equals(zzml2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return this.zzb.zzb();
        }
        if (this.zza != null) {
            return this.zza.zzcb();
        }
        return 0;
    }

    public final zzjs zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            try {
                if (this.zzb != null) {
                    zzjs zzjs = this.zzb;
                    return zzjs;
                }
                if (this.zza == null) {
                    this.zzb = zzjs.zza;
                } else {
                    this.zzb = this.zza.zzbz();
                }
                zzjs zzjs2 = this.zzb;
                return zzjs2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zzml zza(zzml zzml) {
        zzml zzml2 = this.zza;
        this.zzb = null;
        this.zza = zzml;
        return zzml2;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:8|9|10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.measurement.zzml zzb(com.google.android.gms.internal.measurement.zzml r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.measurement.zzml r0 = r1.zza
            if (r0 != 0) goto L_0x001e
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzml r0 = r1.zza     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x000d
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            goto L_0x001e
        L_0x000b:
            r2 = move-exception
            goto L_0x001c
        L_0x000d:
            r1.zza = r2     // Catch:{ zzlk -> 0x0014 }
            com.google.android.gms.internal.measurement.zzjs r0 = com.google.android.gms.internal.measurement.zzjs.zza     // Catch:{ zzlk -> 0x0014 }
            r1.zzb = r0     // Catch:{ zzlk -> 0x0014 }
            goto L_0x001a
        L_0x0014:
            r1.zza = r2     // Catch:{ all -> 0x000b }
            com.google.android.gms.internal.measurement.zzjs r2 = com.google.android.gms.internal.measurement.zzjs.zza     // Catch:{ all -> 0x000b }
            r1.zzb = r2     // Catch:{ all -> 0x000b }
        L_0x001a:
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            goto L_0x001e
        L_0x001c:
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            throw r2
        L_0x001e:
            com.google.android.gms.internal.measurement.zzml r1 = r1.zza
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlt.zzb(com.google.android.gms.internal.measurement.zzml):com.google.android.gms.internal.measurement.zzml");
    }
}
