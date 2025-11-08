package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

public final class SerializationRegistry {
    /* access modifiers changed from: private */
    public final Map<ParserIndex, KeyParser<?>> keyParserMap;
    /* access modifiers changed from: private */
    public final Map<SerializerIndex, KeySerializer<?, ?>> keySerializerMap;
    /* access modifiers changed from: private */
    public final Map<ParserIndex, ParametersParser<?>> parametersParserMap;
    /* access modifiers changed from: private */
    public final Map<SerializerIndex, ParametersSerializer<?, ?>> parametersSerializerMap;

    public static class ParserIndex {
        private final Class<? extends Serialization> keySerializationClass;
        private final Bytes serializationIdentifier;

        public boolean equals(Object obj) {
            if (!(obj instanceof ParserIndex)) {
                return false;
            }
            ParserIndex parserIndex = (ParserIndex) obj;
            return parserIndex.keySerializationClass.equals(this.keySerializationClass) && parserIndex.serializationIdentifier.equals(this.serializationIdentifier);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.keySerializationClass, this.serializationIdentifier});
        }

        public String toString() {
            return this.keySerializationClass.getSimpleName() + ", object identifier: " + this.serializationIdentifier;
        }

        private ParserIndex(Class<? extends Serialization> cls, Bytes bytes) {
            this.keySerializationClass = cls;
            this.serializationIdentifier = bytes;
        }
    }

    public static class SerializerIndex {
        private final Class<?> keyClass;
        private final Class<? extends Serialization> keySerializationClass;

        public boolean equals(Object obj) {
            if (!(obj instanceof SerializerIndex)) {
                return false;
            }
            SerializerIndex serializerIndex = (SerializerIndex) obj;
            return serializerIndex.keyClass.equals(this.keyClass) && serializerIndex.keySerializationClass.equals(this.keySerializationClass);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.keyClass, this.keySerializationClass});
        }

        public String toString() {
            return this.keyClass.getSimpleName() + " with serialization type: " + this.keySerializationClass.getSimpleName();
        }

        private SerializerIndex(Class<?> cls, Class<? extends Serialization> cls2) {
            this.keyClass = cls;
            this.keySerializationClass = cls2;
        }
    }

    public <SerializationT extends Serialization> boolean hasParserForKey(SerializationT serializationt) {
        return this.keyParserMap.containsKey(new ParserIndex(serializationt.getClass(), serializationt.getObjectIdentifier()));
    }

    public <SerializationT extends Serialization> boolean hasParserForParameters(SerializationT serializationt) {
        return this.parametersParserMap.containsKey(new ParserIndex(serializationt.getClass(), serializationt.getObjectIdentifier()));
    }

    public <KeyT extends Key, SerializationT extends Serialization> boolean hasSerializerForKey(KeyT keyt, Class<SerializationT> cls) {
        return this.keySerializerMap.containsKey(new SerializerIndex(keyt.getClass(), cls));
    }

    public <ParametersT extends Parameters, SerializationT extends Serialization> boolean hasSerializerForParameters(ParametersT parameterst, Class<SerializationT> cls) {
        return this.parametersSerializerMap.containsKey(new SerializerIndex(parameterst.getClass(), cls));
    }

    public <SerializationT extends Serialization> Key parseKey(SerializationT serializationt, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        ParserIndex parserIndex = new ParserIndex(serializationt.getClass(), serializationt.getObjectIdentifier());
        if (this.keyParserMap.containsKey(parserIndex)) {
            return this.keyParserMap.get(parserIndex).parseKey(serializationt, secretKeyAccess);
        }
        throw new GeneralSecurityException("No Key Parser for requested key type " + parserIndex + " available");
    }

    public <SerializationT extends Serialization> Parameters parseParameters(SerializationT serializationt) throws GeneralSecurityException {
        ParserIndex parserIndex = new ParserIndex(serializationt.getClass(), serializationt.getObjectIdentifier());
        if (this.parametersParserMap.containsKey(parserIndex)) {
            return this.parametersParserMap.get(parserIndex).parseParameters(serializationt);
        }
        throw new GeneralSecurityException("No Parameters Parser for requested key type " + parserIndex + " available");
    }

    public <KeyT extends Key, SerializationT extends Serialization> SerializationT serializeKey(KeyT keyt, Class<SerializationT> cls, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        SerializerIndex serializerIndex = new SerializerIndex(keyt.getClass(), cls);
        if (this.keySerializerMap.containsKey(serializerIndex)) {
            return this.keySerializerMap.get(serializerIndex).serializeKey(keyt, secretKeyAccess);
        }
        throw new GeneralSecurityException("No Key serializer for " + serializerIndex + " available");
    }

    public <ParametersT extends Parameters, SerializationT extends Serialization> SerializationT serializeParameters(ParametersT parameterst, Class<SerializationT> cls) throws GeneralSecurityException {
        SerializerIndex serializerIndex = new SerializerIndex(parameterst.getClass(), cls);
        if (this.parametersSerializerMap.containsKey(serializerIndex)) {
            return this.parametersSerializerMap.get(serializerIndex).serializeParameters(parameterst);
        }
        throw new GeneralSecurityException("No Key Format serializer for " + serializerIndex + " available");
    }

    private SerializationRegistry(Builder builder) {
        this.keySerializerMap = new HashMap(builder.keySerializerMap);
        this.keyParserMap = new HashMap(builder.keyParserMap);
        this.parametersSerializerMap = new HashMap(builder.parametersSerializerMap);
        this.parametersParserMap = new HashMap(builder.parametersParserMap);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public final Map<ParserIndex, KeyParser<?>> keyParserMap;
        /* access modifiers changed from: private */
        public final Map<SerializerIndex, KeySerializer<?, ?>> keySerializerMap;
        /* access modifiers changed from: private */
        public final Map<ParserIndex, ParametersParser<?>> parametersParserMap;
        /* access modifiers changed from: private */
        public final Map<SerializerIndex, ParametersSerializer<?, ?>> parametersSerializerMap;

        public Builder() {
            this.keySerializerMap = new HashMap();
            this.keyParserMap = new HashMap();
            this.parametersSerializerMap = new HashMap();
            this.parametersParserMap = new HashMap();
        }

        public SerializationRegistry build() {
            return new SerializationRegistry(this);
        }

        @CanIgnoreReturnValue
        public <SerializationT extends Serialization> Builder registerKeyParser(KeyParser<SerializationT> keyParser) throws GeneralSecurityException {
            ParserIndex parserIndex = new ParserIndex(keyParser.getSerializationClass(), keyParser.getObjectIdentifier());
            if (this.keyParserMap.containsKey(parserIndex)) {
                KeyParser keyParser2 = this.keyParserMap.get(parserIndex);
                if (!keyParser2.equals(keyParser) || !keyParser.equals(keyParser2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + parserIndex);
                }
            } else {
                this.keyParserMap.put(parserIndex, keyParser);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public <KeyT extends Key, SerializationT extends Serialization> Builder registerKeySerializer(KeySerializer<KeyT, SerializationT> keySerializer) throws GeneralSecurityException {
            SerializerIndex serializerIndex = new SerializerIndex(keySerializer.getKeyClass(), keySerializer.getSerializationClass());
            if (this.keySerializerMap.containsKey(serializerIndex)) {
                KeySerializer keySerializer2 = this.keySerializerMap.get(serializerIndex);
                if (!keySerializer2.equals(keySerializer) || !keySerializer.equals(keySerializer2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + serializerIndex);
                }
            } else {
                this.keySerializerMap.put(serializerIndex, keySerializer);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public <SerializationT extends Serialization> Builder registerParametersParser(ParametersParser<SerializationT> parametersParser) throws GeneralSecurityException {
            ParserIndex parserIndex = new ParserIndex(parametersParser.getSerializationClass(), parametersParser.getObjectIdentifier());
            if (this.parametersParserMap.containsKey(parserIndex)) {
                ParametersParser parametersParser2 = this.parametersParserMap.get(parserIndex);
                if (!parametersParser2.equals(parametersParser) || !parametersParser.equals(parametersParser2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + parserIndex);
                }
            } else {
                this.parametersParserMap.put(parserIndex, parametersParser);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public <ParametersT extends Parameters, SerializationT extends Serialization> Builder registerParametersSerializer(ParametersSerializer<ParametersT, SerializationT> parametersSerializer) throws GeneralSecurityException {
            SerializerIndex serializerIndex = new SerializerIndex(parametersSerializer.getParametersClass(), parametersSerializer.getSerializationClass());
            if (this.parametersSerializerMap.containsKey(serializerIndex)) {
                ParametersSerializer parametersSerializer2 = this.parametersSerializerMap.get(serializerIndex);
                if (!parametersSerializer2.equals(parametersSerializer) || !parametersSerializer.equals(parametersSerializer2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + serializerIndex);
                }
            } else {
                this.parametersSerializerMap.put(serializerIndex, parametersSerializer);
            }
            return this;
        }

        public Builder(SerializationRegistry serializationRegistry) {
            this.keySerializerMap = new HashMap(serializationRegistry.keySerializerMap);
            this.keyParserMap = new HashMap(serializationRegistry.keyParserMap);
            this.parametersSerializerMap = new HashMap(serializationRegistry.parametersSerializerMap);
            this.parametersParserMap = new HashMap(serializationRegistry.parametersParserMap);
        }
    }
}
