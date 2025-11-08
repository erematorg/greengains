package com.google.gson;

public enum LongSerializationPolicy {
    DEFAULT {
        public JsonElement serialize(Long l2) {
            return l2 == null ? JsonNull.INSTANCE : new JsonPrimitive((Number) l2);
        }
    },
    STRING {
        public JsonElement serialize(Long l2) {
            return l2 == null ? JsonNull.INSTANCE : new JsonPrimitive(l2.toString());
        }
    };

    public abstract JsonElement serialize(Long l2);
}
