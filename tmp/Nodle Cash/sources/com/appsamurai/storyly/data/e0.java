package com.appsamurai.storyly.data;

import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryPollComponent;
import com.appsamurai.storyly.data.c;
import com.appsamurai.storyly.util.f;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class e0 extends a0 {
    @NotNull

    /* renamed from: A  reason: collision with root package name */
    public final c f3645A;

    /* renamed from: a  reason: collision with root package name */
    public int f3646a;

    /* renamed from: b  reason: collision with root package name */
    public int f3647b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final String f3648c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final String f3649d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final String f3650e;

    /* renamed from: f  reason: collision with root package name */
    public final float f3651f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final String f3652g;

    /* renamed from: h  reason: collision with root package name */
    public final int f3653h;

    /* renamed from: i  reason: collision with root package name */
    public final boolean f3654i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final c f3655j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final c f3656k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public c f3657l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public c f3658m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public final c f3659n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public final c f3660o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public final c f3661p;
    @Nullable

    /* renamed from: q  reason: collision with root package name */
    public final c f3662q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public final c f3663r;
    @Nullable

    /* renamed from: s  reason: collision with root package name */
    public final c f3664s;

    /* renamed from: t  reason: collision with root package name */
    public final boolean f3665t;

    /* renamed from: u  reason: collision with root package name */
    public final boolean f3666u;

    /* renamed from: v  reason: collision with root package name */
    public final boolean f3667v;

    /* renamed from: w  reason: collision with root package name */
    public final boolean f3668w;

    /* renamed from: x  reason: collision with root package name */
    public final boolean f3669x;
    @Nullable

    /* renamed from: y  reason: collision with root package name */
    public final String f3670y;
    @NotNull

    /* renamed from: z  reason: collision with root package name */
    public final c f3671z;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<e0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3672a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3673b;

        static {
            a aVar = new a();
            f3672a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyPollLayer", aVar, 27);
            pluginGeneratedSerialDescriptor.addElement("l_o_vote_count", false);
            pluginGeneratedSerialDescriptor.addElement("r_o_vote_count", false);
            pluginGeneratedSerialDescriptor.addElement("l_o_text", false);
            pluginGeneratedSerialDescriptor.addElement("r_o_text", false);
            pluginGeneratedSerialDescriptor.addElement("p_text", false);
            pluginGeneratedSerialDescriptor.addElement("o_h", false);
            pluginGeneratedSerialDescriptor.addElement("theme", true);
            pluginGeneratedSerialDescriptor.addElement("scale", true);
            pluginGeneratedSerialDescriptor.addElement("has_title", true);
            pluginGeneratedSerialDescriptor.addElement("primary_color", true);
            pluginGeneratedSerialDescriptor.addElement("secondary_color", true);
            pluginGeneratedSerialDescriptor.addElement("p_border_color", true);
            pluginGeneratedSerialDescriptor.addElement("p_middle_color", true);
            pluginGeneratedSerialDescriptor.addElement("p_text_color", true);
            pluginGeneratedSerialDescriptor.addElement("p_text_shadow_color", true);
            pluginGeneratedSerialDescriptor.addElement("l_o_text_color", true);
            pluginGeneratedSerialDescriptor.addElement("r_o_text_color", true);
            pluginGeneratedSerialDescriptor.addElement("o_percentage_color", true);
            pluginGeneratedSerialDescriptor.addElement("o_button_color", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("p_option_is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("p_option_is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("is_result", true);
            pluginGeneratedSerialDescriptor.addElement("custom_payload", true);
            pluginGeneratedSerialDescriptor.addElement("defaultPrimaryColor", true);
            pluginGeneratedSerialDescriptor.addElement("defaultSecondaryColor", true);
            f3673b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(stringSerializer);
            c.a aVar = c.f3622b;
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable6 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable7 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable8 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable9 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable10 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable11 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable12 = BuiltinSerializersKt.getNullable(stringSerializer);
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{intSerializer, intSerializer, stringSerializer, stringSerializer, stringSerializer, FloatSerializer.INSTANCE, nullable, intSerializer, booleanSerializer, nullable2, nullable3, nullable4, nullable5, nullable6, nullable7, nullable8, nullable9, nullable10, nullable11, booleanSerializer, booleanSerializer, booleanSerializer, booleanSerializer, booleanSerializer, nullable12, aVar, aVar};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x015a, code lost:
            r39 = r2;
            r2 = r10;
            r7 = r7;
            r4 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x015d, code lost:
            r30 = r29;
            r36 = r51;
            r7 = r7;
            r4 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0161, code lost:
            r29 = r27;
            r7 = r7;
            r4 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0165, code lost:
            r27 = r26;
            r26 = r25;
            r25 = r24;
            r24 = r15;
            r15 = r14;
            r14 = r13;
            r13 = r12;
            r12 = r50;
            r7 = r7;
            r4 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x022b, code lost:
            r7 = r7;
            r4 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x03f0, code lost:
            r28 = r28 | r2;
            r11 = r12;
            r12 = r13;
            r13 = r14;
            r14 = r15;
            r15 = r24;
            r24 = r25;
            r25 = r26;
            r26 = r27;
            r27 = r29;
            r29 = r30;
            r7 = r7;
            r4 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x0402, code lost:
            r10 = r36;
            r2 = r39;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x012a, code lost:
            r7 = r7;
            r4 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x012a, code lost:
            r7 = r7;
            r4 = r4;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r55) {
            /*
                r54 = this;
                r0 = r55
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3673b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
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
                if (r2 == 0) goto L_0x00fb
                int r2 = r0.decodeIntElement(r1, r9)
                int r8 = r0.decodeIntElement(r1, r8)
                java.lang.String r7 = r0.decodeStringElement(r1, r7)
                java.lang.String r4 = r0.decodeStringElement(r1, r4)
                java.lang.String r5 = r0.decodeStringElement(r1, r5)
                float r3 = r0.decodeFloatElement(r1, r3)
                kotlinx.serialization.internal.StringSerializer r9 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r15, r9, r10)
                int r14 = r0.decodeIntElement(r1, r14)
                boolean r6 = r0.decodeBooleanElement(r1, r6)
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r13, r11, r10)
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r12, r11, r10)
                r24 = r2
                r2 = 11
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r10)
                r23 = r2
                r2 = 12
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r10)
                r22 = r2
                r2 = 13
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r10)
                r21 = r2
                r2 = 14
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r10)
                r20 = r2
                r2 = 15
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r10)
                r19 = r2
                r2 = 16
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r10)
                r18 = r2
                r2 = 17
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r10)
                r17 = r2
                r2 = 18
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r11, r10)
                r10 = 19
                boolean r10 = r0.decodeBooleanElement(r1, r10)
                r55 = r2
                r2 = 20
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r54 = r2
                r2 = 21
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r16 = r2
                r2 = 22
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r26 = r2
                r2 = 23
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r27 = r2
                r2 = 24
                r28 = r8
                r8 = 0
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r9, r8)
                r9 = 25
                java.lang.Object r9 = r0.decodeSerializableElement(r1, r9, r11, r8)
                r25 = r2
                r2 = 26
                java.lang.Object r2 = r0.decodeSerializableElement(r1, r2, r11, r8)
                r8 = 134217727(0x7ffffff, float:3.8518597E-34)
                r46 = r54
                r31 = r3
                r29 = r4
                r30 = r5
                r34 = r6
                r45 = r10
                r33 = r14
                r3 = r15
                r47 = r16
                r15 = r19
                r14 = r20
                r48 = r26
                r49 = r27
                r27 = r28
                r4 = r55
                r28 = r7
                r26 = r24
                r7 = r25
                r25 = r8
                goto L_0x0446
            L_0x00fb:
                r2 = r8
                r8 = r10
                r10 = 0
                r49 = r2
                r2 = r8
                r3 = r2
                r4 = r3
                r5 = r4
                r6 = r5
                r7 = r6
                r11 = r7
                r12 = r11
                r13 = r12
                r14 = r13
                r15 = r14
                r44 = r15
                r45 = r44
                r46 = r45
                r24 = r9
                r25 = r24
                r27 = r25
                r28 = r27
                r29 = r28
                r41 = r29
                r42 = r41
                r43 = r42
                r47 = r43
                r48 = r47
                r26 = r10
                r9 = r46
                r10 = r9
            L_0x012a:
                if (r49 == 0) goto L_0x0414
                r50 = r11
                int r11 = r0.decodeElementIndex(r1)
                switch(r11) {
                    case -1: goto L_0x0408;
                    case 0: goto L_0x03d1;
                    case 1: goto L_0x03b2;
                    case 2: goto L_0x0391;
                    case 3: goto L_0x036e;
                    case 4: goto L_0x034b;
                    case 5: goto L_0x0328;
                    case 6: goto L_0x0303;
                    case 7: goto L_0x02ef;
                    case 8: goto L_0x02da;
                    case 9: goto L_0x02c1;
                    case 10: goto L_0x02b1;
                    case 11: goto L_0x028a;
                    case 12: goto L_0x0264;
                    case 13: goto L_0x0254;
                    case 14: goto L_0x022e;
                    case 15: goto L_0x0205;
                    case 16: goto L_0x01f5;
                    case 17: goto L_0x01e5;
                    case 18: goto L_0x01d5;
                    case 19: goto L_0x01c0;
                    case 20: goto L_0x01a7;
                    case 21: goto L_0x0197;
                    case 22: goto L_0x018c;
                    case 23: goto L_0x0181;
                    case 24: goto L_0x0174;
                    case 25: goto L_0x014e;
                    case 26: goto L_0x013b;
                    default: goto L_0x0135;
                }
            L_0x0135:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r11)
                throw r0
            L_0x013b:
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r51 = r10
                r10 = 26
                java.lang.Object r9 = r0.decodeSerializableElement(r1, r10, r11, r9)
                r10 = 67108864(0x4000000, float:1.5046328E-36)
                r28 = r28 | r10
                r11 = r50
                r10 = r51
                goto L_0x012a
            L_0x014e:
                r51 = r10
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 25
                java.lang.Object r2 = r0.decodeSerializableElement(r1, r11, r10, r2)
                r10 = 33554432(0x2000000, float:9.403955E-38)
            L_0x015a:
                r39 = r2
                r2 = r10
            L_0x015d:
                r30 = r29
                r36 = r51
            L_0x0161:
                r10 = 0
                r11 = 6
                r29 = r27
            L_0x0165:
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r12
                r12 = r50
                goto L_0x03f0
            L_0x0174:
                r51 = r10
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r11 = 24
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r11, r10, r7)
                r10 = 16777216(0x1000000, float:2.3509887E-38)
                goto L_0x015a
            L_0x0181:
                r51 = r10
                r10 = 23
                boolean r25 = r0.decodeBooleanElement(r1, r10)
                r10 = 8388608(0x800000, float:1.17549435E-38)
                goto L_0x015a
            L_0x018c:
                r51 = r10
                r10 = 22
                boolean r24 = r0.decodeBooleanElement(r1, r10)
                r10 = 4194304(0x400000, float:5.877472E-39)
                goto L_0x015a
            L_0x0197:
                r51 = r10
                r10 = 21
                boolean r11 = r0.decodeBooleanElement(r1, r10)
                r10 = 2097152(0x200000, float:2.938736E-39)
                r39 = r2
                r2 = r10
                r43 = r11
                goto L_0x015d
            L_0x01a7:
                r51 = r10
                r10 = 20
                boolean r11 = r0.decodeBooleanElement(r1, r10)
                r42 = 1048576(0x100000, float:1.469368E-39)
                r39 = r2
                r30 = r29
                r2 = r42
                r36 = r51
                r10 = 0
                r42 = r11
                r29 = r27
                r11 = 6
                goto L_0x0165
            L_0x01c0:
                r51 = r10
                r10 = 20
                r11 = 19
                boolean r47 = r0.decodeBooleanElement(r1, r11)
                r52 = 524288(0x80000, float:7.34684E-40)
                r39 = r2
                r30 = r29
                r36 = r51
                r2 = r52
                goto L_0x0161
            L_0x01d5:
                r51 = r10
                r11 = 19
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 18
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r11, r10, r4)
                r10 = 262144(0x40000, float:3.67342E-40)
                goto L_0x015a
            L_0x01e5:
                r51 = r10
                r11 = 18
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 17
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r11, r10, r5)
                r10 = 131072(0x20000, float:1.83671E-40)
                goto L_0x015a
            L_0x01f5:
                r51 = r10
                r11 = 17
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 16
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r11, r10, r3)
                r10 = 65536(0x10000, float:9.18355E-41)
                goto L_0x015a
            L_0x0205:
                r51 = r10
                r11 = 16
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 15
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r10, r15)
                r15 = 32768(0x8000, float:4.5918E-41)
                r39 = r2
                r2 = r15
                r30 = r29
                r36 = r51
                r11 = 6
                r15 = r14
                r29 = r27
                r14 = r13
                r27 = r26
                r13 = r12
                r26 = r25
                r12 = r50
                r25 = r24
                r24 = r10
            L_0x022b:
                r10 = 0
                goto L_0x03f0
            L_0x022e:
                r51 = r10
                r11 = 15
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 14
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r10, r14)
                r14 = 16384(0x4000, float:2.2959E-41)
                r39 = r2
                r2 = r14
                r30 = r29
                r36 = r51
                r11 = 6
                r14 = r13
                r29 = r27
                r13 = r12
                r27 = r26
                r12 = r50
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r10
                goto L_0x022b
            L_0x0254:
                r51 = r10
                r11 = 14
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 13
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r11, r10, r6)
                r10 = 8192(0x2000, float:1.14794E-41)
                goto L_0x015a
            L_0x0264:
                r51 = r10
                r11 = 13
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 12
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r10, r13)
                r13 = 4096(0x1000, float:5.74E-42)
                r39 = r2
                r2 = r13
                r30 = r29
                r36 = r51
                r11 = 6
                r13 = r12
                r29 = r27
                r12 = r50
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r10
                goto L_0x022b
            L_0x028a:
                r51 = r10
                r11 = 12
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 11
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r10, r12)
                r12 = 2048(0x800, float:2.87E-42)
                r39 = r2
                r2 = r12
                r30 = r29
                r12 = r50
                r36 = r51
                r11 = 6
                r29 = r27
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r10
                goto L_0x022b
            L_0x02b1:
                r51 = r10
                r11 = 11
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 10
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r11, r10, r8)
                r10 = 1024(0x400, float:1.435E-42)
                goto L_0x015a
            L_0x02c1:
                r51 = r10
                r11 = 10
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r39 = r2
                r2 = r51
                r11 = 9
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r11, r10, r2)
                r10 = 512(0x200, float:7.175E-43)
                r36 = r2
                r2 = r10
                r30 = r29
                goto L_0x0161
            L_0x02da:
                r39 = r2
                r2 = r10
                r10 = 8
                r11 = 9
                boolean r27 = r0.decodeBooleanElement(r1, r10)
                r38 = 256(0x100, float:3.59E-43)
                r36 = r2
                r30 = r29
                r2 = r38
                goto L_0x0161
            L_0x02ef:
                r39 = r2
                r2 = r10
                r10 = 7
                r11 = 9
                int r48 = r0.decodeIntElement(r1, r10)
                r37 = 128(0x80, float:1.794E-43)
                r36 = r2
                r30 = r29
                r2 = r37
                goto L_0x0161
            L_0x0303:
                r39 = r2
                r2 = r10
                r11 = 9
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r36 = r2
                r2 = r50
                r11 = 6
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r11, r10, r2)
                r10 = 64
                r30 = r29
                r29 = r27
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r12
                r12 = r2
                r2 = r10
                goto L_0x022b
            L_0x0328:
                r39 = r2
                r36 = r10
                r2 = r50
                r10 = 5
                r11 = 6
                float r26 = r0.decodeFloatElement(r1, r10)
                r35 = 32
                r30 = r29
                r10 = 0
                r29 = r27
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r12
                r12 = r2
                r2 = r35
                goto L_0x03f0
            L_0x034b:
                r39 = r2
                r36 = r10
                r2 = r50
                r10 = 4
                r11 = 6
                java.lang.String r34 = r0.decodeStringElement(r1, r10)
                r30 = r29
                r46 = r34
                r10 = 0
                r29 = r27
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r12
                r12 = r2
                r2 = 16
                goto L_0x03f0
            L_0x036e:
                r39 = r2
                r36 = r10
                r2 = r50
                r10 = 3
                r11 = 6
                java.lang.String r33 = r0.decodeStringElement(r1, r10)
                r30 = r29
                r45 = r33
                r10 = 0
                r29 = r27
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r12
                r12 = r2
                r2 = 8
                goto L_0x03f0
            L_0x0391:
                r39 = r2
                r36 = r10
                r2 = r50
                r10 = 2
                r11 = 6
                java.lang.String r32 = r0.decodeStringElement(r1, r10)
                r30 = r29
                r44 = r32
                r10 = 0
                r29 = r27
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r12
                r12 = r2
                r2 = 4
                goto L_0x03f0
            L_0x03b2:
                r39 = r2
                r36 = r10
                r2 = r50
                r10 = 1
                r11 = 6
                int r29 = r0.decodeIntElement(r1, r10)
                r30 = r29
                r10 = 0
                r29 = r27
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r12
                r12 = r2
                r2 = 2
                goto L_0x03f0
            L_0x03d1:
                r39 = r2
                r36 = r10
                r2 = r50
                r10 = 0
                r11 = 6
                int r30 = r0.decodeIntElement(r1, r10)
                r41 = r30
                r30 = r29
                r29 = r27
                r27 = r26
                r26 = r25
                r25 = r24
                r24 = r15
                r15 = r14
                r14 = r13
                r13 = r12
                r12 = r2
                r2 = 1
            L_0x03f0:
                r28 = r28 | r2
                r11 = r12
                r12 = r13
                r13 = r14
                r14 = r15
                r15 = r24
                r24 = r25
                r25 = r26
                r26 = r27
                r27 = r29
                r29 = r30
            L_0x0402:
                r10 = r36
                r2 = r39
                goto L_0x012a
            L_0x0408:
                r39 = r2
                r36 = r10
                r2 = r50
                r10 = 0
                r11 = 6
                r11 = r2
                r49 = r10
                goto L_0x0402
            L_0x0414:
                r39 = r2
                r36 = r10
                r2 = r11
                r18 = r3
                r17 = r5
                r21 = r6
                r23 = r12
                r22 = r13
                r49 = r25
                r31 = r26
                r34 = r27
                r25 = r28
                r27 = r29
                r13 = r36
                r26 = r41
                r28 = r44
                r29 = r45
                r30 = r46
                r45 = r47
                r33 = r48
                r3 = r2
                r12 = r8
                r2 = r9
                r48 = r24
                r9 = r39
                r46 = r42
                r47 = r43
            L_0x0446:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.e0 r0 = new com.appsamurai.storyly.data.e0
                r24 = r0
                r32 = r3
                java.lang.String r32 = (java.lang.String) r32
                r35 = r13
                com.appsamurai.storyly.data.c r35 = (com.appsamurai.storyly.data.c) r35
                r36 = r12
                com.appsamurai.storyly.data.c r36 = (com.appsamurai.storyly.data.c) r36
                r37 = r23
                com.appsamurai.storyly.data.c r37 = (com.appsamurai.storyly.data.c) r37
                r38 = r22
                com.appsamurai.storyly.data.c r38 = (com.appsamurai.storyly.data.c) r38
                r39 = r21
                com.appsamurai.storyly.data.c r39 = (com.appsamurai.storyly.data.c) r39
                r40 = r14
                com.appsamurai.storyly.data.c r40 = (com.appsamurai.storyly.data.c) r40
                r41 = r15
                com.appsamurai.storyly.data.c r41 = (com.appsamurai.storyly.data.c) r41
                r42 = r18
                com.appsamurai.storyly.data.c r42 = (com.appsamurai.storyly.data.c) r42
                r43 = r17
                com.appsamurai.storyly.data.c r43 = (com.appsamurai.storyly.data.c) r43
                r44 = r4
                com.appsamurai.storyly.data.c r44 = (com.appsamurai.storyly.data.c) r44
                r50 = r7
                java.lang.String r50 = (java.lang.String) r50
                r51 = r9
                com.appsamurai.storyly.data.c r51 = (com.appsamurai.storyly.data.c) r51
                r52 = r2
                com.appsamurai.storyly.data.c r52 = (com.appsamurai.storyly.data.c) r52
                r53 = 0
                r24.<init>(r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.e0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3673b;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:102:0x01dc, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7.f3671z, (java.lang.Object) kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7.f3652g, (java.lang.Object) "Dark") ? new com.appsamurai.storyly.data.c(androidx.core.view.ViewCompat.MEASURED_STATE_MASK) : new com.appsamurai.storyly.data.c(-1)) == false) goto L_0x01de;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x0208, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7.f3645A, (java.lang.Object) (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7.f3652g, (java.lang.Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_CCFFFFFF : com.appsamurai.storyly.config.styling.a.COLOR_CC000000).b()) == false) goto L_0x020a;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void serialize(kotlinx.serialization.encoding.Encoder r6, java.lang.Object r7) {
            /*
                r5 = this;
                com.appsamurai.storyly.data.e0 r7 = (com.appsamurai.storyly.data.e0) r7
                java.lang.String r5 = "encoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r5)
                java.lang.String r5 = "value"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r5)
                kotlinx.serialization.descriptors.SerialDescriptor r5 = f3673b
                kotlinx.serialization.encoding.CompositeEncoder r6 = r6.beginStructure(r5)
                java.lang.String r0 = "self"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.String r0 = "output"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "serialDesc"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                com.appsamurai.storyly.data.a0.a(r7, r6, r5)
                int r0 = r7.f3646a
                r1 = 0
                r6.encodeIntElement(r5, r1, r0)
                int r0 = r7.f3647b
                r1 = 1
                r6.encodeIntElement(r5, r1, r0)
                java.lang.String r0 = r7.f3648c
                r2 = 2
                r6.encodeStringElement(r5, r2, r0)
                java.lang.String r0 = r7.f3649d
                r3 = 3
                r6.encodeStringElement(r5, r3, r0)
                java.lang.String r0 = r7.f3650e
                r3 = 4
                r6.encodeStringElement(r5, r3, r0)
                float r0 = r7.f3651f
                r3 = 5
                r6.encodeFloatElement(r5, r3, r0)
                r0 = 6
                boolean r3 = r6.shouldEncodeElementDefault(r5, r0)
                if (r3 == 0) goto L_0x0051
                goto L_0x0055
            L_0x0051:
                java.lang.String r3 = r7.f3652g
                if (r3 == 0) goto L_0x005c
            L_0x0055:
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r4 = r7.f3652g
                r6.encodeNullableSerializableElement(r5, r0, r3, r4)
            L_0x005c:
                r0 = 7
                boolean r3 = r6.shouldEncodeElementDefault(r5, r0)
                if (r3 == 0) goto L_0x0064
                goto L_0x0068
            L_0x0064:
                int r3 = r7.f3653h
                if (r3 == r2) goto L_0x006d
            L_0x0068:
                int r2 = r7.f3653h
                r6.encodeIntElement(r5, r0, r2)
            L_0x006d:
                r0 = 8
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0076
                goto L_0x007a
            L_0x0076:
                boolean r2 = r7.f3654i
                if (r2 == r1) goto L_0x007f
            L_0x007a:
                boolean r2 = r7.f3654i
                r6.encodeBooleanElement(r5, r0, r2)
            L_0x007f:
                r0 = 9
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0088
                goto L_0x008c
            L_0x0088:
                com.appsamurai.storyly.data.c r2 = r7.f3655j
                if (r2 == 0) goto L_0x0093
            L_0x008c:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3655j
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x0093:
                r0 = 10
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x009c
                goto L_0x00a0
            L_0x009c:
                com.appsamurai.storyly.data.c r2 = r7.f3656k
                if (r2 == 0) goto L_0x00a7
            L_0x00a0:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3656k
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x00a7:
                r0 = 11
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x00b0
                goto L_0x00b4
            L_0x00b0:
                com.appsamurai.storyly.data.c r2 = r7.f3657l
                if (r2 == 0) goto L_0x00bb
            L_0x00b4:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3657l
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x00bb:
                r0 = 12
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x00c4
                goto L_0x00c8
            L_0x00c4:
                com.appsamurai.storyly.data.c r2 = r7.f3658m
                if (r2 == 0) goto L_0x00cf
            L_0x00c8:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3658m
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x00cf:
                r0 = 13
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x00d8
                goto L_0x00dc
            L_0x00d8:
                com.appsamurai.storyly.data.c r2 = r7.f3659n
                if (r2 == 0) goto L_0x00e3
            L_0x00dc:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3659n
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x00e3:
                r0 = 14
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x00ec
                goto L_0x00f0
            L_0x00ec:
                com.appsamurai.storyly.data.c r2 = r7.f3660o
                if (r2 == 0) goto L_0x00f7
            L_0x00f0:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3660o
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x00f7:
                r0 = 15
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0100
                goto L_0x0104
            L_0x0100:
                com.appsamurai.storyly.data.c r2 = r7.f3661p
                if (r2 == 0) goto L_0x010b
            L_0x0104:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3661p
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x010b:
                r0 = 16
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0114
                goto L_0x0118
            L_0x0114:
                com.appsamurai.storyly.data.c r2 = r7.f3662q
                if (r2 == 0) goto L_0x011f
            L_0x0118:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3662q
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x011f:
                r0 = 17
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0128
                goto L_0x012c
            L_0x0128:
                com.appsamurai.storyly.data.c r2 = r7.f3663r
                if (r2 == 0) goto L_0x0133
            L_0x012c:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3663r
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x0133:
                r0 = 18
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x013c
                goto L_0x0140
            L_0x013c:
                com.appsamurai.storyly.data.c r2 = r7.f3664s
                if (r2 == 0) goto L_0x0147
            L_0x0140:
                com.appsamurai.storyly.data.c$a r2 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r3 = r7.f3664s
                r6.encodeNullableSerializableElement(r5, r0, r2, r3)
            L_0x0147:
                r0 = 19
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0150
                goto L_0x0154
            L_0x0150:
                boolean r2 = r7.f3665t
                if (r2 == r1) goto L_0x0159
            L_0x0154:
                boolean r2 = r7.f3665t
                r6.encodeBooleanElement(r5, r0, r2)
            L_0x0159:
                r0 = 20
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0162
                goto L_0x0166
            L_0x0162:
                boolean r2 = r7.f3666u
                if (r2 == 0) goto L_0x016b
            L_0x0166:
                boolean r2 = r7.f3666u
                r6.encodeBooleanElement(r5, r0, r2)
            L_0x016b:
                r0 = 21
                boolean r2 = r6.shouldEncodeElementDefault(r5, r0)
                if (r2 == 0) goto L_0x0174
                goto L_0x0178
            L_0x0174:
                boolean r2 = r7.f3667v
                if (r2 == r1) goto L_0x017d
            L_0x0178:
                boolean r1 = r7.f3667v
                r6.encodeBooleanElement(r5, r0, r1)
            L_0x017d:
                r0 = 22
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                if (r1 == 0) goto L_0x0186
                goto L_0x018a
            L_0x0186:
                boolean r1 = r7.f3668w
                if (r1 == 0) goto L_0x018f
            L_0x018a:
                boolean r1 = r7.f3668w
                r6.encodeBooleanElement(r5, r0, r1)
            L_0x018f:
                r0 = 23
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                if (r1 == 0) goto L_0x0198
                goto L_0x019c
            L_0x0198:
                boolean r1 = r7.f3669x
                if (r1 == 0) goto L_0x01a1
            L_0x019c:
                boolean r1 = r7.f3669x
                r6.encodeBooleanElement(r5, r0, r1)
            L_0x01a1:
                r0 = 24
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                if (r1 == 0) goto L_0x01aa
                goto L_0x01ae
            L_0x01aa:
                java.lang.String r1 = r7.f3670y
                if (r1 == 0) goto L_0x01b5
            L_0x01ae:
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.String r2 = r7.f3670y
                r6.encodeNullableSerializableElement(r5, r0, r1, r2)
            L_0x01b5:
                r0 = 25
                boolean r1 = r6.shouldEncodeElementDefault(r5, r0)
                if (r1 == 0) goto L_0x01be
                goto L_0x01de
            L_0x01be:
                com.appsamurai.storyly.data.c r1 = r7.f3671z
                java.lang.String r2 = r7.f3652g
                java.lang.String r3 = "Dark"
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                if (r2 == 0) goto L_0x01d2
                com.appsamurai.storyly.data.c r2 = new com.appsamurai.storyly.data.c
                r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
                r2.<init>(r3)
                goto L_0x01d8
            L_0x01d2:
                com.appsamurai.storyly.data.c r2 = new com.appsamurai.storyly.data.c
                r3 = -1
                r2.<init>(r3)
            L_0x01d8:
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
                if (r1 != 0) goto L_0x01e5
            L_0x01de:
                com.appsamurai.storyly.data.c$a r1 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r2 = r7.f3671z
                r6.encodeSerializableElement(r5, r0, r1, r2)
            L_0x01e5:
                r0 = 26
                boolean r0 = r6.shouldEncodeElementDefault(r5, r0)
                if (r0 == 0) goto L_0x01ee
                goto L_0x020a
            L_0x01ee:
                com.appsamurai.storyly.data.c r0 = r7.f3645A
                java.lang.String r1 = r7.f3652g
                java.lang.String r2 = "Dark"
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
                if (r1 == 0) goto L_0x0201
                com.appsamurai.storyly.config.styling.a r1 = com.appsamurai.storyly.config.styling.a.COLOR_CCFFFFFF
            L_0x01fc:
                com.appsamurai.storyly.data.c r1 = r1.b()
                goto L_0x0204
            L_0x0201:
                com.appsamurai.storyly.config.styling.a r1 = com.appsamurai.storyly.config.styling.a.COLOR_CC000000
                goto L_0x01fc
            L_0x0204:
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
                if (r0 != 0) goto L_0x0213
            L_0x020a:
                com.appsamurai.storyly.data.c$a r0 = com.appsamurai.storyly.data.c.f3622b
                com.appsamurai.storyly.data.c r7 = r7.f3645A
                r1 = 26
                r6.encodeSerializableElement(r5, r1, r0, r7)
            L_0x0213:
                r6.endStructure(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.e0.a.serialize(kotlinx.serialization.encoding.Encoder, java.lang.Object):void");
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ e0(int i3, @Required @SerialName("l_o_vote_count") int i4, @Required @SerialName("r_o_vote_count") int i5, @Required @SerialName("l_o_text") String str, @Required @SerialName("r_o_text") String str2, @Required @SerialName("p_text") String str3, @Required @SerialName("o_h") float f2, @SerialName("theme") String str4, @SerialName("scale") int i6, @SerialName("has_title") boolean z2, @SerialName("primary_color") c cVar, @SerialName("secondary_color") c cVar2, @SerialName("p_border_color") c cVar3, @SerialName("p_middle_color") c cVar4, @SerialName("p_text_color") c cVar5, @SerialName("p_text_shadow_color") c cVar6, @SerialName("l_o_text_color") c cVar7, @SerialName("r_o_text_color") c cVar8, @SerialName("o_percentage_color") c cVar9, @SerialName("o_button_color") c cVar10, @SerialName("is_bold") boolean z3, @SerialName("is_italic") boolean z4, @SerialName("p_option_is_bold") boolean z5, @SerialName("p_option_is_italic") boolean z6, @SerialName("is_result") boolean z7, @SerialName("custom_payload") String str5, c cVar11, c cVar12, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        c cVar13;
        c cVar14;
        int i7 = i3;
        if (63 != (i7 & 63)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 63, a.f3672a.getDescriptor());
        }
        this.f3646a = i4;
        this.f3647b = i5;
        this.f3648c = str;
        this.f3649d = str2;
        this.f3650e = str3;
        this.f3651f = f2;
        if ((i7 & 64) == 0) {
            this.f3652g = null;
        } else {
            this.f3652g = str4;
        }
        this.f3653h = (i7 & 128) == 0 ? 2 : i6;
        if ((i7 & 256) == 0) {
            this.f3654i = true;
        } else {
            this.f3654i = z2;
        }
        if ((i7 & 512) == 0) {
            this.f3655j = null;
        } else {
            this.f3655j = cVar;
        }
        if ((i7 & 1024) == 0) {
            this.f3656k = null;
        } else {
            this.f3656k = cVar2;
        }
        if ((i7 & 2048) == 0) {
            this.f3657l = null;
        } else {
            this.f3657l = cVar3;
        }
        if ((i7 & 4096) == 0) {
            this.f3658m = null;
        } else {
            this.f3658m = cVar4;
        }
        if ((i7 & 8192) == 0) {
            this.f3659n = null;
        } else {
            this.f3659n = cVar5;
        }
        if ((i7 & 16384) == 0) {
            this.f3660o = null;
        } else {
            this.f3660o = cVar6;
        }
        if ((32768 & i7) == 0) {
            this.f3661p = null;
        } else {
            this.f3661p = cVar7;
        }
        if ((65536 & i7) == 0) {
            this.f3662q = null;
        } else {
            this.f3662q = cVar8;
        }
        if ((131072 & i7) == 0) {
            this.f3663r = null;
        } else {
            this.f3663r = cVar9;
        }
        if ((262144 & i7) == 0) {
            this.f3664s = null;
        } else {
            this.f3664s = cVar10;
        }
        if ((524288 & i7) == 0) {
            this.f3665t = true;
        } else {
            this.f3665t = z3;
        }
        if ((1048576 & i7) == 0) {
            this.f3666u = false;
        } else {
            this.f3666u = z4;
        }
        if ((2097152 & i7) == 0) {
            this.f3667v = true;
        } else {
            this.f3667v = z5;
        }
        if ((4194304 & i7) == 0) {
            this.f3668w = false;
        } else {
            this.f3668w = z6;
        }
        if ((8388608 & i7) == 0) {
            this.f3669x = false;
        } else {
            this.f3669x = z7;
        }
        if ((16777216 & i7) == 0) {
            this.f3670y = null;
        } else {
            this.f3670y = str5;
        }
        if ((33554432 & i7) == 0) {
            cVar13 = Intrinsics.areEqual((Object) this.f3652g, (Object) "Dark") ? new c(ViewCompat.MEASURED_STATE_MASK) : new c(-1);
        } else {
            cVar13 = cVar11;
        }
        this.f3671z = cVar13;
        if ((i7 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) == 0) {
            cVar14 = (Intrinsics.areEqual((Object) this.f3652g, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_CCFFFFFF : com.appsamurai.storyly.config.styling.a.COLOR_CC000000).b();
        } else {
            cVar14 = cVar12;
        }
        this.f3645A = cVar14;
    }

    public StoryComponent a(b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryPollComponent(b0Var.f3614i, this.f3650e, CollectionsKt.listOf(this.f3648c, this.f3649d), -1, this.f3670y);
    }

    public final c b() {
        c cVar = this.f3656k;
        return cVar == null ? this.f3645A : cVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e0)) {
            return false;
        }
        e0 e0Var = (e0) obj;
        return this.f3646a == e0Var.f3646a && this.f3647b == e0Var.f3647b && Intrinsics.areEqual((Object) this.f3648c, (Object) e0Var.f3648c) && Intrinsics.areEqual((Object) this.f3649d, (Object) e0Var.f3649d) && Intrinsics.areEqual((Object) this.f3650e, (Object) e0Var.f3650e) && Intrinsics.areEqual((Object) Float.valueOf(this.f3651f), (Object) Float.valueOf(e0Var.f3651f)) && Intrinsics.areEqual((Object) this.f3652g, (Object) e0Var.f3652g) && this.f3653h == e0Var.f3653h && this.f3654i == e0Var.f3654i && Intrinsics.areEqual((Object) this.f3655j, (Object) e0Var.f3655j) && Intrinsics.areEqual((Object) this.f3656k, (Object) e0Var.f3656k) && Intrinsics.areEqual((Object) this.f3657l, (Object) e0Var.f3657l) && Intrinsics.areEqual((Object) this.f3658m, (Object) e0Var.f3658m) && Intrinsics.areEqual((Object) this.f3659n, (Object) e0Var.f3659n) && Intrinsics.areEqual((Object) this.f3660o, (Object) e0Var.f3660o) && Intrinsics.areEqual((Object) this.f3661p, (Object) e0Var.f3661p) && Intrinsics.areEqual((Object) this.f3662q, (Object) e0Var.f3662q) && Intrinsics.areEqual((Object) this.f3663r, (Object) e0Var.f3663r) && Intrinsics.areEqual((Object) this.f3664s, (Object) e0Var.f3664s) && this.f3665t == e0Var.f3665t && this.f3666u == e0Var.f3666u && this.f3667v == e0Var.f3667v && this.f3668w == e0Var.f3668w && this.f3669x == e0Var.f3669x && Intrinsics.areEqual((Object) this.f3670y, (Object) e0Var.f3670y);
    }

    public int hashCode() {
        int c3 = android.support.v4.media.session.a.c(this.f3651f, androidx.compose.animation.core.a.i(this.f3650e, androidx.compose.animation.core.a.i(this.f3649d, androidx.compose.animation.core.a.i(this.f3648c, androidx.compose.animation.core.a.c(this.f3647b, Integer.hashCode(this.f3646a) * 31, 31), 31), 31), 31), 31);
        String str = this.f3652g;
        int i3 = 0;
        int c4 = androidx.compose.animation.core.a.c(this.f3653h, (c3 + (str == null ? 0 : str.hashCode())) * 31, 31);
        boolean z2 = this.f3654i;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (c4 + (z2 ? 1 : 0)) * 31;
        c cVar = this.f3655j;
        int hashCode = (i4 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f3656k;
        int hashCode2 = (hashCode + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f3657l;
        int hashCode3 = (hashCode2 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f3658m;
        int hashCode4 = (hashCode3 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f3659n;
        int hashCode5 = (hashCode4 + (cVar5 == null ? 0 : Integer.hashCode(cVar5.f3624a))) * 31;
        c cVar6 = this.f3660o;
        int hashCode6 = (hashCode5 + (cVar6 == null ? 0 : Integer.hashCode(cVar6.f3624a))) * 31;
        c cVar7 = this.f3661p;
        int hashCode7 = (hashCode6 + (cVar7 == null ? 0 : Integer.hashCode(cVar7.f3624a))) * 31;
        c cVar8 = this.f3662q;
        int hashCode8 = (hashCode7 + (cVar8 == null ? 0 : Integer.hashCode(cVar8.f3624a))) * 31;
        c cVar9 = this.f3663r;
        int hashCode9 = (hashCode8 + (cVar9 == null ? 0 : Integer.hashCode(cVar9.f3624a))) * 31;
        c cVar10 = this.f3664s;
        int hashCode10 = (hashCode9 + (cVar10 == null ? 0 : Integer.hashCode(cVar10.f3624a))) * 31;
        boolean z4 = this.f3665t;
        if (z4) {
            z4 = true;
        }
        int i5 = (hashCode10 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f3666u;
        if (z5) {
            z5 = true;
        }
        int i6 = (i5 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.f3667v;
        if (z6) {
            z6 = true;
        }
        int i7 = (i6 + (z6 ? 1 : 0)) * 31;
        boolean z7 = this.f3668w;
        if (z7) {
            z7 = true;
        }
        int i8 = (i7 + (z7 ? 1 : 0)) * 31;
        boolean z8 = this.f3669x;
        if (!z8) {
            z3 = z8;
        }
        int i9 = (i8 + (z3 ? 1 : 0)) * 31;
        String str2 = this.f3670y;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return i9 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyPollLayer(leftOptionVoteCount=" + this.f3646a + ", rightOptionVoteCount=" + this.f3647b + ", leftOptionText=" + this.f3648c + ", rightOptionText=" + this.f3649d + ", pollText=" + this.f3650e + ", optionsButtonHeight=" + this.f3651f + ", theme=" + this.f3652g + ", scale=" + this.f3653h + ", hasTitle=" + this.f3654i + ", primaryColor=" + this.f3655j + ", secondaryColor=" + this.f3656k + ", pollBorderColor=" + this.f3657l + ", pollMiddleColor=" + this.f3658m + ", pollTextColor=" + this.f3659n + ", pollTextShadowColor=" + this.f3660o + ", leftOptionTextColor=" + this.f3661p + ", rightOptionTextColor=" + this.f3662q + ", optionPercentageColor=" + this.f3663r + ", optionsButtonColor=" + this.f3664s + ", isBold=" + this.f3665t + ", isItalic=" + this.f3666u + ", optionIsBold=" + this.f3667v + ", optionIsItalic=" + this.f3668w + ", isResult=" + this.f3669x + ", customPayload=" + this.f3670y + ')';
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var, int i3) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryPollComponent(b0Var.f3614i, this.f3650e, CollectionsKt.listOf(this.f3648c, this.f3649d), i3, this.f3670y);
    }

    public e0(int i3, int i4, @NotNull String str, @NotNull String str2, @NotNull String str3, float f2, @Nullable String str4, int i5, boolean z2, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, @Nullable c cVar6, @Nullable c cVar7, @Nullable c cVar8, @Nullable c cVar9, @Nullable c cVar10, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, @Nullable String str5) {
        Intrinsics.checkNotNullParameter(str, "leftOptionText");
        Intrinsics.checkNotNullParameter(str2, "rightOptionText");
        Intrinsics.checkNotNullParameter(str3, "pollText");
        this.f3646a = i3;
        this.f3647b = i4;
        this.f3648c = str;
        this.f3649d = str2;
        this.f3650e = str3;
        this.f3651f = f2;
        this.f3652g = str4;
        this.f3653h = i5;
        this.f3654i = z2;
        this.f3655j = cVar;
        this.f3656k = cVar2;
        this.f3657l = cVar3;
        this.f3658m = cVar4;
        this.f3659n = cVar5;
        this.f3660o = cVar6;
        this.f3661p = cVar7;
        this.f3662q = cVar8;
        this.f3663r = cVar9;
        this.f3664s = cVar10;
        this.f3665t = z3;
        this.f3666u = z4;
        this.f3667v = z5;
        this.f3668w = z6;
        this.f3669x = z7;
        this.f3670y = str5;
        this.f3671z = Intrinsics.areEqual((Object) str4, (Object) "Dark") ? new c(ViewCompat.MEASURED_STATE_MASK) : new c(-1);
        this.f3645A = (Intrinsics.areEqual((Object) str4, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_CCFFFFFF : com.appsamurai.storyly.config.styling.a.COLOR_CC000000).b();
    }

    @NotNull
    public final c a() {
        c cVar = this.f3658m;
        return cVar == null ? new c(f.a(b().f3624a, 0.166f)) : cVar;
    }
}
