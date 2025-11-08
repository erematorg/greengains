package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PointOfInterestCreator")
@SafeParcelable.Reserved({1})
public final class PointOfInterest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PointOfInterest> CREATOR = new zzs();
    @SafeParcelable.Field(id = 2)
    @NonNull
    public final LatLng latLng;
    @SafeParcelable.Field(id = 4)
    @NonNull
    public final String name;
    @SafeParcelable.Field(id = 3)
    @NonNull
    public final String placeId;

    @SafeParcelable.Constructor
    public PointOfInterest(@SafeParcelable.Param(id = 2) @NonNull LatLng latLng2, @SafeParcelable.Param(id = 3) @NonNull String str, @SafeParcelable.Param(id = 4) @NonNull String str2) {
        this.latLng = latLng2;
        this.placeId = str;
        this.name = str2;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        LatLng latLng2 = this.latLng;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, latLng2, i3, false);
        SafeParcelWriter.writeString(parcel, 3, this.placeId, false);
        SafeParcelWriter.writeString(parcel, 4, this.name, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
