package app.cash.sqldelight.logs;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\u0010\u0007J)\u0010\b\u001a\u00020\u00062\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\n\"\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u0006H\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016JH\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00152\u0019\u0010\u0018\u001a\u0015\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004¢\u0006\u0002\b\u001aH\u0016¢\u0006\u0002\u0010\u001bJh\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u0012\"\u0004\b\u0000\u0010\u001d2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001d0\u00120\u00042\u0006\u0010\u0017\u001a\u00020\u00152\u0019\u0010\u0018\u001a\u0015\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004¢\u0006\u0002\b\u001aH\u0016¢\u0006\u0002\u0010 J#\u0010!\u001a\u00020\u00062\u0019\u0010\u0018\u001a\u0015\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004¢\u0006\u0002\b\u001aH\u0002J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012H\u0016J!\u0010#\u001a\u00020\u00062\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\n\"\u00020\u0005H\u0016¢\u0006\u0002\u0010$J)\u0010%\u001a\u00020\u00062\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\n\"\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lapp/cash/sqldelight/logs/LogSqliteDriver;", "Lapp/cash/sqldelight/db/SqlDriver;", "sqlDriver", "logger", "Lkotlin/Function1;", "", "", "(Lapp/cash/sqldelight/db/SqlDriver;Lkotlin/jvm/functions/Function1;)V", "addListener", "queryKeys", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "([Ljava/lang/String;Lapp/cash/sqldelight/Query$Listener;)V", "close", "currentTransaction", "Lapp/cash/sqldelight/Transacter$Transaction;", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "", "identifier", "", "sql", "parameters", "binders", "Lapp/cash/sqldelight/db/SqlPreparedStatement;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Integer;Ljava/lang/String;ILkotlin/jvm/functions/Function1;)Lapp/cash/sqldelight/db/QueryResult;", "executeQuery", "R", "mapper", "Lapp/cash/sqldelight/db/SqlCursor;", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;ILkotlin/jvm/functions/Function1;)Lapp/cash/sqldelight/db/QueryResult;", "logParameters", "newTransaction", "notifyListeners", "([Ljava/lang/String;)V", "removeListener", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LogSqliteDriver implements SqlDriver {
    /* access modifiers changed from: private */
    @NotNull
    public final Function1<String, Unit> logger;
    @NotNull
    private final SqlDriver sqlDriver;

    public LogSqliteDriver(@NotNull SqlDriver sqlDriver2, @NotNull Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(sqlDriver2, "sqlDriver");
        Intrinsics.checkNotNullParameter(function1, "logger");
        this.sqlDriver = sqlDriver2;
        this.logger = function1;
    }

    private final void logParameters(Function1<? super SqlPreparedStatement, Unit> function1) {
        if (function1 != null) {
            StatementParameterInterceptor statementParameterInterceptor = new StatementParameterInterceptor();
            function1.invoke(statementParameterInterceptor);
            List<Object> andClearParameters = statementParameterInterceptor.getAndClearParameters();
            if (!andClearParameters.isEmpty()) {
                Function1<String, Unit> function12 = this.logger;
                function12.invoke(StringUtils.SPACE + andClearParameters);
            }
        }
    }

    public void addListener(@NotNull String[] strArr, @NotNull Query.Listener listener) {
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Function1<String, Unit> function1 = this.logger;
        function1.invoke("BEGIN " + listener + " LISTENING TO [" + ArraysKt___ArraysKt.joinToString$default((Object[]) strArr, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + AbstractJsonLexerKt.END_LIST);
        this.sqlDriver.addListener((String[]) Arrays.copyOf(strArr, strArr.length), listener);
    }

    public void close() {
        this.logger.invoke("CLOSE CONNECTION");
        this.sqlDriver.close();
    }

    @Nullable
    public Transacter.Transaction currentTransaction() {
        return this.sqlDriver.currentTransaction();
    }

    @NotNull
    public QueryResult<Long> execute(@Nullable Integer num, @NotNull String str, int i3, @Nullable Function1<? super SqlPreparedStatement, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "sql");
        Function1<String, Unit> function12 = this.logger;
        function12.invoke("EXECUTE\n " + str);
        logParameters(function1);
        return this.sqlDriver.execute(num, str, i3, function1);
    }

    @NotNull
    public <R> QueryResult<R> executeQuery(@Nullable Integer num, @NotNull String str, @NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1, int i3, @Nullable Function1<? super SqlPreparedStatement, Unit> function12) {
        Intrinsics.checkNotNullParameter(str, "sql");
        Intrinsics.checkNotNullParameter(function1, "mapper");
        Function1<String, Unit> function13 = this.logger;
        function13.invoke("QUERY\n " + str);
        logParameters(function12);
        return this.sqlDriver.executeQuery(num, str, function1, i3, function12);
    }

    @NotNull
    public QueryResult<Transacter.Transaction> newTransaction() {
        this.logger.invoke("TRANSACTION BEGIN");
        Transacter.Transaction value = this.sqlDriver.newTransaction().getValue();
        value.afterCommit(new LogSqliteDriver$newTransaction$1(this));
        value.afterRollback(new LogSqliteDriver$newTransaction$2(this));
        return QueryResult.Value.m8089boximpl(QueryResult.Value.m8090constructorimpl(value));
    }

    public void notifyListeners(@NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        Function1<String, Unit> function1 = this.logger;
        function1.invoke("NOTIFYING LISTENERS OF [" + ArraysKt___ArraysKt.joinToString$default((Object[]) strArr, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + AbstractJsonLexerKt.END_LIST);
        this.sqlDriver.notifyListeners((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public void removeListener(@NotNull String[] strArr, @NotNull Query.Listener listener) {
        Intrinsics.checkNotNullParameter(strArr, "queryKeys");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Function1<String, Unit> function1 = this.logger;
        function1.invoke("END " + listener + " LISTENING TO [" + ArraysKt___ArraysKt.joinToString$default((Object[]) strArr, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + AbstractJsonLexerKt.END_LIST);
        this.sqlDriver.removeListener((String[]) Arrays.copyOf(strArr, strArr.length), listener);
    }
}
