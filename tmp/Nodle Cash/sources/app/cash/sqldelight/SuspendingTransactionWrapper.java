package app.cash.sqldelight;

import app.cash.sqldelight.Transacter;
import io.nodle.cash.ui.feature.chat.ConversationActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0016J\u0016\u0010\r\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011JC\u0010\u0004\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012-\u0010\u0012\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013¢\u0006\u0002\b\u0016H@¢\u0006\u0002\u0010\u0017J7\u0010\u0018\u001a\u00020\n2'\u0010\u0012\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013¢\u0006\u0002\b\u0016H@¢\u0006\u0002\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lapp/cash/sqldelight/SuspendingTransactionWrapper;", "R", "Lapp/cash/sqldelight/SuspendingTransactionWithoutReturn;", "Lapp/cash/sqldelight/SuspendingTransactionWithReturn;", "transaction", "Lapp/cash/sqldelight/Transacter$Transaction;", "(Lapp/cash/sqldelight/Transacter$Transaction;)V", "getTransaction", "()Lapp/cash/sqldelight/Transacter$Transaction;", "afterCommit", "", "function", "Lkotlin/Function0;", "afterRollback", "rollback", "", "returnValue", "(Ljava/lang/Object;)Ljava/lang/Void;", "body", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transactionWithResult", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class SuspendingTransactionWrapper<R> implements SuspendingTransactionWithoutReturn, SuspendingTransactionWithReturn<R> {
    @NotNull
    private final Transacter.Transaction transaction;

    public SuspendingTransactionWrapper(@NotNull Transacter.Transaction transaction2) {
        Intrinsics.checkNotNullParameter(transaction2, ConversationActivity.MODE_TRANSACTION);
        this.transaction = transaction2;
    }

    public void afterCommit(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "function");
        this.transaction.afterCommit(function0);
    }

    public void afterRollback(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "function");
        this.transaction.afterRollback(function0);
    }

    @NotNull
    public final Transacter.Transaction getTransaction() {
        return this.transaction;
    }

    @NotNull
    public Void rollback() {
        this.transaction.checkThreadConfinement$runtime();
        throw new RollbackException((Object) null, 1, (DefaultConstructorMarker) null);
    }

    @Nullable
    public <R> Object transaction(@NotNull Function2<? super SuspendingTransactionWithReturn<R>, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation) {
        TransacterBase transacter$runtime = this.transaction.getTransacter$runtime();
        Intrinsics.checkNotNull(transacter$runtime, "null cannot be cast to non-null type app.cash.sqldelight.SuspendingTransacter");
        return ((SuspendingTransacter) transacter$runtime).transactionWithResult(false, function2, continuation);
    }

    @Nullable
    public Object transactionWithResult(@NotNull Function2<? super SuspendingTransactionWithoutReturn, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation) {
        TransacterBase transacter$runtime = this.transaction.getTransacter$runtime();
        Intrinsics.checkNotNull(transacter$runtime, "null cannot be cast to non-null type app.cash.sqldelight.SuspendingTransacter");
        Object transaction2 = ((SuspendingTransacter) transacter$runtime).transaction(false, function2, continuation);
        return transaction2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? transaction2 : Unit.INSTANCE;
    }

    @NotNull
    public Void rollback(R r2) {
        this.transaction.checkThreadConfinement$runtime();
        throw new RollbackException(r2);
    }
}
