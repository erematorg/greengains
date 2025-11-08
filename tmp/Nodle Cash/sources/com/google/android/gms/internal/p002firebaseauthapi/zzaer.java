package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaer  reason: invalid package */
public class zzaer implements zzacu<zzaer> {
    private static final String zza = "zzaer";
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private boolean zzf;
    private long zzg;
    @Nullable
    private List<zzafq> zzh;
    @Nullable
    private String zzi;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzaer zza(String str) throws zzaah {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = jSONObject.optString("localId", (String) null);
            this.zzc = jSONObject.optString("email", (String) null);
            this.zzd = jSONObject.optString("idToken", (String) null);
            this.zze = jSONObject.optString("refreshToken", (String) null);
            this.zzf = jSONObject.optBoolean("isNewUser", false);
            this.zzg = jSONObject.optLong("expiresIn", 0);
            this.zzh = zzafq.zza(jSONObject.optJSONArray("mfaInfo"));
            this.zzi = jSONObject.optString("mfaPendingCredential", (String) null);
            return this;
        } catch (NullPointerException | JSONException e3) {
            throw zzahe.zza(e3, zza, str);
        }
    }

    public final long zza() {
        return this.zzg;
    }

    @Nullable
    public final String zzc() {
        return this.zzi;
    }

    @NonNull
    public final String zzd() {
        return this.zze;
    }

    @Nullable
    public final List<zzafq> zze() {
        return this.zzh;
    }

    public final boolean zzf() {
        return !TextUtils.isEmpty(this.zzi);
    }

    public final boolean zzg() {
        return this.zzf;
    }

    @NonNull
    public final String zzb() {
        return this.zzd;
    }
}
