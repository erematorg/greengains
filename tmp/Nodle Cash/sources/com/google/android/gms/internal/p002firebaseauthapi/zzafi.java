package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafi  reason: invalid package */
public class zzafi implements zzacu<zzafi> {
    private static final String zza = "zzafi";
    @Nullable
    private String zzb;

    public zzafi() {
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzafi zza(String str) throws zzaah {
        try {
            this.zzb = Strings.emptyToNull(new JSONObject(str).optString("producerProjectNumber"));
            return this;
        } catch (NullPointerException | JSONException e3) {
            throw zzahe.zza(e3, zza, str);
        }
    }

    public zzafi(String str) {
        this.zzb = str;
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }
}
