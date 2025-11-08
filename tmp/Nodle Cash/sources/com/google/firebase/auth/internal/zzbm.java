package com.google.firebase.auth.internal;

import android.net.Uri;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class zzbm implements Continuation {
    private /* synthetic */ Uri zza;

    public /* synthetic */ zzbm(Uri uri) {
        this.zza = uri;
    }

    public final Object then(Task task) {
        return RecaptchaActivity.zza(this.zza, task);
    }
}
