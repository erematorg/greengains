package com.reown.android.internal.common.signing.cacao;

import A.a;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/CacaoJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/signing/cacao/Cacao;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "headerAdapter", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Header;", "payloadAdapter", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "signatureAdapter", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Signature;", "toString", "", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CacaoJsonAdapter extends JsonAdapter<Cacao> {
    @NotNull
    private final JsonAdapter<Cacao.Header> headerAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<Cacao.Payload> payloadAdapter;
    @NotNull
    private final JsonAdapter<Cacao.Signature> signatureAdapter;

    public CacaoJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("h", TtmlNode.TAG_P, "s");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.headerAdapter = a.h(moshi, Cacao.Header.class, "header", "adapter(...)");
        this.payloadAdapter = a.h(moshi, Cacao.Payload.class, "payload", "adapter(...)");
        this.signatureAdapter = a.h(moshi, Cacao.Signature.class, "signature", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(27, "GeneratedJsonAdapter(Cacao)");
    }

    @NotNull
    public Cacao fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        Cacao.Header header = null;
        Cacao.Payload payload = null;
        Cacao.Signature signature = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                header = this.headerAdapter.fromJson(jsonReader);
                if (header == null) {
                    throw Util.unexpectedNull("header_", "h", jsonReader);
                }
            } else if (selectName == 1) {
                payload = this.payloadAdapter.fromJson(jsonReader);
                if (payload == null) {
                    throw Util.unexpectedNull("payload", TtmlNode.TAG_P, jsonReader);
                }
            } else if (selectName == 2 && (signature = this.signatureAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("signature", "s", jsonReader);
            }
        }
        jsonReader.endObject();
        if (header == null) {
            throw Util.missingProperty("header_", "h", jsonReader);
        } else if (payload == null) {
            throw Util.missingProperty("payload", TtmlNode.TAG_P, jsonReader);
        } else if (signature != null) {
            return new Cacao(header, payload, signature);
        } else {
            throw Util.missingProperty("signature", "s", jsonReader);
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Cacao cacao) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (cacao != null) {
            jsonWriter.beginObject();
            jsonWriter.name("h");
            this.headerAdapter.toJson(jsonWriter, cacao.getHeader());
            jsonWriter.name(TtmlNode.TAG_P);
            this.payloadAdapter.toJson(jsonWriter, cacao.getPayload());
            jsonWriter.name("s");
            this.signatureAdapter.toJson(jsonWriter, cacao.getSignature());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
