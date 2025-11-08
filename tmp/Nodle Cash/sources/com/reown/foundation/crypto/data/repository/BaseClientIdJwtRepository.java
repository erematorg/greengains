package com.reown.foundation.crypto.data.repository;

import com.reown.foundation.common.model.PrivateKey;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.crypto.data.repository.model.IrnJwtClaims;
import com.reown.foundation.util.jwt.JwtHeader;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.util.UtilFunctionsKt;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¢\u0006\u0004\b\f\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000fH\u0016J$\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0013H\u0016J\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u000fJ\u0006\u0010\u0015\u001a\u00020\u0007¨\u0006\u0017"}, d2 = {"Lcom/reown/foundation/crypto/data/repository/BaseClientIdJwtRepository;", "Lcom/reown/foundation/crypto/data/repository/ClientIdJwtRepository;", "<init>", "()V", "setKeyPair", "", "key", "", "privateKey", "Lcom/reown/foundation/common/model/PrivateKey;", "publicKey", "Lcom/reown/foundation/common/model/PublicKey;", "setKeyPair-FCmjpgM", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getKeyPair", "Lkotlin/Pair;", "generateJWT", "serverUrl", "getIssuerClientId", "Lkotlin/Function1;", "generateAndStoreClientIdKeyPair", "generateSubject", "Companion", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseClientIdJwtRepository implements ClientIdJwtRepository {
    @NotNull
    public static final String CLIENT_ID_KEYPAIR_TAG = "key_did_keypair";
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int KEY_NONCE_SIZE = 32;
    private static final int KEY_SIZE = 32;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/reown/foundation/crypto/data/repository/BaseClientIdJwtRepository$Companion;", "", "<init>", "()V", "CLIENT_ID_KEYPAIR_TAG", "", "KEY_SIZE", "", "KEY_NONCE_SIZE", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @NotNull
    public final Pair<String, String> generateAndStoreClientIdKeyPair() {
        SecureRandom secureRandom = new SecureRandom();
        Ed25519KeyPairGenerator ed25519KeyPairGenerator = new Ed25519KeyPairGenerator();
        ed25519KeyPairGenerator.init(new Ed25519KeyGenerationParameters(secureRandom));
        AsymmetricCipherKeyPair generateKeyPair = ed25519KeyPairGenerator.generateKeyPair();
        Intrinsics.checkNotNullExpressionValue(generateKeyPair, "run(...)");
        AsymmetricKeyParameter asymmetricKeyParameter = generateKeyPair.getPublic();
        Intrinsics.checkNotNull(asymmetricKeyParameter, "null cannot be cast to non-null type org.bouncycastle.crypto.params.Ed25519PublicKeyParameters");
        AsymmetricKeyParameter asymmetricKeyParameter2 = generateKeyPair.getPrivate();
        Intrinsics.checkNotNull(asymmetricKeyParameter2, "null cannot be cast to non-null type org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters");
        byte[] encoded = ((Ed25519PublicKeyParameters) asymmetricKeyParameter).getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
        String r12 = PublicKey.m8856constructorimpl(UtilFunctionsKt.bytesToHex(encoded));
        byte[] encoded2 = ((Ed25519PrivateKeyParameters) asymmetricKeyParameter2).getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded2, "getEncoded(...)");
        String r02 = PrivateKey.m8848constructorimpl(UtilFunctionsKt.bytesToHex(encoded2));
        m8863setKeyPairFCmjpgM(CLIENT_ID_KEYPAIR_TAG, r02, r12);
        return TuplesKt.to(r12, r02);
    }

    @NotNull
    public String generateJWT(@NotNull String str, @NotNull Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "serverUrl");
        Intrinsics.checkNotNullParameter(function1, "getIssuerClientId");
        String generateSubject = generateSubject();
        Pair<String, String> keyPair = getKeyPair();
        String encodeEd25519DidKey = JwtUtilsKt.encodeEd25519DidKey(UtilFunctionsKt.hexToBytes(keyPair.component1()));
        function1.invoke((String) CollectionsKt.last(StringsKt__StringsKt.split$default((CharSequence) encodeEd25519DidKey, new String[]{":"}, false, 0, 6, (Object) null)));
        Pair jwtIatAndExp$default = JwtUtilsKt.jwtIatAndExp$default(TimeUnit.SECONDS, 1, TimeUnit.DAYS, 0, 8, (Object) null);
        IrnJwtClaims irnJwtClaims = new IrnJwtClaims(encodeEd25519DidKey, generateSubject, str, ((Number) jwtIatAndExp$default.component1()).longValue(), ((Number) jwtIatAndExp$default.component2()).longValue());
        JwtHeader.Companion companion = JwtHeader.Companion;
        byte[] bytes = JwtUtilsKt.encodeData(companion.getEdDSA().getEncoded(), irnJwtClaims).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        Object r12 = JwtUtilsKt.m8865signJwtFH9GnLg(PrivateKey.m8848constructorimpl(keyPair.component2()), bytes);
        ResultKt.throwOnFailure(r12);
        return JwtUtilsKt.encodeJWT(companion.getEdDSA().getEncoded(), irnJwtClaims, (byte[]) r12);
    }

    @NotNull
    public final String generateSubject() {
        return UtilFunctionsKt.bytesToHex(UtilFunctionsKt.randomBytes(32));
    }

    @NotNull
    public Pair<String, String> getKeyPair() {
        return generateAndStoreClientIdKeyPair();
    }

    /* renamed from: setKeyPair-FCmjpgM  reason: not valid java name */
    public abstract void m8863setKeyPairFCmjpgM(@NotNull String str, @NotNull String str2, @NotNull String str3);
}
