package com.google.android.gms.fido.fido2.api.common;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import org.apache.xerces.impl.xs.SchemaSymbols;

public enum ResidentKeyRequirement implements Parcelable {
    RESIDENT_KEY_DISCOURAGED("discouraged"),
    RESIDENT_KEY_PREFERRED("preferred"),
    RESIDENT_KEY_REQUIRED(SchemaSymbols.ATTVAL_REQUIRED);
    
    @NonNull
    public static final Parcelable.Creator<ResidentKeyRequirement> CREATOR = null;
    @NonNull
    private final String zzb;

    public static class UnsupportedResidentKeyRequirementException extends Exception {
        public UnsupportedResidentKeyRequirementException(@NonNull String str) {
            super(a.l("Resident key requirement ", str, " not supported"));
        }
    }

    static {
        CREATOR = new zzas();
    }

    private ResidentKeyRequirement(String str) {
        this.zzb = str;
    }

    @NonNull
    public static ResidentKeyRequirement fromString(@NonNull String str) throws UnsupportedResidentKeyRequirementException {
        for (ResidentKeyRequirement residentKeyRequirement : values()) {
            if (str.equals(residentKeyRequirement.zzb)) {
                return residentKeyRequirement;
            }
        }
        throw new UnsupportedResidentKeyRequirementException(str);
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
