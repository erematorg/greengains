package app.cash.sqldelight;

import app.cash.sqldelight.db.SqlCursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\rB\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\u000e"}, d2 = {"Lapp/cash/sqldelight/Query;", "RowType", "", "Lapp/cash/sqldelight/ExecutableQuery;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "(Lkotlin/jvm/functions/Function1;)V", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "Listener", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class Query<RowType> extends ExecutableQuery<RowType> {

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lapp/cash/sqldelight/Query$Listener;", "", "queryResultsChanged", "", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Listener {
        void queryResultsChanged();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Query(@NotNull Function1<? super SqlCursor, ? extends RowType> function1) {
        super(function1);
        Intrinsics.checkNotNullParameter(function1, "mapper");
    }

    public abstract void addListener(@NotNull Listener listener);

    public abstract void removeListener(@NotNull Listener listener);
}
