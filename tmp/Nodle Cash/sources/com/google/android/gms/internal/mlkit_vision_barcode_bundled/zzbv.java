package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "MultiScaleDetectionOptionsParcelCreator")
public final class zzbv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbv> CREATOR = new zzbw();
    @SafeParcelable.Field(getter = "getExtraScales", id = 1)
    private final float[] zza;

    @SafeParcelable.Constructor
    public zzbv(@SafeParcelable.Param(id = 1) float[] fArr) {
        this.zza = fArr;
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        float[] fArr = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloatArray(parcel, 1, fArr, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
