package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.jwt.JwtFormat;
import com.google.crypto.tink.proto.JwtHmacAlgorithm;
import com.google.crypto.tink.proto.JwtHmacKey;
import com.google.crypto.tink.proto.JwtHmacKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.errorprone.annotations.Immutable;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.crypto.spec.SecretKeySpec;

public final class JwtHmacKeyManager extends KeyTypeManager<JwtHmacKey> {

    /* renamed from: com.google.crypto.tink.jwt.JwtHmacKeyManager$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.crypto.tink.proto.JwtHmacAlgorithm[] r0 = com.google.crypto.tink.proto.JwtHmacAlgorithm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm = r0
                com.google.crypto.tink.proto.JwtHmacAlgorithm r1 = com.google.crypto.tink.proto.JwtHmacAlgorithm.HS256     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.JwtHmacAlgorithm r1 = com.google.crypto.tink.proto.JwtHmacAlgorithm.HS384     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.proto.JwtHmacAlgorithm r1 = com.google.crypto.tink.proto.JwtHmacAlgorithm.HS512     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwtHmacKeyManager.AnonymousClass3.<clinit>():void");
        }
    }

    @Immutable
    public static final class JwtHmac implements JwtMacInternal {
        private final String algorithm;
        private final Optional<String> customKidFromHmacKey;
        private final PrfMac prfMac;

        public JwtHmac(String str, Optional<String> optional, PrfMac prfMac2) {
            this.algorithm = str;
            this.customKidFromHmacKey = optional;
            this.prfMac = prfMac2;
        }

        public String computeMacAndEncodeWithKid(RawJwt rawJwt, Optional<String> optional) throws GeneralSecurityException {
            if (this.customKidFromHmacKey.isPresent()) {
                if (!optional.isPresent()) {
                    optional = this.customKidFromHmacKey;
                } else {
                    throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                }
            }
            String createUnsignedCompact = JwtFormat.createUnsignedCompact(this.algorithm, optional, rawJwt);
            return JwtFormat.createSignedCompact(createUnsignedCompact, this.prfMac.computeMac(createUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
        }

        public VerifiedJwt verifyMacAndDecodeWithKid(String str, JwtValidator jwtValidator, Optional<String> optional) throws GeneralSecurityException {
            JwtFormat.Parts splitSignedCompact = JwtFormat.splitSignedCompact(str);
            this.prfMac.verifyMac(splitSignedCompact.signatureOrMac, splitSignedCompact.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
            JsonObject parseJson = JsonUtil.parseJson(splitSignedCompact.header);
            JwtFormat.validateHeader(this.algorithm, optional, this.customKidFromHmacKey, parseJson);
            return jwtValidator.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(parseJson), splitSignedCompact.payload));
        }
    }

    public JwtHmacKeyManager() {
        super(JwtHmacKey.class, new PrimitiveFactory<JwtMacInternal, JwtHmacKey>(JwtMacInternal.class) {
            public JwtMacInternal getPrimitive(JwtHmacKey jwtHmacKey) throws GeneralSecurityException {
                JwtHmacAlgorithm algorithm = jwtHmacKey.getAlgorithm();
                PrfHmacJce prfHmacJce = new PrfHmacJce(JwtHmacKeyManager.getHmacAlgorithm(algorithm), new SecretKeySpec(jwtHmacKey.getKeyValue().toByteArray(), "HMAC"));
                return new JwtHmac(JwtHmacKeyManager.getAlgorithmName(algorithm), jwtHmacKey.hasCustomKid() ? Optional.of(jwtHmacKey.getCustomKid().getValue()) : Optional.empty(), new PrfMac(prfHmacJce, prfHmacJce.getMaxOutputLength()));
            }
        });
    }

    /* access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtHmacKeyFormat> createKeyFormat(JwtHmacAlgorithm jwtHmacAlgorithm, int i3, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>((JwtHmacKeyFormat) JwtHmacKeyFormat.newBuilder().setAlgorithm(jwtHmacAlgorithm).setKeySize(i3).build(), outputPrefixType);
    }

    private static KeyTemplate createTemplate(JwtHmacAlgorithm jwtHmacAlgorithm, int i3) {
        return KeyTemplate.create(new JwtHmacKeyManager().getKeyType(), ((JwtHmacKeyFormat) JwtHmacKeyFormat.newBuilder().setAlgorithm(jwtHmacAlgorithm).setKeySize(i3).build()).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    /* access modifiers changed from: private */
    public static final String getAlgorithmName(JwtHmacAlgorithm jwtHmacAlgorithm) throws GeneralSecurityException {
        int i3 = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[jwtHmacAlgorithm.ordinal()];
        if (i3 == 1) {
            return "HS256";
        }
        if (i3 == 2) {
            return "HS384";
        }
        if (i3 == 3) {
            return "HS512";
        }
        throw new GeneralSecurityException("unknown algorithm");
    }

