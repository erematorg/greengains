package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.internal.common.di.b;
import com.reown.android.sdk.storage.data.dao.EventDao;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001(B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J¯\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0002\u0010\u000f\u001a\u0002\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001b\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001c\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0015\u0012\u0013\u0018\u00010\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u0002H\n0\u0010J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020 0\t2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010!\u001a\u00020\rJw\u0010\"\u001a\u00020#2\b\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u00142\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010$J\u0014\u0010%\u001a\u00020#2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0&J\u0006\u0010'\u001a\u00020#R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/EventQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "EventDaoAdapter", "Lcom/reown/android/sdk/storage/data/dao/EventDao$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/android/sdk/storage/data/dao/EventDao$Adapter;)V", "getAllEventsWithLimitAndOffset", "Lapp/cash/sqldelight/Query;", "T", "", "value", "", "value_", "mapper", "Lkotlin/Function11;", "Lkotlin/ParameterName;", "name", "event_id", "", "bundle_id", "timestamp", "event_name", "type", "topic", "", "trace", "correlation_id", "client_id", "direction", "user_agent", "Lcom/reown/android/sdk/storage/data/dao/EventDao;", "value__", "insertOrAbort", "", "(Ljava/lang/Long;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "deleteByIds", "", "deleteAllTelemetry", "GetAllEventsWithLimitAndOffsetQuery", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEventQueries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventQueries.kt\ncom/reown/android/sdk/storage/data/dao/EventQueries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,153:1\n1#2:154\n1878#3,3:155\n*S KotlinDebug\n*F\n+ 1 EventQueries.kt\ncom/reown/android/sdk/storage/data/dao/EventQueries\n*L\n108#1:155,3\n*E\n"})
public final class EventQueries extends TransacterImpl {
    @NotNull
    private final EventDao.Adapter EventDaoAdapter;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J.\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0015\"\u0004\b\u0001\u0010\u00162\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u00150\bH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/EventQueries$GetAllEventsWithLimitAndOffsetQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "value", "", "value_", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/EventQueries;JJLkotlin/jvm/functions/Function1;)V", "getValue", "()J", "getValue_", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetAllEventsWithLimitAndOffsetQuery<T> extends Query<T> {
        final /* synthetic */ EventQueries this$0;
        private final long value;
        private final long value_;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetAllEventsWithLimitAndOffsetQuery(EventQueries eventQueries, long j2, @NotNull long j3, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = eventQueries;
            this.value = j2;
            this.value_ = j3;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetAllEventsWithLimitAndOffsetQuery getAllEventsWithLimitAndOffsetQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindLong(0, Long.valueOf(getAllEventsWithLimitAndOffsetQuery.value));
            sqlPreparedStatement.bindLong(1, Long.valueOf(getAllEventsWithLimitAndOffsetQuery.value_));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"EventDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(34532163, "SELECT event_id, bundle_id, timestamp, event_name, type, topic, trace, correlation_id, client_id, direction, user_agent\nFROM EventDao ed\nLIMIT ? OFFSET ?", function1, 2, new c(this, 0));
        }

        public final long getValue() {
            return this.value;
        }

        public final long getValue_() {
            return this.value_;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"EventDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "Event.sq:getAllEventsWithLimitAndOffset";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EventQueries(@NotNull SqlDriver sqlDriver, @NotNull EventDao.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "EventDaoAdapter");
        this.EventDaoAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteAllTelemetry$lambda$9(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("EventDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteByIds$lambda$7(Collection collection, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        int i3 = 0;
        for (Object next : collection) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            sqlPreparedStatement.bindLong(i3, Long.valueOf(((Number) next).longValue()));
            i3 = i4;
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteByIds$lambda$8(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("EventDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object getAllEventsWithLimitAndOffset$lambda$1(Function11 function11, EventQueries eventQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string);
        Long l3 = sqlCursor.getLong(2);
        Intrinsics.checkNotNull(l3);
        String string2 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor.getString(4);
        Intrinsics.checkNotNull(string3);
        String string4 = sqlCursor.getString(5);
        String string5 = sqlCursor.getString(6);
        return function11.invoke(l2, string, l3, string2, string3, string4, string5 != null ? eventQueries.EventDaoAdapter.getTraceAdapter().decode(string5) : null, sqlCursor.getLong(7), sqlCursor.getString(8), sqlCursor.getString(9), sqlCursor.getString(10));
    }

    /* access modifiers changed from: private */
    public static final EventDao getAllEventsWithLimitAndOffset$lambda$2(long j2, String str, long j3, String str2, String str3, String str4, List list, Long l2, String str5, String str6, String str7) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, "bundle_id");
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "event_name");
        String str10 = str3;
        Intrinsics.checkNotNullParameter(str10, "type");
        return new EventDao(j2, str8, j3, str9, str10, str4, list, l2, str5, str6, str7);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbort$lambda$4(Long l2, String str, long j2, String str2, String str3, String str4, List list, Long l3, String str5, String str6, String str7, EventQueries eventQueries, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, l2);
        sqlPreparedStatement.bindString(1, str);
        sqlPreparedStatement.bindLong(2, Long.valueOf(j2));
        sqlPreparedStatement.bindString(3, str2);
        sqlPreparedStatement.bindString(4, str3);
        sqlPreparedStatement.bindString(5, str4);
        sqlPreparedStatement.bindString(6, list != null ? eventQueries.EventDaoAdapter.getTraceAdapter().encode(list) : null);
        sqlPreparedStatement.bindLong(7, l3);
        sqlPreparedStatement.bindString(8, str5);
        sqlPreparedStatement.bindString(9, str6);
        sqlPreparedStatement.bindString(10, str7);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbort$lambda$5(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("EventDao");
        return Unit.INSTANCE;
    }

    public final void deleteAllTelemetry() {
        SqlDriver.DefaultImpls.execute$default(getDriver(), 1509225965, "DELETE FROM EventDao\nWHERE correlation_id IS NULL", 0, (Function1) null, 8, (Object) null);
        notifyQueries(1509225965, new b(17));
    }

    public final void deleteByIds(@NotNull Collection<Long> collection) {
        Intrinsics.checkNotNullParameter(collection, "event_id");
        String createArguments = createArguments(collection.size());
        SqlDriver driver = getDriver();
        driver.execute((Integer) null, StringsKt__IndentKt.trimMargin$default("\n        |DELETE FROM EventDao\n        |WHERE event_id IN " + createArguments + "\n        ", (String) null, 1, (Object) null), collection.size(), new c(collection, 11));
        notifyQueries(-301265492, new b(18));
    }

    @NotNull
    public final <T> Query<T> getAllEventsWithLimitAndOffset(long j2, long j3, @NotNull Function11<? super Long, ? super String, ? super Long, ? super String, ? super String, ? super String, ? super List<String>, ? super Long, ? super String, ? super String, ? super String, ? extends T> function11) {
        Intrinsics.checkNotNullParameter(function11, "mapper");
        return new GetAllEventsWithLimitAndOffsetQuery(this, j2, j3, new l(function11, this, 2));
    }

    public final void insertOrAbort(@Nullable Long l2, @NotNull String str, long j2, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable List<String> list, @Nullable Long l3, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, "bundle_id");
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "event_name");
        String str10 = str3;
        Intrinsics.checkNotNullParameter(str10, "type");
        SqlDriver driver = getDriver();
        a aVar = r1;
        a aVar2 = new a(l2, str8, j2, str9, str10, str4, list, l3, str5, str6, str7, this);
        driver.execute(679696970, "INSERT OR ABORT INTO EventDao(event_id, bundle_id, timestamp,  event_name, type, topic, trace, correlation_id, client_id, direction, user_agent)\nVALUES (?,?,?,?,?,?, ?, ?, ?, ?, ?)", 11, aVar);
        notifyQueries(679696970, new b(16));
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [kotlin.jvm.functions.Function11, java.lang.Object] */
    @NotNull
    public final Query<EventDao> getAllEventsWithLimitAndOffset(long j2, long j3) {
        return getAllEventsWithLimitAndOffset(j2, j3, new Object());
    }
}
