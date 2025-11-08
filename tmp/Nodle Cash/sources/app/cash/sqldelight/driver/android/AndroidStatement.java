package app.cash.sqldelight.driver.android;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlPreparedStatement;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J-\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u000b0\tH&¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lapp/cash/sqldelight/driver/android/AndroidStatement;", "Lapp/cash/sqldelight/db/SqlPreparedStatement;", "close", "", "execute", "", "executeQuery", "R", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "Lapp/cash/sqldelight/db/QueryResult;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface AndroidStatement extends SqlPreparedStatement {
    void close();

    long execute();

    <R> R executeQuery(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1);
}
