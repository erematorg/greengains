package app.cash.sqldelight.driver.android;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lapp/cash/sqldelight/driver/android/AndroidStatement;", "R", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class AndroidSqliteDriver$executeQuery$1 extends Lambda implements Function0<AndroidStatement> {
    final /* synthetic */ int $parameters;
    final /* synthetic */ String $sql;
    final /* synthetic */ AndroidSqliteDriver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidSqliteDriver$executeQuery$1(String str, AndroidSqliteDriver androidSqliteDriver, int i3) {
        super(0);
        this.$sql = str;
        this.this$0 = androidSqliteDriver;
        this.$parameters = i3;
    }

    @NotNull
    public final AndroidStatement invoke() {
        return new AndroidQuery(this.$sql, this.this$0.getDatabase(), this.$parameters, this.this$0.windowSizeBytes);
    }
}
