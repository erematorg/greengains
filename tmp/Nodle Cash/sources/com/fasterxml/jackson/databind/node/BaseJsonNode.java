package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.io.Serializable;

public abstract class BaseJsonNode extends JsonNode implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: com.fasterxml.jackson.databind.node.BaseJsonNode$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.fasterxml.jackson.databind.JsonNode$OverwriteMode[] r0 = com.fasterxml.jackson.databind.JsonNode.OverwriteMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode = r0
                com.fasterxml.jackson.databind.JsonNode$OverwriteMode r1 = com.fasterxml.jackson.databind.JsonNode.OverwriteMode.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.databind.JsonNode$OverwriteMode r1 = com.fasterxml.jackson.databind.JsonNode.OverwriteMode.NULLS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.databind.JsonNode$OverwriteMode r1 = com.fasterxml.jackson.databind.JsonNode.OverwriteMode.SCALARS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.databind.JsonNode$OverwriteMode r1 = com.fasterxml.jackson.databind.JsonNode.OverwriteMode.ALL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.BaseJsonNode.AnonymousClass1.<clinit>():void");
        }
    }

    public JsonPointer _jsonPointerIfValid(String str) {
        if (str.isEmpty() || str.charAt(0) == '/') {
            return JsonPointer.compile(str);
        }
        return null;
    }

    public <T> T _reportWrongNodeOperation(String str, Object... objArr) {
        throw new UnsupportedOperationException(String.format(str, objArr));
    }

    public <T> T _reportWrongNodeType(String str, Object... objArr) {
        throw new UnsupportedOperationException(String.format(str, objArr));
    }

    public ArrayNode _withArray(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z2) {
        return null;
    }

    public ObjectNode _withObject(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z2) {
        return null;
    }

    public boolean _withXxxMayReplace(JsonNode jsonNode, JsonNode.OverwriteMode overwriteMode) {
        int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$JsonNode$OverwriteMode[overwriteMode.ordinal()];
        if (i3 == 1) {
            return false;
        }
        if (i3 == 2) {
            return jsonNode.isNull();
        }
        if (i3 != 3) {
            return true;
        }
        return !jsonNode.isContainerNode();
    }

    public void _withXxxVerifyReplace(JsonPointer jsonPointer, JsonPointer jsonPointer2, JsonNode.OverwriteMode overwriteMode, boolean z2, JsonNode jsonNode) {
        if (!_withXxxMayReplace(jsonNode, overwriteMode)) {
            _reportWrongNodeType("Cannot replace `JsonNode` of type `%s` for property \"%s\" in JSON Pointer \"%s\" (mode `OverwriteMode.%s`)", jsonNode.getClass().getName(), jsonPointer2.getMatchingProperty(), jsonPointer, overwriteMode);
        }
    }

    public abstract JsonToken asToken();

    public final JsonNode findPath(String str) {
        JsonNode findValue = findValue(str);
        return findValue == null ? MissingNode.getInstance() : findValue;
    }

    public abstract int hashCode();

    public JsonParser.NumberType numberType() {
        return null;
    }

    public JsonNode required(String str) {
        return (JsonNode) _reportRequiredViolation("Node of type `%s` has no fields", getClass().getSimpleName());
    }

    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    public abstract void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException;

    public String toPrettyString() {
        return InternalNodeMapper.nodeToPrettyString(this);
    }

    public String toString() {
        return InternalNodeMapper.nodeToString(this);
    }

    public JsonParser traverse() {
        return new TreeTraversingParser(this);
    }

    public ArrayNode withArray(JsonPointer jsonPointer, JsonNode.OverwriteMode overwriteMode, boolean z2) {
        if (jsonPointer.matches()) {
            if (this instanceof ArrayNode) {
                return (ArrayNode) this;
            }
            _reportWrongNodeType("Can only call `withArray()` with empty JSON Pointer on `ArrayNode`, not `%s`", getClass().getName());
        }
        ArrayNode _withArray = _withArray(jsonPointer, jsonPointer, overwriteMode, z2);
        if (_withArray == null) {
            _reportWrongNodeType("Cannot replace context node (of type `%s`) using `withArray()` with  JSON Pointer '%s'", getClass().getName(), jsonPointer);
        }
        return _withArray;
    }

    public ObjectNode withObject(JsonPointer jsonPointer, JsonNode.OverwriteMode overwriteMode, boolean z2) {
        if (jsonPointer.matches()) {
            if (this instanceof ObjectNode) {
                return (ObjectNode) this;
            }
            _reportWrongNodeType("Can only call `withObject()` with empty JSON Pointer on `ObjectNode`, not `%s`", getClass().getName());
        }
        ObjectNode _withObject = _withObject(jsonPointer, jsonPointer, overwriteMode, z2);
        if (_withObject == null) {
            _reportWrongNodeType("Cannot replace context node (of type `%s`) using `withObject()` with  JSON Pointer '%s'", getClass().getName(), jsonPointer);
        }
        return _withObject;
    }

    public Object writeReplace() {
        return NodeSerialization.from(this);
    }

    public JsonParser traverse(ObjectCodec objectCodec) {
        return new TreeTraversingParser(this, objectCodec);
    }

    public JsonNode required(int i3) {
        return (JsonNode) _reportRequiredViolation("Node of type `%s` has no indexed values", getClass().getSimpleName());
    }
}
