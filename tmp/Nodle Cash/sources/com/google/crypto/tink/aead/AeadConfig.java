package com.google.crypto.tink.aead;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.mac.MacConfig;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class AeadConfig {
    public static final String AES_CTR_HMAC_AEAD_TYPE_URL = new AesCtrHmacAeadKeyManager().getKeyType();
    public static final String AES_EAX_TYPE_URL = new AesEaxKeyManager().getKeyType();
    public static final String AES_GCM_SIV_TYPE_URL = new AesGcmSivKeyManager().getKeyType();
    public static final String AES_GCM_TYPE_URL = new AesGcmKeyManager().getKeyType();
    public static final String CHACHA20_POLY1305_TYPE_URL = new ChaCha20Poly1305KeyManager().getKeyType();
    public static final String KMS_AEAD_TYPE_URL = new KmsAeadKeyManager().getKeyType();
    public static final String KMS_ENVELOPE_AEAD_TYPE_URL = new KmsEnvelopeAeadKeyManager().getKeyType();
    @Deprecated
    public static final RegistryConfig LATEST;
    @Deprecated
    public static final RegistryConfig TINK_1_0_0;
    @Deprecated
    public static final RegistryConfig TINK_1_1_0;
    public static final String XCHACHA20_POLY1305_TYPE_URL = new XChaCha20Poly1305KeyManager().getKeyType();

    static {
        RegistryConfig defaultInstance = RegistryConfig.getDefaultInstance();
        TINK_1_0_0 = defaultInstance;
        TINK_1_1_0 = defaultInstance;
        LATEST = defaultInstance;
        try {
            init();
        } catch (GeneralSecurityException e3) {
            throw new ExceptionInInitializerError(e3);
        }
    }

    private AeadConfig() {
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        register();
    }

    public static void register() throws GeneralSecurityException {
        AeadWrapper.register();
        MacConfig.register();
        AesCtrHmacAeadKeyManager.register(true);
        AesGcmKeyManager.register(true);
        if (!TinkFips.useOnlyFips()) {
            AesEaxKeyManager.register(true);
            AesGcmSivKeyManager.register(true);
            ChaCha20Poly1305KeyManager.register(true);
            KmsAeadKeyManager.register(true);
            KmsEnvelopeAeadKeyManager.register(true);
            XChaCha20Poly1305KeyManager.register(true);
        }
    }

    @Deprecated
    public static void registerStandardKeyTypes() throws GeneralSecurityException {
        register();
    }
}
