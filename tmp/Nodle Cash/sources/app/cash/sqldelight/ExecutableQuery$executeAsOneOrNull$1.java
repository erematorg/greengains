package app.cash.sqldelight;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nQuery.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Query.kt\napp/cash/sqldelight/ExecutableQuery$executeAsOneOrNull$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,205:1\n1#2:206\n*E\n"})
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\n\b\u0000\u0010\u0002 \u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lapp/cash/sqldelight/db/QueryResult;", "RowType", "", "cursor", "Lapp/cash/sqldelight/db/SqlCursor;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class ExecutableQuery$executeAsOneOrNull$1 extends Lambda implements Function1<SqlCursor, QueryResult<RowType>> {
    final /* synthetic */ ExecutableQuery<RowType> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExecutableQuery$executeAsOneOrNull$1(ExecutableQuery<? extends RowType> executableQuery) {
        super(1);
        this.this$0 = executableQuery;
    }

    @NotNull
    public final QueryResult<RowType> invoke(@NotNull SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        if (!sqlCursor.next().getValue().booleanValue()) {
            return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(null));
        }
        RowType invoke = this.this$0.getMapper().invoke(sqlCursor);
        boolean booleanValue = sqlCursor.next().getValue().booleanValue();
        ExecutableQuery<RowType> executableQuery = this.this$0;
        if (!booleanValue) {
            return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(invoke));
        }
        throw new IllegalStateException(("ResultSet returned more than 1 row for " + executableQuery).toString());
    }
}
