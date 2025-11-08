package com.google.crypto.tink.tinkkey.internal;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.tinkkey.TinkKey;
import com.google.errorprone.annotations.Immutable;

@Immutable
public final class ProtoKey implements TinkKey {
    private final boolean hasSecret;
    private final KeyData keyData;
    private final KeyTemplate.OutputPrefixType outputPrefixType;

    public ProtoKey(KeyData keyData2, KeyTemplate.OutputPrefixType outputPrefixType2) {
        this.hasSecret = isSecret(keyData2);
        this.keyData = keyData2;
        this.outputPrefixType = outputPrefixType2;
    }

    private static boolean isSecret(KeyData keyData2) {
        return keyData2.getKeyMaterialType() == KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL || keyData2.getKeyMaterialType() == KeyData.KeyMaterialType.SYMMETRIC || keyData2.getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public KeyTemplate getKeyTemplate() {
        throw new UnsupportedOperationException();
    }

    public KeyTemplate.OutputPrefixType getOutputPrefixType() {
        return this.outputPrefixType;
    }

    public KeyData getProtoKey() {
        return this.keyData;
    }

    public boolean hasSecret() {
        return this.hasSecret;
    }
}
