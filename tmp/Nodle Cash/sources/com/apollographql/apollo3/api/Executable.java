package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Executable.Data;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0002\u000e\u000fJ\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/api/Executable;", "D", "Lcom/apollographql/apollo3/api/Executable$Data;", "", "adapter", "Lcom/apollographql/apollo3/api/Adapter;", "rootField", "Lcom/apollographql/apollo3/api/CompiledField;", "serializeVariables", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "Data", "Variables", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface Executable<D extends Data> {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/apollographql/apollo3/api/Executable$Data;", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Data {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u0005R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/api/Executable$Variables;", "", "valueMap", "", "", "(Ljava/util/Map;)V", "getValueMap", "()Ljava/util/Map;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Variables {
        @NotNull
        private final Map<String, Object> valueMap;

        public Variables(@NotNull Map<String, ? extends Object> map) {
            Intrinsics.checkNotNullParameter(map, "valueMap");
            this.valueMap = map;
        }

        @NotNull
        public final Map<String, Object> getValueMap() {
            return this.valueMap;
        }
    }

    @NotNull
    Adapter<D> adapter();

    @NotNull
    CompiledField rootField();

    void serializeVariables(@NotNull JsonWriter jsonWriter, @NotNull CustomScalarAdapters customScalarAdapters) throws IOException;
}
