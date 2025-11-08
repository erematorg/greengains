package com.reown.android.internal.common.crypto.kmr;

import com.reown.android.internal.common.model.MissingKeyException;
import com.reown.foundation.common.model.Key;
import com.reown.foundation.common.model.PrivateKey;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0011\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0017\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0004\b\u000f\u0010\fJ#\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0004\u001a\u00020\nH&¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0012H&¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\nH&¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0012H&¢\u0006\u0004\b\u001e\u0010\fJ\u000f\u0010\u001f\u001a\u00020\nH&¢\u0006\u0004\b \u0010\u001cJ'\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\nH&¢\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H&¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#H&¢\u0006\u0004\b,\u0010*J\u001f\u0010-\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\nH&¢\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001f\u00101\u001a\u00020#2\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\nH&¢\u0006\u0004\b2\u00103¨\u00064À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "", "setKey", "", "key", "Lcom/reown/foundation/common/model/Key;", "tag", "", "removeKeys", "getPublicKey", "Lcom/reown/foundation/common/model/PublicKey;", "getPublicKey-p9DwDrs", "(Ljava/lang/String;)Ljava/lang/String;", "getSymmetricKey", "Lcom/reown/android/internal/common/model/SymmetricKey;", "getSymmetricKey-p84wnz8", "getKeyPair", "Lkotlin/Pair;", "Lcom/reown/foundation/common/model/PrivateKey;", "getKeyPair-wSlyqho", "(Ljava/lang/String;)Lkotlin/Pair;", "setKeyPair", "publicKey", "privateKey", "setKeyPair-TSAVemk", "(Ljava/lang/String;Ljava/lang/String;)V", "generateAndStoreEd25519KeyPair", "generateAndStoreEd25519KeyPair-uN_RPug", "()Ljava/lang/String;", "deriveAndStoreEd25519KeyPair", "deriveAndStoreEd25519KeyPair--tqZPjU", "generateAndStoreX25519KeyPair", "generateAndStoreX25519KeyPair-uN_RPug", "setKeyAgreement", "topic", "Lcom/reown/foundation/common/model/Topic;", "self", "peer", "setKeyAgreement-tzQMq24", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;)V", "getSelfPublicFromKeyAgreement", "getSelfPublicFromKeyAgreement-p9DwDrs", "(Lcom/reown/foundation/common/model/Topic;)Ljava/lang/String;", "generateAndStoreSymmetricKey", "generateAndStoreSymmetricKey-p84wnz8", "generateSymmetricKeyFromKeyAgreement", "generateSymmetricKeyFromKeyAgreement-rMsFr_I", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "getTopicFromKey", "generateTopicFromKeyAgreement", "generateTopicFromKeyAgreement-V_lFtQw", "(Ljava/lang/String;Ljava/lang/String;)Lcom/reown/foundation/common/model/Topic;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface KeyManagementRepository {
    @NotNull
    /* renamed from: deriveAndStoreEd25519KeyPair--tqZPjU  reason: not valid java name */
    String m8721deriveAndStoreEd25519KeyPairtqZPjU(@NotNull String str);

    @NotNull
    /* renamed from: generateAndStoreEd25519KeyPair-uN_RPug  reason: not valid java name */
    String m8722generateAndStoreEd25519KeyPairuN_RPug();

    @NotNull
    /* renamed from: generateAndStoreSymmetricKey-p84wnz8  reason: not valid java name */
    String m8723generateAndStoreSymmetricKeyp84wnz8(@NotNull Topic topic);

    @NotNull
    /* renamed from: generateAndStoreX25519KeyPair-uN_RPug  reason: not valid java name */
    String m8724generateAndStoreX25519KeyPairuN_RPug();

    @NotNull
    /* renamed from: generateSymmetricKeyFromKeyAgreement-rMsFr_I  reason: not valid java name */
    String m8725generateSymmetricKeyFromKeyAgreementrMsFr_I(@NotNull String str, @NotNull String str2);

    @NotNull
    /* renamed from: generateTopicFromKeyAgreement-V_lFtQw  reason: not valid java name */
    Topic m8726generateTopicFromKeyAgreementV_lFtQw(@NotNull String str, @NotNull String str2);

    @NotNull
    /* renamed from: getKeyPair-wSlyqho  reason: not valid java name */
    Pair<PublicKey, PrivateKey> m8727getKeyPairwSlyqho(@NotNull String str);

    @NotNull
    /* renamed from: getPublicKey-p9DwDrs  reason: not valid java name */
    String m8728getPublicKeyp9DwDrs(@NotNull String str);

    @NotNull
    /* renamed from: getSelfPublicFromKeyAgreement-p9DwDrs  reason: not valid java name */
    String m8729getSelfPublicFromKeyAgreementp9DwDrs(@NotNull Topic topic);

    @NotNull
    /* renamed from: getSymmetricKey-p84wnz8  reason: not valid java name */
    String m8730getSymmetricKeyp84wnz8(@NotNull String str);

    @NotNull
    Topic getTopicFromKey(@NotNull Key key);

    void removeKeys(@NotNull String str) throws MissingKeyException;

    void setKey(@NotNull Key key, @NotNull String str);

    /* renamed from: setKeyAgreement-tzQMq24  reason: not valid java name */
    void m8731setKeyAgreementtzQMq24(@NotNull Topic topic, @NotNull String str, @NotNull String str2);

    /* renamed from: setKeyPair-TSAVemk  reason: not valid java name */
    void m8732setKeyPairTSAVemk(@NotNull String str, @NotNull String str2);
}
