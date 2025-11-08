package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ArrayDecoders;
import java.io.IOException;

@CheckReturnValue
interface Schema<T> {
    boolean equals(T t2, T t3);

    int getSerializedSize(T t2);

    int hashCode(T t2);

    boolean isInitialized(T t2);

    void makeImmutable(T t2);

    void mergeFrom(T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    void mergeFrom(T t2, T t3);

    void mergeFrom(T t2, byte[] bArr, int i3, int i4, ArrayDecoders.Registers registers) throws IOException;

    T newInstance();

    void writeTo(T t2, Writer writer) throws IOException;
}
