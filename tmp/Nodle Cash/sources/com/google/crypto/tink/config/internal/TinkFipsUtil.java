package com.google.crypto.tink.config.internal;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public final class TinkFipsUtil {
    private static final AtomicBoolean isRestrictedToFips = new AtomicBoolean(false);
    private static final Logger logger = Logger.getLogger(TinkFipsUtil.class.getName());

    public enum AlgorithmFipsCompatibility {
        ALGORITHM_NOT_FIPS {
            public boolean isCompatible() {
                return !TinkFipsUtil.useOnlyFips();
            }
        },
        ALGORITHM_REQUIRES_BORINGCRYPTO {
            public boolean isCompatible() {
                return !TinkFipsUtil.useOnlyFips() || TinkFipsUtil.fipsModuleAvailable();
            }
        };

        public abstract boolean isCompatible();
    }

    private TinkFipsUtil() {
    }

    public static Boolean checkConscryptIsAvailableAndUsesFipsBoringSsl() {
        try {
            return (Boolean) Class.forName("org.conscrypt.Conscrypt").getMethod("isBoringSslFIPSBuild", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            logger.info("Conscrypt is not available or does not support checking for FIPS build.");
            return Boolean.FALSE;
        }
    }

    public static boolean fipsModuleAvailable() {
        return checkConscryptIsAvailableAndUsesFipsBoringSsl().booleanValue();
    }

    public static void setFipsRestricted() {
        isRestrictedToFips.set(true);
    }

    public static void unsetFipsRestricted() {
        isRestrictedToFips.set(false);
    }

    public static boolean useOnlyFips() {
        return TinkFipsStatus.useOnlyFips() || isRestrictedToFips.get();
    }
}
