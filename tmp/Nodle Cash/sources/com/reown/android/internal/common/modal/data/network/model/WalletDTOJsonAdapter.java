package com.reown.android.internal.common.modal.data.network.model;

import A.a;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\f\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/reown/android/internal/common/modal/data/network/model/WalletDTOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/modal/data/network/model/WalletDTO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableStringAdapter", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WalletDTOJsonAdapter extends JsonAdapter<WalletDTO> {
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public WalletDTOJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(TtmlNode.ATTR_ID, "name", "homepage", "image_id", "order", "mobile_link", "desktop_link", "webapp_link", "app_store", "play_store", "link_mode");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi, cls, TtmlNode.ATTR_ID, "adapter(...)");
        this.nullableStringAdapter = a.h(moshi, cls, "mobileLink", "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(31, "GeneratedJsonAdapter(WalletDTO)");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0097, code lost:
        r14 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0049, code lost:
        r13 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x004b, code lost:
        r12 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004d, code lost:
        r11 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004f, code lost:
        r10 = r20;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.reown.android.internal.common.modal.data.network.model.WalletDTO fromJson(@org.jetbrains.annotations.NotNull com.squareup.moshi.JsonReader r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            java.lang.String r2 = "reader"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r22.beginObject()
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
            boolean r2 = r22.hasNext()
            java.lang.String r3 = "homepage"
            java.lang.String r15 = "homePage"
            r16 = r14
            java.lang.String r14 = "image_id"
            r17 = r13
            java.lang.String r13 = "imageId"
            r18 = r12
            java.lang.String r12 = "id"
            r19 = r11
            java.lang.String r11 = "name"
            r20 = r10
            java.lang.String r10 = "order"
            if (r2 == 0) goto L_0x00f6
            com.squareup.moshi.JsonReader$Options r2 = r0.options
            int r2 = r1.selectName(r2)
            switch(r2) {
                case -1: goto L_0x00ef;
                case 0: goto L_0x00de;
                case 1: goto L_0x00cd;
                case 2: goto L_0x00bc;
                case 3: goto L_0x00ab;
                case 4: goto L_0x009a;
                case 5: goto L_0x008e;
                case 6: goto L_0x007c;
                case 7: goto L_0x006c;
                case 8: goto L_0x005e;
                case 9: goto L_0x0052;
                case 10: goto L_0x0040;
                default: goto L_0x003f;
            }
        L_0x003f:
            goto L_0x0097
        L_0x0040:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r14 = r2
            java.lang.String r14 = (java.lang.String) r14
        L_0x0049:
            r13 = r17
        L_0x004b:
            r12 = r18
        L_0x004d:
            r11 = r19
        L_0x004f:
            r10 = r20
            goto L_0x0018
        L_0x0052:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r13 = r2
            java.lang.String r13 = (java.lang.String) r13
            r14 = r16
            goto L_0x004b
        L_0x005e:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r12 = r2
            java.lang.String r12 = (java.lang.String) r12
            r14 = r16
            r13 = r17
            goto L_0x004d
        L_0x006c:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            r14 = r16
            r13 = r17
            r12 = r18
            goto L_0x004f
        L_0x007c:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r10 = r2
            java.lang.String r10 = (java.lang.String) r10
            r14 = r16
            r13 = r17
            r12 = r18
            r11 = r19
            goto L_0x0018
        L_0x008e:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9
        L_0x0097:
            r14 = r16
            goto L_0x0049
        L_0x009a:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x00a6
        L_0x00a5:
            goto L_0x0097
        L_0x00a6:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r10, r10, r1)
            throw r0
        L_0x00ab:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r7 = r2
            java.lang.String r7 = (java.lang.String) r7
            if (r7 == 0) goto L_0x00b7
            goto L_0x00a5
        L_0x00b7:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r13, r14, r1)
            throw r0
        L_0x00bc:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x00c8
            goto L_0x00a5
        L_0x00c8:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r15, r3, r1)
            throw r0
        L_0x00cd:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r5 = r2
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L_0x00d9
            goto L_0x00a5
        L_0x00d9:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r11, r11, r1)
            throw r0
        L_0x00de:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r4 = r2
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x00ea
            goto L_0x00a5
        L_0x00ea:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r12, r12, r1)
            throw r0
        L_0x00ef:
            r22.skipName()
            r22.skipValue()
            goto L_0x0097
        L_0x00f6:
            r22.endObject()
            com.reown.android.internal.common.modal.data.network.model.WalletDTO r0 = new com.reown.android.internal.common.modal.data.network.model.WalletDTO
            if (r4 == 0) goto L_0x0128
            if (r5 == 0) goto L_0x0123
            if (r6 == 0) goto L_0x011e
            if (r7 == 0) goto L_0x0119
            if (r8 == 0) goto L_0x0114
            r3 = r0
            r10 = r20
            r11 = r19
            r12 = r18
            r13 = r17
            r14 = r16
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        L_0x0114:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r10, r10, r1)
            throw r0
        L_0x0119:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r13, r14, r1)
            throw r0
        L_0x011e:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r15, r3, r1)
            throw r0
        L_0x0123:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r11, r11, r1)
            throw r0
        L_0x0128:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r12, r12, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.modal.data.network.model.WalletDTOJsonAdapter.fromJson(com.squareup.moshi.JsonReader):com.reown.android.internal.common.modal.data.network.model.WalletDTO");
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable WalletDTO walletDTO) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (walletDTO != null) {
            jsonWriter.beginObject();
            jsonWriter.name(TtmlNode.ATTR_ID);
            this.stringAdapter.toJson(jsonWriter, walletDTO.getId());
            jsonWriter.name("name");
            this.stringAdapter.toJson(jsonWriter, walletDTO.getName());
            jsonWriter.name("homepage");
            this.stringAdapter.toJson(jsonWriter, walletDTO.getHomePage());
            jsonWriter.name("image_id");
            this.stringAdapter.toJson(jsonWriter, walletDTO.getImageId());
            jsonWriter.name("order");
            this.stringAdapter.toJson(jsonWriter, walletDTO.getOrder());
            jsonWriter.name("mobile_link");
            this.nullableStringAdapter.toJson(jsonWriter, walletDTO.getMobileLink());
            jsonWriter.name("desktop_link");
            this.nullableStringAdapter.toJson(jsonWriter, walletDTO.getDesktopLink());
            jsonWriter.name("webapp_link");
            this.nullableStringAdapter.toJson(jsonWriter, walletDTO.getWebappLink());
            jsonWriter.name("app_store");
            this.nullableStringAdapter.toJson(jsonWriter, walletDTO.getAppStore());
            jsonWriter.name("play_store");
            this.nullableStringAdapter.toJson(jsonWriter, walletDTO.getPlayStore());
            jsonWriter.name("link_mode");
            this.nullableStringAdapter.toJson(jsonWriter, walletDTO.getLinkMode());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
