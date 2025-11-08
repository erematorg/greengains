package app.cash.sqldelight;

import androidx.core.os.EnvironmentCompat;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u001aB\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\u00020\u000b\u001aR\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\u00020\u000b\u001aW\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000f\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\u00020\u000b¢\u0006\u0002\u0010\u0012\u001ag\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000f\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u0002H\u00020\u000b¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Query", "Lapp/cash/sqldelight/ExecutableQuery;", "RowType", "", "identifier", "", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "query", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "fileName", "label", "Lapp/cash/sqldelight/Query;", "queryKeys", "", "(I[Ljava/lang/String;Lapp/cash/sqldelight/db/SqlDriver;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lapp/cash/sqldelight/Query;", "(I[Ljava/lang/String;Lapp/cash/sqldelight/db/SqlDriver;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lapp/cash/sqldelight/Query;", "runtime"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class QueryKt {
    @NotNull
    public static final <RowType> Query<RowType> Query(int i3, @NotNull String[] strArr, @NotNull SqlDriver sqlDriver, @NotNull String str, @NotNull Function1<? super SqlCursor, ? extends RowType> function1) {
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
        Intrinsics.checkNotNullParameter(function1, "mapper");
        return Query(i3, strArr, sqlDriver, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, str, function1);
    }

    @NotNull
    public static final <RowType> Query<RowType> Query(int i3, @NotNull String[] strArr, @NotNull SqlDriver sqlDriver, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Function1<? super SqlCursor, ? extends RowType> function1) {
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(str, "fileName");
        Intrinsics.checkNotNullParameter(str2, "label");
        Intrinsics.checkNotNullParameter(str3, SearchIntents.EXTRA_QUERY);
        Intrinsics.checkNotNullParameter(function1, "mapper");
        return new SimpleQuery(i3, strArr, sqlDriver, str, str2, str3, function1);
    }

    @NotNull
    public static final <RowType> ExecutableQuery<RowType> Query(int i3, @NotNull SqlDriver sqlDriver, @NotNull String str, @NotNull Function1<? super SqlCursor, ? extends RowType> function1) {
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
        Intrinsics.checkNotNullParameter(function1, "mapper");
        return Query(i3, sqlDriver, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, str, function1);
    }

    @NotNull
    public static final <RowType> ExecutableQuery<RowType> Query(int i3, @NotNull SqlDriver sqlDriver, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull Function1<? super SqlCursor, ? extends RowType> function1) {
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(str, "fileName");
        Intrinsics.checkNotNullParameter(str2, "label");
        Intrinsics.checkNotNullParameter(str3, SearchIntents.EXTRA_QUERY);
        Intrinsics.checkNotNullParameter(function1, "mapper");
        return new SimpleExecutableQuery(i3, sqlDriver, str, str2, str3, function1);
    }
}
