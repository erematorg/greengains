package com.reown.sign.storage.data.dao.namespace;

import G1.C0235a;
import app.cash.sqldelight.ColumnAdapter;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.p;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.sign.engine.use_case.calls.g;
import com.reown.sign.storage.data.dao.namespace.NamespaceDao;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\"#B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J±\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0001\u0010\u000e\u001a\u0001\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0017\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\n0\u000fJ\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\t2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0010JX\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00102\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010 \u001a\u00020\rJ\u000e\u0010!\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "NamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/namespace/NamespaceDao$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/sign/storage/data/dao/namespace/NamespaceDao$Adapter;)V", "getNamespaces", "Lapp/cash/sqldelight/Query;", "T", "", "session_id", "", "mapper", "Lkotlin/Function5;", "", "Lkotlin/ParameterName;", "name", "key", "", "chains", "accounts", "methods", "events", "Lcom/reown/sign/storage/data/dao/namespace/GetNamespaces;", "isUpdateNamespaceRequestValid", "", "value", "topic", "insertOrAbortNamespace", "", "request_id", "deleteNamespacesByTopic", "GetNamespacesQuery", "IsUpdateNamespaceRequestValidQuery", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNamespaceDaoQueries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NamespaceDaoQueries.kt\ncom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,146:1\n1#2:147\n*E\n"})
public final class NamespaceDaoQueries extends TransacterImpl {
    @NotNull
    private final NamespaceDao.Adapter NamespaceDaoAdapter;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries$GetNamespacesQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "session_id", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;JLkotlin/jvm/functions/Function1;)V", "getSession_id", "()J", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetNamespacesQuery<T> extends Query<T> {
        private final long session_id;
        final /* synthetic */ NamespaceDaoQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetNamespacesQuery(NamespaceDaoQueries namespaceDaoQueries, @NotNull long j2, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = namespaceDaoQueries;
            this.session_id = j2;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetNamespacesQuery getNamespacesQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindLong(0, Long.valueOf(getNamespacesQuery.session_id));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"NamespaceDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(1467835014, "SELECT key, chains, accounts, methods, events\nFROM NamespaceDao\nWHERE session_id = ?", function1, 1, new b(this, 0));
        }

        public final long getSession_id() {
            return this.session_id;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"NamespaceDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "NamespaceDao.sq:getNamespaces";
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J.\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0017\"\u0004\b\u0001\u0010\u00182\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00170\tH\u0016J\b\u0010\u0019\u001a\u00020\u0007H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries$IsUpdateNamespaceRequestValidQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "value", "", "topic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;JLjava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getValue", "()J", "getTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class IsUpdateNamespaceRequestValidQuery<T> extends Query<T> {
        final /* synthetic */ NamespaceDaoQueries this$0;
        @NotNull
        private final String topic;
        private final long value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IsUpdateNamespaceRequestValidQuery(NamespaceDaoQueries namespaceDaoQueries, @NotNull long j2, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = namespaceDaoQueries;
            this.value = j2;
            this.topic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(IsUpdateNamespaceRequestValidQuery isUpdateNamespaceRequestValidQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindLong(0, Long.valueOf(isUpdateNamespaceRequestValidQuery.value));
            sqlPreparedStatement.bindString(1, isUpdateNamespaceRequestValidQuery.topic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"NamespaceDao", "SessionDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(1699224301, "SELECT ? >= (request_id / 1000)\nFROM NamespaceDao\nWHERE session_id = (\n    SELECT id\n    FROM SessionDao\n    WHERE topic = ?\n)", function1, 2, new b(this, 1));
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
            this.this$0.getDriver().removeListener(new String[]{"NamespaceDao", "SessionDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "NamespaceDao.sq:isUpdateNamespaceRequestValid";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NamespaceDaoQueries(@NotNull SqlDriver sqlDriver, @NotNull NamespaceDao.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "NamespaceDaoAdapter");
        this.NamespaceDaoAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteNamespacesByTopic$lambda$7(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteNamespacesByTopic$lambda$8(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("NamespaceDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object getNamespaces$lambda$1(Function5 function5, NamespaceDaoQueries namespaceDaoQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(1);
        List decode = string2 != null ? namespaceDaoQueries.NamespaceDaoAdapter.getChainsAdapter().decode(string2) : null;
        ColumnAdapter<List<String>, String> accountsAdapter = namespaceDaoQueries.NamespaceDaoAdapter.getAccountsAdapter();
        String string3 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string3);
        List<String> decode2 = accountsAdapter.decode(string3);
        ColumnAdapter<List<String>, String> methodsAdapter = namespaceDaoQueries.NamespaceDaoAdapter.getMethodsAdapter();
        String string4 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string4);
        List<String> decode3 = methodsAdapter.decode(string4);
        ColumnAdapter<List<String>, String> eventsAdapter = namespaceDaoQueries.NamespaceDaoAdapter.getEventsAdapter();
        String string5 = sqlCursor.getString(4);
        Intrinsics.checkNotNull(string5);
        return function5.invoke(string, decode, decode2, decode3, eventsAdapter.decode(string5));
    }

    /* access modifiers changed from: private */
    public static final GetNamespaces getNamespaces$lambda$2(String str, List list, List list2, List list3, List list4) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "accounts");
        Intrinsics.checkNotNullParameter(list3, "methods");
        Intrinsics.checkNotNullParameter(list4, "events");
        return new GetNamespaces(str, list, list2, list3, list4);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortNamespace$lambda$5(long j2, String str, List list, NamespaceDaoQueries namespaceDaoQueries, List list2, List list3, List list4, long j3, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        sqlPreparedStatement.bindString(1, str);
        sqlPreparedStatement.bindString(2, list != null ? namespaceDaoQueries.NamespaceDaoAdapter.getChainsAdapter().encode(list) : null);
        sqlPreparedStatement.bindString(3, namespaceDaoQueries.NamespaceDaoAdapter.getAccountsAdapter().encode(list2));
        sqlPreparedStatement.bindString(4, namespaceDaoQueries.NamespaceDaoAdapter.getMethodsAdapter().encode(list3));
        sqlPreparedStatement.bindString(5, namespaceDaoQueries.NamespaceDaoAdapter.getEventsAdapter().encode(list4));
        sqlPreparedStatement.bindLong(6, Long.valueOf(j3));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortNamespace$lambda$6(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("NamespaceDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final boolean isUpdateNamespaceRequestValid$lambda$3(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Boolean bool = sqlCursor.getBoolean(0);
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }

    public final void deleteNamespacesByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(1106900269, "DELETE FROM NamespaceDao\nWHERE session_id = (\n   SELECT id\n   FROM SessionDao\n   WHERE topic = ?\n)", 1, new b(str, 2));
        notifyQueries(1106900269, new g(9));
    }

    @NotNull
    public final <T> Query<T> getNamespaces(long j2, @NotNull Function5<? super String, ? super List<String>, ? super List<String>, ? super List<String>, ? super List<String>, ? extends T> function5) {
        Intrinsics.checkNotNullParameter(function5, "mapper");
        return new GetNamespacesQuery(this, j2, new C0235a(function5, this, 12));
    }

    public final void insertOrAbortNamespace(long j2, @NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4, long j3) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, JwtUtilsKt.DID_METHOD_KEY);
        List<String> list5 = list2;
        Intrinsics.checkNotNullParameter(list5, "accounts");
        List<String> list6 = list3;
        Intrinsics.checkNotNullParameter(list6, "methods");
        List<String> list7 = list4;
        Intrinsics.checkNotNullParameter(list7, "events");
        getDriver().execute(1896248463, "INSERT OR ABORT INTO NamespaceDao(session_id, key, chains, accounts, methods, events, request_id)\nVALUES (?, ?, ?,?,?, ?, ?)", 7, new a(j2, str2, list, this, list5, list6, list7, j3));
        notifyQueries(1896248463, new g(7));
    }

    @NotNull
    public final Query<Boolean> isUpdateNamespaceRequestValid(long j2, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return new IsUpdateNamespaceRequestValidQuery(this, j2, str, new g(8));
    }

    @NotNull
    public final Query<GetNamespaces> getNamespaces(long j2) {
        return getNamespaces(j2, new p(2));
    }
}
