package com.nodle.wallet.zksync.data;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011¨\u0006$"}, d2 = {"Lcom/nodle/wallet/zksync/data/ZkPaymasterData;", "", "paymaster", "", "paymasterInput", "gasLimit", "Ljava/math/BigInteger;", "maxFeePerGas", "gasPerPubData", "maxFeeInToken", "estimatedFeeInToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;Ljava/math/BigInteger;)V", "getPaymaster", "()Ljava/lang/String;", "getPaymasterInput", "getGasLimit", "()Ljava/math/BigInteger;", "getMaxFeePerGas", "getGasPerPubData", "getMaxFeeInToken", "getEstimatedFeeInToken", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ZkPaymasterData {
    @NotNull
    private final BigInteger estimatedFeeInToken;
    @NotNull
    private final BigInteger gasLimit;
    @NotNull
    private final BigInteger gasPerPubData;
    @NotNull
    private final BigInteger maxFeeInToken;
    @NotNull
    private final BigInteger maxFeePerGas;
    @NotNull
    private final String paymaster;
    @NotNull
    private final String paymasterInput;

    public ZkPaymasterData(@NotNull String str, @NotNull String str2, @NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2, @NotNull BigInteger bigInteger3, @NotNull BigInteger bigInteger4, @NotNull BigInteger bigInteger5) {
        Intrinsics.checkNotNullParameter(str, "paymaster");
        Intrinsics.checkNotNullParameter(str2, "paymasterInput");
        Intrinsics.checkNotNullParameter(bigInteger, "gasLimit");
        Intrinsics.checkNotNullParameter(bigInteger2, "maxFeePerGas");
        Intrinsics.checkNotNullParameter(bigInteger3, "gasPerPubData");
        Intrinsics.checkNotNullParameter(bigInteger4, "maxFeeInToken");
        Intrinsics.checkNotNullParameter(bigInteger5, "estimatedFeeInToken");
        this.paymaster = str;
        this.paymasterInput = str2;
        this.gasLimit = bigInteger;
        this.maxFeePerGas = bigInteger2;
        this.gasPerPubData = bigInteger3;
        this.maxFeeInToken = bigInteger4;
        this.estimatedFeeInToken = bigInteger5;
    }

    public static /* synthetic */ ZkPaymasterData copy$default(ZkPaymasterData zkPaymasterData, String str, String str2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = zkPaymasterData.paymaster;
        }
        if ((i3 & 2) != 0) {
            str2 = zkPaymasterData.paymasterInput;
        }
        String str3 = str2;
        if ((i3 & 4) != 0) {
            bigInteger = zkPaymasterData.gasLimit;
        }
        BigInteger bigInteger6 = bigInteger;
        if ((i3 & 8) != 0) {
            bigInteger2 = zkPaymasterData.maxFeePerGas;
        }
        BigInteger bigInteger7 = bigInteger2;
        if ((i3 & 16) != 0) {
            bigInteger3 = zkPaymasterData.gasPerPubData;
        }
        BigInteger bigInteger8 = bigInteger3;
        if ((i3 & 32) != 0) {
            bigInteger4 = zkPaymasterData.maxFeeInToken;
        }
        BigInteger bigInteger9 = bigInteger4;
        if ((i3 & 64) != 0) {
            bigInteger5 = zkPaymasterData.estimatedFeeInToken;
        }
        return zkPaymasterData.copy(str, str3, bigInteger6, bigInteger7, bigInteger8, bigInteger9, bigInteger5);
    }

    @NotNull
    public final String component1() {
        return this.paymaster;
    }

    @NotNull
    public final String component2() {
        return this.paymasterInput;
    }

    @NotNull
    public final BigInteger component3() {
        return this.gasLimit;
    }

    @NotNull
    public final BigInteger component4() {
        return this.maxFeePerGas;
    }

    @NotNull
    public final BigInteger component5() {
        return this.gasPerPubData;
    }

    @NotNull
    public final BigInteger component6() {
        return this.maxFeeInToken;
    }

    @NotNull
    public final BigInteger component7() {
        return this.estimatedFeeInToken;
    }

    @NotNull
    public final ZkPaymasterData copy(@NotNull String str, @NotNull String str2, @NotNull BigInteger bigInteger, @NotNull BigInteger bigInteger2, @NotNull BigInteger bigInteger3, @NotNull BigInteger bigInteger4, @NotNull BigInteger bigInteger5) {
        Intrinsics.checkNotNullParameter(str, "paymaster");
        Intrinsics.checkNotNullParameter(str2, "paymasterInput");
        Intrinsics.checkNotNullParameter(bigInteger, "gasLimit");
        Intrinsics.checkNotNullParameter(bigInteger2, "maxFeePerGas");
        Intrinsics.checkNotNullParameter(bigInteger3, "gasPerPubData");
        Intrinsics.checkNotNullParameter(bigInteger4, "maxFeeInToken");
        Intrinsics.checkNotNullParameter(bigInteger5, "estimatedFeeInToken");
        return new ZkPaymasterData(str, str2, bigInteger, bigInteger2, bigInteger3, bigInteger4, bigInteger5);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZkPaymasterData)) {
            return false;
        }
        ZkPaymasterData zkPaymasterData = (ZkPaymasterData) obj;
        return Intrinsics.areEqual((Object) this.paymaster, (Object) zkPaymasterData.paymaster) && Intrinsics.areEqual((Object) this.paymasterInput, (Object) zkPaymasterData.paymasterInput) && Intrinsics.areEqual((Object) this.gasLimit, (Object) zkPaymasterData.gasLimit) && Intrinsics.areEqual((Object) this.maxFeePerGas, (Object) zkPaymasterData.maxFeePerGas) && Intrinsics.areEqual((Object) this.gasPerPubData, (Object) zkPaymasterData.gasPerPubData) && Intrinsics.areEqual((Object) this.maxFeeInToken, (Object) zkPaymasterData.maxFeeInToken) && Intrinsics.areEqual((Object) this.estimatedFeeInToken, (Object) zkPaymasterData.estimatedFeeInToken);
    }

    @NotNull
    public final BigInteger getEstimatedFeeInToken() {
        return this.estimatedFeeInToken;
    }

    @NotNull
    public final BigInteger getGasLimit() {
        return this.gasLimit;
    }

    @NotNull
    public final BigInteger getGasPerPubData() {
        return this.gasPerPubData;
    }

    @NotNull
    public final BigInteger getMaxFeeInToken() {
        return this.maxFeeInToken;
    }

    @NotNull
    public final BigInteger getMaxFeePerGas() {
        return this.maxFeePerGas;
    }

    @NotNull
    public final String getPaymaster() {
        return this.paymaster;
    }

    @NotNull
    public final String getPaymasterInput() {
        return this.paymasterInput;
    }

    public int hashCode() {
        return this.estimatedFeeInToken.hashCode() + a.c(this.maxFeeInToken, a.c(this.gasPerPubData, a.c(this.maxFeePerGas, a.c(this.gasLimit, androidx.compose.animation.core.a.i(this.paymasterInput, this.paymaster.hashCode() * 31, 31), 31), 31), 31), 31);
    }

    @NotNull
    public String toString() {
        String str = this.paymaster;
        String str2 = this.paymasterInput;
        BigInteger bigInteger = this.gasLimit;
        BigInteger bigInteger2 = this.maxFeePerGas;
        BigInteger bigInteger3 = this.gasPerPubData;
        BigInteger bigInteger4 = this.maxFeeInToken;
        BigInteger bigInteger5 = this.estimatedFeeInToken;
        StringBuilder l2 = C0118y.l("ZkPaymasterData(paymaster=", str, ", paymasterInput=", str2, ", gasLimit=");
        l2.append(bigInteger);
        l2.append(", maxFeePerGas=");
        l2.append(bigInteger2);
        l2.append(", gasPerPubData=");
        l2.append(bigInteger3);
        l2.append(", maxFeeInToken=");
        l2.append(bigInteger4);
        l2.append(", estimatedFeeInToken=");
        l2.append(bigInteger5);
        l2.append(")");
        return l2.toString();
    }
}
