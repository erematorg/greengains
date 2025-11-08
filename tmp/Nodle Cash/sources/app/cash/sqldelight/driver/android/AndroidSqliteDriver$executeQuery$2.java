package app.cash.sqldelight.driver.android;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "R", "Lapp/cash/sqldelight/driver/android/AndroidStatement;", "invoke", "(Lapp/cash/sqldelight/driver/android/AndroidStatement;)Ljava/lang/Object;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class AndroidSqliteDriver$executeQuery$2 extends Lambda implements Function1<AndroidStatement, R> {
    final /* synthetic */ Function1<SqlCursor, QueryResult<R>> $mapper;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidSqliteDriver$executeQuery$2(Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
        super(1);
        this.$mapper = function1;
    }

    public final R invoke(@NotNull AndroidStatement androidStatement) {
        Intrinsics.checkNotNullParameter(androidStatement, "$this$execute");
        return androidStatement.executeQuery(this.$mapper);
    }
}
