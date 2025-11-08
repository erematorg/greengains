package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzw;
import com.google.android.gms.location.zzy;
import com.google.android.gms.location.zzz;

@SafeParcelable.Class(creator = "LocationRequestUpdateDataCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
public final class zzei extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzei> CREATOR = new zzej();
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestUpdateData.OPERATION_ADD", getter = "getOperation", id = 1)
    private final int zza;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getLocationRequest", id = 2)
    @Nullable
    private final zzeg zzb;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getLocationListenerAsBinder", id = 3, type = "android.os.IBinder")
    @Nullable
    private final zzz zzc;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getLocationCallbackAsBinder", id = 5, type = "android.os.IBinder")
    @Nullable
    private final zzw zzd;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getPendingIntent", id = 4)
    @Nullable
    private final PendingIntent zze;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackAsBinder", id = 6, type = "android.os.IBinder")
    @Nullable
    private final zzr zzf;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getListenerId", id = 8)
    @Nullable
    private final String zzg;

    @SafeParcelable.Constructor
    public zzei(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) @Nullable zzeg zzeg, @SafeParcelable.Param(id = 3) @Nullable IBinder iBinder, @SafeParcelable.Param(id = 5) @Nullable IBinder iBinder2, @SafeParcelable.Param(id = 4) @Nullable PendingIntent pendingIntent, @SafeParcelable.Param(id = 6) @Nullable IBinder iBinder3, @SafeParcelable.Param(id = 8) @Nullable String str) {
        this.zza = i3;
        this.zzb = zzeg;
        zzr zzr = null;
        this.zzc = iBinder != null ? zzy.zzb(iBinder) : null;
        this.zze = pendingIntent;
        this.zzd = iBinder2 != null ? zzv.zzb(iBinder2) : null;
        if (iBinder3 != null) {
            IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            zzr = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzp(iBinder3);
        }
        this.zzf = zzr;
        this.zzg = str;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int i4 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i4);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i3, false);
        zzz zzz = this.zzc;
        IBinder iBinder = null;
        SafeParcelWriter.writeIBinder(parcel, 3, zzz == null ? null : zzz.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zze, i3, false);
        zzw zzw = this.zzd;
        SafeParcelWriter.writeIBinder(parcel, 5, zzw == null ? null : zzw.asBinder(), false);
        zzr zzr = this.zzf;
        if (zzr != null) {
            iBinder = zzr.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 6, iBinder, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
