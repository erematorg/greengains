package com.fasterxml.jackson.databind;

import A.a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.Named;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import org.web3j.utils.RevertReasonExtractor;

public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient ArrayBuilders _arrayBuilders;
    protected transient ContextAttributes _attributes;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected LinkedNode<JavaType> _currentType;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final JacksonFeatureSet<StreamReadCapability> _readCapabilities;
    protected final Class<?> _view;

    /* renamed from: com.fasterxml.jackson.databind.DeserializationContext$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.fasterxml.jackson.core.JsonToken[] r0 = com.fasterxml.jackson.core.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$core$JsonToken = r0
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_OBJECT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.END_OBJECT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.START_ARRAY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x003e }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.END_ARRAY     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_FALSE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_TRUE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_EMBEDDED_OBJECT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x006c }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonToken     // Catch:{ NoSuchFieldError -> 0x009c }
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.DeserializationContext.AnonymousClass1.<clinit>():void");
        }
    }

    public DeserializationContext(DeserializerFactory deserializerFactory) {
        this(deserializerFactory, (DeserializerCache) null);
    }

    private TreeTraversingParser _treeAsTokens(JsonNode jsonNode) throws IOException {
        JsonParser jsonParser = this._parser;
        TreeTraversingParser treeTraversingParser = new TreeTraversingParser(jsonNode, jsonParser == null ? null : jsonParser.getCodec());
        treeTraversingParser.nextToken();
        return treeTraversingParser;
    }

    public DateFormat _getDateFormat() {
        DateFormat dateFormat = this._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat2;
        return dateFormat2;
    }

    public boolean _isCompatible(Class<?> cls, Object obj) {
        if (obj == null || cls.isInstance(obj)) {
            return true;
        }
        return cls.isPrimitive() && ClassUtil.wrapperType(cls).isInstance(obj);
    }

    public String _shapeForToken(JsonToken jsonToken) {
        if (jsonToken == null) {
            return "<end of input>";
        }
        switch (AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonToken.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return "Object value";
            case 4:
            case 5:
                return "Array value";
            case 6:
            case 7:
                return "Boolean value";
            case 8:
                return "Embedded Object";
            case 9:
                return "Floating-point value";
            case 10:
                return "Integer value";
            case 11:
                return "String value";
            case 12:
                return "Null value";
            default:
                return "[Unavailable value]";
        }
    }

    public TokenBuffer bufferAsCopyOfValue(JsonParser jsonParser) throws IOException {
        TokenBuffer bufferForInputBuffering = bufferForInputBuffering(jsonParser);
        bufferForInputBuffering.copyCurrentStructure(jsonParser);
        return bufferForInputBuffering;
    }

    public TokenBuffer bufferForInputBuffering(JsonParser jsonParser) {
        return new TokenBuffer(jsonParser, this);
    }

    public final boolean canOverrideAccessModifiers() {
        return this._config.canOverrideAccessModifiers();
    }

    public abstract void checkUnresolvedObjectId() throws UnresolvedForwardReference;

    public Calendar constructCalendar(Date date) {
        Calendar instance = Calendar.getInstance(getTimeZone());
        instance.setTime(date);
        return instance;
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) throws IllegalArgumentException {
        return javaType.hasRawClass(cls) ? javaType : getConfig().getTypeFactory().constructSpecializedType(javaType, cls, false);
    }

    public final JavaType constructType(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        return this._config.constructType(cls);
    }

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    @Deprecated
    public JsonMappingException endOfInputException(Class<?> cls) {
        return MismatchedInputException.from(this._parser, cls, "Unexpected end-of-input when trying to deserialize a ".concat(cls.getName()));
    }

    public String extractScalarFromObject(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Class<?> cls) throws IOException {
        return (String) handleUnexpectedToken(cls, jsonParser);
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        return getTypeFactory().findClass(str);
    }

    public CoercionAction findCoercionAction(LogicalType logicalType, Class<?> cls, CoercionInputShape coercionInputShape) {
        return this._config.findCoercionAction(logicalType, cls, coercionInputShape);
    }

    public CoercionAction findCoercionFromBlankString(LogicalType logicalType, Class<?> cls, CoercionAction coercionAction) {
        return this._config.findCoercionFromBlankString(logicalType, cls, coercionAction);
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        return findValueDeserializer != null ? handleSecondaryContextualization(findValueDeserializer, beanProperty, javaType) : findValueDeserializer;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) throws JsonMappingException {
        InjectableValues injectableValues = this._injectableValues;
        return injectableValues == null ? reportBadDefinition(ClassUtil.classOf(obj), String.format("No 'injectableValues' configured, cannot inject value with id [%s]", new Object[]{obj})) : injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
    }

    public final KeyDeserializer findKeyDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer keyDeserializer;
        try {
            keyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
        } catch (IllegalArgumentException e3) {
            reportBadDefinition(javaType, ClassUtil.exceptionMessage(e3));
            keyDeserializer = null;
        }
        return keyDeserializer instanceof ContextualKeyDeserializer ? ((ContextualKeyDeserializer) keyDeserializer).createContextual(this, beanProperty) : keyDeserializer;
    }

    public final JsonDeserializer<Object> findNonContextualValueDeserializer(JavaType javaType) throws JsonMappingException {
        return this._cache.findValueDeserializer(this, this._factory, javaType);
    }

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator, ObjectIdResolver objectIdResolver);

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (findValueDeserializer == null) {
            return null;
        }
        JsonDeserializer<?> handleSecondaryContextualization = handleSecondaryContextualization(findValueDeserializer, (BeanProperty) null, javaType);
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(this._config, javaType);
        return findTypeDeserializer != null ? new TypeWrappedDeserializer(findTypeDeserializer.forProperty((BeanProperty) null), handleSecondaryContextualization) : handleSecondaryContextualization;
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    public Object getAttribute(Object obj) {
        return this._attributes.getAttribute(obj);
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public JavaType getContextualType() {
        LinkedNode<JavaType> linkedNode = this._currentType;
        if (linkedNode == null) {
            return null;
        }
        return linkedNode.value();
    }

    @Deprecated
    public DateFormat getDateFormat() {
        return _getDateFormat();
    }

    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> cls) {
        return this._config.getDefaultPropertyFormat(cls);
    }

    public final int getDeserializationFeatures() {
        return this._featureFlags;
    }

    public DeserializerFactory getFactory() {
        return this._factory;
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public void handleBadMerge(JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (!isEnabled(MapperFeature.IGNORE_MERGE_FOR_UNMERGEABLE)) {
            JavaType constructType = constructType(jsonDeserializer.handledType());
            throw InvalidDefinitionException.from(getParser(), a.l("Invalid configuration: values of type ", ClassUtil.getTypeDescription(constructType), " cannot be merged"), constructType);
        }
    }

    public Object handleInstantiationProblem(Class<?> cls, Object obj, Throwable th) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleInstantiationProblem = problemHandlers.value().handleInstantiationProblem(this, cls, obj, th);
            if (handleInstantiationProblem != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, handleInstantiationProblem)) {
                    return handleInstantiationProblem;
                }
                reportBadDefinition(constructType(cls), C0118y.f("DeserializationProblemHandler.handleInstantiationProblem() for type ", ClassUtil.getClassDescription(cls), " returned value of type ", ClassUtil.classNameOf(handleInstantiationProblem)));
            }
        }
        ClassUtil.throwIfIOE(th);
        if (!isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
            ClassUtil.throwIfRTE(th);
        }
        throw instantiationException(cls, th);
    }

    public Object handleMissingInstantiator(Class<?> cls, ValueInstantiator valueInstantiator, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        if (jsonParser == null) {
            jsonParser = getParser();
        }
        String _format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleMissingInstantiator = problemHandlers.value().handleMissingInstantiator(this, cls, valueInstantiator, jsonParser, _format);
            if (handleMissingInstantiator != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, handleMissingInstantiator)) {
                    return handleMissingInstantiator;
                }
                reportBadDefinition(constructType(cls), C0118y.f("DeserializationProblemHandler.handleMissingInstantiator() for type ", ClassUtil.getClassDescription(cls), " returned value of type ", ClassUtil.getClassDescription(handleMissingInstantiator)));
            }
        }
        return valueInstantiator == null ? reportBadDefinition(cls, C0118y.f("Cannot construct instance of ", ClassUtil.nameOf(cls), ": ", _format)) : !valueInstantiator.canInstantiate() ? reportBadDefinition(cls, C0118y.f("Cannot construct instance of ", ClassUtil.nameOf(cls), " (no Creators, like default constructor, exist): ", _format)) : reportInputMismatch(cls, C0118y.f("Cannot construct instance of ", ClassUtil.nameOf(cls), " (although at least one Creator exists): ", _format), new Object[0]);
    }

    public JavaType handleMissingTypeId(JavaType javaType, TypeIdResolver typeIdResolver, String str) throws IOException {
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            JavaType handleMissingTypeId = problemHandlers.value().handleMissingTypeId(this, javaType, typeIdResolver, str);
            if (handleMissingTypeId == null) {
                problemHandlers = problemHandlers.next();
            } else if (handleMissingTypeId.hasRawClass(Void.class)) {
                return null;
            } else {
                if (handleMissingTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return handleMissingTypeId;
                }
                throw invalidTypeIdException(javaType, (String) null, "problem handler tried to resolve into non-subtype: " + ClassUtil.getTypeDescription(handleMissingTypeId));
            }
        }
        throw missingTypeIdException(javaType, str);
    }

    public JsonDeserializer<?> handlePrimaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        if (jsonDeserializer instanceof ContextualDeserializer) {
            this._currentType = new LinkedNode<>(javaType, this._currentType);
            try {
                jsonDeserializer = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                this._currentType = this._currentType.next();
            }
        }
        return jsonDeserializer;
    }

    public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        if (jsonDeserializer instanceof ContextualDeserializer) {
            this._currentType = new LinkedNode<>(javaType, this._currentType);
            try {
                jsonDeserializer = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                this._currentType = this._currentType.next();
            }
        }
        return jsonDeserializer;
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonParser jsonParser) throws IOException {
        return handleUnexpectedToken(constructType(cls), jsonParser.currentToken(), jsonParser, (String) null, new Object[0]);
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            if (problemHandlers.value().handleUnknownProperty(this, jsonParser, jsonDeserializer, obj, str)) {
                return true;
            }
        }
        if (!isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            jsonParser.skipChildren();
            return true;
        }
        throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
    }

    public JavaType handleUnknownTypeId(JavaType javaType, String str, TypeIdResolver typeIdResolver, String str2) throws IOException {
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            JavaType handleUnknownTypeId = problemHandlers.value().handleUnknownTypeId(this, javaType, str, typeIdResolver, str2);
            if (handleUnknownTypeId == null) {
                problemHandlers = problemHandlers.next();
            } else if (handleUnknownTypeId.hasRawClass(Void.class)) {
                return null;
            } else {
                if (handleUnknownTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return handleUnknownTypeId;
                }
                throw invalidTypeIdException(javaType, str, "problem handler tried to resolve into non-subtype: " + ClassUtil.getTypeDescription(handleUnknownTypeId));
            }
        }
        if (!isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
            return null;
        }
        throw invalidTypeIdException(javaType, str, str2);
    }

    public Object handleWeirdKey(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        String _format = _format(str2, objArr);
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            Object handleWeirdKey = problemHandlers.value().handleWeirdKey(this, cls, str, _format);
            if (handleWeirdKey == DeserializationProblemHandler.NOT_HANDLED) {
                problemHandlers = problemHandlers.next();
            } else if (handleWeirdKey == null || cls.isInstance(handleWeirdKey)) {
                return handleWeirdKey;
            } else {
                throw weirdStringException(str, cls, C0118y.f("DeserializationProblemHandler.handleWeirdStringValue() for type ", ClassUtil.getClassDescription(cls), " returned value of type ", ClassUtil.getClassDescription(handleWeirdKey)));
            }
        }
        throw weirdKeyException(cls, str, _format);
    }

    public Object handleWeirdNativeValue(JavaType javaType, Object obj, JsonParser jsonParser) throws IOException {
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        Class<?> rawClass = javaType.getRawClass();
        while (problemHandlers != null) {
            Object handleWeirdNativeValue = problemHandlers.value().handleWeirdNativeValue(this, javaType, obj, jsonParser);
            if (handleWeirdNativeValue == DeserializationProblemHandler.NOT_HANDLED) {
                problemHandlers = problemHandlers.next();
            } else if (handleWeirdNativeValue == null || rawClass.isInstance(handleWeirdNativeValue)) {
                return handleWeirdNativeValue;
            } else {
                throw JsonMappingException.from(jsonParser, _format("DeserializationProblemHandler.handleWeirdNativeValue() for type %s returned value of type %s", ClassUtil.getClassDescription(javaType), ClassUtil.getClassDescription(handleWeirdNativeValue)));
            }
        }
        throw weirdNativeValueException(obj, rawClass);
    }

    public Object handleWeirdNumberValue(Class<?> cls, Number number, String str, Object... objArr) throws IOException {
        String _format = _format(str, objArr);
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            Object handleWeirdNumberValue = problemHandlers.value().handleWeirdNumberValue(this, cls, number, _format);
            if (handleWeirdNumberValue == DeserializationProblemHandler.NOT_HANDLED) {
                problemHandlers = problemHandlers.next();
            } else if (_isCompatible(cls, handleWeirdNumberValue)) {
                return handleWeirdNumberValue;
            } else {
                throw weirdNumberException(number, cls, _format("DeserializationProblemHandler.handleWeirdNumberValue() for type %s returned value of type %s", ClassUtil.getClassDescription(cls), ClassUtil.getClassDescription(handleWeirdNumberValue)));
            }
        }
        throw weirdNumberException(number, cls, _format);
    }

    public Object handleWeirdStringValue(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        String _format = _format(str2, objArr);
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        while (problemHandlers != null) {
            Object handleWeirdStringValue = problemHandlers.value().handleWeirdStringValue(this, cls, str, _format);
            if (handleWeirdStringValue == DeserializationProblemHandler.NOT_HANDLED) {
                problemHandlers = problemHandlers.next();
            } else if (_isCompatible(cls, handleWeirdStringValue)) {
                return handleWeirdStringValue;
            } else {
                throw weirdStringException(str, cls, C0118y.f("DeserializationProblemHandler.handleWeirdStringValue() for type ", ClassUtil.getClassDescription(cls), " returned value of type ", ClassUtil.getClassDescription(handleWeirdStringValue)));
            }
        }
        throw weirdStringException(str, cls, _format);
    }

    public final boolean hasDeserializationFeatures(int i3) {
        return (this._featureFlags & i3) == i3;
    }

    public final boolean hasSomeOfFeatures(int i3) {
        return (this._featureFlags & i3) != 0;
    }

    public boolean hasValueDeserializerFor(JavaType javaType, AtomicReference<Throwable> atomicReference) {
        try {
            return this._cache.hasValueDeserializerFor(this, this._factory, javaType);
        } catch (DatabindException e3) {
            if (atomicReference == null) {
                return false;
            }
            atomicReference.set(e3);
            return false;
        } catch (RuntimeException e4) {
            if (atomicReference != null) {
                atomicReference.set(e4);
                return false;
            }
            throw e4;
        }
    }

    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        String str;
        if (th == null) {
            str = RevertReasonExtractor.MISSING_REASON;
        } else {
            str = ClassUtil.exceptionMessage(th);
            if (str == null) {
                str = ClassUtil.nameOf(th.getClass());
            }
        }
        return ValueInstantiationException.from(this._parser, C0118y.f("Cannot construct instance of ", ClassUtil.nameOf(cls), ", problem: ", str), constructType(cls), th);
    }

    public JsonMappingException invalidTypeIdException(JavaType javaType, String str, String str2) {
        return InvalidTypeIdException.from(this._parser, _colonConcat(C0118y.f("Could not resolve type id '", str, "' as a subtype of ", ClassUtil.getTypeDescription(javaType)), str2), javaType, str);
    }

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    @Deprecated
    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    public JsonMappingException missingTypeIdException(JavaType javaType, String str) {
        return InvalidTypeIdException.from(this._parser, _colonConcat(String.format("Could not resolve subtype of %s", new Object[]{javaType}), str), javaType, (String) null);
    }

    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return _getDateFormat().parse(str);
        } catch (ParseException e3) {
            throw new IllegalArgumentException(C0118y.f("Failed to parse Date value '", str, "': ", ClassUtil.exceptionMessage(e3)));
        }
    }

    public <T> T readPropertyValue(JsonParser jsonParser, BeanProperty beanProperty, Class<T> cls) throws IOException {
        return readPropertyValue(jsonParser, beanProperty, getTypeFactory().constructType((Type) cls));
    }

    public JsonNode readTree(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.currentToken();
        return (currentToken == null && (currentToken = jsonParser.nextToken()) == null) ? getNodeFactory().missingNode() : currentToken == JsonToken.VALUE_NULL ? getNodeFactory().nullNode() : (JsonNode) findRootValueDeserializer(this._config.constructType((Class<?>) JsonNode.class)).deserialize(jsonParser, this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001c, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T readTreeAsValue(com.fasterxml.jackson.databind.JsonNode r1, java.lang.Class<T> r2) throws java.io.IOException {
        /*
            r0 = this;
            if (r1 != 0) goto L_0x0004
            r0 = 0
            return r0
        L_0x0004:
            com.fasterxml.jackson.databind.node.TreeTraversingParser r1 = r0._treeAsTokens(r1)
            java.lang.Object r0 = r0.readValue((com.fasterxml.jackson.core.JsonParser) r1, r2)     // Catch:{ all -> 0x0012 }
            if (r1 == 0) goto L_0x0011
            r1.close()
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r2 = move-exception
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x001f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.DeserializationContext.readTreeAsValue(com.fasterxml.jackson.databind.JsonNode, java.lang.Class):java.lang.Object");
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException {
        return readValue(jsonParser, getTypeFactory().constructType((Type) cls));
    }

    public <T> T reportBadCoercion(JsonDeserializer<?> jsonDeserializer, Class<?> cls, Object obj, String str, Object... objArr) throws JsonMappingException {
        throw InvalidFormatException.from(getParser(), _format(str, objArr), obj, cls);
    }

    public <T> T reportBadDefinition(JavaType javaType, String str) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, str, javaType);
    }

    @Deprecated
    public <T> T reportBadMerge(JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        handleBadMerge(jsonDeserializer);
        return null;
    }

    public <T> T reportBadPropertyDefinition(BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, String str, Object... objArr) throws JsonMappingException {
        String _format = _format(str, objArr);
        StringBuilder l2 = C0118y.l("Invalid definition for property ", ClassUtil.nameOf((Named) beanPropertyDefinition), " (of type ", ClassUtil.nameOf(beanDescription.getBeanClass()), "): ");
        l2.append(_format);
        throw InvalidDefinitionException.from(this._parser, l2.toString(), beanDescription, beanPropertyDefinition);
    }

    public <T> T reportBadTypeDefinition(BeanDescription beanDescription, String str, Object... objArr) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, C0118y.f("Invalid type definition for type ", ClassUtil.nameOf(beanDescription.getBeanClass()), ": ", _format(str, objArr)), beanDescription, (BeanPropertyDefinition) null);
    }

    public <T> T reportInputMismatch(JsonDeserializer<?> jsonDeserializer, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), jsonDeserializer.handledType(), _format(str, objArr));
    }

    @Deprecated
    public void reportMappingException(String str, Object... objArr) throws JsonMappingException {
        throw JsonMappingException.from(getParser(), _format(str, objArr));
    }

    @Deprecated
    public void reportMissingContent(String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), (JavaType) null, "No content to map due to end-of-input");
    }

    public <T> T reportPropertyInputMismatch(Class<?> cls, String str, String str2, Object... objArr) throws JsonMappingException {
        MismatchedInputException from = MismatchedInputException.from(getParser(), cls, _format(str2, objArr));
        if (str != null) {
            from.prependPath((Object) cls, str);
        }
        throw from;
    }

    public <T> T reportTrailingTokens(Class<?> cls, JsonParser jsonParser, JsonToken jsonToken) throws JsonMappingException {
        String nameOf = ClassUtil.nameOf(cls);
        throw MismatchedInputException.from(jsonParser, cls, "Trailing token (of type " + jsonToken + ") found after value (bound as " + nameOf + "): not allowed as per `DeserializationFeature.FAIL_ON_TRAILING_TOKENS`");
    }

    @Deprecated
    public void reportUnknownProperty(Object obj, String str, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
        }
    }

    public <T> T reportUnresolvedObjectId(ObjectIdReader objectIdReader, Object obj) throws JsonMappingException {
        return reportInputMismatch((BeanProperty) objectIdReader.idProperty, String.format("No Object Id found for an instance of %s, to assign to property '%s'", new Object[]{ClassUtil.classNameOf(obj), objectIdReader.propertyName}), new Object[0]);
    }

    public void reportWrongTokenException(JsonDeserializer<?> jsonDeserializer, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), jsonDeserializer.handledType(), jsonToken, _format(str, objArr));
    }

    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    @Deprecated
    public JsonMappingException unknownTypeException(JavaType javaType, String str, String str2) {
        return MismatchedInputException.from(this._parser, javaType, _colonConcat(C0118y.f("Could not resolve type id '", str, "' into a subtype of ", ClassUtil.getTypeDescription(javaType)), str2));
    }

    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        JsonParser jsonParser = this._parser;
        StringBuilder l2 = C0118y.l("Cannot deserialize Map key of type ", ClassUtil.nameOf(cls), " from String ", _quotedString(str), ": ");
        l2.append(str2);
        return InvalidFormatException.from(jsonParser, l2.toString(), str, cls);
    }

    public JsonMappingException weirdNativeValueException(Object obj, Class<?> cls) {
        return InvalidFormatException.from(this._parser, C0118y.g("Cannot deserialize value of type ", ClassUtil.nameOf(cls), " from native value (`JsonToken.VALUE_EMBEDDED_OBJECT`) of type ", ClassUtil.classNameOf(obj), ": incompatible types"), obj, cls);
    }

    public JsonMappingException weirdNumberException(Number number, Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        StringBuilder l2 = C0118y.l("Cannot deserialize value of type ", ClassUtil.nameOf(cls), " from number ", String.valueOf(number), ": ");
        l2.append(str);
        return InvalidFormatException.from(jsonParser, l2.toString(), number, cls);
    }

    public JsonMappingException weirdStringException(String str, Class<?> cls, String str2) {
        StringBuilder l2 = C0118y.l("Cannot deserialize value of type ", ClassUtil.nameOf(cls), " from String ", _quotedString(str), ": ");
        l2.append(str2);
        return InvalidFormatException.from(this._parser, l2.toString(), str, cls);
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, JavaType javaType, JsonToken jsonToken, String str) {
        JsonToken currentToken = jsonParser.currentToken();
        return MismatchedInputException.from(jsonParser, javaType, _colonConcat("Unexpected token (" + currentToken + "), expected " + jsonToken, str));
    }

    public DeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        if (deserializerFactory != null) {
            this._factory = deserializerFactory;
            this._cache = deserializerCache == null ? new DeserializerCache() : deserializerCache;
            this._featureFlags = 0;
            this._readCapabilities = null;
            this._config = null;
            this._injectableValues = null;
            this._view = null;
            this._attributes = null;
            return;
        }
        throw new NullPointerException("Cannot pass null DeserializerFactory");
    }

    public final TokenBuffer bufferForInputBuffering() {
        return bufferForInputBuffering(getParser());
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonToken jsonToken, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        return handleUnexpectedToken(constructType(cls), jsonToken, jsonParser, str, objArr);
    }

    public final boolean isEnabled(DatatypeFeature datatypeFeature) {
        return this._config.isEnabled(datatypeFeature);
    }

    @Deprecated
    public JsonMappingException mappingException(String str, Object... objArr) {
        return JsonMappingException.from(getParser(), _format(str, objArr));
    }

    public <T> T readPropertyValue(JsonParser jsonParser, BeanProperty beanProperty, JavaType javaType) throws IOException {
        JsonDeserializer<Object> findContextualValueDeserializer = findContextualValueDeserializer(javaType, beanProperty);
        if (findContextualValueDeserializer == null) {
            return reportBadDefinition(javaType, C0118y.g("Could not find JsonDeserializer for type ", ClassUtil.getTypeDescription(javaType), " (via property ", ClassUtil.nameOf((Named) beanProperty), ")"));
        }
        return findContextualValueDeserializer.deserialize(jsonParser, this);
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException {
        JsonDeserializer<Object> findRootValueDeserializer = findRootValueDeserializer(javaType);
        if (findRootValueDeserializer != null) {
            return findRootValueDeserializer.deserialize(jsonParser, this);
        }
        return reportBadDefinition(javaType, "Could not find JsonDeserializer for type " + ClassUtil.getTypeDescription(javaType));
    }

    public DeserializationContext setAttribute(Object obj, Object obj2) {
        this._attributes = this._attributes.withPerCallAttribute(obj, obj2);
        return this;
    }

    public Object handleUnexpectedToken(JavaType javaType, JsonParser jsonParser) throws IOException {
        return handleUnexpectedToken(javaType, jsonParser.currentToken(), jsonParser, (String) null, new Object[0]);
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (this._featureFlags & deserializationFeature.getMask()) != 0;
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.currentToken());
    }

    public <T> T reportInputMismatch(Class<?> cls, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), cls, _format(str, objArr));
    }

    public void reportWrongTokenException(JavaType javaType, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), javaType, jsonToken, _format(str, objArr));
    }

    public Object handleUnexpectedToken(JavaType javaType, JsonToken jsonToken, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        String _format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object handleUnexpectedToken = problemHandlers.value().handleUnexpectedToken(this, javaType, jsonToken, jsonParser, _format);
            if (handleUnexpectedToken != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(javaType.getRawClass(), handleUnexpectedToken)) {
                    return handleUnexpectedToken;
                }
                reportBadDefinition(javaType, C0118y.f("DeserializationProblemHandler.handleUnexpectedToken() for type ", ClassUtil.getTypeDescription(javaType), " returned value of type ", ClassUtil.classNameOf(handleUnexpectedToken)));
            }
        }
        if (_format == null) {
            String typeDescription = ClassUtil.getTypeDescription(javaType);
            if (jsonToken == null) {
                _format = c.a("Unexpected end-of-input when trying read value of type ", typeDescription);
            } else {
                StringBuilder l2 = C0118y.l("Cannot deserialize value of type ", typeDescription, " from ", _shapeForToken(jsonToken), " (token `JsonToken.");
                l2.append(jsonToken);
                l2.append("`)");
                _format = l2.toString();
            }
        }
        if (jsonToken != null && jsonToken.isScalarValue()) {
            jsonParser.getText();
        }
        reportInputMismatch(javaType, _format, new Object[0]);
        return null;
    }

    public final boolean isEnabled(StreamReadCapability streamReadCapability) {
        return this._readCapabilities.isEnabled(streamReadCapability);
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        JsonParser jsonParser = this._parser;
        String nameOf = ClassUtil.nameOf(cls);
        return JsonMappingException.from(jsonParser, "Cannot deserialize instance of " + nameOf + " out of " + jsonToken + " token");
    }

    public <T> T reportInputMismatch(JavaType javaType, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), javaType, _format(str, objArr));
    }

    public <T> T reportPropertyInputMismatch(JavaType javaType, String str, String str2, Object... objArr) throws JsonMappingException {
        return reportPropertyInputMismatch(javaType.getRawClass(), str, str2, objArr);
    }

    public void reportWrongTokenException(Class<?> cls, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), cls, jsonToken, _format(str, objArr));
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, Class<?> cls, JsonToken jsonToken, String str) {
        JsonToken currentToken = jsonParser.currentToken();
        return MismatchedInputException.from(jsonParser, cls, _colonConcat("Unexpected token (" + currentToken + "), expected " + jsonToken, str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001c, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T readTreeAsValue(com.fasterxml.jackson.databind.JsonNode r1, com.fasterxml.jackson.databind.JavaType r2) throws java.io.IOException {
        /*
            r0 = this;
            if (r1 != 0) goto L_0x0004
            r0 = 0
            return r0
        L_0x0004:
            com.fasterxml.jackson.databind.node.TreeTraversingParser r1 = r0._treeAsTokens(r1)
            java.lang.Object r0 = r0.readValue((com.fasterxml.jackson.core.JsonParser) r1, (com.fasterxml.jackson.databind.JavaType) r2)     // Catch:{ all -> 0x0012 }
            if (r1 == 0) goto L_0x0011
            r1.close()
        L_0x0011:
            return r0
        L_0x0012:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r2 = move-exception
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x001f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.DeserializationContext.readTreeAsValue(com.fasterxml.jackson.databind.JsonNode, com.fasterxml.jackson.databind.JavaType):java.lang.Object");
    }

    public <T> T reportInputMismatch(BeanProperty beanProperty, String str, Object... objArr) throws JsonMappingException {
        JavaType javaType;
        AnnotatedMember member;
        String _format = _format(str, objArr);
        if (beanProperty == null) {
            javaType = null;
        } else {
            javaType = beanProperty.getType();
        }
        MismatchedInputException from = MismatchedInputException.from(getParser(), javaType, _format);
        if (!(beanProperty == null || (member = beanProperty.getMember()) == null)) {
            from.prependPath((Object) member.getDeclaringClass(), beanProperty.getName());
        }
        throw from;
    }

    @Deprecated
    public void reportWrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(jsonParser, jsonToken, _format(str, objArr));
    }

    @Deprecated
    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        return wrongTokenException(jsonParser, (JavaType) null, jsonToken, str);
    }

    public DeserializationContext(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory) {
        this._cache = deserializationContext._cache;
        this._factory = deserializerFactory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._readCapabilities = deserializationContext._readCapabilities;
        this._view = deserializationContext._view;
        this._parser = deserializationContext._parser;
        this._injectableValues = deserializationContext._injectableValues;
        this._attributes = deserializationContext._attributes;
    }

    public JsonMappingException instantiationException(Class<?> cls, String str) {
        return ValueInstantiationException.from(this._parser, C0118y.f("Cannot construct instance of ", ClassUtil.nameOf(cls), ": ", str), constructType(cls));
    }

    public DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        JacksonFeatureSet<StreamReadCapability> jacksonFeatureSet;
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        if (jsonParser == null) {
            jacksonFeatureSet = null;
        } else {
            jacksonFeatureSet = jsonParser.getReadCapabilities();
        }
        this._readCapabilities = jacksonFeatureSet;
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = deserializationConfig.getActiveView();
        this._parser = jsonParser;
        this._injectableValues = injectableValues;
        this._attributes = deserializationConfig.getAttributes();
    }

    public DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig) {
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        this._readCapabilities = null;
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = null;
        this._parser = null;
        this._injectableValues = null;
        this._attributes = null;
    }

    public DeserializationContext(DeserializationContext deserializationContext) {
        this._cache = new DeserializerCache();
        this._factory = deserializationContext._factory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._readCapabilities = deserializationContext._readCapabilities;
        this._view = deserializationContext._view;
        this._injectableValues = null;
    }
}
