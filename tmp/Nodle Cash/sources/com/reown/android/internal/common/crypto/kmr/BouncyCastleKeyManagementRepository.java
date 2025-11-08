package com.reown.android.internal.common.crypto.kmr;

import androidx.browser.trusted.c;
import com.reown.android.internal.common.crypto.UtilsKt;
import com.reown.android.internal.common.model.MissingKeyException;
import com.reown.android.internal.common.model.SymmetricKey;
import com.reown.android.internal.common.storage.key_chain.KeyStore;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Key;
import com.reown.foundation.common.model.PrivateKey;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.util.UtilFunctionsKt;
import java.security.SecureRandom;
import java.util.Locale;
import javax.crypto.KeyGenerator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.HKDFParameters;
import org.bouncycastle.math.ec.rfc7748.X25519;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0000\u0018\u0000 <2\u00020\u0001:\u0001<B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0017\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0012\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\rH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\rH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010\u000fJ\u000f\u0010$\u001a\u00020\rH\u0016¢\u0006\u0004\b%\u0010\u001fJ\u0017\u0010&\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0004\b'\u0010\u0017J\u001f\u0010(\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\rH\u0016¢\u0006\u0004\b)\u0010*J\u001f\u0010+\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\rH\u0016¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010/\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001f\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\r2\u0006\u0010!\u001a\u00020\"H\u0016¢\u0006\u0004\b2\u00103J#\u00104\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\"052\u0006\u0010\b\u001a\u00020\rH\u0016¢\u0006\u0004\b6\u00107J\b\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u0002092\u0006\u0010;\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/reown/android/internal/common/crypto/kmr/BouncyCastleKeyManagementRepository;", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "keyChain", "Lcom/reown/android/internal/common/storage/key_chain/KeyStore;", "<init>", "(Lcom/reown/android/internal/common/storage/key_chain/KeyStore;)V", "setKey", "", "key", "Lcom/reown/foundation/common/model/Key;", "tag", "", "getPublicKey", "Lcom/reown/foundation/common/model/PublicKey;", "getPublicKey-p9DwDrs", "(Ljava/lang/String;)Ljava/lang/String;", "getSymmetricKey", "Lcom/reown/android/internal/common/model/SymmetricKey;", "getSymmetricKey-p84wnz8", "getSelfPublicFromKeyAgreement", "topic", "Lcom/reown/foundation/common/model/Topic;", "getSelfPublicFromKeyAgreement-p9DwDrs", "(Lcom/reown/foundation/common/model/Topic;)Ljava/lang/String;", "setKeyAgreement", "self", "peer", "setKeyAgreement-tzQMq24", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;)V", "generateAndStoreEd25519KeyPair", "generateAndStoreEd25519KeyPair-uN_RPug", "()Ljava/lang/String;", "deriveAndStoreEd25519KeyPair", "privateKey", "Lcom/reown/foundation/common/model/PrivateKey;", "deriveAndStoreEd25519KeyPair--tqZPjU", "generateAndStoreX25519KeyPair", "generateAndStoreX25519KeyPair-uN_RPug", "generateAndStoreSymmetricKey", "generateAndStoreSymmetricKey-p84wnz8", "generateSymmetricKeyFromKeyAgreement", "generateSymmetricKeyFromKeyAgreement-rMsFr_I", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "generateTopicFromKeyAgreement", "generateTopicFromKeyAgreement-V_lFtQw", "(Ljava/lang/String;Ljava/lang/String;)Lcom/reown/foundation/common/model/Topic;", "getTopicFromKey", "removeKeys", "setKeyPair", "publicKey", "setKeyPair-TSAVemk", "(Ljava/lang/String;Ljava/lang/String;)V", "getKeyPair", "Lkotlin/Pair;", "getKeyPair-wSlyqho", "(Ljava/lang/String;)Lkotlin/Pair;", "createSymmetricKey", "", "deriveHKDFKey", "sharedSecret", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BouncyCastleKeyManagementRepository implements KeyManagementRepository {
    @NotNull
    @Deprecated
    public static final String AES = "AES";
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    @Deprecated
    public static final String KEY_AGREEMENT_CONTEXT = "key_agreement/";
    @Deprecated
    public static final int KEY_SIZE = 32;
    @Deprecated
    public static final int SYM_KEY_SIZE = 256;
    @NotNull
    private final KeyStore keyChain;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/android/internal/common/crypto/kmr/BouncyCastleKeyManagementRepository$Companion;", "", "<init>", "()V", "KEY_SIZE", "", "SYM_KEY_SIZE", "AES", "", "KEY_AGREEMENT_CONTEXT", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public BouncyCastleKeyManagementRepository(@NotNull KeyStore keyStore) {
        Intrinsics.checkNotNullParameter(keyStore, "keyChain");
        this.keyChain = keyStore;
    }

    private final byte[] createSymmetricKey() {
        KeyGenerator instance = KeyGenerator.getInstance(AES);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
        return encoded;
    }

    private final byte[] deriveHKDFKey(String str) {
        HKDFBytesGenerator hKDFBytesGenerator = new HKDFBytesGenerator(new SHA256Digest());
        byte[] bArr = new byte[32];
        hKDFBytesGenerator.init(new HKDFParameters(UtilFunctionsKt.hexToBytes(str), new byte[0], new byte[0]));
        hKDFBytesGenerator.generateBytes(bArr, 0, 32);
        return bArr;
    }

    @NotNull
    /* renamed from: deriveAndStoreEd25519KeyPair--tqZPjU  reason: not valid java name */
    public String m8709deriveAndStoreEd25519KeyPairtqZPjU(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "privateKey");
        byte[] encoded = new Ed25519PrivateKeyParameters(UtilFunctionsKt.hexToBytes(str), 0).generatePublicKey().getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "getEncoded(...)");
        String lowerCase = UtilFunctionsKt.bytesToHex(encoded).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String r02 = PublicKey.m8856constructorimpl(lowerCase);
        m8720setKeyPairTSAVemk(r02, str);
        return r02;
    }

    @NotNull
    /* renamed from: generateAndStoreEd25519KeyPair-uN_RPug  reason: not valid java name */
    public String m8710generateAndStoreEd25519KeyPairuN_RPug() {
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[32];
        Ed25519.generatePrivateKey(new SecureRandom(), bArr2);
        Ed25519.generatePublicKey(bArr2, 0, bArr, 0);
        String bytesToHex = UtilFunctionsKt.bytesToHex(bArr);
        Locale locale = Locale.ROOT;
        String lowerCase = bytesToHex.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String r2 = PublicKey.m8856constructorimpl(lowerCase);
        String lowerCase2 = UtilFunctionsKt.bytesToHex(bArr2).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        m8720setKeyPairTSAVemk(r2, PrivateKey.m8848constructorimpl(lowerCase2));
        String lowerCase3 = UtilFunctionsKt.bytesToHex(bArr).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
        return PublicKey.m8856constructorimpl(lowerCase3);
    }

    @NotNull
    /* renamed from: generateAndStoreSymmetricKey-p84wnz8  reason: not valid java name */
    public String m8711generateAndStoreSymmetricKeyp84wnz8(@NotNull Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        String r02 = SymmetricKey.m8778constructorimpl(UtilFunctionsKt.bytesToHex(createSymmetricKey()));
        this.keyChain.setKey(topic.getValue(), SymmetricKey.m8777boximpl(r02));
        return r02;
    }

    @NotNull
    /* renamed from: generateAndStoreX25519KeyPair-uN_RPug  reason: not valid java name */
    public String m8712generateAndStoreX25519KeyPairuN_RPug() {
        byte[] bArr = new byte[32];
        byte[] bArr2 = new byte[32];
        X25519.generatePrivateKey(new SecureRandom(), bArr2);
        X25519.generatePublicKey(bArr2, 0, bArr, 0);
        String bytesToHex = UtilFunctionsKt.bytesToHex(bArr);
        Locale locale = Locale.ROOT;
        String lowerCase = bytesToHex.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String r2 = PublicKey.m8856constructorimpl(lowerCase);
        String lowerCase2 = UtilFunctionsKt.bytesToHex(bArr2).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        m8720setKeyPairTSAVemk(r2, PrivateKey.m8848constructorimpl(lowerCase2));
        String lowerCase3 = UtilFunctionsKt.bytesToHex(bArr).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
        return PublicKey.m8856constructorimpl(lowerCase3);
    }

    @NotNull
    /* renamed from: generateSymmetricKeyFromKeyAgreement-rMsFr_I  reason: not valid java name */
    public String m8713generateSymmetricKeyFromKeyAgreementrMsFr_I(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "self");
        Intrinsics.checkNotNullParameter(str2, "peer");
        byte[] bArr = new byte[32];
        X25519.scalarMult(UtilFunctionsKt.hexToBytes(m8715getKeyPairwSlyqho(str).component2().m8854unboximpl()), 0, UtilFunctionsKt.hexToBytes(str2), 0, bArr, 0);
        return SymmetricKey.m8778constructorimpl(UtilFunctionsKt.bytesToHex(deriveHKDFKey(UtilFunctionsKt.bytesToHex(bArr))));
    }

    @NotNull
    /* renamed from: generateTopicFromKeyAgreement-V_lFtQw  reason: not valid java name */
    public Topic m8714generateTopicFromKeyAgreementV_lFtQw(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "self");
        Intrinsics.checkNotNullParameter(str2, "peer");
        String r02 = m8713generateSymmetricKeyFromKeyAgreementrMsFr_I(str, str2);
        Topic topic = new Topic(UtilsKt.sha256(SymmetricKey.m8781getKeyAsBytesimpl(r02)));
        KeyStore keyStore = this.keyChain;
        String lowerCase = topic.getValue().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        keyStore.setKey(lowerCase, SymmetricKey.m8777boximpl(r02));
        m8719setKeyAgreementtzQMq24(topic, str, str2);
        return topic;
    }

    @NotNull
    /* renamed from: getKeyPair-wSlyqho  reason: not valid java name */
    public Pair<PublicKey, PrivateKey> m8715getKeyPairwSlyqho(@NotNull String str) throws MissingKeyException {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Pair<String, String> keys = this.keyChain.getKeys(str);
        if (keys != null) {
            return new Pair<>(PublicKey.m8855boximpl(PublicKey.m8856constructorimpl(keys.component1())), PrivateKey.m8847boximpl(PrivateKey.m8848constructorimpl(keys.component2())));
        }
        throw new MissingKeyException(c.a("No key pair for tag: ", str));
    }

    @NotNull
    /* renamed from: getPublicKey-p9DwDrs  reason: not valid java name */
    public String m8716getPublicKeyp9DwDrs(@NotNull String str) throws MissingKeyException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        String key = this.keyChain.getKey(str);
        if (key != null) {
            return PublicKey.m8856constructorimpl(key);
        }
        throw new MissingKeyException(c.a("No PublicKey for tag: ", str));
    }

    @NotNull
    /* renamed from: getSelfPublicFromKeyAgreement-p9DwDrs  reason: not valid java name */
    public String m8717getSelfPublicFromKeyAgreementp9DwDrs(@NotNull Topic topic) throws MissingKeyException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        String a2 = c.a(KEY_AGREEMENT_CONTEXT, topic.getValue());
        Pair<String, String> keys = this.keyChain.getKeys(a2);
        if (keys != null) {
            return PublicKey.m8856constructorimpl(keys.component1());
        }
        throw new MissingKeyException(c.a("No key pair for tag: ", a2));
    }

    @NotNull
    /* renamed from: getSymmetricKey-p84wnz8  reason: not valid java name */
    public String m8718getSymmetricKeyp84wnz8(@NotNull String str) throws MissingKeyException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        String key = this.keyChain.getKey(str);
        if (key != null) {
            return SymmetricKey.m8778constructorimpl(key);
        }
        throw new MissingKeyException(c.a("No SymmetricKey for tag: ", str));
    }

    @NotNull
    public Topic getTopicFromKey(@NotNull Key key) {
        Intrinsics.checkNotNullParameter(key, JwtUtilsKt.DID_METHOD_KEY);
        return new Topic(UtilsKt.sha256(key.getKeyAsBytes()));
    }

    public void removeKeys(@NotNull String str) throws MissingKeyException {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        Pair<String, String> keys = this.keyChain.getKeys(str);
        if (keys != null) {
            KeyStore keyStore = this.keyChain;
            String lowerCase = keys.component1().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            keyStore.deleteKeys(lowerCase);
            keyStore.deleteKeys(str);
            return;
        }
        throw new MissingKeyException(c.a("No key pair for tag: ", str));
    }

    public void setKey(@NotNull Key key, @NotNull String str) {
        Intrinsics.checkNotNullParameter(key, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        this.keyChain.setKey(str, key);
    }

    /* renamed from: setKeyAgreement-tzQMq24  reason: not valid java name */
    public void m8719setKeyAgreementtzQMq24(@NotNull Topic topic, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, "self");
        Intrinsics.checkNotNullParameter(str2, "peer");
        this.keyChain.setKeys(c.a(KEY_AGREEMENT_CONTEXT, topic.getValue()), PublicKey.m8855boximpl(str), PublicKey.m8855boximpl(str2));
    }

    /* renamed from: setKeyPair-TSAVemk  reason: not valid java name */
    public void m8720setKeyPairTSAVemk(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "publicKey");
        Intrinsics.checkNotNullParameter(str2, "privateKey");
        this.keyChain.setKeys(str, PublicKey.m8855boximpl(str), PrivateKey.m8847boximpl(str2));
    }
}
