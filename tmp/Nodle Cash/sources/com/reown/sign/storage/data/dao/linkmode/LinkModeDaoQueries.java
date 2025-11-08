package com.reown.sign.storage.data.dao.linkmode;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.sign.engine.use_case.calls.g;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b¨\u0006\r"}, d2 = {"Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "isEnabled", "Lapp/cash/sqldelight/Query;", "", "app_link", "insertOrIgnore", "", "IsEnabledQuery", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LinkModeDaoQueries extends TransacterImpl {

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries$IsEnabledQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "app_link", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getApp_link", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class IsEnabledQuery<T> extends Query<T> {
        @NotNull
        private final String app_link;
        final /* synthetic */ LinkModeDaoQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IsEnabledQuery(@NotNull LinkModeDaoQueries linkModeDaoQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, "app_link");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = linkModeDaoQueries;
            this.app_link = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(IsEnabledQuery isEnabledQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, isEnabledQuery.app_link);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"LinkModeDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(1708459789, "SELECT app_link\nFROM LinkModeDao\nWHERE ? = app_link", function1, 1, new a(this, 0));
        }

        @NotNull
        public final String getApp_link() {
            return this.app_link;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"LinkModeDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "LinkModeDao.sq:isEnabled";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LinkModeDaoQueries(@NotNull SqlDriver sqlDriver) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrIgnore$lambda$1(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrIgnore$lambda$2(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("LinkModeDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String isEnabled$lambda$0(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        return string;
    }

    public final void insertOrIgnore(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "app_link");
        getDriver().execute(-446418728, "INSERT OR IGNORE INTO LinkModeDao (app_link)\nVALUES (?)", 1, new a(str, 1));
        notifyQueries(-446418728, new g(5));
    }

    @NotNull
    public final Query<String> isEnabled(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "app_link");
        return new IsEnabledQuery(this, str, new g(6));
    }
}
