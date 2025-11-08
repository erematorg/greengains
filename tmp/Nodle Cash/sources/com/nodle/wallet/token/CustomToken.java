package com.nodle.wallet.token;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.abi.datatypes.Address;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\r\u0010\u001b\u001a\u00060\tj\u0002`\nHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003JK\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\f\b\u0002\u0010\b\u001a\u00060\tj\u0002`\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0006HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0007\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0018\u0010\b\u001a\u00060\tj\u0002`\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000f¨\u0006$"}, d2 = {"Lcom/nodle/wallet/token/CustomToken;", "Lcom/nodle/wallet/token/GenericToken;", "name", "", "symbol", "decimals", "", "address", "chainId", "", "Lcom/nodle/wallet/ChainId;", "iconUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;JLjava/lang/String;)V", "getName", "()Ljava/lang/String;", "getSymbol", "getDecimals", "()I", "getAddress", "getChainId", "()J", "getIconUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "wallet_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CustomToken extends GenericToken {
    @NotNull
    private final String address;
    private final long chainId;
    private final int decimals;
    @Nullable
    private final String iconUrl;
    @NotNull
    private final String name;
    @NotNull
    private final String symbol;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CustomToken(@NotNull String str, @NotNull String str2, int i3, @NotNull String str3, long j2, @Nullable String str4) {
        super(str, str2, i3, str3, j2, (DefaultConstructorMarker) null);
        String str5 = str3;
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "symbol");
        Intrinsics.checkNotNullParameter(str5, Address.TYPE_NAME);
        this.name = str;
        this.symbol = str2;
        this.decimals = i3;
        this.address = str5;
        this.chainId = j2;
        this.iconUrl = str4;
    }

    public static /* synthetic */ CustomToken copy$default(CustomToken customToken, String str, String str2, int i3, String str3, long j2, String str4, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = customToken.name;
        }
        if ((i4 & 2) != 0) {
            str2 = customToken.symbol;
        }
        String str5 = str2;
        if ((i4 & 4) != 0) {
            i3 = customToken.decimals;
        }
        int i5 = i3;
        if ((i4 & 8) != 0) {
            str3 = customToken.address;
        }
        String str6 = str3;
        if ((i4 & 16) != 0) {
            j2 = customToken.chainId;
        }
        long j3 = j2;
        if ((i4 & 32) != 0) {
            str4 = customToken.iconUrl;
        }
        return customToken.copy(str, str5, i5, str6, j3, str4);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.symbol;
    }

    public final int component3() {
        return this.decimals;
    }

    @NotNull
    public final String component4() {
        return this.address;
    }

    public final long component5() {
        return this.chainId;
    }

    @Nullable
    public final String component6() {
        return this.iconUrl;
    }

    @NotNull
    public final CustomToken copy(@NotNull String str, @NotNull String str2, int i3, @NotNull String str3, long j2, @Nullable String str4) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "symbol");
        Intrinsics.checkNotNullParameter(str3, Address.TYPE_NAME);
        return new CustomToken(str, str2, i3, str3, j2, str4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomToken)) {
            return false;
        }
        CustomToken customToken = (CustomToken) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) customToken.name) && Intrinsics.areEqual((Object) this.symbol, (Object) customToken.symbol) && this.decimals == customToken.decimals && Intrinsics.areEqual((Object) this.address, (Object) customToken.address) && this.chainId == customToken.chainId && Intrinsics.areEqual((Object) this.iconUrl, (Object) customToken.iconUrl);
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

    @Nullable
    public final String getIconUrl() {
        return this.iconUrl;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String getSymbol() {
        return this.symbol;
    }

    public int hashCode() {
        int D2 = a.D(this.chainId, a.i(this.address, a.c(this.decimals, a.i(this.symbol, this.name.hashCode() * 31, 31), 31), 31), 31);
        String str = this.iconUrl;
        return D2 + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.name;
        String str2 = this.symbol;
        int i3 = this.decimals;
        String str3 = this.address;
        long j2 = this.chainId;
        String str4 = this.iconUrl;
        StringBuilder l2 = C0118y.l("CustomToken(name=", str, ", symbol=", str2, ", decimals=");
        l2.append(i3);
        l2.append(", address=");
        l2.append(str3);
        l2.append(", chainId=");
        l2.append(j2);
        l2.append(", iconUrl=");
        l2.append(str4);
        l2.append(")");
        return l2.toString();
    }
}
