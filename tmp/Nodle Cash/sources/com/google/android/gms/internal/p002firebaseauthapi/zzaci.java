package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneMultiFactorAssertion;
import com.google.firebase.auth.TotpMultiFactorAssertion;
import com.google.firebase.auth.TotpSecret;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaci  reason: invalid package */
public final class zzaci {
    private static final Logger zza = new Logger("FirebaseAuth", "FirebaseAuthFallback:");
    private final zzyl zzb;
    private final zzadx zzc;

    public zzaci(FirebaseApp firebaseApp, ScheduledExecutorService scheduledExecutorService) {
        Preconditions.checkNotNull(firebaseApp);
        Context applicationContext = firebaseApp.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zzb = new zzyl(new zzacw(firebaseApp, zzact.zza()));
        this.zzc = new zzadx(applicationContext, scheduledExecutorService);
    }

    public final void zza(String str, @Nullable String str2, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(str, str2, new zzacf(zzacg, zza));
    }

    public final void zzb(String str, String str2, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzb(str, str2, new zzacf(zzacg, zza));
    }

    public final void zzc(String str, String str2, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzc(str, str2, new zzacf(zzacg, zza));
    }

    public final void zzd(String str, @Nullable String str2, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzd(str, str2, new zzacf(zzacg, zza));
    }

    public final void zze(String str, @Nullable String str2, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        this.zzb.zze(str, str2, new zzacf(zzacg, zza));
    }

    public final void zzf(String str, String str2, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzf(str, str2, new zzacf(zzacg, zza));
    }

    public final void zze(String str, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzf(str, new zzacf(zzacg, zza));
    }

    public final void zza(zzxx zzxx, zzacg zzacg) {
        Preconditions.checkNotNull(zzxx);
        Preconditions.checkNotEmpty(zzxx.zza());
        Preconditions.checkNotEmpty(zzxx.zzb());
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(zzxx.zza(), zzxx.zzb(), zzxx.zzc(), new zzacf(zzacg, zza));
    }

    public final void zzd(@Nullable String str, zzacg zzacg) {
        Preconditions.checkNotNull(zzacg);
        this.zzb.zze(str, new zzacf(zzacg, zza));
    }

    public final void zzb(String str, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzb(str, new zzacf(zzacg, zza));
    }

    public final void zzc(String str, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzc(str, new zzacf(zzacg, zza));
    }

