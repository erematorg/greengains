package app.cash.sqldelight.db;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lapp/cash/sqldelight/db/AfterVersion;", "", "afterVersion", "", "block", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlDriver;", "", "(JLkotlin/jvm/functions/Function1;)V", "getAfterVersion", "()J", "getBlock", "()Lkotlin/jvm/functions/Function1;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AfterVersion {
    private final long afterVersion;
    @NotNull
    private final Function1<SqlDriver, Unit> block;

    public AfterVersion(long j2, @NotNull Function1<? super SqlDriver, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        this.afterVersion = j2;
        this.block = function1;
    }

    public final long getAfterVersion() {
        return this.afterVersion;
    }

    @NotNull
    public final Function1<SqlDriver, Unit> getBlock() {
        return this.block;
    }
}
