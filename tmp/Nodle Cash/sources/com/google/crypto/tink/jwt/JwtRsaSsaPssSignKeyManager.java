package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
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
import java.util.Optional;

public final class JwtRsaSsaPssSignKeyManager extends PrivateKeyTypeManager<JwtRsaSsaPssPrivateKey, JwtRsaSsaPssPublicKey> {

    public static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtRsaSsaPssPrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) throws GeneralSecurityException {
            final Optional optional;
            RSAPrivateCrtKey access$000 = JwtRsaSsaPssSignKeyManager.createPrivateKey(jwtRsaSsaPssPrivateKey);
            JwtRsaSsaPssSignKeyManager.selfTestKey(access$000, jwtRsaSsaPssPrivateKey);
            JwtRsaSsaPssAlgorithm algorithm = jwtRsaSsaPssPrivateKey.getPublicKey().getAlgorithm();
            Enums.HashType hashForPssAlgorithm = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(algorithm);
            final RsaSsaPssSignJce rsaSsaPssSignJce = new RsaSsaPssSignJce(access$000, hashForPssAlgorithm, hashForPssAlgorithm, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(algorithm));
            final String name = algorithm.name();
            if (jwtRsaSsaPssPrivateKey.getPublicKey().hasCustomKid()) {
                optional = Optional.of(jwtRsaSsaPssPrivateKey.getPublicKey().getCustomKid().getValue());
            } else {
                optional = Optional.empty();
            }
            return new JwtPublicKeySignInternal() {
                public String signAndEncodeWithKid(RawJwt rawJwt, Optional<String> optional) throws GeneralSecurityException {
                    if (optional.isPresent()) {
                        if (!optional.isPresent()) {
                            optional = optional;
                        } else {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                    }
                    String createUnsignedCompact = JwtFormat.createUnsignedCompact(name, optional, rawJwt);
                    return JwtFormat.createSignedCompact(createUnsignedCompact, rsaSsaPssSignJce.sign(createUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    public JwtRsaSsaPssSignKeyManager() {
        super(JwtRsaSsaPssPrivateKey.class, JwtRsaSsaPssPublicKey.class, new JwtPublicKeySignFactory());
    }

    /* access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat> createKeyFormat(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm, int i3, BigInteger bigInteger, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((JwtRsaSsaPssKeyFormat) JwtRsaSsaPssKeyFormat.newBuilder().setAlgorithm(jwtRsaSsaPssAlgorithm).setModulusSizeInBits(i3).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build(), outputPrefixType);
    }

    /* access modifiers changed from: private */
    public static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) throws GeneralSecurityException {
        return (RSAPrivateCrtKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getD().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getP().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getQ().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getDp().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getDq().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getCrt().toByteArray())));
    }

    public static void registerPair(boolean z2) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPssSignKeyManager(), new JwtRsaSsaPssVerifyKeyManager(), z2);
    }

    /* access modifiers changed from: private */
    public static final void selfTestKey(RSAPrivateCrtKey rSAPrivateCrtKey, JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) throws GeneralSecurityException {
        JwtRsaSsaPssAlgorithm algorithm = jwtRsaSsaPssPrivateKey.getPublicKey().getAlgorithm();
        Enums.HashType hashForPssAlgorithm = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(algorithm);
        SelfKeyTestValidators.validateRsaSsaPss(rSAPrivateCrtKey, (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()))), hashForPssAlgorithm, hashForPssAlgorithm, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(algorithm));
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<JwtRsaSsaPssKeyFormat, JwtRsaSsaPssPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtRsaSsaPssKeyFormat, JwtRsaSsaPssPrivateKey>(JwtRsaSsaPssKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS256;
                BigInteger bigInteger = RSAKeyGenParameterSpec.F4;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("JWT_PS256_2048_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm, 2048, bigInteger, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("JWT_PS256_2048_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm, 2048, bigInteger, outputPrefixType2));
                hashMap.put("JWT_PS256_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm, 3072, bigInteger, outputPrefixType));
                hashMap.put("JWT_PS256_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm, 3072, bigInteger, outputPrefixType2));
                JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm2 = JwtRsaSsaPssAlgorithm.PS384;
                hashMap.put("JWT_PS384_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm2, 3072, bigInteger, outputPrefixType));
                hashMap.put("JWT_PS384_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm2, 3072, bigInteger, outputPrefixType2));
                JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm3 = JwtRsaSsaPssAlgorithm.PS512;
                hashMap.put("JWT_PS512_4096_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm3, 4096, bigInteger, outputPrefixType));
                hashMap.put("JWT_PS512_4096_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm3, 4096, bigInteger, outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public JwtRsaSsaPssPrivateKey createKey(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat) throws GeneralSecurityException {
                JwtRsaSsaPssAlgorithm algorithm = jwtRsaSsaPssKeyFormat.getAlgorithm();
                KeyPairGenerator instance = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                instance.initialize(new RSAKeyGenParameterSpec(jwtRsaSsaPssKeyFormat.getModulusSizeInBits(), new BigInteger(1, jwtRsaSsaPssKeyFormat.getPublicExponent().toByteArray())));
                KeyPair generateKeyPair = instance.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) generateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) generateKeyPair.getPrivate();
                return (JwtRsaSsaPssPrivateKey) JwtRsaSsaPssPrivateKey.newBuilder().setVersion(JwtRsaSsaPssSignKeyManager.this.getVersion()).setPublicKey((JwtRsaSsaPssPublicKey) JwtRsaSsaPssPublicKey.newBuilder().setVersion(JwtRsaSsaPssSignKeyManager.this.getVersion()).setAlgorithm(algorithm).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            public JwtRsaSsaPssPrivateKey deriveKey(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            public JwtRsaSsaPssKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtRsaSsaPssKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(jwtRsaSsaPssKeyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPssKeyFormat.getPublicExponent().toByteArray()));
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public JwtRsaSsaPssPublicKey getPublicKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) {
        return jwtRsaSsaPssPrivateKey.getPublicKey();
    }

    public JwtRsaSsaPssPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtRsaSsaPssPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPssPrivateKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()));
    }
}
