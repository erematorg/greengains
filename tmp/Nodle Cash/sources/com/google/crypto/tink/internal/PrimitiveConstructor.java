package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import java.security.GeneralSecurityException;

public abstract class PrimitiveConstructor<KeyT extends Key, PrimitiveT> {
    private final Class<KeyT> keyClass;
    private final Class<PrimitiveT> primitiveClass;

    public interface PrimitiveConstructionFunction<KeyT extends Key, PrimitiveT> {
        PrimitiveT constructPrimitive(KeyT keyt) throws GeneralSecurityException;
    }

    public static <KeyT extends Key, PrimitiveT> PrimitiveConstructor<KeyT, PrimitiveT> create(final PrimitiveConstructionFunction<KeyT, PrimitiveT> primitiveConstructionFunction, Class<KeyT> cls, Class<PrimitiveT> cls2) {
        return new PrimitiveConstructor<KeyT, PrimitiveT>(cls, cls2) {
            public PrimitiveT constructPrimitive(KeyT keyt) throws GeneralSecurityException {
                return primitiveConstructionFunction.constructPrimitive(keyt);
            }
        };
    }

    public abstract PrimitiveT constructPrimitive(KeyT keyt) throws GeneralSecurityException;

    public Class<KeyT> getKeyClass() {
        return this.keyClass;
    }

    public Class<PrimitiveT> getPrimitiveClass() {
        return this.primitiveClass;
    }

    private PrimitiveConstructor(Class<KeyT> cls, Class<PrimitiveT> cls2) {
        this.keyClass = cls;
        this.primitiveClass = cls2;
    }
}
