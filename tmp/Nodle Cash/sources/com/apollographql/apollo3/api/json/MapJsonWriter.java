package com.apollographql.apollo3.api.json;

import androidx.compose.animation.core.a;
import com.apollographql.apollo3.api.Upload;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u0001H\u0016J\b\u0010\u000f\u001a\u00020\u0001H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0001H\u0016J\b\u0010\u0013\u001a\u00020\u0001H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0004H\u0016J\b\u0010\u0016\u001a\u00020\u0000H\u0016J\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0019H\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\nH\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u001aH\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u001bH\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u001cH\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0004H\u0016J\u001d\u0010\u001d\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u0001H\u001eH\u0002¢\u0006\u0002\u0010\u001fJ\u001a\u0010 \u001a\u0004\u0018\u00010\b*\u0004\u0018\u00010\b2\b\u0010!\u001a\u0004\u0018\u00010\bH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonWriter;", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "()V", "path", "", "getPath", "()Ljava/lang/String;", "root", "", "rootSet", "", "stack", "", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State;", "beginArray", "beginObject", "close", "", "endArray", "endObject", "flush", "name", "nullValue", "value", "Lcom/apollographql/apollo3/api/Upload;", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "", "", "", "valueInternal", "T", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/json/MapJsonWriter;", "mergeWith", "other", "State", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nMapJsonWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapJsonWriter.kt\ncom/apollographql/apollo3/api/json/MapJsonWriter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,184:1\n1549#2:185\n1620#2,3:186\n1549#2:189\n1620#2,3:190\n1549#2:193\n1620#2,3:194\n*S KotlinDebug\n*F\n+ 1 MapJsonWriter.kt\ncom/apollographql/apollo3/api/json/MapJsonWriter\n*L\n84#1:185\n84#1:186,3\n97#1:189\n97#1:190,3\n152#1:193\n152#1:194,3\n*E\n"})
public final class MapJsonWriter implements JsonWriter {
    @Nullable
    private Object root;
    private boolean rootSet;
    @NotNull
    private final List<State> stack = new ArrayList();

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonWriter$State;", "", "()V", "List", "Map", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State$List;", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State$Map;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static abstract class State {

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0019\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonWriter$State$List;", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State;", "list", "", "", "(Ljava/util/List;)V", "getList", "()Ljava/util/List;", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class List extends State {
            @NotNull
            private final java.util.List<Object> list;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public List(@NotNull java.util.List<Object> list2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list2, SchemaSymbols.ATTVAL_LIST);
                this.list = list2;
            }

            @NotNull
            public final java.util.List<Object> getList() {
                return this.list;
            }

            @NotNull
            public String toString() {
                return "List (" + this.list.size() + ')';
            }
        }

        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u0004H\u0016R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonWriter$State$Map;", "Lcom/apollographql/apollo3/api/json/MapJsonWriter$State;", "map", "", "", "", "name", "(Ljava/util/Map;Ljava/lang/String;)V", "getMap", "()Ljava/util/Map;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "toString", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Map extends State {
            @NotNull
            private final java.util.Map<String, Object> map;
            @Nullable
            private String name;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Map(@NotNull java.util.Map<String, Object> map2, @Nullable String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(map2, "map");
                this.map = map2;
                this.name = str;
            }

            @NotNull
            public final java.util.Map<String, Object> getMap() {
                return this.map;
            }

            @Nullable
            public final String getName() {
                return this.name;
            }

            public final void setName(@Nullable String str) {
                this.name = str;
            }

            @NotNull
            public String toString() {
                return a.o(')', this.name, new StringBuilder("Map ("));
            }
        }

        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private State() {
        }
    }

    private final Object mergeWith(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj2 == null) {
            return obj;
        }
        if (obj instanceof List) {
            if (obj2 instanceof List) {
                List list = (List) obj;
                List list2 = (List) obj2;
                if (list.size() == list2.size()) {
                    IntRange indices = CollectionsKt.getIndices((Collection) obj);
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(indices, 10));
                    Iterator it = indices.iterator();
                    while (it.hasNext()) {
                        int nextInt = ((IntIterator) it).nextInt();
                        arrayList.add(mergeWith(list.get(nextInt), list2.get(nextInt)));
                    }
                    return arrayList;
                }
                throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
            }
            throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
        } else if (obj instanceof Map) {
            if (obj2 instanceof Map) {
                Map map = (Map) obj;
                Map map2 = (Map) obj2;
                Iterable<String> plus = SetsKt.plus(map.keySet(), map2.keySet());
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(plus, 10));
                for (String str : plus) {
                    arrayList2.add(TuplesKt.to(str, mergeWith(map.get(str), map2.get(str))));
                }
                return MapsKt.toMap(arrayList2);
            }
            throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
        } else if (Intrinsics.areEqual(obj, obj2)) {
            return obj;
        } else {
            throw new IllegalStateException(("Cannot merge " + obj + " with " + obj2).toString());
        }
    }

    private final <T> MapJsonWriter valueInternal(T t2) {
        State state = (State) CollectionsKt.lastOrNull(this.stack);
        if (state instanceof State.Map) {
            State.Map map = (State.Map) state;
            String name = map.getName();
            if (name != null) {
                if (map.getMap().containsKey(name)) {
                    map.getMap().put(name, mergeWith(map.getMap().get(name), t2));
                } else {
                    map.getMap().put(name, t2);
                }
                map.setName((String) null);
            } else {
                throw new IllegalStateException("Check failed.");
            }
        } else if (state instanceof State.List) {
            ((State.List) state).getList().add(t2);
        } else {
            this.root = t2;
            this.rootSet = true;
        }
        return this;
    }

    @NotNull
    public JsonWriter beginArray() {
        this.stack.add(new State.List(new ArrayList()));
        return this;
    }

    @NotNull
    public JsonWriter beginObject() {
        this.stack.add(new State.Map(new LinkedHashMap(), (String) null));
        return this;
    }

    public void close() {
    }

    @NotNull
    public JsonWriter endArray() {
        List<State> list = this.stack;
        State remove = list.remove(list.size() - 1);
        if (remove instanceof State.List) {
            valueInternal(((State.List) remove).getList());
            return this;
        }
        throw new IllegalStateException("Check failed.");
    }

    @NotNull
    public JsonWriter endObject() {
        List<State> list = this.stack;
        State remove = list.remove(list.size() - 1);
        if (remove instanceof State.Map) {
            valueInternal(((State.Map) remove).getMap());
            return this;
        }
        throw new IllegalStateException("Check failed.");
    }

    public void flush() {
    }

    @NotNull
    public String getPath() {
        String str;
        Iterable<State> iterable = this.stack;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (State state : iterable) {
            if (state instanceof State.List) {
                str = String.valueOf(((State.List) state).getList().size());
            } else if (state instanceof State.Map) {
                str = ((State.Map) state).getName();
                if (str == null) {
                    str = "?";
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
            arrayList.add(str);
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, JwtUtilsKt.JWT_DELIMITER, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public JsonWriter name(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        State state = (State) CollectionsKt.last(this.stack);
        if (state instanceof State.Map) {
            State.Map map = (State.Map) state;
            if (map.getName() == null) {
                map.setName(str);
                return this;
            }
            throw new IllegalStateException("Check failed.");
        }
        throw new IllegalStateException("Check failed.");
    }

    @Nullable
    public final Object root() {
        if (this.rootSet) {
            return this.root;
        }
        throw new IllegalStateException("Check failed.");
    }

    @NotNull
    public MapJsonWriter nullValue() {
        return valueInternal((Object) null);
    }

    @NotNull
    public MapJsonWriter value(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        return valueInternal(str);
    }

    @NotNull
    public MapJsonWriter value(boolean z2) {
        return valueInternal(Boolean.valueOf(z2));
    }

    @NotNull
    public MapJsonWriter value(double d2) {
        return valueInternal(Double.valueOf(d2));
    }

    @NotNull
    public MapJsonWriter value(int i3) {
        return valueInternal(Integer.valueOf(i3));
    }

    @NotNull
    public MapJsonWriter value(long j2) {
        return valueInternal(Long.valueOf(j2));
    }

    @NotNull
    public MapJsonWriter value(@NotNull JsonNumber jsonNumber) {
        Intrinsics.checkNotNullParameter(jsonNumber, "value");
        return valueInternal(jsonNumber);
    }

    @NotNull
    public MapJsonWriter value(@NotNull Upload upload) {
        Intrinsics.checkNotNullParameter(upload, "value");
        return valueInternal((Object) null);
    }

    @NotNull
    public final MapJsonWriter value(@Nullable Object obj) {
        return valueInternal(obj);
    }
}
