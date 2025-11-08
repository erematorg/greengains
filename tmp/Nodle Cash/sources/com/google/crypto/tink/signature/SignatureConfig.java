package com.google.crypto.tink.signature;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class SignatureConfig {
    public static final String ECDSA_PRIVATE_KEY_TYPE_URL = new EcdsaSignKeyManager().getKeyType();
    public static final String ECDSA_PUBLIC_KEY_TYPE_URL = new EcdsaVerifyKeyManager().getKeyType();
    public static final String ED25519_PRIVATE_KEY_TYPE_URL = new Ed25519PrivateKeyManager().getKeyType();
    public static final String ED25519_PUBLIC_KEY_TYPE_URL = new Ed25519PublicKeyManager().getKeyType();
    @Deprecated
    public static final RegistryConfig LATEST = RegistryConfig.getDefaultInstance();
    public static final String RSA_PKCS1_PRIVATE_KEY_TYPE_URL = new RsaSsaPkcs1SignKeyManager().getKeyType();
    public static final String RSA_PKCS1_PUBLIC_KEY_TYPE_URL = new RsaSsaPkcs1VerifyKeyManager().getKeyType();
    public static final String RSA_PSS_PRIVATE_KEY_TYPE_URL = new RsaSsaPssSignKeyManager().getKeyType();
    public static final String RSA_PSS_PUBLIC_KEY_TYPE_URL = new RsaSsaPssVerifyKeyManager().getKeyType();
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

    private SignatureConfig() {
    }

    @Deprecated
    public static void init() throws GeneralSecurityException {
        register();
    }

    public static void register() throws GeneralSecurityException {
        PublicKeySignWrapper.register();
        PublicKeyVerifyWrapper.register();
        EcdsaSignKeyManager.registerPair(true);
        RsaSsaPkcs1SignKeyManager.registerPair(true);
        if (!TinkFips.useOnlyFips()) {
            RsaSsaPssSignKeyManager.registerPair(true);
            Ed25519PrivateKeyManager.registerPair(true);
        }
    }
}
