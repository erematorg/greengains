package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.C0118y;
import java.util.Locale;

public enum ErrorCode implements Parcelable {
    NOT_SUPPORTED_ERR(9),
    INVALID_STATE_ERR(11),
    SECURITY_ERR(18),
    NETWORK_ERR(19),
    ABORT_ERR(20),
    TIMEOUT_ERR(23),
    ENCODING_ERR(27),
    UNKNOWN_ERR(28),
    CONSTRAINT_ERR(29),
    DATA_ERR(30),
    NOT_ALLOWED_ERR(35),
    ATTESTATION_NOT_PRIVATE_ERR(36);
    
    @NonNull
    public static final Parcelable.Creator<ErrorCode> CREATOR = null;
    private final int zzb;

    public static class UnsupportedErrorCodeException extends Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnsupportedErrorCodeException(int i3) {
            super(C0118y.c(i3, "Error code ", " is not supported"));
            Locale locale = Locale.US;
        }
    }

    static {
        CREATOR = new zzw();
    }

    private ErrorCode(int i3) {
        this.zzb = i3;
    }

    @NonNull
    public static ErrorCode toErrorCode(int i3) throws UnsupportedErrorCodeException {
        for (ErrorCode errorCode : values()) {
            if (i3 == errorCode.zzb) {
                return errorCode;
            }
        }
        throw new UnsupportedErrorCodeException(i3);
    }

    public int describeContents() {
        return 0;
    }

    public int getCode() {
        return this.zzb;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        parcel.writeInt(this.zzb);
    }
}
