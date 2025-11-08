package com.google.android.recaptcha.internal;

import java.io.IOException;

public final class zzhf extends IOException {
    public zzhf() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    public zzhf(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    public zzhf(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
