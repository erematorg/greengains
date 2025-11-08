package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.common.api.Status;

final class zzbr extends zzbd {
    final /* synthetic */ zzbs zza;

    public zzbr(zzbs zzbs) {
        this.zza = zzbs;
    }

    public final void zzc(String str) {
        zzbs zzbs = this.zza;
        if (str != null) {
            zzbs.setResult(new zzbv(str));
        } else {
            zzbs.setResult(new zzbv(new Status(AuthApiStatusCodes.AUTH_APP_CERT_ERROR)));
        }
    }
}
