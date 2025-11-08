package com.google.crypto.tink.internal;

import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.tinkkey.KeyHandle;

public final class KeyStatusTypeProtoConverter {

    /* renamed from: com.google.crypto.tink.internal.KeyStatusTypeProtoConverter$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$KeyStatusType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        static {
            /*
                com.google.crypto.tink.tinkkey.KeyHandle$KeyStatusType[] r0 = com.google.crypto.tink.tinkkey.KeyHandle.KeyStatusType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType = r0
                r1 = 1
                com.google.crypto.tink.tinkkey.KeyHandle$KeyStatusType r2 = com.google.crypto.tink.tinkkey.KeyHandle.KeyStatusType.ENABLED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.tinkkey.KeyHandle$KeyStatusType r3 = com.google.crypto.tink.tinkkey.KeyHandle.KeyStatusType.DISABLED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.tinkkey.KeyHandle$KeyStatusType r4 = com.google.crypto.tink.tinkkey.KeyHandle.KeyStatusType.DESTROYED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.google.crypto.tink.proto.KeyStatusType[] r3 = com.google.crypto.tink.proto.KeyStatusType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$crypto$tink$proto$KeyStatusType = r3
                com.google.crypto.tink.proto.KeyStatusType r4 = com.google.crypto.tink.proto.KeyStatusType.ENABLED     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$google$crypto$tink$proto$KeyStatusType     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.crypto.tink.proto.KeyStatusType r3 = com.google.crypto.tink.proto.KeyStatusType.DISABLED     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$KeyStatusType     // Catch:{ NoSuchFieldError -> 0x004d }
                com.google.crypto.tink.proto.KeyStatusType r1 = com.google.crypto.tink.proto.KeyStatusType.DESTROYED     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.internal.KeyStatusTypeProtoConverter.AnonymousClass1.<clinit>():void");
        }
    }

    private KeyStatusTypeProtoConverter() {
    }

    public static KeyHandle.KeyStatusType fromProto(KeyStatusType keyStatusType) {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[keyStatusType.ordinal()];
        if (i3 == 1) {
            return KeyHandle.KeyStatusType.ENABLED;
        }
        if (i3 == 2) {
            return KeyHandle.KeyStatusType.DISABLED;
        }
        if (i3 == 3) {
            return KeyHandle.KeyStatusType.DESTROYED;
        }
        throw new IllegalArgumentException("Unknown key status type.");
    }

    public static KeyStatusType toProto(KeyHandle.KeyStatusType keyStatusType) {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType[keyStatusType.ordinal()];
        if (i3 == 1) {
            return KeyStatusType.ENABLED;
        }
        if (i3 == 2) {
            return KeyStatusType.DISABLED;
        }
        if (i3 == 3) {
            return KeyStatusType.DESTROYED;
        }
        throw new IllegalArgumentException("Unknown key status type.");
    }
}
