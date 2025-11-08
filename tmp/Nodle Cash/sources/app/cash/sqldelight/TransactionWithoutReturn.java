package app.cash.sqldelight;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J!\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\b\bH&¨\u0006\t"}, d2 = {"Lapp/cash/sqldelight/TransactionWithoutReturn;", "Lapp/cash/sqldelight/TransactionCallbacks;", "rollback", "", "transaction", "", "body", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface TransactionWithoutReturn extends TransactionCallbacks {
    @NotNull
    Void rollback();

    void transaction(@NotNull Function1<? super TransactionWithoutReturn, Unit> function1);
}
