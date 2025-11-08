package com.reown.android.internal.common.di;

import app.cash.sqldelight.ColumnAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¨\u0006\b"}, d2 = {"com/reown/android/internal/common/di/BaseStorageModuleKt$baseStorageModule$1$1$1", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "decode", "databaseValue", "encode", "value", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BaseStorageModuleKt$baseStorageModule$1$1$1 implements ColumnAdapter<List<? extends String>, String> {
    public List<String> decode(String str) {
        Intrinsics.checkNotNullParameter(str, "databaseValue");
        if (StringsKt.isBlank(str)) {
            return CollectionsKt.emptyList();
        }
        return StringsKt__StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
    }

    public String encode(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "value");
        return CollectionsKt___CollectionsKt.joinToString$default(list, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
