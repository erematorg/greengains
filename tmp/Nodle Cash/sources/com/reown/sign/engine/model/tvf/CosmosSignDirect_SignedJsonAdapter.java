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

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/engine/model/tvf/CosmosSignDirect_SignedJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signed;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CosmosSignDirect_SignedJsonAdapter extends JsonAdapter<CosmosSignDirect.Signed> {
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public CosmosSignDirect_SignedJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("chainId", "accountNumber", "authInfoBytes", "bodyBytes");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, "chainId", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(45, "GeneratedJsonAdapter(CosmosSignDirect.Signed)");
    }

    @NotNull
    public CosmosSignDirect.Signed fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull("chainId", "chainId", jsonReader);
                }
            } else if (selectName == 1) {
                str2 = this.stringAdapter.fromJson(jsonReader);
                if (str2 == null) {
                    throw Util.unexpectedNull("accountNumber", "accountNumber", jsonReader);
                }
            } else if (selectName == 2) {
                str3 = this.stringAdapter.fromJson(jsonReader);
                if (str3 == null) {
                    throw Util.unexpectedNull("authInfoBytes", "authInfoBytes", jsonReader);
                }
            } else if (selectName == 3 && (str4 = this.stringAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("bodyBytes", "bodyBytes", jsonReader);
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty("chainId", "chainId", jsonReader);
        } else if (str2 == null) {
            throw Util.missingProperty("accountNumber", "accountNumber", jsonReader);
        } else if (str3 == null) {
            throw Util.missingProperty("authInfoBytes", "authInfoBytes", jsonReader);
        } else if (str4 != null) {
            return new CosmosSignDirect.Signed(str, str2, str3, str4);
        } else {
            throw Util.missingProperty("bodyBytes", "bodyBytes", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable CosmosSignDirect.Signed signed) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (signed != null) {
            jsonWriter.beginObject();
            jsonWriter.name("chainId");
            this.stringAdapter.toJson(jsonWriter, signed.getChainId());
            jsonWriter.name("accountNumber");
            this.stringAdapter.toJson(jsonWriter, signed.getAccountNumber());
            jsonWriter.name("authInfoBytes");
            this.stringAdapter.toJson(jsonWriter, signed.getAuthInfoBytes());
            jsonWriter.name("bodyBytes");
            this.stringAdapter.toJson(jsonWriter, signed.getBodyBytes());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
