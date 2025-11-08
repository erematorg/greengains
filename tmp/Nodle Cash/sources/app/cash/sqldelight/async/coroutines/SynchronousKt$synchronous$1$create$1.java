package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "app.cash.sqldelight.async.coroutines.SynchronousKt$synchronous$1$create$1", f = "Synchronous.kt", i = {}, l = {14}, m = "invokeSuspend", n = {}, s = {})
public final class SynchronousKt$synchronous$1$create$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SqlDriver $driver;
    final /* synthetic */ SqlSchema<QueryResult.AsyncValue<Unit>> $this_synchronous;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SynchronousKt$synchronous$1$create$1(SqlSchema<QueryResult.AsyncValue<Unit>> sqlSchema, SqlDriver sqlDriver, Continuation<? super SynchronousKt$synchronous$1$create$1> continuation) {
        super(2, continuation);
        this.$this_synchronous = sqlSchema;
        this.$driver = sqlDriver;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SynchronousKt$synchronous$1$create$1(this.$this_synchronous, this.$driver, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Function1 r4 = this.$this_synchronous.create(this.$driver).m8086unboximpl();
            this.label = 1;
            if (QueryResult.AsyncValue.m8078awaitimpl(r4, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SynchronousKt$synchronous$1$create$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
