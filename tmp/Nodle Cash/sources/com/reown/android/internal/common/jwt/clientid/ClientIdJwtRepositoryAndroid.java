package com.reown.android.internal.common.jwt.clientid;

import com.reown.android.internal.common.exception.CannotFindKeyPairException;
import com.reown.android.internal.common.storage.key_chain.KeyStore;
import com.reown.foundation.common.model.PrivateKey;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.crypto.data.repository.BaseClientIdJwtRepository;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0014\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/reown/android/internal/common/jwt/clientid/ClientIdJwtRepositoryAndroid;", "Lcom/reown/foundation/crypto/data/repository/BaseClientIdJwtRepository;", "keyChain", "Lcom/reown/android/internal/common/storage/key_chain/KeyStore;", "<init>", "(Lcom/reown/android/internal/common/storage/key_chain/KeyStore;)V", "setKeyPair", "", "key", "", "privateKey", "Lcom/reown/foundation/common/model/PrivateKey;", "publicKey", "Lcom/reown/foundation/common/model/PublicKey;", "setKeyPair-FCmjpgM", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getKeyPair", "Lkotlin/Pair;", "doesKeyPairExist", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ClientIdJwtRepositoryAndroid extends BaseClientIdJwtRepository {
    @NotNull
    private final KeyStore keyChain;

    public ClientIdJwtRepositoryAndroid(@NotNull KeyStore keyStore) {
        Intrinsics.checkNotNullParameter(keyStore, "keyChain");
        this.keyChain = keyStore;
    }

    private final boolean doesKeyPairExist() {
        return this.keyChain.checkKeys(BaseClientIdJwtRepository.CLIENT_ID_KEYPAIR_TAG);
    }

    @NotNull
    public Pair<String, String> getKeyPair() {
        if (!doesKeyPairExist()) {
            return generateAndStoreClientIdKeyPair();
        }
        Pair<String, String> keys = this.keyChain.getKeys(BaseClientIdJwtRepository.CLIENT_ID_KEYPAIR_TAG);
        if (keys != null) {
            return TuplesKt.to(keys.component2(), keys.component1());
        }
        throw new CannotFindKeyPairException("No key pair for given tag: key_did_keypair");
    }

    /* renamed from: setKeyPair-FCmjpgM  reason: not valid java name */
    public void m8737setKeyPairFCmjpgM(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(str2, "privateKey");
        Intrinsics.checkNotNullParameter(str3, "publicKey");
        this.keyChain.setKeys(BaseClientIdJwtRepository.CLIENT_ID_KEYPAIR_TAG, PrivateKey.m8847boximpl(str2), PublicKey.m8855boximpl(str3));
    }
}
