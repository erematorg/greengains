package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.util.RawValue;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ContainerNode<T extends ContainerNode<T>> extends BaseJsonNode implements JsonNodeCreator {
    private static final long serialVersionUID = 1;
    protected final JsonNodeFactory _nodeFactory;

    public ContainerNode(JsonNodeFactory jsonNodeFactory) {
        this._nodeFactory = jsonNodeFactory;
    }

    public abstract ObjectNode _withObject(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z2);

    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    public String asText() {
        return "";
    }

    public abstract JsonToken asToken();

    public abstract JsonNode get(int i3);

    public abstract JsonNode get(String str);

    public JsonNode missingNode() {
        return this._nodeFactory.missingNode();
    }

    public final ObjectNode objectNode() {
        return this._nodeFactory.objectNode();
    }

    public final ValueNode pojoNode(Object obj) {
        return this._nodeFactory.pojoNode(obj);
    }

    public final ValueNode rawValueNode(RawValue rawValue) {
        return this._nodeFactory.rawValueNode(rawValue);
    }

    public abstract T removeAll();

    public abstract int size();

    public final ArrayNode arrayNode(int i3) {
        return this._nodeFactory.arrayNode(i3);
    }

    public final BooleanNode booleanNode(boolean z2) {
        return this._nodeFactory.booleanNode(z2);
    }

    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }

    public final TextNode textNode(String str) {
        return this._nodeFactory.textNode(str);
    }

    public ContainerNode() {
        this._nodeFactory = null;
    }

    public final BinaryNode binaryNode(byte[] bArr) {
        return this._nodeFactory.binaryNode(bArr);
    }

    public final BinaryNode binaryNode(byte[] bArr, int i3, int i4) {
        return this._nodeFactory.binaryNode(bArr, i3, i4);
    }

    public final NumericNode numberNode(byte b3) {
        return this._nodeFactory.numberNode(b3);
    }

    public final NumericNode numberNode(short s3) {
        return this._nodeFactory.numberNode(s3);
    }

    public final NumericNode numberNode(int i3) {
        return this._nodeFactory.numberNode(i3);
    }

    public final NumericNode numberNode(long j2) {
        return this._nodeFactory.numberNode(j2);
    }

    public final NumericNode numberNode(float f2) {
        return this._nodeFactory.numberNode(f2);
    }

    public final NumericNode numberNode(double d2) {
        return this._nodeFactory.numberNode(d2);
    }

    public final ValueNode numberNode(BigInteger bigInteger) {
        return this._nodeFactory.numberNode(bigInteger);
    }

    public final ValueNode numberNode(BigDecimal bigDecimal) {
        return this._nodeFactory.numberNode(bigDecimal);
    }

    public final ValueNode numberNode(Byte b3) {
        return this._nodeFactory.numberNode(b3);
    }

    public final ValueNode numberNode(Short sh) {
        return this._nodeFactory.numberNode(sh);
    }

    public final ValueNode numberNode(Integer num) {
        return this._nodeFactory.numberNode(num);
    }

    public final ValueNode numberNode(Long l2) {
        return this._nodeFactory.numberNode(l2);
    }

    public final ValueNode numberNode(Float f2) {
        return this._nodeFactory.numberNode(f2);
    }

    public final ValueNode numberNode(Double d2) {
        return this._nodeFactory.numberNode(d2);
    }
}
