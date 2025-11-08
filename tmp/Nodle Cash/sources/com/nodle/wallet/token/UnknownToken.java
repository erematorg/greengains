package com.nodle.wallet.token;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.nodle.wallet.ChainType;
import com.nodle.wallet.ChainTypeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.abi.datatypes.Address;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\r\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J!\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/nodle/wallet/token/UnknownToken;", "Lcom/nodle/wallet/token/GenericToken;", "address", "", "chainId", "", "Lcom/nodle/wallet/ChainId;", "<init>", "(Ljava/lang/String;J)V", "getAddress", "()Ljava/lang/String;", "getChainId", "()J", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UnknownToken extends GenericToken {
    @NotNull
    private final String address;
    private final long chainId;

    public UnknownToken() {
        this((String) null, 0, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ UnknownToken copy$default(UnknownToken unknownToken, String str, long j2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = unknownToken.address;
        }
        if ((i3 & 2) != 0) {
            j2 = unknownToken.chainId;
        }
        return unknownToken.copy(str, j2);
    }

    @NotNull
    public final String component1() {
        return this.address;
    }

    public final long component2() {
        return this.chainId;
    }

    @NotNull
    public final UnknownToken copy(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        return new UnknownToken(str, j2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UnknownToken)) {
            return false;
        }
        UnknownToken unknownToken = (UnknownToken) obj;
        return Intrinsics.areEqual((Object) this.address, (Object) unknownToken.address) && this.chainId == unknownToken.chainId;
    }

    @NotNull
    public String getAddress() {
        return this.address;
    }

    public long getChainId() {
        return this.chainId;
    }

    public int hashCode() {
        return Long.hashCode(this.chainId) + (this.address.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder i3 = a.i(this.chainId, "UnknownToken(address=", this.address, ", chainId=");
        i3.append(")");
        return i3.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ UnknownToken(String str, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "UnknownToken" : str, (i3 & 2) != 0 ? ChainTypeKt.getChainId(ChainType.ZKSYNC_MAINNET) : j2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnknownToken(@NotNull String str, long j2) {
        super("", "", 18, str, j2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        this.address = str;
        this.chainId = j2;
    }
}
