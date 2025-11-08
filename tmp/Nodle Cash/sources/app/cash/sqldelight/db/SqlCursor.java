package app.cash.sqldelight.db;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0017\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012H&¨\u0006\u0013"}, d2 = {"Lapp/cash/sqldelight/db/SqlCursor;", "", "getBoolean", "", "index", "", "(I)Ljava/lang/Boolean;", "getBytes", "", "getDouble", "", "(I)Ljava/lang/Double;", "getLong", "", "(I)Ljava/lang/Long;", "getString", "", "next", "Lapp/cash/sqldelight/db/QueryResult;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SqlCursor {
    @Nullable
    Boolean getBoolean(int i3);

    @Nullable
    byte[] getBytes(int i3);

    @Nullable
    Double getDouble(int i3);

    @Nullable
    Long getLong(int i3);

    @Nullable
    String getString(int i3);

    @NotNull
    QueryResult<Boolean> next();
}
