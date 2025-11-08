package com.google.crypto.tink.jwt;

import com.google.crypto.tink.proto.RegistryConfig;
import java.security.GeneralSecurityException;

public final class JwtSignatureConfig {
    public static final String JWT_ECDSA_PRIVATE_KEY_TYPE_URL = new JwtEcdsaSignKeyManager().getKeyType();
    public static final String JWT_ECDSA_PUBLIC_KEY_TYPE_URL = new JwtEcdsaVerifyKeyManager().getKeyType();
    public static final String JWT_RSA_PKCS1_PRIVATE_KEY_TYPE_URL = new JwtRsaSsaPkcs1SignKeyManager().getKeyType();
    public static final String JWT_RSA_PKCS1_PUBLIC_KEY_TYPE_URL = new JwtRsaSsaPkcs1VerifyKeyManager().getKeyType();
    public static final String JWT_RSA_PSS_PRIVATE_KEY_TYPE_URL = new JwtRsaSsaPssSignKeyManager().getKeyType();
    public static final String JWT_RSA_PSS_PUBLIC_KEY_TYPE_URL = new JwtRsaSsaPssVerifyKeyManager().getKeyType();
    public static final RegistryConfig LATEST = RegistryConfig.getDefaultInstance();

    private JwtSignatureConfig() {
    }

    public static void register() throws GeneralSecurityException {
        JwtEcdsaSignKeyManager.registerPair(true);
        JwtRsaSsaPkcs1SignKeyManager.registerPair(true);
        JwtRsaSsaPssSignKeyManager.registerPair(true);
        JwtPublicKeySignWrapper.register();
        JwtPublicKeyVerifyWrapper.register();
    }
}
