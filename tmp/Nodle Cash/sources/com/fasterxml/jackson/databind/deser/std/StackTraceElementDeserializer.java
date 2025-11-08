package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;

public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1;
    protected final JsonDeserializer<?> _adapterDeserializer;

    public static final class Adapter {
        public String classLoaderName;
        public String className = "";
        public String declaringClass;
        public String fileName = "";
        public String format;
        public int lineNumber = -1;
        public String methodName = "";
        public String moduleName;
        public String moduleVersion;
        public boolean nativeMethod;
    }

    @Deprecated
    public StackTraceElementDeserializer() {
        this((JsonDeserializer<?>) null);
    }

    public static JsonDeserializer<?> construct(DeserializationContext deserializationContext) throws JsonMappingException {
        return deserializationContext == null ? new StackTraceElementDeserializer() : new StackTraceElementDeserializer(deserializationContext.findNonContextualValueDeserializer(deserializationContext.constructType(Adapter.class)));
    }

    public StackTraceElement constructValue(DeserializationContext deserializationContext, Adapter adapter) {
        return constructValue(deserializationContext, adapter.className, adapter.methodName, adapter.fileName, adapter.lineNumber, adapter.moduleName, adapter.moduleVersion, adapter.classLoaderName);
    }

    public StackTraceElementDeserializer(JsonDeserializer<?> jsonDeserializer) {
        super((Class<?>) StackTraceElement.class);
        this._adapterDeserializer = jsonDeserializer;
    }

    @Deprecated
    public StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i3, String str4, String str5) {
        return constructValue(deserializationContext, str, str2, str3, i3, str4, str5, (String) null);
    }

    public StackTraceElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Adapter adapter;
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.FIELD_NAME) {
            JsonDeserializer<?> jsonDeserializer = this._adapterDeserializer;
            if (jsonDeserializer == null) {
                adapter = (Adapter) deserializationContext.readValue(jsonParser, Adapter.class);
            } else {
                adapter = (Adapter) jsonDeserializer.deserialize(jsonParser, deserializationContext);
            }
            return constructValue(deserializationContext, adapter);
        } else if (currentToken != JsonToken.START_ARRAY || !deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            return (StackTraceElement) deserializationContext.handleUnexpectedToken(this._valueClass, jsonParser);
        } else {
            jsonParser.nextToken();
            StackTraceElement deserialize = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(jsonParser, deserializationContext);
            }
            return deserialize;
        }
    }

    public StackTraceElement constructValue(DeserializationContext deserializationContext, String str, String str2, String str3, int i3, String str4, String str5, String str6) {
        return new StackTraceElement(str, str2, str3, i3);
    }
}
