package com.reown.android.verify.domain;

import androidx.browser.trusted.c;
import com.reown.android.verify.model.JWK;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.util.UtilFunctionsKt;
import io.ipfs.multibase.binary.Base64;
import java.math.BigInteger;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005¨\u0006\u000f"}, d2 = {"Lcom/reown/android/verify/domain/JWTRepository;", "", "<init>", "()V", "generateP256PublicKeyFromJWK", "", "jwk", "Lcom/reown/android/verify/model/JWK;", "verifyJWT", "", "jwt", "publicKey", "", "decodeClaimsJWT", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJWTRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JWTRepository.kt\ncom/reown/android/verify/domain/JWTRepository\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,60:1\n1#2:61\n*E\n"})
public final class JWTRepository {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/reown/android/verify/domain/JWTRepository$Companion;", "", "<init>", "()V", "toBigInt", "Ljava/math/BigInteger;", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final BigInteger toBigInt(@NotNull byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            return new BigInteger(1, bArr);
        }

        private Companion() {
        }
    }

    @NotNull
    public final String decodeClaimsJWT(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "jwt");
        List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{JwtUtilsKt.JWT_DELIMITER}, false, 0, 6, (Object) null);
        if (Z2.size() == 3) {
            byte[] decodeBase64 = Base64.decodeBase64((String) Z2.get(1));
            Intrinsics.checkNotNullExpressionValue(decodeBase64, "decodeBase64(...)");
            return new String(decodeBase64, Charsets.UTF_8);
        }
        throw new Throwable(c.a("Unable to split jwt: ", str));
    }

    @NotNull
    public final String generateP256PublicKeyFromJWK(@NotNull JWK jwk) {
        Intrinsics.checkNotNullParameter(jwk, "jwk");
        byte[] decodeBase64 = Base64.decodeBase64(jwk.getX());
        Intrinsics.checkNotNullExpressionValue(decodeBase64, "decodeBase64(...)");
        byte[] decodeBase642 = Base64.decodeBase64(jwk.getY());
        Intrinsics.checkNotNullExpressionValue(decodeBase642, "decodeBase64(...)");
        ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("P-256");
        Intrinsics.checkNotNullExpressionValue(parameterSpec, "getParameterSpec(...)");
        ECCurve curve = parameterSpec.getCurve();
        Companion companion = Companion;
        byte[] encoded = new ECPublicKeyParameters(curve.createPoint(companion.toBigInt(decodeBase64), companion.toBigInt(decodeBase642)), new ECDomainParameters(parameterSpec.getCurve(), parameterSpec.getG(), parameterSpec.getN(), parameterSpec.getH())).getQ().getEncoded(false);
        Intrinsics.checkNotNull(encoded);
        return UtilFunctionsKt.bytesToHex(encoded);
    }

    public final boolean verifyJWT(@NotNull String str, @NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, "jwt");
        Intrinsics.checkNotNullParameter(bArr, "publicKey");
        try {
            List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{JwtUtilsKt.JWT_DELIMITER}, false, 0, 6, (Object) null);
            if (Z2.size() == 3) {
                byte[] decodeBase64 = Base64.decodeBase64((String) Z2.get(2));
                byte[] bytes = (((String) Z2.get(0)) + JwtUtilsKt.JWT_DELIMITER + ((String) Z2.get(1))).getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                Intrinsics.checkNotNull(decodeBase64);
                BigInteger bigInteger = new BigInteger(1, ArraysKt.sliceArray(decodeBase64, RangesKt.until(0, decodeBase64.length / 2)));
                BigInteger bigInteger2 = new BigInteger(1, ArraysKt.sliceArray(decodeBase64, RangesKt.until(decodeBase64.length / 2, decodeBase64.length)));
                ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("P-256");
                Intrinsics.checkNotNullExpressionValue(parameterSpec, "getParameterSpec(...)");
                ECPoint decodePoint = parameterSpec.getCurve().decodePoint(bArr);
                Intrinsics.checkNotNullExpressionValue(decodePoint, "decodePoint(...)");
                ECPublicKeyParameters eCPublicKeyParameters = new ECPublicKeyParameters(decodePoint, new ECDomainParameters(parameterSpec.getCurve(), parameterSpec.getG(), parameterSpec.getN(), parameterSpec.getH()));
                ECDSASigner eCDSASigner = new ECDSASigner();
                eCDSASigner.init(false, eCPublicKeyParameters);
                SHA256Digest sHA256Digest = new SHA256Digest();
                sHA256Digest.update(bytes, 0, bytes.length);
                byte[] bArr2 = new byte[sHA256Digest.getDigestSize()];
                sHA256Digest.doFinal(bArr2, 0);
                return eCDSASigner.verifySignature(bArr2, bigInteger, bigInteger2);
            }
            throw new Throwable("Unable to split jwt: " + str);
        } catch (Exception unused) {
            return false;
        }
    }
}
