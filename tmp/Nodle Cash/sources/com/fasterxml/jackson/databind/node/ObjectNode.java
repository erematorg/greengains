package com.fasterxml.jackson.databind.node;

import android.support.v4.media.session.a;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.JsonNodeFeature;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ObjectNode extends ContainerNode<ObjectNode> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Map<String, JsonNode> _children;

    public ObjectNode(JsonNodeFactory jsonNodeFactory) {
        super(jsonNodeFactory);
        this._children = new LinkedHashMap();
    }

    public JsonNode _at(JsonPointer jsonPointer) {
        return get(jsonPointer.getMatchingProperty());
    }

    public boolean _childrenEqual(ObjectNode objectNode) {
        return this._children.equals(objectNode._children);
    }

    public ObjectNode _put(String str, JsonNode jsonNode) {
        this._children.put(str, jsonNode);
        return this;
    }

    public ArrayNode _withArray(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z2) {
        if (jsonPointer2.matches()) {
            return null;
        }
        JsonNode _at = _at(jsonPointer2);
        if (_at != null && (_at instanceof BaseJsonNode)) {
            ArrayNode _withArray = ((BaseJsonNode) _at)._withArray(jsonPointer, jsonPointer2.tail(), overwriteMode, z2);
            if (_withArray != null) {
                return _withArray;
            }
            _withXxxVerifyReplace(jsonPointer, jsonPointer2, overwriteMode, z2, _at);
        }
        return _withArrayAddTailProperty(jsonPointer2, z2);
    }

    public ArrayNode _withArrayAddTailProperty(JsonPointer jsonPointer, boolean z2) {
        String matchingProperty = jsonPointer.getMatchingProperty();
        JsonPointer tail = jsonPointer.tail();
        return tail.matches() ? putArray(matchingProperty) : (!z2 || !tail.mayMatchElement()) ? putObject(matchingProperty)._withArrayAddTailProperty(tail, z2) : putArray(matchingProperty)._withArrayAddTailElement(tail, z2);
    }

    public ObjectNode _withObject(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z2) {
        if (jsonPointer2.matches()) {
            return this;
        }
        JsonNode _at = _at(jsonPointer2);
        if (_at != null && (_at instanceof BaseJsonNode)) {
            ObjectNode _withObject = ((BaseJsonNode) _at)._withObject(jsonPointer, jsonPointer2.tail(), overwriteMode, z2);
            if (_withObject != null) {
                return _withObject;
            }
            _withXxxVerifyReplace(jsonPointer, jsonPointer2, overwriteMode, z2, _at);
        }
        return _withObjectAddTailProperty(jsonPointer2, z2);
    }

    public ObjectNode _withObjectAddTailProperty(JsonPointer jsonPointer, boolean z2) {
        String matchingProperty = jsonPointer.getMatchingProperty();
        JsonPointer tail = jsonPointer.tail();
        return tail.matches() ? putObject(matchingProperty) : (!z2 || !tail.mayMatchElement()) ? putObject(matchingProperty)._withObjectAddTailProperty(tail, z2) : putArray(matchingProperty)._withObjectAddTailElement(tail, z2);
    }

    public JsonToken asToken() {
        return JsonToken.START_OBJECT;
    }

    public Iterator<JsonNode> elements() {
        return this._children.values().iterator();
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.util.Comparator<com.fasterxml.jackson.databind.JsonNode> r4, com.fasterxml.jackson.databind.JsonNode r5) {
        /*
            r3 = this;
            boolean r0 = r5 instanceof com.fasterxml.jackson.databind.node.ObjectNode
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.fasterxml.jackson.databind.node.ObjectNode r5 = (com.fasterxml.jackson.databind.node.ObjectNode) r5
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r3 = r3._children
            java.util.Map<java.lang.String, com.fasterxml.jackson.databind.JsonNode> r5 = r5._children
            int r0 = r3.size()
            int r2 = r5.size()
            if (r2 == r0) goto L_0x0017
            return r1
        L_0x0017:
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x001f:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0044
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r2 = r0.getKey()
            java.lang.Object r2 = r5.get(r2)
            com.fasterxml.jackson.databind.JsonNode r2 = (com.fasterxml.jackson.databind.JsonNode) r2
            if (r2 == 0) goto L_0x0043
            java.lang.Object r0 = r0.getValue()
            com.fasterxml.jackson.databind.JsonNode r0 = (com.fasterxml.jackson.databind.JsonNode) r0
            boolean r0 = r0.equals(r4, r2)
            if (r0 != 0) goto L_0x001f
        L_0x0043:
            return r1
        L_0x0044:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.ObjectNode.equals(java.util.Comparator, com.fasterxml.jackson.databind.JsonNode):boolean");
    }

    public Iterator<String> fieldNames() {
        return this._children.keySet().iterator();
    }

    public Iterator<Map.Entry<String, JsonNode>> fields() {
        return this._children.entrySet().iterator();
    }

    public List<JsonNode> findParents(String str, List<JsonNode> list) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(this);
            } else {
                list = ((JsonNode) next.getValue()).findParents(str, list);
            }
        }
        return list;
    }

    public JsonNode findValue(String str) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                return (JsonNode) next.getValue();
            }
            JsonNode findValue = ((JsonNode) next.getValue()).findValue(str);
            if (findValue != null) {
                return findValue;
            }
        }
        return null;
    }

    public List<JsonNode> findValues(String str, List<JsonNode> list) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(next.getValue());
            } else {
                list = ((JsonNode) next.getValue()).findValues(str, list);
            }
        }
        return list;
    }

    public List<String> findValuesAsText(String str, List<String> list) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(((JsonNode) next.getValue()).asText());
            } else {
                list = ((JsonNode) next.getValue()).findValuesAsText(str, list);
            }
        }
        return list;
    }

    public JsonNode get(int i3) {
        return null;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.OBJECT;
    }

    public int hashCode() {
        return this._children.hashCode();
    }

    public boolean isEmpty(SerializerProvider serializerProvider) {
        return this._children.isEmpty();
    }

    public final boolean isObject() {
        return true;
    }

    @Deprecated
    public JsonNode put(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return this._children.put(str, jsonNode);
    }

    @Deprecated
    public JsonNode putAll(Map<String, ? extends JsonNode> map) {
        return setAll(map);
    }

    public ArrayNode putArray(String str) {
        ArrayNode arrayNode = arrayNode();
        _put(str, arrayNode);
        return arrayNode;
    }

    public JsonNode putIfAbsent(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return this._children.putIfAbsent(str, jsonNode);
    }

    public ObjectNode putNull(String str) {
        this._children.put(str, nullNode());
        return this;
    }

    public ObjectNode putObject(String str) {
        ObjectNode objectNode = objectNode();
        _put(str, objectNode);
        return objectNode;
    }

    public ObjectNode putPOJO(String str, Object obj) {
        return _put(str, pojoNode(obj));
    }

    public ObjectNode putRawValue(String str, RawValue rawValue) {
        return _put(str, rawValueNode(rawValue));
    }

    public JsonNode remove(String str) {
        return this._children.remove(str);
    }

    public JsonNode replace(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        return this._children.put(str, jsonNode);
    }

    public JsonNode required(String str) {
        JsonNode jsonNode = this._children.get(str);
        return jsonNode != null ? jsonNode : (JsonNode) _reportRequiredViolation("No value for property '%s' of `ObjectNode`", str);
    }

    public ObjectNode retain(Collection<String> collection) {
        this._children.keySet().retainAll(collection);
        return this;
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (serializerProvider != null) {
            boolean isEnabled = serializerProvider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
            boolean z2 = !isEnabled;
            boolean isEnabled2 = serializerProvider.isEnabled((DatatypeFeature) JsonNodeFeature.WRITE_NULL_PROPERTIES);
            boolean z3 = !isEnabled2;
            if (!isEnabled || !isEnabled2) {
                jsonGenerator.writeStartObject(this);
                serializeFilteredContents(jsonGenerator, serializerProvider, z2, z3);
                jsonGenerator.writeEndObject();
                return;
            }
        }
        jsonGenerator.writeStartObject(this);
        for (Map.Entry next : this._children.entrySet()) {
            jsonGenerator.writeFieldName((String) next.getKey());
            ((JsonNode) next.getValue()).serialize(jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeFilteredContents(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, boolean z2, boolean z3) throws IOException {
        for (Map.Entry next : this._children.entrySet()) {
            BaseJsonNode baseJsonNode = (BaseJsonNode) next.getValue();
            if ((!z2 || !baseJsonNode.isArray() || !baseJsonNode.isEmpty(serializerProvider)) && (!z3 || !baseJsonNode.isNull())) {
                jsonGenerator.writeFieldName((String) next.getKey());
                baseJsonNode.serialize(jsonGenerator, serializerProvider);
            }
        }
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        boolean z2;
        boolean z3;
        if (serializerProvider != null) {
            z3 = !serializerProvider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
            z2 = !serializerProvider.isEnabled((DatatypeFeature) JsonNodeFeature.WRITE_NULL_PROPERTIES);
        } else {
            z3 = false;
            z2 = false;
        }
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(this, JsonToken.START_OBJECT));
        if (z3 || z2) {
            serializeFilteredContents(jsonGenerator, serializerProvider, z3, z2);
        } else {
            for (Map.Entry next : this._children.entrySet()) {
                jsonGenerator.writeFieldName((String) next.getKey());
                ((JsonNode) next.getValue()).serialize(jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }

    public <T extends JsonNode> T set(String str, JsonNode jsonNode) {
        if (jsonNode == null) {
            jsonNode = nullNode();
        }
        this._children.put(str, jsonNode);
        return this;
    }

    public <T extends JsonNode> T setAll(Map<String, ? extends JsonNode> map) {
        for (Map.Entry next : map.entrySet()) {
            Object obj = (JsonNode) next.getValue();
            if (obj == null) {
                obj = nullNode();
            }
            this._children.put(next.getKey(), obj);
        }
        return this;
    }

    public int size() {
        return this._children.size();
    }

    public <T extends JsonNode> T without(String str) {
        this._children.remove(str);
        return this;
    }

    public ObjectNode deepCopy() {
        ObjectNode objectNode = new ObjectNode(this._nodeFactory);
        for (Map.Entry next : this._children.entrySet()) {
            objectNode._children.put(next.getKey(), ((JsonNode) next.getValue()).deepCopy());
        }
        return objectNode;
    }

    public ObjectNode findParent(String str) {
        for (Map.Entry next : this._children.entrySet()) {
            if (str.equals(next.getKey())) {
                return this;
            }
            JsonNode findParent = ((JsonNode) next.getValue()).findParent(str);
            if (findParent != null) {
                return (ObjectNode) findParent;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this._children.isEmpty();
    }

    @Deprecated
    public JsonNode putAll(ObjectNode objectNode) {
        return setAll(objectNode);
    }

    public ObjectNode remove(Collection<String> collection) {
        this._children.keySet().removeAll(collection);
        return this;
    }

    public ObjectNode removeAll() {
        this._children.clear();
        return this;
    }

    public ObjectNode retain(String... strArr) {
        return retain((Collection<String>) Arrays.asList(strArr));
    }

    @Deprecated
    public ObjectNode with(String str) {
        JsonPointer _jsonPointerIfValid = _jsonPointerIfValid(str);
        if (_jsonPointerIfValid != null) {
            return withObject(_jsonPointerIfValid);
        }
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode == null) {
            ObjectNode objectNode = objectNode();
            this._children.put(str, objectNode);
            return objectNode;
        } else if (jsonNode instanceof ObjectNode) {
            return (ObjectNode) jsonNode;
        } else {
            StringBuilder w2 = a.w("Property '", str, "' has value that is not of type `ObjectNode` (but `");
            w2.append(jsonNode.getClass().getName());
            w2.append("`)");
            throw new UnsupportedOperationException(w2.toString());
        }
    }

    public ArrayNode withArray(String str) {
        JsonPointer _jsonPointerIfValid = _jsonPointerIfValid(str);
        if (_jsonPointerIfValid != null) {
            return withArray(_jsonPointerIfValid);
        }
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode == null) {
            ArrayNode arrayNode = arrayNode();
            this._children.put(str, arrayNode);
            return arrayNode;
        } else if (jsonNode instanceof ArrayNode) {
            return (ArrayNode) jsonNode;
        } else {
            StringBuilder w2 = a.w("Property '", str, "' has value that is not of type `ArrayNode` (but `");
            w2.append(jsonNode.getClass().getName());
            w2.append("`)");
            throw new UnsupportedOperationException(w2.toString());
        }
    }

    public <T extends JsonNode> T without(Collection<String> collection) {
        this._children.keySet().removeAll(collection);
        return this;
    }

    public ObjectNode(JsonNodeFactory jsonNodeFactory, Map<String, JsonNode> map) {
        super(jsonNodeFactory);
        this._children = map;
    }

    public JsonNode path(int i3) {
        return MissingNode.getInstance();
    }

    public ObjectNode put(String str, short s3) {
        return _put(str, numberNode(s3));
    }

    public JsonNode get(String str) {
        return this._children.get(str);
    }

    public JsonNode path(String str) {
        JsonNode jsonNode = this._children.get(str);
        if (jsonNode != null) {
            return jsonNode;
        }
        return MissingNode.getInstance();
    }

    public ObjectNode put(String str, Short sh) {
        JsonNode jsonNode;
        if (sh == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = numberNode(sh.shortValue());
        }
        return _put(str, jsonNode);
    }

    public <T extends JsonNode> T setAll(ObjectNode objectNode) {
        this._children.putAll(objectNode._children);
        return this;
    }

    public ObjectNode put(String str, int i3) {
        return _put(str, numberNode(i3));
    }

    public ObjectNode put(String str, Integer num) {
        JsonNode jsonNode;
        if (num == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = numberNode(num.intValue());
        }
        return _put(str, jsonNode);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ObjectNode)) {
            return _childrenEqual((ObjectNode) obj);
        }
        return false;
    }

    public ObjectNode put(String str, long j2) {
        return _put(str, numberNode(j2));
    }

    public ObjectNode put(String str, Long l2) {
        JsonNode jsonNode;
        if (l2 == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = numberNode(l2.longValue());
        }
        return _put(str, jsonNode);
    }

    public ObjectNode put(String str, float f2) {
        return _put(str, numberNode(f2));
    }

    public ObjectNode put(String str, Float f2) {
        JsonNode jsonNode;
        if (f2 == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = numberNode(f2.floatValue());
        }
        return _put(str, jsonNode);
    }

    public ObjectNode put(String str, double d2) {
        return _put(str, numberNode(d2));
    }

    public ObjectNode put(String str, Double d2) {
        JsonNode jsonNode;
        if (d2 == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = numberNode(d2.doubleValue());
        }
        return _put(str, jsonNode);
    }

    public ObjectNode put(String str, BigDecimal bigDecimal) {
        JsonNode jsonNode;
        if (bigDecimal == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = numberNode(bigDecimal);
        }
        return _put(str, jsonNode);
    }

    public ObjectNode put(String str, BigInteger bigInteger) {
        JsonNode jsonNode;
        if (bigInteger == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = numberNode(bigInteger);
        }
        return _put(str, jsonNode);
    }

    public ObjectNode put(String str, String str2) {
        JsonNode jsonNode;
        if (str2 == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = textNode(str2);
        }
        return _put(str, jsonNode);
    }

    public ObjectNode put(String str, boolean z2) {
        return _put(str, booleanNode(z2));
    }

    public ObjectNode put(String str, Boolean bool) {
        JsonNode jsonNode;
        if (bool == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = booleanNode(bool.booleanValue());
        }
        return _put(str, jsonNode);
    }

    public ObjectNode put(String str, byte[] bArr) {
        JsonNode jsonNode;
        if (bArr == null) {
            jsonNode = nullNode();
        } else {
            jsonNode = binaryNode(bArr);
        }
        return _put(str, jsonNode);
    }
}
