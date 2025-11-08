package com.reown.sign.engine.model.tvf;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/engine/model/tvf/RawDataJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/RawData;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableLongAdapter", "", "nullableListOfContractAdapter", "", "Lcom/reown/sign/engine/model/tvf/Contract;", "nullableStringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RawDataJsonAdapter extends JsonAdapter<RawData> {
    @NotNull
    private final JsonAdapter<List<Contract>> nullableListOfContractAdapter;
    @NotNull
    private final JsonAdapter<Long> nullableLongAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;

    public RawDataJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("expiration", "contract", "ref_block_hash", "fee_limit", "timestamp", "ref_block_bytes");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.nullableLongAdapter = a.h(moshi, Long.class, "expiration", "adapter(...)");
        this.nullableListOfContractAdapter = a.i(moshi, Types.newParameterizedType(List.class, Contract.class), "contract", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, String.class, "ref_block_hash", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(29, "GeneratedJsonAdapter(RawData)");
    }

    @NotNull
    public RawData fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Long l2 = null;
        List list = null;
        String str = null;
        Long l3 = null;
        Long l4 = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    l2 = this.nullableLongAdapter.fromJson(jsonReader);
                    break;
                case 1:
                    list = this.nullableListOfContractAdapter.fromJson(jsonReader);
                    break;
                case 2:
                    str = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 3:
                    l3 = this.nullableLongAdapter.fromJson(jsonReader);
                    break;
                case 4:
                    l4 = this.nullableLongAdapter.fromJson(jsonReader);
                    break;
                case 5:
                    str2 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
            }
        }
        jsonReader.endObject();
        return new RawData(l2, list, str, l3, l4, str2);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable RawData rawData) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (rawData != null) {
            jsonWriter.beginObject();
            jsonWriter.name("expiration");
            this.nullableLongAdapter.toJson(jsonWriter, rawData.getExpiration());
            jsonWriter.name("contract");
            this.nullableListOfContractAdapter.toJson(jsonWriter, rawData.getContract());
            jsonWriter.name("ref_block_hash");
            this.nullableStringAdapter.toJson(jsonWriter, rawData.getRef_block_hash());
            jsonWriter.name("fee_limit");
            this.nullableLongAdapter.toJson(jsonWriter, rawData.getFee_limit());
            jsonWriter.name("timestamp");
            this.nullableLongAdapter.toJson(jsonWriter, rawData.getTimestamp());
            jsonWriter.name("ref_block_bytes");
            this.nullableStringAdapter.toJson(jsonWriter, rawData.getRef_block_bytes());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
