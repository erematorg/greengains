package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "BarhopperAdvancedInitConfigParcelCreator")
public final class zzbe extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbe> CREATOR = new zzbf();
    @SafeParcelable.Field(getter = "getMlBinarizerOptions", id = 1)
    private final zzbr zza;

    @SafeParcelable.Constructor
    public zzbe(@SafeParcelable.Param(id = 1) zzbr zzbr) {
        this.zza = zzbr;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        zzbr zzbr = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zzbr, i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
