package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.KeysetHandle;
import java.security.GeneralSecurityException;

@Deprecated
public final class HybridEncryptFactory {
    private HybridEncryptFactory() {
    }

    @Deprecated
    public static HybridEncrypt getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        HybridEncryptWrapper.register();
        return (HybridEncrypt) keysetHandle.getPrimitive(HybridEncrypt.class);
    }
}
