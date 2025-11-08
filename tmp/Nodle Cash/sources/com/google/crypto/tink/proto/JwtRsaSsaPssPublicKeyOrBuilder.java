package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface JwtRsaSsaPssPublicKeyOrBuilder extends MessageLiteOrBuilder {
    JwtRsaSsaPssAlgorithm getAlgorithm();

    int getAlgorithmValue();

    JwtRsaSsaPssPublicKey.CustomKid getCustomKid();

    ByteString getE();

    ByteString getN();

    int getVersion();

    boolean hasCustomKid();
}
