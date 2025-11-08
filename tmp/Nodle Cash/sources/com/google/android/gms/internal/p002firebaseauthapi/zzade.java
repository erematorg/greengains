package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzade  reason: invalid package */
final class zzade implements Runnable {
    private final /* synthetic */ zzadh zza;
    private final /* synthetic */ zzadb zzb;

    public zzade(zzadb zzadb, zzadh zzadh) {
        this.zza = zzadh;
        this.zzb = zzadb;
    }

    public final void run() {
        synchronized (this.zzb.zza.zzh) {
            try {
                if (!this.zzb.zza.zzh.isEmpty()) {
                    this.zza.zza(this.zzb.zza.zzh.get(0), new Object[0]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
