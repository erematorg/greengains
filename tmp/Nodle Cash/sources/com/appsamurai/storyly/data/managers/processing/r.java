package com.appsamurai.storyly.data.managers.processing;

import com.appsamurai.storyly.data.s;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.util.i;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

public final class r {
    @Nullable
    public static final s a(@NotNull f fVar, @NotNull String str) {
        LinkedHashMap linkedHashMap;
        Intrinsics.checkNotNullParameter(fVar, "<this>");
        Intrinsics.checkNotNullParameter(str, "response");
        try {
            Object obj = new JSONObject(str).get("story_groups");
            if (obj != null) {
                Map<String, List<String>> a2 = a((JSONArray) obj, (Set<String>) null);
                for (Map.Entry next : fVar.i().getConfig().getUserData().entrySet()) {
                    str = new Regex("@\\{\\s?" + ((String) next.getKey()) + "\\s?\\}").replace((CharSequence) str, (String) next.getValue());
                }
                Object obj2 = new JSONObject(str).get("story_groups");
                if (obj2 != null) {
                    LinkedHashMap linkedHashMap2 = (LinkedHashMap) a2;
                    Map<String, List<String>> a3 = a((JSONArray) obj2, (Set<String>) CollectionsKt.toSet(linkedHashMap2.keySet()));
                    s sVar = (s) fVar.c().decodeFromString(s.f4195f, str);
                    for (v vVar : sVar.f4197a) {
                        vVar.f4243w = ((LinkedHashMap) a3).get(vVar.f4221a) != null;
                        List list = (List) linkedHashMap2.get(vVar.f4221a);
                        if (list == null) {
                            linkedHashMap = null;
                        } else {
                            linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10)), 16));
                            for (Object next2 : list) {
                                linkedHashMap.put(next2, fVar.i().getConfig().getUserData().get((String) next2));
                            }
                        }
                        vVar.f4242v = linkedHashMap;
                    }
                    return sVar;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static final Map<String, List<String>> a(JSONArray jSONArray, Set<String> set) {
        Regex regex = new Regex("@\\{(.+?)\\}");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        for (JSONObject jSONObject : SequencesKt.mapNotNull(CollectionsKt.asSequence(RangesKt.until(0, jSONArray.length())), new i(jSONArray))) {
            String string = jSONObject.getString(FirebaseAnalytics.Param.GROUP_ID);
            if (set == null || set.contains(string)) {
                if (jSONObject.has("template")) {
                    Object obj = jSONObject.get("template");
                    if ((obj instanceof JSONObject ? (JSONObject) obj : null) != null) {
                    }
                }
                ArrayList arrayList = new ArrayList();
                String jSONObject2 = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "groupItem.toString()");
                for (MatchResult groupValues : regex.findAll(jSONObject2, 0)) {
                    String str = (String) CollectionsKt.lastOrNull(groupValues.getGroupValues());
                    if (str != null) {
                        arrayList.add(str);
                    }
                }
                if (!arrayList.isEmpty()) {
                    Intrinsics.checkNotNullExpressionValue(string, "groupId");
                    linkedHashMap.put(string, CollectionsKt.distinct(arrayList));
                }
            }
        }
        return linkedHashMap;
    }
}
