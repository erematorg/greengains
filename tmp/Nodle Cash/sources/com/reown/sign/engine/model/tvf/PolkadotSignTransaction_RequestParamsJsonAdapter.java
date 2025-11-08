package com.reown.sign.engine.model.tvf;

import A.a;
import com.reown.sign.engine.model.tvf.PolkadotSignTransaction;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.abi.datatypes.Address;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction_RequestParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$RequestParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableStringAdapter", "", "transactionPayloadAdapter", "Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$TransactionPayload;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PolkadotSignTransaction_RequestParamsJsonAdapter extends JsonAdapter<PolkadotSignTransaction.RequestParams> {
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<PolkadotSignTransaction.TransactionPayload> transactionPayloadAdapter;

    public PolkadotSignTransaction_RequestParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(Address.TYPE_NAME, "transactionPayload");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.nullableStringAdapter = a.h(moshi, String.class, Address.TYPE_NAME, "adapter(...)");
        this.transactionPayloadAdapter = a.h(moshi, PolkadotSignTransaction.TransactionPayload.class, "transactionPayload", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(59, "GeneratedJsonAdapter(PolkadotSignTransaction.RequestParams)");
    }

    @NotNull
    public PolkadotSignTransaction.RequestParams fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        PolkadotSignTransaction.TransactionPayload transactionPayload = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.nullableStringAdapter.fromJson(jsonReader);
            } else if (selectName == 1 && (transactionPayload = this.transactionPayloadAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("transactionPayload", "transactionPayload", jsonReader);
            }
        }
        jsonReader.endObject();
        if (transactionPayload != null) {
            return new PolkadotSignTransaction.RequestParams(str, transactionPayload);
        }
        throw Util.missingProperty("transactionPayload", "transactionPayload", jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable PolkadotSignTransaction.RequestParams requestParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (requestParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name(Address.TYPE_NAME);
            this.nullableStringAdapter.toJson(jsonWriter, requestParams.getAddress());
            jsonWriter.name("transactionPayload");
            this.transactionPayloadAdapter.toJson(jsonWriter, requestParams.getTransactionPayload());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
