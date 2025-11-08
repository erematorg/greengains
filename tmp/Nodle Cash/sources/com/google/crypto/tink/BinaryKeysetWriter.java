package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.errorprone.annotations.InlineMe;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class BinaryKeysetWriter implements KeysetWriter {
    private final OutputStream outputStream;

    private BinaryKeysetWriter(OutputStream outputStream2) {
        this.outputStream = outputStream2;
    }

    @InlineMe(imports = {"com.google.crypto.tink.BinaryKeysetWriter", "java.io.FileOutputStream"}, replacement = "BinaryKeysetWriter.withOutputStream(new FileOutputStream(file))")
    @Deprecated
    public static KeysetWriter withFile(File file) throws IOException {
        return withOutputStream(new FileOutputStream(file));
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream2) {
        return new BinaryKeysetWriter(outputStream2);
    }

    public void write(Keyset keyset) throws IOException {
        try {
            keyset.writeTo(this.outputStream);
        } finally {
            this.outputStream.close();
        }
    }

    public void write(EncryptedKeyset encryptedKeyset) throws IOException {
        try {
            encryptedKeyset.writeTo(this.outputStream);
        } finally {
            this.outputStream.close();
        }
    }
}
