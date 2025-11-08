package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.proto.KeyStatusType;
import java.security.GeneralSecurityException;
import java.util.List;

public final class MonitoringUtil {
    public static final MonitoringClient.Logger DO_NOTHING_LOGGER = new DoNothingLogger((AnonymousClass1) null);
    private static final String TYPE_URL_PREFIX = "type.googleapis.com/google.crypto.";

    /* renamed from: com.google.crypto.tink.internal.MonitoringUtil$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$KeyStatusType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.crypto.tink.proto.KeyStatusType[] r0 = com.google.crypto.tink.proto.KeyStatusType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$KeyStatusType = r0
                com.google.crypto.tink.proto.KeyStatusType r1 = com.google.crypto.tink.proto.KeyStatusType.ENABLED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$KeyStatusType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.KeyStatusType r1 = com.google.crypto.tink.proto.KeyStatusType.DISABLED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$KeyStatusType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.proto.KeyStatusType r1 = com.google.crypto.tink.proto.KeyStatusType.DESTROYED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.internal.MonitoringUtil.AnonymousClass1.<clinit>():void");
        }
    }

    public static class DoNothingLogger implements MonitoringClient.Logger {
        private DoNothingLogger() {
        }

        public void log(int i3, long j2) {
        }

        public void logFailure() {
        }

        public /* synthetic */ DoNothingLogger(AnonymousClass1 r12) {
            this();
        }
    }

    private MonitoringUtil() {
    }

    public static <P> MonitoringKeysetInfo getMonitoringKeysetInfo(PrimitiveSet<P> primitiveSet) {
        MonitoringKeysetInfo.Builder newBuilder = MonitoringKeysetInfo.newBuilder();
        newBuilder.setAnnotations(primitiveSet.getAnnotations());
        for (List<PrimitiveSet.Entry<P>> it : primitiveSet.getAll()) {
            for (PrimitiveSet.Entry entry : it) {
                newBuilder.addEntry(parseStatus(entry.getStatus()), entry.getKeyId(), parseKeyTypeUrl(entry.getKeyType()), entry.getOutputPrefixType().name());
            }
        }
        if (primitiveSet.getPrimary() != null) {
            newBuilder.setPrimaryKeyId(primitiveSet.getPrimary().getKeyId());
        }
        try {
            return newBuilder.build();
        } catch (GeneralSecurityException e3) {
            throw new IllegalStateException(e3);
        }
    }

    private static String parseKeyTypeUrl(String str) {
        return !str.startsWith(TYPE_URL_PREFIX) ? str : str.substring(34);
    }

    private static KeyStatus parseStatus(KeyStatusType keyStatusType) {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[keyStatusType.ordinal()];
        if (i3 == 1) {
            return KeyStatus.ENABLED;
        }
        if (i3 == 2) {
            return KeyStatus.DISABLED;
        }
        if (i3 == 3) {
            return KeyStatus.DESTROYED;
        }
        throw new IllegalStateException("Unknown key status");
    }
}
