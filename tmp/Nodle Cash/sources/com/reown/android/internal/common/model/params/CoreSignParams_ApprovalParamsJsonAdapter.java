package com.reown.android.internal.common.model.params;

import A.a;
import com.reown.android.internal.common.model.RelayProtocolOptions;
import com.reown.android.internal.common.model.params.CoreSignParams;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreSignParams_ApprovalParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/model/params/CoreSignParams$ApprovalParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "relayProtocolOptionsAdapter", "Lcom/reown/android/internal/common/model/RelayProtocolOptions;", "stringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreSignParams_ApprovalParamsJsonAdapter extends JsonAdapter<CoreSignParams.ApprovalParams> {
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<RelayProtocolOptions> relayProtocolOptionsAdapter;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public CoreSignParams_ApprovalParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("relay", "responderPublicKey");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.relayProtocolOptionsAdapter = a.h(moshi, RelayProtocolOptions.class, "relay", "adapter(...)");
        this.stringAdapter = a.h(moshi, String.class, "responderPublicKey", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(51, "GeneratedJsonAdapter(CoreSignParams.ApprovalParams)");
    }

    @NotNull
    public CoreSignParams.ApprovalParams fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        RelayProtocolOptions relayProtocolOptions = null;
        String str = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                relayProtocolOptions = this.relayProtocolOptionsAdapter.fromJson(jsonReader);
                if (relayProtocolOptions == null) {
                    throw Util.unexpectedNull("relay", "relay", jsonReader);
                }
            } else if (selectName == 1 && (str = this.stringAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("responderPublicKey", "responderPublicKey", jsonReader);
            }
        }
        jsonReader.endObject();
        if (relayProtocolOptions == null) {
            throw Util.missingProperty("relay", "relay", jsonReader);
        } else if (str != null) {
            return new CoreSignParams.ApprovalParams(relayProtocolOptions, str);
        } else {
            throw Util.missingProperty("responderPublicKey", "responderPublicKey", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable CoreSignParams.ApprovalParams approvalParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (approvalParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name("relay");
            this.relayProtocolOptionsAdapter.toJson(jsonWriter, approvalParams.getRelay());
            jsonWriter.name("responderPublicKey");
            this.stringAdapter.toJson(jsonWriter, approvalParams.getResponderPublicKey());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
