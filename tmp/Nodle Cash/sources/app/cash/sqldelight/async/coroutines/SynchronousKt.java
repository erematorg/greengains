package app.cash.sqldelight.async.coroutines;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlSchema;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00040\u0001¨\u0006\u0005"}, d2 = {"synchronous", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "Lapp/cash/sqldelight/db/QueryResult$AsyncValue;", "sqldelight-async-extensions"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class SynchronousKt {
    @NotNull
    public static final SqlSchema<QueryResult.Value<Unit>> synchronous(@NotNull SqlSchema<QueryResult.AsyncValue<Unit>> sqlSchema) {
        Intrinsics.checkNotNullParameter(sqlSchema, "<this>");
        return new SynchronousKt$synchronous$1(sqlSchema);
    }
}
