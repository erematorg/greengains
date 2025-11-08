package app.cash.sqldelight;

import app.cash.sqldelight.Transacter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class BaseTransacterImpl$notifyQueries$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Transacter.Transaction $transaction;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseTransacterImpl$notifyQueries$1(Transacter.Transaction transaction) {
        super(1);
        this.$transaction = transaction;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        this.$transaction.getPendingTables$runtime().add(str);
    }
}
