package app.cash.sqldelight;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0002J\u0015\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00028\u00012\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\t"}, d2 = {"Lapp/cash/sqldelight/ColumnAdapter;", "T", "", "S", "decode", "databaseValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "encode", "value", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface ColumnAdapter<T, S> {
    @NotNull
    T decode(S s3);

    S encode(@NotNull T t2);
}
