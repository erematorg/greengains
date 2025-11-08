package com.reown.android.internal.common.signing.cacao;

import android.util.Base64;
import com.fasterxml.jackson.core.JsonPointer;
import com.reown.android.internal.common.signing.cacao.Cacao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.apache.commons.text.StringSubstitutor;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a*\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0001\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00040\u00030\u0003*\u0004\u0018\u00010\u0001\u001a\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0006\u001a\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006*\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0006\u001a\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006*\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0006\u001a\u0016\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002¨\u0006\u0011"}, d2 = {"guaranteeNoHexPrefix", "", "parseReCaps", "", "", "decodeReCaps", "", "getMethods", "getChains", "mergeReCaps", "json1", "Lorg/json/JSONObject;", "json2", "concatenateJsonArrays", "Lorg/json/JSONArray;", "arr1", "arr2", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utils.kt\ncom/reown/android/internal/common/signing/cacao/UtilsKt\n+ 2 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,115:1\n32#2:116\n32#2:117\n32#2,2:118\n33#2:120\n33#2:121\n32#2,2:132\n463#3:122\n413#3:123\n1252#4,4:124\n1563#4:128\n1634#4,3:129\n*S KotlinDebug\n*F\n+ 1 Utils.kt\ncom/reown/android/internal/common/signing/cacao/UtilsKt\n*L\n20#1:116\n24#1:117\n31#1:118,2\n24#1:120\n20#1:121\n76#1:132,2\n45#1:122\n45#1:123\n45#1:124,4\n64#1:128\n64#1:129,3\n*E\n"})
public final class UtilsKt {
    private static final JSONArray concatenateJsonArrays(JSONArray jSONArray, JSONArray jSONArray2) {
        JSONArray jSONArray3 = new JSONArray();
        int length = jSONArray.length();
        for (int i3 = 0; i3 < length; i3++) {
            jSONArray3.put(jSONArray.get(i3));
        }
        int length2 = jSONArray2.length();
        for (int i4 = 0; i4 < length2; i4++) {
            jSONArray3.put(jSONArray2.get(i4));
        }
        return jSONArray3;
    }

