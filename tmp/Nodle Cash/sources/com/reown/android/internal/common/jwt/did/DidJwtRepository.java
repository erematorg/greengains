package com.reown.android.internal.common.jwt.did;

import androidx.browser.trusted.c;
import com.reown.android.internal.common.jwt.did.EncodeDidJwtPayloadUseCase;
import com.reown.android.internal.common.model.DidJwt;
import com.reown.foundation.util.jwt.JwtClaims;
import com.reown.foundation.util.jwt.JwtHeader;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a;\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00030\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\f\u001a(\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0001\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\b¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\u0012\u001a\u00020\u0013*\u00020\u0014R\u00020\u0014j\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0002\u0010\u0016\u001a+\u0010\u0017\u001a\u00020\u0013*\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010R\u00020\u0004j\u0006\u0010\u0018\u001a\u00020\u0004¢\u0006\u0002\u0010\u001a¨\u0006\u001b"}, d2 = {"encodeDidJwt", "Lkotlin/Result;", "Lcom/reown/android/internal/common/model/DidJwt;", "R", "Lcom/reown/foundation/util/jwt/JwtClaims;", "identityPrivateKey", "Lcom/reown/foundation/common/model/PrivateKey;", "encodeDidJwtPayloadUseCase", "Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase;", "useCaseParams", "Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase$Params;", "encodeDidJwt-57yAOYI", "(Ljava/lang/String;Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase;Lcom/reown/android/internal/common/jwt/did/EncodeDidJwtPayloadUseCase$Params;)Ljava/lang/Object;", "extractVerifiedDidJwtClaims", "C", "didJwt", "", "(Ljava/lang/String;)Ljava/lang/Object;", "verifyHeader", "", "Lcom/reown/foundation/util/jwt/JwtHeader;", "JwtHeader", "(Lcom/reown/foundation/util/jwt/JwtHeader;Lcom/reown/foundation/util/jwt/JwtHeader;)V", "verifyJwt", "JwtClaims", "signature", "(Lcom/reown/foundation/util/jwt/JwtClaims;Lcom/reown/foundation/util/jwt/JwtClaims;Ljava/lang/String;Ljava/lang/String;)V", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDidJwtRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DidJwtRepository.kt\ncom/reown/android/internal/common/jwt/did/DidJwtRepository\n+ 2 JwtUtils.kt\ncom/reown/foundation/util/jwt/JwtUtilsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,42:1\n98#2,2:43\n101#2,13:46\n1#3:45\n1#3:59\n*S KotlinDebug\n*F\n+ 1 DidJwtRepository.kt\ncom/reown/android/internal/common/jwt/did/DidJwtRepository\n*L\n22#1:43,2\n22#1:46,13\n22#1:45\n*E\n"})
@JvmName(name = "DidJwtRepository")
public final /* synthetic */ class DidJwtRepository {
    @NotNull
    /* renamed from: encodeDidJwt-57yAOYI  reason: not valid java name */
    public static final <R extends JwtClaims> Object m8738encodeDidJwt57yAOYI(@NotNull String str, @NotNull EncodeDidJwtPayloadUseCase<R> encodeDidJwtPayloadUseCase, @NotNull EncodeDidJwtPayloadUseCase.Params params) {
        Intrinsics.checkNotNullParameter(str, "identityPrivateKey");
        Intrinsics.checkNotNullParameter(encodeDidJwtPayloadUseCase, "encodeDidJwtPayloadUseCase");
        Intrinsics.checkNotNullParameter(params, "useCaseParams");
        try {
            Result.Companion companion = Result.Companion;
            R invoke = encodeDidJwtPayloadUseCase.invoke(params);
            JwtHeader.Companion companion2 = JwtHeader.Companion;
            byte[] bytes = JwtUtilsKt.encodeData(companion2.getEdDSA().getEncoded(), invoke).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            Object r2 = JwtUtilsKt.m8865signJwtFH9GnLg(str, bytes);
            ResultKt.throwOnFailure(r2);
            return Result.m8979constructorimpl(DidJwt.m8757boximpl(DidJwt.m8758constructorimpl(JwtUtilsKt.encodeJWT(companion2.getEdDSA().getEncoded(), invoke, (byte[]) r2))));
        } catch (Throwable th) {
            Result.Companion companion3 = Result.Companion;
            return Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
    }

    public static final /* synthetic */ <C extends JwtClaims> Object extractVerifiedDidJwtClaims(String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, "didJwt");
        try {
            Result.Companion companion = Result.Companion;
            List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{JwtUtilsKt.JWT_DELIMITER}, false, 0, 6, (Object) null);
            if (Z2.size() == 3) {
                List list = Z2;
                String str2 = (String) list.get(0);
                String str3 = (String) list.get(1);
                Charset charset = Charsets.UTF_8;
                byte[] bytes = str3.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                String decodeBase64 = JwtUtilsKt.decodeBase64(bytes);
                byte[] bytes2 = str2.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
                String decodeBase642 = JwtUtilsKt.decodeBase64(bytes2);
                byte[] bytes3 = ((String) list.get(2)).getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes3, "getBytes(...)");
                String decodeBase643 = JwtUtilsKt.decodeBase64(bytes3);
                Moshi build = new Moshi.Builder().addLast((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build();
                Intrinsics.reifiedOperationMarker(4, "C");
                JwtClaims fromJson = build.adapter(JwtClaims.class).fromJson(decodeBase64);
                if (fromJson != null) {
                    Intrinsics.checkNotNull(fromJson);
                    JwtHeader fromJson2 = build.adapter(JwtHeader.class).fromJson(decodeBase642);
                    if (fromJson2 != null) {
                        obj = Result.m8979constructorimpl(new Triple(fromJson2, fromJson, decodeBase643));
                        ResultKt.throwOnFailure(obj);
                        Triple triple = (Triple) obj;
                        JwtHeader jwtHeader = (JwtHeader) triple.component1();
                        JwtClaims jwtClaims = (JwtClaims) triple.component2();
                        verifyHeader(jwtHeader, jwtHeader);
                        verifyJwt(jwtClaims, jwtClaims, str, (String) triple.component3());
                        return Result.m8979constructorimpl(jwtClaims);
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

    public static final void verifyHeader(@NotNull JwtHeader jwtHeader, @NotNull JwtHeader jwtHeader2) {
        Intrinsics.checkNotNullParameter(jwtHeader, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(jwtHeader2, "<this>");
        if (!Intrinsics.areEqual((Object) jwtHeader2.getAlgorithm(), (Object) JwtHeader.Companion.getEdDSA().getAlgorithm())) {
            throw new Throwable(c.a("Unsupported header alg: ", jwtHeader2.getAlgorithm()));
        }
    }

    public static final void verifyJwt(@NotNull JwtClaims jwtClaims, @NotNull JwtClaims jwtClaims2, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(jwtClaims, "$context_receiver_0");
        Intrinsics.checkNotNullParameter(jwtClaims2, "<this>");
        Intrinsics.checkNotNullParameter(str, "didJwt");
        Intrinsics.checkNotNullParameter(str2, "signature");
        String decodeEd25519DidKey = JwtUtilsKt.decodeEd25519DidKey(jwtClaims2.getIssuer());
        byte[] bytes = JwtUtilsKt.extractData(str).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        Object r12 = JwtUtilsKt.m8866verifySignaturedKHZaD0(decodeEd25519DidKey, bytes, str2);
        ResultKt.throwOnFailure(r12);
        if (!((Boolean) r12).booleanValue()) {
            throw new Throwable("Invalid signature");
        }
    }
}
