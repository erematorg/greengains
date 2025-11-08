package com.nodle.wallet.zksync.data.api.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001:\u0003\u001b\u001c\u001dB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J5\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse;", "", "txData", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$TxDataWithPaymaster;", "gasLimit", "", "feeTokenAmount", "estimatedFinalFeeTokenAmount", "<init>", "(Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$TxDataWithPaymaster;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTxData", "()Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$TxDataWithPaymaster;", "getGasLimit", "()Ljava/lang/String;", "getFeeTokenAmount", "getEstimatedFinalFeeTokenAmount", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "TxDataWithPaymaster", "CustomData", "PaymasterParams", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiZkPaymasterResponse {
    @Nullable
    private final String estimatedFinalFeeTokenAmount;
    @Nullable
    private final String feeTokenAmount;
    @NotNull
    private final String gasLimit;
    @NotNull
    private final TxDataWithPaymaster txData;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$CustomData;", "", "paymasterParams", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$PaymasterParams;", "gasPerPubdata", "", "<init>", "(Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$PaymasterParams;J)V", "getPaymasterParams", "()Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$PaymasterParams;", "getGasPerPubdata", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CustomData {
        private final long gasPerPubdata;
        @NotNull
        private final PaymasterParams paymasterParams;

        public CustomData(@NotNull PaymasterParams paymasterParams2, long j2) {
            Intrinsics.checkNotNullParameter(paymasterParams2, "paymasterParams");
            this.paymasterParams = paymasterParams2;
            this.gasPerPubdata = j2;
        }

        public static /* synthetic */ CustomData copy$default(CustomData customData, PaymasterParams paymasterParams2, long j2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                paymasterParams2 = customData.paymasterParams;
            }
            if ((i3 & 2) != 0) {
                j2 = customData.gasPerPubdata;
            }
            return customData.copy(paymasterParams2, j2);
        }

        @NotNull
        public final PaymasterParams component1() {
            return this.paymasterParams;
        }

        public final long component2() {
            return this.gasPerPubdata;
        }

        @NotNull
        public final CustomData copy(@NotNull PaymasterParams paymasterParams2, long j2) {
            Intrinsics.checkNotNullParameter(paymasterParams2, "paymasterParams");
            return new CustomData(paymasterParams2, j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CustomData)) {
                return false;
            }
            CustomData customData = (CustomData) obj;
            return Intrinsics.areEqual((Object) this.paymasterParams, (Object) customData.paymasterParams) && this.gasPerPubdata == customData.gasPerPubdata;
        }

        public final long getGasPerPubdata() {
            return this.gasPerPubdata;
        }

        @NotNull
        public final PaymasterParams getPaymasterParams() {
            return this.paymasterParams;
        }

        public int hashCode() {
            return Long.hashCode(this.gasPerPubdata) + (this.paymasterParams.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            PaymasterParams paymasterParams2 = this.paymasterParams;
            long j2 = this.gasPerPubdata;
            return "CustomData(paymasterParams=" + paymasterParams2 + ", gasPerPubdata=" + j2 + ")";
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$PaymasterParams;", "", "paymaster", "", "paymasterInput", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPaymaster", "()Ljava/lang/String;", "getPaymasterInput", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PaymasterParams {
        @NotNull
        private final String paymaster;
        @NotNull
        private final String paymasterInput;

        public PaymasterParams(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "paymaster");
            Intrinsics.checkNotNullParameter(str2, "paymasterInput");
            this.paymaster = str;
            this.paymasterInput = str2;
        }

        public static /* synthetic */ PaymasterParams copy$default(PaymasterParams paymasterParams, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = paymasterParams.paymaster;
            }
            if ((i3 & 2) != 0) {
                str2 = paymasterParams.paymasterInput;
            }
            return paymasterParams.copy(str, str2);
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
        public final PaymasterParams copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "paymaster");
            Intrinsics.checkNotNullParameter(str2, "paymasterInput");
            return new PaymasterParams(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PaymasterParams)) {
                return false;
            }
            PaymasterParams paymasterParams = (PaymasterParams) obj;
            return Intrinsics.areEqual((Object) this.paymaster, (Object) paymasterParams.paymaster) && Intrinsics.areEqual((Object) this.paymasterInput, (Object) paymasterParams.paymasterInput);
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
            return this.paymasterInput.hashCode() + (this.paymaster.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("PaymasterParams(paymaster=", this.paymaster, ", paymasterInput=", this.paymasterInput, ")");
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$TxDataWithPaymaster;", "", "customData", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$CustomData;", "maxFeePerGas", "", "gasLimit", "", "<init>", "(Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$CustomData;Ljava/lang/String;J)V", "getCustomData", "()Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse$CustomData;", "getMaxFeePerGas", "()Ljava/lang/String;", "getGasLimit", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class TxDataWithPaymaster {
        @NotNull
        private final CustomData customData;
        private final long gasLimit;
        @NotNull
        private final String maxFeePerGas;

        public TxDataWithPaymaster(@NotNull CustomData customData2, @NotNull String str, long j2) {
            Intrinsics.checkNotNullParameter(customData2, "customData");
            Intrinsics.checkNotNullParameter(str, "maxFeePerGas");
            this.customData = customData2;
            this.maxFeePerGas = str;
            this.gasLimit = j2;
        }

        public static /* synthetic */ TxDataWithPaymaster copy$default(TxDataWithPaymaster txDataWithPaymaster, CustomData customData2, String str, long j2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                customData2 = txDataWithPaymaster.customData;
            }
            if ((i3 & 2) != 0) {
                str = txDataWithPaymaster.maxFeePerGas;
            }
            if ((i3 & 4) != 0) {
                j2 = txDataWithPaymaster.gasLimit;
            }
            return txDataWithPaymaster.copy(customData2, str, j2);
        }

        @NotNull
        public final CustomData component1() {
            return this.customData;
        }

        @NotNull
        public final String component2() {
            return this.maxFeePerGas;
        }

        public final long component3() {
            return this.gasLimit;
        }

        @NotNull
        public final TxDataWithPaymaster copy(@NotNull CustomData customData2, @NotNull String str, long j2) {
            Intrinsics.checkNotNullParameter(customData2, "customData");
            Intrinsics.checkNotNullParameter(str, "maxFeePerGas");
            return new TxDataWithPaymaster(customData2, str, j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TxDataWithPaymaster)) {
                return false;
            }
            TxDataWithPaymaster txDataWithPaymaster = (TxDataWithPaymaster) obj;
            return Intrinsics.areEqual((Object) this.customData, (Object) txDataWithPaymaster.customData) && Intrinsics.areEqual((Object) this.maxFeePerGas, (Object) txDataWithPaymaster.maxFeePerGas) && this.gasLimit == txDataWithPaymaster.gasLimit;
        }

        @NotNull
        public final CustomData getCustomData() {
            return this.customData;
        }

        public final long getGasLimit() {
            return this.gasLimit;
        }

        @NotNull
        public final String getMaxFeePerGas() {
            return this.maxFeePerGas;
        }

        public int hashCode() {
            return Long.hashCode(this.gasLimit) + a.i(this.maxFeePerGas, this.customData.hashCode() * 31, 31);
        }

        @NotNull
        public String toString() {
            CustomData customData2 = this.customData;
            String str = this.maxFeePerGas;
            long j2 = this.gasLimit;
            StringBuilder sb = new StringBuilder("TxDataWithPaymaster(customData=");
            sb.append(customData2);
            sb.append(", maxFeePerGas=");
            sb.append(str);
            sb.append(", gasLimit=");
            return android.support.v4.media.session.a.k(j2, ")", sb);
        }
    }

    public ApiZkPaymasterResponse(@NotNull TxDataWithPaymaster txDataWithPaymaster, @NotNull String str, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(txDataWithPaymaster, "txData");
        Intrinsics.checkNotNullParameter(str, "gasLimit");
        this.txData = txDataWithPaymaster;
        this.gasLimit = str;
        this.feeTokenAmount = str2;
        this.estimatedFinalFeeTokenAmount = str3;
    }

    public static /* synthetic */ ApiZkPaymasterResponse copy$default(ApiZkPaymasterResponse apiZkPaymasterResponse, TxDataWithPaymaster txDataWithPaymaster, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            txDataWithPaymaster = apiZkPaymasterResponse.txData;
        }
        if ((i3 & 2) != 0) {
            str = apiZkPaymasterResponse.gasLimit;
        }
        if ((i3 & 4) != 0) {
            str2 = apiZkPaymasterResponse.feeTokenAmount;
        }
        if ((i3 & 8) != 0) {
            str3 = apiZkPaymasterResponse.estimatedFinalFeeTokenAmount;
        }
        return apiZkPaymasterResponse.copy(txDataWithPaymaster, str, str2, str3);
    }

    @NotNull
    public final TxDataWithPaymaster component1() {
        return this.txData;
    }

    @NotNull
    public final String component2() {
        return this.gasLimit;
    }

    @Nullable
    public final String component3() {
        return this.feeTokenAmount;
    }

    @Nullable
    public final String component4() {
        return this.estimatedFinalFeeTokenAmount;
    }

    @NotNull
    public final ApiZkPaymasterResponse copy(@NotNull TxDataWithPaymaster txDataWithPaymaster, @NotNull String str, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(txDataWithPaymaster, "txData");
        Intrinsics.checkNotNullParameter(str, "gasLimit");
        return new ApiZkPaymasterResponse(txDataWithPaymaster, str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApiZkPaymasterResponse)) {
            return false;
        }
        ApiZkPaymasterResponse apiZkPaymasterResponse = (ApiZkPaymasterResponse) obj;
        return Intrinsics.areEqual((Object) this.txData, (Object) apiZkPaymasterResponse.txData) && Intrinsics.areEqual((Object) this.gasLimit, (Object) apiZkPaymasterResponse.gasLimit) && Intrinsics.areEqual((Object) this.feeTokenAmount, (Object) apiZkPaymasterResponse.feeTokenAmount) && Intrinsics.areEqual((Object) this.estimatedFinalFeeTokenAmount, (Object) apiZkPaymasterResponse.estimatedFinalFeeTokenAmount);
    }

    @Nullable
    public final String getEstimatedFinalFeeTokenAmount() {
        return this.estimatedFinalFeeTokenAmount;
    }

    @Nullable
    public final String getFeeTokenAmount() {
        return this.feeTokenAmount;
    }

    @NotNull
    public final String getGasLimit() {
        return this.gasLimit;
    }

    @NotNull
    public final TxDataWithPaymaster getTxData() {
        return this.txData;
    }

    public int hashCode() {
        int i3 = a.i(this.gasLimit, this.txData.hashCode() * 31, 31);
        String str = this.feeTokenAmount;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.estimatedFinalFeeTokenAmount;
        if (str2 != null) {
            i4 = str2.hashCode();
        }
        return hashCode + i4;
    }

    @NotNull
    public String toString() {
        TxDataWithPaymaster txDataWithPaymaster = this.txData;
        String str = this.gasLimit;
        String str2 = this.feeTokenAmount;
        String str3 = this.estimatedFinalFeeTokenAmount;
        StringBuilder sb = new StringBuilder("ApiZkPaymasterResponse(txData=");
        sb.append(txDataWithPaymaster);
        sb.append(", gasLimit=");
        sb.append(str);
        sb.append(", feeTokenAmount=");
        return android.support.v4.media.session.a.r(sb, str2, ", estimatedFinalFeeTokenAmount=", str3, ")");
    }
}
