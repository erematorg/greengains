package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadg  reason: invalid package */
public final class zzadg<ResultT, CallbackT> implements zzacx<ResultT> {
    private final zzacz<ResultT, CallbackT> zza;
    private final TaskCompletionSource<ResultT> zzb;

    public zzadg(zzacz<ResultT, CallbackT> zzacz, TaskCompletionSource<ResultT> taskCompletionSource) {
        this.zza = zzacz;
        this.zzb = taskCompletionSource;
    }

    public final void zza(ResultT resultt, Status status) {
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status != null) {
            zzacz<ResultT, CallbackT> zzacz = this.zza;
            if (zzacz.zzs != null) {
                TaskCompletionSource<ResultT> taskCompletionSource = this.zzb;
                FirebaseAuth instance = FirebaseAuth.getInstance(zzacz.zzc);
                zzacz<ResultT, CallbackT> zzacz2 = this.zza;
                taskCompletionSource.setException(zzach.zza(instance, zzacz2.zzs, ("reauthenticateWithCredential".equals(zzacz2.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) ? this.zza.zzd : null));
                return;
            }
            AuthCredential authCredential = zzacz.zzp;
            if (authCredential != null) {
                this.zzb.setException(zzach.zza(status, authCredential, zzacz.zzq, zzacz.zzr));
            } else {
                this.zzb.setException(zzach.zza(status));
            }
        } else {
            this.zzb.setResult(resultt);
        }
    }
}
