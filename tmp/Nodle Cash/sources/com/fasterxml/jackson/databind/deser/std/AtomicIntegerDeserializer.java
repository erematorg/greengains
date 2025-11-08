package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDeserializer extends StdScalarDeserializer<AtomicInteger> {
    private static final long serialVersionUID = 1;

    public AtomicIntegerDeserializer() {
        super((Class<?>) AtomicInteger.class);
    }

    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        return new AtomicInteger();
    }

    public LogicalType logicalType() {
        return LogicalType.Integer;
    }

    public AtomicInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.isExpectedNumberIntToken()) {
            return new AtomicInteger(jsonParser.getIntValue());
        }
        Integer _parseInteger = _parseInteger(jsonParser, deserializationContext, AtomicInteger.class);
        if (_parseInteger == null) {
            return null;
        }
        return new AtomicInteger(_parseInteger.intValue());
    }
}
