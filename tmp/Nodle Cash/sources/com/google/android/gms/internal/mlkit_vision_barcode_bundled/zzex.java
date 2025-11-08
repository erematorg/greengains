package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

public class zzex {
    protected volatile zzfm zza;
    private volatile zzdf zzb;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzex)) {
            return false;
        }
        zzex zzex = (zzex) obj;
        zzfm zzfm = this.zza;
        zzfm zzfm2 = zzex.zza;
        if (zzfm == null && zzfm2 == null) {
            return zzb().equals(zzex.zzb());
        }
        if (zzfm != null && zzfm2 != null) {
            return zzfm.equals(zzfm2);
        }
        if (zzfm != null) {
            zzex.zzd(zzfm.zzac());
            return zzfm.equals(zzex.zza);
        }
        zzd(zzfm2.zzac());
        return this.zza.equals(zzfm2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return ((zzde) this.zzb).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzF();
        }
        return 0;
    }

    public final zzdf zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            try {
                if (this.zzb != null) {
                    zzdf zzdf = this.zzb;
                    return zzdf;
                }
                if (this.zza == null) {
                    this.zzb = zzdf.zzb;
                } else {
                    this.zzb = this.zza.zzC();
                }
                zzdf zzdf2 = this.zzb;
                return zzdf2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zzfm zzc(zzfm zzfm) {
        zzfm zzfm2 = this.zza;
        this.zzb = null;
        this.zza = zzfm;
        return zzfm2;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm r0 = r1.zza     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x000e
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            return
        L_0x000c:
            r2 = move-exception
            goto L_0x001d
        L_0x000e:
            r1.zza = r2     // Catch:{ zzer -> 0x0015 }
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzb     // Catch:{ zzer -> 0x0015 }
            r1.zzb = r0     // Catch:{ zzer -> 0x0015 }
            goto L_0x001b
        L_0x0015:
            r1.zza = r2     // Catch:{ all -> 0x000c }
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzb     // Catch:{ all -> 0x000c }
            r1.zzb = r2     // Catch:{ all -> 0x000c }
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            return
        L_0x001d:
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzex.zzd(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm):void");
    }
}
