package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;

final class zzw implements Runnable {
    private final /* synthetic */ FirebaseAuth zza;
    private final /* synthetic */ InternalTokenResult zzb;

    public zzw(FirebaseAuth firebaseAuth, InternalTokenResult internalTokenResult) {
        this.zza = firebaseAuth;
        this.zzb = internalTokenResult;
    }

    public final void run() {
        for (IdTokenListener onIdTokenChanged : this.zza.zzc) {
            onIdTokenChanged.onIdTokenChanged(this.zzb);
        }
        for (FirebaseAuth.IdTokenListener onIdTokenChanged2 : this.zza.zzb) {
            onIdTokenChanged2.onIdTokenChanged(this.zza);
        }
    }
}
