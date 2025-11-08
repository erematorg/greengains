package com.reown.android.internal.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B=\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000b\u001a\u0004\u0018\u00018\u00012\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000eJ\u0017\u0010\u000f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010J\u001e\u0010\u0011\u001a\u00020\b2\u0014\u0010\u0012\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007H\u0016J\t\u0010\u0013\u001a\u00020\bH\u0001J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00152\u0006\u0010\r\u001a\u00028\u0001H\u0001¢\u0006\u0002\u0010\u0016J\u0018\u0010\u0018\u001a\u0004\u0018\u00018\u00012\u0006\u0010\f\u001a\u00028\u0000H\u0003¢\u0006\u0002\u0010\u0010J\t\u0010\u0019\u001a\u00020\u0015H\u0001R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0004¢\u0006\u0002\n\u0000R$\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c0\u001bX\u0005¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bX\u0005¢\u0006\u0006\u001a\u0004\b \u0010\u001eR\u0012\u0010!\u001a\u00020\"X\u0005¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00010&X\u0005¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u0006)"}, d2 = {"Lcom/reown/android/internal/utils/ObservableMap;", "K", "V", "", "map", "onChange", "Lkotlin/Function1;", "", "", "<init>", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "put", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "clear", "containsKey", "", "(Ljava/lang/Object;)Z", "containsValue", "get", "isEmpty", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nObservableMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ObservableMap.kt\ncom/reown/android/internal/utils/ObservableMap\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,19:1\n1#2:20\n*E\n"})
public final class ObservableMap<K, V> implements Map<K, V>, KMutableMap {
    @NotNull
    private final Map<K, V> map;
    @NotNull
    private final Function1<Map<K, ? extends V>, Unit> onChange;

    public ObservableMap(@NotNull Map<K, V> map2, @NotNull Function1<? super Map<K, ? extends V>, Unit> function1) {
        Intrinsics.checkNotNullParameter(map2, "map");
        Intrinsics.checkNotNullParameter(function1, "onChange");
        this.map = map2;
        this.onChange = function1;
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Nullable
    public V get(Object obj) {
        return this.map.get(obj);
    }

    @NotNull
    public Set<Map.Entry<K, V>> getEntries() {
        return this.map.entrySet();
    }

    @NotNull
    public Set<K> getKeys() {
        return this.map.keySet();
    }

    public int getSize() {
        return this.map.size();
    }

    @NotNull
    public Collection<V> getValues() {
        return this.map.values();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    @Nullable
    public V put(K k2, V v2) {
        V put = this.map.put(k2, v2);
        this.onChange.invoke(this.map);
        return put;
    }

    public void putAll(@NotNull Map<? extends K, ? extends V> map2) {
        Intrinsics.checkNotNullParameter(map2, "from");
        this.map.putAll(map2);
        Unit unit = Unit.INSTANCE;
        this.onChange.invoke(this.map);
    }

    @Nullable
    public V remove(Object obj) {
        V remove = this.map.remove(obj);
        this.onChange.invoke(this.map);
        return remove;
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ObservableMap(Map map2, Function1 function1, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? new LinkedHashMap() : map2, function1);
    }
}
