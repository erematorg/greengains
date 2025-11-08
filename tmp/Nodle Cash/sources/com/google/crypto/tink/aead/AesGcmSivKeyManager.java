package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.subtle.AesGcmSiv;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmSivKey;
import com.google.crypto.tink.proto.AesGcmSivKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public final class AesGcmSivKeyManager extends KeyTypeManager<AesGcmSivKey> {
    public AesGcmSivKeyManager() {
        super(AesGcmSivKey.class, new PrimitiveFactory<Aead, AesGcmSivKey>(Aead.class) {
            public Aead getPrimitive(AesGcmSivKey aesGcmSivKey) throws GeneralSecurityException {
                return new AesGcmSiv(aesGcmSivKey.getKeyValue().toByteArray());
            }
        });
    }

    public static final KeyTemplate aes128GcmSivTemplate() {
        return createKeyTemplate(16, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate aes256GcmSivTemplate() {
        return createKeyTemplate(32, KeyTemplate.OutputPrefixType.TINK);
    }

    private static boolean canUseAesGcmSive() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<AesGcmSivKeyFormat> createKeyFormat(int i3, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((AesGcmSivKeyFormat) AesGcmSivKeyFormat.newBuilder().setKeySize(i3).build(), outputPrefixType);
    }

    private static KeyTemplate createKeyTemplate(int i3, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new AesGcmSivKeyManager().getKeyType(), ((AesGcmSivKeyFormat) AesGcmSivKeyFormat.newBuilder().setKeySize(i3).build()).toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawAes128GcmSivTemplate() {
        return createKeyTemplate(16, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawAes256GcmSivTemplate() {
        return createKeyTemplate(32, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        if (canUseAesGcmSive()) {
            Registry.registerKeyManager(new AesGcmSivKeyManager(), z2);
            AesGcmSivProtoSerialization.register();
        }
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<AesGcmSivKeyFormat, AesGcmSivKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesGcmSivKeyFormat, AesGcmSivKey>(AesGcmSivKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesGcmSivKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("AES128_GCM_SIV", AesGcmSivKeyManager.createKeyFormat(16, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("AES128_GCM_SIV_RAW", AesGcmSivKeyManager.createKeyFormat(16, outputPrefixType2));
                hashMap.put("AES256_GCM_SIV", AesGcmSivKeyManager.createKeyFormat(32, outputPrefixType));
                hashMap.put("AES256_GCM_SIV_RAW", AesGcmSivKeyManager.createKeyFormat(32, outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public AesGcmSivKey createKey(AesGcmSivKeyFormat aesGcmSivKeyFormat) {
                return (AesGcmSivKey) AesGcmSivKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmSivKeyFormat.getKeySize()))).setVersion(AesGcmSivKeyManager.this.getVersion()).build();
            }

            public AesGcmSivKey deriveKey(AesGcmSivKeyFormat aesGcmSivKeyFormat, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(aesGcmSivKeyFormat.getVersion(), AesGcmSivKeyManager.this.getVersion());
                byte[] bArr = new byte[aesGcmSivKeyFormat.getKeySize()];
                try {
                    KeyTypeManager.KeyFactory.readFully(inputStream, bArr);
                    return (AesGcmSivKey) AesGcmSivKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setVersion(AesGcmSivKeyManager.this.getVersion()).build();
                } catch (IOException e3) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e3);
                }
            }

            public AesGcmSivKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesGcmSivKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(AesGcmSivKeyFormat aesGcmSivKeyFormat) throws GeneralSecurityException {
                Validators.validateAesKeySize(aesGcmSivKeyFormat.getKeySize());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public AesGcmSivKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesGcmSivKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(AesGcmSivKey aesGcmSivKey) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmSivKey.getVersion(), getVersion());
        Validators.validateAesKeySize(aesGcmSivKey.getKeyValue().size());
    }
}
