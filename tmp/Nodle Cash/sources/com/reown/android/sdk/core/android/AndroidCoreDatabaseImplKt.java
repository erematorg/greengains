package com.reown.android.sdk.core.android;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import com.reown.android.sdk.core.AndroidCoreDatabase;
import com.reown.android.sdk.core.android.AndroidCoreDatabaseImpl;
import com.reown.android.sdk.storage.data.dao.EventDao;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryDao;
import com.reown.android.sdk.storage.data.dao.MetaData;
import com.reown.android.sdk.storage.data.dao.VerifyContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\b\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\"*\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00050\u00048@X\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"schema", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "Lkotlin/reflect/KClass;", "Lcom/reown/android/sdk/core/AndroidCoreDatabase;", "getSchema", "(Lkotlin/reflect/KClass;)Lapp/cash/sqldelight/db/SqlSchema;", "newInstance", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "EventDaoAdapter", "Lcom/reown/android/sdk/storage/data/dao/EventDao$Adapter;", "JsonRpcHistoryDaoAdapter", "Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryDao$Adapter;", "MetaDataAdapter", "Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;", "VerifyContextAdapter", "Lcom/reown/android/sdk/storage/data/dao/VerifyContext$Adapter;", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AndroidCoreDatabaseImplKt {
    @NotNull
    public static final SqlSchema<QueryResult.Value<Unit>> getSchema(@NotNull KClass<AndroidCoreDatabase> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return AndroidCoreDatabaseImpl.Schema.INSTANCE;
    }

    @NotNull
    public static final AndroidCoreDatabase newInstance(@NotNull KClass<AndroidCoreDatabase> kClass, @NotNull SqlDriver sqlDriver, @NotNull EventDao.Adapter adapter, @NotNull JsonRpcHistoryDao.Adapter adapter2, @NotNull MetaData.Adapter adapter3, @NotNull VerifyContext.Adapter adapter4) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "EventDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter2, "JsonRpcHistoryDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter3, "MetaDataAdapter");
        Intrinsics.checkNotNullParameter(adapter4, "VerifyContextAdapter");
        return new AndroidCoreDatabaseImpl(sqlDriver, adapter, adapter2, adapter3, adapter4);
    }
}
