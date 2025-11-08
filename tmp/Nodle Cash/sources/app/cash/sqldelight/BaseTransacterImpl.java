package app.cash.sqldelight;

import android.support.v4.media.session.a;
import androidx.constraintlayout.core.state.b;
import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.db.SqlDriver;
import io.nodle.cash.ui.feature.chat.ConversationActivity;
import java.util.Arrays;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0004J0\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u001e\u0010\u000e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u000f\u0012\u0004\u0012\u00020\f0\u000fH\u0004J9\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u0001H\u0011H\u0004¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0019"}, d2 = {"Lapp/cash/sqldelight/BaseTransacterImpl;", "", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "getDriver", "()Lapp/cash/sqldelight/db/SqlDriver;", "createArguments", "", "count", "", "notifyQueries", "", "identifier", "tableProvider", "Lkotlin/Function1;", "postTransactionCleanup", "R", "transaction", "Lapp/cash/sqldelight/Transacter$Transaction;", "enclosing", "thrownException", "", "returnValue", "(Lapp/cash/sqldelight/Transacter$Transaction;Lapp/cash/sqldelight/Transacter$Transaction;Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Object;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTransacter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Transacter.kt\napp/cash/sqldelight/BaseTransacterImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,417:1\n1855#2,2:418\n1855#2,2:422\n37#3,2:420\n37#3,2:424\n*S KotlinDebug\n*F\n+ 1 Transacter.kt\napp/cash/sqldelight/BaseTransacterImpl\n*L\n268#1:418,2\n282#1:422,2\n278#1:420,2\n321#1:424,2\n*E\n"})
public abstract class BaseTransacterImpl {
    @NotNull
    private final SqlDriver driver;

    public BaseTransacterImpl(@NotNull SqlDriver sqlDriver) {
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        this.driver = sqlDriver;
    }

    @NotNull
    public final String createArguments(int i3) {
        if (i3 == 0) {
            return "()";
        }
        StringBuilder o3 = b.o(i3 + 2, "(?");
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i4; i5++) {
            o3.append(",?");
        }
        return a.i(')', "toString(...)", o3);
    }

    @NotNull
    public final SqlDriver getDriver() {
        return this.driver;
    }

    public final void notifyQueries(int i3, @NotNull Function1<? super Function1<? super String, Unit>, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "tableProvider");
        Transacter.Transaction currentTransaction = this.driver.currentTransaction();
        if (currentTransaction == null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            function1.invoke(new BaseTransacterImpl$notifyQueries$2(linkedHashSet));
            SqlDriver sqlDriver = this.driver;
            String[] strArr = (String[]) linkedHashSet.toArray(new String[0]);
            sqlDriver.notifyListeners((String[]) Arrays.copyOf(strArr, strArr.length));
        } else if (currentTransaction.getRegisteredQueries$runtime().add(Integer.valueOf(i3))) {
            function1.invoke(new BaseTransacterImpl$notifyQueries$1(currentTransaction));
        }
    }

    public final <R> R postTransactionCleanup(@NotNull Transacter.Transaction transaction, @Nullable Transacter.Transaction transaction2, @Nullable Throwable th, @Nullable R r2) {
        Intrinsics.checkNotNullParameter(transaction, ConversationActivity.MODE_TRANSACTION);
        boolean z2 = false;
        if (transaction2 != null) {
            if (transaction.getSuccessful$runtime() && transaction.getChildrenSuccessful$runtime()) {
                z2 = true;
            }
            transaction2.setChildrenSuccessful$runtime(z2);
            transaction2.getPostCommitHooks$runtime().addAll(transaction.getPostCommitHooks$runtime());
            transaction2.getPostRollbackHooks$runtime().addAll(transaction.getPostRollbackHooks$runtime());
            transaction2.getRegisteredQueries$runtime().addAll(transaction.getRegisteredQueries$runtime());
            transaction2.getPendingTables$runtime().addAll(transaction.getPendingTables$runtime());
        } else if (!transaction.getSuccessful$runtime() || !transaction.getChildrenSuccessful$runtime()) {
            try {
                for (Function0 invoke : transaction.getPostRollbackHooks$runtime()) {
                    invoke.invoke();
                }
                transaction.getPostRollbackHooks$runtime().clear();
            } catch (Throwable th2) {
                if (th != null) {
                    throw new Throwable("Exception while rolling back from an exception.\nOriginal exception: " + th + "\nwith cause " + th.getCause() + "\n\nRollback exception: " + th2, th2);
                }
                throw th2;
            }
        } else {
            if (!transaction.getPendingTables$runtime().isEmpty()) {
                SqlDriver sqlDriver = this.driver;
                String[] strArr = (String[]) transaction.getPendingTables$runtime().toArray(new String[0]);
                sqlDriver.notifyListeners((String[]) Arrays.copyOf(strArr, strArr.length));
            }
            transaction.getPendingTables$runtime().clear();
            transaction.getRegisteredQueries$runtime().clear();
            for (Function0 invoke2 : transaction.getPostCommitHooks$runtime()) {
                invoke2.invoke();
            }
            transaction.getPostCommitHooks$runtime().clear();
        }
        if (transaction2 == null && (th instanceof RollbackException)) {
            return ((RollbackException) th).getValue();
        }
        if (th == null) {
            return r2;
        }
        throw th;
    }
}
