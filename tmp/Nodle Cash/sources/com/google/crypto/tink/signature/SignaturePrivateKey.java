package com.google.crypto.tink.signature;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.PrivateKey;
import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import javax.annotation.Nullable;

@Immutable
@Alpha
public abstract class SignaturePrivateKey extends Key implements PrivateKey {
    @Nullable
    public Integer getIdRequirementOrNull() {
        return getPublicKey().getIdRequirementOrNull();
    }

    public final Bytes getOutputPrefix() {
        return getPublicKey().getOutputPrefix();
    }

    public abstract SignaturePublicKey getPublicKey();

    public SignatureParameters getParameters() {
        return getPublicKey().getParameters();
    }
}
