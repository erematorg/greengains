package com.google.firebase.auth.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuthException;

final class zze implements OnFailureListener {
    private final /* synthetic */ TaskCompletionSource zza;

    public zze(zza zza2, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void onFailure(@NonNull Exception exc) {
        String zzb = zza.zza;
        String message = exc.getMessage();
        Log.e(zzb, "Failed to get reCAPTCHA token with error [" + message + "]- calling backend without app verification");
        if (!(exc instanceof FirebaseAuthException) || !((FirebaseAuthException) exc).getErrorCode().endsWith("UNAUTHORIZED_DOMAIN")) {
            this.zza.setResult(new zzj().zza());
        } else {
            this.zza.setException(exc);
        }
    }
}
