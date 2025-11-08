package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "StyleSpanCreator")
@SafeParcelable.Reserved({1})
public final class StyleSpan extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StyleSpan> CREATOR = new zzae();
    @SafeParcelable.Field(getter = "getStyle", id = 2)
    private final StrokeStyle zza;
    @SafeParcelable.Field(getter = "getSegments", id = 3)
    private final double zzb;

    public StyleSpan(int i3) {
        this.zza = StrokeStyle.colorBuilder(i3).build();
        this.zzb = 1.0d;
    }

    public double getSegments() {
        return this.zzb;
    }

    @NonNull
    public StrokeStyle getStyle() {
        return this.zza;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStyle(), i3, false);
        SafeParcelWriter.writeDouble(parcel, 3, getSegments());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public StyleSpan(int i3, double d2) {
        if (d2 > 0.0d) {
            this.zza = StrokeStyle.colorBuilder(i3).build();
            this.zzb = d2;
            return;
        }
        throw new IllegalArgumentException("A style must be applied to some segments on a polyline.");
    }

    public StyleSpan(@NonNull StrokeStyle strokeStyle) {
        this.zza = strokeStyle;
        this.zzb = 1.0d;
    }

    @SafeParcelable.Constructor
    public StyleSpan(@SafeParcelable.Param(id = 2) @NonNull StrokeStyle strokeStyle, @SafeParcelable.Param(id = 3) double d2) {
        if (d2 > 0.0d) {
            this.zza = strokeStyle;
            this.zzb = d2;
            return;
        }
        throw new IllegalArgumentException("A style must be applied to some segments on a polyline.");
    }
}
