package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagm  reason: invalid package */
public final class zzagm extends zzagi {
    private static final String zza = "zzagm";
    @Nullable
    private String zzb;

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final zzagm zzb(String str) throws zzaah {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("phoneSessionInfo");
            if (optJSONObject != null) {
                this.zzb = zzah.zza(optJSONObject.optString("sessionInfo"));
            }
            return this;
        } catch (NullPointerException | JSONException e3) {
            throw zzahe.zza(e3, zza, str);
        }
    }

    public final /* synthetic */ zzacu zza(String str) throws zzaah {
        return (zzagm) zzb(str);
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }
}
