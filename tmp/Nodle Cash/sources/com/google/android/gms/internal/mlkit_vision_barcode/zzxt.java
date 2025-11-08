package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "DriverLicenseParcelCreator")
public final class zzxt extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzxt> CREATOR = new zzyi();
    @SafeParcelable.Field(getter = "getDocumentType", id = 1)
    @Nullable
    private final String zza;
    @SafeParcelable.Field(getter = "getFirstName", id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getMiddleName", id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getLastName", id = 4)
    @Nullable
    private final String zzd;
    @SafeParcelable.Field(getter = "getGender", id = 5)
    @Nullable
    private final String zze;
    @SafeParcelable.Field(getter = "getAddressStreet", id = 6)
    @Nullable
    private final String zzf;
    @SafeParcelable.Field(getter = "getAddressCity", id = 7)
    @Nullable
    private final String zzg;
    @SafeParcelable.Field(getter = "getAddressState", id = 8)
    @Nullable
    private final String zzh;
    @SafeParcelable.Field(getter = "getAddressZip", id = 9)
    @Nullable
    private final String zzi;
    @SafeParcelable.Field(getter = "getLicenseNumber", id = 10)
    @Nullable
    private final String zzj;
    @SafeParcelable.Field(getter = "getIssueDate", id = 11)
    @Nullable
    private final String zzk;
    @SafeParcelable.Field(getter = "getExpiryDate", id = 12)
    @Nullable
    private final String zzl;
    @SafeParcelable.Field(getter = "getBirthDate", id = 13)
    @Nullable
    private final String zzm;
    @SafeParcelable.Field(getter = "getIssuingCountry", id = 14)
    @Nullable
    private final String zzn;

    @SafeParcelable.Constructor
    public zzxt(@SafeParcelable.Param(id = 1) @Nullable String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable String str4, @SafeParcelable.Param(id = 5) @Nullable String str5, @SafeParcelable.Param(id = 6) @Nullable String str6, @SafeParcelable.Param(id = 7) @Nullable String str7, @SafeParcelable.Param(id = 8) @Nullable String str8, @SafeParcelable.Param(id = 9) @Nullable String str9, @SafeParcelable.Param(id = 10) @Nullable String str10, @SafeParcelable.Param(id = 11) @Nullable String str11, @SafeParcelable.Param(id = 12) @Nullable String str12, @SafeParcelable.Param(id = 13) @Nullable String str13, @SafeParcelable.Param(id = 14) @Nullable String str14) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = str6;
        this.zzg = str7;
        this.zzh = str8;
        this.zzi = str9;
        this.zzj = str10;
        this.zzk = str11;
        this.zzl = str12;
        this.zzm = str13;
        this.zzn = str14;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzh, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzi, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzj, false);
        SafeParcelWriter.writeString(parcel, 11, this.zzk, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzm, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String zza() {
        return this.zzg;
    }

    @Nullable
    public final String zzb() {
        return this.zzh;
    }

    @Nullable
    public final String zzc() {
        return this.zzf;
    }

    @Nullable
    public final String zzd() {
        return this.zzi;
    }

    @Nullable
    public final String zze() {
        return this.zzm;
    }

    @Nullable
    public final String zzf() {
        return this.zza;
    }

    @Nullable
    public final String zzg() {
        return this.zzl;
    }

    @Nullable
    public final String zzh() {
        return this.zzb;
    }

    @Nullable
    public final String zzi() {
        return this.zze;
    }

    @Nullable
    public final String zzj() {
        return this.zzk;
    }

    @Nullable
    public final String zzk() {
        return this.zzn;
    }

    @Nullable
    public final String zzl() {
        return this.zzd;
    }

    @Nullable
    public final String zzm() {
        return this.zzj;
    }

    @Nullable
    public final String zzn() {
        return this.zzc;
    }
}
