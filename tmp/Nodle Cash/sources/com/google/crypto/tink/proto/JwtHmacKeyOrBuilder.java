package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.JwtHmacKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface JwtHmacKeyOrBuilder extends MessageLiteOrBuilder {
    JwtHmacAlgorithm getAlgorithm();

    int getAlgorithmValue();

    JwtHmacKey.CustomKid getCustomKid();

    ByteString getKeyValue();

    int getVersion();

    boolean hasCustomKid();
}
