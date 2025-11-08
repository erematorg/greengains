package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

final class InternalNodeMapper {
    private static final JsonMapper JSON_MAPPER;
    private static final ObjectReader NODE_READER;
    private static final ObjectWriter PRETTY_WRITER;
    private static final ObjectWriter STD_WRITER;

    public static final class IteratorStack {
        private int _end;
        private Iterator<?>[] _stack;
        private int _top;

        public Iterator<?> popOrNull() {
            int i3 = this._top;
            if (i3 == 0) {
                return null;
            }
            Iterator<?>[] itArr = this._stack;
            int i4 = i3 - 1;
            this._top = i4;
            return itArr[i4];
        }

        public void push(Iterator<?> it) {
            int i3 = this._top;
            int i4 = this._end;
            if (i3 < i4) {
                Iterator<?>[] itArr = this._stack;
                this._top = i3 + 1;
                itArr[i3] = it;
                return;
            }
            if (this._stack == null) {
                this._end = 10;
                this._stack = new Iterator[10];
            } else {
                int min = Math.min(SerializerCache.DEFAULT_MAX_CACHED, Math.max(20, i4 >> 1)) + i4;
                this._end = min;
                this._stack = (Iterator[]) Arrays.copyOf(this._stack, min);
            }
            Iterator<?>[] itArr2 = this._stack;
            int i5 = this._top;
            this._top = i5 + 1;
            itArr2[i5] = it;
        }
    }

    static {
        JsonMapper jsonMapper = new JsonMapper();
        JSON_MAPPER = jsonMapper;
        STD_WRITER = jsonMapper.writer();
        PRETTY_WRITER = jsonMapper.writer().withDefaultPrettyPrinter();
        NODE_READER = jsonMapper.readerFor((Class<?>) JsonNode.class);
    }

    private static JsonSerializable _wrapper(BaseJsonNode baseJsonNode) {
        return new WrapperForSerializer(baseJsonNode);
    }

    public static JsonNode bytesToNode(byte[] bArr) throws IOException {
        return (JsonNode) NODE_READER.readValue(bArr);
    }

    public static String nodeToPrettyString(BaseJsonNode baseJsonNode) {
        try {
            return PRETTY_WRITER.writeValueAsString(_wrapper(baseJsonNode));
        } catch (IOException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static String nodeToString(BaseJsonNode baseJsonNode) {
        try {
            return STD_WRITER.writeValueAsString(_wrapper(baseJsonNode));
        } catch (IOException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static byte[] valueToBytes(Object obj) throws IOException {
        return JSON_MAPPER.writeValueAsBytes(obj);
    }

    public static class WrapperForSerializer extends JsonSerializable.Base {
        protected SerializerProvider _context;
        protected final BaseJsonNode _root;

        public WrapperForSerializer(BaseJsonNode baseJsonNode) {
            this._root = baseJsonNode;
        }

        public void _serializeNonRecursive(JsonGenerator jsonGenerator, JsonNode jsonNode) throws IOException {
            if (jsonNode instanceof ObjectNode) {
                jsonGenerator.writeStartObject(this, jsonNode.size());
                _serializeNonRecursive(jsonGenerator, new IteratorStack(), jsonNode.fields());
            } else if (jsonNode instanceof ArrayNode) {
                jsonGenerator.writeStartArray(this, jsonNode.size());
                _serializeNonRecursive(jsonGenerator, new IteratorStack(), jsonNode.elements());
            } else {
                jsonNode.serialize(jsonGenerator, this._context);
            }
        }

        public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            this._context = serializerProvider;
            _serializeNonRecursive(jsonGenerator, this._root);
        }

        public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
            serialize(jsonGenerator, serializerProvider);
        }

        public void _serializeNonRecursive(JsonGenerator jsonGenerator, IteratorStack iteratorStack, Iterator<?> it) throws IOException {
            Iterator<Map.Entry<String, JsonNode>> it2;
            JsonNode jsonNode;
            while (true) {
                boolean hasNext = r5.hasNext();
                Iterator<?> it3 = it;
                if (hasNext) {
                    Object next = it3.next();
                    if (next instanceof Map.Entry) {
                        Map.Entry entry = (Map.Entry) next;
                        jsonGenerator.writeFieldName((String) entry.getKey());
                        jsonNode = (JsonNode) entry.getValue();
                    } else {
                        jsonNode = (JsonNode) next;
                    }
                    if (jsonNode instanceof ObjectNode) {
                        iteratorStack.push(it3);
                        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
                        jsonGenerator.writeStartObject(jsonNode, jsonNode.size());
                        it2 = fields;
                    } else if (jsonNode instanceof ArrayNode) {
                        iteratorStack.push(it3);
                        Iterator<JsonNode> elements = jsonNode.elements();
                        jsonGenerator.writeStartArray(jsonNode, jsonNode.size());
                        it2 = elements;
                    } else {
                        jsonNode.serialize(jsonGenerator, this._context);
                        it2 = it3;
                    }
                } else {
                    if (jsonGenerator.getOutputContext().inArray()) {
                        jsonGenerator.writeEndArray();
                    } else {
                        jsonGenerator.writeEndObject();
                    }
                    Iterator<?> popOrNull = iteratorStack.popOrNull();
                    if (popOrNull != null) {
                        it2 = popOrNull;
                    } else {
                        return;
                    }
                }
                it3 = it2;
            }
        }
    }
}
