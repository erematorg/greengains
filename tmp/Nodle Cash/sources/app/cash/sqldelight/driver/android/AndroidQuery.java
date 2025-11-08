package app.cash.sqldelight.driver.android;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u001f\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001f\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0002\u0010!J\u001f\u0010\"\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\b2\b\u0010#\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010$J\u001a\u0010%\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u0011H\u0016J\b\u0010)\u001a\u00020\u0012H\u0016J\b\u0010*\u001a\u00020+H\u0016J-\u0010,\u001a\u0002H-\"\u0004\b\u0000\u0010-2\u0018\u0010.\u001a\u0014\u0012\u0004\u0012\u00020/\u0012\n\u0012\b\u0012\u0004\u0012\u0002H-000\u0010H\u0016¢\u0006\u0002\u00101J\b\u00102\u001a\u00020\u0004H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0004¢\u0006\u0004\n\u0002\u0010\u0015¨\u00063"}, d2 = {"Lapp/cash/sqldelight/driver/android/AndroidQuery;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "Lapp/cash/sqldelight/driver/android/AndroidStatement;", "sql", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "argCount", "", "windowSizeBytes", "", "(Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteDatabase;ILjava/lang/Long;)V", "getArgCount", "()I", "binds", "", "Lkotlin/Function1;", "Landroidx/sqlite/db/SupportSQLiteProgram;", "", "getSql", "()Ljava/lang/String;", "Ljava/lang/Long;", "bindBoolean", "index", "boolean", "", "(ILjava/lang/Boolean;)V", "bindBytes", "bytes", "", "bindDouble", "double", "", "(ILjava/lang/Double;)V", "bindLong", "long", "(ILjava/lang/Long;)V", "bindString", "string", "bindTo", "statement", "close", "execute", "", "executeQuery", "R", "mapper", "Lapp/cash/sqldelight/db/SqlCursor;", "Lapp/cash/sqldelight/db/QueryResult;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toString", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAndroidSqliteDriver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AndroidSqliteDriver.kt\napp/cash/sqldelight/driver/android/AndroidQuery\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,351:1\n1#2:352\n*E\n"})
final class AndroidQuery implements SupportSQLiteQuery, AndroidStatement {
    private final int argCount;
    @NotNull
    private final List<Function1<SupportSQLiteProgram, Unit>> binds;
    @NotNull
    private final SupportSQLiteDatabase database;
    @NotNull
    private final String sql;
    @Nullable
    private final Long windowSizeBytes;

    public AndroidQuery(@NotNull String str, @NotNull SupportSQLiteDatabase supportSQLiteDatabase, int i3, @Nullable Long l2) {
        Intrinsics.checkNotNullParameter(str, "sql");
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        this.sql = str;
        this.database = supportSQLiteDatabase;
        this.argCount = i3;
        this.windowSizeBytes = l2;
        int argCount2 = getArgCount();
        ArrayList arrayList = new ArrayList(argCount2);
        for (int i4 = 0; i4 < argCount2; i4++) {
            arrayList.add((Object) null);
        }
        this.binds = arrayList;
    }

    public void bindBoolean(int i3, @Nullable Boolean bool) {
        this.binds.set(i3, new AndroidQuery$bindBoolean$1(bool, i3));
    }

    public void bindBytes(int i3, @Nullable byte[] bArr) {
        this.binds.set(i3, new AndroidQuery$bindBytes$1(bArr, i3));
    }

    public void bindDouble(int i3, @Nullable Double d2) {
        this.binds.set(i3, new AndroidQuery$bindDouble$1(d2, i3));
    }

    public void bindLong(int i3, @Nullable Long l2) {
        this.binds.set(i3, new AndroidQuery$bindLong$1(l2, i3));
    }

    public void bindString(int i3, @Nullable String str) {
        this.binds.set(i3, new AndroidQuery$bindString$1(str, i3));
    }

    public void bindTo(@NotNull SupportSQLiteProgram supportSQLiteProgram) {
        Intrinsics.checkNotNullParameter(supportSQLiteProgram, "statement");
        for (Function1 next : this.binds) {
            Intrinsics.checkNotNull(next);
            next.invoke(supportSQLiteProgram);
        }
    }

    public void close() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <R> R executeQuery(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super app.cash.sqldelight.db.SqlCursor, ? extends app.cash.sqldelight.db.QueryResult<R>> r4) {
        /*
            r3 = this;
            java.lang.String r0 = "mapper"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            androidx.sqlite.db.SupportSQLiteDatabase r0 = r3.database
            android.database.Cursor r0 = r0.query((androidx.sqlite.db.SupportSQLiteQuery) r3)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            android.database.Cursor r1 = (android.database.Cursor) r1     // Catch:{ all -> 0x0026 }
            app.cash.sqldelight.driver.android.AndroidCursor r2 = new app.cash.sqldelight.driver.android.AndroidCursor     // Catch:{ all -> 0x0026 }
            java.lang.Long r3 = r3.windowSizeBytes     // Catch:{ all -> 0x0026 }
            r2.<init>(r1, r3)     // Catch:{ all -> 0x0026 }
            java.lang.Object r3 = r4.invoke(r2)     // Catch:{ all -> 0x0026 }
            app.cash.sqldelight.db.QueryResult r3 = (app.cash.sqldelight.db.QueryResult) r3     // Catch:{ all -> 0x0026 }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x0026 }
            r4 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            return r3
        L_0x0026:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r4 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.driver.android.AndroidQuery.executeQuery(kotlin.jvm.functions.Function1):java.lang.Object");
    }

    public int getArgCount() {
        return this.argCount;
    }

    @NotNull
    public String getSql() {
        return this.sql;
    }

    @NotNull
    public String toString() {
        return getSql();
    }

    @NotNull
    public Void execute() {
        throw new UnsupportedOperationException();
    }
}
