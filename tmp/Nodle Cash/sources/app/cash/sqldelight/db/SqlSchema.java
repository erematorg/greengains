package app.cash.sqldelight.db;

import app.cash.sqldelight.db.QueryResult;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004J\u0015\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000bH&¢\u0006\u0002\u0010\fJ9\u0010\r\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011\"\u00020\u0012H&¢\u0006\u0002\u0010\u0013R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lapp/cash/sqldelight/db/SqlSchema;", "T", "Lapp/cash/sqldelight/db/QueryResult;", "", "", "version", "", "getVersion", "()J", "create", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "(Lapp/cash/sqldelight/db/SqlDriver;)Lapp/cash/sqldelight/db/QueryResult;", "migrate", "oldVersion", "newVersion", "callbacks", "", "Lapp/cash/sqldelight/db/AfterVersion;", "(Lapp/cash/sqldelight/db/SqlDriver;JJ[Lapp/cash/sqldelight/db/AfterVersion;)Lapp/cash/sqldelight/db/QueryResult;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SqlSchema<T extends QueryResult<Unit>> {
    @NotNull
    T create(@NotNull SqlDriver sqlDriver);

    long getVersion();

    @NotNull
    T migrate(@NotNull SqlDriver sqlDriver, long j2, long j3, @NotNull AfterVersion... afterVersionArr);
}
