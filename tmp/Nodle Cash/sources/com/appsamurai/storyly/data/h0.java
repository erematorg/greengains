package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
import com.appsamurai.storyly.data.c;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class h0 extends g0 {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final c f3726a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final c f3727b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final c f3728c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final c f3729d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final c f3730e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final String f3731f;

    /* renamed from: g  reason: collision with root package name */
    public final boolean f3732g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public m f3733h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f3734i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f3735j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public String f3736k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public String f3737l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public String f3738m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public String f3739n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public String f3740o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public String f3741p;

    /* renamed from: q  reason: collision with root package name */
    public int f3742q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public List<STRProductItem> f3743r;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<h0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3744a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3745b;

        static {
            a aVar = new a();
            f3744a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyProductListLayer", aVar, 18);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_color", true);
            pluginGeneratedSerialDescriptor.addElement("text_color", true);
            pluginGeneratedSerialDescriptor.addElement("button_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("old_price_color", true);
            pluginGeneratedSerialDescriptor.addElement("c_b_text", true);
            pluginGeneratedSerialDescriptor.addElement("is_title_visible", true);
            pluginGeneratedSerialDescriptor.addElement("products", true);
            pluginGeneratedSerialDescriptor.addElement("is_s_price_visible", true);
            pluginGeneratedSerialDescriptor.addElement("is_price_visible", true);
            pluginGeneratedSerialDescriptor.addElement("p_b_text", true);
            pluginGeneratedSerialDescriptor.addElement("s_b_cart_text", true);
            pluginGeneratedSerialDescriptor.addElement("s_b_back_text", true);
            pluginGeneratedSerialDescriptor.addElement("s_message", true);
            pluginGeneratedSerialDescriptor.addElement("checkout_b_text", true);
            pluginGeneratedSerialDescriptor.addElement("t_text", true);
            pluginGeneratedSerialDescriptor.addElement("max_v", true);
            pluginGeneratedSerialDescriptor.addElement("selectedProduct", true);
            f3745b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            c.a aVar = c.f3622b;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable6 = BuiltinSerializersKt.getNullable(m.f3857b);
            KSerializer<?> nullable7 = BuiltinSerializersKt.getNullable(new ArrayListSerializer(STRProductItem.a.f4034a));
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{nullable, nullable2, nullable3, nullable4, nullable5, stringSerializer, booleanSerializer, nullable6, booleanSerializer, booleanSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, IntSerializer.INSTANCE, nullable7};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x00f5, code lost:
            r15 = 4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x012d, code lost:
            r8 = r24;
            r9 = r25;
            r3 = r34;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0136, code lost:
            r34 = r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0153, code lost:
            r8 = r24;
            r9 = r25;
            r3 = r34;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x017f, code lost:
            r8 = r24;
            r9 = r25;
            r3 = r34;
            r34 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x024c, code lost:
            r7 = r7 | r34;
            r34 = r3;
            r24 = r8;
            r25 = r9;
            r3 = 8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0256, code lost:
            r8 = 11;
            r9 = 10;
            r10 = 9;
            r11 = 7;
            r12 = 6;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r42) {
            /*
                r41 = this;
                r0 = r42
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3745b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r8 = 11
                r9 = 10
                r10 = 9
                r11 = 7
                r12 = 6
                r13 = 5
                r14 = 3
                r3 = 8
                r15 = 4
                r4 = 2
                r5 = 1
                r6 = 0
                r7 = 0
                if (r2 == 0) goto L_0x00af
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r2, r7)
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r2, r7)
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r2, r7)
                java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r14, r2, r7)
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r15, r2, r7)
                java.lang.String r13 = r0.decodeStringElement(r1, r13)
                boolean r12 = r0.decodeBooleanElement(r1, r12)
                com.appsamurai.storyly.data.m$a r15 = com.appsamurai.storyly.data.m.f3857b
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r11, r15, r7)
                boolean r3 = r0.decodeBooleanElement(r1, r3)
                boolean r10 = r0.decodeBooleanElement(r1, r10)
                java.lang.String r9 = r0.decodeStringElement(r1, r9)
                java.lang.String r8 = r0.decodeStringElement(r1, r8)
                r15 = 12
                java.lang.String r15 = r0.decodeStringElement(r1, r15)
                r7 = 13
                java.lang.String r7 = r0.decodeStringElement(r1, r7)
                r18 = r2
                r2 = 14
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r17 = r2
                r2 = 15
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r16 = r2
                r2 = 16
                int r2 = r0.decodeIntElement(r1, r2)
                r42 = r2
                kotlinx.serialization.internal.ArrayListSerializer r2 = new kotlinx.serialization.internal.ArrayListSerializer
                r19 = r3
                com.appsamurai.storyly.data.managers.product.STRProductItem$a r3 = com.appsamurai.storyly.data.managers.product.STRProductItem.a.f4034a
                r2.<init>(r3)
                r41 = r6
                r3 = 17
                r6 = 0
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r3, r2, r6)
                r3 = 262143(0x3ffff, float:3.6734E-40)
                r6 = r41
                r37 = r42
                r20 = r3
                r34 = r7
                r32 = r8
                r31 = r9
                r30 = r10
                r27 = r12
                r26 = r13
                r33 = r15
                r36 = r16
                r35 = r17
                r29 = r19
                goto L_0x0297
            L_0x00af:
                r2 = r6
                r6 = r7
                r7 = r2
                r20 = r7
                r35 = r20
                r36 = r35
                r37 = r5
                r4 = r6
                r5 = r4
                r24 = r5
                r25 = r24
                r26 = r25
                r27 = r26
                r28 = r27
                r29 = r28
                r30 = r29
                r31 = r30
                r32 = r31
                r33 = r32
                r34 = r33
                r2 = r34
                r6 = r36
            L_0x00d6:
                if (r37 == 0) goto L_0x0270
                int r14 = r0.decodeElementIndex(r1)
                switch(r14) {
                    case -1: goto L_0x0260;
                    case 0: goto L_0x0232;
                    case 1: goto L_0x0218;
                    case 2: goto L_0x0201;
                    case 3: goto L_0x01ea;
                    case 4: goto L_0x01d3;
                    case 5: goto L_0x01c6;
                    case 6: goto L_0x01af;
                    case 7: goto L_0x0195;
                    case 8: goto L_0x018a;
                    case 9: goto L_0x0175;
                    case 10: goto L_0x0168;
                    case 11: goto L_0x015b;
                    case 12: goto L_0x0147;
                    case 13: goto L_0x013a;
                    case 14: goto L_0x0121;
                    case 15: goto L_0x010f;
                    case 16: goto L_0x00f8;
                    case 17: goto L_0x00e5;
                    default: goto L_0x00df;
                }
            L_0x00df:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r14)
                throw r0
            L_0x00e5:
                kotlinx.serialization.internal.ArrayListSerializer r14 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.managers.product.STRProductItem$a r15 = com.appsamurai.storyly.data.managers.product.STRProductItem.a.f4034a
                r14.<init>(r15)
                r15 = 17
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r15, r14, r2)
                r14 = 131072(0x20000, float:1.83671E-40)
                r7 = r7 | r14
            L_0x00f5:
                r14 = 3
                r15 = 4
                goto L_0x00d6
            L_0x00f8:
                r14 = 16
                r15 = 17
                int r6 = r0.decodeIntElement(r1, r14)
                r39 = 65536(0x10000, float:9.18355E-41)
                r8 = r24
                r9 = r25
                r3 = r34
                r34 = r39
            L_0x010a:
                r12 = 0
                r14 = 12
                goto L_0x024c
            L_0x010f:
                r14 = 15
                r15 = 17
                java.lang.String r16 = r0.decodeStringElement(r1, r14)
                r34 = 32768(0x8000, float:4.5918E-41)
                r3 = r16
                r8 = r24
                r9 = r25
                goto L_0x010a
            L_0x0121:
                r14 = 14
                r15 = 17
                java.lang.String r16 = r0.decodeStringElement(r1, r14)
                r17 = 16384(0x4000, float:2.2959E-41)
                r33 = r16
            L_0x012d:
                r8 = r24
                r9 = r25
                r3 = r34
                r12 = 0
                r14 = 12
            L_0x0136:
                r34 = r17
                goto L_0x024c
            L_0x013a:
                r14 = 13
                r15 = 17
                java.lang.String r16 = r0.decodeStringElement(r1, r14)
                r17 = 8192(0x2000, float:1.14794E-41)
                r32 = r16
                goto L_0x012d
            L_0x0147:
                r14 = 12
                r15 = 17
                java.lang.String r16 = r0.decodeStringElement(r1, r14)
                r17 = 4096(0x1000, float:5.74E-42)
                r31 = r16
            L_0x0153:
                r8 = r24
                r9 = r25
                r3 = r34
                r12 = 0
                goto L_0x0136
            L_0x015b:
                r14 = 12
                r15 = 17
                java.lang.String r16 = r0.decodeStringElement(r1, r8)
                r17 = 2048(0x800, float:2.87E-42)
                r30 = r16
                goto L_0x0153
            L_0x0168:
                r14 = 12
                r15 = 17
                java.lang.String r16 = r0.decodeStringElement(r1, r9)
                r17 = 1024(0x400, float:1.435E-42)
                r29 = r16
                goto L_0x0153
            L_0x0175:
                r14 = 12
                r15 = 17
                boolean r35 = r0.decodeBooleanElement(r1, r10)
                r16 = 512(0x200, float:7.175E-43)
            L_0x017f:
                r8 = r24
                r9 = r25
                r3 = r34
                r12 = 0
                r34 = r16
                goto L_0x024c
            L_0x018a:
                r14 = 12
                r15 = 17
                boolean r20 = r0.decodeBooleanElement(r1, r3)
                r16 = 256(0x100, float:3.59E-43)
                goto L_0x017f
            L_0x0195:
                r14 = 12
                r15 = 17
                com.appsamurai.storyly.data.m$a r3 = com.appsamurai.storyly.data.m.f3857b
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r11, r3, r5)
                r5 = 128(0x80, float:1.794E-43)
                r8 = r24
                r9 = r25
                r12 = 0
                r40 = r5
                r5 = r3
                r3 = r34
                r34 = r40
                goto L_0x024c
            L_0x01af:
                r14 = 12
                r15 = 17
                boolean r36 = r0.decodeBooleanElement(r1, r12)
                r3 = 64
                r8 = r24
                r9 = r25
                r12 = 0
                r40 = r34
                r34 = r3
                r3 = r40
                goto L_0x024c
            L_0x01c6:
                r14 = 12
                r15 = 17
                java.lang.String r3 = r0.decodeStringElement(r1, r13)
                r17 = 32
                r28 = r3
                goto L_0x0153
            L_0x01d3:
                r14 = 12
                r15 = 17
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r8 = 4
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r8, r3, r4)
                r4 = r3
                r8 = r24
                r9 = r25
                r3 = r34
                r12 = 0
                r34 = 16
                goto L_0x024c
            L_0x01ea:
                r8 = r15
                r14 = 12
                r15 = 17
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r8 = r24
                r9 = 3
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r9, r3, r8)
                r8 = r3
                r9 = r25
                r3 = r34
                r12 = 0
                r34 = 8
                goto L_0x024c
            L_0x0201:
                r8 = r24
                r9 = 3
                r14 = 12
                r15 = 17
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r9 = r25
                r10 = 2
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r10, r3, r9)
                r9 = r3
                r3 = r34
                r12 = 0
                r34 = 4
                goto L_0x024c
            L_0x0218:
                r8 = r24
                r9 = r25
                r10 = 2
                r14 = 12
                r15 = 17
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r10 = r26
                r11 = 1
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r11, r3, r10)
                r26 = r3
                r3 = r34
                r12 = 0
                r34 = 2
                goto L_0x024c
            L_0x0232:
                r8 = r24
                r9 = r25
                r10 = r26
                r11 = 1
                r14 = 12
                r15 = 17
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r11 = r27
                r12 = 0
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r12, r3, r11)
                r27 = r3
                r3 = r34
                r34 = 1
            L_0x024c:
                r7 = r7 | r34
                r34 = r3
                r24 = r8
                r25 = r9
                r3 = 8
            L_0x0256:
                r8 = 11
                r9 = 10
                r10 = 9
                r11 = 7
                r12 = 6
                goto L_0x00f5
            L_0x0260:
                r8 = r24
                r9 = r25
                r10 = r26
                r11 = r27
                r12 = 0
                r14 = 12
                r15 = 17
                r37 = r12
                goto L_0x0256
            L_0x0270:
                r8 = r24
                r9 = r25
                r10 = r26
                r11 = r27
                r18 = r4
                r37 = r6
                r14 = r8
                r4 = r9
                r6 = r11
                r26 = r28
                r27 = r36
                r11 = r5
                r5 = r10
                r36 = r34
                r34 = r32
                r32 = r30
                r30 = r35
                r35 = r33
                r33 = r31
                r31 = r29
                r29 = r20
                r20 = r7
            L_0x0297:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.h0 r0 = new com.appsamurai.storyly.data.h0
                r19 = r0
                r21 = r6
                com.appsamurai.storyly.data.c r21 = (com.appsamurai.storyly.data.c) r21
                r22 = r5
                com.appsamurai.storyly.data.c r22 = (com.appsamurai.storyly.data.c) r22
                r23 = r4
                com.appsamurai.storyly.data.c r23 = (com.appsamurai.storyly.data.c) r23
                r24 = r14
                com.appsamurai.storyly.data.c r24 = (com.appsamurai.storyly.data.c) r24
                r25 = r18
                com.appsamurai.storyly.data.c r25 = (com.appsamurai.storyly.data.c) r25
                r28 = r11
                com.appsamurai.storyly.data.m r28 = (com.appsamurai.storyly.data.m) r28
                r38 = r2
                java.util.List r38 = (java.util.List) r38
                r19.<init>(r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.h0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3745b;
        }

        public void serialize(Encoder encoder, Object obj) {
            h0 h0Var = (h0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(h0Var, "value");
            SerialDescriptor serialDescriptor = f3745b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(h0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || h0Var.f3726a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, c.f3622b, h0Var.f3726a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || h0Var.f3727b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, c.f3622b, h0Var.f3727b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || h0Var.f3728c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, c.f3622b, h0Var.f3728c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || h0Var.f3729d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, c.f3622b, h0Var.f3729d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || h0Var.f3730e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, c.f3622b, h0Var.f3730e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || !Intrinsics.areEqual((Object) h0Var.f3731f, (Object) "")) {
                beginStructure.encodeStringElement(serialDescriptor, 5, h0Var.f3731f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || !h0Var.f3732g) {
                beginStructure.encodeBooleanElement(serialDescriptor, 6, h0Var.f3732g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || h0Var.f3733h != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, m.f3857b, h0Var.f3733h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || !h0Var.f3734i) {
                beginStructure.encodeBooleanElement(serialDescriptor, 8, h0Var.f3734i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || !h0Var.f3735j) {
                beginStructure.encodeBooleanElement(serialDescriptor, 9, h0Var.f3735j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || !Intrinsics.areEqual((Object) h0Var.f3736k, (Object) "Add to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 10, h0Var.f3736k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || !Intrinsics.areEqual((Object) h0Var.f3737l, (Object) "Go to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 11, h0Var.f3737l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || !Intrinsics.areEqual((Object) h0Var.f3738m, (Object) "Continue with Stories")) {
                beginStructure.encodeStringElement(serialDescriptor, 12, h0Var.f3738m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || !Intrinsics.areEqual((Object) h0Var.f3739n, (Object) "Added to your Cart successfully")) {
                beginStructure.encodeStringElement(serialDescriptor, 13, h0Var.f3739n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || !Intrinsics.areEqual((Object) h0Var.f3740o, (Object) "Go to Checkout")) {
                beginStructure.encodeStringElement(serialDescriptor, 14, h0Var.f3740o);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 15) || !Intrinsics.areEqual((Object) h0Var.f3741p, (Object) "Total")) {
                beginStructure.encodeStringElement(serialDescriptor, 15, h0Var.f3741p);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 16) || h0Var.f3742q != 4) {
                beginStructure.encodeIntElement(serialDescriptor, 16, h0Var.f3742q);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 17) || h0Var.f3743r != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 17, new ArrayListSerializer(STRProductItem.a.f4034a), h0Var.f3743r);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public h0() {
        this((c) null, (c) null, (c) null, (c) null, (c) null, (String) null, false, (m) null, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 131071);
    }

    public static h0 a(h0 h0Var, c cVar, c cVar2, c cVar3, c cVar4, c cVar5, String str, boolean z2, m mVar, boolean z3, boolean z4, String str2, String str3, String str4, String str5, String str6, String str7, int i3, int i4) {
        h0 h0Var2 = h0Var;
        int i5 = i4;
        c cVar6 = (i5 & 1) != 0 ? h0Var2.f3726a : null;
        c cVar7 = (i5 & 2) != 0 ? h0Var2.f3727b : null;
        c cVar8 = (i5 & 4) != 0 ? h0Var2.f3728c : null;
        c cVar9 = (i5 & 8) != 0 ? h0Var2.f3729d : null;
        c cVar10 = (i5 & 16) != 0 ? h0Var2.f3730e : null;
        String str8 = (i5 & 32) != 0 ? h0Var2.f3731f : null;
        boolean z5 = (i5 & 64) != 0 ? h0Var2.f3732g : z2;
        m mVar2 = (i5 & 128) != 0 ? h0Var2.f3733h : null;
        boolean z6 = (i5 & 256) != 0 ? h0Var2.f3734i : z3;
        boolean z7 = (i5 & 512) != 0 ? h0Var2.f3735j : z4;
        String str9 = (i5 & 1024) != 0 ? h0Var2.f3736k : null;
        String str10 = (i5 & 2048) != 0 ? h0Var2.f3737l : null;
        String str11 = (i5 & 4096) != 0 ? h0Var2.f3738m : null;
        String str12 = (i5 & 8192) != 0 ? h0Var2.f3739n : null;
        boolean z8 = z7;
        String str13 = (i5 & 16384) != 0 ? h0Var2.f3740o : null;
        boolean z9 = z6;
        String str14 = (i5 & 32768) != 0 ? h0Var2.f3741p : null;
        int i6 = (i5 & 65536) != 0 ? h0Var2.f3742q : i3;
        h0Var.getClass();
        Intrinsics.checkNotNullParameter(str8, "catalogButtonText");
        Intrinsics.checkNotNullParameter(str9, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str10, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str11, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str12, "successMessage");
        Intrinsics.checkNotNullParameter(str13, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str14, "totalText");
        return new h0(cVar6, cVar7, cVar8, cVar9, cVar10, str8, z5, mVar2, z9, z8, str9, str10, str11, str12, str13, str14, i6);
    }

    @Nullable
    public m c() {
        return this.f3733h;
    }

    @NotNull
    public String d() {
        return this.f3736k;
    }

    @NotNull
    public String e() {
        return this.f3738m;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h0)) {
            return false;
        }
        h0 h0Var = (h0) obj;
        return Intrinsics.areEqual((Object) this.f3726a, (Object) h0Var.f3726a) && Intrinsics.areEqual((Object) this.f3727b, (Object) h0Var.f3727b) && Intrinsics.areEqual((Object) this.f3728c, (Object) h0Var.f3728c) && Intrinsics.areEqual((Object) this.f3729d, (Object) h0Var.f3729d) && Intrinsics.areEqual((Object) this.f3730e, (Object) h0Var.f3730e) && Intrinsics.areEqual((Object) this.f3731f, (Object) h0Var.f3731f) && this.f3732g == h0Var.f3732g && Intrinsics.areEqual((Object) this.f3733h, (Object) h0Var.f3733h) && this.f3734i == h0Var.f3734i && this.f3735j == h0Var.f3735j && Intrinsics.areEqual((Object) this.f3736k, (Object) h0Var.f3736k) && Intrinsics.areEqual((Object) this.f3737l, (Object) h0Var.f3737l) && Intrinsics.areEqual((Object) this.f3738m, (Object) h0Var.f3738m) && Intrinsics.areEqual((Object) this.f3739n, (Object) h0Var.f3739n) && Intrinsics.areEqual((Object) this.f3740o, (Object) h0Var.f3740o) && Intrinsics.areEqual((Object) this.f3741p, (Object) h0Var.f3741p) && this.f3742q == h0Var.f3742q;
    }

    @NotNull
    public String f() {
        return this.f3737l;
    }

    @NotNull
    public String g() {
        return this.f3739n;
    }

    @NotNull
    public String h() {
        return this.f3741p;
    }

    public int hashCode() {
        c cVar = this.f3726a;
        int i3 = 0;
        int hashCode = (cVar == null ? 0 : Integer.hashCode(cVar.f3624a)) * 31;
        c cVar2 = this.f3727b;
        int hashCode2 = (hashCode + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f3728c;
        int hashCode3 = (hashCode2 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f3729d;
        int hashCode4 = (hashCode3 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f3730e;
        int i4 = androidx.compose.animation.core.a.i(this.f3731f, (hashCode4 + (cVar5 == null ? 0 : Integer.hashCode(cVar5.f3624a))) * 31, 31);
        boolean z2 = this.f3732g;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i5 = (i4 + (z2 ? 1 : 0)) * 31;
        m mVar = this.f3733h;
        if (mVar != null) {
            i3 = mVar.hashCode();
        }
        int i6 = (i5 + i3) * 31;
        boolean z4 = this.f3734i;
        if (z4) {
            z4 = true;
        }
        int i7 = (i6 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f3735j;
        if (!z5) {
            z3 = z5;
        }
        return Integer.hashCode(this.f3742q) + androidx.compose.animation.core.a.i(this.f3741p, androidx.compose.animation.core.a.i(this.f3740o, androidx.compose.animation.core.a.i(this.f3739n, androidx.compose.animation.core.a.i(this.f3738m, androidx.compose.animation.core.a.i(this.f3737l, androidx.compose.animation.core.a.i(this.f3736k, (i7 + (z3 ? 1 : 0)) * 31, 31), 31), 31), 31), 31), 31);
    }

    public boolean i() {
        return this.f3735j;
    }

    public boolean j() {
        return this.f3734i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyProductListLayer(bgColor=");
        sb.append(this.f3726a);
        sb.append(", borderColor=");
        sb.append(this.f3727b);
        sb.append(", textColor=");
        sb.append(this.f3728c);
        sb.append(", buttonBgColor=");
        sb.append(this.f3729d);
        sb.append(", oldPriceColor=");
        sb.append(this.f3730e);
        sb.append(", catalogButtonText=");
        sb.append(this.f3731f);
        sb.append(", isProductTitleVisible=");
        sb.append(this.f3732g);
        sb.append(", productData=");
        sb.append(this.f3733h);
        sb.append(", isProductSalesPriceVisible=");
        sb.append(this.f3734i);
        sb.append(", isProductPriceVisible=");
        sb.append(this.f3735j);
        sb.append(", purchaseButtonText=");
        sb.append(this.f3736k);
        sb.append(", successButtonCartText=");
        sb.append(this.f3737l);
        sb.append(", successButtonBackText=");
        sb.append(this.f3738m);
        sb.append(", successMessage=");
        sb.append(this.f3739n);
        sb.append(", checkoutButtonText=");
        sb.append(this.f3740o);
        sb.append(", totalText=");
        sb.append(this.f3741p);
        sb.append(", maxVariantCount=");
        return android.support.v4.media.session.a.p(sb, this.f3742q, ')');
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ h0(int i3, @SerialName("bg_color") c cVar, @SerialName("border_color") c cVar2, @SerialName("text_color") c cVar3, @SerialName("button_bg_color") c cVar4, @SerialName("old_price_color") c cVar5, @SerialName("c_b_text") String str, @SerialName("is_title_visible") boolean z2, @SerialName("products") m mVar, @SerialName("is_s_price_visible") boolean z3, @SerialName("is_price_visible") boolean z4, @SerialName("p_b_text") String str2, @SerialName("s_b_cart_text") String str3, @SerialName("s_b_back_text") String str4, @SerialName("s_message") String str5, @SerialName("checkout_b_text") String str6, @SerialName("t_text") String str7, @SerialName("max_v") int i4, List list) {
        int i5 = i3;
        if ((i5 & 1) == 0) {
            this.f3726a = null;
        } else {
            this.f3726a = cVar;
        }
        if ((i5 & 2) == 0) {
            this.f3727b = null;
        } else {
            this.f3727b = cVar2;
        }
        if ((i5 & 4) == 0) {
            this.f3728c = null;
        } else {
            this.f3728c = cVar3;
        }
        if ((i5 & 8) == 0) {
            this.f3729d = null;
        } else {
            this.f3729d = cVar4;
        }
        if ((i5 & 16) == 0) {
            this.f3730e = null;
        } else {
            this.f3730e = cVar5;
        }
        this.f3731f = (i5 & 32) == 0 ? "" : str;
        if ((i5 & 64) == 0) {
            this.f3732g = true;
        } else {
            this.f3732g = z2;
        }
        if ((i5 & 128) == 0) {
            this.f3733h = null;
        } else {
            this.f3733h = mVar;
        }
        if ((i5 & 256) == 0) {
            this.f3734i = true;
        } else {
            this.f3734i = z3;
        }
        if ((i5 & 512) == 0) {
            this.f3735j = true;
        } else {
            this.f3735j = z4;
        }
        this.f3736k = (i5 & 1024) == 0 ? "Add to Cart" : str2;
        this.f3737l = (i5 & 2048) == 0 ? "Go to Cart" : str3;
        this.f3738m = (i5 & 4096) == 0 ? "Continue with Stories" : str4;
        this.f3739n = (i5 & 8192) == 0 ? "Added to your Cart successfully" : str5;
        this.f3740o = (i5 & 16384) == 0 ? "Go to Checkout" : str6;
        this.f3741p = (32768 & i5) == 0 ? "Total" : str7;
        this.f3742q = (65536 & i5) == 0 ? 4 : i4;
        if ((i5 & 131072) == 0) {
            this.f3743r = null;
        } else {
            this.f3743r = list;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ h0(com.appsamurai.storyly.data.c r13, com.appsamurai.storyly.data.c r14, com.appsamurai.storyly.data.c r15, com.appsamurai.storyly.data.c r16, com.appsamurai.storyly.data.c r17, java.lang.String r18, boolean r19, com.appsamurai.storyly.data.m r20, boolean r21, boolean r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, int r29, int r30) {
        /*
            r12 = this;
            r0 = r30
            r1 = r0 & 32
            r2 = 0
            if (r1 == 0) goto L_0x000a
            java.lang.String r1 = ""
            goto L_0x000b
        L_0x000a:
            r1 = r2
        L_0x000b:
            r3 = r0 & 64
            r4 = 1
            if (r3 == 0) goto L_0x0012
            r3 = r4
            goto L_0x0014
        L_0x0012:
            r3 = r19
        L_0x0014:
            r5 = r0 & 256(0x100, float:3.59E-43)
            if (r5 == 0) goto L_0x001a
            r5 = r4
            goto L_0x001c
        L_0x001a:
            r5 = r21
        L_0x001c:
            r6 = r0 & 512(0x200, float:7.175E-43)
            if (r6 == 0) goto L_0x0021
            goto L_0x0023
        L_0x0021:
            r4 = r22
        L_0x0023:
            r6 = r0 & 1024(0x400, float:1.435E-42)
            if (r6 == 0) goto L_0x002a
            java.lang.String r6 = "Add to Cart"
            goto L_0x002b
        L_0x002a:
            r6 = r2
        L_0x002b:
            r7 = r0 & 2048(0x800, float:2.87E-42)
            if (r7 == 0) goto L_0x0032
            java.lang.String r7 = "Go to Cart"
            goto L_0x0033
        L_0x0032:
            r7 = r2
        L_0x0033:
            r8 = r0 & 4096(0x1000, float:5.74E-42)
            if (r8 == 0) goto L_0x003a
            java.lang.String r8 = "Continue with Stories"
            goto L_0x003b
        L_0x003a:
            r8 = r2
        L_0x003b:
            r9 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r9 == 0) goto L_0x0042
            java.lang.String r9 = "Added to your Cart successfully"
            goto L_0x0043
        L_0x0042:
            r9 = r2
        L_0x0043:
            r10 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r10 == 0) goto L_0x004a
            java.lang.String r10 = "Go to Checkout"
            goto L_0x004b
        L_0x004a:
            r10 = r2
        L_0x004b:
            r11 = 32768(0x8000, float:4.5918E-41)
            r11 = r11 & r0
            if (r11 == 0) goto L_0x0053
            java.lang.String r2 = "Total"
        L_0x0053:
            r11 = 65536(0x10000, float:9.18355E-41)
            r0 = r0 & r11
            if (r0 == 0) goto L_0x005a
            r0 = 4
            goto L_0x005c
        L_0x005a:
            r0 = r29
        L_0x005c:
            r11 = 0
            r14 = r11
            r15 = r11
            r16 = r11
            r17 = r11
            r18 = r11
            r21 = r11
            r13 = r12
            r19 = r1
            r20 = r3
            r22 = r5
            r23 = r4
            r24 = r6
            r25 = r7
            r26 = r8
            r27 = r9
            r28 = r10
            r29 = r2
            r30 = r0
            r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.h0.<init>(com.appsamurai.storyly.data.c, com.appsamurai.storyly.data.c, com.appsamurai.storyly.data.c, com.appsamurai.storyly.data.c, com.appsamurai.storyly.data.c, java.lang.String, boolean, com.appsamurai.storyly.data.m, boolean, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int):void");
    }

    public h0(@Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, @NotNull String str, boolean z2, @Nullable m mVar, boolean z3, boolean z4, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, int i3) {
        String str8 = str2;
        String str9 = str3;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        String str13 = str7;
        Intrinsics.checkNotNullParameter(str, "catalogButtonText");
        Intrinsics.checkNotNullParameter(str8, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str9, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str10, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str11, "successMessage");
        Intrinsics.checkNotNullParameter(str12, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str13, "totalText");
        this.f3726a = cVar;
        this.f3727b = cVar2;
        this.f3728c = cVar3;
        this.f3729d = cVar4;
        this.f3730e = cVar5;
        this.f3731f = str;
        this.f3732g = z2;
        this.f3733h = mVar;
        this.f3734i = z3;
        this.f3735j = z4;
        this.f3736k = str8;
        this.f3737l = str9;
        this.f3738m = str10;
        this.f3739n = str11;
        this.f3740o = str12;
        this.f3741p = str13;
        this.f3742q = i3;
    }

    @NotNull
    public String a() {
        return this.f3740o;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.ProductCatalog);
    }
}
