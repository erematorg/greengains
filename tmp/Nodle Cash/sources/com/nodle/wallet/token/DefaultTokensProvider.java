package com.nodle.wallet.token;

import com.nodle.wallet.ChainType;
import com.nodle.wallet.ChainTypeKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fJ\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\nJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u0013"}, d2 = {"Lcom/nodle/wallet/token/DefaultTokensProvider;", "", "<init>", "()V", "getToken", "Lcom/nodle/wallet/token/GenericToken;", "tokenAddress", "", "chainId", "", "Lcom/nodle/wallet/ChainId;", "getAllSupportedTokens", "", "containsToken", "", "getSupportedTokens", "chainType", "Lcom/nodle/wallet/ChainType;", "Companion", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultTokensProvider {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    @NotNull
    public static final List<GenericToken> ZKSYNC_SUPPORTED_TOKENS;
    /* access modifiers changed from: private */
    @NotNull
    public static final Map<Pair<String, Long>, GenericToken> tokensMap;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR-\u0010\t\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u000b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/nodle/wallet/token/DefaultTokensProvider$Companion;", "", "<init>", "()V", "ZKSYNC_SUPPORTED_TOKENS", "", "Lcom/nodle/wallet/token/GenericToken;", "getZKSYNC_SUPPORTED_TOKENS", "()Ljava/util/List;", "tokensMap", "", "Lkotlin/Pair;", "", "", "Lcom/nodle/wallet/ChainId;", "getTokensMap", "()Ljava/util/Map;", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Map<Pair<String, Long>, GenericToken> getTokensMap() {
            return DefaultTokensProvider.tokensMap;
        }

        @NotNull
        public final List<GenericToken> getZKSYNC_SUPPORTED_TOKENS() {
            return DefaultTokensProvider.ZKSYNC_SUPPORTED_TOKENS;
        }

        private Companion() {
        }
    }

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
            throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.token.DefaultTokensProvider.WhenMappings.<clinit>():void");
        }
    }

    static {
        ZkToken zkToken = ZkToken.INSTANCE;
        EthToken ethToken = EthToken.INSTANCE;
        USDCToken uSDCToken = USDCToken.INSTANCE;
        ZKSYNC_SUPPORTED_TOKENS = CollectionsKt.listOf(zkToken, ethToken, uSDCToken);
        NodleToken nodleToken = NodleToken.INSTANCE;
        String address = nodleToken.getAddress();
        ChainType chainType = ChainType.ZKSYNC_MAINNET;
        Pair pair = TuplesKt.to(new Pair(address, Long.valueOf(ChainTypeKt.getChainId(chainType))), nodleToken);
        NodleTestnetToken nodleTestnetToken = NodleTestnetToken.INSTANCE;
        Pair pair2 = TuplesKt.to(new Pair(nodleTestnetToken.getAddress(), Long.valueOf(ChainTypeKt.getChainId(ChainType.ZKSYNC_TESTNET))), nodleTestnetToken);
        NodlePolkadotToken nodlePolkadotToken = NodlePolkadotToken.INSTANCE;
        tokensMap = MapsKt.mapOf(pair, pair2, TuplesKt.to(new Pair(nodlePolkadotToken.getAddress(), Long.valueOf(ChainTypeKt.getChainId(ChainType.POLKADOT_MAINNET))), nodlePolkadotToken), TuplesKt.to(new Pair(nodlePolkadotToken.getAddress(), Long.valueOf(ChainTypeKt.getChainId(ChainType.POLKADOT_TESTNET))), nodlePolkadotToken), TuplesKt.to(new Pair(ethToken.getAddress(), Long.valueOf(ChainTypeKt.getChainId(chainType))), ethToken), TuplesKt.to(new Pair(zkToken.getAddress(), Long.valueOf(ChainTypeKt.getChainId(chainType))), zkToken), TuplesKt.to(new Pair(uSDCToken.getAddress(), Long.valueOf(ChainTypeKt.getChainId(chainType))), uSDCToken));
    }

    public final boolean containsToken(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, "tokenAddress");
        return tokensMap.containsKey(new Pair(str, Long.valueOf(j2)));
    }

    @NotNull
    public final List<GenericToken> getAllSupportedTokens() {
        return CollectionsKt.plus(CollectionsKt.listOf(NodleToken.INSTANCE), ZKSYNC_SUPPORTED_TOKENS);
    }

    @NotNull
    public final List<GenericToken> getSupportedTokens(@NotNull ChainType chainType) {
        Intrinsics.checkNotNullParameter(chainType, "chainType");
        int i3 = WhenMappings.$EnumSwitchMapping$0[chainType.ordinal()];
        if (i3 == 1 || i3 == 2) {
            return CollectionsKt.listOf(NodlePolkadotToken.INSTANCE);
        }
        if (i3 == 3) {
            return CollectionsKt.plus(CollectionsKt.listOf(NodleToken.INSTANCE), ZKSYNC_SUPPORTED_TOKENS);
        }
        if (i3 == 4) {
            return CollectionsKt.plus(CollectionsKt.listOf(NodleTestnetToken.INSTANCE), ZKSYNC_SUPPORTED_TOKENS);
        }
        throw new NoWhenBranchMatchedException();
    }

    @Nullable
    public final GenericToken getToken(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, "tokenAddress");
        return tokensMap.get(new Pair(str, Long.valueOf(j2)));
    }
}
