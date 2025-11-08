package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class POJONode extends ValueNode {
    private static final long serialVersionUID = 2;
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    public boolean _pojoEquals(POJONode pOJONode) {
        Object obj = this._value;
        return obj == null ? pOJONode._value == null : obj.equals(pOJONode._value);
    }

    public boolean asBoolean(boolean z2) {
        Object obj = this._value;
        return (obj == null || !(obj instanceof Boolean)) ? z2 : ((Boolean) obj).booleanValue();
    }

    public double asDouble(double d2) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).doubleValue() : d2;
    }

    public int asInt(int i3) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).intValue() : i3;
    }

    public long asLong(long j2) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).longValue() : j2;
    }

    public String asText() {
        Object obj = this._value;
        return obj == null ? AbstractJsonLexerKt.NULL : obj.toString();
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public byte[] binaryValue() throws IOException {
        Object obj = this._value;
        return obj instanceof byte[] ? (byte[]) obj : super.binaryValue();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof POJONode)) {
            return _pojoEquals((POJONode) obj);
        }
        return false;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.POJO;
    }

    public Object getPojo() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Object obj = this._value;
        if (obj == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        } else if (obj instanceof JsonSerializable) {
            ((JsonSerializable) obj).serialize(jsonGenerator, serializerProvider);
        } else {
            serializerProvider.defaultSerializeValue(obj, jsonGenerator);
        }
    }

    public String asText(String str) {
        Object obj = this._value;
        return obj == null ? str : obj.toString();
    }
}
