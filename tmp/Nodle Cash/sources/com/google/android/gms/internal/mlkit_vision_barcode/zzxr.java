package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "CalendarEventParcelCreator")
public final class zzxr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzxr> CREATOR = new zzyg();
    @SafeParcelable.Field(getter = "getSummary", id = 1)
    @Nullable
    private final String zza;
    @SafeParcelable.Field(getter = "getDescription", id = 2)
    @Nullable
    private final String zzb;
    @SafeParcelable.Field(getter = "getLocation", id = 3)
    @Nullable
    private final String zzc;
    @SafeParcelable.Field(getter = "getOrganizer", id = 4)
    @Nullable
    private final String zzd;
    @SafeParcelable.Field(getter = "getStatus", id = 5)
    @Nullable
    private final String zze;
    @SafeParcelable.Field(getter = "getStart", id = 6)
    @Nullable
    private final zzxq zzf;
    @SafeParcelable.Field(getter = "getEnd", id = 7)
    @Nullable
    private final zzxq zzg;

    @SafeParcelable.Constructor
    public zzxr(@SafeParcelable.Param(id = 1) @Nullable String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable String str4, @SafeParcelable.Param(id = 5) @Nullable String str5, @SafeParcelable.Param(id = 6) @Nullable zzxq zzxq, @SafeParcelable.Param(id = 7) @Nullable zzxq zzxq2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = zzxq;
        this.zzg = zzxq2;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final zzxq zza() {
        return this.zzg;
    }

    @Nullable
    public final zzxq zzb() {
        return this.zzf;
    }

    @Nullable
    public final String zzc() {
        return this.zzb;
    }

    @Nullable
    public final String zzd() {
        return this.zzc;
    }

    @Nullable
    public final String zze() {
        return this.zzd;
    }

    @Nullable
    public final String zzf() {
        return this.zze;
    }

    @Nullable
    public final String zzg() {
        return this.zza;
    }
}
