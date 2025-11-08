package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import java.security.GeneralSecurityException;

public class StreamingAeadWrapper implements PrimitiveWrapper<StreamingAead, StreamingAead> {
    private static final StreamingAeadWrapper WRAPPER = new StreamingAeadWrapper();

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<StreamingAead> getInputPrimitiveClass() {
        return StreamingAead.class;
    }

    public Class<StreamingAead> getPrimitiveClass() {
        return StreamingAead.class;
    }

    public StreamingAead wrap(PrimitiveSet<StreamingAead> primitiveSet) throws GeneralSecurityException {
        return new StreamingAeadHelper(primitiveSet);
    }
}
