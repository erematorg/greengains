package com.nodle.wallet.zksync.data.api.model;

import A.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.abi.datatypes.Address;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiRewardsEstimateFeeRequest;", "", "address", "", "<init>", "(Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiRewardsEstimateFeeRequest {
    @NotNull
    private final String address;

    public ApiRewardsEstimateFeeRequest(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        this.address = str;
    }

    public static /* synthetic */ ApiRewardsEstimateFeeRequest copy$default(ApiRewardsEstimateFeeRequest apiRewardsEstimateFeeRequest, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = apiRewardsEstimateFeeRequest.address;
        }
        return apiRewardsEstimateFeeRequest.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.address;
    }

    @NotNull
    public final ApiRewardsEstimateFeeRequest copy(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        return new ApiRewardsEstimateFeeRequest(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ApiRewardsEstimateFeeRequest) && Intrinsics.areEqual((Object) this.address, (Object) ((ApiRewardsEstimateFeeRequest) obj).address);
    }

    @NotNull
    public final String getAddress() {
        return this.address;
    }

    public int hashCode() {
        return this.address.hashCode();
    }

    @NotNull
    public String toString() {
        return a.l("ApiRewardsEstimateFeeRequest(address=", this.address, ")");
    }
}
