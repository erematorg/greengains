package com.nodle.wallet.zksync.data.api.model;

import A.a;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiRewardsEstimateFeeResponse;", "", "gasLimit", "", "<init>", "(Ljava/lang/String;)V", "getGasLimit", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiRewardsEstimateFeeResponse {
    @SerializedName("gas_limit")
    @NotNull
    private final String gasLimit;

    public ApiRewardsEstimateFeeResponse(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "gasLimit");
        this.gasLimit = str;
    }

    public static /* synthetic */ ApiRewardsEstimateFeeResponse copy$default(ApiRewardsEstimateFeeResponse apiRewardsEstimateFeeResponse, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = apiRewardsEstimateFeeResponse.gasLimit;
        }
        return apiRewardsEstimateFeeResponse.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.gasLimit;
    }

    @NotNull
    public final ApiRewardsEstimateFeeResponse copy(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "gasLimit");
        return new ApiRewardsEstimateFeeResponse(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApiRewardsEstimateFeeResponse) && Intrinsics.areEqual((Object) this.gasLimit, (Object) ((ApiRewardsEstimateFeeResponse) obj).gasLimit);
    }

    @NotNull
    public final String getGasLimit() {
        return this.gasLimit;
    }

    public int hashCode() {
        return this.gasLimit.hashCode();
    }

    @NotNull
    public String toString() {
        return a.l("ApiRewardsEstimateFeeResponse(gasLimit=", this.gasLimit, ")");
    }
}
