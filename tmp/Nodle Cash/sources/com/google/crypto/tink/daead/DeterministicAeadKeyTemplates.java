package com.google.crypto.tink.daead;

import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;

public final class DeterministicAeadKeyTemplates {
    public static final KeyTemplate AES256_SIV = createAesSivKeyTemplate(64);

    private DeterministicAeadKeyTemplates() {
    }

    public static KeyTemplate createAesSivKeyTemplate(int i3) {
        return (KeyTemplate) KeyTemplate.newBuilder().setValue(((AesSivKeyFormat) AesSivKeyFormat.newBuilder().setKeySize(i3).build()).toByteString()).setTypeUrl(new AesSivKeyManager().getKeyType()).setOutputPrefixType(OutputPrefixType.TINK).build();
    }
}
