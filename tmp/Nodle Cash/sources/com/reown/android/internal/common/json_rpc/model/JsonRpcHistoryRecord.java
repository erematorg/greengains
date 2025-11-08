package com.reown.android.internal.common.json_rpc.model;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\nHÆ\u0003JI\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/reown/android/internal/common/json_rpc/model/JsonRpcHistoryRecord;", "", "id", "", "topic", "", "method", "body", "response", "transportType", "Lcom/reown/android/internal/common/model/TransportType;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/TransportType;)V", "getId", "()J", "getTopic", "()Ljava/lang/String;", "getMethod", "getBody", "getResponse", "getTransportType", "()Lcom/reown/android/internal/common/model/TransportType;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JsonRpcHistoryRecord {
    @NotNull
    private final String body;
    private final long id;
    @NotNull
    private final String method;
    @Nullable
    private final String response;
    @NotNull
    private final String topic;
    @Nullable
    private final TransportType transportType;

    public JsonRpcHistoryRecord(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable TransportType transportType2) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "body");
        this.id = j2;
        this.topic = str;
        this.method = str2;
        this.body = str3;
        this.response = str4;
        this.transportType = transportType2;
    }

    public static /* synthetic */ JsonRpcHistoryRecord copy$default(JsonRpcHistoryRecord jsonRpcHistoryRecord, long j2, String str, String str2, String str3, String str4, TransportType transportType2, int i3, Object obj) {
        JsonRpcHistoryRecord jsonRpcHistoryRecord2 = jsonRpcHistoryRecord;
        return jsonRpcHistoryRecord.copy((i3 & 1) != 0 ? jsonRpcHistoryRecord2.id : j2, (i3 & 2) != 0 ? jsonRpcHistoryRecord2.topic : str, (i3 & 4) != 0 ? jsonRpcHistoryRecord2.method : str2, (i3 & 8) != 0 ? jsonRpcHistoryRecord2.body : str3, (i3 & 16) != 0 ? jsonRpcHistoryRecord2.response : str4, (i3 & 32) != 0 ? jsonRpcHistoryRecord2.transportType : transportType2);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.topic;
    }

    @NotNull
    public final String component3() {
        return this.method;
    }

    @NotNull
    public final String component4() {
        return this.body;
    }

    @Nullable
    public final String component5() {
        return this.response;
    }

    @Nullable
    public final TransportType component6() {
        return this.transportType;
    }

    @NotNull
    public final JsonRpcHistoryRecord copy(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable TransportType transportType2) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "body");
        return new JsonRpcHistoryRecord(j2, str, str2, str3, str4, transportType2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JsonRpcHistoryRecord)) {
            return false;
        }
        JsonRpcHistoryRecord jsonRpcHistoryRecord = (JsonRpcHistoryRecord) obj;
        return this.id == jsonRpcHistoryRecord.id && Intrinsics.areEqual((Object) this.topic, (Object) jsonRpcHistoryRecord.topic) && Intrinsics.areEqual((Object) this.method, (Object) jsonRpcHistoryRecord.method) && Intrinsics.areEqual((Object) this.body, (Object) jsonRpcHistoryRecord.body) && Intrinsics.areEqual((Object) this.response, (Object) jsonRpcHistoryRecord.response) && this.transportType == jsonRpcHistoryRecord.transportType;
    }

    @NotNull
    public final String getBody() {
        return this.body;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    @Nullable
    public final String getResponse() {
        return this.response;
    }

    @NotNull
    public final String getTopic() {
        return this.topic;
    }

    @Nullable
    public final TransportType getTransportType() {
        return this.transportType;
    }

    public int hashCode() {
        int i3 = a.i(this.body, a.i(this.method, a.i(this.topic, Long.hashCode(this.id) * 31, 31), 31), 31);
        String str = this.response;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        TransportType transportType2 = this.transportType;
        if (transportType2 != null) {
            i4 = transportType2.hashCode();
        }
        return hashCode + i4;
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        String str = this.topic;
        String str2 = this.method;
        String str3 = this.body;
        String str4 = this.response;
        TransportType transportType2 = this.transportType;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "JsonRpcHistoryRecord(id=", ", topic=", str);
        b.w(v2, ", method=", str2, ", body=", str3);
        v2.append(", response=");
        v2.append(str4);
        v2.append(", transportType=");
        v2.append(transportType2);
        v2.append(")");
        return v2.toString();
    }
}
