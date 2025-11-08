package com.nodle.wallet.zksync.data.api.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.abi.datatypes.Address;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsRequest;", "", "address", "", "sequence", "", "signature", "<init>", "(Ljava/lang/String;JLjava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getSequence", "()J", "getSignature", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiZkRewardsRequest {
    @NotNull
    private final String address;
    private final long sequence;
    @NotNull
    private final String signature;

    public ApiZkRewardsRequest(@NotNull String str, long j2, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        Intrinsics.checkNotNullParameter(str2, "signature");
        this.address = str;
        this.sequence = j2;
        this.signature = str2;
    }

    public static /* synthetic */ ApiZkRewardsRequest copy$default(ApiZkRewardsRequest apiZkRewardsRequest, String str, long j2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = apiZkRewardsRequest.address;
        }
        if ((i3 & 2) != 0) {
            j2 = apiZkRewardsRequest.sequence;
        }
        if ((i3 & 4) != 0) {
            str2 = apiZkRewardsRequest.signature;
        }
        return apiZkRewardsRequest.copy(str, j2, str2);
    }

    @NotNull
    public final String component1() {
        return this.address;
    }

    public final long component2() {
        return this.sequence;
    }

    @NotNull
    public final String component3() {
        return this.signature;
    }

    @NotNull
    public final ApiZkRewardsRequest copy(@NotNull String str, long j2, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        Intrinsics.checkNotNullParameter(str2, "signature");
        return new ApiZkRewardsRequest(str, j2, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApiZkRewardsRequest)) {
            return false;
        }
        ApiZkRewardsRequest apiZkRewardsRequest = (ApiZkRewardsRequest) obj;
        return Intrinsics.areEqual((Object) this.address, (Object) apiZkRewardsRequest.address) && this.sequence == apiZkRewardsRequest.sequence && Intrinsics.areEqual((Object) this.signature, (Object) apiZkRewardsRequest.signature);
    }

    @NotNull
    public final String getAddress() {
        return this.address;
    }

    public final long getSequence() {
        return this.sequence;
    }

    @NotNull
    public final String getSignature() {
        return this.signature;
    }

    public int hashCode() {
        return this.signature.hashCode() + a.D(this.sequence, this.address.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        String str = this.address;
        long j2 = this.sequence;
        return C0118y.j(com.appsamurai.storyly.exoplayer2.core.source.chunk.a.i(j2, "ApiZkRewardsRequest(address=", str, ", sequence="), ", signature=", this.signature, ")");
    }
}
