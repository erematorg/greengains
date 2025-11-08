package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface JwtEcdsaKeyFormatOrBuilder extends MessageLiteOrBuilder {
    JwtEcdsaAlgorithm getAlgorithm();

    int getAlgorithmValue();

    int getVersion();
}
