package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface JwtRsaSsaPkcs1PublicKeyOrBuilder extends MessageLiteOrBuilder {
    JwtRsaSsaPkcs1Algorithm getAlgorithm();

    int getAlgorithmValue();

    JwtRsaSsaPkcs1PublicKey.CustomKid getCustomKid();

    ByteString getE();

    ByteString getN();

    int getVersion();

    boolean hasCustomKid();
}
