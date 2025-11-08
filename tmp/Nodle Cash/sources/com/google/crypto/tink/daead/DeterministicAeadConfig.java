package com.google.crypto.tink.daead;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import com.google.errorprone.annotations.InlineMe;
import java.security.GeneralSecurityException;

public final class DeterministicAeadConfig {
    public static final String AES_SIV_TYPE_URL = new AesSivKeyManager().getKeyType();
    @Deprecated
    public static final RegistryConfig LATEST = RegistryConfig.getDefaultInstance();
    @Deprecated
    public static final RegistryConfig TINK_1_1_0 = RegistryConfig.getDefaultInstance();

    static {
        try {
            register();
        } catch (GeneralSecurityException e3) {
            throw new ExceptionInInitializerError(e3);
        }
    }

    private DeterministicAeadConfig() {
    }

    @InlineMe(imports = {"com.google.crypto.tink.daead.DeterministicAeadConfig"}, replacement = "DeterministicAeadConfig.register()")
    @Deprecated
    public static void init() throws GeneralSecurityException {
        register();
    }

    public static void register() throws GeneralSecurityException {
        DeterministicAeadWrapper.register();
        if (!TinkFips.useOnlyFips()) {
            AesSivKeyManager.register(true);
        }
    }
}
