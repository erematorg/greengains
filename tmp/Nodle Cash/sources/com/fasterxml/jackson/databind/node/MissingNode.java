package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public final class MissingNode extends ValueNode {
    private static final MissingNode instance = new MissingNode();
    private static final long serialVersionUID = 1;

    public static MissingNode getInstance() {
        return instance;
    }

    public String asText(String str) {
        return str;
    }

    public JsonToken asToken() {
        return JsonToken.NOT_AVAILABLE;
    }

    public <T extends JsonNode> T deepCopy() {
        return this;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.MISSING;
    }

    public int hashCode() {
        return JsonNodeType.MISSING.ordinal();
    }

    public boolean isMissingNode() {
        return true;
    }

    public Object readResolve() {
        return instance;
    }

    public JsonNode require() {
        return (JsonNode) _reportRequiredViolation("require() called on `MissingNode`", new Object[0]);
    }

    public JsonNode requireNonNull() {
        return (JsonNode) _reportRequiredViolation("requireNonNull() called on `MissingNode`", new Object[0]);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNull();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        jsonGenerator.writeNull();
    }

    public String toPrettyString() {
        return "";
    }

    public String toString() {
        return "";
    }

    public String asText() {
        return "";
    }
}
