package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.RsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
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

public final class RsaSsaPssSignKeyManager extends PrivateKeyTypeManager<RsaSsaPssPrivateKey, RsaSsaPssPublicKey> {
    public RsaSsaPssSignKeyManager() {
        super(RsaSsaPssPrivateKey.class, RsaSsaPssPublicKey.class, new PrimitiveFactory<PublicKeySign, RsaSsaPssPrivateKey>(PublicKeySign.class) {
            public PublicKeySign getPrimitive(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) throws GeneralSecurityException {
                KeyFactory instance = EngineFactory.KEY_FACTORY.getInstance("RSA");
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) instance.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getD().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getP().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getQ().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getDp().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getDq().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getCrt().toByteArray())));
                RsaSsaPssParams params = rsaSsaPssPrivateKey.getPublicKey().getParams();
                SelfKeyTestValidators.validateRsaSsaPss(rSAPrivateCrtKey, (RSAPublicKey) instance.generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()))), SigUtil.toHashType(params.getSigHash()), SigUtil.toHashType(params.getMgf1Hash()), params.getSaltLength());
                return new RsaSsaPssSignJce(rSAPrivateCrtKey, SigUtil.toHashType(params.getSigHash()), SigUtil.toHashType(params.getMgf1Hash()), params.getSaltLength());
            }
        });
    }

    /* access modifiers changed from: private */
    public static RsaSsaPssKeyFormat createKeyFormat(HashType hashType, HashType hashType2, int i3, int i4, BigInteger bigInteger) {
        return (RsaSsaPssKeyFormat) RsaSsaPssKeyFormat.newBuilder().setParams((RsaSsaPssParams) RsaSsaPssParams.newBuilder().setSigHash(hashType).setMgf1Hash(hashType2).setSaltLength(i3).build()).setModulusSizeInBits(i4).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build();
    }

    private static KeyTemplate createKeyTemplate(HashType hashType, HashType hashType2, int i3, int i4, BigInteger bigInteger, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new RsaSsaPssSignKeyManager().getKeyType(), createKeyFormat(hashType, hashType2, i3, i4, bigInteger).toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawRsa3072PssSha256F4Template() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawRsa4096PssSha512F4Template() {
        HashType hashType = HashType.SHA512;
        return createKeyTemplate(hashType, hashType, 64, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z2) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new RsaSsaPssSignKeyManager(), new RsaSsaPssVerifyKeyManager(), z2);
    }

    public static final KeyTemplate rsa3072PssSha256F4Template() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rsa4096PssSha512F4Template() {
        HashType hashType = HashType.SHA512;
        return createKeyTemplate(hashType, hashType, 64, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<RsaSsaPssKeyFormat, RsaSsaPssPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<RsaSsaPssKeyFormat, RsaSsaPssPrivateKey>(RsaSsaPssKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<RsaSsaPssKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                HashType hashType = HashType.SHA256;
                BigInteger bigInteger = RSAKeyGenParameterSpec.F4;
                RsaSsaPssKeyFormat access$000 = RsaSsaPssSignKeyManager.createKeyFormat(hashType, hashType, 32, 3072, bigInteger);
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("RSA_SSA_PSS_3072_SHA256_F4", new KeyTypeManager.KeyFactory.KeyFormat(access$000, outputPrefixType));
                RsaSsaPssKeyFormat access$0002 = RsaSsaPssSignKeyManager.createKeyFormat(hashType, hashType, 32, 3072, bigInteger);
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("RSA_SSA_PSS_3072_SHA256_F4_RAW", new KeyTypeManager.KeyFactory.KeyFormat(access$0002, outputPrefixType2));
                hashMap.put("RSA_SSA_PSS_3072_SHA256_SHA256_32_F4", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(hashType, hashType, 32, 3072, bigInteger), outputPrefixType));
                HashType hashType2 = HashType.SHA512;
                hashMap.put("RSA_SSA_PSS_4096_SHA512_F4", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(hashType2, hashType2, 64, 4096, bigInteger), outputPrefixType));
                hashMap.put("RSA_SSA_PSS_4096_SHA512_F4_RAW", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(hashType2, hashType2, 64, 4096, bigInteger), outputPrefixType2));
                hashMap.put("RSA_SSA_PSS_4096_SHA512_SHA512_64_F4", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(hashType2, hashType2, 64, 4096, bigInteger), outputPrefixType));
                return Collections.unmodifiableMap(hashMap);
            }

            public RsaSsaPssPrivateKey createKey(RsaSsaPssKeyFormat rsaSsaPssKeyFormat) throws GeneralSecurityException {
                RsaSsaPssParams params = rsaSsaPssKeyFormat.getParams();
                Validators.validateRsaModulusSize(rsaSsaPssKeyFormat.getModulusSizeInBits());
                Validators.validateSignatureHash(SigUtil.toHashType(params.getSigHash()));
                KeyPairGenerator instance = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                instance.initialize(new RSAKeyGenParameterSpec(rsaSsaPssKeyFormat.getModulusSizeInBits(), new BigInteger(1, rsaSsaPssKeyFormat.getPublicExponent().toByteArray())));
                KeyPair generateKeyPair = instance.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) generateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) generateKeyPair.getPrivate();
                return (RsaSsaPssPrivateKey) RsaSsaPssPrivateKey.newBuilder().setVersion(RsaSsaPssSignKeyManager.this.getVersion()).setPublicKey((RsaSsaPssPublicKey) RsaSsaPssPublicKey.newBuilder().setVersion(RsaSsaPssSignKeyManager.this.getVersion()).setParams(params).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            public RsaSsaPssKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return RsaSsaPssKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(RsaSsaPssKeyFormat rsaSsaPssKeyFormat) throws GeneralSecurityException {
                SigUtil.validateRsaSsaPssParams(rsaSsaPssKeyFormat.getParams());
                Validators.validateRsaModulusSize(rsaSsaPssKeyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssKeyFormat.getPublicExponent().toByteArray()));
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public RsaSsaPssPublicKey getPublicKey(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) throws GeneralSecurityException {
        return rsaSsaPssPrivateKey.getPublicKey();
    }

    public RsaSsaPssPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return RsaSsaPssPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPssPrivateKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()));
        SigUtil.validateRsaSsaPssParams(rsaSsaPssPrivateKey.getPublicKey().getParams());
    }
}
