package com.google.crypto.tink;

import java.security.GeneralSecurityException;

public interface PrimitiveWrapper<B, P> {
    Class<B> getInputPrimitiveClass();

    Class<P> getPrimitiveClass();

    P wrap(PrimitiveSet<B> primitiveSet) throws GeneralSecurityException;
}
