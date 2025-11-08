package com.google.android.gms.fido.fido2.api.common;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public enum AttestationConveyancePreference implements Parcelable {
    NONE("none"),
    INDIRECT(DevicePublicKeyStringDef.INDIRECT),
    DIRECT(DevicePublicKeyStringDef.DIRECT);
    
    @NonNull
    public static final Parcelable.Creator<AttestationConveyancePreference> CREATOR = null;
    @NonNull
    private final String zzb;

    public static class UnsupportedAttestationConveyancePreferenceException extends Exception {
        public UnsupportedAttestationConveyancePreferenceException(@NonNull String str) {
            super(a.l("Attestation conveyance preference ", str, " not supported"));
        }
    }

    static {
        CREATOR = new zzb();
    }

    private AttestationConveyancePreference(String str) {
        this.zzb = str;
    }

    @NonNull
    public static AttestationConveyancePreference fromString(@NonNull String str) throws UnsupportedAttestationConveyancePreferenceException {
        for (AttestationConveyancePreference attestationConveyancePreference : values()) {
            if (str.equals(attestationConveyancePreference.zzb)) {
                return attestationConveyancePreference;
            }
        }
        throw new UnsupportedAttestationConveyancePreferenceException(str);
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
