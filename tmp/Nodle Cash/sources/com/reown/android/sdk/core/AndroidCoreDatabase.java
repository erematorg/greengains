package com.reown.android.sdk.core;

import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import com.reown.android.sdk.core.android.AndroidCoreDatabaseImplKt;
import com.reown.android.sdk.storage.data.dao.EventDao;
import com.reown.android.sdk.storage.data.dao.EventQueries;
import com.reown.android.sdk.storage.data.dao.IdentitiesQueries;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryDao;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryQueries;
import com.reown.android.sdk.storage.data.dao.MetaData;
import com.reown.android.sdk.storage.data.dao.MetaDataQueries;
import com.reown.android.sdk.storage.data.dao.PairingQueries;
import com.reown.android.sdk.storage.data.dao.PushMessageQueries;
import com.reown.android.sdk.storage.data.dao.VerifyContext;
import com.reown.android.sdk.storage.data.dao.VerifyContextQueries;
import com.reown.android.sdk.storage.data.dao.VerifyPublicKeyQueries;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \"2\u00020\u0001:\u0001\"R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#À\u0006\u0003"}, d2 = {"Lcom/reown/android/sdk/core/AndroidCoreDatabase;", "Lapp/cash/sqldelight/Transacter;", "eventQueries", "Lcom/reown/android/sdk/storage/data/dao/EventQueries;", "getEventQueries", "()Lcom/reown/android/sdk/storage/data/dao/EventQueries;", "identitiesQueries", "Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;", "getIdentitiesQueries", "()Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;", "jsonRpcHistoryQueries", "Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;", "getJsonRpcHistoryQueries", "()Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;", "metaDataQueries", "Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;", "getMetaDataQueries", "()Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;", "pairingQueries", "Lcom/reown/android/sdk/storage/data/dao/PairingQueries;", "getPairingQueries", "()Lcom/reown/android/sdk/storage/data/dao/PairingQueries;", "pushMessageQueries", "Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;", "getPushMessageQueries", "()Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;", "verifyContextQueries", "Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries;", "getVerifyContextQueries", "()Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries;", "verifyPublicKeyQueries", "Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKeyQueries;", "getVerifyPublicKeyQueries", "()Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKeyQueries;", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AndroidCoreDatabase extends Transacter {
    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/core/AndroidCoreDatabase$Companion;", "", "<init>", "()V", "Schema", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "getSchema", "()Lapp/cash/sqldelight/db/SqlSchema;", "invoke", "Lcom/reown/android/sdk/core/AndroidCoreDatabase;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "EventDaoAdapter", "Lcom/reown/android/sdk/storage/data/dao/EventDao$Adapter;", "JsonRpcHistoryDaoAdapter", "Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryDao$Adapter;", "MetaDataAdapter", "Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;", "VerifyContextAdapter", "Lcom/reown/android/sdk/storage/data/dao/VerifyContext$Adapter;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @NotNull
        public final SqlSchema<QueryResult.Value<Unit>> getSchema() {
            return AndroidCoreDatabaseImplKt.getSchema(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class));
        }

        @NotNull
        public final AndroidCoreDatabase invoke(@NotNull SqlDriver sqlDriver, @NotNull EventDao.Adapter adapter, @NotNull JsonRpcHistoryDao.Adapter adapter2, @NotNull MetaData.Adapter adapter3, @NotNull VerifyContext.Adapter adapter4) {
            Intrinsics.checkNotNullParameter(sqlDriver, "driver");
            Intrinsics.checkNotNullParameter(adapter, "EventDaoAdapter");
            Intrinsics.checkNotNullParameter(adapter2, "JsonRpcHistoryDaoAdapter");
            Intrinsics.checkNotNullParameter(adapter3, "MetaDataAdapter");
            Intrinsics.checkNotNullParameter(adapter4, "VerifyContextAdapter");
            return AndroidCoreDatabaseImplKt.newInstance(Reflection.getOrCreateKotlinClass(AndroidCoreDatabase.class), sqlDriver, adapter, adapter2, adapter3, adapter4);
        }
    }

    @NotNull
    EventQueries getEventQueries();

    @NotNull
    IdentitiesQueries getIdentitiesQueries();

    @NotNull
    JsonRpcHistoryQueries getJsonRpcHistoryQueries();

    @NotNull
    MetaDataQueries getMetaDataQueries();

    @NotNull
    PairingQueries getPairingQueries();

    @NotNull
    PushMessageQueries getPushMessageQueries();

    @NotNull
    VerifyContextQueries getVerifyContextQueries();

    @NotNull
    VerifyPublicKeyQueries getVerifyPublicKeyQueries();
}
