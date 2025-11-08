package com.reown.sign.sign;

import app.cash.sqldelight.db.AfterVersion;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 SignDatabaseImpl.kt\ncom/reown/sign/sign/SignDatabaseImpl$Schema\n*L\n1#1,328:1\n894#2:329\n*E\n"})
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* renamed from: com.reown.sign.sign.SignDatabaseImpl$Schema$migrate-zeHU3Mk$$inlined$sortedBy$1  reason: invalid class name */
public final class SignDatabaseImpl$Schema$migratezeHU3Mk$$inlined$sortedBy$1<T> implements Comparator {
    public final int compare(T t2, T t3) {
        return ComparisonsKt.compareValues(Long.valueOf(((AfterVersion) t2).getAfterVersion()), Long.valueOf(((AfterVersion) t3).getAfterVersion()));
    }
}
