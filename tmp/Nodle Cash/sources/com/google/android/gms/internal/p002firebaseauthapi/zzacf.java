package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacf  reason: invalid package */
public class zzacf {
    private final zzacg zza;
    private final Logger zzb;

    public zzacf(zzacf zzacf) {
        this(zzacf.zza, zzacf.zzb);
    }

    public final void zza(String str) {
        try {
            this.zza.zza(str);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending auto retrieval timeout response.", e3, new Object[0]);
        }
    }

    public void zzb(String str) {
        try {
            this.zza.zzb(str);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending send verification code response.", e3, new Object[0]);
        }
    }

    public final void zzc(String str) {
        try {
            this.zza.zzc(str);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending set account info response.", e3, new Object[0]);
        }
    }

    public final void zza(zzaem zzaem) {
        try {
            this.zza.zza(zzaem);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending create auth uri response.", e3, new Object[0]);
        }
    }

    public final void zzb() {
        try {
            this.zza.zzb();
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending email verification response.", e3, new Object[0]);
        }
    }

    public final void zzc() {
        try {
            this.zza.zzc();
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when setting FirebaseUI Version", e3, new Object[0]);
        }
    }

    public zzacf(zzacg zzacg, Logger logger) {
        this.zza = (zzacg) Preconditions.checkNotNull(zzacg);
        this.zzb = (Logger) Preconditions.checkNotNull(logger);
    }

    public final void zza() {
        try {
            this.zza.zza();
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending delete account response.", e3, new Object[0]);
        }
    }

    public final void zza(zzyj zzyj) {
        try {
            this.zza.zza(zzyj);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending failure result with credential", e3, new Object[0]);
        }
    }

    public final void zza(zzym zzym) {
        try {
            this.zza.zza(zzym);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending failure result for mfa", e3, new Object[0]);
        }
    }

    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) {
        try {
            this.zza.zza(status, phoneAuthCredential);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending failure result.", e3, new Object[0]);
        }
    }

    public void zza(Status status) {
        try {
            this.zza.zza(status);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending failure result.", e3, new Object[0]);
        }
    }

    public final void zza(zzafi zzafi) {
        try {
            this.zza.zza(zzafi);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending Play Integrity Producer project response.", e3, new Object[0]);
        }
    }

    public final void zza(zzafn zzafn) {
        try {
            this.zza.zza(zzafn);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending get recaptcha config response.", e3, new Object[0]);
        }
    }

    public final void zza(zzafm zzafm, zzaff zzaff) {
        try {
            this.zza.zza(zzafm, zzaff);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending get token and account info user response", e3, new Object[0]);
        }
    }

    public final void zza(@Nullable zzafz zzafz) {
        try {
            this.zza.zza(zzafz);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending password reset response.", e3, new Object[0]);
        }
    }

    public final void zza(zzaga zzaga) {
        try {
            this.zza.zza(zzaga);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending revoke token response.", e3, new Object[0]);
        }
    }

    public final void zza(zzagi zzagi) {
        try {
            this.zza.zza(zzagi);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending start mfa enrollment response.", e3, new Object[0]);
        }
    }

    public final void zza(zzafm zzafm) {
        try {
            this.zza.zza(zzafm);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending token result.", e3, new Object[0]);
        }
    }

    public final void zza(PhoneAuthCredential phoneAuthCredential) {
        try {
            this.zza.zza(phoneAuthCredential);
        } catch (RemoteException e3) {
            this.zzb.e("RemoteException when sending verification completed response.", e3, new Object[0]);
        }
    }
}
