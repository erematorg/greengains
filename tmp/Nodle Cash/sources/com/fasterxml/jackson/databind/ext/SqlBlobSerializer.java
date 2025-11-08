package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@JacksonStdImpl
public class SqlBlobSerializer extends StdScalarSerializer<Blob> {
    public SqlBlobSerializer() {
        super(Blob.class);
    }

    public void _writeValue(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        InputStream inputStream;
        try {
            inputStream = blob.getBinaryStream();
        } catch (SQLException e3) {
            serializerProvider.reportMappingProblem(e3, "Failed to access `java.sql.Blob` value to write as binary value", new Object[0]);
            inputStream = null;
        }
        jsonGenerator.writeBinary(serializerProvider.getConfig().getBase64Variant(), inputStream, -1);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
        if (expectArrayFormat != null) {
            expectArrayFormat.itemsFormat(JsonFormatTypes.INTEGER);
        }
    }

    public boolean isEmpty(SerializerProvider serializerProvider, Blob blob) {
        return blob == null;
    }

    public void serialize(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        _writeValue(blob, jsonGenerator, serializerProvider);
    }

    public void serializeWithType(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        WritableTypeId writeTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(blob, JsonToken.VALUE_EMBEDDED_OBJECT));
        _writeValue(blob, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writeTypePrefix);
    }
}
