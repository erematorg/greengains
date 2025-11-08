package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;
import java.security.GeneralSecurityException;

public class PrivilegedRegistry {
    private PrivilegedRegistry() {
    }

    public static KeyData deriveKey(KeyTemplate keyTemplate, InputStream inputStream) throws GeneralSecurityException {
        return Registry.deriveKey(keyTemplate, inputStream);
    }

    public static MessageLite parseKeyData(KeyData keyData) throws GeneralSecurityException, InvalidProtocolBufferException {
        return Registry.parseKeyData(keyData);
    }
}
