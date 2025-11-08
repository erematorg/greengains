package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.Ed25519KeyFormat;
import com.google.crypto.tink.proto.Ed25519PrivateKey;
import com.google.crypto.tink.proto.Ed25519PublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Ed25519Sign;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Ed25519PrivateKeyManager extends PrivateKeyTypeManager<Ed25519PrivateKey, Ed25519PublicKey> {
    public Ed25519PrivateKeyManager() {
        super(Ed25519PrivateKey.class, Ed25519PublicKey.class, new PrimitiveFactory<PublicKeySign, Ed25519PrivateKey>(PublicKeySign.class) {
            public PublicKeySign getPrimitive(Ed25519PrivateKey ed25519PrivateKey) throws GeneralSecurityException {
                return new Ed25519Sign(ed25519PrivateKey.getKeyValue().toByteArray());
            }
        });
    }

    public static final KeyTemplate ed25519Template() {
        return KeyTemplate.create(new Ed25519PrivateKeyManager().getKeyType(), new byte[0], KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawEd25519Template() {
        return KeyTemplate.create(new Ed25519PrivateKeyManager().getKeyType(), new byte[0], KeyTemplate.OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z2) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new Ed25519PrivateKeyManager(), new Ed25519PublicKeyManager(), z2);
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<Ed25519KeyFormat, Ed25519PrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<Ed25519KeyFormat, Ed25519PrivateKey>(Ed25519KeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<Ed25519KeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("ED25519", new KeyTypeManager.KeyFactory.KeyFormat(Ed25519KeyFormat.getDefaultInstance(), KeyTemplate.OutputPrefixType.TINK));
                Ed25519KeyFormat defaultInstance = Ed25519KeyFormat.getDefaultInstance();
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("ED25519_RAW", new KeyTypeManager.KeyFactory.KeyFormat(defaultInstance, outputPrefixType));
                hashMap.put("ED25519WithRawOutput", new KeyTypeManager.KeyFactory.KeyFormat(Ed25519KeyFormat.getDefaultInstance(), outputPrefixType));
                return Collections.unmodifiableMap(hashMap);
            }

            public void validateKeyFormat(Ed25519KeyFormat ed25519KeyFormat) throws GeneralSecurityException {
            }

            public Ed25519PrivateKey createKey(Ed25519KeyFormat ed25519KeyFormat) throws GeneralSecurityException {
                Ed25519Sign.KeyPair newKeyPair = Ed25519Sign.KeyPair.newKeyPair();
                return (Ed25519PrivateKey) Ed25519PrivateKey.newBuilder().setVersion(Ed25519PrivateKeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(newKeyPair.getPrivateKey())).setPublicKey((Ed25519PublicKey) Ed25519PublicKey.newBuilder().setVersion(Ed25519PrivateKeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(newKeyPair.getPublicKey())).build()).build();
            }

            public Ed25519PrivateKey deriveKey(Ed25519KeyFormat ed25519KeyFormat, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(ed25519KeyFormat.getVersion(), Ed25519PrivateKeyManager.this.getVersion());
                byte[] bArr = new byte[32];
                try {
                    KeyTypeManager.KeyFactory.readFully(inputStream, bArr);
                    Ed25519Sign.KeyPair newKeyPairFromSeed = Ed25519Sign.KeyPair.newKeyPairFromSeed(bArr);
                    return (Ed25519PrivateKey) Ed25519PrivateKey.newBuilder().setVersion(Ed25519PrivateKeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(newKeyPairFromSeed.getPrivateKey())).setPublicKey((Ed25519PublicKey) Ed25519PublicKey.newBuilder().setVersion(Ed25519PrivateKeyManager.this.getVersion()).setKeyValue(ByteString.copyFrom(newKeyPairFromSeed.getPublicKey())).build()).build();
                } catch (IOException e3) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e3);
                }
            }

            public Ed25519KeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return Ed25519KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public Ed25519PublicKey getPublicKey(Ed25519PrivateKey ed25519PrivateKey) throws GeneralSecurityException {
        return ed25519PrivateKey.getPublicKey();
    }

    public Ed25519PrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return Ed25519PrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(Ed25519PrivateKey ed25519PrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(ed25519PrivateKey.getVersion(), getVersion());
        new Ed25519PublicKeyManager().validateKey(ed25519PrivateKey.getPublicKey());
        if (ed25519PrivateKey.getKeyValue().size() != 32) {
            throw new GeneralSecurityException("invalid Ed25519 private key: incorrect key length");
        }
    }
}
