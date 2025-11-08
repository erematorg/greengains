package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
import com.appsamurai.storyly.data.c;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class p extends g0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4094a;

    /* renamed from: b  reason: collision with root package name */
    public final int f4095b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final c f4096c;

    /* renamed from: d  reason: collision with root package name */
    public final int f4097d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final c f4098e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final c f4099f;

    /* renamed from: g  reason: collision with root package name */
    public final int f4100g;

    /* renamed from: h  reason: collision with root package name */
    public final int f4101h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public String f4102i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f4103j;

    /* renamed from: k  reason: collision with root package name */
    public final boolean f4104k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public m f4105l;

    /* renamed from: m  reason: collision with root package name */
    public boolean f4106m;

    /* renamed from: n  reason: collision with root package name */
    public boolean f4107n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public String f4108o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public String f4109p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public String f4110q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public String f4111r;
    @NotNull

    /* renamed from: s  reason: collision with root package name */
    public String f4112s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public String f4113t;

    /* renamed from: u  reason: collision with root package name */
    public int f4114u;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<p> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4115a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4116b;

        static {
            a aVar = new a();
            f4115a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyButtonActionLayer", aVar, 21);
            pluginGeneratedSerialDescriptor.addElement("text", false);
            pluginGeneratedSerialDescriptor.addElement("text_alignment", true);
            pluginGeneratedSerialDescriptor.addElement("text_color", true);
            pluginGeneratedSerialDescriptor.addElement("text_size", true);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_thickness", true);
            pluginGeneratedSerialDescriptor.addElement("border_radius", true);
            pluginGeneratedSerialDescriptor.addElement("outlink", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
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
            f4116b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(m.f3857b);
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            c.a aVar = c.f3622b;
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, intSerializer, aVar, intSerializer, aVar, aVar, intSerializer, intSerializer, nullable, booleanSerializer, booleanSerializer, nullable2, booleanSerializer, booleanSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, intSerializer};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0139, code lost:
            r4 = r2;
            r2 = r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x016d, code lost:
            r5 = r4;
            r4 = r2;
            r2 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0186, code lost:
            r66 = r4;
            r4 = r2;
            r2 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x018c, code lost:
            r5 = r66;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0211, code lost:
            r40 = r40 | r2;
            r2 = r4;
            r4 = r5;
            r3 = 5;
            r6 = 8;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r68) {
            /*
                r67 = this;
                r0 = r68
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4116b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r11 = 11
                r12 = 10
                r13 = 9
                r14 = 7
                r15 = 6
                r3 = 5
                r4 = 3
                r6 = 8
                r5 = 4
                r7 = 2
                r8 = 1
                r9 = 0
                r10 = 0
                if (r2 == 0) goto L_0x00c5
                java.lang.String r2 = r0.decodeStringElement(r1, r9)
                int r8 = r0.decodeIntElement(r1, r8)
                com.appsamurai.storyly.data.c$a r9 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r7 = r0.decodeSerializableElement(r1, r7, r9, r10)
                int r4 = r0.decodeIntElement(r1, r4)
                java.lang.Object r5 = r0.decodeSerializableElement(r1, r5, r9, r10)
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r3, r9, r10)
                int r9 = r0.decodeIntElement(r1, r15)
                int r14 = r0.decodeIntElement(r1, r14)
                kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r15, r10)
                boolean r13 = r0.decodeBooleanElement(r1, r13)
                boolean r12 = r0.decodeBooleanElement(r1, r12)
                com.appsamurai.storyly.data.m$a r15 = com.appsamurai.storyly.data.m.f3857b
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r15, r10)
                r11 = 12
                boolean r11 = r0.decodeBooleanElement(r1, r11)
                r15 = 13
                boolean r15 = r0.decodeBooleanElement(r1, r15)
                r21 = r2
                r2 = 14
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r20 = r2
                r2 = 15
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r19 = r2
                r2 = 16
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r18 = r2
                r2 = 17
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r17 = r2
                r2 = 18
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r16 = r2
                r2 = 19
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r68 = r9
                r9 = 20
                int r9 = r0.decodeIntElement(r1, r9)
                r22 = 2097151(0x1fffff, float:2.938734E-39)
                r51 = r68
                r64 = r2
                r48 = r4
                r46 = r8
                r65 = r9
                r57 = r11
                r55 = r12
                r54 = r13
                r52 = r14
                r58 = r15
                r63 = r16
                r62 = r17
                r61 = r18
                r60 = r19
                r59 = r20
                r45 = r21
                r44 = r22
                goto L_0x024c
            L_0x00c5:
                r2 = r9
                r9 = 20
                r23 = r2
                r24 = r23
                r25 = r24
                r35 = r25
                r36 = r35
                r37 = r36
                r38 = r37
                r39 = r38
                r40 = r39
                r41 = r8
                r4 = r10
                r7 = r4
                r8 = r7
                r27 = r8
                r28 = r27
                r29 = r28
                r30 = r29
                r31 = r30
                r32 = r31
                r33 = r32
                r34 = r33
                r2 = r34
                r10 = r40
            L_0x00f3:
                if (r41 == 0) goto L_0x0223
                int r5 = r0.decodeElementIndex(r1)
                switch(r5) {
                    case -1: goto L_0x021b;
                    case 0: goto L_0x0204;
                    case 1: goto L_0x01f7;
                    case 2: goto L_0x01e6;
                    case 3: goto L_0x01da;
                    case 4: goto L_0x01cc;
                    case 5: goto L_0x01bc;
                    case 6: goto L_0x01b5;
                    case 7: goto L_0x01ae;
                    case 8: goto L_0x019e;
                    case 9: goto L_0x0197;
                    case 10: goto L_0x0190;
                    case 11: goto L_0x017e;
                    case 12: goto L_0x0175;
                    case 13: goto L_0x0165;
                    case 14: goto L_0x0159;
                    case 15: goto L_0x014c;
                    case 16: goto L_0x0140;
                    case 17: goto L_0x012e;
                    case 18: goto L_0x011c;
                    case 19: goto L_0x010c;
                    case 20: goto L_0x0102;
                    default: goto L_0x00fc;
                }
            L_0x00fc:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r5)
                throw r0
            L_0x0102:
                int r25 = r0.decodeIntElement(r1, r9)
                r5 = 1048576(0x100000, float:1.469368E-39)
                r40 = r40 | r5
            L_0x010a:
                r5 = 4
                goto L_0x00f3
            L_0x010c:
                r5 = 19
                java.lang.String r34 = r0.decodeStringElement(r1, r5)
                r42 = 524288(0x80000, float:7.34684E-40)
                r5 = r4
                r3 = 0
                r6 = 2
                r4 = r2
                r2 = r42
                goto L_0x0211
            L_0x011c:
                r5 = 18
                java.lang.String r16 = r0.decodeStringElement(r1, r5)
                r33 = 262144(0x40000, float:3.67342E-40)
                r5 = r4
                r3 = 0
                r6 = 2
                r4 = r2
                r2 = r33
                r33 = r16
                goto L_0x0211
            L_0x012e:
                r5 = 17
                java.lang.String r16 = r0.decodeStringElement(r1, r5)
                r17 = 131072(0x20000, float:1.83671E-40)
                r5 = r4
                r32 = r16
            L_0x0139:
                r3 = 0
                r6 = 2
                r4 = r2
                r2 = r17
                goto L_0x0211
            L_0x0140:
                r5 = 16
                java.lang.String r16 = r0.decodeStringElement(r1, r5)
                r17 = 65536(0x10000, float:9.18355E-41)
                r5 = r4
                r31 = r16
                goto L_0x0139
            L_0x014c:
                r5 = 15
                java.lang.String r16 = r0.decodeStringElement(r1, r5)
                r17 = 32768(0x8000, float:4.5918E-41)
                r5 = r4
                r30 = r16
                goto L_0x0139
            L_0x0159:
                r5 = 14
                java.lang.String r16 = r0.decodeStringElement(r1, r5)
                r17 = 16384(0x4000, float:2.2959E-41)
                r5 = r4
                r29 = r16
                goto L_0x0139
            L_0x0165:
                r5 = 13
                boolean r39 = r0.decodeBooleanElement(r1, r5)
                r16 = 8192(0x2000, float:1.14794E-41)
            L_0x016d:
                r5 = r4
                r3 = 0
                r6 = 2
                r4 = r2
                r2 = r16
                goto L_0x0211
            L_0x0175:
                r5 = 12
                boolean r35 = r0.decodeBooleanElement(r1, r5)
                r16 = 4096(0x1000, float:5.74E-42)
                goto L_0x016d
            L_0x017e:
                com.appsamurai.storyly.data.m$a r5 = com.appsamurai.storyly.data.m.f3857b
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r11, r5, r2)
                r5 = 2048(0x800, float:2.87E-42)
            L_0x0186:
                r3 = 0
                r6 = 2
                r66 = r4
                r4 = r2
                r2 = r5
            L_0x018c:
                r5 = r66
                goto L_0x0211
            L_0x0190:
                boolean r36 = r0.decodeBooleanElement(r1, r12)
                r5 = 1024(0x400, float:1.435E-42)
                goto L_0x0186
            L_0x0197:
                boolean r37 = r0.decodeBooleanElement(r1, r13)
                r5 = 512(0x200, float:7.175E-43)
                goto L_0x0186
            L_0x019e:
                kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r6, r5, r8)
                r8 = 256(0x100, float:3.59E-43)
                r3 = 0
                r6 = 2
                r66 = r4
                r4 = r2
                r2 = r8
                r8 = r5
                goto L_0x018c
            L_0x01ae:
                int r38 = r0.decodeIntElement(r1, r14)
                r5 = 128(0x80, float:1.794E-43)
                goto L_0x0186
            L_0x01b5:
                int r24 = r0.decodeIntElement(r1, r15)
                r5 = 64
                goto L_0x0186
            L_0x01bc:
                com.appsamurai.storyly.data.c$a r5 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r5 = r0.decodeSerializableElement(r1, r3, r5, r7)
                r7 = 32
                r3 = 0
                r6 = 2
                r66 = r4
                r4 = r2
                r2 = r7
                r7 = r5
                goto L_0x018c
            L_0x01cc:
                com.appsamurai.storyly.data.c$a r5 = com.appsamurai.storyly.data.c.f3622b
                r3 = 4
                java.lang.Object r4 = r0.decodeSerializableElement(r1, r3, r5, r4)
                r5 = r4
                r3 = 0
                r6 = 2
                r4 = r2
                r2 = 16
                goto L_0x0211
            L_0x01da:
                r3 = 4
                r5 = 3
                int r10 = r0.decodeIntElement(r1, r5)
                r5 = r4
                r3 = 0
                r4 = r2
                r2 = r6
                r6 = 2
                goto L_0x0211
            L_0x01e6:
                r5 = 3
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r5 = r27
                r6 = 2
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r6, r3, r5)
                r27 = r3
                r5 = r4
                r3 = 0
                r4 = r2
                r2 = 4
                goto L_0x0211
            L_0x01f7:
                r5 = r27
                r3 = 1
                r6 = 2
                int r23 = r0.decodeIntElement(r1, r3)
                r3 = 0
                r5 = r4
                r4 = r2
                r2 = r6
                goto L_0x0211
            L_0x0204:
                r5 = r27
                r3 = 0
                r6 = 2
                java.lang.String r26 = r0.decodeStringElement(r1, r3)
                r28 = r26
                r5 = r4
                r4 = r2
                r2 = 1
            L_0x0211:
                r40 = r40 | r2
                r2 = r4
                r4 = r5
                r3 = 5
                r5 = 4
                r6 = 8
                goto L_0x00f3
            L_0x021b:
                r5 = r27
                r3 = 0
                r41 = r3
                r3 = 5
                goto L_0x010a
            L_0x0223:
                r5 = r27
                r3 = r7
                r6 = r8
                r48 = r10
                r46 = r23
                r51 = r24
                r65 = r25
                r45 = r28
                r59 = r29
                r60 = r30
                r61 = r31
                r62 = r32
                r63 = r33
                r64 = r34
                r57 = r35
                r55 = r36
                r54 = r37
                r52 = r38
                r58 = r39
                r44 = r40
                r10 = r2
                r7 = r5
                r5 = r4
            L_0x024c:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.p r0 = new com.appsamurai.storyly.data.p
                r43 = r0
                r47 = r7
                com.appsamurai.storyly.data.c r47 = (com.appsamurai.storyly.data.c) r47
                r49 = r5
                com.appsamurai.storyly.data.c r49 = (com.appsamurai.storyly.data.c) r49
                r50 = r3
                com.appsamurai.storyly.data.c r50 = (com.appsamurai.storyly.data.c) r50
                r53 = r6
                java.lang.String r53 = (java.lang.String) r53
                r56 = r10
                com.appsamurai.storyly.data.m r56 = (com.appsamurai.storyly.data.m) r56
                r43.<init>(r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.p.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4116b;
        }

        public void serialize(Encoder encoder, Object obj) {
            p pVar = (p) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(pVar, "value");
            SerialDescriptor serialDescriptor = f4116b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(pVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeStringElement(serialDescriptor, 0, pVar.f4094a);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || pVar.f4095b != 1) {
                beginStructure.encodeIntElement(serialDescriptor, 1, pVar.f4095b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || !Intrinsics.areEqual((Object) pVar.f4096c, (Object) new c(-1))) {
                beginStructure.encodeSerializableElement(serialDescriptor, 2, c.f3622b, pVar.f4096c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || pVar.f4097d != 0) {
                beginStructure.encodeIntElement(serialDescriptor, 3, pVar.f4097d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || !Intrinsics.areEqual((Object) pVar.f4098e, (Object) com.appsamurai.storyly.config.styling.a.COLOR_189FFF.b())) {
                beginStructure.encodeSerializableElement(serialDescriptor, 4, c.f3622b, pVar.f4098e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || !Intrinsics.areEqual((Object) pVar.f4099f, (Object) new c(0))) {
                beginStructure.encodeSerializableElement(serialDescriptor, 5, c.f3622b, pVar.f4099f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || pVar.f4100g != 0) {
                beginStructure.encodeIntElement(serialDescriptor, 6, pVar.f4100g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || pVar.f4101h != 33) {
                beginStructure.encodeIntElement(serialDescriptor, 7, pVar.f4101h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || pVar.f4102i != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, pVar.f4102i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || pVar.f4103j) {
                beginStructure.encodeBooleanElement(serialDescriptor, 9, pVar.f4103j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || pVar.f4104k) {
                beginStructure.encodeBooleanElement(serialDescriptor, 10, pVar.f4104k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || pVar.f4105l != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 11, m.f3857b, pVar.f4105l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || !pVar.f4106m) {
                beginStructure.encodeBooleanElement(serialDescriptor, 12, pVar.f4106m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || !pVar.f4107n) {
                beginStructure.encodeBooleanElement(serialDescriptor, 13, pVar.f4107n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || !Intrinsics.areEqual((Object) pVar.f4108o, (Object) "Add to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 14, pVar.f4108o);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 15) || !Intrinsics.areEqual((Object) pVar.f4109p, (Object) "Go to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 15, pVar.f4109p);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 16) || !Intrinsics.areEqual((Object) pVar.f4110q, (Object) "Continue with Stories")) {
                beginStructure.encodeStringElement(serialDescriptor, 16, pVar.f4110q);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 17) || !Intrinsics.areEqual((Object) pVar.f4111r, (Object) "Added to your Cart successfully")) {
                beginStructure.encodeStringElement(serialDescriptor, 17, pVar.f4111r);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 18) || !Intrinsics.areEqual((Object) pVar.f4112s, (Object) "Go to Checkout")) {
                beginStructure.encodeStringElement(serialDescriptor, 18, pVar.f4112s);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 19) || !Intrinsics.areEqual((Object) pVar.f4113t, (Object) "Total")) {
                beginStructure.encodeStringElement(serialDescriptor, 19, pVar.f4113t);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 20) || pVar.f4114u != 4) {
                beginStructure.encodeIntElement(serialDescriptor, 20, pVar.f4114u);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ p(int i3, @Required @SerialName("text") String str, @SerialName("text_alignment") int i4, @SerialName("text_color") c cVar, @SerialName("text_size") int i5, @SerialName("bg_color") c cVar2, @SerialName("border_color") c cVar3, @SerialName("border_thickness") int i6, @SerialName("border_radius") int i7, @SerialName("outlink") String str2, @SerialName("is_bold") boolean z2, @SerialName("is_italic") boolean z3, @SerialName("products") m mVar, @SerialName("is_s_price_visible") boolean z4, @SerialName("is_price_visible") boolean z5, @SerialName("p_b_text") String str3, @SerialName("s_b_cart_text") String str4, @SerialName("s_b_back_text") String str5, @SerialName("s_message") String str6, @SerialName("checkout_b_text") String str7, @SerialName("t_text") String str8, @SerialName("max_v") int i8) {
        c cVar4;
        c cVar5;
        c cVar6;
        int i9 = i3;
        if (1 != (i9 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 1, a.f4115a.getDescriptor());
        }
        this.f4094a = str;
        if ((i9 & 2) == 0) {
            this.f4095b = 1;
        } else {
            this.f4095b = i4;
        }
        if ((i9 & 4) == 0) {
            cVar4 = new c(-1);
        } else {
            cVar4 = cVar;
        }
        this.f4096c = cVar4;
        if ((i9 & 8) == 0) {
            this.f4097d = 0;
        } else {
            this.f4097d = i5;
        }
        if ((i9 & 16) == 0) {
            cVar5 = com.appsamurai.storyly.config.styling.a.COLOR_189FFF.b();
        } else {
            cVar5 = cVar2;
        }
        this.f4098e = cVar5;
        if ((i9 & 32) == 0) {
            cVar6 = new c(0);
        } else {
            cVar6 = cVar3;
        }
        this.f4099f = cVar6;
        if ((i9 & 64) == 0) {
            this.f4100g = 0;
        } else {
            this.f4100g = i6;
        }
        this.f4101h = (i9 & 128) == 0 ? 33 : i7;
        if ((i9 & 256) == 0) {
            this.f4102i = null;
        } else {
            this.f4102i = str2;
        }
        if ((i9 & 512) == 0) {
            this.f4103j = false;
        } else {
            this.f4103j = z2;
        }
        if ((i9 & 1024) == 0) {
            this.f4104k = false;
        } else {
            this.f4104k = z3;
        }
        if ((i9 & 2048) == 0) {
            this.f4105l = null;
        } else {
            this.f4105l = mVar;
        }
        if ((i9 & 4096) == 0) {
            this.f4106m = true;
        } else {
            this.f4106m = z4;
        }
        if ((i9 & 8192) == 0) {
            this.f4107n = true;
        } else {
            this.f4107n = z5;
        }
        this.f4108o = (i9 & 16384) == 0 ? "Add to Cart" : str3;
        this.f4109p = (32768 & i9) == 0 ? "Go to Cart" : str4;
        this.f4110q = (65536 & i9) == 0 ? "Continue with Stories" : str5;
        this.f4111r = (131072 & i9) == 0 ? "Added to your Cart successfully" : str6;
        this.f4112s = (262144 & i9) == 0 ? "Go to Checkout" : str7;
        this.f4113t = (524288 & i9) == 0 ? "Total" : str8;
        this.f4114u = (i9 & 1048576) == 0 ? 4 : i8;
    }

    @NotNull
    public String a() {
        return this.f4112s;
    }

    @Nullable
    public m c() {
        return this.f4105l;
    }

    @NotNull
    public String d() {
        return this.f4108o;
    }

    @NotNull
    public String e() {
        return this.f4110q;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return Intrinsics.areEqual((Object) this.f4094a, (Object) pVar.f4094a) && this.f4095b == pVar.f4095b && Intrinsics.areEqual((Object) this.f4096c, (Object) pVar.f4096c) && this.f4097d == pVar.f4097d && Intrinsics.areEqual((Object) this.f4098e, (Object) pVar.f4098e) && Intrinsics.areEqual((Object) this.f4099f, (Object) pVar.f4099f) && this.f4100g == pVar.f4100g && this.f4101h == pVar.f4101h && Intrinsics.areEqual((Object) this.f4102i, (Object) pVar.f4102i) && this.f4103j == pVar.f4103j && this.f4104k == pVar.f4104k && Intrinsics.areEqual((Object) this.f4105l, (Object) pVar.f4105l) && this.f4106m == pVar.f4106m && this.f4107n == pVar.f4107n && Intrinsics.areEqual((Object) this.f4108o, (Object) pVar.f4108o) && Intrinsics.areEqual((Object) this.f4109p, (Object) pVar.f4109p) && Intrinsics.areEqual((Object) this.f4110q, (Object) pVar.f4110q) && Intrinsics.areEqual((Object) this.f4111r, (Object) pVar.f4111r) && Intrinsics.areEqual((Object) this.f4112s, (Object) pVar.f4112s) && Intrinsics.areEqual((Object) this.f4113t, (Object) pVar.f4113t) && this.f4114u == pVar.f4114u;
    }

    @NotNull
    public String f() {
        return this.f4109p;
    }

    @NotNull
    public String g() {
        return this.f4111r;
    }

    @NotNull
    public String h() {
        return this.f4113t;
    }

    public int hashCode() {
        int c3 = androidx.compose.animation.core.a.c(this.f4101h, androidx.compose.animation.core.a.c(this.f4100g, androidx.compose.animation.core.a.c(this.f4099f.f3624a, androidx.compose.animation.core.a.c(this.f4098e.f3624a, androidx.compose.animation.core.a.c(this.f4097d, androidx.compose.animation.core.a.c(this.f4096c.f3624a, androidx.compose.animation.core.a.c(this.f4095b, this.f4094a.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31);
        String str = this.f4102i;
        int i3 = 0;
        int hashCode = (c3 + (str == null ? 0 : str.hashCode())) * 31;
        boolean z2 = this.f4103j;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (hashCode + (z2 ? 1 : 0)) * 31;
        boolean z4 = this.f4104k;
        if (z4) {
            z4 = true;
        }
        int i5 = (i4 + (z4 ? 1 : 0)) * 31;
        m mVar = this.f4105l;
        if (mVar != null) {
            i3 = mVar.hashCode();
        }
        int i6 = (i5 + i3) * 31;
        boolean z5 = this.f4106m;
        if (z5) {
            z5 = true;
        }
        int i7 = (i6 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.f4107n;
        if (!z6) {
            z3 = z6;
        }
        return Integer.hashCode(this.f4114u) + androidx.compose.animation.core.a.i(this.f4113t, androidx.compose.animation.core.a.i(this.f4112s, androidx.compose.animation.core.a.i(this.f4111r, androidx.compose.animation.core.a.i(this.f4110q, androidx.compose.animation.core.a.i(this.f4109p, androidx.compose.animation.core.a.i(this.f4108o, (i7 + (z3 ? 1 : 0)) * 31, 31), 31), 31), 31), 31), 31);
    }

    public boolean i() {
        return this.f4107n;
    }

    public boolean j() {
        return this.f4106m;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyButtonActionLayer(buttonText=");
        sb.append(this.f4094a);
        sb.append(", textAlignment=");
        sb.append(this.f4095b);
        sb.append(", textColor=");
        sb.append(this.f4096c);
        sb.append(", textSize=");
        sb.append(this.f4097d);
        sb.append(", backgroundColor=");
        sb.append(this.f4098e);
        sb.append(", borderColor=");
        sb.append(this.f4099f);
        sb.append(", borderThickness=");
        sb.append(this.f4100g);
        sb.append(", borderRadius=");
        sb.append(this.f4101h);
        sb.append(", actionUrl=");
        sb.append(this.f4102i);
        sb.append(", isBold=");
        sb.append(this.f4103j);
        sb.append(", isItalic=");
        sb.append(this.f4104k);
        sb.append(", productData=");
        sb.append(this.f4105l);
        sb.append(", isProductSalesPriceVisible=");
        sb.append(this.f4106m);
        sb.append(", isProductPriceVisible=");
        sb.append(this.f4107n);
        sb.append(", purchaseButtonText=");
        sb.append(this.f4108o);
        sb.append(", successButtonCartText=");
        sb.append(this.f4109p);
        sb.append(", successButtonBackText=");
        sb.append(this.f4110q);
        sb.append(", successMessage=");
        sb.append(this.f4111r);
        sb.append(", checkoutButtonText=");
        sb.append(this.f4112s);
        sb.append(", totalText=");
        sb.append(this.f4113t);
        sb.append(", maxVariantCount=");
        return android.support.v4.media.session.a.p(sb, this.f4114u, ')');
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.ButtonAction);
    }

    public p(@NotNull String str, int i3, @NotNull c cVar, int i4, @NotNull c cVar2, @NotNull c cVar3, int i5, int i6, @Nullable String str2, boolean z2, boolean z3, @Nullable m mVar, boolean z4, boolean z5, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, int i7) {
        c cVar4 = cVar2;
        c cVar5 = cVar3;
        String str9 = str3;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        String str13 = str7;
        String str14 = str8;
        Intrinsics.checkNotNullParameter(str, "buttonText");
        Intrinsics.checkNotNullParameter(cVar, "textColor");
        Intrinsics.checkNotNullParameter(cVar4, TtmlNode.ATTR_TTS_BACKGROUND_COLOR);
        Intrinsics.checkNotNullParameter(cVar5, "borderColor");
        Intrinsics.checkNotNullParameter(str9, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str10, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str11, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str12, "successMessage");
        Intrinsics.checkNotNullParameter(str13, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str14, "totalText");
        this.f4094a = str;
        this.f4095b = i3;
        this.f4096c = cVar;
        this.f4097d = i4;
        this.f4098e = cVar4;
        this.f4099f = cVar5;
        this.f4100g = i5;
        this.f4101h = i6;
        this.f4102i = str2;
        this.f4103j = z2;
        this.f4104k = z3;
        this.f4105l = mVar;
        this.f4106m = z4;
        this.f4107n = z5;
        this.f4108o = str9;
        this.f4109p = str10;
        this.f4110q = str11;
        this.f4111r = str12;
        this.f4112s = str13;
        this.f4113t = str14;
        this.f4114u = i7;
    }
}
