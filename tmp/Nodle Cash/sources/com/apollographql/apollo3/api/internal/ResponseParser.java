package com.apollographql.apollo3.api.internal;

import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Error;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonReaders;
import com.apollographql.apollo3.api.json.MapJsonReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\r\u001a\u00020\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010J\f\u0010\u0012\u001a\u00020\u000e*\u00020\bH\u0002J\f\u0010\u0013\u001a\u00020\u0014*\u00020\bH\u0002J\u0014\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0016*\u00020\bH\u0002J\u0012\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016*\u00020\bH\u0002J\u0014\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016*\u00020\bH\u0002¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/api/internal/ResponseParser;", "", "()V", "parse", "Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "jsonReader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "parseError", "Lcom/apollographql/apollo3/api/Error;", "payload", "", "", "readError", "readErrorLocation", "Lcom/apollographql/apollo3/api/Error$Location;", "readErrorLocations", "", "readErrors", "readPath", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nResponseParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResponseParser.kt\ncom/apollographql/apollo3/api/internal/ResponseParser\n+ 2 Okio.kt\nokio/Okio__OkioKt\n+ 3 uuid.kt\ncom/benasher44/uuid/UuidKt\n*L\n1#1,146:1\n52#2,5:147\n57#2,13:153\n96#3:152\n*S KotlinDebug\n*F\n+ 1 ResponseParser.kt\ncom/apollographql/apollo3/api/internal/ResponseParser\n*L\n24#1:147,5\n24#1:153,13\n42#1:152\n*E\n"})
public final class ResponseParser {
    @NotNull
    public static final ResponseParser INSTANCE = new ResponseParser();

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.apollographql.apollo3.api.json.JsonReader$Token[] r0 = com.apollographql.apollo3.api.json.JsonReader.Token.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.NUMBER     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.apollographql.apollo3.api.json.JsonReader$Token r1 = com.apollographql.apollo3.api.json.JsonReader.Token.LONG     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.internal.ResponseParser.WhenMappings.<clinit>():void");
        }
    }

    private ResponseParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.apollographql.apollo3.api.Error readError(com.apollographql.apollo3.api.json.JsonReader r10) {
        /*
            r9 = this;
            r10.beginObject()
            java.lang.String r0 = ""
            r1 = 0
            r3 = r0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x000b:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x0070
            java.lang.String r2 = r10.nextName()
            int r8 = r2.hashCode()
            switch(r8) {
                case -1809421292: goto L_0x004b;
                case -1197189282: goto L_0x003d;
                case 3433509: goto L_0x002f;
                case 954925063: goto L_0x001d;
                default: goto L_0x001c;
            }
        L_0x001c:
            goto L_0x0053
        L_0x001d:
            java.lang.String r8 = "message"
            boolean r8 = r2.equals(r8)
            if (r8 == 0) goto L_0x0053
            java.lang.String r2 = r10.nextString()
            if (r2 != 0) goto L_0x002d
            r3 = r0
            goto L_0x000b
        L_0x002d:
            r3 = r2
            goto L_0x000b
        L_0x002f:
            java.lang.String r8 = "path"
            boolean r8 = r2.equals(r8)
            if (r8 != 0) goto L_0x0038
            goto L_0x0053
        L_0x0038:
            java.util.List r5 = r9.readPath(r10)
            goto L_0x000b
        L_0x003d:
            java.lang.String r8 = "locations"
            boolean r8 = r2.equals(r8)
            if (r8 != 0) goto L_0x0046
            goto L_0x0053
        L_0x0046:
            java.util.List r4 = r9.readErrorLocations(r10)
            goto L_0x000b
        L_0x004b:
            java.lang.String r8 = "extensions"
            boolean r8 = r2.equals(r8)
            if (r8 != 0) goto L_0x0062
        L_0x0053:
            if (r7 != 0) goto L_0x005a
            java.util.LinkedHashMap r7 = new java.util.LinkedHashMap
            r7.<init>()
        L_0x005a:
            java.lang.Object r8 = com.apollographql.apollo3.api.json.JsonReaders.readAny(r10)
            r7.put(r2, r8)
            goto L_0x000b
        L_0x0062:
            java.lang.Object r2 = com.apollographql.apollo3.api.json.JsonReaders.readAny(r10)
            boolean r6 = r2 instanceof java.util.Map
            if (r6 == 0) goto L_0x006e
            java.util.Map r2 = (java.util.Map) r2
            r6 = r2
            goto L_0x000b
        L_0x006e:
            r6 = r1
            goto L_0x000b
        L_0x0070:
            r10.endObject()
            com.apollographql.apollo3.api.Error r9 = new com.apollographql.apollo3.api.Error
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.internal.ResponseParser.readError(com.apollographql.apollo3.api.json.JsonReader):com.apollographql.apollo3.api.Error");
    }

    private final Error.Location readErrorLocation(JsonReader jsonReader) {
        jsonReader.beginObject();
        int i3 = -1;
        int i4 = -1;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (Intrinsics.areEqual((Object) nextName, (Object) "line")) {
                i3 = jsonReader.nextInt();
            } else if (Intrinsics.areEqual((Object) nextName, (Object) "column")) {
                i4 = jsonReader.nextInt();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return new Error.Location(i3, i4);
    }

    private final List<Error.Location> readErrorLocations(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonReader.Token.NULL) {
            return (List) jsonReader.nextNull();
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(readErrorLocation(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    private final List<Error> readErrors(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonReader.Token.NULL) {
            jsonReader.nextNull();
            return CollectionsKt.emptyList();
        }
        jsonReader.beginArray();
        ArrayList arrayList = new ArrayList();
        while (jsonReader.hasNext()) {
            arrayList.add(readError(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    private final List<Object> readPath(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonReader.Token.NULL) {
            return (List) jsonReader.nextNull();
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            int i3 = WhenMappings.$EnumSwitchMapping$0[jsonReader.peek().ordinal()];
            if (i3 == 1 || i3 == 2) {
                arrayList.add(Integer.valueOf(jsonReader.nextInt()));
            } else {
                String nextString = jsonReader.nextString();
                Intrinsics.checkNotNull(nextString);
                arrayList.add(nextString);
            }
        }
        jsonReader.endArray();
        return arrayList;
    }

    @NotNull
    public final <D extends Operation.Data> ApolloResponse<D> parse(@NotNull JsonReader jsonReader, @NotNull Operation<D> operation, @NotNull CustomScalarAdapters customScalarAdapters) {
        ApolloResponse<D> apolloResponse;
        Intrinsics.checkNotNullParameter(jsonReader, "jsonReader");
        Intrinsics.checkNotNullParameter(operation, "operation");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Throwable th = null;
        try {
            jsonReader.beginObject();
            Operation.Data data = null;
            List<Error> list = null;
            Map map = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                int hashCode = nextName.hashCode();
                if (hashCode != -1809421292) {
                    if (hashCode != -1294635157) {
                        if (hashCode == 3076010) {
                            if (nextName.equals("data")) {
                                data = (Operation.Data) Adapters.m8201nullable(operation.adapter()).fromJson(jsonReader, customScalarAdapters);
                            }
                        }
                    } else if (nextName.equals("errors")) {
                        list = INSTANCE.readErrors(jsonReader);
                    }
                } else if (nextName.equals("extensions")) {
                    Object readAny = JsonReaders.readAny(jsonReader);
                    map = readAny instanceof Map ? (Map) readAny : null;
                }
                jsonReader.skipValue();
            }
            jsonReader.endObject();
            UUID randomUUID = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(randomUUID, "randomUUID()");
            apolloResponse = new ApolloResponse.Builder(operation, randomUUID, data).errors(list).extensions(map).build();
        } catch (Throwable th2) {
            Throwable th3 = th2;
            apolloResponse = null;
            th = th3;
        }
        try {
            jsonReader.close();
        } catch (Throwable th4) {
            if (th == null) {
                th = th4;
            } else {
                ExceptionsKt.addSuppressed(th, th4);
            }
        }
        if (th == null) {
            Intrinsics.checkNotNull(apolloResponse);
            return apolloResponse;
        }
        throw th;
    }

    @NotNull
    public final Error parseError(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "payload");
        return readError(new MapJsonReader(map, (List) null, 2, (DefaultConstructorMarker) null));
    }
}
