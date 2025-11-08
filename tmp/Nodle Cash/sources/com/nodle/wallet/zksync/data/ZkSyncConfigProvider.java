package com.nodle.wallet.zksync.data;

import com.nodle.wallet.ChainType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/nodle/wallet/zksync/data/ZkSyncConfigProvider;", "", "<init>", "()V", "ZKSYNC_MAINNET", "Lcom/nodle/wallet/zksync/data/ZkSyncConfig;", "getZKSYNC_MAINNET", "()Lcom/nodle/wallet/zksync/data/ZkSyncConfig;", "ZKSYNC_TESTNET", "getZKSYNC_TESTNET", "getConfig", "chainType", "Lcom/nodle/wallet/ChainType;", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ZkSyncConfigProvider {
    @NotNull
    public static final ZkSyncConfigProvider INSTANCE = new ZkSyncConfigProvider();
    @NotNull
    private static final ZkSyncConfig ZKSYNC_MAINNET = new ZkSyncConfig("0x95b3641d549f719eb5105f9550eca4a7a2f305de", "0xb2b7075ead608fabf04c3959983ee63df5b0e0fc", "0x5de7fe085ee66Fb48447e75AA8fb0598a080AEe0", "0xE629B208046F7A33dE3A43931c9FE505A7Ac3d36", "https://mainnet.era.zksync.io", 324);
    @NotNull
    private static final ZkSyncConfig ZKSYNC_TESTNET = new ZkSyncConfig("0x999368030Ba79898E83EaAE0E49E89B7f6410940", "0x94d095cfF9feef70d2b343e7f82ef7eac3a0c7A7", "0x1427d38B967435a3F8f476Cda0bc4F51fe66AF4D", "0xba8a8ff7E7332f7e05205Ec9fC927965435C552c", "https://sepolia.era.zksync.dev", 300);

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
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
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.data.ZkSyncConfigProvider.WhenMappings.<clinit>():void");
        }
    }

    private ZkSyncConfigProvider() {
    }

    @NotNull
    public final ZkSyncConfig getConfig(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "chainType");
        int i3 = WhenMappings.$EnumSwitchMapping$0[chainType.ordinal()];
        if (i3 == 1) {
            return ZKSYNC_MAINNET;
        }
        if (i3 == 2) {
            return ZKSYNC_TESTNET;
        }
        throw new IllegalArgumentException("Unsupported chain type");
    }

    @NotNull
    public final ZkSyncConfig getZKSYNC_MAINNET() {
        return ZKSYNC_MAINNET;
    }

    @NotNull
    public final ZkSyncConfig getZKSYNC_TESTNET() {
        return ZKSYNC_TESTNET;
    }
}
