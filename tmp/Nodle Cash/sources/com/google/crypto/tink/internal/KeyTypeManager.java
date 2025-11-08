package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Alpha
public abstract class KeyTypeManager<KeyProtoT extends MessageLite> {
    private final Class<KeyProtoT> clazz;
    private final Map<Class<?>, PrimitiveFactory<?, KeyProtoT>> factories;
    private final Class<?> firstPrimitiveClass;

    public static abstract class KeyFactory<KeyFormatProtoT extends MessageLite, KeyProtoT extends MessageLite> {
        private final Class<KeyFormatProtoT> clazz;

        public static final class KeyFormat<KeyFormatProtoT> {
            public KeyFormatProtoT keyFormat;
            public KeyTemplate.OutputPrefixType outputPrefixType;

            public KeyFormat(KeyFormatProtoT keyformatprotot, KeyTemplate.OutputPrefixType outputPrefixType2) {
                this.keyFormat = keyformatprotot;
                this.outputPrefixType = outputPrefixType2;
            }
        }

        public KeyFactory(Class<KeyFormatProtoT> cls) {
            this.clazz = cls;
        }

        public static void readFully(InputStream inputStream, byte[] bArr) throws IOException, GeneralSecurityException {
            int length = bArr.length;
            int i3 = 0;
            while (i3 < length) {
                int read = inputStream.read(bArr, i3, length - i3);
                if (read != -1) {
                    i3 += read;
                } else {
                    throw new GeneralSecurityException("Not enough pseudorandomness provided");
                }
            }
        }

        public abstract KeyProtoT createKey(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;

        public KeyProtoT deriveKey(KeyFormatProtoT keyformatprotot, InputStream inputStream) throws GeneralSecurityException {
            throw new GeneralSecurityException("deriveKey not implemented for key of type " + this.clazz);
        }

        public final Class<KeyFormatProtoT> getKeyFormatClass() {
            return this.clazz;
        }

        public Map<String, KeyFormat<KeyFormatProtoT>> keyFormats() throws GeneralSecurityException {
            return Collections.emptyMap();
        }

        public abstract KeyFormatProtoT parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException;

        public abstract void validateKeyFormat(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;
    }

    @SafeVarargs
    public KeyTypeManager(Class<KeyProtoT> cls, PrimitiveFactory<?, KeyProtoT>... primitiveFactoryArr) {
        this.clazz = cls;
        HashMap hashMap = new HashMap();
        int length = primitiveFactoryArr.length;
        int i3 = 0;
        while (i3 < length) {
            PrimitiveFactory<?, KeyProtoT> primitiveFactory = primitiveFactoryArr[i3];
            if (!hashMap.containsKey(primitiveFactory.getPrimitiveClass())) {
                hashMap.put(primitiveFactory.getPrimitiveClass(), primitiveFactory);
                i3++;
            } else {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive " + primitiveFactory.getPrimitiveClass().getCanonicalName());
            }
        }
        if (primitiveFactoryArr.length > 0) {
            this.firstPrimitiveClass = primitiveFactoryArr[0].getPrimitiveClass();
        } else {
            this.firstPrimitiveClass = Void.class;
        }
        this.factories = Collections.unmodifiableMap(hashMap);
    }

    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public final Class<?> firstSupportedPrimitiveClass() {
        return this.firstPrimitiveClass;
    }

    public final Class<KeyProtoT> getKeyClass() {
        return this.clazz;
    }

    public abstract String getKeyType();

    public final <P> P getPrimitive(KeyProtoT keyprotot, Class<P> cls) throws GeneralSecurityException {
        PrimitiveFactory primitiveFactory = this.factories.get(cls);
        if (primitiveFactory != null) {
            return primitiveFactory.getPrimitive(keyprotot);
        }
        throw new IllegalArgumentException("Requested primitive class " + cls.getCanonicalName() + " not supported.");
    }

    public abstract int getVersion();

    public KeyFactory<?, KeyProtoT> keyFactory() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract KeyData.KeyMaterialType keyMaterialType();

    public abstract KeyProtoT parseKey(ByteString byteString) throws InvalidProtocolBufferException;

    public final Set<Class<?>> supportedPrimitives() {
        return this.factories.keySet();
    }

    public abstract void validateKey(KeyProtoT keyprotot) throws GeneralSecurityException;
}
