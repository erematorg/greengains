package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import androidx.autofill.HintConstants;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahb  reason: invalid package */
public final class zzahb implements zzacr {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private boolean zzf;

    private zzahb() {
    }

    public static zzahb zza(String str, String str2, boolean z2) {
        zzahb zzahb = new zzahb();
        zzahb.zzb = Preconditions.checkNotEmpty(str);
        zzahb.zzc = Preconditions.checkNotEmpty(str2);
        zzahb.zzf = z2;
        return zzahb;
    }

    public static zzahb zzb(String str, String str2, boolean z2) {
        zzahb zzahb = new zzahb();
        zzahb.zza = Preconditions.checkNotEmpty(str);
        zzahb.zzd = Preconditions.checkNotEmpty(str2);
        zzahb.zzf = z2;
        return zzahb;
    }

    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.zzd)) {
            jSONObject.put(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, this.zza);
            jSONObject.put("temporaryProof", this.zzd);
        } else {
            jSONObject.put("sessionInfo", this.zzb);
            jSONObject.put("code", this.zzc);
        }
        String str = this.zze;
        if (str != null) {
            jSONObject.put("idToken", str);
        }
        if (!this.zzf) {
            jSONObject.put("operation", 2);
        }
        return jSONObject.toString();
    }

    public final void zza(String str) {
        this.zze = str;
    }
}
