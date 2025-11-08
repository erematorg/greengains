package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
