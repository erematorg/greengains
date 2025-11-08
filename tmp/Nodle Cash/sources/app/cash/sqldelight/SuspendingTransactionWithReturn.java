package app.cash.sqldelight;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006JC\u0010\u0007\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t¢\u0006\u0002\b\fH¦@¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lapp/cash/sqldelight/SuspendingTransactionWithReturn;", "R", "Lapp/cash/sqldelight/TransactionCallbacks;", "rollback", "", "returnValue", "(Ljava/lang/Object;)Ljava/lang/Void;", "transaction", "body", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SuspendingTransactionWithReturn<R> extends TransactionCallbacks {
    @NotNull
    Void rollback(R r2);

    @Nullable
    <R> Object transaction(@NotNull Function2<? super SuspendingTransactionWithReturn<R>, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation);
}
