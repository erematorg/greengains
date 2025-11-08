package com.reown.android.internal.common.di;

import android.support.v4.media.session.a;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import app.cash.sqldelight.ColumnAdapter;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.objectweb.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u001c\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J\u001c\u0010\u0006\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¨\u0006\b"}, d2 = {"com/reown/android/internal/common/di/BaseStorageModuleKt$baseStorageModule$1$2$1", "Lapp/cash/sqldelight/ColumnAdapter;", "", "", "decode", "databaseValue", "encode", "value", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BaseStorageModuleKt$baseStorageModule$1$2$1 implements ColumnAdapter<Map<String, ? extends String>, String> {
    /* access modifiers changed from: private */
    public static final CharSequence encode$lambda$0(Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "<destruct>");
        return a.n((String) entry.getKey(), StickyVariantProvider.KEY_VALUE_DELIMITER, (String) entry.getValue());
    }

    public Map<String, String> decode(String str) {
        Intrinsics.checkNotNullParameter(str, "databaseValue");
        if (StringsKt.isBlank(str)) {
            return MapsKt.emptyMap();
        }
        int A2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, (char) SignatureVisitor.INSTANCEOF, 0, false, 6, (Object) null);
        if (A2 <= 0) {
            return MapsKt.emptyMap();
        }
        String substring = str.substring(0, A2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String obj = StringsKt__StringsKt.trim((CharSequence) substring).toString();
        String substring2 = str.substring(A2 + 1);
        Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
        return MapsKt.mapOf(TuplesKt.to(obj, StringsKt__StringsKt.trim((CharSequence) substring2).toString()));
    }

    public String encode(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "value");
        return CollectionsKt___CollectionsKt.joinToString$default(map.entrySet(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new b(1), 31, (Object) null);
    }
}
