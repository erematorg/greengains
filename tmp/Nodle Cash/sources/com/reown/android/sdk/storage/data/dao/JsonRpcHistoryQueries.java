package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.ExecutableQuery;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.di.b;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryDao;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0003)*+B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J°\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0001\u0010\u000e\u001a\u0001\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\n0\u000fJ\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\u0006\u0010\f\u001a\u00020\rJ°\u0001\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00122\u0001\u0010\u000e\u001a\u0001\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\n0\u000fJ\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\t2\u0006\u0010\u0013\u001a\u00020\u0012J¨\u0001\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0001\u0010\u000e\u001a\u0001\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\n0\u000fJ\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\tJ¨\u0001\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0001\u0010\u000e\u001a\u0001\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\n0\u000fJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\tJ\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\t2\u0006\u0010\f\u001a\u00020\rJ\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0#J0\u0010$\u001a\u00020%2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017J\u0018\u0010&\u001a\u00020%2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00122\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010'\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010(\u001a\u00020%2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "JsonRpcHistoryDaoAdapter", "Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryDao$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryDao$Adapter;)V", "getJsonRpcHistoryRecord", "Lapp/cash/sqldelight/Query;", "T", "", "request_id", "", "mapper", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", "", "topic", "method", "body", "response", "Lcom/reown/android/internal/common/model/TransportType;", "transport_type", "Lcom/reown/android/sdk/storage/data/dao/GetJsonRpcHistoryRecord;", "getJsonRpcRecordsByTopic", "Lcom/reown/android/sdk/storage/data/dao/GetJsonRpcRecordsByTopic;", "getJsonRpcRecords", "Lcom/reown/android/sdk/storage/data/dao/GetJsonRpcRecords;", "getPendingSessionRequests", "Lcom/reown/android/sdk/storage/data/dao/GetPendingSessionRequests;", "doesJsonRpcNotExist", "", "selectLastInsertedRowId", "Lapp/cash/sqldelight/ExecutableQuery;", "insertOrAbortJsonRpcHistory", "", "updateJsonRpcHistory", "deleteJsonRpcHistory", "deleteJsonRpcHistoryByRequestId", "GetJsonRpcHistoryRecordQuery", "GetJsonRpcRecordsByTopicQuery", "DoesJsonRpcNotExistQuery", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsonRpcHistoryQueries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonRpcHistoryQueries.kt\ncom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,296:1\n1#2:297\n*E\n"})
public final class JsonRpcHistoryQueries extends TransacterImpl {
    @NotNull
    private final JsonRpcHistoryDao.Adapter JsonRpcHistoryDaoAdapter;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries$DoesJsonRpcNotExistQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "request_id", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;JLkotlin/jvm/functions/Function1;)V", "getRequest_id", "()J", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class DoesJsonRpcNotExistQuery<T> extends Query<T> {
        private final long request_id;
        final /* synthetic */ JsonRpcHistoryQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DoesJsonRpcNotExistQuery(JsonRpcHistoryQueries jsonRpcHistoryQueries, @NotNull long j2, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = jsonRpcHistoryQueries;
            this.request_id = j2;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(DoesJsonRpcNotExistQuery doesJsonRpcNotExistQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindLong(0, Long.valueOf(doesJsonRpcNotExistQuery.request_id));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"JsonRpcHistoryDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(495001253, "SELECT NOT EXISTS (\n    SELECT 1\n    FROM JsonRpcHistoryDao\n    WHERE request_id = ?\n    LIMIT 1\n)", function1, 1, new c(this, 3));
        }

        public final long getRequest_id() {
            return this.request_id;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"JsonRpcHistoryDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "JsonRpcHistory.sq:doesJsonRpcNotExist";
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries$GetJsonRpcHistoryRecordQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "request_id", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;JLkotlin/jvm/functions/Function1;)V", "getRequest_id", "()J", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetJsonRpcHistoryRecordQuery<T> extends Query<T> {
        private final long request_id;
        final /* synthetic */ JsonRpcHistoryQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetJsonRpcHistoryRecordQuery(JsonRpcHistoryQueries jsonRpcHistoryQueries, @NotNull long j2, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = jsonRpcHistoryQueries;
            this.request_id = j2;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetJsonRpcHistoryRecordQuery getJsonRpcHistoryRecordQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindLong(0, Long.valueOf(getJsonRpcHistoryRecordQuery.request_id));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"JsonRpcHistoryDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(1274335899, "SELECT request_id, topic, method, body, response, transport_type\nFROM JsonRpcHistoryDao\nWHERE  request_id = ?", function1, 1, new c(this, 4));
        }

        public final long getRequest_id() {
            return this.request_id;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"JsonRpcHistoryDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "JsonRpcHistory.sq:getJsonRpcHistoryRecord";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries$GetJsonRpcRecordsByTopicQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "topic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetJsonRpcRecordsByTopicQuery<T> extends Query<T> {
        final /* synthetic */ JsonRpcHistoryQueries this$0;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetJsonRpcRecordsByTopicQuery(@NotNull JsonRpcHistoryQueries jsonRpcHistoryQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = jsonRpcHistoryQueries;
            this.topic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetJsonRpcRecordsByTopicQuery getJsonRpcRecordsByTopicQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getJsonRpcRecordsByTopicQuery.topic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"JsonRpcHistoryDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(208828576, "SELECT request_id, topic, method, body, response, transport_type\nFROM JsonRpcHistoryDao\nWHERE topic = ?", function1, 1, new c(this, 5));
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"JsonRpcHistoryDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "JsonRpcHistory.sq:getJsonRpcRecordsByTopic";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonRpcHistoryQueries(@NotNull SqlDriver sqlDriver, @NotNull JsonRpcHistoryDao.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "JsonRpcHistoryDaoAdapter");
        this.JsonRpcHistoryDaoAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteJsonRpcHistory$lambda$19(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteJsonRpcHistory$lambda$20(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("JsonRpcHistoryDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteJsonRpcHistoryByRequestId$lambda$21(long j2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteJsonRpcHistoryByRequestId$lambda$22(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("JsonRpcHistoryDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final boolean doesJsonRpcNotExist$lambda$12(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Boolean bool = sqlCursor.getBoolean(0);
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }

    /* access modifiers changed from: private */
    public static final Object getJsonRpcHistoryRecord$lambda$1(Function6 function6, JsonRpcHistoryQueries jsonRpcHistoryQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string3);
        String string4 = sqlCursor.getString(4);
        String string5 = sqlCursor.getString(5);
        return function6.invoke(l2, string, string2, string3, string4, string5 != null ? jsonRpcHistoryQueries.JsonRpcHistoryDaoAdapter.getTransport_typeAdapter().decode(string5) : null);
    }

    /* access modifiers changed from: private */
    public static final GetJsonRpcHistoryRecord getJsonRpcHistoryRecord$lambda$2(long j2, String str, String str2, String str3, String str4, TransportType transportType) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "body");
        return new GetJsonRpcHistoryRecord(j2, str, str2, str3, str4, transportType);
    }

    /* access modifiers changed from: private */
    public static final Object getJsonRpcRecords$lambda$7(Function6 function6, JsonRpcHistoryQueries jsonRpcHistoryQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string3);
        String string4 = sqlCursor.getString(4);
        String string5 = sqlCursor.getString(5);
        return function6.invoke(l2, string, string2, string3, string4, string5 != null ? jsonRpcHistoryQueries.JsonRpcHistoryDaoAdapter.getTransport_typeAdapter().decode(string5) : null);
    }

    /* access modifiers changed from: private */
    public static final GetJsonRpcRecords getJsonRpcRecords$lambda$8(long j2, String str, String str2, String str3, String str4, TransportType transportType) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "body");
        return new GetJsonRpcRecords(j2, str, str2, str3, str4, transportType);
    }

    /* access modifiers changed from: private */
    public static final Object getJsonRpcRecordsByTopic$lambda$4(Function6 function6, JsonRpcHistoryQueries jsonRpcHistoryQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string3);
        String string4 = sqlCursor.getString(4);
        String string5 = sqlCursor.getString(5);
        return function6.invoke(l2, string, string2, string3, string4, string5 != null ? jsonRpcHistoryQueries.JsonRpcHistoryDaoAdapter.getTransport_typeAdapter().decode(string5) : null);
    }

    /* access modifiers changed from: private */
    public static final GetJsonRpcRecordsByTopic getJsonRpcRecordsByTopic$lambda$5(long j2, String str, String str2, String str3, String str4, TransportType transportType) {
        Intrinsics.checkNotNullParameter(str, "topic_");
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "body");
        return new GetJsonRpcRecordsByTopic(j2, str, str2, str3, str4, transportType);
    }

    /* access modifiers changed from: private */
    public static final Object getPendingSessionRequests$lambda$10(Function6 function6, JsonRpcHistoryQueries jsonRpcHistoryQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string3);
        String string4 = sqlCursor.getString(4);
        String string5 = sqlCursor.getString(5);
        return function6.invoke(l2, string, string2, string3, string4, string5 != null ? jsonRpcHistoryQueries.JsonRpcHistoryDaoAdapter.getTransport_typeAdapter().decode(string5) : null);
    }

    /* access modifiers changed from: private */
    public static final GetPendingSessionRequests getPendingSessionRequests$lambda$11(long j2, String str, String str2, String str3, String str4, TransportType transportType) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str3, "body");
        return new GetPendingSessionRequests(j2, str, str2, str3, str4, transportType);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortJsonRpcHistory$lambda$15(long j2, String str, String str2, String str3, TransportType transportType, JsonRpcHistoryQueries jsonRpcHistoryQueries, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        sqlPreparedStatement.bindString(1, str);
        sqlPreparedStatement.bindString(2, str2);
        sqlPreparedStatement.bindString(3, str3);
        sqlPreparedStatement.bindString(4, transportType != null ? jsonRpcHistoryQueries.JsonRpcHistoryDaoAdapter.getTransport_typeAdapter().encode(transportType) : null);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortJsonRpcHistory$lambda$16(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("JsonRpcHistoryDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final long selectLastInsertedRowId$lambda$13(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        return l2.longValue();
    }

    /* access modifiers changed from: private */
    public static final Unit updateJsonRpcHistory$lambda$17(String str, long j2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindLong(1, Long.valueOf(j2));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateJsonRpcHistory$lambda$18(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("JsonRpcHistoryDao");
        return Unit.INSTANCE;
    }

    public final void deleteJsonRpcHistory(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(280411301, "DELETE FROM JsonRpcHistoryDao\nWHERE topic = ?", 1, new d(str, 1));
        notifyQueries(280411301, new b(27));
    }

    public final void deleteJsonRpcHistoryByRequestId(long j2) {
        getDriver().execute(1727437710, "DELETE FROM JsonRpcHistoryDao\nWHERE request_id = ?", 1, new androidx.room.support.b(j2, 2));
        notifyQueries(1727437710, new b(28));
    }

    @NotNull
    public final Query<Boolean> doesJsonRpcNotExist(long j2) {
        return new DoesJsonRpcNotExistQuery(this, j2, new b(26));
    }

    @NotNull
    public final <T> Query<T> getJsonRpcHistoryRecord(long j2, @NotNull Function6<? super Long, ? super String, ? super String, ? super String, ? super String, ? super TransportType, ? extends T> function6) {
        Intrinsics.checkNotNullParameter(function6, "mapper");
        return new GetJsonRpcHistoryRecordQuery(this, j2, new e(function6, this, 2));
    }

    @NotNull
    public final <T> Query<T> getJsonRpcRecords(@NotNull Function6<? super Long, ? super String, ? super String, ? super String, ? super String, ? super TransportType, ? extends T> function6) {
        Intrinsics.checkNotNullParameter(function6, "mapper");
        return QueryKt.Query(-1374020776, new String[]{"JsonRpcHistoryDao"}, getDriver(), "JsonRpcHistory.sq", "getJsonRpcRecords", "SELECT request_id, topic, method, body, response, transport_type\nFROM JsonRpcHistoryDao", new e(function6, this, 0));
    }

    @NotNull
    public final <T> Query<T> getJsonRpcRecordsByTopic(@NotNull String str, @NotNull Function6<? super Long, ? super String, ? super String, ? super String, ? super String, ? super TransportType, ? extends T> function6) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function6, "mapper");
        return new GetJsonRpcRecordsByTopicQuery(this, str, new e(function6, this, 3));
    }

    @NotNull
    public final <T> Query<T> getPendingSessionRequests(@NotNull Function6<? super Long, ? super String, ? super String, ? super String, ? super String, ? super TransportType, ? extends T> function6) {
        Intrinsics.checkNotNullParameter(function6, "mapper");
        return QueryKt.Query(-488427882, new String[]{"JsonRpcHistoryDao"}, getDriver(), "JsonRpcHistory.sq", "getPendingSessionRequests", "SELECT request_id, topic, method, body, response, transport_type\nFROM JsonRpcHistoryDao\nWHERE method = \"wc_sessionRequest\" AND response IS NULL", new e(function6, this, 1));
    }

    public final void insertOrAbortJsonRpcHistory(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable TransportType transportType) {
        String str4 = str;
        Intrinsics.checkNotNullParameter(str4, PushMessagingService.KEY_TOPIC);
        String str5 = str2;
        Intrinsics.checkNotNullParameter(str5, FirebaseAnalytics.Param.METHOD);
        String str6 = str3;
        Intrinsics.checkNotNullParameter(str6, "body");
        getDriver().execute(-1670151256, "INSERT OR ABORT INTO JsonRpcHistoryDao (request_id, topic, method, body, transport_type)\nVALUES (?, ?, ?, ?, ?)", 5, new h(j2, str4, str5, str6, transportType, this, 0));
        notifyQueries(-1670151256, new b(25));
    }

    @NotNull
    public final ExecutableQuery<Long> selectLastInsertedRowId() {
        return QueryKt.Query(-1493166392, getDriver(), "JsonRpcHistory.sq", "selectLastInsertedRowId", "SELECT last_insert_rowid()", new b(24));
    }

    public final void updateJsonRpcHistory(@Nullable String str, long j2) {
        getDriver().execute(-510549181, "UPDATE JsonRpcHistoryDao\nSET response = ?\nWHERE request_id = ?", 2, new f(str, j2, 0));
        notifyQueries(-510549181, new b(23));
    }

    @NotNull
    public final Query<GetJsonRpcHistoryRecord> getJsonRpcHistoryRecord(long j2) {
        return getJsonRpcHistoryRecord(j2, new g(2));
    }

    @NotNull
    public final Query<GetJsonRpcRecords> getJsonRpcRecords() {
        return getJsonRpcRecords(new g(3));
    }

    @NotNull
    public final Query<GetJsonRpcRecordsByTopic> getJsonRpcRecordsByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return getJsonRpcRecordsByTopic(str, new g(1));
    }

    @NotNull
    public final Query<GetPendingSessionRequests> getPendingSessionRequests() {
        return getPendingSessionRequests(new g(0));
    }
}
