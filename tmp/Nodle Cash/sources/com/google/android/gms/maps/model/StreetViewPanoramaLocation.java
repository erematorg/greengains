package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "StreetViewPanoramaLocationCreator")
@SafeParcelable.Reserved({1})
public class StreetViewPanoramaLocation extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewPanoramaLocation> CREATOR = new zzz();
    @SafeParcelable.Field(id = 2)
    @NonNull
    public final StreetViewPanoramaLink[] links;
    @SafeParcelable.Field(id = 4)
    @NonNull
    public final String panoId;
    @SafeParcelable.Field(id = 3)
    @NonNull
    public final LatLng position;

    @SafeParcelable.Constructor
    public StreetViewPanoramaLocation(@SafeParcelable.Param(id = 2) @NonNull StreetViewPanoramaLink[] streetViewPanoramaLinkArr, @SafeParcelable.Param(id = 3) @NonNull LatLng latLng, @SafeParcelable.Param(id = 4) @NonNull String str) {
        this.links = streetViewPanoramaLinkArr;
        this.position = latLng;
        this.panoId = str;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLocation)) {
            return false;
        }
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) obj;
        return this.panoId.equals(streetViewPanoramaLocation.panoId) && this.position.equals(streetViewPanoramaLocation.position);
    }

    public int hashCode() {
        return Objects.hashCode(this.position, this.panoId);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("panoId", this.panoId).add("position", this.position.toString()).toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = this.links;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, streetViewPanoramaLinkArr, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.position, i3, false);
        SafeParcelWriter.writeString(parcel, 4, this.panoId, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
