package app.cash.sqldelight;

import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.db.SqlDriver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J)\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0002\b\rH\u0016J:\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\b\u001a\u00020\t2\u001d\u0010\u0010\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0011\u0012\u0004\u0012\u0002H\u000f0\u000b¢\u0006\u0002\b\rH\u0016¢\u0006\u0002\u0010\u0012J:\u0010\u0013\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\b\u001a\u00020\t2\u001d\u0010\u0014\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0015\u0012\u0004\u0012\u0002H\u000f0\u000b¢\u0006\u0002\b\rH\u0002¢\u0006\u0002\u0010\u0012¨\u0006\u0016"}, d2 = {"Lapp/cash/sqldelight/TransacterImpl;", "Lapp/cash/sqldelight/BaseTransacterImpl;", "Lapp/cash/sqldelight/Transacter;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "transaction", "", "noEnclosing", "", "body", "Lkotlin/Function1;", "Lapp/cash/sqldelight/TransactionWithoutReturn;", "Lkotlin/ExtensionFunctionType;", "transactionWithResult", "R", "bodyWithReturn", "Lapp/cash/sqldelight/TransactionWithReturn;", "(ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "transactionWithWrapper", "wrapperBody", "Lapp/cash/sqldelight/TransactionWrapper;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTransacter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Transacter.kt\napp/cash/sqldelight/TransacterImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,417:1\n1#2:418\n*E\n"})
public abstract class TransacterImpl extends BaseTransacterImpl implements Transacter {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TransacterImpl(@NotNull SqlDriver sqlDriver) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
    }

    private final <R> R transactionWithWrapper(boolean z2, Function1<? super TransactionWrapper<R>, ? extends R> function1) {
        Object obj;
        Transacter.Transaction value = getDriver().newTransaction().getValue();
        Transacter.Transaction enclosingTransaction$runtime = value.enclosingTransaction$runtime();
        if (enclosingTransaction$runtime == null || !z2) {
            Throwable th = null;
            try {
                value.setTransacter$runtime(this);
                obj = function1.invoke(new TransactionWrapper(value));
                try {
                    value.setSuccessful$runtime(true);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                obj = null;
                th = th4;
            }
            value.endTransaction$runtime();
            return postTransactionCleanup(value, enclosingTransaction$runtime, th, obj);
        }
        throw new IllegalStateException("Already in a transaction");
    }

    public void transaction(boolean z2, @NotNull Function1<? super TransactionWithoutReturn, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "body");
        transactionWithWrapper(z2, function1);
    }

    public <R> R transactionWithResult(boolean z2, @NotNull Function1<? super TransactionWithReturn<R>, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(function1, "bodyWithReturn");
        return transactionWithWrapper(z2, function1);
    }
}
