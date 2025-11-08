package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Executable;
import com.apollographql.apollo3.api.Fragment.Data;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u000eJ\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/api/Fragment;", "D", "Lcom/apollographql/apollo3/api/Fragment$Data;", "Lcom/apollographql/apollo3/api/Executable;", "adapter", "Lcom/apollographql/apollo3/api/Adapter;", "rootField", "Lcom/apollographql/apollo3/api/CompiledField;", "serializeVariables", "", "writer", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "Data", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface Fragment<D extends Data> extends Executable<D> {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/apollographql/apollo3/api/Fragment$Data;", "Lcom/apollographql/apollo3/api/Executable$Data;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public interface Data extends Executable.Data {
    }

    @NotNull
    Adapter<D> adapter();

    @NotNull
    CompiledField rootField();

    void serializeVariables(@NotNull JsonWriter jsonWriter, @NotNull CustomScalarAdapters customScalarAdapters) throws IOException;
}
