package com.reown.sign.engine.model.tvf;

import A.a;
import com.reown.sign.engine.model.tvf.XRPLSignTransactionFor;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor_SignerWrapperJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$SignerWrapper;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "nullableSignerAdapter", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$Signer;", "toString", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class XRPLSignTransactionFor_SignerWrapperJsonAdapter extends JsonAdapter<XRPLSignTransactionFor.SignerWrapper> {
    @NotNull
    private final JsonAdapter<XRPLSignTransactionFor.Signer> nullableSignerAdapter;
    @NotNull
    private final JsonReader.Options options;

    public XRPLSignTransactionFor_SignerWrapperJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("Signer");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.nullableSignerAdapter = a.h(moshi, XRPLSignTransactionFor.Signer.class, "Signer", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(58, "GeneratedJsonAdapter(XRPLSignTransactionFor.SignerWrapper)");
    }

    @NotNull
    public XRPLSignTransactionFor.SignerWrapper fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        XRPLSignTransactionFor.Signer signer = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                signer = this.nullableSignerAdapter.fromJson(jsonReader);
            }
        }
        jsonReader.endObject();
        return new XRPLSignTransactionFor.SignerWrapper(signer);
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable XRPLSignTransactionFor.SignerWrapper signerWrapper) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (signerWrapper != null) {
            jsonWriter.beginObject();
            jsonWriter.name("Signer");
            this.nullableSignerAdapter.toJson(jsonWriter, signerWrapper.getSigner());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
