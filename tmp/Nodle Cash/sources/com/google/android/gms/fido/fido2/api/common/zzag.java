package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GoogleTunnelServerIdExtensionCreator")
public final class zzag extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzag> CREATOR = new zzah();
    @SafeParcelable.Field(getter = "getTunnelServerId", id = 1)
    @NonNull
    private final String zza;

    @SafeParcelable.Constructor
    public zzag(@SafeParcelable.Param(id = 1) @NonNull String str) {
        this.zza = (String) Preconditions.checkNotNull(str);
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof zzag)) {
            return false;
        }
        return this.zza.equals(((zzag) obj).zza);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public final void writeToParcel(Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
