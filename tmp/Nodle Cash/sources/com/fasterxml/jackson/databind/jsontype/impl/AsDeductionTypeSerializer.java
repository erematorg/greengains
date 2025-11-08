package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsDeductionTypeSerializer extends TypeSerializerBase {
    private static final AsDeductionTypeSerializer INSTANCE = new AsDeductionTypeSerializer();

    public AsDeductionTypeSerializer() {
        super((TypeIdResolver) null, (BeanProperty) null);
    }

    public static AsDeductionTypeSerializer instance() {
        return INSTANCE;
    }

    public AsDeductionTypeSerializer forProperty(BeanProperty beanProperty) {
        return this;
    }

    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.EXISTING_PROPERTY;
    }

    public WritableTypeId writeTypePrefix(JsonGenerator jsonGenerator, WritableTypeId writableTypeId) throws IOException {
        if (!writableTypeId.valueShape.isStructStart() || jsonGenerator.canWriteTypeId()) {
            return null;
        }
        return jsonGenerator.writeTypePrefix(writableTypeId);
    }

    public WritableTypeId writeTypeSuffix(JsonGenerator jsonGenerator, WritableTypeId writableTypeId) throws IOException {
        if (writableTypeId == null) {
            return null;
        }
        return jsonGenerator.writeTypeSuffix(writableTypeId);
    }
}
