package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPkcs1SignJce;
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

public final class JwtRsaSsaPkcs1SignKeyManager extends PrivateKeyTypeManager<JwtRsaSsaPkcs1PrivateKey, JwtRsaSsaPkcs1PublicKey> {

    public static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtRsaSsaPkcs1PrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey) throws GeneralSecurityException {
            final Optional optional;
            RSAPrivateCrtKey access$000 = JwtRsaSsaPkcs1SignKeyManager.createPrivateKey(jwtRsaSsaPkcs1PrivateKey);
            JwtRsaSsaPkcs1SignKeyManager.selfTestKey(access$000, jwtRsaSsaPkcs1PrivateKey);
            JwtRsaSsaPkcs1Algorithm algorithm = jwtRsaSsaPkcs1PrivateKey.getPublicKey().getAlgorithm();
            final RsaSsaPkcs1SignJce rsaSsaPkcs1SignJce = new RsaSsaPkcs1SignJce(access$000, JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(algorithm));
            final String name = algorithm.name();
            if (jwtRsaSsaPkcs1PrivateKey.getPublicKey().hasCustomKid()) {
                optional = Optional.of(jwtRsaSsaPkcs1PrivateKey.getPublicKey().getCustomKid().getValue());
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
                    return JwtFormat.createSignedCompact(createUnsignedCompact, rsaSsaPkcs1SignJce.sign(createUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    public JwtRsaSsaPkcs1SignKeyManager() {
        super(JwtRsaSsaPkcs1PrivateKey.class, JwtRsaSsaPkcs1PublicKey.class, new JwtPublicKeySignFactory());
    }

    /* access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPkcs1KeyFormat> createKeyFormat(JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm, int i3, BigInteger bigInteger, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((JwtRsaSsaPkcs1KeyFormat) JwtRsaSsaPkcs1KeyFormat.newBuilder().setAlgorithm(jwtRsaSsaPkcs1Algorithm).setModulusSizeInBits(i3).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build(), outputPrefixType);
    }

    /* access modifiers changed from: private */
    public static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey) throws GeneralSecurityException {
        return (RSAPrivateCrtKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getD().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getP().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getQ().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getDp().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getDq().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getCrt().toByteArray())));
    }

    public static void registerPair(boolean z2) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPkcs1SignKeyManager(), new JwtRsaSsaPkcs1VerifyKeyManager(), z2);
    }

    /* access modifiers changed from: private */
    public static final void selfTestKey(RSAPrivateCrtKey rSAPrivateCrtKey, JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey) throws GeneralSecurityException {
        SelfKeyTestValidators.validateRsaSsaPkcs1(rSAPrivateCrtKey, (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()))), JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(jwtRsaSsaPkcs1PrivateKey.getPublicKey().getAlgorithm()));
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PrivateKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<JwtRsaSsaPkcs1KeyFormat, JwtRsaSsaPkcs1PrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtRsaSsaPkcs1KeyFormat, JwtRsaSsaPkcs1PrivateKey>(JwtRsaSsaPkcs1KeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPkcs1KeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS256;
                BigInteger bigInteger = RSAKeyGenParameterSpec.F4;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("JWT_RS256_2048_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(jwtRsaSsaPkcs1Algorithm, 2048, bigInteger, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("JWT_RS256_2048_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(jwtRsaSsaPkcs1Algorithm, 2048, bigInteger, outputPrefixType2));
                hashMap.put("JWT_RS256_3072_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(jwtRsaSsaPkcs1Algorithm, 3072, bigInteger, outputPrefixType));
                hashMap.put("JWT_RS256_3072_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(jwtRsaSsaPkcs1Algorithm, 3072, bigInteger, outputPrefixType2));
                JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm2 = JwtRsaSsaPkcs1Algorithm.RS384;
                hashMap.put("JWT_RS384_3072_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(jwtRsaSsaPkcs1Algorithm2, 3072, bigInteger, outputPrefixType));
                hashMap.put("JWT_RS384_3072_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(jwtRsaSsaPkcs1Algorithm2, 3072, bigInteger, outputPrefixType2));
                JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm3 = JwtRsaSsaPkcs1Algorithm.RS512;
                hashMap.put("JWT_RS512_4096_F4_RAW", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(jwtRsaSsaPkcs1Algorithm3, 4096, bigInteger, outputPrefixType));
                hashMap.put("JWT_RS512_4096_F4", JwtRsaSsaPkcs1SignKeyManager.createKeyFormat(jwtRsaSsaPkcs1Algorithm3, 4096, bigInteger, outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public JwtRsaSsaPkcs1PrivateKey createKey(JwtRsaSsaPkcs1KeyFormat jwtRsaSsaPkcs1KeyFormat) throws GeneralSecurityException {
                JwtRsaSsaPkcs1Algorithm algorithm = jwtRsaSsaPkcs1KeyFormat.getAlgorithm();
                KeyPairGenerator instance = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                instance.initialize(new RSAKeyGenParameterSpec(jwtRsaSsaPkcs1KeyFormat.getModulusSizeInBits(), new BigInteger(1, jwtRsaSsaPkcs1KeyFormat.getPublicExponent().toByteArray())));
                KeyPair generateKeyPair = instance.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) generateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) generateKeyPair.getPrivate();
                return (JwtRsaSsaPkcs1PrivateKey) JwtRsaSsaPkcs1PrivateKey.newBuilder().setVersion(JwtRsaSsaPkcs1SignKeyManager.this.getVersion()).setPublicKey((JwtRsaSsaPkcs1PublicKey) JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(JwtRsaSsaPkcs1SignKeyManager.this.getVersion()).setAlgorithm(algorithm).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            public JwtRsaSsaPkcs1PrivateKey deriveKey(JwtRsaSsaPkcs1KeyFormat jwtRsaSsaPkcs1KeyFormat, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            public JwtRsaSsaPkcs1KeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtRsaSsaPkcs1KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(JwtRsaSsaPkcs1KeyFormat jwtRsaSsaPkcs1KeyFormat) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(jwtRsaSsaPkcs1KeyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPkcs1KeyFormat.getPublicExponent().toByteArray()));
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public JwtRsaSsaPkcs1PublicKey getPublicKey(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey) {
        return jwtRsaSsaPkcs1PrivateKey.getPublicKey();
    }

    public JwtRsaSsaPkcs1PrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtRsaSsaPkcs1PrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPkcs1PrivateKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()));
    }
}
