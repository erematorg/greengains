package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "BarcodeScannerOptionsParcelCreator")
public final class zzyd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzyd> CREATOR = new zzye();
    @SafeParcelable.Field(getter = "getSupportedFormats", id = 1)
    private final int zza;
    @SafeParcelable.Field(getter = "isAllPotentialBarcodesEnabled", id = 2)
    private final boolean zzb;

    @SafeParcelable.Constructor
    public zzyd(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) boolean z2) {
        this.zza = i3;
        this.zzb = z2;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int i4 = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i4);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
