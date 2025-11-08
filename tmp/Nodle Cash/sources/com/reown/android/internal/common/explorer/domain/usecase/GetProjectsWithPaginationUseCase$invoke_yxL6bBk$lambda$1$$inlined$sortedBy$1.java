package com.reown.android.internal.common.explorer.domain.usecase;

import com.reown.android.internal.common.explorer.data.model.Project;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 GetProjectsWithPaginationUseCase.kt\ncom/reown/android/internal/common/explorer/domain/usecase/GetProjectsWithPaginationUseCase\n*L\n1#1,328:1\n10#2:329\n*E\n"})
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class GetProjectsWithPaginationUseCase$invoke_yxL6bBk$lambda$1$$inlined$sortedBy$1<T> implements Comparator {
    public final int compare(T t2, T t3) {
        Long order = ((Project) t2).getOrder();
        long j2 = Long.MAX_VALUE;
        Long valueOf = Long.valueOf(order != null ? order.longValue() : Long.MAX_VALUE);
        Long order2 = ((Project) t3).getOrder();
        if (order2 != null) {
            j2 = order2.longValue();
        }
        return ComparisonsKt.compareValues(valueOf, Long.valueOf(j2));
    }
}
