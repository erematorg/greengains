package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import androidx.browser.trusted.c;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzaj;
import com.google.firebase.auth.internal.zzg;

final class zzk implements OnCompleteListener<zzg> {
    private final /* synthetic */ PhoneAuthOptions zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    public zzk(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, String str) {
        this.zza = phoneAuthOptions;
        this.zzb = str;
        this.zzc = firebaseAuth;
    }

    public final void onComplete(Task<zzg> task) {
        String str;
        String str2;
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            String str3 = "Error while validating application identity: ";
            if (exception != null) {
                str3 = c.a(str3, exception.getMessage());
            }
            Log.e("FirebaseAuth", str3);
            if (exception == null || !zza.zza(exception)) {
                Log.e("FirebaseAuth", "Proceeding without any application identifier.");
                str2 = null;
                str = null;
            } else {
                FirebaseAuth.zza((FirebaseException) exception, this.zza, this.zzb);
                return;
            }
        } else {
            str2 = task.getResult().zzc();
            str = task.getResult().zza();
        }
        long longValue = this.zza.zzg().longValue();
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zza2 = this.zzc.zza(this.zza.zzh(), this.zza.zze());
        if (TextUtils.isEmpty(str2)) {
            zza2 = this.zzc.zza(this.zza, zza2);
        }
        PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks = zza2;
        zzaj zzaj = (zzaj) Preconditions.checkNotNull(this.zza.zzc());
        if (zzaj.zzd()) {
            this.zzc.zze.zza(zzaj, (String) Preconditions.checkNotNull(this.zza.zzh()), this.zzc.zzi, longValue, this.zza.zzd() != null, this.zza.zzk(), str2, str, this.zzc.zzi(), onVerificationStateChangedCallbacks, this.zza.zzi(), this.zza.zza());
        } else {
            this.zzc.zze.zza(zzaj, (PhoneMultiFactorInfo) Preconditions.checkNotNull(this.zza.zzf()), this.zzc.zzi, longValue, this.zza.zzd() != null, this.zza.zzk(), str2, str, this.zzc.zzi(), onVerificationStateChangedCallbacks, this.zza.zzi(), this.zza.zza());
        }
    }
}
