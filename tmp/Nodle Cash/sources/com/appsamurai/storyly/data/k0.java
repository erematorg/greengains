package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryQuizComponent;
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
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class k0 extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3807a;

    /* renamed from: b  reason: collision with root package name */
    public final float f3808b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final String f3809c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final List<String> f3810d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final List<Integer> f3811e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public final Integer f3812f;

    /* renamed from: g  reason: collision with root package name */
    public final int f3813g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f3814h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public c f3815i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final c f3816j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final c f3817k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public final c f3818l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public final c f3819m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public final c f3820n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public final c f3821o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public final c f3822p;
    @Nullable

    /* renamed from: q  reason: collision with root package name */
    public final c f3823q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public final c f3824r;
    @Nullable

    /* renamed from: s  reason: collision with root package name */
    public final c f3825s;

    /* renamed from: t  reason: collision with root package name */
    public final boolean f3826t;

    /* renamed from: u  reason: collision with root package name */
    public final boolean f3827u;

    /* renamed from: v  reason: collision with root package name */
    public final boolean f3828v;

    /* renamed from: w  reason: collision with root package name */
    public final boolean f3829w;

    /* renamed from: x  reason: collision with root package name */
    public final boolean f3830x;
    @Nullable

    /* renamed from: y  reason: collision with root package name */
    public final String f3831y;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<k0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3832a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3833b;

        static {
            a aVar = new a();
            f3832a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyQuizLayer", aVar, 25);
            pluginGeneratedSerialDescriptor.addElement("theme", false);
            pluginGeneratedSerialDescriptor.addElement("o_h", false);
            pluginGeneratedSerialDescriptor.addElement("q_text", false);
            pluginGeneratedSerialDescriptor.addElement("q_o_texts", false);
            pluginGeneratedSerialDescriptor.addElement("q_o_votes", true);
            pluginGeneratedSerialDescriptor.addElement("q_answer", true);
            pluginGeneratedSerialDescriptor.addElement("scale", true);
            pluginGeneratedSerialDescriptor.addElement("has_title", true);
            pluginGeneratedSerialDescriptor.addElement("q_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_text_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_text_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_o_text_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_o_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_o_border_color", true);
            pluginGeneratedSerialDescriptor.addElement("w_answer_color", true);
            pluginGeneratedSerialDescriptor.addElement("r_answer_color", true);
            pluginGeneratedSerialDescriptor.addElement("percent_bar_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_s_o_border_color", true);
            pluginGeneratedSerialDescriptor.addElement("q_border_color", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("q_option_is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("q_option_is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("is_result", true);
            pluginGeneratedSerialDescriptor.addElement("custom_payload", true);
            f3833b = pluginGeneratedSerialDescriptor;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: kotlinx.serialization.KSerializer<?>[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        @org.jetbrains.annotations.NotNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public kotlinx.serialization.KSerializer<?>[] childSerializers() {
            /*
                r19 = this;
                kotlinx.serialization.internal.StringSerializer r0 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                kotlinx.serialization.internal.ArrayListSerializer r1 = new kotlinx.serialization.internal.ArrayListSerializer
                r1.<init>(r0)
                kotlinx.serialization.internal.ArrayListSerializer r2 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.IntSerializer r3 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r2.<init>(r3)
                kotlinx.serialization.KSerializer r2 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r2)
                kotlinx.serialization.KSerializer r4 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r3)
                com.appsamurai.storyly.data.c$a r5 = com.appsamurai.storyly.data.c.f3622b
                kotlinx.serialization.KSerializer r6 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r7 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r8 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r9 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r10 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r11 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r12 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r13 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r14 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r15 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r5 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r5)
                kotlinx.serialization.KSerializer r16 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r0)
                r19 = r5
                r5 = 25
                kotlinx.serialization.KSerializer[] r5 = new kotlinx.serialization.KSerializer[r5]
                r17 = 0
                r5[r17] = r0
                kotlinx.serialization.internal.FloatSerializer r17 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                r18 = 1
                r5[r18] = r17
                r17 = 2
                r5[r17] = r0
                r0 = 3
                r5[r0] = r1
                r0 = 4
                r5[r0] = r2
                r0 = 5
                r5[r0] = r4
                r0 = 6
                r5[r0] = r3
                kotlinx.serialization.internal.BooleanSerializer r0 = kotlinx.serialization.internal.BooleanSerializer.INSTANCE
                r1 = 7
                r5[r1] = r0
                r1 = 8
                r5[r1] = r6
                r1 = 9
                r5[r1] = r7
                r1 = 10
                r5[r1] = r8
                r1 = 11
                r5[r1] = r9
                r1 = 12
                r5[r1] = r10
                r1 = 13
                r5[r1] = r11
                r1 = 14
                r5[r1] = r12
                r1 = 15
                r5[r1] = r13
                r1 = 16
                r5[r1] = r14
                r1 = 17
                r5[r1] = r15
                r1 = 18
                r5[r1] = r19
                r1 = 19
                r5[r1] = r0
                r1 = 20
                r5[r1] = r0
                r1 = 21
                r5[r1] = r0
                r1 = 22
                r5[r1] = r0
                r1 = 23
                r5[r1] = r0
                r0 = 24
                r5[r0] = r16
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.k0.a.childSerializers():kotlinx.serialization.KSerializer[]");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: java.lang.Object} */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0153, code lost:
            r27 = r41;
            r30 = r48;
            r31 = r49;
            r10 = r10;
            r8 = r8;
            r6 = r6;
            r3 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0159, code lost:
            r10 = r10;
            r8 = r8;
            r6 = r6;
            r3 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x015a, code lost:
            r10 = r10;
            r8 = r8;
            r6 = r6;
            r3 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x01ad, code lost:
            r32 = r2;
            r12 = r11;
            r10 = r10;
            r8 = r8;
            r6 = r6;
            r3 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0323, code lost:
            r25 = r25 | r12;
            r41 = r27;
            r12 = r30;
            r11 = r31;
            r10 = r10;
            r8 = r8;
            r6 = r6;
            r3 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x032b, code lost:
            r2 = r32;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0121, code lost:
            r10 = r10;
            r8 = r8;
            r6 = r6;
            r3 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0121, code lost:
            r10 = r10;
            r8 = r8;
            r6 = r6;
            r3 = r3;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r52) {
            /*
                r51 = this;
                r0 = r52
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3833b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
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
                if (r2 == 0) goto L_0x00f6
                java.lang.String r2 = r0.decodeStringElement(r1, r9)
                float r8 = r0.decodeFloatElement(r1, r8)
                java.lang.String r7 = r0.decodeStringElement(r1, r7)
                kotlinx.serialization.internal.ArrayListSerializer r9 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r9.<init>(r11)
                java.lang.Object r4 = r0.decodeSerializableElement(r1, r4, r9, r10)
                kotlinx.serialization.internal.ArrayListSerializer r9 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.IntSerializer r12 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r9.<init>(r12)
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r9, r10)
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r12, r10)
                int r9 = r0.decodeIntElement(r1, r15)
                boolean r12 = r0.decodeBooleanElement(r1, r14)
                com.appsamurai.storyly.data.c$a r14 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r14, r10)
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r13, r14, r10)
                r15 = 10
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r15, r14, r10)
                r24 = r2
                r2 = 11
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r10)
                r23 = r2
                r2 = 12
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r10)
                r22 = r2
                r2 = 13
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r10)
                r21 = r2
                r2 = 14
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r10)
                r20 = r2
                r2 = 15
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r10)
                r19 = r2
                r2 = 16
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r10)
                r18 = r2
                r2 = 17
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r10)
                r17 = r2
                r2 = 18
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r14, r10)
                r14 = 19
                boolean r14 = r0.decodeBooleanElement(r1, r14)
                r10 = 20
                boolean r10 = r0.decodeBooleanElement(r1, r10)
                r51 = r2
                r2 = 21
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r52 = r2
                r2 = 22
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r16 = r2
                r2 = 23
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r26 = r2
                r2 = 24
                r27 = r9
                r9 = 0
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r9)
                r9 = 33554431(0x1ffffff, float:9.403954E-38)
                r43 = r52
                r31 = r3
                r42 = r10
                r29 = r12
                r41 = r14
                r44 = r16
                r3 = r20
                r14 = r22
                r22 = r24
                r45 = r26
                r28 = r27
                r10 = r6
                r24 = r7
                r7 = r15
                r15 = r21
                r6 = r23
                r23 = r8
                r21 = r9
                r8 = r51
                goto L_0x0366
            L_0x00f6:
                r2 = r9
                r9 = r10
                r10 = 0
                r25 = r2
                r26 = r25
                r37 = r26
                r38 = r37
                r39 = r38
                r44 = r39
                r45 = r44
                r46 = r45
                r47 = r8
                r2 = r9
                r3 = r2
                r4 = r3
                r5 = r4
                r6 = r5
                r7 = r6
                r8 = r7
                r11 = r8
                r12 = r11
                r13 = r12
                r14 = r13
                r15 = r14
                r41 = r15
                r42 = r41
                r43 = r42
                r40 = r10
                r10 = r43
            L_0x0121:
                if (r47 == 0) goto L_0x033d
                r48 = r12
                int r12 = r0.decodeElementIndex(r1)
                switch(r12) {
                    case -1: goto L_0x032f;
                    case 0: goto L_0x0310;
                    case 1: goto L_0x02fb;
                    case 2: goto L_0x02e6;
                    case 3: goto L_0x02c8;
                    case 4: goto L_0x02ab;
                    case 5: goto L_0x0291;
                    case 6: goto L_0x0280;
                    case 7: goto L_0x026f;
                    case 8: goto L_0x025f;
                    case 9: goto L_0x024f;
                    case 10: goto L_0x0234;
                    case 11: goto L_0x0224;
                    case 12: goto L_0x0209;
                    case 13: goto L_0x01ee;
                    case 14: goto L_0x01df;
                    case 15: goto L_0x01cf;
                    case 16: goto L_0x01c0;
                    case 17: goto L_0x01b1;
                    case 18: goto L_0x019f;
                    case 19: goto L_0x0188;
                    case 20: goto L_0x017b;
                    case 21: goto L_0x016c;
                    case 22: goto L_0x015d;
                    case 23: goto L_0x0145;
                    case 24: goto L_0x0132;
                    default: goto L_0x012c;
                }
            L_0x012c:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r12)
                throw r0
            L_0x0132:
                kotlinx.serialization.internal.StringSerializer r12 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r49 = r11
                r11 = 24
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r11, r12, r2)
                r11 = 16777216(0x1000000, float:2.3509887E-38)
                r25 = r25 | r11
                r12 = r48
                r11 = r49
                goto L_0x0121
            L_0x0145:
                r49 = r11
                r11 = 23
                boolean r11 = r0.decodeBooleanElement(r1, r11)
                r12 = 8388608(0x800000, float:1.17549435E-38)
                r32 = r2
                r39 = r11
            L_0x0153:
                r27 = r41
                r30 = r48
                r31 = r49
            L_0x0159:
                r2 = 0
            L_0x015a:
                r11 = 2
                goto L_0x0323
            L_0x015d:
                r49 = r11
                r11 = 22
                boolean r11 = r0.decodeBooleanElement(r1, r11)
                r12 = 4194304(0x400000, float:5.877472E-39)
                r32 = r2
                r38 = r11
                goto L_0x0153
            L_0x016c:
                r49 = r11
                r11 = 21
                boolean r11 = r0.decodeBooleanElement(r1, r11)
                r12 = 2097152(0x200000, float:2.938736E-39)
                r32 = r2
                r37 = r11
                goto L_0x0153
            L_0x017b:
                r49 = r11
                r11 = 20
                boolean r44 = r0.decodeBooleanElement(r1, r11)
                r12 = 1048576(0x100000, float:1.469368E-39)
                r32 = r2
                goto L_0x0153
            L_0x0188:
                r49 = r11
                r11 = 20
                r12 = 19
                boolean r46 = r0.decodeBooleanElement(r1, r12)
                r50 = 524288(0x80000, float:7.34684E-40)
                r32 = r2
                r27 = r41
                r30 = r48
                r31 = r49
                r12 = r50
                goto L_0x0159
            L_0x019f:
                r49 = r11
                r12 = 19
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 18
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r12, r11, r8)
                r11 = 262144(0x40000, float:3.67342E-40)
            L_0x01ad:
                r32 = r2
                r12 = r11
                goto L_0x0153
            L_0x01b1:
                r49 = r11
                r12 = 18
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 17
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r12, r11, r7)
                r11 = 131072(0x20000, float:1.83671E-40)
                goto L_0x01ad
            L_0x01c0:
                r49 = r11
                r12 = 17
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 16
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r12, r11, r4)
                r11 = 65536(0x10000, float:9.18355E-41)
                goto L_0x01ad
            L_0x01cf:
                r49 = r11
                r12 = 16
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 15
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r12, r11, r5)
                r11 = 32768(0x8000, float:4.5918E-41)
                goto L_0x01ad
            L_0x01df:
                r49 = r11
                r12 = 15
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 14
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r12, r11, r3)
                r11 = 16384(0x4000, float:2.2959E-41)
                goto L_0x01ad
            L_0x01ee:
                r49 = r11
                r12 = 14
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 13
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r12, r11, r15)
                r15 = 8192(0x2000, float:1.14794E-41)
                r32 = r2
                r12 = r15
                r27 = r41
                r30 = r48
                r31 = r49
                r2 = 0
                r15 = r11
                goto L_0x015a
            L_0x0209:
                r49 = r11
                r12 = 13
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 12
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r12, r11, r14)
                r14 = 4096(0x1000, float:5.74E-42)
                r32 = r2
                r12 = r14
                r27 = r41
                r30 = r48
                r31 = r49
                r2 = 0
                r14 = r11
                goto L_0x015a
            L_0x0224:
                r49 = r11
                r12 = 12
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 11
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r12, r11, r6)
                r11 = 2048(0x800, float:2.87E-42)
                goto L_0x01ad
            L_0x0234:
                r49 = r11
                r12 = 11
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 10
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r12, r11, r13)
                r13 = 1024(0x400, float:1.435E-42)
                r32 = r2
                r12 = r13
                r27 = r41
                r30 = r48
                r31 = r49
                r2 = 0
                r13 = r11
                goto L_0x015a
            L_0x024f:
                r49 = r11
                r12 = 10
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 9
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r12, r11, r9)
                r11 = 512(0x200, float:7.175E-43)
                goto L_0x01ad
            L_0x025f:
                r49 = r11
                r12 = 9
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r12 = 8
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r12, r11, r10)
                r11 = 256(0x100, float:3.59E-43)
                goto L_0x01ad
            L_0x026f:
                r49 = r11
                r11 = 7
                r12 = 8
                boolean r45 = r0.decodeBooleanElement(r1, r11)
                r34 = 128(0x80, float:1.794E-43)
                r32 = r2
                r12 = r34
                goto L_0x0153
            L_0x0280:
                r49 = r11
                r11 = 6
                r12 = 8
                int r26 = r0.decodeIntElement(r1, r11)
                r33 = 64
                r32 = r2
                r12 = r33
                goto L_0x0153
            L_0x0291:
                r49 = r11
                r12 = 8
                kotlinx.serialization.internal.IntSerializer r11 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r32 = r2
                r2 = r49
                r12 = 5
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r12, r11, r2)
                r11 = 32
                r31 = r2
                r12 = r11
                r27 = r41
                r30 = r48
                goto L_0x0159
            L_0x02ab:
                r32 = r2
                r2 = r11
                r12 = 5
                kotlinx.serialization.internal.ArrayListSerializer r11 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.IntSerializer r12 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r11.<init>(r12)
                r31 = r2
                r2 = r48
                r12 = 4
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r12, r11, r2)
                r30 = r2
                r27 = r41
                r2 = 0
                r11 = 2
                r12 = 16
                goto L_0x0323
            L_0x02c8:
                r32 = r2
                r31 = r11
                r2 = r48
                r12 = 4
                kotlinx.serialization.internal.ArrayListSerializer r11 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.StringSerializer r12 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r11.<init>(r12)
                r30 = r2
                r12 = r41
                r2 = 3
                java.lang.Object r11 = r0.decodeSerializableElement(r1, r2, r11, r12)
                r27 = r11
                r2 = 0
                r11 = 2
                r12 = 8
                goto L_0x0323
            L_0x02e6:
                r32 = r2
                r31 = r11
                r12 = r41
                r30 = r48
                r2 = 3
                r11 = 2
                java.lang.String r29 = r0.decodeStringElement(r1, r11)
                r27 = r12
                r43 = r29
                r2 = 0
                r12 = 4
                goto L_0x0323
            L_0x02fb:
                r32 = r2
                r31 = r11
                r12 = r41
                r30 = r48
                r2 = 1
                r11 = 2
                float r28 = r0.decodeFloatElement(r1, r2)
                r27 = r12
                r40 = r28
                r2 = 0
                r12 = r11
                goto L_0x0323
            L_0x0310:
                r32 = r2
                r31 = r11
                r12 = r41
                r30 = r48
                r2 = 0
                r11 = 2
                java.lang.String r27 = r0.decodeStringElement(r1, r2)
                r42 = r27
                r27 = r12
                r12 = 1
            L_0x0323:
                r25 = r25 | r12
                r41 = r27
                r12 = r30
                r11 = r31
            L_0x032b:
                r2 = r32
                goto L_0x0121
            L_0x032f:
                r32 = r2
                r31 = r11
                r12 = r41
                r30 = r48
                r2 = 0
                r47 = r2
                r12 = r30
                goto L_0x032b
            L_0x033d:
                r32 = r2
                r31 = r11
                r30 = r12
                r12 = r41
                r18 = r4
                r19 = r5
                r17 = r7
                r4 = r12
                r7 = r13
                r21 = r25
                r28 = r26
                r5 = r30
                r23 = r40
                r22 = r42
                r24 = r43
                r42 = r44
                r29 = r45
                r41 = r46
                r13 = r9
                r43 = r37
                r44 = r38
                r45 = r39
            L_0x0366:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.k0 r0 = new com.appsamurai.storyly.data.k0
                r20 = r0
                r25 = r4
                java.util.List r25 = (java.util.List) r25
                r26 = r5
                java.util.List r26 = (java.util.List) r26
                r27 = r31
                java.lang.Integer r27 = (java.lang.Integer) r27
                r30 = r10
                com.appsamurai.storyly.data.c r30 = (com.appsamurai.storyly.data.c) r30
                r31 = r13
                com.appsamurai.storyly.data.c r31 = (com.appsamurai.storyly.data.c) r31
                r32 = r7
                com.appsamurai.storyly.data.c r32 = (com.appsamurai.storyly.data.c) r32
                r33 = r6
                com.appsamurai.storyly.data.c r33 = (com.appsamurai.storyly.data.c) r33
                r34 = r14
                com.appsamurai.storyly.data.c r34 = (com.appsamurai.storyly.data.c) r34
                r35 = r15
                com.appsamurai.storyly.data.c r35 = (com.appsamurai.storyly.data.c) r35
                r36 = r3
                com.appsamurai.storyly.data.c r36 = (com.appsamurai.storyly.data.c) r36
                r37 = r19
                com.appsamurai.storyly.data.c r37 = (com.appsamurai.storyly.data.c) r37
                r38 = r18
                com.appsamurai.storyly.data.c r38 = (com.appsamurai.storyly.data.c) r38
                r39 = r17
                com.appsamurai.storyly.data.c r39 = (com.appsamurai.storyly.data.c) r39
                r40 = r8
                com.appsamurai.storyly.data.c r40 = (com.appsamurai.storyly.data.c) r40
                r46 = r2
                java.lang.String r46 = (java.lang.String) r46
                r47 = 0
                r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.k0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3833b;
        }

        public void serialize(Encoder encoder, Object obj) {
            k0 k0Var = (k0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(k0Var, "value");
            SerialDescriptor serialDescriptor = f3833b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(k0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(k0Var, beginStructure, serialDescriptor);
            beginStructure.encodeStringElement(serialDescriptor, 0, k0Var.f3807a);
            beginStructure.encodeFloatElement(serialDescriptor, 1, k0Var.f3808b);
            beginStructure.encodeStringElement(serialDescriptor, 2, k0Var.f3809c);
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            beginStructure.encodeSerializableElement(serialDescriptor, 3, new ArrayListSerializer(stringSerializer), k0Var.f3810d);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || k0Var.f3811e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, new ArrayListSerializer(IntSerializer.INSTANCE), k0Var.f3811e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || k0Var.f3812f != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 5, IntSerializer.INSTANCE, k0Var.f3812f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || k0Var.f3813g != 2) {
                beginStructure.encodeIntElement(serialDescriptor, 6, k0Var.f3813g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || !k0Var.f3814h) {
                beginStructure.encodeBooleanElement(serialDescriptor, 7, k0Var.f3814h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || k0Var.f3815i != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, c.f3622b, k0Var.f3815i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || k0Var.f3816j != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, c.f3622b, k0Var.f3816j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || k0Var.f3817k != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 10, c.f3622b, k0Var.f3817k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || k0Var.f3818l != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 11, c.f3622b, k0Var.f3818l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || k0Var.f3819m != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 12, c.f3622b, k0Var.f3819m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || k0Var.f3820n != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 13, c.f3622b, k0Var.f3820n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || k0Var.f3821o != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 14, c.f3622b, k0Var.f3821o);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 15) || k0Var.f3822p != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 15, c.f3622b, k0Var.f3822p);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 16) || k0Var.f3823q != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 16, c.f3622b, k0Var.f3823q);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 17) || k0Var.f3824r != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 17, c.f3622b, k0Var.f3824r);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 18) || k0Var.f3825s != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 18, c.f3622b, k0Var.f3825s);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 19) || !k0Var.f3826t) {
                beginStructure.encodeBooleanElement(serialDescriptor, 19, k0Var.f3826t);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 20) || k0Var.f3827u) {
                beginStructure.encodeBooleanElement(serialDescriptor, 20, k0Var.f3827u);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 21) || k0Var.f3828v) {
                beginStructure.encodeBooleanElement(serialDescriptor, 21, k0Var.f3828v);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 22) || k0Var.f3829w) {
                beginStructure.encodeBooleanElement(serialDescriptor, 22, k0Var.f3829w);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 23) || k0Var.f3830x) {
                beginStructure.encodeBooleanElement(serialDescriptor, 23, k0Var.f3830x);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 24) || k0Var.f3831y != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 24, stringSerializer, k0Var.f3831y);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ k0(int i3, @Required @SerialName("theme") String str, @Required @SerialName("o_h") float f2, @Required @SerialName("q_text") String str2, @Required @SerialName("q_o_texts") List list, @SerialName("q_o_votes") List list2, @SerialName("q_answer") Integer num, @SerialName("scale") int i4, @SerialName("has_title") boolean z2, @SerialName("q_bg_color") c cVar, @SerialName("q_text_color") c cVar2, @SerialName("q_text_bg_color") c cVar3, @SerialName("q_o_text_color") c cVar4, @SerialName("q_o_bg_color") c cVar5, @SerialName("q_o_border_color") c cVar6, @SerialName("w_answer_color") c cVar7, @SerialName("r_answer_color") c cVar8, @SerialName("percent_bar_color") c cVar9, @SerialName("q_s_o_border_color") c cVar10, @SerialName("q_border_color") c cVar11, @SerialName("is_bold") boolean z3, @SerialName("is_italic") boolean z4, @SerialName("q_option_is_bold") boolean z5, @SerialName("q_option_is_italic") boolean z6, @SerialName("is_result") boolean z7, @SerialName("custom_payload") String str3, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        int i5 = i3;
        if (15 != (i5 & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 15, a.f3832a.getDescriptor());
        }
        this.f3807a = str;
        this.f3808b = f2;
        this.f3809c = str2;
        this.f3810d = list;
        if ((i5 & 16) == 0) {
            this.f3811e = null;
        } else {
            this.f3811e = list2;
        }
        if ((i5 & 32) == 0) {
            this.f3812f = null;
        } else {
            this.f3812f = num;
        }
        this.f3813g = (i5 & 64) == 0 ? 2 : i4;
        if ((i5 & 128) == 0) {
            this.f3814h = true;
        } else {
            this.f3814h = z2;
        }
        if ((i5 & 256) == 0) {
            this.f3815i = null;
        } else {
            this.f3815i = cVar;
        }
        if ((i5 & 512) == 0) {
            this.f3816j = null;
        } else {
            this.f3816j = cVar2;
        }
        if ((i5 & 1024) == 0) {
            this.f3817k = null;
        } else {
            this.f3817k = cVar3;
        }
        if ((i5 & 2048) == 0) {
            this.f3818l = null;
        } else {
            this.f3818l = cVar4;
        }
        if ((i5 & 4096) == 0) {
            this.f3819m = null;
        } else {
            this.f3819m = cVar5;
        }
        if ((i5 & 8192) == 0) {
            this.f3820n = null;
        } else {
            this.f3820n = cVar6;
        }
        if ((i5 & 16384) == 0) {
            this.f3821o = null;
        } else {
            this.f3821o = cVar7;
        }
        if ((32768 & i5) == 0) {
            this.f3822p = null;
        } else {
            this.f3822p = cVar8;
        }
        if ((65536 & i5) == 0) {
            this.f3823q = null;
        } else {
            this.f3823q = cVar9;
        }
        if ((131072 & i5) == 0) {
            this.f3824r = null;
        } else {
            this.f3824r = cVar10;
        }
        if ((262144 & i5) == 0) {
            this.f3825s = null;
        } else {
            this.f3825s = cVar11;
        }
        if ((524288 & i5) == 0) {
            this.f3826t = true;
        } else {
            this.f3826t = z3;
        }
        if ((1048576 & i5) == 0) {
            this.f3827u = false;
        } else {
            this.f3827u = z4;
        }
        if ((2097152 & i5) == 0) {
            this.f3828v = false;
        } else {
            this.f3828v = z5;
        }
        if ((4194304 & i5) == 0) {
            this.f3829w = false;
        } else {
            this.f3829w = z6;
        }
        if ((8388608 & i5) == 0) {
            this.f3830x = false;
        } else {
            this.f3830x = z7;
        }
        if ((i5 & 16777216) == 0) {
            this.f3831y = null;
        } else {
            this.f3831y = str3;
        }
    }

    public StoryComponent a(b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryQuizComponent(b0Var.f3614i, this.f3809c, this.f3810d, this.f3812f, -1, this.f3831y);
    }

    @NotNull
    public final c b() {
        c cVar = this.f3820n;
        if (cVar != null) {
            return cVar;
        }
        return (Intrinsics.areEqual((Object) this.f3807a, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_6A6A6A : com.appsamurai.storyly.config.styling.a.COLOR_EFEFEF).b();
    }

    @NotNull
    public final c c() {
        c cVar = this.f3818l;
        return cVar == null ? Intrinsics.areEqual((Object) this.f3807a, (Object) "Dark") ? new c(-1) : com.appsamurai.storyly.config.styling.a.COLOR_262626.b() : cVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k0)) {
            return false;
        }
        k0 k0Var = (k0) obj;
        return Intrinsics.areEqual((Object) this.f3807a, (Object) k0Var.f3807a) && Intrinsics.areEqual((Object) Float.valueOf(this.f3808b), (Object) Float.valueOf(k0Var.f3808b)) && Intrinsics.areEqual((Object) this.f3809c, (Object) k0Var.f3809c) && Intrinsics.areEqual((Object) this.f3810d, (Object) k0Var.f3810d) && Intrinsics.areEqual((Object) this.f3811e, (Object) k0Var.f3811e) && Intrinsics.areEqual((Object) this.f3812f, (Object) k0Var.f3812f) && this.f3813g == k0Var.f3813g && this.f3814h == k0Var.f3814h && Intrinsics.areEqual((Object) this.f3815i, (Object) k0Var.f3815i) && Intrinsics.areEqual((Object) this.f3816j, (Object) k0Var.f3816j) && Intrinsics.areEqual((Object) this.f3817k, (Object) k0Var.f3817k) && Intrinsics.areEqual((Object) this.f3818l, (Object) k0Var.f3818l) && Intrinsics.areEqual((Object) this.f3819m, (Object) k0Var.f3819m) && Intrinsics.areEqual((Object) this.f3820n, (Object) k0Var.f3820n) && Intrinsics.areEqual((Object) this.f3821o, (Object) k0Var.f3821o) && Intrinsics.areEqual((Object) this.f3822p, (Object) k0Var.f3822p) && Intrinsics.areEqual((Object) this.f3823q, (Object) k0Var.f3823q) && Intrinsics.areEqual((Object) this.f3824r, (Object) k0Var.f3824r) && Intrinsics.areEqual((Object) this.f3825s, (Object) k0Var.f3825s) && this.f3826t == k0Var.f3826t && this.f3827u == k0Var.f3827u && this.f3828v == k0Var.f3828v && this.f3829w == k0Var.f3829w && this.f3830x == k0Var.f3830x && Intrinsics.areEqual((Object) this.f3831y, (Object) k0Var.f3831y);
    }

    public int hashCode() {
        int j2 = androidx.compose.animation.core.a.j(this.f3810d, androidx.compose.animation.core.a.i(this.f3809c, android.support.v4.media.session.a.c(this.f3808b, this.f3807a.hashCode() * 31, 31), 31), 31);
        List<Integer> list = this.f3811e;
        int i3 = 0;
        int hashCode = (j2 + (list == null ? 0 : list.hashCode())) * 31;
        Integer num = this.f3812f;
        int c3 = androidx.compose.animation.core.a.c(this.f3813g, (hashCode + (num == null ? 0 : num.hashCode())) * 31, 31);
        boolean z2 = this.f3814h;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (c3 + (z2 ? 1 : 0)) * 31;
        c cVar = this.f3815i;
        int hashCode2 = (i4 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f3816j;
        int hashCode3 = (hashCode2 + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f3817k;
        int hashCode4 = (hashCode3 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f3818l;
        int hashCode5 = (hashCode4 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f3819m;
        int hashCode6 = (hashCode5 + (cVar5 == null ? 0 : Integer.hashCode(cVar5.f3624a))) * 31;
        c cVar6 = this.f3820n;
        int hashCode7 = (hashCode6 + (cVar6 == null ? 0 : Integer.hashCode(cVar6.f3624a))) * 31;
        c cVar7 = this.f3821o;
        int hashCode8 = (hashCode7 + (cVar7 == null ? 0 : Integer.hashCode(cVar7.f3624a))) * 31;
        c cVar8 = this.f3822p;
        int hashCode9 = (hashCode8 + (cVar8 == null ? 0 : Integer.hashCode(cVar8.f3624a))) * 31;
        c cVar9 = this.f3823q;
        int hashCode10 = (hashCode9 + (cVar9 == null ? 0 : Integer.hashCode(cVar9.f3624a))) * 31;
        c cVar10 = this.f3824r;
        int hashCode11 = (hashCode10 + (cVar10 == null ? 0 : Integer.hashCode(cVar10.f3624a))) * 31;
        c cVar11 = this.f3825s;
        int hashCode12 = (hashCode11 + (cVar11 == null ? 0 : Integer.hashCode(cVar11.f3624a))) * 31;
        boolean z4 = this.f3826t;
        if (z4) {
            z4 = true;
        }
        int i5 = (hashCode12 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f3827u;
        if (z5) {
            z5 = true;
        }
        int i6 = (i5 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.f3828v;
        if (z6) {
            z6 = true;
        }
        int i7 = (i6 + (z6 ? 1 : 0)) * 31;
        boolean z7 = this.f3829w;
        if (z7) {
            z7 = true;
        }
        int i8 = (i7 + (z7 ? 1 : 0)) * 31;
        boolean z8 = this.f3830x;
        if (!z8) {
            z3 = z8;
        }
        int i9 = (i8 + (z3 ? 1 : 0)) * 31;
        String str = this.f3831y;
        if (str != null) {
            i3 = str.hashCode();
        }
        return i9 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyQuizLayer(theme=" + this.f3807a + ", optionsButtonHeight=" + this.f3808b + ", quizText=" + this.f3809c + ", quizOptionTexts=" + this.f3810d + ", quizOptionVoteCounts=" + this.f3811e + ", quizAnswer=" + this.f3812f + ", scale=" + this.f3813g + ", hasTitle=" + this.f3814h + ", quizBgColor=" + this.f3815i + ", quizTextColor=" + this.f3816j + ", quizTextBgColor=" + this.f3817k + ", quizOptionTextColor=" + this.f3818l + ", quizOptionBgColor=" + this.f3819m + ", quizOptionBorderColor=" + this.f3820n + ", wrongAnswerColor=" + this.f3821o + ", rightAnswerColor=" + this.f3822p + ", animatedPercentBarColor=" + this.f3823q + ", quizSelectedOptionBorderColor=" + this.f3824r + ", quizBorderColor=" + this.f3825s + ", isBold=" + this.f3826t + ", isItalic=" + this.f3827u + ", optionIsBold=" + this.f3828v + ", optionIsItalic=" + this.f3829w + ", isResult=" + this.f3830x + ", customPayload=" + this.f3831y + ')';
    }

    public k0(@NotNull String str, float f2, @NotNull String str2, @NotNull List<String> list, @Nullable List<Integer> list2, @Nullable Integer num, int i3, boolean z2, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, @Nullable c cVar6, @Nullable c cVar7, @Nullable c cVar8, @Nullable c cVar9, @Nullable c cVar10, @Nullable c cVar11, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(str, "theme");
        Intrinsics.checkNotNullParameter(str2, "quizText");
        Intrinsics.checkNotNullParameter(list, "quizOptionTexts");
        this.f3807a = str;
        this.f3808b = f2;
        this.f3809c = str2;
        this.f3810d = list;
        this.f3811e = list2;
        this.f3812f = num;
        this.f3813g = i3;
        this.f3814h = z2;
        this.f3815i = cVar;
        this.f3816j = cVar2;
        this.f3817k = cVar3;
        this.f3818l = cVar4;
        this.f3819m = cVar5;
        this.f3820n = cVar6;
        this.f3821o = cVar7;
        this.f3822p = cVar8;
        this.f3823q = cVar9;
        this.f3824r = cVar10;
        this.f3825s = cVar11;
        this.f3826t = z3;
        this.f3827u = z4;
        this.f3828v = z5;
        this.f3829w = z6;
        this.f3830x = z7;
        this.f3831y = str3;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var, int i3) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryQuizComponent(b0Var.f3614i, this.f3809c, this.f3810d, this.f3812f, i3, this.f3831y);
    }

    @NotNull
    public final c a() {
        c cVar = this.f3819m;
        if (cVar == null) {
            return Intrinsics.areEqual((Object) this.f3807a, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_434343.b() : new c(-1);
        }
        return cVar;
    }
}
