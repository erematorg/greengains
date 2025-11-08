package com.google.android.gms.common;

public final class GooglePlayServicesNotAvailableException extends Exception {
    public final int errorCode;

    public GooglePlayServicesNotAvailableException(int i3) {
        this.errorCode = i3;
    }
}
