package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.core.state.b;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacw  reason: invalid package */
final class zzacw extends zzadk implements zzaee {
    private zzacq zza;
    private zzacp zzb;
    private zzadt zzc;
    private final zzact zzd;
    private final FirebaseApp zze;
    private String zzf;
    @VisibleForTesting
    private zzacv zzg;

    public zzacw(FirebaseApp firebaseApp, zzact zzact) {
        this(firebaseApp, zzact, (zzadt) null, (zzacq) null, (zzacp) null);
    }

    @NonNull
    private final zzacv zzb() {
        if (this.zzg == null) {
            this.zzg = new zzacv(this.zze, this.zzd.zzb());
        }
        return this.zzg;
    }

    public final void zza(zzaen zzaen, zzadm<zzaem> zzadm) {
        Preconditions.checkNotNull(zzaen);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/createAuthUri", this.zzf), zzaen, zzadm, zzaem.class, zzacq.zza);
    }

    @VisibleForTesting
    private zzacw(FirebaseApp firebaseApp, zzact zzact, zzadt zzadt, zzacq zzacq, zzacp zzacp) {
        this.zze = firebaseApp;
        this.zzf = firebaseApp.getOptions().getApiKey();
        this.zzd = (zzact) Preconditions.checkNotNull(zzact);
        zza((zzadt) null, (zzacq) null, (zzacp) null);
        zzaec.zza(this.zzf, this);
    }

    public final void zza(zzaep zzaep, zzadm<Void> zzadm) {
        Preconditions.checkNotNull(zzaep);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/deleteAccount", this.zzf), zzaep, zzadm, Void.class, zzacq.zza);
    }

    public final void zza(zzaeo zzaeo, zzadm<zzaer> zzadm) {
        Preconditions.checkNotNull(zzaeo);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/emailLinkSignin", this.zzf), zzaeo, zzadm, zzaer.class, zzacq.zza);
    }

    public final void zza(zzaeq zzaeq, zzadm<zzaet> zzadm) {
        Preconditions.checkNotNull(zzaeq);
        Preconditions.checkNotNull(zzadm);
        zzacp zzacp = this.zzb;
        zzadp.zza(zzacp.zza("/accounts/mfaEnrollment:finalize", this.zzf), zzaeq, zzadm, zzaet.class, zzacp.zza);
    }

    public final void zza(zzaes zzaes, zzadm<zzaev> zzadm) {
        Preconditions.checkNotNull(zzaes);
        Preconditions.checkNotNull(zzadm);
        zzacp zzacp = this.zzb;
        zzadp.zza(zzacp.zza("/accounts/mfaSignIn:finalize", this.zzf), zzaes, zzadm, zzaev.class, zzacp.zza);
    }

    public final void zza(zzafa zzafa, zzadm<zzafm> zzadm) {
        Preconditions.checkNotNull(zzafa);
        Preconditions.checkNotNull(zzadm);
        zzadt zzadt = this.zzc;
        zzadp.zza(zzadt.zza("/token", this.zzf), zzafa, zzadm, zzafm.class, zzadt.zza);
    }

    public final void zza(zzafd zzafd, zzadm<zzafc> zzadm) {
        Preconditions.checkNotNull(zzafd);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/getAccountInfo", this.zzf), zzafd, zzadm, zzafc.class, zzacq.zza);
    }

    public final void zza(zzafh zzafh, zzadm<zzafg> zzadm) {
        Preconditions.checkNotNull(zzafh);
        Preconditions.checkNotNull(zzadm);
        if (zzafh.zzb() != null) {
            zzb().zzb(zzafh.zzb().zze());
        }
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/getOobConfirmationCode", this.zzf), zzafh, zzadm, zzafg.class, zzacq.zza);
    }

    public final void zza(zzafj zzafj, zzadm<zzafi> zzadm) {
        Preconditions.checkNotNull(zzafj);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/getRecaptchaParam", this.zzf), zzadm, zzafi.class, zzacq.zza);
    }

    public final void zza(zzafk zzafk, zzadm<zzafn> zzadm) {
        Preconditions.checkNotNull(zzafk);
        Preconditions.checkNotNull(zzadm);
        zzacp zzacp = this.zzb;
        String zza2 = zzacp.zza("/recaptchaConfig", this.zzf);
        String zzb2 = zzafk.zzb();
        String zzc2 = zzafk.zzc();
        zzadp.zza(zza2 + "&clientType=" + zzb2 + "&version=" + zzc2, zzadm, zzafn.class, zzacp.zza);
    }

    public final void zza() {
        zza((zzadt) null, (zzacq) null, (zzacp) null);
    }

    public final void zza(zzafw zzafw, zzadm<zzafz> zzadm) {
        Preconditions.checkNotNull(zzafw);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/resetPassword", this.zzf), zzafw, zzadm, zzafz.class, zzacq.zza);
    }

    public final void zza(zzafy zzafy, zzadm<zzaga> zzadm) {
        Preconditions.checkNotNull(zzafy);
        Preconditions.checkNotNull(zzadm);
        zzacp zzacp = this.zzb;
        zzadp.zza(zzacp.zza("/accounts:revokeToken", this.zzf), zzafy, zzadm, zzaga.class, zzacp.zza);
    }

