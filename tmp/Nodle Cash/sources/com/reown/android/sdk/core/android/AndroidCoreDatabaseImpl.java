package com.reown.android.sdk.core.android;

import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.AfterVersion;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import com.reown.android.sdk.core.AndroidCoreDatabase;
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
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001/B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(X\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020,X\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u00060"}, d2 = {"Lcom/reown/android/sdk/core/android/AndroidCoreDatabaseImpl;", "Lapp/cash/sqldelight/TransacterImpl;", "Lcom/reown/android/sdk/core/AndroidCoreDatabase;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "EventDaoAdapter", "Lcom/reown/android/sdk/storage/data/dao/EventDao$Adapter;", "JsonRpcHistoryDaoAdapter", "Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryDao$Adapter;", "MetaDataAdapter", "Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;", "VerifyContextAdapter", "Lcom/reown/android/sdk/storage/data/dao/VerifyContext$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/android/sdk/storage/data/dao/EventDao$Adapter;Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryDao$Adapter;Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;Lcom/reown/android/sdk/storage/data/dao/VerifyContext$Adapter;)V", "eventQueries", "Lcom/reown/android/sdk/storage/data/dao/EventQueries;", "getEventQueries", "()Lcom/reown/android/sdk/storage/data/dao/EventQueries;", "identitiesQueries", "Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;", "getIdentitiesQueries", "()Lcom/reown/android/sdk/storage/data/dao/IdentitiesQueries;", "jsonRpcHistoryQueries", "Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;", "getJsonRpcHistoryQueries", "()Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;", "metaDataQueries", "Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;", "getMetaDataQueries", "()Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;", "pairingQueries", "Lcom/reown/android/sdk/storage/data/dao/PairingQueries;", "getPairingQueries", "()Lcom/reown/android/sdk/storage/data/dao/PairingQueries;", "pushMessageQueries", "Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;", "getPushMessageQueries", "()Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;", "verifyContextQueries", "Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries;", "getVerifyContextQueries", "()Lcom/reown/android/sdk/storage/data/dao/VerifyContextQueries;", "verifyPublicKeyQueries", "Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKeyQueries;", "getVerifyPublicKeyQueries", "()Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKeyQueries;", "Schema", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class AndroidCoreDatabaseImpl extends TransacterImpl implements AndroidCoreDatabase {
    @NotNull
    private final EventQueries eventQueries;
    @NotNull
    private final IdentitiesQueries identitiesQueries;
    @NotNull
    private final JsonRpcHistoryQueries jsonRpcHistoryQueries;
    @NotNull
    private final MetaDataQueries metaDataQueries;
    @NotNull
    private final PairingQueries pairingQueries;
    @NotNull
    private final PushMessageQueries pushMessageQueries;
    @NotNull
    private final VerifyContextQueries verifyContextQueries;
    @NotNull
    private final VerifyPublicKeyQueries verifyPublicKeyQueries;

    @SourceDebugExtension({"SMAP\nAndroidCoreDatabaseImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidCoreDatabaseImpl.kt\ncom/reown/android/sdk/core/android/AndroidCoreDatabaseImpl$Schema\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,278:1\n3829#2:279\n4344#2,2:280\n1056#3:282\n1869#3,2:283\n*S KotlinDebug\n*F\n+ 1 AndroidCoreDatabaseImpl.kt\ncom/reown/android/sdk/core/android/AndroidCoreDatabaseImpl$Schema\n*L\n263#1:279\n263#1:280,2\n264#1:282\n265#1:283,2\n*E\n"})
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0012\u0010\u0013JA\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016\"\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/reown/android/sdk/core/android/AndroidCoreDatabaseImpl$Schema;", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "<init>", "()V", "version", "", "getVersion", "()J", "create", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "create-0iQ1-z0", "(Lapp/cash/sqldelight/db/SqlDriver;)Ljava/lang/Object;", "migrateInternal", "oldVersion", "newVersion", "migrateInternal-ElmaSbI", "(Lapp/cash/sqldelight/db/SqlDriver;JJ)Ljava/lang/Object;", "migrate", "callbacks", "", "Lapp/cash/sqldelight/db/AfterVersion;", "migrate-zeHU3Mk", "(Lapp/cash/sqldelight/db/SqlDriver;JJ[Lapp/cash/sqldelight/db/AfterVersion;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Schema implements SqlSchema<QueryResult.Value<Unit>> {
        @NotNull
        public static final Schema INSTANCE = new Schema();

        private Schema() {
        }

        /* renamed from: migrateInternal-ElmaSbI  reason: not valid java name */
        private final Object m8843migrateInternalElmaSbI(SqlDriver sqlDriver, long j2, long j3) {
            if (j2 <= 1 && j3 > 1) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS StoreValues", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE StoreValues (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    storeId INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    value TEXT NOT NULL,\n    timestamp INTEGER NOT NULL,\n    UNIQUE(storeId, key)\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 2 && j3 > 2) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE VerifyContext (\n    id INTEGER PRIMARY KEY NOT NULL,\n    origin TEXT NOT NULL,\n    validation TEXT NOT NULL,\n    verify_url TEXT NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 3 && j3 > 3) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE VerifyContext ADD COLUMN is_scam INTEGER", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 4 && j3 > 4) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS StoreValues", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS Accounts", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver2 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "DROP TABLE IF EXISTS Stores", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE Identities ADD COLUMN cacao_payload TEXT DEFAULT NULL", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "ALTER TABLE Identities ADD COLUMN is_owner INTEGER DEFAULT 0", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 5 && j3 > 5) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS Identities", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE Identities (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    identity TEXT UNIQUE NOT NULL,\n    accountId TEXT NOT NULL,\n    cacao_payload TEXT DEFAULT NULL,\n    is_owner INTEGER DEFAULT 0\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 6 && j3 > 6) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE PushMessage (\n\tid TEXT PRIMARY KEY NOT NULL,\n\ttopic TEXT NOT NULL,\n  \tblob TEXT NOT NULL,\n  \ttag INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 7 && j3 > 7) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE Pairing ADD COLUMN is_proposal_received INTEGER DEFAULT 1", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 8 && j3 > 8) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE EventDao(\n    event_id INTEGER PRIMARY KEY NOT NULL,\n    bundle_id TEXT NOT NULL,\n    timestamp INTEGER NOT NULL,\n    event_name TEXT NOT NULL,\n    type TEXT NOT NULL,\n    topic TEXT,\n    trace TEXT\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 9 && j3 > 9) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE JsonRpcHistoryDao ADD COLUMN transport_type TEXT", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE MetaData ADD COLUMN app_link TEXT", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE MetaData ADD COLUMN link_mode INTEGER", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 10 && j3 > 10) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE EventDao ADD COLUMN correlation_id INTEGER", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE EventDao ADD COLUMN client_id TEXT", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE EventDao ADD COLUMN direction TEXT", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE VerifyPublicKey(\n    key TEXT PRIMARY KEY NOT NULL,\n    expires_at INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 11 && j3 > 11) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE EventDao ADD COLUMN user_agent TEXT", 0, (Function1) null, 8, (Object) null);
            }
            return QueryResult.Companion.m8087getUnitmlRZEE();
        }

        public /* bridge */ /* synthetic */ QueryResult create(SqlDriver sqlDriver) {
            return QueryResult.Value.m8089boximpl(m8844create0iQ1z0(sqlDriver));
        }

        @NotNull
        /* renamed from: create-0iQ1-z0  reason: not valid java name */
        public Object m8844create0iQ1z0(@NotNull SqlDriver sqlDriver) {
            Intrinsics.checkNotNullParameter(sqlDriver, "driver");
            SqlDriver sqlDriver2 = sqlDriver;
            SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE EventDao(\n    event_id INTEGER PRIMARY KEY NOT NULL,\n    bundle_id TEXT NOT NULL,\n    timestamp INTEGER NOT NULL,\n    event_name TEXT NOT NULL,\n    type TEXT NOT NULL,\n    topic TEXT,\n    trace TEXT,\n    correlation_id INTEGER,\n    client_id TEXT,\n    direction TEXT,\n    user_agent TEXT\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver sqlDriver3 = sqlDriver;
            SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE Identities (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    identity TEXT UNIQUE NOT NULL,\n    accountId TEXT NOT NULL,\n    cacao_payload TEXT DEFAULT NULL,\n    is_owner INTEGER DEFAULT 0\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE JsonRpcHistoryDao(\n  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n  request_id INTEGER UNIQUE NOT NULL,\n  topic TEXT NOT NULL,\n  method TEXT NOT NULL,\n  body TEXT NOT NULL,\n  response TEXT,\n  transport_type TEXT\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE MetaData(\n\tid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\tsequence_topic TEXT NOT NULL,\n  \tname TEXT NOT NULL,\n  \tdescription TEXT NOT NULL,\n  \turl TEXT NOT NULL,\n  \ticons TEXT NOT NULL,\n  \tnative TEXT,\n  \ttype TEXT NOT NULL,\n    app_link TEXT,\n    link_mode INTEGER,\n  \tUNIQUE(sequence_topic, type)\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE Pairing (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    topic TEXT UNIQUE NOT NULL,\n    expiry INTEGER NOT NULL,\n    relay_protocol TEXT NOT NULL,\n    relay_data TEXT,\n    uri TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    is_active INTEGER NOT NULL,\n    is_proposal_received INTEGER DEFAULT 1\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE PushMessage (\n\tid TEXT PRIMARY KEY NOT NULL,\n\ttopic TEXT NOT NULL,\n  \tblob TEXT NOT NULL,\n  \ttag INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE VerifyContext (\n    id INTEGER PRIMARY KEY NOT NULL,\n    origin TEXT NOT NULL,\n    validation TEXT NOT NULL,\n    verify_url TEXT NOT NULL,\n    is_scam INTEGER\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE VerifyPublicKey(\n    key TEXT PRIMARY KEY NOT NULL,\n    expires_at INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            return QueryResult.Companion.m8087getUnitmlRZEE();
        }

        public long getVersion() {
            return 12;
        }

        public /* bridge */ /* synthetic */ QueryResult migrate(SqlDriver sqlDriver, long j2, long j3, AfterVersion[] afterVersionArr) {
            return QueryResult.Value.m8089boximpl(m8845migratezeHU3Mk(sqlDriver, j2, j3, afterVersionArr));
        }

        @NotNull
        /* renamed from: migrate-zeHU3Mk  reason: not valid java name */
        public Object m8845migratezeHU3Mk(@NotNull SqlDriver sqlDriver, long j2, long j3, @NotNull AfterVersion... afterVersionArr) {
            Intrinsics.checkNotNullParameter(sqlDriver, "driver");
            Intrinsics.checkNotNullParameter(afterVersionArr, "callbacks");
            ArrayList arrayList = new ArrayList();
            for (AfterVersion afterVersion : afterVersionArr) {
                long afterVersion2 = afterVersion.getAfterVersion();
                if (j2 <= afterVersion2 && afterVersion2 < j3) {
                    arrayList.add(afterVersion);
                }
            }
            long j4 = j2;
            for (AfterVersion afterVersion3 : CollectionsKt.sortedWith(arrayList, new AndroidCoreDatabaseImpl$Schema$migratezeHU3Mk$$inlined$sortedBy$1())) {
                INSTANCE.m8843migrateInternalElmaSbI(sqlDriver, j4, afterVersion3.getAfterVersion() + 1);
                afterVersion3.getBlock().invoke(sqlDriver);
                j4 = afterVersion3.getAfterVersion() + 1;
            }
            if (j4 < j3) {
                m8843migrateInternalElmaSbI(sqlDriver, j4, j3);
            }
            return QueryResult.Companion.m8087getUnitmlRZEE();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidCoreDatabaseImpl(@NotNull SqlDriver sqlDriver, @NotNull EventDao.Adapter adapter, @NotNull JsonRpcHistoryDao.Adapter adapter2, @NotNull MetaData.Adapter adapter3, @NotNull VerifyContext.Adapter adapter4) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "EventDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter2, "JsonRpcHistoryDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter3, "MetaDataAdapter");
        Intrinsics.checkNotNullParameter(adapter4, "VerifyContextAdapter");
        this.eventQueries = new EventQueries(sqlDriver, adapter);
        this.identitiesQueries = new IdentitiesQueries(sqlDriver);
        this.jsonRpcHistoryQueries = new JsonRpcHistoryQueries(sqlDriver, adapter2);
        this.metaDataQueries = new MetaDataQueries(sqlDriver, adapter3);
        this.pairingQueries = new PairingQueries(sqlDriver, adapter3);
        this.pushMessageQueries = new PushMessageQueries(sqlDriver);
        this.verifyContextQueries = new VerifyContextQueries(sqlDriver, adapter4);
        this.verifyPublicKeyQueries = new VerifyPublicKeyQueries(sqlDriver);
    }

    @NotNull
    public EventQueries getEventQueries() {
        return this.eventQueries;
    }

    @NotNull
    public IdentitiesQueries getIdentitiesQueries() {
        return this.identitiesQueries;
    }

    @NotNull
    public JsonRpcHistoryQueries getJsonRpcHistoryQueries() {
        return this.jsonRpcHistoryQueries;
    }

    @NotNull
    public MetaDataQueries getMetaDataQueries() {
        return this.metaDataQueries;
    }

    @NotNull
    public PairingQueries getPairingQueries() {
        return this.pairingQueries;
    }

    @NotNull
    public PushMessageQueries getPushMessageQueries() {
        return this.pushMessageQueries;
    }

    @NotNull
    public VerifyContextQueries getVerifyContextQueries() {
        return this.verifyContextQueries;
    }

    @NotNull
    public VerifyPublicKeyQueries getVerifyPublicKeyQueries() {
        return this.verifyPublicKeyQueries;
    }
}
