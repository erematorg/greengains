package com.reown.android.sdk.storage.data.dao;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import app.cash.sqldelight.ColumnAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001&BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u000bHÆ\u0003JS\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryDao;", "", "id", "", "request_id", "topic", "", "method", "body", "response", "transport_type", "Lcom/reown/android/internal/common/model/TransportType;", "<init>", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/internal/common/model/TransportType;)V", "getId", "()J", "getRequest_id", "getTopic", "()Ljava/lang/String;", "getMethod", "getBody", "getResponse", "getTransport_type", "()Lcom/reown/android/internal/common/model/TransportType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "Adapter", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JsonRpcHistoryDao {
    @NotNull
    private final String body;
    private final long id;
    @NotNull
    private final String method;
    private final long request_id;
    @Nullable
    private final String response;
    @NotNull
    private final String topic;
    @Nullable
    private final TransportType transport_type;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryDao$Adapter;", "", "transport_typeAdapter", "Lapp/cash/sqldelight/ColumnAdapter;", "Lcom/reown/android/internal/common/model/TransportType;", "", "<init>", "(Lapp/cash/sqldelight/ColumnAdapter;)V", "getTransport_typeAdapter", "()Lapp/cash/sqldelight/ColumnAdapter;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Adapter {
        @NotNull
        private final ColumnAdapter<TransportType, String> transport_typeAdapter;

        public Adapter(@NotNull ColumnAdapter<TransportType, String> columnAdapter) {
            Intrinsics.checkNotNullParameter(columnAdapter, "transport_typeAdapter");
            this.transport_typeAdapter = columnAdapter;
        }

        @NotNull
        public final ColumnAdapter<TransportType, String> getTransport_typeAdapter() {
            return this.transport_typeAdapter;
        }
    }

    public JsonRpcHistoryDao(long j2, long j3, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable TransportType transportType) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "body");
        this.id = j2;
        this.request_id = j3;
        this.topic = str;
        this.method = str2;
        this.body = str3;
        this.response = str4;
        this.transport_type = transportType;
    }

    public static /* synthetic */ JsonRpcHistoryDao copy$default(JsonRpcHistoryDao jsonRpcHistoryDao, long j2, long j3, String str, String str2, String str3, String str4, TransportType transportType, int i3, Object obj) {
        JsonRpcHistoryDao jsonRpcHistoryDao2 = jsonRpcHistoryDao;
        return jsonRpcHistoryDao.copy((i3 & 1) != 0 ? jsonRpcHistoryDao2.id : j2, (i3 & 2) != 0 ? jsonRpcHistoryDao2.request_id : j3, (i3 & 4) != 0 ? jsonRpcHistoryDao2.topic : str, (i3 & 8) != 0 ? jsonRpcHistoryDao2.method : str2, (i3 & 16) != 0 ? jsonRpcHistoryDao2.body : str3, (i3 & 32) != 0 ? jsonRpcHistoryDao2.response : str4, (i3 & 64) != 0 ? jsonRpcHistoryDao2.transport_type : transportType);
    }

    public final long component1() {
        return this.id;
    }

    public final long component2() {
        return this.request_id;
    }

    @NotNull
    public final String component3() {
        return this.topic;
    }

    @NotNull
    public final String component4() {
        return this.method;
    }

    @NotNull
    public final String component5() {
        return this.body;
    }

    @Nullable
    public final String component6() {
        return this.response;
    }

    @Nullable
    public final TransportType component7() {
        return this.transport_type;
    }

    @NotNull
    public final JsonRpcHistoryDao copy(long j2, long j3, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable TransportType transportType) {
        String str5 = str;
        Intrinsics.checkNotNullParameter(str5, PushMessagingService.KEY_TOPIC);
        String str6 = str2;
        Intrinsics.checkNotNullParameter(str6, FirebaseAnalytics.Param.METHOD);
        String str7 = str3;
        Intrinsics.checkNotNullParameter(str7, "body");
        return new JsonRpcHistoryDao(j2, j3, str5, str6, str7, str4, transportType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JsonRpcHistoryDao)) {
            return false;
        }
        JsonRpcHistoryDao jsonRpcHistoryDao = (JsonRpcHistoryDao) obj;
        return this.id == jsonRpcHistoryDao.id && this.request_id == jsonRpcHistoryDao.request_id && Intrinsics.areEqual((Object) this.topic, (Object) jsonRpcHistoryDao.topic) && Intrinsics.areEqual((Object) this.method, (Object) jsonRpcHistoryDao.method) && Intrinsics.areEqual((Object) this.body, (Object) jsonRpcHistoryDao.body) && Intrinsics.areEqual((Object) this.response, (Object) jsonRpcHistoryDao.response) && this.transport_type == jsonRpcHistoryDao.transport_type;
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

    public final long getRequest_id() {
        return this.request_id;
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
    public final TransportType getTransport_type() {
        return this.transport_type;
    }

    public int hashCode() {
        int i3 = a.i(this.body, a.i(this.method, a.i(this.topic, a.D(this.request_id, Long.hashCode(this.id) * 31, 31), 31), 31), 31);
        String str = this.response;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        TransportType transportType = this.transport_type;
        if (transportType != null) {
            i4 = transportType.hashCode();
        }
        return hashCode + i4;
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        long j3 = this.request_id;
        String str = this.topic;
        String str2 = this.method;
        String str3 = this.body;
        String str4 = this.response;
        TransportType transportType = this.transport_type;
        StringBuilder u3 = a.u("JsonRpcHistoryDao(id=", j2, ", request_id=");
        u3.append(j3);
        u3.append(", topic=");
        u3.append(str);
        b.w(u3, ", method=", str2, ", body=", str3);
        u3.append(", response=");
        u3.append(str4);
        u3.append(", transport_type=");
        u3.append(transportType);
        u3.append(")");
        return u3.toString();
    }
}
