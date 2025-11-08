package com.reown.sign.engine.model.tvf;

import A.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.sign.engine.model.tvf.PolkadotSignTransaction;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.abi.datatypes.Address;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction_TransactionPayloadJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$TransactionPayload;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableStringAdapter", "nullableIntAdapter", "", "constructorRef", "Ljava/lang/reflect/Constructor;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PolkadotSignTransaction_TransactionPayloadJsonAdapter extends JsonAdapter<PolkadotSignTransaction.TransactionPayload> {
    @Nullable
    private volatile Constructor<PolkadotSignTransaction.TransactionPayload> constructorRef;
    @NotNull
    private final JsonAdapter<Integer> nullableIntAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public PolkadotSignTransaction_TransactionPayloadJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(FirebaseAnalytics.Param.METHOD, "specVersion", "transactionVersion", "genesisHash", "blockHash", "era", "nonce", "tip", "mode", "metadataHash", Address.TYPE_NAME, "version");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, FirebaseAnalytics.Param.METHOD, "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "specVersion", "adapter(...)");
        this.nullableIntAdapter = a.h(moshi, Integer.class, "version", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(64, "GeneratedJsonAdapter(PolkadotSignTransaction.TransactionPayload)");
    }

    @NotNull
    public PolkadotSignTransaction.TransactionPayload fromJson(@NotNull JsonReader jsonReader) {
        JsonReader jsonReader2 = jsonReader;
        Intrinsics.checkNotNullParameter(jsonReader2, "reader");
        jsonReader.beginObject();
        String str = null;
        int i3 = -1;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        Integer num = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader2.selectName(this.options)) {
                case -1:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
                case 0:
                    str = this.stringAdapter.fromJson(jsonReader2);
                    if (str != null) {
                        break;
                    } else {
                        throw Util.unexpectedNull(FirebaseAnalytics.Param.METHOD, FirebaseAnalytics.Param.METHOD, jsonReader2);
                    }
                case 1:
                    str2 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 2:
                    str3 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 3:
                    str4 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 4:
                    str5 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 5:
                    str6 = this.nullableStringAdapter.fromJson(jsonReader2);
                    i3 = -33;
                    break;
                case 6:
                    str7 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 7:
                    str8 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 8:
                    str9 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 9:
                    str10 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 10:
                    str11 = this.nullableStringAdapter.fromJson(jsonReader2);
                    break;
                case 11:
                    num = this.nullableIntAdapter.fromJson(jsonReader2);
                    break;
            }
        }
        jsonReader.endObject();
        if (i3 != -33) {
            Constructor<PolkadotSignTransaction.TransactionPayload> constructor = this.constructorRef;
            if (constructor == null) {
                constructor = PolkadotSignTransaction.TransactionPayload.class.getDeclaredConstructor(new Class[]{String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.TYPE, Util.DEFAULT_CONSTRUCTOR_MARKER});
                this.constructorRef = constructor;
                Intrinsics.checkNotNullExpressionValue(constructor, "also(...)");
            }
            Constructor<PolkadotSignTransaction.TransactionPayload> constructor2 = constructor;
            if (str != null) {
                PolkadotSignTransaction.TransactionPayload newInstance = constructor2.newInstance(new Object[]{str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, num, Integer.valueOf(i3), null});
                Intrinsics.checkNotNullExpressionValue(newInstance, "newInstance(...)");
                return newInstance;
            }
            throw Util.missingProperty(FirebaseAnalytics.Param.METHOD, FirebaseAnalytics.Param.METHOD, jsonReader);
        } else if (str != null) {
            return new PolkadotSignTransaction.TransactionPayload(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, num);
        } else {
            throw Util.missingProperty(FirebaseAnalytics.Param.METHOD, FirebaseAnalytics.Param.METHOD, jsonReader2);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable PolkadotSignTransaction.TransactionPayload transactionPayload) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (transactionPayload != null) {
            jsonWriter.beginObject();
            jsonWriter.name(FirebaseAnalytics.Param.METHOD);
            this.stringAdapter.toJson(jsonWriter, transactionPayload.getMethod());
            jsonWriter.name("specVersion");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getSpecVersion());
            jsonWriter.name("transactionVersion");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getTransactionVersion());
            jsonWriter.name("genesisHash");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getGenesisHash());
            jsonWriter.name("blockHash");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getBlockHash());
            jsonWriter.name("era");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getEra());
            jsonWriter.name("nonce");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getNonce());
            jsonWriter.name("tip");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getTip());
            jsonWriter.name("mode");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getMode());
            jsonWriter.name("metadataHash");
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getMetadataHash());
            jsonWriter.name(Address.TYPE_NAME);
            this.nullableStringAdapter.toJson(jsonWriter, transactionPayload.getAddress());
            jsonWriter.name("version");
            this.nullableIntAdapter.toJson(jsonWriter, transactionPayload.getVersion());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
