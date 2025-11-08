package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface JwtEcdsaPublicKeyOrBuilder extends MessageLiteOrBuilder {
    JwtEcdsaAlgorithm getAlgorithm();

    int getAlgorithmValue();

    JwtEcdsaPublicKey.CustomKid getCustomKid();

    int getVersion();

    ByteString getX();

    ByteString getY();

    boolean hasCustomKid();
}
