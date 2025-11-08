package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.CompiledField;
import com.apollographql.apollo3.api.ObjectType;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.api.json.MapJsonReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000F\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a[\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010\u001aR\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001aP\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001aP\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001aY\u0010\u001d\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u001f\u001a$\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a$\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a*\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0019*\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010#\u001a\u00020\bH\u0002¨\u0006$"}, d2 = {"buildData", "T", "adapter", "Lcom/apollographql/apollo3/api/Adapter;", "selections", "", "Lcom/apollographql/apollo3/api/CompiledSelection;", "typename", "", "map", "", "", "resolver", "Lcom/apollographql/apollo3/api/FakeResolver;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "(Lcom/apollographql/apollo3/api/Adapter;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Lcom/apollographql/apollo3/api/FakeResolver;Lcom/apollographql/apollo3/api/CustomScalarAdapters;)Ljava/lang/Object;", "buildFakeObject", "base", "buildFieldOfNonNullType", "path", "id", "mergedField", "Lcom/apollographql/apollo3/api/CompiledField;", "value", "Lcom/apollographql/apollo3/api/Optional;", "type", "Lcom/apollographql/apollo3/api/CompiledType;", "buildFieldOfType", "buildFragmentData", "block", "(Lcom/apollographql/apollo3/api/Adapter;Ljava/util/List;Ljava/lang/String;Ljava/lang/Object;Lcom/apollographql/apollo3/api/FakeResolver;Lcom/apollographql/apollo3/api/CompiledType;Lcom/apollographql/apollo3/api/CustomScalarAdapters;)Ljava/lang/Object;", "collect", "collectAndMerge", "getOrAbsent", "key", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nfakeResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 fakeResolver.kt\ncom/apollographql/apollo3/api/FakeResolverKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,374:1\n1360#2:375\n1446#2,5:376\n1477#2:381\n1502#2,3:382\n1505#2,3:392\n1549#2:395\n1620#2,2:396\n1360#2:398\n1446#2,5:399\n1622#2:404\n1559#2:405\n1590#2,4:406\n1549#2:410\n1620#2,3:411\n1603#2,9:414\n1855#2:423\n1856#2:425\n1612#2:426\n1179#2,2:427\n1253#2,4:429\n372#3,7:385\n1#4:424\n*S KotlinDebug\n*F\n+ 1 fakeResolver.kt\ncom/apollographql/apollo3/api/FakeResolverKt\n*L\n68#1:375\n68#1:376,5\n89#1:381\n89#1:382,3\n89#1:392,3\n89#1:395\n89#1:396,2\n94#1:398\n94#1:399,5\n89#1:404\n176#1:405\n176#1:406,4\n180#1:410\n180#1:411,3\n200#1:414,9\n200#1:423\n200#1:425\n200#1:426\n215#1:427,2\n215#1:429,4\n89#1:385,7\n200#1:424\n*E\n"})
public final class FakeResolverKt {
    public static final <T> T buildData(@NotNull Adapter<T> adapter, @NotNull List<? extends CompiledSelection> list, @NotNull String str, @NotNull Map<String, ? extends Object> map, @NotNull FakeResolver fakeResolver, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(list, "selections");
        Intrinsics.checkNotNullParameter(str, "typename");
        Intrinsics.checkNotNullParameter(map, "map");
        Intrinsics.checkNotNullParameter(fakeResolver, "resolver");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return Adapters.m8202obj(adapter, false).fromJson(new MapJsonReader(buildFakeObject(list, str, map, fakeResolver, customScalarAdapters), (List) null, 2, (DefaultConstructorMarker) null), CustomScalarAdapters.PassThrough);
    }

    private static final Map<String, Object> buildFakeObject(List<? extends CompiledSelection> list, String str, Map<String, ? extends Object> map, FakeResolver fakeResolver, CustomScalarAdapters customScalarAdapters) {
        Object buildFieldOfType = buildFieldOfType(CollectionsKt.emptyList(), "", new CompiledField.Builder("data", new CompiledNotNullType(new ObjectType.Builder(str).build())).selections(list).build(), fakeResolver, new Optional.Present(map), new CompiledNotNullType(new ObjectType.Builder(str).build()), customScalarAdapters);
        Intrinsics.checkNotNull(buildFieldOfType, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        return (Map) buildFieldOfType;
    }

    /* JADX WARNING: type inference failed for: r12v8, types: [java.util.List] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object buildFieldOfNonNullType(java.util.List<? extends java.lang.Object> r20, java.lang.String r21, com.apollographql.apollo3.api.CompiledField r22, com.apollographql.apollo3.api.FakeResolver r23, com.apollographql.apollo3.api.Optional<? extends java.lang.Object> r24, com.apollographql.apollo3.api.CompiledType r25, com.apollographql.apollo3.api.CustomScalarAdapters r26) {
        /*
            r0 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r1 = r24
            r11 = r25
            boolean r2 = r11 instanceof com.apollographql.apollo3.api.CompiledListType
            java.lang.String r3 = ""
            r12 = 0
            if (r2 == 0) goto L_0x00d0
            boolean r2 = r1 instanceof com.apollographql.apollo3.api.Optional.Present
            r4 = 0
            if (r2 == 0) goto L_0x0076
            com.apollographql.apollo3.api.Optional$Present r1 = (com.apollographql.apollo3.api.Optional.Present) r1
            java.lang.Object r1 = r1.getValue()
            boolean r2 = r1 instanceof java.util.List
            if (r2 == 0) goto L_0x0025
            r12 = r1
            java.util.List r12 = (java.util.List) r12
        L_0x0025:
            if (r12 == 0) goto L_0x0070
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r13 = new java.util.ArrayList
            int r1 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r12, 10)
            r13.<init>(r1)
            java.util.Iterator r12 = r12.iterator()
        L_0x0036:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x022d
            java.lang.Object r1 = r12.next()
            int r14 = r4 + 1
            if (r4 >= 0) goto L_0x0047
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L_0x0047:
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r3)
            com.apollographql.apollo3.api.Optional$Present r5 = new com.apollographql.apollo3.api.Optional$Present
            r5.<init>(r1)
            r1 = r11
            com.apollographql.apollo3.api.CompiledListType r1 = (com.apollographql.apollo3.api.CompiledListType) r1
            com.apollographql.apollo3.api.CompiledType r6 = r1.getOfType()
            r1 = r2
            r2 = r21
            r3 = r22
            r4 = r23
            r7 = r26
            java.lang.Object r1 = buildFieldOfType(r1, r2, r3, r4, r5, r6, r7)
            r13.add(r1)
            r4 = r14
            goto L_0x0036
        L_0x0070:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        L_0x0076:
            com.apollographql.apollo3.api.FakeResolverContext r1 = new com.apollographql.apollo3.api.FakeResolverContext
            r1.<init>(r0, r8, r9)
            int r1 = r10.resolveListSize(r1)
            kotlin.ranges.IntRange r1 = kotlin.ranges.RangesKt.until((int) r4, (int) r1)
            java.util.ArrayList r13 = new java.util.ArrayList
            int r2 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, 10)
            r13.<init>(r2)
            java.util.Iterator r12 = r1.iterator()
        L_0x0090:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x022d
            r1 = r12
            kotlin.collections.IntIterator r1 = (kotlin.collections.IntIterator) r1
            int r1 = r1.nextInt()
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r8)
            r3.append(r1)
            java.lang.String r3 = r3.toString()
            com.apollographql.apollo3.api.Optional$Absent r5 = com.apollographql.apollo3.api.Optional.Absent.INSTANCE
            r1 = r11
            com.apollographql.apollo3.api.CompiledListType r1 = (com.apollographql.apollo3.api.CompiledListType) r1
            com.apollographql.apollo3.api.CompiledType r6 = r1.getOfType()
            r1 = r2
            r2 = r3
            r3 = r22
            r4 = r23
            r7 = r26
            java.lang.Object r1 = buildFieldOfType(r1, r2, r3, r4, r5, r6, r7)
            r13.add(r1)
            goto L_0x0090
        L_0x00d0:
            boolean r2 = r11 instanceof com.apollographql.apollo3.api.CompiledNamedType
            if (r2 == 0) goto L_0x022e
            boolean r2 = r1 instanceof com.apollographql.apollo3.api.Optional.Present
            java.lang.String r4 = "__typename"
            if (r2 == 0) goto L_0x018b
            java.util.List r2 = r22.getSelections()
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0182
            com.apollographql.apollo3.api.Optional$Present r1 = (com.apollographql.apollo3.api.Optional.Present) r1
            java.lang.Object r1 = r1.getValue()
            boolean r2 = r1 instanceof java.util.Map
            if (r2 == 0) goto L_0x00f4
            java.util.Map r1 = (java.util.Map) r1
            r11 = r1
            goto L_0x00f5
        L_0x00f4:
            r11 = r12
        L_0x00f5:
            if (r11 == 0) goto L_0x017c
            java.lang.Object r1 = r11.get(r4)
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L_0x0102
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x0103
        L_0x0102:
            r1 = r12
        L_0x0103:
            if (r1 == 0) goto L_0x0176
            java.lang.String r2 = r10.stableIdForObject(r11, r9)
            if (r2 != 0) goto L_0x010c
            goto L_0x010d
        L_0x010c:
            r8 = r2
        L_0x010d:
            java.util.List r2 = r22.getSelections()
            java.util.List r1 = collectAndMerge(r2, r1)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r13 = r1.iterator()
        L_0x0120:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto L_0x0170
            java.lang.Object r1 = r13.next()
            r14 = r1
            com.apollographql.apollo3.api.CompiledField r14 = (com.apollographql.apollo3.api.CompiledField) r14
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.String r2 = r14.getResponseName()
            java.util.List r1 = kotlin.collections.CollectionsKt.plus(r1, r2)
            java.lang.StringBuilder r2 = A.a.p(r8)
            java.lang.String r3 = r14.getResponseName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = r14.getResponseName()
            com.apollographql.apollo3.api.Optional r5 = getOrAbsent(r11, r3)
            com.apollographql.apollo3.api.CompiledType r6 = r14.getType()
            r3 = r14
            r4 = r23
            r7 = r26
            java.lang.Object r1 = buildFieldOfType(r1, r2, r3, r4, r5, r6, r7)
            boolean r2 = r1 instanceof com.apollographql.apollo3.api.Optional.Absent
            if (r2 == 0) goto L_0x0162
            r1 = r12
            goto L_0x016a
        L_0x0162:
            java.lang.String r2 = r14.getResponseName()
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
        L_0x016a:
            if (r1 == 0) goto L_0x0120
            r9.add(r1)
            goto L_0x0120
        L_0x0170:
            java.util.Map r13 = kotlin.collections.MapsKt.toMap(r9)
            goto L_0x022d
        L_0x0176:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        L_0x017c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        L_0x0182:
            r0 = r1
            com.apollographql.apollo3.api.Optional$Present r0 = (com.apollographql.apollo3.api.Optional.Present) r0
            java.lang.Object r13 = r0.getValue()
            goto L_0x022d
        L_0x018b:
            java.util.List r1 = r22.getSelections()
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0211
            com.apollographql.apollo3.api.FakeResolverContext r1 = new com.apollographql.apollo3.api.FakeResolverContext
            r1.<init>(r0, r8, r9)
            java.lang.String r1 = r10.resolveTypename(r1)
            kotlin.Pair r2 = kotlin.TuplesKt.to(r4, r1)
            java.util.Map r8 = kotlin.collections.MapsKt.mapOf(r2)
            java.util.List r2 = r22.getSelections()
            java.util.List r1 = collectAndMerge(r2, r1)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            r2 = 16
            int r2 = androidx.compose.animation.core.a.h(r1, r2)
            java.util.LinkedHashMap r13 = new java.util.LinkedHashMap
            r13.<init>(r2)
            java.util.Iterator r9 = r1.iterator()
        L_0x01c1:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L_0x022d
            java.lang.Object r1 = r9.next()
            r3 = r1
            com.apollographql.apollo3.api.CompiledField r3 = (com.apollographql.apollo3.api.CompiledField) r3
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.String r2 = r3.getResponseName()
            java.util.List r1 = kotlin.collections.CollectionsKt.plus(r1, r2)
            java.lang.String r11 = r3.getResponseName()
            r14 = r1
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            r17 = 0
            r18 = 0
            r15 = 0
            r16 = 0
            r19 = 63
            java.lang.String r2 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(r14, r15, r16, r17, 0, (java.lang.CharSequence) null, r18, r19, (java.lang.Object) null)
            java.lang.String r4 = r3.getResponseName()
            com.apollographql.apollo3.api.Optional r5 = getOrAbsent(r8, r4)
            com.apollographql.apollo3.api.CompiledType r6 = r3.getType()
            r4 = r23
            r7 = r26
            java.lang.Object r1 = buildFieldOfType(r1, r2, r3, r4, r5, r6, r7)
            kotlin.Pair r1 = kotlin.TuplesKt.to(r11, r1)
            java.lang.Object r2 = r1.getFirst()
            java.lang.Object r1 = r1.getSecond()
            r13.put(r2, r1)
            goto L_0x01c1
        L_0x0211:
            com.apollographql.apollo3.api.FakeResolverContext r1 = new com.apollographql.apollo3.api.FakeResolverContext
            r1.<init>(r0, r8, r9)
            java.lang.Object r13 = r10.resolveLeaf(r1)
            boolean r0 = r11 instanceof com.apollographql.apollo3.api.CustomScalarType
            if (r0 == 0) goto L_0x022d
            r0 = r11
            com.apollographql.apollo3.api.CustomScalarType r0 = (com.apollographql.apollo3.api.CustomScalarType) r0     // Catch:{ Exception -> 0x0227 }
            r1 = r26
            com.apollographql.apollo3.api.Adapter r12 = r1.responseAdapterFor(r0)     // Catch:{ Exception -> 0x0227 }
        L_0x0227:
            if (r12 == 0) goto L_0x022d
            java.lang.Object r13 = com.apollographql.apollo3.api.ObjectBuilderKt.adaptValue(r12, r13)
        L_0x022d:
            return r13
        L_0x022e:
            boolean r0 = r11 instanceof com.apollographql.apollo3.api.CompiledNotNullType
            if (r0 == 0) goto L_0x0238
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        L_0x0238:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.FakeResolverKt.buildFieldOfNonNullType(java.util.List, java.lang.String, com.apollographql.apollo3.api.CompiledField, com.apollographql.apollo3.api.FakeResolver, com.apollographql.apollo3.api.Optional, com.apollographql.apollo3.api.CompiledType, com.apollographql.apollo3.api.CustomScalarAdapters):java.lang.Object");
    }

    private static final Object buildFieldOfType(List<? extends Object> list, String str, CompiledField compiledField, FakeResolver fakeResolver, Optional<? extends Object> optional, CompiledType compiledType, CustomScalarAdapters customScalarAdapters) {
        boolean z2 = optional instanceof Optional.Present;
        if (z2 && (((Optional.Present) optional).getValue() instanceof Optional.Absent)) {
            return Optional.Absent.INSTANCE;
        }
        if (compiledType instanceof CompiledNotNullType) {
            return buildFieldOfNonNullType(list, str, compiledField, fakeResolver, optional, ((CompiledNotNullType) compiledType).getOfType(), customScalarAdapters);
        } else if (z2) {
            if (((Optional.Present) optional).getValue() == null) {
                return null;
            }
            return buildFieldOfType(list, str, compiledField, fakeResolver, optional, new CompiledNotNullType(compiledType), customScalarAdapters);
        } else if (fakeResolver.resolveMaybeNull(new FakeResolverContext(list, str, compiledField))) {
            return null;
        } else {
            return buildFieldOfType(list, str, compiledField, fakeResolver, optional, new CompiledNotNullType(compiledType), customScalarAdapters);
        }
    }

    public static final <T> T buildFragmentData(@NotNull Adapter<T> adapter, @NotNull List<? extends CompiledSelection> list, @NotNull String str, @Nullable Object obj, @NotNull FakeResolver fakeResolver, @NotNull CompiledType compiledType, @NotNull CustomScalarAdapters customScalarAdapters) {
        Map map;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(list, "selections");
        Intrinsics.checkNotNullParameter(str, "typename");
        Intrinsics.checkNotNullParameter(fakeResolver, "resolver");
        Intrinsics.checkNotNullParameter(compiledType, "type");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        if (obj == null) {
            map = MapsKt.mapOf(TuplesKt.to("__typename", fakeResolver.resolveTypename(new FakeResolverContext(CollectionsKt.emptyList(), "fragmentRoot", new CompiledField.Builder("__fragmentRoot", compiledType).build()))));
        } else {
            Function1 function1 = (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1);
            map = (Map) ((Function1) obj).invoke(ObjectBuilderKt.getGlobalBuilder());
        }
        return buildData(adapter, list, str, map, fakeResolver, customScalarAdapters);
    }

    public static /* synthetic */ Object buildFragmentData$default(Adapter adapter, List list, String str, Object obj, FakeResolver fakeResolver, CompiledType compiledType, CustomScalarAdapters customScalarAdapters, int i3, Object obj2) {
        if ((i3 & 8) != 0) {
            obj = null;
        }
        return buildFragmentData(adapter, list, str, obj, fakeResolver, compiledType, customScalarAdapters);
    }

    private static final List<CompiledField> collect(List<? extends CompiledSelection> list, String str) {
        List<CompiledField> list2;
        ArrayList arrayList = new ArrayList();
        for (CompiledSelection compiledSelection : list) {
            if (compiledSelection instanceof CompiledField) {
                list2 = CollectionsKt.listOf(compiledSelection);
            } else if (compiledSelection instanceof CompiledFragment) {
                CompiledFragment compiledFragment = (CompiledFragment) compiledSelection;
                list2 = compiledFragment.getPossibleTypes().contains(str) ? collect(compiledFragment.getSelections(), str) : CollectionsKt.emptyList();
            } else {
                throw new NoWhenBranchMatchedException();
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, list2);
        }
        return arrayList;
    }

    private static final List<CompiledField> collectAndMerge(List<? extends CompiledSelection> list, String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object next : collect(list, str)) {
            String responseName = ((CompiledField) next).getResponseName();
            Object obj = linkedHashMap.get(responseName);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(responseName, obj);
            }
            ((List) obj).add(next);
        }
        Iterable<List> values = linkedHashMap.values();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10));
        for (List<CompiledField> list2 : values) {
            CompiledField compiledField = (CompiledField) CollectionsKt.first(list2);
            CompiledField.Builder alias = new CompiledField.Builder(compiledField.getName(), compiledField.getType()).alias(compiledField.getAlias());
            ArrayList arrayList2 = new ArrayList();
            for (CompiledField selections : list2) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList2, selections.getSelections());
            }
            arrayList.add(alias.selections(arrayList2).build());
        }
        return arrayList;
    }

    private static final Optional<Object> getOrAbsent(Map<String, ? extends Object> map, String str) {
        return map.containsKey(str) ? new Optional.Present(map.get(str)) : Optional.Absent.INSTANCE;
    }
}
