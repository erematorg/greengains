package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "AppMetadataCreator")
@SafeParcelable.Reserved({1, 17, 20, 33})
public final class zzp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzp> CREATOR = new zzo();
    @SafeParcelable.Field(id = 2)
    @Nullable
    public final String zza;
    @SafeParcelable.Field(id = 32)
    public final int zzaa;
    @SafeParcelable.Field(id = 34)
    public final long zzab;
    @SafeParcelable.Field(id = 35)
    @Nullable
    public final String zzac;
    @SafeParcelable.Field(defaultValue = "", id = 36)
    public final String zzad;
    @SafeParcelable.Field(id = 13)
    @Deprecated
    private final long zzae;
    @SafeParcelable.Field(id = 24)
    @Nullable
    private final String zzaf;
    @SafeParcelable.Field(id = 3)
    @Nullable
    public final String zzb;
    @SafeParcelable.Field(id = 4)
    @Nullable
    public final String zzc;
    @SafeParcelable.Field(id = 5)
    @Nullable
    public final String zzd;
    @SafeParcelable.Field(id = 6)
    public final long zze;
    @SafeParcelable.Field(id = 7)
    public final long zzf;
    @SafeParcelable.Field(id = 8)
    @Nullable
    public final String zzg;
    @SafeParcelable.Field(defaultValue = "true", id = 9)
    public final boolean zzh;
    @SafeParcelable.Field(id = 10)
    public final boolean zzi;
    @SafeParcelable.Field(defaultValueUnchecked = "Integer.MIN_VALUE", id = 11)
    public final long zzj;
    @SafeParcelable.Field(id = 12)
    @Nullable
    public final String zzk;
    @SafeParcelable.Field(id = 14)
    public final long zzl;
    @SafeParcelable.Field(id = 15)
    public final int zzm;
    @SafeParcelable.Field(defaultValue = "true", id = 16)
    public final boolean zzn;
    @SafeParcelable.Field(id = 18)
    public final boolean zzo;
    @SafeParcelable.Field(id = 19)
    @Nullable
    public final String zzp;
    @SafeParcelable.Field(id = 21)
    @Nullable
    public final Boolean zzq;
    @SafeParcelable.Field(id = 22)
    public final long zzr;
    @SafeParcelable.Field(id = 23)
    @Nullable
    public final List<String> zzs;
    @SafeParcelable.Field(defaultValue = "", id = 25)
    public final String zzt;
    @SafeParcelable.Field(defaultValue = "", id = 26)
    public final String zzu;
    @SafeParcelable.Field(id = 27)
    @Nullable
    public final String zzv;
    @SafeParcelable.Field(defaultValue = "false", id = 28)
    public final boolean zzw;
    @SafeParcelable.Field(id = 29)
    public final long zzx;
    @SafeParcelable.Field(defaultValue = "100", id = 30)
    public final int zzy;
    @SafeParcelable.Field(defaultValue = "", id = 31)
    public final String zzz;

    public zzp(@Nullable String str, @Nullable String str2, @Nullable String str3, long j2, @Nullable String str4, long j3, long j4, @Nullable String str5, boolean z2, boolean z3, @Nullable String str6, long j5, long j6, int i3, boolean z4, boolean z5, @Nullable String str7, @Nullable Boolean bool, long j7, @Nullable List<String> list, @Nullable String str8, String str9, String str10, @Nullable String str11, boolean z6, long j8, int i4, String str12, int i5, long j9, @Nullable String str13, String str14) {
        Preconditions.checkNotEmpty(str);
        this.zza = str;
        this.zzb = TextUtils.isEmpty(str2) ? null : str2;
        this.zzc = str3;
        this.zzj = j2;
        this.zzd = str4;
        this.zze = j3;
        this.zzf = j4;
        this.zzg = str5;
        this.zzh = z2;
        this.zzi = z3;
        this.zzk = str6;
        this.zzae = j5;
        this.zzl = j6;
        this.zzm = i3;
        this.zzn = z4;
        this.zzo = z5;
        this.zzp = str7;
        this.zzq = bool;
        this.zzr = j7;
        this.zzs = list;
        this.zzaf = null;
        this.zzt = str9;
        this.zzu = str10;
        this.zzv = str11;
        this.zzw = z6;
        this.zzx = j8;
        this.zzy = i4;
        this.zzz = str12;
        this.zzaa = i5;
        this.zzab = j9;
        this.zzac = str13;
        this.zzad = str14;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zze);
        SafeParcelWriter.writeLong(parcel, 7, this.zzf);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzh);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeString(parcel, 12, this.zzk, false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzae);
        SafeParcelWriter.writeLong(parcel, 14, this.zzl);
        SafeParcelWriter.writeInt(parcel, 15, this.zzm);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzn);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzo);
        SafeParcelWriter.writeString(parcel, 19, this.zzp, false);
        SafeParcelWriter.writeBooleanObject(parcel, 21, this.zzq, false);
        SafeParcelWriter.writeLong(parcel, 22, this.zzr);
        SafeParcelWriter.writeStringList(parcel, 23, this.zzs, false);
        SafeParcelWriter.writeString(parcel, 24, this.zzaf, false);
        SafeParcelWriter.writeString(parcel, 25, this.zzt, false);
        SafeParcelWriter.writeString(parcel, 26, this.zzu, false);
        SafeParcelWriter.writeString(parcel, 27, this.zzv, false);
        SafeParcelWriter.writeBoolean(parcel, 28, this.zzw);
        SafeParcelWriter.writeLong(parcel, 29, this.zzx);
        SafeParcelWriter.writeInt(parcel, 30, this.zzy);
        SafeParcelWriter.writeString(parcel, 31, this.zzz, false);
        SafeParcelWriter.writeInt(parcel, 32, this.zzaa);
        SafeParcelWriter.writeLong(parcel, 34, this.zzab);
        SafeParcelWriter.writeString(parcel, 35, this.zzac, false);
        SafeParcelWriter.writeString(parcel, 36, this.zzad, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzp(@SafeParcelable.Param(id = 2) @Nullable String str, @SafeParcelable.Param(id = 3) @Nullable String str2, @SafeParcelable.Param(id = 4) @Nullable String str3, @SafeParcelable.Param(id = 5) @Nullable String str4, @SafeParcelable.Param(id = 6) long j2, @SafeParcelable.Param(id = 7) long j3, @SafeParcelable.Param(id = 8) @Nullable String str5, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) boolean z3, @SafeParcelable.Param(id = 11) long j4, @SafeParcelable.Param(id = 12) @Nullable String str6, @SafeParcelable.Param(id = 13) long j5, @SafeParcelable.Param(id = 14) long j6, @SafeParcelable.Param(id = 15) int i3, @SafeParcelable.Param(id = 16) boolean z4, @SafeParcelable.Param(id = 18) boolean z5, @SafeParcelable.Param(id = 19) @Nullable String str7, @SafeParcelable.Param(id = 21) @Nullable Boolean bool, @SafeParcelable.Param(id = 22) long j7, @SafeParcelable.Param(id = 23) @Nullable List<String> list, @SafeParcelable.Param(id = 24) @Nullable String str8, @SafeParcelable.Param(id = 25) String str9, @SafeParcelable.Param(id = 26) String str10, @SafeParcelable.Param(id = 27) String str11, @SafeParcelable.Param(id = 28) boolean z6, @SafeParcelable.Param(id = 29) long j8, @SafeParcelable.Param(id = 30) int i4, @SafeParcelable.Param(id = 31) String str12, @SafeParcelable.Param(id = 32) int i5, @SafeParcelable.Param(id = 34) long j9, @SafeParcelable.Param(id = 35) @Nullable String str13, @SafeParcelable.Param(id = 36) String str14) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzj = j4;
        this.zzd = str4;
        this.zze = j2;
        this.zzf = j3;
        this.zzg = str5;
        this.zzh = z2;
        this.zzi = z3;
        this.zzk = str6;
        this.zzae = j5;
        this.zzl = j6;
        this.zzm = i3;
        this.zzn = z4;
        this.zzo = z5;
        this.zzp = str7;
        this.zzq = bool;
        this.zzr = j7;
        this.zzs = list;
        this.zzaf = str8;
        this.zzt = str9;
        this.zzu = str10;
        this.zzv = str11;
        this.zzw = z6;
        this.zzx = j8;
        this.zzy = i4;
        this.zzz = str12;
        this.zzaa = i5;
        this.zzab = j9;
        this.zzac = str13;
        this.zzad = str14;
    }
}
