package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.reown.android.internal.common.di.i;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JN\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\b\b\u0000\u0010\b*\u00020\t26\u0010\n\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\b0\u000bJ\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0010¨\u0006\u0015"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKeyQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "getKey", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "key", "", "expires_at", "Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKey;", "upsertKey", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyPublicKeyQueries extends TransacterImpl {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerifyPublicKeyQueries(@NotNull SqlDriver sqlDriver) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
    }

    /* access modifiers changed from: private */
    public static final Object getKey$lambda$0(Function2 function2, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        Long l2 = sqlCursor.getLong(1);
        Intrinsics.checkNotNull(l2);
        return function2.invoke(string, l2);
    }

    /* access modifiers changed from: private */
    public static final VerifyPublicKey getKey$lambda$1(String str, long j2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        return new VerifyPublicKey(str, j2);
    }

    /* access modifiers changed from: private */
    public static final Unit upsertKey$lambda$2(String str, long j2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindLong(1, Long.valueOf(j2));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit upsertKey$lambda$3(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("VerifyPublicKey");
        return Unit.INSTANCE;
    }

    @NotNull
    public final <T> Query<T> getKey(@NotNull Function2<? super String, ? super Long, ? extends T> function2) {
        Intrinsics.checkNotNullParameter(function2, "mapper");
        return QueryKt.Query(-962554474, new String[]{"VerifyPublicKey"}, getDriver(), "VerifyPublicKey.sq", "getKey", "SELECT key, expires_at\nFROM VerifyPublicKey", new c(function2, 14));
    }

    public final void upsertKey(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        getDriver().execute(66605347, "INSERT OR REPLACE INTO VerifyPublicKey(key, expires_at)\nVALUES (?, ?)", 2, new f(str, j2, 2));
        notifyQueries(66605347, new k(14));
    }

    @NotNull
    public final Query<VerifyPublicKey> getKey() {
        return getKey(new i(27));
    }
}
