package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.autofill.HintConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.p002firebaseauthapi.zzaff;
import com.google.android.gms.internal.p002firebaseauthapi.zzafv;
import com.google.android.gms.internal.p002firebaseauthapi.zzxy;
import com.google.firebase.auth.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "DefaultAuthUserInfoCreator")
public final class zzy extends AbstractSafeParcelable implements UserInfo {
    public static final Parcelable.Creator<zzy> CREATOR = new zzx();
    @SafeParcelable.Field(getter = "getUid", id = 1)
    @NonNull
    private String zza;
    @SafeParcelable.Field(getter = "getProviderId", id = 2)
    @NonNull
    private String zzb;
    @SafeParcelable.Field(getter = "getDisplayName", id = 3)
    @Nullable
    private String zzc;
    @SafeParcelable.Field(getter = "getPhotoUrlString", id = 4)
    @Nullable
    private String zzd;
    @Nullable
    private Uri zze;
    @SafeParcelable.Field(getter = "getEmail", id = 5)
    @Nullable
    private String zzf;
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 6)
    @Nullable
    private String zzg;
    @SafeParcelable.Field(getter = "isEmailVerified", id = 7)
    private boolean zzh;
    @SafeParcelable.Field(getter = "getRawUserInfo", id = 8)
    @Nullable
    private String zzi;

    public zzy(zzafv zzafv) {
        Preconditions.checkNotNull(zzafv);
        this.zza = zzafv.zzd();
        this.zzb = Preconditions.checkNotEmpty(zzafv.zzf());
        this.zzc = zzafv.zzb();
        Uri zza2 = zzafv.zza();
        if (zza2 != null) {
            this.zzd = zza2.toString();
            this.zze = zza2;
        }
        this.zzf = zzafv.zzc();
        this.zzg = zzafv.zze();
        this.zzh = false;
        this.zzi = zzafv.zzg();
    }

    @Nullable
    public static zzy zza(@NonNull String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzy(jSONObject.optString("userId"), jSONObject.optString("providerId"), jSONObject.optString("email"), jSONObject.optString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER), jSONObject.optString("displayName"), jSONObject.optString("photoUrl"), jSONObject.optBoolean("isEmailVerified"), jSONObject.optString("rawUserInfo"));
        } catch (JSONException e3) {
            Log.d("DefaultAuthUserInfo", "Failed to unpack UserInfo from JSON");
            throw new zzxy(e3);
        }
    }

    @Nullable
    public final String getDisplayName() {
        return this.zzc;
    }

    @Nullable
    public final String getEmail() {
        return this.zzf;
    }

    @Nullable
    public final String getPhoneNumber() {
        return this.zzg;
    }

    @Nullable
    public final Uri getPhotoUrl() {
        if (!TextUtils.isEmpty(this.zzd) && this.zze == null) {
            this.zze = Uri.parse(this.zzd);
        }
        return this.zze;
    }

    @NonNull
    public final String getProviderId() {
        return this.zzb;
    }

    @NonNull
    public final String getUid() {
        return this.zza;
    }

    public final boolean isEmailVerified() {
        return this.zzh;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUid(), false);
        SafeParcelWriter.writeString(parcel, 2, getProviderId(), false);
        SafeParcelWriter.writeString(parcel, 3, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, getEmail(), false);
        SafeParcelWriter.writeString(parcel, 6, getPhoneNumber(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, isEmailVerified());
        SafeParcelWriter.writeString(parcel, 8, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String zzb() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("userId", this.zza);
            jSONObject.putOpt("providerId", this.zzb);
            jSONObject.putOpt("displayName", this.zzc);
            jSONObject.putOpt("photoUrl", this.zzd);
            jSONObject.putOpt("email", this.zzf);
            jSONObject.putOpt(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, this.zzg);
            jSONObject.putOpt("isEmailVerified", Boolean.valueOf(this.zzh));
            jSONObject.putOpt("rawUserInfo", this.zzi);
            return jSONObject.toString();
        } catch (JSONException e3) {
            Log.d("DefaultAuthUserInfo", "Failed to jsonify this object");
            throw new zzxy(e3);
        }
    }

    public zzy(zzaff zzaff, String str) {
        Preconditions.checkNotNull(zzaff);
        Preconditions.checkNotEmpty(str);
        this.zza = Preconditions.checkNotEmpty(zzaff.zzi());
        this.zzb = str;
        this.zzf = zzaff.zzh();
        this.zzc = zzaff.zzg();
        Uri zzc2 = zzaff.zzc();
        if (zzc2 != null) {
            this.zzd = zzc2.toString();
            this.zze = zzc2;
        }
        this.zzh = zzaff.zzm();
        this.zzi = null;
        this.zzg = zzaff.zzj();
    }

    @Nullable
    public final String zza() {
        return this.zzi;
    }

    @SafeParcelable.Constructor
    public zzy(@SafeParcelable.Param(id = 1) @NonNull String str, @SafeParcelable.Param(id = 2) @NonNull String str2, @SafeParcelable.Param(id = 5) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable String str4, @SafeParcelable.Param(id = 3) @Nullable String str5, @SafeParcelable.Param(id = 6) @Nullable String str6, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) @Nullable String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzf = str3;
        this.zzg = str4;
        this.zzc = str5;
        this.zzd = str6;
        if (!TextUtils.isEmpty(str6)) {
            this.zze = Uri.parse(this.zzd);
        }
        this.zzh = z2;
        this.zzi = str7;
    }
}
