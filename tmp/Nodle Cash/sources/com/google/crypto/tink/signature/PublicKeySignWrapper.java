package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;

public class PublicKeySignWrapper implements PrimitiveWrapper<PublicKeySign, PublicKeySign> {
    /* access modifiers changed from: private */
    public static final byte[] FORMAT_VERSION = {0};
    private static final PublicKeySignWrapper WRAPPER = new PublicKeySignWrapper();

    public static class WrappedPublicKeySign implements PublicKeySign {
        private final MonitoringClient.Logger logger;
        private final PrimitiveSet<PublicKeySign> primitives;

        public WrappedPublicKeySign(PrimitiveSet<PublicKeySign> primitiveSet) {
            this.primitives = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                this.logger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet), "public_key_sign", "sign");
            } else {
                this.logger = MonitoringUtil.DO_NOTHING_LOGGER;
            }
        }

        public byte[] sign(byte[] bArr) throws GeneralSecurityException {
            if (this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                bArr = Bytes.concat(bArr, PublicKeySignWrapper.FORMAT_VERSION);
            }
            try {
                byte[] concat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().sign(bArr));
                this.logger.log(this.primitives.getPrimary().getKeyId(), (long) bArr.length);
                return concat;
            } catch (GeneralSecurityException e3) {
                this.logger.logFailure();
                throw e3;
            }
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<PublicKeySign> getInputPrimitiveClass() {
        return PublicKeySign.class;
    }

    public Class<PublicKeySign> getPrimitiveClass() {
        return PublicKeySign.class;
    }

    public PublicKeySign wrap(PrimitiveSet<PublicKeySign> primitiveSet) {
        return new WrappedPublicKeySign(primitiveSet);
    }
}
