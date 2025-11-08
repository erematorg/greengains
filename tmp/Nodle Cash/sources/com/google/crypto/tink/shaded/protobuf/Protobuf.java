package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@CheckReturnValue
final class Protobuf {
    private static final Protobuf INSTANCE = new Protobuf();
    private final ConcurrentMap<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap();
    private final SchemaFactory schemaFactory = new ManifestSchemaFactory();

    private Protobuf() {
    }

    public static Protobuf getInstance() {
        return INSTANCE;
    }

    public int getTotalSchemaSize() {
        int i3 = 0;
        for (Schema next : this.schemaCache.values()) {
            if (next instanceof MessageSchema) {
                i3 += ((MessageSchema) next).getSchemaSize();
            }
        }
        return i3;
    }

    public <T> boolean isInitialized(T t2) {
        return schemaFor(t2).isInitialized(t2);
    }

    public <T> void makeImmutable(T t2) {
        schemaFor(t2).makeImmutable(t2);
    }

    public <T> void mergeFrom(T t2, Reader reader) throws IOException {
        mergeFrom(t2, reader, ExtensionRegistryLite.getEmptyRegistry());
    }

    public Schema<?> registerSchema(Class<?> cls, Schema<?> schema) {
        Internal.checkNotNull(cls, "messageType");
        Internal.checkNotNull(schema, "schema");
        return this.schemaCache.putIfAbsent(cls, schema);
    }

    @CanIgnoreReturnValue
    public Schema<?> registerSchemaOverride(Class<?> cls, Schema<?> schema) {
        Internal.checkNotNull(cls, "messageType");
        Internal.checkNotNull(schema, "schema");
        return this.schemaCache.put(cls, schema);
    }

    public <T> Schema<T> schemaFor(Class<T> cls) {
        Internal.checkNotNull(cls, "messageType");
        Schema<T> schema = this.schemaCache.get(cls);
        if (schema != null) {
            return schema;
        }
        Schema<T> createSchema = this.schemaFactory.createSchema(cls);
        Schema<?> registerSchema = registerSchema(cls, createSchema);
        return registerSchema != null ? registerSchema : createSchema;
    }

    public <T> void writeTo(T t2, Writer writer) throws IOException {
        schemaFor(t2).writeTo(t2, writer);
    }

    public <T> void mergeFrom(T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        schemaFor(t2).mergeFrom(t2, reader, extensionRegistryLite);
    }

    public <T> Schema<T> schemaFor(T t2) {
        return schemaFor(t2.getClass());
    }
}
