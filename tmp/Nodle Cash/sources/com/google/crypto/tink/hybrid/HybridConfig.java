package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class HybridConfig {
    public static final String ECIES_AEAD_HKDF_PRIVATE_KEY_TYPE_URL = new EciesAeadHkdfPrivateKeyManager().getKeyType();
    public static final String ECIES_AEAD_HKDF_PUBLIC_KEY_TYPE_URL = new EciesAeadHkdfPublicKeyManager().getKeyType();
    @Deprecated
    public static final RegistryConfig LATEST = RegistryConfig.getDefaultInstance();
    @Deprecated
    public static final RegistryConfig TINK_1_0_0 = RegistryConfig.getDefaultInstance();
    @Deprecated
    public static final RegistryConfig TINK_1_1_0 = RegistryConfig.getDefaultInstance();

    static {
        try {
            init();
        } catch (GeneralSecurityException e3) {
            throw new ExceptionInInitializerError(e3);
        }
    }

    private HybridConfig() {
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        register();
    }

    public static void register() throws GeneralSecurityException {
        HybridDecryptWrapper.register();
        HybridEncryptWrapper.register();
        AeadConfig.register();
        if (!TinkFips.useOnlyFips()) {
            EciesAeadHkdfPrivateKeyManager.registerPair(true);
            HpkePrivateKeyManager.registerPair(true);
        }
    }
}
