package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "CircleOptionsCreator")
@SafeParcelable.Reserved({1})
public final class CircleOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();
    @SafeParcelable.Field(getter = "getCenter", id = 2)
    @Nullable
    private LatLng zza;
    @SafeParcelable.Field(getter = "getRadius", id = 3)
    private double zzb;
    @SafeParcelable.Field(getter = "getStrokeWidth", id = 4)
    private float zzc;
    @SafeParcelable.Field(getter = "getStrokeColor", id = 5)
    private int zzd;
    @SafeParcelable.Field(getter = "getFillColor", id = 6)
    private int zze;
    @SafeParcelable.Field(getter = "getZIndex", id = 7)
    private float zzf;
    @SafeParcelable.Field(getter = "isVisible", id = 8)
    private boolean zzg;
    @SafeParcelable.Field(getter = "isClickable", id = 9)
    private boolean zzh;
    @SafeParcelable.Field(getter = "getStrokePattern", id = 10)
    @Nullable
    private List zzi;

    public CircleOptions() {
        this.zza = null;
        this.zzb = 0.0d;
        this.zzc = 10.0f;
        this.zzd = ViewCompat.MEASURED_STATE_MASK;
        this.zze = 0;
        this.zzf = 0.0f;
        this.zzg = true;
        this.zzh = false;
        this.zzi = null;
    }

    @NonNull
    public CircleOptions center(@NonNull LatLng latLng) {
        Preconditions.checkNotNull(latLng, "center must not be null.");
        this.zza = latLng;
        return this;
    }

    @NonNull
    public CircleOptions clickable(boolean z2) {
        this.zzh = z2;
        return this;
    }

    @NonNull
    public CircleOptions fillColor(int i3) {
        this.zze = i3;
        return this;
    }

    @Nullable
    public LatLng getCenter() {
        return this.zza;
    }

    public int getFillColor() {
        return this.zze;
    }

    public double getRadius() {
        return this.zzb;
    }

    public int getStrokeColor() {
        return this.zzd;
    }

    @Nullable
    public List<PatternItem> getStrokePattern() {
        return this.zzi;
    }

    public float getStrokeWidth() {
        return this.zzc;
    }

    public float getZIndex() {
        return this.zzf;
    }

    public boolean isClickable() {
        return this.zzh;
    }

    public boolean isVisible() {
        return this.zzg;
    }

    @NonNull
    public CircleOptions radius(double d2) {
        this.zzb = d2;
        return this;
    }

    @NonNull
    public CircleOptions strokeColor(int i3) {
        this.zzd = i3;
        return this;
    }

    @NonNull
    public CircleOptions strokePattern(@Nullable List<PatternItem> list) {
        this.zzi = list;
        return this;
    }

    @NonNull
    public CircleOptions strokeWidth(float f2) {
        this.zzc = f2;
        return this;
    }

    @NonNull
    public CircleOptions visible(boolean z2) {
        this.zzg = z2;
        return this;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getCenter(), i3, false);
        SafeParcelWriter.writeDouble(parcel, 3, getRadius());
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isClickable());
        SafeParcelWriter.writeTypedList(parcel, 10, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public CircleOptions zIndex(float f2) {
        this.zzf = f2;
        return this;
    }

    @SafeParcelable.Constructor
    public CircleOptions(@SafeParcelable.Param(id = 2) LatLng latLng, @SafeParcelable.Param(id = 3) double d2, @SafeParcelable.Param(id = 4) float f2, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 6) int i4, @SafeParcelable.Param(id = 7) float f3, @SafeParcelable.Param(id = 8) boolean z2, @SafeParcelable.Param(id = 9) boolean z3, @SafeParcelable.Param(id = 10) @Nullable List list) {
        this.zza = latLng;
        this.zzb = d2;
        this.zzc = f2;
        this.zzd = i3;
        this.zze = i4;
        this.zzf = f3;
        this.zzg = z2;
        this.zzh = z3;
        this.zzi = list;
    }
}
