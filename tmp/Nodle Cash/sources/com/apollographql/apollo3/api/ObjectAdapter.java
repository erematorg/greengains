package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.apollographql.apollo3.api.json.JsonWriters;
import com.apollographql.apollo3.api.json.MapJsonReader;
import com.apollographql.apollo3.api.json.MapJsonWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\r\u0012\t\u0012\u0007H\u0001¢\u0006\u0002\b\u00030\u0002B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001d\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ%\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/ObjectAdapter;", "T", "Lcom/apollographql/apollo3/api/Adapter;", "Lkotlin/jvm/JvmSuppressWildcards;", "wrappedAdapter", "buffered", "", "(Lcom/apollographql/apollo3/api/Adapter;Z)V", "fromJson", "reader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "(Lcom/apollographql/apollo3/api/json/JsonReader;Lcom/apollographql/apollo3/api/CustomScalarAdapters;)Ljava/lang/Object;", "toJson", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "value", "(Lcom/apollographql/apollo3/api/json/JsonWriter;Lcom/apollographql/apollo3/api/CustomScalarAdapters;Ljava/lang/Object;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ObjectAdapter<T> implements Adapter<T> {
    private final boolean buffered;
    @NotNull
    private final Adapter<T> wrappedAdapter;

    public ObjectAdapter(@NotNull Adapter<T> adapter, boolean z2) {
        Intrinsics.checkNotNullParameter(adapter, "wrappedAdapter");
        this.wrappedAdapter = adapter;
        this.buffered = z2;
    }

    public T fromJson(@NotNull JsonReader jsonReader, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        if (this.buffered) {
            jsonReader = MapJsonReader.Companion.buffer(jsonReader);
        }
        jsonReader.beginObject();
        T fromJson = this.wrappedAdapter.fromJson(jsonReader, customScalarAdapters);
        jsonReader.endObject();
        return fromJson;
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @NotNull CustomScalarAdapters customScalarAdapters, T t2) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        if (!this.buffered || (jsonWriter instanceof MapJsonWriter)) {
            jsonWriter.beginObject();
            this.wrappedAdapter.toJson(jsonWriter, customScalarAdapters, t2);
            jsonWriter.endObject();
            return;
        }
        MapJsonWriter mapJsonWriter = new MapJsonWriter();
        mapJsonWriter.beginObject();
        this.wrappedAdapter.toJson(mapJsonWriter, customScalarAdapters, t2);
        mapJsonWriter.endObject();
        Object root = mapJsonWriter.root();
        Intrinsics.checkNotNull(root);
        JsonWriters.writeAny(jsonWriter, root);
    }
}
