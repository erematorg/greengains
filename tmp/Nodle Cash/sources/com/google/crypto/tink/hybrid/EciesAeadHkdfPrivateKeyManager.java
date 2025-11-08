package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesAeadHkdfPrivateKey;
import com.google.crypto.tink.proto.EciesAeadHkdfPublicKey;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EciesAeadHkdfHybridDecrypt;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class EciesAeadHkdfPrivateKeyManager extends PrivateKeyTypeManager<EciesAeadHkdfPrivateKey, EciesAeadHkdfPublicKey> {
    /* access modifiers changed from: private */
    public static final byte[] EMPTY_SALT = new byte[0];

    /* renamed from: com.google.crypto.tink.hybrid.EciesAeadHkdfPrivateKeyManager$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.crypto.tink.KeyTemplate$OutputPrefixType[] r0 = com.google.crypto.tink.KeyTemplate.OutputPrefixType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType = r0
                com.google.crypto.tink.KeyTemplate$OutputPrefixType r1 = com.google.crypto.tink.KeyTemplate.OutputPrefixType.TINK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.KeyTemplate$OutputPrefixType r1 = com.google.crypto.tink.KeyTemplate.OutputPrefixType.LEGACY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.KeyTemplate$OutputPrefixType r1 = com.google.crypto.tink.KeyTemplate.OutputPrefixType.RAW     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.crypto.tink.KeyTemplate$OutputPrefixType r1 = com.google.crypto.tink.KeyTemplate.OutputPrefixType.CRUNCHY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.hybrid.EciesAeadHkdfPrivateKeyManager.AnonymousClass3.<clinit>():void");
        }
    }

    public EciesAeadHkdfPrivateKeyManager() {
        super(EciesAeadHkdfPrivateKey.class, EciesAeadHkdfPublicKey.class, new PrimitiveFactory<HybridDecrypt, EciesAeadHkdfPrivateKey>(HybridDecrypt.class) {
            public HybridDecrypt getPrimitive(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey) throws GeneralSecurityException {
                EciesAeadHkdfParams params = eciesAeadHkdfPrivateKey.getPublicKey().getParams();
                EciesHkdfKemParams kemParams = params.getKemParams();
                return new EciesAeadHkdfHybridDecrypt(EllipticCurves.getEcPrivateKey(HybridUtil.toCurveType(kemParams.getCurveType()), eciesAeadHkdfPrivateKey.getKeyValue().toByteArray()), kemParams.getHkdfSalt().toByteArray(), HybridUtil.toHmacAlgo(kemParams.getHkdfHashType()), HybridUtil.toPointFormatType(params.getEcPointFormat()), new RegistryEciesAeadHkdfDemHelper(params.getDemParams().getAeadDem()));
            }
        });
    }

    /* access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<EciesAeadHkdfKeyFormat> createKeyFormat(EllipticCurveType ellipticCurveType, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate keyTemplate, byte[] bArr, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((EciesAeadHkdfKeyFormat) EciesAeadHkdfKeyFormat.newBuilder().setParams(createParams(ellipticCurveType, hashType, ecPointFormat, keyTemplate, bArr)).build(), outputPrefixType);
    }

    private static KeyTemplate createKeyTemplate(EllipticCurveType ellipticCurveType, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate keyTemplate, KeyTemplate.OutputPrefixType outputPrefixType, byte[] bArr) {
        return KeyTemplate.create(new EciesAeadHkdfPrivateKeyManager().getKeyType(), ((EciesAeadHkdfKeyFormat) EciesAeadHkdfKeyFormat.newBuilder().setParams(createParams(ellipticCurveType, hashType, ecPointFormat, keyTemplate, bArr)).build()).toByteArray(), outputPrefixType);
    }

    public static EciesAeadHkdfParams createParams(EllipticCurveType ellipticCurveType, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate keyTemplate, byte[] bArr) {
        EciesAeadDemParams.Builder newBuilder = EciesAeadDemParams.newBuilder();
        return (EciesAeadHkdfParams) EciesAeadHkdfParams.newBuilder().setKemParams((EciesHkdfKemParams) EciesHkdfKemParams.newBuilder().setCurveType(ellipticCurveType).setHkdfHashType(hashType).setHkdfSalt(ByteString.copyFrom(bArr)).build()).setDemParams((EciesAeadDemParams) newBuilder.setAeadDem((com.google.crypto.tink.proto.KeyTemplate) com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(keyTemplate.getTypeUrl()).setValue(ByteString.copyFrom(keyTemplate.getValue())).setOutputPrefixType(toProto(keyTemplate.getOutputPrefixType())).build()).build()).setEcPointFormat(ecPointFormat).build();
    }

    public static final KeyTemplate eciesP256HkdfHmacSha256Aes128CtrHmacSha256Template() {
        return createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AesCtrHmacAeadKeyManager.aes128CtrHmacSha256Template(), KeyTemplate.OutputPrefixType.TINK, EMPTY_SALT);
    }

    public static final KeyTemplate eciesP256HkdfHmacSha256Aes128GcmTemplate() {
        return createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AesGcmKeyManager.aes128GcmTemplate(), KeyTemplate.OutputPrefixType.TINK, EMPTY_SALT);
    }

    public static final KeyTemplate rawEciesP256HkdfHmacSha256Aes128CtrHmacSha256CompressedTemplate() {
        return createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AesCtrHmacAeadKeyManager.aes128CtrHmacSha256Template(), KeyTemplate.OutputPrefixType.RAW, EMPTY_SALT);
    }

    public static final KeyTemplate rawEciesP256HkdfHmacSha256Aes128GcmCompressedTemplate() {
        return createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AesGcmKeyManager.aes128GcmTemplate(), KeyTemplate.OutputPrefixType.RAW, EMPTY_SALT);
    }

    public static void registerPair(boolean z2) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new EciesAeadHkdfPrivateKeyManager(), new EciesAeadHkdfPublicKeyManager(), z2);
    }

    private static OutputPrefixType toProto(KeyTemplate.OutputPrefixType outputPrefixType) {
        int i3 = AnonymousClass3.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[outputPrefixType.ordinal()];
        if (i3 == 1) {
            return OutputPrefixType.TINK;
        }
        if (i3 == 2) {
            return OutputPrefixType.LEGACY;
        }
        if (i3 == 3) {
            return OutputPrefixType.RAW;
        }
        if (i3 == 4) {
            return OutputPrefixType.CRUNCHY;
        }
        throw new IllegalArgumentException("Unknown output prefix type");
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<EciesAeadHkdfKeyFormat, EciesAeadHkdfPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<EciesAeadHkdfKeyFormat, EciesAeadHkdfPrivateKey>(EciesAeadHkdfKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<EciesAeadHkdfKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                EllipticCurveType ellipticCurveType = EllipticCurveType.NIST_P256;
                HashType hashType = HashType.SHA256;
                EcPointFormat ecPointFormat = EcPointFormat.UNCOMPRESSED;
                KeyTemplate keyTemplate = KeyTemplates.get("AES128_GCM");
                byte[] access$000 = EciesAeadHkdfPrivateKeyManager.EMPTY_SALT;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                EcPointFormat ecPointFormat2 = ecPointFormat;
                hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat2, keyTemplate, access$000, outputPrefixType));
                KeyTemplate keyTemplate2 = KeyTemplates.get("AES128_GCM");
                byte[] access$0002 = EciesAeadHkdfPrivateKeyManager.EMPTY_SALT;
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat2, keyTemplate2, access$0002, outputPrefixType2));
                EcPointFormat ecPointFormat3 = EcPointFormat.COMPRESSED;
                EcPointFormat ecPointFormat4 = ecPointFormat3;
                hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat4, KeyTemplates.get("AES128_GCM"), EciesAeadHkdfPrivateKeyManager.EMPTY_SALT, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType3 = outputPrefixType2;
                hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat4, KeyTemplates.get("AES128_GCM"), EciesAeadHkdfPrivateKeyManager.EMPTY_SALT, outputPrefixType3));
                hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat4, KeyTemplates.get("AES128_GCM"), EciesAeadHkdfPrivateKeyManager.EMPTY_SALT, outputPrefixType3));
                EcPointFormat ecPointFormat5 = ecPointFormat;
                hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat5, KeyTemplates.get("AES128_CTR_HMAC_SHA256"), EciesAeadHkdfPrivateKeyManager.EMPTY_SALT, outputPrefixType));
                hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat5, KeyTemplates.get("AES128_CTR_HMAC_SHA256"), EciesAeadHkdfPrivateKeyManager.EMPTY_SALT, outputPrefixType2));
                EcPointFormat ecPointFormat6 = ecPointFormat3;
                hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat6, KeyTemplates.get("AES128_CTR_HMAC_SHA256"), EciesAeadHkdfPrivateKeyManager.EMPTY_SALT, outputPrefixType));
                hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", EciesAeadHkdfPrivateKeyManager.createKeyFormat(ellipticCurveType, hashType, ecPointFormat6, KeyTemplates.get("AES128_CTR_HMAC_SHA256"), EciesAeadHkdfPrivateKeyManager.EMPTY_SALT, outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public EciesAeadHkdfPrivateKey createKey(EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat) throws GeneralSecurityException {
                KeyPair generateKeyPair = EllipticCurves.generateKeyPair(HybridUtil.toCurveType(eciesAeadHkdfKeyFormat.getParams().getKemParams().getCurveType()));
                ECPoint w2 = ((ECPublicKey) generateKeyPair.getPublic()).getW();
                return (EciesAeadHkdfPrivateKey) EciesAeadHkdfPrivateKey.newBuilder().setVersion(EciesAeadHkdfPrivateKeyManager.this.getVersion()).setPublicKey((EciesAeadHkdfPublicKey) EciesAeadHkdfPublicKey.newBuilder().setVersion(EciesAeadHkdfPrivateKeyManager.this.getVersion()).setParams(eciesAeadHkdfKeyFormat.getParams()).setX(ByteString.copyFrom(w2.getAffineX().toByteArray())).setY(ByteString.copyFrom(w2.getAffineY().toByteArray())).build()).setKeyValue(ByteString.copyFrom(((ECPrivateKey) generateKeyPair.getPrivate()).getS().toByteArray())).build();
            }

            public EciesAeadHkdfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return EciesAeadHkdfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat) throws GeneralSecurityException {
                HybridUtil.validate(eciesAeadHkdfKeyFormat.getParams());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public EciesAeadHkdfPublicKey getPublicKey(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey) throws GeneralSecurityException {
        return eciesAeadHkdfPrivateKey.getPublicKey();
    }

    public EciesAeadHkdfPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return EciesAeadHkdfPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey) throws GeneralSecurityException {
        if (!eciesAeadHkdfPrivateKey.getKeyValue().isEmpty()) {
            Validators.validateVersion(eciesAeadHkdfPrivateKey.getVersion(), getVersion());
            HybridUtil.validate(eciesAeadHkdfPrivateKey.getPublicKey().getParams());
            return;
        }
        throw new GeneralSecurityException("invalid ECIES private key");
    }
}
