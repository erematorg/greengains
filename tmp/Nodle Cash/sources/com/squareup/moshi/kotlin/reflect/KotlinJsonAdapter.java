package com.squareup.moshi.kotlin.reflect;

import android.support.v4.media.session.a;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.internal.Util;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002 !BU\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u001c\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u0006\u0012\u001a\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0015\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0016R'\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R%\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\""}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter;", "T", "Lcom/squareup/moshi/JsonAdapter;", "constructor", "Lkotlin/reflect/KFunction;", "allBindings", "", "Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$Binding;", "", "nonIgnoredBindings", "options", "Lcom/squareup/moshi/JsonReader$Options;", "(Lkotlin/reflect/KFunction;Ljava/util/List;Ljava/util/List;Lcom/squareup/moshi/JsonReader$Options;)V", "getAllBindings", "()Ljava/util/List;", "getConstructor", "()Lkotlin/reflect/KFunction;", "getNonIgnoredBindings", "getOptions", "()Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "(Lcom/squareup/moshi/JsonReader;)Ljava/lang/Object;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value", "(Lcom/squareup/moshi/JsonWriter;Ljava/lang/Object;)V", "toString", "", "Binding", "IndexedParameterMap", "moshi-kotlin"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KotlinJsonAdapter<T> extends JsonAdapter<T> {
    @NotNull
    private final List<Binding<T, Object>> allBindings;
    @NotNull
    private final KFunction<T> constructor;
    @NotNull
    private final List<Binding<T, Object>> nonIgnoredBindings;
    @NotNull
    private final JsonReader.Options options;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\b\b\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u0003BA\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007HÆ\u0003J\u0015\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\tHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010\u001d\u001a\u00020\rHÆ\u0003J[\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\u0013\u0010\"\u001a\u00028\u00022\u0006\u0010#\u001a\u00028\u0001¢\u0006\u0002\u0010$J\t\u0010%\u001a\u00020\rHÖ\u0001J\u001b\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00028\u00012\u0006\u0010#\u001a\u00028\u0002¢\u0006\u0002\u0010)J\t\u0010*\u001a\u00020\u0005HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006+"}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$Binding;", "K", "P", "", "jsonName", "", "adapter", "Lcom/squareup/moshi/JsonAdapter;", "property", "Lkotlin/reflect/KProperty1;", "parameter", "Lkotlin/reflect/KParameter;", "propertyIndex", "", "(Ljava/lang/String;Lcom/squareup/moshi/JsonAdapter;Lkotlin/reflect/KProperty1;Lkotlin/reflect/KParameter;I)V", "getAdapter", "()Lcom/squareup/moshi/JsonAdapter;", "getJsonName", "()Ljava/lang/String;", "getParameter", "()Lkotlin/reflect/KParameter;", "getProperty", "()Lkotlin/reflect/KProperty1;", "getPropertyIndex", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "get", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", "set", "", "result", "(Ljava/lang/Object;Ljava/lang/Object;)V", "toString", "moshi-kotlin"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Binding<K, P> {
        @NotNull
        private final JsonAdapter<P> adapter;
        @NotNull
        private final String jsonName;
        @Nullable
        private final KParameter parameter;
        @NotNull
        private final KProperty1<K, P> property;
        private final int propertyIndex;

        public Binding(@NotNull String str, @NotNull JsonAdapter<P> jsonAdapter, @NotNull KProperty1<K, ? extends P> kProperty1, @Nullable KParameter kParameter, int i3) {
            Intrinsics.checkNotNullParameter(str, "jsonName");
            Intrinsics.checkNotNullParameter(jsonAdapter, "adapter");
            Intrinsics.checkNotNullParameter(kProperty1, "property");
            this.jsonName = str;
            this.adapter = jsonAdapter;
            this.property = kProperty1;
            this.parameter = kParameter;
            this.propertyIndex = i3;
        }

        public static /* synthetic */ Binding copy$default(Binding binding, String str, JsonAdapter<P> jsonAdapter, KProperty1<K, P> kProperty1, KParameter kParameter, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                str = binding.jsonName;
            }
            if ((i4 & 2) != 0) {
                jsonAdapter = binding.adapter;
            }
            JsonAdapter<P> jsonAdapter2 = jsonAdapter;
            if ((i4 & 4) != 0) {
                kProperty1 = binding.property;
            }
            KProperty1<K, P> kProperty12 = kProperty1;
            if ((i4 & 8) != 0) {
                kParameter = binding.parameter;
            }
            KParameter kParameter2 = kParameter;
            if ((i4 & 16) != 0) {
                i3 = binding.propertyIndex;
            }
            return binding.copy(str, jsonAdapter2, kProperty12, kParameter2, i3);
        }

        @NotNull
        public final String component1() {
            return this.jsonName;
        }

        @NotNull
        public final JsonAdapter<P> component2() {
            return this.adapter;
        }

        @NotNull
        public final KProperty1<K, P> component3() {
            return this.property;
        }

        @Nullable
        public final KParameter component4() {
            return this.parameter;
        }

        public final int component5() {
            return this.propertyIndex;
        }

        @NotNull
        public final Binding<K, P> copy(@NotNull String str, @NotNull JsonAdapter<P> jsonAdapter, @NotNull KProperty1<K, ? extends P> kProperty1, @Nullable KParameter kParameter, int i3) {
            Intrinsics.checkNotNullParameter(str, "jsonName");
            Intrinsics.checkNotNullParameter(jsonAdapter, "adapter");
            Intrinsics.checkNotNullParameter(kProperty1, "property");
            return new Binding(str, jsonAdapter, kProperty1, kParameter, i3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Binding)) {
                return false;
            }
            Binding binding = (Binding) obj;
            return Intrinsics.areEqual((Object) this.jsonName, (Object) binding.jsonName) && Intrinsics.areEqual((Object) this.adapter, (Object) binding.adapter) && Intrinsics.areEqual((Object) this.property, (Object) binding.property) && Intrinsics.areEqual((Object) this.parameter, (Object) binding.parameter) && this.propertyIndex == binding.propertyIndex;
        }

        public final P get(K k2) {
            return this.property.get(k2);
        }

        @NotNull
        public final JsonAdapter<P> getAdapter() {
            return this.adapter;
        }

        @NotNull
        public final String getJsonName() {
            return this.jsonName;
        }

        @Nullable
        public final KParameter getParameter() {
            return this.parameter;
        }

        @NotNull
        public final KProperty1<K, P> getProperty() {
            return this.property;
        }

        public final int getPropertyIndex() {
            return this.propertyIndex;
        }

        public int hashCode() {
            int hashCode = (this.property.hashCode() + ((this.adapter.hashCode() + (this.jsonName.hashCode() * 31)) * 31)) * 31;
            KParameter kParameter = this.parameter;
            return Integer.hashCode(this.propertyIndex) + ((hashCode + (kParameter == null ? 0 : kParameter.hashCode())) * 31);
        }

        public final void set(K k2, P p2) {
            if (p2 != KotlinJsonAdapterKt.ABSENT_VALUE) {
                KProperty1<K, P> kProperty1 = this.property;
                Intrinsics.checkNotNull(kProperty1, "null cannot be cast to non-null type kotlin.reflect.KMutableProperty1<K of com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding, P of com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding>");
                ((KMutableProperty1) kProperty1).set(k2, p2);
            }
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("Binding(jsonName=");
            sb.append(this.jsonName);
            sb.append(", adapter=");
            sb.append(this.adapter);
            sb.append(", property=");
            sb.append(this.property);
            sb.append(", parameter=");
            sb.append(this.parameter);
            sb.append(", propertyIndex=");
            return a.p(sb, this.propertyIndex, ')');
        }
    }

    @SourceDebugExtension({"SMAP\nKotlinJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KotlinJsonAdapter.kt\ncom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$IndexedParameterMap\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,328:1\n1559#2:329\n1590#2,4:330\n857#2,2:334\n*S KotlinDebug\n*F\n+ 1 KotlinJsonAdapter.kt\ncom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$IndexedParameterMap\n*L\n172#1:329\n172#1:330,4\n175#1:334,2\n*E\n"})
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u0002H\u0002J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u0016R(\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000b0\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$IndexedParameterMap;", "Lkotlin/collections/AbstractMutableMap;", "Lkotlin/reflect/KParameter;", "", "parameterKeys", "", "parameterValues", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "[Ljava/lang/Object;", "containsKey", "", "key", "get", "put", "value", "moshi-kotlin"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class IndexedParameterMap extends AbstractMutableMap<KParameter, Object> {
        @NotNull
        private final List<KParameter> parameterKeys;
        @NotNull
        private final Object[] parameterValues;

        public IndexedParameterMap(@NotNull List<? extends KParameter> list, @NotNull Object[] objArr) {
            Intrinsics.checkNotNullParameter(list, "parameterKeys");
            Intrinsics.checkNotNullParameter(objArr, "parameterValues");
            this.parameterKeys = list;
            this.parameterValues = objArr;
        }

        public final /* bridge */ boolean containsKey(Object obj) {
            if (!(obj instanceof KParameter)) {
                return false;
            }
            return containsKey((KParameter) obj);
        }

        public final /* bridge */ Object get(Object obj) {
            if (!(obj instanceof KParameter)) {
                return null;
            }
            return get((KParameter) obj);
        }

        @NotNull
        public Set<Map.Entry<KParameter, Object>> getEntries() {
            Iterable iterable = this.parameterKeys;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
            int i3 = 0;
            for (Object next : iterable) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList.add(new AbstractMap.SimpleEntry((KParameter) next, this.parameterValues[i3]));
                i3 = i4;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next2 = it.next();
                if (((AbstractMap.SimpleEntry) next2).getValue() != KotlinJsonAdapterKt.ABSENT_VALUE) {
                    linkedHashSet.add(next2);
                }
            }
            return linkedHashSet;
        }

        public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
            return !(obj instanceof KParameter) ? obj2 : getOrDefault((KParameter) obj, obj2);
        }

        @Nullable
        public Object put(@NotNull KParameter kParameter, @Nullable Object obj) {
            Intrinsics.checkNotNullParameter(kParameter, JwtUtilsKt.DID_METHOD_KEY);
            return null;
        }

        public final /* bridge */ Object remove(Object obj) {
            if (!(obj instanceof KParameter)) {
                return null;
            }
            return remove((KParameter) obj);
        }

        public boolean containsKey(@NotNull KParameter kParameter) {
            Intrinsics.checkNotNullParameter(kParameter, JwtUtilsKt.DID_METHOD_KEY);
            return this.parameterValues[kParameter.getIndex()] != KotlinJsonAdapterKt.ABSENT_VALUE;
        }

        @Nullable
        public Object get(@NotNull KParameter kParameter) {
            Intrinsics.checkNotNullParameter(kParameter, JwtUtilsKt.DID_METHOD_KEY);
            Object obj = this.parameterValues[kParameter.getIndex()];
            if (obj != KotlinJsonAdapterKt.ABSENT_VALUE) {
                return obj;
            }
            return null;
        }

        public /* bridge */ Object getOrDefault(KParameter kParameter, Object obj) {
            return super.getOrDefault(kParameter, obj);
        }

        public /* bridge */ Object remove(KParameter kParameter) {
            return super.remove(kParameter);
        }

        public final /* bridge */ boolean remove(Object obj, Object obj2) {
            if (!(obj instanceof KParameter)) {
                return false;
            }
            return remove((KParameter) obj, obj2);
        }

        public /* bridge */ boolean remove(KParameter kParameter, Object obj) {
            return super.remove(kParameter, obj);
        }
    }

    public KotlinJsonAdapter(@NotNull KFunction<? extends T> kFunction, @NotNull List<Binding<T, Object>> list, @NotNull List<Binding<T, Object>> list2, @NotNull JsonReader.Options options2) {
        Intrinsics.checkNotNullParameter(kFunction, "constructor");
        Intrinsics.checkNotNullParameter(list, "allBindings");
        Intrinsics.checkNotNullParameter(list2, "nonIgnoredBindings");
        Intrinsics.checkNotNullParameter(options2, "options");
        this.constructor = kFunction;
        this.allBindings = list;
        this.nonIgnoredBindings = list2;
        this.options = options2;
    }

    public T fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        int size = this.constructor.getParameters().size();
        int size2 = this.allBindings.size();
        Object[] objArr = new Object[size2];
        for (int i3 = 0; i3 < size2; i3++) {
            objArr[i3] = KotlinJsonAdapterKt.ABSENT_VALUE;
        }
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                Binding binding = this.nonIgnoredBindings.get(selectName);
                int propertyIndex = binding.getPropertyIndex();
                if (objArr[propertyIndex] == KotlinJsonAdapterKt.ABSENT_VALUE) {
                    Object fromJson = binding.getAdapter().fromJson(jsonReader);
                    objArr[propertyIndex] = fromJson;
                    if (fromJson == null && !binding.getProperty().getReturnType().isMarkedNullable()) {
                        JsonDataException unexpectedNull = Util.unexpectedNull(binding.getProperty().getName(), binding.getJsonName(), jsonReader);
                        Intrinsics.checkNotNullExpressionValue(unexpectedNull, "unexpectedNull(\n        …         reader\n        )");
                        throw unexpectedNull;
                    }
                } else {
                    throw new JsonDataException("Multiple values for '" + binding.getProperty().getName() + "' at " + jsonReader.getPath());
                }
            }
        }
        jsonReader.endObject();
        boolean z2 = this.allBindings.size() == size;
        for (int i4 = 0; i4 < size; i4++) {
            if (objArr[i4] == KotlinJsonAdapterKt.ABSENT_VALUE) {
                if (this.constructor.getParameters().get(i4).isOptional()) {
                    z2 = false;
                } else {
                    String str = null;
                    if (this.constructor.getParameters().get(i4).getType().isMarkedNullable()) {
                        objArr[i4] = null;
                    } else {
                        String name = this.constructor.getParameters().get(i4).getName();
                        Binding binding2 = this.allBindings.get(i4);
                        if (binding2 != null) {
                            str = binding2.getJsonName();
                        }
                        JsonDataException missingProperty = Util.missingProperty(name, str, jsonReader);
                        Intrinsics.checkNotNullExpressionValue(missingProperty, "missingProperty(\n       …       reader\n          )");
                        throw missingProperty;
                    }
                }
            }
        }
        T call = z2 ? this.constructor.call(Arrays.copyOf(objArr, size2)) : this.constructor.callBy(new IndexedParameterMap(this.constructor.getParameters(), objArr));
        int size3 = this.allBindings.size();
        while (size < size3) {
            Binding<T, Object> binding3 = this.allBindings.get(size);
            Intrinsics.checkNotNull(binding3);
            binding3.set(call, objArr[size]);
            size++;
        }
        return call;
    }

    @NotNull
    public final List<Binding<T, Object>> getAllBindings() {
        return this.allBindings;
    }

    @NotNull
    public final KFunction<T> getConstructor() {
        return this.constructor;
    }

    @NotNull
    public final List<Binding<T, Object>> getNonIgnoredBindings() {
        return this.nonIgnoredBindings;
    }

    @NotNull
    public final JsonReader.Options getOptions() {
        return this.options;
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable T t2) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (t2 != null) {
            jsonWriter.beginObject();
            for (Binding next : this.allBindings) {
                if (next != null) {
                    jsonWriter.name(next.getJsonName());
                    next.getAdapter().toJson(jsonWriter, next.get(t2));
                }
            }
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value == null");
    }

    @NotNull
    public String toString() {
        return "KotlinJsonAdapter(" + this.constructor.getReturnType() + ')';
    }
}