    public static final /* synthetic */ String decodeReCaps(List list) {
        String str;
        if (list != null) {
            try {
                str = (String) CollectionsKt.last(list);
            } catch (Exception unused) {
                return null;
            }
        } else {
            str = null;
        }
        if (str == null || !StringsKt__StringsJVMKt.startsWith$default(str, Cacao.Payload.RECAPS_PREFIX, false, 2, (Object) null)) {
            return null;
        }
        byte[] decode = Base64.decode(StringsKt__StringsKt.removePrefix(str, (CharSequence) Cacao.Payload.RECAPS_PREFIX), 2);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        return new String(decode, Charsets.UTF_8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        r1 = kotlin.collections.CollectionsKt.distinct((r1 = kotlin.collections.CollectionsKt.flatten((r1 = r1.values()))));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ java.util.List getChains(java.util.List r1) {
        /*
            java.lang.String r1 = decodeReCaps(r1)
            java.util.Map r1 = parseReCaps(r1)
            java.lang.String r0 = "eip155"
            java.lang.Object r1 = r1.get(r0)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 == 0) goto L_0x0028
            java.util.Collection r1 = r1.values()
            if (r1 == 0) goto L_0x0028
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.List r1 = kotlin.collections.CollectionsKt.flatten(r1)
            if (r1 == 0) goto L_0x0028
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.List r1 = kotlin.collections.CollectionsKt.distinct(r1)
            if (r1 != 0) goto L_0x002c
        L_0x0028:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x002c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.signing.cacao.UtilsKt.getChains(java.util.List):java.util.List");
    }

    public static final /* synthetic */ List getMethods(List list) {
        Set keySet;
        List sorted;
        Map map = (Map) parseReCaps(decodeReCaps(list)).get("eip155");
        if (map == null || (keySet = map.keySet()) == null || (sorted = CollectionsKt.sorted(keySet)) == null) {
            return CollectionsKt.emptyList();
        }
        Iterable<String> iterable = sorted;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (String h02 : iterable) {
            arrayList.add(StringsKt__StringsKt.substringAfter$default(h02, (char) JsonPointer.SEPARATOR, (String) null, 2, (Object) null));
        }
        return arrayList;
    }

    public static final /* synthetic */ String mergeReCaps(JSONObject jSONObject, JSONObject jSONObject2) {
        Intrinsics.checkNotNullParameter(jSONObject, "json1");
        Intrinsics.checkNotNullParameter(jSONObject2, "json2");
        JSONObject jSONObject3 = new JSONObject(jSONObject.toString());
        Iterator<String> keys = jSONObject2.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "keys(...)");
        while (keys.hasNext()) {
            String next = keys.next();
            if (!jSONObject3.has(next)) {
                jSONObject3.put(next, jSONObject2.get(next));
            } else {
                Object obj = jSONObject3.get(next);
                Object obj2 = jSONObject2.get(next);
                if ((obj instanceof JSONObject) && (obj2 instanceof JSONObject)) {
                    jSONObject3.put(next, mergeReCaps((JSONObject) obj, (JSONObject) obj2));
                } else if (!(obj instanceof JSONArray) || !(obj2 instanceof JSONArray)) {
                    jSONObject3.put(next, obj2);
                } else {
                    jSONObject3.put(next, concatenateJsonArrays((JSONArray) obj, (JSONArray) obj2));
                }
            }
        }
        String jSONObject4 = jSONObject3.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject4, "toString(...)");
        return StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(jSONObject4, "\\\"", "\"", false, 4, (Object) null), "\"{", "{", false, 4, (Object) null), "}\"", StringSubstitutor.DEFAULT_VAR_END, false, 4, (Object) null);
    }

    public static final /* synthetic */ Map parseReCaps(String str) {
        String str2 = str;
        if (str2 == null || str.length() == 0) {
            return MapsKt.toMutableMap(MapsKt.emptyMap());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONObject jSONObject = new JSONObject(str2).getJSONObject(Cacao.Payload.ATT_KEY);
        Iterator<String> keys = jSONObject.keys();
        String str3 = "keys(...)";
        Intrinsics.checkNotNullExpressionValue(keys, str3);
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            Iterator<String> keys2 = jSONObject2.keys();
            Intrinsics.checkNotNullExpressionValue(keys2, str3);
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                JSONArray jSONArray = jSONObject2.getJSONArray(next2);
                ArrayList arrayList = new ArrayList();
                int length = jSONArray.length();
                for (int i3 = 0; i3 < length; i3++) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i3);
                    Iterator<String> keys3 = jSONObject3.keys();
                    Intrinsics.checkNotNullExpressionValue(keys3, str3);
                    while (keys3.hasNext()) {
                        JSONArray jSONArray2 = jSONObject3.getJSONArray(keys3.next());
                        JSONObject jSONObject4 = jSONObject;
                        int length2 = jSONArray2.length();
                        Iterator<String> it = keys;
                        int i4 = 0;
                        while (i4 < length2) {
                            int i5 = length2;
                            String string = jSONArray2.getString(i4);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                            arrayList.add(string);
                            i4++;
                            length2 = i5;
                            str3 = str3;
                        }
                        jSONObject = jSONObject4;
                        keys = it;
                    }
                    JSONObject jSONObject5 = jSONObject;
                    Iterator<String> it2 = keys;
                    String str4 = str3;
                }
                JSONObject jSONObject6 = jSONObject;
                Iterator<String> it3 = keys;
                String str5 = str3;
                linkedHashMap2.put(next2, arrayList);
            }
            JSONObject jSONObject7 = jSONObject;
            Iterator<String> it4 = keys;
            String str6 = str3;
            linkedHashMap.put(next, linkedHashMap2);
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            linkedHashMap3.put(entry.getKey(), MapsKt.toMutableMap((Map) entry.getValue()));
        }
        return MapsKt.toMutableMap(linkedHashMap3);
    }
}
