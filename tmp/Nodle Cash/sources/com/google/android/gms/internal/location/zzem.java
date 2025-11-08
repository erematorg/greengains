package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "RemoveGeofencingRequestCreator")
@SafeParcelable.Reserved({1000})
public final class zzem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzem> CREATOR = new zzen();
    @SafeParcelable.Field(getter = "getGeofenceIds", id = 1)
    private final List zza;
    @SafeParcelable.Field(getter = "getPendingIntent", id = 2)
    @Nullable
    private final PendingIntent zzb;
    @SafeParcelable.Field(defaultValue = "", getter = "getTag", id = 3)
    private final String zzc;

    @SafeParcelable.Constructor
    public zzem(@SafeParcelable.Param(id = 1) @Nullable List list, @SafeParcelable.Param(id = 2) @Nullable PendingIntent pendingIntent, @SafeParcelable.Param(id = 3) String str) {
        this.zza = list == null ? zzex.zzi() : zzex.zzj(list);
        this.zzb = pendingIntent;
        this.zzc = str;
    }

    public static zzem zza(List list) {
        Preconditions.checkNotNull(list, "geofence can't be null.");
        Preconditions.checkArgument(!list.isEmpty(), "Geofences must contains at least one id.");
        return new zzem(list, (PendingIntent) null, "");
    }

    public static zzem zzb(PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent can not be null.");
        return new zzem((List) null, pendingIntent, "");
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        List list = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, list, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i3, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
