package com.google.firebase.auth.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.p002firebaseauthapi.zzafz;
import com.google.firebase.auth.ActionCodeInfo;
import com.google.firebase.auth.ActionCodeResult;

public final class zzr implements ActionCodeResult {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final ActionCodeInfo zzd;

    public zzr(zzafz zzafz) {
        int i3 = 5;
        this.zzb = zzafz.zzg() ? zzafz.zzc() : zzafz.zzb();
        this.zzc = zzafz.zzb();
        ActionCodeInfo actionCodeInfo = null;
        if (!zzafz.zzh()) {
            this.zza = 3;
            this.zzd = null;
            return;
        }
        String zzd2 = zzafz.zzd();
        zzd2.getClass();
        char c3 = 65535;
        switch (zzd2.hashCode()) {
            case -1874510116:
                if (zzd2.equals("REVERT_SECOND_FACTOR_ADDITION")) {
                    c3 = 0;
                    break;
                }
                break;
            case -1452371317:
                if (zzd2.equals("PASSWORD_RESET")) {
                    c3 = 1;
                    break;
                }
                break;
            case -1341836234:
                if (zzd2.equals("VERIFY_EMAIL")) {
                    c3 = 2;
                    break;
                }
                break;
            case -1099157829:
                if (zzd2.equals("VERIFY_AND_CHANGE_EMAIL")) {
                    c3 = 3;
                    break;
                }
                break;
            case 870738373:
                if (zzd2.equals("EMAIL_SIGNIN")) {
                    c3 = 4;
                    break;
                }
                break;
            case 970484929:
                if (zzd2.equals("RECOVER_EMAIL")) {
                    c3 = 5;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                i3 = 6;
                break;
            case 1:
                i3 = 0;
                break;
            case 2:
                i3 = 1;
                break;
            case 3:
                break;
            case 4:
                i3 = 4;
                break;
            case 5:
                i3 = 2;
                break;
            default:
                i3 = 3;
                break;
        }
        this.zza = i3;
        if (i3 == 4 || i3 == 3) {
            this.zzd = null;
            return;
        }
        if (zzafz.zzf()) {
            actionCodeInfo = new zzs(zzafz.zzb(), zzbh.zza(zzafz.zza()));
        } else if (zzafz.zzg()) {
            actionCodeInfo = new zzq(zzafz.zzc(), zzafz.zzb());
        } else if (zzafz.zze()) {
            actionCodeInfo = new zzp(zzafz.zzb());
        }
        this.zzd = actionCodeInfo;
    }

    @Nullable
    public final String getData(int i3) {
        if (this.zza == 4) {
            return null;
        }
        if (i3 == 0) {
            return this.zzb;
        }
        if (i3 != 1) {
            return null;
        }
        return this.zzc;
    }

    @Nullable
    public final ActionCodeInfo getInfo() {
        return this.zzd;
    }

    public final int getOperation() {
        return this.zza;
    }
}
