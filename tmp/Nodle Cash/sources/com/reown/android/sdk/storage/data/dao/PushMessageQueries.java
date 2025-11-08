package com.reown.android.sdk.storage.data.dao;

import androidx.camera.core.processing.concurrent.b;
import androidx.compose.material.pullrefresh.c;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005Jk\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b\u0000\u0010\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2K\u0010\f\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\b0\rJ\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00072\u0006\u0010\n\u001a\u00020\u000bJ&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u000b¨\u0006\u001c"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "getPushMessageById", "Lapp/cash/sqldelight/Query;", "T", "", "id", "", "mapper", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "topic", "blob", "Lcom/reown/android/sdk/storage/data/dao/GetPushMessageById;", "doesMessagesExistsByRequestId", "", "upsertMessage", "", "tag", "", "deleteMessageByTopic", "GetPushMessageByIdQuery", "DoesMessagesExistsByRequestIdQuery", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMessageQueries extends TransacterImpl {

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries$DoesMessagesExistsByRequestIdQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "id", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getId", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class DoesMessagesExistsByRequestIdQuery<T> extends Query<T> {
        @NotNull
        private final String id;
        final /* synthetic */ PushMessageQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DoesMessagesExistsByRequestIdQuery(@NotNull PushMessageQueries pushMessageQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = pushMessageQueries;
            this.id = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(DoesMessagesExistsByRequestIdQuery doesMessagesExistsByRequestIdQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, doesMessagesExistsByRequestIdQuery.id);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"PushMessage"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-318979531, "SELECT EXISTS(SELECT 1 FROM PushMessage WHERE id = ?)", function1, 1, new c(this, 8));
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"PushMessage"}, listener);
        }

        @NotNull
        public String toString() {
            return "PushMessage.sq:doesMessagesExistsByRequestId";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries$GetPushMessageByIdQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "id", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getId", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetPushMessageByIdQuery<T> extends Query<T> {
        @NotNull
        private final String id;
        final /* synthetic */ PushMessageQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetPushMessageByIdQuery(@NotNull PushMessageQueries pushMessageQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = pushMessageQueries;
            this.id = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetPushMessageByIdQuery getPushMessageByIdQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getPushMessageByIdQuery.id);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"PushMessage"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(426083206, "SELECT id, topic, blob\nFROM PushMessage\nWHERE id = ?", function1, 1, new c(this, 9));
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"PushMessage"}, listener);
        }

        @NotNull
        public String toString() {
            return "PushMessage.sq:getPushMessageById";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PushMessageQueries(@NotNull SqlDriver sqlDriver) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
    }

    /* access modifiers changed from: private */
    public static final Unit deleteMessageByTopic$lambda$5(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteMessageByTopic$lambda$6(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("PushMessage");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final boolean doesMessagesExistsByRequestId$lambda$2(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Boolean bool = sqlCursor.getBoolean(0);
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }

    /* access modifiers changed from: private */
    public static final Object getPushMessageById$lambda$0(Function3 function3, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string3);
        return function3.invoke(string, string2, string3);
    }

    /* access modifiers changed from: private */
    public static final GetPushMessageById getPushMessageById$lambda$1(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "id_");
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_BLOB);
        return new GetPushMessageById(str, str2, str3);
    }

    /* access modifiers changed from: private */
    public static final Unit upsertMessage$lambda$3(String str, String str2, String str3, long j2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindString(1, str2);
        sqlPreparedStatement.bindString(2, str3);
        sqlPreparedStatement.bindLong(3, Long.valueOf(j2));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit upsertMessage$lambda$4(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("PushMessage");
        return Unit.INSTANCE;
    }

    public final void deleteMessageByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(-542250727, "DELETE FROM PushMessage\nWHERE topic = ?", 1, new d(str, 4));
        notifyQueries(-542250727, new k(11));
    }

    @NotNull
    public final Query<Boolean> doesMessagesExistsByRequestId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        return new DoesMessagesExistsByRequestIdQuery(this, str, new k(9));
    }

    @NotNull
    public final <T> Query<T> getPushMessageById(@NotNull String str, @NotNull Function3<? super String, ? super String, ? super String, ? extends T> function3) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(function3, "mapper");
        return new GetPushMessageByIdQuery(this, str, new c(function3, 13));
    }

    public final void upsertMessage(@NotNull String str, @NotNull String str2, @NotNull String str3, long j2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_BLOB);
        getDriver().execute(-856915717, "INSERT OR REPLACE INTO PushMessage(id, topic, blob, tag)\nVALUES (?, ?, ?, ?)", 4, new c(str, str2, str3, j2));
        notifyQueries(-856915717, new k(10));
    }

    @NotNull
    public final Query<GetPushMessageById> getPushMessageById(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        return getPushMessageById(str, new b(2));
    }
}
