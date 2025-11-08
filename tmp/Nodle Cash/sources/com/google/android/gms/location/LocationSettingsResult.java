package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LocationSettingsResultCreator")
@SafeParcelable.Reserved({1000})
public final class LocationSettingsResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<LocationSettingsResult> CREATOR = new zzai();
    @SafeParcelable.Field(getter = "getStatus", id = 1)
    private final Status zza;
    @SafeParcelable.Field(getter = "getLocationSettingsStates", id = 2)
    @Nullable
    private final LocationSettingsStates zzb;

    @SafeParcelable.Constructor
    public LocationSettingsResult(@SafeParcelable.Param(id = 1) @NonNull Status status, @SafeParcelable.Param(id = 2) @Nullable LocationSettingsStates locationSettingsStates) {
        this.zza = status;
        this.zzb = locationSettingsStates;
    }

    @Nullable
    public LocationSettingsStates getLocationSettingsStates() {
        return this.zzb;
    }

    @NonNull
    public Status getStatus() {
        return this.zza;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i3, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getLocationSettingsStates(), i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
