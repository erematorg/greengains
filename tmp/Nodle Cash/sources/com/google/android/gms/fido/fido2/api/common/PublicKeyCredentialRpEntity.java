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

@SafeParcelable.Class(creator = "PublicKeyCredentialRpEntityCreator")
@SafeParcelable.Reserved({1})
public class PublicKeyCredentialRpEntity extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialRpEntity> CREATOR = new zzap();
    @SafeParcelable.Field(getter = "getId", id = 2)
    @NonNull
    private final String zza;
    @SafeParcelable.Field(getter = "getName", id = 3)
    @NonNull
    private final String zzb;
    @SafeParcelable.Field(getter = "getIcon", id = 4)
    @Nullable
    private final String zzc;

    @SafeParcelable.Constructor
    public PublicKeyCredentialRpEntity(@SafeParcelable.Param(id = 2) @NonNull String str, @SafeParcelable.Param(id = 3) @NonNull String str2, @SafeParcelable.Param(id = 4) @Nullable String str3) {
        this.zza = (String) Preconditions.checkNotNull(str);
        this.zzb = (String) Preconditions.checkNotNull(str2);
        this.zzc = str3;
    }

    public boolean equals(@NonNull Object obj) {
        if (!(obj instanceof PublicKeyCredentialRpEntity)) {
            return false;
        }
        PublicKeyCredentialRpEntity publicKeyCredentialRpEntity = (PublicKeyCredentialRpEntity) obj;
        return Objects.equal(this.zza, publicKeyCredentialRpEntity.zza) && Objects.equal(this.zzb, publicKeyCredentialRpEntity.zzb) && Objects.equal(this.zzc, publicKeyCredentialRpEntity.zzc);
    }

    @Nullable
    public String getIcon() {
        return this.zzc;
    }

    @NonNull
    public String getId() {
        return this.zza;
    }

    @NonNull
    public String getName() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getIcon(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
