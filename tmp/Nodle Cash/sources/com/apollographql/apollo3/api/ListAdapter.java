package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0013\u0012\u000f\u0012\r\u0012\t\u0012\u0007H\u0001¢\u0006\u0002\b\u00040\u00030\u0002B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/api/ListAdapter;", "T", "Lcom/apollographql/apollo3/api/Adapter;", "", "Lkotlin/jvm/JvmSuppressWildcards;", "wrappedAdapter", "(Lcom/apollographql/apollo3/api/Adapter;)V", "fromJson", "reader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "toJson", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "value", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nAdapters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Adapters.kt\ncom/apollographql/apollo3/api/ListAdapter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,345:1\n1855#2,2:346\n*S KotlinDebug\n*F\n+ 1 Adapters.kt\ncom/apollographql/apollo3/api/ListAdapter\n*L\n38#1:346,2\n*E\n"})
public final class ListAdapter<T> implements Adapter<List<T>> {
    @NotNull
    private final Adapter<T> wrappedAdapter;

    public ListAdapter(@NotNull Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "wrappedAdapter");
        this.wrappedAdapter = adapter;
    }

    @NotNull
    public List<T> fromJson(@NotNull JsonReader jsonReader, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        jsonReader.beginArray();
        ArrayList arrayList = new ArrayList();
        while (jsonReader.hasNext()) {
            arrayList.add(this.wrappedAdapter.fromJson(jsonReader, customScalarAdapters));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @NotNull CustomScalarAdapters customScalarAdapters, @NotNull List<? extends T> list) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Intrinsics.checkNotNullParameter(list, "value");
        jsonWriter.beginArray();
        for (Object json : list) {
            this.wrappedAdapter.toJson(jsonWriter, customScalarAdapters, json);
        }
        jsonWriter.endArray();
    }
}
