package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ContactInfoParcelCreator")
public final class zzxs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzxs> CREATOR = new zzyh();
    @SafeParcelable.Field(getter = "getName", id = 1)
    @Nullable
    private final zzxw zza;
    @SafeParcelable.Field(getter = "getOrganization", id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getPhones", id = 4)
    @Nullable
    private final zzxx[] zzd;
    @SafeParcelable.Field(getter = "getEmails", id = 5)
    @Nullable
    private final zzxu[] zze;
    @SafeParcelable.Field(getter = "getUrls", id = 6)
    @Nullable
    private final String[] zzf;
    @SafeParcelable.Field(getter = "getAddresses", id = 7)
    @Nullable
    private final zzxp[] zzg;

    @SafeParcelable.Constructor
    public zzxs(@SafeParcelable.Param(id = 1) @Nullable zzxw zzxw, @SafeParcelable.Param(id = 2) @Nullable String str, @SafeParcelable.Param(id = 3) @Nullable String str2, @SafeParcelable.Param(id = 4) @Nullable zzxx[] zzxxArr, @SafeParcelable.Param(id = 5) @Nullable zzxu[] zzxuArr, @SafeParcelable.Param(id = 6) @Nullable String[] strArr, @SafeParcelable.Param(id = 7) @Nullable zzxp[] zzxpArr) {
        this.zza = zzxw;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzxxArr;
        this.zze = zzxuArr;
        this.zzf = strArr;
        this.zzg = zzxpArr;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i3, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.zzd, i3, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i3, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.zzg, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzxw zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final String zzc() {
        return this.zzc;
    }

    @Nullable
    public final zzxp[] zzd() {
        return this.zzg;
    }

    @Nullable
    public final zzxu[] zze() {
        return this.zze;
    }

    @Nullable
    public final zzxx[] zzf() {
        return this.zzd;
    }

    @Nullable
    public final String[] zzg() {
        return this.zzf;
    }
}
