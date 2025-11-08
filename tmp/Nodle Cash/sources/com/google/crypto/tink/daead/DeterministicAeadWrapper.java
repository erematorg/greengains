package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;

public class DeterministicAeadWrapper implements PrimitiveWrapper<DeterministicAead, DeterministicAead> {
    private static final DeterministicAeadWrapper WRAPPER = new DeterministicAeadWrapper();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(DeterministicAeadWrapper.class.getName());

    public static class WrappedDeterministicAead implements DeterministicAead {
        private final MonitoringClient.Logger decLogger;
        private final MonitoringClient.Logger encLogger;
        private final PrimitiveSet<DeterministicAead> primitives;

        public WrappedDeterministicAead(PrimitiveSet<DeterministicAead> primitiveSet) {
            this.primitives = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                MonitoringClient monitoringClient = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitiveSet);
                this.encLogger = monitoringClient.createLogger(monitoringKeysetInfo, "daead", "encrypt");
                this.decLogger = monitoringClient.createLogger(monitoringKeysetInfo, "daead", "decrypt");
                return;
            }
            MonitoringClient.Logger logger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.encLogger = logger;
            this.decLogger = logger;
        }

        public byte[] decryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOf = Arrays.copyOf(bArr, 5);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry next : this.primitives.getPrimitive(copyOf)) {
                    try {
                        byte[] decryptDeterministically = ((DeterministicAead) next.getPrimitive()).decryptDeterministically(copyOfRange, bArr2);
                        this.decLogger.log(next.getKeyId(), (long) copyOfRange.length);
                        return decryptDeterministically;
                    } catch (GeneralSecurityException e3) {
                        Logger access$000 = DeterministicAeadWrapper.logger;
                        access$000.info("ciphertext prefix matches a key, but cannot decrypt: " + e3);
                    }
                }
            }
            for (PrimitiveSet.Entry next2 : this.primitives.getRawPrimitives()) {
                try {
                    byte[] decryptDeterministically2 = ((DeterministicAead) next2.getPrimitive()).decryptDeterministically(bArr, bArr2);
                    this.decLogger.log(next2.getKeyId(), (long) bArr.length);
                    return decryptDeterministically2;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }

        public byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            try {
                byte[] concat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().encryptDeterministically(bArr, bArr2));
                this.encLogger.log(this.primitives.getPrimary().getKeyId(), (long) bArr.length);
                return concat;
            } catch (GeneralSecurityException e3) {
                this.encLogger.logFailure();
                throw e3;
            }
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<DeterministicAead> getInputPrimitiveClass() {
        return DeterministicAead.class;
    }

    public Class<DeterministicAead> getPrimitiveClass() {
        return DeterministicAead.class;
    }

    public DeterministicAead wrap(PrimitiveSet<DeterministicAead> primitiveSet) {
        return new WrappedDeterministicAead(primitiveSet);
    }
}
