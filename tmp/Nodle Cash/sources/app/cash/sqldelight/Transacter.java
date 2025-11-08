package app.cash.sqldelight;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.internal.CurrentThreadIdKt;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u000fJ+\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\b\tH&J<\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001d\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\r\u0012\u0004\u0012\u0002H\u000b0\u0007¢\u0006\u0002\b\tH&¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"Lapp/cash/sqldelight/Transacter;", "Lapp/cash/sqldelight/TransacterBase;", "transaction", "", "noEnclosing", "", "body", "Lkotlin/Function1;", "Lapp/cash/sqldelight/TransactionWithoutReturn;", "Lkotlin/ExtensionFunctionType;", "transactionWithResult", "R", "bodyWithReturn", "Lapp/cash/sqldelight/TransactionWithReturn;", "(ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Transaction", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface Transacter extends TransacterBase {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void transaction$default(Transacter transacter, boolean z2, Function1 function1, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                transacter.transaction(z2, function1);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transaction");
        }

        public static /* synthetic */ Object transactionWithResult$default(Transacter transacter, boolean z2, Function1 function1, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return transacter.transactionWithResult(z2, function1);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: transactionWithResult");
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010'\u001a\u00020\u00162\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0016J\u0016\u0010)\u001a\u00020\u00162\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0016J\r\u0010*\u001a\u00020\u0016H\u0000¢\u0006\u0002\b+J\u000f\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0000¢\u0006\u0002\b,J\u0013\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00160.H\u0000¢\u0006\u0002\b/J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00160.2\u0006\u0010\u001e\u001a\u00020\u0004H$R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\u0000X¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u001a\u0010\u001e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u00060"}, d2 = {"Lapp/cash/sqldelight/Transacter$Transaction;", "Lapp/cash/sqldelight/TransactionCallbacks;", "()V", "childrenSuccessful", "", "getChildrenSuccessful$runtime", "()Z", "setChildrenSuccessful$runtime", "(Z)V", "enclosingTransaction", "getEnclosingTransaction", "()Lapp/cash/sqldelight/Transacter$Transaction;", "ownerThreadId", "", "pendingTables", "", "", "getPendingTables$runtime", "()Ljava/util/Set;", "postCommitHooks", "", "Lkotlin/Function0;", "", "getPostCommitHooks$runtime", "()Ljava/util/List;", "postRollbackHooks", "getPostRollbackHooks$runtime", "registeredQueries", "", "getRegisteredQueries$runtime", "successful", "getSuccessful$runtime", "setSuccessful$runtime", "transacter", "Lapp/cash/sqldelight/TransacterBase;", "getTransacter$runtime", "()Lapp/cash/sqldelight/TransacterBase;", "setTransacter$runtime", "(Lapp/cash/sqldelight/TransacterBase;)V", "afterCommit", "function", "afterRollback", "checkThreadConfinement", "checkThreadConfinement$runtime", "enclosingTransaction$runtime", "endTransaction", "Lapp/cash/sqldelight/db/QueryResult;", "endTransaction$runtime", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static abstract class Transaction implements TransactionCallbacks {
        private boolean childrenSuccessful = true;
        private final long ownerThreadId = CurrentThreadIdKt.currentThreadId();
        @NotNull
        private final Set<String> pendingTables = new LinkedHashSet();
        @NotNull
        private final List<Function0<Unit>> postCommitHooks = new ArrayList();
        @NotNull
        private final List<Function0<Unit>> postRollbackHooks = new ArrayList();
        @NotNull
        private final Set<Integer> registeredQueries = new LinkedHashSet();
        private boolean successful;
        @Nullable
        private TransacterBase transacter;

        public void afterCommit(@NotNull Function0<Unit> function0) {
            Intrinsics.checkNotNullParameter(function0, "function");
            checkThreadConfinement$runtime();
            this.postCommitHooks.add(function0);
        }

        public void afterRollback(@NotNull Function0<Unit> function0) {
            Intrinsics.checkNotNullParameter(function0, "function");
            checkThreadConfinement$runtime();
            this.postRollbackHooks.add(function0);
        }

        public final void checkThreadConfinement$runtime() {
            if (this.ownerThreadId != CurrentThreadIdKt.currentThreadId()) {
                throw new IllegalStateException("Transaction objects (`TransactionWithReturn` and `TransactionWithoutReturn`) must be used\nonly within the transaction lambda scope.");
            }
        }

        @Nullable
        public final Transaction enclosingTransaction$runtime() {
            return getEnclosingTransaction();
        }

        @NotNull
        public abstract QueryResult<Unit> endTransaction(boolean z2);

        @NotNull
        public final QueryResult<Unit> endTransaction$runtime() {
            checkThreadConfinement$runtime();
            return endTransaction(this.successful && this.childrenSuccessful);
        }

        public final boolean getChildrenSuccessful$runtime() {
            return this.childrenSuccessful;
        }

        @Nullable
        public abstract Transaction getEnclosingTransaction();

        @NotNull
        public final Set<String> getPendingTables$runtime() {
            return this.pendingTables;
        }

        @NotNull
        public final List<Function0<Unit>> getPostCommitHooks$runtime() {
            return this.postCommitHooks;
        }

        @NotNull
        public final List<Function0<Unit>> getPostRollbackHooks$runtime() {
            return this.postRollbackHooks;
        }

        @NotNull
        public final Set<Integer> getRegisteredQueries$runtime() {
            return this.registeredQueries;
        }

        public final boolean getSuccessful$runtime() {
            return this.successful;
        }

        @Nullable
        public final TransacterBase getTransacter$runtime() {
            return this.transacter;
        }

        public final void setChildrenSuccessful$runtime(boolean z2) {
            this.childrenSuccessful = z2;
        }

        public final void setSuccessful$runtime(boolean z2) {
            this.successful = z2;
        }

        public final void setTransacter$runtime(@Nullable TransacterBase transacterBase) {
            this.transacter = transacterBase;
        }
    }

    void transaction(boolean z2, @NotNull Function1<? super TransactionWithoutReturn, Unit> function1);

    <R> R transactionWithResult(boolean z2, @NotNull Function1<? super TransactionWithReturn<R>, ? extends R> function1);
}
