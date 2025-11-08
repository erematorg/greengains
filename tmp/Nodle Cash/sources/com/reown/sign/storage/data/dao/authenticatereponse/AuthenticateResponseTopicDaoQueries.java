package com.reown.sign.storage.data.dao.authenticatereponse;

import S0.f;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.di.a;
import io.nodle.cash.ui.feature.envelop.view.g;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uniffi.xmtpv3.i;
import uniffi.xmtpv3.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JN\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b\u0000\u0010\b*\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\f¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/storage/data/dao/authenticatereponse/AuthenticateResponseTopicDaoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "getListOfTopics", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "pairingTopic", "responseTopic", "Lcom/reown/sign/storage/data/dao/authenticatereponse/GetListOfTopics;", "insertOrAbort", "", "deleteByPairingTopic", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthenticateResponseTopicDaoQueries extends TransacterImpl {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AuthenticateResponseTopicDaoQueries(@NotNull SqlDriver sqlDriver) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
    }

    /* access modifiers changed from: private */
    public static final Unit deleteByPairingTopic$lambda$4(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteByPairingTopic$lambda$5(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("AuthenticateResponseTopicDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object getListOfTopics$lambda$0(Function2 function2, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string2);
        return function2.invoke(string, string2);
    }

    /* access modifiers changed from: private */
    public static final GetListOfTopics getListOfTopics$lambda$1(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        Intrinsics.checkNotNullParameter(str2, "responseTopic");
        return new GetListOfTopics(str, str2);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbort$lambda$2(String str, String str2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindString(1, str2);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbort$lambda$3(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("AuthenticateResponseTopicDao");
        return Unit.INSTANCE;
    }

    public final void deleteByPairingTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        getDriver().execute(-477408843, "DELETE FROM AuthenticateResponseTopicDao\nWHERE pairingTopic = ?", 1, new f(str, 9));
        notifyQueries(-477408843, new j(8));
    }

    @NotNull
    public final <T> Query<T> getListOfTopics(@NotNull Function2<? super String, ? super String, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return QueryKt.Query(-812935773, new String[]{"AuthenticateResponseTopicDao"}, getDriver(), "AuthenticateResponseTopicDao.sq", "getListOfTopics", "SELECT ard.pairingTopic, ard.responseTopic\nFROM AuthenticateResponseTopicDao ard", new g(function2, 29));
    }

    public final void insertOrAbort(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        Intrinsics.checkNotNullParameter(str2, "responseTopic");
        getDriver().execute(1853327784, "INSERT OR ABORT INTO AuthenticateResponseTopicDao(pairingTopic, responseTopic)\nVALUES (?,?)", 2, new a(str, str2, 5));
        notifyQueries(1853327784, new j(9));
    }

    @NotNull
    public final Query<GetListOfTopics> getListOfTopics() {
        return getListOfTopics(new i(10));
    }
}
