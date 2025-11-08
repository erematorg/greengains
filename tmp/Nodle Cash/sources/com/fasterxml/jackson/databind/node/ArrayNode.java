package com.fasterxml.jackson.databind.node;

import A.a;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayNode extends ContainerNode<ArrayNode> implements Serializable {
    private static final long serialVersionUID = 1;
    private final List<JsonNode> _children;

    public ArrayNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new ArrayList();
    }

    public ArrayNode _add(JsonNode jsonNode) {
        this._children.add(jsonNode);
        return this;
    }

    public JsonNode _at(JsonPointer jsonPointer) {
        return get(jsonPointer.getMatchingIndex());
    }

    public boolean _childrenEqual(ArrayNode arrayNode) {
        return this._children.equals(arrayNode._children);
    }

    public ArrayNode _insert(int i3, JsonNode jsonNode) {
        if (i3 < 0) {
            this._children.add(0, jsonNode);
        } else if (i3 >= this._children.size()) {
            this._children.add(jsonNode);
        } else {
            this._children.add(i3, jsonNode);
        }
        return this;
    }

    public ArrayNode _set(int i3, JsonNode jsonNode) {
        if (i3 < 0 || i3 >= this._children.size()) {
            StringBuilder o3 = a.o(i3, "Illegal index ", ", array size ");
            o3.append(size());
            throw new IndexOutOfBoundsException(o3.toString());
        }
        this._children.set(i3, jsonNode);
        return this;
    }

    public ArrayNode _withArray(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z2) {
        if (jsonPointer2.matches()) {
            return this;
        }
        JsonNode _at = _at(jsonPointer2);
        if (_at != null && (_at instanceof BaseJsonNode)) {
            ArrayNode _withArray = ((BaseJsonNode) _at)._withArray(jsonPointer, jsonPointer2.tail(), overwriteMode, z2);
            if (_withArray != null) {
                return _withArray;
            }
            _withXxxVerifyReplace(jsonPointer, jsonPointer2, overwriteMode, z2, _at);
        }
        return _withArrayAddTailElement(jsonPointer2, z2);
    }

    public ArrayNode _withArrayAddTailElement(JsonPointer jsonPointer, boolean z2) {
        int matchingIndex = jsonPointer.getMatchingIndex();
        if (matchingIndex < 0) {
            return null;
        }
        JsonPointer tail = jsonPointer.tail();
        if (tail.matches()) {
            ArrayNode arrayNode = arrayNode();
            _withXxxSetArrayElement(matchingIndex, arrayNode);
            return arrayNode;
        } else if (!z2 || !tail.mayMatchElement()) {
            ArrayNode arrayNode2 = arrayNode();
            _withXxxSetArrayElement(matchingIndex, arrayNode2);
            return arrayNode2._withArrayAddTailElement(tail, z2);
        } else {
            ArrayNode arrayNode3 = arrayNode();
            _withXxxSetArrayElement(matchingIndex, arrayNode3);
            return arrayNode3._withArrayAddTailElement(tail, z2);
        }
    }

    public ObjectNode _withObject(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z2) {
        if (jsonPointer2.matches()) {
            return null;
        }
        JsonNode _at = _at(jsonPointer2);
        if (_at != null && (_at instanceof BaseJsonNode)) {
            ObjectNode _withObject = ((BaseJsonNode) _at)._withObject(jsonPointer, jsonPointer2.tail(), overwriteMode, z2);
            if (_withObject != null) {
                return _withObject;
            }
            _withXxxVerifyReplace(jsonPointer, jsonPointer2, overwriteMode, z2, _at);
        }
        return _withObjectAddTailElement(jsonPointer2, z2);
    }

    public ObjectNode _withObjectAddTailElement(JsonPointer jsonPointer, boolean z2) {
        int matchingIndex = jsonPointer.getMatchingIndex();
        if (matchingIndex < 0) {
            return null;
        }
        JsonPointer tail = jsonPointer.tail();
        if (tail.matches()) {
            ObjectNode objectNode = objectNode();
            _withXxxSetArrayElement(matchingIndex, objectNode);
            return objectNode;
        } else if (!z2 || !tail.mayMatchElement()) {
            ObjectNode objectNode2 = objectNode();
            _withXxxSetArrayElement(matchingIndex, objectNode2);
            return objectNode2._withObjectAddTailProperty(tail, z2);
        } else {
            ArrayNode arrayNode = arrayNode();
            _withXxxSetArrayElement(matchingIndex, arrayNode);
            return arrayNode._withObjectAddTailElement(tail, z2);
        }
    }

    public void _withXxxSetArrayElement(int i3, JsonNode jsonNode) {
        if (i3 >= size()) {
            int maxElementIndexForInsert = this._nodeFactory.getMaxElementIndexForInsert();
            if (i3 > maxElementIndexForInsert) {
                _reportWrongNodeOperation("Too big Array index (%d; max %d) to use for insert with `JsonPointer`", Integer.valueOf(i3), Integer.valueOf(maxElementIndexForInsert));
            }
            while (i3 >= size()) {
                addNull();
            }
        }
        set(i3, jsonNode);
    }

    public ArrayNode add(JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _add(jsonNode);
        return this;
    }

    public ArrayNode addAll(ArrayNode arrayNode) {
        this._children.addAll(arrayNode._children);
        return this;
    }

    public ArrayNode addArray() {
        ArrayNode arrayNode = arrayNode();
        _add(arrayNode);
        return arrayNode;
    }

    public ArrayNode addNull() {
        return _add(nullNode());
    }

    public ObjectNode addObject() {
        ObjectNode objectNode = objectNode();
        _add(objectNode);
        return objectNode;
    }

    public ArrayNode addPOJO(Object obj) {
        return _add(obj == null ? nullNode() : pojoNode(obj));
    }

    public ArrayNode addRawValue(RawValue rawValue) {
        return _add(rawValue == null ? nullNode() : rawValueNode(rawValue));
    }

    public JsonToken asToken() {
        return JsonToken.START_ARRAY;
    }

    public Iterator<JsonNode> elements() {
        return this._children.iterator();
    }

    public boolean equals(Comparator<JsonNode> comparator, JsonNode jsonNode) {
        if (!(jsonNode instanceof ArrayNode)) {
            return false;
        }
        ArrayNode arrayNode = (ArrayNode) jsonNode;
        int size = this._children.size();
        if (arrayNode.size() != size) {
            return false;
        }
        List<JsonNode> list = this._children;
        List<JsonNode> list2 = arrayNode._children;
        for (int i3 = 0; i3 < size; i3++) {
            if (!list.get(i3).equals(comparator, list2.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        for (JsonNode findParents : this._children) {
            list = findParents.findParents(str, list);
        }
        return list;
    }

    public JsonNode findValue(String str) {
        for (JsonNode findValue : this._children) {
            JsonNode findValue2 = findValue.findValue(str);
            if (findValue2 != null) {
                return findValue2;
            }
        }
        return null;
    }

    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        for (JsonNode findValues : this._children) {
            list = findValues.findValues(str, list);
        }
        return list;
    }

    public List<String> findValuesAsText(String str, List<String> list) {
        for (JsonNode findValuesAsText : this._children) {
            list = findValuesAsText.findValuesAsText(str, list);
        }
        return list;
    }

    public JsonNode get(String str) {
        return null;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.ARRAY;
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public ArrayNode insert(int i3, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        _insert(i3, jsonNode);
        return this;
    }

    public ArrayNode insertArray(int i3) {
        ArrayNode arrayNode = arrayNode();
        _insert(i3, arrayNode);
        return arrayNode;
    }

    public ArrayNode insertNull(int i3) {
        return _insert(i3, nullNode());
    }

    public ObjectNode insertObject(int i3) {
        ObjectNode objectNode = objectNode();
        _insert(i3, objectNode);
        return objectNode;
    }

    public ArrayNode insertPOJO(int i3, Object obj) {
        return _insert(i3, obj == null ? nullNode() : pojoNode(obj));
    }

    public ArrayNode insertRawValue(int i3, RawValue rawValue) {
        return _insert(i3, rawValue == null ? nullNode() : rawValueNode(rawValue));
    }

    public boolean isArray() {
        return true;
    }

    public boolean isEmpty(SerializerProvider serializerProvider) {
        return this._children.isEmpty();
    }

    public JsonNode remove(int i3) {
        if (i3 < 0 || i3 >= this._children.size()) {
            return null;
        }
        return this._children.remove(i3);
    }

    public JsonNode required(int i3) {
        return (i3 < 0 || i3 >= this._children.size()) ? (JsonNode) _reportRequiredViolation("No value at index #%d [0, %d) of `ArrayNode`", Integer.valueOf(i3), Integer.valueOf(this._children.size())) : this._children.get(i3);
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<JsonNode> list = this._children;
        int size = list.size();
        jsonGenerator.writeStartArray(this, size);
        for (int i3 = 0; i3 < size; i3++) {
            list.get(i3).serialize(jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(this, JsonToken.START_ARRAY));
        Iterator<JsonNode> it = this._children.iterator();
        while (it.hasNext()) {
            ((BaseJsonNode) it.next()).serialize(jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }

    public JsonNode set(int i3, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        if (i3 >= 0 && i3 < this._children.size()) {
            return this._children.set(i3, jsonNode);
        }
        StringBuilder o3 = a.o(i3, "Illegal index ", ", array size ");
        o3.append(size());
        throw new IndexOutOfBoundsException(o3.toString());
    }

    public ArrayNode setNull(int i3) {
        return _set(i3, nullNode());
    }

    public ArrayNode setPOJO(int i3, Object obj) {
        return _set(i3, obj == null ? nullNode() : pojoNode(obj));
    }

    public ArrayNode setRawValue(int i3, RawValue rawValue) {
        return _set(i3, rawValue == null ? nullNode() : rawValueNode(rawValue));
    }

    public int size() {
        return this._children.size();
    }

    public ArrayNode addAll(Collection<? extends JsonNode> collection) {
        for (JsonNode add : collection) {
            add(add);
        }
        return this;
    }

    public ArrayNode deepCopy() {
        ArrayNode arrayNode = new ArrayNode(this._nodeFactory);
        for (JsonNode deepCopy : this._children) {
            arrayNode._children.add(deepCopy.deepCopy());
        }
        return arrayNode;
    }

    public ObjectNode findParent(String str) {
        for (JsonNode findParent : this._children) {
            JsonNode findParent2 = findParent.findParent(str);
            if (findParent2 != null) {
                return (ObjectNode) findParent2;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this._children.isEmpty();
    }

    public ArrayNode removeAll() {
        this._children.clear();
        return this;
    }

    @Deprecated
    public ObjectNode with(String str) {
        JsonPointer _jsonPointerIfValid = _jsonPointerIfValid(str);
        if (_jsonPointerIfValid != null) {
            return withObject(_jsonPointerIfValid);
        }
        return (ObjectNode) super.with(str);
    }

    public ArrayNode withArray(String str) {
        JsonPointer _jsonPointerIfValid = _jsonPointerIfValid(str);
        if (_jsonPointerIfValid != null) {
            return withArray(_jsonPointerIfValid);
        }
        return (ArrayNode) super.withArray(str);
    }

    public ArrayNode(JsonNodeFactory jsonNodeFactory, int i3) {
        super(jsonNodeFactory);
        this._children = new ArrayList(i3);
    }

    public ArrayNode add(short s3) {
        return _add(numberNode(s3));
    }

    public ArrayNode insert(int i3, short s3) {
        return _insert(i3, numberNode(s3));
    }

    public JsonNode path(String str) {
        return MissingNode.getInstance();
    }

    public ArrayNode add(Short sh) {
        return _add(sh == null ? nullNode() : numberNode(sh.shortValue()));
    }

    public JsonNode get(int i3) {
        if (i3 < 0 || i3 >= this._children.size()) {
            return null;
        }
        return this._children.get(i3);
    }

    public ArrayNode insert(int i3, Short sh) {
        return _insert(i3, sh == null ? nullNode() : numberNode(sh.shortValue()));
    }

    public JsonNode path(int i3) {
        if (i3 < 0 || i3 >= this._children.size()) {
            return MissingNode.getInstance();
        }
        return this._children.get(i3);
    }

    public ArrayNode(JsonNodeFactory jsonNodeFactory, List<JsonNode> list) {
        super(jsonNodeFactory);
        this._children = list;
    }

    public ArrayNode add(int i3) {
        return _add(numberNode(i3));
    }

    public ArrayNode insert(int i3, int i4) {
        return _insert(i3, numberNode(i4));
    }

    public ArrayNode add(Integer num) {
        return _add(num == null ? nullNode() : numberNode(num.intValue()));
    }

    public ArrayNode insert(int i3, Integer num) {
        return _insert(i3, num == null ? nullNode() : numberNode(num.intValue()));
    }

    public ArrayNode add(long j2) {
        return _add(numberNode(j2));
    }

    public ArrayNode insert(int i3, long j2) {
        return _insert(i3, numberNode(j2));
    }

    public ArrayNode add(Long l2) {
        return _add(l2 == null ? nullNode() : numberNode(l2.longValue()));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ArrayNode)) {
            return this._children.equals(((ArrayNode) obj)._children);
        }
        return false;
    }

    public ArrayNode insert(int i3, Long l2) {
        return _insert(i3, l2 == null ? nullNode() : numberNode(l2.longValue()));
    }

    public ArrayNode add(float f2) {
        return _add(numberNode(f2));
    }

    public ArrayNode insert(int i3, float f2) {
        return _insert(i3, numberNode(f2));
    }

    public ArrayNode add(Float f2) {
        return _add(f2 == null ? nullNode() : numberNode(f2.floatValue()));
    }

    public ArrayNode insert(int i3, Float f2) {
        return _insert(i3, f2 == null ? nullNode() : numberNode(f2.floatValue()));
    }

    public ArrayNode add(double d2) {
        return _add(numberNode(d2));
    }

    public ArrayNode insert(int i3, double d2) {
        return _insert(i3, numberNode(d2));
    }

    public ArrayNode set(int i3, short s3) {
        return _set(i3, numberNode(s3));
    }

    public ArrayNode add(Double d2) {
        return _add(d2 == null ? nullNode() : numberNode(d2.doubleValue()));
    }

    public ArrayNode insert(int i3, Double d2) {
        return _insert(i3, d2 == null ? nullNode() : numberNode(d2.doubleValue()));
    }

    public ArrayNode set(int i3, Short sh) {
        return _set(i3, sh == null ? nullNode() : numberNode(sh.shortValue()));
    }

    public ArrayNode add(BigDecimal bigDecimal) {
        return _add(bigDecimal == null ? nullNode() : numberNode(bigDecimal));
    }

    public ArrayNode insert(int i3, BigDecimal bigDecimal) {
        return _insert(i3, bigDecimal == null ? nullNode() : numberNode(bigDecimal));
    }

    public ArrayNode set(int i3, int i4) {
        return _set(i3, numberNode(i4));
    }

    public ArrayNode add(BigInteger bigInteger) {
        return _add(bigInteger == null ? nullNode() : numberNode(bigInteger));
    }

    public ArrayNode insert(int i3, BigInteger bigInteger) {
        return _insert(i3, bigInteger == null ? nullNode() : numberNode(bigInteger));
    }

    public ArrayNode set(int i3, Integer num) {
        return _set(i3, num == null ? nullNode() : numberNode(num.intValue()));
    }

    public ArrayNode add(String str) {
        return _add(str == null ? nullNode() : textNode(str));
    }

    public ArrayNode insert(int i3, String str) {
        return _insert(i3, str == null ? nullNode() : textNode(str));
    }

    public ArrayNode set(int i3, long j2) {
        return _set(i3, numberNode(j2));
    }

    public ArrayNode add(boolean z2) {
        return _add(booleanNode(z2));
    }

    public ArrayNode insert(int i3, boolean z2) {
        return _insert(i3, booleanNode(z2));
    }

    public ArrayNode set(int i3, Long l2) {
        return _set(i3, l2 == null ? nullNode() : numberNode(l2.longValue()));
    }

    public ArrayNode add(Boolean bool) {
        return _add(bool == null ? nullNode() : booleanNode(bool.booleanValue()));
    }

    public ArrayNode insert(int i3, Boolean bool) {
        if (bool == null) {
            return insertNull(i3);
        }
        return _insert(i3, booleanNode(bool.booleanValue()));
    }

    public ArrayNode set(int i3, float f2) {
        return _set(i3, numberNode(f2));
    }

    public ArrayNode add(byte[] bArr) {
        return _add(bArr == null ? nullNode() : binaryNode(bArr));
    }

    public ArrayNode set(int i3, Float f2) {
        return _set(i3, f2 == null ? nullNode() : numberNode(f2.floatValue()));
    }

    public ArrayNode insert(int i3, byte[] bArr) {
        if (bArr == null) {
            return insertNull(i3);
        }
        return _insert(i3, binaryNode(bArr));
    }

    public ArrayNode set(int i3, double d2) {
        return _set(i3, numberNode(d2));
    }

    public ArrayNode set(int i3, Double d2) {
        return _set(i3, d2 == null ? nullNode() : numberNode(d2.doubleValue()));
    }

    public ArrayNode set(int i3, BigDecimal bigDecimal) {
        return _set(i3, bigDecimal == null ? nullNode() : numberNode(bigDecimal));
    }

    public ArrayNode set(int i3, BigInteger bigInteger) {
        return _set(i3, bigInteger == null ? nullNode() : numberNode(bigInteger));
    }

    public ArrayNode set(int i3, String str) {
        return _set(i3, str == null ? nullNode() : textNode(str));
    }

    public ArrayNode set(int i3, boolean z2) {
        return _set(i3, booleanNode(z2));
    }

    public ArrayNode set(int i3, Boolean bool) {
        return _set(i3, bool == null ? nullNode() : booleanNode(bool.booleanValue()));
    }

    public ArrayNode set(int i3, byte[] bArr) {
        return _set(i3, bArr == null ? nullNode() : binaryNode(bArr));
    }
}
