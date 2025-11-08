package com.reown.sign.engine.model.tvf;

import A.a;
import com.reown.sign.engine.model.tvf.XRPLSignTransactionFor;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0011\u001a\u00020\nH\u0016J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor_TransactionJsonJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$TransactionJson;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableStringAdapter", "", "nullableLongAdapter", "", "nullableListOfSignerWrapperAdapter", "", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$SignerWrapper;", "stringAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class XRPLSignTransactionFor_TransactionJsonJsonAdapter extends JsonAdapter<XRPLSignTransactionFor.TransactionJson> {
    @NotNull
    private final JsonAdapter<List<XRPLSignTransactionFor.SignerWrapper>> nullableListOfSignerWrapperAdapter;
    @NotNull
    private final JsonAdapter<Long> nullableLongAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public XRPLSignTransactionFor_TransactionJsonJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("Account", "TransactionType", "Amount", "Destination", "Fee", "Flags", "LastLedgerSequence", "Sequence", "Signers", "SigningPubKey", "hash");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.nullableStringAdapter = a.h(moshi, cls, "Account", "adapter(...)");
        this.nullableLongAdapter = a.h(moshi, Long.class, "Flags", "adapter(...)");
        this.nullableListOfSignerWrapperAdapter = a.i(moshi, Types.newParameterizedType(List.class, XRPLSignTransactionFor.SignerWrapper.class), "Signers", "adapter(...)");
        this.stringAdapter = a.h(moshi, cls, "hash", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(60, "GeneratedJsonAdapter(XRPLSignTransactionFor.TransactionJson)");
    }

    @NotNull
    public XRPLSignTransactionFor.TransactionJson fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        Long l2 = null;
        Long l3 = null;
        Long l4 = null;
        List list = null;
        String str6 = null;
        String str7 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 1:
                    str2 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
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
                    l2 = this.nullableLongAdapter.fromJson(jsonReader);
                    break;
                case 6:
                    l3 = this.nullableLongAdapter.fromJson(jsonReader);
                    break;
                case 7:
                    l4 = this.nullableLongAdapter.fromJson(jsonReader);
                    break;
                case 8:
                    list = this.nullableListOfSignerWrapperAdapter.fromJson(jsonReader);
                    break;
                case 9:
                    str6 = this.nullableStringAdapter.fromJson(jsonReader);
                    break;
                case 10:
                    str7 = this.stringAdapter.fromJson(jsonReader);
                    if (str7 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("hash", "hash", jsonReader);
                    }
            }
        }
        jsonReader.endObject();
        if (str7 != null) {
            return new XRPLSignTransactionFor.TransactionJson(str, str2, str3, str4, str5, l2, l3, l4, list, str6, str7);
        }
        throw Util.missingProperty("hash", "hash", jsonReader);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable XRPLSignTransactionFor.TransactionJson transactionJson) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (transactionJson != null) {
            jsonWriter.beginObject();
            jsonWriter.name("Account");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getAccount());
            jsonWriter.name("TransactionType");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getTransactionType());
            jsonWriter.name("Amount");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getAmount());
            jsonWriter.name("Destination");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getDestination());
            jsonWriter.name("Fee");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getFee());
            jsonWriter.name("Flags");
            this.nullableLongAdapter.toJson(jsonWriter, transactionJson.getFlags());
            jsonWriter.name("LastLedgerSequence");
            this.nullableLongAdapter.toJson(jsonWriter, transactionJson.getLastLedgerSequence());
            jsonWriter.name("Sequence");
            this.nullableLongAdapter.toJson(jsonWriter, transactionJson.getSequence());
            jsonWriter.name("Signers");
            this.nullableListOfSignerWrapperAdapter.toJson(jsonWriter, transactionJson.getSigners());
            jsonWriter.name("SigningPubKey");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getSigningPubKey());
            jsonWriter.name("hash");
            this.stringAdapter.toJson(jsonWriter, transactionJson.getHash());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
