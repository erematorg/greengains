package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafu  reason: invalid package */
public final class zzafu {
    private List<zzafv> zza;

    public zzafu() {
        this.zza = new ArrayList();
    }

    public static zzafu zza(JSONArray jSONArray) throws JSONException {
        zzafv zzafv;
        if (jSONArray == null || jSONArray.length() == 0) {
            return new zzafu(new ArrayList());
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i3);
            if (jSONObject == null) {
                zzafv = new zzafv();
            } else {
                String emptyToNull = Strings.emptyToNull(jSONObject.optString("federatedId", (String) null));
                String emptyToNull2 = Strings.emptyToNull(jSONObject.optString("displayName", (String) null));
                String emptyToNull3 = Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null));
                zzafv = new zzafv(emptyToNull, emptyToNull2, emptyToNull3, Strings.emptyToNull(jSONObject.optString("providerId", (String) null)), (String) null, Strings.emptyToNull(jSONObject.optString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, (String) null)), Strings.emptyToNull(jSONObject.optString("email", (String) null)));
            }
            arrayList.add(zzafv);
        }
        return new zzafu(arrayList);
    }

    private zzafu(List<zzafv> list) {
        if (!list.isEmpty()) {
            this.zza = Collections.unmodifiableList(list);
        } else {
            this.zza = Collections.emptyList();
        }
    }

    public final List<zzafv> zza() {
        return this.zza;
    }
}
