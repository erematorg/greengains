package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HpkeAead;
import com.google.crypto.tink.proto.HpkeKdf;
import com.google.crypto.tink.proto.HpkeKem;
import com.google.crypto.tink.proto.HpkeKeyFormat;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePrivateKey;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.X25519;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class HpkePrivateKeyManager extends PrivateKeyTypeManager<HpkePrivateKey, HpkePublicKey> {

    /* renamed from: com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.crypto.tink.proto.HpkeKem[] r0 = com.google.crypto.tink.proto.HpkeKem.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem = r0
                com.google.crypto.tink.proto.HpkeKem r1 = com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HpkeKem     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.HpkeKem r1 = com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HpkeKem     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.proto.HpkeKem r1 = com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HpkeKem     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.crypto.tink.proto.HpkeKem r1 = com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.AnonymousClass3.<clinit>():void");
        }
    }

    public HpkePrivateKeyManager() {
        super(HpkePrivateKey.class, HpkePublicKey.class, new PrimitiveFactory<HybridDecrypt, HpkePrivateKey>(HybridDecrypt.class) {
            public HybridDecrypt getPrimitive(HpkePrivateKey hpkePrivateKey) throws GeneralSecurityException {
                return HpkeDecrypt.createHpkeDecrypt(hpkePrivateKey);
            }
        });
    }

    /* access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<HpkeKeyFormat> createKeyFormat(HpkeKem hpkeKem, HpkeKdf hpkeKdf, HpkeAead hpkeAead, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((HpkeKeyFormat) HpkeKeyFormat.newBuilder().setParams((HpkeParams) HpkeParams.newBuilder().setKem(hpkeKem).setKdf(hpkeKdf).setAead(hpkeAead).build()).build(), outputPrefixType);
    }

    public static void registerPair(boolean z2) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new HpkePrivateKeyManager(), new HpkePublicKeyManager(), z2);
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<HpkeKeyFormat, HpkePrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HpkeKeyFormat, HpkePrivateKey>(HpkeKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<HpkeKeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                HpkeKem hpkeKem = HpkeKem.DHKEM_X25519_HKDF_SHA256;
                HpkeKdf hpkeKdf = HpkeKdf.HKDF_SHA256;
                HpkeAead hpkeAead = HpkeAead.AES_128_GCM;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead, outputPrefixType2));
                HpkeAead hpkeAead2 = HpkeAead.AES_256_GCM;
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead2, outputPrefixType));
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead2, outputPrefixType2));
                HpkeAead hpkeAead3 = HpkeAead.CHACHA20_POLY1305;
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead3, outputPrefixType));
                hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead3, outputPrefixType2));
                HpkeKem hpkeKem2 = HpkeKem.DHKEM_P256_HKDF_SHA256;
                hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem2, hpkeKdf, hpkeAead, outputPrefixType));
                hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem2, hpkeKdf, hpkeAead, outputPrefixType2));
                hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem2, hpkeKdf, hpkeAead2, outputPrefixType));
                hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem2, hpkeKdf, hpkeAead2, outputPrefixType2));
                HpkeKem hpkeKem3 = HpkeKem.DHKEM_P384_HKDF_SHA384;
                HpkeKdf hpkeKdf2 = HpkeKdf.HKDF_SHA384;
                hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem3, hpkeKdf2, hpkeAead, outputPrefixType));
                hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem3, hpkeKdf2, hpkeAead, outputPrefixType2));
                hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem3, hpkeKdf2, hpkeAead2, outputPrefixType));
                hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem3, hpkeKdf2, hpkeAead2, outputPrefixType2));
                HpkeKem hpkeKem4 = HpkeKem.DHKEM_P521_HKDF_SHA512;
                HpkeKdf hpkeKdf3 = HpkeKdf.HKDF_SHA512;
                hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem4, hpkeKdf3, hpkeAead, outputPrefixType));
                hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem4, hpkeKdf3, hpkeAead, outputPrefixType2));
                hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem4, hpkeKdf3, hpkeAead2, outputPrefixType));
                hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem4, hpkeKdf3, hpkeAead2, outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public HpkePrivateKey createKey(HpkeKeyFormat hpkeKeyFormat) throws GeneralSecurityException {
                byte[] bArr;
                byte[] bArr2;
                int i3 = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[hpkeKeyFormat.getParams().getKem().ordinal()];
                if (i3 == 1) {
                    bArr = X25519.generatePrivateKey();
                    bArr2 = X25519.publicFromPrivate(bArr);
                } else if (i3 == 2 || i3 == 3 || i3 == 4) {
                    EllipticCurves.CurveType nistHpkeKemToCurve = HpkeUtil.nistHpkeKemToCurve(hpkeKeyFormat.getParams().getKem());
                    KeyPair generateKeyPair = EllipticCurves.generateKeyPair(nistHpkeKemToCurve);
                    bArr2 = EllipticCurves.pointEncode(nistHpkeKemToCurve, EllipticCurves.PointFormatType.UNCOMPRESSED, ((ECPublicKey) generateKeyPair.getPublic()).getW());
                    bArr = ((ECPrivateKey) generateKeyPair.getPrivate()).getS().toByteArray();
                } else {
                    throw new GeneralSecurityException("Invalid KEM");
                }
                return (HpkePrivateKey) HpkePrivateKey.newBuilder().setVersion(HpkePrivateKeyManager.this.getVersion()).setPublicKey((HpkePublicKey) HpkePublicKey.newBuilder().setVersion(HpkePrivateKeyManager.this.getVersion()).setParams(hpkeKeyFormat.getParams()).setPublicKey(ByteString.copyFrom(bArr2)).build()).setPrivateKey(ByteString.copyFrom(bArr)).build();
            }

            public HpkeKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return HpkeKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(HpkeKeyFormat hpkeKeyFormat) throws GeneralSecurityException {
                HpkeUtil.validateParams(hpkeKeyFormat.getParams());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public HpkePublicKey getPublicKey(HpkePrivateKey hpkePrivateKey) {
        return hpkePrivateKey.getPublicKey();
    }

    public HpkePrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HpkePrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(HpkePrivateKey hpkePrivateKey) throws GeneralSecurityException {
        if (hpkePrivateKey.getPrivateKey().isEmpty()) {
            throw new GeneralSecurityException("Private key is empty.");
        } else if (hpkePrivateKey.hasPublicKey()) {
            Validators.validateVersion(hpkePrivateKey.getVersion(), getVersion());
            HpkeUtil.validateParams(hpkePrivateKey.getPublicKey().getParams());
        } else {
            throw new GeneralSecurityException("Missing public key.");
        }
    }
}
