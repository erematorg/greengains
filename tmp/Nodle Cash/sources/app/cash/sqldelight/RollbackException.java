package app.cash.sqldelight;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lapp/cash/sqldelight/RollbackException;", "", "value", "", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class RollbackException extends Throwable {
    @Nullable
    private final Object value;

    public RollbackException() {
        this((Object) null, 1, (DefaultConstructorMarker) null);
    }

    @Nullable
    public final Object getValue() {
        return this.value;
    }

    public RollbackException(@Nullable Object obj) {
        this.value = obj;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RollbackException(Object obj, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : obj);
    }
}
