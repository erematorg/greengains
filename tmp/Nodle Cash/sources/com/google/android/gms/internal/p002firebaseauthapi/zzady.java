package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzady  reason: invalid package */
final class zzady extends zzacf {
    private final String zza;
    private final /* synthetic */ zzadx zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzady(zzadx zzadx, zzacf zzacf, String str) {
        super(zzacf);
        this.zzb = zzadx;
        this.zza = str;
    }

    public final void zza(Status status) {
        zzadx.zza.e(C0118y.f("SMS verification code request failed: ", CommonStatusCodes.getStatusCodeString(status.getStatusCode()), StringUtils.SPACE, status.getStatusMessage()), new Object[0]);
        zzaea zzaea = (zzaea) this.zzb.zzd.get(this.zza);
        if (zzaea != null) {
            for (zzacf zza2 : zzaea.zzb) {
                zza2.zza(status);
            }
            this.zzb.zzc(this.zza);
        }
    }

    public final void zzb(String str) {
        zzadx.zza.d("onCodeSent", new Object[0]);
        zzaea zzaea = (zzaea) this.zzb.zzd.get(this.zza);
        if (zzaea != null) {
            for (zzacf zzb2 : zzaea.zzb) {
                zzb2.zzb(str);
            }
            zzaea.zzg = true;
            zzaea.zzd = str;
            if (zzaea.zza <= 0) {
                this.zzb.zzb(this.zza);
            } else if (!zzaea.zzc) {
                this.zzb.zze(this.zza);
            } else if (!zzah.zzc(zzaea.zze)) {
                zzadx.zza(this.zzb, this.zza);
            }
        }
    }
}
