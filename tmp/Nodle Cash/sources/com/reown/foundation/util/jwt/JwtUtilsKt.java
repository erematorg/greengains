package com.reown.foundation.util.jwt;

import androidx.browser.trusted.c;
import com.reown.foundation.common.model.PrivateKey;
import com.reown.foundation.common.model.PublicKey;
import com.reown.util.UtilFunctionsKt;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
import io.ipfs.multibase.Base58;
import io.ipfs.multibase.Multibase;
import io.ipfs.multibase.binary.Base64;
import java.lang.reflect.Type;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0019\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\t2\u0006\u0010\n\u001a\u0002H\t¢\u0006\u0002\u0010\u000b\u001a\u000e\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0006\u001a\u000e\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0006\u001a\u000e\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0006\u001a\u000e\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0006\u001a\u0013\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0001¢\u0006\u0002\u0010\u0016\u001a\u0013\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0001¢\u0006\u0002\u0010\u0016\u001a4\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001a\u001a\u000e\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u0001\u001a\u000e\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u0001\u001a\u000e\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u0001\u001a\u000e\u0010&\u001a\u00020\u00012\u0006\u0010'\u001a\u00020\u0001\u001a:\u0010(\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u00020\u00010*0)\"\n\b\u0000\u0010,\u0018\u0001*\u00020\u00042\u0006\u0010-\u001a\u00020\u0001H\b¢\u0006\u0002\u0010.\u001a\u000e\u0010/\u001a\u00020\u00012\u0006\u0010-\u001a\u00020\u0001\u001a#\u00100\u001a\b\u0012\u0004\u0012\u00020\u00060)2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0006¢\u0006\u0004\b4\u00105\u001a+\u00106\u001a\b\u0012\u0004\u0012\u0002070)2\u0006\u0010\u0011\u001a\u00020\u00142\u0006\u00103\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0001¢\u0006\u0004\b8\u00109\"\u000e\u0010:\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010;\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010<\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010=\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010>\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010?\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010@\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010A\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"encodeJWT", "", "encodedHeader", "claims", "Lcom/reown/foundation/util/jwt/JwtClaims;", "signature", "", "encodeData", "encodeJSON", "T", "jsonObj", "(Ljava/lang/Object;)Ljava/lang/String;", "encodeBase64", "bytes", "decodeBase64", "value", "encodeEd25519DidKey", "publicKey", "encodeX25519DidKey", "decodeEd25519DidKey", "Lcom/reown/foundation/common/model/PublicKey;", "didKey", "(Ljava/lang/String;)Ljava/lang/String;", "decodeX25519DidKey", "jwtIatAndExp", "Lkotlin/Pair;", "", "timeunit", "Ljava/util/concurrent/TimeUnit;", "expirySourceDuration", "expiryTimeUnit", "timestampInMs", "encodeDidPkh", "caip10Account", "decodeDidPkh", "didPkh", "encodeDidWeb", "appDomain", "decodeDidWeb", "didWeb", "decodeJwt", "Lkotlin/Result;", "Lkotlin/Triple;", "Lcom/reown/foundation/util/jwt/JwtHeader;", "C", "jwt", "(Ljava/lang/String;)Ljava/lang/Object;", "extractData", "signJwt", "privateKey", "Lcom/reown/foundation/common/model/PrivateKey;", "data", "signJwt-FH9GnLg", "(Ljava/lang/String;[B)Ljava/lang/Object;", "verifySignature", "", "verifySignature-dKHZaD0", "(Ljava/lang/String;[BLjava/lang/String;)Ljava/lang/Object;", "JWT_DELIMITER", "DID_DELIMITER", "DID_PREFIX", "DID_METHOD_KEY", "DID_METHOD_PKH", "DID_METHOD_WEB", "MULTICODEC_ED25519_HEADER", "MULTICODEC_X25519_HEADER", "foundation"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJwtUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JwtUtils.kt\ncom/reown/foundation/util/jwt/JwtUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,149:1\n1#2:150\n*E\n"})
public final /* synthetic */ class JwtUtilsKt {
    @NotNull
    public static final String DID_DELIMITER = ":";
    @NotNull
    public static final String DID_METHOD_KEY = "key";
    @NotNull
    public static final String DID_METHOD_PKH = "pkh";
    @NotNull
    public static final String DID_METHOD_WEB = "web";
    @NotNull
    public static final String DID_PREFIX = "did";
    @NotNull
    public static final String JWT_DELIMITER = ".";
    @NotNull
    public static final String MULTICODEC_ED25519_HEADER = "K36";
    @NotNull
    public static final String MULTICODEC_X25519_HEADER = "Jxg";

    @NotNull
    public static final String decodeBase64(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
        byte[] decodeBase64 = Base64.decodeBase64(bArr);
        Intrinsics.checkNotNullExpressionValue(decodeBase64, "decodeBase64(...)");
        String str = new String(decodeBase64, Charsets.ISO_8859_1);
        Charset charset = Charsets.UTF_8;
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return new String(bytes, charset);
    }

    @NotNull
    public static final String decodeDidPkh(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "didPkh");
        return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.takeLast(StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null), 3), ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final String decodeDidWeb(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "didWeb");
        return StringsKt__StringsKt.removePrefix(str, (CharSequence) CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf(DID_PREFIX, "web"), ":", (CharSequence) null, ":", 0, (CharSequence) null, (Function1) null, 58, (Object) null));
    }

    @NotNull
    public static final String decodeEd25519DidKey(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "didKey");
        byte[] decode = Multibase.decode((String) CollectionsKt.last(StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null)));
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        String bytesToHex = UtilFunctionsKt.bytesToHex(decode);
        if (StringsKt__StringsJVMKt.startsWith$default(bytesToHex, "ed01", false, 2, (Object) null)) {
            return PublicKey.m8856constructorimpl(StringsKt__StringsKt.removePrefix(bytesToHex, (CharSequence) "ed01"));
        }
        throw new Throwable(c.a("Invalid key: ", bytesToHex));
    }

    public static final /* synthetic */ <C extends JwtClaims> Object decodeJwt(String str) {
        Intrinsics.checkNotNullParameter(str, "jwt");
        try {
            Result.Companion companion = Result.Companion;
            List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{JWT_DELIMITER}, false, 0, 6, (Object) null);
            if (Z2.size() == 3) {
                List list = Z2;
                String str2 = (String) list.get(0);
                String str3 = (String) list.get(1);
                Charset charset = Charsets.UTF_8;
                byte[] bytes = str3.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                String decodeBase64 = decodeBase64(bytes);
                byte[] bytes2 = str2.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
                String decodeBase642 = decodeBase64(bytes2);
                byte[] bytes3 = ((String) list.get(2)).getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes3, "getBytes(...)");
                String decodeBase643 = decodeBase64(bytes3);
                Moshi build = new Moshi.Builder().addLast((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
                Intrinsics.reifiedOperationMarker(4, "C");
                JwtClaims fromJson = build.adapter(JwtClaims.class).fromJson(decodeBase64);
                if (fromJson != null) {
                    Intrinsics.checkNotNull(fromJson);
                    JwtHeader fromJson2 = build.adapter(JwtHeader.class).fromJson(decodeBase642);
                    if (fromJson2 != null) {
                        return Result.m8979constructorimpl(new Triple(fromJson2, fromJson, decodeBase643));
                    }
                    throw new Throwable("Invalid header: ".concat(str2));
                }
                throw new Throwable("Invalid claims: ".concat(str3));
            }
            throw new Throwable("Unable to split jwt: " + str);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            return Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
    }

    @NotNull
    public static final String decodeX25519DidKey(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "didKey");
        byte[] decode = Multibase.decode((String) CollectionsKt.last(StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null)));
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        String bytesToHex = UtilFunctionsKt.bytesToHex(decode);
        if (StringsKt__StringsJVMKt.startsWith$default(bytesToHex, "ec01", false, 2, (Object) null)) {
            return PublicKey.m8856constructorimpl(StringsKt__StringsKt.removePrefix(bytesToHex, (CharSequence) "ec01"));
        }
        throw new Throwable(c.a("Invalid key: ", bytesToHex));
    }

    @NotNull
    public static final String encodeBase64(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "bytes");
        byte[] encodeBase64URLSafe = Base64.encodeBase64URLSafe(bArr);
        Intrinsics.checkNotNullExpressionValue(encodeBase64URLSafe, "encodeBase64URLSafe(...)");
        return new String(encodeBase64URLSafe, Charsets.UTF_8);
    }

    @NotNull
    public static final String encodeData(@NotNull String str, @NotNull JwtClaims jwtClaims) {
        Intrinsics.checkNotNullParameter(str, "encodedHeader");
        Intrinsics.checkNotNullParameter(jwtClaims, "claims");
        return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf(str, encodeJSON(jwtClaims)), JWT_DELIMITER, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final String encodeDidPkh(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "caip10Account");
        return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf(DID_PREFIX, DID_METHOD_PKH, str), ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final String encodeDidWeb(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "appDomain");
        return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf(DID_PREFIX, "web", new URI(str).getHost()), ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final String encodeEd25519DidKey(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "publicKey");
        byte[] decode = Base58.decode(MULTICODEC_ED25519_HEADER);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf(DID_PREFIX, DID_METHOD_KEY, Multibase.encode(Multibase.Base.Base58BTC, ArraysKt.plus(decode, bArr))), ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final <T> String encodeJSON(T t2) {
        Moshi build = new Moshi.Builder().addLast((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
        Intrinsics.checkNotNull(t2);
        String json = build.adapter((Type) t2.getClass()).toJson(t2);
        Intrinsics.checkNotNull(json);
        byte[] bytes = json.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return encodeBase64(bytes);
    }

    @NotNull
    public static final String encodeJWT(@NotNull String str, @NotNull JwtClaims jwtClaims, @NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, "encodedHeader");
        Intrinsics.checkNotNullParameter(jwtClaims, "claims");
        Intrinsics.checkNotNullParameter(bArr, "signature");
        return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf(str, encodeJSON(jwtClaims), encodeBase64(bArr)), JWT_DELIMITER, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final String encodeX25519DidKey(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "publicKey");
        byte[] decode = Base58.decode(MULTICODEC_X25519_HEADER);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf(DID_PREFIX, DID_METHOD_KEY, Multibase.encode(Multibase.Base.Base58BTC, ArraysKt.plus(decode, bArr))), ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final String extractData(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "jwt");
        List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{JWT_DELIMITER}, false, 0, 6, (Object) null);
        if (Z2.size() == 3) {
            return CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt.listOf((String) Z2.get(0), (String) Z2.get(1)), JWT_DELIMITER, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        throw new Throwable(c.a("Unable to split jwt: ", str));
    }

    @NotNull
    public static final Pair<Long, Long> jwtIatAndExp(@NotNull TimeUnit timeUnit, long j2, @NotNull TimeUnit timeUnit2, long j3) {
        Intrinsics.checkNotNullParameter(timeUnit, "timeunit");
        Intrinsics.checkNotNullParameter(timeUnit2, "expiryTimeUnit");
        long convert = timeUnit.convert(j3, TimeUnit.MILLISECONDS);
        return TuplesKt.to(Long.valueOf(convert), Long.valueOf(timeUnit.convert(j2, timeUnit2) + convert));
    }

    public static /* synthetic */ Pair jwtIatAndExp$default(TimeUnit timeUnit, long j2, TimeUnit timeUnit2, long j3, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            j3 = System.currentTimeMillis();
        }
        return jwtIatAndExp(timeUnit, j2, timeUnit2, j3);
    }

    @NotNull
    /* renamed from: signJwt-FH9GnLg  reason: not valid java name */
    public static final Object m8865signJwtFH9GnLg(@NotNull String str, @NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, "privateKey");
        Intrinsics.checkNotNullParameter(bArr, "data");
        try {
            Result.Companion companion = Result.Companion;
            Ed25519PrivateKeyParameters ed25519PrivateKeyParameters = new Ed25519PrivateKeyParameters(PrivateKey.m8851getKeyAsBytesimpl(str));
            Ed25519Signer ed25519Signer = new Ed25519Signer();
            ed25519Signer.init(true, ed25519PrivateKeyParameters);
            ed25519Signer.update(bArr, 0, bArr.length);
            return Result.m8979constructorimpl(ed25519Signer.generateSignature());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            return Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
    }

    @NotNull
    /* renamed from: verifySignature-dKHZaD0  reason: not valid java name */
    public static final Object m8866verifySignaturedKHZaD0(@NotNull String str, @NotNull byte[] bArr, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "publicKey");
        Intrinsics.checkNotNullParameter(bArr, "data");
        Intrinsics.checkNotNullParameter(str2, "signature");
        try {
            Result.Companion companion = Result.Companion;
            Ed25519PublicKeyParameters ed25519PublicKeyParameters = new Ed25519PublicKeyParameters(PublicKey.m8859getKeyAsBytesimpl(str));
            Ed25519Signer ed25519Signer = new Ed25519Signer();
            ed25519Signer.init(false, ed25519PublicKeyParameters);
            ed25519Signer.update(bArr, 0, bArr.length);
            byte[] bytes = str2.getBytes(Charsets.ISO_8859_1);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return Result.m8979constructorimpl(Boolean.valueOf(ed25519Signer.verifySignature(bytes)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            return Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
    }
}
