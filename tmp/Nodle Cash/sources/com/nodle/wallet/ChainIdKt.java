package com.nodle.wallet;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0002\u001a\u00020\u0003*\u00060\u0001j\u0002`\u0004*\n\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u0005"}, d2 = {"ChainId", "", "isPolkadot", "", "Lcom/nodle/wallet/ChainId;", "wallet_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ChainIdKt {
    public static final boolean isPolkadot(long j2) {
        return j2 == ChainTypeKt.getChainId(ChainType.POLKADOT_MAINNET) || j2 == ChainTypeKt.getChainId(ChainType.POLKADOT_TESTNET);
    }
}
