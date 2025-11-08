package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface HpkePublicKeyOrBuilder extends MessageLiteOrBuilder {
    HpkeParams getParams();

    ByteString getPublicKey();

    int getVersion();

    boolean hasParams();
}
