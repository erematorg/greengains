package com.reown.android.internal.common.modal.data.network.model;

import com.squareup.moshi.Json;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/reown/android/internal/common/modal/data/network/model/GetAndroidDataDTO;", "", "count", "", "data", "", "Lcom/reown/android/internal/common/modal/data/network/model/WalletDataDTO;", "<init>", "(ILjava/util/List;)V", "getCount", "()I", "getData", "()Ljava/util/List;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetAndroidDataDTO {
    private final int count;
    @NotNull
    private final List<WalletDataDTO> data;

    public GetAndroidDataDTO(@Json(name = "count") int i3, @NotNull @Json(name = "data") List<WalletDataDTO> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        this.count = i3;
        this.data = list;
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final List<WalletDataDTO> getData() {
        return this.data;
    }
}
