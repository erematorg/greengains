package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ContactInfoCreator")
@SafeParcelable.Reserved({1})
public final class zzl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzl> CREATOR = new zzy();
    @SafeParcelable.Field(id = 2)
    public zzp zza;
    @SafeParcelable.Field(id = 3)
    public String zzb;
    @SafeParcelable.Field(id = 4)
    public String zzc;
    @SafeParcelable.Field(id = 5)
    public zzq[] zzd;
    @SafeParcelable.Field(id = 6)
    public zzn[] zze;
    @SafeParcelable.Field(id = 7)
    public String[] zzf;
    @SafeParcelable.Field(id = 8)
    public zzi[] zzg;

    public zzl() {
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i3, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzd, i3, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.zze, i3, false);
        SafeParcelWriter.writeStringArray(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzg, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzl(@SafeParcelable.Param(id = 2) zzp zzp, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) zzq[] zzqArr, @SafeParcelable.Param(id = 6) zzn[] zznArr, @SafeParcelable.Param(id = 7) String[] strArr, @SafeParcelable.Param(id = 8) zzi[] zziArr) {
        this.zza = zzp;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzqArr;
        this.zze = zznArr;
        this.zzf = strArr;
        this.zzg = zziArr;
    }
}
