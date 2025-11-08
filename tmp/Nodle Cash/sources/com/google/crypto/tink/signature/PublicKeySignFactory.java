package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PublicKeySign;
import java.security.GeneralSecurityException;

@Deprecated
public final class PublicKeySignFactory {
    private PublicKeySignFactory() {
    }

    @Deprecated
    public static PublicKeySign getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        PublicKeySignWrapper.register();
        return (PublicKeySign) keysetHandle.getPrimitive(PublicKeySign.class);
    }
}
