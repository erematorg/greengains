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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PolygonOptionsCreator")
@SafeParcelable.Reserved({1})
public final class PolygonOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzt();
    @SafeParcelable.Field(getter = "getPoints", id = 2)
    private final List zza;
    @SafeParcelable.Field(getter = "getHolesForParcel", id = 3, type = "java.util.List")
    private final List zzb;
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
    @SafeParcelable.Field(getter = "isGeodesic", id = 9)
    private boolean zzh;
    @SafeParcelable.Field(getter = "isClickable", id = 10)
    private boolean zzi;
    @SafeParcelable.Field(getter = "getStrokeJointType", id = 11)
    private int zzj;
    @SafeParcelable.Field(getter = "getStrokePattern", id = 12)
    @Nullable
    private List zzk;

    public PolygonOptions() {
        this.zzc = 10.0f;
        this.zzd = ViewCompat.MEASURED_STATE_MASK;
        this.zze = 0;
        this.zzf = 0.0f;
        this.zzg = true;
        this.zzh = false;
        this.zzi = false;
        this.zzj = 0;
        this.zzk = null;
        this.zza = new ArrayList();
        this.zzb = new ArrayList();
    }

    @NonNull
    public PolygonOptions add(@NonNull LatLng latLng) {
        Preconditions.checkNotNull(latLng, "point must not be null.");
        this.zza.add(latLng);
        return this;
    }

    @NonNull
    public PolygonOptions addAll(@NonNull Iterable<LatLng> iterable) {
        Preconditions.checkNotNull(iterable, "points must not be null.");
        for (LatLng add : iterable) {
            this.zza.add(add);
        }
        return this;
    }

    @NonNull
    public PolygonOptions addHole(@NonNull Iterable<LatLng> iterable) {
        Preconditions.checkNotNull(iterable, "points must not be null.");
        ArrayList arrayList = new ArrayList();
        for (LatLng add : iterable) {
            arrayList.add(add);
        }
        this.zzb.add(arrayList);
        return this;
    }

    @NonNull
    public PolygonOptions clickable(boolean z2) {
        this.zzi = z2;
        return this;
    }

    @NonNull
    public PolygonOptions fillColor(int i3) {
        this.zze = i3;
        return this;
    }

    @NonNull
    public PolygonOptions geodesic(boolean z2) {
        this.zzh = z2;
        return this;
    }

    public int getFillColor() {
        return this.zze;
    }

    @NonNull
    public List<List<LatLng>> getHoles() {
        return this.zzb;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return this.zza;
    }

    public int getStrokeColor() {
        return this.zzd;
    }

    public int getStrokeJointType() {
        return this.zzj;
    }

    @Nullable
    public List<PatternItem> getStrokePattern() {
        return this.zzk;
    }

    public float getStrokeWidth() {
        return this.zzc;
    }

    public float getZIndex() {
        return this.zzf;
    }

    public boolean isClickable() {
        return this.zzi;
    }

    public boolean isGeodesic() {
        return this.zzh;
    }

    public boolean isVisible() {
        return this.zzg;
    }

    @NonNull
    public PolygonOptions strokeColor(int i3) {
        this.zzd = i3;
        return this;
    }

    @NonNull
    public PolygonOptions strokeJointType(int i3) {
        this.zzj = i3;
        return this;
    }

    @NonNull
    public PolygonOptions strokePattern(@Nullable List<PatternItem> list) {
        this.zzk = list;
        return this;
    }

    @NonNull
    public PolygonOptions strokeWidth(float f2) {
        this.zzc = f2;
        return this;
    }

    @NonNull
    public PolygonOptions visible(boolean z2) {
        this.zzg = z2;
        return this;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeList(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 10, isClickable());
        SafeParcelWriter.writeInt(parcel, 11, getStrokeJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public PolygonOptions zIndex(float f2) {
        this.zzf = f2;
        return this;
    }

    @NonNull
    public PolygonOptions add(@NonNull LatLng... latLngArr) {
        Preconditions.checkNotNull(latLngArr, "points must not be null.");
        this.zza.addAll(Arrays.asList(latLngArr));
        return this;
    }

    @SafeParcelable.Constructor
    public PolygonOptions(@SafeParcelable.Param(id = 2) List list, @SafeParcelable.Param(id = 3) List list2, @SafeParcelable.Param(id = 4) float f2, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 6) int i4, @SafeParcelable.Param(id = 7) float f3, @SafeParcelable.Param(id = 8) boolean z2, @SafeParcelable.Param(id = 9) boolean z3, @SafeParcelable.Param(id = 10) boolean z4, @SafeParcelable.Param(id = 11) int i5, @SafeParcelable.Param(id = 12) @Nullable List list3) {
        this.zza = list;
        this.zzb = list2;
        this.zzc = f2;
        this.zzd = i3;
        this.zze = i4;
        this.zzf = f3;
        this.zzg = z2;
        this.zzh = z3;
        this.zzi = z4;
        this.zzj = i5;
        this.zzk = list3;
    }
}
