package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyl  reason: invalid package */
public final class zzyl {
    /* access modifiers changed from: private */
    public final zzadk zza;

    public zzyl(zzadk zzadk) {
        this.zza = (zzadk) Preconditions.checkNotNull(zzadk);
    }

    public final void zzb(String str, String str2, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzaad(this, str2, zzacf));
    }

    public final void zzc(String str, String str2, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzaag(this, str2, zzacf));
    }

    public final void zzd(String str, @Nullable String str2, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(new zzafw(str, (String) null, str2), (zzadm<zzafz>) new zzyy(this, zzacf));
    }

    public final void zze(String str, @Nullable String str2, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(new zzaen(str, str2), (zzadm<zzaem>) new zzyw(this, zzacf));
    }

    public final void zzf(String str, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzzh(this, zzacf));
    }

    public static /* synthetic */ void zza(zzyl zzyl, zzagu zzagu, zzacf zzacf, zzadn zzadn) {
        Status status;
        if (zzagu.zzo()) {
            zzf zzb = zzagu.zzb();
            String zzc = zzagu.zzc();
            String zzj = zzagu.zzj();
            if (zzagu.zzm()) {
                status = new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL);
            } else {
                status = zzal.zza(zzagu.zzd());
            }
            zzacf.zza(new zzyj(status, zzb, zzc, zzj));
            return;
        }
        zzyl.zza(new zzafm(zzagu.zzi(), zzagu.zze(), Long.valueOf(zzagu.zza()), "Bearer"), zzagu.zzh(), zzagu.zzg(), Boolean.valueOf(zzagu.zzn()), zzagu.zzb(), zzacf, zzadn);
    }

    public final void zzf(String str, String str2, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacf);
        zza(str2, (zzadm<zzafm>) new zzzk(this, str, zzacf));
    }

    public final void zzb(String str, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(new zzafa(str), (zzadm<zzafm>) new zzyo(this, zzacf));
    }

    public final void zzc(String str, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzzw(this, zzacf));
    }

    public final void zzd(@Nullable String str, zzacf zzacf) {
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(str, (zzadm<Void>) new zzaaa(this, zzacf));
    }

    public final void zze(@Nullable String str, zzacf zzacf) {
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(new zzagh(str), (zzadm<zzagg>) new zzaac(this, zzacf));
    }

    private final void zzb(zzafh zzafh, zzacf zzacf) {
        Preconditions.checkNotNull(zzafh);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzafh, (zzadm<zzafg>) new zzzz(this, zzacf));
    }

    public final void zzb(String str, String str2, @Nullable String str3, @Nullable String str4, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(new zzagz(str, str2, str3, str4), (zzadm<zzagy>) new zzyq(this, zzacf));
    }

    public final void zzb(String str, String str2, @Nullable String str3, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzzm(this, str2, str3, zzacf));
    }

    public static /* synthetic */ void zza(zzyl zzyl, zzacf zzacf, zzagh zzagh, zzadn zzadn) {
        Preconditions.checkNotNull(zzacf);
        Preconditions.checkNotNull(zzagh);
        Preconditions.checkNotNull(zzadn);
        zzyl.zza.zza(zzagh, (zzadm<zzagg>) new zzze(zzyl, zzacf, zzadn));
    }

    public static /* synthetic */ void zza(zzyl zzyl, zzacf zzacf, zzafm zzafm, zzagf zzagf, zzadn zzadn) {
        Preconditions.checkNotNull(zzacf);
        Preconditions.checkNotNull(zzafm);
        Preconditions.checkNotNull(zzagf);
        Preconditions.checkNotNull(zzadn);
        zzyl.zza.zza(new zzafd(zzafm.zzc()), (zzadm<zzafc>) new zzyr(zzyl, zzadn, zzacf, zzafm, zzagf));
    }

    public static /* synthetic */ void zza(zzyl zzyl, zzacf zzacf, zzafm zzafm, zzaff zzaff, zzagf zzagf, zzadn zzadn) {
        Preconditions.checkNotNull(zzacf);
        Preconditions.checkNotNull(zzafm);
        Preconditions.checkNotNull(zzaff);
        Preconditions.checkNotNull(zzagf);
        Preconditions.checkNotNull(zzadn);
        zzyl.zza.zza(zzagf, (zzadm<zzage>) new zzyu(zzyl, zzagf, zzaff, zzacf, zzafm, zzadn));
    }

    public final void zza(String str, @Nullable String str2, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        zzagf zzagf = new zzagf();
        zzagf.zze(str);
        zzagf.zzh(str2);
        this.zza.zza(zzagf, (zzadm<zzage>) new zzaaf(this, zzacf));
    }

    public final void zza(String str, String str2, @Nullable String str3, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(new zzafw(str, str2, str3), (zzadm<zzafz>) new zzza(this, zzacf));
    }

    public final void zza(String str, String str2, @Nullable String str3, @Nullable String str4, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(new zzagh(str, str2, (String) null, str3, str4, (String) null), (zzadm<zzagg>) new zzyn(this, zzacf));
    }

    public final void zza(String str, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzzy(this, zzacf));
    }

    private final void zza(String str, zzadm<zzafm> zzadm) {
        Preconditions.checkNotNull(zzadm);
        Preconditions.checkNotEmpty(str);
        zzafm zzb = zzafm.zzb(str);
        if (zzb.zzg()) {
            zzadm.zza(zzb);
            return;
        }
        this.zza.zza(new zzafa(zzb.zzd()), (zzadm<zzafm>) new zzaai(this, zzadm));
    }

    public final void zza(zzaeq zzaeq, String str, zzacf zzacf) {
        Preconditions.checkNotNull(zzaeq);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzzq(this, zzaeq, zzacf));
    }

    public final void zza(zzaes zzaes, zzacf zzacf) {
        Preconditions.checkNotNull(zzaes);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzaes, (zzadm<zzaev>) new zzzs(this, zzacf));
    }

    /* access modifiers changed from: private */
    public final void zza(zzaeo zzaeo, zzacf zzacf) {
        Preconditions.checkNotNull(zzaeo);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzaeo, (zzadm<zzaer>) new zzys(this, zzacf));
    }

    /* access modifiers changed from: private */
    public final void zza(zzafm zzafm, @Nullable String str, @Nullable String str2, @Nullable Boolean bool, @Nullable zzf zzf, zzacf zzacf, zzadn zzadn) {
        Preconditions.checkNotNull(zzafm);
        Preconditions.checkNotNull(zzadn);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(new zzafd(zzafm.zzc()), (zzadm<zzafc>) new zzyt(this, zzadn, str2, str, bool, zzf, zzacf, zzafm));
    }

    public final void zza(zzafj zzafj, zzacf zzacf) {
        Preconditions.checkNotNull(zzafj);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzafj, (zzadm<zzafi>) new zzzt(this, zzacf));
    }

    public final void zza(zzafk zzafk, zzacf zzacf) {
        Preconditions.checkNotNull(zzafk);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzafk, (zzadm<zzafn>) new zzzu(this, zzacf));
    }

    public final void zza(String str, String str2, String str3, @Nullable String str4, @Nullable String str5, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzacf);
        zza(str3, (zzadm<zzafm>) new zzzb(this, str, str2, str4, str5, zzacf));
    }

    public final void zza(String str, zzags zzags, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzags);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzzf(this, zzags, zzacf));
    }

    public final void zza(String str, zzahb zzahb, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzahb);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzzd(this, zzahb, zzacf));
    }

    public final void zza(zzafy zzafy, zzacf zzacf) {
        this.zza.zza(zzafy, (zzadm<zzaga>) new zzaab(this, zzacf));
    }

    public final void zza(String str, @Nullable ActionCodeSettings actionCodeSettings, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        zzafh zzafh = new zzafh(4);
        zzafh.zzd(str);
        if (actionCodeSettings != null) {
            zzafh.zza(actionCodeSettings);
        }
        zzb(zzafh, zzacf);
    }

    public final void zza(String str, ActionCodeSettings actionCodeSettings, @Nullable String str2, @Nullable String str3, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacf);
        zzafh zzafh = new zzafh(actionCodeSettings.zza());
        zzafh.zzb(str);
        zzafh.zza(actionCodeSettings);
        zzafh.zzc(str2);
        zzafh.zza(str3);
        this.zza.zza(zzafh, (zzadm<zzafg>) new zzyv(this, zzacf));
    }

    public final void zza(zzagd zzagd, zzacf zzacf) {
        Preconditions.checkNotEmpty(zzagd.zzd());
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzagd, (zzadm<zzagc>) new zzyz(this, zzacf));
    }

    public final void zza(zzags zzags, zzacf zzacf) {
        Preconditions.checkNotNull(zzags);
        Preconditions.checkNotNull(zzacf);
        zzags.zzb(true);
        this.zza.zza(zzags, (zzadm<zzagu>) new zzzo(this, zzacf));
    }

    public final void zza(zzagx zzagx, zzacf zzacf) {
        Preconditions.checkNotNull(zzagx);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzagx, (zzadm<zzagw>) new zzyx(this, zzacf));
    }

    public final void zza(EmailAuthCredential emailAuthCredential, @Nullable String str, zzacf zzacf) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zzacf);
        if (emailAuthCredential.zzg()) {
            zza(emailAuthCredential.zzb(), (zzadm<zzafm>) new zzyp(this, emailAuthCredential, str, zzacf));
        } else {
            zza(new zzaeo(emailAuthCredential, (String) null, str), zzacf);
        }
    }

    public final void zza(zzahb zzahb, zzacf zzacf) {
        Preconditions.checkNotNull(zzahb);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzahb, (zzadm<zzaha>) new zzzc(this, zzacf));
    }

    public final void zza(zzagj zzagj, zzacf zzacf) {
        Preconditions.checkNotNull(zzagj);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzagj, (zzadm<zzagi>) new zzzn(this, zzagj, zzacf));
    }

    public final void zza(zzagl zzagl, zzacf zzacf) {
        Preconditions.checkNotNull(zzagl);
        Preconditions.checkNotNull(zzacf);
        this.zza.zza(zzagl, (zzadm<zzagk>) new zzzr(this, zzacf));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzacf zzacf) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzacf);
        zza(str, (zzadm<zzafm>) new zzaae(this, userProfileChangeRequest, zzacf));
    }

    public final void zza(zzafh zzafh, zzacf zzacf) {
        zzb(zzafh, zzacf);
    }
}
