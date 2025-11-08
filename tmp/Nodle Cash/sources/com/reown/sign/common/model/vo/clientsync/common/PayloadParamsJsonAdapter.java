package com.reown.sign.common.model.vo.clientsync.common;

import A.a;
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

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParamsJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/common/model/vo/clientsync/common/PayloadParams;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "listOfStringAdapter", "", "nullableStringAdapter", "nullableListOfStringAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PayloadParamsJsonAdapter extends JsonAdapter<PayloadParams> {
    @NotNull
    private final JsonAdapter<List<String>> listOfStringAdapter;
    @NotNull
    private final JsonAdapter<List<String>> nullableListOfStringAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public PayloadParamsJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("type", "chains", "domain", "aud", "nonce", "version", "iat", "nbf", "exp", "statement", "requestId", "resources");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, "type", "adapter(...)");
        Class<List> cls2 = List.class;
        this.listOfStringAdapter = a.i(moshi, Types.newParameterizedType(cls2, cls), "chains", "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "nbf", "adapter(...)");
        this.nullableListOfStringAdapter = a.i(moshi, Types.newParameterizedType(cls2, cls), "resources", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(35, "GeneratedJsonAdapter(PayloadParams)");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0055, code lost:
        r10 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00bb, code lost:
        r15 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x004d, code lost:
        r14 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x004f, code lost:
        r13 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0051, code lost:
        r12 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0053, code lost:
        r11 = r20;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.reown.sign.common.model.vo.clientsync.common.PayloadParams fromJson(@org.jetbrains.annotations.NotNull com.squareup.moshi.JsonReader r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            java.lang.String r2 = "reader"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r23.beginObject()
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
            r15 = r14
        L_0x0019:
            boolean r2 = r23.hasNext()
            java.lang.String r3 = "type"
            r16 = r15
            java.lang.String r15 = "chains"
            r17 = r14
            java.lang.String r14 = "domain"
            r18 = r13
            java.lang.String r13 = "aud"
            r19 = r12
            java.lang.String r12 = "nonce"
            r20 = r11
            java.lang.String r11 = "version"
            r21 = r10
            java.lang.String r10 = "iat"
            if (r2 == 0) goto L_0x011f
            com.squareup.moshi.JsonReader$Options r2 = r0.options
            int r2 = r1.selectName(r2)
            switch(r2) {
                case -1: goto L_0x0118;
                case 0: goto L_0x0107;
                case 1: goto L_0x00f6;
                case 2: goto L_0x00e5;
                case 3: goto L_0x00d4;
                case 4: goto L_0x00c3;
                case 5: goto L_0x00b0;
                case 6: goto L_0x0094;
                case 7: goto L_0x0082;
                case 8: goto L_0x0072;
                case 9: goto L_0x0064;
                case 10: goto L_0x0058;
                case 11: goto L_0x0044;
                default: goto L_0x0042;
            }
        L_0x0042:
            goto L_0x00bb
        L_0x0044:
            com.squareup.moshi.JsonAdapter<java.util.List<java.lang.String>> r2 = r0.nullableListOfStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r15 = r2
            java.util.List r15 = (java.util.List) r15
        L_0x004d:
            r14 = r17
        L_0x004f:
            r13 = r18
        L_0x0051:
            r12 = r19
        L_0x0053:
            r11 = r20
        L_0x0055:
            r10 = r21
            goto L_0x0019
        L_0x0058:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r14 = r2
            java.lang.String r14 = (java.lang.String) r14
            r15 = r16
            goto L_0x004f
        L_0x0064:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r13 = r2
            java.lang.String r13 = (java.lang.String) r13
            r15 = r16
            r14 = r17
            goto L_0x0051
        L_0x0072:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r12 = r2
            java.lang.String r12 = (java.lang.String) r12
            r15 = r16
            r14 = r17
            r13 = r18
            goto L_0x0053
        L_0x0082:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            r15 = r16
            r14 = r17
            r13 = r18
            r12 = r19
            goto L_0x0055
        L_0x0094:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x00ab
            r10 = r2
            r15 = r16
            r14 = r17
            r13 = r18
            r12 = r19
            r11 = r20
            goto L_0x0019
        L_0x00ab:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r10, r10, r1)
            throw r0
        L_0x00b0:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9
            if (r9 == 0) goto L_0x00be
        L_0x00bb:
            r15 = r16
            goto L_0x004d
        L_0x00be:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r11, r11, r1)
            throw r0
        L_0x00c3:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x00cf
            goto L_0x00bb
        L_0x00cf:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r12, r12, r1)
            throw r0
        L_0x00d4:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r7 = r2
            java.lang.String r7 = (java.lang.String) r7
            if (r7 == 0) goto L_0x00e0
            goto L_0x00bb
        L_0x00e0:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r13, r13, r1)
            throw r0
        L_0x00e5:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x00f1
            goto L_0x00bb
        L_0x00f1:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r14, r14, r1)
            throw r0
        L_0x00f6:
            com.squareup.moshi.JsonAdapter<java.util.List<java.lang.String>> r2 = r0.listOfStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r5 = r2
            java.util.List r5 = (java.util.List) r5
            if (r5 == 0) goto L_0x0102
            goto L_0x00bb
        L_0x0102:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r15, r15, r1)
            throw r0
        L_0x0107:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r4 = r2
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x0113
            goto L_0x00bb
        L_0x0113:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r3, r3, r1)
            throw r0
        L_0x0118:
            r23.skipName()
            r23.skipValue()
            goto L_0x00bb
        L_0x011f:
            r23.endObject()
            com.reown.sign.common.model.vo.clientsync.common.PayloadParams r0 = new com.reown.sign.common.model.vo.clientsync.common.PayloadParams
            if (r4 == 0) goto L_0x0161
            if (r5 == 0) goto L_0x015c
            if (r6 == 0) goto L_0x0157
            if (r7 == 0) goto L_0x0152
            if (r8 == 0) goto L_0x014d
            if (r9 == 0) goto L_0x0148
            if (r21 == 0) goto L_0x0143
            r3 = r0
            r10 = r21
            r11 = r20
            r12 = r19
            r13 = r18
            r14 = r17
            r15 = r16
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return r0
        L_0x0143:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r10, r10, r1)
            throw r0
        L_0x0148:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r11, r11, r1)
            throw r0
        L_0x014d:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r12, r12, r1)
            throw r0
        L_0x0152:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r13, r13, r1)
            throw r0
        L_0x0157:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r14, r14, r1)
            throw r0
        L_0x015c:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r15, r15, r1)
            throw r0
        L_0x0161:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r3, r3, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.common.model.vo.clientsync.common.PayloadParamsJsonAdapter.fromJson(com.squareup.moshi.JsonReader):com.reown.sign.common.model.vo.clientsync.common.PayloadParams");
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable PayloadParams payloadParams) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (payloadParams != null) {
            jsonWriter.beginObject();
            jsonWriter.name("type");
            this.stringAdapter.toJson(jsonWriter, payloadParams.getType());
            jsonWriter.name("chains");
            this.listOfStringAdapter.toJson(jsonWriter, payloadParams.getChains());
            jsonWriter.name("domain");
            this.stringAdapter.toJson(jsonWriter, payloadParams.getDomain());
            jsonWriter.name("aud");
            this.stringAdapter.toJson(jsonWriter, payloadParams.getAud());
            jsonWriter.name("nonce");
            this.stringAdapter.toJson(jsonWriter, payloadParams.getNonce());
            jsonWriter.name("version");
            this.stringAdapter.toJson(jsonWriter, payloadParams.getVersion());
            jsonWriter.name("iat");
            this.stringAdapter.toJson(jsonWriter, payloadParams.getIat());
            jsonWriter.name("nbf");
            this.nullableStringAdapter.toJson(jsonWriter, payloadParams.getNbf());
            jsonWriter.name("exp");
            this.nullableStringAdapter.toJson(jsonWriter, payloadParams.getExp());
            jsonWriter.name("statement");
            this.nullableStringAdapter.toJson(jsonWriter, payloadParams.getStatement());
            jsonWriter.name("requestId");
            this.nullableStringAdapter.toJson(jsonWriter, payloadParams.getRequestId());
            jsonWriter.name("resources");
            this.nullableListOfStringAdapter.toJson(jsonWriter, payloadParams.getResources());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
