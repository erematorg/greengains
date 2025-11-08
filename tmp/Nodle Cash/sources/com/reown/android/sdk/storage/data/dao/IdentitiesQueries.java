package com.reown.android.sdk.storage.data.dao;

import androidx.compose.material.C0140g;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.internal.common.di.b;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u001a\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bJC\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0007\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\t\u001a\u00020\b2#\u0010\r\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u000b0\u000eJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\u0006\u0010\t\u001a\u00020\bJ/\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0018J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\b¨\u0006\u001c"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "getAccountIdByIdentity", "Lapp/cash/sqldelight/Query;", "", "identity", "getCacaoPayloadByIdentity", "T", "", "mapper", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "cacao_payload", "Lcom/reown/android/sdk/storage/data/dao/GetCacaoPayloadByIdentity;", "insertOrAbortIdentity", "", "accountId", "is_owner", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "removeIdentity", "GetAccountIdByIdentityQuery", "GetCacaoPayloadByIdentityQuery", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IdentitiesQueries extends TransacterImpl {

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries$GetAccountIdByIdentityQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "identity", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getIdentity", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetAccountIdByIdentityQuery<T> extends Query<T> {
        @NotNull
        private final String identity;
        final /* synthetic */ IdentitiesQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetAccountIdByIdentityQuery(@NotNull IdentitiesQueries identitiesQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, "identity");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = identitiesQueries;
            this.identity = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetAccountIdByIdentityQuery getAccountIdByIdentityQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getAccountIdByIdentityQuery.identity);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"Identities"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-656635057, "SELECT accountId\nFROM Identities\nWHERE identity = ?", function1, 1, new c(this, 1));
        }

        @NotNull
        public final String getIdentity() {
            return this.identity;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"Identities"}, listener);
        }

        @NotNull
        public String toString() {
            return "Identities.sq:getAccountIdByIdentity";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries$GetCacaoPayloadByIdentityQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "identity", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getIdentity", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetCacaoPayloadByIdentityQuery<T> extends Query<T> {
        @NotNull
        private final String identity;
        final /* synthetic */ IdentitiesQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetCacaoPayloadByIdentityQuery(@NotNull IdentitiesQueries identitiesQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, "identity");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = identitiesQueries;
            this.identity = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetCacaoPayloadByIdentityQuery getCacaoPayloadByIdentityQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getCacaoPayloadByIdentityQuery.identity);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"Identities"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-345344034, "SELECT cacao_payload\nFROM Identities\nWHERE identity = ? AND is_owner = 1", function1, 1, new c(this, 2));
        }

        @NotNull
        public final String getIdentity() {
            return this.identity;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"Identities"}, listener);
        }

        @NotNull
        public String toString() {
            return "Identities.sq:getCacaoPayloadByIdentity";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IdentitiesQueries(@NotNull SqlDriver sqlDriver) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
    }

    /* access modifiers changed from: private */
    public static final String getAccountIdByIdentity$lambda$0(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        return string;
    }

    /* access modifiers changed from: private */
    public static final Object getCacaoPayloadByIdentity$lambda$1(Function1 function1, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        return function1.invoke(sqlCursor.getString(0));
    }

    /* access modifiers changed from: private */
    public static final GetCacaoPayloadByIdentity getCacaoPayloadByIdentity$lambda$2(String str) {
        return new GetCacaoPayloadByIdentity(str);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortIdentity$lambda$3(String str, String str2, String str3, Boolean bool, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindString(1, str2);
        sqlPreparedStatement.bindString(2, str3);
        sqlPreparedStatement.bindBoolean(3, bool);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortIdentity$lambda$4(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("Identities");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit removeIdentity$lambda$5(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit removeIdentity$lambda$6(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("Identities");
        return Unit.INSTANCE;
    }

    @NotNull
    public final Query<String> getAccountIdByIdentity(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "identity");
        return new GetAccountIdByIdentityQuery(this, str, new b(19));
    }

    @NotNull
    public final <T> Query<T> getCacaoPayloadByIdentity(@NotNull String str, @NotNull Function1<? super String, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(str, "identity");
        Intrinsics.checkNotNullParameter(function1, "mapper");
        return new GetCacaoPayloadByIdentityQuery(this, str, new c(function1, 12));
    }

    public final void insertOrAbortIdentity(@NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "identity");
        Intrinsics.checkNotNullParameter(str2, "accountId");
        getDriver().execute(1929998314, "INSERT OR ABORT INTO Identities(identity, accountId, cacao_payload, is_owner)\nVALUES (?, ?, ?, ?)", 4, new C0140g(8, (Object) str, (Object) str2, (Object) str3, (Object) bool));
        notifyQueries(1929998314, new b(22));
    }

    public final void removeIdentity(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "identity");
        getDriver().execute(-973529238, "DELETE FROM Identities\nWHERE identity = ?", 1, new d(str, 0));
        notifyQueries(-973529238, new b(21));
    }

    @NotNull
    public final Query<GetCacaoPayloadByIdentity> getCacaoPayloadByIdentity(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "identity");
        return getCacaoPayloadByIdentity(str, new b(20));
    }
}
