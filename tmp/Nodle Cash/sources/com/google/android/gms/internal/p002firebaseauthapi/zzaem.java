package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaem  reason: invalid package */
public class zzaem implements zzacu<zzaem> {
    private static final String zza = "zzaem";
    private String zzb;
    private boolean zzc;
    private String zzd;
    private boolean zze;
    private zzagr zzf = zzagr.zza();
    private List<String> zzg;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzaem zza(String str) throws zzaah {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optString("authUri", (String) null);
            this.zzc = jSONObject.optBoolean("registered", false);
            this.zzd = jSONObject.optString("providerId", (String) null);
            this.zze = jSONObject.optBoolean("forExistingProvider", false);
            if (!jSONObject.has("allProviders")) {
                this.zzf = zzagr.zza();
            } else {
                this.zzf = new zzagr(1, zzahe.zza(jSONObject.optJSONArray("allProviders")));
            }
            this.zzg = zzahe.zza(jSONObject.optJSONArray("signinMethods"));
            return this;
        } catch (NullPointerException | JSONException e3) {
            throw zzahe.zza(e3, zza, str);
        }
    }

    @Nullable
    public final List<String> zza() {
        return this.zzg;
    }
}
