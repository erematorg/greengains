package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafl  reason: invalid package */
public class zzafl implements zzacu<zzafl> {
    private static final String zza = "zzafl";
    private List<String> zzb;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzafl zza(String str) throws zzaah {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("authorizedDomains");
            if (optJSONArray != null) {
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    this.zzb.add(optJSONArray.getString(i3));
                }
            }
            return this;
        } catch (JSONException e3) {
            throw zzahe.zza((Exception) e3, zza, str);
        }
    }

    public final List<String> zza() {
        return this.zzb;
    }
}
