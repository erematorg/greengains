package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafn  reason: invalid package */
public class zzafn implements zzacu<zzafn> {
    private static final String zza = "zzafn";
    @Nullable
    private String zzb;
    private zzaq<zzafx> zzc;

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final zzafn zza(String str) throws zzaah {
        zzaq<zzafx> zzaq;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("recaptchaKey"));
            if (jSONObject.has("recaptchaEnforcementState")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("recaptchaEnforcementState");
                if (optJSONArray != null) {
                    if (optJSONArray.length() != 0) {
                        zzap zzg = zzaq.zzg();
                        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i3);
                            zzg.zza(jSONObject2 == null ? zzafx.zza((String) null, (String) null) : zzafx.zza(Strings.emptyToNull(jSONObject2.optString("provider")), Strings.emptyToNull(jSONObject2.optString("enforcementState"))));
                        }
                        zzaq = zzg.zza();
                        this.zzc = zzaq;
                    }
                }
                zzaq = zzaq.zza(new ArrayList());
                this.zzc = zzaq;
            }
            return this;
        } catch (NullPointerException | JSONException e3) {
            throw zzahe.zza(e3, zza, str);
        }
    }

    public final boolean zzb(String str) {
        Preconditions.checkNotEmpty(str);
        zzaq<zzafx> zzaq = this.zzc;
        String str2 = null;
        if (zzaq != null && !zzaq.isEmpty()) {
            zzaq<zzafx> zzaq2 = this.zzc;
            int size = zzaq2.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    break;
                }
                zzafx zzafx = zzaq2.get(i3);
                i3++;
                zzafx zzafx2 = zzafx;
                String zza2 = zzafx2.zza();
                String zzb2 = zzafx2.zzb();
                if (zza2 != null && zzb2 != null && zzb2.equals(str)) {
                    str2 = zzafx2.zza();
                    break;
                }
            }
        }
        if (str2 == null) {
            return false;
        }
        return str2.equals("ENFORCE") || str2.equals("AUDIT");
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }
}
