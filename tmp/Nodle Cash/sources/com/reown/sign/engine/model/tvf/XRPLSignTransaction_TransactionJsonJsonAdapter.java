package com.reown.sign.engine.model.tvf;

import A.a;
import com.reown.sign.engine.model.tvf.XRPLSignTransaction;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0012\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction_TransactionJsonJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TransactionJson;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableStringAdapter", "", "nullableLongAdapter", "", "nullableIntAdapter", "", "nullableTakerPaysAdapter", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TakerPays;", "stringAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class XRPLSignTransaction_TransactionJsonJsonAdapter extends JsonAdapter<XRPLSignTransaction.TransactionJson> {
    @NotNull
    private final JsonAdapter<Integer> nullableIntAdapter;
    @NotNull
    private final JsonAdapter<Long> nullableLongAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonAdapter<XRPLSignTransaction.TakerPays> nullableTakerPaysAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public XRPLSignTransaction_TransactionJsonJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("Account", "Expiration", "Fee", "Flags", "OfferSequence", "Sequence", "LastLedgerSequence", "SigningPubKey", "TakerGets", "TakerPays", "TransactionType", "TxnSignature", "hash");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.nullableStringAdapter = a.h(moshi, cls, "Account", "adapter(...)");
        this.nullableLongAdapter = a.h(moshi, Long.class, "Expiration", "adapter(...)");
        this.nullableIntAdapter = a.h(moshi, Integer.class, "Flags", "adapter(...)");
        this.nullableTakerPaysAdapter = a.h(moshi, XRPLSignTransaction.TakerPays.class, "TakerPays", "adapter(...)");
        this.stringAdapter = a.h(moshi, cls, "hash", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(57, "GeneratedJsonAdapter(XRPLSignTransaction.TransactionJson)");
    }

    @NotNull
    public XRPLSignTransaction.TransactionJson fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        String str = null;
        Long l2 = null;
        String str2 = null;
        Integer num = null;
        Long l3 = null;
        Long l4 = null;
        Long l5 = null;
        String str3 = null;
        String str4 = null;
        XRPLSignTransaction.TakerPays takerPays = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader2.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 1:
                    l2 = this.nullableLongAdapter.fromJson(jsonReader2);
                    break;
                case 2:
                    str2 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 3:
                    num = this.nullableIntAdapter.fromJson(jsonReader2);
                    break;
                case 4:
                    l3 = this.nullableLongAdapter.fromJson(jsonReader2);
                    break;
                case 5:
                    l4 = this.nullableLongAdapter.fromJson(jsonReader2);
                    break;
                case 6:
                    l5 = this.nullableLongAdapter.fromJson(jsonReader2);
                    break;
                case 7:
                    str3 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 8:
                    str4 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 9:
                    takerPays = this.nullableTakerPaysAdapter.fromJson(jsonReader2);
                    break;
                case 10:
                    str5 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 11:
                    str6 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 12:
                    str7 = this.stringAdapter.fromJson(jsonReader2);
                    if (str7 != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull("hash", "hash", jsonReader2);
                    }
            }
        }
        jsonReader.endObject();
        if (str7 != null) {
            return new XRPLSignTransaction.TransactionJson(str, l2, str2, num, l3, l4, l5, str3, str4, takerPays, str5, str6, str7);
        }
        throw Util.missingProperty("hash", "hash", jsonReader2);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable XRPLSignTransaction.TransactionJson transactionJson) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (transactionJson != null) {
            jsonWriter.beginObject();
            jsonWriter.name("Account");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getAccount());
            jsonWriter.name("Expiration");
            this.nullableLongAdapter.toJson(jsonWriter, transactionJson.getExpiration());
            jsonWriter.name("Fee");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getFee());
            jsonWriter.name("Flags");
            this.nullableIntAdapter.toJson(jsonWriter, transactionJson.getFlags());
            jsonWriter.name("OfferSequence");
            this.nullableLongAdapter.toJson(jsonWriter, transactionJson.getOfferSequence());
            jsonWriter.name("Sequence");
            this.nullableLongAdapter.toJson(jsonWriter, transactionJson.getSequence());
            jsonWriter.name("LastLedgerSequence");
            this.nullableLongAdapter.toJson(jsonWriter, transactionJson.getLastLedgerSequence());
            jsonWriter.name("SigningPubKey");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getSigningPubKey());
            jsonWriter.name("TakerGets");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getTakerGets());
            jsonWriter.name("TakerPays");
            this.nullableTakerPaysAdapter.toJson(jsonWriter, transactionJson.getTakerPays());
            jsonWriter.name("TransactionType");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getTransactionType());
            jsonWriter.name("TxnSignature");
            this.nullableStringAdapter.toJson(jsonWriter, transactionJson.getTxnSignature());
            jsonWriter.name("hash");
            this.stringAdapter.toJson(jsonWriter, transactionJson.getHash());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
