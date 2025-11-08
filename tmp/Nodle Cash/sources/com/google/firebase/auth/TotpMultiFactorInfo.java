package com.google.firebase.auth;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.p002firebaseauthapi.zzagq;
import com.google.android.gms.internal.p002firebaseauthapi.zzxy;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "TotpMultiFactorInfoCreator")
public class TotpMultiFactorInfo extends MultiFactorInfo {
    @NonNull
    public static final Parcelable.Creator<TotpMultiFactorInfo> CREATOR = new zzat();
    @SafeParcelable.Field(getter = "getUid", id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getEnrollmentTimestamp", id = 3)
    private final long zzc;
    @SafeParcelable.Field(getter = "getTotpInfo", id = 4)
    private final zzagq zzd;

    @SafeParcelable.Constructor
    public TotpMultiFactorInfo(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) long j2, @SafeParcelable.Param(id = 4) zzagq zzagq) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
        this.zzc = j2;
        this.zzd = (zzagq) Preconditions.checkNotNull(zzagq, "totpInfo cannot be null.");
    }

    @NonNull
    public static TotpMultiFactorInfo zza(@NonNull JSONObject jSONObject) {
        if (jSONObject.has("enrollmentTimestamp")) {
            long optLong = jSONObject.optLong("enrollmentTimestamp");
            if (jSONObject.opt("totpInfo") != null) {
                return new TotpMultiFactorInfo(jSONObject.optString("uid"), jSONObject.optString("displayName"), optLong, new zzagq());
            }
            throw new IllegalArgumentException("A totpInfo is required to build a TotpMultiFactorInfo instance.");
        }
        throw new IllegalArgumentException("An enrollment timestamp in seconds of UTC time since Unix epoch is required to build a TotpMultiFactorInfo instance.");
    }

    @Nullable
    public String getDisplayName() {
        return this.zzb;
    }

    public long getEnrollmentTimestamp() {
        return this.zzc;
    }

    @NonNull
    public String getFactorId() {
        return TotpMultiFactorGenerator.FACTOR_ID;
    }

    @NonNull
    public String getUid() {
        return this.zza;
    }

    @Nullable
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(MultiFactorInfo.FACTOR_ID_KEY, TotpMultiFactorGenerator.FACTOR_ID);
            jSONObject.putOpt("uid", this.zza);
            jSONObject.putOpt("displayName", this.zzb);
            jSONObject.putOpt("enrollmentTimestamp", Long.valueOf(this.zzc));
            jSONObject.putOpt("totpInfo", this.zzd);
            return jSONObject;
        } catch (JSONException e3) {
            Log.d("TotpMultiFactorInfo", "Failed to jsonify this object");
            throw new zzxy(e3);
        }
    }

    @SuppressLint({"FirebaseUnknownNullness"})
    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUid(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeLong(parcel, 3, getEnrollmentTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
