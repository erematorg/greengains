package com.reown.android.internal.common.signing.cacao;

import A.a;
import com.reown.android.internal.common.signing.cacao.Cacao;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\r0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/reown/android/internal/common/signing/cacao/Cacao_PayloadJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/signing/cacao/Cacao$Payload;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableStringAdapter", "nullableListOfStringAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Cacao_PayloadJsonAdapter extends JsonAdapter<Cacao.Payload> {
    @NotNull
    private final JsonAdapter<List<String>> nullableListOfStringAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public Cacao_PayloadJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("iss", "domain", "aud", "version", "nonce", "iat", "nbf", "exp", "statement", "requestId", "resources");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, "iss", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "nbf", "adapter(...)");
        this.nullableListOfStringAdapter = a.i(moshi, Types.newParameterizedType(List.class, cls), "resources", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(35, "GeneratedJsonAdapter(Cacao.Payload)");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x007f, code lost:
        r14 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0045, code lost:
        r13 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0047, code lost:
        r12 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0049, code lost:
        r11 = r19;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.reown.android.internal.common.signing.cacao.Cacao.Payload fromJson(@org.jetbrains.annotations.NotNull com.squareup.moshi.JsonReader r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            java.lang.String r2 = "reader"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r21.beginObject()
            r2 = 0
            r4 = r2
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
        L_0x0018:
            boolean r2 = r21.hasNext()
            java.lang.String r3 = "iss"
            java.lang.String r15 = "domain"
            r16 = r14
            java.lang.String r14 = "aud"
            r17 = r13
            java.lang.String r13 = "version"
            r18 = r12
            java.lang.String r12 = "nonce"
            r19 = r11
            java.lang.String r11 = "iat"
            if (r2 == 0) goto L_0x00ef
            com.squareup.moshi.JsonReader$Options r2 = r0.options
            int r2 = r1.selectName(r2)
            switch(r2) {
                case -1: goto L_0x00e8;
                case 0: goto L_0x00d7;
                case 1: goto L_0x00c6;
                case 2: goto L_0x00b5;
                case 3: goto L_0x00a4;
                case 4: goto L_0x0093;
                case 5: goto L_0x0082;
                case 6: goto L_0x0076;
                case 7: goto L_0x0066;
                case 8: goto L_0x0058;
                case 9: goto L_0x004c;
                case 10: goto L_0x003c;
                default: goto L_0x003b;
            }
        L_0x003b:
            goto L_0x007f
        L_0x003c:
            com.squareup.moshi.JsonAdapter<java.util.List<java.lang.String>> r2 = r0.nullableListOfStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r14 = r2
            java.util.List r14 = (java.util.List) r14
        L_0x0045:
            r13 = r17
        L_0x0047:
            r12 = r18
        L_0x0049:
            r11 = r19
            goto L_0x0018
        L_0x004c:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r13 = r2
            java.lang.String r13 = (java.lang.String) r13
            r14 = r16
            goto L_0x0047
        L_0x0058:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r12 = r2
            java.lang.String r12 = (java.lang.String) r12
            r14 = r16
            r13 = r17
            goto L_0x0049
        L_0x0066:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            r14 = r16
            r13 = r17
            r12 = r18
            goto L_0x0018
        L_0x0076:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r10 = r2
            java.lang.String r10 = (java.lang.String) r10
        L_0x007f:
            r14 = r16
            goto L_0x0045
        L_0x0082:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9
            if (r9 == 0) goto L_0x008e
        L_0x008d:
            goto L_0x007f
        L_0x008e:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r11, r11, r1)
            throw r0
        L_0x0093:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x009f
            goto L_0x008d
        L_0x009f:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r12, r12, r1)
            throw r0
        L_0x00a4:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r7 = r2
            java.lang.String r7 = (java.lang.String) r7
            if (r7 == 0) goto L_0x00b0
            goto L_0x008d
        L_0x00b0:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r13, r13, r1)
            throw r0
        L_0x00b5:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x00c1
            goto L_0x008d
        L_0x00c1:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r14, r14, r1)
            throw r0
        L_0x00c6:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r5 = r2
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L_0x00d2
            goto L_0x008d
        L_0x00d2:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r15, r15, r1)
            throw r0
        L_0x00d7:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r4 = r2
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x00e3
            goto L_0x008d
        L_0x00e3:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r3, r3, r1)
            throw r0
        L_0x00e8:
            r21.skipName()
            r21.skipValue()
            goto L_0x007f
        L_0x00ef:
            r21.endObject()
            com.reown.android.internal.common.signing.cacao.Cacao$Payload r0 = new com.reown.android.internal.common.signing.cacao.Cacao$Payload
            if (r4 == 0) goto L_0x0126
            if (r5 == 0) goto L_0x0121
            if (r6 == 0) goto L_0x011c
            if (r7 == 0) goto L_0x0117
            if (r8 == 0) goto L_0x0112
            if (r9 == 0) goto L_0x010d
            r3 = r0
            r11 = r19
            r12 = r18
            r13 = r17
            r14 = r16
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        L_0x010d:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r11, r11, r1)
            throw r0
        L_0x0112:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r12, r12, r1)
            throw r0
        L_0x0117:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r13, r13, r1)
            throw r0
        L_0x011c:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r14, r14, r1)
            throw r0
        L_0x0121:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r15, r15, r1)
            throw r0
        L_0x0126:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r3, r3, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.signing.cacao.Cacao_PayloadJsonAdapter.fromJson(com.squareup.moshi.JsonReader):com.reown.android.internal.common.signing.cacao.Cacao$Payload");
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable Cacao.Payload payload) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (payload != null) {
            jsonWriter.beginObject();
            jsonWriter.name("iss");
            this.stringAdapter.toJson(jsonWriter, payload.getIss());
            jsonWriter.name("domain");
            this.stringAdapter.toJson(jsonWriter, payload.getDomain());
            jsonWriter.name("aud");
            this.stringAdapter.toJson(jsonWriter, payload.getAud());
            jsonWriter.name("version");
            this.stringAdapter.toJson(jsonWriter, payload.getVersion());
            jsonWriter.name("nonce");
            this.stringAdapter.toJson(jsonWriter, payload.getNonce());
            jsonWriter.name("iat");
            this.stringAdapter.toJson(jsonWriter, payload.getIat());
            jsonWriter.name("nbf");
            this.nullableStringAdapter.toJson(jsonWriter, payload.getNbf());
            jsonWriter.name("exp");
            this.nullableStringAdapter.toJson(jsonWriter, payload.getExp());
            jsonWriter.name("statement");
            this.nullableStringAdapter.toJson(jsonWriter, payload.getStatement());
            jsonWriter.name("requestId");
            this.nullableStringAdapter.toJson(jsonWriter, payload.getRequestId());
            jsonWriter.name("resources");
            this.nullableListOfStringAdapter.toJson(jsonWriter, payload.getResources());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
