package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.ExecutableQuery;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@¢\u0006\u0002\u0010\u0005\u001a\"\u0010\u0006\u001a\u0002H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@¢\u0006\u0002\u0010\u0005\u001a$\u0010\u0007\u001a\u0004\u0018\u0001H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H@¢\u0006\u0002\u0010\u0005¨\u0006\b"}, d2 = {"awaitAsList", "", "T", "", "Lapp/cash/sqldelight/ExecutableQuery;", "(Lapp/cash/sqldelight/ExecutableQuery;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitAsOne", "awaitAsOneOrNull", "sqldelight-async-extensions"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class QueryExtensionsKt {
    @Nullable
    public static final <T> Object awaitAsList(@NotNull ExecutableQuery<? extends T> executableQuery, @NotNull Continuation<? super List<? extends T>> continuation) {
        return executableQuery.execute(new QueryExtensionsKt$awaitAsList$2(executableQuery)).await(continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> java.lang.Object awaitAsOne(@org.jetbrains.annotations.NotNull app.cash.sqldelight.ExecutableQuery<? extends T> r4, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r5) {
        /*
            boolean r0 = r5 instanceof app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsOne$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsOne$1 r0 = (app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsOne$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsOne$1 r0 = new app.cash.sqldelight.async.coroutines.QueryExtensionsKt$awaitAsOne$1
            r0.<init>(r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            java.lang.Object r4 = r0.L$0
            app.cash.sqldelight.ExecutableQuery r4 = (app.cash.sqldelight.ExecutableQuery) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0043
        L_0x002d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = awaitAsOneOrNull(r4, r0)
            if (r5 != r1) goto L_0x0043
            return r1
        L_0x0043:
            if (r5 == 0) goto L_0x0046
            return r5
        L_0x0046:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "ResultSet returned null for "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.async.coroutines.QueryExtensionsKt.awaitAsOne(app.cash.sqldelight.ExecutableQuery, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final <T> Object awaitAsOneOrNull(@NotNull ExecutableQuery<? extends T> executableQuery, @NotNull Continuation<? super T> continuation) {
        return executableQuery.execute(new QueryExtensionsKt$awaitAsOneOrNull$2(executableQuery)).await(continuation);
    }
}
