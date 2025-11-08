package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "BarcodeParcelCreator")
public final class zzay extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzay> CREATOR = new zzaz();
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
    private final zzar zzg;
    @SafeParcelable.Field(getter = "getPhoneParcel", id = 8)
    @Nullable
    private final zzau zzh;
    @SafeParcelable.Field(getter = "getSmsParcel", id = 9)
    @Nullable
    private final zzav zzi;
    @SafeParcelable.Field(getter = "getWiFiParcel", id = 10)
    @Nullable
    private final zzax zzj;
    @SafeParcelable.Field(getter = "getUrlBookmarkParcel", id = 11)
    @Nullable
    private final zzaw zzk;
    @SafeParcelable.Field(getter = "getGeoPointParcel", id = 12)
    @Nullable
    private final zzas zzl;
    @SafeParcelable.Field(getter = "getCalendarEventParcel", id = 13)
    @Nullable
    private final zzao zzm;
    @SafeParcelable.Field(getter = "getContactInfoParcel", id = 14)
    @Nullable
    private final zzap zzn;
    @SafeParcelable.Field(getter = "getDriverLicenseParcel", id = 15)
    @Nullable
    private final zzaq zzo;

    @SafeParcelable.Constructor
    public zzay(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) @Nullable String str, @SafeParcelable.Param(id = 3) @Nullable String str2, @SafeParcelable.Param(id = 4) @Nullable byte[] bArr, @SafeParcelable.Param(id = 5) @Nullable Point[] pointArr, @SafeParcelable.Param(id = 6) int i4, @SafeParcelable.Param(id = 7) @Nullable zzar zzar, @SafeParcelable.Param(id = 8) @Nullable zzau zzau, @SafeParcelable.Param(id = 9) @Nullable zzav zzav, @SafeParcelable.Param(id = 10) @Nullable zzax zzax, @SafeParcelable.Param(id = 11) @Nullable zzaw zzaw, @SafeParcelable.Param(id = 12) @Nullable zzas zzas, @SafeParcelable.Param(id = 13) @Nullable zzao zzao, @SafeParcelable.Param(id = 14) @Nullable zzap zzap, @SafeParcelable.Param(id = 15) @Nullable zzaq zzaq) {
        this.zza = i3;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bArr;
        this.zze = pointArr;
        this.zzf = i4;
        this.zzg = zzar;
        this.zzh = zzau;
        this.zzi = zzav;
        this.zzj = zzax;
        this.zzk = zzaw;
        this.zzl = zzas;
        this.zzm = zzao;
        this.zzn = zzap;
        this.zzo = zzaq;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int i4 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i4);
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
}
