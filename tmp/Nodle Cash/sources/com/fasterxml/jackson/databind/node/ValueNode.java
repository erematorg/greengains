package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.List;

public abstract class ValueNode extends BaseJsonNode {
    private static final long serialVersionUID = 1;

    public JsonNode _at(JsonPointer jsonPointer) {
        return null;
    }

    public abstract JsonToken asToken();

    public <T extends JsonNode> T deepCopy() {
        return this;
    }

    public final ObjectNode findParent(String str) {
        return null;
    }

    public final List<JsonNode> findParents(String str, List<JsonNode> list) {
        return list;
    }

    public final JsonNode findValue(String str) {
        return null;
    }

    public final List<JsonNode> findValues(String str, List<JsonNode> list) {
        return list;
    }

    public final List<String> findValuesAsText(String str, List<String> list) {
        return list;
    }

    public final JsonNode get(int i3) {
        return null;
    }

    public final boolean has(int i3) {
        return false;
    }

    public final boolean hasNonNull(int i3) {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(this, asToken()));
        serialize(jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }

    public final JsonNode get(String str) {
        return null;
    }

    public final boolean has(String str) {
        return false;
    }

    public final boolean hasNonNull(String str) {
        return false;
    }

    public final JsonNode path(int i3) {
        return MissingNode.getInstance();
    }

    public final JsonNode path(String str) {
        return MissingNode.getInstance();
    }
}
