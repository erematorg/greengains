package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;

public class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    private static final long serialVersionUID = 2;
    protected final String _value;

    public TextNode(String str) {
        this._value = str;
    }

    @Deprecated
    public static void appendQuoted(StringBuilder sb, String str) {
        sb.append('\"');
        CharTypes.appendQuoted(sb, str);
        sb.append('\"');
    }

    public static TextNode valueOf(String str) {
        if (str == null) {
            return null;
        }
        return str.isEmpty() ? EMPTY_STRING_NODE : new TextNode(str);
    }

    public boolean asBoolean(boolean z2) {
        String str = this._value;
        if (str != null) {
            String trim = str.trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim)) {
                return false;
            }
        }
        return z2;
    }

    public double asDouble(double d2) {
        return NumberInput.parseAsDouble(this._value, d2);
    }

    public int asInt(int i3) {
        return NumberInput.parseAsInt(this._value, i3);
    }

    public long asLong(long j2) {
        return NumberInput.parseAsLong(this._value, j2);
    }

    public String asText() {
        return this._value;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    public byte[] binaryValue() throws IOException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof TextNode)) {
            return ((TextNode) obj)._value.equals(this._value);
        }
        return false;
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        String trim = this._value.trim();
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(Math.max(16, Math.min(65536, ((trim.length() >> 2) * 3) + 4)));
        try {
            base64Variant.decode(trim, byteArrayBuilder);
            return byteArrayBuilder.toByteArray();
        } catch (IllegalArgumentException e3) {
            String message = e3.getMessage();
            throw InvalidFormatException.from((JsonParser) null, "Cannot access contents of TextNode as binary due to broken Base64 encoding: " + message, trim, byte[].class);
        }
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.STRING;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String str = this._value;
        if (str == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(str);
        }
    }

    public String textValue() {
        return this._value;
    }

    public String asText(String str) {
        String str2 = this._value;
        return str2 == null ? str : str2;
    }
}
