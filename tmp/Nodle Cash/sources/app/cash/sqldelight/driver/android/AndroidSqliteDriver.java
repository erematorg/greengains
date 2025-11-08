package app.cash.sqldelight.driver.android;

import android.content.Context;
import androidx.collection.SieveCacheKt;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.db.AfterVersion;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import app.cash.sqldelight.db.SqlSchema;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.ClassUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000«\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t*\u0001&\u0018\u00002\u00020\u0001:\u0002NOB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004Bc\b\u0017\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017B%\b\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u001aB3\b\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u001bJ)\u0010,\u001a\u00020\b2\u0012\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0.\"\u00020\f2\u0006\u0010/\u001a\u00020#H\u0016¢\u0006\u0002\u00100J\b\u00101\u001a\u00020\bH\u0016J\n\u00102\u001a\u0004\u0018\u00010*H\u0016Jm\u00103\u001a\b\u0012\u0004\u0012\u0002H40\u0007\"\u0004\b\u0000\u001042\b\u00105\u001a\u0004\u0018\u00010\u00122\f\u00106\u001a\b\u0012\u0004\u0012\u000208072\u0019\u00109\u001a\u0015\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\b\u0018\u00010:¢\u0006\u0002\b<2\u0017\u0010=\u001a\u0013\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u0002H40:¢\u0006\u0002\b<H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b>\u0010?JH\u00103\u001a\b\u0012\u0004\u0012\u00020\u00160@2\b\u00105\u001a\u0004\u0018\u00010\u00122\u0006\u0010A\u001a\u00020\f2\u0006\u0010B\u001a\u00020\u00122\u0019\u00109\u001a\u0015\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\b\u0018\u00010:¢\u0006\u0002\b<H\u0016¢\u0006\u0002\u0010CJp\u0010D\u001a\b\u0012\u0004\u0012\u0002HE0\u0007\"\u0004\b\u0000\u0010E2\b\u00105\u001a\u0004\u0018\u00010\u00122\u0006\u0010A\u001a\u00020\f2\u0018\u0010F\u001a\u0014\u0012\u0004\u0012\u00020G\u0012\n\u0012\b\u0012\u0004\u0012\u0002HE0@0:2\u0006\u0010B\u001a\u00020\u00122\u0019\u00109\u001a\u0015\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\b\u0018\u00010:¢\u0006\u0002\b<H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bH\u0010IJ\u000e\u0010J\u001a\b\u0012\u0004\u0012\u00020*0@H\u0016J!\u0010K\u001a\u00020\b2\u0012\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0.\"\u00020\fH\u0016¢\u0006\u0002\u0010LJ)\u0010M\u001a\u00020\b2\u0012\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0.\"\u00020\f2\u0006\u0010/\u001a\u00020#H\u0016¢\u0006\u0002\u00100R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR6\u0010 \u001a*\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0!j\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"`$X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u00020&X\u0004¢\u0006\u0004\n\u0002\u0010'R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0004¢\u0006\u0004\n\u0002\u0010+\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006P"}, d2 = {"Lapp/cash/sqldelight/driver/android/AndroidSqliteDriver;", "Lapp/cash/sqldelight/db/SqlDriver;", "openHelper", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;)V", "schema", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "context", "Landroid/content/Context;", "name", "", "factory", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "callback", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "cacheSize", "", "useNoBackupDirectory", "", "windowSizeBytes", "", "(Lapp/cash/sqldelight/db/SqlSchema;Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;IZLjava/lang/Long;)V", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "(Landroidx/sqlite/db/SupportSQLiteDatabase;ILjava/lang/Long;)V", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;Landroidx/sqlite/db/SupportSQLiteDatabase;ILjava/lang/Long;)V", "getDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "database$delegate", "Lkotlin/Lazy;", "listeners", "Ljava/util/LinkedHashMap;", "", "Lapp/cash/sqldelight/Query$Listener;", "Lkotlin/collections/LinkedHashMap;", "statements", "app/cash/sqldelight/driver/android/AndroidSqliteDriver$statements$1", "Lapp/cash/sqldelight/driver/android/AndroidSqliteDriver$statements$1;", "transactions", "Ljava/lang/ThreadLocal;", "Lapp/cash/sqldelight/Transacter$Transaction;", "Ljava/lang/Long;", "addListener", "queryKeys", "", "listener", "([Ljava/lang/String;Lapp/cash/sqldelight/Query$Listener;)V", "close", "currentTransaction", "execute", "T", "identifier", "createStatement", "Lkotlin/Function0;", "Lapp/cash/sqldelight/driver/android/AndroidStatement;", "binders", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlPreparedStatement;", "Lkotlin/ExtensionFunctionType;", "result", "execute-zeHU3Mk", "(Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lapp/cash/sqldelight/db/QueryResult;", "sql", "parameters", "(Ljava/lang/Integer;Ljava/lang/String;ILkotlin/jvm/functions/Function1;)Lapp/cash/sqldelight/db/QueryResult;", "executeQuery", "R", "mapper", "Lapp/cash/sqldelight/db/SqlCursor;", "executeQuery-0yMERmw", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "newTransaction", "notifyListeners", "([Ljava/lang/String;)V", "removeListener", "Callback", "Transaction", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAndroidSqliteDriver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidSqliteDriver.kt\napp/cash/sqldelight/driver/android/AndroidSqliteDriver\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,351:1\n13309#2:352\n13310#2:360\n13309#2,2:361\n13309#2:363\n13310#2:365\n372#3,7:353\n1#4:364\n1855#5,2:366\n*S KotlinDebug\n*F\n+ 1 AndroidSqliteDriver.kt\napp/cash/sqldelight/driver/android/AndroidSqliteDriver\n*L\n99#1:352\n99#1:360\n107#1:361,2\n116#1:363\n116#1:365\n100#1:353,7\n118#1:366,2\n*E\n"})
public final class AndroidSqliteDriver implements SqlDriver {
    private final int cacheSize;
    @NotNull
    private final Lazy database$delegate;
    @NotNull
    private final LinkedHashMap<String, Set<Query.Listener>> listeners;
    /* access modifiers changed from: private */
    @Nullable
    public final SupportSQLiteOpenHelper openHelper;
    @NotNull
    private final AndroidSqliteDriver$statements$1 statements;
    /* access modifiers changed from: private */
    @NotNull
    public final ThreadLocal<Transacter.Transaction> transactions;
    /* access modifiers changed from: private */
    @Nullable
    public final Long windowSizeBytes;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B-\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u0018\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\nR\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lapp/cash/sqldelight/driver/android/AndroidSqliteDriver$Callback;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "schema", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "callbacks", "", "Lapp/cash/sqldelight/db/AfterVersion;", "(Lapp/cash/sqldelight/db/SqlSchema;[Lapp/cash/sqldelight/db/AfterVersion;)V", "[Lapp/cash/sqldelight/db/AfterVersion;", "onCreate", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static class Callback extends SupportSQLiteOpenHelper.Callback {
        @NotNull
        private final AfterVersion[] callbacks;
        @NotNull
        private final SqlSchema<QueryResult.Value<Unit>> schema;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Callback(@NotNull SqlSchema<QueryResult.Value<Unit>> sqlSchema, @NotNull AfterVersion... afterVersionArr) {
            super((int) sqlSchema.getVersion());
            Intrinsics.checkNotNullParameter(sqlSchema, "schema");
            Intrinsics.checkNotNullParameter(afterVersionArr, "callbacks");
            if (sqlSchema.getVersion() <= SieveCacheKt.NodeLinkMask) {
                this.schema = sqlSchema;
                this.callbacks = afterVersionArr;
                return;
            }
            throw new IllegalStateException(("Schema version is larger than Int.MAX_VALUE: " + sqlSchema.getVersion() + ClassUtils.PACKAGE_SEPARATOR_CHAR).toString());
        }

        public void onCreate(@NotNull SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
            this.schema.create(new AndroidSqliteDriver((SupportSQLiteOpenHelper) null, supportSQLiteDatabase, 1, (Long) null, 8, (DefaultConstructorMarker) null));
        }

        public void onUpgrade(@NotNull SupportSQLiteDatabase supportSQLiteDatabase, int i3, int i4) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
            AfterVersion[] afterVersionArr = this.callbacks;
            this.schema.migrate(new AndroidSqliteDriver((SupportSQLiteOpenHelper) null, supportSQLiteDatabase, 1, (Long) null, 8, (DefaultConstructorMarker) null), (long) i3, (long) i4, (AfterVersion[]) Arrays.copyOf(afterVersionArr, afterVersionArr.length));
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0014R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000b"}, d2 = {"Lapp/cash/sqldelight/driver/android/AndroidSqliteDriver$Transaction;", "Lapp/cash/sqldelight/Transacter$Transaction;", "enclosingTransaction", "(Lapp/cash/sqldelight/driver/android/AndroidSqliteDriver;Lapp/cash/sqldelight/Transacter$Transaction;)V", "getEnclosingTransaction", "()Lapp/cash/sqldelight/Transacter$Transaction;", "endTransaction", "Lapp/cash/sqldelight/db/QueryResult;", "", "successful", "", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class Transaction extends Transacter.Transaction {
        @Nullable
        private final Transacter.Transaction enclosingTransaction;

        public Transaction(@Nullable Transacter.Transaction transaction) {
            this.enclosingTransaction = transaction;
        }

        @NotNull
        public QueryResult<Unit> endTransaction(boolean z2) {
            if (getEnclosingTransaction() == null) {
                if (z2) {
                    AndroidSqliteDriver.this.getDatabase().setTransactionSuccessful();
                    AndroidSqliteDriver.this.getDatabase().endTransaction();
                } else {
                    AndroidSqliteDriver.this.getDatabase().endTransaction();
                }
            }
            AndroidSqliteDriver.this.transactions.set(getEnclosingTransaction());
            return QueryResult.Value.m8089boximpl(QueryResult.Companion.m8087getUnitmlRZEE());
        }

        @Nullable
        public Transacter.Transaction getEnclosingTransaction() {
            return this.enclosingTransaction;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AndroidSqliteDriver(@NotNull SupportSQLiteDatabase supportSQLiteDatabase) {
        this(supportSQLiteDatabase, 0, (Long) null, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
    }

    /* renamed from: execute-zeHU3Mk  reason: not valid java name */
    private final <T> Object m8097executezeHU3Mk(Integer num, Function0<? extends AndroidStatement> function0, Function1<? super SqlPreparedStatement, Unit> function1, Function1<? super AndroidStatement, ? extends T> function12) {
        AndroidStatement androidStatement = num != null ? (AndroidStatement) this.statements.remove(num) : null;
        if (androidStatement == null) {
            androidStatement = (AndroidStatement) function0.invoke();
        }
        if (function1 != null) {
            try {
                function1.invoke(androidStatement);
            } catch (Throwable th) {
                if (num != null) {
                    AndroidStatement androidStatement2 = (AndroidStatement) this.statements.put(num, androidStatement);
                    if (androidStatement2 != null) {
                        androidStatement2.close();
                    }
                } else {
                    androidStatement.close();
                }
                throw th;
            }
        }
        Object r3 = QueryResult.Value.m8090constructorimpl(function12.invoke(androidStatement));
        if (num != null) {
            AndroidStatement androidStatement3 = (AndroidStatement) this.statements.put(num, androidStatement);
            if (androidStatement3 != null) {
                androidStatement3.close();
            }
        } else {
            androidStatement.close();
        }
        return r3;
    }

    /* access modifiers changed from: private */
    public final SupportSQLiteDatabase getDatabase() {
        return (SupportSQLiteDatabase) this.database$delegate.getValue();
    }

    public void addListener(@NotNull String[] strArr, @NotNull Query.Listener listener) {
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.listeners) {
            try {
                for (String str : strArr) {
                    LinkedHashMap<String, Set<Query.Listener>> linkedHashMap = this.listeners;
                    Set<Query.Listener> set = linkedHashMap.get(str);
                    if (set == null) {
                        set = new LinkedHashSet<>();
                        linkedHashMap.put(str, set);
                    }
                    set.add(listener);
                }
                Unit unit = Unit.INSTANCE;
            } finally {
            }
        }
    }

    public void close() {
        Unit unit;
        this.statements.evictAll();
        SupportSQLiteOpenHelper supportSQLiteOpenHelper = this.openHelper;
        if (supportSQLiteOpenHelper != null) {
            supportSQLiteOpenHelper.close();
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            getDatabase().close();
        }
    }

    @Nullable
    public Transacter.Transaction currentTransaction() {
        return this.transactions.get();
    }

    @NotNull
    public QueryResult<Long> execute(@Nullable Integer num, @NotNull String str, int i3, @Nullable Function1<? super SqlPreparedStatement, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "sql");
        return QueryResult.Value.m8089boximpl(m8097executezeHU3Mk(num, new AndroidSqliteDriver$execute$1(this, str), function1, AndroidSqliteDriver$execute$2.INSTANCE));
    }

    public /* bridge */ /* synthetic */ QueryResult executeQuery(Integer num, String str, Function1 function1, int i3, Function1 function12) {
        return QueryResult.Value.m8089boximpl(m8098executeQuery0yMERmw(num, str, function1, i3, function12));
    }

    @NotNull
    /* renamed from: executeQuery-0yMERmw  reason: not valid java name */
    public <R> Object m8098executeQuery0yMERmw(@Nullable Integer num, @NotNull String str, @NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1, int i3, @Nullable Function1<? super SqlPreparedStatement, Unit> function12) {
        Intrinsics.checkNotNullParameter(str, "sql");
        Intrinsics.checkNotNullParameter(function1, "mapper");
        return m8097executezeHU3Mk(num, new AndroidSqliteDriver$executeQuery$1(str, this, i3), function12, new AndroidSqliteDriver$executeQuery$2(function1));
    }

    @NotNull
    public QueryResult<Transacter.Transaction> newTransaction() {
        Transacter.Transaction transaction = this.transactions.get();
        Transaction transaction2 = new Transaction(transaction);
        this.transactions.set(transaction2);
        if (transaction == null) {
            getDatabase().beginTransactionNonExclusive();
        }
        return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(transaction2));
    }

    public void notifyListeners(@NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        LinkedHashSet<Query.Listener> linkedHashSet = new LinkedHashSet<>();
        synchronized (this.listeners) {
            try {
                for (String str : strArr) {
                    Set set = this.listeners.get(str);
                    if (set != null) {
                        linkedHashSet.addAll(set);
                    }
                }
                Unit unit = Unit.INSTANCE;
            } finally {
            }
        }
        for (Query.Listener queryResultsChanged : linkedHashSet) {
            queryResultsChanged.queryResultsChanged();
        }
    }

    public void removeListener(@NotNull String[] strArr, @NotNull Query.Listener listener) {
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.listeners) {
            try {
                for (String str : strArr) {
                    Set set = this.listeners.get(str);
                    if (set != null) {
                        set.remove(listener);
                    }
                }
                Unit unit = Unit.INSTANCE;
            } finally {
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AndroidSqliteDriver(@NotNull SupportSQLiteDatabase supportSQLiteDatabase, int i3) {
        this(supportSQLiteDatabase, i3, (Long) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AndroidSqliteDriver(@NotNull SqlSchema<QueryResult.Value<Unit>> sqlSchema, @NotNull Context context) {
        this(sqlSchema, context, (String) null, (SupportSQLiteOpenHelper.Factory) null, (SupportSQLiteOpenHelper.Callback) null, 0, false, (Long) null, 252, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(sqlSchema, "schema");
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AndroidSqliteDriver(@NotNull SqlSchema<QueryResult.Value<Unit>> sqlSchema, @NotNull Context context, @Nullable String str) {
        this(sqlSchema, context, str, (SupportSQLiteOpenHelper.Factory) null, (SupportSQLiteOpenHelper.Callback) null, 0, false, (Long) null, 248, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(sqlSchema, "schema");
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @kotlin.jvm.JvmOverloads
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AndroidSqliteDriver(@org.jetbrains.annotations.NotNull app.cash.sqldelight.db.SqlSchema<app.cash.sqldelight.db.QueryResult.Value<kotlin.Unit>> r13, @org.jetbrains.annotations.NotNull android.content.Context r14, @org.jetbrains.annotations.Nullable java.lang.String r15, @org.jetbrains.annotations.NotNull androidx.sqlite.db.SupportSQLiteOpenHelper.Factory r16) {
        /*
            r12 = this;
            java.lang.String r0 = "schema"
            r2 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "context"
            r3 = r14
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "factory"
            r5 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r10 = 240(0xf0, float:3.36E-43)
            r11 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r1 = r12
            r4 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.driver.android.AndroidSqliteDriver.<init>(app.cash.sqldelight.db.SqlSchema, android.content.Context, java.lang.String, androidx.sqlite.db.SupportSQLiteOpenHelper$Factory):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @kotlin.jvm.JvmOverloads
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AndroidSqliteDriver(@org.jetbrains.annotations.NotNull app.cash.sqldelight.db.SqlSchema<app.cash.sqldelight.db.QueryResult.Value<kotlin.Unit>> r13, @org.jetbrains.annotations.NotNull android.content.Context r14, @org.jetbrains.annotations.Nullable java.lang.String r15, @org.jetbrains.annotations.NotNull androidx.sqlite.db.SupportSQLiteOpenHelper.Factory r16, @org.jetbrains.annotations.NotNull androidx.sqlite.db.SupportSQLiteOpenHelper.Callback r17) {
        /*
            r12 = this;
            java.lang.String r0 = "schema"
            r2 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "context"
            r3 = r14
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "factory"
            r5 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "callback"
            r6 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r10 = 224(0xe0, float:3.14E-43)
            r11 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r1 = r12
            r4 = r15
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.driver.android.AndroidSqliteDriver.<init>(app.cash.sqldelight.db.SqlSchema, android.content.Context, java.lang.String, androidx.sqlite.db.SupportSQLiteOpenHelper$Factory, androidx.sqlite.db.SupportSQLiteOpenHelper$Callback):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @kotlin.jvm.JvmOverloads
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AndroidSqliteDriver(@org.jetbrains.annotations.NotNull app.cash.sqldelight.db.SqlSchema<app.cash.sqldelight.db.QueryResult.Value<kotlin.Unit>> r13, @org.jetbrains.annotations.NotNull android.content.Context r14, @org.jetbrains.annotations.Nullable java.lang.String r15, @org.jetbrains.annotations.NotNull androidx.sqlite.db.SupportSQLiteOpenHelper.Factory r16, @org.jetbrains.annotations.NotNull androidx.sqlite.db.SupportSQLiteOpenHelper.Callback r17, int r18) {
        /*
            r12 = this;
            java.lang.String r0 = "schema"
            r2 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "context"
            r3 = r14
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "factory"
            r5 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "callback"
            r6 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r10 = 192(0xc0, float:2.69E-43)
            r11 = 0
            r8 = 0
            r9 = 0
            r1 = r12
            r4 = r15
            r7 = r18
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.driver.android.AndroidSqliteDriver.<init>(app.cash.sqldelight.db.SqlSchema, android.content.Context, java.lang.String, androidx.sqlite.db.SupportSQLiteOpenHelper$Factory, androidx.sqlite.db.SupportSQLiteOpenHelper$Callback, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @kotlin.jvm.JvmOverloads
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AndroidSqliteDriver(@org.jetbrains.annotations.NotNull app.cash.sqldelight.db.SqlSchema<app.cash.sqldelight.db.QueryResult.Value<kotlin.Unit>> r13, @org.jetbrains.annotations.NotNull android.content.Context r14, @org.jetbrains.annotations.Nullable java.lang.String r15, @org.jetbrains.annotations.NotNull androidx.sqlite.db.SupportSQLiteOpenHelper.Factory r16, @org.jetbrains.annotations.NotNull androidx.sqlite.db.SupportSQLiteOpenHelper.Callback r17, int r18, boolean r19) {
        /*
            r12 = this;
            java.lang.String r0 = "schema"
            r2 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "context"
            r3 = r14
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "factory"
            r5 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "callback"
            r6 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r10 = 128(0x80, float:1.794E-43)
            r11 = 0
            r9 = 0
            r1 = r12
            r4 = r15
            r7 = r18
            r8 = r19
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.driver.android.AndroidSqliteDriver.<init>(app.cash.sqldelight.db.SqlSchema, android.content.Context, java.lang.String, androidx.sqlite.db.SupportSQLiteOpenHelper$Factory, androidx.sqlite.db.SupportSQLiteOpenHelper$Callback, int, boolean):void");
    }

    private AndroidSqliteDriver(SupportSQLiteOpenHelper supportSQLiteOpenHelper, SupportSQLiteDatabase supportSQLiteDatabase, int i3, Long l2) {
        this.openHelper = supportSQLiteOpenHelper;
        this.cacheSize = i3;
        this.windowSizeBytes = l2;
        if ((supportSQLiteOpenHelper != null) ^ (supportSQLiteDatabase != null)) {
            this.transactions = new ThreadLocal<>();
            this.database$delegate = LazyKt.lazy(new AndroidSqliteDriver$database$2(this, supportSQLiteDatabase));
            this.statements = new AndroidSqliteDriver$statements$1(i3);
            this.listeners = new LinkedHashMap<>();
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AndroidSqliteDriver(SupportSQLiteOpenHelper supportSQLiteOpenHelper, SupportSQLiteDatabase supportSQLiteDatabase, int i3, Long l2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : supportSQLiteOpenHelper, (i4 & 2) != 0 ? null : supportSQLiteDatabase, i3, (i4 & 8) != 0 ? null : l2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AndroidSqliteDriver(@NotNull SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        this(supportSQLiteOpenHelper, (SupportSQLiteDatabase) null, 20, (Long) null);
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper, "openHelper");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ AndroidSqliteDriver(app.cash.sqldelight.db.SqlSchema r13, android.content.Context r14, java.lang.String r15, androidx.sqlite.db.SupportSQLiteOpenHelper.Factory r16, androidx.sqlite.db.SupportSQLiteOpenHelper.Callback r17, int r18, boolean r19, java.lang.Long r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r12 = this;
            r0 = r21
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r6 = r2
            goto L_0x000a
        L_0x0009:
            r6 = r15
        L_0x000a:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0015
            androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory r1 = new androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
            r1.<init>()
            r7 = r1
            goto L_0x0017
        L_0x0015:
            r7 = r16
        L_0x0017:
            r1 = r0 & 16
            r3 = 0
            if (r1 == 0) goto L_0x0026
            app.cash.sqldelight.driver.android.AndroidSqliteDriver$Callback r1 = new app.cash.sqldelight.driver.android.AndroidSqliteDriver$Callback
            app.cash.sqldelight.db.AfterVersion[] r4 = new app.cash.sqldelight.db.AfterVersion[r3]
            r5 = r13
            r1.<init>(r13, r4)
            r8 = r1
            goto L_0x0029
        L_0x0026:
            r5 = r13
            r8 = r17
        L_0x0029:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0031
            r1 = 20
            r9 = r1
            goto L_0x0033
        L_0x0031:
            r9 = r18
        L_0x0033:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0039
            r10 = r3
            goto L_0x003b
        L_0x0039:
            r10 = r19
        L_0x003b:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0041
            r11 = r2
            goto L_0x0043
        L_0x0041:
            r11 = r20
        L_0x0043:
            r3 = r12
            r4 = r13
            r5 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.driver.android.AndroidSqliteDriver.<init>(app.cash.sqldelight.db.SqlSchema, android.content.Context, java.lang.String, androidx.sqlite.db.SupportSQLiteOpenHelper$Factory, androidx.sqlite.db.SupportSQLiteOpenHelper$Callback, int, boolean, java.lang.Long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AndroidSqliteDriver(@NotNull SqlSchema<QueryResult.Value<Unit>> sqlSchema, @NotNull Context context, @Nullable String str, @NotNull SupportSQLiteOpenHelper.Factory factory, @NotNull SupportSQLiteOpenHelper.Callback callback, int i3, boolean z2, @Nullable Long l2) {
        this(factory.create(SupportSQLiteOpenHelper.Configuration.Companion.builder(context).callback(callback).name(str).noBackupDirectory(z2).build()), (SupportSQLiteDatabase) null, i3, l2);
        Intrinsics.checkNotNullParameter(sqlSchema, "schema");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(callback, com.sun.jna.Callback.METHOD_NAME);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AndroidSqliteDriver(SupportSQLiteDatabase supportSQLiteDatabase, int i3, Long l2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(supportSQLiteDatabase, (i4 & 2) != 0 ? 20 : i3, (i4 & 4) != 0 ? null : l2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AndroidSqliteDriver(@NotNull SupportSQLiteDatabase supportSQLiteDatabase, int i3, @Nullable Long l2) {
        this((SupportSQLiteOpenHelper) null, supportSQLiteDatabase, i3, l2);
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
    }
}
