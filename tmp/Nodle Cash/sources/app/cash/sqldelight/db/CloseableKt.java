package app.cash.sqldelight.db;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aA\u0010\u0000\u001a\u0002H\u0001\"\u0010\b\u0000\u0010\u0002*\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004\"\u0004\b\u0001\u0010\u0001*\u0002H\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0006H\bø\u0001\u0000¢\u0006\u0002\u0010\u0007*\n\u0010\b\"\u00020\u00032\u00020\u0003\u0002\u0007\n\u0005\b20\u0001¨\u0006\t"}, d2 = {"use", "R", "T", "Ljava/io/Closeable;", "Lapp/cash/sqldelight/db/Closeable;", "body", "Lkotlin/Function1;", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Closeable", "runtime"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class CloseableKt {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r2, r3);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T extends java.io.Closeable, R> R use(T r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super T, ? extends R> r3) {
        /*
            java.lang.String r0 = "body"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r0 = 1
            java.lang.Object r3 = r3.invoke(r2)     // Catch:{ all -> 0x0015 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r1)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            return r3
        L_0x0015:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0017 }
        L_0x0017:
            r1 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            kotlin.io.CloseableKt.closeFinally(r2, r3)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.db.CloseableKt.use(java.io.Closeable, kotlin.jvm.functions.Function1):java.lang.Object");
    }
}
