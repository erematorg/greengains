package com.google.crypto.tink;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class TinkProtoKeysetFormat {
    private TinkProtoKeysetFormat() {
    }

    public static KeysetHandle parseEncryptedKeyset(byte[] bArr, Aead aead, byte[] bArr2) throws GeneralSecurityException {
        try {
            return KeysetHandle.readWithAssociatedData(BinaryKeysetReader.withBytes(bArr), aead, bArr2);
        } catch (IOException unused) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static KeysetHandle parseKeyset(byte[] bArr, SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (secretKeyAccess != null) {
            try {
                return CleartextKeysetHandle.read(BinaryKeysetReader.withBytes(bArr));
            } catch (IOException unused) {
                throw new GeneralSecurityException("Parse keyset failed");
            }
        } else {
            throw new NullPointerException("SecretKeyAccess cannot be null");
        }
    }

    public static KeysetHandle parseKeysetWithoutSecret(byte[] bArr) throws GeneralSecurityException {
        try {
            return KeysetHandle.readNoSecret(BinaryKeysetReader.withBytes(bArr));
        } catch (IOException unused) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static byte[] serializeEncryptedKeyset(KeysetHandle keysetHandle, Aead aead, byte[] bArr) throws GeneralSecurityException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            keysetHandle.writeWithAssociatedData(BinaryKeysetWriter.withOutputStream(byteArrayOutputStream), aead, bArr);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }

    public static byte[] serializeKeyset(KeysetHandle keysetHandle, SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (secretKeyAccess != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                CleartextKeysetHandle.write(keysetHandle, BinaryKeysetWriter.withOutputStream(byteArrayOutputStream));
                return byteArrayOutputStream.toByteArray();
            } catch (IOException unused) {
                throw new GeneralSecurityException("Serialize keyset failed");
            }
        } else {
            throw new NullPointerException("SecretKeyAccess cannot be null");
        }
    }

    public static byte[] serializeKeysetWithoutSecret(KeysetHandle keysetHandle) throws GeneralSecurityException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            keysetHandle.writeNoSecret(BinaryKeysetWriter.withOutputStream(byteArrayOutputStream));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }
}
