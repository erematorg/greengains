package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class zzbn implements Continuation {
    private /* synthetic */ zzbl zza;

    public /* synthetic */ zzbn(zzbl zzbl) {
        this.zza = zzbl;
    }

    public final Object then(Task task) {
        zzbl zzbl = this.zza;
        if (task.isSuccessful()) {
            return zzbl.zza((String) task.getResult());
        }
        String message = ((Exception) Preconditions.checkNotNull(task.getException())).getMessage();
        Log.e("RecaptchaCallWrapper", "Failed to get Recaptcha token, error - " + message + "\n\n Failing open with a fake token.");
        return zzbl.zza("NO_RECAPTCHA");
    }
}
