package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.internal.zzar;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadb  reason: invalid package */
final class zzadb implements zzacg {
    final /* synthetic */ zzacz zza;

    public zzadb(zzacz zzacz) {
        this.zza = zzacz;
    }

    private final void zza(zzadh zzadh) {
        this.zza.zzi.execute(new zzade(this, zzadh));
    }

    public final void zzb(String str) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 8;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        this.zza.zzo = str;
        zza((zzadh) new zzada(this, str));
    }

    public final void zzc(String str) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 7;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        zzacz zzacz = this.zza;
        zzacz.zzn = str;
        zzacz.zza(zzacz);
    }

    private final void zza(Status status, AuthCredential authCredential, @Nullable String str, @Nullable String str2) {
        zzacz.zza(this.zza, status);
        zzacz zzacz = this.zza;
        zzacz.zzp = authCredential;
        zzacz.zzq = str;
        zzacz.zzr = str2;
        zzar zzar = zzacz.zzf;
        if (zzar != null) {
            zzar.zza(status);
        }
        this.zza.zza(status);
    }

    public final void zzb() throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 6;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        zzacz.zza(this.zza);
    }

    public final void zzc() throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 9;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        zzacz.zza(this.zza);
    }

    public final void zza(String str) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 8;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        zzacz zzacz = this.zza;
        zzacz.zzo = str;
        zzacz.zzz = true;
        this.zza.zzx = true;
        zza((zzadh) new zzadc(this, str));
    }

    public final void zza(zzaem zzaem) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 3;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        zzacz zzacz = this.zza;
        zzacz.zzl = zzaem;
        zzacz.zza(zzacz);
    }

    public final void zza() throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 5;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        zzacz.zza(this.zza);
    }

    public final void zza(zzyj zzyj) {
        zza(zzyj.zza(), zzyj.zzb(), zzyj.zzc(), zzyj.zzd());
    }

    public final void zza(zzym zzym) {
        zzacz zzacz = this.zza;
        zzacz.zzs = zzym;
        zzacz.zza(zzal.zza("REQUIRES_SECOND_FACTOR_AUTH"));
    }

    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 2;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        zza(status, phoneAuthCredential, (String) null, (String) null);
    }

    public final void zza(Status status) throws RemoteException {
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null) {
            if (statusMessage.contains("MISSING_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17081);
            } else if (statusMessage.contains("MISSING_MFA_ENROLLMENT_ID")) {
                status = new Status(17082);
            } else if (statusMessage.contains("INVALID_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17083);
            } else if (statusMessage.contains("MFA_ENROLLMENT_NOT_FOUND")) {
                status = new Status(17084);
            } else if (statusMessage.contains("ADMIN_ONLY_OPERATION")) {
                status = new Status(17085);
            } else if (statusMessage.contains("UNVERIFIED_EMAIL")) {
                status = new Status(17086);
            } else if (statusMessage.contains("SECOND_FACTOR_EXISTS")) {
                status = new Status(17087);
            } else if (statusMessage.contains("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                status = new Status(17088);
            } else if (statusMessage.contains("UNSUPPORTED_FIRST_FACTOR")) {
                status = new Status(17089);
            } else if (statusMessage.contains("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                status = new Status(17090);
            }
        }
        zzacz zzacz = this.zza;
        if (zzacz.zza == 8) {
            zzacz.zzz = true;
            this.zza.zzx = false;
            zza((zzadh) new zzadf(this, status));
            return;
        }
        zzacz.zza(zzacz, status);
        this.zza.zza(status);
    }

    public final void zza(zzafi zzafi) throws RemoteException {
        zzacz zzacz = this.zza;
        zzacz.zzu = zzafi;
        zzacz.zza(zzacz);
    }

    public final void zza(zzafn zzafn) throws RemoteException {
        zzacz zzacz = this.zza;
        zzacz.zzt = zzafn;
        zzacz.zza(zzacz);
    }

    public final void zza(zzafm zzafm, zzaff zzaff) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 2;
        Preconditions.checkState(z2, "Unexpected response type: " + i3);
        zzacz zzacz = this.zza;
        zzacz.zzj = zzafm;
        zzacz.zzk = zzaff;
        zzacz.zza(zzacz);
    }

    public final void zza(@Nullable zzafz zzafz) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 4;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        zzacz zzacz = this.zza;
        zzacz.zzm = zzafz;
        zzacz.zza(zzacz);
    }

    public final void zza(zzaga zzaga) throws RemoteException {
        zzacz zzacz = this.zza;
        zzacz.zzw = zzaga;
        zzacz.zza(zzacz);
    }

    public final void zza(zzagi zzagi) throws RemoteException {
        zzacz zzacz = this.zza;
        zzacz.zzv = zzagi;
        zzacz.zza(zzacz);
    }

    public final void zza(zzafm zzafm) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = true;
        if (i3 != 1) {
            z2 = false;
        }
        Preconditions.checkState(z2, "Unexpected response type: " + i3);
        zzacz zzacz = this.zza;
        zzacz.zzj = zzafm;
        zzacz.zza(zzacz);
    }

    public final void zza(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        int i3 = this.zza.zza;
        boolean z2 = i3 == 8;
        Preconditions.checkState(z2, "Unexpected response type " + i3);
        this.zza.zzz = true;
        this.zza.zzx = true;
        zza((zzadh) new zzadd(this, phoneAuthCredential));
    }
}
