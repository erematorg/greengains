package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@Immutable
@Alpha
@CheckReturnValue
public final class SecretKeyAccess {
    private static final SecretKeyAccess INSTANCE = new SecretKeyAccess();

    private SecretKeyAccess() {
    }

    public static SecretKeyAccess instance() {
        return INSTANCE;
    }

    @CanIgnoreReturnValue
    public static SecretKeyAccess requireAccess(@Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (secretKeyAccess != null) {
            return secretKeyAccess;
        }
        throw new GeneralSecurityException("SecretKeyAccess is required");
    }
}