    public final void zza(zzagd zzagd, zzadm<zzagc> zzadm) {
        Preconditions.checkNotNull(zzagd);
        Preconditions.checkNotNull(zzadm);
        if (!TextUtils.isEmpty(zzagd.zzc())) {
            zzb().zzb(zzagd.zzc());
        }
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/sendVerificationCode", this.zzf), zzagd, zzadm, zzagc.class, zzacq.zza);
    }

    public final void zza(zzagf zzagf, zzadm<zzage> zzadm) {
        Preconditions.checkNotNull(zzagf);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/setAccountInfo", this.zzf), zzagf, zzadm, zzage.class, zzacq.zza);
    }

    public final void zza(@Nullable String str, zzadm<Void> zzadm) {
        Preconditions.checkNotNull(zzadm);
        zzb().zza(str);
        zzadm.zza(null);
    }

    public final void zza(zzagh zzagh, zzadm<zzagg> zzadm) {
        Preconditions.checkNotNull(zzagh);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/signupNewUser", this.zzf), zzagh, zzadm, zzagg.class, zzacq.zza);
    }

    public final void zza(zzagj zzagj, zzadm<zzagi> zzadm) {
        Preconditions.checkNotNull(zzagj);
        Preconditions.checkNotNull(zzadm);
        if (zzagj instanceof zzagn) {
            zzagn zzagn = (zzagn) zzagj;
            if (!TextUtils.isEmpty(zzagn.zzb())) {
                zzb().zzb(zzagn.zzb());
            }
        }
        zzacp zzacp = this.zzb;
        zzadp.zza(zzacp.zza("/accounts/mfaEnrollment:start", this.zzf), zzagj, zzadm, zzagi.class, zzacp.zza);
    }

    public final void zza(zzagl zzagl, zzadm<zzagk> zzadm) {
        Preconditions.checkNotNull(zzagl);
        Preconditions.checkNotNull(zzadm);
        if (!TextUtils.isEmpty(zzagl.zzb())) {
            zzb().zzb(zzagl.zzb());
        }
        zzacp zzacp = this.zzb;
        zzadp.zza(zzacp.zza("/accounts/mfaSignIn:start", this.zzf), zzagl, zzadm, zzagk.class, zzacp.zza);
    }

    private final void zza(zzadt zzadt, zzacq zzacq, zzacp zzacp) {
        this.zzc = null;
        this.zza = null;
        this.zzb = null;
        String zza2 = zzaed.zza("firebear.secureToken");
        if (TextUtils.isEmpty(zza2)) {
            zza2 = zzaec.zzd(this.zzf);
        } else {
            b.u("Found hermetic configuration for secureToken URL: ", zza2, "LocalClient");
        }
        if (this.zzc == null) {
            this.zzc = new zzadt(zza2, zzb());
        }
        String zza3 = zzaed.zza("firebear.identityToolkit");
        if (TextUtils.isEmpty(zza3)) {
            zza3 = zzaec.zzb(this.zzf);
        } else {
            b.u("Found hermetic configuration for identityToolkit URL: ", zza3, "LocalClient");
        }
        if (this.zza == null) {
            this.zza = new zzacq(zza3, zzb());
        }
        String zza4 = zzaed.zza("firebear.identityToolkitV2");
        if (TextUtils.isEmpty(zza4)) {
            zza4 = zzaec.zzc(this.zzf);
        } else {
            b.u("Found hermetic configuration for identityToolkitV2 URL: ", zza4, "LocalClient");
        }
        if (this.zzb == null) {
            this.zzb = new zzacp(zza4, zzb());
        }
    }

    public final void zza(zzags zzags, zzadm<zzagu> zzadm) {
        Preconditions.checkNotNull(zzags);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/verifyAssertion", this.zzf), zzags, zzadm, zzagu.class, zzacq.zza);
    }

    public final void zza(zzagx zzagx, zzadm<zzagw> zzadm) {
        Preconditions.checkNotNull(zzagx);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/verifyCustomToken", this.zzf), zzagx, zzadm, zzagw.class, zzacq.zza);
    }

    public final void zza(zzagz zzagz, zzadm<zzagy> zzadm) {
        Preconditions.checkNotNull(zzagz);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/verifyPassword", this.zzf), zzagz, zzadm, zzagy.class, zzacq.zza);
    }

    public final void zza(zzahb zzahb, zzadm<zzaha> zzadm) {
        Preconditions.checkNotNull(zzahb);
        Preconditions.checkNotNull(zzadm);
        zzacq zzacq = this.zza;
        zzadp.zza(zzacq.zza("/verifyPhoneNumber", this.zzf), zzahb, zzadm, zzaha.class, zzacq.zza);
    }

    public final void zza(zzahd zzahd, zzadm<zzahc> zzadm) {
        Preconditions.checkNotNull(zzahd);
        Preconditions.checkNotNull(zzadm);
        zzacp zzacp = this.zzb;
        zzadp.zza(zzacp.zza("/accounts/mfaEnrollment:withdraw", this.zzf), zzahd, zzadm, zzahc.class, zzacp.zza);
    }
}
