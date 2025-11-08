package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
import com.appsamurai.storyly.data.c;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class x extends g0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f4252a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public String f4253b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public String f4254c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final c f4255d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final List<c> f4256e;

    /* renamed from: f  reason: collision with root package name */
    public final float f4257f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final String f4258g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f4259h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public final String f4260i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public m f4261j;

    /* renamed from: k  reason: collision with root package name */
    public boolean f4262k;

    /* renamed from: l  reason: collision with root package name */
    public boolean f4263l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public String f4264m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public String f4265n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public String f4266o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public String f4267p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public String f4268q;
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public String f4269r;

    /* renamed from: s  reason: collision with root package name */
    public int f4270s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final b f4271t;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<x> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4272a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4273b;

        static {
            a aVar = new a();
            f4272a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyImageLayer", aVar, 20);
            pluginGeneratedSerialDescriptor.addElement("content_mode", true);
            pluginGeneratedSerialDescriptor.addElement("image_url", true);
            pluginGeneratedSerialDescriptor.addElement("image_path", true);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("gradient_colors", true);
            pluginGeneratedSerialDescriptor.addElement("border_radius", true);
            pluginGeneratedSerialDescriptor.addElement("outlink", true);
            pluginGeneratedSerialDescriptor.addElement("is_bg", true);
            pluginGeneratedSerialDescriptor.addElement("alt_text", true);
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
            pluginGeneratedSerialDescriptor.addElement("imageSource", true);
            f4273b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(stringSerializer);
            c.a aVar = c.f3622b;
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(new ArrayListSerializer(aVar));
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable6 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable7 = BuiltinSerializersKt.getNullable(m.f3857b);
            EnumSerializer enumSerializer = new EnumSerializer("com.appsamurai.storyly.data.StorylyImageLayer.ImageSourceType", b.values());
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{intSerializer, nullable, nullable2, nullable3, nullable4, FloatSerializer.INSTANCE, nullable5, booleanSerializer, nullable6, nullable7, booleanSerializer, booleanSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, intSerializer, enumSerializer};
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v0, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v0, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v4, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v5, resolved type: java.lang.String} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v5, resolved type: java.lang.String} */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0159, code lost:
            r5 = r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x015b, code lost:
            r7 = r27;
            r14 = r28;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x01ab, code lost:
            r25 = r2;
            r6 = r5;
            r5 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0292, code lost:
            r12 = r12 | r5;
            r5 = r6;
            r27 = r7;
            r28 = r14;
            r2 = r25;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x029a, code lost:
            r3 = 5;
            r6 = 8;
            r14 = 7;
            r15 = 6;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r44) {
            /*
                r43 = this;
                r0 = r44
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4273b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                java.lang.String r4 = "com.appsamurai.storyly.data.StorylyImageLayer.ImageSourceType"
                r14 = 7
                r15 = 6
                r3 = 5
                r5 = 3
                r6 = 8
                r7 = 4
                r8 = 2
                r9 = 1
                r10 = 0
                r11 = 0
                if (r2 == 0) goto L_0x00ce
                int r2 = r0.decodeIntElement(r1, r10)
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r10, r11)
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r10, r11)
                com.appsamurai.storyly.data.c$a r12 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r12, r11)
                kotlinx.serialization.internal.ArrayListSerializer r13 = new kotlinx.serialization.internal.ArrayListSerializer
                r13.<init>(r12)
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r13, r11)
                float r3 = r0.decodeFloatElement(r1, r3)
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r15, r10, r11)
                boolean r13 = r0.decodeBooleanElement(r1, r14)
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r10, r11)
                com.appsamurai.storyly.data.m$a r10 = com.appsamurai.storyly.data.m.f3857b
                r14 = 9
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r14, r10, r11)
                r14 = 10
                boolean r14 = r0.decodeBooleanElement(r1, r14)
                r15 = 11
                boolean r15 = r0.decodeBooleanElement(r1, r15)
                r11 = 12
                java.lang.String r11 = r0.decodeStringElement(r1, r11)
                r21 = r2
                r2 = 13
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r20 = r2
                r2 = 14
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r19 = r2
                r2 = 15
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r18 = r2
                r2 = 16
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r17 = r2
                r2 = 17
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r16 = r2
                r2 = 18
                int r2 = r0.decodeIntElement(r1, r2)
                r44 = r2
                kotlinx.serialization.internal.EnumSerializer r2 = new kotlinx.serialization.internal.EnumSerializer
                r22 = r3
                com.appsamurai.storyly.data.x$b[] r3 = com.appsamurai.storyly.data.x.b.values()
                r2.<init>(r4, r3)
                r3 = 19
                r4 = 0
                java.lang.Object r2 = r0.decodeSerializableElement(r1, r3, r2, r4)
                r3 = 1048575(0xfffff, float:1.469367E-39)
                r23 = r44
                r4 = r3
                r37 = r13
                r38 = r14
                r36 = r16
                r14 = r8
                r16 = r15
                r8 = r5
                r15 = r9
                r5 = r21
                r9 = r7
                r21 = r17
                r17 = r11
                r11 = r12
                r12 = r10
                r10 = r6
                r42 = r20
                r20 = r18
                r18 = r42
                goto L_0x02d6
            L_0x00ce:
                r2 = r11
                r11 = 0
                r5 = r2
                r8 = r5
                r27 = r8
                r28 = r27
                r29 = r28
                r30 = r29
                r31 = r30
                r32 = r31
                r33 = r32
                r34 = r33
                r35 = r34
                r36 = r35
                r40 = r9
                r12 = r10
                r37 = r12
                r38 = r37
                r39 = r38
                r13 = r11
                r9 = r36
                r2 = r39
                r11 = r2
                r10 = r9
            L_0x00f6:
                if (r40 == 0) goto L_0x02b3
                int r7 = r0.decodeElementIndex(r1)
                switch(r7) {
                    case -1: goto L_0x02a2;
                    case 0: goto L_0x0280;
                    case 1: goto L_0x0264;
                    case 2: goto L_0x024b;
                    case 3: goto L_0x0235;
                    case 4: goto L_0x0219;
                    case 5: goto L_0x020c;
                    case 6: goto L_0x01fd;
                    case 7: goto L_0x01ea;
                    case 8: goto L_0x01d4;
                    case 9: goto L_0x01bc;
                    case 10: goto L_0x01b1;
                    case 11: goto L_0x01a1;
                    case 12: goto L_0x0191;
                    case 13: goto L_0x0181;
                    case 14: goto L_0x0171;
                    case 15: goto L_0x0160;
                    case 16: goto L_0x014a;
                    case 17: goto L_0x0131;
                    case 18: goto L_0x011a;
                    case 19: goto L_0x0105;
                    default: goto L_0x00ff;
                }
            L_0x00ff:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r7)
                throw r0
            L_0x0105:
                kotlinx.serialization.internal.EnumSerializer r7 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.x$b[] r3 = com.appsamurai.storyly.data.x.b.values()
                r7.<init>(r4, r3)
                r3 = 19
                java.lang.Object r10 = r0.decodeSerializableElement(r1, r3, r7, r10)
                r7 = 524288(0x80000, float:7.34684E-40)
                r12 = r12 | r7
                r3 = 5
                r7 = 4
                goto L_0x00f6
            L_0x011a:
                r3 = 19
                r7 = 18
                int r11 = r0.decodeIntElement(r1, r7)
                r41 = 262144(0x40000, float:3.67342E-40)
                r25 = r2
                r6 = r5
                r7 = r27
                r14 = r28
                r5 = r41
            L_0x012d:
                r2 = 1
            L_0x012e:
                r3 = 0
                goto L_0x0292
            L_0x0131:
                r3 = 17
                r7 = 18
                java.lang.String r16 = r0.decodeStringElement(r1, r3)
                r36 = 131072(0x20000, float:1.83671E-40)
                r25 = r2
                r6 = r5
                r7 = r27
                r14 = r28
                r5 = r36
                r2 = 1
                r3 = 0
                r36 = r16
                goto L_0x0292
            L_0x014a:
                r3 = 16
                r7 = 18
                java.lang.String r16 = r0.decodeStringElement(r1, r3)
                r17 = 65536(0x10000, float:9.18355E-41)
                r25 = r2
                r6 = r5
                r35 = r16
            L_0x0159:
                r5 = r17
            L_0x015b:
                r7 = r27
                r14 = r28
                goto L_0x012d
            L_0x0160:
                r3 = 15
                r7 = 18
                java.lang.String r16 = r0.decodeStringElement(r1, r3)
                r17 = 32768(0x8000, float:4.5918E-41)
                r25 = r2
                r6 = r5
                r34 = r16
                goto L_0x0159
            L_0x0171:
                r3 = 14
                r7 = 18
                java.lang.String r16 = r0.decodeStringElement(r1, r3)
                r17 = 16384(0x4000, float:2.2959E-41)
                r25 = r2
                r6 = r5
                r33 = r16
                goto L_0x0159
            L_0x0181:
                r3 = 13
                r7 = 18
                java.lang.String r16 = r0.decodeStringElement(r1, r3)
                r17 = 8192(0x2000, float:1.14794E-41)
                r25 = r2
                r6 = r5
                r32 = r16
                goto L_0x0159
            L_0x0191:
                r3 = 12
                r7 = 18
                java.lang.String r16 = r0.decodeStringElement(r1, r3)
                r17 = 4096(0x1000, float:5.74E-42)
                r25 = r2
                r6 = r5
                r31 = r16
                goto L_0x0159
            L_0x01a1:
                r3 = 11
                r7 = 18
                boolean r39 = r0.decodeBooleanElement(r1, r3)
                r16 = 2048(0x800, float:2.87E-42)
            L_0x01ab:
                r25 = r2
                r6 = r5
                r5 = r16
                goto L_0x015b
            L_0x01b1:
                r3 = 10
                r7 = 18
                boolean r38 = r0.decodeBooleanElement(r1, r3)
                r16 = 1024(0x400, float:1.435E-42)
                goto L_0x01ab
            L_0x01bc:
                r7 = 18
                com.appsamurai.storyly.data.m$a r3 = com.appsamurai.storyly.data.m.f3857b
                r7 = 9
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r7, r3, r9)
                r9 = 512(0x200, float:7.175E-43)
                r25 = r2
                r6 = r5
                r5 = r9
                r7 = r27
                r14 = r28
                r2 = 1
                r9 = r3
                goto L_0x012e
            L_0x01d4:
                r7 = 9
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r6, r3, r8)
                r8 = 256(0x100, float:3.59E-43)
                r25 = r2
                r6 = r5
                r5 = r8
                r7 = r27
                r14 = r28
                r2 = 1
                r8 = r3
                goto L_0x012e
            L_0x01ea:
                r7 = 9
                boolean r37 = r0.decodeBooleanElement(r1, r14)
                r3 = 128(0x80, float:1.794E-43)
                r25 = r2
                r6 = r5
                r7 = r27
                r14 = r28
                r2 = 1
                r5 = r3
                goto L_0x012e
            L_0x01fd:
                r7 = 9
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r15, r3, r5)
                r5 = 64
                r25 = r2
                r6 = r3
                goto L_0x015b
            L_0x020c:
                r7 = 9
                float r13 = r0.decodeFloatElement(r1, r3)
                r17 = 32
                r25 = r2
                r6 = r5
                goto L_0x0159
            L_0x0219:
                r7 = 9
                kotlinx.serialization.internal.ArrayListSerializer r3 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.c$a r6 = com.appsamurai.storyly.data.c.f3622b
                r3.<init>(r6)
                r6 = r27
                r7 = 4
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r7, r3, r6)
                r25 = r2
                r7 = r3
                r6 = r5
                r14 = r28
                r2 = 1
                r3 = 0
                r5 = 16
                goto L_0x0292
            L_0x0235:
                r6 = r27
                r7 = 4
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r7 = r28
                r14 = 3
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r14, r3, r7)
                r25 = r2
                r14 = r3
                r7 = r6
                r2 = 1
                r3 = 0
                r6 = r5
                r5 = 8
                goto L_0x0292
            L_0x024b:
                r6 = r27
                r7 = r28
                r14 = 3
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r14 = r29
                r15 = 2
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r15, r3, r14)
                r25 = r2
                r29 = r3
                r14 = r7
                r2 = 1
                r3 = 0
                r7 = r6
                r6 = r5
                r5 = 4
                goto L_0x0292
            L_0x0264:
                r6 = r27
                r7 = r28
                r14 = r29
                r15 = 2
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r26 = r2
                r15 = r30
                r2 = 1
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r2, r3, r15)
                r30 = r3
                r25 = r26
                r3 = 0
                r14 = r7
                r7 = r6
                r6 = r5
                r5 = 2
                goto L_0x0292
            L_0x0280:
                r6 = r27
                r7 = r28
                r14 = r29
                r15 = r30
                r2 = 1
                r3 = 0
                int r25 = r0.decodeIntElement(r1, r3)
                r14 = r7
                r7 = r6
                r6 = r5
                r5 = r2
            L_0x0292:
                r12 = r12 | r5
                r5 = r6
                r27 = r7
                r28 = r14
                r2 = r25
            L_0x029a:
                r3 = 5
                r6 = 8
                r7 = 4
                r14 = 7
                r15 = 6
                goto L_0x00f6
            L_0x02a2:
                r26 = r2
                r6 = r27
                r7 = r28
                r14 = r29
                r15 = r30
                r2 = 1
                r3 = 0
                r40 = r3
                r2 = r26
                goto L_0x029a
            L_0x02b3:
                r26 = r2
                r6 = r27
                r7 = r28
                r14 = r29
                r15 = r30
                r2 = r10
                r23 = r11
                r4 = r12
                r22 = r13
                r17 = r31
                r18 = r32
                r19 = r33
                r20 = r34
                r21 = r35
                r16 = r39
                r11 = r5
                r10 = r8
                r12 = r9
                r5 = r26
                r9 = r6
                r8 = r7
            L_0x02d6:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.x r0 = new com.appsamurai.storyly.data.x
                r3 = r0
                r6 = r15
                java.lang.String r6 = (java.lang.String) r6
                r7 = r14
                java.lang.String r7 = (java.lang.String) r7
                com.appsamurai.storyly.data.c r8 = (com.appsamurai.storyly.data.c) r8
                java.util.List r9 = (java.util.List) r9
                java.lang.String r11 = (java.lang.String) r11
                r13 = r10
                java.lang.String r13 = (java.lang.String) r13
                r14 = r12
                com.appsamurai.storyly.data.m r14 = (com.appsamurai.storyly.data.m) r14
                r24 = r2
                com.appsamurai.storyly.data.x$b r24 = (com.appsamurai.storyly.data.x.b) r24
                r10 = r22
                r12 = r37
                r15 = r38
                r22 = r36
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.x.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4273b;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d6, code lost:
            if (r7.f4271t != (r7.f4255d != null ? com.appsamurai.storyly.data.x.b.f4274a : r7.f4256e != null ? com.appsamurai.storyly.data.x.b.f4275b : r7.f4254c != null ? com.appsamurai.storyly.data.x.b.f4277d : com.appsamurai.storyly.data.x.b.f4276c)) goto L_0x01d8;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void serialize(kotlinx.serialization.encoding.Encoder r6, java.lang.Object r7) {
            /*
                r5 = this;
                com.appsamurai.storyly.data.x r7 = (com.appsamurai.storyly.data.x) r7
                java.lang.String r5 = "encoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r5)
                java.lang.String r5 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r5)
                kotlinx.serialization.descriptors.SerialDescriptor r5 = f4273b
                kotlinx.serialization.encoding.CompositeEncoder r6 = r6.beginStructure(r5)
                java.lang.String r0 = "self"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.String r0 = "output"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "serialDesc"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                r0 = 0
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                r2 = 1
                if (r1 == 0) goto L_0x002b
                goto L_0x002f
            L_0x002b:
                int r1 = r7.f4252a
                if (r1 == r2) goto L_0x0034
            L_0x002f:
                int r1 = r7.f4252a
                r6.encodeIntElement(r5, r0, r1)
            L_0x0034:
                boolean r0 = r6.shouldEncodeElementDefault(r5, r2)
                if (r0 == 0) goto L_0x003b
                goto L_0x003f
            L_0x003b:
                java.lang.String r0 = r7.f4253b
                if (r0 == 0) goto L_0x0046
            L_0x003f:
                kotlinx.serialization.internal.StringSerializer r0 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r1 = r7.f4253b
                r6.encodeNullableSerializableElement(r5, r2, r0, r1)
            L_0x0046:
                r0 = 2
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                if (r1 == 0) goto L_0x004e
                goto L_0x0052
            L_0x004e:
                java.lang.String r1 = r7.f4254c
                if (r1 == 0) goto L_0x0059
            L_0x0052:
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r3 = r7.f4254c
                r6.encodeNullableSerializableElement(r5, r0, r1, r3)
            L_0x0059:
                r0 = 3
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                if (r1 == 0) goto L_0x0061
                goto L_0x0065
            L_0x0061:
                com.appsamurai.storyly.data.c r1 = r7.f4255d
                if (r1 == 0) goto L_0x006c
            L_0x0065:
                com.appsamurai.storyly.data.c$a r1 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f4255d
                r6.encodeNullableSerializableElement(r5, r0, r1, r3)
            L_0x006c:
                r0 = 4
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                if (r1 == 0) goto L_0x0074
                goto L_0x0078
            L_0x0074:
                java.util.List<com.appsamurai.storyly.data.c> r1 = r7.f4256e
                if (r1 == 0) goto L_0x0084
            L_0x0078:
                kotlinx.serialization.internal.ArrayListSerializer r1 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r1.<init>(r3)
                java.util.List<com.appsamurai.storyly.data.c> r3 = r7.f4256e
                r6.encodeNullableSerializableElement(r5, r0, r1, r3)
            L_0x0084:
                r1 = 5
                boolean r3 = r6.shouldEncodeElementDefault(r5, r1)
                if (r3 == 0) goto L_0x008c
                goto L_0x009d
            L_0x008c:
                float r3 = r7.f4257f
                java.lang.Float r3 = java.lang.Float.valueOf(r3)
                r4 = 0
                java.lang.Float r4 = java.lang.Float.valueOf(r4)
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
                if (r3 != 0) goto L_0x00a2
            L_0x009d:
                float r3 = r7.f4257f
                r6.encodeFloatElement(r5, r1, r3)
            L_0x00a2:
                r1 = 6
                boolean r3 = r6.shouldEncodeElementDefault(r5, r1)
                if (r3 == 0) goto L_0x00aa
                goto L_0x00ae
            L_0x00aa:
                java.lang.String r3 = r7.f4258g
                if (r3 == 0) goto L_0x00b5
            L_0x00ae:
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r4 = r7.f4258g
                r6.encodeNullableSerializableElement(r5, r1, r3, r4)
            L_0x00b5:
                r1 = 7
                boolean r3 = r6.shouldEncodeElementDefault(r5, r1)
                if (r3 == 0) goto L_0x00bd
                goto L_0x00c1
            L_0x00bd:
                boolean r3 = r7.f4259h
                if (r3 == 0) goto L_0x00c6
            L_0x00c1:
                boolean r3 = r7.f4259h
                r6.encodeBooleanElement(r5, r1, r3)
            L_0x00c6:
                r1 = 8
                boolean r3 = r6.shouldEncodeElementDefault(r5, r1)
                if (r3 == 0) goto L_0x00cf
                goto L_0x00d3
            L_0x00cf:
                java.lang.String r3 = r7.f4260i
                if (r3 == 0) goto L_0x00da
            L_0x00d3:
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r4 = r7.f4260i
                r6.encodeNullableSerializableElement(r5, r1, r3, r4)
            L_0x00da:
                r1 = 9
                boolean r3 = r6.shouldEncodeElementDefault(r5, r1)
                if (r3 == 0) goto L_0x00e3
                goto L_0x00e7
            L_0x00e3:
                com.appsamurai.storyly.data.m r3 = r7.f4261j
                if (r3 == 0) goto L_0x00ee
            L_0x00e7:
                com.appsamurai.storyly.data.m$a r3 = com.appsamurai.storyly.data.m.f3857b
                com.appsamurai.storyly.data.m r4 = r7.f4261j
                r6.encodeNullableSerializableElement(r5, r1, r3, r4)
            L_0x00ee:
                r1 = 10
                boolean r3 = r6.shouldEncodeElementDefault(r5, r1)
                if (r3 == 0) goto L_0x00f7
                goto L_0x00fb
            L_0x00f7:
                boolean r3 = r7.f4262k
                if (r3 == r2) goto L_0x0100
            L_0x00fb:
                boolean r3 = r7.f4262k
                r6.encodeBooleanElement(r5, r1, r3)
            L_0x0100:
                r1 = 11
                boolean r3 = r6.shouldEncodeElementDefault(r5, r1)
                if (r3 == 0) goto L_0x0109
                goto L_0x010d
            L_0x0109:
                boolean r3 = r7.f4263l
                if (r3 == r2) goto L_0x0112
            L_0x010d:
                boolean r2 = r7.f4263l
                r6.encodeBooleanElement(r5, r1, r2)
            L_0x0112:
                r1 = 12
                boolean r2 = r6.shouldEncodeElementDefault(r5, r1)
                if (r2 == 0) goto L_0x011b
                goto L_0x0125
            L_0x011b:
                java.lang.String r2 = r7.f4264m
                java.lang.String r3 = "Add to Cart"
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 != 0) goto L_0x012a
            L_0x0125:
                java.lang.String r2 = r7.f4264m
                r6.encodeStringElement(r5, r1, r2)
            L_0x012a:
                r1 = 13
                boolean r2 = r6.shouldEncodeElementDefault(r5, r1)
                if (r2 == 0) goto L_0x0133
                goto L_0x013d
            L_0x0133:
                java.lang.String r2 = r7.f4265n
                java.lang.String r3 = "Go to Cart"
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 != 0) goto L_0x0142
            L_0x013d:
                java.lang.String r2 = r7.f4265n
                r6.encodeStringElement(r5, r1, r2)
            L_0x0142:
                r1 = 14
                boolean r2 = r6.shouldEncodeElementDefault(r5, r1)
                if (r2 == 0) goto L_0x014b
                goto L_0x0155
            L_0x014b:
                java.lang.String r2 = r7.f4266o
                java.lang.String r3 = "Continue with Stories"
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 != 0) goto L_0x015a
            L_0x0155:
                java.lang.String r2 = r7.f4266o
                r6.encodeStringElement(r5, r1, r2)
            L_0x015a:
                r1 = 15
                boolean r2 = r6.shouldEncodeElementDefault(r5, r1)
                if (r2 == 0) goto L_0x0163
                goto L_0x016d
            L_0x0163:
                java.lang.String r2 = r7.f4267p
                java.lang.String r3 = "Added to your Cart successfully"
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 != 0) goto L_0x0172
            L_0x016d:
                java.lang.String r2 = r7.f4267p
                r6.encodeStringElement(r5, r1, r2)
            L_0x0172:
                r1 = 16
                boolean r2 = r6.shouldEncodeElementDefault(r5, r1)
                if (r2 == 0) goto L_0x017b
                goto L_0x0185
            L_0x017b:
                java.lang.String r2 = r7.f4268q
                java.lang.String r3 = "Go to Checkout"
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 != 0) goto L_0x018a
            L_0x0185:
                java.lang.String r2 = r7.f4268q
                r6.encodeStringElement(r5, r1, r2)
            L_0x018a:
                r1 = 17
                boolean r2 = r6.shouldEncodeElementDefault(r5, r1)
                if (r2 == 0) goto L_0x0193
                goto L_0x019d
            L_0x0193:
                java.lang.String r2 = r7.f4269r
                java.lang.String r3 = "Total"
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 != 0) goto L_0x01a2
            L_0x019d:
                java.lang.String r2 = r7.f4269r
                r6.encodeStringElement(r5, r1, r2)
            L_0x01a2:
                r1 = 18
                boolean r2 = r6.shouldEncodeElementDefault(r5, r1)
                if (r2 == 0) goto L_0x01ab
                goto L_0x01af
            L_0x01ab:
                int r2 = r7.f4270s
                if (r2 == r0) goto L_0x01b4
            L_0x01af:
                int r0 = r7.f4270s
                r6.encodeIntElement(r5, r1, r0)
            L_0x01b4:
                r0 = 19
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                if (r1 == 0) goto L_0x01bd
                goto L_0x01d8
            L_0x01bd:
                com.appsamurai.storyly.data.x$b r1 = r7.f4271t
                com.appsamurai.storyly.data.c r2 = r7.f4255d
                if (r2 == 0) goto L_0x01c6
                com.appsamurai.storyly.data.x$b r2 = com.appsamurai.storyly.data.x.b.Color
                goto L_0x01d6
            L_0x01c6:
                java.util.List<com.appsamurai.storyly.data.c> r2 = r7.f4256e
                if (r2 == 0) goto L_0x01cd
                com.appsamurai.storyly.data.x$b r2 = com.appsamurai.storyly.data.x.b.Gradient
                goto L_0x01d6
            L_0x01cd:
                java.lang.String r2 = r7.f4254c
                if (r2 == 0) goto L_0x01d4
                com.appsamurai.storyly.data.x$b r2 = com.appsamurai.storyly.data.x.b.ImagePath
                goto L_0x01d6
            L_0x01d4:
                com.appsamurai.storyly.data.x$b r2 = com.appsamurai.storyly.data.x.b.ImageUrl
            L_0x01d6:
                if (r1 == r2) goto L_0x01e8
            L_0x01d8:
                kotlinx.serialization.internal.EnumSerializer r1 = new kotlinx.serialization.internal.EnumSerializer
                com.appsamurai.storyly.data.x$b[] r2 = com.appsamurai.storyly.data.x.b.values()
                java.lang.String r3 = "com.appsamurai.storyly.data.StorylyImageLayer.ImageSourceType"
                r1.<init>(r3, r2)
                com.appsamurai.storyly.data.x$b r7 = r7.f4271t
                r6.encodeSerializableElement(r5, r0, r1, r7)
            L_0x01e8:
                r6.endStructure(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.x.a.serialize(kotlinx.serialization.encoding.Encoder, java.lang.Object):void");
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public enum b {
        Color,
        Gradient,
        ImageUrl,
        ImagePath
    }

    public x() {
        this(0, (String) null, (String) null, (c) null, (List) null, 0.0f, (String) null, false, (String) null, (m) null, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 524287);
    }

    @NotNull
    public String a() {
        return this.f4268q;
    }

    @Nullable
    public m c() {
        return this.f4261j;
    }

    @NotNull
    public String d() {
        return this.f4264m;
    }

    @NotNull
    public String e() {
        return this.f4266o;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return this.f4252a == xVar.f4252a && Intrinsics.areEqual((Object) this.f4253b, (Object) xVar.f4253b) && Intrinsics.areEqual((Object) this.f4254c, (Object) xVar.f4254c) && Intrinsics.areEqual((Object) this.f4255d, (Object) xVar.f4255d) && Intrinsics.areEqual((Object) this.f4256e, (Object) xVar.f4256e) && Intrinsics.areEqual((Object) Float.valueOf(this.f4257f), (Object) Float.valueOf(xVar.f4257f)) && Intrinsics.areEqual((Object) this.f4258g, (Object) xVar.f4258g) && this.f4259h == xVar.f4259h && Intrinsics.areEqual((Object) this.f4260i, (Object) xVar.f4260i) && Intrinsics.areEqual((Object) this.f4261j, (Object) xVar.f4261j) && this.f4262k == xVar.f4262k && this.f4263l == xVar.f4263l && Intrinsics.areEqual((Object) this.f4264m, (Object) xVar.f4264m) && Intrinsics.areEqual((Object) this.f4265n, (Object) xVar.f4265n) && Intrinsics.areEqual((Object) this.f4266o, (Object) xVar.f4266o) && Intrinsics.areEqual((Object) this.f4267p, (Object) xVar.f4267p) && Intrinsics.areEqual((Object) this.f4268q, (Object) xVar.f4268q) && Intrinsics.areEqual((Object) this.f4269r, (Object) xVar.f4269r) && this.f4270s == xVar.f4270s;
    }

    @NotNull
    public String f() {
        return this.f4265n;
    }

    @NotNull
    public String g() {
        return this.f4267p;
    }

    @NotNull
    public String h() {
        return this.f4269r;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f4252a) * 31;
        String str = this.f4253b;
        int i3 = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f4254c;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        c cVar = this.f4255d;
        int hashCode4 = (hashCode3 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        List<c> list = this.f4256e;
        int c3 = android.support.v4.media.session.a.c(this.f4257f, (hashCode4 + (list == null ? 0 : list.hashCode())) * 31, 31);
        String str3 = this.f4258g;
        int hashCode5 = (c3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        boolean z2 = this.f4259h;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (hashCode5 + (z2 ? 1 : 0)) * 31;
        String str4 = this.f4260i;
        int hashCode6 = (i4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        m mVar = this.f4261j;
        if (mVar != null) {
            i3 = mVar.hashCode();
        }
        int i5 = (hashCode6 + i3) * 31;
        boolean z4 = this.f4262k;
        if (z4) {
            z4 = true;
        }
        int i6 = (i5 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f4263l;
        if (!z5) {
            z3 = z5;
        }
        return Integer.hashCode(this.f4270s) + androidx.compose.animation.core.a.i(this.f4269r, androidx.compose.animation.core.a.i(this.f4268q, androidx.compose.animation.core.a.i(this.f4267p, androidx.compose.animation.core.a.i(this.f4266o, androidx.compose.animation.core.a.i(this.f4265n, androidx.compose.animation.core.a.i(this.f4264m, (i6 + (z3 ? 1 : 0)) * 31, 31), 31), 31), 31), 31), 31);
    }

    public boolean i() {
        return this.f4263l;
    }

    public boolean j() {
        return this.f4262k;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyImageLayer(contentMode=");
        sb.append(this.f4252a);
        sb.append(", imageUrl=");
        sb.append(this.f4253b);
        sb.append(", imagePath=");
        sb.append(this.f4254c);
        sb.append(", backgroundColor=");
        sb.append(this.f4255d);
        sb.append(", gradientColors=");
        sb.append(this.f4256e);
        sb.append(", borderRadius=");
        sb.append(this.f4257f);
        sb.append(", actionUrl=");
        sb.append(this.f4258g);
        sb.append(", isBackground=");
        sb.append(this.f4259h);
        sb.append(", altText=");
        sb.append(this.f4260i);
        sb.append(", productData=");
        sb.append(this.f4261j);
        sb.append(", isProductSalesPriceVisible=");
        sb.append(this.f4262k);
        sb.append(", isProductPriceVisible=");
        sb.append(this.f4263l);
        sb.append(", purchaseButtonText=");
        sb.append(this.f4264m);
        sb.append(", successButtonCartText=");
        sb.append(this.f4265n);
        sb.append(", successButtonBackText=");
        sb.append(this.f4266o);
        sb.append(", successMessage=");
        sb.append(this.f4267p);
        sb.append(", checkoutButtonText=");
        sb.append(this.f4268q);
        sb.append(", totalText=");
        sb.append(this.f4269r);
        sb.append(", maxVariantCount=");
        return android.support.v4.media.session.a.p(sb, this.f4270s, ')');
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ x(int i3, @SerialName("content_mode") int i4, @SerialName("image_url") String str, @SerialName("image_path") String str2, @SerialName("bg_color") c cVar, @SerialName("gradient_colors") List list, @SerialName("border_radius") float f2, @SerialName("outlink") String str3, @SerialName("is_bg") boolean z2, @SerialName("alt_text") String str4, @SerialName("products") m mVar, @SerialName("is_s_price_visible") boolean z3, @SerialName("is_price_visible") boolean z4, @SerialName("p_b_text") String str5, @SerialName("s_b_cart_text") String str6, @SerialName("s_b_back_text") String str7, @SerialName("s_message") String str8, @SerialName("checkout_b_text") String str9, @SerialName("t_text") String str10, @SerialName("max_v") int i5, b bVar) {
        b bVar2;
        int i6 = i3;
        if ((i6 & 1) == 0) {
            this.f4252a = 1;
        } else {
            this.f4252a = i4;
        }
        if ((i6 & 2) == 0) {
            this.f4253b = null;
        } else {
            this.f4253b = str;
        }
        if ((i6 & 4) == 0) {
            this.f4254c = null;
        } else {
            this.f4254c = str2;
        }
        if ((i6 & 8) == 0) {
            this.f4255d = null;
        } else {
            this.f4255d = cVar;
        }
        if ((i6 & 16) == 0) {
            this.f4256e = null;
        } else {
            this.f4256e = list;
        }
        this.f4257f = (i6 & 32) == 0 ? 0.0f : f2;
        if ((i6 & 64) == 0) {
            this.f4258g = null;
        } else {
            this.f4258g = str3;
        }
        this.f4259h = (i6 & 128) == 0 ? false : z2;
        if ((i6 & 256) == 0) {
            this.f4260i = null;
        } else {
            this.f4260i = str4;
        }
        if ((i6 & 512) == 0) {
            this.f4261j = null;
        } else {
            this.f4261j = mVar;
        }
        if ((i6 & 1024) == 0) {
            this.f4262k = true;
        } else {
            this.f4262k = z3;
        }
        if ((i6 & 2048) == 0) {
            this.f4263l = true;
        } else {
            this.f4263l = z4;
        }
        this.f4264m = (i6 & 4096) == 0 ? "Add to Cart" : str5;
        this.f4265n = (i6 & 8192) == 0 ? "Go to Cart" : str6;
        this.f4266o = (i6 & 16384) == 0 ? "Continue with Stories" : str7;
        this.f4267p = (32768 & i6) == 0 ? "Added to your Cart successfully" : str8;
        this.f4268q = (65536 & i6) == 0 ? "Go to Checkout" : str9;
        this.f4269r = (131072 & i6) == 0 ? "Total" : str10;
        this.f4270s = (262144 & i6) == 0 ? 4 : i5;
        if ((i6 & 524288) != 0) {
            bVar2 = bVar;
        } else if (this.f4255d != null) {
            bVar2 = b.Color;
        } else if (this.f4256e != null) {
            bVar2 = b.Gradient;
        } else if (this.f4254c != null) {
            bVar2 = b.ImagePath;
        } else {
            bVar2 = b.ImageUrl;
        }
        this.f4271t = bVar2;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.Image);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ x(int r14, java.lang.String r15, java.lang.String r16, com.appsamurai.storyly.data.c r17, java.util.List r18, float r19, java.lang.String r20, boolean r21, java.lang.String r22, com.appsamurai.storyly.data.m r23, boolean r24, boolean r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, java.lang.String r31, int r32, int r33) {
        /*
            r13 = this;
            r0 = r33
            r1 = r0 & 1
            r2 = 1
            if (r1 == 0) goto L_0x0009
            r1 = r2
            goto L_0x000a
        L_0x0009:
            r1 = r14
        L_0x000a:
            r3 = r0 & 32
            if (r3 == 0) goto L_0x0010
            r3 = 0
            goto L_0x0012
        L_0x0010:
            r3 = r19
        L_0x0012:
            r4 = r0 & 128(0x80, float:1.794E-43)
            if (r4 == 0) goto L_0x0018
            r4 = 0
            goto L_0x001a
        L_0x0018:
            r4 = r21
        L_0x001a:
            r5 = r0 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto L_0x0020
            r5 = r2
            goto L_0x0022
        L_0x0020:
            r5 = r24
        L_0x0022:
            r6 = r0 & 2048(0x800, float:2.87E-42)
            if (r6 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r2 = r25
        L_0x0029:
            r6 = r0 & 4096(0x1000, float:5.74E-42)
            r7 = 0
            if (r6 == 0) goto L_0x0031
            java.lang.String r6 = "Add to Cart"
            goto L_0x0032
        L_0x0031:
            r6 = r7
        L_0x0032:
            r8 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r8 == 0) goto L_0x0039
            java.lang.String r8 = "Go to Cart"
            goto L_0x003a
        L_0x0039:
            r8 = r7
        L_0x003a:
            r9 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r9 == 0) goto L_0x0041
            java.lang.String r9 = "Continue with Stories"
            goto L_0x0042
        L_0x0041:
            r9 = r7
        L_0x0042:
            r10 = 32768(0x8000, float:4.5918E-41)
            r10 = r10 & r0
            if (r10 == 0) goto L_0x004b
            java.lang.String r10 = "Added to your Cart successfully"
            goto L_0x004c
        L_0x004b:
            r10 = r7
        L_0x004c:
            r11 = 65536(0x10000, float:9.18355E-41)
            r11 = r11 & r0
            if (r11 == 0) goto L_0x0054
            java.lang.String r11 = "Go to Checkout"
            goto L_0x0055
        L_0x0054:
            r11 = r7
        L_0x0055:
            r12 = 131072(0x20000, float:1.83671E-40)
            r12 = r12 & r0
            if (r12 == 0) goto L_0x005c
            java.lang.String r7 = "Total"
        L_0x005c:
            r12 = 262144(0x40000, float:3.67342E-40)
            r0 = r0 & r12
            if (r0 == 0) goto L_0x0063
            r0 = 4
            goto L_0x0065
        L_0x0063:
            r0 = r32
        L_0x0065:
            r12 = 0
            r16 = r12
            r17 = r12
            r18 = r12
            r19 = r12
            r21 = r12
            r23 = r12
            r24 = r12
            r14 = r13
            r15 = r1
            r20 = r3
            r22 = r4
            r25 = r5
            r26 = r2
            r27 = r6
            r28 = r8
            r29 = r9
            r30 = r10
            r31 = r11
            r32 = r7
            r33 = r0
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.x.<init>(int, java.lang.String, java.lang.String, com.appsamurai.storyly.data.c, java.util.List, float, java.lang.String, boolean, java.lang.String, com.appsamurai.storyly.data.m, boolean, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int):void");
    }

    public x(int i3, @Nullable String str, @Nullable String str2, @Nullable c cVar, @Nullable List<c> list, float f2, @Nullable String str3, boolean z2, @Nullable String str4, @Nullable m mVar, boolean z3, boolean z4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, @NotNull String str9, @NotNull String str10, int i4) {
        b bVar;
        String str11 = str2;
        c cVar2 = cVar;
        List<c> list2 = list;
        String str12 = str5;
        String str13 = str6;
        String str14 = str7;
        String str15 = str8;
        String str16 = str9;
        String str17 = str10;
        Intrinsics.checkNotNullParameter(str12, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str13, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str14, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str15, "successMessage");
        Intrinsics.checkNotNullParameter(str16, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str17, "totalText");
        this.f4252a = i3;
        this.f4253b = str;
        this.f4254c = str11;
        this.f4255d = cVar2;
        this.f4256e = list2;
        this.f4257f = f2;
        this.f4258g = str3;
        this.f4259h = z2;
        this.f4260i = str4;
        this.f4261j = mVar;
        this.f4262k = z3;
        this.f4263l = z4;
        this.f4264m = str12;
        this.f4265n = str13;
        this.f4266o = str14;
        this.f4267p = str15;
        this.f4268q = str16;
        this.f4269r = str17;
        this.f4270s = i4;
        if (cVar2 != null) {
            bVar = b.Color;
        } else if (list2 != null) {
            bVar = b.Gradient;
        } else if (str11 != null) {
            bVar = b.ImagePath;
        } else {
            bVar = b.ImageUrl;
        }
        this.f4271t = bVar;
    }
}
