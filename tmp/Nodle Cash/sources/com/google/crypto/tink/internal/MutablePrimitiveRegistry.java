package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.internal.PrimitiveRegistry;
import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;

public final class MutablePrimitiveRegistry {
    private static MutablePrimitiveRegistry globalInstance = new MutablePrimitiveRegistry();
    private final AtomicReference<PrimitiveRegistry> registry = new AtomicReference<>(new PrimitiveRegistry.Builder().build());

    public static MutablePrimitiveRegistry globalInstance() {
        return globalInstance;
    }

    public static void resetGlobalInstanceTestOnly() {
        globalInstance = new MutablePrimitiveRegistry();
    }

    public <WrapperPrimitiveT> Class<?> getInputPrimitiveClass(Class<WrapperPrimitiveT> cls) throws GeneralSecurityException {
        return this.registry.get().getInputPrimitiveClass(cls);
    }

    public <KeyT extends Key, PrimitiveT> PrimitiveT getPrimitive(KeyT keyt, Class<PrimitiveT> cls) throws GeneralSecurityException {
        return this.registry.get().getPrimitive(keyt, cls);
    }

    public synchronized <KeyT extends Key, PrimitiveT> void registerPrimitiveConstructor(PrimitiveConstructor<KeyT, PrimitiveT> primitiveConstructor) throws GeneralSecurityException {
        this.registry.set(new PrimitiveRegistry.Builder(this.registry.get()).registerPrimitiveConstructor(primitiveConstructor).build());
    }

    public synchronized <InputPrimitiveT, WrapperPrimitiveT> void registerPrimitiveWrapper(PrimitiveWrapper<InputPrimitiveT, WrapperPrimitiveT> primitiveWrapper) throws GeneralSecurityException {
        this.registry.set(new PrimitiveRegistry.Builder(this.registry.get()).registerPrimitiveWrapper(primitiveWrapper).build());
    }

    public <InputPrimitiveT, WrapperPrimitiveT> WrapperPrimitiveT wrap(PrimitiveSet<InputPrimitiveT> primitiveSet, Class<WrapperPrimitiveT> cls) throws GeneralSecurityException {
        return this.registry.get().wrap(primitiveSet, cls);
    }
}
