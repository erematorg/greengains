package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajt  reason: invalid package */
public class zzajt {
    private static final zzaiq zza = zzaiq.zza;
    private zzaho zzb;
    private volatile zzakp zzc;
    private volatile zzaho zzd;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzajt)) {
            return false;
        }
        zzajt zzajt = (zzajt) obj;
        zzakp zzakp = this.zzc;
        zzakp zzakp2 = zzajt.zzc;
        return (zzakp == null && zzakp2 == null) ? zzc().equals(zzajt.zzc()) : (zzakp == null || zzakp2 == null) ? zzakp != null ? zzakp.equals(zzajt.zzb(zzakp.zzh())) : zzb(zzakp2.zzh()).equals(zzakp2) : zzakp.equals(zzakp2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzakp zza(zzakp zzakp) {
        zzakp zzakp2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzakp;
        return zzakp2;
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zzb();
        }
        if (this.zzc != null) {
            return this.zzc.zzl();
        }
        return 0;
    }

    public final zzaho zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            try {
                if (this.zzd != null) {
                    zzaho zzaho = this.zzd;
                    return zzaho;
                }
                if (this.zzc == null) {
                    this.zzd = zzaho.zza;
                } else {
                    this.zzd = this.zzc.zzj();
                }
                zzaho zzaho2 = this.zzd;
                return zzaho2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:8|9|10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.p002firebaseauthapi.zzakp zzb(com.google.android.gms.internal.p002firebaseauthapi.zzakp r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.firebase-auth-api.zzakp r0 = r1.zzc
            if (r0 != 0) goto L_0x001e
            monitor-enter(r1)
            com.google.android.gms.internal.firebase-auth-api.zzakp r0 = r1.zzc     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x000d
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            goto L_0x001e
        L_0x000b:
            r2 = move-exception
            goto L_0x001c
        L_0x000d:
            r1.zzc = r2     // Catch:{ zzajk -> 0x0014 }
            com.google.android.gms.internal.firebase-auth-api.zzaho r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaho.zza     // Catch:{ zzajk -> 0x0014 }
            r1.zzd = r0     // Catch:{ zzajk -> 0x0014 }
            goto L_0x001a
        L_0x0014:
            r1.zzc = r2     // Catch:{ all -> 0x000b }
            com.google.android.gms.internal.firebase-auth-api.zzaho r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaho.zza     // Catch:{ all -> 0x000b }
            r1.zzd = r2     // Catch:{ all -> 0x000b }
        L_0x001a:
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            goto L_0x001e
        L_0x001c:
            monitor-exit(r1)     // Catch:{ all -> 0x000b }
            throw r2
        L_0x001e:
            com.google.android.gms.internal.firebase-auth-api.zzakp r1 = r1.zzc
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzajt.zzb(com.google.android.gms.internal.firebase-auth-api.zzakp):com.google.android.gms.internal.firebase-auth-api.zzakp");
    }
}
