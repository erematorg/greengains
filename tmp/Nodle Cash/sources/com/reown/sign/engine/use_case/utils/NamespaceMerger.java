package com.reown.sign.engine.use_case.utils;

import com.reown.sign.engine.model.EngineDO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00052\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00052\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005J.\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000b2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bH\u0002¨\u0006\u000e"}, d2 = {"Lcom/reown/sign/engine/use_case/utils/NamespaceMerger;", "", "<init>", "()V", "merge", "", "", "Lcom/reown/sign/engine/model/EngineDO$Namespace$Proposal;", "requiredNamespaces", "optionalNamespaces", "mergeChains", "", "required", "optional", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNamespaceMerger.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NamespaceMerger.kt\ncom/reown/sign/engine/use_case/utils/NamespaceMerger\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,71:1\n216#2,2:72\n1#3:74\n1869#4,2:75\n*S KotlinDebug\n*F\n+ 1 NamespaceMerger.kt\ncom/reown/sign/engine/use_case/utils/NamespaceMerger\n*L\n21#1:72,2\n63#1:75,2\n*E\n"})
public final class NamespaceMerger {
    @NotNull
    public static final NamespaceMerger INSTANCE = new NamespaceMerger();

    private NamespaceMerger() {
    }

    private final List<String> mergeChains(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            arrayList.addAll(list);
        }
        if (list2 != null) {
            for (String str : list2) {
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public final Map<String, EngineDO.Namespace.Proposal> merge(@Nullable Map<String, EngineDO.Namespace.Proposal> map, @Nullable Map<String, EngineDO.Namespace.Proposal> map2) {
        Map<String, EngineDO.Namespace.Proposal> map3;
        if (map2 == null || (map3 = MapsKt.toMutableMap(map2)) == null) {
            map3 = new LinkedHashMap<>();
        }
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                EngineDO.Namespace.Proposal proposal = (EngineDO.Namespace.Proposal) next.getValue();
                EngineDO.Namespace.Proposal proposal2 = map3.get(str);
                if (proposal2 != null) {
                    map3.put(str, new EngineDO.Namespace.Proposal(INSTANCE.mergeChains(proposal.getChains(), proposal2.getChains()), CollectionsKt.distinct(CollectionsKt.plus(proposal.getMethods(), proposal2.getMethods())), CollectionsKt.distinct(CollectionsKt.plus(proposal.getEvents(), proposal2.getEvents()))));
                } else {
                    map3.put(str, proposal);
                }
            }
        }
        if (map3.isEmpty()) {
            return null;
        }
        return map3;
    }
}
