package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryImageQuizComponent;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class y extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<String> f4279a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final String f4280b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public List<String> f4281c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public List<String> f4282d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final Integer f4283e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public final List<Integer> f4284f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public c f4285g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final c f4286h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public c f4287i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public c f4288j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public c f4289k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public c f4290l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public c f4291m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public c f4292n;

    /* renamed from: o  reason: collision with root package name */
    public final boolean f4293o;

    /* renamed from: p  reason: collision with root package name */
    public final boolean f4294p;

    /* renamed from: q  reason: collision with root package name */
    public final boolean f4295q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public final String f4296r;
    @Nullable

    /* renamed from: s  reason: collision with root package name */
    public final List<String> f4297s;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<y> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4298a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4299b;

        static {
            a aVar = new a();
            f4298a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyImageQuizLayer", aVar, 19);
            pluginGeneratedSerialDescriptor.addElement("q_o_images", false);
            pluginGeneratedSerialDescriptor.addElement("q_title", true);
            pluginGeneratedSerialDescriptor.addElement("q_o_texts", true);
            pluginGeneratedSerialDescriptor.addElement("q_alt_texts", true);
            pluginGeneratedSerialDescriptor.addElement("q_answer", true);
            pluginGeneratedSerialDescriptor.addElement("q_o_votes", true);
            pluginGeneratedSerialDescriptor.addElement("q_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_title_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_title_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("w_o_f_b_color", true);
            pluginGeneratedSerialDescriptor.addElement("c_o_b_color", true);
            pluginGeneratedSerialDescriptor.addElement("w_o_i_b_color", true);
            pluginGeneratedSerialDescriptor.addElement("p_b_border_color", true);
            pluginGeneratedSerialDescriptor.addElement("i_q_b_color", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("is_result", true);
            pluginGeneratedSerialDescriptor.addElement("custom_payload", true);
            pluginGeneratedSerialDescriptor.addElement("options", true);
            f4299b = pluginGeneratedSerialDescriptor;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: kotlinx.serialization.KSerializer<?>[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        @org.jetbrains.annotations.NotNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public kotlinx.serialization.KSerializer<?>[] childSerializers() {
            /*
                r17 = this;
                kotlinx.serialization.internal.ArrayListSerializer r0 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r0.<init>(r1)
                kotlinx.serialization.KSerializer r2 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.internal.ArrayListSerializer r3 = new kotlinx.serialization.internal.ArrayListSerializer
                r3.<init>(r1)
                kotlinx.serialization.KSerializer r3 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r3)
                kotlinx.serialization.internal.ArrayListSerializer r4 = new kotlinx.serialization.internal.ArrayListSerializer
                r4.<init>(r1)
                kotlinx.serialization.KSerializer r4 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r4)
                kotlinx.serialization.internal.IntSerializer r5 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                kotlinx.serialization.KSerializer r6 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.internal.ArrayListSerializer r7 = new kotlinx.serialization.internal.ArrayListSerializer
                r7.<init>(r5)
                kotlinx.serialization.KSerializer r5 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                com.appsamurai.storyly.data.c$a r7 = com.appsamurai.storyly.data.c.f3622b
                kotlinx.serialization.KSerializer r8 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                kotlinx.serialization.KSerializer r9 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                kotlinx.serialization.KSerializer r10 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                kotlinx.serialization.KSerializer r11 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                kotlinx.serialization.KSerializer r12 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                kotlinx.serialization.KSerializer r13 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                kotlinx.serialization.KSerializer r14 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                kotlinx.serialization.KSerializer r7 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r7)
                kotlinx.serialization.KSerializer r15 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                r17 = r15
                kotlinx.serialization.internal.ArrayListSerializer r15 = new kotlinx.serialization.internal.ArrayListSerializer
                r15.<init>(r1)
                kotlinx.serialization.KSerializer r1 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r15)
                r15 = 19
                kotlinx.serialization.KSerializer[] r15 = new kotlinx.serialization.KSerializer[r15]
                r16 = 0
                r15[r16] = r0
                r0 = 1
                r15[r0] = r2
                r0 = 2
                r15[r0] = r3
                r0 = 3
                r15[r0] = r4
                r0 = 4
                r15[r0] = r6
                r0 = 5
                r15[r0] = r5
                r0 = 6
                r15[r0] = r8
                r0 = 7
                r15[r0] = r9
                r0 = 8
                r15[r0] = r10
                r0 = 9
                r15[r0] = r11
                r0 = 10
                r15[r0] = r12
                r0 = 11
                r15[r0] = r13
                r0 = 12
                r15[r0] = r14
                r0 = 13
                r15[r0] = r7
                kotlinx.serialization.internal.BooleanSerializer r0 = kotlinx.serialization.internal.BooleanSerializer.INSTANCE
                r2 = 14
                r15[r2] = r0
                r2 = 15
                r15[r2] = r0
                r2 = 16
                r15[r2] = r0
                r0 = 17
                r15[r0] = r17
                r0 = 18
                r15[r0] = r1
                return r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.y.a.childSerializers():kotlinx.serialization.KSerializer[]");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v3, resolved type: java.lang.Object} */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0112, code lost:
            r11 = r29;
            r12 = r30;
            r25 = r31;
            r24 = r32;
            r23 = r33;
            r22 = r34;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x012b, code lost:
            r27 = r2;
            r26 = r3;
            r2 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0155, code lost:
            r27 = r2;
            r26 = r3;
            r2 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x02fa, code lost:
            r8 = r8 | r2;
            r29 = r11;
            r30 = r12;
            r34 = r22;
            r33 = r23;
            r32 = r24;
            r31 = r25;
            r3 = r26;
            r2 = r27;
            r4 = 8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x030d, code lost:
            r11 = 9;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r45) {
            /*
                r44 = this;
                r0 = r45
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4299b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r11 = 9
                r12 = 7
                r13 = 6
                r14 = 5
                r15 = 3
                r4 = 8
                r3 = 4
                r5 = 2
                r6 = 1
                r7 = 0
                r8 = 0
                if (r2 == 0) goto L_0x00bb
                kotlinx.serialization.internal.ArrayListSerializer r2 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r9 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r2.<init>(r9)
                java.lang.Object r2 = r0.decodeSerializableElement(r1, r7, r2, r8)
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r9, r8)
                kotlinx.serialization.internal.ArrayListSerializer r7 = new kotlinx.serialization.internal.ArrayListSerializer
                r7.<init>(r9)
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r7, r8)
                kotlinx.serialization.internal.ArrayListSerializer r7 = new kotlinx.serialization.internal.ArrayListSerializer
                r7.<init>(r9)
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r15, r7, r8)
                kotlinx.serialization.internal.IntSerializer r15 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r15, r8)
                kotlinx.serialization.internal.ArrayListSerializer r10 = new kotlinx.serialization.internal.ArrayListSerializer
                r10.<init>(r15)
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r14, r10, r8)
                com.appsamurai.storyly.data.c$a r14 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r13, r14, r8)
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r12, r14, r8)
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r14, r8)
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r11, r14, r8)
                r15 = 10
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r15, r14, r8)
                r21 = r2
                r2 = 11
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r8)
                r20 = r2
                r2 = 12
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r8)
                r19 = r2
                r2 = 13
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r8)
                r14 = 14
                boolean r14 = r0.decodeBooleanElement(r1, r14)
                r8 = 15
                boolean r8 = r0.decodeBooleanElement(r1, r8)
                r17 = r2
                r2 = 16
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r16 = r2
                r45 = r7
                r2 = 17
                r7 = 0
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r9, r7)
                r18 = r2
                kotlinx.serialization.internal.ArrayListSerializer r2 = new kotlinx.serialization.internal.ArrayListSerializer
                r2.<init>(r9)
                r9 = 18
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r9, r2, r7)
                r7 = 524287(0x7ffff, float:7.34683E-40)
                r23 = r7
                r39 = r8
                r38 = r14
                r40 = r16
                r7 = r45
                goto L_0x035e
            L_0x00bb:
                r2 = r7
                r7 = r8
                r8 = r2
                r36 = r8
                r37 = r36
                r38 = r6
                r3 = r7
                r5 = r3
                r6 = r5
                r9 = r6
                r10 = r9
                r13 = r10
                r14 = r13
                r15 = r14
                r29 = r15
                r30 = r29
                r31 = r30
                r32 = r31
                r33 = r32
                r34 = r33
                r35 = r34
                r2 = r35
                r7 = r37
            L_0x00de:
                if (r38 == 0) goto L_0x032b
                int r12 = r0.decodeElementIndex(r1)
                switch(r12) {
                    case -1: goto L_0x0311;
                    case 0: goto L_0x02d6;
                    case 1: goto L_0x02b7;
                    case 2: goto L_0x0293;
                    case 3: goto L_0x026e;
                    case 4: goto L_0x0250;
                    case 5: goto L_0x022c;
                    case 6: goto L_0x020b;
                    case 7: goto L_0x01ea;
                    case 8: goto L_0x01c9;
                    case 9: goto L_0x01be;
                    case 10: goto L_0x019d;
                    case 11: goto L_0x017c;
                    case 12: goto L_0x015b;
                    case 13: goto L_0x0149;
                    case 14: goto L_0x013e;
                    case 15: goto L_0x0132;
                    case 16: goto L_0x0121;
                    case 17: goto L_0x0101;
                    case 18: goto L_0x00ed;
                    default: goto L_0x00e7;
                }
            L_0x00e7:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r12)
                throw r0
            L_0x00ed:
                kotlinx.serialization.internal.ArrayListSerializer r12 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r4 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r12.<init>(r4)
                r4 = 18
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r4, r12, r9)
                r12 = 262144(0x40000, float:3.67342E-40)
                r8 = r8 | r12
                r4 = 8
            L_0x00ff:
                r12 = 7
                goto L_0x00de
            L_0x0101:
                r4 = 18
                kotlinx.serialization.internal.StringSerializer r12 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r4 = 17
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r4, r12, r10)
                r12 = 131072(0x20000, float:1.83671E-40)
                r27 = r2
                r26 = r3
                r2 = r12
            L_0x0112:
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
            L_0x011e:
                r3 = 0
                goto L_0x02fa
            L_0x0121:
                r4 = 17
                r12 = 16
                boolean r7 = r0.decodeBooleanElement(r1, r12)
                r16 = 65536(0x10000, float:9.18355E-41)
            L_0x012b:
                r27 = r2
                r26 = r3
                r2 = r16
                goto L_0x0112
            L_0x0132:
                r4 = 15
                r12 = 16
                boolean r36 = r0.decodeBooleanElement(r1, r4)
                r16 = 32768(0x8000, float:4.5918E-41)
                goto L_0x012b
            L_0x013e:
                r4 = 14
                r12 = 16
                boolean r37 = r0.decodeBooleanElement(r1, r4)
                r16 = 16384(0x4000, float:2.2959E-41)
                goto L_0x012b
            L_0x0149:
                r12 = 16
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r12 = 13
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r12, r4, r2)
                r4 = 8192(0x2000, float:1.14794E-41)
            L_0x0155:
                r27 = r2
                r26 = r3
                r2 = r4
                goto L_0x0112
            L_0x015b:
                r12 = 13
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r12 = 12
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r12, r4, r6)
                r6 = 4096(0x1000, float:5.74E-42)
                r27 = r2
                r26 = r3
                r2 = r6
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r3 = 0
                r6 = r4
                goto L_0x02fa
            L_0x017c:
                r12 = 12
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r12 = 11
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r12, r4, r5)
                r5 = 2048(0x800, float:2.87E-42)
                r27 = r2
                r26 = r3
                r2 = r5
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r3 = 0
                r5 = r4
                goto L_0x02fa
            L_0x019d:
                r12 = 11
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r12 = 10
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r12, r4, r15)
                r15 = 1024(0x400, float:1.435E-42)
                r27 = r2
                r26 = r3
                r2 = r15
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r3 = 0
                r15 = r4
                goto L_0x02fa
            L_0x01be:
                r12 = 10
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r11, r4, r3)
                r4 = 512(0x200, float:7.175E-43)
                goto L_0x0155
            L_0x01c9:
                r12 = 10
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r11 = 8
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r11, r4, r14)
                r14 = 256(0x100, float:3.59E-43)
                r27 = r2
                r26 = r3
                r2 = r14
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r3 = 0
                r14 = r4
                goto L_0x02fa
            L_0x01ea:
                r11 = r4
                r12 = 10
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r11 = 7
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r11, r4, r13)
                r13 = 128(0x80, float:1.794E-43)
                r27 = r2
                r26 = r3
                r2 = r13
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r3 = 0
                r13 = r4
                goto L_0x02fa
            L_0x020b:
                r11 = 7
                r12 = 10
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r11 = r29
                r12 = 6
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r12, r4, r11)
                r11 = 64
                r27 = r2
                r26 = r3
                r2 = r11
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r3 = 0
                r11 = r4
                goto L_0x02fa
            L_0x022c:
                r11 = r29
                r12 = 6
                kotlinx.serialization.internal.ArrayListSerializer r4 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.IntSerializer r12 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r4.<init>(r12)
                r27 = r2
                r12 = r30
                r2 = 5
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r2, r4, r12)
                r12 = 32
                r26 = r3
                r2 = r12
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r3 = 0
                r12 = r4
                goto L_0x02fa
            L_0x0250:
                r27 = r2
                r11 = r29
                r12 = r30
                r2 = 5
                kotlinx.serialization.internal.IntSerializer r4 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r26 = r3
                r2 = r31
                r3 = 4
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r3, r4, r2)
                r25 = r2
                r24 = r32
                r23 = r33
                r22 = r34
                r2 = 16
                goto L_0x011e
            L_0x026e:
                r27 = r2
                r26 = r3
                r11 = r29
                r12 = r30
                r2 = r31
                r3 = 4
                kotlinx.serialization.internal.ArrayListSerializer r4 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r4.<init>(r3)
                r25 = r2
                r3 = r32
                r2 = 3
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r2, r4, r3)
                r24 = r3
                r23 = r33
                r22 = r34
                r2 = 8
                goto L_0x011e
            L_0x0293:
                r27 = r2
                r26 = r3
                r11 = r29
                r12 = r30
                r25 = r31
                r3 = r32
                r2 = 3
                kotlinx.serialization.internal.ArrayListSerializer r4 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r2 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r4.<init>(r2)
                r24 = r3
                r2 = r33
                r3 = 2
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r3, r4, r2)
                r23 = r2
                r22 = r34
                r2 = 4
                goto L_0x011e
            L_0x02b7:
                r27 = r2
                r26 = r3
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r2 = r33
                r3 = 2
                kotlinx.serialization.internal.StringSerializer r4 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r23 = r2
                r3 = r34
                r2 = 1
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r2, r4, r3)
                r22 = r3
                r2 = 2
                goto L_0x011e
            L_0x02d6:
                r27 = r2
                r26 = r3
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r3 = r34
                r2 = 1
                kotlinx.serialization.internal.ArrayListSerializer r4 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r2 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r4.<init>(r2)
                r22 = r3
                r2 = r35
                r3 = 0
                java.lang.Object r2 = r0.decodeSerializableElement(r1, r3, r4, r2)
                r35 = r2
                r2 = 1
            L_0x02fa:
                r8 = r8 | r2
                r29 = r11
                r30 = r12
                r34 = r22
                r33 = r23
                r32 = r24
                r31 = r25
                r3 = r26
                r2 = r27
                r4 = 8
            L_0x030d:
                r11 = 9
                goto L_0x00ff
            L_0x0311:
                r27 = r2
                r26 = r3
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r2 = r35
                r3 = 0
                r38 = r3
                r3 = r26
                r2 = r27
                goto L_0x030d
            L_0x032b:
                r27 = r2
                r26 = r3
                r11 = r29
                r12 = r30
                r25 = r31
                r24 = r32
                r23 = r33
                r22 = r34
                r2 = r35
                r21 = r2
                r20 = r5
                r19 = r6
                r40 = r7
                r2 = r9
                r18 = r10
                r10 = r12
                r12 = r13
                r4 = r14
                r6 = r22
                r5 = r23
                r7 = r24
                r3 = r25
                r17 = r27
                r39 = r36
                r38 = r37
                r23 = r8
                r13 = r11
                r11 = r26
            L_0x035e:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.y r0 = new com.appsamurai.storyly.data.y
                r22 = r0
                r24 = r21
                java.util.List r24 = (java.util.List) r24
                r25 = r6
                java.lang.String r25 = (java.lang.String) r25
                r26 = r5
                java.util.List r26 = (java.util.List) r26
                r27 = r7
                java.util.List r27 = (java.util.List) r27
                r28 = r3
                java.lang.Integer r28 = (java.lang.Integer) r28
                r29 = r10
                java.util.List r29 = (java.util.List) r29
                r30 = r13
                com.appsamurai.storyly.data.c r30 = (com.appsamurai.storyly.data.c) r30
                r31 = r12
                com.appsamurai.storyly.data.c r31 = (com.appsamurai.storyly.data.c) r31
                r32 = r4
                com.appsamurai.storyly.data.c r32 = (com.appsamurai.storyly.data.c) r32
                r33 = r11
                com.appsamurai.storyly.data.c r33 = (com.appsamurai.storyly.data.c) r33
                r34 = r15
                com.appsamurai.storyly.data.c r34 = (com.appsamurai.storyly.data.c) r34
                r35 = r20
                com.appsamurai.storyly.data.c r35 = (com.appsamurai.storyly.data.c) r35
                r36 = r19
                com.appsamurai.storyly.data.c r36 = (com.appsamurai.storyly.data.c) r36
                r37 = r17
                com.appsamurai.storyly.data.c r37 = (com.appsamurai.storyly.data.c) r37
                r41 = r18
                java.lang.String r41 = (java.lang.String) r41
                r42 = r2
                java.util.List r42 = (java.util.List) r42
                r43 = 0
                r22.<init>(r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.y.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4299b;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:94:0x01a5, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) (r3 == null || r3.isEmpty()) ? r7.f4279a : r7.f4281c) == false) goto L_0x01a7;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void serialize(kotlinx.serialization.encoding.Encoder r6, java.lang.Object r7) {
            /*
                r5 = this;
                com.appsamurai.storyly.data.y r7 = (com.appsamurai.storyly.data.y) r7
                java.lang.String r5 = "encoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r5)
                java.lang.String r5 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r5)
                kotlinx.serialization.descriptors.SerialDescriptor r5 = f4299b
                kotlinx.serialization.encoding.CompositeEncoder r6 = r6.beginStructure(r5)
                java.lang.String r0 = "self"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.String r0 = "output"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "serialDesc"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                com.appsamurai.storyly.data.a0.a(r7, r6, r5)
                kotlinx.serialization.internal.ArrayListSerializer r0 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r0.<init>(r1)
                java.util.List<java.lang.String> r2 = r7.f4279a
                r3 = 0
                r6.encodeSerializableElement(r5, r3, r0, r2)
                r0 = 1
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x003a
                goto L_0x0044
            L_0x003a:
                java.lang.String r2 = r7.f4280b
                java.lang.String r3 = ""
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 != 0) goto L_0x0049
            L_0x0044:
                java.lang.String r2 = r7.f4280b
                r6.encodeNullableSerializableElement(r5, r0, r1, r2)
            L_0x0049:
                r2 = 2
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x0051
                goto L_0x0055
            L_0x0051:
                java.util.List<java.lang.String> r3 = r7.f4281c
                if (r3 == 0) goto L_0x005f
            L_0x0055:
                kotlinx.serialization.internal.ArrayListSerializer r3 = new kotlinx.serialization.internal.ArrayListSerializer
                r3.<init>(r1)
                java.util.List<java.lang.String> r4 = r7.f4281c
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x005f:
                r2 = 3
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x0067
                goto L_0x006b
            L_0x0067:
                java.util.List<java.lang.String> r3 = r7.f4282d
                if (r3 == 0) goto L_0x0075
            L_0x006b:
                kotlinx.serialization.internal.ArrayListSerializer r3 = new kotlinx.serialization.internal.ArrayListSerializer
                r3.<init>(r1)
                java.util.List<java.lang.String> r4 = r7.f4282d
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x0075:
                r2 = 4
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x007d
                goto L_0x0081
            L_0x007d:
                java.lang.Integer r3 = r7.f4283e
                if (r3 == 0) goto L_0x0088
            L_0x0081:
                kotlinx.serialization.internal.IntSerializer r3 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                java.lang.Integer r4 = r7.f4283e
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x0088:
                r2 = 5
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x0090
                goto L_0x0094
            L_0x0090:
                java.util.List<java.lang.Integer> r3 = r7.f4284f
                if (r3 == 0) goto L_0x00a0
            L_0x0094:
                kotlinx.serialization.internal.ArrayListSerializer r3 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.IntSerializer r4 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r3.<init>(r4)
                java.util.List<java.lang.Integer> r4 = r7.f4284f
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x00a0:
                r2 = 6
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x00a8
                goto L_0x00ac
            L_0x00a8:
                com.appsamurai.storyly.data.c r3 = r7.f4285g
                if (r3 == 0) goto L_0x00b3
            L_0x00ac:
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r4 = r7.f4285g
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x00b3:
                r2 = 7
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x00bb
                goto L_0x00bf
            L_0x00bb:
                com.appsamurai.storyly.data.c r3 = r7.f4286h
                if (r3 == 0) goto L_0x00c6
            L_0x00bf:
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r4 = r7.f4286h
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x00c6:
                r2 = 8
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x00cf
                goto L_0x00d3
            L_0x00cf:
                com.appsamurai.storyly.data.c r3 = r7.f4287i
                if (r3 == 0) goto L_0x00da
            L_0x00d3:
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r4 = r7.f4287i
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x00da:
                r2 = 9
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x00e3
                goto L_0x00e7
            L_0x00e3:
                com.appsamurai.storyly.data.c r3 = r7.f4288j
                if (r3 == 0) goto L_0x00ee
            L_0x00e7:
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r4 = r7.f4288j
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x00ee:
                r2 = 10
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x00f7
                goto L_0x00fb
            L_0x00f7:
                com.appsamurai.storyly.data.c r3 = r7.f4289k
                if (r3 == 0) goto L_0x0102
            L_0x00fb:
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r4 = r7.f4289k
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x0102:
                r2 = 11
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x010b
                goto L_0x010f
            L_0x010b:
                com.appsamurai.storyly.data.c r3 = r7.f4290l
                if (r3 == 0) goto L_0x0116
            L_0x010f:
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r4 = r7.f4290l
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x0116:
                r2 = 12
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x011f
                goto L_0x0123
            L_0x011f:
                com.appsamurai.storyly.data.c r3 = r7.f4291m
                if (r3 == 0) goto L_0x012a
            L_0x0123:
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r4 = r7.f4291m
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x012a:
                r2 = 13
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x0133
                goto L_0x0137
            L_0x0133:
                com.appsamurai.storyly.data.c r3 = r7.f4292n
                if (r3 == 0) goto L_0x013e
            L_0x0137:
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r4 = r7.f4292n
                r6.encodeNullableSerializableElement(r5, r2, r3, r4)
            L_0x013e:
                r2 = 14
                boolean r3 = r6.shouldEncodeElementDefault(r5, r2)
                if (r3 == 0) goto L_0x0147
                goto L_0x014b
            L_0x0147:
                boolean r3 = r7.f4293o
                if (r3 == r0) goto L_0x0150
            L_0x014b:
                boolean r0 = r7.f4293o
                r6.encodeBooleanElement(r5, r2, r0)
            L_0x0150:
                r0 = 15
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0159
                goto L_0x015d
            L_0x0159:
                boolean r2 = r7.f4294p
                if (r2 == 0) goto L_0x0162
            L_0x015d:
                boolean r2 = r7.f4294p
                r6.encodeBooleanElement(r5, r0, r2)
            L_0x0162:
                r0 = 16
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x016b
                goto L_0x016f
            L_0x016b:
                boolean r2 = r7.f4295q
                if (r2 == 0) goto L_0x0174
            L_0x016f:
                boolean r2 = r7.f4295q
                r6.encodeBooleanElement(r5, r0, r2)
            L_0x0174:
                r0 = 17
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x017d
                goto L_0x0181
            L_0x017d:
                java.lang.String r2 = r7.f4296r
                if (r2 == 0) goto L_0x0186
            L_0x0181:
                java.lang.String r2 = r7.f4296r
                r6.encodeNullableSerializableElement(r5, r0, r1, r2)
            L_0x0186:
                r0 = 18
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x018f
                goto L_0x01a7
            L_0x018f:
                java.util.List<java.lang.String> r2 = r7.f4297s
                java.util.List<java.lang.String> r3 = r7.f4281c
                if (r3 == 0) goto L_0x019f
                boolean r3 = r3.isEmpty()
                if (r3 == 0) goto L_0x019c
                goto L_0x019f
            L_0x019c:
                java.util.List<java.lang.String> r3 = r7.f4281c
                goto L_0x01a1
            L_0x019f:
                java.util.List<java.lang.String> r3 = r7.f4279a
            L_0x01a1:
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 != 0) goto L_0x01b1
            L_0x01a7:
                kotlinx.serialization.internal.ArrayListSerializer r2 = new kotlinx.serialization.internal.ArrayListSerializer
                r2.<init>(r1)
                java.util.List<java.lang.String> r7 = r7.f4297s
                r6.encodeNullableSerializableElement(r5, r0, r2, r7)
            L_0x01b1:
                r6.endStructure(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.y.a.serialize(kotlinx.serialization.encoding.Encoder, java.lang.Object):void");
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ y(int i3, @Required @SerialName("q_o_images") List list, @SerialName("q_title") String str, @SerialName("q_o_texts") List list2, @SerialName("q_alt_texts") List list3, @SerialName("q_answer") Integer num, @SerialName("q_o_votes") List list4, @SerialName("q_bg_color") c cVar, @SerialName("q_title_color") c cVar2, @SerialName("q_title_bg_color") c cVar3, @SerialName("w_o_f_b_color") c cVar4, @SerialName("c_o_b_color") c cVar5, @SerialName("w_o_i_b_color") c cVar6, @SerialName("p_b_border_color") c cVar7, @SerialName("i_q_b_color") c cVar8, @SerialName("is_bold") boolean z2, @SerialName("is_italic") boolean z3, @SerialName("is_result") boolean z4, @SerialName("custom_payload") String str2, List list5, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        List list6;
        int i4 = i3;
        if (1 != (i4 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 1, a.f4298a.getDescriptor());
        }
        this.f4279a = list;
        this.f4280b = (i4 & 2) == 0 ? "" : str;
        if ((i4 & 4) == 0) {
            this.f4281c = null;
        } else {
            this.f4281c = list2;
        }
        if ((i4 & 8) == 0) {
            this.f4282d = null;
        } else {
            this.f4282d = list3;
        }
        if ((i4 & 16) == 0) {
            this.f4283e = null;
        } else {
            this.f4283e = num;
        }
        if ((i4 & 32) == 0) {
            this.f4284f = null;
        } else {
            this.f4284f = list4;
        }
        if ((i4 & 64) == 0) {
            this.f4285g = null;
        } else {
            this.f4285g = cVar;
        }
        if ((i4 & 128) == 0) {
            this.f4286h = null;
        } else {
            this.f4286h = cVar2;
        }
        if ((i4 & 256) == 0) {
            this.f4287i = null;
        } else {
            this.f4287i = cVar3;
        }
        if ((i4 & 512) == 0) {
            this.f4288j = null;
        } else {
            this.f4288j = cVar4;
        }
        if ((i4 & 1024) == 0) {
            this.f4289k = null;
        } else {
            this.f4289k = cVar5;
        }
        if ((i4 & 2048) == 0) {
            this.f4290l = null;
        } else {
            this.f4290l = cVar6;
        }
        if ((i4 & 4096) == 0) {
            this.f4291m = null;
        } else {
            this.f4291m = cVar7;
        }
        if ((i4 & 8192) == 0) {
            this.f4292n = null;
        } else {
            this.f4292n = cVar8;
        }
        if ((i4 & 16384) == 0) {
            this.f4293o = true;
        } else {
            this.f4293o = z2;
        }
        if ((32768 & i4) == 0) {
            this.f4294p = false;
        } else {
            this.f4294p = z3;
        }
        if ((65536 & i4) == 0) {
            this.f4295q = false;
        } else {
            this.f4295q = z4;
        }
        if ((131072 & i4) == 0) {
            this.f4296r = null;
        } else {
            this.f4296r = str2;
        }
        if ((i4 & 262144) == 0) {
            List<String> list7 = this.f4281c;
            list6 = (list7 == null || list7.isEmpty()) ? this.f4279a : this.f4281c;
        } else {
            list6 = list5;
        }
        this.f4297s = list6;
    }

    public static y a(y yVar, List list, String str, List list2, List list3, Integer num, List list4, c cVar, c cVar2, c cVar3, c cVar4, c cVar5, c cVar6, c cVar7, c cVar8, boolean z2, boolean z3, boolean z4, String str2, int i3) {
        y yVar2 = yVar;
        int i4 = i3;
        List<String> list5 = (i4 & 1) != 0 ? yVar2.f4279a : null;
        String str3 = (i4 & 2) != 0 ? yVar2.f4280b : null;
        List<String> list6 = (i4 & 4) != 0 ? yVar2.f4281c : null;
        List<String> list7 = (i4 & 8) != 0 ? yVar2.f4282d : null;
        Integer num2 = (i4 & 16) != 0 ? yVar2.f4283e : null;
        List<Integer> list8 = (i4 & 32) != 0 ? yVar2.f4284f : null;
        c cVar9 = (i4 & 64) != 0 ? yVar2.f4285g : null;
        c cVar10 = (i4 & 128) != 0 ? yVar2.f4286h : null;
        c cVar11 = (i4 & 256) != 0 ? yVar2.f4287i : null;
        c cVar12 = (i4 & 512) != 0 ? yVar2.f4288j : null;
        c cVar13 = (i4 & 1024) != 0 ? yVar2.f4289k : null;
        c cVar14 = (i4 & 2048) != 0 ? yVar2.f4290l : null;
        c cVar15 = (i4 & 4096) != 0 ? yVar2.f4291m : null;
        c cVar16 = (i4 & 8192) != 0 ? yVar2.f4292n : null;
        boolean z5 = (i4 & 16384) != 0 ? yVar2.f4293o : z2;
        boolean z6 = (i4 & 32768) != 0 ? yVar2.f4294p : z3;
        boolean z7 = (i4 & 65536) != 0 ? yVar2.f4295q : z4;
        String str4 = (i4 & 131072) != 0 ? yVar2.f4296r : null;
        yVar.getClass();
        Intrinsics.checkNotNullParameter(list5, "imageUrlList");
        return new y(list5, str3, list6, list7, num2, list8, cVar9, cVar10, cVar11, cVar12, cVar13, cVar14, cVar15, cVar16, z5, z6, z7, str4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return Intrinsics.areEqual((Object) this.f4279a, (Object) yVar.f4279a) && Intrinsics.areEqual((Object) this.f4280b, (Object) yVar.f4280b) && Intrinsics.areEqual((Object) this.f4281c, (Object) yVar.f4281c) && Intrinsics.areEqual((Object) this.f4282d, (Object) yVar.f4282d) && Intrinsics.areEqual((Object) this.f4283e, (Object) yVar.f4283e) && Intrinsics.areEqual((Object) this.f4284f, (Object) yVar.f4284f) && Intrinsics.areEqual((Object) this.f4285g, (Object) yVar.f4285g) && Intrinsics.areEqual((Object) this.f4286h, (Object) yVar.f4286h) && Intrinsics.areEqual((Object) this.f4287i, (Object) yVar.f4287i) && Intrinsics.areEqual((Object) this.f4288j, (Object) yVar.f4288j) && Intrinsics.areEqual((Object) this.f4289k, (Object) yVar.f4289k) && Intrinsics.areEqual((Object) this.f4290l, (Object) yVar.f4290l) && Intrinsics.areEqual((Object) this.f4291m, (Object) yVar.f4291m) && Intrinsics.areEqual((Object) this.f4292n, (Object) yVar.f4292n) && this.f4293o == yVar.f4293o && this.f4294p == yVar.f4294p && this.f4295q == yVar.f4295q && Intrinsics.areEqual((Object) this.f4296r, (Object) yVar.f4296r);
    }

    public int hashCode() {
        int hashCode = this.f4279a.hashCode() * 31;
        String str = this.f4280b;
        int i3 = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<String> list = this.f4281c;
        int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.f4282d;
        int hashCode4 = (hashCode3 + (list2 == null ? 0 : list2.hashCode())) * 31;
        Integer num = this.f4283e;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        List<Integer> list3 = this.f4284f;
        int hashCode6 = (hashCode5 + (list3 == null ? 0 : list3.hashCode())) * 31;
        c cVar = this.f4285g;
        int hashCode7 = (hashCode6 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f4286h;
        int hashCode8 = (hashCode7 + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f4287i;
        int hashCode9 = (hashCode8 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f4288j;
        int hashCode10 = (hashCode9 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f4289k;
        int hashCode11 = (hashCode10 + (cVar5 == null ? 0 : Integer.hashCode(cVar5.f3624a))) * 31;
        c cVar6 = this.f4290l;
        int hashCode12 = (hashCode11 + (cVar6 == null ? 0 : Integer.hashCode(cVar6.f3624a))) * 31;
        c cVar7 = this.f4291m;
        int hashCode13 = (hashCode12 + (cVar7 == null ? 0 : Integer.hashCode(cVar7.f3624a))) * 31;
        c cVar8 = this.f4292n;
        int hashCode14 = (hashCode13 + (cVar8 == null ? 0 : Integer.hashCode(cVar8.f3624a))) * 31;
        boolean z2 = this.f4293o;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (hashCode14 + (z2 ? 1 : 0)) * 31;
        boolean z4 = this.f4294p;
        if (z4) {
            z4 = true;
        }
        int i5 = (i4 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f4295q;
        if (!z5) {
            z3 = z5;
        }
        int i6 = (i5 + (z3 ? 1 : 0)) * 31;
        String str2 = this.f4296r;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return i6 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyImageQuizLayer(imageUrlList=" + this.f4279a + ", quizTitle=" + this.f4280b + ", optionTextList=" + this.f4281c + ", altTextList=" + this.f4282d + ", quizAnswer=" + this.f4283e + ", quizOptionVoteCounts=" + this.f4284f + ", quizBgColor=" + this.f4285g + ", quizTitleColor=" + this.f4286h + ", quizTitleBgColor=" + this.f4287i + ", wrongOptionFinalBorderColor=" + this.f4288j + ", correctOptionBorderColor=" + this.f4289k + ", wrongOptionInitialBorderColor=" + this.f4290l + ", pollBarBorderColor=" + this.f4291m + ", imageQuizBorderColor=" + this.f4292n + ", isBold=" + this.f4293o + ", isItalic=" + this.f4294p + ", isResult=" + this.f4295q + ", customPayload=" + this.f4296r + ')';
    }

    public StoryComponent a(b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryImageQuizComponent(b0Var.f3614i, this.f4280b, this.f4297s, this.f4283e, -1, this.f4296r);
    }

    public y(@NotNull List<String> list, @Nullable String str, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable Integer num, @Nullable List<Integer> list4, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, @Nullable c cVar6, @Nullable c cVar7, @Nullable c cVar8, boolean z2, boolean z3, boolean z4, @Nullable String str2) {
        List<String> list5 = list2;
        Intrinsics.checkNotNullParameter(list, "imageUrlList");
        this.f4279a = list;
        this.f4280b = str;
        this.f4281c = list5;
        this.f4282d = list3;
        this.f4283e = num;
        this.f4284f = list4;
        this.f4285g = cVar;
        this.f4286h = cVar2;
        this.f4287i = cVar3;
        this.f4288j = cVar4;
        this.f4289k = cVar5;
        this.f4290l = cVar6;
        this.f4291m = cVar7;
        this.f4292n = cVar8;
        this.f4293o = z2;
        this.f4294p = z3;
        this.f4295q = z4;
        this.f4296r = str2;
        this.f4297s = (list5 == null || list2.isEmpty()) ? this.f4279a : this.f4281c;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var, int i3) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryImageQuizComponent(b0Var.f3614i, this.f4280b, this.f4297s, this.f4283e, i3, this.f4296r);
    }
}
