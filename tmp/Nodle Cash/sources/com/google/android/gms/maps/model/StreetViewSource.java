package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "StreetViewSourceCreator")
@SafeParcelable.Reserved({1})
public final class StreetViewSource extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewSource> CREATOR = new zzab();
    @NonNull
    public static final StreetViewSource DEFAULT = new StreetViewSource(0);
    @NonNull
    public static final StreetViewSource OUTDOOR = new StreetViewSource(1);
    @SafeParcelable.Field(getter = "getType", id = 2)
    private final int zza;

    @SafeParcelable.Constructor
    public StreetViewSource(@SafeParcelable.Param(id = 2) int i3) {
        this.zza = i3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StreetViewSource) && this.zza == ((StreetViewSource) obj).zza;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza));
    }

    @NonNull
    public String toString() {
        int i3 = this.zza;
        return "StreetViewSource:".concat(i3 != 0 ? i3 != 1 ? C0118y.c(i3, "UNKNOWN(", ")") : "OUTDOOR" : "DEFAULT");
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int i4 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, i4);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
