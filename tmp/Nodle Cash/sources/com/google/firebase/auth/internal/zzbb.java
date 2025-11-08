package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p002firebaseauthapi.zzach;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import java.lang.ref.WeakReference;

final class zzbb extends BroadcastReceiver {
    private final WeakReference<Activity> zza;
    private final TaskCompletionSource<String> zzb;
    private final /* synthetic */ zzau zzc;

    public zzbb(zzau zzau, Activity activity, TaskCompletionSource<String> taskCompletionSource) {
        this.zzc = zzau;
        this.zza = new WeakReference<>(activity);
        this.zzb = taskCompletionSource;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zza.get() == null) {
            Log.e("FederatedAuthReceiver", "Failed to unregister BroadcastReceiver because the Activity that launched this flow has been garbage collected; please do not finish() your Activity while performing a FederatedAuthProvider operation.");
            this.zzb.setException(zzach.zza(new Status((int) FirebaseError.ERROR_INTERNAL_ERROR, "Activity that started the web operation is no longer alive; see logcat for details")));
            zzau.zza(context);
        } else if (intent.hasExtra("com.google.firebase.auth.internal.OPERATION")) {
            String stringExtra = intent.getStringExtra("com.google.firebase.auth.internal.OPERATION");
            if ("com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA".equals(stringExtra)) {
                zzau.zza(this.zzc, intent, (TaskCompletionSource) this.zzb, context);
                return;
            }
            TaskCompletionSource<String> taskCompletionSource = this.zzb;
            taskCompletionSource.setException(zzach.zza(zzal.zza("WEB_CONTEXT_CANCELED:Unknown operation received (" + stringExtra + ")")));
        } else if (zzcc.zzb(intent)) {
            this.zzb.setException(zzach.zza(zzcc.zza(intent)));
            zzau.zza(context);
        } else if (intent.hasExtra("com.google.firebase.auth.internal.EXTRA_CANCELED")) {
            this.zzb.setException(zzach.zza(zzal.zza("WEB_CONTEXT_CANCELED")));
            zzau.zza(context);
        }
    }
}
