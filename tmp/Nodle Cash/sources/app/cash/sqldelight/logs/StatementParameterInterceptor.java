package app.cash.sqldelight.logs;

import app.cash.sqldelight.db.SqlPreparedStatement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001f\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u000e\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u001cR\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lapp/cash/sqldelight/logs/StatementParameterInterceptor;", "Lapp/cash/sqldelight/db/SqlPreparedStatement;", "()V", "values", "", "", "bindBoolean", "", "index", "", "boolean", "", "(ILjava/lang/Boolean;)V", "bindBytes", "bytes", "", "bindDouble", "double", "", "(ILjava/lang/Double;)V", "bindLong", "long", "", "(ILjava/lang/Long;)V", "bindString", "string", "", "getAndClearParameters", "", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class StatementParameterInterceptor implements SqlPreparedStatement {
    @NotNull
    private final List<Object> values = new ArrayList();

    public void bindBoolean(int i3, @Nullable Boolean bool) {
        this.values.add(bool);
    }

    public void bindBytes(int i3, @Nullable byte[] bArr) {
        this.values.add(bArr);
    }

    public void bindDouble(int i3, @Nullable Double d2) {
        this.values.add(d2);
    }

    public void bindLong(int i3, @Nullable Long l2) {
        this.values.add(l2);
    }

    public void bindString(int i3, @Nullable String str) {
        this.values.add(str);
    }

    @NotNull
    public final List<Object> getAndClearParameters() {
        List<Object> list = CollectionsKt.toList(this.values);
        this.values.clear();
        return list;
    }
}
