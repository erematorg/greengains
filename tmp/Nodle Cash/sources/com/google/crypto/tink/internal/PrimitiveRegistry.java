package com.google.crypto.tink.internal;

import androidx.constraintlayout.core.state.b;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PrimitiveRegistry {
    /* access modifiers changed from: private */
    public final Map<PrimitiveConstructorIndex, PrimitiveConstructor<?, ?>> primitiveConstructorMap;
    /* access modifiers changed from: private */
    public final Map<Class<?>, PrimitiveWrapper<?, ?>> primitiveWrapperMap;

    public static final class PrimitiveConstructorIndex {
        private final Class<?> keyClass;
        private final Class<?> primitiveClass;

        public boolean equals(Object obj) {
            if (!(obj instanceof PrimitiveConstructorIndex)) {
                return false;
            }
            PrimitiveConstructorIndex primitiveConstructorIndex = (PrimitiveConstructorIndex) obj;
            return primitiveConstructorIndex.keyClass.equals(this.keyClass) && primitiveConstructorIndex.primitiveClass.equals(this.primitiveClass);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.keyClass, this.primitiveClass});
        }

        public String toString() {
            return this.keyClass.getSimpleName() + " with primitive type: " + this.primitiveClass.getSimpleName();
        }

        private PrimitiveConstructorIndex(Class<?> cls, Class<?> cls2) {
            this.keyClass = cls;
            this.primitiveClass = cls2;
        }
    }

    public Class<?> getInputPrimitiveClass(Class<?> cls) throws GeneralSecurityException {
        if (this.primitiveWrapperMap.containsKey(cls)) {
            return this.primitiveWrapperMap.get(cls).getInputPrimitiveClass();
        }
        throw new GeneralSecurityException(b.l("No input primitive class for ", cls, " available"));
    }

    public <KeyT extends Key, PrimitiveT> PrimitiveT getPrimitive(KeyT keyt, Class<PrimitiveT> cls) throws GeneralSecurityException {
        PrimitiveConstructorIndex primitiveConstructorIndex = new PrimitiveConstructorIndex(keyt.getClass(), cls);
        if (this.primitiveConstructorMap.containsKey(primitiveConstructorIndex)) {
            return this.primitiveConstructorMap.get(primitiveConstructorIndex).constructPrimitive(keyt);
        }
        throw new GeneralSecurityException("No PrimitiveConstructor for " + primitiveConstructorIndex + " available");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.crypto.tink.PrimitiveSet<InputPrimitiveT>, com.google.crypto.tink.PrimitiveSet] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <InputPrimitiveT, WrapperPrimitiveT> WrapperPrimitiveT wrap(com.google.crypto.tink.PrimitiveSet<InputPrimitiveT> r2, java.lang.Class<WrapperPrimitiveT> r3) throws java.security.GeneralSecurityException {
        /*
            r1 = this;
            java.util.Map<java.lang.Class<?>, com.google.crypto.tink.PrimitiveWrapper<?, ?>> r0 = r1.primitiveWrapperMap
            boolean r0 = r0.containsKey(r3)
            if (r0 == 0) goto L_0x0039
            java.util.Map<java.lang.Class<?>, com.google.crypto.tink.PrimitiveWrapper<?, ?>> r1 = r1.primitiveWrapperMap
            java.lang.Object r1 = r1.get(r3)
            com.google.crypto.tink.PrimitiveWrapper r1 = (com.google.crypto.tink.PrimitiveWrapper) r1
            java.lang.Class r3 = r2.getPrimitiveClass()
            java.lang.Class r0 = r1.getInputPrimitiveClass()
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0031
            java.lang.Class r3 = r1.getInputPrimitiveClass()
            java.lang.Class r0 = r2.getPrimitiveClass()
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0031
            java.lang.Object r1 = r1.wrap(r2)
            return r1
        L_0x0031:
            java.security.GeneralSecurityException r1 = new java.security.GeneralSecurityException
            java.lang.String r2 = "Input primitive type of the wrapper doesn't match the type of primitives in the provided PrimitiveSet"
            r1.<init>(r2)
            throw r1
        L_0x0039:
            java.security.GeneralSecurityException r1 = new java.security.GeneralSecurityException
            java.lang.String r2 = "No wrapper found for "
            java.lang.String r2 = androidx.constraintlayout.core.state.b.k(r2, r3)
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.internal.PrimitiveRegistry.wrap(com.google.crypto.tink.PrimitiveSet, java.lang.Class):java.lang.Object");
    }

    private PrimitiveRegistry(Builder builder) {
        this.primitiveConstructorMap = new HashMap(builder.primitiveConstructorMap);
        this.primitiveWrapperMap = new HashMap(builder.primitiveWrapperMap);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public final Map<PrimitiveConstructorIndex, PrimitiveConstructor<?, ?>> primitiveConstructorMap;
        /* access modifiers changed from: private */
        public final Map<Class<?>, PrimitiveWrapper<?, ?>> primitiveWrapperMap;

        public Builder() {
            this.primitiveConstructorMap = new HashMap();
            this.primitiveWrapperMap = new HashMap();
        }

        public PrimitiveRegistry build() {
            return new PrimitiveRegistry(this);
        }

        @CanIgnoreReturnValue
        public <KeyT extends Key, PrimitiveT> Builder registerPrimitiveConstructor(PrimitiveConstructor<KeyT, PrimitiveT> primitiveConstructor) throws GeneralSecurityException {
            if (primitiveConstructor != null) {
                PrimitiveConstructorIndex primitiveConstructorIndex = new PrimitiveConstructorIndex(primitiveConstructor.getKeyClass(), primitiveConstructor.getPrimitiveClass());
                if (this.primitiveConstructorMap.containsKey(primitiveConstructorIndex)) {
                    PrimitiveConstructor primitiveConstructor2 = this.primitiveConstructorMap.get(primitiveConstructorIndex);
                    if (!primitiveConstructor2.equals(primitiveConstructor) || !primitiveConstructor.equals(primitiveConstructor2)) {
                        throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: " + primitiveConstructorIndex);
                    }
                } else {
                    this.primitiveConstructorMap.put(primitiveConstructorIndex, primitiveConstructor);
                }
                return this;
            }
            throw new NullPointerException("primitive constructor must be non-null");
        }

        @CanIgnoreReturnValue
        public <InputPrimitiveT, WrapperPrimitiveT> Builder registerPrimitiveWrapper(PrimitiveWrapper<InputPrimitiveT, WrapperPrimitiveT> primitiveWrapper) throws GeneralSecurityException {
            if (primitiveWrapper != null) {
                Class<WrapperPrimitiveT> primitiveClass = primitiveWrapper.getPrimitiveClass();
                if (this.primitiveWrapperMap.containsKey(primitiveClass)) {
                    PrimitiveWrapper primitiveWrapper2 = this.primitiveWrapperMap.get(primitiveClass);
                    if (!primitiveWrapper2.equals(primitiveWrapper) || !primitiveWrapper.equals(primitiveWrapper2)) {
                        throw new GeneralSecurityException(b.k("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type", primitiveClass));
                    }
                } else {
                    this.primitiveWrapperMap.put(primitiveClass, primitiveWrapper);
                }
                return this;
            }
            throw new NullPointerException("wrapper must be non-null");
        }

        public Builder(PrimitiveRegistry primitiveRegistry) {
            this.primitiveConstructorMap = new HashMap(primitiveRegistry.primitiveConstructorMap);
            this.primitiveWrapperMap = new HashMap(primitiveRegistry.primitiveWrapperMap);
        }
    }
}
