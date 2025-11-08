package app.cash.sqldelight.driver.android;

import androidx.sqlite.db.SupportSQLiteStatement;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u001a\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001f\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0012J\u001f\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0002\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0006H\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0016J-\u0010\u001c\u001a\u0002H\u001d\"\u0004\b\u0000\u0010\u001d2\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001d0!0\u001fH\u0016¢\u0006\u0002\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lapp/cash/sqldelight/driver/android/AndroidPreparedStatement;", "Lapp/cash/sqldelight/driver/android/AndroidStatement;", "statement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "(Landroidx/sqlite/db/SupportSQLiteStatement;)V", "bindBoolean", "", "index", "", "boolean", "", "(ILjava/lang/Boolean;)V", "bindBytes", "bytes", "", "bindDouble", "double", "", "(ILjava/lang/Double;)V", "bindLong", "long", "", "(ILjava/lang/Long;)V", "bindString", "string", "", "close", "execute", "executeQuery", "R", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "Lapp/cash/sqldelight/db/QueryResult;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class AndroidPreparedStatement implements AndroidStatement {
    @NotNull
    private final SupportSQLiteStatement statement;

    public AndroidPreparedStatement(@NotNull SupportSQLiteStatement supportSQLiteStatement) {
        Intrinsics.checkNotNullParameter(supportSQLiteStatement, "statement");
        this.statement = supportSQLiteStatement;
    }

    public void bindBoolean(int i3, @Nullable Boolean bool) {
        if (bool == null) {
            this.statement.bindNull(i3 + 1);
        } else {
            this.statement.bindLong(i3 + 1, bool.booleanValue() ? 1 : 0);
        }
    }

    public void bindBytes(int i3, @Nullable byte[] bArr) {
        SupportSQLiteStatement supportSQLiteStatement = this.statement;
        int i4 = i3 + 1;
        if (bArr == null) {
            supportSQLiteStatement.bindNull(i4);
        } else {
            supportSQLiteStatement.bindBlob(i4, bArr);
        }
    }

    public void bindDouble(int i3, @Nullable Double d2) {
        SupportSQLiteStatement supportSQLiteStatement = this.statement;
        int i4 = i3 + 1;
        if (d2 == null) {
            supportSQLiteStatement.bindNull(i4);
        } else {
            supportSQLiteStatement.bindDouble(i4, d2.doubleValue());
        }
    }

    public void bindLong(int i3, @Nullable Long l2) {
        SupportSQLiteStatement supportSQLiteStatement = this.statement;
        int i4 = i3 + 1;
        if (l2 == null) {
            supportSQLiteStatement.bindNull(i4);
        } else {
            supportSQLiteStatement.bindLong(i4, l2.longValue());
        }
    }

    public void bindString(int i3, @Nullable String str) {
        SupportSQLiteStatement supportSQLiteStatement = this.statement;
        int i4 = i3 + 1;
        if (str == null) {
            supportSQLiteStatement.bindNull(i4);
        } else {
            supportSQLiteStatement.bindString(i4, str);
        }
    }

    public void close() {
        this.statement.close();
    }

    public long execute() {
        return (long) this.statement.executeUpdateDelete();
    }

    public <R> R executeQuery(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
        Intrinsics.checkNotNullParameter(function1, "mapper");
        throw new UnsupportedOperationException();
    }
}
