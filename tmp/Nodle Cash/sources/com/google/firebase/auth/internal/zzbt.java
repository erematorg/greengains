package com.google.firebase.auth.internal;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import androidx.browser.trusted.c;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzac;
import com.google.android.gms.internal.p002firebaseauthapi.zzafn;
import com.google.android.gms.internal.p002firebaseauthapi.zzah;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaTasksClient;
import java.util.List;

final class zzbt implements Continuation<zzafn, Task<RecaptchaTasksClient>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzbu zzb;

    public zzbt(zzbu zzbu, String str) {
        this.zza = str;
        this.zzb = zzbu;
    }

    public final /* synthetic */ Object then(Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(new zzbr((String) Preconditions.checkNotNull(((Exception) Preconditions.checkNotNull(task.getException())).getMessage())));
        }
        zzafn zzafn = (zzafn) task.getResult();
        String zza2 = zzafn.zza();
        if (zzah.zzc(zza2)) {
            return Tasks.forException(new zzbr(c.a("No Recaptcha Enterprise siteKey configured for tenant/project ", this.zza)));
        }
        List<String> zza3 = zzac.zza((char) JsonPointer.SEPARATOR).zza((CharSequence) zza2);
        String str = zza3.size() != 4 ? null : zza3.get(3);
        if (TextUtils.isEmpty(str)) {
            return Tasks.forException(new Exception(c.a("Invalid siteKey format ", zza2)));
        }
        if (Log.isLoggable("RecaptchaHandler", 4)) {
            String str2 = this.zza;
            Log.i("RecaptchaHandler", "Successfully obtained site key for tenant " + str2);
        }
        this.zzb.zzd = zzafn;
        zzbu zzbu = this.zzb;
        Task<RecaptchaTasksClient> zza4 = zzbu.zzc.zza((Application) zzbu.zzb.getApplicationContext(), str);
        this.zzb.zza.put(this.zza, zza4);
        return zza4;
    }
}
