package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.util.Arrays;

abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> implements ContextualDeserializer {
    protected final boolean _mergeArrays;
    protected final boolean _mergeObjects;
    protected final Boolean _supportsUpdates;

    public static final class ContainerStack {
        private int _end;
        private ContainerNode[] _stack;
        private int _top;

        public ContainerNode popOrNull() {
            int i3 = this._top;
            if (i3 == 0) {
                return null;
            }
            ContainerNode[] containerNodeArr = this._stack;
            int i4 = i3 - 1;
            this._top = i4;
            return containerNodeArr[i4];
        }

        public void push(ContainerNode containerNode) {
            int i3 = this._top;
            int i4 = this._end;
            if (i3 < i4) {
                ContainerNode[] containerNodeArr = this._stack;
                this._top = i3 + 1;
                containerNodeArr[i3] = containerNode;
                return;
            }
            if (this._stack == null) {
                this._end = 10;
                this._stack = new ContainerNode[10];
            } else {
                int min = Math.min(SerializerCache.DEFAULT_MAX_CACHED, Math.max(20, i4 >> 1)) + i4;
                this._end = min;
                this._stack = (ContainerNode[]) Arrays.copyOf(this._stack, min);
            }
            ContainerNode[] containerNodeArr2 = this._stack;
            int i5 = this._top;
            this._top = i5 + 1;
            containerNodeArr2[i5] = containerNode;
        }

        public int size() {
            return this._top;
        }
    }

    public BaseNodeDeserializer(Class<T> cls, Boolean bool) {
        super((Class<?>) cls);
        this._supportsUpdates = bool;
        this._mergeArrays = true;
        this._mergeObjects = true;
    }

    private static boolean _shouldMerge(Boolean bool, Boolean bool2) {
        if (bool != null) {
            return bool.booleanValue();
        }
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        return true;
    }

    public abstract JsonDeserializer<?> _createWithMerge(boolean z2, boolean z3);

    public final JsonNode _deserializeAnyScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 2) {
            return nodeFactory.objectNode();
        }
        switch (currentTokenId) {
            case 6:
                return nodeFactory.textNode(jsonParser.getText());
            case 7:
                return _fromInt(jsonParser, deserializationContext, nodeFactory);
            case 8:
                return _fromFloat(jsonParser, deserializationContext, nodeFactory);
            case 9:
                return nodeFactory.booleanNode(true);
            case 10:
                return nodeFactory.booleanNode(false);
            case 11:
                return nodeFactory.nullNode();
            case 12:
                return _fromEmbedded(jsonParser, deserializationContext);
            default:
                return (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        r14 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0078, code lost:
        r16 = r7.replace(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007c, code lost:
        if (r16 == null) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007e, code lost:
        r14 = r7;
        _handleDuplicateField(r19, r20, r21, r4, r7, r16, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0091, code lost:
        r7 = r14;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.node.ContainerNode<?> _deserializeContainerNoRecursion(com.fasterxml.jackson.core.JsonParser r19, com.fasterxml.jackson.databind.DeserializationContext r20, com.fasterxml.jackson.databind.node.JsonNodeFactory r21, com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.ContainerStack r22, com.fasterxml.jackson.databind.node.ContainerNode<?> r23) throws java.io.IOException {
        /*
            r18 = this;
            r8 = r18
            r9 = r19
            r10 = r21
            r11 = r22
            int r0 = r20.getDeserializationFeatures()
            int r1 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.F_MASK_INT_COERCIONS
            r12 = r0 & r1
            r0 = r23
        L_0x0012:
            boolean r1 = r0 instanceof com.fasterxml.jackson.databind.node.ObjectNode
            r13 = 0
            r14 = 1
            if (r1 == 0) goto L_0x00d7
            r1 = r0
            com.fasterxml.jackson.databind.node.ObjectNode r1 = (com.fasterxml.jackson.databind.node.ObjectNode) r1
            java.lang.String r2 = r19.nextFieldName()
            r15 = r0
            r7 = r1
            r4 = r2
        L_0x0022:
            if (r4 == 0) goto L_0x0129
            com.fasterxml.jackson.core.JsonToken r0 = r19.nextToken()
            if (r0 != 0) goto L_0x002c
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
        L_0x002c:
            int r0 = r0.id()
            if (r0 == r14) goto L_0x00b1
            r1 = 3
            if (r0 == r1) goto L_0x0093
            switch(r0) {
                case 6: goto L_0x006d;
                case 7: goto L_0x0066;
                case 8: goto L_0x005f;
                case 9: goto L_0x0058;
                case 10: goto L_0x0051;
                case 11: goto L_0x0040;
                default: goto L_0x0038;
            }
        L_0x0038:
            com.fasterxml.jackson.databind.JsonNode r0 = r18._deserializeRareScalar(r19, r20)
            r6 = r20
        L_0x003e:
            r5 = r0
            goto L_0x0078
        L_0x0040:
            com.fasterxml.jackson.databind.cfg.JsonNodeFeature r0 = com.fasterxml.jackson.databind.cfg.JsonNodeFeature.READ_NULL_PROPERTIES
            r6 = r20
            boolean r0 = r6.isEnabled((com.fasterxml.jackson.databind.cfg.DatatypeFeature) r0)
            if (r0 != 0) goto L_0x004c
        L_0x004a:
            r14 = r7
            goto L_0x0091
        L_0x004c:
            com.fasterxml.jackson.databind.node.NullNode r0 = r21.nullNode()
            goto L_0x003e
        L_0x0051:
            r6 = r20
            com.fasterxml.jackson.databind.node.BooleanNode r0 = r10.booleanNode((boolean) r13)
            goto L_0x003e
        L_0x0058:
            r6 = r20
            com.fasterxml.jackson.databind.node.BooleanNode r0 = r10.booleanNode((boolean) r14)
            goto L_0x003e
        L_0x005f:
            r6 = r20
            com.fasterxml.jackson.databind.JsonNode r0 = r18._fromFloat(r19, r20, r21)
            goto L_0x003e
        L_0x0066:
            r6 = r20
            com.fasterxml.jackson.databind.JsonNode r0 = r8._fromInt((com.fasterxml.jackson.core.JsonParser) r9, (int) r12, (com.fasterxml.jackson.databind.node.JsonNodeFactory) r10)
            goto L_0x003e
        L_0x006d:
            r6 = r20
            java.lang.String r0 = r19.getText()
            com.fasterxml.jackson.databind.node.TextNode r0 = r10.textNode((java.lang.String) r0)
            goto L_0x003e
        L_0x0078:
            com.fasterxml.jackson.databind.JsonNode r16 = r7.replace(r4, r5)
            if (r16 == 0) goto L_0x004a
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r17 = r5
            r5 = r7
            r6 = r16
            r14 = r7
            r7 = r17
            r0._handleDuplicateField(r1, r2, r3, r4, r5, r6, r7)
        L_0x0091:
            r7 = r14
            goto L_0x00d0
        L_0x0093:
            r14 = r7
            com.fasterxml.jackson.databind.node.ArrayNode r13 = r21.arrayNode()
            com.fasterxml.jackson.databind.JsonNode r6 = r14.replace(r4, r13)
            if (r6 == 0) goto L_0x00ab
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r5 = r14
            r7 = r13
            r0._handleDuplicateField(r1, r2, r3, r4, r5, r6, r7)
        L_0x00ab:
            r11.push(r15)
            r0 = r13
            goto L_0x0143
        L_0x00b1:
            r14 = r7
            com.fasterxml.jackson.databind.node.ObjectNode r7 = r21.objectNode()
            com.fasterxml.jackson.databind.JsonNode r6 = r14.replace(r4, r7)
            if (r6 == 0) goto L_0x00ca
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r5 = r14
            r14 = r7
            r0._handleDuplicateField(r1, r2, r3, r4, r5, r6, r7)
            goto L_0x00cb
        L_0x00ca:
            r14 = r7
        L_0x00cb:
            r11.push(r15)
            r7 = r14
            r15 = r7
        L_0x00d0:
            java.lang.String r4 = r19.nextFieldName()
            r14 = 1
            goto L_0x0022
        L_0x00d7:
            r1 = r0
            com.fasterxml.jackson.databind.node.ArrayNode r1 = (com.fasterxml.jackson.databind.node.ArrayNode) r1
        L_0x00da:
            com.fasterxml.jackson.core.JsonToken r2 = r19.nextToken()
            if (r2 != 0) goto L_0x00e2
            com.fasterxml.jackson.core.JsonToken r2 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
        L_0x00e2:
            int r2 = r2.id()
            switch(r2) {
                case 1: goto L_0x0139;
                case 2: goto L_0x00e9;
                case 3: goto L_0x012e;
                case 4: goto L_0x0129;
                case 5: goto L_0x00e9;
                case 6: goto L_0x011c;
                case 7: goto L_0x0113;
                case 8: goto L_0x010a;
                case 9: goto L_0x0101;
                case 10: goto L_0x00f9;
                case 11: goto L_0x00f1;
                default: goto L_0x00e9;
            }
        L_0x00e9:
            com.fasterxml.jackson.databind.JsonNode r2 = r18._deserializeRareScalar(r19, r20)
            r1.add((com.fasterxml.jackson.databind.JsonNode) r2)
            goto L_0x00da
        L_0x00f1:
            com.fasterxml.jackson.databind.node.NullNode r2 = r21.nullNode()
            r1.add((com.fasterxml.jackson.databind.JsonNode) r2)
            goto L_0x00da
        L_0x00f9:
            com.fasterxml.jackson.databind.node.BooleanNode r2 = r10.booleanNode((boolean) r13)
            r1.add((com.fasterxml.jackson.databind.JsonNode) r2)
            goto L_0x00da
        L_0x0101:
            r2 = 1
            com.fasterxml.jackson.databind.node.BooleanNode r3 = r10.booleanNode((boolean) r2)
            r1.add((com.fasterxml.jackson.databind.JsonNode) r3)
            goto L_0x00da
        L_0x010a:
            r2 = 1
            com.fasterxml.jackson.databind.JsonNode r3 = r18._fromFloat(r19, r20, r21)
            r1.add((com.fasterxml.jackson.databind.JsonNode) r3)
            goto L_0x00da
        L_0x0113:
            r2 = 1
            com.fasterxml.jackson.databind.JsonNode r3 = r8._fromInt((com.fasterxml.jackson.core.JsonParser) r9, (int) r12, (com.fasterxml.jackson.databind.node.JsonNodeFactory) r10)
            r1.add((com.fasterxml.jackson.databind.JsonNode) r3)
            goto L_0x00da
        L_0x011c:
            r2 = 1
            java.lang.String r3 = r19.getText()
            com.fasterxml.jackson.databind.node.TextNode r3 = r10.textNode((java.lang.String) r3)
            r1.add((com.fasterxml.jackson.databind.JsonNode) r3)
            goto L_0x00da
        L_0x0129:
            com.fasterxml.jackson.databind.node.ContainerNode r0 = r22.popOrNull()
            goto L_0x0143
        L_0x012e:
            r11.push(r0)
            com.fasterxml.jackson.databind.node.ArrayNode r0 = r21.arrayNode()
            r1.add((com.fasterxml.jackson.databind.JsonNode) r0)
            goto L_0x0143
        L_0x0139:
            r11.push(r0)
            com.fasterxml.jackson.databind.node.ObjectNode r0 = r21.objectNode()
            r1.add((com.fasterxml.jackson.databind.JsonNode) r0)
        L_0x0143:
            if (r0 != 0) goto L_0x0012
            return r23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer._deserializeContainerNoRecursion(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.node.JsonNodeFactory, com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer$ContainerStack, com.fasterxml.jackson.databind.node.ContainerNode):com.fasterxml.jackson.databind.node.ContainerNode");
    }

    public final ObjectNode _deserializeObjectAtName(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, ContainerStack containerStack) throws IOException {
        JsonNode _deserializeContainerNoRecursion;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        String currentName = jsonParser.currentName();
        while (currentName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == null) {
                nextToken = JsonToken.NOT_AVAILABLE;
            }
            int id = nextToken.id();
            if (id == 1) {
                _deserializeContainerNoRecursion = _deserializeContainerNoRecursion(jsonParser, deserializationContext, jsonNodeFactory, containerStack, jsonNodeFactory.objectNode());
            } else if (id != 3) {
                _deserializeContainerNoRecursion = _deserializeAnyScalar(jsonParser, deserializationContext);
            } else {
                _deserializeContainerNoRecursion = _deserializeContainerNoRecursion(jsonParser, deserializationContext, jsonNodeFactory, containerStack, jsonNodeFactory.arrayNode());
            }
            JsonNode jsonNode = _deserializeContainerNoRecursion;
            JsonNode replace = objectNode.replace(currentName, jsonNode);
            if (replace != null) {
                _handleDuplicateField(jsonParser, deserializationContext, jsonNodeFactory, currentName, objectNode, replace, jsonNode);
            }
            currentName = jsonParser.nextFieldName();
        }
        return objectNode;
    }

    public final JsonNode _deserializeRareScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int currentTokenId = jsonParser.currentTokenId();
        return currentTokenId != 2 ? currentTokenId != 8 ? currentTokenId != 12 ? (JsonNode) deserializationContext.handleUnexpectedToken(handledType(), jsonParser) : _fromEmbedded(jsonParser, deserializationContext) : _fromFloat(jsonParser, deserializationContext, deserializationContext.getNodeFactory()) : deserializationContext.getNodeFactory().objectNode();
    }

    public final JsonNode _fromEmbedded(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        Object embeddedObject = jsonParser.getEmbeddedObject();
        return embeddedObject == null ? nodeFactory.nullNode() : embeddedObject.getClass() == byte[].class ? nodeFactory.binaryNode((byte[]) embeddedObject) : embeddedObject instanceof RawValue ? nodeFactory.rawValueNode((RawValue) embeddedObject) : embeddedObject instanceof JsonNode ? (JsonNode) embeddedObject : nodeFactory.pojoNode(embeddedObject);
    }

    public final JsonNode _fromFloat(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        return numberType == JsonParser.NumberType.BIG_DECIMAL ? jsonNodeFactory.numberNode(jsonParser.getDecimalValue()) : deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.isNaN() ? jsonNodeFactory.numberNode(jsonParser.getDoubleValue()) : jsonNodeFactory.numberNode(jsonParser.getDecimalValue()) : numberType == JsonParser.NumberType.FLOAT ? jsonNodeFactory.numberNode(jsonParser.getFloatValue()) : jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
    }

    public final JsonNode _fromInt(JsonParser jsonParser, int i3, JsonNodeFactory jsonNodeFactory) throws IOException {
        if (i3 == 0) {
            JsonParser.NumberType numberType = jsonParser.getNumberType();
            if (numberType == JsonParser.NumberType.INT) {
                return jsonNodeFactory.numberNode(jsonParser.getIntValue());
            }
            if (numberType == JsonParser.NumberType.LONG) {
                return jsonNodeFactory.numberNode(jsonParser.getLongValue());
            }
            return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
        } else if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(i3)) {
            return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
        } else {
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
    }

    public void _handleDuplicateField(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory, String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws IOException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
            deserializationContext.reportInputMismatch((Class<?>) JsonNode.class, "Duplicate field '%s' for `ObjectNode`: not allowed when `DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY` enabled", str);
        }
        if (!deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)) {
            return;
        }
        if (jsonNode.isArray()) {
            ((ArrayNode) jsonNode).add(jsonNode2);
            objectNode.replace(str, jsonNode);
            return;
        }
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        arrayNode.add(jsonNode);
        arrayNode.add(jsonNode2);
        objectNode.replace(str, arrayNode);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        Boolean defaultMergeable = config.getDefaultMergeable(ArrayNode.class);
        Boolean defaultMergeable2 = config.getDefaultMergeable(ObjectNode.class);
        Boolean defaultMergeable3 = config.getDefaultMergeable(JsonNode.class);
        boolean _shouldMerge = _shouldMerge(defaultMergeable, defaultMergeable3);
        boolean _shouldMerge2 = _shouldMerge(defaultMergeable2, defaultMergeable3);
        return (_shouldMerge == this._mergeArrays && _shouldMerge2 == this._mergeObjects) ? this : _createWithMerge(_shouldMerge, _shouldMerge2);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public boolean isCachable() {
        return true;
    }

    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._supportsUpdates;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonNode updateObject(com.fasterxml.jackson.core.JsonParser r9, com.fasterxml.jackson.databind.DeserializationContext r10, com.fasterxml.jackson.databind.node.ObjectNode r11, com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.ContainerStack r12) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r9.isExpectedStartObjectToken()
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = r9.nextFieldName()
            goto L_0x001e
        L_0x000b:
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME
            boolean r0 = r9.hasToken(r0)
            if (r0 != 0) goto L_0x001a
            java.lang.Object r8 = r8.deserialize(r9, r10)
            com.fasterxml.jackson.databind.JsonNode r8 = (com.fasterxml.jackson.databind.JsonNode) r8
            return r8
        L_0x001a:
            java.lang.String r0 = r9.currentName()
        L_0x001e:
            com.fasterxml.jackson.databind.node.JsonNodeFactory r7 = r10.getNodeFactory()
        L_0x0022:
            if (r0 == 0) goto L_0x00c8
            com.fasterxml.jackson.core.JsonToken r1 = r9.nextToken()
            com.fasterxml.jackson.databind.JsonNode r2 = r11.get((java.lang.String) r0)
            if (r2 == 0) goto L_0x0061
            boolean r3 = r2 instanceof com.fasterxml.jackson.databind.node.ObjectNode
            if (r3 == 0) goto L_0x0048
            com.fasterxml.jackson.core.JsonToken r3 = com.fasterxml.jackson.core.JsonToken.START_OBJECT
            if (r1 != r3) goto L_0x0061
            boolean r3 = r8._mergeObjects
            if (r3 == 0) goto L_0x0061
            r1 = r2
            com.fasterxml.jackson.databind.node.ObjectNode r1 = (com.fasterxml.jackson.databind.node.ObjectNode) r1
            com.fasterxml.jackson.databind.JsonNode r1 = r8.updateObject(r9, r10, r1, r12)
            if (r1 == r2) goto L_0x00c2
            r11.set(r0, r1)
            goto L_0x00c2
        L_0x0048:
            boolean r3 = r2 instanceof com.fasterxml.jackson.databind.node.ArrayNode
            if (r3 == 0) goto L_0x0061
            com.fasterxml.jackson.core.JsonToken r3 = com.fasterxml.jackson.core.JsonToken.START_ARRAY
            if (r1 != r3) goto L_0x0061
            boolean r3 = r8._mergeArrays
            if (r3 == 0) goto L_0x0061
            r6 = r2
            com.fasterxml.jackson.databind.node.ArrayNode r6 = (com.fasterxml.jackson.databind.node.ArrayNode) r6
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r7
            r5 = r12
            r1._deserializeContainerNoRecursion(r2, r3, r4, r5, r6)
            goto L_0x00c2
        L_0x0061:
            if (r1 != 0) goto L_0x0065
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.NOT_AVAILABLE
        L_0x0065:
            int r1 = r1.id()
            r2 = 1
            if (r1 == r2) goto L_0x00b2
            r3 = 3
            if (r1 == r3) goto L_0x00a4
            r3 = 6
            if (r1 == r3) goto L_0x009b
            r3 = 7
            if (r1 == r3) goto L_0x0096
            switch(r1) {
                case 9: goto L_0x0091;
                case 10: goto L_0x008b;
                case 11: goto L_0x007d;
                default: goto L_0x0078;
            }
        L_0x0078:
            com.fasterxml.jackson.databind.JsonNode r1 = r8._deserializeRareScalar(r9, r10)
            goto L_0x00bf
        L_0x007d:
            com.fasterxml.jackson.databind.cfg.JsonNodeFeature r1 = com.fasterxml.jackson.databind.cfg.JsonNodeFeature.READ_NULL_PROPERTIES
            boolean r1 = r10.isEnabled((com.fasterxml.jackson.databind.cfg.DatatypeFeature) r1)
            if (r1 != 0) goto L_0x0086
            goto L_0x00c2
        L_0x0086:
            com.fasterxml.jackson.databind.node.NullNode r1 = r7.nullNode()
            goto L_0x00bf
        L_0x008b:
            r1 = 0
            com.fasterxml.jackson.databind.node.BooleanNode r1 = r7.booleanNode((boolean) r1)
            goto L_0x00bf
        L_0x0091:
            com.fasterxml.jackson.databind.node.BooleanNode r1 = r7.booleanNode((boolean) r2)
            goto L_0x00bf
        L_0x0096:
            com.fasterxml.jackson.databind.JsonNode r1 = r8._fromInt((com.fasterxml.jackson.core.JsonParser) r9, (com.fasterxml.jackson.databind.DeserializationContext) r10, (com.fasterxml.jackson.databind.node.JsonNodeFactory) r7)
            goto L_0x00bf
        L_0x009b:
            java.lang.String r1 = r9.getText()
            com.fasterxml.jackson.databind.node.TextNode r1 = r7.textNode((java.lang.String) r1)
            goto L_0x00bf
        L_0x00a4:
            com.fasterxml.jackson.databind.node.ArrayNode r6 = r7.arrayNode()
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r7
            r5 = r12
            com.fasterxml.jackson.databind.node.ContainerNode r1 = r1._deserializeContainerNoRecursion(r2, r3, r4, r5, r6)
            goto L_0x00bf
        L_0x00b2:
            com.fasterxml.jackson.databind.node.ObjectNode r6 = r7.objectNode()
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r7
            r5 = r12
            com.fasterxml.jackson.databind.node.ContainerNode r1 = r1._deserializeContainerNoRecursion(r2, r3, r4, r5, r6)
        L_0x00bf:
            r11.set(r0, r1)
        L_0x00c2:
            java.lang.String r0 = r9.nextFieldName()
            goto L_0x0022
        L_0x00c8:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.updateObject(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.node.ObjectNode, com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer$ContainerStack):com.fasterxml.jackson.databind.JsonNode");
    }

    public BaseNodeDeserializer(BaseNodeDeserializer<?> baseNodeDeserializer, boolean z2, boolean z3) {
        super((StdDeserializer<?>) baseNodeDeserializer);
        this._supportsUpdates = baseNodeDeserializer._supportsUpdates;
        this._mergeArrays = z2;
        this._mergeObjects = z3;
    }

    public final JsonNode _fromInt(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException {
        JsonParser.NumberType numberType;
        int deserializationFeatures = deserializationContext.getDeserializationFeatures();
        if ((StdDeserializer.F_MASK_INT_COERCIONS & deserializationFeatures) == 0) {
            numberType = jsonParser.getNumberType();
        } else if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(deserializationFeatures)) {
            numberType = JsonParser.NumberType.BIG_INTEGER;
        } else if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(deserializationFeatures)) {
            numberType = JsonParser.NumberType.LONG;
        } else {
            numberType = jsonParser.getNumberType();
        }
        if (numberType == JsonParser.NumberType.INT) {
            return jsonNodeFactory.numberNode(jsonParser.getIntValue());
        }
        if (numberType == JsonParser.NumberType.LONG) {
            return jsonNodeFactory.numberNode(jsonParser.getLongValue());
        }
        return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
    }
}
