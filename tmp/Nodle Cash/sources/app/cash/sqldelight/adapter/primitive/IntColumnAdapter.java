package app.cash.sqldelight.adapter.primitive;

import app.cash.sqldelight.ColumnAdapter;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\b\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0007J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lapp/cash/sqldelight/adapter/primitive/IntColumnAdapter;", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "()V", "decode", "databaseValue", "(J)Ljava/lang/Integer;", "encode", "value", "(I)Ljava/lang/Long;", "sqldelight-primitive-adapters"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class IntColumnAdapter implements ColumnAdapter<Integer, Long> {
    @NotNull
    public static final IntColumnAdapter INSTANCE = new IntColumnAdapter();

    private IntColumnAdapter() {
    }

    public /* bridge */ /* synthetic */ Object decode(Object obj) {
        return decode(((Number) obj).longValue());
    }

    public /* bridge */ /* synthetic */ Object encode(Object obj) {
        return encode(((Number) obj).intValue());
    }

    @NotNull
    public Integer decode(long j2) {
        return Integer.valueOf((int) j2);
    }

    @NotNull
    public Long encode(int i3) {
        return Long.valueOf((long) i3);
    }
}
