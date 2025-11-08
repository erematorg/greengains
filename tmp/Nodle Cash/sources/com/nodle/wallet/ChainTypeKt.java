package com.nodle.wallet;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\"\u0019\u0010\u0006\u001a\u00060\u0007j\u0002`\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"isPolkadot", "", "Lcom/nodle/wallet/ChainType;", "isZkSync", "isMainnet", "isTestnet", "chainId", "", "Lcom/nodle/wallet/ChainId;", "getChainId", "(Lcom/nodle/wallet/ChainType;)J", "wallet_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ChainTypeKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.nodle.wallet.ChainType[] r0 = com.nodle.wallet.ChainType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.nodle.wallet.ChainType r1 = com.nodle.wallet.ChainType.ZKSYNC_MAINNET     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.nodle.wallet.ChainType r1 = com.nodle.wallet.ChainType.ZKSYNC_TESTNET     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.nodle.wallet.ChainType r1 = com.nodle.wallet.ChainType.POLKADOT_MAINNET     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.nodle.wallet.ChainType r1 = com.nodle.wallet.ChainType.POLKADOT_TESTNET     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.ChainTypeKt.WhenMappings.<clinit>():void");
        }
    }

    public static final long getChainId(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "<this>");
        int i3 = WhenMappings.$EnumSwitchMapping$0[chainType.ordinal()];
        if (i3 == 1) {
            return 324;
        }
        if (i3 == 2) {
            return 300;
        }
        if (i3 == 3) {
            return -1;
        }
        if (i3 == 4) {
            return -2;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final boolean isMainnet(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "<this>");
        return chainType == ChainType.POLKADOT_MAINNET || chainType == ChainType.ZKSYNC_MAINNET;
    }

    public static final boolean isPolkadot(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "<this>");
        return chainType == ChainType.POLKADOT_MAINNET || chainType == ChainType.POLKADOT_TESTNET;
    }

    public static final boolean isTestnet(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "<this>");
        return chainType == ChainType.POLKADOT_TESTNET || chainType == ChainType.ZKSYNC_TESTNET;
    }

    public static final boolean isZkSync(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "<this>");
        return chainType == ChainType.ZKSYNC_MAINNET || chainType == ChainType.ZKSYNC_TESTNET;
    }
}
