package com.reown.android.internal.common.model.type;

import com.reown.android.internal.common.model.type.ClientParams;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/model/type/JsonRpcClientSync;", "T", "Lcom/reown/android/internal/common/model/type/ClientParams;", "Lcom/reown/android/internal/common/model/type/SerializableJsonRpc;", "id", "", "getId", "()J", "method", "", "getMethod", "()Ljava/lang/String;", "jsonrpc", "getJsonrpc", "params", "getParams", "()Lcom/reown/android/internal/common/model/type/ClientParams;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface JsonRpcClientSync<T extends ClientParams> extends SerializableJsonRpc {
    long getId();

    @NotNull
    String getJsonrpc();

    @NotNull
    String getMethod();

    @NotNull
    T getParams();
}
