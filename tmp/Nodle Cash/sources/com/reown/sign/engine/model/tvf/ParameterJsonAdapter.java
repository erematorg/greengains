package com.reown.sign.engine.model.tvf;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/ParameterJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/Parameter;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableStringAdapter", "", "nullableContractValueAdapter", "Lcom/reown/sign/engine/model/tvf/ContractValue;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ParameterJsonAdapter extends JsonAdapter<Parameter> {
    @NotNull
    private final JsonAdapter<ContractValue> nullableContractValueAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;

    public ParameterJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("type_url", "value");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.nullableStringAdapter = a.h(moshi, String.class, "type_url", "adapter(...)");
        this.nullableContractValueAdapter = a.h(moshi, ContractValue.class, "value", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(31, "GeneratedJsonAdapter(Parameter)");
    }

    @NotNull
    public Parameter fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        ContractValue contractValue = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.nullableStringAdapter.fromJson(jsonReader);
            } else if (selectName == 1) {
                contractValue = this.nullableContractValueAdapter.fromJson(jsonReader);
            }
        }
        jsonReader.endObject();
        return new Parameter(str, contractValue);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Parameter parameter) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (parameter != null) {
            jsonWriter.beginObject();
            jsonWriter.name("type_url");
            this.nullableStringAdapter.toJson(jsonWriter, parameter.getType_url());
            jsonWriter.name("value");
            this.nullableContractValueAdapter.toJson(jsonWriter, parameter.getValue());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
