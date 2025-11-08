package app.cash.sqldelight.driver.android;

import androidx.sqlite.db.SupportSQLiteProgram;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroidx/sqlite/db/SupportSQLiteProgram;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class AndroidQuery$bindBytes$1 extends Lambda implements Function1<SupportSQLiteProgram, Unit> {
    final /* synthetic */ byte[] $bytes;
    final /* synthetic */ int $index;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidQuery$bindBytes$1(byte[] bArr, int i3) {
        super(1);
        this.$bytes = bArr;
        this.$index = i3;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SupportSQLiteProgram) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull SupportSQLiteProgram supportSQLiteProgram) {
        Intrinsics.checkNotNullParameter(supportSQLiteProgram, "it");
        byte[] bArr = this.$bytes;
        int i3 = this.$index + 1;
        if (bArr == null) {
            supportSQLiteProgram.bindNull(i3);
        } else {
            supportSQLiteProgram.bindBlob(i3, bArr);
        }
    }
}
