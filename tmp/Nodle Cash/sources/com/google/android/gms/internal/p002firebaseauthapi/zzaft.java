package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "PasskeyInfoCreator")
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaft  reason: invalid package */
public final class zzaft extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaft> CREATOR = new zzafs();
    @SafeParcelable.Field(getter = "getCredentialId", id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getName", id = 2)
    private final String zzb;
    @SafeParcelable.Field(getter = "getDisplayName", id = 3)
    private final String zzc;

    @SafeParcelable.Constructor
    public zzaft(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    public static zzaq<zzaft> zza(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return zzaq.zza(new ArrayList());
        }
        zzap zzg = zzaq.zzg();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i3);
            zzg.zza(new zzaft(jSONObject.getString("credentialId"), jSONObject.getString("name"), jSONObject.getString("displayName")));
        }
        return zzg.zza();
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static final zzaft zza(JSONObject jSONObject) throws JSONException {
        return new zzaft(jSONObject.getString("credentialId"), jSONObject.getString("name"), jSONObject.getString("displayName"));
    }

    public static final JSONObject zza(zzaft zzaft) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("credentialId", zzaft.zza);
        jSONObject.put("name", zzaft.zzb);
        jSONObject.put("displayName", zzaft.zzc);
        return jSONObject;
    }
}
