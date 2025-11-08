package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafy  reason: invalid package */
public abstract class zzafy implements zzacr {
    public static zzagb zzg() {
        return new zzaei();
    }

    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("idToken", zzc());
        jSONObject.put(SchemaSymbols.ATTVAL_TOKEN, zzf());
        jSONObject.put("providerId", zzd());
        jSONObject.put("tokenType", zzb().toString());
        jSONObject.put("tenantId", zze());
        return jSONObject.toString();
    }

    public abstract zzafb zzb();

    public abstract String zzc();

    public abstract String zzd();

    @Nullable
    public abstract String zze();

    public abstract String zzf();
}
