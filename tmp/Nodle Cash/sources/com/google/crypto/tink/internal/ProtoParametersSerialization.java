package com.google.crypto.tink.internal;

import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;

@Immutable
public final class ProtoParametersSerialization implements Serialization {
    private final KeyTemplate keyTemplate;
    private final Bytes objectIdentifier;

    private ProtoParametersSerialization(KeyTemplate keyTemplate2) {
        this.keyTemplate = keyTemplate2;
        this.objectIdentifier = Util.toBytesFromPrintableAscii(keyTemplate2.getTypeUrl());
    }

    public static ProtoParametersSerialization create(String str, OutputPrefixType outputPrefixType, MessageLite messageLite) {
        return create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(str).setOutputPrefixType(outputPrefixType).setValue(messageLite.toByteString()).build());
    }

    public KeyTemplate getKeyTemplate() {
        return this.keyTemplate;
    }

    public Bytes getObjectIdentifier() {
        return this.objectIdentifier;
    }

    public static ProtoParametersSerialization create(KeyTemplate keyTemplate2) {
        return new ProtoParametersSerialization(keyTemplate2);
    }
}
