package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.ExecutableQuery;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lapp/cash/sqldelight/db/QueryResult;", "", "T", "", "cursor", "Lapp/cash/sqldelight/db/SqlCursor;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class QueryExtensionsKt$awaitAsList$2 extends Lambda implements Function1<SqlCursor, QueryResult<List<T>>> {
    final /* synthetic */ ExecutableQuery<T> $this_awaitAsList;

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsList$2$1", f = "QueryExtensions.kt", i = {}, l = {14, 15}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsList$2$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super List<T>>, Object> {
        int label;

        @NotNull
        public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
            return new AnonymousClass1(next, arrayList, executableQuery, sqlCursor2, continuation);
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0053 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x005c  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L_0x001e
                if (r1 == r3) goto L_0x001a
                if (r1 != r2) goto L_0x0012
                kotlin.ResultKt.throwOnFailure(r5)
                goto L_0x0054
            L_0x0012:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L_0x001a:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L_0x002c
            L_0x001e:
                kotlin.ResultKt.throwOnFailure(r5)
                app.cash.sqldelight.db.QueryResult<java.lang.Boolean> r5 = r2
                r4.label = r3
                java.lang.Object r5 = r5.await(r4)
                if (r5 != r0) goto L_0x002c
                return r0
            L_0x002c:
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                boolean r5 = r5.booleanValue()
                if (r5 == 0) goto L_0x0071
                java.util.List<T> r5 = r3
                app.cash.sqldelight.ExecutableQuery<T> r1 = r4
                kotlin.jvm.functions.Function1 r1 = r1.getMapper()
                app.cash.sqldelight.db.SqlCursor r3 = r5
                java.lang.Object r1 = r1.invoke(r3)
                r5.add(r1)
            L_0x0045:
                app.cash.sqldelight.db.SqlCursor r5 = r5
                app.cash.sqldelight.db.QueryResult r5 = r5.next()
                r4.label = r2
                java.lang.Object r5 = r5.await(r4)
                if (r5 != r0) goto L_0x0054
                return r0
            L_0x0054:
                java.lang.Boolean r5 = (java.lang.Boolean) r5
                boolean r5 = r5.booleanValue()
                if (r5 == 0) goto L_0x006e
                java.util.List<T> r5 = r3
                app.cash.sqldelight.ExecutableQuery<T> r1 = r4
                kotlin.jvm.functions.Function1 r1 = r1.getMapper()
                app.cash.sqldelight.db.SqlCursor r3 = r5
                java.lang.Object r1 = r1.invoke(r3)
                r5.add(r1)
                goto L_0x0045
            L_0x006e:
                java.util.List<T> r4 = r3
                return r4
            L_0x0071:
                java.util.List<T> r4 = r3
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsList$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Nullable
        public final Object invoke(@Nullable Continuation<? super List<T>> continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QueryExtensionsKt$awaitAsList$2(ExecutableQuery<? extends T> executableQuery) {
        super(1);
        this.$this_awaitAsList = executableQuery;
    }

    @NotNull
    public final QueryResult<List<T>> invoke(@NotNull SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        final QueryResult<Boolean> next = sqlCursor.next();
        final ArrayList arrayList = new ArrayList();
        if (next instanceof QueryResult.AsyncValue) {
            final ExecutableQuery<T> executableQuery = this.$this_awaitAsList;
            final SqlCursor sqlCursor2 = sqlCursor;
            return QueryResult.AsyncValue.m8079boximpl(QueryResult.AsyncValue.m8080constructorimpl(new AnonymousClass1((Continuation<? super AnonymousClass1>) null)));
        } else if (!(next instanceof QueryResult.Value)) {
            throw new NoWhenBranchMatchedException();
        } else if (!next.getValue().booleanValue()) {
            return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(arrayList));
        } else {
            arrayList.add(this.$this_awaitAsList.getMapper().invoke(sqlCursor));
            while (sqlCursor.next().getValue().booleanValue()) {
                arrayList.add(this.$this_awaitAsList.getMapper().invoke(sqlCursor));
            }
            return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(arrayList));
        }
    }
}
