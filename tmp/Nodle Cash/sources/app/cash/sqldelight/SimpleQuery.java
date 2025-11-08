package app.cash.sqldelight;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003BQ\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00028\u00000\u000f¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J.\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0018\"\u0004\b\u0001\u0010\u00192\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u00180\u000fH\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u001b\u001a\u00020\bH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006\u001c"}, d2 = {"Lapp/cash/sqldelight/SimpleQuery;", "RowType", "", "Lapp/cash/sqldelight/Query;", "identifier", "", "queryKeys", "", "", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "fileName", "label", "query", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "(I[Ljava/lang/String;Lapp/cash/sqldelight/db/SqlDriver;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "[Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "removeListener", "toString", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class SimpleQuery<RowType> extends Query<RowType> {
    @NotNull
    private final SqlDriver driver;
    @NotNull
    private final String fileName;
    private final int identifier;
    @NotNull
    private final String label;
    @NotNull
    private final String query;
    @NotNull
    private final String[] queryKeys;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SimpleQuery(int i3, @NotNull String[] strArr, @NotNull SqlDriver sqlDriver, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Function1<? super SqlCursor, ? extends RowType> function1) {
        super(function1);
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(str, "fileName");
        Intrinsics.checkNotNullParameter(str2, "label");
        Intrinsics.checkNotNullParameter(str3, SearchIntents.EXTRA_QUERY);
        Intrinsics.checkNotNullParameter(function1, "mapper");
        this.identifier = i3;
        this.queryKeys = strArr;
        this.driver = sqlDriver;
        this.fileName = str;
        this.label = str2;
        this.query = str3;
    }

    public void addListener(@NotNull Query.Listener listener) {
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        SqlDriver sqlDriver = this.driver;
        String[] strArr = this.queryKeys;
        sqlDriver.addListener((String[]) Arrays.copyOf(strArr, strArr.length), listener);
    }

    @NotNull
    public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
        Intrinsics.checkNotNullParameter(function1, "mapper");
        return this.driver.executeQuery(Integer.valueOf(this.identifier), this.query, function1, 0, (Function1<? super SqlPreparedStatement, Unit>) null);
    }

    public void removeListener(@NotNull Query.Listener listener) {
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        SqlDriver sqlDriver = this.driver;
        String[] strArr = this.queryKeys;
        sqlDriver.removeListener((String[]) Arrays.copyOf(strArr, strArr.length), listener);
    }

    @NotNull
    public String toString() {
        return this.fileName + AbstractJsonLexerKt.COLON + this.label;
    }
}
