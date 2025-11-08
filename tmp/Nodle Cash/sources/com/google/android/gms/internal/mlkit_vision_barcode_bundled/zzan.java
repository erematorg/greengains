package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "CalendarDateTimeParcelCreator")
public final class zzan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzan> CREATOR = new zzbg();
    @SafeParcelable.Field(getter = "getYear", id = 1)
    private final int zza;
    @SafeParcelable.Field(getter = "getMonth", id = 2)
    private final int zzb;
    @SafeParcelable.Field(getter = "getDay", id = 3)
    private final int zzc;
    @SafeParcelable.Field(getter = "getHours", id = 4)
    private final int zzd;
    @SafeParcelable.Field(getter = "getMinutes", id = 5)
    private final int zze;
    @SafeParcelable.Field(getter = "getSeconds", id = 6)
    private final int zzf;
    @SafeParcelable.Field(getter = "isUtc", id = 7)
    private final boolean zzg;
    @SafeParcelable.Field(getter = "getRawValue", id = 8)
    @Nullable
    private final String zzh;

    @SafeParcelable.Constructor
    public zzan(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) int i4, @SafeParcelable.Param(id = 3) int i5, @SafeParcelable.Param(id = 4) int i6, @SafeParcelable.Param(id = 5) int i7, @SafeParcelable.Param(id = 6) int i8, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) @Nullable String str) {
        this.zza = i3;
        this.zzb = i4;
        this.zzc = i5;
        this.zzd = i6;
        this.zze = i7;
        this.zzf = i8;
        this.zzg = z2;
        this.zzh = str;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int i4 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i4);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel, 5, this.zze);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzg);
        SafeParcelWriter.writeString(parcel, 8, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
