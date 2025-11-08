package app.cash.sqldelight.driver.android;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lapp/cash/sqldelight/driver/android/AndroidStatement;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class AndroidSqliteDriver$execute$1 extends Lambda implements Function0<AndroidStatement> {
    final /* synthetic */ String $sql;
    final /* synthetic */ AndroidSqliteDriver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidSqliteDriver$execute$1(AndroidSqliteDriver androidSqliteDriver, String str) {
        super(0);
        this.this$0 = androidSqliteDriver;
        this.$sql = str;
    }

    @NotNull
    public final AndroidStatement invoke() {
        return new AndroidPreparedStatement(this.this$0.getDatabase().compileStatement(this.$sql));
    }
}
