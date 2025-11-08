package com.appsamurai.storyly.data;

import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.StoryCommentComponent;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.data.c;
import com.appsamurai.storyly.util.f;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
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
public final class q extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4120a;

    /* renamed from: b  reason: collision with root package name */
    public final int f4121b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final String f4122c;

    /* renamed from: d  reason: collision with root package name */
    public final boolean f4123d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final String f4124e;

    /* renamed from: f  reason: collision with root package name */
    public final boolean f4125f;

    /* renamed from: g  reason: collision with root package name */
    public final boolean f4126g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final c f4127h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public final c f4128i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final c f4129j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final c f4130k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public final c f4131l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public final c f4132m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public final c f4133n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public final c f4134o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public final c f4135p;
    @Nullable

    /* renamed from: q  reason: collision with root package name */
    public final c f4136q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public final String f4137r;
    @Nullable

    /* renamed from: s  reason: collision with root package name */
    public final Float f4138s;
    @Nullable

    /* renamed from: t  reason: collision with root package name */
    public final Integer f4139t;
    @Nullable

    /* renamed from: u  reason: collision with root package name */
    public final Float f4140u;

    /* renamed from: v  reason: collision with root package name */
    public final float f4141v;

    /* renamed from: w  reason: collision with root package name */
    public final float f4142w;

    /* renamed from: x  reason: collision with root package name */
    public final float f4143x;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<q> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4144a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4145b;

        static {
            a aVar = new a();
            f4144a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyCommentLayer", aVar, 24);
            pluginGeneratedSerialDescriptor.addElement("theme", false);
            pluginGeneratedSerialDescriptor.addElement("scale", true);
            pluginGeneratedSerialDescriptor.addElement("title", true);
            pluginGeneratedSerialDescriptor.addElement("has_title", true);
            pluginGeneratedSerialDescriptor.addElement("placeholder", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("primary_color", true);
            pluginGeneratedSerialDescriptor.addElement("secondary_color", true);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_color", true);
            pluginGeneratedSerialDescriptor.addElement("t_color", true);
            pluginGeneratedSerialDescriptor.addElement("i_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("i_border_color", true);
            pluginGeneratedSerialDescriptor.addElement("i_color", true);
            pluginGeneratedSerialDescriptor.addElement("s_button_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("s_button_color", true);
            pluginGeneratedSerialDescriptor.addElement("a_t", true);
            pluginGeneratedSerialDescriptor.addElement("a_h", true);
            pluginGeneratedSerialDescriptor.addElement("a_l_c", true);
            pluginGeneratedSerialDescriptor.addElement("a_l_h", true);
            pluginGeneratedSerialDescriptor.addElement("defaultBorderColorAlpha", true);
            pluginGeneratedSerialDescriptor.addElement("defaultInputBackgroundColorAlpha", true);
            pluginGeneratedSerialDescriptor.addElement("defaultInputBorderColorAlpha", true);
            f4145b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            c.a aVar = c.f3622b;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable6 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable7 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable8 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable9 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable10 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable11 = BuiltinSerializersKt.getNullable(stringSerializer);
            FloatSerializer floatSerializer = FloatSerializer.INSTANCE;
            KSerializer<?> nullable12 = BuiltinSerializersKt.getNullable(floatSerializer);
            KSerializer<?> nullable13 = BuiltinSerializersKt.getNullable(intSerializer);
            KSerializer<?> nullable14 = BuiltinSerializersKt.getNullable(floatSerializer);
            FloatSerializer floatSerializer2 = floatSerializer;
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, intSerializer, stringSerializer, booleanSerializer, stringSerializer, booleanSerializer, booleanSerializer, nullable, nullable2, nullable3, nullable4, nullable5, nullable6, nullable7, nullable8, nullable9, nullable10, nullable11, nullable12, nullable13, nullable14, floatSerializer2, floatSerializer2, floatSerializer2};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x011e, code lost:
            r6 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0129, code lost:
            r33 = r3;
            r32 = r35;
            r31 = r36;
            r24 = r37;
            r49 = r34;
            r34 = r2;
            r2 = r11;
            r11 = r49;
            r6 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0151, code lost:
            r33 = r3;
            r11 = r34;
            r32 = r35;
            r31 = r36;
            r24 = r37;
            r34 = r2;
            r2 = r10;
            r6 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0161, code lost:
            r6 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0256, code lost:
            r24 = r37;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x025a, code lost:
            r6 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x02fe, code lost:
            r6 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x034b, code lost:
            r44 = r44 | r2;
            r37 = r24;
            r36 = r31;
            r35 = r32;
            r6 = r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0353, code lost:
            r3 = r33;
            r2 = r34;
            r10 = 11;
            r34 = r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x011e, code lost:
            r6 = r6;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r51) {
            /*
                r50 = this;
                r0 = r51
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4145b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r10 = 11
                r11 = 10
                r12 = 9
                r13 = 7
                r14 = 6
                r15 = 5
                r3 = 3
                r5 = 8
                r4 = 4
                r6 = 2
                r7 = 1
                r8 = 0
                r9 = 0
                if (r2 == 0) goto L_0x00db
                java.lang.String r2 = r0.decodeStringElement(r1, r8)
                int r7 = r0.decodeIntElement(r1, r7)
                java.lang.String r6 = r0.decodeStringElement(r1, r6)
                boolean r3 = r0.decodeBooleanElement(r1, r3)
                java.lang.String r4 = r0.decodeStringElement(r1, r4)
                boolean r8 = r0.decodeBooleanElement(r1, r15)
                boolean r14 = r0.decodeBooleanElement(r1, r14)
                com.appsamurai.storyly.data.c$a r15 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r13, r15, r9)
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r15, r9)
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r12, r15, r9)
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r11, r15, r9)
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r15, r9)
                r22 = r2
                r2 = 12
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r15, r9)
                r21 = r2
                r2 = 13
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r15, r9)
                r20 = r2
                r2 = 14
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r15, r9)
                r19 = r2
                r2 = 15
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r15, r9)
                r18 = r2
                r2 = 16
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r15, r9)
                kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r17 = r2
                r2 = 17
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r15, r9)
                kotlinx.serialization.internal.FloatSerializer r15 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                r16 = r2
                r2 = 18
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r15, r9)
                r51 = r2
                kotlinx.serialization.internal.IntSerializer r2 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r23 = r3
                r3 = 19
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r3, r2, r9)
                r3 = 20
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r15, r9)
                r9 = 21
                float r9 = r0.decodeFloatElement(r1, r9)
                r15 = 22
                float r15 = r0.decodeFloatElement(r1, r15)
                r50 = r2
                r2 = 23
                float r2 = r0.decodeFloatElement(r1, r2)
                r24 = 16777215(0xffffff, float:2.3509886E-38)
                r47 = r2
                r28 = r4
                r26 = r6
                r25 = r7
                r29 = r8
                r45 = r9
                r30 = r14
                r46 = r15
                r15 = r18
                r14 = r19
                r27 = r23
                r23 = r24
                r2 = r50
                r6 = r51
                r24 = r22
                goto L_0x03a0
            L_0x00db:
                r2 = 0
                r41 = r2
                r43 = r41
                r45 = r7
                r22 = r8
                r23 = r22
                r42 = r23
                r44 = r42
                r3 = r9
                r4 = r3
                r5 = r4
                r6 = r5
                r7 = r6
                r12 = r7
                r13 = r12
                r14 = r13
                r15 = r14
                r34 = r15
                r35 = r34
                r36 = r35
                r37 = r36
                r38 = r37
                r39 = r38
                r40 = r39
                r8 = r40
                r9 = r44
            L_0x0105:
                if (r45 == 0) goto L_0x036e
                int r11 = r0.decodeElementIndex(r1)
                switch(r11) {
                    case -1: goto L_0x035d;
                    case 0: goto L_0x0332;
                    case 1: goto L_0x031a;
                    case 2: goto L_0x0300;
                    case 3: goto L_0x02e3;
                    case 4: goto L_0x02ca;
                    case 5: goto L_0x02b1;
                    case 6: goto L_0x0297;
                    case 7: goto L_0x027a;
                    case 8: goto L_0x025e;
                    case 9: goto L_0x023d;
                    case 10: goto L_0x021f;
                    case 11: goto L_0x0200;
                    case 12: goto L_0x01f2;
                    case 13: goto L_0x01d3;
                    case 14: goto L_0x01b5;
                    case 15: goto L_0x0196;
                    case 16: goto L_0x0189;
                    case 17: goto L_0x017c;
                    case 18: goto L_0x016f;
                    case 19: goto L_0x0164;
                    case 20: goto L_0x0147;
                    case 21: goto L_0x013e;
                    case 22: goto L_0x0121;
                    case 23: goto L_0x0114;
                    default: goto L_0x010e;
                }
            L_0x010e:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r11)
                throw r0
            L_0x0114:
                r2 = 23
                float r2 = r0.decodeFloatElement(r1, r2)
                r11 = 8388608(0x800000, float:1.17549435E-38)
                r44 = r44 | r11
            L_0x011e:
                r11 = 10
                goto L_0x0105
            L_0x0121:
                r11 = 22
                float r43 = r0.decodeFloatElement(r1, r11)
                r11 = 4194304(0x400000, float:5.877472E-39)
            L_0x0129:
                r33 = r3
                r32 = r35
                r31 = r36
                r24 = r37
                r3 = 0
                r10 = 6
                r26 = 3
                r49 = r34
                r34 = r2
                r2 = r11
                r11 = r49
                goto L_0x034b
            L_0x013e:
                r11 = 21
                float r41 = r0.decodeFloatElement(r1, r11)
                r11 = 2097152(0x200000, float:2.938736E-39)
                goto L_0x0129
            L_0x0147:
                kotlinx.serialization.internal.FloatSerializer r11 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                r10 = 20
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r10, r11, r8)
                r10 = 1048576(0x100000, float:1.469368E-39)
            L_0x0151:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r24 = r37
                r3 = 0
                r26 = 3
                r34 = r2
                r2 = r10
            L_0x0161:
                r10 = 6
                goto L_0x034b
            L_0x0164:
                kotlinx.serialization.internal.IntSerializer r10 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r11 = 19
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r11, r10, r7)
                r10 = 524288(0x80000, float:7.34684E-40)
                goto L_0x0151
            L_0x016f:
                r11 = 19
                kotlinx.serialization.internal.FloatSerializer r10 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                r11 = 18
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r11, r10, r6)
                r10 = 262144(0x40000, float:3.67342E-40)
                goto L_0x0151
            L_0x017c:
                r11 = 18
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r11 = 17
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r11, r10, r3)
                r10 = 131072(0x20000, float:1.83671E-40)
                goto L_0x0151
            L_0x0189:
                r11 = 17
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 16
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r11, r10, r4)
                r10 = 65536(0x10000, float:9.18355E-41)
                goto L_0x0151
            L_0x0196:
                r11 = 16
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 15
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r10, r15)
                r15 = 32768(0x8000, float:4.5918E-41)
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r24 = r37
                r3 = 0
                r26 = 3
                r34 = r2
                r2 = r15
                r15 = r10
                goto L_0x0161
            L_0x01b5:
                r11 = 15
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 14
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r10, r14)
                r14 = 16384(0x4000, float:2.2959E-41)
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r24 = r37
                r3 = 0
                r26 = 3
                r34 = r2
                r2 = r14
                r14 = r10
                goto L_0x0161
            L_0x01d3:
                r11 = 14
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 13
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r10, r13)
                r13 = 8192(0x2000, float:1.14794E-41)
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r24 = r37
                r3 = 0
                r26 = 3
                r34 = r2
                r2 = r13
                r13 = r10
                goto L_0x0161
            L_0x01f2:
                r11 = 13
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 12
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r11, r10, r5)
                r10 = 4096(0x1000, float:5.74E-42)
                goto L_0x0151
            L_0x0200:
                r11 = 12
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = 11
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r11, r10, r12)
                r12 = 2048(0x800, float:2.87E-42)
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r24 = r37
                r3 = 0
                r26 = 3
                r34 = r2
                r2 = r12
                r12 = r10
                goto L_0x0161
            L_0x021f:
                r11 = r10
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r11 = r34
                r34 = r2
                r2 = 10
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r2, r10, r11)
                r11 = 1024(0x400, float:1.435E-42)
                r33 = r3
                r2 = r11
                r32 = r35
                r31 = r36
                r24 = r37
                r3 = 0
                r26 = 3
                r11 = r10
                goto L_0x0161
            L_0x023d:
                r11 = r34
                r34 = r2
                r2 = 10
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r33 = r3
                r2 = r35
                r3 = 9
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r3, r10, r2)
                r10 = 512(0x200, float:7.175E-43)
                r32 = r2
                r2 = r10
                r31 = r36
            L_0x0256:
                r24 = r37
            L_0x0258:
                r3 = 0
                r10 = 6
            L_0x025a:
                r26 = 3
                goto L_0x034b
            L_0x025e:
                r33 = r3
                r11 = r34
                r3 = 9
                r34 = r2
                r2 = r35
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r32 = r2
                r3 = r36
                r2 = 8
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r2, r10, r3)
                r10 = 256(0x100, float:3.59E-43)
                r31 = r3
                r2 = r10
                goto L_0x0256
            L_0x027a:
                r33 = r3
                r11 = r34
                r32 = r35
                r3 = r36
                r34 = r2
                r2 = 8
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r31 = r3
                r2 = r37
                r3 = 7
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r3, r10, r2)
                r10 = 128(0x80, float:1.794E-43)
                r24 = r2
                r2 = r10
                goto L_0x0258
            L_0x0297:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r3 = 7
                r10 = 6
                r34 = r2
                r2 = r37
                boolean r42 = r0.decodeBooleanElement(r1, r10)
                r30 = 64
                r24 = r2
                r2 = r30
            L_0x02af:
                r3 = 0
                goto L_0x025a
            L_0x02b1:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r3 = 5
                r10 = 6
                r34 = r2
                r2 = r37
                boolean r23 = r0.decodeBooleanElement(r1, r3)
                r29 = 32
                r24 = r2
                r2 = r29
                goto L_0x02af
            L_0x02ca:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r3 = 4
                r10 = 6
                r34 = r2
                r2 = r37
                java.lang.String r28 = r0.decodeStringElement(r1, r3)
                r24 = r2
                r40 = r28
                r2 = 16
                goto L_0x02af
            L_0x02e3:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r3 = 4
                r9 = 3
                r10 = 6
                r34 = r2
                r2 = r37
                boolean r27 = r0.decodeBooleanElement(r1, r9)
                r24 = r2
                r26 = r9
                r9 = r27
                r2 = 8
            L_0x02fe:
                r3 = 0
                goto L_0x034b
            L_0x0300:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r3 = 2
                r10 = 6
                r26 = 3
                r34 = r2
                r2 = r37
                java.lang.String r27 = r0.decodeStringElement(r1, r3)
                r24 = r2
                r39 = r27
                r2 = 4
                goto L_0x02fe
            L_0x031a:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r3 = 1
                r10 = 6
                r26 = 3
                r34 = r2
                r2 = r37
                int r22 = r0.decodeIntElement(r1, r3)
                r24 = r2
                r2 = 2
                goto L_0x02fe
            L_0x0332:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r3 = 0
                r10 = 6
                r26 = 3
                r34 = r2
                r2 = r37
                java.lang.String r24 = r0.decodeStringElement(r1, r3)
                r38 = r24
                r24 = r2
                r2 = 1
            L_0x034b:
                r44 = r44 | r2
                r37 = r24
                r36 = r31
                r35 = r32
            L_0x0353:
                r3 = r33
                r2 = r34
                r10 = 11
                r34 = r11
                goto L_0x011e
            L_0x035d:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r3 = 0
                r10 = 6
                r34 = r2
                r2 = r37
                r45 = r3
                goto L_0x0353
            L_0x036e:
                r33 = r3
                r11 = r34
                r32 = r35
                r31 = r36
                r34 = r2
                r2 = r37
                r17 = r4
                r21 = r5
                r3 = r8
                r27 = r9
                r10 = r12
                r20 = r13
                r25 = r22
                r29 = r23
                r5 = r31
                r12 = r32
                r16 = r33
                r47 = r34
                r24 = r38
                r26 = r39
                r28 = r40
                r45 = r41
                r30 = r42
                r46 = r43
                r23 = r44
                r13 = r2
                r2 = r7
            L_0x03a0:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.q r0 = new com.appsamurai.storyly.data.q
                r22 = r0
                r31 = r13
                com.appsamurai.storyly.data.c r31 = (com.appsamurai.storyly.data.c) r31
                r32 = r5
                com.appsamurai.storyly.data.c r32 = (com.appsamurai.storyly.data.c) r32
                r33 = r12
                com.appsamurai.storyly.data.c r33 = (com.appsamurai.storyly.data.c) r33
                r34 = r11
                com.appsamurai.storyly.data.c r34 = (com.appsamurai.storyly.data.c) r34
                r35 = r10
                com.appsamurai.storyly.data.c r35 = (com.appsamurai.storyly.data.c) r35
                r36 = r21
                com.appsamurai.storyly.data.c r36 = (com.appsamurai.storyly.data.c) r36
                r37 = r20
                com.appsamurai.storyly.data.c r37 = (com.appsamurai.storyly.data.c) r37
                r38 = r14
                com.appsamurai.storyly.data.c r38 = (com.appsamurai.storyly.data.c) r38
                r39 = r15
                com.appsamurai.storyly.data.c r39 = (com.appsamurai.storyly.data.c) r39
                r40 = r17
                com.appsamurai.storyly.data.c r40 = (com.appsamurai.storyly.data.c) r40
                r41 = r16
                java.lang.String r41 = (java.lang.String) r41
                r42 = r6
                java.lang.Float r42 = (java.lang.Float) r42
                r43 = r2
                java.lang.Integer r43 = (java.lang.Integer) r43
                r44 = r3
                java.lang.Float r44 = (java.lang.Float) r44
                r48 = 0
                r22.<init>(r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.q.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4145b;
        }

        public void serialize(Encoder encoder, Object obj) {
            q qVar = (q) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(qVar, "value");
            SerialDescriptor serialDescriptor = f4145b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(qVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(qVar, beginStructure, serialDescriptor);
            beginStructure.encodeStringElement(serialDescriptor, 0, qVar.f4120a);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || qVar.f4121b != 0) {
                beginStructure.encodeIntElement(serialDescriptor, 1, qVar.f4121b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || !Intrinsics.areEqual((Object) qVar.f4122c, (Object) "")) {
                beginStructure.encodeStringElement(serialDescriptor, 2, qVar.f4122c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || !qVar.f4123d) {
                beginStructure.encodeBooleanElement(serialDescriptor, 3, qVar.f4123d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || !Intrinsics.areEqual((Object) qVar.f4124e, (Object) "")) {
                beginStructure.encodeStringElement(serialDescriptor, 4, qVar.f4124e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || !qVar.f4125f) {
                beginStructure.encodeBooleanElement(serialDescriptor, 5, qVar.f4125f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || qVar.f4126g) {
                beginStructure.encodeBooleanElement(serialDescriptor, 6, qVar.f4126g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || qVar.f4127h != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, c.f3622b, qVar.f4127h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || qVar.f4128i != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, c.f3622b, qVar.f4128i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || qVar.f4129j != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, c.f3622b, qVar.f4129j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || qVar.f4130k != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 10, c.f3622b, qVar.f4130k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || qVar.f4131l != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 11, c.f3622b, qVar.f4131l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || qVar.f4132m != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 12, c.f3622b, qVar.f4132m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || qVar.f4133n != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 13, c.f3622b, qVar.f4133n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || qVar.f4134o != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 14, c.f3622b, qVar.f4134o);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 15) || qVar.f4135p != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 15, c.f3622b, qVar.f4135p);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 16) || qVar.f4136q != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 16, c.f3622b, qVar.f4136q);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 17) || qVar.f4137r != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 17, StringSerializer.INSTANCE, qVar.f4137r);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 18) || qVar.f4138s != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 18, FloatSerializer.INSTANCE, qVar.f4138s);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 19) || qVar.f4139t != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 19, IntSerializer.INSTANCE, qVar.f4139t);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 20) || qVar.f4140u != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 20, FloatSerializer.INSTANCE, qVar.f4140u);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 21) || !Intrinsics.areEqual((Object) Float.valueOf(qVar.f4141v), (Object) Float.valueOf(0.1f))) {
                beginStructure.encodeFloatElement(serialDescriptor, 21, qVar.f4141v);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 22) || !Intrinsics.areEqual((Object) Float.valueOf(qVar.f4142w), (Object) Float.valueOf(0.8f))) {
                beginStructure.encodeFloatElement(serialDescriptor, 22, qVar.f4142w);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 23) || !Intrinsics.areEqual((Object) Float.valueOf(qVar.f4143x), (Object) Float.valueOf(0.1f))) {
                beginStructure.encodeFloatElement(serialDescriptor, 23, qVar.f4143x);
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
    public /* synthetic */ q(int i3, @Required @SerialName("theme") String str, @SerialName("scale") int i4, @SerialName("title") String str2, @SerialName("has_title") boolean z2, @SerialName("placeholder") String str3, @SerialName("is_bold") boolean z3, @SerialName("is_italic") boolean z4, @SerialName("primary_color") c cVar, @SerialName("secondary_color") c cVar2, @SerialName("bg_color") c cVar3, @SerialName("border_color") c cVar4, @SerialName("t_color") c cVar5, @SerialName("i_bg_color") c cVar6, @SerialName("i_border_color") c cVar7, @SerialName("i_color") c cVar8, @SerialName("s_button_bg_color") c cVar9, @SerialName("s_button_color") c cVar10, @SerialName("a_t") String str4, @SerialName("a_h") Float f2, @SerialName("a_l_c") Integer num, @SerialName("a_l_h") Float f3, float f4, float f5, float f6, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        int i5 = i3;
        if (1 != (i5 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 1, a.f4144a.getDescriptor());
        }
        this.f4120a = str;
        if ((i5 & 2) == 0) {
            this.f4121b = 0;
        } else {
            this.f4121b = i4;
        }
        if ((i5 & 4) == 0) {
            this.f4122c = "";
        } else {
            this.f4122c = str2;
        }
        if ((i5 & 8) == 0) {
            this.f4123d = true;
        } else {
            this.f4123d = z2;
        }
        if ((i5 & 16) == 0) {
            this.f4124e = "";
        } else {
            this.f4124e = str3;
        }
        if ((i5 & 32) == 0) {
            this.f4125f = true;
        } else {
            this.f4125f = z3;
        }
        if ((i5 & 64) == 0) {
            this.f4126g = false;
        } else {
            this.f4126g = z4;
        }
        if ((i5 & 128) == 0) {
            this.f4127h = null;
        } else {
            this.f4127h = cVar;
        }
        if ((i5 & 256) == 0) {
            this.f4128i = null;
        } else {
            this.f4128i = cVar2;
        }
        if ((i5 & 512) == 0) {
            this.f4129j = null;
        } else {
            this.f4129j = cVar3;
        }
        if ((i5 & 1024) == 0) {
            this.f4130k = null;
        } else {
            this.f4130k = cVar4;
        }
        if ((i5 & 2048) == 0) {
            this.f4131l = null;
        } else {
            this.f4131l = cVar5;
        }
        if ((i5 & 4096) == 0) {
            this.f4132m = null;
        } else {
            this.f4132m = cVar6;
        }
        if ((i5 & 8192) == 0) {
            this.f4133n = null;
        } else {
            this.f4133n = cVar7;
        }
        if ((i5 & 16384) == 0) {
            this.f4134o = null;
        } else {
            this.f4134o = cVar8;
        }
        if ((32768 & i5) == 0) {
            this.f4135p = null;
        } else {
            this.f4135p = cVar9;
        }
        if ((65536 & i5) == 0) {
            this.f4136q = null;
        } else {
            this.f4136q = cVar10;
        }
        if ((131072 & i5) == 0) {
            this.f4137r = null;
        } else {
            this.f4137r = str4;
        }
        if ((262144 & i5) == 0) {
            this.f4138s = null;
        } else {
            this.f4138s = f2;
        }
        if ((524288 & i5) == 0) {
            this.f4139t = null;
        } else {
            this.f4139t = num;
        }
        if ((1048576 & i5) == 0) {
            this.f4140u = null;
        } else {
            this.f4140u = f3;
        }
        if ((2097152 & i5) == 0) {
            this.f4141v = 0.1f;
        } else {
            this.f4141v = f4;
        }
        this.f4142w = (4194304 & i5) == 0 ? 0.8f : f5;
        if ((i5 & 8388608) == 0) {
            this.f4143x = 0.1f;
        } else {
            this.f4143x = f6;
        }
    }

    public static q a(q qVar, String str, int i3, String str2, boolean z2, String str3, boolean z3, boolean z4, c cVar, c cVar2, c cVar3, c cVar4, c cVar5, c cVar6, c cVar7, c cVar8, c cVar9, c cVar10, String str4, Float f2, Integer num, Float f3, int i4) {
        q qVar2 = qVar;
        int i5 = i4;
        String str5 = (i5 & 1) != 0 ? qVar2.f4120a : null;
        int i6 = (i5 & 2) != 0 ? qVar2.f4121b : i3;
        String str6 = (i5 & 4) != 0 ? qVar2.f4122c : null;
        boolean z5 = (i5 & 8) != 0 ? qVar2.f4123d : z2;
        String str7 = (i5 & 16) != 0 ? qVar2.f4124e : null;
        boolean z6 = (i5 & 32) != 0 ? qVar2.f4125f : z3;
        boolean z7 = (i5 & 64) != 0 ? qVar2.f4126g : z4;
        c cVar11 = (i5 & 128) != 0 ? qVar2.f4127h : null;
        c cVar12 = (i5 & 256) != 0 ? qVar2.f4128i : null;
        c cVar13 = (i5 & 512) != 0 ? qVar2.f4129j : null;
        c cVar14 = (i5 & 1024) != 0 ? qVar2.f4130k : null;
        c cVar15 = (i5 & 2048) != 0 ? qVar2.f4131l : null;
        c cVar16 = (i5 & 4096) != 0 ? qVar2.f4132m : null;
        c cVar17 = (i5 & 8192) != 0 ? qVar2.f4133n : null;
        c cVar18 = (i5 & 16384) != 0 ? qVar2.f4134o : null;
        c cVar19 = (i5 & 32768) != 0 ? qVar2.f4135p : null;
        c cVar20 = (i5 & 65536) != 0 ? qVar2.f4136q : null;
        String str8 = (i5 & 131072) != 0 ? qVar2.f4137r : null;
        Float f4 = (i5 & 262144) != 0 ? qVar2.f4138s : null;
        Integer num2 = (i5 & 524288) != 0 ? qVar2.f4139t : null;
        Float f5 = (i5 & 1048576) != 0 ? qVar2.f4140u : null;
        qVar.getClass();
        Intrinsics.checkNotNullParameter(str5, "theme");
        Intrinsics.checkNotNullParameter(str6, "title");
        Intrinsics.checkNotNullParameter(str7, "inputPlaceholder");
        return new q(str5, i6, str6, z5, str7, z6, z7, cVar11, cVar12, cVar13, cVar14, cVar15, cVar16, cVar17, cVar18, cVar19, cVar20, str8, f4, num2, f5);
    }

    public final boolean b() {
        String str = this.f4137r;
        return !(str == null || StringsKt.isBlank(str));
    }

    @NotNull
    public final c c() {
        c cVar = this.f4129j;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = this.f4128i;
        return cVar2 == null ? Intrinsics.areEqual((Object) this.f4120a, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_212121.b() : new c(-1) : cVar2;
    }

    @NotNull
    public final c d() {
        c cVar = this.f4132m;
        return cVar == null ? new c(f.a(-1, this.f4142w)) : cVar;
    }

    @NotNull
    public final c e() {
        c cVar = this.f4134o;
        return cVar == null ? new c(ViewCompat.MEASURED_STATE_MASK) : cVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return Intrinsics.areEqual((Object) this.f4120a, (Object) qVar.f4120a) && this.f4121b == qVar.f4121b && Intrinsics.areEqual((Object) this.f4122c, (Object) qVar.f4122c) && this.f4123d == qVar.f4123d && Intrinsics.areEqual((Object) this.f4124e, (Object) qVar.f4124e) && this.f4125f == qVar.f4125f && this.f4126g == qVar.f4126g && Intrinsics.areEqual((Object) this.f4127h, (Object) qVar.f4127h) && Intrinsics.areEqual((Object) this.f4128i, (Object) qVar.f4128i) && Intrinsics.areEqual((Object) this.f4129j, (Object) qVar.f4129j) && Intrinsics.areEqual((Object) this.f4130k, (Object) qVar.f4130k) && Intrinsics.areEqual((Object) this.f4131l, (Object) qVar.f4131l) && Intrinsics.areEqual((Object) this.f4132m, (Object) qVar.f4132m) && Intrinsics.areEqual((Object) this.f4133n, (Object) qVar.f4133n) && Intrinsics.areEqual((Object) this.f4134o, (Object) qVar.f4134o) && Intrinsics.areEqual((Object) this.f4135p, (Object) qVar.f4135p) && Intrinsics.areEqual((Object) this.f4136q, (Object) qVar.f4136q) && Intrinsics.areEqual((Object) this.f4137r, (Object) qVar.f4137r) && Intrinsics.areEqual((Object) this.f4138s, (Object) qVar.f4138s) && Intrinsics.areEqual((Object) this.f4139t, (Object) qVar.f4139t) && Intrinsics.areEqual((Object) this.f4140u, (Object) qVar.f4140u);
    }

    @NotNull
    public final c f() {
        c cVar = this.f4131l;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = this.f4127h;
        return cVar2 == null ? Intrinsics.areEqual((Object) this.f4120a, (Object) "Dark") ? new c(-1) : com.appsamurai.storyly.config.styling.a.COLOR_212121.b() : cVar2;
    }

    public int hashCode() {
        int i3 = androidx.compose.animation.core.a.i(this.f4122c, androidx.compose.animation.core.a.c(this.f4121b, this.f4120a.hashCode() * 31, 31), 31);
        boolean z2 = this.f4123d;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = androidx.compose.animation.core.a.i(this.f4124e, (i3 + (z2 ? 1 : 0)) * 31, 31);
        boolean z4 = this.f4125f;
        if (z4) {
            z4 = true;
        }
        int i5 = (i4 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f4126g;
        if (!z5) {
            z3 = z5;
        }
        int i6 = (i5 + (z3 ? 1 : 0)) * 31;
        c cVar = this.f4127h;
        int i7 = 0;
        int hashCode = (i6 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f4128i;
        int hashCode2 = (hashCode + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f4129j;
        int hashCode3 = (hashCode2 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f4130k;
        int hashCode4 = (hashCode3 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f4131l;
        int hashCode5 = (hashCode4 + (cVar5 == null ? 0 : Integer.hashCode(cVar5.f3624a))) * 31;
        c cVar6 = this.f4132m;
        int hashCode6 = (hashCode5 + (cVar6 == null ? 0 : Integer.hashCode(cVar6.f3624a))) * 31;
        c cVar7 = this.f4133n;
        int hashCode7 = (hashCode6 + (cVar7 == null ? 0 : Integer.hashCode(cVar7.f3624a))) * 31;
        c cVar8 = this.f4134o;
        int hashCode8 = (hashCode7 + (cVar8 == null ? 0 : Integer.hashCode(cVar8.f3624a))) * 31;
        c cVar9 = this.f4135p;
        int hashCode9 = (hashCode8 + (cVar9 == null ? 0 : Integer.hashCode(cVar9.f3624a))) * 31;
        c cVar10 = this.f4136q;
        int hashCode10 = (hashCode9 + (cVar10 == null ? 0 : Integer.hashCode(cVar10.f3624a))) * 31;
        String str = this.f4137r;
        int hashCode11 = (hashCode10 + (str == null ? 0 : str.hashCode())) * 31;
        Float f2 = this.f4138s;
        int hashCode12 = (hashCode11 + (f2 == null ? 0 : f2.hashCode())) * 31;
        Integer num = this.f4139t;
        int hashCode13 = (hashCode12 + (num == null ? 0 : num.hashCode())) * 31;
        Float f3 = this.f4140u;
        if (f3 != null) {
            i7 = f3.hashCode();
        }
        return hashCode13 + i7;
    }

    @NotNull
    public String toString() {
        return "StorylyCommentLayer(theme=" + this.f4120a + ", scale=" + this.f4121b + ", title=" + this.f4122c + ", hasTitle=" + this.f4123d + ", inputPlaceholder=" + this.f4124e + ", isBold=" + this.f4125f + ", isItalic=" + this.f4126g + ", primaryColor=" + this.f4127h + ", secondaryColor=" + this.f4128i + ", backgroundColor=" + this.f4129j + ", borderColor=" + this.f4130k + ", titleTextColor=" + this.f4131l + ", inputBackgroundColor=" + this.f4132m + ", inputBorderColor=" + this.f4133n + ", inputColor=" + this.f4134o + ", sendButtonBackgroundColor=" + this.f4135p + ", sendButtonColor=" + this.f4136q + ", answerText=" + this.f4137r + ", answerHeight=" + this.f4138s + ", answerLineCount=" + this.f4139t + ", answerLineHeight=" + this.f4140u + ')';
    }

    public q(@NotNull String str, int i3, @NotNull String str2, boolean z2, @NotNull String str3, boolean z3, boolean z4, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, @Nullable c cVar6, @Nullable c cVar7, @Nullable c cVar8, @Nullable c cVar9, @Nullable c cVar10, @Nullable String str4, @Nullable Float f2, @Nullable Integer num, @Nullable Float f3) {
        Intrinsics.checkNotNullParameter(str, "theme");
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(str3, "inputPlaceholder");
        this.f4120a = str;
        this.f4121b = i3;
        this.f4122c = str2;
        this.f4123d = z2;
        this.f4124e = str3;
        this.f4125f = z3;
        this.f4126g = z4;
        this.f4127h = cVar;
        this.f4128i = cVar2;
        this.f4129j = cVar3;
        this.f4130k = cVar4;
        this.f4131l = cVar5;
        this.f4132m = cVar6;
        this.f4133n = cVar7;
        this.f4134o = cVar8;
        this.f4135p = cVar9;
        this.f4136q = cVar10;
        this.f4137r = str4;
        this.f4138s = f2;
        this.f4139t = num;
        this.f4140u = f3;
        this.f4141v = 0.1f;
        this.f4142w = 0.8f;
        this.f4143x = 0.1f;
    }

    public StoryComponent a(b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryCommentComponent(b0Var.f3614i, "");
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var, @NotNull String str) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        Intrinsics.checkNotNullParameter(str, "userResponse");
        return new StoryCommentComponent(b0Var.f3614i, str);
    }

    public final boolean a() {
        return this.f4123d && this.f4122c.length() > 0;
    }

    public final float a(@NotNull Float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "scaleList");
        Float f2 = (Float) ArraysKt.getOrNull((T[]) fArr, this.f4121b);
        if (f2 == null) {
            return 0.0f;
        }
        return f2.floatValue();
    }
}
