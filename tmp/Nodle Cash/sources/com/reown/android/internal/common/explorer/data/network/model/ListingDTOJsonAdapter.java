package com.reown.android.internal.common.explorer.data.network.model;

import A.a;
import com.reown.android.pulse.model.ConnectionMethod;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u001c\u001a\u00020\nH\u0016J\u0010\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u001a\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\r0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\r0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0001X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\r0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/network/model/ListingDTOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/android/internal/common/explorer/data/network/model/ListingDTO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "nullableStringAdapter", "listOfStringAdapter", "", "imageUrlDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "appDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;", "nullableListOfInjectedDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/InjectedDTO;", "mobileDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;", "desktopDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/DesktopDTO;", "listOfSupportedStandardDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/SupportedStandardDTO;", "metadataDTOAdapter", "Lcom/reown/android/internal/common/explorer/data/network/model/MetadataDTO;", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ListingDTOJsonAdapter extends JsonAdapter<ListingDTO> {
    @NotNull
    private final JsonAdapter<AppDTO> appDTOAdapter;
    @NotNull
    private final JsonAdapter<DesktopDTO> desktopDTOAdapter;
    @NotNull
    private final JsonAdapter<ImageUrlDTO> imageUrlDTOAdapter;
    @NotNull
    private final JsonAdapter<List<String>> listOfStringAdapter;
    @NotNull
    private final JsonAdapter<List<SupportedStandardDTO>> listOfSupportedStandardDTOAdapter;
    @NotNull
    private final JsonAdapter<MetadataDTO> metadataDTOAdapter;
    @NotNull
    private final JsonAdapter<MobileDTO> mobileDTOAdapter;
    @NotNull
    private final JsonAdapter<List<InjectedDTO>> nullableListOfInjectedDTOAdapter;
    @NotNull
    private final JsonAdapter<String> nullableStringAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public ListingDTOJsonAdapter(@NotNull Moshi moshi) {
        Moshi moshi2 = moshi;
        Intrinsics.checkNotNullParameter(moshi2, "moshi");
        JsonReader.Options of = JsonReader.Options.of(TtmlNode.ATTR_ID, "name", "description", "homepage", "chains", "versions", "sdks", "app_type", "image_id", "image_url", "app", "injected", ConnectionMethod.MOBILE, "desktop", "supported_standards", TtmlNode.TAG_METADATA, "updatedAt");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        Class<String> cls = String.class;
        this.stringAdapter = a.h(moshi2, cls, TtmlNode.ATTR_ID, "adapter(...)");
        this.nullableStringAdapter = a.h(moshi2, cls, "description", "adapter(...)");
        Type[] typeArr = {cls};
        Class<List> cls2 = List.class;
        this.listOfStringAdapter = a.i(moshi2, Types.newParameterizedType(cls2, typeArr), "chains", "adapter(...)");
        this.imageUrlDTOAdapter = a.h(moshi2, ImageUrlDTO.class, "imageUrl", "adapter(...)");
        this.appDTOAdapter = a.h(moshi2, AppDTO.class, "app", "adapter(...)");
        this.nullableListOfInjectedDTOAdapter = a.i(moshi2, Types.newParameterizedType(cls2, InjectedDTO.class), "injected", "adapter(...)");
        this.mobileDTOAdapter = a.h(moshi2, MobileDTO.class, ConnectionMethod.MOBILE, "adapter(...)");
        this.desktopDTOAdapter = a.h(moshi2, DesktopDTO.class, "desktop", "adapter(...)");
        this.listOfSupportedStandardDTOAdapter = a.i(moshi2, Types.newParameterizedType(cls2, SupportedStandardDTO.class), "supportedStandards", "adapter(...)");
        this.metadataDTOAdapter = a.h(moshi2, MetadataDTO.class, TtmlNode.TAG_METADATA, "adapter(...)");
    }

    @NotNull
    public String toString() {
        return a.j(32, "GeneratedJsonAdapter(ListingDTO)");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x008f, code lost:
        r13 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0091, code lost:
        r12 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0093, code lost:
        r11 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0095, code lost:
        r10 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0097, code lost:
        r9 = r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0099, code lost:
        r8 = r29;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x009b, code lost:
        r7 = r30;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x009d, code lost:
        r5 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x009f, code lost:
        r4 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0089, code lost:
        r15 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x008b, code lost:
        r6 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x008d, code lost:
        r14 = r23;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.reown.android.internal.common.explorer.data.network.model.ListingDTO fromJson(@org.jetbrains.annotations.NotNull com.squareup.moshi.JsonReader r40) {
        /*
            r39 = this;
            r0 = r39
            r1 = r40
            java.lang.String r2 = "reader"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r40.beginObject()
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
            r16 = r15
            r17 = r16
            r18 = r17
            r19 = r18
            r20 = r19
        L_0x0023:
            boolean r2 = r40.hasNext()
            java.lang.String r3 = "app_type"
            r21 = r15
            java.lang.String r15 = "appType"
            r22 = r6
            java.lang.String r6 = "image_id"
            r23 = r14
            java.lang.String r14 = "imageId"
            r24 = r13
            java.lang.String r13 = "image_url"
            r25 = r12
            java.lang.String r12 = "imageUrl"
            r26 = r11
            java.lang.String r11 = "supported_standards"
            r27 = r10
            java.lang.String r10 = "supportedStandards"
            r28 = r9
            java.lang.String r9 = "id"
            r29 = r8
            java.lang.String r8 = "name"
            r30 = r7
            java.lang.String r7 = "homepage"
            r31 = r5
            java.lang.String r5 = "chains"
            r32 = r4
            java.lang.String r4 = "versions"
            r33 = r9
            java.lang.String r9 = "sdks"
            r34 = r8
            java.lang.String r8 = "app"
            r35 = r7
            java.lang.String r7 = "mobile"
            r36 = r5
            java.lang.String r5 = "desktop"
            r37 = r4
            java.lang.String r4 = "metadata"
            r38 = r9
            java.lang.String r9 = "updatedAt"
            if (r2 == 0) goto L_0x024f
            com.squareup.moshi.JsonReader$Options r2 = r0.options
            int r2 = r1.selectName(r2)
            switch(r2) {
                case -1: goto L_0x0247;
                case 0: goto L_0x021d;
                case 1: goto L_0x01f5;
                case 2: goto L_0x01e8;
                case 3: goto L_0x01c2;
                case 4: goto L_0x019e;
                case 5: goto L_0x017c;
                case 6: goto L_0x015c;
                case 7: goto L_0x0140;
                case 8: goto L_0x0126;
                case 9: goto L_0x010e;
                case 10: goto L_0x00f9;
                case 11: goto L_0x00ef;
                case 12: goto L_0x00dd;
                case 13: goto L_0x00cb;
                case 14: goto L_0x00b9;
                case 15: goto L_0x00a7;
                case 16: goto L_0x007d;
                default: goto L_0x007c;
            }
        L_0x007c:
            goto L_0x0089
        L_0x007d:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r20 = r2
            java.lang.String r20 = (java.lang.String) r20
            if (r20 == 0) goto L_0x00a2
        L_0x0089:
            r15 = r21
        L_0x008b:
            r6 = r22
        L_0x008d:
            r14 = r23
        L_0x008f:
            r13 = r24
        L_0x0091:
            r12 = r25
        L_0x0093:
            r11 = r26
        L_0x0095:
            r10 = r27
        L_0x0097:
            r9 = r28
        L_0x0099:
            r8 = r29
        L_0x009b:
            r7 = r30
        L_0x009d:
            r5 = r31
        L_0x009f:
            r4 = r32
            goto L_0x0023
        L_0x00a2:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r9, r9, r1)
            throw r0
        L_0x00a7:
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.explorer.data.network.model.MetadataDTO> r2 = r0.metadataDTOAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r19 = r2
            com.reown.android.internal.common.explorer.data.network.model.MetadataDTO r19 = (com.reown.android.internal.common.explorer.data.network.model.MetadataDTO) r19
            if (r19 == 0) goto L_0x00b4
            goto L_0x0089
        L_0x00b4:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r4, r4, r1)
            throw r0
        L_0x00b9:
            com.squareup.moshi.JsonAdapter<java.util.List<com.reown.android.internal.common.explorer.data.network.model.SupportedStandardDTO>> r2 = r0.listOfSupportedStandardDTOAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r18 = r2
            java.util.List r18 = (java.util.List) r18
            if (r18 == 0) goto L_0x00c6
            goto L_0x0089
        L_0x00c6:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r10, r11, r1)
            throw r0
        L_0x00cb:
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.explorer.data.network.model.DesktopDTO> r2 = r0.desktopDTOAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r17 = r2
            com.reown.android.internal.common.explorer.data.network.model.DesktopDTO r17 = (com.reown.android.internal.common.explorer.data.network.model.DesktopDTO) r17
            if (r17 == 0) goto L_0x00d8
            goto L_0x0089
        L_0x00d8:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r5, r5, r1)
            throw r0
        L_0x00dd:
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.explorer.data.network.model.MobileDTO> r2 = r0.mobileDTOAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r16 = r2
            com.reown.android.internal.common.explorer.data.network.model.MobileDTO r16 = (com.reown.android.internal.common.explorer.data.network.model.MobileDTO) r16
            if (r16 == 0) goto L_0x00ea
            goto L_0x0089
        L_0x00ea:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r7, r7, r1)
            throw r0
        L_0x00ef:
            com.squareup.moshi.JsonAdapter<java.util.List<com.reown.android.internal.common.explorer.data.network.model.InjectedDTO>> r2 = r0.nullableListOfInjectedDTOAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r15 = r2
            java.util.List r15 = (java.util.List) r15
            goto L_0x008b
        L_0x00f9:
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.explorer.data.network.model.AppDTO> r2 = r0.appDTOAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r14 = r2
            com.reown.android.internal.common.explorer.data.network.model.AppDTO r14 = (com.reown.android.internal.common.explorer.data.network.model.AppDTO) r14
            if (r14 == 0) goto L_0x0109
            r15 = r21
            r6 = r22
            goto L_0x008f
        L_0x0109:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r8, r8, r1)
            throw r0
        L_0x010e:
            com.squareup.moshi.JsonAdapter<com.reown.android.internal.common.explorer.data.network.model.ImageUrlDTO> r2 = r0.imageUrlDTOAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            com.reown.android.internal.common.explorer.data.network.model.ImageUrlDTO r2 = (com.reown.android.internal.common.explorer.data.network.model.ImageUrlDTO) r2
            if (r2 == 0) goto L_0x0121
            r13 = r2
            r15 = r21
            r6 = r22
            r14 = r23
            goto L_0x0091
        L_0x0121:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r12, r13, r1)
            throw r0
        L_0x0126:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r12 = r2
            java.lang.String r12 = (java.lang.String) r12
            if (r12 == 0) goto L_0x013b
            r15 = r21
            r6 = r22
            r14 = r23
            r13 = r24
            goto L_0x0093
        L_0x013b:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r14, r6, r1)
            throw r0
        L_0x0140:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            if (r11 == 0) goto L_0x0157
            r15 = r21
            r6 = r22
            r14 = r23
            r13 = r24
            r12 = r25
            goto L_0x0095
        L_0x0157:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r15, r3, r1)
            throw r0
        L_0x015c:
            com.squareup.moshi.JsonAdapter<java.util.List<java.lang.String>> r2 = r0.listOfStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r10 = r2
            java.util.List r10 = (java.util.List) r10
            if (r10 == 0) goto L_0x0175
            r15 = r21
            r6 = r22
            r14 = r23
            r13 = r24
            r12 = r25
            r11 = r26
            goto L_0x0097
        L_0x0175:
            r2 = r38
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r2, r2, r1)
            throw r0
        L_0x017c:
            com.squareup.moshi.JsonAdapter<java.util.List<java.lang.String>> r2 = r0.listOfStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r9 = r2
            java.util.List r9 = (java.util.List) r9
            if (r9 == 0) goto L_0x0197
            r15 = r21
            r6 = r22
            r14 = r23
            r13 = r24
            r12 = r25
            r11 = r26
            r10 = r27
            goto L_0x0099
        L_0x0197:
            r2 = r37
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r2, r2, r1)
            throw r0
        L_0x019e:
            com.squareup.moshi.JsonAdapter<java.util.List<java.lang.String>> r2 = r0.listOfStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r8 = r2
            java.util.List r8 = (java.util.List) r8
            if (r8 == 0) goto L_0x01bb
            r15 = r21
            r6 = r22
            r14 = r23
            r13 = r24
            r12 = r25
            r11 = r26
            r10 = r27
            r9 = r28
            goto L_0x009b
        L_0x01bb:
            r2 = r36
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r2, r2, r1)
            throw r0
        L_0x01c2:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r7 = r2
            java.lang.String r7 = (java.lang.String) r7
            if (r7 == 0) goto L_0x01e1
            r15 = r21
            r6 = r22
            r14 = r23
            r13 = r24
            r12 = r25
            r11 = r26
            r10 = r27
            r9 = r28
            r8 = r29
            goto L_0x009d
        L_0x01e1:
            r2 = r35
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r2, r2, r1)
            throw r0
        L_0x01e8:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.nullableStringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            r15 = r21
            goto L_0x008d
        L_0x01f5:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r5 = r2
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L_0x0216
            r15 = r21
            r6 = r22
            r14 = r23
            r13 = r24
            r12 = r25
            r11 = r26
            r10 = r27
            r9 = r28
            r8 = r29
            r7 = r30
            goto L_0x009f
        L_0x0216:
            r2 = r34
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r2, r2, r1)
            throw r0
        L_0x021d:
            com.squareup.moshi.JsonAdapter<java.lang.String> r2 = r0.stringAdapter
            java.lang.Object r2 = r2.fromJson((com.squareup.moshi.JsonReader) r1)
            r4 = r2
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x0240
            r15 = r21
            r6 = r22
            r14 = r23
            r13 = r24
            r12 = r25
            r11 = r26
            r10 = r27
            r9 = r28
            r8 = r29
            r7 = r30
            r5 = r31
            goto L_0x0023
        L_0x0240:
            r2 = r33
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.unexpectedNull(r2, r2, r1)
            throw r0
        L_0x0247:
            r40.skipName()
            r40.skipValue()
            goto L_0x0089
        L_0x024f:
            r0 = r37
            r2 = r38
            r40.endObject()
            com.reown.android.internal.common.explorer.data.network.model.ListingDTO r37 = new com.reown.android.internal.common.explorer.data.network.model.ListingDTO
            if (r32 == 0) goto L_0x02e0
            if (r31 == 0) goto L_0x02d9
            if (r30 == 0) goto L_0x02d2
            if (r29 == 0) goto L_0x02cb
            if (r28 == 0) goto L_0x02c6
            if (r27 == 0) goto L_0x02c1
            if (r26 == 0) goto L_0x02bc
            if (r25 == 0) goto L_0x02b7
            if (r24 == 0) goto L_0x02b2
            if (r23 == 0) goto L_0x02ad
            if (r16 == 0) goto L_0x02a8
            if (r17 == 0) goto L_0x02a3
            if (r18 == 0) goto L_0x029e
            if (r19 == 0) goto L_0x0299
            if (r20 == 0) goto L_0x0294
            r3 = r37
            r4 = r32
            r5 = r31
            r6 = r22
            r7 = r30
            r8 = r29
            r9 = r28
            r10 = r27
            r11 = r26
            r12 = r25
            r13 = r24
            r14 = r23
            r15 = r21
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r37
        L_0x0294:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r9, r9, r1)
            throw r0
        L_0x0299:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r4, r4, r1)
            throw r0
        L_0x029e:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r10, r11, r1)
            throw r0
        L_0x02a3:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r5, r5, r1)
            throw r0
        L_0x02a8:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r7, r7, r1)
            throw r0
        L_0x02ad:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r8, r8, r1)
            throw r0
        L_0x02b2:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r12, r13, r1)
            throw r0
        L_0x02b7:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r14, r6, r1)
            throw r0
        L_0x02bc:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r15, r3, r1)
            throw r0
        L_0x02c1:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r2, r2, r1)
            throw r0
        L_0x02c6:
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r0, r0, r1)
            throw r0
        L_0x02cb:
            r0 = r36
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r0, r0, r1)
            throw r0
        L_0x02d2:
            r0 = r35
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r0, r0, r1)
            throw r0
        L_0x02d9:
            r0 = r34
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r0, r0, r1)
            throw r0
        L_0x02e0:
            r0 = r33
            com.squareup.moshi.JsonDataException r0 = com.squareup.moshi.internal.Util.missingProperty(r0, r0, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.explorer.data.network.model.ListingDTOJsonAdapter.fromJson(com.squareup.moshi.JsonReader):com.reown.android.internal.common.explorer.data.network.model.ListingDTO");
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable ListingDTO listingDTO) {
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        if (listingDTO != null) {
            jsonWriter.beginObject();
            jsonWriter.name(TtmlNode.ATTR_ID);
            this.stringAdapter.toJson(jsonWriter, listingDTO.getId());
            jsonWriter.name("name");
            this.stringAdapter.toJson(jsonWriter, listingDTO.getName());
            jsonWriter.name("description");
            this.nullableStringAdapter.toJson(jsonWriter, listingDTO.getDescription());
            jsonWriter.name("homepage");
            this.stringAdapter.toJson(jsonWriter, listingDTO.getHomepage());
            jsonWriter.name("chains");
            this.listOfStringAdapter.toJson(jsonWriter, listingDTO.getChains());
            jsonWriter.name("versions");
            this.listOfStringAdapter.toJson(jsonWriter, listingDTO.getVersions());
            jsonWriter.name("sdks");
            this.listOfStringAdapter.toJson(jsonWriter, listingDTO.getSdks());
            jsonWriter.name("app_type");
            this.stringAdapter.toJson(jsonWriter, listingDTO.getAppType());
            jsonWriter.name("image_id");
            this.stringAdapter.toJson(jsonWriter, listingDTO.getImageId());
            jsonWriter.name("image_url");
            this.imageUrlDTOAdapter.toJson(jsonWriter, listingDTO.getImageUrl());
            jsonWriter.name("app");
            this.appDTOAdapter.toJson(jsonWriter, listingDTO.getApp());
            jsonWriter.name("injected");
            this.nullableListOfInjectedDTOAdapter.toJson(jsonWriter, listingDTO.getInjected());
            jsonWriter.name(ConnectionMethod.MOBILE);
            this.mobileDTOAdapter.toJson(jsonWriter, listingDTO.getMobile());
            jsonWriter.name("desktop");
            this.desktopDTOAdapter.toJson(jsonWriter, listingDTO.getDesktop());
            jsonWriter.name("supported_standards");
            this.listOfSupportedStandardDTOAdapter.toJson(jsonWriter, listingDTO.getSupportedStandards());
            jsonWriter.name(TtmlNode.TAG_METADATA);
            this.metadataDTOAdapter.toJson(jsonWriter, listingDTO.getMetadata());
            jsonWriter.name("updatedAt");
            this.stringAdapter.toJson(jsonWriter, listingDTO.getUpdatedAt());
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
    }
}
