package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "CalendarEventParcelCreator")
public final class zzao extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzao> CREATOR = new zzbh();
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
    private final zzan zzf;
    @SafeParcelable.Field(getter = "getEnd", id = 7)
    @Nullable
    private final zzan zzg;

    @SafeParcelable.Constructor
    public zzao(@SafeParcelable.Param(id = 1) @Nullable String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable String str4, @SafeParcelable.Param(id = 5) @Nullable String str5, @SafeParcelable.Param(id = 6) @Nullable zzan zzan, @SafeParcelable.Param(id = 7) @Nullable zzan zzan2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = zzan;
        this.zzg = zzan2;
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
}
