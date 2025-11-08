package com.google.firebase.auth.internal;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.state.b;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzach;
import com.google.android.gms.internal.p002firebaseauthapi.zzadq;
import com.google.android.gms.internal.p002firebaseauthapi.zzaec;
import com.google.android.gms.internal.p002firebaseauthapi.zzafi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;

public class zza {
    /* access modifiers changed from: private */
    public static final String zza = "zza";
    private static final zza zzb = new zza();
    /* access modifiers changed from: private */
    public String zzc;

    private zza() {
    }

    public final Task<zzg> zza(FirebaseAuth firebaseAuth, @Nullable String str, @Nullable Activity activity, boolean z2, boolean z3) {
        Task<zzafi> task;
        zzz zzz = (zzz) firebaseAuth.getFirebaseAuthSettings();
        zzcb zzc2 = zzcb.zzc();
        if (zzaec.zza(firebaseAuth.getApp()) || zzz.zze()) {
            return Tasks.forResult(new zzj().zza());
        }
        String str2 = zza;
        boolean zzc3 = zzz.zzc();
        Log.i(str2, "ForceRecaptchaFlow from phoneAuthOptions = " + z3 + ", ForceRecaptchaFlow from firebaseSettings = " + zzc3);
        boolean zzc4 = z3 | zzz.zzc();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Task<String> zzb2 = zzc2.zzb();
        if (zzb2 != null) {
            if (zzb2.isSuccessful()) {
                return Tasks.forResult(new zzj().zzb(zzb2.getResult()).zza());
            }
            String message = zzb2.getException().getMessage();
            Log.e(str2, "Error in previous reCAPTCHA flow: " + message);
            Log.e(str2, "Continuing with application verification as normal");
        }
        if (!z2 || zzc4) {
            zza(firebaseAuth, zzc2, activity, taskCompletionSource);
        } else {
            IntegrityManager create = IntegrityManagerFactory.create(firebaseAuth.getApp().getApplicationContext());
            if (!TextUtils.isEmpty(this.zzc)) {
                task = Tasks.forResult(new zzafi(this.zzc));
            } else {
                task = firebaseAuth.zza();
            }
            task.continueWithTask(firebaseAuth.zzf(), new zzb(this, str, create)).addOnCompleteListener(new zzc(this, taskCompletionSource, firebaseAuth, zzc2, activity));
        }
        return taskCompletionSource.getTask();
    }

    public static zza zza() {
        return zzb;
    }

    private final void zza(FirebaseAuth firebaseAuth, zzcb zzcb, Activity activity, TaskCompletionSource<zzg> taskCompletionSource) {
        Task task;
        if (activity == null) {
            taskCompletionSource.setException(new FirebaseAuthMissingActivityForRecaptchaException());
            return;
        }
        zzbi.zza(firebaseAuth.getApp().getApplicationContext(), firebaseAuth);
        Preconditions.checkNotNull(activity);
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        if (!zzau.zza().zza(activity, (TaskCompletionSource<String>) taskCompletionSource2)) {
            task = Tasks.forException(zzach.zza(new Status(17057, "reCAPTCHA flow already in progress")));
        } else {
            new zzadq(firebaseAuth, activity).zza();
            task = taskCompletionSource2.getTask();
        }
        task.addOnSuccessListener(new zzd(this, taskCompletionSource)).addOnFailureListener(new zze(this, taskCompletionSource));
    }

    public final /* synthetic */ void zza(TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, zzcb zzcb, Activity activity, Task task) {
        if (!task.isSuccessful() || task.getResult() == null || TextUtils.isEmpty(((IntegrityTokenResponse) task.getResult()).token())) {
            b.u("Play Integrity Token fetch failed, falling back to Recaptcha", task.getException() == null ? "" : task.getException().getMessage(), zza);
            zza(firebaseAuth, zzcb, activity, taskCompletionSource);
            return;
        }
        taskCompletionSource.setResult(new zzj().zza(((IntegrityTokenResponse) task.getResult()).token()).zza());
    }

    public static boolean zza(Exception exc) {
        if (!(exc instanceof FirebaseAuthMissingActivityForRecaptchaException)) {
            return (exc instanceof FirebaseAuthException) && ((FirebaseAuthException) exc).getErrorCode().endsWith("UNAUTHORIZED_DOMAIN");
        }
        return true;
    }
}
