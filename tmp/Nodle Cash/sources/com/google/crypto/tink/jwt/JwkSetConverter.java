package com.google.crypto.tink.jwt;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Base64;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.errorprone.annotations.InlineMe;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.Optional;
import javax.annotation.Nullable;
import org.web3j.ens.contracts.generated.OffchainResolverContract;

public final class JwkSetConverter {
    private static final String JWT_ECDSA_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    private static final String JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    private static final String JWT_RSA_SSA_PSS_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";

    /* renamed from: com.google.crypto.tink.jwt.JwkSetConverter$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|21|22|23|24|(3:25|26|28)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|21|22|23|24|25|26|28) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0068 */
        static {
            /*
                com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm[] r0 = com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm = r0
                r1 = 1
                com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm r2 = com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm.PS256     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm r3 = com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm.PS384     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm r4 = com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm.PS512     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm[] r3 = com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm = r3
                com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm r4 = com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm.RS256     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r3 = $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm r4 = com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm.RS384     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r3 = $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm     // Catch:{ NoSuchFieldError -> 0x004d }
                com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm r4 = com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm.RS512     // Catch:{ NoSuchFieldError -> 0x004d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                com.google.crypto.tink.proto.JwtEcdsaAlgorithm[] r3 = com.google.crypto.tink.proto.JwtEcdsaAlgorithm.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm = r3
                com.google.crypto.tink.proto.JwtEcdsaAlgorithm r4 = com.google.crypto.tink.proto.JwtEcdsaAlgorithm.ES256     // Catch:{ NoSuchFieldError -> 0x005e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                int[] r1 = $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm     // Catch:{ NoSuchFieldError -> 0x0068 }
                com.google.crypto.tink.proto.JwtEcdsaAlgorithm r3 = com.google.crypto.tink.proto.JwtEcdsaAlgorithm.ES384     // Catch:{ NoSuchFieldError -> 0x0068 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0068 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0068 }
            L_0x0068:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm     // Catch:{ NoSuchFieldError -> 0x0072 }
                com.google.crypto.tink.proto.JwtEcdsaAlgorithm r1 = com.google.crypto.tink.proto.JwtEcdsaAlgorithm.ES512     // Catch:{ NoSuchFieldError -> 0x0072 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0072 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0072 }
            L_0x0072:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.jwt.JwkSetConverter.AnonymousClass1.<clinit>():void");
        }
    }

    private JwkSetConverter() {
    }

    private static JsonObject convertJwtEcdsaKey(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        String str2;
        try {
            JwtEcdsaPublicKey parseFrom = JwtEcdsaPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[parseFrom.getAlgorithm().ordinal()];
            if (i3 == 1) {
                str2 = "ES256";
                str = "P-256";
            } else if (i3 == 2) {
                str2 = "ES384";
                str = "P-384";
            } else if (i3 == 3) {
                str2 = "ES512";
                str = "P-521";
            } else {
                throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kty", "EC");
            jsonObject.addProperty("crv", str);
            jsonObject.addProperty("x", Base64.urlSafeEncode(parseFrom.getX().toByteArray()));
            jsonObject.addProperty("y", Base64.urlSafeEncode(parseFrom.getY().toByteArray()));
            jsonObject.addProperty("use", "sig");
            jsonObject.addProperty("alg", str2);
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(OffchainResolverContract.FUNC_VERIFY);
            jsonObject.add("key_ops", jsonArray);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonObject.addProperty("kid", kid.get());
            } else if (parseFrom.hasCustomKid()) {
                jsonObject.addProperty("kid", parseFrom.getCustomKid().getValue());
            }
            return jsonObject;
        } catch (InvalidProtocolBufferException e3) {
            throw new GeneralSecurityException("failed to parse value as JwtEcdsaPublicKey proto", e3);
        }
    }

    private static JsonObject convertJwtRsaSsaPkcs1(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        try {
            JwtRsaSsaPkcs1PublicKey parseFrom = JwtRsaSsaPkcs1PublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[parseFrom.getAlgorithm().ordinal()];
            if (i3 == 1) {
                str = "RS256";
            } else if (i3 == 2) {
                str = "RS384";
            } else if (i3 == 3) {
                str = "RS512";
            } else {
                throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kty", "RSA");
            jsonObject.addProperty("n", Base64.urlSafeEncode(parseFrom.getN().toByteArray()));
            jsonObject.addProperty("e", Base64.urlSafeEncode(parseFrom.getE().toByteArray()));
            jsonObject.addProperty("use", "sig");
            jsonObject.addProperty("alg", str);
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(OffchainResolverContract.FUNC_VERIFY);
            jsonObject.add("key_ops", jsonArray);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonObject.addProperty("kid", kid.get());
            } else if (parseFrom.hasCustomKid()) {
                jsonObject.addProperty("kid", parseFrom.getCustomKid().getValue());
            }
            return jsonObject;
        } catch (InvalidProtocolBufferException e3) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPkcs1PublicKey proto", e3);
        }
    }

    private static JsonObject convertJwtRsaSsaPss(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        try {
            JwtRsaSsaPssPublicKey parseFrom = JwtRsaSsaPssPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[parseFrom.getAlgorithm().ordinal()];
            if (i3 == 1) {
                str = "PS256";
            } else if (i3 == 2) {
                str = "PS384";
            } else if (i3 == 3) {
                str = "PS512";
            } else {
                throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("kty", "RSA");
            jsonObject.addProperty("n", Base64.urlSafeEncode(parseFrom.getN().toByteArray()));
            jsonObject.addProperty("e", Base64.urlSafeEncode(parseFrom.getE().toByteArray()));
            jsonObject.addProperty("use", "sig");
            jsonObject.addProperty("alg", str);
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(OffchainResolverContract.FUNC_VERIFY);
            jsonObject.add("key_ops", jsonArray);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonObject.addProperty("kid", kid.get());
            } else if (parseFrom.hasCustomKid()) {
                jsonObject.addProperty("kid", parseFrom.getCustomKid().getValue());
            }
            return jsonObject;
        } catch (InvalidProtocolBufferException e3) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPssPublicKey proto", e3);
        }
    }

    private static ProtoKeySerialization convertToEcdsaKey(JsonObject jsonObject) throws GeneralSecurityException {
        JwtEcdsaAlgorithm jwtEcdsaAlgorithm;
        String stringItem = getStringItem(jsonObject, "alg");
        stringItem.getClass();
        char c3 = 65535;
        switch (stringItem.hashCode()) {
            case 66245349:
                if (stringItem.equals("ES256")) {
                    c3 = 0;
                    break;
                }
                break;
            case 66246401:
                if (stringItem.equals("ES384")) {
                    c3 = 1;
                    break;
                }
                break;
            case 66248104:
                if (stringItem.equals("ES512")) {
                    c3 = 2;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                expectStringItem(jsonObject, "crv", "P-256");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES256;
                break;
            case 1:
                expectStringItem(jsonObject, "crv", "P-384");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES384;
                break;
            case 2:
                expectStringItem(jsonObject, "crv", "P-521");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Ecdsa Algorithm: " + getStringItem(jsonObject, "alg"));
        }
        if (!jsonObject.has("d")) {
            expectStringItem(jsonObject, "kty", "EC");
            validateUseIsSig(jsonObject);
            validateKeyOpsIsVerify(jsonObject);
            JwtEcdsaPublicKey.Builder y2 = JwtEcdsaPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtEcdsaAlgorithm).setX(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonObject, "x")))).setY(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonObject, "y"))));
            if (jsonObject.has("kid")) {
                y2.setCustomKid((JwtEcdsaPublicKey.CustomKid) JwtEcdsaPublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonObject, "kid")).build());
            }
            return ProtoKeySerialization.create(JWT_ECDSA_PUBLIC_KEY_URL, ((JwtEcdsaPublicKey) y2.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, (Integer) null);
        }
        throw new UnsupportedOperationException("importing ECDSA private keys is not implemented");
    }

    private static ProtoKeySerialization convertToRsaSsaPkcs1Key(JsonObject jsonObject) throws GeneralSecurityException {
        JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm;
        String stringItem = getStringItem(jsonObject, "alg");
        stringItem.getClass();
        char c3 = 65535;
        switch (stringItem.hashCode()) {
            case 78251122:
                if (stringItem.equals("RS256")) {
                    c3 = 0;
                    break;
                }
                break;
            case 78252174:
                if (stringItem.equals("RS384")) {
                    c3 = 1;
                    break;
                }
                break;
            case 78253877:
                if (stringItem.equals("RS512")) {
                    c3 = 2;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS256;
                break;
            case 1:
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS384;
                break;
            case 2:
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + getStringItem(jsonObject, "alg"));
        }
        if (jsonObject.has(TtmlNode.TAG_P) || jsonObject.has("q") || jsonObject.has("dp") || jsonObject.has("dq") || jsonObject.has("d") || jsonObject.has("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        expectStringItem(jsonObject, "kty", "RSA");
        validateUseIsSig(jsonObject);
        validateKeyOpsIsVerify(jsonObject);
        JwtRsaSsaPkcs1PublicKey.Builder n2 = JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPkcs1Algorithm).setE(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonObject, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonObject, "n"))));
        if (jsonObject.has("kid")) {
            n2.setCustomKid((JwtRsaSsaPkcs1PublicKey.CustomKid) JwtRsaSsaPkcs1PublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonObject, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL, ((JwtRsaSsaPkcs1PublicKey) n2.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, (Integer) null);
    }

    private static ProtoKeySerialization convertToRsaSsaPssKey(JsonObject jsonObject) throws GeneralSecurityException {
        JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm;
        String stringItem = getStringItem(jsonObject, "alg");
        stringItem.getClass();
        char c3 = 65535;
        switch (stringItem.hashCode()) {
            case 76404080:
                if (stringItem.equals("PS256")) {
                    c3 = 0;
                    break;
                }
                break;
            case 76405132:
                if (stringItem.equals("PS384")) {
                    c3 = 1;
                    break;
                }
                break;
            case 76406835:
                if (stringItem.equals("PS512")) {
                    c3 = 2;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS256;
                break;
            case 1:
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS384;
                break;
            case 2:
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + getStringItem(jsonObject, "alg"));
        }
        if (jsonObject.has(TtmlNode.TAG_P) || jsonObject.has("q") || jsonObject.has("dq") || jsonObject.has("dq") || jsonObject.has("d") || jsonObject.has("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        expectStringItem(jsonObject, "kty", "RSA");
        validateUseIsSig(jsonObject);
        validateKeyOpsIsVerify(jsonObject);
        JwtRsaSsaPssPublicKey.Builder n2 = JwtRsaSsaPssPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPssAlgorithm).setE(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonObject, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonObject, "n"))));
        if (jsonObject.has("kid")) {
            n2.setCustomKid((JwtRsaSsaPssPublicKey.CustomKid) JwtRsaSsaPssPublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonObject, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_RSA_SSA_PSS_PUBLIC_KEY_URL, ((JwtRsaSsaPssPublicKey) n2.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, (Integer) null);
    }

    private static void expectStringItem(JsonObject jsonObject, String str, String str2) throws GeneralSecurityException {
        String stringItem = getStringItem(jsonObject, str);
        if (!stringItem.equals(str2)) {
            throw new GeneralSecurityException(C0118y.f("unexpected ", str, " value: ", stringItem));
        }
    }

    @InlineMe(imports = {"com.google.crypto.tink.jwt.JwkSetConverter"}, replacement = "JwkSetConverter.fromPublicKeysetHandle(handle)")
    @Deprecated
    public static String fromKeysetHandle(KeysetHandle keysetHandle, KeyAccess keyAccess) throws IOException, GeneralSecurityException {
        return fromPublicKeysetHandle(keysetHandle);
    }

    public static String fromPublicKeysetHandle(KeysetHandle keysetHandle) throws IOException, GeneralSecurityException {
        JsonArray jsonArray = new JsonArray();
        for (int i3 = 0; i3 < keysetHandle.size(); i3++) {
            KeysetHandle.Entry at = keysetHandle.getAt(i3);
            if (at.getStatus() == KeyStatus.ENABLED) {
                Key key = at.getKey();
                if (key instanceof LegacyProtoKey) {
                    ProtoKeySerialization serialization = ((LegacyProtoKey) key).getSerialization((SecretKeyAccess) null);
                    if (serialization.getOutputPrefixType() != OutputPrefixType.RAW && serialization.getOutputPrefixType() != OutputPrefixType.TINK) {
                        throw new GeneralSecurityException("only OutputPrefixType RAW and TINK are supported");
                    } else if (serialization.getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC) {
                        String typeUrl = serialization.getTypeUrl();
                        typeUrl.getClass();
                        char c3 = 65535;
                        switch (typeUrl.hashCode()) {
                            case -1204668709:
                                if (typeUrl.equals(JWT_ECDSA_PUBLIC_KEY_URL)) {
                                    c3 = 0;
                                    break;
                                }
                                break;
                            case 516334794:
                                if (typeUrl.equals(JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL)) {
                                    c3 = 1;
                                    break;
                                }
                                break;
                            case 1174255008:
                                if (typeUrl.equals(JWT_RSA_SSA_PSS_PUBLIC_KEY_URL)) {
                                    c3 = 2;
                                    break;
                                }
                                break;
                        }
                        switch (c3) {
                            case 0:
                                jsonArray.add((JsonElement) convertJwtEcdsaKey(serialization));
                                break;
                            case 1:
                                jsonArray.add((JsonElement) convertJwtRsaSsaPkcs1(serialization));
                                break;
                            case 2:
                                jsonArray.add((JsonElement) convertJwtRsaSsaPss(serialization));
                                break;
                            default:
                                throw new GeneralSecurityException(a.l("key type ", serialization.getTypeUrl(), " is not supported"));
                        }
                    } else {
                        throw new GeneralSecurityException("only public keys can be converted");
                    }
                } else {
                    throw new GeneralSecurityException("only LegacyProtoKey is currently supported");
                }
            }
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(UserMetadata.KEYDATA_FILENAME, jsonArray);
        return jsonObject.toString();
    }

    private static Optional<String> getKid(@Nullable Integer num) {
        return num == null ? Optional.empty() : Optional.of(Base64.urlSafeEncode(ByteBuffer.allocate(4).putInt(num.intValue()).array()));
    }

    private static String getStringItem(JsonObject jsonObject, String str) throws GeneralSecurityException {
        if (!jsonObject.has(str)) {
            throw new GeneralSecurityException(android.support.v4.media.session.a.m(str, " not found"));
        } else if (jsonObject.get(str).isJsonPrimitive() && jsonObject.get(str).getAsJsonPrimitive().isString()) {
            return jsonObject.get(str).getAsString();
        } else {
            throw new GeneralSecurityException(android.support.v4.media.session.a.m(str, " is not a string"));
        }
    }

    @InlineMe(imports = {"com.google.crypto.tink.jwt.JwkSetConverter"}, replacement = "JwkSetConverter.toPublicKeysetHandle(jwkSet)")
    @Deprecated
    public static KeysetHandle toKeysetHandle(String str, KeyAccess keyAccess) throws IOException, GeneralSecurityException {
        return toPublicKeysetHandle(str);
    }

    public static KeysetHandle toPublicKeysetHandle(String str) throws IOException, GeneralSecurityException {
        ProtoKeySerialization protoKeySerialization;
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            jsonReader.setLenient(false);
            JsonObject asJsonObject = Streams.parse(jsonReader).getAsJsonObject();
            KeysetHandle.Builder newBuilder = KeysetHandle.newBuilder();
            Iterator<JsonElement> it = asJsonObject.get(UserMetadata.KEYDATA_FILENAME).getAsJsonArray().iterator();
            while (it.hasNext()) {
                JsonObject asJsonObject2 = it.next().getAsJsonObject();
                String substring = getStringItem(asJsonObject2, "alg").substring(0, 2);
                substring.getClass();
                char c3 = 65535;
                switch (substring.hashCode()) {
                    case 2222:
                        if (substring.equals("ES")) {
                            c3 = 0;
                            break;
                        }
                        break;
                    case 2563:
                        if (substring.equals("PS")) {
                            c3 = 1;
                            break;
                        }
                        break;
                    case 2625:
                        if (substring.equals("RS")) {
                            c3 = 2;
                            break;
                        }
                        break;
                }
                switch (c3) {
                    case 0:
                        protoKeySerialization = convertToEcdsaKey(asJsonObject2);
                        break;
                    case 1:
                        protoKeySerialization = convertToRsaSsaPssKey(asJsonObject2);
                        break;
                    case 2:
                        protoKeySerialization = convertToRsaSsaPkcs1Key(asJsonObject2);
                        break;
                    default:
                        throw new GeneralSecurityException("unexpected alg value: " + getStringItem(asJsonObject2, "alg"));
                }
                newBuilder.addEntry(KeysetHandle.importKey(new LegacyProtoKey(protoKeySerialization, (SecretKeyAccess) null)).withRandomId());
            }
            if (newBuilder.size() > 0) {
                newBuilder.getAt(0).makePrimary();
                return newBuilder.build();
            }
            throw new GeneralSecurityException("empty keyset");
        } catch (JsonParseException | IllegalStateException | StackOverflowError e3) {
            throw new GeneralSecurityException("JWK set is invalid JSON", e3);
        }
    }

    private static void validateKeyOpsIsVerify(JsonObject jsonObject) throws GeneralSecurityException {
        if (jsonObject.has("key_ops")) {
            if (jsonObject.get("key_ops").isJsonArray()) {
                JsonArray asJsonArray = jsonObject.get("key_ops").getAsJsonArray();
                if (asJsonArray.size() != 1) {
                    throw new GeneralSecurityException("key_ops must contain exactly one element");
                } else if (!asJsonArray.get(0).isJsonPrimitive() || !asJsonArray.get(0).getAsJsonPrimitive().isString()) {
                    throw new GeneralSecurityException("key_ops is not a string");
                } else if (!asJsonArray.get(0).getAsString().equals(OffchainResolverContract.FUNC_VERIFY)) {
                    throw new GeneralSecurityException("unexpected keyOps value: " + asJsonArray.get(0).getAsString());
                }
            } else {
                throw new GeneralSecurityException("key_ops is not an array");
            }
        }
    }

    private static void validateUseIsSig(JsonObject jsonObject) throws GeneralSecurityException {
        if (jsonObject.has("use")) {
            expectStringItem(jsonObject, "use", "sig");
        }
    }
}
