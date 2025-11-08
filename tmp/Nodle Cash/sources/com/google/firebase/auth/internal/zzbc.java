package com.google.firebase.auth.internal;

import android.net.Uri;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class zzbc implements Continuation {
    private /* synthetic */ Uri zza;

    public /* synthetic */ zzbc(Uri uri) {
        this.zza = uri;
    }

    public final Object then(Task task) {
        return GenericIdpActivity.zza(this.zza, task);
    }
}