    public final void zzb(String str, String str2, @Nullable String str3, @Nullable String str4, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacg);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzb(str, str2, str3, str4, new zzacf(zzacg, zza));
    }

    public final void zza(String str, String str2, @Nullable String str3, @Nullable String str4, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(str, str2, str3, str4, new zzacf(zzacg, zza));
    }

    public final void zza(String str, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(str, new zzacf(zzacg, zza));
    }

    public final void zza(MultiFactorAssertion multiFactorAssertion, String str, @Nullable String str2, @Nullable String str3, zzacg zzacg) {
        zzaeq zzaeq;
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotNull(zzacg);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential zza2 = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            zzaeq = zzaeu.zza(str, (String) Preconditions.checkNotNull(zza2.zzc()), (String) Preconditions.checkNotNull(zza2.getSmsCode()), str2, str3);
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
            zzaeq = zzaew.zza(str, Preconditions.checkNotEmpty(str2), Preconditions.checkNotEmpty(((TotpSecret) Preconditions.checkNotNull(totpMultiFactorAssertion.zza())).getSessionInfo()), Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str3);
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
        this.zzb.zza(zzaeq, str, new zzacf(zzacg, zza));
    }

    public final void zza(String str, MultiFactorAssertion multiFactorAssertion, @Nullable String str2, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzacg);
        if (multiFactorAssertion instanceof PhoneMultiFactorAssertion) {
            PhoneAuthCredential zza2 = ((PhoneMultiFactorAssertion) multiFactorAssertion).zza();
            this.zzb.zza((zzaes) zzaex.zza(str, (String) Preconditions.checkNotNull(zza2.zzc()), (String) Preconditions.checkNotNull(zza2.getSmsCode()), str2), new zzacf(zzacg, zza));
        } else if (multiFactorAssertion instanceof TotpMultiFactorAssertion) {
            TotpMultiFactorAssertion totpMultiFactorAssertion = (TotpMultiFactorAssertion) multiFactorAssertion;
            this.zzb.zza((zzaes) zzaez.zza(str, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzc()), str2, Preconditions.checkNotEmpty(totpMultiFactorAssertion.zzb())), new zzacf(zzacg, zza));
        } else {
            throw new IllegalArgumentException("multiFactorAssertion must be either PhoneMultiFactorAssertion or TotpMultiFactorAssertion.");
        }
    }

    public final void zza(zzya zzya, zzacg zzacg) {
        Preconditions.checkNotNull(zzya);
        this.zzb.zza(zzafj.zzb(), new zzacf(zzacg, zza));
    }

    public final void zza(zzxz zzxz, zzacg zzacg) {
        Preconditions.checkNotNull(zzxz);
        this.zzb.zza(zzafk.zza(zzxz.zzb(), zzxz.zza()), new zzacf(zzacg, zza));
    }

    public final void zza(String str, String str2, String str3, @Nullable String str4, @Nullable String str5, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(str, str2, str3, str4, str5, new zzacf(zzacg, zza));
    }

    public final void zza(String str, zzags zzags, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzags);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(str, zzags, new zzacf(zzacg, zza));
    }

    public final void zza(zzyc zzyc, zzacg zzacg) {
        Preconditions.checkNotNull(zzacg);
        Preconditions.checkNotNull(zzyc);
        this.zzb.zza(Preconditions.checkNotEmpty(zzyc.zzb()), zzadr.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzyc.zza())), new zzacf(zzacg, zza));
    }

    public final void zza(zzafy zzafy, zzacg zzacg) {
        Preconditions.checkNotNull(zzafy);
        this.zzb.zza(zzafy, new zzacf(zzacg, zza));
    }

    public final void zza(@NonNull zzyb zzyb, zzacg zzacg) {
        Preconditions.checkNotNull(zzyb);
        Preconditions.checkNotEmpty(zzyb.zzb());
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(zzyb.zzb(), zzyb.zza(), new zzacf(zzacg, zza));
    }

    public final void zza(@NonNull zzye zzye, zzacg zzacg) {
        Preconditions.checkNotNull(zzye);
        Preconditions.checkNotEmpty(zzye.zzc());
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(zzye.zzc(), zzye.zza(), zzye.zzd(), zzye.zzb(), new zzacf(zzacg, zza));
    }

    public final void zza(zzyd zzyd, zzacg zzacg) {
        Preconditions.checkNotNull(zzacg);
        Preconditions.checkNotNull(zzyd);
        zzagd zzagd = (zzagd) Preconditions.checkNotNull(zzyd.zza());
        String zzd = zzagd.zzd();
        zzacf zzacf = new zzacf(zzacg, zza);
        if (this.zzc.zzd(zzd)) {
            if (zzagd.zze()) {
                this.zzc.zzc(zzd);
            } else {
                this.zzc.zzb(zzacf, zzd);
                return;
            }
        }
        long zzb2 = zzagd.zzb();
        boolean zzf = zzagd.zzf();
        if (zza(zzb2, zzf)) {
            zzagd.zza(new zzaeh(this.zzc.zzb()));
        }
        this.zzc.zza(zzd, zzacf, zzb2, zzf);
        this.zzb.zza(zzagd, this.zzc.zza(zzacf, zzd));
    }

    public final void zza(zzyg zzyg, zzacg zzacg) {
        Preconditions.checkNotNull(zzyg);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzd(zzyg.zza(), new zzacf(zzacg, zza));
    }

    public final void zza(zzags zzags, zzacg zzacg) {
        Preconditions.checkNotNull(zzags);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(zzags, new zzacf(zzacg, zza));
    }

    public final void zza(zzagx zzagx, zzacg zzacg) {
        Preconditions.checkNotNull(zzagx);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(zzagx, new zzacf(zzacg, zza));
    }

    public final void zza(zzyf zzyf, zzacg zzacg) {
        Preconditions.checkNotNull(zzyf);
        Preconditions.checkNotNull(zzyf.zza());
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(zzyf.zza(), zzyf.zzb(), new zzacf(zzacg, zza));
    }

    public final void zza(zzyi zzyi, zzacg zzacg) {
        Preconditions.checkNotNull(zzacg);
        Preconditions.checkNotNull(zzyi);
        this.zzb.zza(zzadr.zza((PhoneAuthCredential) Preconditions.checkNotNull(zzyi.zza())), new zzacf(zzacg, zza));
    }

    public final void zza(String str, String str2, @Nullable String str3, long j2, boolean z2, boolean z3, @Nullable String str4, @Nullable String str5, boolean z4, zzacg zzacg) {
        String str6 = str2;
        String str7 = str;
        Preconditions.checkNotEmpty(str, "idToken should not be empty.");
        Preconditions.checkNotNull(zzacg);
        zzacf zzacf = new zzacf(zzacg, zza);
        if (this.zzc.zzd(str2)) {
            if (z2) {
                this.zzc.zzc(str2);
            } else {
                this.zzc.zzb(zzacf, str2);
                return;
            }
        }
        zzagn zza2 = zzagn.zza(str, str2, str3, str4, str5, (String) null);
        long j3 = j2;
        if (zza(j2, z4)) {
            zza2.zza(new zzaeh(this.zzc.zzb()));
        }
        this.zzc.zza(str2, zzacf, j2, z4);
        this.zzb.zza((zzagj) zza2, this.zzc.zza(zzacf, str2));
    }

    public final void zza(zzyh zzyh, zzacg zzacg) {
        Preconditions.checkNotNull(zzyh);
        Preconditions.checkNotNull(zzacg);
        String phoneNumber = zzyh.zzb().getPhoneNumber();
        zzacf zzacf = new zzacf(zzacg, zza);
        if (this.zzc.zzd(phoneNumber)) {
            if (zzyh.zzg()) {
                this.zzc.zzc(phoneNumber);
            } else {
                this.zzc.zzb(zzacf, phoneNumber);
                return;
            }
        }
        long zza2 = zzyh.zza();
        boolean zzh = zzyh.zzh();
        zzagl zza3 = zzagl.zza(zzyh.zzd(), zzyh.zzb().getUid(), zzyh.zzb().getPhoneNumber(), zzyh.zzc(), zzyh.zzf(), zzyh.zze());
        if (zza(zza2, zzh)) {
            zza3.zza(new zzaeh(this.zzc.zzb()));
        }
        this.zzc.zza(phoneNumber, zzacf, zza2, zzh);
        this.zzb.zza(zza3, this.zzc.zza(zzacf, phoneNumber));
    }

    public final void zza(zzagp zzagp, zzacg zzacg) {
        this.zzb.zza((zzagj) zzagp, new zzacf((zzacg) Preconditions.checkNotNull(zzacg), zza));
    }

    public final void zza(String str, String str2, @Nullable String str3, zzacg zzacg) {
        Preconditions.checkNotEmpty(str, "cachedTokenState should not be empty.");
        Preconditions.checkNotEmpty(str2, "uid should not be empty.");
        Preconditions.checkNotNull(zzacg);
        this.zzb.zzb(str, str2, str3, new zzacf(zzacg, zza));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzacg zzacg) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzacg);
        this.zzb.zza(str, userProfileChangeRequest, new zzacf(zzacg, zza));
    }

    public final void zza(zzyk zzyk, zzacg zzacg) {
        Preconditions.checkNotNull(zzyk);
        this.zzb.zza(zzafh.zza(zzyk.zza(), zzyk.zzb(), zzyk.zzc()), new zzacf(zzacg, zza));
    }

    private static boolean zza(long j2, boolean z2) {
        if (j2 > 0 && z2) {
            return true;
        }
        zza.w("App hash will not be appended to the request.", new Object[0]);
        return false;
    }
}
