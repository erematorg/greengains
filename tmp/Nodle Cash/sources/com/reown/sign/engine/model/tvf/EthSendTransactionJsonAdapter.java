package com.reown.sign.engine.model.tvf;

import A.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\f\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/EthSendTransactionJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/EthSendTransaction;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableStringAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EthSendTransactionJsonAdapter extends JsonAdapter<EthSendTransaction> {
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public EthSendTransactionJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("from", TypedValues.TransitionType.S_TO, "data", "gasLimit", "gasPrice", "value", "nonce");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, "from", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "data", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(40, "GeneratedJsonAdapter(EthSendTransaction)");
    }

    @NotNull
    public EthSendTransaction fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.stringAdapter.fromJson(jsonReader);
                    if (str != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("from", "from", jsonReader);
                    }
                case 1:
                    str2 = this.stringAdapter.fromJson(jsonReader);
                    if (str2 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull(TypedValues.TransitionType.S_TO, TypedValues.TransitionType.S_TO, jsonReader);
                    }
                case 2:
                    str3 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 3:
                    str4 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 4:
                    str5 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 5:
                    str6 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 6:
                    str7 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty("from", "from", jsonReader);
        } else if (str2 != null) {
            return new EthSendTransaction(str, str2, str3, str4, str5, str6, str7);
        } else {
            throw Util.missingProperty(TypedValues.TransitionType.S_TO, TypedValues.TransitionType.S_TO, jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable EthSendTransaction ethSendTransaction) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (ethSendTransaction != null) {
            jsonWriter.beginObject();
            jsonWriter.name("from");
            this.stringAdapter.toJson(jsonWriter, ethSendTransaction.getFrom());
            jsonWriter.name(TypedValues.TransitionType.S_TO);
            this.stringAdapter.toJson(jsonWriter, ethSendTransaction.getTo());
            jsonWriter.name("data");
            this.nullableStringAdapter.toJson(jsonWriter, ethSendTransaction.getData());
            jsonWriter.name("gasLimit");
            this.nullableStringAdapter.toJson(jsonWriter, ethSendTransaction.getGasLimit());
            jsonWriter.name("gasPrice");
            this.nullableStringAdapter.toJson(jsonWriter, ethSendTransaction.getGasPrice());
            jsonWriter.name("value");
            this.nullableStringAdapter.toJson(jsonWriter, ethSendTransaction.getValue());
            jsonWriter.name("nonce");
            this.nullableStringAdapter.toJson(jsonWriter, ethSendTransaction.getNonce());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
