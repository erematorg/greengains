package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "BarcodeCreator")
@SafeParcelable.Reserved({1})
public final class zzu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzu> CREATOR = new zzv();
    @SafeParcelable.Field(id = 2)
    public int zza;
    @SafeParcelable.Field(id = 3)
    public String zzb;
    @SafeParcelable.Field(id = 4)
    public String zzc;
    @SafeParcelable.Field(id = 5)
    public int zzd;
    @SafeParcelable.Field(id = 6)
    public Point[] zze;
    @SafeParcelable.Field(id = 7)
    public zzn zzf;
    @SafeParcelable.Field(id = 8)
    public zzq zzg;
    @SafeParcelable.Field(id = 9)
    public zzr zzh;
    @SafeParcelable.Field(id = 10)
    public zzt zzi;
    @SafeParcelable.Field(id = 11)
    public zzs zzj;
    @SafeParcelable.Field(id = 12)
    public zzo zzk;
    @SafeParcelable.Field(id = 13)
    public zzk zzl;
    @SafeParcelable.Field(id = 14)
    public zzl zzm;
    @SafeParcelable.Field(id = 15)
    public zzm zzn;
    @SafeParcelable.Field(id = 16)
    public byte[] zzo;
    @SafeParcelable.Field(id = 17)
    public boolean zzp;
    @SafeParcelable.Field(id = 18)
    public double zzq;

    public zzu() {
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzd);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.zze, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzf, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzh, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzj, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzl, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzm, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.zzn, i3, false);
        SafeParcelWriter.writeByteArray(parcel, 16, this.zzo, false);
        SafeParcelWriter.writeBoolean(parcel, 17, this.zzp);
        SafeParcelWriter.writeDouble(parcel, 18, this.zzq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzu(@SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) int i4, @SafeParcelable.Param(id = 6) Point[] pointArr, @SafeParcelable.Param(id = 7) zzn zzn2, @SafeParcelable.Param(id = 8) zzq zzq2, @SafeParcelable.Param(id = 9) zzr zzr, @SafeParcelable.Param(id = 10) zzt zzt, @SafeParcelable.Param(id = 11) zzs zzs, @SafeParcelable.Param(id = 12) zzo zzo2, @SafeParcelable.Param(id = 13) zzk zzk2, @SafeParcelable.Param(id = 14) zzl zzl2, @SafeParcelable.Param(id = 15) zzm zzm2, @SafeParcelable.Param(id = 16) byte[] bArr, @SafeParcelable.Param(id = 17) boolean z2, @SafeParcelable.Param(id = 18) double d2) {
        this.zza = i3;
        this.zzb = str;
        this.zzo = bArr;
        this.zzc = str2;
        this.zzd = i4;
        this.zze = pointArr;
        this.zzp = z2;
        this.zzq = d2;
        this.zzf = zzn2;
        this.zzg = zzq2;
        this.zzh = zzr;
        this.zzi = zzt;
        this.zzj = zzs;
        this.zzk = zzo2;
        this.zzl = zzk2;
        this.zzm = zzl2;
        this.zzn = zzm2;
    }
}
