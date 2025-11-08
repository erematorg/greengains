package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class MapProperty extends PropertyWriter {
    private static final BeanProperty BOGUS_PROP = new BeanProperty.Bogus();
    private static final long serialVersionUID = 1;
    protected Object _key;
    protected JsonSerializer<Object> _keySerializer;
    protected final BeanProperty _property;
    protected final TypeSerializer _typeSerializer;
    protected Object _value;
    protected JsonSerializer<Object> _valueSerializer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MapProperty(TypeSerializer typeSerializer, BeanProperty beanProperty) {
        super(beanProperty == null ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : beanProperty.getMetadata());
        this._typeSerializer = typeSerializer;
        this._property = beanProperty == null ? BOGUS_PROP : beanProperty;
    }

    @Deprecated
    public void depositSchemaProperty(ObjectNode objectNode, SerializerProvider serializerProvider) throws JsonMappingException {
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._property.getAnnotation(cls);
    }

    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return this._property.getContextAnnotation(cls);
    }

    public PropertyName getFullName() {
        return new PropertyName(getName());
    }

    public AnnotatedMember getMember() {
        return this._property.getMember();
    }

    public String getName() {
        Object obj = this._key;
        return obj instanceof String ? (String) obj : String.valueOf(obj);
    }

    public JavaType getType() {
        return this._property.getType();
    }

    public Object getValue() {
        return this._value;
    }

    public PropertyName getWrapperName() {
        return this._property.getWrapperName();
    }

    public void reset(Object obj, Object obj2, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        this._key = obj;
        this._value = obj2;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
    }

    public void serializeAsElement(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        TypeSerializer typeSerializer = this._typeSerializer;
        if (typeSerializer == null) {
            this._valueSerializer.serialize(this._value, jsonGenerator, serializerProvider);
        } else {
            this._valueSerializer.serializeWithType(this._value, jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        this._keySerializer.serialize(this._key, jsonGenerator, serializerProvider);
        TypeSerializer typeSerializer = this._typeSerializer;
        if (typeSerializer == null) {
            this._valueSerializer.serialize(this._value, jsonGenerator, serializerProvider);
        } else {
            this._valueSerializer.serializeWithType(this._value, jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    public void serializeAsOmittedField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        if (!jsonGenerator.canOmitFields()) {
            jsonGenerator.writeOmittedField(getName());
        }
    }

    public void serializeAsPlaceholder(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        jsonGenerator.writeNull();
    }

    public void setValue(Object obj) {
        this._value = obj;
    }

    public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException {
        this._property.depositSchemaProperty(jsonObjectFormatVisitor, serializerProvider);
    }

    @Deprecated
    public void reset(Object obj, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        reset(obj, this._value, jsonSerializer, jsonSerializer2);
    }
}
