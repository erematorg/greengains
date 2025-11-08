package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.GoogleMapOptions;

@SafeParcelable.Class(creator = "LatLngBoundsCreator")
@SafeParcelable.Reserved({1})
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new zzm();
    @SafeParcelable.Field(id = 3)
    @NonNull
    public final LatLng northeast;
    @SafeParcelable.Field(id = 2)
    @NonNull
    public final LatLng southwest;

    public static final class Builder {
        private double zza = Double.POSITIVE_INFINITY;
        private double zzb = Double.NEGATIVE_INFINITY;
        private double zzc = Double.NaN;
        private double zzd = Double.NaN;

        @NonNull
        public LatLngBounds build() {
            Preconditions.checkState(!Double.isNaN(this.zzc), "no included points");
            return new LatLngBounds(new LatLng(this.zza, this.zzc), new LatLng(this.zzb, this.zzd));
        }

        @NonNull
        public Builder include(@NonNull LatLng latLng) {
            Preconditions.checkNotNull(latLng, "point must not be null");
            this.zza = Math.min(this.zza, latLng.latitude);
            this.zzb = Math.max(this.zzb, latLng.latitude);
            double d2 = latLng.longitude;
            if (Double.isNaN(this.zzc)) {
                this.zzc = d2;
                this.zzd = d2;
            } else {
                double d3 = this.zzc;
                double d4 = this.zzd;
                if (d3 > d4 ? !(d3 <= d2 || d2 <= d4) : !(d3 <= d2 && d2 <= d4)) {
                    Parcelable.Creator<LatLngBounds> creator = LatLngBounds.CREATOR;
                    if (((d3 - d2) + 360.0d) % 360.0d < ((d2 - d4) + 360.0d) % 360.0d) {
                        this.zzc = d2;
                    } else {
                        this.zzd = d2;
                    }
                }
            }
            return this;
        }
    }

    @SafeParcelable.Constructor
    public LatLngBounds(@SafeParcelable.Param(id = 2) @NonNull LatLng latLng, @SafeParcelable.Param(id = 3) @NonNull LatLng latLng2) {
        Preconditions.checkNotNull(latLng, "southwest must not be null.");
        Preconditions.checkNotNull(latLng2, "northeast must not be null.");
        double d2 = latLng2.latitude;
        double d3 = latLng.latitude;
        Preconditions.checkArgument(d2 >= d3, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(d3), Double.valueOf(latLng2.latitude));
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @Nullable
    public static LatLngBounds createFromAttributes(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        return GoogleMapOptions.zzb(context, attributeSet);
    }

    private final boolean zza(double d2) {
        LatLng latLng = this.northeast;
        double d3 = this.southwest.longitude;
        double d4 = latLng.longitude;
        return d3 <= d4 ? d3 <= d2 && d2 <= d4 : d3 <= d2 || d2 <= d4;
    }

    public boolean contains(@NonNull LatLng latLng) {
        LatLng latLng2 = (LatLng) Preconditions.checkNotNull(latLng, "point must not be null.");
        double d2 = latLng2.latitude;
        return this.southwest.latitude <= d2 && d2 <= this.northeast.latitude && zza(latLng2.longitude);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    @NonNull
    public LatLng getCenter() {
        LatLng latLng = this.northeast;
        LatLng latLng2 = this.southwest;
        double d2 = latLng2.latitude + latLng.latitude;
        double d3 = latLng.longitude;
        double d4 = latLng2.longitude;
        if (d4 > d3) {
            d3 += 360.0d;
        }
        return new LatLng(d2 / 2.0d, (d3 + d4) / 2.0d);
    }

    public int hashCode() {
        return Objects.hashCode(this.southwest, this.northeast);
    }

    @NonNull
    public LatLngBounds including(@NonNull LatLng latLng) {
        LatLng latLng2 = (LatLng) Preconditions.checkNotNull(latLng, "point must not be null.");
        double min = Math.min(this.southwest.latitude, latLng2.latitude);
        double max = Math.max(this.northeast.latitude, latLng2.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = latLng2.longitude;
        if (!zza(d4)) {
            if (((d3 - d4) + 360.0d) % 360.0d < ((d4 - d2) + 360.0d) % 360.0d) {
                d3 = d4;
            } else {
                d2 = d4;
            }
        }
        return new LatLngBounds(new LatLng(min, d3), new LatLng(max, d2));
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        LatLng latLng = this.southwest;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, latLng, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.northeast, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
