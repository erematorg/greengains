package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class zzbk implements OnCompleteListener {
    private /* synthetic */ RecaptchaActivity zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzbk(RecaptchaActivity recaptchaActivity, String str) {
        this.zza = recaptchaActivity;
        this.zzb = str;
    }

    public final void onComplete(Task task) {
        this.zza.zza(this.zzb, task);
    }
}
