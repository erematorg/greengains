package com.nodle.wallet.token;

import com.nodle.wallet.ChainType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0012\u0010\u0004\u001a\u00020\u00052\n\u0010\b\u001a\u00060\tj\u0002`\n¨\u0006\u000b"}, d2 = {"Lcom/nodle/wallet/token/NodleTokenPerChainProvider;", "", "<init>", "()V", "getNodleToken", "Lcom/nodle/wallet/token/GenericToken;", "chainType", "Lcom/nodle/wallet/ChainType;", "chainId", "", "Lcom/nodle/wallet/ChainId;", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NodleTokenPerChainProvider {
    @NotNull
    public static final NodleTokenPerChainProvider INSTANCE = new NodleTokenPerChainProvider();

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
                com.nodle.wallet.ChainType r1 = com.nodle.wallet.ChainType.POLKADOT_MAINNET     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.nodle.wallet.ChainType r1 = com.nodle.wallet.ChainType.POLKADOT_TESTNET     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.nodle.wallet.ChainType r1 = com.nodle.wallet.ChainType.ZKSYNC_MAINNET     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.nodle.wallet.ChainType r1 = com.nodle.wallet.ChainType.ZKSYNC_TESTNET     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.token.NodleTokenPerChainProvider.WhenMappings.<clinit>():void");
        }
    }

    private NodleTokenPerChainProvider() {
    }

    @NotNull
    public final GenericToken getNodleToken(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "chainType");
        int i3 = WhenMappings.$EnumSwitchMapping$0[chainType.ordinal()];
        if (i3 == 1) {
            return NodlePolkadotToken.INSTANCE;
        }
        if (i3 == 2) {
            return NodlePolkadotToken.INSTANCE;
        }
        if (i3 == 3) {
            return NodleToken.INSTANCE;
        }
        if (i3 == 4) {
            return NodleTestnetToken.INSTANCE;
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public final GenericToken getNodleToken(long j2) {
        ChainType fromChainId = ChainType.Companion.fromChainId(j2);
        if (fromChainId == null) {
            fromChainId = ChainType.POLKADOT_MAINNET;
        }
        return getNodleToken(fromChainId);
    }
}
