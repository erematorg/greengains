package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;

public class HybridEncryptWrapper implements PrimitiveWrapper<HybridEncrypt, HybridEncrypt> {
    private static final HybridEncryptWrapper WRAPPER = new HybridEncryptWrapper();

    public static class WrappedHybridEncrypt implements HybridEncrypt {
        private final MonitoringClient.Logger encLogger;
        final PrimitiveSet<HybridEncrypt> primitives;

        public WrappedHybridEncrypt(PrimitiveSet<HybridEncrypt> primitiveSet) {
            this.primitives = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                this.encLogger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet), "hybrid_encrypt", "encrypt");
            } else {
                this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            }
        }

        public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (this.primitives.getPrimary() != null) {
                try {
                    byte[] concat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().encrypt(bArr, bArr2));
                    this.encLogger.log(this.primitives.getPrimary().getKeyId(), (long) bArr.length);
                    return concat;
                } catch (GeneralSecurityException e3) {
                    this.encLogger.logFailure();
                    throw e3;
                }
            } else {
                this.encLogger.logFailure();
                throw new GeneralSecurityException("keyset without primary key");
            }
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<HybridEncrypt> getInputPrimitiveClass() {
        return HybridEncrypt.class;
    }

    public Class<HybridEncrypt> getPrimitiveClass() {
        return HybridEncrypt.class;
    }

    public HybridEncrypt wrap(PrimitiveSet<HybridEncrypt> primitiveSet) {
        return new WrappedHybridEncrypt(primitiveSet);
    }
}
