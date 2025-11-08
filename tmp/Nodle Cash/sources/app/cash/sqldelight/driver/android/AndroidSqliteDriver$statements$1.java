package app.cash.sqldelight.driver.android;

import android.util.LruCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u0014¨\u0006\u000b"}, d2 = {"app/cash/sqldelight/driver/android/AndroidSqliteDriver$statements$1", "Landroid/util/LruCache;", "", "Lapp/cash/sqldelight/driver/android/AndroidStatement;", "entryRemoved", "", "evicted", "", "key", "oldValue", "newValue", "sqldelight-android-driver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AndroidSqliteDriver$statements$1 extends LruCache<Integer, AndroidStatement> {
    public AndroidSqliteDriver$statements$1(int i3) {
        super(i3);
    }

    public /* bridge */ /* synthetic */ void entryRemoved(boolean z2, Object obj, Object obj2, Object obj3) {
        entryRemoved(z2, ((Number) obj).intValue(), (AndroidStatement) obj2, (AndroidStatement) obj3);
    }

    public void entryRemoved(boolean z2, int i3, @NotNull AndroidStatement androidStatement, @Nullable AndroidStatement androidStatement2) {
        Intrinsics.checkNotNullParameter(androidStatement, "oldValue");
        if (z2) {
            androidStatement.close();
        }
    }
}
