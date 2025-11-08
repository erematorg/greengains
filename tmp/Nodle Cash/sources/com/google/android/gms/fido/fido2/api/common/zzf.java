package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;

@SafeParcelable.Class(creator = "AuthenticationExtensionsDevicePublicKeyOutputsCreator")
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = new zzg();
    @SafeParcelable.Field(getter = "getSignature", id = 1)
    @Nullable
    private final byte[] zza;
    @SafeParcelable.Field(getter = "getAuthenticatorOutput", id = 2)
    @Nullable
    private final byte[] zzb;

    @SafeParcelable.Constructor
    public zzf(@SafeParcelable.Param(id = 1) @Nullable byte[] bArr, @SafeParcelable.Param(id = 2) @Nullable byte[] bArr2) {
        this.zza = bArr;
        this.zzb = bArr2;
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof zzf)) {
            return false;
        }
        zzf zzf = (zzf) obj;
        return Arrays.equals(this.zza, zzf.zza) && Arrays.equals(this.zzb, zzf.zzb);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 1, this.zza, false);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
