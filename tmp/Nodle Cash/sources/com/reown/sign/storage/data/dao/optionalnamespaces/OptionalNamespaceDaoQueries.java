package com.reown.sign.storage.data.dao.optionalnamespaces;

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
import com.reown.android.sdk.storage.data.dao.h;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.sign.engine.use_case.calls.g;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDao;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2t\u0010\u000e\u001ap\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00100\u0014¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\n0\u000fJ\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00180\t2\u0006\u0010\f\u001a\u00020\rJB\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00102\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014J\u000e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0010J\u000e\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "OptionalNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDao$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDao$Adapter;)V", "getOptionalNamespaces", "Lapp/cash/sqldelight/Query;", "T", "", "session_id", "", "mapper", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", "key", "", "chains", "methods", "events", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/GetOptionalNamespaces;", "insertOrAbortOptionalNamespace", "", "deleteOptionalNamespacesByTopic", "topic", "deleteProposalNamespacesProposerKey", "proposer_key", "GetOptionalNamespacesQuery", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOptionalNamespaceDaoQueries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OptionalNamespaceDaoQueries.kt\ncom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,119:1\n1#2:120\n*E\n"})
public final class OptionalNamespaceDaoQueries extends TransacterImpl {
    @NotNull
    private final OptionalNamespaceDao.Adapter OptionalNamespaceDaoAdapter;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries$GetOptionalNamespacesQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "session_id", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;JLkotlin/jvm/functions/Function1;)V", "getSession_id", "()J", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetOptionalNamespacesQuery<T> extends Query<T> {
        private final long session_id;
        final /* synthetic */ OptionalNamespaceDaoQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetOptionalNamespacesQuery(OptionalNamespaceDaoQueries optionalNamespaceDaoQueries, @NotNull long j2, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = optionalNamespaceDaoQueries;
            this.session_id = j2;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetOptionalNamespacesQuery getOptionalNamespacesQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindLong(0, Long.valueOf(getOptionalNamespacesQuery.session_id));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"OptionalNamespaceDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-661152245, "SELECT key, chains, methods, events\nFROM OptionalNamespaceDao\nWHERE session_id = ?", function1, 1, new a(this, 2));
        }

        public final long getSession_id() {
            return this.session_id;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"OptionalNamespaceDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "OptionalNamespaceDao.sq:getOptionalNamespaces";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OptionalNamespaceDaoQueries(@NotNull SqlDriver sqlDriver, @NotNull OptionalNamespaceDao.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "OptionalNamespaceDaoAdapter");
        this.OptionalNamespaceDaoAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteOptionalNamespacesByTopic$lambda$6(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteOptionalNamespacesByTopic$lambda$7(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("OptionalNamespaceDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteProposalNamespacesProposerKey$lambda$8(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteProposalNamespacesProposerKey$lambda$9(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("OptionalNamespaceDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object getOptionalNamespaces$lambda$1(Function4 function4, OptionalNamespaceDaoQueries optionalNamespaceDaoQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(1);
        List decode = string2 != null ? optionalNamespaceDaoQueries.OptionalNamespaceDaoAdapter.getChainsAdapter().decode(string2) : null;
        ColumnAdapter<List<String>, String> methodsAdapter = optionalNamespaceDaoQueries.OptionalNamespaceDaoAdapter.getMethodsAdapter();
        String string3 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string3);
        List<String> decode2 = methodsAdapter.decode(string3);
        ColumnAdapter<List<String>, String> eventsAdapter = optionalNamespaceDaoQueries.OptionalNamespaceDaoAdapter.getEventsAdapter();
        String string4 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string4);
        return function4.invoke(string, decode, decode2, eventsAdapter.decode(string4));
    }

    /* access modifiers changed from: private */
    public static final GetOptionalNamespaces getOptionalNamespaces$lambda$2(String str, List list, List list2, List list3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(list2, "methods");
        Intrinsics.checkNotNullParameter(list3, "events");
        return new GetOptionalNamespaces(str, list, list2, list3);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortOptionalNamespace$lambda$4(long j2, String str, List list, OptionalNamespaceDaoQueries optionalNamespaceDaoQueries, List list2, List list3, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        sqlPreparedStatement.bindString(1, str);
        sqlPreparedStatement.bindString(2, list != null ? optionalNamespaceDaoQueries.OptionalNamespaceDaoAdapter.getChainsAdapter().encode(list) : null);
        sqlPreparedStatement.bindString(3, optionalNamespaceDaoQueries.OptionalNamespaceDaoAdapter.getMethodsAdapter().encode(list2));
        sqlPreparedStatement.bindString(4, optionalNamespaceDaoQueries.OptionalNamespaceDaoAdapter.getEventsAdapter().encode(list3));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortOptionalNamespace$lambda$5(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("OptionalNamespaceDao");
        return Unit.INSTANCE;
    }

    public final void deleteOptionalNamespacesByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(-679620494, "DELETE FROM OptionalNamespaceDao\nWHERE session_id = (\n   SELECT id\n   FROM SessionDao\n   WHERE topic = ?\n)", 1, new a(str, 0));
        notifyQueries(-679620494, new g(10));
    }

    public final void deleteProposalNamespacesProposerKey(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "proposer_key");
        getDriver().execute(-1916409261, "DELETE FROM OptionalNamespaceDao\nWHERE session_id = (\n   SELECT request_id\n   FROM ProposalDao\n   WHERE proposer_key = ?\n)", 1, new a(str, 1));
        notifyQueries(-1916409261, new g(12));
    }

    @NotNull
    public final <T> Query<T> getOptionalNamespaces(long j2, @NotNull Function4<? super String, ? super List<String>, ? super List<String>, ? super List<String>, ? extends T> function4) {
        Intrinsics.checkNotNullParameter(function4, "mapper");
        return new GetOptionalNamespacesQuery(this, j2, new C0235a(function4, this, 13));
    }

    public final void insertOrAbortOptionalNamespace(long j2, @NotNull String str, @Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, JwtUtilsKt.DID_METHOD_KEY);
        List<String> list4 = list2;
        Intrinsics.checkNotNullParameter(list4, "methods");
        List<String> list5 = list3;
        Intrinsics.checkNotNullParameter(list5, "events");
        getDriver().execute(-1695916886, "INSERT OR ABORT INTO OptionalNamespaceDao(session_id, key, chains, methods, events)\nVALUES (?, ?, ?, ?, ?)", 5, new h(j2, str2, list, this, list4, list5, 1));
        notifyQueries(-1695916886, new g(11));
    }

    @NotNull
    public final Query<GetOptionalNamespaces> getOptionalNamespaces(long j2) {
        return getOptionalNamespaces(j2, new b(0));
    }
}
