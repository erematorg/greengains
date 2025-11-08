package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
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

public class AeadWrapper implements PrimitiveWrapper<Aead, Aead> {
    private static final AeadWrapper WRAPPER = new AeadWrapper();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(AeadWrapper.class.getName());

    public static class WrappedAead implements Aead {
        private final MonitoringClient.Logger decLogger;
        private final MonitoringClient.Logger encLogger;
        private final PrimitiveSet<Aead> pSet;

        public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOf = Arrays.copyOf(bArr, 5);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry next : this.pSet.getPrimitive(copyOf)) {
                    try {
                        byte[] decrypt = ((Aead) next.getPrimitive()).decrypt(copyOfRange, bArr2);
                        this.decLogger.log(next.getKeyId(), (long) copyOfRange.length);
                        return decrypt;
                    } catch (GeneralSecurityException e3) {
                        Logger access$000 = AeadWrapper.logger;
                        access$000.info("ciphertext prefix matches a key, but cannot decrypt: " + e3);
                    }
                }
            }
            for (PrimitiveSet.Entry next2 : this.pSet.getRawPrimitives()) {
                try {
                    byte[] decrypt2 = ((Aead) next2.getPrimitive()).decrypt(bArr, bArr2);
                    this.decLogger.log(next2.getKeyId(), (long) bArr.length);
                    return decrypt2;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }

        public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            try {
                byte[] concat = Bytes.concat(this.pSet.getPrimary().getIdentifier(), this.pSet.getPrimary().getPrimitive().encrypt(bArr, bArr2));
                this.encLogger.log(this.pSet.getPrimary().getKeyId(), (long) bArr.length);
                return concat;
            } catch (GeneralSecurityException e3) {
                this.encLogger.logFailure();
                throw e3;
            }
        }

        private WrappedAead(PrimitiveSet<Aead> primitiveSet) {
            this.pSet = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                MonitoringClient monitoringClient = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitiveSet);
                this.encLogger = monitoringClient.createLogger(monitoringKeysetInfo, "aead", "encrypt");
                this.decLogger = monitoringClient.createLogger(monitoringKeysetInfo, "aead", "decrypt");
                return;
            }
            MonitoringClient.Logger logger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.encLogger = logger;
            this.decLogger = logger;
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<Aead> getInputPrimitiveClass() {
        return Aead.class;
    }

    public Class<Aead> getPrimitiveClass() {
        return Aead.class;
    }

    public Aead wrap(PrimitiveSet<Aead> primitiveSet) throws GeneralSecurityException {
        return new WrappedAead(primitiveSet);
    }
}
