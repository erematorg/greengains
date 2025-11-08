package app.cash.sqldelight;

import app.cash.sqldelight.db.SqlDriver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J?\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b¢\u0006\u0002\b\u000fH@¢\u0006\u0002\u0010\u0010JK\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\b\u001a\u00020\t2-\u0010\u0013\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b¢\u0006\u0002\b\u000fH@¢\u0006\u0002\u0010\u0010JK\u0010\u0015\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\b\u001a\u00020\t2-\u0010\u0016\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b¢\u0006\u0002\b\u000fH@¢\u0006\u0002\u0010\u0010¨\u0006\u0018"}, d2 = {"Lapp/cash/sqldelight/SuspendingTransacterImpl;", "Lapp/cash/sqldelight/BaseTransacterImpl;", "Lapp/cash/sqldelight/SuspendingTransacter;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "(Lapp/cash/sqldelight/db/SqlDriver;)V", "transaction", "", "noEnclosing", "", "body", "Lkotlin/Function2;", "Lapp/cash/sqldelight/SuspendingTransactionWithoutReturn;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transactionWithResult", "R", "bodyWithReturn", "Lapp/cash/sqldelight/SuspendingTransactionWithReturn;", "transactionWithWrapper", "wrapperBody", "Lapp/cash/sqldelight/SuspendingTransactionWrapper;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTransacter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Transacter.kt\napp/cash/sqldelight/SuspendingTransacterImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,417:1\n1#2:418\n*E\n"})
public abstract class SuspendingTransacterImpl extends BaseTransacterImpl implements SuspendingTransacter {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SuspendingTransacterImpl(@NotNull SqlDriver sqlDriver) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
    }

    public static /* synthetic */ Object transaction$suspendImpl(SuspendingTransacterImpl suspendingTransacterImpl, boolean z2, Function2<? super SuspendingTransactionWithoutReturn, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object transactionWithWrapper = suspendingTransacterImpl.transactionWithWrapper(z2, function2, continuation);
        return transactionWithWrapper == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? transactionWithWrapper : Unit.INSTANCE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: kotlin.jvm.functions.Function2<? super app.cash.sqldelight.SuspendingTransactionWrapper<R>, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object>} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0104 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x012a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <R> java.lang.Object transactionWithWrapper(boolean r11, kotlin.jvm.functions.Function2<? super app.cash.sqldelight.SuspendingTransactionWrapper<R>, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r12, kotlin.coroutines.Continuation<? super R> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof app.cash.sqldelight.SuspendingTransacterImpl$transactionWithWrapper$1
            if (r0 == 0) goto L_0x0013
            r0 = r13
            app.cash.sqldelight.SuspendingTransacterImpl$transactionWithWrapper$1 r0 = (app.cash.sqldelight.SuspendingTransacterImpl$transactionWithWrapper$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            app.cash.sqldelight.SuspendingTransacterImpl$transactionWithWrapper$1 r0 = new app.cash.sqldelight.SuspendingTransacterImpl$transactionWithWrapper$1
            r0.<init>(r10, r13)
        L_0x0018:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x00a6
            if (r2 == r6) goto L_0x0097
            if (r2 == r5) goto L_0x007a
            if (r2 == r4) goto L_0x0067
            if (r2 == r3) goto L_0x0050
            r10 = 5
            if (r2 != r10) goto L_0x0048
            java.lang.Object r10 = r0.L$3
            java.lang.Object r11 = r0.L$2
            app.cash.sqldelight.Transacter$Transaction r11 = (app.cash.sqldelight.Transacter.Transaction) r11
            java.lang.Object r12 = r0.L$1
            app.cash.sqldelight.Transacter$Transaction r12 = (app.cash.sqldelight.Transacter.Transaction) r12
            java.lang.Object r0 = r0.L$0
            app.cash.sqldelight.SuspendingTransacterImpl r0 = (app.cash.sqldelight.SuspendingTransacterImpl) r0
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r10 = r0.postTransactionCleanup(r12, r11, r7, r10)
            return r10
        L_0x0048:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0050:
            java.lang.Object r10 = r0.L$4
            java.lang.Object r11 = r0.L$3
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.Object r12 = r0.L$2
            app.cash.sqldelight.Transacter$Transaction r12 = (app.cash.sqldelight.Transacter.Transaction) r12
            java.lang.Object r1 = r0.L$1
            app.cash.sqldelight.Transacter$Transaction r1 = (app.cash.sqldelight.Transacter.Transaction) r1
            java.lang.Object r0 = r0.L$0
            app.cash.sqldelight.SuspendingTransacterImpl r0 = (app.cash.sqldelight.SuspendingTransacterImpl) r0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x012e
        L_0x0067:
            java.lang.Object r10 = r0.L$3
            java.lang.Object r11 = r0.L$2
            app.cash.sqldelight.Transacter$Transaction r11 = (app.cash.sqldelight.Transacter.Transaction) r11
            java.lang.Object r12 = r0.L$1
            app.cash.sqldelight.Transacter$Transaction r12 = (app.cash.sqldelight.Transacter.Transaction) r12
            java.lang.Object r0 = r0.L$0
            app.cash.sqldelight.SuspendingTransacterImpl r0 = (app.cash.sqldelight.SuspendingTransacterImpl) r0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0107
        L_0x007a:
            java.lang.Object r10 = r0.L$2
            app.cash.sqldelight.Transacter$Transaction r10 = (app.cash.sqldelight.Transacter.Transaction) r10
            java.lang.Object r11 = r0.L$1
            app.cash.sqldelight.Transacter$Transaction r11 = (app.cash.sqldelight.Transacter.Transaction) r11
            java.lang.Object r12 = r0.L$0
            app.cash.sqldelight.SuspendingTransacterImpl r12 = (app.cash.sqldelight.SuspendingTransacterImpl) r12
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x008e }
            r8 = r11
            r11 = r10
            r10 = r12
            r12 = r8
            goto L_0x00ed
        L_0x008e:
            r13 = move-exception
            r8 = r12
            r12 = r10
            r10 = r8
            r9 = r13
            r13 = r11
            r11 = r9
            goto L_0x0114
        L_0x0097:
            boolean r11 = r0.Z$0
            java.lang.Object r10 = r0.L$1
            r12 = r10
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            java.lang.Object r10 = r0.L$0
            app.cash.sqldelight.SuspendingTransacterImpl r10 = (app.cash.sqldelight.SuspendingTransacterImpl) r10
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00c0
        L_0x00a6:
            kotlin.ResultKt.throwOnFailure(r13)
            app.cash.sqldelight.db.SqlDriver r13 = r10.getDriver()
            app.cash.sqldelight.db.QueryResult r13 = r13.newTransaction()
            r0.L$0 = r10
            r0.L$1 = r12
            r0.Z$0 = r11
            r0.label = r6
            java.lang.Object r13 = r13.await(r0)
            if (r13 != r1) goto L_0x00c0
            return r1
        L_0x00c0:
            app.cash.sqldelight.Transacter$Transaction r13 = (app.cash.sqldelight.Transacter.Transaction) r13
            app.cash.sqldelight.Transacter$Transaction r2 = r13.enclosingTransaction$runtime()
            if (r2 == 0) goto L_0x00d3
            if (r11 != 0) goto L_0x00cb
            goto L_0x00d3
        L_0x00cb:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Already in a transaction"
            r10.<init>(r11)
            throw r10
        L_0x00d3:
            r13.setTransacter$runtime(r10)     // Catch:{ all -> 0x0112 }
            app.cash.sqldelight.SuspendingTransactionWrapper r11 = new app.cash.sqldelight.SuspendingTransactionWrapper     // Catch:{ all -> 0x0112 }
            r11.<init>(r13)     // Catch:{ all -> 0x0112 }
            r0.L$0 = r10     // Catch:{ all -> 0x0112 }
            r0.L$1 = r13     // Catch:{ all -> 0x0112 }
            r0.L$2 = r2     // Catch:{ all -> 0x0112 }
            r0.label = r5     // Catch:{ all -> 0x0112 }
            java.lang.Object r11 = r12.invoke(r11, r0)     // Catch:{ all -> 0x0112 }
            if (r11 != r1) goto L_0x00ea
            return r1
        L_0x00ea:
            r12 = r13
            r13 = r11
            r11 = r2
        L_0x00ed:
            r12.setSuccessful$runtime(r6)     // Catch:{ all -> 0x010c }
            app.cash.sqldelight.db.QueryResult r2 = r12.endTransaction$runtime()
            r0.L$0 = r10
            r0.L$1 = r12
            r0.L$2 = r11
            r0.L$3 = r13
            r0.label = r4
            java.lang.Object r0 = r2.await(r0)
            if (r0 != r1) goto L_0x0105
            return r1
        L_0x0105:
            r0 = r10
            r10 = r13
        L_0x0107:
            java.lang.Object r10 = r0.postTransactionCleanup(r12, r11, r7, r10)
            return r10
        L_0x010c:
            r2 = move-exception
            r7 = r13
            r13 = r12
            r12 = r11
            r11 = r2
            goto L_0x0114
        L_0x0112:
            r11 = move-exception
            r12 = r2
        L_0x0114:
            app.cash.sqldelight.db.QueryResult r2 = r13.endTransaction$runtime()
            r0.L$0 = r10
            r0.L$1 = r13
            r0.L$2 = r12
            r0.L$3 = r11
            r0.L$4 = r7
            r0.label = r3
            java.lang.Object r0 = r2.await(r0)
            if (r0 != r1) goto L_0x012b
            return r1
        L_0x012b:
            r0 = r10
            r1 = r13
            r10 = r7
        L_0x012e:
            java.lang.Object r10 = r0.postTransactionCleanup(r1, r12, r11, r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.SuspendingTransacterImpl.transactionWithWrapper(boolean, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public Object transaction(boolean z2, @NotNull Function2<? super SuspendingTransactionWithoutReturn, ? super Continuation<? super Unit>, ? extends Object> function2, @NotNull Continuation<? super Unit> continuation) {
        return transaction$suspendImpl(this, z2, function2, continuation);
    }

    @Nullable
    public <R> Object transactionWithResult(boolean z2, @NotNull Function2<? super SuspendingTransactionWithReturn<R>, ? super Continuation<? super R>, ? extends Object> function2, @NotNull Continuation<? super R> continuation) {
        return transactionWithWrapper(z2, function2, continuation);
    }
}
