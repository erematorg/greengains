package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.db.AfterVersion;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001J#\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\fJG\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011\"\u00020\u0012H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0015"}, d2 = {"app/cash/sqldelight/async/coroutines/SynchronousKt$synchronous$1", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "version", "", "getVersion", "()J", "create", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "create-0iQ1-z0", "(Lapp/cash/sqldelight/db/SqlDriver;)Ljava/lang/Object;", "migrate", "oldVersion", "newVersion", "callbacks", "", "Lapp/cash/sqldelight/db/AfterVersion;", "migrate-zeHU3Mk", "(Lapp/cash/sqldelight/db/SqlDriver;JJ[Lapp/cash/sqldelight/db/AfterVersion;)Ljava/lang/Object;", "sqldelight-async-extensions"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SynchronousKt$synchronous$1 implements SqlSchema<QueryResult.Value<Unit>> {
    final /* synthetic */ SqlSchema<QueryResult.AsyncValue<Unit>> $this_synchronous;
    private final long version;

    public SynchronousKt$synchronous$1(SqlSchema<QueryResult.AsyncValue<Unit>> sqlSchema) {
        this.$this_synchronous = sqlSchema;
        this.version = sqlSchema.getVersion();
    }

    public /* bridge */ /* synthetic */ QueryResult create(SqlDriver sqlDriver) {
        return QueryResult.Value.m8089boximpl(m8076create0iQ1z0(sqlDriver));
    }

    @NotNull
    /* renamed from: create-0iQ1-z0  reason: not valid java name */
    public Object m8076create0iQ1z0(@NotNull SqlDriver sqlDriver) {
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        return QueryResult.Value.m8090constructorimpl(BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SynchronousKt$synchronous$1$create$1(this.$this_synchronous, sqlDriver, (Continuation<? super SynchronousKt$synchronous$1$create$1>) null), 1, (Object) null));
    }

    public long getVersion() {
        return this.version;
    }

    public /* bridge */ /* synthetic */ QueryResult migrate(SqlDriver sqlDriver, long j2, long j3, AfterVersion[] afterVersionArr) {
        return QueryResult.Value.m8089boximpl(m8077migratezeHU3Mk(sqlDriver, j2, j3, afterVersionArr));
    }

    @NotNull
    /* renamed from: migrate-zeHU3Mk  reason: not valid java name */
    public Object m8077migratezeHU3Mk(@NotNull SqlDriver sqlDriver, long j2, long j3, @NotNull AfterVersion... afterVersionArr) {
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        AfterVersion[] afterVersionArr2 = afterVersionArr;
        Intrinsics.checkNotNullParameter(afterVersionArr2, "callbacks");
        return QueryResult.Value.m8090constructorimpl(BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new SynchronousKt$synchronous$1$migrate$1(this.$this_synchronous, sqlDriver, j2, j3, afterVersionArr2, (Continuation<? super SynchronousKt$synchronous$1$migrate$1>) null), 1, (Object) null));
    }
}