    /* access modifiers changed from: private */
    public static final String getHmacAlgorithm(JwtHmacAlgorithm jwtHmacAlgorithm) throws GeneralSecurityException {
        int i3 = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[jwtHmacAlgorithm.ordinal()];
        if (i3 == 1) {
            return "HMACSHA256";
        }
        if (i3 == 2) {
            return "HMACSHA384";
        }
        if (i3 == 3) {
            return "HMACSHA512";
        }
        throw new GeneralSecurityException("unknown algorithm");
    }

    /* access modifiers changed from: private */
    public static final int getMinimumKeySizeInBytes(JwtHmacAlgorithm jwtHmacAlgorithm) throws GeneralSecurityException {
        int i3 = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[jwtHmacAlgorithm.ordinal()];
        if (i3 == 1) {
            return 32;
        }
        if (i3 == 2) {
            return 48;
        }
        if (i3 == 3) {
            return 64;
        }
        throw new GeneralSecurityException("unknown algorithm");
    }

    public static final KeyTemplate hs256Template() {
        return createTemplate(JwtHmacAlgorithm.HS256, 32);
    }

    public static final KeyTemplate hs384Template() {
        return createTemplate(JwtHmacAlgorithm.HS384, 48);
    }

    public static final KeyTemplate hs512Template() {
        return createTemplate(JwtHmacAlgorithm.HS512, 64);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new JwtHmacKeyManager(), z2);
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtHmacKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<JwtHmacKeyFormat, JwtHmacKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtHmacKeyFormat, JwtHmacKey>(JwtHmacKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtHmacKeyFormat>> keyFormats() {
                HashMap hashMap = new HashMap();
                JwtHmacAlgorithm jwtHmacAlgorithm = JwtHmacAlgorithm.HS256;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                hashMap.put("JWT_HS256_RAW", JwtHmacKeyManager.createKeyFormat(jwtHmacAlgorithm, 32, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.TINK;
                hashMap.put("JWT_HS256", JwtHmacKeyManager.createKeyFormat(jwtHmacAlgorithm, 32, outputPrefixType2));
                JwtHmacAlgorithm jwtHmacAlgorithm2 = JwtHmacAlgorithm.HS384;
                hashMap.put("JWT_HS384_RAW", JwtHmacKeyManager.createKeyFormat(jwtHmacAlgorithm2, 48, outputPrefixType));
                hashMap.put("JWT_HS384", JwtHmacKeyManager.createKeyFormat(jwtHmacAlgorithm2, 48, outputPrefixType2));
                JwtHmacAlgorithm jwtHmacAlgorithm3 = JwtHmacAlgorithm.HS512;
                hashMap.put("JWT_HS512_RAW", JwtHmacKeyManager.createKeyFormat(jwtHmacAlgorithm3, 64, outputPrefixType));
                hashMap.put("JWT_HS512", JwtHmacKeyManager.createKeyFormat(jwtHmacAlgorithm3, 64, outputPrefixType2));
                return Collections.unmodifiableMap(hashMap);
            }

            public JwtHmacKey createKey(JwtHmacKeyFormat jwtHmacKeyFormat) {
                return (JwtHmacKey) JwtHmacKey.newBuilder().setVersion(JwtHmacKeyManager.this.getVersion()).setAlgorithm(jwtHmacKeyFormat.getAlgorithm()).setKeyValue(ByteString.copyFrom(Random.randBytes(jwtHmacKeyFormat.getKeySize()))).build();
            }

            public JwtHmacKey deriveKey(JwtHmacKeyFormat jwtHmacKeyFormat, InputStream inputStream) throws GeneralSecurityException {
                throw new UnsupportedOperationException();
            }

            public JwtHmacKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return JwtHmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(JwtHmacKeyFormat jwtHmacKeyFormat) throws GeneralSecurityException {
                if (jwtHmacKeyFormat.getKeySize() < JwtHmacKeyManager.getMinimumKeySizeInBytes(jwtHmacKeyFormat.getAlgorithm())) {
                    throw new GeneralSecurityException("key too short");
                }
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public JwtHmacKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return JwtHmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(JwtHmacKey jwtHmacKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtHmacKey.getVersion(), getVersion());
        if (jwtHmacKey.getKeyValue().size() < getMinimumKeySizeInBytes(jwtHmacKey.getAlgorithm())) {
            throw new GeneralSecurityException("key too short");
        }
    }
}
