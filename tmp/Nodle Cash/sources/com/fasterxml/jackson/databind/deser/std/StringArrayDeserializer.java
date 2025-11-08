package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.Objects;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer<String[]> implements ContextualDeserializer {
    private static final String[] NO_STRINGS = new String[0];
    public static final StringArrayDeserializer instance = new StringArrayDeserializer();
    private static final long serialVersionUID = 2;
    protected JsonDeserializer<String> _elementDeserializer;
    protected final NullValueProvider _nullProvider;
    protected final boolean _skipNullValues;
    protected final Boolean _unwrapSingle;

    public StringArrayDeserializer() {
        this((JsonDeserializer<?>) null, (NullValueProvider) null, (Boolean) null);
    }

    private final String[] handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        CoercionAction coercionAction;
        CoercionAction findCoercionFromBlankString;
        Boolean bool = this._unwrapSingle;
        if (bool != Boolean.TRUE && (bool != null || !deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return jsonParser.hasToken(JsonToken.VALUE_STRING) ? (String[]) _deserializeFromString(jsonParser, deserializationContext) : (String[]) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        }
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            str = (String) this._nullProvider.getNullValue(deserializationContext);
        } else {
            if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
                String text = jsonParser.getText();
                if (text.isEmpty()) {
                    CoercionAction findCoercionAction = deserializationContext.findCoercionAction(logicalType(), handledType(), CoercionInputShape.EmptyString);
                    if (findCoercionAction != CoercionAction.Fail) {
                        return (String[]) _deserializeFromEmptyString(jsonParser, deserializationContext, findCoercionAction, handledType(), "empty String (\"\")");
                    }
                } else if (StdDeserializer._isBlank(text) && (findCoercionFromBlankString = deserializationContext.findCoercionFromBlankString(logicalType(), handledType(), coercionAction)) != (coercionAction = CoercionAction.Fail)) {
                    return (String[]) _deserializeFromEmptyString(jsonParser, deserializationContext, findCoercionFromBlankString, handledType(), "blank String (all whitespace)");
                }
            }
            str = _parseString(jsonParser, deserializationContext, this._nullProvider);
        }
        return new String[]{str};
    }

    public final String[] _deserializeCustom(JsonParser jsonParser, DeserializationContext deserializationContext, String[] strArr) throws IOException {
        Object[] objArr;
        int i3;
        String str;
        Class<String> cls = String.class;
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        if (strArr == null) {
            objArr = leaseObjectBuffer.resetAndStart();
            i3 = 0;
        } else {
            i3 = strArr.length;
            objArr = leaseObjectBuffer.resetAndStart(strArr, i3);
        }
        JsonDeserializer<String> jsonDeserializer = this._elementDeserializer;
        while (true) {
            try {
                if (jsonParser.nextTextValue() == null) {
                    JsonToken currentToken = jsonParser.currentToken();
                    if (currentToken == JsonToken.END_ARRAY) {
                        String[] strArr2 = (String[]) leaseObjectBuffer.completeAndClearBuffer(objArr, i3, cls);
                        deserializationContext.returnObjectBuffer(leaseObjectBuffer);
                        return strArr2;
                    } else if (currentToken != JsonToken.VALUE_NULL) {
                        str = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    } else if (!this._skipNullValues) {
                        str = (String) this._nullProvider.getNullValue(deserializationContext);
                    }
                } else {
                    str = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                }
                if (i3 >= objArr.length) {
                    objArr = leaseObjectBuffer.appendCompletedChunk(objArr);
                    i3 = 0;
                }
                int i4 = i3 + 1;
                try {
                    objArr[i3] = str;
                    i3 = i4;
                } catch (Exception e3) {
                    e = e3;
                    i3 = i4;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) cls, i3);
                }
            } catch (Exception e4) {
                e = e4;
                throw JsonMappingException.wrapWithPath((Throwable) e, (Object) cls, i3);
            }
        }
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer findConvertingContentDeserializer = findConvertingContentDeserializer(deserializationContext, beanProperty, this._elementDeserializer);
        JavaType constructType = deserializationContext.constructType(String.class);
        JsonDeserializer findContextualValueDeserializer = findConvertingContentDeserializer == null ? deserializationContext.findContextualValueDeserializer(constructType, beanProperty) : deserializationContext.handleSecondaryContextualization(findConvertingContentDeserializer, beanProperty, constructType);
        Boolean findFormatFeature = findFormatFeature(deserializationContext, beanProperty, String[].class, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        NullValueProvider findContentNullProvider = findContentNullProvider(deserializationContext, beanProperty, findContextualValueDeserializer);
        if (findContextualValueDeserializer != null && isDefaultDeserializer(findContextualValueDeserializer)) {
            findContextualValueDeserializer = null;
        }
        return (this._elementDeserializer == findContextualValueDeserializer && Objects.equals(this._unwrapSingle, findFormatFeature) && this._nullProvider == findContentNullProvider) ? this : new StringArrayDeserializer(findContextualValueDeserializer, findContentNullProvider, findFormatFeature);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }

    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return NO_STRINGS;
    }

    public LogicalType logicalType() {
        return LogicalType.Array;
    }

    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }

    public StringArrayDeserializer(JsonDeserializer<?> jsonDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        super((Class<?>) String[].class);
        this._elementDeserializer = jsonDeserializer;
        this._nullProvider = nullValueProvider;
        this._unwrapSingle = bool;
        this._skipNullValues = NullsConstantProvider.isSkipper(nullValueProvider);
    }

    public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext);
        }
        if (this._elementDeserializer != null) {
            return _deserializeCustom(jsonParser, deserializationContext, (String[]) null);
        }
        ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
        Object[] resetAndStart = leaseObjectBuffer.resetAndStart();
        int i3 = 0;
        while (true) {
            try {
                String nextTextValue = jsonParser.nextTextValue();
                if (nextTextValue == null) {
                    JsonToken currentToken = jsonParser.currentToken();
                    if (currentToken == JsonToken.END_ARRAY) {
                        String[] strArr = (String[]) leaseObjectBuffer.completeAndClearBuffer(resetAndStart, i3, String.class);
                        deserializationContext.returnObjectBuffer(leaseObjectBuffer);
                        return strArr;
                    } else if (currentToken != JsonToken.VALUE_NULL) {
                        nextTextValue = _parseString(jsonParser, deserializationContext, this._nullProvider);
                    } else if (!this._skipNullValues) {
                        nextTextValue = (String) this._nullProvider.getNullValue(deserializationContext);
                    }
                }
                if (i3 >= resetAndStart.length) {
                    resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                    i3 = 0;
                }
                int i4 = i3 + 1;
                try {
                    resetAndStart[i3] = nextTextValue;
                    i3 = i4;
                } catch (Exception e3) {
                    e = e3;
                    i3 = i4;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) resetAndStart, leaseObjectBuffer.bufferedSize() + i3);
                }
            } catch (Exception e4) {
                e = e4;
                throw JsonMappingException.wrapWithPath((Throwable) e, (Object) resetAndStart, leaseObjectBuffer.bufferedSize() + i3);
            }
        }
    }

    public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, String[] strArr) throws IOException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            String[] handleNonArray = handleNonArray(jsonParser, deserializationContext);
            if (handleNonArray == null) {
                return strArr;
            }
            int length = strArr.length;
            String[] strArr2 = new String[(handleNonArray.length + length)];
            System.arraycopy(strArr, 0, strArr2, 0, length);
            System.arraycopy(handleNonArray, 0, strArr2, length, handleNonArray.length);
            return strArr2;
        } else if (this._elementDeserializer != null) {
            return _deserializeCustom(jsonParser, deserializationContext, strArr);
        } else {
            ObjectBuffer leaseObjectBuffer = deserializationContext.leaseObjectBuffer();
            int length2 = strArr.length;
            Object[] resetAndStart = leaseObjectBuffer.resetAndStart(strArr, length2);
            while (true) {
                try {
                    String nextTextValue = jsonParser.nextTextValue();
                    if (nextTextValue == null) {
                        JsonToken currentToken = jsonParser.currentToken();
                        if (currentToken == JsonToken.END_ARRAY) {
                            String[] strArr3 = (String[]) leaseObjectBuffer.completeAndClearBuffer(resetAndStart, length2, String.class);
                            deserializationContext.returnObjectBuffer(leaseObjectBuffer);
                            return strArr3;
                        } else if (currentToken != JsonToken.VALUE_NULL) {
                            nextTextValue = _parseString(jsonParser, deserializationContext, this._nullProvider);
                        } else if (this._skipNullValues) {
                            return NO_STRINGS;
                        } else {
                            nextTextValue = (String) this._nullProvider.getNullValue(deserializationContext);
                        }
                    }
                    if (length2 >= resetAndStart.length) {
                        resetAndStart = leaseObjectBuffer.appendCompletedChunk(resetAndStart);
                        length2 = 0;
                    }
                    int i3 = length2 + 1;
                    try {
                        resetAndStart[length2] = nextTextValue;
                        length2 = i3;
                    } catch (Exception e3) {
                        e = e3;
                        length2 = i3;
                        throw JsonMappingException.wrapWithPath((Throwable) e, (Object) resetAndStart, leaseObjectBuffer.bufferedSize() + length2);
                    }
                } catch (Exception e4) {
                    e = e4;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) resetAndStart, leaseObjectBuffer.bufferedSize() + length2);
                }
            }
        }
    }
}
