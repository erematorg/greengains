package com.google.firebase.auth.internal;

import android.util.Base64;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.p002firebaseauthapi.zzafi;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import java.security.MessageDigest;

final class zzb implements Continuation<zzafi, Task<IntegrityTokenResponse>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ IntegrityManager zzb;
    private final /* synthetic */ zza zzc;

    public zzb(zza zza2, String str, IntegrityManager integrityManager) {
        this.zza = str;
        this.zzb = integrityManager;
        this.zzc = zza2;
    }

    @Nullable
    public final /* synthetic */ Object then(Task task) throws Exception {
        if (task.isSuccessful()) {
            this.zzc.zzc = ((zzafi) task.getResult()).zza();
            return this.zzb.requestIntegrityToken(IntegrityTokenRequest.builder().setCloudProjectNumber(Long.parseLong(((zzafi) task.getResult()).zza())).setNonce(new String(Base64.encode(MessageDigest.getInstance("SHA-256").digest(this.zza.getBytes("UTF-8")), 11))).build());
        }
        String zzb2 = zza.zza;
        String message = task.getException().getMessage();
        Log.e(zzb2, "Problem retrieving Play Integrity producer project:  " + message);
        return Tasks.forException(task.getException());
    }
}
