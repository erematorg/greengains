package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.zzf;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafc  reason: invalid package */
public class zzafc implements zzacu<zzafc> {
    private static final String zza = "zzafc";
    private zzafe zzb;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzafc zza(String str) throws zzaah {
        zzafe zzafe;
        int i3;
        zzaff zzaff;
        String str2 = str;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (!jSONObject.has("users")) {
                zzafe = new zzafe();
            } else {
                JSONArray optJSONArray = jSONObject.optJSONArray("users");
                if (optJSONArray != null) {
                    if (optJSONArray.length() != 0) {
                        ArrayList arrayList = new ArrayList(optJSONArray.length());
                        boolean z2 = false;
                        int i4 = 0;
                        while (i4 < optJSONArray.length()) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i4);
                            if (jSONObject2 == null) {
                                zzaff = new zzaff();
                                i3 = i4;
                            } else {
                                i3 = i4;
                                zzaff = new zzaff(Strings.emptyToNull(jSONObject2.optString("localId", (String) null)), Strings.emptyToNull(jSONObject2.optString("email", (String) null)), jSONObject2.optBoolean("emailVerified", z2), Strings.emptyToNull(jSONObject2.optString("displayName", (String) null)), Strings.emptyToNull(jSONObject2.optString("photoUrl", (String) null)), zzafu.zza(jSONObject2.optJSONArray("providerUserInfo")), Strings.emptyToNull(jSONObject2.optString("rawPassword", (String) null)), Strings.emptyToNull(jSONObject2.optString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, (String) null)), jSONObject2.optLong("createdAt", 0), jSONObject2.optLong("lastLoginAt", 0), false, (zzf) null, zzafq.zza(jSONObject2.optJSONArray("mfaInfo")), zzaft.zza(jSONObject2.optJSONArray("passkeyInfo")));
                            }
                            arrayList.add(zzaff);
                            i4 = i3 + 1;
                            z2 = false;
                        }
                        zzafe = new zzafe(arrayList);
                    }
                }
                zzafe = new zzafe(new ArrayList());
            }
            this.zzb = zzafe;
            return this;
        } catch (NullPointerException | JSONException e3) {
            throw zzahe.zza(e3, zza, str2);
        }
    }

    public final List<zzaff> zza() {
        return this.zzb.zza();
    }
}
