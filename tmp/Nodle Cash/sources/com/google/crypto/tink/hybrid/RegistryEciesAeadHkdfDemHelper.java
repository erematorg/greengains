package com.google.crypto.tink.hybrid;

import androidx.browser.trusted.c;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.daead.DeterministicAeadConfig;
import com.google.crypto.tink.hybrid.subtle.AeadOrDaead;
import com.google.crypto.tink.proto.AesCtrHmacAeadKey;
import com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesGcmKey;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.AesSivKey;
import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.HmacKey;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.EciesAeadHkdfDemHelper;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class RegistryEciesAeadHkdfDemHelper implements EciesAeadHkdfDemHelper {
    private AesCtrHmacAeadKey aesCtrHmacAeadKey;
    private int aesCtrKeySize;
    private AesGcmKey aesGcmKey;
    private AesSivKey aesSivKey;
    private final String demKeyTypeUrl;
    private final int symmetricKeySize;

    public RegistryEciesAeadHkdfDemHelper(KeyTemplate keyTemplate) throws GeneralSecurityException {
        String typeUrl = keyTemplate.getTypeUrl();
        this.demKeyTypeUrl = typeUrl;
        if (typeUrl.equals(AeadConfig.AES_GCM_TYPE_URL)) {
            try {
                AesGcmKeyFormat parseFrom = AesGcmKeyFormat.parseFrom(keyTemplate.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                this.aesGcmKey = (AesGcmKey) Registry.newKey(keyTemplate);
                this.symmetricKeySize = parseFrom.getKeySize();
            } catch (InvalidProtocolBufferException e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e3);
            }
        } else if (typeUrl.equals(AeadConfig.AES_CTR_HMAC_AEAD_TYPE_URL)) {
            try {
                AesCtrHmacAeadKeyFormat parseFrom2 = AesCtrHmacAeadKeyFormat.parseFrom(keyTemplate.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                this.aesCtrHmacAeadKey = (AesCtrHmacAeadKey) Registry.newKey(keyTemplate);
                this.aesCtrKeySize = parseFrom2.getAesCtrKeyFormat().getKeySize();
                this.symmetricKeySize = this.aesCtrKeySize + parseFrom2.getHmacKeyFormat().getKeySize();
            } catch (InvalidProtocolBufferException e4) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e4);
            }
        } else if (typeUrl.equals(DeterministicAeadConfig.AES_SIV_TYPE_URL)) {
            try {
                AesSivKeyFormat parseFrom3 = AesSivKeyFormat.parseFrom(keyTemplate.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                this.aesSivKey = (AesSivKey) Registry.newKey(keyTemplate);
                this.symmetricKeySize = parseFrom3.getKeySize();
            } catch (InvalidProtocolBufferException e5) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e5);
            }
        } else {
            throw new GeneralSecurityException(c.a("unsupported AEAD DEM key type: ", typeUrl));
        }
    }

    public AeadOrDaead getAeadOrDaead(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length == getSymmetricKeySizeInBytes()) {
            Class<Aead> cls = Aead.class;
            if (this.demKeyTypeUrl.equals(AeadConfig.AES_GCM_TYPE_URL)) {
                return new AeadOrDaead((Aead) Registry.getPrimitive(this.demKeyTypeUrl, (MessageLite) (AesGcmKey) ((AesGcmKey.Builder) AesGcmKey.newBuilder().mergeFrom(this.aesGcmKey)).setKeyValue(ByteString.copyFrom(bArr, 0, this.symmetricKeySize)).build(), cls));
            } else if (this.demKeyTypeUrl.equals(AeadConfig.AES_CTR_HMAC_AEAD_TYPE_URL)) {
                byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.aesCtrKeySize);
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.aesCtrKeySize, this.symmetricKeySize);
                AesCtrHmacAeadKey.Builder aesCtrKey = AesCtrHmacAeadKey.newBuilder().setVersion(this.aesCtrHmacAeadKey.getVersion()).setAesCtrKey((AesCtrKey) ((AesCtrKey.Builder) AesCtrKey.newBuilder().mergeFrom(this.aesCtrHmacAeadKey.getAesCtrKey())).setKeyValue(ByteString.copyFrom(copyOfRange)).build());
                return new AeadOrDaead((Aead) Registry.getPrimitive(this.demKeyTypeUrl, (MessageLite) (AesCtrHmacAeadKey) aesCtrKey.setHmacKey((HmacKey) ((HmacKey.Builder) HmacKey.newBuilder().mergeFrom(this.aesCtrHmacAeadKey.getHmacKey())).setKeyValue(ByteString.copyFrom(copyOfRange2)).build()).build(), cls));
            } else if (this.demKeyTypeUrl.equals(DeterministicAeadConfig.AES_SIV_TYPE_URL)) {
                return new AeadOrDaead((DeterministicAead) Registry.getPrimitive(this.demKeyTypeUrl, (MessageLite) (AesSivKey) ((AesSivKey.Builder) AesSivKey.newBuilder().mergeFrom(this.aesSivKey)).setKeyValue(ByteString.copyFrom(bArr, 0, this.symmetricKeySize)).build(), DeterministicAead.class));
            } else {
                throw new GeneralSecurityException("unknown DEM key type");
            }
        } else {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
    }

    public int getSymmetricKeySizeInBytes() {
        return this.symmetricKeySize;
    }
}
