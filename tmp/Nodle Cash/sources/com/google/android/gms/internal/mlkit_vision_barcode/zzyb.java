package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "BarcodeParcelCreator")
public final class zzyb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzyb> CREATOR = new zzyc();
    @SafeParcelable.Field(getter = "getFormat", id = 1)
    private final int zza;
    @SafeParcelable.Field(getter = "getDisplayValue", id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getRawValue", id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getRawBytes", id = 4)
    @Nullable
    private final byte[] zzd;
    @SafeParcelable.Field(getter = "getCornerPoints", id = 5)
    @Nullable
    private final Point[] zze;
    @SafeParcelable.Field(getter = "getValueType", id = 6)
    private final int zzf;
    @SafeParcelable.Field(getter = "getEmailParcel", id = 7)
    @Nullable
    private final zzxu zzg;
    @SafeParcelable.Field(getter = "getPhoneParcel", id = 8)
    @Nullable
    private final zzxx zzh;
    @SafeParcelable.Field(getter = "getSmsParcel", id = 9)
    @Nullable
    private final zzxy zzi;
    @SafeParcelable.Field(getter = "getWiFiParcel", id = 10)
    @Nullable
    private final zzya zzj;
    @SafeParcelable.Field(getter = "getUrlBookmarkParcel", id = 11)
    @Nullable
    private final zzxz zzk;
    @SafeParcelable.Field(getter = "getGeoPointParcel", id = 12)
    @Nullable
    private final zzxv zzl;
    @SafeParcelable.Field(getter = "getCalendarEventParcel", id = 13)
    @Nullable
    private final zzxr zzm;
    @SafeParcelable.Field(getter = "getContactInfoParcel", id = 14)
    @Nullable
    private final zzxs zzn;
    @SafeParcelable.Field(getter = "getDriverLicenseParcel", id = 15)
    @Nullable
    private final zzxt zzo;

    @SafeParcelable.Constructor
    public zzyb(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) @Nullable String str, @SafeParcelable.Param(id = 3) @Nullable String str2, @SafeParcelable.Param(id = 4) @Nullable byte[] bArr, @SafeParcelable.Param(id = 5) @Nullable Point[] pointArr, @SafeParcelable.Param(id = 6) int i4, @SafeParcelable.Param(id = 7) @Nullable zzxu zzxu, @SafeParcelable.Param(id = 8) @Nullable zzxx zzxx, @SafeParcelable.Param(id = 9) @Nullable zzxy zzxy, @SafeParcelable.Param(id = 10) @Nullable zzya zzya, @SafeParcelable.Param(id = 11) @Nullable zzxz zzxz, @SafeParcelable.Param(id = 12) @Nullable zzxv zzxv, @SafeParcelable.Param(id = 13) @Nullable zzxr zzxr, @SafeParcelable.Param(id = 14) @Nullable zzxs zzxs, @SafeParcelable.Param(id = 15) @Nullable zzxt zzxt) {
        this.zza = i3;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bArr;
        this.zze = pointArr;
        this.zzf = i4;
        this.zzg = zzxu;
        this.zzh = zzxx;
        this.zzi = zzxy;
        this.zzj = zzya;
        this.zzk = zzxz;
        this.zzl = zzxv;
        this.zzm = zzxr;
        this.zzn = zzxs;
        this.zzo = zzxt;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i3, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzh, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzi, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzj, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzm, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzn, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.zzo, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzf;
    }

    @Nullable
    public final zzxr zzc() {
        return this.zzm;
    }

    @Nullable
    public final zzxs zzd() {
        return this.zzn;
    }

    @Nullable
    public final zzxt zze() {
        return this.zzo;
    }

    @Nullable
    public final zzxu zzf() {
        return this.zzg;
    }

    @Nullable
    public final zzxv zzg() {
        return this.zzl;
    }

    @Nullable
    public final zzxx zzh() {
        return this.zzh;
    }

    @Nullable
    public final zzxy zzi() {
        return this.zzi;
    }

    @Nullable
    public final zzxz zzj() {
        return this.zzk;
    }

    @Nullable
    public final zzya zzk() {
        return this.zzj;
    }

    @Nullable
    public final String zzl() {
        return this.zzb;
    }

    @Nullable
    public final String zzm() {
        return this.zzc;
    }

    @Nullable
    public final byte[] zzn() {
        return this.zzd;
    }

    @Nullable
    public final Point[] zzo() {
        return this.zze;
    }
}
