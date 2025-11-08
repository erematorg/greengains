package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.errorprone.annotations.InlineMe;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class BinaryKeysetReader implements KeysetReader {
    private final InputStream inputStream;

    private BinaryKeysetReader(InputStream inputStream2) {
        this.inputStream = inputStream2;
    }

    public static KeysetReader withBytes(byte[] bArr) {
        return new BinaryKeysetReader(new ByteArrayInputStream(bArr));
    }

    @InlineMe(imports = {"com.google.crypto.tink.BinaryKeysetReader", "java.io.FileInputStream"}, replacement = "BinaryKeysetReader.withInputStream(new FileInputStream(file))")
    @Deprecated
    public static KeysetReader withFile(File file) throws IOException {
        return withInputStream(new FileInputStream(file));
    }

    public static KeysetReader withInputStream(InputStream inputStream2) {
        return new BinaryKeysetReader(inputStream2);
    }

    public Keyset read() throws IOException {
        try {
            return Keyset.parseFrom(this.inputStream, ExtensionRegistryLite.getEmptyRegistry());
        } finally {
            this.inputStream.close();
        }
    }

    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            return EncryptedKeyset.parseFrom(this.inputStream, ExtensionRegistryLite.getEmptyRegistry());
        } finally {
            this.inputStream.close();
        }
    }
}
