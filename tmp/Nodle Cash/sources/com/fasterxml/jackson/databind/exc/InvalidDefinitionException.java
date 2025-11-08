package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.io.Closeable;

public class InvalidDefinitionException extends JsonMappingException {
    protected transient BeanDescription _beanDesc;
    protected transient BeanPropertyDefinition _property;
    protected final JavaType _type;

    public InvalidDefinitionException(JsonParser jsonParser, String str, JavaType javaType) {
        super((Closeable) jsonParser, str);
        this._type = javaType;
        this._beanDesc = null;
        this._property = null;
    }

    public static InvalidDefinitionException from(JsonParser jsonParser, String str, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) {
        return new InvalidDefinitionException(jsonParser, str, beanDescription, beanPropertyDefinition);
    }

    public BeanDescription getBeanDescription() {
        return this._beanDesc;
    }

    public BeanPropertyDefinition getProperty() {
        return this._property;
    }

    public JavaType getType() {
        return this._type;
    }

    public static InvalidDefinitionException from(JsonParser jsonParser, String str, JavaType javaType) {
        return new InvalidDefinitionException(jsonParser, str, javaType);
    }

    public static InvalidDefinitionException from(JsonGenerator jsonGenerator, String str, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) {
        return new InvalidDefinitionException(jsonGenerator, str, beanDescription, beanPropertyDefinition);
    }

    public static InvalidDefinitionException from(JsonGenerator jsonGenerator, String str, JavaType javaType) {
        return new InvalidDefinitionException(jsonGenerator, str, javaType);
    }

    public InvalidDefinitionException(JsonGenerator jsonGenerator, String str, JavaType javaType) {
        super((Closeable) jsonGenerator, str);
        this._type = javaType;
        this._beanDesc = null;
        this._property = null;
    }

    public InvalidDefinitionException(JsonParser jsonParser, String str, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) {
        super((Closeable) jsonParser, str);
        JavaType javaType;
        if (beanDescription == null) {
            javaType = null;
        } else {
            javaType = beanDescription.getType();
        }
        this._type = javaType;
        this._beanDesc = beanDescription;
        this._property = beanPropertyDefinition;
    }

    public InvalidDefinitionException(JsonGenerator jsonGenerator, String str, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) {
        super((Closeable) jsonGenerator, str);
        JavaType javaType;
        if (beanDescription == null) {
            javaType = null;
        } else {
            javaType = beanDescription.getType();
        }
        this._type = javaType;
        this._beanDesc = beanDescription;
        this._property = beanPropertyDefinition;
    }
}
