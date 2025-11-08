package app.cash.sqldelight.adapter.primitive;

import app.cash.sqldelight.ColumnAdapter;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\b\b\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0007J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lapp/cash/sqldelight/adapter/primitive/FloatColumnAdapter;", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "()V", "decode", "databaseValue", "(D)Ljava/lang/Float;", "encode", "value", "(F)Ljava/lang/Double;", "sqldelight-primitive-adapters"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FloatColumnAdapter implements ColumnAdapter<Float, Double> {
    @NotNull
    public static final FloatColumnAdapter INSTANCE = new FloatColumnAdapter();

    private FloatColumnAdapter() {
    }

    public /* bridge */ /* synthetic */ Object decode(Object obj) {
        return decode(((Number) obj).doubleValue());
    }

    public /* bridge */ /* synthetic */ Object encode(Object obj) {
        return encode(((Number) obj).floatValue());
    }

    @NotNull
    public Float decode(double d2) {
        return Float.valueOf((float) d2);
    }

    @NotNull
    public Double encode(float f2) {
        return Double.valueOf((double) f2);
    }
}
