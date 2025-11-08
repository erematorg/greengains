package com.reown.sign.engine.model.tvf;

import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 Near.kt\ncom/reown/sign/engine/model/tvf/NearSignTransaction$BufferData\n*L\n1#1,328:1\n27#2:329\n*E\n"})
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class NearSignTransaction$BufferData$toByteArray$$inlined$sortedBy$1<T> implements Comparator {
    public final int compare(T t2, T t3) {
        return ComparisonsKt.compareValues(Integer.valueOf(Integer.parseInt((String) ((Map.Entry) t2).getKey())), Integer.valueOf(Integer.parseInt((String) ((Map.Entry) t3).getKey())));
    }
}
