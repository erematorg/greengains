package app.cash.sqldelight.db;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.Transacter;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00060\u0001j\u0002`\u0002J)\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\nJ\n\u0010\u000b\u001a\u0004\u0018\u00010\fH&JJ\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00112\u001b\b\u0002\u0010\u0014\u001a\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0015¢\u0006\u0002\b\u0017H&¢\u0006\u0002\u0010\u0018Jj\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u000e\"\u0004\b\u0000\u0010\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0018\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001a0\u000e0\u00152\u0006\u0010\u0013\u001a\u00020\u00112\u001b\b\u0002\u0010\u0014\u001a\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0015¢\u0006\u0002\b\u0017H&¢\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH&J!\u0010\u001f\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H&¢\u0006\u0002\u0010 J)\u0010!\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\n¨\u0006\""}, d2 = {"Lapp/cash/sqldelight/db/SqlDriver;", "Ljava/io/Closeable;", "Lapp/cash/sqldelight/db/Closeable;", "addListener", "", "queryKeys", "", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "([Ljava/lang/String;Lapp/cash/sqldelight/Query$Listener;)V", "currentTransaction", "Lapp/cash/sqldelight/Transacter$Transaction;", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "", "identifier", "", "sql", "parameters", "binders", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlPreparedStatement;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Integer;Ljava/lang/String;ILkotlin/jvm/functions/Function1;)Lapp/cash/sqldelight/db/QueryResult;", "executeQuery", "R", "mapper", "Lapp/cash/sqldelight/db/SqlCursor;", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Lapp/cash/sqldelight/db/QueryResult;", "newTransaction", "notifyListeners", "([Ljava/lang/String;)V", "removeListener", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SqlDriver extends Closeable {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ QueryResult execute$default(SqlDriver sqlDriver, Integer num, String str, int i3, Function1 function1, int i4, Object obj) {
            if (obj == null) {
                if ((i4 & 8) != 0) {
                    function1 = null;
                }
                return sqlDriver.execute(num, str, i3, function1);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: execute");
        }

        public static /* synthetic */ QueryResult executeQuery$default(SqlDriver sqlDriver, Integer num, String str, Function1 function1, int i3, Function1 function12, int i4, Object obj) {
            if (obj == null) {
                if ((i4 & 16) != 0) {
                    function12 = null;
                }
                return sqlDriver.executeQuery(num, str, function1, i3, function12);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: executeQuery");
        }
    }

    void addListener(@NotNull String[] strArr, @NotNull Query.Listener listener);

    @Nullable
    Transacter.Transaction currentTransaction();

    @NotNull
    QueryResult<Long> execute(@Nullable Integer num, @NotNull String str, int i3, @Nullable Function1<? super SqlPreparedStatement, Unit> function1);

    @NotNull
    <R> QueryResult<R> executeQuery(@Nullable Integer num, @NotNull String str, @NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1, int i3, @Nullable Function1<? super SqlPreparedStatement, Unit> function12);

    @NotNull
    QueryResult<Transacter.Transaction> newTransaction();

    void notifyListeners(@NotNull String... strArr);

    void removeListener(@NotNull String[] strArr, @NotNull Query.Listener listener);
}
