package com.nodle.wallet.zksync.data.api.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest;", "", "feeTokenAddress", "", "gasLimit", "isTestnet", "", "checkNFT", "txData", "Lcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest$TxData;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZLcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest$TxData;)V", "getFeeTokenAddress", "()Ljava/lang/String;", "getGasLimit", "()Z", "getCheckNFT", "getTxData", "()Lcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest$TxData;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "TxData", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiZyfiPaymasterRequest {
    private final boolean checkNFT;
    @NotNull
    private final String feeTokenAddress;
    @NotNull
    private final String gasLimit;
    private final boolean isTestnet;
    @NotNull
    private final TxData txData;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest$TxData;", "", "from", "", "to", "value", "data", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "getTo", "getValue", "getData", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class TxData {
        @NotNull
        private final String data;
        @NotNull
        private final String from;
        @NotNull
        private final String to;
        @NotNull
        private final String value;

        public TxData(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            Intrinsics.checkNotNullParameter(str, "from");
            Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
            Intrinsics.checkNotNullParameter(str3, "value");
            Intrinsics.checkNotNullParameter(str4, "data");
            this.from = str;
            this.to = str2;
            this.value = str3;
            this.data = str4;
        }

        public static /* synthetic */ TxData copy$default(TxData txData, String str, String str2, String str3, String str4, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = txData.from;
            }
            if ((i3 & 2) != 0) {
                str2 = txData.to;
            }
            if ((i3 & 4) != 0) {
                str3 = txData.value;
            }
            if ((i3 & 8) != 0) {
                str4 = txData.data;
            }
            return txData.copy(str, str2, str3, str4);
        }

        @NotNull
        public final String component1() {
            return this.from;
        }

        @NotNull
        public final String component2() {
            return this.to;
        }

        @NotNull
        public final String component3() {
            return this.value;
        }

        @NotNull
        public final String component4() {
            return this.data;
        }

        @NotNull
        public final TxData copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            Intrinsics.checkNotNullParameter(str, "from");
            Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
            Intrinsics.checkNotNullParameter(str3, "value");
            Intrinsics.checkNotNullParameter(str4, "data");
            return new TxData(str, str2, str3, str4);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TxData)) {
                return false;
            }
            TxData txData = (TxData) obj;
            return Intrinsics.areEqual((Object) this.from, (Object) txData.from) && Intrinsics.areEqual((Object) this.to, (Object) txData.to) && Intrinsics.areEqual((Object) this.value, (Object) txData.value) && Intrinsics.areEqual((Object) this.data, (Object) txData.data);
        }

        @NotNull
        public final String getData() {
            return this.data;
        }

        @NotNull
        public final String getFrom() {
            return this.from;
        }

        @NotNull
        public final String getTo() {
            return this.to;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.data.hashCode() + a.i(this.value, a.i(this.to, this.from.hashCode() * 31, 31), 31);
        }

        @NotNull
        public String toString() {
            String str = this.from;
            String str2 = this.to;
            return android.support.v4.media.session.a.r(C0118y.l("TxData(from=", str, ", to=", str2, ", value="), this.value, ", data=", this.data, ")");
        }
    }

    public ApiZyfiPaymasterRequest(@NotNull String str, @NotNull String str2, boolean z2, boolean z3, @NotNull TxData txData2) {
        Intrinsics.checkNotNullParameter(str, "feeTokenAddress");
        Intrinsics.checkNotNullParameter(str2, "gasLimit");
        Intrinsics.checkNotNullParameter(txData2, "txData");
        this.feeTokenAddress = str;
        this.gasLimit = str2;
        this.isTestnet = z2;
        this.checkNFT = z3;
        this.txData = txData2;
    }

    public static /* synthetic */ ApiZyfiPaymasterRequest copy$default(ApiZyfiPaymasterRequest apiZyfiPaymasterRequest, String str, String str2, boolean z2, boolean z3, TxData txData2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = apiZyfiPaymasterRequest.feeTokenAddress;
        }
        if ((i3 & 2) != 0) {
            str2 = apiZyfiPaymasterRequest.gasLimit;
        }
        String str3 = str2;
        if ((i3 & 4) != 0) {
            z2 = apiZyfiPaymasterRequest.isTestnet;
        }
        boolean z4 = z2;
        if ((i3 & 8) != 0) {
            z3 = apiZyfiPaymasterRequest.checkNFT;
        }
        boolean z5 = z3;
        if ((i3 & 16) != 0) {
            txData2 = apiZyfiPaymasterRequest.txData;
        }
        return apiZyfiPaymasterRequest.copy(str, str3, z4, z5, txData2);
    }

    @NotNull
    public final String component1() {
        return this.feeTokenAddress;
    }

    @NotNull
    public final String component2() {
        return this.gasLimit;
    }

    public final boolean component3() {
        return this.isTestnet;
    }

    public final boolean component4() {
        return this.checkNFT;
    }

    @NotNull
    public final TxData component5() {
        return this.txData;
    }

    @NotNull
    public final ApiZyfiPaymasterRequest copy(@NotNull String str, @NotNull String str2, boolean z2, boolean z3, @NotNull TxData txData2) {
        Intrinsics.checkNotNullParameter(str, "feeTokenAddress");
        Intrinsics.checkNotNullParameter(str2, "gasLimit");
        Intrinsics.checkNotNullParameter(txData2, "txData");
        return new ApiZyfiPaymasterRequest(str, str2, z2, z3, txData2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApiZyfiPaymasterRequest)) {
            return false;
        }
        ApiZyfiPaymasterRequest apiZyfiPaymasterRequest = (ApiZyfiPaymasterRequest) obj;
        return Intrinsics.areEqual((Object) this.feeTokenAddress, (Object) apiZyfiPaymasterRequest.feeTokenAddress) && Intrinsics.areEqual((Object) this.gasLimit, (Object) apiZyfiPaymasterRequest.gasLimit) && this.isTestnet == apiZyfiPaymasterRequest.isTestnet && this.checkNFT == apiZyfiPaymasterRequest.checkNFT && Intrinsics.areEqual((Object) this.txData, (Object) apiZyfiPaymasterRequest.txData);
    }

    public final boolean getCheckNFT() {
        return this.checkNFT;
    }

    @NotNull
    public final String getFeeTokenAddress() {
        return this.feeTokenAddress;
    }

    @NotNull
    public final String getGasLimit() {
        return this.gasLimit;
    }

    @NotNull
    public final TxData getTxData() {
        return this.txData;
    }

    public int hashCode() {
        return this.txData.hashCode() + android.support.v4.media.session.a.f(this.checkNFT, android.support.v4.media.session.a.f(this.isTestnet, a.i(this.gasLimit, this.feeTokenAddress.hashCode() * 31, 31), 31), 31);
    }

    public final boolean isTestnet() {
        return this.isTestnet;
    }

    @NotNull
    public String toString() {
        String str = this.feeTokenAddress;
        String str2 = this.gasLimit;
        boolean z2 = this.isTestnet;
        boolean z3 = this.checkNFT;
        TxData txData2 = this.txData;
        StringBuilder l2 = C0118y.l("ApiZyfiPaymasterRequest(feeTokenAddress=", str, ", gasLimit=", str2, ", isTestnet=");
        l2.append(z2);
        l2.append(", checkNFT=");
        l2.append(z3);
        l2.append(", txData=");
        l2.append(txData2);
        l2.append(")");
        return l2.toString();
    }
}
