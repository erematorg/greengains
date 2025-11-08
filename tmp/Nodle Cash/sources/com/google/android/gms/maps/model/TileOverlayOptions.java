package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.maps.zzaw;
import com.google.android.gms.internal.maps.zzax;

@SafeParcelable.Class(creator = "TileOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
public final class TileOverlayOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzak();
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getTileProviderDelegate", id = 2, type = "android.os.IBinder")
    @Nullable
    public zzax zza;
    @Nullable
    private TileProvider zzb;
    @SafeParcelable.Field(getter = "isVisible", id = 3)
    private boolean zzc = true;
    @SafeParcelable.Field(getter = "getZIndex", id = 4)
    private float zzd;
    @SafeParcelable.Field(defaultValue = "true", getter = "getFadeIn", id = 5)
    private boolean zze = true;
    @SafeParcelable.Field(getter = "getTransparency", id = 6)
    private float zzf = 0.0f;

    public TileOverlayOptions() {
    }

    @NonNull
    public TileOverlayOptions fadeIn(boolean z2) {
        this.zze = z2;
        return this;
    }

    public boolean getFadeIn() {
        return this.zze;
    }

    @Nullable
    public TileProvider getTileProvider() {
        return this.zzb;
    }

    public float getTransparency() {
        return this.zzf;
    }

    public float getZIndex() {
        return this.zzd;
    }

    public boolean isVisible() {
        return this.zzc;
    }

    @NonNull
    public TileOverlayOptions tileProvider(@NonNull TileProvider tileProvider) {
        this.zzb = (TileProvider) Preconditions.checkNotNull(tileProvider, "tileProvider must not be null.");
        this.zza = new zzaj(this, tileProvider);
        return this;
    }

    @NonNull
    public TileOverlayOptions transparency(float f2) {
        boolean z2 = false;
        if (f2 >= 0.0f && f2 <= 1.0f) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Transparency must be in the range [0..1]");
        this.zzf = f2;
        return this;
    }

    @NonNull
    public TileOverlayOptions visible(boolean z2) {
        this.zzc = z2;
        return this;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        zzax zzax = this.zza;
        SafeParcelWriter.writeIBinder(parcel, 2, zzax == null ? null : zzax.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public TileOverlayOptions zIndex(float f2) {
        this.zzd = f2;
        return this;
    }

    @SafeParcelable.Constructor
    public TileOverlayOptions(@SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) float f2, @SafeParcelable.Param(id = 5) boolean z3, @SafeParcelable.Param(id = 6) float f3) {
        zzai zzai;
        zzax zzc2 = zzaw.zzc(iBinder);
        this.zza = zzc2;
        if (zzc2 == null) {
            zzai = null;
        } else {
            zzai = new zzai(this);
        }
        this.zzb = zzai;
        this.zzc = z2;
        this.zzd = f2;
        this.zze = z3;
        this.zzf = f3;
    }
}
