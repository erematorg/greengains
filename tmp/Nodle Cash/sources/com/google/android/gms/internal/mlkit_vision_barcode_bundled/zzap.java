package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ContactInfoParcelCreator")
public final class zzap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzap> CREATOR = new zzbi();
    @SafeParcelable.Field(getter = "getName", id = 1)
    @Nullable
    private final zzat zza;
    @SafeParcelable.Field(getter = "getOrganization", id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getPhones", id = 4)
    @Nullable
    private final zzau[] zzd;
    @SafeParcelable.Field(getter = "getEmails", id = 5)
    @Nullable
    private final zzar[] zze;
    @SafeParcelable.Field(getter = "getUrls", id = 6)
    @Nullable
    private final String[] zzf;
    @SafeParcelable.Field(getter = "getAddresses", id = 7)
    @Nullable
    private final zzam[] zzg;

    @SafeParcelable.Constructor
    public zzap(@SafeParcelable.Param(id = 1) @Nullable zzat zzat, @SafeParcelable.Param(id = 2) @Nullable String str, @SafeParcelable.Param(id = 3) @Nullable String str2, @SafeParcelable.Param(id = 4) @Nullable zzau[] zzauArr, @SafeParcelable.Param(id = 5) @Nullable zzar[] zzarArr, @SafeParcelable.Param(id = 6) @Nullable String[] strArr, @SafeParcelable.Param(id = 7) @Nullable zzam[] zzamArr) {
        this.zza = zzat;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzauArr;
        this.zze = zzarArr;
        this.zzf = strArr;
        this.zzg = zzamArr;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        zzat zzat = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zzat, i3, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.zzd, i3, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i3, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.zzg, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
