package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lapp/cash/sqldelight/db/QueryResult;", "R", "it", "Lapp/cash/sqldelight/db/SqlCursor;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class DriverExtensionsKt$awaitQuery$2 extends Lambda implements Function1<SqlCursor, QueryResult<R>> {
    final /* synthetic */ Function2<SqlCursor, Continuation<? super R>, Object> $mapper;

    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H@"}, d2 = {"<anonymous>", "R"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "app.cash.sqldelight.async.coroutines.DriverExtensionsKt$awaitQuery$2$1", f = "DriverExtensions.kt", i = {}, l = {15}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: app.cash.sqldelight.async.coroutines.DriverExtensionsKt$awaitQuery$2$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super R>, Object> {
        int label;

        @NotNull
        public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
            return new AnonymousClass1(function2, sqlCursor, continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                Function2<SqlCursor, Continuation<? super R>, Object> function2 = function2;
                SqlCursor sqlCursor = sqlCursor;
                this.label = 1;
                obj = function2.invoke(sqlCursor, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return obj;
        }

        @Nullable
        public final Object invoke(@Nullable Continuation<? super R> continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DriverExtensionsKt$awaitQuery$2(Function2<? super SqlCursor, ? super Continuation<? super R>, ? extends Object> function2) {
        super(1);
        this.$mapper = function2;
    }

    @NotNull
    public final QueryResult<R> invoke(@NotNull final SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "it");
        final Function2<SqlCursor, Continuation<? super R>, Object> function2 = this.$mapper;
        return QueryResult.AsyncValue.m8079boximpl(QueryResult.AsyncValue.m8080constructorimpl(new AnonymousClass1((Continuation<? super AnonymousClass1>) null)));
    }
}
