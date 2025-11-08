package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.NonNull;
import androidx.browser.trusted.c;
import com.google.android.gms.tasks.OnFailureListener;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadz  reason: invalid package */
final class zzadz implements OnFailureListener {
    public zzadz(zzadx zzadx) {
    }

    public final void onFailure(@NonNull Exception exc) {
        zzadx.zza.e(c.a("SmsRetrieverClient failed to start: ", exc.getMessage()), new Object[0]);
    }
}
