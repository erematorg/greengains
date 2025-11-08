package com.google.crypto.tink.config.internal;

final class TinkFipsStatus {
    private TinkFipsStatus() {
    }

    public static boolean useOnlyFips() {
        return false;
    }
}
