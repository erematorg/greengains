package com.google.mlkit.vision.common.internal;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "VisionImageMetadataParcelCreator")
public class VisionImageMetadataParcel extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<VisionImageMetadataParcel> CREATOR = new zzg();
    @SafeParcelable.Field(id = 2)
    @KeepForSdk
    public final int height;
    @SafeParcelable.Field(id = 5)
    @KeepForSdk
    public final int rotation;
    @SafeParcelable.Field(id = 4)
    @KeepForSdk
    public final long timestampMillis;
    @SafeParcelable.Field(id = 1)
    @KeepForSdk
    public final int width;
    @SafeParcelable.Field(id = 3)
    public final int zza;

    @SafeParcelable.Constructor
    public VisionImageMetadataParcel(@SafeParcelable.Param(id = 1) int i3, @SafeParcelable.Param(id = 2) int i4, @SafeParcelable.Param(id = 3) int i5, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) int i6) {
        this.width = i3;
        this.height = i4;
        this.zza = i5;
        this.timestampMillis = j2;
        this.rotation = i6;
    }

    @KeepForSdk
    @Nullable
    public Matrix getUprightRotationMatrix() {
        return ImageUtils.getInstance().getUprightRotationMatrix(this.width, this.height, this.rotation);
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.width);
        SafeParcelWriter.writeInt(parcel, 2, this.height);
        SafeParcelWriter.writeInt(parcel, 3, this.zza);
        SafeParcelWriter.writeLong(parcel, 4, this.timestampMillis);
        SafeParcelWriter.writeInt(parcel, 5, this.rotation);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
