package com.reown.sign.storage.sequence;

import com.reown.android.internal.common.model.Namespace;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class SessionStorageRepository$getTempNamespaces$1 extends FunctionReferenceImpl implements Function6<Long, String, List<? extends String>, List<? extends String>, List<? extends String>, List<? extends String>, Pair<? extends String, ? extends Namespace.Session>> {
    public SessionStorageRepository$getTempNamespaces$1(Object obj) {
        super(6, obj, SessionStorageRepository.class, "mapTempNamespaceToNamespaceVO", "mapTempNamespaceToNamespaceVO(JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lkotlin/Pair;", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return invoke(((Number) obj).longValue(), (String) obj2, (List<String>) (List) obj3, (List<String>) (List) obj4, (List<String>) (List) obj5, (List<String>) (List) obj6);
    }

    public final Pair<String, Namespace.Session> invoke(long j2, String str, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        Intrinsics.checkNotNullParameter(str, "p1");
        Intrinsics.checkNotNullParameter(list2, "p3");
        Intrinsics.checkNotNullParameter(list3, "p4");
        Intrinsics.checkNotNullParameter(list4, "p5");
        return ((SessionStorageRepository) this.receiver).mapTempNamespaceToNamespaceVO(j2, str, list, list2, list3, list4);
    }
}
