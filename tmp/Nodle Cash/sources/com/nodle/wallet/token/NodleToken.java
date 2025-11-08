package com.nodle.wallet.token;

import com.nodle.wallet.ChainType;
import com.nodle.wallet.ChainTypeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001¨\u0006\u000f"}, d2 = {"Lcom/nodle/wallet/token/NodleToken;", "Lcom/nodle/wallet/token/GenericToken;", "Lcom/nodle/wallet/token/DefaultToken;", "Lcom/nodle/wallet/token/NODL;", "<init>", "()V", "readResolve", "", "equals", "", "other", "hashCode", "", "toString", "", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NodleToken extends GenericToken implements DefaultToken, NODL {
    @NotNull
    public static final NodleToken INSTANCE = new NodleToken();

    private NodleToken() {
        super("Nodle Token", "NODL", 18, "0xBD4372e44c5eE654dd838304006E1f0f69983154", ChainTypeKt.getChainId(ChainType.ZKSYNC_MAINNET), (DefaultConstructorMarker) null);
    }

    private final Object readResolve() {
        return INSTANCE;
    }

    public boolean equals(@Nullable Object obj) {
        return this == obj || (obj instanceof NodleToken);
    }

    public int hashCode() {
        return -1042125752;
    }

    @NotNull
    public String toString() {
        return "NodleToken";
    }
}
