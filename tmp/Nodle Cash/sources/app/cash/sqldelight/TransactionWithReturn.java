package app.cash.sqldelight;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J2\u0010\u0007\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0000\u0012\u0004\u0012\u0002H\u00010\t¢\u0006\u0002\b\nH&¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lapp/cash/sqldelight/TransactionWithReturn;", "R", "Lapp/cash/sqldelight/TransactionCallbacks;", "rollback", "", "returnValue", "(Ljava/lang/Object;)Ljava/lang/Void;", "transaction", "body", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface TransactionWithReturn<R> extends TransactionCallbacks {
    @NotNull
    Void rollback(R r2);

    <R> R transaction(@NotNull Function1<? super TransactionWithReturn<R>, ? extends R> function1);
}
