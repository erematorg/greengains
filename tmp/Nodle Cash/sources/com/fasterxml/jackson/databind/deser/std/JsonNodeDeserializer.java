package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;

public class JsonNodeDeserializer extends BaseNodeDeserializer<JsonNode> {
    private static final JsonNodeDeserializer instance = new JsonNodeDeserializer();

    public static final class ArrayDeserializer extends BaseNodeDeserializer<ArrayNode> {
        protected static final ArrayDeserializer _instance = new ArrayDeserializer();
        private static final long serialVersionUID = 1;

        public ArrayDeserializer() {
            super(ArrayNode.class, Boolean.TRUE);
        }

        public static ArrayDeserializer getInstance() {
            return _instance;
        }

        public JsonDeserializer<?> _createWithMerge(boolean z2, boolean z3) {
            return new ArrayDeserializer(this, z2, z3);
        }

        public ArrayDeserializer(ArrayDeserializer arrayDeserializer, boolean z2, boolean z3) {
            super(arrayDeserializer, z2, z3);
        }

        public ArrayNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return (ArrayNode) deserializationContext.handleUnexpectedToken((Class<?>) ArrayNode.class, jsonParser);
            }
            JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
            ArrayNode arrayNode = nodeFactory.arrayNode();
            _deserializeContainerNoRecursion(jsonParser, deserializationContext, nodeFactory, new BaseNodeDeserializer.ContainerStack(), arrayNode);
            return arrayNode;
        }

        public ArrayNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, ArrayNode arrayNode) throws IOException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return (ArrayNode) deserializationContext.handleUnexpectedToken((Class<?>) ArrayNode.class, jsonParser);
            }
            _deserializeContainerNoRecursion(jsonParser, deserializationContext, deserializationContext.getNodeFactory(), new BaseNodeDeserializer.ContainerStack(), arrayNode);
            return arrayNode;
        }
    }

    public static final class ObjectDeserializer extends BaseNodeDeserializer<ObjectNode> {
        protected static final ObjectDeserializer _instance = new ObjectDeserializer();
        private static final long serialVersionUID = 1;

        public ObjectDeserializer() {
            super(ObjectNode.class, Boolean.TRUE);
        }

        public static ObjectDeserializer getInstance() {
            return _instance;
        }

        public JsonDeserializer<?> _createWithMerge(boolean z2, boolean z3) {
            return new ObjectDeserializer(this, z2, z3);
        }

        public ObjectDeserializer(ObjectDeserializer objectDeserializer, boolean z2, boolean z3) {
            super(objectDeserializer, z2, z3);
        }

        public ObjectNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
            if (jsonParser.isExpectedStartObjectToken()) {
                ObjectNode objectNode = nodeFactory.objectNode();
                _deserializeContainerNoRecursion(jsonParser, deserializationContext, nodeFactory, new BaseNodeDeserializer.ContainerStack(), objectNode);
                return objectNode;
            } else if (jsonParser.hasToken(JsonToken.FIELD_NAME)) {
                return _deserializeObjectAtName(jsonParser, deserializationContext, nodeFactory, new BaseNodeDeserializer.ContainerStack());
            } else {
                if (jsonParser.hasToken(JsonToken.END_OBJECT)) {
                    return nodeFactory.objectNode();
                }
                return (ObjectNode) deserializationContext.handleUnexpectedToken((Class<?>) ObjectNode.class, jsonParser);
            }
        }

        public ObjectNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, ObjectNode objectNode) throws IOException {
            if (jsonParser.isExpectedStartObjectToken() || jsonParser.hasToken(JsonToken.FIELD_NAME)) {
                return (ObjectNode) updateObject(jsonParser, deserializationContext, objectNode, new BaseNodeDeserializer.ContainerStack());
            }
            return (ObjectNode) deserializationContext.handleUnexpectedToken((Class<?>) ObjectNode.class, jsonParser);
        }
    }

    public JsonNodeDeserializer() {
        super(JsonNode.class, (Boolean) null);
    }

    public static JsonDeserializer<? extends JsonNode> getDeserializer(Class<?> cls) {
        return cls == ObjectNode.class ? ObjectDeserializer.getInstance() : cls == ArrayNode.class ? ArrayDeserializer.getInstance() : instance;
    }

    public JsonDeserializer<?> _createWithMerge(boolean z2, boolean z3) {
        return new JsonNodeDeserializer(this, z2, z3);
    }

    public /* bridge */ /* synthetic */ JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        return super.createContextual(deserializationContext, beanProperty);
    }

    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return super.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    public Object getAbsentValue(DeserializationContext deserializationContext) {
        return null;
    }

    public /* bridge */ /* synthetic */ boolean isCachable() {
        return super.isCachable();
    }

    public /* bridge */ /* synthetic */ LogicalType logicalType() {
        return super.logicalType();
    }

    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._supportsUpdates;
    }

    public JsonNodeDeserializer(JsonNodeDeserializer jsonNodeDeserializer, boolean z2, boolean z3) {
        super(jsonNodeDeserializer, z2, z3);
    }

    public JsonNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        BaseNodeDeserializer.ContainerStack containerStack = new BaseNodeDeserializer.ContainerStack();
        JsonNodeFactory nodeFactory = deserializationContext.getNodeFactory();
        int currentTokenId = jsonParser.currentTokenId();
        if (currentTokenId == 1) {
            return _deserializeContainerNoRecursion(jsonParser, deserializationContext, nodeFactory, containerStack, nodeFactory.objectNode());
        } else if (currentTokenId == 2) {
            return nodeFactory.objectNode();
        } else {
            if (currentTokenId == 3) {
                return _deserializeContainerNoRecursion(jsonParser, deserializationContext, nodeFactory, containerStack, nodeFactory.arrayNode());
            } else if (currentTokenId != 5) {
                return _deserializeAnyScalar(jsonParser, deserializationContext);
            } else {
                return _deserializeObjectAtName(jsonParser, deserializationContext, nodeFactory, containerStack);
            }
        }
    }

    public JsonNode getNullValue(DeserializationContext deserializationContext) {
        return deserializationContext.getNodeFactory().nullNode();
    }
}
