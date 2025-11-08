package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.ExecutableQuery;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nQueryExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 QueryExtensions.kt\napp/cash/sqldelight/async/coroutines/QueryExtensionsKt$awaitAsOneOrNull$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,55:1\n1#2:56\n*E\n"})
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lapp/cash/sqldelight/db/QueryResult;", "T", "", "cursor", "Lapp/cash/sqldelight/db/SqlCursor;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class QueryExtensionsKt$awaitAsOneOrNull$2 extends Lambda implements Function1<SqlCursor, QueryResult<T>> {
    final /* synthetic */ ExecutableQuery<T> $this_awaitAsOneOrNull;

    @SourceDebugExtension({"SMAP\nQueryExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 QueryExtensions.kt\napp/cash/sqldelight/async/coroutines/QueryExtensionsKt$awaitAsOneOrNull$2$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,55:1\n1#2:56\n*E\n"})
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0010\u0000\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "T", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsOneOrNull$2$1", f = "QueryExtensions.kt", i = {1}, l = {40, 42}, m = "invokeSuspend", n = {"value"}, s = {"L$0"})
    /* renamed from: app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsOneOrNull$2$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super T>, Object> {
        Object L$0;
        int label;

        @NotNull
        public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
            return new AnonymousClass1(next, executableQuery, sqlCursor, continuation);
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x0061 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0062  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L_0x0020
                if (r1 == r3) goto L_0x001c
                if (r1 != r2) goto L_0x0014
                java.lang.Object r0 = r4.L$0
                kotlin.ResultKt.throwOnFailure(r5)
                goto L_0x0057
            L_0x0014:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L_0x001c:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L_0x002e
            L_0x0020:
                kotlin.ResultKt.throwOnFailure(r5)
                app.cash.sqldelight.db.QueryResult<java.lang.Boolean> r5 = r0
                r4.label = r3
                java.lang.Object r5 = r5.await(r4)
                if (r5 != r0) goto L_0x002e
                return r0
            L_0x002e:
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                boolean r5 = r5.booleanValue()
                if (r5 != 0) goto L_0x0038
                r4 = 0
                return r4
            L_0x0038:
                app.cash.sqldelight.ExecutableQuery<T> r5 = r3
                kotlin.jvm.functions.Function1 r5 = r5.getMapper()
                app.cash.sqldelight.db.SqlCursor r1 = r4
                java.lang.Object r5 = r5.invoke(r1)
                app.cash.sqldelight.db.SqlCursor r1 = r4
                app.cash.sqldelight.db.QueryResult r1 = r1.next()
                r4.L$0 = r5
                r4.label = r2
                java.lang.Object r1 = r1.await(r4)
                if (r1 != r0) goto L_0x0055
                return r0
            L_0x0055:
                r0 = r5
                r5 = r1
            L_0x0057:
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                boolean r5 = r5.booleanValue()
                app.cash.sqldelight.ExecutableQuery<T> r4 = r3
                if (r5 != 0) goto L_0x0062
                return r0
            L_0x0062:
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                java.lang.String r0 = "ResultSet returned more than 1 row for "
                r5.<init>(r0)
                r5.append(r4)
                java.lang.String r4 = r5.toString()
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r4 = r4.toString()
                r5.<init>(r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsOneOrNull$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Nullable
        public final Object invoke(@Nullable Continuation<? super T> continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QueryExtensionsKt$awaitAsOneOrNull$2(ExecutableQuery<? extends T> executableQuery) {
        super(1);
        this.$this_awaitAsOneOrNull = executableQuery;
    }

    @NotNull
    public final QueryResult<T> invoke(@NotNull final SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        final QueryResult<Boolean> next = sqlCursor.next();
        if (next instanceof QueryResult.AsyncValue) {
            final ExecutableQuery<T> executableQuery = this.$this_awaitAsOneOrNull;
            return QueryResult.AsyncValue.m8079boximpl(QueryResult.AsyncValue.m8080constructorimpl(new AnonymousClass1((Continuation<? super AnonymousClass1>) null)));
        } else if (!(next instanceof QueryResult.Value)) {
            throw new NoWhenBranchMatchedException();
        } else if (!next.getValue().booleanValue()) {
            return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(null));
        } else {
            T invoke = this.$this_awaitAsOneOrNull.getMapper().invoke(sqlCursor);
            boolean booleanValue = sqlCursor.next().getValue().booleanValue();
            ExecutableQuery<T> executableQuery2 = this.$this_awaitAsOneOrNull;
            if (!booleanValue) {
                return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(invoke));
            }
            throw new IllegalStateException(("ResultSet returned more than 1 row for " + executableQuery2).toString());
        }
    }
}
