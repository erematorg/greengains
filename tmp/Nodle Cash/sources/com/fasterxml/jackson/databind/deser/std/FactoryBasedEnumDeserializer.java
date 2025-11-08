package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;

class FactoryBasedEnumDeserializer extends StdDeserializer<Object> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final SettableBeanProperty[] _creatorProps;
    protected final JsonDeserializer<?> _deser;
    protected final AnnotatedMethod _factory;
    protected final boolean _hasArgs;
    protected final JavaType _inputType;
    private transient PropertyBasedCreator _propCreator;
    protected final ValueInstantiator _valueInstantiator;

    public FactoryBasedEnumDeserializer(Class<?> cls, AnnotatedMethod annotatedMethod, JavaType javaType, ValueInstantiator valueInstantiator, SettableBeanProperty[] settableBeanPropertyArr) {
        super(cls);
        this._factory = annotatedMethod;
        this._hasArgs = true;
        this._inputType = (javaType.hasRawClass(String.class) || javaType.hasRawClass(CharSequence.class)) ? null : javaType;
        this._deser = null;
        this._valueInstantiator = valueInstantiator;
        this._creatorProps = settableBeanPropertyArr;
    }

    private Throwable throwOrReturnThrowable(Throwable th, DeserializationContext deserializationContext) throws IOException {
        Throwable rootCause = ClassUtil.getRootCause(th);
        ClassUtil.throwIfError(rootCause);
        boolean z2 = deserializationContext == null || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (rootCause instanceof IOException) {
            if (!z2 || !(rootCause instanceof JacksonException)) {
                throw ((IOException) rootCause);
            }
        } else if (!z2) {
            ClassUtil.throwIfRTE(rootCause);
        }
        return rootCause;
    }

    public final Object _deserializeWithErrorWrapping(JsonParser jsonParser, DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) throws IOException {
        try {
            return settableBeanProperty.deserialize(jsonParser, deserializationContext);
        } catch (Exception e3) {
            return wrapAndThrow(e3, handledType(), settableBeanProperty.getName(), deserializationContext);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r2._inputType;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonDeserializer<?> createContextual(com.fasterxml.jackson.databind.DeserializationContext r3, com.fasterxml.jackson.databind.BeanProperty r4) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.JsonDeserializer<?> r0 = r2._deser
            if (r0 != 0) goto L_0x0016
            com.fasterxml.jackson.databind.JavaType r0 = r2._inputType
            if (r0 == 0) goto L_0x0016
            com.fasterxml.jackson.databind.deser.SettableBeanProperty[] r1 = r2._creatorProps
            if (r1 != 0) goto L_0x0016
            com.fasterxml.jackson.databind.deser.std.FactoryBasedEnumDeserializer r1 = new com.fasterxml.jackson.databind.deser.std.FactoryBasedEnumDeserializer
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r3.findContextualValueDeserializer(r0, r4)
            r1.<init>((com.fasterxml.jackson.databind.deser.std.FactoryBasedEnumDeserializer) r2, (com.fasterxml.jackson.databind.JsonDeserializer<?>) r3)
            return r1
        L_0x0016:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.FactoryBasedEnumDeserializer.createContextual(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object obj;
        String str;
        JsonDeserializer<?> jsonDeserializer = this._deser;
        if (jsonDeserializer != null) {
            obj = jsonDeserializer.deserialize(jsonParser, deserializationContext);
        } else if (!this._hasArgs) {
            jsonParser.skipChildren();
            try {
                return this._factory.call();
            } catch (Exception e3) {
                return deserializationContext.handleInstantiationProblem(this._valueClass, (Object) null, ClassUtil.throwRootCauseIfIOE(e3));
            }
        } else if (this._creatorProps != null) {
            if (!jsonParser.isExpectedStartObjectToken()) {
                JavaType valueType = getValueType(deserializationContext);
                deserializationContext.reportInputMismatch(valueType, "Input mismatch reading Enum %s: properties-based `@JsonCreator` (%s) expects JSON Object (JsonToken.START_OBJECT), got JsonToken.%s", ClassUtil.getTypeDescription(valueType), this._factory, jsonParser.currentToken());
            }
            if (this._propCreator == null) {
                this._propCreator = PropertyBasedCreator.construct(deserializationContext, this._valueInstantiator, this._creatorProps, deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
            }
            jsonParser.nextToken();
            return deserializeEnumUsingPropertyBased(jsonParser, deserializationContext, this._propCreator);
        } else {
            JsonToken currentToken = jsonParser.currentToken();
            boolean z2 = currentToken == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
            if (z2) {
                currentToken = jsonParser.nextToken();
            }
            if (currentToken == null || !currentToken.isScalarValue()) {
                jsonParser.skipChildren();
                str = "";
            } else {
                str = jsonParser.getValueAsString();
            }
            if (z2 && jsonParser.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(jsonParser, deserializationContext);
            }
            obj = str;
        }
        try {
            return this._factory.callOnWith(this._valueClass, obj);
        } catch (Exception e4) {
            Throwable throwRootCauseIfIOE = ClassUtil.throwRootCauseIfIOE(e4);
            if (!(throwRootCauseIfIOE instanceof IllegalArgumentException) || !deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return deserializationContext.handleInstantiationProblem(this._valueClass, obj, throwRootCauseIfIOE);
            }
            return null;
        }
    }

    public Object deserializeEnumUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext, PropertyBasedCreator propertyBasedCreator) throws IOException {
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, (ObjectIdReader) null);
        JsonToken currentToken = jsonParser.currentToken();
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.currentName();
            jsonParser.nextToken();
            SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(currentName);
            if (!startBuilding.readIdProperty(currentName) || findCreatorProperty != null) {
                if (findCreatorProperty != null) {
                    startBuilding.assignParameter(findCreatorProperty, _deserializeWithErrorWrapping(jsonParser, deserializationContext, findCreatorProperty));
                } else {
                    jsonParser.skipChildren();
                }
            }
            currentToken = jsonParser.nextToken();
        }
        return propertyBasedCreator.build(deserializationContext, startBuilding);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return this._deser == null ? deserialize(jsonParser, deserializationContext) : typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public boolean isCachable() {
        return true;
    }

    public LogicalType logicalType() {
        return LogicalType.Enum;
    }

    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.FALSE;
    }

    public Object wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(th, deserializationContext), obj, str);
    }

    public FactoryBasedEnumDeserializer(Class<?> cls, AnnotatedMethod annotatedMethod) {
        super(cls);
        this._factory = annotatedMethod;
        this._hasArgs = false;
        this._inputType = null;
        this._deser = null;
        this._valueInstantiator = null;
        this._creatorProps = null;
    }

    public FactoryBasedEnumDeserializer(FactoryBasedEnumDeserializer factoryBasedEnumDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super(factoryBasedEnumDeserializer._valueClass);
        this._inputType = factoryBasedEnumDeserializer._inputType;
        this._factory = factoryBasedEnumDeserializer._factory;
        this._hasArgs = factoryBasedEnumDeserializer._hasArgs;
        this._valueInstantiator = factoryBasedEnumDeserializer._valueInstantiator;
        this._creatorProps = factoryBasedEnumDeserializer._creatorProps;
        this._deser = jsonDeserializer;
    }
}
