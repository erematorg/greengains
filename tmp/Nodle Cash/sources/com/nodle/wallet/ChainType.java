package com.nodle.wallet;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lcom/nodle/wallet/ChainType;", "", "<init>", "(Ljava/lang/String;I)V", "POLKADOT_MAINNET", "POLKADOT_TESTNET", "ZKSYNC_MAINNET", "ZKSYNC_TESTNET", "Companion", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum ChainType {
    POLKADOT_MAINNET,
    POLKADOT_TESTNET,
    ZKSYNC_MAINNET,
    ZKSYNC_TESTNET;
    
    @NotNull
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\b¨\u0006\t"}, d2 = {"Lcom/nodle/wallet/ChainType$Companion;", "", "<init>", "()V", "fromChainId", "Lcom/nodle/wallet/ChainType;", "chainId", "", "Lcom/nodle/wallet/ChainId;", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final ChainType fromChainId(long j2) {
            if (j2 == -1) {
                return ChainType.POLKADOT_MAINNET;
            }
            if (j2 == -2) {
                return ChainType.POLKADOT_TESTNET;
            }
            if (j2 == 324) {
                return ChainType.ZKSYNC_MAINNET;
            }
            if (j2 == 300) {
                return ChainType.ZKSYNC_TESTNET;
            }
            return null;
        }

        private Companion() {
        }
    }

    static {
        ChainType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @NotNull
    public static EnumEntries<ChainType> getEntries() {
        return $ENTRIES;
    }
}
