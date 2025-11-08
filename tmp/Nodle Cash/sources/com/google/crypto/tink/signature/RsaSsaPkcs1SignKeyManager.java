package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey;
import com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPkcs1SignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class RsaSsaPkcs1SignKeyManager extends PrivateKeyTypeManager<RsaSsaPkcs1PrivateKey, RsaSsaPkcs1PublicKey> {
    public RsaSsaPkcs1SignKeyManager() {
        super(RsaSsaPkcs1PrivateKey.class, RsaSsaPkcs1PublicKey.class, new PrimitiveFactory<PublicKeySign, RsaSsaPkcs1PrivateKey>(PublicKeySign.class) {
            public PublicKeySign getPrimitive(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey) throws GeneralSecurityException {
                KeyFactory instance = EngineFactory.KEY_FACTORY.getInstance("RSA");
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) instance.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getD().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getP().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getQ().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getDp().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getDq().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getCrt().toByteArray())));
                RsaSsaPkcs1Params params = rsaSsaPkcs1PrivateKey.getPublicKey().getParams();
                SelfKeyTestValidators.validateRsaSsaPkcs1(rSAPrivateCrtKey, (RSAPublicKey) instance.generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()))), SigUtil.toHashType(params.getHashType()));
                return new RsaSsaPkcs1SignJce(rSAPrivateCrtKey, SigUtil.toHashType(params.getHashType()));
            }
        });
    }

    /* access modifiers changed from: private */
    public static RsaSsaPkcs1KeyFormat createKeyFormat(HashType hashType, int i3, BigInteger bigInteger) {
        return (RsaSsaPkcs1KeyFormat) RsaSsaPkcs1KeyFormat.newBuilder().setParams((RsaSsaPkcs1Params) RsaSsaPkcs1Params.newBuilder().setHashType(hashType).build()).setModulusSizeInBits(i3).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build();
    }

    private static KeyTemplate createKeyTemplate(HashType hashType, int i3, BigInteger bigInteger, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new RsaSsaPkcs1SignKeyManager().getKeyType(), createKeyFormat(hashType, i3, bigInteger).toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawRsa3072SsaPkcs1Sha256F4Template() {
        return createKeyTemplate(HashType.SHA256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawRsa4096SsaPkcs1Sha512F4Template() {
        return createKeyTemplate(HashType.SHA512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z2) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new RsaSsaPkcs1SignKeyManager(), new RsaSsaPkcs1VerifyKeyManager(), z2);
    }

    public static final KeyTemplate rsa3072SsaPkcs1Sha256F4Template() {
        return createKeyTemplate(HashType.SHA256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rsa4096SsaPkcs1Sha512F4Template() {
        return createKeyTemplate(HashType.SHA512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<RsaSsaPkcs1KeyFormat, RsaSsaPkcs1PrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<RsaSsaPkcs1KeyFormat, RsaSsaPkcs1PrivateKey>(RsaSsaPkcs1KeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<RsaSsaPkcs1KeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                HashType hashType = HashType.SHA256;
                BigInteger bigInteger = RSAKeyGenParameterSpec.F4;
                RsaSsaPkcs1KeyFormat access$000 = RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType, 3072, bigInteger);
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("RSA_SSA_PKCS1_3072_SHA256_F4", new KeyTypeManager.KeyFactory.KeyFormat(access$000, outputPrefixType));
                RsaSsaPkcs1KeyFormat access$0002 = RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType, 3072, bigInteger);
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("RSA_SSA_PKCS1_3072_SHA256_F4_RAW", new KeyTypeManager.KeyFactory.KeyFormat(access$0002, outputPrefixType2));
                hashMap.put("RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType, 3072, bigInteger), outputPrefixType2));
                HashType hashType2 = HashType.SHA512;
                hashMap.put("RSA_SSA_PKCS1_4096_SHA512_F4", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType2, 4096, bigInteger), outputPrefixType));
                hashMap.put("RSA_SSA_PKCS1_4096_SHA512_F4_RAW", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType2, 4096, bigInteger), outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public RsaSsaPkcs1PrivateKey createKey(RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat) throws GeneralSecurityException {
                RsaSsaPkcs1Params params = rsaSsaPkcs1KeyFormat.getParams();
                KeyPairGenerator instance = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                instance.initialize(new RSAKeyGenParameterSpec(rsaSsaPkcs1KeyFormat.getModulusSizeInBits(), new BigInteger(1, rsaSsaPkcs1KeyFormat.getPublicExponent().toByteArray())));
                KeyPair generateKeyPair = instance.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) generateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) generateKeyPair.getPrivate();
                return (RsaSsaPkcs1PrivateKey) RsaSsaPkcs1PrivateKey.newBuilder().setVersion(RsaSsaPkcs1SignKeyManager.this.getVersion()).setPublicKey((RsaSsaPkcs1PublicKey) RsaSsaPkcs1PublicKey.newBuilder().setVersion(RsaSsaPkcs1SignKeyManager.this.getVersion()).setParams(params).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            public RsaSsaPkcs1KeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return RsaSsaPkcs1KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat) throws GeneralSecurityException {
                SigUtil.validateRsaSsaPkcs1Params(rsaSsaPkcs1KeyFormat.getParams());
                Validators.validateRsaModulusSize(rsaSsaPkcs1KeyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPkcs1KeyFormat.getPublicExponent().toByteArray()));
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public RsaSsaPkcs1PublicKey getPublicKey(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey) throws GeneralSecurityException {
        return rsaSsaPkcs1PrivateKey.getPublicKey();
    }

    public RsaSsaPkcs1PrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return RsaSsaPkcs1PrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPkcs1PrivateKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()));
        SigUtil.validateRsaSsaPkcs1Params(rsaSsaPkcs1PrivateKey.getPublicKey().getParams());
    }
}
