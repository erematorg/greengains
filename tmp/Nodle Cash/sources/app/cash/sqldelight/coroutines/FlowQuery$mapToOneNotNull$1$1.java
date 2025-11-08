package app.cash.sqldelight.coroutines;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.async.coroutines.QueryExtensionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u0003H@"}, d2 = {"<anonymous>", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "app.cash.sqldelight.coroutines.FlowQuery$mapToOneNotNull$1$1", f = "FlowExtensions.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {})
public final class FlowQuery$mapToOneNotNull$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Query<T> $it;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowQuery$mapToOneNotNull$1$1(Query<? extends T> query, Continuation<? super FlowQuery$mapToOneNotNull$1$1> continuation) {
        super(2, continuation);
        this.$it = query;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FlowQuery$mapToOneNotNull$1$1(this.$it, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Query<T> query = this.$it;
            this.label = 1;
            obj = QueryExtensionsKt.awaitAsOneOrNull(query, this);
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
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super T> continuation) {
        return ((FlowQuery$mapToOneNotNull$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
