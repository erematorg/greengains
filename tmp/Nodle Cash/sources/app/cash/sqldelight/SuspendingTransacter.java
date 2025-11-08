package app.cash.sqldelight;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JA\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052'\u0010\u0006\u001a#\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007¢\u0006\u0002\b\u000bH¦@¢\u0006\u0002\u0010\fJM\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\b\b\u0002\u0010\u0004\u001a\u00020\u00052-\u0010\u000f\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007¢\u0006\u0002\b\u000bH¦@¢\u0006\u0002\u0010\f¨\u0006\u0011"}, d2 = {"Lapp/cash/sqldelight/SuspendingTransacter;", "Lapp/cash/sqldelight/TransacterBase;", "transaction", "", "noEnclosing", "", "body", "Lkotlin/Function2;", "Lapp/cash/sqldelight/SuspendingTransactionWithoutReturn;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transactionWithResult", "R", "bodyWithReturn", "Lapp/cash/sqldelight/SuspendingTransactionWithReturn;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SuspendingTransacter extends TransacterBase {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object transaction$default(SuspendingTransacter suspendingTransacter, boolean z2, Function2 function2, Continuation continuation, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return suspendingTransacter.transaction(z2, function2, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transaction");
        }

        public static /* synthetic */ Object transactionWithResult$default(SuspendingTransacter suspendingTransacter, boolean z2, Function2 function2, Continuation continuation, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return suspendingTransacter.transactionWithResult(z2, function2, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transactionWithResult");
        }
    }

    @Nullable
    Object transaction(boolean z2, @NotNull Function2<? super SuspendingTransactionWithoutReturn, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    <R> Object transactionWithResult(boolean z2, @NotNull Function2<? super SuspendingTransactionWithReturn<R>, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation);
}
