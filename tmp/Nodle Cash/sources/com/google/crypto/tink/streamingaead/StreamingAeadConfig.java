package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class StreamingAeadConfig {
    public static final String AES_CTR_HMAC_STREAMINGAEAD_TYPE_URL = new AesCtrHmacStreamingKeyManager().getKeyType();
    public static final String AES_GCM_HKDF_STREAMINGAEAD_TYPE_URL = new AesGcmHkdfStreamingKeyManager().getKeyType();
    public static final RegistryConfig LATEST = RegistryConfig.getDefaultInstance();
    @Deprecated
    public static final RegistryConfig TINK_1_1_0 = RegistryConfig.getDefaultInstance();

    static {
        try {
            init();
        } catch (GeneralSecurityException e3) {
            throw new ExceptionInInitializerError(e3);
        }
    }

    private StreamingAeadConfig() {
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        register();
    }

    public static void register() throws GeneralSecurityException {
        StreamingAeadWrapper.register();
        if (!TinkFips.useOnlyFips()) {
            AesCtrHmacStreamingKeyManager.register(true);
            AesGcmHkdfStreamingKeyManager.register(true);
        }
    }
}
