package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaKeyFormat;
import com.google.crypto.tink.proto.JwtEcdsaPrivateKey;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class JwtEcdsaSignKeyManager extends PrivateKeyTypeManager<JwtEcdsaPrivateKey, JwtEcdsaPublicKey> {

    public static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtEcdsaPrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        private static final void selfTestKey(ECPrivateKey eCPrivateKey, JwtEcdsaPrivateKey jwtEcdsaPrivateKey) throws GeneralSecurityException {
            SelfKeyTestValidators.validateEcdsa(eCPrivateKey, EllipticCurves.getEcPublicKey(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaPrivateKey.getPublicKey().getAlgorithm()), jwtEcdsaPrivateKey.getPublicKey().getX().toByteArray(), jwtEcdsaPrivateKey.getPublicKey().getY().toByteArray()), JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(jwtEcdsaPrivateKey.getPublicKey().getAlgorithm()), EllipticCurves.EcdsaEncoding.IEEE_P1363);
        }

        public JwtPublicKeySignInternal getPrimitive(JwtEcdsaPrivateKey jwtEcdsaPrivateKey) throws GeneralSecurityException {
            final Optional optional;
            ECPrivateKey ecPrivateKey = EllipticCurves.getEcPrivateKey(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaPrivateKey.getPublicKey().getAlgorithm()), jwtEcdsaPrivateKey.getKeyValue().toByteArray());
            selfTestKey(ecPrivateKey, jwtEcdsaPrivateKey);
            JwtEcdsaAlgorithm algorithm = jwtEcdsaPrivateKey.getPublicKey().getAlgorithm();
            final EcdsaSignJce ecdsaSignJce = new EcdsaSignJce(ecPrivateKey, JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(algorithm), EllipticCurves.EcdsaEncoding.IEEE_P1363);
            final String name = algorithm.name();
            if (jwtEcdsaPrivateKey.getPublicKey().hasCustomKid()) {
                optional = Optional.of(jwtEcdsaPrivateKey.getPublicKey().getCustomKid().getValue());
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
                    return JwtFormat.createSignedCompact(createUnsignedCompact, ecdsaSignJce.sign(createUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    public JwtEcdsaSignKeyManager() {
        super(JwtEcdsaPrivateKey.class, JwtEcdsaPublicKey.class, new JwtPublicKeySignFactory());
    }

    /* access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat> createKeyFormat(JwtEcdsaAlgorithm jwtEcdsaAlgorithm, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((JwtEcdsaKeyFormat) JwtEcdsaKeyFormat.newBuilder().setAlgorithm(jwtEcdsaAlgorithm).build(), outputPrefixType);
    }

    public static void registerPair(boolean z2) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new JwtEcdsaSignKeyManager(), new JwtEcdsaVerifyKeyManager(), z2);
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtEcdsaPrivateKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<JwtEcdsaKeyFormat, JwtEcdsaPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtEcdsaKeyFormat, JwtEcdsaPrivateKey>(JwtEcdsaKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                JwtEcdsaAlgorithm jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES256;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("JWT_ES256_RAW", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("JWT_ES256", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm, outputPrefixType2));
                JwtEcdsaAlgorithm jwtEcdsaAlgorithm2 = JwtEcdsaAlgorithm.ES384;
                hashMap.put("JWT_ES384_RAW", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm2, outputPrefixType));
                hashMap.put("JWT_ES384", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm2, outputPrefixType2));
                JwtEcdsaAlgorithm jwtEcdsaAlgorithm3 = JwtEcdsaAlgorithm.ES512;
                hashMap.put("JWT_ES512_RAW", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm3, outputPrefixType));
                hashMap.put("JWT_ES512", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm3, outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public JwtEcdsaPrivateKey createKey(JwtEcdsaKeyFormat jwtEcdsaKeyFormat) throws GeneralSecurityException {
                JwtEcdsaAlgorithm algorithm = jwtEcdsaKeyFormat.getAlgorithm();
                KeyPair generateKeyPair = EllipticCurves.generateKeyPair(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaKeyFormat.getAlgorithm()));
                ECPoint w2 = ((ECPublicKey) generateKeyPair.getPublic()).getW();
                return (JwtEcdsaPrivateKey) JwtEcdsaPrivateKey.newBuilder().setVersion(JwtEcdsaSignKeyManager.this.getVersion()).setPublicKey((JwtEcdsaPublicKey) JwtEcdsaPublicKey.newBuilder().setVersion(JwtEcdsaSignKeyManager.this.getVersion()).setAlgorithm(algorithm).setX(ByteString.copyFrom(w2.getAffineX().toByteArray())).setY(ByteString.copyFrom(w2.getAffineY().toByteArray())).build()).setKeyValue(ByteString.copyFrom(((ECPrivateKey) generateKeyPair.getPrivate()).getS().toByteArray())).build();
            }

            public JwtEcdsaPrivateKey deriveKey(JwtEcdsaKeyFormat jwtEcdsaKeyFormat, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            public JwtEcdsaKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtEcdsaKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(JwtEcdsaKeyFormat jwtEcdsaKeyFormat) throws GeneralSecurityException {
                JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(jwtEcdsaKeyFormat.getAlgorithm());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    public JwtEcdsaPublicKey getPublicKey(JwtEcdsaPrivateKey jwtEcdsaPrivateKey) {
        return jwtEcdsaPrivateKey.getPublicKey();
    }

    public JwtEcdsaPrivateKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtEcdsaPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(JwtEcdsaPrivateKey jwtEcdsaPrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtEcdsaPrivateKey.getVersion(), getVersion());
        JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(jwtEcdsaPrivateKey.getPublicKey().getAlgorithm());
    }
}
