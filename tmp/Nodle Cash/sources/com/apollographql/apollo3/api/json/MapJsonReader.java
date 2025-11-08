package com.apollographql.apollo3.api.json;

import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.internal.UtilsKt;
import com.apollographql.apollo3.exception.JsonDataException;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 <2\u00020\u0001:\u0001<B-\b\u0007\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0012\u0010\u001c\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010\u001e\u001a\u00020\u0000H\u0016J\b\u0010\u001f\u001a\u00020\u0000H\u0016J\b\u0010 \u001a\u00020\u001bH\u0016J\b\u0010!\u001a\u00020\u0000H\u0016J\b\u0010\"\u001a\u00020\u0000H\u0016J\u001e\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u00042\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0002J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0016J\b\u0010'\u001a\u00020\u0004H\u0002J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020)H\u0016J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u0019H\u0016J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020\u0004H\u0016J\n\u00101\u001a\u0004\u0018\u000102H\u0016J\b\u00103\u001a\u000204H\u0016J\n\u00105\u001a\u0004\u0018\u00010\u0004H\u0016J\u0006\u00106\u001a\u00020\u0005J\b\u00107\u001a\u00020\u0015H\u0016J\b\u00108\u001a\u00020\u001bH\u0016J\u0016\u00109\u001a\u00020\u00192\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0016J\b\u0010;\u001a\u00020\u001bH\u0016R&\u0010\t\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u00030\nX\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\r0\nX\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\nX\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonReader;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "root", "", "", "", "pathRoot", "", "(Ljava/util/Map;Ljava/util/List;)V", "containerStack", "", "[Ljava/util/Map;", "iteratorStack", "", "[Ljava/util/Iterator;", "nameIndexStack", "", "path", "[Ljava/lang/Object;", "peekedData", "peekedToken", "Lcom/apollographql/apollo3/api/json/JsonReader$Token;", "getRoot", "()Ljava/util/Map;", "stackSize", "", "advanceIterator", "", "anyToToken", "any", "beginArray", "beginObject", "close", "endArray", "endObject", "findName", "needle", "haystack", "getPath", "getPathAsString", "hasNext", "", "nextBoolean", "nextDouble", "", "nextInt", "nextLong", "", "nextName", "nextNull", "", "nextNumber", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "nextString", "nextValue", "peek", "rewind", "selectName", "names", "skipValue", "Companion", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nMapJsonReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapJsonReader.kt\ncom/apollographql/apollo3/api/json/MapJsonReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,421:1\n1#2:422\n*E\n"})
public final class MapJsonReader implements JsonReader {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    private Map<String, Object>[] containerStack;
    @NotNull
    private final Iterator<?>[] iteratorStack;
    @NotNull
    private final int[] nameIndexStack;
    @NotNull
    private final Object[] path;
    @NotNull
    private final List<Object> pathRoot;
    @Nullable
    private Object peekedData;
    @NotNull
    private JsonReader.Token peekedToken;
    @NotNull
    private final Map<String, Object> root;
    private int stackSize;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo3/api/json/MapJsonReader$Companion;", "", "()V", "buffer", "Lcom/apollographql/apollo3/api/json/MapJsonReader;", "Lcom/apollographql/apollo3/api/json/JsonReader;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final MapJsonReader buffer(@NotNull JsonReader jsonReader) {
            Intrinsics.checkNotNullParameter(jsonReader, "<this>");
            if (jsonReader instanceof MapJsonReader) {
                return (MapJsonReader) jsonReader;
            }
            JsonReader.Token peek = jsonReader.peek();
            if (peek == JsonReader.Token.BEGIN_OBJECT) {
                List<Object> path = jsonReader.getPath();
                Object readAny = JsonReaders.readAny(jsonReader);
                Intrinsics.checkNotNull(readAny, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
                return new MapJsonReader((Map) readAny, path);
            }
            throw new IllegalStateException(("Failed to buffer json reader, expected `BEGIN_OBJECT` but found `" + peek + "` json token").toString());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.apollographql.apollo3.api.json.JsonReader$Token[] r0 = com.apollographql.apollo3.api.json.JsonReader.Token.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.END_OBJECT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.END_ARRAY     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.STRING     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.NUMBER     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.LONG     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.MapJsonReader.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public MapJsonReader(@NotNull Map<String, ? extends Object> map) {
        this(map, (List) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(map, "root");
    }

    private final void advanceIterator() {
        int i3 = this.stackSize;
        if (i3 == 0) {
            this.peekedToken = JsonReader.Token.END_DOCUMENT;
            return;
        }
        Iterator<?> it = this.iteratorStack[i3 - 1];
        Intrinsics.checkNotNull(it);
        Object[] objArr = this.path;
        int i4 = this.stackSize;
        if (objArr[i4 - 1] instanceof Integer) {
            int i5 = i4 - 1;
            Object obj = objArr[i4 - 1];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            objArr[i5] = Integer.valueOf(((Integer) obj).intValue() + 1);
        }
        if (it.hasNext()) {
            Object next = it.next();
            this.peekedData = next;
            this.peekedToken = next instanceof Map.Entry ? JsonReader.Token.NAME : anyToToken(next);
            return;
        }
        this.peekedToken = this.path[this.stackSize + -1] instanceof Integer ? JsonReader.Token.END_ARRAY : JsonReader.Token.END_OBJECT;
    }

    private final JsonReader.Token anyToToken(Object obj) {
        return obj == null ? JsonReader.Token.NULL : obj instanceof List ? JsonReader.Token.BEGIN_ARRAY : obj instanceof Map ? JsonReader.Token.BEGIN_OBJECT : obj instanceof Integer ? JsonReader.Token.NUMBER : obj instanceof Long ? JsonReader.Token.LONG : obj instanceof Double ? JsonReader.Token.NUMBER : obj instanceof JsonNumber ? JsonReader.Token.NUMBER : obj instanceof String ? JsonReader.Token.STRING : obj instanceof Boolean ? JsonReader.Token.BOOLEAN : JsonReader.Token.ANY;
    }

    private final int findName(String str, List<String> list) {
        int i3 = this.nameIndexStack[this.stackSize - 1];
        if (i3 >= list.size() || !Intrinsics.areEqual((Object) list.get(i3), (Object) str)) {
            int indexOf = list.indexOf(str);
            if (indexOf != -1) {
                this.nameIndexStack[this.stackSize - 1] = indexOf + 1;
            }
            return indexOf;
        }
        int[] iArr = this.nameIndexStack;
        int i4 = this.stackSize;
        iArr[i4 - 1] = iArr[i4 - 1] + 1;
        return i3;
    }

    private final String getPathAsString() {
        return CollectionsKt___CollectionsKt.joinToString$default(getPath(), JwtUtilsKt.JWT_DELIMITER, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public void close() {
    }

    @NotNull
    public List<Object> getPath() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.pathRoot);
        int i3 = this.stackSize;
        for (int i4 = 0; i4 < i3; i4++) {
            Object obj = this.path[i4];
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @NotNull
    public final Map<String, Object> getRoot() {
        return this.root;
    }

    public boolean hasNext() {
        int i3 = WhenMappings.$EnumSwitchMapping$0[peek().ordinal()];
        return (i3 == 1 || i3 == 2) ? false : true;
    }

    public boolean nextBoolean() {
        if (peek() == JsonReader.Token.BOOLEAN) {
            Object obj = this.peekedData;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
            Boolean bool = (Boolean) obj;
            bool.getClass();
            advanceIterator();
            return bool.booleanValue();
        }
        throw new JsonDataException("Expected BOOLEAN but was " + peek() + " at path " + getPathAsString());
    }

    public double nextDouble() {
        double d2;
        int i3 = WhenMappings.$EnumSwitchMapping$0[peek().ordinal()];
        if (i3 == 3 || i3 == 4 || i3 == 5) {
            Object obj = this.peekedData;
            if (obj instanceof Integer) {
                d2 = (double) ((Number) obj).intValue();
            } else if (obj instanceof Long) {
                d2 = UtilsKt.m8216LongToDoubleExact(((Number) obj).longValue());
            } else if (obj instanceof Double) {
                d2 = ((Number) obj).doubleValue();
            } else if (obj instanceof String) {
                d2 = Double.parseDouble((String) obj);
            } else if (obj instanceof JsonNumber) {
                d2 = Double.parseDouble(((JsonNumber) obj).getValue());
            } else {
                throw new IllegalStateException(("Expected a Double but got " + obj + " instead").toString());
            }
            advanceIterator();
            return d2;
        }
        throw new JsonDataException("Expected a Double but was " + peek() + " at path " + getPathAsString());
    }

    public int nextInt() {
        int i3;
        int i4 = WhenMappings.$EnumSwitchMapping$0[peek().ordinal()];
        if (i4 == 3 || i4 == 4 || i4 == 5) {
            Object obj = this.peekedData;
            if (obj instanceof Integer) {
                i3 = ((Number) obj).intValue();
            } else if (obj instanceof Long) {
                i3 = UtilsKt.m8217LongToIntExact(((Number) obj).longValue());
            } else if (obj instanceof Double) {
                i3 = UtilsKt.m8214DoubleToIntExact(((Number) obj).doubleValue());
            } else if (obj instanceof String) {
                i3 = Integer.parseInt((String) obj);
            } else if (obj instanceof JsonNumber) {
                i3 = Integer.parseInt(((JsonNumber) obj).getValue());
            } else {
                throw new IllegalStateException(("Expected an Int but got " + obj + " instead").toString());
            }
            advanceIterator();
            return i3;
        }
        throw new JsonDataException("Expected an Int but was " + peek() + " at path " + getPathAsString());
    }

    public long nextLong() {
        long j2;
        int i3 = WhenMappings.$EnumSwitchMapping$0[peek().ordinal()];
        if (i3 == 3 || i3 == 4 || i3 == 5) {
            Object obj = this.peekedData;
            if (obj instanceof Integer) {
                j2 = (long) ((Number) obj).intValue();
            } else if (obj instanceof Long) {
                j2 = ((Number) obj).longValue();
            } else if (obj instanceof Double) {
                j2 = UtilsKt.m8215DoubleToLongExact(((Number) obj).doubleValue());
            } else if (obj instanceof String) {
                j2 = Long.parseLong((String) obj);
            } else if (obj instanceof JsonNumber) {
                j2 = Long.parseLong(((JsonNumber) obj).getValue());
            } else {
                throw new IllegalStateException(("Expected Int but got " + obj + " instead").toString());
            }
            advanceIterator();
            return j2;
        }
        throw new JsonDataException("Expected a Long but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public String nextName() {
        if (peek() == JsonReader.Token.NAME) {
            Object obj = this.peekedData;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Map.Entry<kotlin.String, kotlin.Any?>");
            Map.Entry entry = (Map.Entry) obj;
            this.path[this.stackSize - 1] = entry.getKey();
            this.peekedData = entry.getValue();
            this.peekedToken = anyToToken(entry.getValue());
            return (String) entry.getKey();
        }
        throw new JsonDataException("Expected NAME but was " + peek() + " at path " + getPathAsString());
    }

    @Nullable
    public Void nextNull() {
        if (peek() == JsonReader.Token.NULL) {
            advanceIterator();
            return null;
        }
        throw new JsonDataException("Expected NULL but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public JsonNumber nextNumber() {
        JsonNumber jsonNumber;
        int i3 = WhenMappings.$EnumSwitchMapping$0[peek().ordinal()];
        if (i3 == 3 || i3 == 4 || i3 == 5) {
            Object obj = this.peekedData;
            boolean z2 = true;
            if (!(obj instanceof Integer ? true : obj instanceof Long)) {
                z2 = obj instanceof Double;
            }
            if (z2) {
                jsonNumber = new JsonNumber(obj.toString());
            } else if (obj instanceof String) {
                jsonNumber = new JsonNumber((String) obj);
            } else if (obj instanceof JsonNumber) {
                jsonNumber = (JsonNumber) obj;
            } else {
                throw new IllegalStateException(("Expected JsonNumber but got " + obj + " instead").toString());
            }
            advanceIterator();
            return jsonNumber;
        }
        throw new JsonDataException("Expected a Number but was " + peek() + " at path " + getPathAsString());
    }

    @Nullable
    public String nextString() {
        int i3 = WhenMappings.$EnumSwitchMapping$0[peek().ordinal()];
        if (i3 == 3 || i3 == 4 || i3 == 5) {
            Object obj = this.peekedData;
            Intrinsics.checkNotNull(obj);
            String obj2 = obj.toString();
            advanceIterator();
            return obj2;
        }
        throw new JsonDataException("Expected a String but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public final Object nextValue() {
        Object obj = this.peekedData;
        if (obj != null) {
            advanceIterator();
            return obj;
        }
        throw new JsonDataException("Expected a non-null value at path " + getPathAsString());
    }

    @NotNull
    public JsonReader.Token peek() {
        return this.peekedToken;
    }

    public void rewind() {
        Map<String, Object>[] mapArr = this.containerStack;
        int i3 = this.stackSize;
        Map<String, Object> map = mapArr[i3 - 1];
        this.path[i3 - 1] = null;
        Intrinsics.checkNotNull(map);
        this.iteratorStack[i3 - 1] = map.entrySet().iterator();
        this.nameIndexStack[this.stackSize - 1] = 0;
        advanceIterator();
    }

    public int selectName(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "names");
        while (hasNext()) {
            int findName = findName(nextName(), list);
            if (findName != -1) {
                return findName;
            }
            skipValue();
        }
        return -1;
    }

    public void skipValue() {
        advanceIterator();
    }

    @JvmOverloads
    public MapJsonReader(@NotNull Map<String, ? extends Object> map, @NotNull List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(map, "root");
        Intrinsics.checkNotNullParameter(list, "pathRoot");
        this.root = map;
        this.pathRoot = list;
        this.path = new Object[256];
        this.containerStack = new Map[256];
        this.iteratorStack = new Iterator[256];
        this.nameIndexStack = new int[256];
        this.peekedToken = JsonReader.Token.BEGIN_OBJECT;
        this.peekedData = map;
    }

    @NotNull
    public MapJsonReader beginArray() {
        if (peek() == JsonReader.Token.BEGIN_ARRAY) {
            Object obj = this.peekedData;
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
            List list = (List) obj;
            int i3 = this.stackSize;
            if (i3 < 256) {
                this.stackSize = i3 + 1;
                this.path[i3] = -1;
                this.iteratorStack[this.stackSize - 1] = list.iterator();
                advanceIterator();
                return this;
            }
            throw new IllegalStateException("Nesting too deep");
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public MapJsonReader beginObject() {
        if (peek() == JsonReader.Token.BEGIN_OBJECT) {
            int i3 = this.stackSize;
            if (i3 < 256) {
                this.stackSize = i3 + 1;
                Object[] objArr = this.containerStack;
                Object obj = this.peekedData;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
                objArr[i3] = obj;
                rewind();
                return this;
            }
            throw new IllegalStateException("Nesting too deep");
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public MapJsonReader endArray() {
        if (peek() == JsonReader.Token.END_ARRAY) {
            int i3 = this.stackSize - 1;
            this.stackSize = i3;
            this.iteratorStack[i3] = null;
            this.path[i3] = null;
            advanceIterator();
            return this;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + getPathAsString());
    }

    @NotNull
    public MapJsonReader endObject() {
        int i3 = this.stackSize - 1;
        this.stackSize = i3;
        this.iteratorStack[i3] = null;
        this.path[i3] = null;
        this.containerStack[i3] = null;
        advanceIterator();
        return this;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MapJsonReader(Map map, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, (i3 & 2) != 0 ? CollectionsKt.emptyList() : list);
    }
}
