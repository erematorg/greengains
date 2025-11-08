package com.nodle.wallet.token;

import com.nodle.wallet.ChainType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0012\u0010\u0004\u001a\u00020\u00052\n\u0010\b\u001a\u00060\tj\u0002`\n¨\u0006\u000b"}, d2 = {"Lcom/nodle/wallet/token/NodleTokenProvider;", "", "<init>", "()V", "getNodleToken", "Lcom/nodle/wallet/token/GenericToken;", "chainType", "Lcom/nodle/wallet/ChainType;", "chainId", "", "Lcom/nodle/wallet/ChainId;", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NodleTokenProvider {
    @NotNull
    public final GenericToken getNodleToken(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "chainType");
        return NodleTokenPerChainProvider.INSTANCE.getNodleToken(chainType);
    }

    @NotNull
    public final GenericToken getNodleToken(long j2) {
        return NodleTokenPerChainProvider.INSTANCE.getNodleToken(j2);
    }
}
