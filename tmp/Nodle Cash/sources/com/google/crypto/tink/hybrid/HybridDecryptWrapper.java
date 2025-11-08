package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;

public class HybridDecryptWrapper implements PrimitiveWrapper<HybridDecrypt, HybridDecrypt> {
    private static final HybridDecryptWrapper WRAPPER = new HybridDecryptWrapper();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(HybridDecryptWrapper.class.getName());

    public static class WrappedHybridDecrypt implements HybridDecrypt {
        private final MonitoringClient.Logger decLogger;
        private final PrimitiveSet<HybridDecrypt> primitives;

        public WrappedHybridDecrypt(PrimitiveSet<HybridDecrypt> primitiveSet) {
            this.primitives = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                this.decLogger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet), "hybrid_decrypt", "decrypt");
            } else {
                this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            }
        }

        public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry next : this.primitives.getPrimitive(copyOfRange)) {
                    try {
                        byte[] decrypt = ((HybridDecrypt) next.getPrimitive()).decrypt(copyOfRange2, bArr2);
                        this.decLogger.log(next.getKeyId(), (long) copyOfRange2.length);
                        return decrypt;
                    } catch (GeneralSecurityException e3) {
                        Logger access$000 = HybridDecryptWrapper.logger;
                        access$000.info("ciphertext prefix matches a key, but cannot decrypt: " + e3.toString());
                    }
                }
            }
            for (PrimitiveSet.Entry next2 : this.primitives.getRawPrimitives()) {
                try {
                    byte[] decrypt2 = ((HybridDecrypt) next2.getPrimitive()).decrypt(bArr, bArr2);
                    this.decLogger.log(next2.getKeyId(), (long) bArr.length);
                    return decrypt2;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<HybridDecrypt> getInputPrimitiveClass() {
        return HybridDecrypt.class;
    }

    public Class<HybridDecrypt> getPrimitiveClass() {
        return HybridDecrypt.class;
    }

    public HybridDecrypt wrap(PrimitiveSet<HybridDecrypt> primitiveSet) {
        return new WrappedHybridDecrypt(primitiveSet);
    }
}
