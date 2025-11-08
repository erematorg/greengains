package com.google.firebase.auth;

import android.util.Log;
import androidx.constraintlayout.core.state.b;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzg;

final class zzl implements OnCompleteListener<zzg> {
    private final /* synthetic */ PhoneAuthOptions zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    public zzl(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, String str) {
        this.zza = phoneAuthOptions;
        this.zzb = str;
        this.zzc = firebaseAuth;
    }

    public final void onComplete(Task<zzg> task) {
        String str;
        String str2;
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            b.u("Error while validating application identity: ", exception != null ? exception.getMessage() : "", "FirebaseAuth");
            if (exception == null || !zza.zza(exception)) {
                Log.e("FirebaseAuth", "Proceeding without any application identifier.");
                str = null;
                str2 = null;
            } else {
                FirebaseAuth.zza((FirebaseException) exception, this.zza, this.zzb);
                return;
            }
        } else {
            String zzc2 = task.getResult().zzc();
            str2 = task.getResult().zza();
            str = zzc2;
        }
        this.zzc.zza(this.zza, str, str2);
    }
}
