package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.web3j.ens.contracts.generated.OffchainResolverContract;

class MacWrapper implements PrimitiveWrapper<Mac, Mac> {
    /* access modifiers changed from: private */
    public static final byte[] FORMAT_VERSION = {0};
    private static final MacWrapper WRAPPER = new MacWrapper();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(MacWrapper.class.getName());

    public static class WrappedMac implements Mac {
        private final MonitoringClient.Logger computeLogger;
        private final PrimitiveSet<Mac> primitives;
        private final MonitoringClient.Logger verifyLogger;

        public byte[] computeMac(byte[] bArr) throws GeneralSecurityException {
            if (this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                bArr = Bytes.concat(bArr, MacWrapper.FORMAT_VERSION);
            }
            try {
                byte[] concat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().computeMac(bArr));
                this.computeLogger.log(this.primitives.getPrimary().getKeyId(), (long) bArr.length);
                return concat;
            } catch (GeneralSecurityException e3) {
                this.computeLogger.logFailure();
                throw e3;
            }
        }

        public void verifyMac(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOf = Arrays.copyOf(bArr, 5);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry next : this.primitives.getPrimitive(copyOf)) {
                    byte[] concat = next.getOutputPrefixType().equals(OutputPrefixType.LEGACY) ? Bytes.concat(bArr2, MacWrapper.FORMAT_VERSION) : bArr2;
                    try {
                        ((Mac) next.getPrimitive()).verifyMac(copyOfRange, concat);
                        this.verifyLogger.log(next.getKeyId(), (long) concat.length);
                        return;
                    } catch (GeneralSecurityException e3) {
                        Logger access$100 = MacWrapper.logger;
                        access$100.info("tag prefix matches a key, but cannot verify: " + e3);
                    }
                }
                for (PrimitiveSet.Entry next2 : this.primitives.getRawPrimitives()) {
                    try {
                        ((Mac) next2.getPrimitive()).verifyMac(bArr, bArr2);
                        this.verifyLogger.log(next2.getKeyId(), (long) bArr2.length);
                        return;
                    } catch (GeneralSecurityException unused) {
                    }
                }
                this.verifyLogger.logFailure();
                throw new GeneralSecurityException("invalid MAC");
            }
            this.verifyLogger.logFailure();
            throw new GeneralSecurityException("tag too short");
        }

        private WrappedMac(PrimitiveSet<Mac> primitiveSet) {
            this.primitives = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                MonitoringClient monitoringClient = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitiveSet);
                this.computeLogger = monitoringClient.createLogger(monitoringKeysetInfo, "mac", "compute");
                this.verifyLogger = monitoringClient.createLogger(monitoringKeysetInfo, "mac", OffchainResolverContract.FUNC_VERIFY);
                return;
            }
            MonitoringClient.Logger logger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.computeLogger = logger;
            this.verifyLogger = logger;
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    private void validateMacKeyPrefixes(PrimitiveSet<Mac> primitiveSet) throws GeneralSecurityException {
        for (List<PrimitiveSet.Entry<Mac>> it : primitiveSet.getAll()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    PrimitiveSet.Entry entry = (PrimitiveSet.Entry) it2.next();
                    if (entry.getKey() instanceof MacKey) {
                        MacKey macKey = (MacKey) entry.getKey();
                        com.google.crypto.tink.util.Bytes copyFrom = com.google.crypto.tink.util.Bytes.copyFrom(entry.getIdentifier());
                        if (!copyFrom.equals(macKey.getOutputPrefix())) {
                            throw new GeneralSecurityException("Mac Key with parameters " + macKey.getParameters() + " has wrong output prefix (" + macKey.getOutputPrefix() + ") instead of (" + copyFrom + ")");
                        }
                    }
                }
            }
        }
    }

    public Class<Mac> getInputPrimitiveClass() {
        return Mac.class;
    }

    public Class<Mac> getPrimitiveClass() {
        return Mac.class;
    }

    public Mac wrap(PrimitiveSet<Mac> primitiveSet) throws GeneralSecurityException {
        validateMacKeyPrefixes(primitiveSet);
        return new WrappedMac(primitiveSet);
    }
}
