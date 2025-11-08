package com.fasterxml.jackson.databind;

import A.a;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.StreamWriteFeature;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.CoercionConfigs;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.MutableCoercionConfig;
import com.fasterxml.jackson.databind.cfg.MutableConfigOverride;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.text.lookup.StringLookupFactory;

public class ObjectMapper extends ObjectCodec implements Versioned, Serializable {
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR;
    protected static final BaseSettings DEFAULT_BASE;
    private static final long serialVersionUID = 2;
    protected final CoercionConfigs _coercionConfigs;
    protected final ConfigOverrides _configOverrides;
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected SimpleMixInResolver _mixIns;
    protected Set<Object> _registeredModuleTypes;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected DefaultSerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    /* renamed from: com.fasterxml.jackson.databind.ObjectMapper$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.fasterxml.jackson.databind.ObjectMapper$DefaultTyping[] r0 = com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping = r0
                com.fasterxml.jackson.databind.ObjectMapper$DefaultTyping r1 = com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.databind.ObjectMapper$DefaultTyping r1 = com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.databind.ObjectMapper$DefaultTyping r1 = com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.NON_FINAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.databind.ObjectMapper$DefaultTyping r1 = com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.EVERYTHING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping     // Catch:{ NoSuchFieldError -> 0x003e }
                com.fasterxml.jackson.databind.ObjectMapper$DefaultTyping r1 = com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ObjectMapper.AnonymousClass3.<clinit>():void");
        }
    }

    public static class DefaultTypeResolverBuilder extends StdTypeResolverBuilder implements Serializable {
        private static final long serialVersionUID = 1;
        protected final DefaultTyping _appliesFor;
        protected final PolymorphicTypeValidator _subtypeValidator;

        @Deprecated
        public DefaultTypeResolverBuilder(DefaultTyping defaultTyping) {
            this(defaultTyping, (PolymorphicTypeValidator) LaissezFaireSubTypeValidator.instance);
        }

        private static <T> T _requireNonNull(T t2, String str) {
            if (t2 != null) {
                return t2;
            }
            throw new NullPointerException(str);
        }

        public static DefaultTypeResolverBuilder construct(DefaultTyping defaultTyping, PolymorphicTypeValidator polymorphicTypeValidator) {
            return new DefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator);
        }

        public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection) {
            if (useForType(javaType)) {
                return super.buildTypeDeserializer(deserializationConfig, javaType, collection);
            }
            return null;
        }

        public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection) {
            if (useForType(javaType)) {
                return super.buildTypeSerializer(serializationConfig, javaType, collection);
            }
            return null;
        }

        public PolymorphicTypeValidator subTypeValidator(MapperConfig<?> mapperConfig) {
            return this._subtypeValidator;
        }

        public boolean useForType(JavaType javaType) {
            if (javaType.isPrimitive()) {
                return false;
            }
            int i3 = AnonymousClass3.$SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[this._appliesFor.ordinal()];
            Class<TreeNode> cls = TreeNode.class;
            if (i3 == 1) {
                while (javaType.isArrayType()) {
                    javaType = javaType.getContentType();
                }
            } else if (i3 != 2) {
                if (i3 == 3) {
                    while (javaType.isArrayType()) {
                        javaType = javaType.getContentType();
                    }
                    while (javaType.isReferenceType()) {
                        javaType = javaType.getReferencedType();
                    }
                    return !javaType.isFinal() && !cls.isAssignableFrom(javaType.getRawClass());
                } else if (i3 != 4) {
                    return javaType.isJavaLangObject();
                } else {
                    return true;
                }
            }
            while (javaType.isReferenceType()) {
                javaType = javaType.getReferencedType();
            }
            return javaType.isJavaLangObject() || (!javaType.isConcrete() && !cls.isAssignableFrom(javaType.getRawClass()));
        }

        public DefaultTypeResolverBuilder(DefaultTyping defaultTyping, PolymorphicTypeValidator polymorphicTypeValidator) {
            this._appliesFor = (DefaultTyping) _requireNonNull(defaultTyping, "Can not pass `null` DefaultTyping");
            this._subtypeValidator = (PolymorphicTypeValidator) _requireNonNull(polymorphicTypeValidator, "Can not pass `null` PolymorphicTypeValidator");
        }

        public DefaultTypeResolverBuilder withDefaultImpl(Class<?> cls) {
            if (this._defaultImpl == cls) {
                return this;
            }
            ClassUtil.verifyMustOverride(DefaultTypeResolverBuilder.class, this, "withDefaultImpl");
            return new DefaultTypeResolverBuilder(this, cls);
        }

        public DefaultTypeResolverBuilder(DefaultTypeResolverBuilder defaultTypeResolverBuilder, Class<?> cls) {
            super(defaultTypeResolverBuilder, cls);
            this._appliesFor = defaultTypeResolverBuilder._appliesFor;
            this._subtypeValidator = defaultTypeResolverBuilder._subtypeValidator;
        }
    }

    public enum DefaultTyping {
        JAVA_LANG_OBJECT,
        OBJECT_AND_NON_CONCRETE,
        NON_CONCRETE_AND_ARRAYS,
        NON_FINAL,
        EVERYTHING
    }

    static {
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector = new JacksonAnnotationIntrospector();
        DEFAULT_ANNOTATION_INTROSPECTOR = jacksonAnnotationIntrospector;
        DEFAULT_BASE = new BaseSettings((ClassIntrospector) null, jacksonAnnotationIntrospector, (PropertyNamingStrategy) null, TypeFactory.defaultInstance(), (TypeResolverBuilder<?>) null, StdDateFormat.instance, (HandlerInstantiator) null, Locale.getDefault(), (TimeZone) null, Base64Variants.getDefaultVariant(), LaissezFaireSubTypeValidator.instance, new DefaultAccessorNamingStrategy.Provider());
    }

    public ObjectMapper() {
        this((JsonFactory) null, (DefaultSerializerProvider) null, (DefaultDeserializationContext) null);
    }

    private final void _writeCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException {
        Closeable closeable = (Closeable) obj;
        try {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
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

    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException {
        Closeable closeable = (Closeable) obj;
        try {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
            if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            closeable.close();
        } catch (Exception e3) {
            ClassUtil.closeOnFailAndThrowAsIOE((JsonGenerator) null, closeable, e3);
        }
    }

    public static List<Module> findModules() {
        return findModules((ClassLoader) null);
    }

    private static <T> ServiceLoader<T> secureGetServiceLoader(final Class<T> cls, final ClassLoader classLoader) {
        return System.getSecurityManager() == null ? classLoader == null ? ServiceLoader.load(cls) : ServiceLoader.load(cls, classLoader) : (ServiceLoader) AccessController.doPrivileged(new PrivilegedAction<ServiceLoader<T>>() {
            public ServiceLoader<T> run() {
                ClassLoader classLoader = classLoader;
                return classLoader == null ? ServiceLoader.load(cls) : ServiceLoader.load(cls, classLoader);
            }
        });
    }

    public final void _assertNotNull(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException(a.l("argument \"", str, "\" is null"));
        }
    }

    public void _checkInvalidCopy(Class<?> cls) {
        if (getClass() != cls) {
            throw new IllegalStateException("Failed copy()/copyWith(): " + getClass().getName() + " (version: " + version() + ") does not override copy()/copyWith(); it has to");
        }
    }

    @Deprecated
    public final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) throws IOException {
        getSerializationConfig().initialize(jsonGenerator);
        _writeValueAndClose(jsonGenerator, obj);
    }

    public TypeResolverBuilder<?> _constructDefaultTypeResolverBuilder(DefaultTyping defaultTyping, PolymorphicTypeValidator polymorphicTypeValidator) {
        return DefaultTypeResolverBuilder.construct(defaultTyping, polymorphicTypeValidator);
    }

    public Object _convert(Object obj, JavaType javaType) throws IllegalArgumentException {
        Object obj2;
        DefaultSerializerProvider _serializerProvider2 = _serializerProvider(getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE));
        TokenBuffer bufferForValueConversion = _serializerProvider2.bufferForValueConversion(this);
        if (isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            bufferForValueConversion = bufferForValueConversion.forceUseOfBigDecimal(true);
        }
        try {
            _serializerProvider2.serializeValue(bufferForValueConversion, obj);
            JsonParser asParser = bufferForValueConversion.asParser();
            DeserializationConfig deserializationConfig = getDeserializationConfig();
            JsonToken _initForReading = _initForReading(asParser, javaType);
            if (_initForReading == JsonToken.VALUE_NULL) {
                DefaultDeserializationContext createDeserializationContext = createDeserializationContext(asParser, deserializationConfig);
                obj2 = _findRootDeserializer(createDeserializationContext, javaType).getNullValue(createDeserializationContext);
            } else {
                if (_initForReading != JsonToken.END_ARRAY) {
                    if (_initForReading != JsonToken.END_OBJECT) {
                        DefaultDeserializationContext createDeserializationContext2 = createDeserializationContext(asParser, deserializationConfig);
                        obj2 = _findRootDeserializer(createDeserializationContext2, javaType).deserialize(asParser, createDeserializationContext2);
                    }
                }
                obj2 = null;
            }
            asParser.close();
            return obj2;
        } catch (IOException e3) {
            throw new IllegalArgumentException(e3.getMessage(), e3);
        }
    }

    public JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws DatabindException {
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> findRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
        if (findRootValueDeserializer == null) {
            return (JsonDeserializer) deserializationContext.reportBadDefinition(javaType, "Cannot find a deserializer for type " + javaType);
        }
        this._rootDeserializers.put(javaType, findRootValueDeserializer);
        return findRootValueDeserializer;
    }

    public JsonToken _initForReading(JsonParser jsonParser, JavaType javaType) throws IOException {
        this._deserializationConfig.initialize(jsonParser);
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken != null || (currentToken = jsonParser.nextToken()) != null) {
            return currentToken;
        }
        throw MismatchedInputException.from(jsonParser, javaType, "No content to map due to end-of-input");
    }

    public ObjectReader _newReader(DeserializationConfig deserializationConfig) {
        return new ObjectReader(this, deserializationConfig);
    }

    public ObjectWriter _newWriter(SerializationConfig serializationConfig) {
        return new ObjectWriter(this, serializationConfig);
    }

    public Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) throws IOException {
        Object obj;
        try {
            DeserializationConfig deserializationConfig = getDeserializationConfig();
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, deserializationConfig);
            JsonToken _initForReading = _initForReading(jsonParser, javaType);
            if (_initForReading == JsonToken.VALUE_NULL) {
                obj = _findRootDeserializer(createDeserializationContext, javaType).getNullValue(createDeserializationContext);
            } else {
                if (_initForReading != JsonToken.END_ARRAY) {
                    if (_initForReading != JsonToken.END_OBJECT) {
                        obj = createDeserializationContext.readRootValue(jsonParser, javaType, _findRootDeserializer(createDeserializationContext, javaType), (Object) null);
                        createDeserializationContext.checkUnresolvedObjectId();
                    }
                }
                obj = null;
            }
            if (deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                _verifyNoTrailingTokens(jsonParser, createDeserializationContext, javaType);
            }
            if (jsonParser != null) {
                jsonParser.close();
            }
            return obj;
        } catch (Throwable th) {
            if (jsonParser != null) {
                try {
                    jsonParser.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0054, code lost:
        if (r6 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005b, code lost:
        r5.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005e, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonNode _readTreeAndClose(com.fasterxml.jackson.core.JsonParser r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.Class<com.fasterxml.jackson.databind.JsonNode> r0 = com.fasterxml.jackson.databind.JsonNode.class
            com.fasterxml.jackson.databind.JavaType r0 = r5.constructType((java.lang.reflect.Type) r0)     // Catch:{ all -> 0x0025 }
            com.fasterxml.jackson.databind.DeserializationConfig r1 = r5.getDeserializationConfig()     // Catch:{ all -> 0x0025 }
            r1.initialize(r6)     // Catch:{ all -> 0x0025 }
            com.fasterxml.jackson.core.JsonToken r2 = r6.currentToken()     // Catch:{ all -> 0x0025 }
            if (r2 != 0) goto L_0x0027
            com.fasterxml.jackson.core.JsonToken r2 = r6.nextToken()     // Catch:{ all -> 0x0025 }
            if (r2 != 0) goto L_0x0027
            com.fasterxml.jackson.databind.node.JsonNodeFactory r5 = r1.getNodeFactory()     // Catch:{ all -> 0x0025 }
            com.fasterxml.jackson.databind.JsonNode r5 = r5.missingNode()     // Catch:{ all -> 0x0025 }
            r6.close()
            return r5
        L_0x0025:
            r5 = move-exception
            goto L_0x0052
        L_0x0027:
            com.fasterxml.jackson.databind.deser.DefaultDeserializationContext r3 = r5.createDeserializationContext(r6, r1)     // Catch:{ all -> 0x0025 }
            com.fasterxml.jackson.core.JsonToken r4 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL     // Catch:{ all -> 0x0025 }
            if (r2 != r4) goto L_0x0038
            com.fasterxml.jackson.databind.node.JsonNodeFactory r2 = r1.getNodeFactory()     // Catch:{ all -> 0x0025 }
            com.fasterxml.jackson.databind.node.NullNode r2 = r2.nullNode()     // Catch:{ all -> 0x0025 }
            goto L_0x0043
        L_0x0038:
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r5._findRootDeserializer(r3, r0)     // Catch:{ all -> 0x0025 }
            r4 = 0
            java.lang.Object r2 = r3.readRootValue(r6, r0, r2, r4)     // Catch:{ all -> 0x0025 }
            com.fasterxml.jackson.databind.JsonNode r2 = (com.fasterxml.jackson.databind.JsonNode) r2     // Catch:{ all -> 0x0025 }
        L_0x0043:
            com.fasterxml.jackson.databind.DeserializationFeature r4 = com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_TRAILING_TOKENS     // Catch:{ all -> 0x0025 }
            boolean r1 = r1.isEnabled((com.fasterxml.jackson.databind.DeserializationFeature) r4)     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x004e
            r5._verifyNoTrailingTokens(r6, r3, r0)     // Catch:{ all -> 0x0025 }
        L_0x004e:
            r6.close()
            return r2
        L_0x0052:
            throw r5     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r0 = move-exception
            if (r6 == 0) goto L_0x005e
            r6.close()     // Catch:{ all -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r6 = move-exception
            r5.addSuppressed(r6)
        L_0x005e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ObjectMapper._readTreeAndClose(com.fasterxml.jackson.core.JsonParser):com.fasterxml.jackson.databind.JsonNode");
    }

    public Object _readValue(DeserializationConfig deserializationConfig, JsonParser jsonParser, JavaType javaType) throws IOException {
        JsonToken _initForReading = _initForReading(jsonParser, javaType);
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, deserializationConfig);
        Object nullValue = _initForReading == JsonToken.VALUE_NULL ? _findRootDeserializer(createDeserializationContext, javaType).getNullValue(createDeserializationContext) : (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) ? null : createDeserializationContext.readRootValue(jsonParser, javaType, _findRootDeserializer(createDeserializationContext, javaType), (Object) null);
        jsonParser.clearCurrentToken();
        if (deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            _verifyNoTrailingTokens(jsonParser, createDeserializationContext, javaType);
        }
        return nullValue;
    }

    public DefaultSerializerProvider _serializerProvider(SerializationConfig serializationConfig) {
        return this._serializerProvider.createInstance(serializationConfig, this._serializerFactory);
    }

    public final void _verifyNoTrailingTokens(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType) throws IOException {
        JsonToken nextToken = jsonParser.nextToken();
        if (nextToken != null) {
            deserializationContext.reportTrailingTokens(ClassUtil.rawClass(javaType), jsonParser, nextToken);
        }
    }

    public void _verifySchemaType(FormatSchema formatSchema) {
        if (formatSchema != null && !this._jsonFactory.canUseSchema(formatSchema)) {
            throw new IllegalArgumentException("Cannot use FormatSchema of type " + formatSchema.getClass().getName() + " for format " + this._jsonFactory.getFormatName());
        }
    }

    public final void _writeValueAndClose(JsonGenerator jsonGenerator, Object obj) throws IOException {
        SerializationConfig serializationConfig = getSerializationConfig();
        if (!serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
            try {
                _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
                jsonGenerator.close();
            } catch (Exception e3) {
                ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, e3);
            }
        } else {
            _writeCloseable(jsonGenerator, obj, serializationConfig);
        }
    }

    public void acceptJsonFormatVisitor(Class<?> cls, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        acceptJsonFormatVisitor(this._typeFactory.constructType((Type) cls), jsonFormatVisitorWrapper);
    }

    public ObjectMapper activateDefaultTyping(PolymorphicTypeValidator polymorphicTypeValidator) {
        return activateDefaultTyping(polymorphicTypeValidator, DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    public ObjectMapper activateDefaultTypingAsProperty(PolymorphicTypeValidator polymorphicTypeValidator, DefaultTyping defaultTyping, String str) {
        return setDefaultTyping(_constructDefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator).init(JsonTypeInfo.Id.CLASS, (TypeIdResolver) null).inclusion(JsonTypeInfo.As.PROPERTY).typeProperty(str));
    }

    public ObjectMapper addHandler(DeserializationProblemHandler deserializationProblemHandler) {
        this._deserializationConfig = this._deserializationConfig.withHandler(deserializationProblemHandler);
        return this;
    }

    public ObjectMapper addMixIn(Class<?> cls, Class<?> cls2) {
        this._mixIns.addLocalDefinition(cls, cls2);
        return this;
    }

    @Deprecated
    public final void addMixInAnnotations(Class<?> cls, Class<?> cls2) {
        addMixIn(cls, cls2);
    }

    public boolean canDeserialize(JavaType javaType) {
        return createDeserializationContext((JsonParser) null, getDeserializationConfig()).hasValueDeserializerFor(javaType, (AtomicReference<Throwable>) null);
    }

    public boolean canSerialize(Class<?> cls) {
        return _serializerProvider(getSerializationConfig()).hasSerializerFor(cls, (AtomicReference<Throwable>) null);
    }

    public ObjectMapper clearProblemHandlers() {
        this._deserializationConfig = this._deserializationConfig.withNoProblemHandlers();
        return this;
    }

    public MutableCoercionConfig coercionConfigDefaults() {
        return this._coercionConfigs.defaultCoercions();
    }

    public MutableCoercionConfig coercionConfigFor(LogicalType logicalType) {
        return this._coercionConfigs.findOrCreateCoercion(logicalType);
    }

    public MutableConfigOverride configOverride(Class<?> cls) {
        return this._configOverrides.findOrCreateOverride(cls);
    }

    @Deprecated
    public ObjectMapper configure(MapperFeature mapperFeature, boolean z2) {
        this._serializationConfig = (SerializationConfig) (z2 ? this._serializationConfig.with(new MapperFeature[]{mapperFeature}) : this._serializationConfig.without(new MapperFeature[]{mapperFeature}));
        this._deserializationConfig = (DeserializationConfig) (z2 ? this._deserializationConfig.with(new MapperFeature[]{mapperFeature}) : this._deserializationConfig.without(new MapperFeature[]{mapperFeature}));
        return this;
    }

    public JavaType constructType(Type type) {
        _assertNotNull("t", type);
        return this._typeFactory.constructType(type);
    }

    public <T> T convertValue(Object obj, Class<T> cls) throws IllegalArgumentException {
        return _convert(obj, this._typeFactory.constructType((Type) cls));
    }

    public ObjectMapper copy() {
        _checkInvalidCopy(ObjectMapper.class);
        return new ObjectMapper(this);
    }

    public ObjectMapper copyWith(JsonFactory jsonFactory) {
        _checkInvalidCopy(ObjectMapper.class);
        return new ObjectMapper(this, jsonFactory);
    }

    public DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return this._deserializationContext.createInstance(deserializationConfig, jsonParser, this._injectableValues);
    }

    public JsonGenerator createGenerator(OutputStream outputStream) throws IOException {
        _assertNotNull("out", outputStream);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public JsonParser createNonBlockingByteArrayParser() throws IOException {
        return this._deserializationConfig.initialize(this._jsonFactory.createNonBlockingByteArrayParser());
    }

    public JsonParser createParser(File file) throws IOException {
        _assertNotNull("src", file);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(file));
    }

    public ObjectMapper deactivateDefaultTyping() {
        return setDefaultTyping((TypeResolverBuilder<?>) null);
    }

    public ClassIntrospector defaultClassIntrospector() {
        return new BasicClassIntrospector();
    }

    @Deprecated
    public ObjectMapper disable(MapperFeature... mapperFeatureArr) {
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.without(mapperFeatureArr);
        this._serializationConfig = (SerializationConfig) this._serializationConfig.without(mapperFeatureArr);
        return this;
    }

    @Deprecated
    public ObjectMapper disableDefaultTyping() {
        return setDefaultTyping((TypeResolverBuilder<?>) null);
    }

    @Deprecated
    public ObjectMapper enable(MapperFeature... mapperFeatureArr) {
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(mapperFeatureArr);
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(mapperFeatureArr);
        return this;
    }

    @Deprecated
    public ObjectMapper enableDefaultTyping() {
        return activateDefaultTyping(getPolymorphicTypeValidator());
    }

    @Deprecated
    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaultTyping, String str) {
        return activateDefaultTypingAsProperty(getPolymorphicTypeValidator(), defaultTyping, str);
    }

    public ObjectMapper findAndRegisterModules() {
        return registerModules((Iterable<? extends Module>) findModules());
    }

    public Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixIns.findMixInClassFor(cls);
    }

    @Deprecated
    public JsonSchema generateJsonSchema(Class<?> cls) throws JsonMappingException {
        return _serializerProvider(getSerializationConfig()).generateJsonSchema(cls);
    }

    public DateFormat getDateFormat() {
        return this._serializationConfig.getDateFormat();
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public DeserializationContext getDeserializationContext() {
        return this._deserializationContext;
    }

    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    public InjectableValues getInjectableValues() {
        return this._injectableValues;
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public PolymorphicTypeValidator getPolymorphicTypeValidator() {
        return this._deserializationConfig.getBaseSettings().getPolymorphicTypeValidator();
    }

    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._serializationConfig.getPropertyNamingStrategy();
    }

    public Set<Object> getRegisteredModuleIds() {
        Set<Object> set = this._registeredModuleTypes;
        return set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public SerializerFactory getSerializerFactory() {
        return this._serializerFactory;
    }

    public SerializerProvider getSerializerProvider() {
        return this._serializerProvider;
    }

    public SerializerProvider getSerializerProviderInstance() {
        return _serializerProvider(this._serializationConfig);
    }

    public SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public VisibilityChecker<?> getVisibilityChecker() {
        return this._serializationConfig.getDefaultVisibilityChecker();
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._serializationConfig.isEnabled(mapperFeature);
    }

    public int mixInCount() {
        return this._mixIns.localSize();
    }

    public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException {
        _assertNotNull(TtmlNode.TAG_P, jsonParser);
        DeserializationConfig deserializationConfig = getDeserializationConfig();
        if (jsonParser.currentToken() == null && jsonParser.nextToken() == null) {
            return null;
        }
        T t2 = (JsonNode) _readValue(deserializationConfig, jsonParser, constructType((Type) JsonNode.class));
        return t2 == null ? getNodeFactory().nullNode() : t2;
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException, StreamReadException, DatabindException {
        _assertNotNull(TtmlNode.TAG_P, jsonParser);
        return _readValue(getDeserializationConfig(), jsonParser, this._typeFactory.constructType((Type) cls));
    }

    public ObjectReader reader() {
        return _newReader(getDeserializationConfig()).with(this._injectableValues);
    }

    public ObjectReader readerFor(JavaType javaType) {
        return _newReader(getDeserializationConfig(), javaType, (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public ObjectReader readerForArrayOf(Class<?> cls) {
        _assertNotNull("type", cls);
        return _newReader(getDeserializationConfig(), this._typeFactory.constructArrayType(cls), (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public ObjectReader readerForListOf(Class<?> cls) {
        _assertNotNull("type", cls);
        return _newReader(getDeserializationConfig(), this._typeFactory.constructCollectionType((Class<? extends Collection>) List.class, cls), (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public ObjectReader readerForMapOf(Class<?> cls) {
        _assertNotNull("type", cls);
        return _newReader(getDeserializationConfig(), this._typeFactory.constructMapType((Class<? extends Map>) Map.class, (Class<?>) String.class, cls), (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public ObjectReader readerForUpdating(Object obj) {
        return _newReader(getDeserializationConfig(), obj == null ? null : this._typeFactory.constructType((Type) obj.getClass()), obj, (FormatSchema) null, this._injectableValues);
    }

    public ObjectReader readerWithView(Class<?> cls) {
        return _newReader(getDeserializationConfig().withView(cls));
    }

    public ObjectMapper registerModule(Module module) {
        Object typeId;
        _assertNotNull("module", module);
        if (module.getModuleName() == null) {
            throw new IllegalArgumentException("Module without defined name");
        } else if (module.version() != null) {
            for (Module registerModule : module.getDependencies()) {
                registerModule(registerModule);
            }
            if (isEnabled(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS) && (typeId = module.getTypeId()) != null) {
                if (this._registeredModuleTypes == null) {
                    this._registeredModuleTypes = new LinkedHashSet();
                }
                if (!this._registeredModuleTypes.add(typeId)) {
                    return this;
                }
            }
            module.setupModule(new Module.SetupContext() {
                public void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
                    DeserializerFactory withAbstractTypeResolver = ObjectMapper.this._deserializationContext._factory.withAbstractTypeResolver(abstractTypeResolver);
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._deserializationContext = objectMapper._deserializationContext.with(withAbstractTypeResolver);
                }

                public void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
                    DeserializerFactory withDeserializerModifier = ObjectMapper.this._deserializationContext._factory.withDeserializerModifier(beanDeserializerModifier);
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._deserializationContext = objectMapper._deserializationContext.with(withDeserializerModifier);
                }

                public void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._serializerFactory = objectMapper._serializerFactory.withSerializerModifier(beanSerializerModifier);
                }

                public void addDeserializationProblemHandler(DeserializationProblemHandler deserializationProblemHandler) {
                    ObjectMapper.this.addHandler(deserializationProblemHandler);
                }

                public void addDeserializers(Deserializers deserializers) {
                    DeserializerFactory withAdditionalDeserializers = ObjectMapper.this._deserializationContext._factory.withAdditionalDeserializers(deserializers);
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._deserializationContext = objectMapper._deserializationContext.with(withAdditionalDeserializers);
                }

                public void addKeyDeserializers(KeyDeserializers keyDeserializers) {
                    DeserializerFactory withAdditionalKeyDeserializers = ObjectMapper.this._deserializationContext._factory.withAdditionalKeyDeserializers(keyDeserializers);
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._deserializationContext = objectMapper._deserializationContext.with(withAdditionalKeyDeserializers);
                }

                public void addKeySerializers(Serializers serializers) {
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalKeySerializers(serializers);
                }

                public void addSerializers(Serializers serializers) {
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalSerializers(serializers);
                }

                public void addTypeModifier(TypeModifier typeModifier) {
                    ObjectMapper.this.setTypeFactory(ObjectMapper.this._typeFactory.withModifier(typeModifier));
                }

                public void addValueInstantiators(ValueInstantiators valueInstantiators) {
                    DeserializerFactory withValueInstantiators = ObjectMapper.this._deserializationContext._factory.withValueInstantiators(valueInstantiators);
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._deserializationContext = objectMapper._deserializationContext.with(withValueInstantiators);
                }

                public void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._deserializationConfig = (DeserializationConfig) objectMapper._deserializationConfig.withAppendedAnnotationIntrospector(annotationIntrospector);
                    ObjectMapper objectMapper2 = ObjectMapper.this;
                    objectMapper2._serializationConfig = (SerializationConfig) objectMapper2._serializationConfig.withAppendedAnnotationIntrospector(annotationIntrospector);
                }

                public MutableConfigOverride configOverride(Class<?> cls) {
                    return ObjectMapper.this.configOverride(cls);
                }

                public Version getMapperVersion() {
                    return ObjectMapper.this.version();
                }

                public <C extends ObjectCodec> C getOwner() {
                    return ObjectMapper.this;
                }

                public TypeFactory getTypeFactory() {
                    return ObjectMapper.this._typeFactory;
                }

                public void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._deserializationConfig = (DeserializationConfig) objectMapper._deserializationConfig.withInsertedAnnotationIntrospector(annotationIntrospector);
                    ObjectMapper objectMapper2 = ObjectMapper.this;
                    objectMapper2._serializationConfig = (SerializationConfig) objectMapper2._serializationConfig.withInsertedAnnotationIntrospector(annotationIntrospector);
                }

                public boolean isEnabled(MapperFeature mapperFeature) {
                    return ObjectMapper.this.isEnabled(mapperFeature);
                }

                public void registerSubtypes(Class<?>... clsArr) {
                    ObjectMapper.this.registerSubtypes(clsArr);
                }

                public void setClassIntrospector(ClassIntrospector classIntrospector) {
                    ObjectMapper objectMapper = ObjectMapper.this;
                    objectMapper._deserializationConfig = (DeserializationConfig) objectMapper._deserializationConfig.with(classIntrospector);
                    ObjectMapper objectMapper2 = ObjectMapper.this;
                    objectMapper2._serializationConfig = (SerializationConfig) objectMapper2._serializationConfig.with(classIntrospector);
                }

                public void setMixInAnnotations(Class<?> cls, Class<?> cls2) {
                    ObjectMapper.this.addMixIn(cls, cls2);
                }

                public void setNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
                    ObjectMapper.this.setPropertyNamingStrategy(propertyNamingStrategy);
                }

                public boolean isEnabled(DeserializationFeature deserializationFeature) {
                    return ObjectMapper.this.isEnabled(deserializationFeature);
                }

                public void registerSubtypes(NamedType... namedTypeArr) {
                    ObjectMapper.this.registerSubtypes(namedTypeArr);
                }

                public boolean isEnabled(SerializationFeature serializationFeature) {
                    return ObjectMapper.this.isEnabled(serializationFeature);
                }

                public void registerSubtypes(Collection<Class<?>> collection) {
                    ObjectMapper.this.registerSubtypes(collection);
                }

                public boolean isEnabled(JsonFactory.Feature feature) {
                    return ObjectMapper.this.isEnabled(feature);
                }

                public boolean isEnabled(JsonParser.Feature feature) {
                    return ObjectMapper.this.isEnabled(feature);
                }

                public boolean isEnabled(JsonGenerator.Feature feature) {
                    return ObjectMapper.this.isEnabled(feature);
                }
            });
            return this;
        } else {
            throw new IllegalArgumentException("Module without defined version");
        }
    }

    public ObjectMapper registerModules(Module... moduleArr) {
        for (Module registerModule : moduleArr) {
            registerModule(registerModule);
        }
        return this;
    }

    public void registerSubtypes(Class<?>... clsArr) {
        getSubtypeResolver().registerSubtypes(clsArr);
    }

    public ObjectMapper setAccessorNaming(AccessorNamingStrategy.Provider provider) {
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(provider);
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(provider);
        return this;
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(annotationIntrospector);
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(annotationIntrospector);
        return this;
    }

    public ObjectMapper setAnnotationIntrospectors(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(annotationIntrospector);
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(annotationIntrospector2);
        return this;
    }

    public ObjectMapper setBase64Variant(Base64Variant base64Variant) {
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(base64Variant);
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(base64Variant);
        return this;
    }

    public ObjectMapper setConfig(DeserializationConfig deserializationConfig) {
        _assertNotNull("config", deserializationConfig);
        this._deserializationConfig = deserializationConfig;
        return this;
    }

    public ObjectMapper setConstructorDetector(ConstructorDetector constructorDetector) {
        this._deserializationConfig = this._deserializationConfig.with(constructorDetector);
        return this;
    }

    public ObjectMapper setDateFormat(DateFormat dateFormat) {
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(dateFormat);
        this._serializationConfig = this._serializationConfig.with(dateFormat);
        return this;
    }

    public ObjectMapper setDefaultAttributes(ContextAttributes contextAttributes) {
        this._deserializationConfig = this._deserializationConfig.with(contextAttributes);
        this._serializationConfig = this._serializationConfig.with(contextAttributes);
        return this;
    }

    public ObjectMapper setDefaultLeniency(Boolean bool) {
        this._configOverrides.setDefaultLeniency(bool);
        return this;
    }

    public ObjectMapper setDefaultMergeable(Boolean bool) {
        this._configOverrides.setDefaultMergeable(bool);
        return this;
    }

    public ObjectMapper setDefaultPrettyPrinter(PrettyPrinter prettyPrinter) {
        this._serializationConfig = this._serializationConfig.withDefaultPrettyPrinter(prettyPrinter);
        return this;
    }

    public ObjectMapper setDefaultPropertyInclusion(JsonInclude.Value value) {
        this._configOverrides.setDefaultInclusion(value);
        return this;
    }

    public ObjectMapper setDefaultSetterInfo(JsonSetter.Value value) {
        this._configOverrides.setDefaultSetterInfo(value);
        return this;
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder<?> typeResolverBuilder) {
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(typeResolverBuilder);
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(typeResolverBuilder);
        return this;
    }

    public ObjectMapper setDefaultVisibility(JsonAutoDetect.Value value) {
        this._configOverrides.setDefaultVisibility(VisibilityChecker.Std.construct(value));
        return this;
    }

    public ObjectMapper setFilterProvider(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
        return this;
    }

    @Deprecated
    public void setFilters(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
    }

    public Object setHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(handlerInstantiator);
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(handlerInstantiator);
        return this;
    }

    public ObjectMapper setInjectableValues(InjectableValues injectableValues) {
        this._injectableValues = injectableValues;
        return this;
    }

    public ObjectMapper setLocale(Locale locale) {
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(locale);
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(locale);
        return this;
    }

    @Deprecated
    public void setMixInAnnotations(Map<Class<?>, Class<?>> map) {
        setMixIns(map);
    }

    public ObjectMapper setMixInResolver(ClassIntrospector.MixInResolver mixInResolver) {
        SimpleMixInResolver withOverrides = this._mixIns.withOverrides(mixInResolver);
        if (withOverrides != this._mixIns) {
            this._mixIns = withOverrides;
            this._deserializationConfig = new DeserializationConfig(this._deserializationConfig, withOverrides);
            this._serializationConfig = new SerializationConfig(this._serializationConfig, withOverrides);
        }
        return this;
    }

    public ObjectMapper setMixIns(Map<Class<?>, Class<?>> map) {
        this._mixIns.setLocalDefinitions(map);
        return this;
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory jsonNodeFactory) {
        this._deserializationConfig = this._deserializationConfig.with(jsonNodeFactory);
        return this;
    }

    public ObjectMapper setPolymorphicTypeValidator(PolymorphicTypeValidator polymorphicTypeValidator) {
        this._deserializationConfig = this._deserializationConfig._withBase(this._deserializationConfig.getBaseSettings().with(polymorphicTypeValidator));
        return this;
    }

    @Deprecated
    public ObjectMapper setPropertyInclusion(JsonInclude.Value value) {
        return setDefaultPropertyInclusion(value);
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(propertyNamingStrategy);
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(propertyNamingStrategy);
        return this;
    }

    public ObjectMapper setSerializationInclusion(JsonInclude.Include include) {
        setPropertyInclusion(JsonInclude.Value.construct(include, include));
        return this;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory serializerFactory) {
        this._serializerFactory = serializerFactory;
        return this;
    }

    public ObjectMapper setSerializerProvider(DefaultSerializerProvider defaultSerializerProvider) {
        this._serializerProvider = defaultSerializerProvider;
        return this;
    }

    public ObjectMapper setSubtypeResolver(SubtypeResolver subtypeResolver) {
        this._subtypeResolver = subtypeResolver;
        this._deserializationConfig = this._deserializationConfig.with(subtypeResolver);
        this._serializationConfig = this._serializationConfig.with(subtypeResolver);
        return this;
    }

    public ObjectMapper setTimeZone(TimeZone timeZone) {
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(timeZone);
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(timeZone);
        return this;
    }

    public ObjectMapper setTypeFactory(TypeFactory typeFactory) {
        this._typeFactory = typeFactory;
        this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(typeFactory);
        this._serializationConfig = (SerializationConfig) this._serializationConfig.with(typeFactory);
        return this;
    }

    public ObjectMapper setVisibility(VisibilityChecker<?> visibilityChecker) {
        this._configOverrides.setDefaultVisibility(visibilityChecker);
        return this;
    }

    @Deprecated
    public void setVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        setVisibility(visibilityChecker);
    }

    public JsonFactory tokenStreamFactory() {
        return this._jsonFactory;
    }

    public JsonParser treeAsTokens(TreeNode treeNode) {
        _assertNotNull("n", treeNode);
        return new TreeTraversingParser((JsonNode) treeNode, this);
    }

    public <T> T treeToValue(TreeNode treeNode, Class<T> cls) throws IllegalArgumentException, JsonProcessingException {
        T pojo;
        if (treeNode == null) {
            return null;
        }
        try {
            if (TreeNode.class.isAssignableFrom(cls) && cls.isAssignableFrom(treeNode.getClass())) {
                return treeNode;
            }
            if (treeNode.asToken() != JsonToken.VALUE_EMBEDDED_OBJECT || !(treeNode instanceof POJONode) || ((pojo = ((POJONode) treeNode).getPojo()) != null && !cls.isInstance(pojo))) {
                return readValue(treeAsTokens(treeNode), cls);
            }
            return pojo;
        } catch (JsonProcessingException e3) {
            throw e3;
        } catch (IOException e4) {
            throw new IllegalArgumentException(e4.getMessage(), e4);
        }
    }

    public <T> T updateValue(T t2, Object obj) throws JsonMappingException {
        if (t2 == null || obj == null) {
            return t2;
        }
        DefaultSerializerProvider _serializerProvider2 = _serializerProvider(getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE));
        TokenBuffer bufferForValueConversion = _serializerProvider2.bufferForValueConversion(this);
        if (isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            bufferForValueConversion = bufferForValueConversion.forceUseOfBigDecimal(true);
        }
        try {
            _serializerProvider2.serializeValue(bufferForValueConversion, obj);
            JsonParser asParser = bufferForValueConversion.asParser();
            T readValue = readerForUpdating(t2).readValue(asParser);
            asParser.close();
            return readValue;
        } catch (IOException e3) {
            if (e3 instanceof JsonMappingException) {
                throw ((JsonMappingException) e3);
            }
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r4 != null) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends com.fasterxml.jackson.databind.JsonNode> T valueToTree(java.lang.Object r4) throws java.lang.IllegalArgumentException {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x000b
            com.fasterxml.jackson.databind.node.JsonNodeFactory r3 = r3.getNodeFactory()
            com.fasterxml.jackson.databind.node.NullNode r3 = r3.nullNode()
            return r3
        L_0x000b:
            com.fasterxml.jackson.databind.SerializationConfig r0 = r3.getSerializationConfig()
            com.fasterxml.jackson.databind.SerializationFeature r1 = com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE
            com.fasterxml.jackson.databind.SerializationConfig r0 = r0.without((com.fasterxml.jackson.databind.SerializationFeature) r1)
            com.fasterxml.jackson.databind.ser.DefaultSerializerProvider r0 = r3._serializerProvider(r0)
            com.fasterxml.jackson.databind.util.TokenBuffer r1 = r0.bufferForValueConversion(r3)
            com.fasterxml.jackson.databind.DeserializationFeature r2 = com.fasterxml.jackson.databind.DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS
            boolean r2 = r3.isEnabled((com.fasterxml.jackson.databind.DeserializationFeature) r2)
            if (r2 == 0) goto L_0x002a
            r2 = 1
            com.fasterxml.jackson.databind.util.TokenBuffer r1 = r1.forceUseOfBigDecimal(r2)
        L_0x002a:
            r0.serializeValue(r1, r4)     // Catch:{ IOException -> 0x003d }
            com.fasterxml.jackson.core.JsonParser r4 = r1.asParser()     // Catch:{ IOException -> 0x003d }
            com.fasterxml.jackson.core.TreeNode r3 = r3.readTree((com.fasterxml.jackson.core.JsonParser) r4)     // Catch:{ all -> 0x0040 }
            com.fasterxml.jackson.databind.JsonNode r3 = (com.fasterxml.jackson.databind.JsonNode) r3     // Catch:{ all -> 0x0040 }
            if (r4 == 0) goto L_0x003f
            r4.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x003f
        L_0x003d:
            r3 = move-exception
            goto L_0x004e
        L_0x003f:
            return r3
        L_0x0040:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r0 = move-exception
            if (r4 == 0) goto L_0x004d
            r4.close()     // Catch:{ all -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r4 = move-exception
            r3.addSuppressed(r4)     // Catch:{ IOException -> 0x003d }
        L_0x004d:
            throw r0     // Catch:{ IOException -> 0x003d }
        L_0x004e:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r3.getMessage()
            r4.<init>(r0, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ObjectMapper.valueToTree(java.lang.Object):com.fasterxml.jackson.databind.JsonNode");
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) throws IOException {
        _assertNotNull("g", jsonGenerator);
        SerializationConfig serializationConfig = getSerializationConfig();
        _serializerProvider(serializationConfig).serializeValue(jsonGenerator, treeNode);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, StreamWriteException, DatabindException {
        _assertNotNull("g", jsonGenerator);
        SerializationConfig serializationConfig = getSerializationConfig();
        if (serializationConfig.isEnabled(SerializationFeature.INDENT_OUTPUT) && jsonGenerator.getPrettyPrinter() == null) {
            jsonGenerator.setPrettyPrinter(serializationConfig.constructDefaultPrettyPrinter());
        }
        if (!serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
            if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
                return;
            }
            return;
        }
        _writeCloseableValue(jsonGenerator, obj, serializationConfig);
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
            com.fasterxml.jackson.core.JsonFactory r1 = r2._jsonFactory     // Catch:{ JsonProcessingException -> 0x0021, IOException -> 0x001f }
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ObjectMapper.writeValueAsBytes(java.lang.Object):byte[]");
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        try {
            _writeValueAndClose(createGenerator((Writer) segmentedStringWriter), obj);
            return segmentedStringWriter.getAndClear();
        } catch (JsonProcessingException e3) {
            throw e3;
        } catch (IOException e4) {
            throw JsonMappingException.fromUnexpectedIOE(e4);
        }
    }

    public ObjectWriter writer() {
        return _newWriter(getSerializationConfig());
    }

    public ObjectWriter writerFor(Class<?> cls) {
        JavaType javaType;
        SerializationConfig serializationConfig = getSerializationConfig();
        if (cls == null) {
            javaType = null;
        } else {
            javaType = this._typeFactory.constructType((Type) cls);
        }
        return _newWriter(serializationConfig, javaType, (PrettyPrinter) null);
    }

    public ObjectWriter writerWithDefaultPrettyPrinter() {
        SerializationConfig serializationConfig = getSerializationConfig();
        return _newWriter(serializationConfig, (JavaType) null, serializationConfig.getDefaultPrettyPrinter());
    }

    @Deprecated
    public ObjectWriter writerWithType(Class<?> cls) {
        JavaType javaType;
        SerializationConfig serializationConfig = getSerializationConfig();
        if (cls == null) {
            javaType = null;
        } else {
            javaType = this._typeFactory.constructType((Type) cls);
        }
        return _newWriter(serializationConfig, javaType, (PrettyPrinter) null);
    }

    public ObjectWriter writerWithView(Class<?> cls) {
        return _newWriter(getSerializationConfig().withView(cls));
    }

    public ObjectMapper(JsonFactory jsonFactory) {
        this(jsonFactory, (DefaultSerializerProvider) null, (DefaultDeserializationContext) null);
    }

    public static List<Module> findModules(ClassLoader classLoader) {
        ArrayList arrayList = new ArrayList();
        Iterator<Module> it = secureGetServiceLoader(Module.class, classLoader).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public ObjectReader _newReader(DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        return new ObjectReader(this, deserializationConfig, javaType, obj, formatSchema, injectableValues);
    }

    public ObjectWriter _newWriter(SerializationConfig serializationConfig, FormatSchema formatSchema) {
        return new ObjectWriter(this, serializationConfig, formatSchema);
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        if (javaType != null) {
            _serializerProvider(getSerializationConfig()).acceptJsonFormatVisitor(javaType, jsonFormatVisitorWrapper);
            return;
        }
        throw new IllegalArgumentException("type must be provided");
    }

    public ObjectMapper activateDefaultTyping(PolymorphicTypeValidator polymorphicTypeValidator, DefaultTyping defaultTyping) {
        return activateDefaultTyping(polymorphicTypeValidator, defaultTyping, JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public boolean canSerialize(Class<?> cls, AtomicReference<Throwable> atomicReference) {
        return _serializerProvider(getSerializationConfig()).hasSerializerFor(cls, atomicReference);
    }

    public MutableCoercionConfig coercionConfigFor(Class<?> cls) {
        return this._coercionConfigs.findOrCreateCoercion(cls);
    }

    public <T> T convertValue(Object obj, TypeReference<T> typeReference) throws IllegalArgumentException {
        return _convert(obj, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public ArrayNode createArrayNode() {
        return this._deserializationConfig.getNodeFactory().arrayNode();
    }

    public ObjectNode createObjectNode() {
        return this._deserializationConfig.getNodeFactory().objectNode();
    }

    @Deprecated
    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping) {
        return enableDefaultTyping(defaultTyping, JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public boolean isEnabled(SerializationFeature serializationFeature) {
        return this._serializationConfig.isEnabled(serializationFeature);
    }

    public JsonNode missingNode() {
        return this._deserializationConfig.getNodeFactory().missingNode();
    }

    public JsonNode nullNode() {
        return this._deserializationConfig.getNodeFactory().nullNode();
    }

    public ObjectReader reader(DeserializationFeature deserializationFeature) {
        return _newReader(getDeserializationConfig().with(deserializationFeature));
    }

    public ObjectReader readerFor(Class<?> cls) {
        return _newReader(getDeserializationConfig(), cls == null ? null : this._typeFactory.constructType((Type) cls), (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public void registerSubtypes(NamedType... namedTypeArr) {
        getSubtypeResolver().registerSubtypes(namedTypeArr);
    }

    public ObjectMapper setDefaultPropertyInclusion(JsonInclude.Include include) {
        this._configOverrides.setDefaultInclusion(JsonInclude.Value.construct(include, include));
        return this;
    }

    public ObjectMapper setVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility) {
        this._configOverrides.setDefaultVisibility(this._configOverrides.getDefaultVisibility().withVisibility(propertyAccessor, visibility));
        return this;
    }

    public ObjectWriter writer(SerializationFeature serializationFeature) {
        return _newWriter(getSerializationConfig().with(serializationFeature));
    }

    public ObjectMapper(ObjectMapper objectMapper) {
        this(objectMapper, (JsonFactory) null);
    }

    public ObjectWriter _newWriter(SerializationConfig serializationConfig, JavaType javaType, PrettyPrinter prettyPrinter) {
        return new ObjectWriter(this, serializationConfig, javaType, prettyPrinter);
    }

    public ObjectMapper activateDefaultTyping(PolymorphicTypeValidator polymorphicTypeValidator, DefaultTyping defaultTyping, JsonTypeInfo.As as) {
        if (as != JsonTypeInfo.As.EXTERNAL_PROPERTY) {
            return setDefaultTyping(_constructDefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator).init(JsonTypeInfo.Id.CLASS, (TypeIdResolver) null).inclusion(as));
        }
        throw new IllegalArgumentException("Cannot use includeAs of " + as);
    }

    public JavaType constructType(TypeReference<?> typeReference) {
        _assertNotNull("typeRef", typeReference);
        return this._typeFactory.constructType(typeReference);
    }

    public <T> T convertValue(Object obj, JavaType javaType) throws IllegalArgumentException {
        return _convert(obj, javaType);
    }

    public JsonParser createParser(URL url) throws IOException {
        _assertNotNull("src", url);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(url));
    }

    public ObjectMapper disable(SerializationFeature serializationFeature) {
        this._serializationConfig = this._serializationConfig.without(serializationFeature);
        return this;
    }

    public ObjectMapper enable(SerializationFeature serializationFeature) {
        this._serializationConfig = this._serializationConfig.with(serializationFeature);
        return this;
    }

    @Deprecated
    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping, JsonTypeInfo.As as) {
        return activateDefaultTyping(getPolymorphicTypeValidator(), defaultTyping, as);
    }

    public boolean isEnabled(DeserializationFeature deserializationFeature) {
        return this._deserializationConfig.isEnabled(deserializationFeature);
    }

    public <T> T readValue(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException, StreamReadException, DatabindException {
        _assertNotNull(TtmlNode.TAG_P, jsonParser);
        return _readValue(getDeserializationConfig(), jsonParser, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public ObjectReader reader(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        return _newReader(getDeserializationConfig().with(deserializationFeature, deserializationFeatureArr));
    }

    public ObjectMapper registerModules(Iterable<? extends Module> iterable) {
        _assertNotNull("modules", iterable);
        for (Module registerModule : iterable) {
            registerModule(registerModule);
        }
        return this;
    }

    public void registerSubtypes(Collection<Class<?>> collection) {
        getSubtypeResolver().registerSubtypes(collection);
    }

    public ObjectMapper setConfig(SerializationConfig serializationConfig) {
        _assertNotNull("config", serializationConfig);
        this._serializationConfig = serializationConfig;
        return this;
    }

    public ObjectWriter writer(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        return _newWriter(getSerializationConfig().with(serializationFeature, serializationFeatureArr));
    }

    public ObjectMapper(ObjectMapper objectMapper, JsonFactory jsonFactory) {
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        jsonFactory = jsonFactory == null ? objectMapper._jsonFactory.copy() : jsonFactory;
        this._jsonFactory = jsonFactory;
        jsonFactory.setCodec(this);
        this._subtypeResolver = objectMapper._subtypeResolver.copy();
        this._typeFactory = objectMapper._typeFactory;
        this._injectableValues = objectMapper._injectableValues;
        ConfigOverrides copy = objectMapper._configOverrides.copy();
        this._configOverrides = copy;
        CoercionConfigs copy2 = objectMapper._coercionConfigs.copy();
        this._coercionConfigs = copy2;
        this._mixIns = objectMapper._mixIns.copy();
        RootNameLookup rootNameLookup = new RootNameLookup();
        ConfigOverrides configOverrides = copy;
        this._serializationConfig = new SerializationConfig(objectMapper._serializationConfig, this._subtypeResolver, this._mixIns, rootNameLookup, configOverrides);
        this._deserializationConfig = new DeserializationConfig(objectMapper._deserializationConfig, this._subtypeResolver, this._mixIns, rootNameLookup, configOverrides, copy2);
        this._serializerProvider = objectMapper._serializerProvider.copy();
        this._deserializationContext = objectMapper._deserializationContext.copy();
        this._serializerFactory = objectMapper._serializerFactory;
        Set<Object> set = objectMapper._registeredModuleTypes;
        if (set == null) {
            this._registeredModuleTypes = null;
        } else {
            this._registeredModuleTypes = new LinkedHashSet(set);
        }
    }

    public boolean canDeserialize(JavaType javaType, AtomicReference<Throwable> atomicReference) {
        return createDeserializationContext((JsonParser) null, getDeserializationConfig()).hasValueDeserializerFor(javaType, atomicReference);
    }

    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        _assertNotNull("out", outputStream);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(outputStream, jsonEncoding);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public ObjectMapper disable(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        this._serializationConfig = this._serializationConfig.without(serializationFeature, serializationFeatureArr);
        return this;
    }

    public ObjectMapper enable(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        this._serializationConfig = this._serializationConfig.with(serializationFeature, serializationFeatureArr);
        return this;
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._deserializationConfig.isEnabled(feature, this._jsonFactory);
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
        return readValues(jsonParser, (JavaType) resolvedType);
    }

    public ObjectReader reader(JsonNodeFactory jsonNodeFactory) {
        _assertNotNull("nodeFactory", jsonNodeFactory);
        return _newReader(getDeserializationConfig()).with(jsonNodeFactory);
    }

    public ObjectReader readerFor(TypeReference<?> typeReference) {
        _assertNotNull("type", typeReference);
        return _newReader(getDeserializationConfig(), this._typeFactory.constructType(typeReference), (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public ObjectWriter writer(DateFormat dateFormat) {
        return _newWriter(getSerializationConfig().with(dateFormat));
    }

    public ObjectWriter writerFor(TypeReference<?> typeReference) {
        JavaType javaType;
        SerializationConfig serializationConfig = getSerializationConfig();
        if (typeReference == null) {
            javaType = null;
        } else {
            javaType = this._typeFactory.constructType(typeReference);
        }
        return _newWriter(serializationConfig, javaType, (PrettyPrinter) null);
    }

    @Deprecated
    public ObjectWriter writerWithType(TypeReference<?> typeReference) {
        JavaType javaType;
        SerializationConfig serializationConfig = getSerializationConfig();
        if (typeReference == null) {
            javaType = null;
        } else {
            javaType = this._typeFactory.constructType(typeReference);
        }
        return _newWriter(serializationConfig, javaType, (PrettyPrinter) null);
    }

    public ObjectMapper configure(SerializationFeature serializationFeature, boolean z2) {
        this._serializationConfig = z2 ? this._serializationConfig.with(serializationFeature) : this._serializationConfig.without(serializationFeature);
        return this;
    }

    public JsonParser createParser(InputStream inputStream) throws IOException {
        _assertNotNull("in", inputStream);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(inputStream));
    }

    public ObjectMapper disable(DeserializationFeature deserializationFeature) {
        this._deserializationConfig = this._deserializationConfig.without(deserializationFeature);
        return this;
    }

    public ObjectMapper enable(DeserializationFeature deserializationFeature) {
        this._deserializationConfig = this._deserializationConfig.with(deserializationFeature);
        return this;
    }

    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this._serializationConfig.isEnabled(feature, this._jsonFactory);
    }

    public final <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, StreamReadException, DatabindException {
        _assertNotNull(TtmlNode.TAG_P, jsonParser);
        return _readValue(getDeserializationConfig(), jsonParser, (JavaType) resolvedType);
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, JavaType javaType) throws IOException {
        _assertNotNull(TtmlNode.TAG_P, jsonParser);
        DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser, getDeserializationConfig());
        return new MappingIterator(javaType, jsonParser, createDeserializationContext, _findRootDeserializer(createDeserializationContext, javaType), false, (Object) null);
    }

    public ObjectWriter writer(PrettyPrinter prettyPrinter) {
        if (prettyPrinter == null) {
            prettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
        }
        return _newWriter(getSerializationConfig(), (JavaType) null, prettyPrinter);
    }

    public ObjectMapper disable(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        this._deserializationConfig = this._deserializationConfig.without(deserializationFeature, deserializationFeatureArr);
        return this;
    }

    public ObjectMapper enable(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        this._deserializationConfig = this._deserializationConfig.with(deserializationFeature, deserializationFeatureArr);
        return this;
    }

    public boolean isEnabled(JsonFactory.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public ObjectReader reader(FormatSchema formatSchema) {
        _verifySchemaType(formatSchema);
        return _newReader(getDeserializationConfig(), (JavaType) null, (Object) null, formatSchema, this._injectableValues);
    }

    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) throws IOException {
        _assertNotNull("g", jsonGenerator);
        SerializationConfig serializationConfig = getSerializationConfig();
        _serializerProvider(serializationConfig).serializeValue(jsonGenerator, jsonNode);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public ObjectMapper configure(DeserializationFeature deserializationFeature, boolean z2) {
        this._deserializationConfig = z2 ? this._deserializationConfig.with(deserializationFeature) : this._deserializationConfig.without(deserializationFeature);
        return this;
    }

    public JsonGenerator createGenerator(Writer writer) throws IOException {
        _assertNotNull("w", writer);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(writer);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public JsonParser createParser(Reader reader) throws IOException {
        _assertNotNull("r", reader);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(reader));
    }

    public ObjectMapper disable(JsonParser.Feature... featureArr) {
        for (JsonParser.Feature disable : featureArr) {
            this._jsonFactory.disable(disable);
        }
        return this;
    }

    public ObjectMapper enable(JsonParser.Feature... featureArr) {
        for (JsonParser.Feature enable : featureArr) {
            this._jsonFactory.enable(enable);
        }
        return this;
    }

    public boolean isEnabled(StreamReadFeature streamReadFeature) {
        return isEnabled(streamReadFeature.mappedFeature());
    }

    public JsonNode readTree(InputStream inputStream) throws IOException {
        _assertNotNull("in", inputStream);
        return _readTreeAndClose(this._jsonFactory.createParser(inputStream));
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException, StreamReadException, DatabindException {
        _assertNotNull(TtmlNode.TAG_P, jsonParser);
        return _readValue(getDeserializationConfig(), jsonParser, javaType);
    }

    public ObjectWriter writer(FilterProvider filterProvider) {
        return _newWriter(getSerializationConfig().withFilters(filterProvider));
    }

    public ObjectWriter writerFor(JavaType javaType) {
        return _newWriter(getSerializationConfig(), javaType, (PrettyPrinter) null);
    }

    @Deprecated
    public ObjectWriter writerWithType(JavaType javaType) {
        return _newWriter(getSerializationConfig(), javaType, (PrettyPrinter) null);
    }

    public boolean isEnabled(StreamWriteFeature streamWriteFeature) {
        return isEnabled(streamWriteFeature.mappedFeature());
    }

    public ObjectReader reader(InjectableValues injectableValues) {
        return _newReader(getDeserializationConfig(), (JavaType) null, (Object) null, (FormatSchema) null, injectableValues);
    }

    public ObjectWriter writer(FormatSchema formatSchema) {
        _verifySchemaType(formatSchema);
        return _newWriter(getSerializationConfig(), formatSchema);
    }

    public ObjectMapper configure(DatatypeFeature datatypeFeature, boolean z2) {
        if (z2) {
            this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.with(datatypeFeature);
            this._serializationConfig = (SerializationConfig) this._serializationConfig.with(datatypeFeature);
        } else {
            this._deserializationConfig = (DeserializationConfig) this._deserializationConfig.without(datatypeFeature);
            this._serializationConfig = (SerializationConfig) this._serializationConfig.without(datatypeFeature);
        }
        return this;
    }

    public JsonParser createParser(byte[] bArr) throws IOException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(bArr));
    }

    public ObjectMapper disable(JsonGenerator.Feature... featureArr) {
        for (JsonGenerator.Feature disable : featureArr) {
            this._jsonFactory.disable(disable);
        }
        return this;
    }

    public ObjectMapper enable(JsonGenerator.Feature... featureArr) {
        for (JsonGenerator.Feature enable : featureArr) {
            this._jsonFactory.enable(enable);
        }
        return this;
    }

    public JsonNode readTree(Reader reader) throws IOException {
        _assertNotNull("r", reader);
        return _readTreeAndClose(this._jsonFactory.createParser(reader));
    }

    public <T> T readValue(File file, Class<T> cls) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", file);
        return _readMapAndClose(this._jsonFactory.createParser(file), this._typeFactory.constructType((Type) cls));
    }

    public ObjectReader reader(Base64Variant base64Variant) {
        return _newReader((DeserializationConfig) getDeserializationConfig().with(base64Variant));
    }

    public JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        _assertNotNull("outputFile", file);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(file, jsonEncoding);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, Class<T> cls) throws IOException {
        return readValues(jsonParser, this._typeFactory.constructType((Type) cls));
    }

    public ObjectReader reader(ContextAttributes contextAttributes) {
        return _newReader(getDeserializationConfig().with(contextAttributes));
    }

    public ObjectWriter writer(Base64Variant base64Variant) {
        return _newWriter((SerializationConfig) getSerializationConfig().with(base64Variant));
    }

    public JsonParser createParser(byte[] bArr, int i3, int i4) throws IOException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(bArr, i3, i4));
    }

    public JsonNode readTree(String str) throws JsonProcessingException, JsonMappingException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        try {
            return _readTreeAndClose(this._jsonFactory.createParser(str));
        } catch (JsonProcessingException e3) {
            throw e3;
        } catch (IOException e4) {
            throw JsonMappingException.fromUnexpectedIOE(e4);
        }
    }

    public <T> T readValue(File file, TypeReference<T> typeReference) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", file);
        return _readMapAndClose(this._jsonFactory.createParser(file), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
        return readValues(jsonParser, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    @Deprecated
    public ObjectReader reader(JavaType javaType) {
        return _newReader(getDeserializationConfig(), javaType, (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public <T> T treeToValue(TreeNode treeNode, JavaType javaType) throws IllegalArgumentException, JsonProcessingException {
        T pojo;
        if (treeNode == null) {
            return null;
        }
        try {
            if (javaType.isTypeOrSubTypeOf(TreeNode.class) && javaType.isTypeOrSuperTypeOf(treeNode.getClass())) {
                return treeNode;
            }
            if (treeNode.asToken() != JsonToken.VALUE_EMBEDDED_OBJECT || !(treeNode instanceof POJONode) || ((pojo = ((POJONode) treeNode).getPojo()) != null && !javaType.isTypeOrSuperTypeOf(pojo.getClass()))) {
                return readValue(treeAsTokens(treeNode), javaType);
            }
            return pojo;
        } catch (JsonProcessingException e3) {
            throw e3;
        } catch (IOException e4) {
            throw new IllegalArgumentException(e4.getMessage(), e4);
        }
    }

    public void writeValue(File file, Object obj) throws IOException, StreamWriteException, DatabindException {
        _writeValueAndClose(createGenerator(file, JsonEncoding.UTF8), obj);
    }

    public ObjectWriter writer(CharacterEscapes characterEscapes) {
        return _newWriter(getSerializationConfig()).with(characterEscapes);
    }

    @Deprecated
    public ObjectReader reader(Class<?> cls) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructType((Type) cls), (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public void writeValue(OutputStream outputStream, Object obj) throws IOException, StreamWriteException, DatabindException {
        _writeValueAndClose(createGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public ObjectWriter writer(ContextAttributes contextAttributes) {
        return _newWriter(getSerializationConfig().with(contextAttributes));
    }

    public ObjectMapper configure(JsonParser.Feature feature, boolean z2) {
        this._jsonFactory.configure(feature, z2);
        return this;
    }

    public JsonGenerator createGenerator(DataOutput dataOutput) throws IOException {
        _assertNotNull("out", dataOutput);
        JsonGenerator createGenerator = this._jsonFactory.createGenerator(dataOutput);
        this._serializationConfig.initialize(createGenerator);
        return createGenerator;
    }

    public JsonParser createParser(String str) throws IOException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(str));
    }

    public <T> T readValue(File file, JavaType javaType) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", file);
        return _readMapAndClose(this._jsonFactory.createParser(file), javaType);
    }

    @Deprecated
    public ObjectReader reader(TypeReference<?> typeReference) {
        return _newReader(getDeserializationConfig(), this._typeFactory.constructType(typeReference), (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public void writeValue(DataOutput dataOutput, Object obj) throws IOException {
        _writeValueAndClose(createGenerator(dataOutput), obj);
    }

    public ObjectMapper configure(JsonGenerator.Feature feature, boolean z2) {
        this._jsonFactory.configure(feature, z2);
        return this;
    }

    public void writeValue(Writer writer, Object obj) throws IOException, StreamWriteException, DatabindException {
        _writeValueAndClose(createGenerator(writer), obj);
    }

    public JsonParser createParser(char[] cArr) throws IOException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, cArr);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(cArr));
    }

    public JsonNode readTree(byte[] bArr) throws IOException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return _readTreeAndClose(this._jsonFactory.createParser(bArr));
    }

    public <T> T readValue(URL url, Class<T> cls) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", url);
        return _readMapAndClose(this._jsonFactory.createParser(url), this._typeFactory.constructType((Type) cls));
    }

    public JsonParser createParser(char[] cArr, int i3, int i4) throws IOException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, cArr);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(cArr, i3, i4));
    }

    public JsonNode readTree(byte[] bArr, int i3, int i4) throws IOException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return _readTreeAndClose(this._jsonFactory.createParser(bArr, i3, i4));
    }

    public <T> T readValue(URL url, TypeReference<T> typeReference) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", url);
        return _readMapAndClose(this._jsonFactory.createParser(url), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public JsonParser createParser(DataInput dataInput) throws IOException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, dataInput);
        return this._deserializationConfig.initialize(this._jsonFactory.createParser(dataInput));
    }

    public JsonNode readTree(File file) throws IOException {
        _assertNotNull(StringLookupFactory.KEY_FILE, file);
        return _readTreeAndClose(this._jsonFactory.createParser(file));
    }

    public <T> T readValue(URL url, JavaType javaType) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", url);
        return _readMapAndClose(this._jsonFactory.createParser(url), javaType);
    }

    public JsonNode readTree(URL url) throws IOException {
        _assertNotNull("source", url);
        return _readTreeAndClose(this._jsonFactory.createParser(url));
    }

    public <T> T readValue(String str, Class<T> cls) throws JsonProcessingException, JsonMappingException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        return readValue(str, this._typeFactory.constructType((Type) cls));
    }

    public ObjectMapper(JsonFactory jsonFactory, DefaultSerializerProvider defaultSerializerProvider, DefaultDeserializationContext defaultDeserializationContext) {
        JsonFactory jsonFactory2 = jsonFactory;
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (jsonFactory2 == null) {
            this._jsonFactory = new MappingJsonFactory(this);
        } else {
            this._jsonFactory = jsonFactory2;
            if (jsonFactory.getCodec() == null) {
                jsonFactory2.setCodec(this);
            }
        }
        this._subtypeResolver = new StdSubtypeResolver();
        RootNameLookup rootNameLookup = new RootNameLookup();
        this._typeFactory = TypeFactory.defaultInstance();
        SimpleMixInResolver simpleMixInResolver = new SimpleMixInResolver((ClassIntrospector.MixInResolver) null);
        this._mixIns = simpleMixInResolver;
        BaseSettings withClassIntrospector = DEFAULT_BASE.withClassIntrospector(defaultClassIntrospector());
        ConfigOverrides configOverrides = new ConfigOverrides();
        this._configOverrides = configOverrides;
        CoercionConfigs coercionConfigs = new CoercionConfigs();
        this._coercionConfigs = coercionConfigs;
        BaseSettings baseSettings = withClassIntrospector;
        SimpleMixInResolver simpleMixInResolver2 = simpleMixInResolver;
        RootNameLookup rootNameLookup2 = rootNameLookup;
        ConfigOverrides configOverrides2 = configOverrides;
        this._serializationConfig = new SerializationConfig(baseSettings, this._subtypeResolver, simpleMixInResolver2, rootNameLookup2, configOverrides2, DatatypeFeatures.defaultFeatures());
        this._deserializationConfig = new DeserializationConfig(baseSettings, this._subtypeResolver, simpleMixInResolver2, rootNameLookup2, configOverrides2, coercionConfigs, DatatypeFeatures.defaultFeatures());
        boolean requiresPropertyOrdering = this._jsonFactory.requiresPropertyOrdering();
        SerializationConfig serializationConfig = this._serializationConfig;
        MapperFeature mapperFeature = MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
        if (serializationConfig.isEnabled(mapperFeature) ^ requiresPropertyOrdering) {
            configure(mapperFeature, requiresPropertyOrdering);
        }
        this._serializerProvider = defaultSerializerProvider == null ? new DefaultSerializerProvider.Impl() : defaultSerializerProvider;
        this._deserializationContext = defaultDeserializationContext == null ? new DefaultDeserializationContext.Impl((DeserializerFactory) BeanDeserializerFactory.instance) : defaultDeserializationContext;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    public <T> T readValue(String str, TypeReference<T> typeReference) throws JsonProcessingException, JsonMappingException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        return readValue(str, this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(String str, JavaType javaType) throws JsonProcessingException, JsonMappingException {
        _assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        try {
            return _readMapAndClose(this._jsonFactory.createParser(str), javaType);
        } catch (JsonProcessingException e3) {
            throw e3;
        } catch (IOException e4) {
            throw JsonMappingException.fromUnexpectedIOE(e4);
        }
    }

    public <T> T readValue(Reader reader, Class<T> cls) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", reader);
        return _readMapAndClose(this._jsonFactory.createParser(reader), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(Reader reader, TypeReference<T> typeReference) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", reader);
        return _readMapAndClose(this._jsonFactory.createParser(reader), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(Reader reader, JavaType javaType) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", reader);
        return _readMapAndClose(this._jsonFactory.createParser(reader), javaType);
    }

    public <T> T readValue(InputStream inputStream, Class<T> cls) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", inputStream);
        return _readMapAndClose(this._jsonFactory.createParser(inputStream), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(InputStream inputStream, TypeReference<T> typeReference) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", inputStream);
        return _readMapAndClose(this._jsonFactory.createParser(inputStream), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(InputStream inputStream, JavaType javaType) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", inputStream);
        return _readMapAndClose(this._jsonFactory.createParser(inputStream), javaType);
    }

    public <T> T readValue(byte[] bArr, Class<T> cls) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", bArr);
        return _readMapAndClose(this._jsonFactory.createParser(bArr), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(byte[] bArr, int i3, int i4, Class<T> cls) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", bArr);
        return _readMapAndClose(this._jsonFactory.createParser(bArr, i3, i4), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(byte[] bArr, TypeReference<T> typeReference) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", bArr);
        return _readMapAndClose(this._jsonFactory.createParser(bArr), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(byte[] bArr, int i3, int i4, TypeReference<T> typeReference) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", bArr);
        return _readMapAndClose(this._jsonFactory.createParser(bArr, i3, i4), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(byte[] bArr, JavaType javaType) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", bArr);
        return _readMapAndClose(this._jsonFactory.createParser(bArr), javaType);
    }

    public <T> T readValue(byte[] bArr, int i3, int i4, JavaType javaType) throws IOException, StreamReadException, DatabindException {
        _assertNotNull("src", bArr);
        return _readMapAndClose(this._jsonFactory.createParser(bArr, i3, i4), javaType);
    }

    public <T> T readValue(DataInput dataInput, Class<T> cls) throws IOException {
        _assertNotNull("src", dataInput);
        return _readMapAndClose(this._jsonFactory.createParser(dataInput), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(DataInput dataInput, JavaType javaType) throws IOException {
        _assertNotNull("src", dataInput);
        return _readMapAndClose(this._jsonFactory.createParser(dataInput), javaType);
    }
}
