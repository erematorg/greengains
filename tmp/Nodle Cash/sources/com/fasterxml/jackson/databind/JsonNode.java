package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class JsonNode extends JsonSerializable.Base implements TreeNode, Iterable<JsonNode> {

    /* renamed from: com.fasterxml.jackson.databind.JsonNode$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.fasterxml.jackson.databind.node.JsonNodeType[] r0 = com.fasterxml.jackson.databind.node.JsonNodeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType = r0
                com.fasterxml.jackson.databind.node.JsonNodeType r1 = com.fasterxml.jackson.databind.node.JsonNodeType.ARRAY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.databind.node.JsonNodeType r1 = com.fasterxml.jackson.databind.node.JsonNodeType.OBJECT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.databind.node.JsonNodeType r1 = com.fasterxml.jackson.databind.node.JsonNodeType.MISSING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.JsonNode.AnonymousClass1.<clinit>():void");
        }
    }

    public enum OverwriteMode {
        NONE,
        NULLS,
        SCALARS,
        ALL
    }

    public abstract JsonNode _at(JsonPointer jsonPointer);

    public <T> T _reportRequiredViolation(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    public <T extends JsonNode> T _this() {
        return this;
    }

    public boolean asBoolean(boolean z2) {
        return z2;
    }

    public double asDouble(double d2) {
        return d2;
    }

    public int asInt(int i3) {
        return i3;
    }

    public long asLong(long j2) {
        return j2;
    }

    public abstract String asText();

    public String asText(String str) {
        String asText = asText();
        return asText == null ? str : asText;
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.ZERO;
    }

    public byte[] binaryValue() throws IOException {
        return null;
    }

    public boolean booleanValue() {
        return false;
    }

    public boolean canConvertToExactIntegral() {
        return isIntegralNumber();
    }

    public boolean canConvertToInt() {
        return false;
    }

    public boolean canConvertToLong() {
        return false;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.ZERO;
    }

    public abstract <T extends JsonNode> T deepCopy();

    public double doubleValue() {
        return 0.0d;
    }

    public Iterator<JsonNode> elements() {
        return ClassUtil.emptyIterator();
    }

    public abstract boolean equals(Object obj);

    public boolean equals(Comparator<JsonNode> comparator, JsonNode jsonNode) {
        return comparator.compare(this, jsonNode) == 0;
    }

    public Iterator<String> fieldNames() {
        return ClassUtil.emptyIterator();
    }

    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return ClassUtil.emptyIterator();
    }

    public abstract JsonNode findParent(String str);

    public final List<JsonNode> findParents(String str) {
        List<JsonNode> findParents = findParents(str, (List<JsonNode>) null);
        return findParents == null ? Collections.emptyList() : findParents;
    }

    public abstract List<JsonNode> findParents(String str, List<JsonNode> list);

    public abstract JsonNode findPath(String str);

    public abstract JsonNode findValue(String str);

    public final List<JsonNode> findValues(String str) {
        List<JsonNode> findValues = findValues(str, (List<JsonNode>) null);
        return findValues == null ? Collections.emptyList() : findValues;
    }

    public abstract List<JsonNode> findValues(String str, List<JsonNode> list);

    public final List<String> findValuesAsText(String str) {
        List<String> findValuesAsText = findValuesAsText(str, (List<String>) null);
        return findValuesAsText == null ? Collections.emptyList() : findValuesAsText;
    }

    public abstract List<String> findValuesAsText(String str, List<String> list);

    public float floatValue() {
        return 0.0f;
    }

    public abstract JsonNode get(int i3);

    public JsonNode get(String str) {
        return null;
    }

    public abstract JsonNodeType getNodeType();

    public boolean has(String str) {
        return get(str) != null;
    }

    public boolean hasNonNull(String str) {
        JsonNode jsonNode = get(str);
        return jsonNode != null && !jsonNode.isNull();
    }

    public int intValue() {
        return 0;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isBigDecimal() {
        return false;
    }

    public boolean isBigInteger() {
        return false;
    }

    public final boolean isBinary() {
        return getNodeType() == JsonNodeType.BINARY;
    }

    public final boolean isBoolean() {
        return getNodeType() == JsonNodeType.BOOLEAN;
    }

    public final boolean isContainerNode() {
        JsonNodeType nodeType = getNodeType();
        return nodeType == JsonNodeType.OBJECT || nodeType == JsonNodeType.ARRAY;
    }

    public boolean isDouble() {
        return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFloat() {
        return false;
    }

    public boolean isFloatingPointNumber() {
        return false;
    }

    public boolean isInt() {
        return false;
    }

    public boolean isIntegralNumber() {
        return false;
    }

    public boolean isLong() {
        return false;
    }

    public boolean isMissingNode() {
        return false;
    }

    public final boolean isNull() {
        return getNodeType() == JsonNodeType.NULL;
    }

    public final boolean isNumber() {
        return getNodeType() == JsonNodeType.NUMBER;
    }

    public boolean isObject() {
        return false;
    }

    public final boolean isPojo() {
        return getNodeType() == JsonNodeType.POJO;
    }

    public boolean isShort() {
        return false;
    }

    public final boolean isTextual() {
        return getNodeType() == JsonNodeType.STRING;
    }

    public final boolean isValueNode() {
        int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$node$JsonNodeType[getNodeType().ordinal()];
        return (i3 == 1 || i3 == 2 || i3 == 3) ? false : true;
    }

    public final Iterator<JsonNode> iterator() {
        return elements();
    }

    public long longValue() {
        return 0;
    }

    public Number numberValue() {
        return null;
    }

    public abstract JsonNode path(int i3);

    public abstract JsonNode path(String str);

    public <T extends JsonNode> T require() throws IllegalArgumentException {
        return _this();
    }

    public <T extends JsonNode> T requireNonNull() throws IllegalArgumentException {
        return _this();
    }

    public JsonNode required(String str) throws IllegalArgumentException {
        return (JsonNode) _reportRequiredViolation("Node of type `%s` has no fields", getClass().getName());
    }

    public JsonNode requiredAt(String str) throws IllegalArgumentException {
        return requiredAt(JsonPointer.compile(str));
    }

    public short shortValue() {
        return 0;
    }

    public int size() {
        return 0;
    }

    public String textValue() {
        return null;
    }

    public String toPrettyString() {
        return toString();
    }

    public abstract String toString();

    @Deprecated
    public <T extends JsonNode> T with(String str) {
        throw new UnsupportedOperationException("`JsonNode` not of type `ObjectNode` (but " + getClass().getName() + "), cannot call `with(String)` on it");
    }

    public <T extends JsonNode> T withArray(String str) {
        throw new UnsupportedOperationException("`JsonNode` not of type `ObjectNode` (but `" + getClass().getName() + ")`, cannot call `withArray()` on it");
    }

    public final ObjectNode withObject(String str) {
        return withObject(JsonPointer.compile(str));
    }

    public boolean asBoolean() {
        return asBoolean(false);
    }

    public double asDouble() {
        return asDouble(0.0d);
    }

    public int asInt() {
        return asInt(0);
    }

    public long asLong() {
        return asLong(0);
    }

    public boolean has(int i3) {
        return get(i3) != null;
    }

    public JsonNode required(int i3) throws IllegalArgumentException {
        return (JsonNode) _reportRequiredViolation("Node of type `%s` has no indexed values", getClass().getName());
    }

    public final JsonNode requiredAt(JsonPointer jsonPointer) throws IllegalArgumentException {
        JsonNode jsonNode = this;
        for (JsonPointer jsonPointer2 = jsonPointer; !jsonPointer2.matches(); jsonPointer2 = jsonPointer2.tail()) {
            jsonNode = jsonNode._at(jsonPointer2);
            if (jsonNode == null) {
                _reportRequiredViolation("No node at '%s' (unmatched part: '%s')", jsonPointer, jsonPointer2);
            }
        }
        return jsonNode;
    }

    public final ObjectNode withObject(String str, OverwriteMode overwriteMode, boolean z2) {
        return withObject(JsonPointer.compile(str), overwriteMode, z2);
    }

    public final JsonNode at(JsonPointer jsonPointer) {
        if (jsonPointer.matches()) {
            return this;
        }
        JsonNode _at = _at(jsonPointer);
        if (_at == null) {
            return MissingNode.getInstance();
        }
        return _at.at(jsonPointer.tail());
    }

    public boolean hasNonNull(int i3) {
        JsonNode jsonNode = get(i3);
        return jsonNode != null && !jsonNode.isNull();
    }

    public ArrayNode withArray(String str, OverwriteMode overwriteMode, boolean z2) {
        return withArray(JsonPointer.compile(str), overwriteMode, z2);
    }

    public final ObjectNode withObject(JsonPointer jsonPointer) {
        return withObject(jsonPointer, OverwriteMode.NULLS, true);
    }

    public final ArrayNode withArray(JsonPointer jsonPointer) {
        return withArray(jsonPointer, OverwriteMode.NULLS, true);
    }

    public ObjectNode withObject(JsonPointer jsonPointer, OverwriteMode overwriteMode, boolean z2) {
        throw new UnsupportedOperationException("`withObject(JsonPointer)` not implemented by `" + getClass().getName() + "`");
    }

    public ArrayNode withArray(JsonPointer jsonPointer, OverwriteMode overwriteMode, boolean z2) {
        throw new UnsupportedOperationException("`withArray(JsonPointer)` not implemented by ".concat(getClass().getName()));
    }

    public final JsonNode at(String str) {
        return at(JsonPointer.compile(str));
    }
}
