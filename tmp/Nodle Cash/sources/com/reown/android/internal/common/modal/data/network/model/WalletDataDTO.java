package com.reown.android.internal.common.modal.data.network.model;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lcom/reown/android/internal/common/modal/data/network/model/WalletDataDTO;", "", "id", "", "appId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getAppId", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletDataDTO {
    @Nullable
    private final String appId;
    @NotNull
    private final String id;

    public WalletDataDTO(@NotNull @Json(name = "id") String str, @Nullable @Json(name = "android_app_id") String str2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        this.id = str;
        this.appId = str2;
    }

    @Nullable
    public final String getAppId() {
        return this.appId;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }
}
