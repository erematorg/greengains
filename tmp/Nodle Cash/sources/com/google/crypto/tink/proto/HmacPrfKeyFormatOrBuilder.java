package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

public interface HmacPrfKeyFormatOrBuilder extends MessageLiteOrBuilder {
    int getKeySize();

    HmacPrfParams getParams();

    int getVersion();

    boolean hasParams();
}
