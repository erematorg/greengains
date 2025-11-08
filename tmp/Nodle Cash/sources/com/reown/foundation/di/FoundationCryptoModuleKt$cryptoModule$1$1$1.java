package com.reown.foundation.di;

import com.reown.foundation.crypto.data.repository.BaseClientIdJwtRepository;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"com/reown/foundation/di/FoundationCryptoModuleKt$cryptoModule$1$1$1", "Lcom/reown/foundation/crypto/data/repository/BaseClientIdJwtRepository;", "setKeyPair", "", "key", "", "privateKey", "Lcom/reown/foundation/common/model/PrivateKey;", "publicKey", "Lcom/reown/foundation/common/model/PublicKey;", "setKeyPair-FCmjpgM", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FoundationCryptoModuleKt$cryptoModule$1$1$1 extends BaseClientIdJwtRepository {
    /* renamed from: setKeyPair-FCmjpgM  reason: not valid java name */
    public void m8864setKeyPairFCmjpgM(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(str2, "privateKey");
        Intrinsics.checkNotNullParameter(str3, "publicKey");
    }
}
