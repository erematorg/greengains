package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "BarhopperAdvancedConfigParcelCreator")
public final class zzbc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbc> CREATOR = new zzbd();
    @SafeParcelable.Field(getter = "getMultiScaleDecodingOptions", id = 1)
    private final zzbt zza;
    @SafeParcelable.Field(getter = "getMultiScaleDetectionOptions", id = 2)
    private final zzbv zzb;
    @SafeParcelable.Field(getter = "getQrFilterFinderPatternsOnAngle", id = 3)
    private final boolean zzc = true;
    @SafeParcelable.Field(getter = "getQrEnableFourthCornerApproximation", id = 4)
    private final boolean zzd;

    @SafeParcelable.Constructor
    public zzbc(@SafeParcelable.Param(id = 1) zzbt zzbt, @SafeParcelable.Param(id = 2) zzbv zzbv, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) boolean z3) {
        this.zza = zzbt;
        this.zzb = zzbv;
        this.zzd = z3;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i3, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i3, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzbt zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return this.zzd;
    }
}
