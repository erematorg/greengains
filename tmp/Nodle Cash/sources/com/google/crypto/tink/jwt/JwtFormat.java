package com.google.crypto.tink.jwt;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Base64;
import com.google.gson.JsonObject;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

final class JwtFormat {

    public static class Parts {
        String header;
        String payload;
        byte[] signatureOrMac;
        String unsignedCompact;

        public Parts(String str, byte[] bArr, String str2, String str3) {
            this.unsignedCompact = str;
            this.signatureOrMac = bArr;
            this.header = str2;
            this.payload = str3;
        }
    }

    private JwtFormat() {
    }

    public static String createHeader(String str, Optional<String> optional, Optional<String> optional2) throws InvalidAlgorithmParameterException {
        validateAlgorithm(str);
        JsonObject jsonObject = new JsonObject();
        if (optional2.isPresent()) {
            jsonObject.addProperty("kid", optional2.get());
        }
        jsonObject.addProperty("alg", str);
        if (optional.isPresent()) {
            jsonObject.addProperty(ClientData.KEY_TYPE, optional.get());
        }
        return Base64.urlSafeEncode(jsonObject.toString().getBytes(Util.UTF_8));
    }

    public static String createSignedCompact(String str, byte[] bArr) {
        StringBuilder q2 = a.q(str, JwtUtilsKt.JWT_DELIMITER);
        q2.append(encodeSignature(bArr));
        return q2.toString();
    }

    public static String createUnsignedCompact(String str, Optional<String> optional, RawJwt rawJwt) throws InvalidAlgorithmParameterException, JwtInvalidException {
        String jsonPayload = rawJwt.getJsonPayload();
        Optional of = rawJwt.hasTypeHeader() ? Optional.of(rawJwt.getTypeHeader()) : Optional.empty();
        return createHeader(str, of, optional) + JwtUtilsKt.JWT_DELIMITER + encodePayload(jsonPayload);
    }

    public static String decodeHeader(String str) throws JwtInvalidException {
        byte[] strictUrlSafeDecode = strictUrlSafeDecode(str);
        validateUtf8(strictUrlSafeDecode);
        return new String(strictUrlSafeDecode, Util.UTF_8);
    }

    public static String decodePayload(String str) throws JwtInvalidException {
        byte[] strictUrlSafeDecode = strictUrlSafeDecode(str);
        validateUtf8(strictUrlSafeDecode);
        return new String(strictUrlSafeDecode, Util.UTF_8);
    }

    public static byte[] decodeSignature(String str) throws JwtInvalidException {
        return strictUrlSafeDecode(str);
    }

    public static String encodePayload(String str) {
        return Base64.urlSafeEncode(str.getBytes(Util.UTF_8));
    }

    public static String encodeSignature(byte[] bArr) {
        return Base64.urlSafeEncode(bArr);
    }

    public static Optional<Integer> getKeyId(String str) {
        byte[] urlSafeDecode = Base64.urlSafeDecode(str);
        return urlSafeDecode.length != 4 ? Optional.empty() : Optional.of(Integer.valueOf(ByteBuffer.wrap(urlSafeDecode).getInt()));
    }

    public static Optional<String> getKid(int i3, OutputPrefixType outputPrefixType) throws JwtInvalidException {
        if (outputPrefixType == OutputPrefixType.RAW) {
            return Optional.empty();
        }
        if (outputPrefixType == OutputPrefixType.TINK) {
            return Optional.of(Base64.urlSafeEncode(ByteBuffer.allocate(4).putInt(i3).array()));
        }
        throw new JwtInvalidException("unsupported output prefix type");
    }

    private static String getStringHeader(JsonObject jsonObject, String str) throws JwtInvalidException {
        if (!jsonObject.has(str)) {
            throw new JwtInvalidException(a.l("header ", str, " does not exist"));
        } else if (jsonObject.get(str).isJsonPrimitive() && jsonObject.get(str).getAsJsonPrimitive().isString()) {
            return jsonObject.get(str).getAsString();
        } else {
            throw new JwtInvalidException(a.l("header ", str, " is not a string"));
        }
    }

    public static Optional<String> getTypeHeader(JsonObject jsonObject) throws JwtInvalidException {
        return jsonObject.has(ClientData.KEY_TYPE) ? Optional.of(getStringHeader(jsonObject, ClientData.KEY_TYPE)) : Optional.empty();
    }

    public static boolean isValidUrlsafeBase64Char(char c3) {
        return (c3 >= 'a' && c3 <= 'z') || (c3 >= 'A' && c3 <= 'Z') || ((c3 >= '0' && c3 <= '9') || c3 == '-' || c3 == '_');
    }

