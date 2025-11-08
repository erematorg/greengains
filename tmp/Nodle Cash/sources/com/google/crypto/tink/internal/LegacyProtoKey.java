package com.google.crypto.tink.internal;

import androidx.camera.camera2.internal.C0118y;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
public final class LegacyProtoKey extends Key {
    private final ProtoKeySerialization serialization;

    /* renamed from: com.google.crypto.tink.internal.LegacyProtoKey$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$KeyData$KeyMaterialType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
        static {
            /*
                com.google.crypto.tink.proto.KeyData$KeyMaterialType[] r0 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$KeyData$KeyMaterialType = r0
                r1 = 1
                com.google.crypto.tink.proto.KeyData$KeyMaterialType r2 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.SYMMETRIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$crypto$tink$proto$KeyData$KeyMaterialType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.KeyData$KeyMaterialType r3 = com.google.crypto.tink.proto.KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.google.crypto.tink.proto.OutputPrefixType[] r2 = com.google.crypto.tink.proto.OutputPrefixType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = r2
                com.google.crypto.tink.proto.OutputPrefixType r3 = com.google.crypto.tink.proto.OutputPrefixType.TINK     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.google.crypto.tink.proto.OutputPrefixType r2 = com.google.crypto.tink.proto.OutputPrefixType.LEGACY     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.crypto.tink.proto.OutputPrefixType r1 = com.google.crypto.tink.proto.OutputPrefixType.RAW     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x004e }
                com.google.crypto.tink.proto.OutputPrefixType r1 = com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY     // Catch:{ NoSuchFieldError -> 0x004e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.internal.LegacyProtoKey.AnonymousClass1.<clinit>():void");
        }
    }

    @Immutable
    public static class LegacyProtoParametersNotForCreation extends Parameters {
        private final OutputPrefixType outputPrefixType;
        private final String typeUrl;

        public /* synthetic */ LegacyProtoParametersNotForCreation(String str, OutputPrefixType outputPrefixType2, AnonymousClass1 r3) {
            this(str, outputPrefixType2);
        }

        private static String outputPrefixToString(OutputPrefixType outputPrefixType2) {
            int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType2.ordinal()];
            return i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK";
        }

        public boolean hasIdRequirement() {
            return this.outputPrefixType != OutputPrefixType.RAW;
        }

        public String toString() {
            return C0118y.g("(typeUrl=", this.typeUrl, ", outputPrefixType=", outputPrefixToString(this.outputPrefixType), ")");
        }

        private LegacyProtoParametersNotForCreation(String str, OutputPrefixType outputPrefixType2) {
            this.typeUrl = str;
            this.outputPrefixType = outputPrefixType2;
        }
    }

    public LegacyProtoKey(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        throwIfMissingAccess(protoKeySerialization, secretKeyAccess);
        this.serialization = protoKeySerialization;
    }

    private static void throwIfMissingAccess(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$KeyData$KeyMaterialType[protoKeySerialization.getKeyMaterialType().ordinal()];
        if (i3 == 1 || i3 == 2) {
            SecretKeyAccess.requireAccess(secretKeyAccess);
        }
    }

    public boolean equalsKey(Key key) {
        if (!(key instanceof LegacyProtoKey)) {
            return false;
        }
        ProtoKeySerialization protoKeySerialization = ((LegacyProtoKey) key).serialization;
        if (protoKeySerialization.getOutputPrefixType().equals(this.serialization.getOutputPrefixType()) && protoKeySerialization.getKeyMaterialType().equals(this.serialization.getKeyMaterialType()) && protoKeySerialization.getTypeUrl().equals(this.serialization.getTypeUrl()) && Objects.equals(protoKeySerialization.getIdRequirementOrNull(), this.serialization.getIdRequirementOrNull())) {
            return Bytes.equal(this.serialization.getValue().toByteArray(), protoKeySerialization.getValue().toByteArray());
        }
        return false;
    }

    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.serialization.getIdRequirementOrNull();
    }

    public Parameters getParameters() {
        return new LegacyProtoParametersNotForCreation(this.serialization.getTypeUrl(), this.serialization.getOutputPrefixType(), (AnonymousClass1) null);
    }

    public ProtoKeySerialization getSerialization(@Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        throwIfMissingAccess(this.serialization, secretKeyAccess);
        return this.serialization;
    }
}
