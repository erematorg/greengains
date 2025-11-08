package com.appsamurai.storyly.data;

import android.graphics.Point;
import com.appsamurai.storyly.util.m;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable(with = a.class)
public final class b0 {
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public static final a f3605o = new a();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3606a;

    /* renamed from: b  reason: collision with root package name */
    public final float f3607b;

    /* renamed from: c  reason: collision with root package name */
    public final float f3608c;

    /* renamed from: d  reason: collision with root package name */
    public final float f3609d;

    /* renamed from: e  reason: collision with root package name */
    public final float f3610e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public final Float f3611f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final Float f3612g;

    /* renamed from: h  reason: collision with root package name */
    public final float f3613h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final String f3614i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final a0 f3615j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final Long f3616k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public final Long f3617l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public final b f3618m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final Lazy f3619n = LazyKt.lazy(new b(this));

    public static final class a implements KSerializer<b0> {

        /* renamed from: com.appsamurai.storyly.data.b0$a$a  reason: collision with other inner class name */
        public static final class C0008a extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {

            /* renamed from: a  reason: collision with root package name */
            public static final C0008a f3620a = new C0008a();

            public C0008a() {
                super(1);
            }

            public Object invoke(Object obj) {
                Intrinsics.checkNotNullParameter((ClassSerialDescriptorBuilder) obj, "$this$buildClassSerialDescriptor");
                return Unit.INSTANCE;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:141:0x0338  */
        /* JADX WARNING: Removed duplicated region for block: B:158:0x0363 A[Catch:{ Exception -> 0x0376 }] */
        /* JADX WARNING: Removed duplicated region for block: B:159:0x0364 A[Catch:{ Exception -> 0x0376 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r19) {
            /*
                r18 = this;
                r0 = r19
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                boolean r1 = r0 instanceof kotlinx.serialization.json.JsonDecoder
                if (r1 == 0) goto L_0x000f
                r1 = r0
                kotlinx.serialization.json.JsonDecoder r1 = (kotlinx.serialization.json.JsonDecoder) r1
                goto L_0x0010
            L_0x000f:
                r1 = 0
            L_0x0010:
                if (r1 != 0) goto L_0x0014
                goto L_0x0336
            L_0x0014:
                kotlinx.serialization.json.JsonElement r1 = r1.decodeJsonElement()
                kotlinx.serialization.json.JsonObject r1 = kotlinx.serialization.json.JsonElementKt.getJsonObject(r1)
                if (r1 == 0) goto L_0x001f
                goto L_0x0020
            L_0x001f:
                r1 = 0
            L_0x0020:
                if (r1 != 0) goto L_0x0024
                goto L_0x0336
            L_0x0024:
                java.lang.String r3 = "type"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x0030
                goto L_0x0036
            L_0x0030:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x0038
            L_0x0036:
                r5 = 0
                goto L_0x003d
            L_0x0038:
                java.lang.String r3 = r3.getContent()
                r5 = r3
            L_0x003d:
                if (r5 != 0) goto L_0x0041
                goto L_0x0336
            L_0x0041:
                java.lang.String r3 = "placeholder"
                r1.get((java.lang.Object) r3)
                java.lang.String r3 = "uid"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x0052
                goto L_0x0058
            L_0x0052:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x005a
            L_0x0058:
                r3 = 0
                goto L_0x005e
            L_0x005a:
                java.lang.String r3 = r3.getContent()
            L_0x005e:
                if (r3 != 0) goto L_0x007d
                java.util.UUID r3 = java.util.UUID.randomUUID()
                java.lang.String r3 = r3.toString()
                java.lang.String r4 = "randomUUID().toString()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                java.util.Locale r4 = java.util.Locale.ENGLISH
                java.lang.String r6 = "ENGLISH"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
                java.lang.String r3 = r3.toUpperCase(r4)
                java.lang.String r4 = "(this as java.lang.String).toUpperCase(locale)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            L_0x007d:
                r13 = r3
                java.lang.String r3 = "x"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                r4 = 0
                if (r3 != 0) goto L_0x008b
                goto L_0x0098
            L_0x008b:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x0092
                goto L_0x0098
            L_0x0092:
                java.lang.Float r3 = kotlinx.serialization.json.JsonElementKt.getFloatOrNull(r3)
                if (r3 != 0) goto L_0x009a
            L_0x0098:
                r6 = r4
                goto L_0x009f
            L_0x009a:
                float r3 = r3.floatValue()
                r6 = r3
            L_0x009f:
                java.lang.String r3 = "y"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                if (r3 != 0) goto L_0x00ab
                goto L_0x00b8
            L_0x00ab:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x00b2
                goto L_0x00b8
            L_0x00b2:
                java.lang.Float r3 = kotlinx.serialization.json.JsonElementKt.getFloatOrNull(r3)
                if (r3 != 0) goto L_0x00ba
            L_0x00b8:
                r7 = r4
                goto L_0x00bf
            L_0x00ba:
                float r3 = r3.floatValue()
                r7 = r3
            L_0x00bf:
                java.lang.String r3 = "w"
                java.lang.Object r3 = r1.get((java.lang.Object) r3)
                kotlinx.serialization.json.JsonElement r3 = (kotlinx.serialization.json.JsonElement) r3
                r8 = 1120403456(0x42c80000, float:100.0)
                if (r3 != 0) goto L_0x00cd
                goto L_0x00da
            L_0x00cd:
                kotlinx.serialization.json.JsonPrimitive r3 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r3)
                if (r3 != 0) goto L_0x00d4
                goto L_0x00da
            L_0x00d4:
                java.lang.Float r3 = kotlinx.serialization.json.JsonElementKt.getFloatOrNull(r3)
                if (r3 != 0) goto L_0x00dc
            L_0x00da:
                r3 = r8
                goto L_0x00e0
            L_0x00dc:
                float r3 = r3.floatValue()
            L_0x00e0:
                java.lang.String r9 = "h"
                java.lang.Object r9 = r1.get((java.lang.Object) r9)
                kotlinx.serialization.json.JsonElement r9 = (kotlinx.serialization.json.JsonElement) r9
                if (r9 != 0) goto L_0x00eb
                goto L_0x00f8
            L_0x00eb:
                kotlinx.serialization.json.JsonPrimitive r9 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r9)
                if (r9 != 0) goto L_0x00f2
                goto L_0x00f8
            L_0x00f2:
                java.lang.Float r9 = kotlinx.serialization.json.JsonElementKt.getFloatOrNull(r9)
                if (r9 != 0) goto L_0x00fa
            L_0x00f8:
                r9 = r8
                goto L_0x00ff
            L_0x00fa:
                float r8 = r9.floatValue()
                goto L_0x00f8
            L_0x00ff:
                java.lang.String r8 = "c_x"
                java.lang.Object r8 = r1.get((java.lang.Object) r8)
                kotlinx.serialization.json.JsonElement r8 = (kotlinx.serialization.json.JsonElement) r8
                if (r8 != 0) goto L_0x010a
                goto L_0x0110
            L_0x010a:
                kotlinx.serialization.json.JsonPrimitive r8 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r8)
                if (r8 != 0) goto L_0x0112
            L_0x0110:
                r10 = 0
                goto L_0x0117
            L_0x0112:
                java.lang.Float r8 = kotlinx.serialization.json.JsonElementKt.getFloatOrNull(r8)
                r10 = r8
            L_0x0117:
                java.lang.String r8 = "c_y"
                java.lang.Object r8 = r1.get((java.lang.Object) r8)
                kotlinx.serialization.json.JsonElement r8 = (kotlinx.serialization.json.JsonElement) r8
                if (r8 != 0) goto L_0x0122
                goto L_0x0128
            L_0x0122:
                kotlinx.serialization.json.JsonPrimitive r8 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r8)
                if (r8 != 0) goto L_0x012a
            L_0x0128:
                r11 = 0
                goto L_0x012f
            L_0x012a:
                java.lang.Float r8 = kotlinx.serialization.json.JsonElementKt.getFloatOrNull(r8)
                r11 = r8
            L_0x012f:
                java.lang.String r8 = "rotation"
                java.lang.Object r8 = r1.get((java.lang.Object) r8)
                kotlinx.serialization.json.JsonElement r8 = (kotlinx.serialization.json.JsonElement) r8
                if (r8 != 0) goto L_0x013a
                goto L_0x0147
            L_0x013a:
                kotlinx.serialization.json.JsonPrimitive r8 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r8)
                if (r8 != 0) goto L_0x0141
                goto L_0x0147
            L_0x0141:
                java.lang.Float r8 = kotlinx.serialization.json.JsonElementKt.getFloatOrNull(r8)
                if (r8 != 0) goto L_0x0149
            L_0x0147:
                r12 = r4
                goto L_0x014e
            L_0x0149:
                float r4 = r8.floatValue()
                goto L_0x0147
            L_0x014e:
                java.lang.String r4 = "animation"
                java.lang.Object r4 = r1.get((java.lang.Object) r4)
                kotlinx.serialization.json.JsonElement r4 = (kotlinx.serialization.json.JsonElement) r4
                if (r4 != 0) goto L_0x015b
                r17 = 0
                goto L_0x016c
            L_0x015b:
                r8 = r0
                kotlinx.serialization.json.JsonDecoder r8 = (kotlinx.serialization.json.JsonDecoder) r8
                kotlinx.serialization.json.Json r8 = r8.getJson()
                com.appsamurai.storyly.data.b$a r14 = com.appsamurai.storyly.data.b.a.f3603a
                java.lang.Object r4 = r8.decodeFromJsonElement(r14, r4)
                com.appsamurai.storyly.data.b r4 = (com.appsamurai.storyly.data.b) r4
                r17 = r4
            L_0x016c:
                java.lang.String r4 = "swipe"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x0186
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.n0$a r4 = com.appsamurai.storyly.data.n0.a.f4076a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
            L_0x0183:
                r14 = r0
                goto L_0x0334
            L_0x0186:
                java.lang.String r4 = "button"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x019d
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.p$a r4 = com.appsamurai.storyly.data.p.a.f4115a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x019d:
                java.lang.String r4 = "text"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x01b5
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.o0$a r4 = com.appsamurai.storyly.data.o0.a.f4092a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x01b5:
                java.lang.String r4 = "emoji"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x01cc
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.t$a r4 = com.appsamurai.storyly.data.t.a.f4211a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x01cc:
                java.lang.String r4 = "image"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x01e3
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.x$a r4 = com.appsamurai.storyly.data.x.a.f4272a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x01e3:
                java.lang.String r4 = "image_cta"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x01fa
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.x$a r4 = com.appsamurai.storyly.data.x.a.f4272a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x01fa:
                java.lang.String r4 = "poll"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x0212
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.e0$a r4 = com.appsamurai.storyly.data.e0.a.f3672a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x0212:
                java.lang.String r4 = "countdown"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x022a
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.r$a r4 = com.appsamurai.storyly.data.r.a.f4184a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x022a:
                java.lang.String r4 = "quiz"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x0242
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.k0$a r4 = com.appsamurai.storyly.data.k0.a.f3832a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x0242:
                java.lang.String r4 = "rating"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x025a
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.l0$a r4 = com.appsamurai.storyly.data.l0.a.f3855a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x025a:
                java.lang.String r4 = "product_tag"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x0272
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.i0$a r4 = com.appsamurai.storyly.data.i0.a.f3786a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x0272:
                java.lang.String r4 = "product_card"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x028a
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.f0$a r4 = com.appsamurai.storyly.data.f0.a.f3717a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x028a:
                java.lang.String r4 = "product_catalog"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x02a2
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.h0$a r4 = com.appsamurai.storyly.data.h0.a.f3744a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x02a2:
                java.lang.String r4 = "promo_code"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x02ba
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.j0$a r4 = com.appsamurai.storyly.data.j0.a.f3801a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x02ba:
                java.lang.String r4 = "comment"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x02d2
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.q$a r4 = com.appsamurai.storyly.data.q.a.f4144a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x02d2:
                java.lang.String r4 = "video"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x02eb
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.q0$a r4 = com.appsamurai.storyly.data.q0.a.f4153a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x02eb:
                java.lang.String r4 = "ad"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x0303
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.o$a r4 = com.appsamurai.storyly.data.o.a.f4079a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x0303:
                java.lang.String r4 = "link_cta"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x031b
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.c0$a r4 = com.appsamurai.storyly.data.c0.a.f3629a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x031b:
                java.lang.String r4 = "image_quiz"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)
                if (r4 == 0) goto L_0x0333
                kotlinx.serialization.json.JsonDecoder r0 = (kotlinx.serialization.json.JsonDecoder) r0
                kotlinx.serialization.json.Json r0 = r0.getJson()
                com.appsamurai.storyly.data.y$a r4 = com.appsamurai.storyly.data.y.a.f4298a
                java.lang.Object r0 = r0.decodeFromJsonElement(r4, r1)
                com.appsamurai.storyly.data.a0 r0 = (com.appsamurai.storyly.data.a0) r0
                goto L_0x0183
            L_0x0333:
                r14 = 0
            L_0x0334:
                if (r14 != 0) goto L_0x0338
            L_0x0336:
                r2 = 0
                goto L_0x037e
            L_0x0338:
                r0 = 1000(0x3e8, float:1.401E-42)
                java.lang.String r4 = "start_time"
                java.lang.Object r4 = r1.get((java.lang.Object) r4)     // Catch:{ Exception -> 0x0344 }
                kotlinx.serialization.json.JsonElement r4 = (kotlinx.serialization.json.JsonElement) r4     // Catch:{ Exception -> 0x0344 }
                if (r4 != 0) goto L_0x0346
            L_0x0344:
                r8 = r3
                goto L_0x0358
            L_0x0346:
                kotlinx.serialization.json.JsonPrimitive r4 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r4)     // Catch:{ Exception -> 0x0344 }
                float r4 = kotlinx.serialization.json.JsonElementKt.getFloat(r4)     // Catch:{ Exception -> 0x0344 }
                float r8 = (float) r0
                float r4 = r4 * r8
                r8 = r3
                long r2 = (long) r4
                java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x0358 }
                r15 = r2
                goto L_0x0359
            L_0x0358:
                r15 = 0
            L_0x0359:
                java.lang.String r2 = "end_time"
                java.lang.Object r1 = r1.get((java.lang.Object) r2)     // Catch:{ Exception -> 0x0376 }
                kotlinx.serialization.json.JsonElement r1 = (kotlinx.serialization.json.JsonElement) r1     // Catch:{ Exception -> 0x0376 }
                if (r1 != 0) goto L_0x0364
                goto L_0x0376
            L_0x0364:
                kotlinx.serialization.json.JsonPrimitive r1 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r1)     // Catch:{ Exception -> 0x0376 }
                float r1 = kotlinx.serialization.json.JsonElementKt.getFloat(r1)     // Catch:{ Exception -> 0x0376 }
                float r0 = (float) r0     // Catch:{ Exception -> 0x0376 }
                float r1 = r1 * r0
                long r0 = (long) r1     // Catch:{ Exception -> 0x0376 }
                java.lang.Long r2 = java.lang.Long.valueOf(r0)     // Catch:{ Exception -> 0x0376 }
                r16 = r2
                goto L_0x0378
            L_0x0376:
                r16 = 0
            L_0x0378:
                com.appsamurai.storyly.data.b0 r2 = new com.appsamurai.storyly.data.b0
                r4 = r2
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            L_0x037e:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.b0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return SerialDescriptorsKt.buildClassSerialDescriptor("StorylyLayerItem", new SerialDescriptor[0], C0008a.f3620a);
        }

        public void serialize(Encoder encoder, Object obj) {
            b0 b0Var = (b0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
        }
    }

    public static final class b extends Lambda implements Function0<Point> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b0 f3621a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(b0 b0Var) {
            super(0);
            this.f3621a = b0Var;
        }

        public Object invoke() {
            b0 b0Var = this.f3621a;
            Float f2 = b0Var.f3611f;
            if (!(f2 == null || b0Var.f3612g == null)) {
                float floatValue = f2.floatValue();
                b0 b0Var2 = this.f3621a;
                float f3 = (float) 2;
                float width = ((float) m.c().width()) * (floatValue - (b0Var2.f3609d / f3));
                float f4 = (float) 100;
                new Point((int) (width / f4), (int) ((((float) m.c().height()) * (b0Var2.f3612g.floatValue() - (this.f3621a.f3610e / f3))) / f4));
            }
            float f5 = (float) 100;
            float height = (((float) m.c().height()) * this.f3621a.f3610e) / f5;
            float width2 = (((float) m.c().width()) * this.f3621a.f3609d) / f5;
            float width3 = (((float) m.c().width()) * this.f3621a.f3607b) / f5;
            b0 b0Var3 = this.f3621a;
            float height2 = (((float) m.c().height()) * b0Var3.f3608c) / f5;
            float f6 = (float) 2;
            float f7 = width2 / f6;
            float f8 = height / f6;
            double d2 = (((double) b0Var3.f3613h) * 3.141592653589793d) / ((double) 180);
            double d3 = (double) ((width3 + f7) - width3);
            float f9 = f8;
            double d4 = (double) ((height2 + f8) - height2);
            Pair pair = new Pair(Double.valueOf(((Math.cos(d2) * d3) - (Math.sin(d2) * d4)) + ((double) width3)), Double.valueOf((Math.cos(d2) * d4) + (Math.sin(d2) * d3) + ((double) height2)));
            return new Point((int) (((Number) pair.getFirst()).doubleValue() - ((double) f7)), (int) (((Number) pair.getSecond()).doubleValue() - ((double) f9)));
        }
    }

    public b0(@NotNull String str, float f2, float f3, float f4, float f5, @Nullable Float f6, @Nullable Float f7, float f8, @NotNull String str2, @NotNull a0 a0Var, @Nullable Long l2, @Nullable Long l3, @Nullable b bVar) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(str2, "layerId");
        Intrinsics.checkNotNullParameter(a0Var, "storylyLayer");
        this.f3606a = str;
        this.f3607b = f2;
        this.f3608c = f3;
        this.f3609d = f4;
        this.f3610e = f5;
        this.f3611f = f6;
        this.f3612g = f7;
        this.f3613h = f8;
        this.f3614i = str2;
        this.f3615j = a0Var;
        this.f3616k = l2;
        this.f3617l = l3;
        this.f3618m = bVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: com.appsamurai.storyly.data.x} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: com.appsamurai.storyly.data.x} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: com.appsamurai.storyly.data.x} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: com.appsamurai.storyly.data.o0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: com.appsamurai.storyly.data.x} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: com.appsamurai.storyly.data.x} */
    /* JADX WARNING: type inference failed for: r0v46 */
    /* JADX WARNING: type inference failed for: r0v49 */
    /* JADX WARNING: type inference failed for: r0v50 */
    /* JADX WARNING: type inference failed for: r0v51 */
    /* JADX WARNING: type inference failed for: r0v52 */
    /* JADX WARNING: type inference failed for: r0v53 */
    /* JADX WARNING: type inference failed for: r3v41, types: [com.appsamurai.storyly.data.j0] */
    /* JADX WARNING: type inference failed for: r0v54 */
    /* JADX WARNING: type inference failed for: r0v55 */
    /* JADX WARNING: type inference failed for: r0v56 */
    /* JADX WARNING: type inference failed for: r0v57 */
    /* JADX WARNING: type inference failed for: r3v42, types: [com.appsamurai.storyly.data.k0] */
    /* JADX WARNING: type inference failed for: r3v43, types: [com.appsamurai.storyly.data.r] */
    /* JADX WARNING: type inference failed for: r2v21, types: [com.appsamurai.storyly.data.e0] */
    /* JADX WARNING: type inference failed for: r2v22, types: [com.appsamurai.storyly.data.t] */
    /* JADX WARNING: type inference failed for: r31v7, types: [com.appsamurai.storyly.data.p] */
    /* JADX WARNING: type inference failed for: r12v15, types: [com.appsamurai.storyly.data.n0] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x04d0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x04d2  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.appsamurai.storyly.data.b0 a() {
        /*
            r69 = this;
            r0 = r69
            com.appsamurai.storyly.data.a0 r1 = r0.f3615j
            boolean r2 = r1 instanceof com.appsamurai.storyly.data.n0
            java.lang.String r4 = "buttonText"
            java.lang.String r5 = "totalText"
            java.lang.String r6 = "checkoutButtonText"
            java.lang.String r7 = "successMessage"
            java.lang.String r8 = "successButtonBackText"
            java.lang.String r9 = "successButtonCartText"
            java.lang.String r10 = "purchaseButtonText"
            java.lang.String r11 = "textColor"
            if (r2 == 0) goto L_0x0099
            com.appsamurai.storyly.data.n0 r1 = (com.appsamurai.storyly.data.n0) r1
            java.lang.String r2 = r1.f4059a
            r13 = r2
            com.appsamurai.storyly.data.c r12 = r1.f4060b
            r14 = r12
            com.appsamurai.storyly.data.c r15 = r1.f4061c
            r30 = r15
            com.appsamurai.storyly.data.c r3 = r1.f4062d
            r16 = r3
            java.lang.String r3 = r1.f4063e
            r17 = r3
            boolean r3 = r1.f4064f
            r18 = r3
            boolean r3 = r1.f4065g
            r19 = r3
            com.appsamurai.storyly.data.m r3 = r1.f4066h
            r20 = r3
            boolean r3 = r1.f4067i
            r21 = r3
            boolean r3 = r1.f4068j
            r22 = r3
            java.lang.String r3 = r1.f4069k
            r23 = r3
            java.lang.String r0 = r1.f4070l
            r24 = r0
            r31 = r13
            java.lang.String r13 = r1.f4071m
            r25 = r13
            r32 = r14
            java.lang.String r14 = r1.f4072n
            r26 = r14
            r33 = r15
            java.lang.String r15 = r1.f4073o
            r27 = r15
            r34 = r5
            java.lang.String r5 = r1.f4074p
            r28 = r5
            int r1 = r1.f4075q
            r29 = r1
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r11)
            java.lang.String r1 = "iconColor"
            r2 = r30
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r9)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r6)
            r0 = r34
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.appsamurai.storyly.data.n0 r0 = new com.appsamurai.storyly.data.n0
            r12 = r0
            r13 = r31
            r14 = r32
            r15 = r33
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
        L_0x0096:
            r11 = r0
            goto L_0x04ce
        L_0x0099:
            r0 = r5
            boolean r2 = r1 instanceof com.appsamurai.storyly.data.p
            if (r2 == 0) goto L_0x012b
            com.appsamurai.storyly.data.p r1 = (com.appsamurai.storyly.data.p) r1
            java.lang.String r2 = r1.f4094a
            r32 = r2
            int r3 = r1.f4095b
            r33 = r3
            com.appsamurai.storyly.data.c r3 = r1.f4096c
            r34 = r3
            int r5 = r1.f4097d
            r35 = r5
            com.appsamurai.storyly.data.c r5 = r1.f4098e
            r36 = r5
            com.appsamurai.storyly.data.c r12 = r1.f4099f
            r37 = r12
            int r13 = r1.f4100g
            r38 = r13
            int r13 = r1.f4101h
            r39 = r13
            java.lang.String r13 = r1.f4102i
            r40 = r13
            boolean r13 = r1.f4103j
            r41 = r13
            boolean r13 = r1.f4104k
            r42 = r13
            com.appsamurai.storyly.data.m r13 = r1.f4105l
            r43 = r13
            boolean r13 = r1.f4106m
            r44 = r13
            boolean r13 = r1.f4107n
            r45 = r13
            java.lang.String r13 = r1.f4108o
            r46 = r13
            java.lang.String r14 = r1.f4109p
            r47 = r14
            java.lang.String r15 = r1.f4110q
            r48 = r15
            r16 = r0
            java.lang.String r0 = r1.f4111r
            r49 = r0
            r17 = r6
            java.lang.String r6 = r1.f4112s
            r50 = r6
            r18 = r6
            java.lang.String r6 = r1.f4113t
            r51 = r6
            int r1 = r1.f4114u
            r52 = r1
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r11)
            java.lang.String r1 = "backgroundColor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)
            java.lang.String r1 = "borderColor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r9)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r7)
            r0 = r17
            r1 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r2 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r2)
            com.appsamurai.storyly.data.p r0 = new com.appsamurai.storyly.data.p
            r31 = r0
            r31.<init>(r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52)
            goto L_0x0096
        L_0x012b:
            r2 = r0
            r0 = r6
            boolean r3 = r1 instanceof com.appsamurai.storyly.data.o0
            if (r3 == 0) goto L_0x0171
            com.appsamurai.storyly.data.o0 r1 = (com.appsamurai.storyly.data.o0) r1
            java.lang.String r13 = r1.f4081a
            java.lang.Float r14 = r1.f4082b
            java.lang.Float r15 = r1.f4083c
            java.lang.Integer r0 = r1.f4084d
            com.appsamurai.storyly.data.c r2 = r1.f4085e
            int r3 = r1.f4086f
            int r4 = r1.f4087g
            int r5 = r1.f4088h
            com.appsamurai.storyly.data.c r6 = r1.f4089i
            java.lang.String r7 = r1.f4090j
            com.appsamurai.storyly.util.font.h r1 = r1.f4091k
            java.lang.String r8 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r11)
            java.lang.String r8 = "textSpanColor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r8)
            com.appsamurai.storyly.data.o0 r8 = new com.appsamurai.storyly.data.o0
            r12 = r8
            r16 = r0
            r17 = r2
            r18 = r3
            r19 = r4
            r20 = r5
            r21 = r6
            r22 = r7
            r23 = r1
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            r11 = r8
            goto L_0x04ce
        L_0x0171:
            boolean r3 = r1 instanceof com.appsamurai.storyly.data.t
            if (r3 == 0) goto L_0x0196
            com.appsamurai.storyly.data.t r1 = (com.appsamurai.storyly.data.t) r1
            java.util.List<java.lang.String> r3 = r1.f4202a
            java.lang.String r4 = r1.f4203b
            com.appsamurai.storyly.data.c r5 = r1.f4204c
            com.appsamurai.storyly.data.c r6 = r1.f4205d
            com.appsamurai.storyly.data.c r7 = r1.f4206e
            com.appsamurai.storyly.data.c r8 = r1.f4207f
            java.lang.String r9 = r1.f4208g
            java.util.Map<java.lang.String, java.lang.Integer> r10 = r1.f4209h
            boolean r11 = r1.f4210i
            java.lang.String r0 = "emojiCodes"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.appsamurai.storyly.data.t r0 = new com.appsamurai.storyly.data.t
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            goto L_0x0096
        L_0x0196:
            boolean r3 = r1 instanceof com.appsamurai.storyly.data.x
            if (r3 == 0) goto L_0x01fe
            com.appsamurai.storyly.data.x r1 = (com.appsamurai.storyly.data.x) r1
            int r12 = r1.f4252a
            java.lang.String r13 = r1.f4253b
            java.lang.String r14 = r1.f4254c
            com.appsamurai.storyly.data.c r15 = r1.f4255d
            java.util.List<com.appsamurai.storyly.data.c> r3 = r1.f4256e
            r16 = r3
            float r3 = r1.f4257f
            r17 = r3
            java.lang.String r3 = r1.f4258g
            r18 = r3
            boolean r3 = r1.f4259h
            r19 = r3
            java.lang.String r3 = r1.f4260i
            r20 = r3
            com.appsamurai.storyly.data.m r3 = r1.f4261j
            r21 = r3
            boolean r3 = r1.f4262k
            r22 = r3
            boolean r3 = r1.f4263l
            r23 = r3
            java.lang.String r3 = r1.f4264m
            r24 = r3
            java.lang.String r4 = r1.f4265n
            r25 = r4
            java.lang.String r5 = r1.f4266o
            r26 = r5
            java.lang.String r6 = r1.f4267p
            r27 = r6
            java.lang.String r11 = r1.f4268q
            r28 = r11
            r31 = r12
            java.lang.String r12 = r1.f4269r
            r29 = r12
            int r1 = r1.f4270s
            r30 = r1
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r9)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r2)
            com.appsamurai.storyly.data.x r0 = new com.appsamurai.storyly.data.x
            r11 = r0
            r12 = r31
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)
            goto L_0x04ce
        L_0x01fe:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.e0
            if (r0 == 0) goto L_0x0274
            com.appsamurai.storyly.data.e0 r1 = (com.appsamurai.storyly.data.e0) r1
            int r3 = r1.f3646a
            int r4 = r1.f3647b
            java.lang.String r0 = r1.f3648c
            r5 = r0
            java.lang.String r2 = r1.f3649d
            r6 = r2
            java.lang.String r15 = r1.f3650e
            r7 = r15
            float r8 = r1.f3651f
            java.lang.String r9 = r1.f3652g
            int r10 = r1.f3653h
            boolean r11 = r1.f3654i
            com.appsamurai.storyly.data.c r12 = r1.f3655j
            com.appsamurai.storyly.data.c r13 = r1.f3656k
            com.appsamurai.storyly.data.c r14 = r1.f3657l
            r16 = r15
            com.appsamurai.storyly.data.c r15 = r1.f3658m
            r28 = r3
            r3 = r16
            r29 = r4
            com.appsamurai.storyly.data.c r4 = r1.f3659n
            r16 = r4
            com.appsamurai.storyly.data.c r4 = r1.f3660o
            r17 = r4
            com.appsamurai.storyly.data.c r4 = r1.f3661p
            r18 = r4
            com.appsamurai.storyly.data.c r4 = r1.f3662q
            r19 = r4
            com.appsamurai.storyly.data.c r4 = r1.f3663r
            r20 = r4
            com.appsamurai.storyly.data.c r4 = r1.f3664s
            r21 = r4
            boolean r4 = r1.f3665t
            r22 = r4
            boolean r4 = r1.f3666u
            r23 = r4
            boolean r4 = r1.f3667v
            r24 = r4
            boolean r4 = r1.f3668w
            r25 = r4
            boolean r4 = r1.f3669x
            r26 = r4
            java.lang.String r1 = r1.f3670y
            r27 = r1
            java.lang.String r1 = "leftOptionText"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r0 = "rightOptionText"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "pollText"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.appsamurai.storyly.data.e0 r0 = new com.appsamurai.storyly.data.e0
            r2 = r0
            r3 = r28
            r4 = r29
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            goto L_0x0096
        L_0x0274:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.r
            java.lang.String r2 = "theme"
            if (r0 == 0) goto L_0x02ba
            com.appsamurai.storyly.data.r r1 = (com.appsamurai.storyly.data.r) r1
            java.lang.String r0 = r1.f4169a
            r4 = r0
            java.lang.String r3 = r1.f4170b
            r5 = r3
            long r6 = r1.f4171c
            java.lang.Long r8 = r1.f4172d
            java.lang.String r9 = r1.f4173e
            java.lang.String r10 = r1.f4174f
            float r11 = r1.f4175g
            boolean r12 = r1.f4176h
            com.appsamurai.storyly.data.c r13 = r1.f4177i
            com.appsamurai.storyly.data.c r14 = r1.f4178j
            com.appsamurai.storyly.data.c r15 = r1.f4179k
            r20 = r4
            com.appsamurai.storyly.data.c r4 = r1.f4180l
            r16 = r4
            com.appsamurai.storyly.data.c r4 = r1.f4181m
            r17 = r4
            boolean r4 = r1.f4182n
            r18 = r4
            boolean r1 = r1.f4183o
            r19 = r1
            java.lang.String r1 = "title"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            com.appsamurai.storyly.data.r r0 = new com.appsamurai.storyly.data.r
            r3 = r0
            r4 = r20
            r3.<init>(r4, r5, r6, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            goto L_0x0096
        L_0x02ba:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.k0
            if (r0 == 0) goto L_0x0330
            com.appsamurai.storyly.data.k0 r1 = (com.appsamurai.storyly.data.k0) r1
            java.lang.String r0 = r1.f3807a
            r4 = r0
            float r5 = r1.f3808b
            java.lang.String r3 = r1.f3809c
            r6 = r3
            java.util.List<java.lang.String> r15 = r1.f3810d
            r7 = r15
            java.util.List<java.lang.Integer> r8 = r1.f3811e
            java.lang.Integer r9 = r1.f3812f
            int r10 = r1.f3813g
            boolean r11 = r1.f3814h
            com.appsamurai.storyly.data.c r12 = r1.f3815i
            com.appsamurai.storyly.data.c r13 = r1.f3816j
            com.appsamurai.storyly.data.c r14 = r1.f3817k
            r16 = r15
            com.appsamurai.storyly.data.c r15 = r1.f3818l
            r29 = r4
            r4 = r16
            r30 = r5
            com.appsamurai.storyly.data.c r5 = r1.f3819m
            r16 = r5
            com.appsamurai.storyly.data.c r5 = r1.f3820n
            r17 = r5
            com.appsamurai.storyly.data.c r5 = r1.f3821o
            r18 = r5
            com.appsamurai.storyly.data.c r5 = r1.f3822p
            r19 = r5
            com.appsamurai.storyly.data.c r5 = r1.f3823q
            r20 = r5
            com.appsamurai.storyly.data.c r5 = r1.f3824r
            r21 = r5
            com.appsamurai.storyly.data.c r5 = r1.f3825s
            r22 = r5
            boolean r5 = r1.f3826t
            r23 = r5
            boolean r5 = r1.f3827u
            r24 = r5
            boolean r5 = r1.f3828v
            r25 = r5
            boolean r5 = r1.f3829w
            r26 = r5
            boolean r5 = r1.f3830x
            r27 = r5
            java.lang.String r1 = r1.f3831y
            r28 = r1
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            java.lang.String r0 = "quizText"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "quizOptionTexts"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.appsamurai.storyly.data.k0 r0 = new com.appsamurai.storyly.data.k0
            r3 = r0
            r4 = r29
            r5 = r30
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            goto L_0x0096
        L_0x0330:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.l0
            if (r0 == 0) goto L_0x0354
            r3 = r1
            com.appsamurai.storyly.data.l0 r3 = (com.appsamurai.storyly.data.l0) r3
            r19 = 0
            r20 = 65535(0xffff, float:9.1834E-41)
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            com.appsamurai.storyly.data.l0 r0 = com.appsamurai.storyly.data.l0.a(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            goto L_0x0096
        L_0x0354:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.i0
            if (r0 == 0) goto L_0x03ac
            r31 = r1
            com.appsamurai.storyly.data.i0 r31 = (com.appsamurai.storyly.data.i0) r31
            r67 = -1
            r68 = 7
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r44 = 0
            r45 = 0
            r46 = 0
            r47 = 0
            r48 = 0
            r49 = 0
            r50 = 0
            r51 = 0
            r52 = 0
            r53 = 0
            r54 = 0
            r55 = 0
            r56 = 0
            r57 = 0
            r58 = 0
            r59 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r63 = 0
            r64 = 0
            r65 = 0
            r66 = 0
            com.appsamurai.storyly.data.i0 r0 = com.appsamurai.storyly.data.i0.a(r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68)
            goto L_0x0096
        L_0x03ac:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.f0
            if (r0 == 0) goto L_0x03fb
            r31 = r1
            com.appsamurai.storyly.data.f0 r31 = (com.appsamurai.storyly.data.f0) r31
            r62 = 0
            r63 = 2147483647(0x7fffffff, float:NaN)
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r44 = 0
            r45 = 0
            r46 = 0
            r47 = 0
            r48 = 0
            r49 = 0
            r50 = 0
            r51 = 0
            r52 = 0
            r53 = 0
            r54 = 0
            r55 = 0
            r56 = 0
            r57 = 0
            r58 = 0
            r59 = 0
            r60 = 0
            r61 = 0
            com.appsamurai.storyly.data.f0 r0 = com.appsamurai.storyly.data.f0.a(r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63)
            goto L_0x0096
        L_0x03fb:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.h0
            if (r0 == 0) goto L_0x0421
            r3 = r1
            com.appsamurai.storyly.data.h0 r3 = (com.appsamurai.storyly.data.h0) r3
            r20 = 0
            r21 = 131071(0x1ffff, float:1.8367E-40)
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            com.appsamurai.storyly.data.h0 r0 = com.appsamurai.storyly.data.h0.a(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            goto L_0x0096
        L_0x0421:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.j0
            if (r0 == 0) goto L_0x0447
            com.appsamurai.storyly.data.j0 r1 = (com.appsamurai.storyly.data.j0) r1
            java.lang.String r4 = r1.f3793a
            java.lang.String r5 = r1.f3794b
            java.lang.Float r6 = r1.f3795c
            com.appsamurai.storyly.data.c r7 = r1.f3796d
            com.appsamurai.storyly.data.c r8 = r1.f3797e
            com.appsamurai.storyly.data.c r9 = r1.f3798f
            boolean r10 = r1.f3799g
            boolean r11 = r1.f3800h
            java.lang.String r0 = "promoCode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r2)
            com.appsamurai.storyly.data.j0 r0 = new com.appsamurai.storyly.data.j0
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            goto L_0x0096
        L_0x0447:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.q
            if (r0 == 0) goto L_0x0474
            r2 = r1
            com.appsamurai.storyly.data.q r2 = (com.appsamurai.storyly.data.q) r2
            r23 = 0
            r24 = 2097151(0x1fffff, float:2.938734E-39)
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            com.appsamurai.storyly.data.q r0 = com.appsamurai.storyly.data.q.a(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            goto L_0x0096
        L_0x0474:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.q0
            if (r0 == 0) goto L_0x0488
            r2 = r1
            com.appsamurai.storyly.data.q0 r2 = (com.appsamurai.storyly.data.q0) r2
            r7 = 0
            r8 = 31
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            com.appsamurai.storyly.data.q0 r0 = com.appsamurai.storyly.data.q0.a(r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0096
        L_0x0488:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.o
            if (r0 == 0) goto L_0x0493
            com.appsamurai.storyly.data.o r0 = new com.appsamurai.storyly.data.o
            r0.<init>()
            goto L_0x0096
        L_0x0493:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.c0
            if (r0 == 0) goto L_0x04a6
            r2 = r1
            com.appsamurai.storyly.data.c0 r2 = (com.appsamurai.storyly.data.c0) r2
            r6 = 0
            r7 = 15
            r3 = 0
            r4 = 0
            r5 = 0
            com.appsamurai.storyly.data.c0 r0 = com.appsamurai.storyly.data.c0.a(r2, r3, r4, r5, r6, r7)
            goto L_0x0096
        L_0x04a6:
            boolean r0 = r1 instanceof com.appsamurai.storyly.data.y
            if (r0 == 0) goto L_0x04cd
            r2 = r1
            com.appsamurai.storyly.data.y r2 = (com.appsamurai.storyly.data.y) r2
            r20 = 0
            r21 = 262143(0x3ffff, float:3.6734E-40)
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            com.appsamurai.storyly.data.y r0 = com.appsamurai.storyly.data.y.a(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            goto L_0x0096
        L_0x04cd:
            r11 = 0
        L_0x04ce:
            if (r11 != 0) goto L_0x04d2
            r0 = 0
            return r0
        L_0x04d2:
            com.appsamurai.storyly.data.b0 r0 = new com.appsamurai.storyly.data.b0
            r1 = r69
            java.lang.String r2 = r1.f3606a
            float r3 = r1.f3607b
            float r4 = r1.f3608c
            float r5 = r1.f3609d
            float r6 = r1.f3610e
            java.lang.Float r7 = r1.f3611f
            java.lang.Float r8 = r1.f3612g
            float r9 = r1.f3613h
            java.lang.String r10 = r1.f3614i
            java.lang.Long r12 = r1.f3616k
            java.lang.Long r13 = r1.f3617l
            com.appsamurai.storyly.data.b r14 = r1.f3618m
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.b0.a():com.appsamurai.storyly.data.b0");
    }

    @NotNull
    public final Point b() {
        return (Point) this.f3619n.getValue();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b0)) {
            return false;
        }
        b0 b0Var = (b0) obj;
        return Intrinsics.areEqual((Object) this.f3606a, (Object) b0Var.f3606a) && Intrinsics.areEqual((Object) Float.valueOf(this.f3607b), (Object) Float.valueOf(b0Var.f3607b)) && Intrinsics.areEqual((Object) Float.valueOf(this.f3608c), (Object) Float.valueOf(b0Var.f3608c)) && Intrinsics.areEqual((Object) Float.valueOf(this.f3609d), (Object) Float.valueOf(b0Var.f3609d)) && Intrinsics.areEqual((Object) Float.valueOf(this.f3610e), (Object) Float.valueOf(b0Var.f3610e)) && Intrinsics.areEqual((Object) this.f3611f, (Object) b0Var.f3611f) && Intrinsics.areEqual((Object) this.f3612g, (Object) b0Var.f3612g) && Intrinsics.areEqual((Object) Float.valueOf(this.f3613h), (Object) Float.valueOf(b0Var.f3613h)) && Intrinsics.areEqual((Object) this.f3614i, (Object) b0Var.f3614i) && Intrinsics.areEqual((Object) this.f3615j, (Object) b0Var.f3615j) && Intrinsics.areEqual((Object) this.f3616k, (Object) b0Var.f3616k) && Intrinsics.areEqual((Object) this.f3617l, (Object) b0Var.f3617l) && Intrinsics.areEqual((Object) this.f3618m, (Object) b0Var.f3618m);
    }

    public int hashCode() {
        int c3 = android.support.v4.media.session.a.c(this.f3610e, android.support.v4.media.session.a.c(this.f3609d, android.support.v4.media.session.a.c(this.f3608c, android.support.v4.media.session.a.c(this.f3607b, this.f3606a.hashCode() * 31, 31), 31), 31), 31);
        Float f2 = this.f3611f;
        int i3 = 0;
        int hashCode = (c3 + (f2 == null ? 0 : f2.hashCode())) * 31;
        Float f3 = this.f3612g;
        int hashCode2 = (this.f3615j.hashCode() + androidx.compose.animation.core.a.i(this.f3614i, android.support.v4.media.session.a.c(this.f3613h, (hashCode + (f3 == null ? 0 : f3.hashCode())) * 31, 31), 31)) * 31;
        Long l2 = this.f3616k;
        int hashCode3 = (hashCode2 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.f3617l;
        int hashCode4 = (hashCode3 + (l3 == null ? 0 : l3.hashCode())) * 31;
        b bVar = this.f3618m;
        if (bVar != null) {
            i3 = bVar.hashCode();
        }
        return hashCode4 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyLayerItem(type=" + this.f3606a + ", x=" + this.f3607b + ", y=" + this.f3608c + ", w=" + this.f3609d + ", h=" + this.f3610e + ", centerX=" + this.f3611f + ", centerY=" + this.f3612g + ", rotation=" + this.f3613h + ", layerId=" + this.f3614i + ", storylyLayer=" + this.f3615j + ", startTime=" + this.f3616k + ", endTime=" + this.f3617l + ", animationScheme=" + this.f3618m + ')';
    }
}