    public static Parts splitSignedCompact(String str) throws JwtInvalidException {
        validateASCII(str);
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String substring = str.substring(0, lastIndexOf);
            byte[] decodeSignature = decodeSignature(str.substring(lastIndexOf + 1));
            int indexOf = substring.indexOf(46);
            if (indexOf >= 0) {
                String substring2 = substring.substring(0, indexOf);
                String substring3 = substring.substring(indexOf + 1);
                if (substring3.indexOf(46) <= 0) {
                    return new Parts(substring, decodeSignature, decodeHeader(substring2), decodePayload(substring3));
                }
                throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
            }
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
    }

    public static byte[] strictUrlSafeDecode(String str) throws JwtInvalidException {
        int i3 = 0;
        while (i3 < str.length()) {
            if (isValidUrlsafeBase64Char(str.charAt(i3))) {
                i3++;
            } else {
                throw new JwtInvalidException("invalid encoding");
            }
        }
        try {
            return Base64.urlSafeDecode(str);
        } catch (IllegalArgumentException e3) {
            throw new JwtInvalidException("invalid encoding: " + e3);
        }
    }

    public static void validateASCII(String str) throws JwtInvalidException {
        int i3 = 0;
        while (i3 < str.length()) {
            if ((str.charAt(i3) & 128) <= 0) {
                i3++;
            } else {
                throw new JwtInvalidException("Non ascii character");
            }
        }
    }

    private static void validateAlgorithm(String str) throws InvalidAlgorithmParameterException {
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case 66245349:
                if (str.equals("ES256")) {
                    c3 = 0;
                    break;
                }
                break;
            case 66246401:
                if (str.equals("ES384")) {
                    c3 = 1;
                    break;
                }
                break;
            case 66248104:
                if (str.equals("ES512")) {
                    c3 = 2;
                    break;
                }
                break;
            case 69015912:
                if (str.equals("HS256")) {
                    c3 = 3;
                    break;
                }
                break;
            case 69016964:
                if (str.equals("HS384")) {
                    c3 = 4;
                    break;
                }
                break;
            case 69018667:
                if (str.equals("HS512")) {
                    c3 = 5;
                    break;
                }
                break;
            case 76404080:
                if (str.equals("PS256")) {
                    c3 = 6;
                    break;
                }
                break;
            case 76405132:
                if (str.equals("PS384")) {
                    c3 = 7;
                    break;
                }
                break;
            case 76406835:
                if (str.equals("PS512")) {
                    c3 = 8;
                    break;
                }
                break;
            case 78251122:
                if (str.equals("RS256")) {
                    c3 = 9;
                    break;
                }
                break;
            case 78252174:
                if (str.equals("RS384")) {
                    c3 = 10;
                    break;
                }
                break;
            case 78253877:
                if (str.equals("RS512")) {
                    c3 = 11;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return;
            default:
                throw new InvalidAlgorithmParameterException("invalid algorithm: ".concat(str));
        }
    }

    public static void validateHeader(String str, Optional<String> optional, Optional<String> optional2, JsonObject jsonObject) throws InvalidAlgorithmParameterException, JwtInvalidException {
        validateAlgorithm(str);
        String stringHeader = getStringHeader(jsonObject, "alg");
        if (!stringHeader.equals(str)) {
            throw new InvalidAlgorithmParameterException(C0118y.f("invalid algorithm; expected ", str, ", got ", stringHeader));
        } else if (jsonObject.has("crit")) {
            throw new JwtInvalidException("all tokens with crit headers are rejected");
        } else if (!optional.isPresent() || !optional2.isPresent()) {
            boolean has = jsonObject.has("kid");
            if (optional.isPresent()) {
                if (has) {
                    validateKidInHeader(optional.get(), jsonObject);
                } else {
                    throw new JwtInvalidException("missing kid in header");
                }
            }
            if (optional2.isPresent() && has) {
                validateKidInHeader(optional2.get(), jsonObject);
            }
        } else {
            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
        }
    }

    private static void validateKidInHeader(String str, JsonObject jsonObject) throws JwtInvalidException {
        if (!getStringHeader(jsonObject, "kid").equals(str)) {
            throw new JwtInvalidException("invalid kid in header");
        }
    }

    public static void validateUtf8(byte[] bArr) throws JwtInvalidException {
        try {
            Util.UTF_8.newDecoder().decode(ByteBuffer.wrap(bArr));
        } catch (CharacterCodingException e3) {
            throw new JwtInvalidException(e3.getMessage());
        }
    }
}
