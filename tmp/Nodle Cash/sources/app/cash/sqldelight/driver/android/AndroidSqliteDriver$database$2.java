package app.cash.sqldelight.driver.android;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class AndroidSqliteDriver$database$2 extends Lambda implements Function0<SupportSQLiteDatabase> {
    final /* synthetic */ SupportSQLiteDatabase $database;
    final /* synthetic */ AndroidSqliteDriver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidSqliteDriver$database$2(AndroidSqliteDriver androidSqliteDriver, SupportSQLiteDatabase supportSQLiteDatabase) {
        super(0);
        this.this$0 = androidSqliteDriver;
        this.$database = supportSQLiteDatabase;
    }

    @NotNull
    public final SupportSQLiteDatabase invoke() {
        SupportSQLiteDatabase writableDatabase;
        SupportSQLiteOpenHelper access$getOpenHelper$p = this.this$0.openHelper;
        if (access$getOpenHelper$p != null && (writableDatabase = access$getOpenHelper$p.getWritableDatabase()) != null) {
            return writableDatabase;
        }
        SupportSQLiteDatabase supportSQLiteDatabase = this.$database;
        Intrinsics.checkNotNull(supportSQLiteDatabase);
        return supportSQLiteDatabase;
    }
}
