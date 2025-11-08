package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;

public class AsArrayTypeDeserializer extends TypeDeserializerBase implements Serializable {
    private static final long serialVersionUID = 1;

    public AsArrayTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z2, JavaType javaType2) {
        super(javaType, typeIdResolver, str, z2, javaType2);
    }

    public Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken jsonToken;
        Object typeId;
        if (jsonParser.canReadTypeId() && (typeId = jsonParser.getTypeId()) != null) {
            return _deserializeWithNativeTypeId(jsonParser, deserializationContext, typeId);
        }
        boolean isExpectedStartArrayToken = jsonParser.isExpectedStartArrayToken();
        String _locateTypeId = _locateTypeId(jsonParser, deserializationContext);
        JsonDeserializer<Object> _findDeserializer = _findDeserializer(deserializationContext, _locateTypeId);
        JsonParserSequence jsonParserSequence = jsonParser;
        if (this._typeIdVisible) {
            jsonParserSequence = jsonParser;
            if (!_usesExternalId()) {
                boolean hasToken = jsonParser.hasToken(JsonToken.START_OBJECT);
                jsonParserSequence = jsonParser;
                if (hasToken) {
                    TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
                    bufferForInputBuffering.writeStartObject();
                    bufferForInputBuffering.writeFieldName(this._typePropertyName);
                    bufferForInputBuffering.writeString(_locateTypeId);
                    jsonParser.clearCurrentToken();
                    JsonParserSequence createFlattened = JsonParserSequence.createFlattened(false, bufferForInputBuffering.asParser(jsonParser), jsonParser);
                    createFlattened.nextToken();
                    jsonParserSequence = createFlattened;
                }
            }
        }
        if (isExpectedStartArrayToken && jsonParserSequence.currentToken() == JsonToken.END_ARRAY) {
            return _findDeserializer.getNullValue(deserializationContext);
        }
        Object deserialize = _findDeserializer.deserialize(jsonParserSequence, deserializationContext);
        if (isExpectedStartArrayToken && jsonParserSequence.nextToken() != (jsonToken = JsonToken.END_ARRAY)) {
            deserializationContext.reportWrongTokenException(baseType(), jsonToken, "expected closing END_ARRAY after type information and deserialized value", new Object[0]);
        }
        return deserialize;
    }

    public String _locateTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.isExpectedStartArrayToken()) {
            JsonToken nextToken = jsonParser.nextToken();
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            if (nextToken == jsonToken || (nextToken != null && nextToken.isScalarValue())) {
                String text = jsonParser.getText();
                jsonParser.nextToken();
                return text;
            }
            deserializationContext.reportWrongTokenException(baseType(), jsonToken, "need JSON String, Number of Boolean that contains type id (for subtype of %s)", baseTypeName());
            return null;
        } else if (this._defaultImpl != null) {
            return this._idResolver.idFromBaseType();
        } else {
            JavaType baseType = baseType();
            JsonToken jsonToken2 = JsonToken.START_ARRAY;
            deserializationContext.reportWrongTokenException(baseType, jsonToken2, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + baseTypeName(), new Object[0]);
            return null;
        }
    }

    public boolean _usesExternalId() {
        return false;
    }

    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public TypeDeserializer forProperty(BeanProperty beanProperty) {
        return beanProperty == this._property ? this : new AsArrayTypeDeserializer(this, beanProperty);
    }

    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.WRAPPER_ARRAY;
    }

    public AsArrayTypeDeserializer(AsArrayTypeDeserializer asArrayTypeDeserializer, BeanProperty beanProperty) {
        super(asArrayTypeDeserializer, beanProperty);
    }
}
