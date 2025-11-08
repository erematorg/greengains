package com.reown.android.sdk.storage.data.dao;

import androidx.room.support.b;
import app.cash.sqldelight.ColumnAdapter;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.internal.common.model.Validation;
import com.reown.android.sdk.storage.data.dao.VerifyContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2w\u0010\u000e\u001as\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\n0\u000fJ\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00190\t2\u0006\u0010\f\u001a\u00020\rJ\u0001\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2w\u0010\u000e\u001as\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u0002H\n0\u000fJ\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\tJ7\u0010\u001b\u001a\u00020\u001c2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "VerifyContextAdapter", "Lcom/reown/android/sdk/storage/data/dao/VerifyContext$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/android/sdk/storage/data/dao/VerifyContext$Adapter;)V", "getVerifyContextById", "Lapp/cash/sqldelight/Query;", "T", "", "id", "", "mapper", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "", "origin", "Lcom/reown/android/internal/common/model/Validation;", "validation", "verify_url", "", "is_scam", "Lcom/reown/android/sdk/storage/data/dao/VerifyContext;", "geListOfVerifyContexts", "insertOrAbortVerifyContext", "", "(Ljava/lang/Long;Ljava/lang/String;Lcom/reown/android/internal/common/model/Validation;Ljava/lang/String;Ljava/lang/Boolean;)V", "deleteVerifyContext", "GetVerifyContextByIdQuery", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyContextQueries extends TransacterImpl {
    @NotNull
    private final VerifyContext.Adapter VerifyContextAdapter;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries$GetVerifyContextByIdQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "id", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries;JLkotlin/jvm/functions/Function1;)V", "getId", "()J", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetVerifyContextByIdQuery<T> extends Query<T> {
        private final long id;
        final /* synthetic */ VerifyContextQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetVerifyContextByIdQuery(VerifyContextQueries verifyContextQueries, @NotNull long j2, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = verifyContextQueries;
            this.id = j2;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetVerifyContextByIdQuery getVerifyContextByIdQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindLong(0, Long.valueOf(getVerifyContextByIdQuery.id));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"VerifyContext"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(1022112920, "SELECT id, origin, validation, verify_url, is_scam\nFROM VerifyContext\nWHERE id = ?", function1, 1, new c(this, 10));
        }

        public final long getId() {
            return this.id;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"VerifyContext"}, listener);
        }

        @NotNull
        public String toString() {
            return "VerifyContext.sq:getVerifyContextById";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerifyContextQueries(@NotNull SqlDriver sqlDriver, @NotNull VerifyContext.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "VerifyContextAdapter");
        this.VerifyContextAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteVerifyContext$lambda$6(long j2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteVerifyContext$lambda$7(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("VerifyContext");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object geListOfVerifyContexts$lambda$2(Function5 function5, VerifyContextQueries verifyContextQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string);
        ColumnAdapter<Validation, String> validationAdapter = verifyContextQueries.VerifyContextAdapter.getValidationAdapter();
        String string2 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string2);
        Validation decode = validationAdapter.decode(string2);
        String string3 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string3);
        return function5.invoke(l2, string, decode, string3, sqlCursor.getBoolean(4));
    }

    /* access modifiers changed from: private */
    public static final VerifyContext geListOfVerifyContexts$lambda$3(long j2, String str, Validation validation, String str2, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "origin");
        Intrinsics.checkNotNullParameter(validation, Constants.VALIDATION_FEATURE);
        Intrinsics.checkNotNullParameter(str2, "verify_url");
        return new VerifyContext(j2, str, validation, str2, bool);
    }

    /* access modifiers changed from: private */
    public static final Object getVerifyContextById$lambda$0(Function5 function5, VerifyContextQueries verifyContextQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string);
        ColumnAdapter<Validation, String> validationAdapter = verifyContextQueries.VerifyContextAdapter.getValidationAdapter();
        String string2 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string2);
        Validation decode = validationAdapter.decode(string2);
        String string3 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string3);
        return function5.invoke(l2, string, decode, string3, sqlCursor.getBoolean(4));
    }

    /* access modifiers changed from: private */
    public static final VerifyContext getVerifyContextById$lambda$1(long j2, String str, Validation validation, String str2, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "origin");
        Intrinsics.checkNotNullParameter(validation, Constants.VALIDATION_FEATURE);
        Intrinsics.checkNotNullParameter(str2, "verify_url");
        return new VerifyContext(j2, str, validation, str2, bool);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortVerifyContext$lambda$4(Long l2, String str, VerifyContextQueries verifyContextQueries, Validation validation, String str2, Boolean bool, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, l2);
        sqlPreparedStatement.bindString(1, str);
        sqlPreparedStatement.bindString(2, verifyContextQueries.VerifyContextAdapter.getValidationAdapter().encode(validation));
        sqlPreparedStatement.bindString(3, str2);
        sqlPreparedStatement.bindBoolean(4, bool);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortVerifyContext$lambda$5(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("VerifyContext");
        return Unit.INSTANCE;
    }

    public final void deleteVerifyContext(long j2) {
        getDriver().execute(1781194981, "DELETE FROM VerifyContext\nWHERE id = ?", 1, new b(j2, 3));
        notifyQueries(1781194981, new k(12));
    }

    @NotNull
    public final <T> Query<T> geListOfVerifyContexts(@NotNull Function5<? super Long, ? super String, ? super Validation, ? super String, ? super Boolean, ? extends T> function5) {
        Intrinsics.checkNotNullParameter(function5, "mapper");
        return QueryKt.Query(844063894, new String[]{"VerifyContext"}, getDriver(), "VerifyContext.sq", "geListOfVerifyContexts", "SELECT id, origin, validation, verify_url, is_scam\nFROM VerifyContext", new q(function5, this, 0));
    }

    @NotNull
    public final <T> Query<T> getVerifyContextById(long j2, @NotNull Function5<? super Long, ? super String, ? super Validation, ? super String, ? super Boolean, ? extends T> function5) {
        Intrinsics.checkNotNullParameter(function5, "mapper");
        return new GetVerifyContextByIdQuery(this, j2, new q(function5, this, 1));
    }

    public final void insertOrAbortVerifyContext(@Nullable Long l2, @NotNull String str, @NotNull Validation validation, @NotNull String str2, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "origin");
        Intrinsics.checkNotNullParameter(validation, Constants.VALIDATION_FEATURE);
        Intrinsics.checkNotNullParameter(str2, "verify_url");
        getDriver().execute(859993640, "INSERT OR ABORT INTO VerifyContext(id, origin, validation, verify_url, is_scam)\nVALUES (?, ?, ?, ?, ?)", 5, new r(l2, str, this, validation, str2, bool));
        notifyQueries(859993640, new k(13));
    }

    @NotNull
    public final Query<VerifyContext> geListOfVerifyContexts() {
        return geListOfVerifyContexts(new p(0));
    }

    @NotNull
    public final Query<VerifyContext> getVerifyContextById(long j2) {
        return getVerifyContextById(j2, new p(1));
    }
}
