package com.google.android.gms.internal.p002firebaseauthapi;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafr  reason: invalid package */
public final class zzafr {
    private String zza;
    private String zzb;
    private String zzc;
    private Long zzd;
    private Long zze;
    private boolean zzf;

    public static zzafr zza(String str) throws UnsupportedEncodingException {
        try {
            zzafr zzafr = new zzafr();
            JSONObject jSONObject = new JSONObject(str);
            zzafr.zza = jSONObject.optString("iss");
            zzafr.zzb = jSONObject.optString("aud");
            zzafr.zzc = jSONObject.optString("sub");
            zzafr.zzd = Long.valueOf(jSONObject.optLong("iat"));
            zzafr.zze = Long.valueOf(jSONObject.optLong("exp"));
            zzafr.zzf = jSONObject.optBoolean("is_anonymous");
            return zzafr;
        } catch (JSONException e3) {
            if (Log.isLoggable("JwtToken", 3)) {
                Log.d("JwtToken", "Failed to read JwtToken from JSONObject. ".concat(String.valueOf(e3)));
            }
            throw new UnsupportedEncodingException("Failed to read JwtToken from JSONObject. ".concat(String.valueOf(e3)));
        }
    }

    public final Long zzb() {
        return this.zzd;
    }

    public final Long zza() {
        return this.zze;
    }
}
