package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.jwt.JwtFormat;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.Validators;
import com.google.gson.JsonObject;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Optional;

class JwtEcdsaVerifyKeyManager extends KeyTypeManager<JwtEcdsaPublicKey> {

    /* renamed from: com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.crypto.tink.proto.JwtEcdsaAlgorithm[] r0 = com.google.crypto.tink.proto.JwtEcdsaAlgorithm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm = r0
                com.google.crypto.tink.proto.JwtEcdsaAlgorithm r1 = com.google.crypto.tink.proto.JwtEcdsaAlgorithm.ES256     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.JwtEcdsaAlgorithm r1 = com.google.crypto.tink.proto.JwtEcdsaAlgorithm.ES384     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.proto.JwtEcdsaAlgorithm r1 = com.google.crypto.tink.proto.JwtEcdsaAlgorithm.ES512     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwtEcdsaVerifyKeyManager.AnonymousClass1.<clinit>():void");
        }
    }

    public static class JwtPublicKeyVerifyFactory extends PrimitiveFactory<JwtPublicKeyVerifyInternal, JwtEcdsaPublicKey> {
        public JwtPublicKeyVerifyFactory() {
            super(JwtPublicKeyVerifyInternal.class);
        }

        public JwtPublicKeyVerifyInternal getPrimitive(JwtEcdsaPublicKey jwtEcdsaPublicKey) throws GeneralSecurityException {
            final Optional optional;
            final EcdsaVerifyJce ecdsaVerifyJce = new EcdsaVerifyJce(EllipticCurves.getEcPublicKey(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaPublicKey.getAlgorithm()), jwtEcdsaPublicKey.getX().toByteArray(), jwtEcdsaPublicKey.getY().toByteArray()), JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(jwtEcdsaPublicKey.getAlgorithm()), EllipticCurves.EcdsaEncoding.IEEE_P1363);
            final String name = jwtEcdsaPublicKey.getAlgorithm().name();
            if (jwtEcdsaPublicKey.hasCustomKid()) {
                optional = Optional.of(jwtEcdsaPublicKey.getCustomKid().getValue());
            } else {
                optional = Optional.empty();
            }
            return new JwtPublicKeyVerifyInternal() {
                public VerifiedJwt verifyAndDecodeWithKid(String str, JwtValidator jwtValidator, Optional<String> optional) throws GeneralSecurityException {
                    JwtFormat.Parts splitSignedCompact = JwtFormat.splitSignedCompact(str);
                    ecdsaVerifyJce.verify(splitSignedCompact.signatureOrMac, splitSignedCompact.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                    JsonObject parseJson = JsonUtil.parseJson(splitSignedCompact.header);
                    JwtFormat.validateHeader(name, optional, optional, parseJson);
                    return jwtValidator.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(parseJson), splitSignedCompact.payload));
                }
            };
        }
    }

    public JwtEcdsaVerifyKeyManager() {
        super(JwtEcdsaPublicKey.class, new JwtPublicKeyVerifyFactory());
    }

    public static final EllipticCurves.CurveType getCurve(JwtEcdsaAlgorithm jwtEcdsaAlgorithm) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[jwtEcdsaAlgorithm.ordinal()];
        if (i3 == 1) {
            return EllipticCurves.CurveType.NIST_P256;
        }
        if (i3 == 2) {
            return EllipticCurves.CurveType.NIST_P384;
        }
        if (i3 == 3) {
            return EllipticCurves.CurveType.NIST_P521;
        }
        throw new GeneralSecurityException("unknown algorithm " + jwtEcdsaAlgorithm.name());
    }

    public static Enums.HashType hashForEcdsaAlgorithm(JwtEcdsaAlgorithm jwtEcdsaAlgorithm) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[jwtEcdsaAlgorithm.ordinal()];
        if (i3 == 1) {
            return Enums.HashType.SHA256;
        }
        if (i3 == 2) {
            return Enums.HashType.SHA384;
        }
        if (i3 == 3) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("unknown algorithm " + jwtEcdsaAlgorithm.name());
    }

    public static final void validateEcdsaAlgorithm(JwtEcdsaAlgorithm jwtEcdsaAlgorithm) throws GeneralSecurityException {
        hashForEcdsaAlgorithm(jwtEcdsaAlgorithm);
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    public JwtEcdsaPublicKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtEcdsaPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(JwtEcdsaPublicKey jwtEcdsaPublicKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtEcdsaPublicKey.getVersion(), getVersion());
        validateEcdsaAlgorithm(jwtEcdsaPublicKey.getAlgorithm());
    }
}
