package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

@SafeParcelable.Class(creator = "LocationSettingsStatesCreator")
@SafeParcelable.Reserved({1000})
public final class LocationSettingsStates extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<LocationSettingsStates> CREATOR = new zzaj();
    @SafeParcelable.Field(getter = "isGpsUsable", id = 1)
    private final boolean zza;
    @SafeParcelable.Field(getter = "isNetworkLocationUsable", id = 2)
    private final boolean zzb;
    @SafeParcelable.Field(getter = "isBleUsable", id = 3)
    private final boolean zzc;
    @SafeParcelable.Field(getter = "isGpsPresent", id = 4)
    private final boolean zzd;
    @SafeParcelable.Field(getter = "isNetworkLocationPresent", id = 5)
    private final boolean zze;
    @SafeParcelable.Field(getter = "isBlePresent", id = 6)
    private final boolean zzf;

    @SafeParcelable.Constructor
    public LocationSettingsStates(@SafeParcelable.Param(id = 1) boolean z2, @SafeParcelable.Param(id = 2) boolean z3, @SafeParcelable.Param(id = 3) boolean z4, @SafeParcelable.Param(id = 4) boolean z5, @SafeParcelable.Param(id = 5) boolean z6, @SafeParcelable.Param(id = 6) boolean z7) {
        this.zza = z2;
        this.zzb = z3;
        this.zzc = z4;
        this.zzd = z5;
        this.zze = z6;
        this.zzf = z7;
    }

    @Nullable
    public static LocationSettingsStates fromIntent(@NonNull Intent intent) {
        return (LocationSettingsStates) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
    }

    public boolean isBlePresent() {
        return this.zzf;
    }

    public boolean isBleUsable() {
        return this.zzc;
    }

    public boolean isGpsPresent() {
        return this.zzd;
    }

    public boolean isGpsUsable() {
        return this.zza;
    }

    public boolean isLocationPresent() {
        return this.zzd || this.zze;
    }

    public boolean isLocationUsable() {
        return this.zza || this.zzb;
    }

    public boolean isNetworkLocationPresent() {
        return this.zze;
    }

    public boolean isNetworkLocationUsable() {
        return this.zzb;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, isGpsUsable());
        SafeParcelWriter.writeBoolean(parcel, 2, isNetworkLocationUsable());
        SafeParcelWriter.writeBoolean(parcel, 3, isBleUsable());
        SafeParcelWriter.writeBoolean(parcel, 4, isGpsPresent());
        SafeParcelWriter.writeBoolean(parcel, 5, isNetworkLocationPresent());
        SafeParcelWriter.writeBoolean(parcel, 6, isBlePresent());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
