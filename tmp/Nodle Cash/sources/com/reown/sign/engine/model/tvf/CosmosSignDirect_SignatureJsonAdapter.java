package com.reown.sign.engine.model.tvf;

import A.a;
import com.reown.sign.engine.model.tvf.CosmosSignDirect;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/CosmosSignDirect_SignatureJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signature;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "pubKeyAdapter", "Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$PubKey;", "stringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CosmosSignDirect_SignatureJsonAdapter extends JsonAdapter<CosmosSignDirect.Signature> {
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<CosmosSignDirect.PubKey> pubKeyAdapter;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public CosmosSignDirect_SignatureJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("pub_key", "signature");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.pubKeyAdapter = a.h(moshi, CosmosSignDirect.PubKey.class, "pub_key", "adapter(...)");
        this.stringAdapter = a.h(moshi, String.class, "signature", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(48, "GeneratedJsonAdapter(CosmosSignDirect.Signature)");
    }

    @NotNull
    public CosmosSignDirect.Signature fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        CosmosSignDirect.PubKey pubKey = null;
        String str = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                pubKey = this.pubKeyAdapter.fromJson(jsonReader);
                if (pubKey == null) {
                    throw Util.unexpectedNull("pub_key", "pub_key", jsonReader);
                }
            } else if (selectName == 1 && (str = this.stringAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("signature", "signature", jsonReader);
            }
        }
        jsonReader.endObject();
        if (pubKey == null) {
            throw Util.missingProperty("pub_key", "pub_key", jsonReader);
        } else if (str != null) {
            return new CosmosSignDirect.Signature(pubKey, str);
        } else {
            throw Util.missingProperty("signature", "signature", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable CosmosSignDirect.Signature signature) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (signature != null) {
            jsonWriter.beginObject();
            jsonWriter.name("pub_key");
            this.pubKeyAdapter.toJson(jsonWriter, signature.getPub_key());
            jsonWriter.name("signature");
            this.stringAdapter.toJson(jsonWriter, signature.getSignature());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
