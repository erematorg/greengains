package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "VisibleRegionCreator")
@SafeParcelable.Reserved({1})
public final class VisibleRegion extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<VisibleRegion> CREATOR = new zzal();
    @SafeParcelable.Field(id = 4)
    @NonNull
    public final LatLng farLeft;
    @SafeParcelable.Field(id = 5)
    @NonNull
    public final LatLng farRight;
    @SafeParcelable.Field(id = 6)
    @NonNull
    public final LatLngBounds latLngBounds;
    @SafeParcelable.Field(id = 2)
    @NonNull
    public final LatLng nearLeft;
    @SafeParcelable.Field(id = 3)
    @NonNull
    public final LatLng nearRight;

    @SafeParcelable.Constructor
    public VisibleRegion(@SafeParcelable.Param(id = 2) @NonNull LatLng latLng, @SafeParcelable.Param(id = 3) @NonNull LatLng latLng2, @SafeParcelable.Param(id = 4) @NonNull LatLng latLng3, @SafeParcelable.Param(id = 5) @NonNull LatLng latLng4, @SafeParcelable.Param(id = 6) @NonNull LatLngBounds latLngBounds2) {
        this.nearLeft = latLng;
        this.nearRight = latLng2;
        this.farLeft = latLng3;
        this.farRight = latLng4;
        this.latLngBounds = latLngBounds2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) obj;
        return this.nearLeft.equals(visibleRegion.nearLeft) && this.nearRight.equals(visibleRegion.nearRight) && this.farLeft.equals(visibleRegion.farLeft) && this.farRight.equals(visibleRegion.farRight) && this.latLngBounds.equals(visibleRegion.latLngBounds);
    }

    public int hashCode() {
        return Objects.hashCode(this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("nearLeft", this.nearLeft).add("nearRight", this.nearRight).add("farLeft", this.farLeft).add("farRight", this.farRight).add("latLngBounds", this.latLngBounds).toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        LatLng latLng = this.nearLeft;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, latLng, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.nearRight, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.farLeft, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.farRight, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.latLngBounds, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
