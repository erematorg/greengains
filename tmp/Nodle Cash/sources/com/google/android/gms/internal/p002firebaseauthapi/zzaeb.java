package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaeb  reason: invalid package */
final class zzaeb extends BroadcastReceiver {
    private final String zza;
    private final /* synthetic */ zzadx zzb;

    public zzaeb(zzadx zzadx, String str) {
        this.zzb = zzadx;
        this.zza = str;
    }

    public final void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            if (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode() == 0) {
                String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                zzaea zzaea = (zzaea) this.zzb.zzd.get(this.zza);
                if (zzaea == null) {
                    zzadx.zza.e("Verification code received with no active retrieval session.", new Object[0]);
                } else {
                    String zza2 = zzadx.zza(str);
                    zzaea.zze = zza2;
                    if (zza2 == null) {
                        zzadx.zza.e("Unable to extract verification code.", new Object[0]);
                    } else if (!zzah.zzc(zzaea.zzd)) {
                        zzadx.zza(this.zzb, this.zza);
                    }
                }
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}
