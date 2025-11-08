package app.cash.sqldelight.driver.android;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lapp/cash/sqldelight/driver/android/AndroidStatement;", "invoke", "(Lapp/cash/sqldelight/driver/android/AndroidStatement;)Ljava/lang/Long;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class AndroidSqliteDriver$execute$2 extends Lambda implements Function1<AndroidStatement, Long> {
    public static final AndroidSqliteDriver$execute$2 INSTANCE = new AndroidSqliteDriver$execute$2();

    public AndroidSqliteDriver$execute$2() {
        super(1);
    }

    @NotNull
    public final Long invoke(@NotNull AndroidStatement androidStatement) {
        Intrinsics.checkNotNullParameter(androidStatement, "$this$execute");
        return Long.valueOf(androidStatement.execute());
    }
}
