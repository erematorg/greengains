package com.google.android.gms.internal.p002firebaseauthapi;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.TotpMultiFactorAssertion;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzac;
import com.google.firebase.auth.internal.zzae;
import com.google.firebase.auth.internal.zzaj;
import com.google.firebase.auth.internal.zzar;
import com.google.firebase.auth.internal.zzas;
import com.google.firebase.auth.internal.zzbh;
import com.google.firebase.auth.internal.zzbz;
import com.google.firebase.auth.internal.zzi;
import com.google.firebase.auth.internal.zzy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaak  reason: invalid package */
public final class zzaak extends zzadj {
    public zzaak(FirebaseApp firebaseApp, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        this.zza = new zzaci(firebaseApp, scheduledExecutorService);
        this.zzb = executor;
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        return zza((zzaaj) new zzaaj(str, str2).zza(firebaseApp));
    }

    public final Task<ActionCodeResult> zzb(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        return zza((zzaam) new zzaam(str, str2).zza(firebaseApp));
    }

    public final Task<SignInMethodQueryResult> zzc(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        return zza((zzaaq) new zzaaq(str, str2).zza(firebaseApp));
    }

    public final Task<Void> zzd(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbz zzbz) {
        return zza((zzaca) new zzaca(str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, String str2, @Nullable String str3) {
        return zza((zzaal) new zzaal(str, str2, str3).zza(firebaseApp));
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, @Nullable String str, zzbz zzbz) {
        return zza((zzaba) new zzaba(authCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<AuthResult> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, @Nullable String str, zzbz zzbz) {
        return zza((zzaaz) new zzaaz(authCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, String str2, String str3, @Nullable String str4, zzi zzi) {
        return zza((zzaao) new zzaao(str, str2, str3, str4).zza(firebaseApp).zza(zzi));
    }

    public final Task<String> zzd(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        return zza((zzace) new zzace(str, str2).zza(firebaseApp));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, @Nullable String str, zzbz zzbz) {
        return zza((zzabb) new zzabb(emailAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbz zzbz) {
        return zza((zzabx) new zzabx(str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    @NonNull
    public final Task<Void> zza(FirebaseUser firebaseUser, zzas zzas) {
        return zza((zzaan) new zzaan().zza(firebaseUser).zza(zzas).zza((zzar) zzas));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, PhoneMultiFactorAssertion phoneMultiFactorAssertion, FirebaseUser firebaseUser, @Nullable String str, zzi zzi) {
        zzads.zza();
        zzaap zzaap = new zzaap(phoneMultiFactorAssertion, firebaseUser.zze(), str, (String) null);
        zzaap.zza(firebaseApp).zza(zzi);
        return zza(zzaap);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, @Nullable String str3, @Nullable String str4, zzbz zzbz) {
        return zza((zzabd) new zzabd(str, str2, str3, str4).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, TotpMultiFactorAssertion totpMultiFactorAssertion, FirebaseUser firebaseUser, @Nullable String str, @Nullable String str2, zzi zzi) {
        zzaap zzaap = new zzaap(totpMultiFactorAssertion, firebaseUser.zze(), str, str2);
        zzaap.zza(firebaseApp).zza(zzi);
        return zza(zzaap);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, @Nullable String str, zzbz zzbz) {
        zzads.zza();
        return zza((zzabf) new zzabf(phoneAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, @Nullable FirebaseUser firebaseUser, PhoneMultiFactorAssertion phoneMultiFactorAssertion, String str, zzi zzi) {
        zzads.zza();
        zzaas zzaas = new zzaas(phoneMultiFactorAssertion, str, (String) null);
        zzaas.zza(firebaseApp).zza(zzi);
        if (firebaseUser != null) {
            zzaas.zza(firebaseUser);
        }
        return zza(zzaas);
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, @Nullable String str2, @Nullable String str3) {
        actionCodeSettings.zza(6);
        return zza((zzabj) new zzabj(str, actionCodeSettings, str2, str3, "sendSignInLinkToEmail").zza(firebaseApp));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, @Nullable FirebaseUser firebaseUser, TotpMultiFactorAssertion totpMultiFactorAssertion, String str, @Nullable String str2, zzi zzi) {
        zzaas zzaas = new zzaas(totpMultiFactorAssertion, str, str2);
        zzaas.zza(firebaseApp).zza(zzi);
        if (firebaseUser != null) {
            zzaas.zza(firebaseUser);
        }
        return zza(zzaas);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, String str, String str2, @Nullable String str3, @Nullable String str4, zzi zzi) {
        return zza((zzabq) new zzabq(str, str2, str3, str4).zza(firebaseApp).zza(zzi));
    }

    public final Task<GetTokenResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbz zzbz) {
        return zza((zzaar) new zzaar(str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, zzbz zzbz) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbz);
        List<String> zzg = firebaseUser.zzg();
        if ((zzg != null && !zzg.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzach.zza(new Status((int) FirebaseError.ERROR_NO_SUCH_PROVIDER, str)));
        }
        str.getClass();
        if (!str.equals("password")) {
            return zza((zzaby) new zzaby(str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
        }
        return zza((zzabv) new zzabv().zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<zzafi> zza() {
        return zza(new zzaau());
    }

    public final Task<zzafn> zza(@Nullable String str, String str2) {
        return zza(new zzaat(str, str2));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, @Nullable String str, zzbz zzbz) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzbz);
        List<String> zzg = firebaseUser.zzg();
        if (zzg != null && zzg.contains(authCredential.getProvider())) {
            return Tasks.forException(zzach.zza(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzf()) {
                return zza((zzaaw) new zzaaw(emailAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
            }
            return zza((zzaax) new zzaax(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
        } else if (authCredential instanceof PhoneAuthCredential) {
            zzads.zza();
            return zza((zzaay) new zzaay((PhoneAuthCredential) authCredential).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
        } else {
            Preconditions.checkNotNull(firebaseApp);
            Preconditions.checkNotNull(authCredential);
            Preconditions.checkNotNull(firebaseUser);
            Preconditions.checkNotNull(zzbz);
            return zza((zzaav) new zzaav(authCredential).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
        }
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, @Nullable String str, zzbz zzbz) {
        return zza((zzabc) new zzabc(emailAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, @Nullable String str3, @Nullable String str4, zzbz zzbz) {
        return zza((zzabe) new zzabe(str, str2, str3, str4).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, @Nullable String str, zzbz zzbz) {
        zzads.zza();
        return zza((zzabg) new zzabg(phoneAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    @NonNull
    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, zzbz zzbz) {
        return zza((zzabi) new zzabi().zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zza(String str, String str2, String str3, @Nullable String str4) {
        return zza(new zzabh(str, str2, str3, str4));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, @Nullable ActionCodeSettings actionCodeSettings, String str) {
        return zza((zzabk) new zzabk(str, actionCodeSettings).zza(firebaseApp));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, @Nullable String str2, @Nullable String str3) {
        actionCodeSettings.zza(1);
        return zza((zzabj) new zzabj(str, actionCodeSettings, str2, str3, "sendPasswordResetEmail").zza(firebaseApp));
    }

    @NonNull
    public final Task<Void> zza(@Nullable String str) {
        return zza(new zzabm(str));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, zzi zzi, @Nullable String str) {
        return zza((zzabl) new zzabl(str).zza(firebaseApp).zza(zzi));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, AuthCredential authCredential, @Nullable String str, zzi zzi) {
        return zza((zzabo) new zzabo(authCredential, str).zza(firebaseApp).zza(zzi));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, @Nullable String str2, zzi zzi) {
        return zza((zzabn) new zzabn(str, str2).zza(firebaseApp).zza(zzi));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, @Nullable String str, zzi zzi) {
        return zza((zzabp) new zzabp(emailAuthCredential, str).zza(firebaseApp).zza(zzi));
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, @Nullable String str, zzi zzi) {
        zzads.zza();
        return zza((zzabs) new zzabs(phoneAuthCredential, str).zza(firebaseApp).zza(zzi));
    }

    public final Task<Void> zza(zzaj zzaj, String str, @Nullable String str2, long j2, boolean z2, boolean z3, @Nullable String str3, @Nullable String str4, boolean z4, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, @Nullable Activity activity) {
        zzabr zzabr = new zzabr(zzaj, str, str2, j2, z2, z3, str3, str4, z4);
        String str5 = str;
        zzabr.zza(onVerificationStateChangedCallbacks, activity, executor, str);
        return zza(zzabr);
    }

    public final Task<zzagi> zza(zzaj zzaj, @Nullable String str) {
        return zza(new zzabu(zzaj, str));
    }

    public final Task<Void> zza(zzaj zzaj, PhoneMultiFactorInfo phoneMultiFactorInfo, @Nullable String str, long j2, boolean z2, boolean z3, @Nullable String str2, @Nullable String str3, boolean z4, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Executor executor, @Nullable Activity activity) {
        zzabt zzabt = new zzabt(phoneMultiFactorInfo, Preconditions.checkNotEmpty(zzaj.zzc()), str, j2, z2, z3, str2, str3, z4);
        Activity activity2 = activity;
        zzabt.zza(onVerificationStateChangedCallbacks, activity2, executor, phoneMultiFactorInfo.getUid());
        return zza(zzabt);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, @Nullable String str2, zzbz zzbz) {
        return zza((zzabw) new zzabw(firebaseUser.zze(), str, str2).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, zzbz zzbz) {
        zzads.zza();
        return zza((zzabz) new zzabz(phoneAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, zzbz zzbz) {
        return zza((zzacc) new zzacc(userProfileChangeRequest).zza(firebaseApp).zza(firebaseUser).zza(zzbz).zza((zzar) zzbz));
    }

    public final Task<Void> zza(String str, String str2, ActionCodeSettings actionCodeSettings) {
        actionCodeSettings.zza(7);
        return zza(new zzacb(str, str2, actionCodeSettings));
    }

    @VisibleForTesting
    @NonNull
    public static zzac zza(FirebaseApp firebaseApp, zzaff zzaff) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzaff);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new zzy(zzaff, "firebase"));
        List<zzafv> zzl = zzaff.zzl();
        if (zzl != null && !zzl.isEmpty()) {
            for (int i3 = 0; i3 < zzl.size(); i3++) {
                arrayList.add(new zzy(zzl.get(i3)));
            }
        }
        zzac zzac = new zzac(firebaseApp, arrayList);
        zzac.zza(new zzae(zzaff.zzb(), zzaff.zza()));
        zzac.zza(zzaff.zzn());
        zzac.zza(zzaff.zze());
        zzac.zzc(zzbh.zza(zzaff.zzk()));
        zzac.zzb(zzaff.zzd());
        return zzac;
    }

    public final void zza(FirebaseApp firebaseApp, zzagd zzagd, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, Executor executor) {
        zza((zzacd) new zzacd(zzagd).zza(firebaseApp).zza(onVerificationStateChangedCallbacks, activity, executor, zzagd.zzd()));
    }
}
