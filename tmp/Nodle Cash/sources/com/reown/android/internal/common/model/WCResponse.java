package com.reown.android.internal.common.model;

import androidx.compose.animation.core.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/reown/android/internal/common/model/WCResponse;", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "method", "", "response", "Lcom/reown/android/internal/common/JsonRpcResponse;", "params", "Lcom/reown/android/internal/common/model/type/ClientParams;", "<init>", "(Lcom/reown/foundation/common/model/Topic;Ljava/lang/String;Lcom/reown/android/internal/common/JsonRpcResponse;Lcom/reown/android/internal/common/model/type/ClientParams;)V", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getMethod", "()Ljava/lang/String;", "getResponse", "()Lcom/reown/android/internal/common/JsonRpcResponse;", "getParams", "()Lcom/reown/android/internal/common/model/type/ClientParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WCResponse {
    @NotNull
    private final String method;
    @NotNull
    private final ClientParams params;
    @NotNull
    private final JsonRpcResponse response;
    @NotNull
    private final Topic topic;

    public WCResponse(@NotNull Topic topic2, @NotNull String str, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull ClientParams clientParams) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(jsonRpcResponse, "response");
        Intrinsics.checkNotNullParameter(clientParams, "params");
        this.topic = topic2;
        this.method = str;
        this.response = jsonRpcResponse;
        this.params = clientParams;
    }

    public static /* synthetic */ WCResponse copy$default(WCResponse wCResponse, Topic topic2, String str, JsonRpcResponse jsonRpcResponse, ClientParams clientParams, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            topic2 = wCResponse.topic;
        }
        if ((i3 & 2) != 0) {
            str = wCResponse.method;
        }
        if ((i3 & 4) != 0) {
            jsonRpcResponse = wCResponse.response;
        }
        if ((i3 & 8) != 0) {
            clientParams = wCResponse.params;
        }
        return wCResponse.copy(topic2, str, jsonRpcResponse, clientParams);
    }

    @NotNull
    public final Topic component1() {
        return this.topic;
    }

    @NotNull
    public final String component2() {
        return this.method;
    }

    @NotNull
    public final JsonRpcResponse component3() {
        return this.response;
    }

    @NotNull
    public final ClientParams component4() {
        return this.params;
    }

    @NotNull
    public final WCResponse copy(@NotNull Topic topic2, @NotNull String str, @NotNull JsonRpcResponse jsonRpcResponse, @NotNull ClientParams clientParams) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(jsonRpcResponse, "response");
        Intrinsics.checkNotNullParameter(clientParams, "params");
        return new WCResponse(topic2, str, jsonRpcResponse, clientParams);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WCResponse)) {
            return false;
        }
        WCResponse wCResponse = (WCResponse) obj;
        return Intrinsics.areEqual((Object) this.topic, (Object) wCResponse.topic) && Intrinsics.areEqual((Object) this.method, (Object) wCResponse.method) && Intrinsics.areEqual((Object) this.response, (Object) wCResponse.response) && Intrinsics.areEqual((Object) this.params, (Object) wCResponse.params);
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    @NotNull
    public final ClientParams getParams() {
        return this.params;
    }

    @NotNull
    public final JsonRpcResponse getResponse() {
        return this.response;
    }

    @NotNull
    public final Topic getTopic() {
        return this.topic;
    }

    public int hashCode() {
        int i3 = a.i(this.method, this.topic.hashCode() * 31, 31);
        return this.params.hashCode() + ((this.response.hashCode() + i3) * 31);
    }

    @NotNull
    public String toString() {
        Topic topic2 = this.topic;
        String str = this.method;
        JsonRpcResponse jsonRpcResponse = this.response;
        ClientParams clientParams = this.params;
        return "WCResponse(topic=" + topic2 + ", method=" + str + ", response=" + jsonRpcResponse + ", params=" + clientParams + ")";
    }
}
