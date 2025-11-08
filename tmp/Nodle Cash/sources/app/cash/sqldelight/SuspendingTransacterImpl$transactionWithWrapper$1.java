package app.cash.sqldelight;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "app.cash.sqldelight.SuspendingTransacterImpl", f = "Transacter.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4}, l = {397, 407, 412, 412, 412}, m = "transactionWithWrapper", n = {"this", "wrapperBody", "noEnclosing", "this", "transaction", "enclosing", "this", "transaction", "enclosing", "returnValue", "this", "transaction", "enclosing", "thrownException", "returnValue", "this", "transaction", "enclosing", "returnValue"}, s = {"L$0", "L$1", "Z$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3"})
public final class SuspendingTransacterImpl$transactionWithWrapper$1<R> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SuspendingTransacterImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SuspendingTransacterImpl$transactionWithWrapper$1(SuspendingTransacterImpl suspendingTransacterImpl, Continuation<? super SuspendingTransacterImpl$transactionWithWrapper$1> continuation) {
        super(continuation);
        this.this$0 = suspendingTransacterImpl;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.transactionWithWrapper(false, (Function2) null, this);
    }
}
