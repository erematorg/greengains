package com.google.firebase.auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

final class zzx implements Continuation<GetTokenResult, Task<Void>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ FirebaseAuth zzb;

    public zzx(FirebaseAuth firebaseAuth, String str) {
        this.zza = str;
        this.zzb = firebaseAuth;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        return !task.isSuccessful() ? Tasks.forException((Exception) Preconditions.checkNotNull(task.getException())) : this.zzb.zze.zza(this.zza, (String) Preconditions.checkNotNull(((GetTokenResult) task.getResult()).getToken()), "apple.com", this.zzb.zzk);
    }
}
