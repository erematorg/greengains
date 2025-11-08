package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface HmacPrfKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getKeyValue();

    HmacPrfParams getParams();

    int getVersion();

    boolean hasParams();
}
