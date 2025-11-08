package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.ExecutableQuery;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "app.cash.sqldelight.async.coroutines.QueryExtensionsKt", f = "QueryExtensions.kt", i = {0}, l = {29}, m = "awaitAsOne", n = {"$this$awaitAsOne"}, s = {"L$0"})
public final class QueryExtensionsKt$awaitAsOne$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    public QueryExtensionsKt$awaitAsOne$1(Continuation<? super QueryExtensionsKt$awaitAsOne$1> continuation) {
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return QueryExtensionsKt.awaitAsOne((ExecutableQuery) null, this);
    }
}
