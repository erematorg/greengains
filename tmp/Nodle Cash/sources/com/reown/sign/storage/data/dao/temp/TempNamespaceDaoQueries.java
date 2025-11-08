package com.reown.sign.storage.data.dao.temp;

import G1.C0235a;
import androidx.room.support.b;
import app.cash.sqldelight.ColumnAdapter;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.sign.engine.use_case.calls.g;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDao;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002$%B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007JÆ\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2¥\u0001\u0010\u000e\u001a \u0001\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0015¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0017\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00130\u0015¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u0002H\n0\u000fJ\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\t2\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\rJ`\u0010\u001f\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00152\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u00152\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010!\u001a\u00020 2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\"\u001a\u00020 2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010#\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "TempNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao$Adapter;)V", "getTempNamespacesByRequestId", "Lapp/cash/sqldelight/Query;", "T", "", "request_id", "", "mapper", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", "session_id", "", "key", "", "chains", "accounts", "methods", "events", "Lcom/reown/sign/storage/data/dao/temp/GetTempNamespacesByRequestId;", "isUpdateNamespaceRequestValid", "", "topic", "value", "insertOrAbortNamespace", "", "markNamespaceAcknowledged", "deleteTempNamespacesByRequestId", "deleteTempNamespacesByTopic", "GetTempNamespacesByRequestIdQuery", "IsUpdateNamespaceRequestValidQuery", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTempNamespaceDaoQueries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TempNamespaceDaoQueries.kt\ncom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,169:1\n1#2:170\n*E\n"})
public final class TempNamespaceDaoQueries extends TransacterImpl {
    @NotNull
    private final TempNamespaceDao.Adapter TempNamespaceDaoAdapter;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries$GetTempNamespacesByRequestIdQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "request_id", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;JLkotlin/jvm/functions/Function1;)V", "getRequest_id", "()J", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetTempNamespacesByRequestIdQuery<T> extends Query<T> {
        private final long request_id;
        final /* synthetic */ TempNamespaceDaoQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetTempNamespacesByRequestIdQuery(TempNamespaceDaoQueries tempNamespaceDaoQueries, @NotNull long j2, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = tempNamespaceDaoQueries;
            this.request_id = j2;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetTempNamespacesByRequestIdQuery getTempNamespacesByRequestIdQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindLong(0, Long.valueOf(getTempNamespacesByRequestIdQuery.request_id));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"TempNamespaceDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-817201940, "SELECT session_id, key, chains, accounts, methods, events\nFROM TempNamespaceDao\nWHERE request_id = ?", function1, 1, new c(this, 0));
        }

        public final long getRequest_id() {
            return this.request_id;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"TempNamespaceDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "TempNamespaceDao.sq:getTempNamespacesByRequestId";
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J.\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0017\"\u0004\b\u0001\u0010\u00182\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00170\tH\u0016J\b\u0010\u0019\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries$IsUpdateNamespaceRequestValidQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "topic", "", "value", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;Ljava/lang/String;JLkotlin/jvm/functions/Function1;)V", "getTopic", "()Ljava/lang/String;", "getValue", "()J", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class IsUpdateNamespaceRequestValidQuery<T> extends Query<T> {
        final /* synthetic */ TempNamespaceDaoQueries this$0;
        @NotNull
        private final String topic;
        private final long value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IsUpdateNamespaceRequestValidQuery(@NotNull TempNamespaceDaoQueries tempNamespaceDaoQueries, String str, @NotNull long j2, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = tempNamespaceDaoQueries;
            this.topic = str;
            this.value = j2;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(IsUpdateNamespaceRequestValidQuery isUpdateNamespaceRequestValidQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, isUpdateNamespaceRequestValidQuery.topic);
            sqlPreparedStatement.bindLong(1, Long.valueOf(isUpdateNamespaceRequestValidQuery.value));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"TempNamespaceDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-790026502, "SELECT COUNT(*) = 0\nFROM TempNamespaceDao\nWHERE topic = ? AND request_id / 1000 >= ? AND isAcknowledged = 1", function1, 2, new c(this, 1));
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public final long getValue() {
            return this.value;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"TempNamespaceDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "TempNamespaceDao.sq:isUpdateNamespaceRequestValid";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TempNamespaceDaoQueries(@NotNull SqlDriver sqlDriver, @NotNull TempNamespaceDao.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "TempNamespaceDaoAdapter");
        this.TempNamespaceDaoAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteTempNamespacesByRequestId$lambda$10(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("TempNamespaceDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteTempNamespacesByRequestId$lambda$9(long j2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteTempNamespacesByTopic$lambda$11(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteTempNamespacesByTopic$lambda$12(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("TempNamespaceDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object getTempNamespacesByRequestId$lambda$1(Function6 function6, TempNamespaceDaoQueries tempNamespaceDaoQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(2);
        List decode = string2 != null ? tempNamespaceDaoQueries.TempNamespaceDaoAdapter.getChainsAdapter().decode(string2) : null;
        ColumnAdapter<List<String>, String> accountsAdapter = tempNamespaceDaoQueries.TempNamespaceDaoAdapter.getAccountsAdapter();
        String string3 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string3);
        List<String> decode2 = accountsAdapter.decode(string3);
        ColumnAdapter<List<String>, String> methodsAdapter = tempNamespaceDaoQueries.TempNamespaceDaoAdapter.getMethodsAdapter();
        String string4 = sqlCursor.getString(4);
        Intrinsics.checkNotNull(string4);
        List<String> decode3 = methodsAdapter.decode(string4);
        ColumnAdapter<List<String>, String> eventsAdapter = tempNamespaceDaoQueries.TempNamespaceDaoAdapter.getEventsAdapter();
        String string5 = sqlCursor.getString(5);
        Intrinsics.checkNotNull(string5);
        return function6.invoke(l2, string, decode, decode2, decode3, eventsAdapter.decode(string5));
    }

    /* access modifiers changed from: private */
    public static final GetTempNamespacesByRequestId getTempNamespacesByRequestId$lambda$2(long j2, String str, List list, List list2, List list3, List list4) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "accounts");
        Intrinsics.checkNotNullParameter(list3, "methods");
        Intrinsics.checkNotNullParameter(list4, "events");
        return new GetTempNamespacesByRequestId(j2, str, list, list2, list3, list4);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortNamespace$lambda$5(long j2, String str, String str2, List list, TempNamespaceDaoQueries tempNamespaceDaoQueries, List list2, List list3, List list4, long j3, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        sqlPreparedStatement.bindString(1, str);
        sqlPreparedStatement.bindString(2, str2);
        sqlPreparedStatement.bindString(3, list != null ? tempNamespaceDaoQueries.TempNamespaceDaoAdapter.getChainsAdapter().encode(list) : null);
        sqlPreparedStatement.bindString(4, tempNamespaceDaoQueries.TempNamespaceDaoAdapter.getAccountsAdapter().encode(list2));
        sqlPreparedStatement.bindString(5, tempNamespaceDaoQueries.TempNamespaceDaoAdapter.getMethodsAdapter().encode(list3));
        sqlPreparedStatement.bindString(6, tempNamespaceDaoQueries.TempNamespaceDaoAdapter.getEventsAdapter().encode(list4));
        sqlPreparedStatement.bindLong(7, Long.valueOf(j3));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortNamespace$lambda$6(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("TempNamespaceDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final boolean isUpdateNamespaceRequestValid$lambda$3(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Boolean bool = sqlCursor.getBoolean(0);
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }

    /* access modifiers changed from: private */
    public static final Unit markNamespaceAcknowledged$lambda$7(long j2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit markNamespaceAcknowledged$lambda$8(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("TempNamespaceDao");
        return Unit.INSTANCE;
    }

    public final void deleteTempNamespacesByRequestId(long j2) {
        getDriver().execute(1755505633, "DELETE FROM TempNamespaceDao\nWHERE request_id = ?", 1, new b(j2, 5));
        notifyQueries(1755505633, new b(1));
    }

    public final void deleteTempNamespacesByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(-1170956154, "DELETE FROM TempNamespaceDao\nWHERE topic = ?", 1, new c(str, 2));
        notifyQueries(-1170956154, new b(0));
    }

    @NotNull
    public final <T> Query<T> getTempNamespacesByRequestId(long j2, @NotNull Function6<? super Long, ? super String, ? super List<String>, ? super List<String>, ? super List<String>, ? super List<String>, ? extends T> function6) {
        Intrinsics.checkNotNullParameter(function6, "mapper");
        return new GetTempNamespacesByRequestIdQuery(this, j2, new C0235a(function6, this, 15));
    }

    public final void insertOrAbortNamespace(long j2, @NotNull String str, @NotNull String str2, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4, long j3) {
        String str3 = str;
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_TOPIC);
        String str4 = str2;
        Intrinsics.checkNotNullParameter(str4, JwtUtilsKt.DID_METHOD_KEY);
        List<String> list5 = list2;
        Intrinsics.checkNotNullParameter(list5, "accounts");
        List<String> list6 = list3;
        Intrinsics.checkNotNullParameter(list6, "methods");
        List<String> list7 = list4;
        Intrinsics.checkNotNullParameter(list7, "events");
        getDriver().execute(1383279906, "INSERT OR ABORT INTO TempNamespaceDao(session_id, topic, key, chains, accounts, methods, events, request_id)\nVALUES (?, ?, ?, ?, ?, ?, ?, ?)", 8, new a(j2, str3, str4, list, this, list5, list6, list7, j3));
        notifyQueries(1383279906, new g(29));
    }

    @NotNull
    public final Query<Boolean> isUpdateNamespaceRequestValid(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return new IsUpdateNamespaceRequestValidQuery(this, str, j2, new g(27));
    }

    public final void markNamespaceAcknowledged(long j2) {
        getDriver().execute(60190747, "UPDATE TempNamespaceDao\nSET isAcknowledged = 1\nWHERE request_id = ?", 1, new b(j2, 4));
        notifyQueries(60190747, new g(28));
    }

    @NotNull
    public final Query<GetTempNamespacesByRequestId> getTempNamespacesByRequestId(long j2) {
        return getTempNamespacesByRequestId(j2, new com.reown.android.sdk.storage.data.dao.g(4));
    }
}
