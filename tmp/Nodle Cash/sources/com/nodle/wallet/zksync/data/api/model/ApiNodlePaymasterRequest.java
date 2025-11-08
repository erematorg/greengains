package com.nodle.wallet.zksync.data.api.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/model/ApiNodlePaymasterRequest;", "", "from", "", "to", "data", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "getTo", "getData", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiNodlePaymasterRequest {
    @NotNull
    private final String data;
    @NotNull
    private final String from;
    @NotNull
    private final String to;

    public ApiNodlePaymasterRequest(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(str, "from");
        Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
        Intrinsics.checkNotNullParameter(str3, "data");
        this.from = str;
        this.to = str2;
        this.data = str3;
    }

    public static /* synthetic */ ApiNodlePaymasterRequest copy$default(ApiNodlePaymasterRequest apiNodlePaymasterRequest, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = apiNodlePaymasterRequest.from;
        }
        if ((i3 & 2) != 0) {
            str2 = apiNodlePaymasterRequest.to;
        }
        if ((i3 & 4) != 0) {
            str3 = apiNodlePaymasterRequest.data;
        }
        return apiNodlePaymasterRequest.copy(str, str2, str3);
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
        return this.data;
    }

    @NotNull
    public final ApiNodlePaymasterRequest copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(str, "from");
        Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
        Intrinsics.checkNotNullParameter(str3, "data");
        return new ApiNodlePaymasterRequest(str, str2, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApiNodlePaymasterRequest)) {
            return false;
        }
        ApiNodlePaymasterRequest apiNodlePaymasterRequest = (ApiNodlePaymasterRequest) obj;
        return Intrinsics.areEqual((Object) this.from, (Object) apiNodlePaymasterRequest.from) && Intrinsics.areEqual((Object) this.to, (Object) apiNodlePaymasterRequest.to) && Intrinsics.areEqual((Object) this.data, (Object) apiNodlePaymasterRequest.data);
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

    public int hashCode() {
        return this.data.hashCode() + a.i(this.to, this.from.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        String str = this.from;
        String str2 = this.to;
        return A.a.n(C0118y.l("ApiNodlePaymasterRequest(from=", str, ", to=", str2, ", data="), this.data, ")");
    }
}
