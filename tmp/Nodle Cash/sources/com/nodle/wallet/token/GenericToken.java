package com.nodle.wallet.token;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B5\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0007\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0018\u0010\b\u001a\u00060\tj\u0002`\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u0001\b\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Lcom/nodle/wallet/token/GenericToken;", "Ljava/io/Serializable;", "name", "", "symbol", "decimals", "", "address", "chainId", "", "Lcom/nodle/wallet/ChainId;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;J)V", "getName", "()Ljava/lang/String;", "getSymbol", "getDecimals", "()I", "getAddress", "getChainId", "()J", "Lcom/nodle/wallet/token/CustomToken;", "Lcom/nodle/wallet/token/EthToken;", "Lcom/nodle/wallet/token/NodlePolkadotToken;", "Lcom/nodle/wallet/token/NodleTestnetToken;", "Lcom/nodle/wallet/token/NodleToken;", "Lcom/nodle/wallet/token/USDCToken;", "Lcom/nodle/wallet/token/UnknownToken;", "Lcom/nodle/wallet/token/ZkToken;", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class GenericToken implements Serializable {
    @NotNull
    private final String address;
    private final long chainId;
    private final int decimals;
    @NotNull
    private final String name;
    @NotNull
    private final String symbol;

    public /* synthetic */ GenericToken(String str, String str2, int i3, String str3, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i3, str3, j2);
    }

    @NotNull
    public String getAddress() {
        return this.address;
    }

    public long getChainId() {
        return this.chainId;
    }

    public int getDecimals() {
        return this.decimals;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String getSymbol() {
        return this.symbol;
    }

    private GenericToken(String str, String str2, int i3, String str3, long j2) {
        this.name = str;
        this.symbol = str2;
        this.decimals = i3;
        this.address = str3;
        this.chainId = j2;
    }
}
