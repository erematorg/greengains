package com.fasterxml.jackson.databind;

import A.a;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamWriteFeature;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public class ObjectWriter implements Versioned, Serializable {
    protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
    private static final long serialVersionUID = 1;
    protected final SerializationConfig _config;
    protected final JsonFactory _generatorFactory;
    protected final GeneratorSettings _generatorSettings;
    protected final Prefetch _prefetch;
    protected final SerializerFactory _serializerFactory;
    protected final DefaultSerializerProvider _serializerProvider;

    public static final class Prefetch implements Serializable {
        public static final Prefetch empty = new Prefetch((JavaType) null, (JsonSerializer<Object>) null, (TypeSerializer) null);
        private static final long serialVersionUID = 1;
        private final JavaType rootType;
        private final TypeSerializer typeSerializer;
        private final JsonSerializer<Object> valueSerializer;

        private Prefetch(JavaType javaType, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer2) {
            this.rootType = javaType;
            this.valueSerializer = jsonSerializer;
            this.typeSerializer = typeSerializer2;
        }

        public Prefetch forRootType(ObjectWriter objectWriter, JavaType javaType) {
            if (javaType == null) {
                return (this.rootType == null || this.valueSerializer == null) ? this : new Prefetch((JavaType) null, (JsonSerializer<Object>) null, (TypeSerializer) null);
            }
            if (javaType.equals(this.rootType)) {
                return this;
            }
            if (javaType.isJavaLangObject()) {
                try {
                    return new Prefetch((JavaType) null, (JsonSerializer<Object>) null, objectWriter._serializerProvider().findTypeSerializer(javaType));
                } catch (JsonMappingException e3) {
                    throw new RuntimeJsonMappingException(e3);
                }
            } else {
                if (objectWriter.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH)) {
                    try {
                        JsonSerializer<Object> findTypedValueSerializer = objectWriter._serializerProvider().findTypedValueSerializer(javaType, true, (BeanProperty) null);
                        return findTypedValueSerializer instanceof TypeWrappedSerializer ? new Prefetch(javaType, (JsonSerializer<Object>) null, ((TypeWrappedSerializer) findTypedValueSerializer).typeSerializer()) : new Prefetch(javaType, findTypedValueSerializer, (TypeSerializer) null);
                    } catch (DatabindException unused) {
                    }
                }
                return new Prefetch(javaType, (JsonSerializer<Object>) null, this.typeSerializer);
            }
        }

        public final TypeSerializer getTypeSerializer() {
            return this.typeSerializer;
        }

        public final JsonSerializer<Object> getValueSerializer() {
            return this.valueSerializer;
        }

        public boolean hasSerializer() {
            return (this.valueSerializer == null && this.typeSerializer == null) ? false : true;
        }

        public void serialize(JsonGenerator jsonGenerator, Object obj, DefaultSerializerProvider defaultSerializerProvider) throws IOException {
            TypeSerializer typeSerializer2 = this.typeSerializer;
            if (typeSerializer2 != null) {
                defaultSerializerProvider.serializePolymorphic(jsonGenerator, obj, this.rootType, this.valueSerializer, typeSerializer2);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this.valueSerializer;
            if (jsonSerializer != null) {
                defaultSerializerProvider.serializeValue(jsonGenerator, obj, this.rootType, jsonSerializer);
                return;
            }
            JavaType javaType = this.rootType;
            if (javaType != null) {
                defaultSerializerProvider.serializeValue(jsonGenerator, obj, javaType);
            } else {
                defaultSerializerProvider.serializeValue(jsonGenerator, obj);
            }
        }
    }

    public ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter) {
        this._config = serializationConfig;
        this._serializerProvider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._generatorFactory = objectMapper._jsonFactory;
        this._generatorSettings = prettyPrinter == null ? GeneratorSettings.empty : new GeneratorSettings(prettyPrinter, (FormatSchema) null, (CharacterEscapes) null, (SerializableString) null);
        if (javaType == null) {
            this._prefetch = Prefetch.empty;
        } else if (javaType.hasRawClass(Object.class)) {
            this._prefetch = Prefetch.empty.forRootType(this, javaType);
        } else {
            this._prefetch = Prefetch.empty.forRootType(this, javaType.withStaticTyping());
        }
    }

    private final void _writeCloseable(JsonGenerator jsonGenerator, Object obj) throws IOException {
        Closeable closeable = (Closeable) obj;
        try {
            this._prefetch.serialize(jsonGenerator, obj, _serializerProvider());
            try {
                closeable.close();
                jsonGenerator.close();
            } catch (Exception e3) {
                e = e3;
                closeable = null;
                ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, closeable, e);
            }
        } catch (Exception e4) {
            e = e4;
            ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, closeable, e);
        }
    }

    public final void _assertNotNull(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException(a.l("argument \"", str, "\" is null"));
        }
    }

    public final JsonGenerator _configureGenerator(JsonGenerator jsonGenerator) {
        this._config.initialize(jsonGenerator);
        this._generatorSettings.initialize(jsonGenerator);
        return jsonGenerator;
    }

    public ObjectWriter _new(ObjectWriter objectWriter, JsonFactory jsonFactory) {
        return new ObjectWriter(objectWriter, jsonFactory);
    }

    public SequenceWriter _newSequenceWriter(boolean z2, JsonGenerator jsonGenerator, boolean z3) throws IOException {
        return new SequenceWriter(_serializerProvider(), _configureGenerator(jsonGenerator), z3, this._prefetch).init(z2);
    }

    public DefaultSerializerProvider _serializerProvider() {
        return this._serializerProvider.createInstance(this._config, this._serializerFactory);
    }

    public void _verifySchemaType(FormatSchema formatSchema) {
        if (formatSchema != null && !this._generatorFactory.canUseSchema(formatSchema)) {
            throw new IllegalArgumentException("Cannot use FormatSchema of type " + formatSchema.getClass().getName() + " for format " + this._generatorFactory.getFormatName());
        }
    }

    public final void _writeValueAndClose(JsonGenerator jsonGenerator, Object obj) throws IOException {
        if (!this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
            try {
                this._prefetch.serialize(jsonGenerator, obj, _serializerProvider());
                jsonGenerator.close();
            } catch (Exception e3) {
                ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, e3);
            }
        } else {
            _writeCloseable(jsonGenerator, obj);
        }
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        _assertNotNull("type", javaType);
        _assertNotNull("visitor", jsonFormatVisitorWrapper);
        _serializerProvider().acceptJsonFormatVisitor(javaType, jsonFormatVisitorWrapper);
    }

    public boolean canSerialize(Class<?> cls) {
        _assertNotNull("type", cls);
        return _serializerProvider().hasSerializerFor(cls, (AtomicReference<Throwable>) null);
    }

    public JsonGenerator createGenerator(OutputStream outputStream) throws IOException {
        _assertNotNull("out", outputStream);
        return _configureGenerator(this._generatorFactory.createGenerator(outputStream, JsonEncoding.UTF8));
    }

    public ObjectWriter forType(JavaType javaType) {
        return _new(this._generatorSettings, this._prefetch.forRootType(this, javaType));
    }

    public ContextAttributes getAttributes() {
        return this._config.getAttributes();
    }

    public SerializationConfig getConfig() {
        return this._config;
    }

    public JsonFactory getFactory() {
        return this._generatorFactory;
    }

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public boolean hasPrefetchedSerializer() {
        return this._prefetch.hasSerializer();
    }

    public boolean isEnabled(SerializationFeature serializationFeature) {
        return this._config.isEnabled(serializationFeature);
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public ObjectWriter with(SerializationFeature serializationFeature) {
        return _new(this, this._config.with(serializationFeature));
    }

    public ObjectWriter withAttribute(Object obj, Object obj2) {
        return _new(this, (SerializationConfig) this._config.withAttribute(obj, obj2));
    }

    public ObjectWriter withAttributes(Map<?, ?> map) {
        return _new(this, (SerializationConfig) this._config.withAttributes(map));
    }

    public ObjectWriter withDefaultPrettyPrinter() {
        return with(this._config.getDefaultPrettyPrinter());
    }

    public ObjectWriter withFeatures(SerializationFeature... serializationFeatureArr) {
        return _new(this, this._config.withFeatures(serializationFeatureArr));
    }

    public ObjectWriter withRootName(String str) {
        return _new(this, (SerializationConfig) this._config.withRootName(str));
    }

    public ObjectWriter withRootValueSeparator(String str) {
        return _new(this._generatorSettings.withRootValueSeparator(str), this._prefetch);
    }

    @Deprecated
    public ObjectWriter withSchema(FormatSchema formatSchema) {
        return with(formatSchema);
    }

    @Deprecated
    public ObjectWriter withType(JavaType javaType) {
        return forType(javaType);
    }

    public ObjectWriter withView(Class<?> cls) {
        return _new(this, this._config.withView(cls));
    }

    public ObjectWriter without(SerializationFeature serializationFeature) {
        return _new(this, this._config.without(serializationFeature));
    }

    public ObjectWriter withoutAttribute(Object obj) {
        return _new(this, (SerializationConfig) this._config.withoutAttribute(obj));
    }

    public ObjectWriter withoutFeatures(SerializationFeature... serializationFeatureArr) {
        return _new(this, this._config.withoutFeatures(serializationFeatureArr));
    }

    public ObjectWriter withoutRootName() {
        return _new(this, this._config.withRootName(PropertyName.NO_NAME));
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException {
        _assertNotNull("g", jsonGenerator);
        _configureGenerator(jsonGenerator);
        if (!this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
            this._prefetch.serialize(jsonGenerator, obj, _serializerProvider());
            if (this._config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
                return;
            }
            return;
        }
        Closeable closeable = (Closeable) obj;
        try {
            this._prefetch.serialize(jsonGenerator, obj, _serializerProvider());
            if (this._config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            closeable.close();
        } catch (Exception e3) {
            ClassUtil.closeOnFailAndThrowAsIOE((JsonGenerator) null, closeable, e3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] writeValueAsBytes(java.lang.Object r3) throws com.fasterxml.jackson.core.JsonProcessingException {
        /*
            r2 = this;
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = new com.fasterxml.jackson.core.util.ByteArrayBuilder     // Catch:{ JsonProcessingException -> 0x0021, IOException -> 0x001f }
            com.fasterxml.jackson.core.JsonFactory r1 = r2._generatorFactory     // Catch:{ JsonProcessingException -> 0x0021, IOException -> 0x001f }
            com.fasterxml.jackson.core.util.BufferRecycler r1 = r1._getBufferRecycler()     // Catch:{ JsonProcessingException -> 0x0021, IOException -> 0x001f }
            r0.<init>((com.fasterxml.jackson.core.util.BufferRecycler) r1)     // Catch:{ JsonProcessingException -> 0x0021, IOException -> 0x001f }
            com.fasterxml.jackson.core.JsonEncoding r1 = com.fasterxml.jackson.core.JsonEncoding.UTF8     // Catch:{ all -> 0x0023 }
            com.fasterxml.jackson.core.JsonGenerator r1 = r2.createGenerator((java.io.OutputStream) r0, (com.fasterxml.jackson.core.JsonEncoding) r1)     // Catch:{ all -> 0x0023 }
            r2._writeValueAndClose(r1, r3)     // Catch:{ all -> 0x0023 }
            byte[] r2 = r0.toByteArray()     // Catch:{ all -> 0x0023 }
            r0.release()     // Catch:{ all -> 0x0023 }
            r0.close()     // Catch:{ JsonProcessingException -> 0x0021, IOException -> 0x001f }
            return r2
        L_0x001f:
            r2 = move-exception
            goto L_0x002f
        L_0x0021:
            r2 = move-exception
            goto L_0x0034
        L_0x0023:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ JsonProcessingException -> 0x0021, IOException -> 0x001f }
        L_0x002e:
            throw r3     // Catch:{ JsonProcessingException -> 0x0021, IOException -> 0x001f }
        L_0x002f:
            com.fasterxml.jackson.databind.JsonMappingException r2 = com.fasterxml.jackson.databind.JsonMappingException.fromUnexpectedIOE(r2)
            throw r2
        L_0x0034:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ObjectWriter.writeValueAsBytes(java.lang.Object):byte[]");
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(this._generatorFactory._getBufferRecycler());
        try {
            _writeValueAndClose(createGenerator((Writer) segmentedStringWriter), obj);
            return segmentedStringWriter.getAndClear();
        } catch (JsonProcessingException e3) {
            throw e3;
        } catch (IOException e4) {
            throw JsonMappingException.fromUnexpectedIOE(e4);
        }
    }

    public SequenceWriter writeValues(File file) throws IOException {
        return _newSequenceWriter(false, createGenerator(file, JsonEncoding.UTF8), true);
    }

    public SequenceWriter writeValuesAsArray(File file) throws IOException {
        return _newSequenceWriter(true, createGenerator(file, JsonEncoding.UTF8), true);
    }

    public static final class GeneratorSettings implements Serializable {
        public static final GeneratorSettings empty = new GeneratorSettings((PrettyPrinter) null, (FormatSchema) null, (CharacterEscapes) null, (SerializableString) null);
        private static final long serialVersionUID = 1;
        public final CharacterEscapes characterEscapes;
        public final PrettyPrinter prettyPrinter;
        public final SerializableString rootValueSeparator;
        public final FormatSchema schema;

        public GeneratorSettings(PrettyPrinter prettyPrinter2, FormatSchema formatSchema, CharacterEscapes characterEscapes2, SerializableString serializableString) {
            this.prettyPrinter = prettyPrinter2;
            this.schema = formatSchema;
            this.characterEscapes = characterEscapes2;
            this.rootValueSeparator = serializableString;
        }

        private final String _rootValueSeparatorAsString() {
            SerializableString serializableString = this.rootValueSeparator;
            if (serializableString == null) {
                return null;
            }
            return serializableString.getValue();
        }

        public void initialize(JsonGenerator jsonGenerator) {
            PrettyPrinter prettyPrinter2 = this.prettyPrinter;
            if (prettyPrinter2 != null) {
                if (prettyPrinter2 == ObjectWriter.NULL_PRETTY_PRINTER) {
                    jsonGenerator.setPrettyPrinter((PrettyPrinter) null);
                } else {
                    if (prettyPrinter2 instanceof Instantiatable) {
                        prettyPrinter2 = (PrettyPrinter) ((Instantiatable) prettyPrinter2).createInstance();
                    }
                    jsonGenerator.setPrettyPrinter(prettyPrinter2);
                }
            }
            CharacterEscapes characterEscapes2 = this.characterEscapes;
            if (characterEscapes2 != null) {
                jsonGenerator.setCharacterEscapes(characterEscapes2);
            }
            FormatSchema formatSchema = this.schema;
            if (formatSchema != null) {
                jsonGenerator.setSchema(formatSchema);
            }
            SerializableString serializableString = this.rootValueSeparator;
            if (serializableString != null) {
                jsonGenerator.setRootValueSeparator(serializableString);
            }
        }

        public GeneratorSettings with(PrettyPrinter prettyPrinter2) {
            if (prettyPrinter2 == null) {
                prettyPrinter2 = ObjectWriter.NULL_PRETTY_PRINTER;
            }
            return prettyPrinter2 == this.prettyPrinter ? this : new GeneratorSettings(prettyPrinter2, this.schema, this.characterEscapes, this.rootValueSeparator);
        }

        public GeneratorSettings withRootValueSeparator(String str) {
            if (str == null) {
                if (this.rootValueSeparator == null) {
                    return this;
                }
                return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, (SerializableString) null);
            } else if (str.equals(_rootValueSeparatorAsString())) {
                return this;
            } else {
                return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, new SerializedString(str));
            }
        }

        public GeneratorSettings with(FormatSchema formatSchema) {
            return this.schema == formatSchema ? this : new GeneratorSettings(this.prettyPrinter, formatSchema, this.characterEscapes, this.rootValueSeparator);
        }

        public GeneratorSettings with(CharacterEscapes characterEscapes2) {
            return this.characterEscapes == characterEscapes2 ? this : new GeneratorSettings(this.prettyPrinter, this.schema, characterEscapes2, this.rootValueSeparator);
        }

        public GeneratorSettings withRootValueSeparator(SerializableString serializableString) {
            if (serializableString == null) {
                if (this.rootValueSeparator == null) {
                    return this;
                }
                return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, (SerializableString) null);
            } else if (serializableString.equals(this.rootValueSeparator)) {
                return this;
            } else {
                return new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, serializableString);
            }
        }
    }

    public ObjectWriter _new(ObjectWriter objectWriter, SerializationConfig serializationConfig) {
        if (serializationConfig == this._config) {
            return this;
        }
        return new ObjectWriter(objectWriter, serializationConfig);
    }

    public ObjectWriter forType(Class<?> cls) {
        return forType(this._config.constructType(cls));
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public ObjectWriter with(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        return _new(this, this._config.with(serializationFeature, serializationFeatureArr));
    }

    public ObjectWriter withFeatures(DatatypeFeature... datatypeFeatureArr) {
        return _new(this, (SerializationConfig) this._config.withFeatures(datatypeFeatureArr));
    }

    public ObjectWriter withRootName(PropertyName propertyName) {
        return _new(this, this._config.withRootName(propertyName));
    }

    public ObjectWriter withRootValueSeparator(SerializableString serializableString) {
        return _new(this._generatorSettings.withRootValueSeparator(serializableString), this._prefetch);
    }

    @Deprecated
    public ObjectWriter withType(Class<?> cls) {
        return forType(cls);
    }

    public ObjectWriter without(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        return _new(this, this._config.without(serializationFeature, serializationFeatureArr));
    }

    public ObjectWriter withoutFeatures(DatatypeFeature... datatypeFeatureArr) {
        return _new(this, (SerializationConfig) this._config.withoutFeatures(datatypeFeatureArr));
    }

    public SequenceWriter writeValues(JsonGenerator jsonGenerator) throws IOException {
        _assertNotNull("g", jsonGenerator);
        return _newSequenceWriter(false, _configureGenerator(jsonGenerator), false);
    }

    public SequenceWriter writeValuesAsArray(JsonGenerator jsonGenerator) throws IOException {
        _assertNotNull("gen", jsonGenerator);
        return _newSequenceWriter(true, jsonGenerator, false);
    }

    public boolean canSerialize(Class<?> cls, AtomicReference<Throwable> atomicReference) {
        _assertNotNull("type", cls);
        return _serializerProvider().hasSerializerFor(cls, atomicReference);
    }

    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        _assertNotNull("out", outputStream);
        return _configureGenerator(this._generatorFactory.createGenerator(outputStream, jsonEncoding));
    }

    public ObjectWriter forType(TypeReference<?> typeReference) {
        return forType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public boolean isEnabled(DatatypeFeature datatypeFeature) {
        return this._config.isEnabled(datatypeFeature);
    }

    public ObjectWriter with(DatatypeFeature datatypeFeature) {
        return _new(this, (SerializationConfig) this._config.with(datatypeFeature));
    }

    public ObjectWriter withFeatures(JsonGenerator.Feature... featureArr) {
        return _new(this, this._config.withFeatures(featureArr));
    }

    @Deprecated
    public ObjectWriter withType(TypeReference<?> typeReference) {
        return forType(typeReference);
    }

    public ObjectWriter without(DatatypeFeature datatypeFeature) {
        return _new(this, (SerializationConfig) this._config.without(datatypeFeature));
    }

    public ObjectWriter withoutFeatures(JsonGenerator.Feature... featureArr) {
        return _new(this, this._config.withoutFeatures(featureArr));
    }

    public ObjectWriter _new(GeneratorSettings generatorSettings, Prefetch prefetch) {
        if (this._generatorSettings == generatorSettings && this._prefetch == prefetch) {
            return this;
        }
        return new ObjectWriter(this, this._config, generatorSettings, prefetch);
    }

    public void acceptJsonFormatVisitor(Class<?> cls, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        _assertNotNull("type", cls);
        _assertNotNull("visitor", jsonFormatVisitorWrapper);
        acceptJsonFormatVisitor(this._config.constructType(cls), jsonFormatVisitorWrapper);
    }

    @Deprecated
    public boolean isEnabled(JsonParser.Feature feature) {
        return this._generatorFactory.isEnabled(feature);
    }

    public ObjectWriter with(JsonGenerator.Feature feature) {
        return _new(this, this._config.with(feature));
    }

    public ObjectWriter withFeatures(FormatFeature... formatFeatureArr) {
        return _new(this, this._config.withFeatures(formatFeatureArr));
    }

    public ObjectWriter without(JsonGenerator.Feature feature) {
        return _new(this, this._config.without(feature));
    }

    public ObjectWriter withoutFeatures(FormatFeature... formatFeatureArr) {
        return _new(this, this._config.withoutFeatures(formatFeatureArr));
    }

    public SequenceWriter writeValues(Writer writer) throws IOException {
        return _newSequenceWriter(false, createGenerator(writer), true);
    }

    public SequenceWriter writeValuesAsArray(Writer writer) throws IOException {
        return _newSequenceWriter(true, createGenerator(writer), true);
    }

    public JsonGenerator createGenerator(Writer writer) throws IOException {
        _assertNotNull("w", writer);
        return _configureGenerator(this._generatorFactory.createGenerator(writer));
    }

    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this._generatorFactory.isEnabled(feature);
    }

    public ObjectWriter with(StreamWriteFeature streamWriteFeature) {
        return _new(this, this._config.with(streamWriteFeature.mappedFeature()));
    }

    public ObjectWriter without(StreamWriteFeature streamWriteFeature) {
        return _new(this, this._config.without(streamWriteFeature.mappedFeature()));
    }

    public SequenceWriter writeValues(OutputStream outputStream) throws IOException {
        return _newSequenceWriter(false, createGenerator(outputStream, JsonEncoding.UTF8), true);
    }

    public SequenceWriter writeValuesAsArray(OutputStream outputStream) throws IOException {
        return _newSequenceWriter(true, createGenerator(outputStream, JsonEncoding.UTF8), true);
    }

    public boolean isEnabled(StreamWriteFeature streamWriteFeature) {
        return this._generatorFactory.isEnabled(streamWriteFeature);
    }

    public ObjectWriter with(FormatFeature formatFeature) {
        return _new(this, this._config.with(formatFeature));
    }

    public ObjectWriter without(FormatFeature formatFeature) {
        return _new(this, this._config.without(formatFeature));
    }

    public SequenceWriter writeValues(DataOutput dataOutput) throws IOException {
        return _newSequenceWriter(false, createGenerator(dataOutput), true);
    }

    public SequenceWriter writeValuesAsArray(DataOutput dataOutput) throws IOException {
        return _newSequenceWriter(true, createGenerator(dataOutput), true);
    }

    public JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        _assertNotNull("outputFile", file);
        return _configureGenerator(this._generatorFactory.createGenerator(file, jsonEncoding));
    }

    public ObjectWriter with(DateFormat dateFormat) {
        return _new(this, this._config.with(dateFormat));
    }

    public ObjectWriter with(FilterProvider filterProvider) {
        if (filterProvider == this._config.getFilterProvider()) {
            return this;
        }
        return _new(this, this._config.withFilters(filterProvider));
    }

    public JsonGenerator createGenerator(DataOutput dataOutput) throws IOException {
        _assertNotNull("out", dataOutput);
        return _configureGenerator(this._generatorFactory.createGenerator(dataOutput));
    }

    public ObjectWriter with(PrettyPrinter prettyPrinter) {
        return _new(this._generatorSettings.with(prettyPrinter), this._prefetch);
    }

    public ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._serializerProvider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._generatorFactory = objectMapper._jsonFactory;
        this._generatorSettings = GeneratorSettings.empty;
        this._prefetch = Prefetch.empty;
    }

    public ObjectWriter with(FormatSchema formatSchema) {
        _verifySchemaType(formatSchema);
        return _new(this._generatorSettings.with(formatSchema), this._prefetch);
    }

    public ObjectWriter with(Locale locale) {
        return _new(this, (SerializationConfig) this._config.with(locale));
    }

    public void writeValue(File file, Object obj) throws IOException, StreamWriteException, DatabindException {
        _writeValueAndClose(createGenerator(file, JsonEncoding.UTF8), obj);
    }

    public ObjectWriter with(TimeZone timeZone) {
        return _new(this, (SerializationConfig) this._config.with(timeZone));
    }

    public void writeValue(OutputStream outputStream, Object obj) throws IOException, StreamWriteException, DatabindException {
        _writeValueAndClose(createGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public ObjectWriter with(Base64Variant base64Variant) {
        return _new(this, (SerializationConfig) this._config.with(base64Variant));
    }

    public void writeValue(Writer writer, Object obj) throws IOException, StreamWriteException, DatabindException {
        _writeValueAndClose(createGenerator(writer), obj);
    }

    public ObjectWriter with(CharacterEscapes characterEscapes) {
        return _new(this._generatorSettings.with(characterEscapes), this._prefetch);
    }

    public void writeValue(DataOutput dataOutput, Object obj) throws IOException, StreamWriteException, DatabindException {
        _writeValueAndClose(createGenerator(dataOutput), obj);
    }

    public ObjectWriter with(JsonFactory jsonFactory) {
        return jsonFactory == this._generatorFactory ? this : _new(this, jsonFactory);
    }

    public ObjectWriter(ObjectMapper objectMapper, SerializationConfig serializationConfig, FormatSchema formatSchema) {
        this._config = serializationConfig;
        this._serializerProvider = objectMapper._serializerProvider;
        this._serializerFactory = objectMapper._serializerFactory;
        this._generatorFactory = objectMapper._jsonFactory;
        this._generatorSettings = formatSchema == null ? GeneratorSettings.empty : new GeneratorSettings((PrettyPrinter) null, formatSchema, (CharacterEscapes) null, (SerializableString) null);
        this._prefetch = Prefetch.empty;
    }

    public ObjectWriter with(ContextAttributes contextAttributes) {
        return _new(this, this._config.with(contextAttributes));
    }

    public ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig, GeneratorSettings generatorSettings, Prefetch prefetch) {
        this._config = serializationConfig;
        this._serializerProvider = objectWriter._serializerProvider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._generatorFactory = objectWriter._generatorFactory;
        this._generatorSettings = generatorSettings;
        this._prefetch = prefetch;
    }

    public ObjectWriter(ObjectWriter objectWriter, SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._serializerProvider = objectWriter._serializerProvider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._generatorFactory = objectWriter._generatorFactory;
        this._generatorSettings = objectWriter._generatorSettings;
        this._prefetch = objectWriter._prefetch;
    }

    public ObjectWriter(ObjectWriter objectWriter, JsonFactory jsonFactory) {
        this._config = (SerializationConfig) objectWriter._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, jsonFactory.requiresPropertyOrdering());
        this._serializerProvider = objectWriter._serializerProvider;
        this._serializerFactory = objectWriter._serializerFactory;
        this._generatorFactory = jsonFactory;
        this._generatorSettings = objectWriter._generatorSettings;
        this._prefetch = objectWriter._prefetch;
    }
}
