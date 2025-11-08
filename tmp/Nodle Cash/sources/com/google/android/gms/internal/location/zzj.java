package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzs;
import com.google.android.gms.location.zzt;

@SafeParcelable.Class(creator = "DeviceOrientationRequestUpdateDataCreator")
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestUpdateData.OPERATION_ADD", id = 1)
    final int zza;
    @SafeParcelable.Field(defaultValueUnchecked = "null", id = 2)
    @Nullable
    final zzh zzb;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getDeviceOrientationListenerBinder", id = 3, type = "android.os.IBinder")
    @Nullable
    final zzt zzc;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 4, type = "android.os.IBinder")
    @Nullable
    final zzr zzd;

    @SafeParcelable.Constructor
    public zzj(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) @Nullable zzh zzh, @SafeParcelable.Param(id = 3) IBinder iBinder, @SafeParcelable.Param(id = 4) @Nullable IBinder iBinder2) {
        this.zza = i3;
        this.zzb = zzh;
        zzr zzr = null;
        this.zzc = iBinder == null ? null : zzs.zzb(iBinder);
        if (iBinder2 != null) {
            IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzr = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzp(iBinder2);
        }
        this.zzd = zzr;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i3, false);
        zzt zzt = this.zzc;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 3, zzt == null ? null : zzt.asBinder(), false);
        zzr zzr = this.zzd;
        if (zzr != null) {
            iBinder = zzr.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 4, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
