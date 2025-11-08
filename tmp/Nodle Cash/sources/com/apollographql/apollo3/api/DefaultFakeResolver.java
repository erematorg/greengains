package com.apollographql.apollo3.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rH\u0016J(\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\f\u0010\u0019\u001a\u00020\u0013*\u00020\u000bH\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\r\u0012\t\u0012\u00070\b¢\u0006\u0002\b\t0\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/apollographql/apollo3/api/DefaultFakeResolver;", "Lcom/apollographql/apollo3/api/FakeResolver;", "types", "", "Lcom/apollographql/apollo3/api/CompiledNamedType;", "(Ljava/util/List;)V", "allTypes", "enumTypes", "Lcom/apollographql/apollo3/api/EnumType;", "Lkotlin/internal/NoInfer;", "resolveLeaf", "", "context", "Lcom/apollographql/apollo3/api/FakeResolverContext;", "resolveListSize", "", "resolveMaybeNull", "", "resolveTypename", "", "stableIdForObject", "obj", "", "mergedField", "Lcom/apollographql/apollo3/api/CompiledField;", "toPathComponent", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nfakeResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 fakeResolver.kt\ncom/apollographql/apollo3/api/DefaultFakeResolver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,374:1\n800#2,11:375\n378#2,7:386\n1855#2,2:394\n1#3:393\n*S KotlinDebug\n*F\n+ 1 fakeResolver.kt\ncom/apollographql/apollo3/api/DefaultFakeResolver\n*L\n263#1:375,11\n272#1:386,7\n316#1:394,2\n*E\n"})
public class DefaultFakeResolver implements FakeResolver {
    @NotNull
    private final List<CompiledNamedType> allTypes;
    @NotNull
    private final List<EnumType> enumTypes;

    public DefaultFakeResolver(@NotNull List<? extends CompiledNamedType> list) {
        Intrinsics.checkNotNullParameter(list, "types");
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            if (next instanceof EnumType) {
                arrayList.add(next);
            }
        }
        this.enumTypes = arrayList;
        this.allTypes = list;
    }

    /* access modifiers changed from: private */
    public final String toPathComponent(Object obj) {
        if (!(obj instanceof Integer)) {
            return obj.toString();
        }
        return "[" + obj + AbstractJsonLexerKt.END_LIST;
    }

    @NotNull
    public Object resolveLeaf(@NotNull FakeResolverContext fakeResolverContext) {
        Object obj;
        int i3;
        Intrinsics.checkNotNullParameter(fakeResolverContext, "context");
        String name = fakeResolverContext.getMergedField().getType().rawType().getName();
        switch (name.hashCode()) {
            case -1808118735:
                if (name.equals("String")) {
                    List<Object> path = fakeResolverContext.getPath();
                    ListIterator<Object> listIterator = path.listIterator(path.size());
                    while (true) {
                        if (!listIterator.hasPrevious()) {
                            i3 = -1;
                        } else if (listIterator.previous() instanceof String) {
                            i3 = listIterator.nextIndex();
                        }
                    }
                    return CollectionsKt___CollectionsKt.joinToString$default(fakeResolverContext.getPath().subList(i3, fakeResolverContext.getPath().size()), "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new DefaultFakeResolver$resolveLeaf$1(this), 30, (Object) null);
                }
                break;
            case 2331:
                if (name.equals(SchemaSymbols.ATTVAL_ID)) {
                    return String.valueOf(Math.abs(fakeResolverContext.getId().hashCode()));
                }
                break;
            case 73679:
                if (name.equals("Int")) {
                    return Integer.valueOf(fakeResolverContext.getId().hashCode() % 100);
                }
                break;
            case 67973692:
                if (name.equals("Float")) {
                    return Double.valueOf(((double) ((float) (fakeResolverContext.getId().hashCode() % 100000))) / 100.0d);
                }
                break;
            case 1729365000:
                if (name.equals("Boolean")) {
                    return Boolean.valueOf(fakeResolverContext.getId().hashCode() % 2 == 0);
                }
                break;
        }
        Iterator it = this.enumTypes.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((EnumType) obj).getName(), (Object) name)) {
                }
            } else {
                obj = null;
            }
        }
        EnumType enumType = (EnumType) obj;
        if (enumType != null) {
            List<String> values = enumType.getValues();
            int hashCode = fakeResolverContext.getId().hashCode();
            int size = enumType.getValues().size();
            int i4 = hashCode % size;
            return values.get(i4 + (size & (((i4 ^ size) & ((-i4) | i4)) >> 31)));
        }
        throw new IllegalStateException("Don't know how to instantiate leaf ".concat(name).toString());
    }

    public int resolveListSize(@NotNull FakeResolverContext fakeResolverContext) {
        Intrinsics.checkNotNullParameter(fakeResolverContext, "context");
        return 3;
    }

    public boolean resolveMaybeNull(@NotNull FakeResolverContext fakeResolverContext) {
        Intrinsics.checkNotNullParameter(fakeResolverContext, "context");
        return false;
    }

    @NotNull
    public String resolveTypename(@NotNull FakeResolverContext fakeResolverContext) {
        Intrinsics.checkNotNullParameter(fakeResolverContext, "context");
        List<ObjectType> possibleTypes = PossibleTypes.possibleTypes(this.allTypes, fakeResolverContext.getMergedField().getType().rawType());
        int hashCode = fakeResolverContext.getId().hashCode();
        int size = possibleTypes.size();
        int i3 = hashCode % size;
        return possibleTypes.get(i3 + (size & (((i3 ^ size) & ((-i3) | i3)) >> 31))).getName();
    }

    @Nullable
    public String stableIdForObject(@NotNull Map<String, ? extends Object> map, @NotNull CompiledField compiledField) {
        Intrinsics.checkNotNullParameter(map, "obj");
        Intrinsics.checkNotNullParameter(compiledField, "mergedField");
        List<String> keyFields = CompiledGraphQL.keyFields(compiledField.getType().rawType());
        if (map.containsKey("__stableId")) {
            return String.valueOf(map.get("__stableId"));
        }
        if (keyFields.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(map.get("__typename")));
        for (String str : keyFields) {
            sb.append(String.valueOf(map.get(str)));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}
