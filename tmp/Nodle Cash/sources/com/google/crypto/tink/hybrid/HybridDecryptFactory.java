package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeysetHandle;
import java.security.GeneralSecurityException;

@Deprecated
public final class HybridDecryptFactory {
    private HybridDecryptFactory() {
    }

    @Deprecated
    public static HybridDecrypt getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        HybridDecryptWrapper.register();
        return (HybridDecrypt) keysetHandle.getPrimitive(HybridDecrypt.class);
    }
}
