package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Immutable;

@Immutable
@Alpha
@CheckReturnValue
public final class InsecureSecretKeyAccess {
    private InsecureSecretKeyAccess() {
    }

    public static SecretKeyAccess get() {
        return SecretKeyAccess.instance();
    }
}
