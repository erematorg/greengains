package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "StrokeStyleCreator")
@SafeParcelable.Reserved({1})
public final class StrokeStyle extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<StrokeStyle> CREATOR = new zzad();
    @SafeParcelable.Field(getter = "getWidth", id = 2)
    private final float zza;
    @SafeParcelable.Field(getter = "getColor", id = 3)
    private final int zzb;
    @SafeParcelable.Field(getter = "getToColor", id = 4)
    private final int zzc;
    @SafeParcelable.Field(getter = "isVisible", id = 5)
    private final boolean zzd;
    @SafeParcelable.Field(getter = "getStamp", id = 6)
    @Nullable
    private final StampStyle zze;

    public static final class Builder {
        private float zza;
        private int zzb;
        private int zzc;
        private boolean zzd;
        @Nullable
        private StampStyle zze;

        private Builder() {
            throw null;
        }

        @NonNull
        public StrokeStyle build() {
            return new StrokeStyle(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        @NonNull
        public Builder stamp(@NonNull StampStyle stampStyle) {
            this.zze = stampStyle;
            return this;
        }

        @NonNull
        public final Builder zza(int i3) {
            this.zzb = i3;
            this.zzc = i3;
            return this;
        }

        @NonNull
        public final Builder zzb(int i3, int i4) {
            this.zzb = i3;
            this.zzc = i4;
            return this;
        }

        @NonNull
        public final Builder zzc(boolean z2) {
            this.zzd = z2;
            return this;
        }

        @NonNull
        public final Builder zzd(float f2) {
            this.zza = f2;
            return this;
        }

        public /* synthetic */ Builder(zzac zzac) {
        }

        public Builder(@NonNull StrokeStyle strokeStyle) {
            this.zza = strokeStyle.zza();
            Pair zzb2 = strokeStyle.zzb();
            this.zzb = ((Integer) zzb2.first).intValue();
            this.zzc = ((Integer) zzb2.second).intValue();
            this.zzd = strokeStyle.isVisible();
            this.zze = strokeStyle.getStamp();
        }
    }

    @SafeParcelable.Constructor
    public StrokeStyle(@SafeParcelable.Param(id = 2) float f2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) int i4, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) @Nullable StampStyle stampStyle) {
        this.zza = f2;
        this.zzb = i3;
        this.zzc = i4;
        this.zzd = z2;
        this.zze = stampStyle;
    }

    @NonNull
    public static Builder colorBuilder(int i3) {
        Builder builder = new Builder((zzac) null);
        builder.zza(i3);
        return builder;
    }

    @NonNull
    public static Builder gradientBuilder(int i3, int i4) {
        Builder builder = new Builder((zzac) null);
        builder.zzb(i3, i4);
        return builder;
    }

    @NonNull
    public static Builder transparentColorBuilder() {
        Builder builder = new Builder((zzac) null);
        builder.zza(0);
        return builder;
    }

    @Nullable
    public StampStyle getStamp() {
        return this.zze;
    }

    public boolean isVisible() {
        return this.zzd;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zza);
        SafeParcelWriter.writeInt(parcel, 3, this.zzb);
        SafeParcelWriter.writeInt(parcel, 4, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 5, isVisible());
        SafeParcelWriter.writeParcelable(parcel, 6, getStamp(), i3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final float zza() {
        return this.zza;
    }

    @NonNull
    public final Pair zzb() {
        return new Pair(Integer.valueOf(this.zzb), Integer.valueOf(this.zzc));
    }
}
