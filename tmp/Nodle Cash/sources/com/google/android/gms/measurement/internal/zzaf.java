package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConditionalUserPropertyParcelCreator")
public final class zzaf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaf> CREATOR = new zzae();
    @SafeParcelable.Field(id = 2)
    @Nullable
    public String zza;
    @SafeParcelable.Field(id = 3)
    public String zzb;
    @SafeParcelable.Field(id = 4)
    public zzok zzc;
    @SafeParcelable.Field(id = 5)
    public long zzd;
    @SafeParcelable.Field(id = 6)
    public boolean zze;
    @SafeParcelable.Field(id = 7)
    @Nullable
    public String zzf;
    @SafeParcelable.Field(id = 8)
    @Nullable
    public zzbh zzg;
    @SafeParcelable.Field(id = 9)
    public long zzh;
    @SafeParcelable.Field(id = 10)
    @Nullable
    public zzbh zzi;
    @SafeParcelable.Field(id = 11)
    public long zzj;
    @SafeParcelable.Field(id = 12)
    @Nullable
    public zzbh zzk;

    public zzaf(zzaf zzaf) {
        Preconditions.checkNotNull(zzaf);
        this.zza = zzaf.zza;
        this.zzb = zzaf.zzb;
        this.zzc = zzaf.zzc;
        this.zzd = zzaf.zzd;
        this.zze = zzaf.zze;
        this.zzf = zzaf.zzf;
        this.zzg = zzaf.zzg;
        this.zzh = zzaf.zzh;
        this.zzi = zzaf.zzi;
        this.zzj = zzaf.zzj;
        this.zzk = zzaf.zzk;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i3, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i3, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i3, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzaf(@SafeParcelable.Param(id = 2) @Nullable String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) zzok zzok, @SafeParcelable.Param(id = 5) long j2, @SafeParcelable.Param(id = 6) boolean z2, @SafeParcelable.Param(id = 7) @Nullable String str3, @SafeParcelable.Param(id = 8) @Nullable zzbh zzbh, @SafeParcelable.Param(id = 9) long j3, @SafeParcelable.Param(id = 10) @Nullable zzbh zzbh2, @SafeParcelable.Param(id = 11) long j4, @SafeParcelable.Param(id = 12) @Nullable zzbh zzbh3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzok;
        this.zzd = j2;
        this.zze = z2;
        this.zzf = str3;
        this.zzg = zzbh;
        this.zzh = j3;
        this.zzi = zzbh2;
        this.zzj = j4;
        this.zzk = zzbh3;
    }
}
