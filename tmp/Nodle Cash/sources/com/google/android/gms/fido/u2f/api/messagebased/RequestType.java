package com.google.android.gms.fido.u2f.api.messagebased;

import androidx.annotation.NonNull;

@Deprecated
public enum RequestType {
    REGISTER("u2f_register_request"),
    SIGN("u2f_sign_request");
    
    private final String zzb;

    public static class UnsupportedRequestTypeException extends Exception {
        public UnsupportedRequestTypeException(@NonNull String str) {
            super("Unsupported request type ".concat(String.valueOf(str)));
        }
    }

    private RequestType(String str) {
        this.zzb = str;
    }

    @NonNull
    public static RequestType fromString(@NonNull String str) throws UnsupportedRequestTypeException {
        for (RequestType requestType : values()) {
            if (str.equals(requestType.zzb)) {
                return requestType;
            }
        }
        throw new UnsupportedRequestTypeException(str);
    }

    @NonNull
    public String toString() {
        return this.zzb;
    }
}
