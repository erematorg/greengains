package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AdditionalUserInfo;
import java.util.Map;

@SafeParcelable.Class(creator = "DefaultAdditionalUserInfoCreator")
public final class zzu implements AdditionalUserInfo {
    public static final Parcelable.Creator<zzu> CREATOR = new zzt();
    @SafeParcelable.Field(getter = "getProviderId", id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getRawUserInfo", id = 2)
    private final String zzb;
    private Map<String, Object> zzc;
    @SafeParcelable.Field(getter = "isNewUser", id = 3)
    private boolean zzd;

    public zzu(boolean z2) {
        this.zzd = z2;
        this.zzb = null;
        this.zza = null;
        this.zzc = null;
    }

    public final int describeContents() {
        return 0;
    }

    @Nullable
    public final Map<String, Object> getProfile() {
        return this.zzc;
    }

    @Nullable
    public final String getProviderId() {
        return this.zza;
    }

    @Nullable
    public final String getUsername() {
        if ("github.com".equals(this.zza)) {
            return (String) this.zzc.get(FirebaseAnalytics.Event.LOGIN);
        }
        if ("twitter.com".equals(this.zza)) {
            return (String) this.zzc.get(FirebaseAnalytics.Param.SCREEN_NAME);
        }
        return null;
    }

    public final boolean isNewUser() {
        return this.zzd;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProviderId(), false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 3, isNewUser());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzu(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) boolean z2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzbe.zzb(str2);
        this.zzd = z2;
    }
}
