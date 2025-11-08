package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.nio.file.Path;

public class Java7HandlersImpl extends Java7Handlers {
    private final Class<?> _pathClass = Path.class;

    public Class<?> getClassJavaNioFilePath() {
        return this._pathClass;
    }

    public JsonDeserializer<?> getDeserializerForJavaNioFilePath(Class<?> cls) {
        if (cls == this._pathClass) {
            return new NioPathDeserializer();
        }
        return null;
    }

    public JsonSerializer<?> getSerializerForJavaNioFilePath(Class<?> cls) {
        if (this._pathClass.isAssignableFrom(cls)) {
            return new NioPathSerializer();
        }
        return null;
    }
}
