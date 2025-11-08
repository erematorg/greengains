package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000f\u0012\u000b\u0012\t\u0018\u0001H\u0001¢\u0006\u0002\b\u00040\u0003B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\u0004\u0018\u00018\u00002\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fJ'\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/apollographql/apollo3/api/NullableAdapter;", "T", "", "Lcom/apollographql/apollo3/api/Adapter;", "Lkotlin/jvm/JvmSuppressWildcards;", "wrappedAdapter", "(Lcom/apollographql/apollo3/api/Adapter;)V", "fromJson", "reader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "(Lcom/apollographql/apollo3/api/json/JsonReader;Lcom/apollographql/apollo3/api/CustomScalarAdapters;)Ljava/lang/Object;", "toJson", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "value", "(Lcom/apollographql/apollo3/api/json/JsonWriter;Lcom/apollographql/apollo3/api/CustomScalarAdapters;Ljava/lang/Object;)V", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class NullableAdapter<T> implements Adapter<T> {
    @NotNull
    private final Adapter<T> wrappedAdapter;

    public NullableAdapter(@NotNull Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "wrappedAdapter");
        this.wrappedAdapter = adapter;
        if (adapter instanceof NullableAdapter) {
            throw new IllegalStateException("The adapter is already nullable");
        }
    }

    @Nullable
    public T fromJson(@NotNull JsonReader jsonReader, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        if (jsonReader.peek() != JsonReader.Token.NULL) {
            return this.wrappedAdapter.fromJson(jsonReader, customScalarAdapters);
        }
        jsonReader.skipValue();
        return null;
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @NotNull CustomScalarAdapters customScalarAdapters, @Nullable T t2) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        if (t2 == null) {
            jsonWriter.nullValue();
        } else {
            this.wrappedAdapter.toJson(jsonWriter, customScalarAdapters, t2);
        }
    }
}
