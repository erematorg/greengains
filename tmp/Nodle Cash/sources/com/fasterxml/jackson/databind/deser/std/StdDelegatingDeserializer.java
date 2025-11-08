package com.fasterxml.jackson.databind.deser.std;

import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.util.Collection;

public class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected final Converter<Object, T> _converter;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JavaType _delegateType;

    public StdDelegatingDeserializer(Converter<?, T> converter) {
        super((Class<?>) Object.class);
        this._converter = converter;
        this._delegateType = null;
        this._delegateDeserializer = null;
    }

    public T _convertIfNonNull(Object obj) {
        if (obj == null) {
            return null;
        }
        return this._converter.convert(obj);
    }

    public Object _handleIncompatibleUpdateValue(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        throw new UnsupportedOperationException(String.format(b.h(obj, "Cannot update object of type %s (using deserializer for type %s)"), new Object[]{this._delegateType}));
    }

    public T convertValue(Object obj) {
        return this._converter.convert(obj);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            JsonDeserializer<?> handleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, this._delegateType);
            return handleSecondaryContextualization != this._delegateDeserializer ? withDelegate(this._converter, this._delegateType, handleSecondaryContextualization) : this;
        }
        JavaType inputType = this._converter.getInputType(deserializationContext.getTypeFactory());
        return withDelegate(this._converter, inputType, deserializationContext.findContextualValueDeserializer(inputType, beanProperty));
    }

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object deserialize = this._delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (deserialize == null) {
            return null;
        }
        return convertValue(deserialize);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        Object deserialize = this._delegateDeserializer.deserialize(jsonParser, deserializationContext);
        if (deserialize == null) {
            return null;
        }
        return convertValue(deserialize);
    }

    public Object getAbsentValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return _convertIfNonNull(this._delegateDeserializer.getAbsentValue(deserializationContext));
    }

    public JsonDeserializer<?> getDelegatee() {
        return this._delegateDeserializer;
    }

    public AccessPattern getEmptyAccessPattern() {
        return this._delegateDeserializer.getEmptyAccessPattern();
    }

    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return _convertIfNonNull(this._delegateDeserializer.getEmptyValue(deserializationContext));
    }

    public Collection<Object> getKnownPropertyNames() {
        return this._delegateDeserializer.getKnownPropertyNames();
    }

    public AccessPattern getNullAccessPattern() {
        return this._delegateDeserializer.getNullAccessPattern();
    }

    public T getNullValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return _convertIfNonNull(this._delegateDeserializer.getNullValue(deserializationContext));
    }

    public Class<?> handledType() {
        return this._delegateDeserializer.handledType();
    }

    public boolean isCachable() {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        return jsonDeserializer != null && jsonDeserializer.isCachable();
    }

    public LogicalType logicalType() {
        return this._delegateDeserializer.logicalType();
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null && (jsonDeserializer instanceof ResolvableDeserializer)) {
            ((ResolvableDeserializer) jsonDeserializer).resolve(deserializationContext);
        }
    }

    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._delegateDeserializer.supportsUpdate(deserializationConfig);
    }

    public StdDelegatingDeserializer<T> withDelegate(Converter<Object, T> converter, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        ClassUtil.verifyMustOverride(StdDelegatingDeserializer.class, this, "withDelegate");
        return new StdDelegatingDeserializer<>(converter, javaType, jsonDeserializer);
    }

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        if (this._delegateType.getRawClass().isAssignableFrom(obj.getClass())) {
            return this._delegateDeserializer.deserialize(jsonParser, deserializationContext, obj);
        }
        return _handleIncompatibleUpdateValue(jsonParser, deserializationContext, obj);
    }

    public StdDelegatingDeserializer(Converter<Object, T> converter, JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        super(javaType);
        this._converter = converter;
        this._delegateType = javaType;
        this._delegateDeserializer = jsonDeserializer;
    }

    public StdDelegatingDeserializer(StdDelegatingDeserializer<T> stdDelegatingDeserializer) {
        super((StdDeserializer<?>) stdDelegatingDeserializer);
        this._converter = stdDelegatingDeserializer._converter;
        this._delegateType = stdDelegatingDeserializer._delegateType;
        this._delegateDeserializer = stdDelegatingDeserializer._delegateDeserializer;
    }
}
