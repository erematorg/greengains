package com.apollographql.apollo3.api.json;

import com.apollographql.apollo3.annotations.ApolloInternal;
import com.apollographql.apollo3.api.json.JsonReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0018\u0010\u0003\u001a\u00020\u0002*\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0006\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¨\u0006\b"}, d2 = {"guessNumber", "", "Lcom/apollographql/apollo3/api/json/JsonReader;", "jsonReader", "", "", "Lokio/BufferedSource;", "readAny", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@JvmName(name = "-JsonReaders")
/* renamed from: com.apollographql.apollo3.api.json.-JsonReaders  reason: invalid class name */
public final class JsonReaders {

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* renamed from: com.apollographql.apollo3.api.json.-JsonReaders$WhenMappings */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.apollographql.apollo3.api.json.JsonReader$Token[] r0 = com.apollographql.apollo3.api.json.JsonReader.Token.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.NULL     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.LONG     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.NUMBER     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.STRING     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.BEGIN_OBJECT     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.BEGIN_ARRAY     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.JsonReaders.WhenMappings.<clinit>():void");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return r2.nextNumber();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        return java.lang.Long.valueOf(r2.nextLong());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        return java.lang.Double.valueOf(r2.nextDouble());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0009 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Object guessNumber(com.apollographql.apollo3.api.json.JsonReader r2) {
        /*
            int r0 = r2.nextInt()     // Catch:{ Exception -> 0x0009 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0009 }
            return r2
        L_0x0009:
            long r0 = r2.nextLong()     // Catch:{ Exception -> 0x0012 }
            java.lang.Long r2 = java.lang.Long.valueOf(r0)     // Catch:{ Exception -> 0x0012 }
            return r2
        L_0x0012:
            double r0 = r2.nextDouble()     // Catch:{ Exception -> 0x001b }
            java.lang.Double r2 = java.lang.Double.valueOf(r0)     // Catch:{ Exception -> 0x001b }
            return r2
        L_0x001b:
            com.apollographql.apollo3.api.json.JsonNumber r2 = r2.nextNumber()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.json.JsonReaders.guessNumber(com.apollographql.apollo3.api.json.JsonReader):java.lang.Object");
    }

    @NotNull
    public static final JsonReader jsonReader(@NotNull BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        return new BufferedSourceJsonReader(bufferedSource);
    }

    @Nullable
    @ApolloInternal
    public static final Object readAny(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "<this>");
        JsonReader.Token peek = jsonReader.peek();
        switch (WhenMappings.$EnumSwitchMapping$0[peek.ordinal()]) {
            case 1:
                return jsonReader.nextNull();
            case 2:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 3:
            case 4:
                return guessNumber(jsonReader);
            case 5:
                return jsonReader.nextString();
            case 6:
                jsonReader.beginObject();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                while (jsonReader.hasNext()) {
                    linkedHashMap.put(jsonReader.nextName(), readAny(jsonReader));
                }
                jsonReader.endObject();
                return linkedHashMap;
            case 7:
                jsonReader.beginArray();
                ArrayList arrayList = new ArrayList();
                while (jsonReader.hasNext()) {
                    arrayList.add(readAny(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            default:
                throw new IllegalStateException(("unknown token " + peek).toString());
        }
    }

    @NotNull
    public static final JsonReader jsonReader(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return new MapJsonReader(map, (List) null, 2, (DefaultConstructorMarker) null);
    }
}
