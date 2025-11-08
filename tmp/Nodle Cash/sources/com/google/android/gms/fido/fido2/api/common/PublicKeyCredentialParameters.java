package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;

@SafeParcelable.Class(creator = "PublicKeyCredentialParametersCreator")
@SafeParcelable.Reserved({1})
public class PublicKeyCredentialParameters extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialParameters> CREATOR = new zzan();
    @SafeParcelable.Field(getter = "getTypeAsString", id = 2, type = "java.lang.String")
    @NonNull
    private final PublicKeyCredentialType zza;
    @SafeParcelable.Field(getter = "getAlgorithmIdAsInteger", id = 3, type = "java.lang.Integer")
    @NonNull
    private final COSEAlgorithmIdentifier zzb;

    @SafeParcelable.Constructor
    public PublicKeyCredentialParameters(@SafeParcelable.Param(id = 2) @NonNull String str, @SafeParcelable.Param(id = 3) int i3) {
        Preconditions.checkNotNull(str);
        try {
            this.zza = PublicKeyCredentialType.fromString(str);
            Preconditions.checkNotNull(Integer.valueOf(i3));
            try {
                this.zzb = COSEAlgorithmIdentifier.fromCoseValue(i3);
            } catch (COSEAlgorithmIdentifier.UnsupportedAlgorithmIdentifierException e3) {
                throw new IllegalArgumentException(e3);
            }
        } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e4) {
            throw new IllegalArgumentException(e4);
        }
    }

    public boolean equals(@NonNull Object obj) {
        if (!(obj instanceof PublicKeyCredentialParameters)) {
            return false;
        }
        PublicKeyCredentialParameters publicKeyCredentialParameters = (PublicKeyCredentialParameters) obj;
        return this.zza.equals(publicKeyCredentialParameters.zza) && this.zzb.equals(publicKeyCredentialParameters.zzb);
    }

    @NonNull
    public COSEAlgorithmIdentifier getAlgorithm() {
        return this.zzb;
    }

    public int getAlgorithmIdAsInteger() {
        return this.zzb.toCoseValue();
    }

    @NonNull
    public PublicKeyCredentialType getType() {
        return this.zza;
    }

    @NonNull
    public String getTypeAsString() {
        return this.zza.toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getTypeAsString(), false);
        SafeParcelWriter.writeIntegerObject(parcel, 3, Integer.valueOf(getAlgorithmIdAsInteger()), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
