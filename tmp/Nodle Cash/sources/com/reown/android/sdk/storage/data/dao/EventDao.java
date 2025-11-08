package com.reown.android.sdk.storage.data.dao;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import app.cash.sqldelight.ColumnAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u00015Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bHÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010.J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0015¨\u00066"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/EventDao;", "", "event_id", "", "bundle_id", "", "timestamp", "event_name", "type", "topic", "trace", "", "correlation_id", "client_id", "direction", "user_agent", "<init>", "(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEvent_id", "()J", "getBundle_id", "()Ljava/lang/String;", "getTimestamp", "getEvent_name", "getType", "getTopic", "getTrace", "()Ljava/util/List;", "getCorrelation_id", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getClient_id", "getDirection", "getUser_agent", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/reown/android/sdk/storage/data/dao/EventDao;", "equals", "", "other", "hashCode", "", "toString", "Adapter", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EventDao {
    @NotNull
    private final String bundle_id;
    @Nullable
    private final String client_id;
    @Nullable
    private final Long correlation_id;
    @Nullable
    private final String direction;
    private final long event_id;
    @NotNull
    private final String event_name;
    private final long timestamp;
    @Nullable
    private final String topic;
    @Nullable
    private final List<String> trace;
    @NotNull
    private final String type;
    @Nullable
    private final String user_agent;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0018\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R#\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/EventDao$Adapter;", "", "traceAdapter", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "<init>", "(Lapp/cash/sqldelight/ColumnAdapter;)V", "getTraceAdapter", "()Lapp/cash/sqldelight/ColumnAdapter;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Adapter {
        @NotNull
        private final ColumnAdapter<List<String>, String> traceAdapter;

        public Adapter(@NotNull ColumnAdapter<List<String>, String> columnAdapter) {
            Intrinsics.checkNotNullParameter(columnAdapter, "traceAdapter");
            this.traceAdapter = columnAdapter;
        }

        @NotNull
        public final ColumnAdapter<List<String>, String> getTraceAdapter() {
            return this.traceAdapter;
        }
    }

    public EventDao(long j2, @NotNull String str, long j3, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable List<String> list, @Nullable Long l2, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        Intrinsics.checkNotNullParameter(str, "bundle_id");
        Intrinsics.checkNotNullParameter(str2, "event_name");
        Intrinsics.checkNotNullParameter(str3, "type");
        this.event_id = j2;
        this.bundle_id = str;
        this.timestamp = j3;
        this.event_name = str2;
        this.type = str3;
        this.topic = str4;
        this.trace = list;
        this.correlation_id = l2;
        this.client_id = str5;
        this.direction = str6;
        this.user_agent = str7;
    }

    public static /* synthetic */ EventDao copy$default(EventDao eventDao, long j2, String str, long j3, String str2, String str3, String str4, List list, Long l2, String str5, String str6, String str7, int i3, Object obj) {
        EventDao eventDao2 = eventDao;
        int i4 = i3;
        return eventDao.copy((i4 & 1) != 0 ? eventDao2.event_id : j2, (i4 & 2) != 0 ? eventDao2.bundle_id : str, (i4 & 4) != 0 ? eventDao2.timestamp : j3, (i4 & 8) != 0 ? eventDao2.event_name : str2, (i4 & 16) != 0 ? eventDao2.type : str3, (i4 & 32) != 0 ? eventDao2.topic : str4, (i4 & 64) != 0 ? eventDao2.trace : list, (i4 & 128) != 0 ? eventDao2.correlation_id : l2, (i4 & 256) != 0 ? eventDao2.client_id : str5, (i4 & 512) != 0 ? eventDao2.direction : str6, (i4 & 1024) != 0 ? eventDao2.user_agent : str7);
    }

    public final long component1() {
        return this.event_id;
    }

    @Nullable
    public final String component10() {
        return this.direction;
    }

    @Nullable
    public final String component11() {
        return this.user_agent;
    }

    @NotNull
    public final String component2() {
        return this.bundle_id;
    }

    public final long component3() {
        return this.timestamp;
    }

    @NotNull
    public final String component4() {
        return this.event_name;
    }

    @NotNull
    public final String component5() {
        return this.type;
    }

    @Nullable
    public final String component6() {
        return this.topic;
    }

    @Nullable
    public final List<String> component7() {
        return this.trace;
    }

    @Nullable
    public final Long component8() {
        return this.correlation_id;
    }

    @Nullable
    public final String component9() {
        return this.client_id;
    }

    @NotNull
    public final EventDao copy(long j2, @NotNull String str, long j3, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable List<String> list, @Nullable Long l2, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, "bundle_id");
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "event_name");
        String str10 = str3;
        Intrinsics.checkNotNullParameter(str10, "type");
        return new EventDao(j2, str8, j3, str9, str10, str4, list, l2, str5, str6, str7);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EventDao)) {
            return false;
        }
        EventDao eventDao = (EventDao) obj;
        return this.event_id == eventDao.event_id && Intrinsics.areEqual((Object) this.bundle_id, (Object) eventDao.bundle_id) && this.timestamp == eventDao.timestamp && Intrinsics.areEqual((Object) this.event_name, (Object) eventDao.event_name) && Intrinsics.areEqual((Object) this.type, (Object) eventDao.type) && Intrinsics.areEqual((Object) this.topic, (Object) eventDao.topic) && Intrinsics.areEqual((Object) this.trace, (Object) eventDao.trace) && Intrinsics.areEqual((Object) this.correlation_id, (Object) eventDao.correlation_id) && Intrinsics.areEqual((Object) this.client_id, (Object) eventDao.client_id) && Intrinsics.areEqual((Object) this.direction, (Object) eventDao.direction) && Intrinsics.areEqual((Object) this.user_agent, (Object) eventDao.user_agent);
    }

    @NotNull
    public final String getBundle_id() {
        return this.bundle_id;
    }

    @Nullable
    public final String getClient_id() {
        return this.client_id;
    }

    @Nullable
    public final Long getCorrelation_id() {
        return this.correlation_id;
    }

    @Nullable
    public final String getDirection() {
        return this.direction;
    }

    public final long getEvent_id() {
        return this.event_id;
    }

    @NotNull
    public final String getEvent_name() {
        return this.event_name;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    @Nullable
    public final String getTopic() {
        return this.topic;
    }

    @Nullable
    public final List<String> getTrace() {
        return this.trace;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @Nullable
    public final String getUser_agent() {
        return this.user_agent;
    }

    public int hashCode() {
        int i3 = a.i(this.type, a.i(this.event_name, a.D(this.timestamp, a.i(this.bundle_id, Long.hashCode(this.event_id) * 31, 31), 31), 31), 31);
        String str = this.topic;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        List<String> list = this.trace;
        int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
        Long l2 = this.correlation_id;
        int hashCode3 = (hashCode2 + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str2 = this.client_id;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.direction;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.user_agent;
        if (str4 != null) {
            i4 = str4.hashCode();
        }
        return hashCode5 + i4;
    }

    @NotNull
    public String toString() {
        long j2 = this.event_id;
        String str = this.bundle_id;
        long j3 = this.timestamp;
        String str2 = this.event_name;
        String str3 = this.type;
        String str4 = this.topic;
        List<String> list = this.trace;
        Long l2 = this.correlation_id;
        String str5 = this.client_id;
        String str6 = this.direction;
        String str7 = this.user_agent;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "EventDao(event_id=", ", bundle_id=", str);
        v2.append(", timestamp=");
        v2.append(j3);
        v2.append(", event_name=");
        b.w(v2, str2, ", type=", str3, ", topic=");
        v2.append(str4);
        v2.append(", trace=");
        v2.append(list);
        v2.append(", correlation_id=");
        v2.append(l2);
        v2.append(", client_id=");
        v2.append(str5);
        v2.append(", direction=");
        return android.support.v4.media.session.a.r(v2, str6, ", user_agent=", str7, ")");
    }
}
