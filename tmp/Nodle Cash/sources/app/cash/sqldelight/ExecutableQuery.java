package app.cash.sqldelight;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0006J.\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0001\u0010\u000b2\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\u0004H&J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rJ\u000b\u0010\u000e\u001a\u00028\u0000¢\u0006\u0002\u0010\u000fJ\r\u0010\u0010\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u000fR\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lapp/cash/sqldelight/ExecutableQuery;", "RowType", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "(Lkotlin/jvm/functions/Function1;)V", "getMapper", "()Lkotlin/jvm/functions/Function1;", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "executeAsList", "", "executeAsOne", "()Ljava/lang/Object;", "executeAsOneOrNull", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class ExecutableQuery<RowType> {
    @NotNull
    private final Function1<SqlCursor, RowType> mapper;

    public ExecutableQuery(@NotNull Function1<? super SqlCursor, ? extends RowType> function1) {
        Intrinsics.checkNotNullParameter(function1, "mapper");
        this.mapper = function1;
    }

    @NotNull
    public abstract <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1);

    @NotNull
    public final List<RowType> executeAsList() {
        return (List) execute(new ExecutableQuery$executeAsList$1(this)).getValue();
    }

    @NotNull
    public final RowType executeAsOne() {
        RowType executeAsOneOrNull = executeAsOneOrNull();
        if (executeAsOneOrNull != null) {
            return executeAsOneOrNull;
        }
        throw new NullPointerException("ResultSet returned null for " + this);
    }

    @Nullable
    public final RowType executeAsOneOrNull() {
        return execute(new ExecutableQuery$executeAsOneOrNull$1(this)).getValue();
    }

    @NotNull
    public final Function1<SqlCursor, RowType> getMapper() {
        return this.mapper;
    }
}
