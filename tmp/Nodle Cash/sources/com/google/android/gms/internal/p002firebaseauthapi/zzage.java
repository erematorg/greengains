package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Strings;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzage  reason: invalid package */
public class zzage implements zzacu<zzage> {
    private static final String zza = "zzage";
    private String zzb;
    private String zzc;
    private Boolean zzd;
    private String zze;
    private String zzf;
    private zzafu zzg;
    private String zzh;
    private String zzi;
    private long zzj;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzage zza(String str) throws zzaah {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("email", (String) null));
            this.zzc = Strings.emptyToNull(jSONObject.optString("passwordHash", (String) null));
            this.zzd = Boolean.valueOf(jSONObject.optBoolean("emailVerified", false));
            this.zze = Strings.emptyToNull(jSONObject.optString("displayName", (String) null));
            this.zzf = Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null));
            this.zzg = zzafu.zza(jSONObject.optJSONArray("providerUserInfo"));
            this.zzh = Strings.emptyToNull(jSONObject.optString("idToken", (String) null));
            this.zzi = Strings.emptyToNull(jSONObject.optString("refreshToken", (String) null));
            this.zzj = jSONObject.optLong("expiresIn", 0);
            return this;
        } catch (NullPointerException | JSONException e3) {
            throw zzahe.zza(e3, zza, str);
        }
    }

    public final long zza() {
        return this.zzj;
    }

    @Nullable
    public final String zzc() {
        return this.zzh;
    }

    @Nullable
    public final String zzd() {
        return this.zzi;
    }

    @Nullable
    public final List<zzafv> zze() {
        zzafu zzafu = this.zzg;
        if (zzafu != null) {
            return zzafu.zza();
        }
        return null;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }
}
