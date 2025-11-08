package com.google.crypto.tink.prf;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Immutable
public class PrfSetWrapper implements PrimitiveWrapper<Prf, PrfSet> {
    private static final PrfSetWrapper WRAPPER = new PrfSetWrapper();

    public static class WrappedPrfSet extends PrfSet {
        private final Map<Integer, Prf> keyIdToPrfMap;
        private final int primaryKeyId;

        @Immutable
        public static class PrfWithMonitoring implements Prf {
            private final int keyId;
            private final MonitoringClient.Logger logger;
            private final Prf prf;

            public PrfWithMonitoring(Prf prf2, int i3, MonitoringClient.Logger logger2) {
                this.prf = prf2;
                this.keyId = i3;
                this.logger = logger2;
            }

            public byte[] compute(byte[] bArr, int i3) throws GeneralSecurityException {
                try {
                    byte[] compute = this.prf.compute(bArr, i3);
                    this.logger.log(this.keyId, (long) bArr.length);
                    return compute;
                } catch (GeneralSecurityException e3) {
                    this.logger.logFailure();
                    throw e3;
                }
            }
        }

        public Map<Integer, Prf> getPrfs() throws GeneralSecurityException {
            return this.keyIdToPrfMap;
        }

        public int getPrimaryId() {
            return this.primaryKeyId;
        }

        private WrappedPrfSet(PrimitiveSet<Prf> primitiveSet) throws GeneralSecurityException {
            MonitoringClient.Logger logger;
            if (primitiveSet.getRawPrimitives().isEmpty()) {
                throw new GeneralSecurityException("No primitives provided.");
            } else if (primitiveSet.getPrimary() != null) {
                if (primitiveSet.hasAnnotations()) {
                    logger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet), "prf", "compute");
                } else {
                    logger = MonitoringUtil.DO_NOTHING_LOGGER;
                }
                this.primaryKeyId = primitiveSet.getPrimary().getKeyId();
                List<PrimitiveSet.Entry<Prf>> rawPrimitives = primitiveSet.getRawPrimitives();
                HashMap hashMap = new HashMap();
                for (PrimitiveSet.Entry next : rawPrimitives) {
                    if (next.getOutputPrefixType().equals(OutputPrefixType.RAW)) {
                        hashMap.put(Integer.valueOf(next.getKeyId()), new PrfWithMonitoring((Prf) next.getPrimitive(), next.getKeyId(), logger));
                    } else {
                        throw new GeneralSecurityException("Key " + next.getKeyId() + " has non raw prefix type");
                    }
                }
                this.keyIdToPrfMap = Collections.unmodifiableMap(hashMap);
            } else {
                throw new GeneralSecurityException("Primary key not set.");
            }
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<Prf> getInputPrimitiveClass() {
        return Prf.class;
    }

    public Class<PrfSet> getPrimitiveClass() {
        return PrfSet.class;
    }

    public PrfSet wrap(PrimitiveSet<Prf> primitiveSet) throws GeneralSecurityException {
        return new WrappedPrfSet(primitiveSet);
    }
}
