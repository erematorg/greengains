package app.cash.sqldelight.driver.android;

import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\t\u001a\u00020\nH\u0016J\u001b\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0016H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0019"}, d2 = {"Lapp/cash/sqldelight/driver/android/AndroidCursor;", "Lapp/cash/sqldelight/db/SqlCursor;", "cursor", "Landroid/database/Cursor;", "windowSizeBytes", "", "(Landroid/database/Cursor;Ljava/lang/Long;)V", "getBoolean", "", "index", "", "(I)Ljava/lang/Boolean;", "getBytes", "", "getDouble", "", "(I)Ljava/lang/Double;", "getLong", "(I)Ljava/lang/Long;", "getString", "", "next", "Lapp/cash/sqldelight/db/QueryResult$Value;", "next-mlR-ZEE", "()Ljava/lang/Object;", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class AndroidCursor implements SqlCursor {
    @NotNull
    private final Cursor cursor;

    public AndroidCursor(@NotNull Cursor cursor2, @Nullable Long l2) {
        Intrinsics.checkNotNullParameter(cursor2, "cursor");
        this.cursor = cursor2;
        if (l2 != null && (cursor2 instanceof AbstractWindowedCursor)) {
            Api28Impl.setWindowSize((AbstractWindowedCursor) cursor2, l2.longValue());
        }
    }

    @Nullable
    public Boolean getBoolean(int i3) {
        if (this.cursor.isNull(i3)) {
            return null;
        }
        return Boolean.valueOf(this.cursor.getLong(i3) == 1);
    }

    @Nullable
    public byte[] getBytes(int i3) {
        if (this.cursor.isNull(i3)) {
            return null;
        }
        return this.cursor.getBlob(i3);
    }

    @Nullable
    public Double getDouble(int i3) {
        if (this.cursor.isNull(i3)) {
            return null;
        }
        return Double.valueOf(this.cursor.getDouble(i3));
    }

    @Nullable
    public Long getLong(int i3) {
        if (this.cursor.isNull(i3)) {
            return null;
        }
        return Long.valueOf(this.cursor.getLong(i3));
    }

    @Nullable
    public String getString(int i3) {
        if (this.cursor.isNull(i3)) {
            return null;
        }
        return this.cursor.getString(i3);
    }

    public /* bridge */ /* synthetic */ QueryResult next() {
        return QueryResult.Value.m8089boximpl(m8096nextmlRZEE());
    }

    @NotNull
    /* renamed from: next-mlR-ZEE  reason: not valid java name */
    public Object m8096nextmlRZEE() {
        return QueryResult.Value.m8090constructorimpl(Boolean.valueOf(this.cursor.moveToNext()));
    }
}
