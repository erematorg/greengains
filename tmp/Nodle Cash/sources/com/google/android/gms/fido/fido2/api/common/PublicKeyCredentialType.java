package com.google.android.gms.fido.fido2.api.common;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public enum PublicKeyCredentialType implements Parcelable {
    PUBLIC_KEY("public-key");
    
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialType> CREATOR = null;
    @NonNull
    private final String zzb;

    public static class UnsupportedPublicKeyCredTypeException extends Exception {
        public UnsupportedPublicKeyCredTypeException(@NonNull String str) {
            super(str);
        }
    }

    static {
        CREATOR = new zzaq();
    }

    private PublicKeyCredentialType(String str) {
        this.zzb = "public-key";
    }

    @NonNull
    public static PublicKeyCredentialType fromString(@NonNull String str) throws UnsupportedPublicKeyCredTypeException {
        for (PublicKeyCredentialType publicKeyCredentialType : values()) {
            if (str.equals(publicKeyCredentialType.zzb)) {
                return publicKeyCredentialType;
            }
        }
        throw new UnsupportedPublicKeyCredTypeException(a.l("PublicKeyCredentialType ", str, " not supported"));
    }

    public int describeContents() {
        return 0;
    }

    @NonNull
    public String toString() {
        return this.zzb;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        parcel.writeString(this.zzb);
    }
}
