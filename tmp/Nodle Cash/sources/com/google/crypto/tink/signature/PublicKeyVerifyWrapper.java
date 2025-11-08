package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;
import org.web3j.ens.contracts.generated.OffchainResolverContract;

class PublicKeyVerifyWrapper implements PrimitiveWrapper<PublicKeyVerify, PublicKeyVerify> {
    /* access modifiers changed from: private */
    public static final byte[] FORMAT_VERSION = {0};
    private static final PublicKeyVerifyWrapper WRAPPER = new PublicKeyVerifyWrapper();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(PublicKeyVerifyWrapper.class.getName());

    public static class WrappedPublicKeyVerify implements PublicKeyVerify {
        private final MonitoringClient.Logger monitoringLogger;
        private final PrimitiveSet<PublicKeyVerify> primitives;

        public WrappedPublicKeyVerify(PrimitiveSet<PublicKeyVerify> primitiveSet) {
            this.primitives = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                this.monitoringLogger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet), "public_key_verify", OffchainResolverContract.FUNC_VERIFY);
            } else {
                this.monitoringLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            }
        }

        public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] copyOf = Arrays.copyOf(bArr, 5);
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry next : this.primitives.getPrimitive(copyOf)) {
                    byte[] concat = next.getOutputPrefixType().equals(OutputPrefixType.LEGACY) ? Bytes.concat(bArr2, PublicKeyVerifyWrapper.FORMAT_VERSION) : bArr2;
                    try {
                        ((PublicKeyVerify) next.getPrimitive()).verify(copyOfRange, concat);
                        this.monitoringLogger.log(next.getKeyId(), (long) concat.length);
                        return;
                    } catch (GeneralSecurityException e3) {
                        Logger access$100 = PublicKeyVerifyWrapper.logger;
                        access$100.info("signature prefix matches a key, but cannot verify: " + e3);
                    }
                }
                for (PrimitiveSet.Entry next2 : this.primitives.getRawPrimitives()) {
                    try {
                        ((PublicKeyVerify) next2.getPrimitive()).verify(bArr, bArr2);
                        this.monitoringLogger.log(next2.getKeyId(), (long) bArr2.length);
                        return;
                    } catch (GeneralSecurityException unused) {
                    }
                }
                this.monitoringLogger.logFailure();
                throw new GeneralSecurityException("invalid signature");
            }
            this.monitoringLogger.logFailure();
            throw new GeneralSecurityException("signature too short");
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<PublicKeyVerify> getInputPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    public Class<PublicKeyVerify> getPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    public PublicKeyVerify wrap(PrimitiveSet<PublicKeyVerify> primitiveSet) {
        return new WrappedPublicKeyVerify(primitiveSet);
    }
}
