package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface JwtHmacKeyFormatOrBuilder extends MessageLiteOrBuilder {
    JwtHmacAlgorithm getAlgorithm();

    int getAlgorithmValue();

    int getKeySize();

    int getVersion();
}
