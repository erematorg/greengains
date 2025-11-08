package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001aI\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u001b\b\u0002\u0010\b\u001a\u0015\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t¢\u0006\u0002\b\fH@¢\u0006\u0002\u0010\r\u001a\u001e\u0010\u000e\u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H@¢\u0006\u0002\u0010\u0011\u001a.\u0010\u0012\u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H@¢\u0006\u0002\u0010\u0015\u001as\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u0017*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\"\u0010\u0018\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00170\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00192\u0006\u0010\u0007\u001a\u00020\u00042\u001b\b\u0002\u0010\b\u001a\u0015\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t¢\u0006\u0002\b\fH@¢\u0006\u0002\u0010\u001d¨\u0006\u001e"}, d2 = {"await", "", "Lapp/cash/sqldelight/db/SqlDriver;", "identifier", "", "sql", "", "parameters", "binders", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlPreparedStatement;", "", "Lkotlin/ExtensionFunctionType;", "(Lapp/cash/sqldelight/db/SqlDriver;Ljava/lang/Integer;Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitCreate", "Lapp/cash/sqldelight/db/SqlSchema;", "driver", "(Lapp/cash/sqldelight/db/SqlSchema;Lapp/cash/sqldelight/db/SqlDriver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitMigrate", "oldVersion", "newVersion", "(Lapp/cash/sqldelight/db/SqlSchema;Lapp/cash/sqldelight/db/SqlDriver;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitQuery", "R", "mapper", "Lkotlin/Function2;", "Lapp/cash/sqldelight/db/SqlCursor;", "Lkotlin/coroutines/Continuation;", "", "(Lapp/cash/sqldelight/db/SqlDriver;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function2;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sqldelight-async-extensions"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class DriverExtensionsKt {
    @Nullable
    public static final Object await(@NotNull SqlDriver sqlDriver, @Nullable Integer num, @NotNull String str, int i3, @Nullable Function1<? super SqlPreparedStatement, Unit> function1, @NotNull Continuation<? super Long> continuation) {
        return sqlDriver.execute(num, str, i3, function1).await(continuation);
    }

    public static /* synthetic */ Object await$default(SqlDriver sqlDriver, Integer num, String str, int i3, Function1 function1, Continuation continuation, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            function1 = null;
        }
        return await(sqlDriver, num, str, i3, function1, continuation);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [app.cash.sqldelight.db.SqlSchema<?>, app.cash.sqldelight.db.SqlSchema] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object awaitCreate(@org.jetbrains.annotations.NotNull app.cash.sqldelight.db.SqlSchema<?> r0, @org.jetbrains.annotations.NotNull app.cash.sqldelight.db.SqlDriver r1, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r2) {
        /*
            app.cash.sqldelight.db.QueryResult r0 = r0.create(r1)
            java.lang.Object r0 = r0.await(r2)
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r1) goto L_0x000f
            return r0
        L_0x000f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.async.coroutines.DriverExtensionsKt.awaitCreate(app.cash.sqldelight.db.SqlSchema, app.cash.sqldelight.db.SqlDriver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: app.cash.sqldelight.db.SqlSchema<?>} */
    /* JADX WARNING: type inference failed for: r8v1, types: [app.cash.sqldelight.db.QueryResult] */
    /* JADX WARNING: Multi-variable type inference failed */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object awaitMigrate(@org.jetbrains.annotations.NotNull app.cash.sqldelight.db.SqlSchema<?> r8, @org.jetbrains.annotations.NotNull app.cash.sqldelight.db.SqlDriver r9, long r10, long r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r0 = 0
            app.cash.sqldelight.db.AfterVersion[] r7 = new app.cash.sqldelight.db.AfterVersion[r0]
            r1 = r8
            r2 = r9
            r3 = r10
            r5 = r12
            app.cash.sqldelight.db.QueryResult r8 = r1.migrate(r2, r3, r5, r7)
            java.lang.Object r8 = r8.await(r14)
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r8 != r9) goto L_0x0016
            return r8
        L_0x0016:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.async.coroutines.DriverExtensionsKt.awaitMigrate(app.cash.sqldelight.db.SqlSchema, app.cash.sqldelight.db.SqlDriver, long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final <R> Object awaitQuery(@NotNull SqlDriver sqlDriver, @Nullable Integer num, @NotNull String str, @NotNull Function2<? super SqlCursor, ? super Continuation<? super R>, ? extends Object> function2, int i3, @Nullable Function1<? super SqlPreparedStatement, Unit> function1, @NotNull Continuation<? super R> continuation) {
        return sqlDriver.executeQuery(num, str, new DriverExtensionsKt$awaitQuery$2(function2), i3, function1).await(continuation);
    }

    public static /* synthetic */ Object awaitQuery$default(SqlDriver sqlDriver, Integer num, String str, Function2 function2, int i3, Function1 function1, Continuation continuation, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            function1 = null;
        }
        return awaitQuery(sqlDriver, num, str, function2, i3, function1, continuation);
    }
}
