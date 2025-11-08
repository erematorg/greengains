package com.nodle.wallet.token;

import com.nodle.wallet.ChainType;
import com.nodle.wallet.ChainTypeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\n\u001a\u00020\u000bHÖ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001¨\u0006\u000e"}, d2 = {"Lcom/nodle/wallet/token/ZkToken;", "Lcom/nodle/wallet/token/GenericToken;", "Lcom/nodle/wallet/token/DefaultToken;", "<init>", "()V", "readResolve", "", "equals", "", "other", "hashCode", "", "toString", "", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ZkToken extends GenericToken implements DefaultToken {
    @NotNull
    public static final ZkToken INSTANCE = new ZkToken();

    private ZkToken() {
        super("ZKsync", "ZK", 18, "0x5A7d6b2F92C77FAD6CCaBd7EE0624E64907Eaf3E", ChainTypeKt.getChainId(ChainType.ZKSYNC_MAINNET), (DefaultConstructorMarker) null);
    }

    private final Object readResolve() {
        return INSTANCE;
    }

    public boolean equals(@Nullable Object obj) {
        return this == obj || (obj instanceof ZkToken);
    }

    public int hashCode() {
        return -954828611;
    }

    @NotNull
    public String toString() {
        return "ZkToken";
    }
}
