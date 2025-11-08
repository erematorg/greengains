package com.apollographql.apollo3.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u0006\u001a$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\b"}, d2 = {"possibleTypes", "", "Lcom/apollographql/apollo3/api/ObjectType;", "allTypes", "Lcom/apollographql/apollo3/api/CompiledType;", "type", "Lcom/apollographql/apollo3/api/CompiledNamedType;", "possibleTypesInternal", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\npossibleTypes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 possibleTypes.kt\ncom/apollographql/apollo3/api/PossibleTypes\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,42:1\n1360#2:43\n1446#2,2:44\n1747#2,3:46\n1747#2,3:49\n1448#2,3:52\n1655#2,8:55\n1045#2:63\n*S KotlinDebug\n*F\n+ 1 possibleTypes.kt\ncom/apollographql/apollo3/api/PossibleTypes\n*L\n12#1:43\n12#1:44,2\n15#1:46,3\n22#1:49,3\n12#1:52,3\n40#1:55,8\n40#1:63\n*E\n"})
@JvmName(name = "PossibleTypes")
public final class PossibleTypes {
    @NotNull
    public static final List<ObjectType> possibleTypes(@NotNull List<? extends CompiledType> list, @NotNull CompiledNamedType compiledNamedType) {
        Intrinsics.checkNotNullParameter(list, "allTypes");
        Intrinsics.checkNotNullParameter(compiledNamedType, "type");
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object next : possibleTypesInternal(list, compiledNamedType)) {
            if (hashSet.add(((ObjectType) next).getName())) {
                arrayList.add(next);
            }
        }
        return CollectionsKt.sortedWith(arrayList, new PossibleTypes$possibleTypes$$inlined$sortedBy$1());
    }

    private static final List<ObjectType> possibleTypesInternal(List<? extends CompiledType> list, CompiledNamedType compiledNamedType) {
        List<ObjectType> list2;
        if (compiledNamedType instanceof ObjectType) {
            return CollectionsKt.listOf(compiledNamedType);
        }
        if (compiledNamedType instanceof UnionType) {
            return ArraysKt.toList((T[]) ((UnionType) compiledNamedType).getMembers());
        }
        if (compiledNamedType instanceof InterfaceType) {
            ArrayList arrayList = new ArrayList();
            for (CompiledType compiledType : list) {
                if (compiledType instanceof ObjectType) {
                    Iterable iterable = ((ObjectType) compiledType).getImplements();
                    if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                        Iterator it = iterable.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (Intrinsics.areEqual((Object) ((InterfaceType) it.next()).getName(), (Object) compiledNamedType.getName())) {
                                    list2 = possibleTypesInternal(list, (CompiledNamedType) compiledType);
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    list2 = CollectionsKt.emptyList();
                } else if (compiledType instanceof InterfaceType) {
                    Iterable iterable2 = ((InterfaceType) compiledType).getImplements();
                    if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                        Iterator it2 = iterable2.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (Intrinsics.areEqual((Object) ((InterfaceType) it2.next()).getName(), (Object) compiledNamedType.getName())) {
                                    list2 = possibleTypesInternal(list, (CompiledNamedType) compiledType);
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    list2 = CollectionsKt.emptyList();
                } else {
                    list2 = CollectionsKt.emptyList();
                }
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, list2);
            }
            return arrayList;
        }
        throw new IllegalStateException(("Type '" + compiledNamedType + "' can only have one possible type").toString());
    }
}
