package app.cash.sqldelight;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\n\b\u0000\u0010\u0003 \u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lapp/cash/sqldelight/db/QueryResult;", "", "RowType", "", "cursor", "Lapp/cash/sqldelight/db/SqlCursor;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class ExecutableQuery$executeAsList$1 extends Lambda implements Function1<SqlCursor, QueryResult<List<RowType>>> {
    final /* synthetic */ ExecutableQuery<RowType> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExecutableQuery$executeAsList$1(ExecutableQuery<? extends RowType> executableQuery) {
        super(1);
        this.this$0 = executableQuery;
    }

    @NotNull
    public final QueryResult<List<RowType>> invoke(@NotNull SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        ArrayList arrayList = new ArrayList();
        while (sqlCursor.next().getValue().booleanValue()) {
            arrayList.add(this.this$0.getMapper().invoke(sqlCursor));
        }
        return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(arrayList));
    }
}
