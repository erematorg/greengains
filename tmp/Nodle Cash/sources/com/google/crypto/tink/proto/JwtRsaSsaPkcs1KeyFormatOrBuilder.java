package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface JwtRsaSsaPkcs1KeyFormatOrBuilder extends MessageLiteOrBuilder {
    JwtRsaSsaPkcs1Algorithm getAlgorithm();

    int getAlgorithmValue();

    int getModulusSizeInBits();

    ByteString getPublicExponent();

    int getVersion();
}
