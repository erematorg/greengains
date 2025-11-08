package com.apollographql.apollo3.internal;

import com.apollographql.apollo3.annotations.ApolloInternal;
import com.apollographql.apollo3.api.DeferredFragmentIdentifier;
import com.apollographql.apollo3.api.json.BufferedSourceJsonReader;
import com.apollographql.apollo3.api.json.JsonReaders;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J<\u0010\u0019\u001a\u00020\u001a2\u0018\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u00062\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011j\u0002`\u0012H\u0002J\"\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011j\u0002`\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J2\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011j\u0002`\u00122\u0018\u0010!\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011j\u0002`\u0012J \u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011j\u0002`\u00122\u0006\u0010!\u001a\u00020\u001fJ\"\u0010\"\u001a\u00020\u001a2\u0018\u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011j\u0002`\u0012H\u0002J2\u0010$\u001a\u0004\u0018\u00010\u00012\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011j\u0002`\u00122\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00010&H\u0002J\u0006\u0010'\u001a\u00020\u001aR \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR#\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0011j\u0002`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006("}, d2 = {"Lcom/apollographql/apollo3/internal/DeferredJsonMerger;", "", "()V", "_merged", "", "", "Lcom/apollographql/apollo3/internal/MutableJsonMap;", "_mergedFragmentIds", "", "Lcom/apollographql/apollo3/api/DeferredFragmentIdentifier;", "<set-?>", "", "hasNext", "getHasNext", "()Z", "isEmptyPayload", "merged", "", "Lcom/apollographql/apollo3/internal/JsonMap;", "getMerged", "()Ljava/util/Map;", "mergedFragmentIds", "", "getMergedFragmentIds", "()Ljava/util/Set;", "deepMerge", "", "destination", "map", "jsonToMap", "json", "Lokio/BufferedSource;", "merge", "payload", "mergeData", "incrementalItem", "nodeAtPath", "path", "", "reset", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nDeferredJsonMerger.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeferredJsonMerger.kt\ncom/apollographql/apollo3/internal/DeferredJsonMerger\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,152:1\n1#2:153\n*E\n"})
@ApolloInternal
public final class DeferredJsonMerger {
    @NotNull
    private final Map<String, Object> _merged;
    @NotNull
    private final Set<DeferredFragmentIdentifier> _mergedFragmentIds;
    private boolean hasNext = true;
    private boolean isEmptyPayload;
    @NotNull
    private final Map<String, Object> merged;
    @NotNull
    private final Set<DeferredFragmentIdentifier> mergedFragmentIds;

    public DeferredJsonMerger() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this._merged = linkedHashMap;
        this.merged = linkedHashMap;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this._mergedFragmentIds = linkedHashSet;
        this.mergedFragmentIds = linkedHashSet;
    }

    private final void deepMerge(Map<String, Object> map, Map<String, ? extends Object> map2) {
        for (Map.Entry next : map2.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (!map.containsKey(str) || !TypeIntrinsics.isMutableMap(map.get(str))) {
                map.put(str, value);
            } else {
                Object obj = map.get(str);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any?>{ com.apollographql.apollo3.internal.DeferredJsonMergerKt.MutableJsonMap }");
                Map asMutableMap = TypeIntrinsics.asMutableMap(obj);
                Map map3 = value instanceof Map ? (Map) value : null;
                if (map3 != null) {
                    deepMerge(asMutableMap, map3);
                } else {
                    throw new IllegalStateException(("'" + str + "' is an object in destination but not in map").toString());
                }
            }
        }
    }

    private final Map<String, Object> jsonToMap(BufferedSource bufferedSource) {
        Object readAny = JsonReaders.readAny(new BufferedSourceJsonReader(bufferedSource));
        Intrinsics.checkNotNull(readAny, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>{ com.apollographql.apollo3.internal.DeferredJsonMergerKt.JsonMap }");
        return (Map) readAny;
    }

    private final void mergeData(Map<String, ? extends Object> map) {
        Map map2 = (Map) map.get("data");
        Object obj = map.get("path");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) obj;
        Object obj2 = this.merged.get("data");
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>{ com.apollographql.apollo3.internal.DeferredJsonMergerKt.JsonMap }");
        Map map3 = (Map) obj2;
        if (map2 != null) {
            Object nodeAtPath = nodeAtPath(map3, list);
            Intrinsics.checkNotNull(nodeAtPath, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any?>{ com.apollographql.apollo3.internal.DeferredJsonMergerKt.MutableJsonMap }");
            deepMerge(TypeIntrinsics.asMutableMap(nodeAtPath), map2);
            this._mergedFragmentIds.add(new DeferredFragmentIdentifier(list, (String) map.get("label")));
        }
    }

    private final Object nodeAtPath(Map<String, ? extends Object> map, List<? extends Object> list) {
        Object obj;
        Object obj2 = map;
        for (Object next : list) {
            if (obj2 instanceof List) {
                Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.Int");
                obj = ((List) obj2).get(((Integer) next).intValue());
            } else {
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>{ com.apollographql.apollo3.internal.DeferredJsonMergerKt.JsonMap }");
                obj = ((Map) obj2).get(next);
            }
            obj2 = obj;
        }
        return obj2;
    }

    public final boolean getHasNext() {
        return this.hasNext;
    }

    @NotNull
    public final Map<String, Object> getMerged() {
        return this.merged;
    }

    @NotNull
    public final Set<DeferredFragmentIdentifier> getMergedFragmentIds() {
        return this.mergedFragmentIds;
    }

    public final boolean isEmptyPayload() {
        return this.isEmptyPayload;
    }

    @NotNull
    public final Map<String, Object> merge(@NotNull BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "payload");
        return merge((Map<String, ? extends Object>) jsonToMap(bufferedSource));
    }

    public final void reset() {
        this._merged.clear();
        this._mergedFragmentIds.clear();
        this.hasNext = true;
        this.isEmptyPayload = false;
    }

    @NotNull
    public final Map<String, Object> merge(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "payload");
        if (this.merged.isEmpty()) {
            this._merged.putAll(map);
            return this.merged;
        }
        Object obj = map.get("incremental");
        List<Map> list = obj instanceof List ? (List) obj : null;
        boolean z2 = false;
        if (list == null) {
            this.isEmptyPayload = true;
        } else {
            this.isEmptyPayload = false;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Map map2 : list) {
                mergeData(map2);
                Object obj2 = map2.get("errors");
                List list2 = obj2 instanceof List ? (List) obj2 : null;
                if (list2 != null) {
                    CollectionsKt__MutableCollectionsKt.addAll(arrayList, list2);
                }
                Object obj3 = map2.get("extensions");
                Map map3 = obj3 instanceof Map ? (Map) obj3 : null;
                if (map3 != null) {
                    arrayList2.add(map3);
                }
            }
            if (!arrayList.isEmpty()) {
                this._merged.put("errors", arrayList);
            } else {
                this._merged.remove("errors");
            }
            if (!arrayList2.isEmpty()) {
                this._merged.put("extensions", MapsKt.mapOf(TuplesKt.to("incremental", arrayList2)));
            } else {
                this._merged.remove("extensions");
            }
        }
        Boolean bool = (Boolean) map.get("hasNext");
        if (bool != null) {
            z2 = bool.booleanValue();
        }
        this.hasNext = z2;
        return this.merged;
    }
}
