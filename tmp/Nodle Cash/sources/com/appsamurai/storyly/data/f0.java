package com.appsamurai.storyly.data;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
import com.appsamurai.storyly.data.c;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.google.firebase.analytics.FirebaseAnalytics;
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
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class f0 extends g0 {
    @NotNull

    /* renamed from: A  reason: collision with root package name */
    public String f3686A;
    @NotNull

    /* renamed from: B  reason: collision with root package name */
    public String f3687B;
    @NotNull

    /* renamed from: C  reason: collision with root package name */
    public String f3688C;
    @NotNull

    /* renamed from: D  reason: collision with root package name */
    public String f3689D;

    /* renamed from: E  reason: collision with root package name */
    public int f3690E;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f3691a;

    /* renamed from: b  reason: collision with root package name */
    public float f3692b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public String f3693c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final c f3694d;

    /* renamed from: e  reason: collision with root package name */
    public final boolean f3695e;

    /* renamed from: f  reason: collision with root package name */
    public final boolean f3696f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final String f3697g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f3698h;

    /* renamed from: i  reason: collision with root package name */
    public final boolean f3699i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final c f3700j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final String f3701k;

    /* renamed from: l  reason: collision with root package name */
    public final boolean f3702l;

    /* renamed from: m  reason: collision with root package name */
    public final boolean f3703m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public final c f3704n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public final c f3705o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public final c f3706p;
    @Nullable

    /* renamed from: q  reason: collision with root package name */
    public final c f3707q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public final c f3708r;
    @Nullable

    /* renamed from: s  reason: collision with root package name */
    public final c f3709s;
    @Nullable

    /* renamed from: t  reason: collision with root package name */
    public c f3710t;
    @Nullable

    /* renamed from: u  reason: collision with root package name */
    public String f3711u;
    @Nullable

    /* renamed from: v  reason: collision with root package name */
    public m f3712v;

    /* renamed from: w  reason: collision with root package name */
    public boolean f3713w;

    /* renamed from: x  reason: collision with root package name */
    public boolean f3714x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    public String f3715y;
    @NotNull

    /* renamed from: z  reason: collision with root package name */
    public String f3716z;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<f0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3717a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3718b;

        static {
            a aVar = new a();
            f3717a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyProductCardLayer", aVar, 31);
            pluginGeneratedSerialDescriptor.addElement("image_url", false);
            pluginGeneratedSerialDescriptor.addElement("border_radius", true);
            pluginGeneratedSerialDescriptor.addElement("title", true);
            pluginGeneratedSerialDescriptor.addElement("t_color", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            pluginGeneratedSerialDescriptor.addElement(FirebaseAnalytics.Param.PRICE, true);
            pluginGeneratedSerialDescriptor.addElement("price_is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("price_is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("p_color", true);
            pluginGeneratedSerialDescriptor.addElement("old_price", true);
            pluginGeneratedSerialDescriptor.addElement("old_price_is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("old_price_is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("old_price_color", true);
            pluginGeneratedSerialDescriptor.addElement("icon_color", true);
            pluginGeneratedSerialDescriptor.addElement("icon_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("primary_color", true);
            pluginGeneratedSerialDescriptor.addElement("secondary_color", true);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_color", true);
            pluginGeneratedSerialDescriptor.addElement("outlink", true);
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
            f3718b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(stringSerializer);
            c.a aVar = c.f3622b;
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable6 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable7 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable8 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable9 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable10 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable11 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable12 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable13 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable14 = BuiltinSerializersKt.getNullable(m.f3857b);
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, FloatSerializer.INSTANCE, nullable, nullable2, booleanSerializer, booleanSerializer, nullable3, booleanSerializer, booleanSerializer, nullable4, nullable5, booleanSerializer, booleanSerializer, nullable6, nullable7, nullable8, nullable9, nullable10, nullable11, nullable12, nullable13, nullable14, booleanSerializer, booleanSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, IntSerializer.INSTANCE};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0176, code lost:
            r37 = r51;
            r47 = r52;
            r38 = r53;
            r50 = r54;
            r51 = r1;
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0181, code lost:
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x018c, code lost:
            r7 = r16;
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x01e8, code lost:
            r7 = r3;
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x025d, code lost:
            r7 = r29;
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0280, code lost:
            r38 = r53;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0282, code lost:
            r50 = r54;
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x02c2, code lost:
            r2 = r50;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0374, code lost:
            r66 = r50;
            r50 = r2;
            r2 = r66;
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x03a8, code lost:
            r60 = r60 | r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x03c3, code lost:
            r7 = kotlin.Unit.INSTANCE;
            r53 = r38;
            r52 = r47;
            r54 = r50;
            r1 = r51;
            r3 = 30;
            r51 = r37;
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x03c3, code lost:
            r15 = r15;
            r10 = r10;
            r9 = r9;
            r5 = r5;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r69) {
            /*
                r68 = this;
                r0 = r69
                java.lang.String r11 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r11)
                kotlinx.serialization.descriptors.SerialDescriptor r11 = f3718b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r11)
                boolean r12 = r0.decodeSequentially()
                r7 = 10
                r8 = 9
                r9 = 7
                r10 = 6
                r13 = 5
                r14 = 3
                r1 = 8
                r15 = 4
                r2 = 2
                r3 = 1
                r4 = 0
                r5 = 0
                if (r12 == 0) goto L_0x0123
                java.lang.String r4 = r0.decodeStringElement(r11, r4)
                float r3 = r0.decodeFloatElement(r11, r3)
                kotlinx.serialization.internal.StringSerializer r12 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r11, r2, r12, r5)
                com.appsamurai.storyly.data.c$a r6 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r14 = r0.decodeNullableSerializableElement(r11, r14, r6, r5)
                boolean r15 = r0.decodeBooleanElement(r11, r15)
                boolean r13 = r0.decodeBooleanElement(r11, r13)
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r11, r10, r12, r5)
                boolean r9 = r0.decodeBooleanElement(r11, r9)
                boolean r1 = r0.decodeBooleanElement(r11, r1)
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r11, r8, r6, r5)
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r11, r7, r12, r5)
                r5 = 11
                boolean r5 = r0.decodeBooleanElement(r11, r5)
                r31 = r1
                r1 = 12
                boolean r1 = r0.decodeBooleanElement(r11, r1)
                r30 = r1
                r29 = r4
                r1 = 13
                r4 = 0
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r1, r6, r4)
                r32 = r1
                r1 = 14
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r1, r6, r4)
                r28 = r1
                r1 = 15
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r1, r6, r4)
                r27 = r1
                r1 = 16
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r1, r6, r4)
                r26 = r1
                r1 = 17
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r1, r6, r4)
                r25 = r1
                r1 = 18
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r1, r6, r4)
                r24 = r1
                r1 = 19
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r1, r6, r4)
                r6 = 20
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r11, r6, r12, r4)
                com.appsamurai.storyly.data.m$a r12 = com.appsamurai.storyly.data.m.f3857b
                r69 = r1
                r1 = 21
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r1, r12, r4)
                r4 = 22
                boolean r4 = r0.decodeBooleanElement(r11, r4)
                r12 = 23
                boolean r12 = r0.decodeBooleanElement(r11, r12)
                r22 = r1
                r1 = 24
                java.lang.String r1 = r0.decodeStringElement(r11, r1)
                r21 = r1
                r1 = 25
                java.lang.String r1 = r0.decodeStringElement(r11, r1)
                r20 = r1
                r1 = 26
                java.lang.String r1 = r0.decodeStringElement(r11, r1)
                r19 = r1
                r1 = 27
                java.lang.String r1 = r0.decodeStringElement(r11, r1)
                r18 = r1
                r1 = 28
                java.lang.String r1 = r0.decodeStringElement(r11, r1)
                r17 = r1
                r1 = 29
                java.lang.String r1 = r0.decodeStringElement(r11, r1)
                r16 = r3
                r3 = 30
                int r3 = r0.decodeIntElement(r11, r3)
                r23 = 2147483647(0x7fffffff, float:NaN)
                r64 = r1
                r65 = r3
                r57 = r4
                r46 = r5
                r42 = r9
                r3 = r10
                r58 = r12
                r40 = r13
                r39 = r15
                r36 = r16
                r63 = r17
                r62 = r18
                r61 = r19
                r60 = r20
                r59 = r21
                r1 = r22
                r34 = r23
                r15 = r24
                r10 = r26
                r9 = r27
                r35 = r29
                r47 = r30
                r43 = r31
                r5 = r69
                goto L_0x0416
            L_0x0123:
                r6 = r4
                r4 = r5
                r5 = r3
                r3 = 30
                r12 = 0
                r1 = r4
                r2 = r1
                r8 = r2
                r9 = r8
                r10 = r9
                r13 = r10
                r14 = r13
                r15 = r14
                r35 = r15
                r39 = r35
                r40 = r39
                r41 = r40
                r42 = r41
                r43 = r42
                r44 = r43
                r51 = r44
                r52 = r51
                r53 = r52
                r54 = r53
                r61 = r5
                r33 = r6
                r34 = r33
                r55 = r34
                r56 = r55
                r57 = r56
                r58 = r57
                r59 = r58
                r60 = r59
                r32 = r12
                r5 = r54
                r4 = r60
                r12 = r4
                r6 = r5
            L_0x0161:
                if (r61 == 0) goto L_0x03d5
                int r7 = r0.decodeElementIndex(r11)
                switch(r7) {
                    case -1: goto L_0x03ab;
                    case 0: goto L_0x0392;
                    case 1: goto L_0x037b;
                    case 2: goto L_0x035c;
                    case 3: goto L_0x033b;
                    case 4: goto L_0x031d;
                    case 5: goto L_0x0301;
                    case 6: goto L_0x02dd;
                    case 7: goto L_0x02c5;
                    case 8: goto L_0x02aa;
                    case 9: goto L_0x0288;
                    case 10: goto L_0x026c;
                    case 11: goto L_0x0261;
                    case 12: goto L_0x0253;
                    case 13: goto L_0x0246;
                    case 14: goto L_0x0239;
                    case 15: goto L_0x022b;
                    case 16: goto L_0x021e;
                    case 17: goto L_0x0211;
                    case 18: goto L_0x0204;
                    case 19: goto L_0x01f7;
                    case 20: goto L_0x01ea;
                    case 21: goto L_0x01dc;
                    case 22: goto L_0x01d1;
                    case 23: goto L_0x01c6;
                    case 24: goto L_0x01bb;
                    case 25: goto L_0x01b0;
                    case 26: goto L_0x01a5;
                    case 27: goto L_0x019a;
                    case 28: goto L_0x018f;
                    case 29: goto L_0x0184;
                    case 30: goto L_0x0170;
                    default: goto L_0x016a;
                }
            L_0x016a:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r7)
                throw r0
            L_0x0170:
                int r33 = r0.decodeIntElement(r11, r3)
                r7 = 1073741824(0x40000000, float:2.0)
            L_0x0176:
                r37 = r51
                r47 = r52
                r38 = r53
                r50 = r54
                r3 = 2
                r51 = r1
            L_0x0181:
                r1 = 0
                goto L_0x03a8
            L_0x0184:
                r7 = 29
                java.lang.String r44 = r0.decodeStringElement(r11, r7)
                r16 = 536870912(0x20000000, float:1.0842022E-19)
            L_0x018c:
                r7 = r16
                goto L_0x0176
            L_0x018f:
                r3 = 28
                r7 = 29
                java.lang.String r43 = r0.decodeStringElement(r11, r3)
                r16 = 268435456(0x10000000, float:2.5243549E-29)
                goto L_0x018c
            L_0x019a:
                r3 = 27
                r7 = 29
                java.lang.String r42 = r0.decodeStringElement(r11, r3)
                r16 = 134217728(0x8000000, float:3.85186E-34)
                goto L_0x018c
            L_0x01a5:
                r3 = 26
                r7 = 29
                java.lang.String r41 = r0.decodeStringElement(r11, r3)
                r16 = 67108864(0x4000000, float:1.5046328E-36)
                goto L_0x018c
            L_0x01b0:
                r3 = 25
                r7 = 29
                java.lang.String r40 = r0.decodeStringElement(r11, r3)
                r16 = 33554432(0x2000000, float:9.403955E-38)
                goto L_0x018c
            L_0x01bb:
                r3 = 24
                r7 = 29
                java.lang.String r39 = r0.decodeStringElement(r11, r3)
                r16 = 16777216(0x1000000, float:2.3509887E-38)
                goto L_0x018c
            L_0x01c6:
                r3 = 23
                r7 = 29
                boolean r57 = r0.decodeBooleanElement(r11, r3)
                r16 = 8388608(0x800000, float:1.17549435E-38)
                goto L_0x018c
            L_0x01d1:
                r3 = 22
                r7 = 29
                boolean r34 = r0.decodeBooleanElement(r11, r3)
                r16 = 4194304(0x400000, float:5.877472E-39)
                goto L_0x018c
            L_0x01dc:
                r7 = 29
                com.appsamurai.storyly.data.m$a r3 = com.appsamurai.storyly.data.m.f3857b
                r7 = 21
                java.lang.Object r14 = r0.decodeNullableSerializableElement(r11, r7, r3, r14)
                r3 = 2097152(0x200000, float:2.938736E-39)
            L_0x01e8:
                r7 = r3
                goto L_0x0176
            L_0x01ea:
                r7 = 21
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r7 = 20
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r11, r7, r3, r2)
                r3 = 1048576(0x100000, float:1.469368E-39)
                goto L_0x01e8
            L_0x01f7:
                r7 = 20
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r7 = 19
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r11, r7, r3, r5)
                r3 = 524288(0x80000, float:7.34684E-40)
                goto L_0x01e8
            L_0x0204:
                r7 = 19
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r7 = 18
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r11, r7, r3, r15)
                r3 = 262144(0x40000, float:3.67342E-40)
                goto L_0x01e8
            L_0x0211:
                r7 = 18
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r7 = 17
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r11, r7, r3, r13)
                r3 = 131072(0x20000, float:1.83671E-40)
                goto L_0x01e8
            L_0x021e:
                r7 = 17
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r7 = 16
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r11, r7, r3, r10)
                r3 = 65536(0x10000, float:9.18355E-41)
                goto L_0x01e8
            L_0x022b:
                r7 = 16
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r7 = 15
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r11, r7, r3, r9)
                r3 = 32768(0x8000, float:4.5918E-41)
                goto L_0x01e8
            L_0x0239:
                r7 = 15
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r7 = 14
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r11, r7, r3, r1)
                r3 = 16384(0x4000, float:2.2959E-41)
                goto L_0x01e8
            L_0x0246:
                r7 = 14
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r7 = 13
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r11, r7, r3, r8)
                r3 = 8192(0x2000, float:1.14794E-41)
                goto L_0x01e8
            L_0x0253:
                r3 = 12
                r7 = 13
                boolean r12 = r0.decodeBooleanElement(r11, r3)
                r29 = 4096(0x1000, float:5.74E-42)
            L_0x025d:
                r7 = r29
                goto L_0x0176
            L_0x0261:
                r3 = 11
                r7 = 13
                boolean r55 = r0.decodeBooleanElement(r11, r3)
                r29 = 2048(0x800, float:2.87E-42)
                goto L_0x025d
            L_0x026c:
                r7 = 13
                kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r7 = r51
                r51 = r1
                r1 = 10
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r11, r1, r3, r7)
                r7 = 1024(0x400, float:1.435E-42)
                r37 = r3
                r47 = r52
            L_0x0280:
                r38 = r53
            L_0x0282:
                r50 = r54
                r1 = 0
                r3 = 2
                goto L_0x03a8
            L_0x0288:
                r7 = r51
                r51 = r1
                r1 = 10
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r50 = r2
                r1 = r52
                r2 = 9
                java.lang.Object r52 = r0.decodeNullableSerializableElement(r11, r2, r3, r1)
                r1 = 512(0x200, float:7.175E-43)
                r37 = r7
                r2 = r50
                r47 = r52
                r38 = r53
                r50 = r54
                r3 = 2
                r7 = r1
                goto L_0x0181
            L_0x02aa:
                r50 = r2
                r7 = r51
                r2 = 9
                r3 = 8
                r51 = r1
                r1 = r52
                boolean r4 = r0.decodeBooleanElement(r11, r3)
                r49 = 256(0x100, float:3.59E-43)
                r47 = r1
                r37 = r7
                r7 = r49
            L_0x02c2:
                r2 = r50
                goto L_0x0280
            L_0x02c5:
                r50 = r2
                r7 = r51
                r2 = 7
                r3 = 8
                r51 = r1
                r1 = r52
                boolean r56 = r0.decodeBooleanElement(r11, r2)
                r48 = 128(0x80, float:1.794E-43)
                r47 = r1
                r37 = r7
                r7 = r48
                goto L_0x02c2
            L_0x02dd:
                r50 = r2
                r7 = r51
                r3 = 8
                r51 = r1
                r1 = r52
                kotlinx.serialization.internal.StringSerializer r2 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r47 = r1
                r3 = r53
                r1 = 6
                java.lang.Object r53 = r0.decodeNullableSerializableElement(r11, r1, r2, r3)
                r2 = 64
                r37 = r7
                r38 = r53
                r1 = 0
                r3 = 2
                r7 = r2
                r2 = r50
                r50 = r54
                goto L_0x03a8
            L_0x0301:
                r50 = r2
                r7 = r51
                r47 = r52
                r3 = r53
                r2 = 5
                r51 = r1
                r1 = 6
                boolean r58 = r0.decodeBooleanElement(r11, r2)
                r46 = 32
                r38 = r3
                r37 = r7
                r7 = r46
                r2 = r50
                goto L_0x0282
            L_0x031d:
                r50 = r2
                r7 = r51
                r47 = r52
                r3 = r53
                r2 = 5
                r51 = r1
                r1 = 4
                boolean r59 = r0.decodeBooleanElement(r11, r1)
                r38 = r3
                r37 = r7
                r2 = r50
                r50 = r54
                r1 = 0
                r3 = 2
                r7 = 16
                goto L_0x03a8
            L_0x033b:
                r50 = r2
                r7 = r51
                r47 = r52
                r3 = r53
                r2 = 5
                r51 = r1
                com.appsamurai.storyly.data.c$a r1 = com.appsamurai.storyly.data.c.f3622b
                r38 = r3
                r2 = r54
                r3 = 3
                java.lang.Object r54 = r0.decodeNullableSerializableElement(r11, r3, r1, r2)
                r37 = r7
                r2 = r50
                r50 = r54
                r1 = 0
                r3 = 2
                r7 = 8
                goto L_0x03a8
            L_0x035c:
                r50 = r2
                r7 = r51
                r47 = r52
                r38 = r53
                r2 = r54
                r3 = 3
                r51 = r1
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r3 = 2
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r11, r3, r1, r6)
                r37 = r7
                r1 = 0
                r7 = 4
            L_0x0374:
                r66 = r50
                r50 = r2
                r2 = r66
                goto L_0x03a8
            L_0x037b:
                r50 = r2
                r7 = r51
                r47 = r52
                r38 = r53
                r2 = r54
                r3 = 2
                r51 = r1
                r1 = 1
                float r32 = r0.decodeFloatElement(r11, r1)
                r37 = r7
                r1 = 0
                r7 = r3
                goto L_0x0374
            L_0x0392:
                r50 = r2
                r7 = r51
                r47 = r52
                r38 = r53
                r2 = r54
                r3 = 2
                r51 = r1
                r1 = 0
                java.lang.String r35 = r0.decodeStringElement(r11, r1)
                r37 = r7
                r7 = 1
                goto L_0x0374
            L_0x03a8:
                r60 = r60 | r7
                goto L_0x03c3
            L_0x03ab:
                r50 = r2
                r7 = r51
                r47 = r52
                r38 = r53
                r2 = r54
                r3 = 2
                r51 = r1
                r1 = 0
                r61 = r1
                r37 = r7
                r66 = r50
                r50 = r2
                r2 = r66
            L_0x03c3:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                r53 = r38
                r52 = r47
                r54 = r50
                r1 = r51
                r3 = 30
                r7 = 10
                r51 = r37
                goto L_0x0161
            L_0x03d5:
                r50 = r2
                r7 = r51
                r47 = r52
                r38 = r53
                r2 = r54
                r51 = r1
                r25 = r13
                r1 = r14
                r36 = r32
                r65 = r33
                r3 = r38
                r61 = r41
                r62 = r42
                r63 = r43
                r64 = r44
                r28 = r51
                r46 = r55
                r42 = r56
                r14 = r2
                r43 = r4
                r2 = r6
                r32 = r8
                r8 = r47
                r6 = r50
                r47 = r12
                r66 = r57
                r57 = r34
                r34 = r60
                r60 = r40
                r40 = r58
                r58 = r66
                r67 = r59
                r59 = r39
                r39 = r67
            L_0x0416:
                r0.endStructure(r11)
                com.appsamurai.storyly.data.f0 r0 = new com.appsamurai.storyly.data.f0
                r33 = r0
                r37 = r2
                java.lang.String r37 = (java.lang.String) r37
                r38 = r14
                com.appsamurai.storyly.data.c r38 = (com.appsamurai.storyly.data.c) r38
                r41 = r3
                java.lang.String r41 = (java.lang.String) r41
                r44 = r8
                com.appsamurai.storyly.data.c r44 = (com.appsamurai.storyly.data.c) r44
                r45 = r7
                java.lang.String r45 = (java.lang.String) r45
                r48 = r32
                com.appsamurai.storyly.data.c r48 = (com.appsamurai.storyly.data.c) r48
                r49 = r28
                com.appsamurai.storyly.data.c r49 = (com.appsamurai.storyly.data.c) r49
                r50 = r9
                com.appsamurai.storyly.data.c r50 = (com.appsamurai.storyly.data.c) r50
                r51 = r10
                com.appsamurai.storyly.data.c r51 = (com.appsamurai.storyly.data.c) r51
                r52 = r25
                com.appsamurai.storyly.data.c r52 = (com.appsamurai.storyly.data.c) r52
                r53 = r15
                com.appsamurai.storyly.data.c r53 = (com.appsamurai.storyly.data.c) r53
                r54 = r5
                com.appsamurai.storyly.data.c r54 = (com.appsamurai.storyly.data.c) r54
                r55 = r6
                java.lang.String r55 = (java.lang.String) r55
                r56 = r1
                com.appsamurai.storyly.data.m r56 = (com.appsamurai.storyly.data.m) r56
                r33.<init>(r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.f0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3718b;
        }

        public void serialize(Encoder encoder, Object obj) {
            f0 f0Var = (f0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(f0Var, "value");
            SerialDescriptor serialDescriptor = f3718b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(f0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeStringElement(serialDescriptor, 0, f0Var.f3691a);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || !Intrinsics.areEqual((Object) Float.valueOf(f0Var.f3692b), (Object) Float.valueOf(0.0f))) {
                beginStructure.encodeFloatElement(serialDescriptor, 1, f0Var.f3692b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || f0Var.f3693c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, f0Var.f3693c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || f0Var.f3694d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, c.f3622b, f0Var.f3694d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || !f0Var.f3695e) {
                beginStructure.encodeBooleanElement(serialDescriptor, 4, f0Var.f3695e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || f0Var.f3696f) {
                beginStructure.encodeBooleanElement(serialDescriptor, 5, f0Var.f3696f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || f0Var.f3697g != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, f0Var.f3697g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || !f0Var.f3698h) {
                beginStructure.encodeBooleanElement(serialDescriptor, 7, f0Var.f3698h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || f0Var.f3699i) {
                beginStructure.encodeBooleanElement(serialDescriptor, 8, f0Var.f3699i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || f0Var.f3700j != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, c.f3622b, f0Var.f3700j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || f0Var.f3701k != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, f0Var.f3701k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || f0Var.f3702l) {
                beginStructure.encodeBooleanElement(serialDescriptor, 11, f0Var.f3702l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || f0Var.f3703m) {
                beginStructure.encodeBooleanElement(serialDescriptor, 12, f0Var.f3703m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || f0Var.f3704n != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 13, c.f3622b, f0Var.f3704n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || f0Var.f3705o != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 14, c.f3622b, f0Var.f3705o);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 15) || f0Var.f3706p != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 15, c.f3622b, f0Var.f3706p);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 16) || f0Var.f3707q != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 16, c.f3622b, f0Var.f3707q);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 17) || f0Var.f3708r != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 17, c.f3622b, f0Var.f3708r);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 18) || f0Var.f3709s != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 18, c.f3622b, f0Var.f3709s);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 19) || f0Var.f3710t != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 19, c.f3622b, f0Var.f3710t);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 20) || f0Var.f3711u != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 20, StringSerializer.INSTANCE, f0Var.f3711u);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 21) || f0Var.f3712v != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 21, m.f3857b, f0Var.f3712v);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 22) || !f0Var.f3713w) {
                beginStructure.encodeBooleanElement(serialDescriptor, 22, f0Var.f3713w);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 23) || !f0Var.f3714x) {
                beginStructure.encodeBooleanElement(serialDescriptor, 23, f0Var.f3714x);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 24) || !Intrinsics.areEqual((Object) f0Var.f3715y, (Object) "Add to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 24, f0Var.f3715y);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 25) || !Intrinsics.areEqual((Object) f0Var.f3716z, (Object) "Go to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 25, f0Var.f3716z);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 26) || !Intrinsics.areEqual((Object) f0Var.f3686A, (Object) "Continue with Stories")) {
                beginStructure.encodeStringElement(serialDescriptor, 26, f0Var.f3686A);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 27) || !Intrinsics.areEqual((Object) f0Var.f3687B, (Object) "Added to your Cart successfully")) {
                beginStructure.encodeStringElement(serialDescriptor, 27, f0Var.f3687B);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 28) || !Intrinsics.areEqual((Object) f0Var.f3688C, (Object) "Go to Checkout")) {
                beginStructure.encodeStringElement(serialDescriptor, 28, f0Var.f3688C);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 29) || !Intrinsics.areEqual((Object) f0Var.f3689D, (Object) "Total")) {
                beginStructure.encodeStringElement(serialDescriptor, 29, f0Var.f3689D);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 30) || f0Var.f3690E != 4) {
                beginStructure.encodeIntElement(serialDescriptor, 30, f0Var.f3690E);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ f0(int i3, @Required @SerialName("image_url") String str, @SerialName("border_radius") float f2, @SerialName("title") String str2, @SerialName("t_color") c cVar, @SerialName("is_bold") boolean z2, @SerialName("is_italic") boolean z3, @SerialName("price") String str3, @SerialName("price_is_bold") boolean z4, @SerialName("price_is_italic") boolean z5, @SerialName("p_color") c cVar2, @SerialName("old_price") String str4, @SerialName("old_price_is_bold") boolean z6, @SerialName("old_price_is_italic") boolean z7, @SerialName("old_price_color") c cVar3, @SerialName("icon_color") c cVar4, @SerialName("icon_bg_color") c cVar5, @SerialName("primary_color") c cVar6, @SerialName("secondary_color") c cVar7, @SerialName("bg_color") c cVar8, @SerialName("border_color") c cVar9, @SerialName("outlink") String str5, @SerialName("products") m mVar, @SerialName("is_s_price_visible") boolean z8, @SerialName("is_price_visible") boolean z9, @SerialName("p_b_text") String str6, @SerialName("s_b_cart_text") String str7, @SerialName("s_b_back_text") String str8, @SerialName("s_message") String str9, @SerialName("checkout_b_text") String str10, @SerialName("t_text") String str11, @SerialName("max_v") int i4) {
        int i5 = i3;
        if (1 != (i5 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 1, a.f3717a.getDescriptor());
        }
        this.f3691a = str;
        this.f3692b = (i5 & 2) == 0 ? 0.0f : f2;
        if ((i5 & 4) == 0) {
            this.f3693c = null;
        } else {
            this.f3693c = str2;
        }
        if ((i5 & 8) == 0) {
            this.f3694d = null;
        } else {
            this.f3694d = cVar;
        }
        if ((i5 & 16) == 0) {
            this.f3695e = true;
        } else {
            this.f3695e = z2;
        }
        if ((i5 & 32) == 0) {
            this.f3696f = false;
        } else {
            this.f3696f = z3;
        }
        if ((i5 & 64) == 0) {
            this.f3697g = null;
        } else {
            this.f3697g = str3;
        }
        if ((i5 & 128) == 0) {
            this.f3698h = true;
        } else {
            this.f3698h = z4;
        }
        if ((i5 & 256) == 0) {
            this.f3699i = false;
        } else {
            this.f3699i = z5;
        }
        if ((i5 & 512) == 0) {
            this.f3700j = null;
        } else {
            this.f3700j = cVar2;
        }
        if ((i5 & 1024) == 0) {
            this.f3701k = null;
        } else {
            this.f3701k = str4;
        }
        if ((i5 & 2048) == 0) {
            this.f3702l = false;
        } else {
            this.f3702l = z6;
        }
        if ((i5 & 4096) == 0) {
            this.f3703m = false;
        } else {
            this.f3703m = z7;
        }
        if ((i5 & 8192) == 0) {
            this.f3704n = null;
        } else {
            this.f3704n = cVar3;
        }
        if ((i5 & 16384) == 0) {
            this.f3705o = null;
        } else {
            this.f3705o = cVar4;
        }
        if ((32768 & i5) == 0) {
            this.f3706p = null;
        } else {
            this.f3706p = cVar5;
        }
        if ((65536 & i5) == 0) {
            this.f3707q = null;
        } else {
            this.f3707q = cVar6;
        }
        if ((131072 & i5) == 0) {
            this.f3708r = null;
        } else {
            this.f3708r = cVar7;
        }
        if ((262144 & i5) == 0) {
            this.f3709s = null;
        } else {
            this.f3709s = cVar8;
        }
        if ((524288 & i5) == 0) {
            this.f3710t = null;
        } else {
            this.f3710t = cVar9;
        }
        if ((1048576 & i5) == 0) {
            this.f3711u = null;
        } else {
            this.f3711u = str5;
        }
        if ((2097152 & i5) == 0) {
            this.f3712v = null;
        } else {
            this.f3712v = mVar;
        }
        if ((4194304 & i5) == 0) {
            this.f3713w = true;
        } else {
            this.f3713w = z8;
        }
        if ((8388608 & i5) == 0) {
            this.f3714x = true;
        } else {
            this.f3714x = z9;
        }
        this.f3715y = (16777216 & i5) == 0 ? "Add to Cart" : str6;
        this.f3716z = (33554432 & i5) == 0 ? "Go to Cart" : str7;
        this.f3686A = (67108864 & i5) == 0 ? "Continue with Stories" : str8;
        this.f3687B = (134217728 & i5) == 0 ? "Added to your Cart successfully" : str9;
        this.f3688C = (268435456 & i5) == 0 ? "Go to Checkout" : str10;
        this.f3689D = (536870912 & i5) == 0 ? "Total" : str11;
        this.f3690E = (i5 & 1073741824) == 0 ? 4 : i4;
    }

    public static f0 a(f0 f0Var, String str, float f2, String str2, c cVar, boolean z2, boolean z3, String str3, boolean z4, boolean z5, c cVar2, String str4, boolean z6, boolean z7, c cVar3, c cVar4, c cVar5, c cVar6, c cVar7, c cVar8, c cVar9, String str5, m mVar, boolean z8, boolean z9, String str6, String str7, String str8, String str9, String str10, String str11, int i3, int i4) {
        String str12;
        m mVar2;
        f0 f0Var2 = f0Var;
        int i5 = i4;
        String str13 = (i5 & 1) != 0 ? f0Var2.f3691a : null;
        float f3 = (i5 & 2) != 0 ? f0Var2.f3692b : f2;
        String str14 = (i5 & 4) != 0 ? f0Var2.f3693c : null;
        c cVar10 = (i5 & 8) != 0 ? f0Var2.f3694d : null;
        boolean z10 = (i5 & 16) != 0 ? f0Var2.f3695e : z2;
        boolean z11 = (i5 & 32) != 0 ? f0Var2.f3696f : z3;
        String str15 = (i5 & 64) != 0 ? f0Var2.f3697g : null;
        boolean z12 = (i5 & 128) != 0 ? f0Var2.f3698h : z4;
        boolean z13 = (i5 & 256) != 0 ? f0Var2.f3699i : z5;
        c cVar11 = (i5 & 512) != 0 ? f0Var2.f3700j : null;
        String str16 = (i5 & 1024) != 0 ? f0Var2.f3701k : null;
        boolean z14 = (i5 & 2048) != 0 ? f0Var2.f3702l : z6;
        boolean z15 = (i5 & 4096) != 0 ? f0Var2.f3703m : z7;
        c cVar12 = (i5 & 8192) != 0 ? f0Var2.f3704n : null;
        c cVar13 = (i5 & 16384) != 0 ? f0Var2.f3705o : null;
        c cVar14 = (i5 & 32768) != 0 ? f0Var2.f3706p : null;
        c cVar15 = (i5 & 65536) != 0 ? f0Var2.f3707q : null;
        c cVar16 = (i5 & 131072) != 0 ? f0Var2.f3708r : null;
        c cVar17 = (i5 & 262144) != 0 ? f0Var2.f3709s : null;
        c cVar18 = (i5 & 524288) != 0 ? f0Var2.f3710t : null;
        String str17 = (i5 & 1048576) != 0 ? f0Var2.f3711u : null;
        if ((i5 & 2097152) != 0) {
            str12 = str17;
            mVar2 = f0Var2.f3712v;
        } else {
            str12 = str17;
            mVar2 = null;
        }
        m mVar3 = mVar2;
        boolean z16 = (i5 & 4194304) != 0 ? f0Var2.f3713w : z8;
        boolean z17 = (i5 & 8388608) != 0 ? f0Var2.f3714x : z9;
        String str18 = (i5 & 16777216) != 0 ? f0Var2.f3715y : null;
        boolean z18 = z15;
        String str19 = (i5 & 33554432) != 0 ? f0Var2.f3716z : null;
        boolean z19 = z14;
        String str20 = (i5 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? f0Var2.f3686A : null;
        String str21 = str16;
        String str22 = (i5 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? f0Var2.f3687B : null;
        c cVar19 = cVar11;
        String str23 = (i5 & 268435456) != 0 ? f0Var2.f3688C : null;
        boolean z20 = z13;
        String str24 = (i5 & 536870912) != 0 ? f0Var2.f3689D : null;
        int i6 = (i5 & 1073741824) != 0 ? f0Var2.f3690E : i3;
        f0Var.getClass();
        Intrinsics.checkNotNullParameter(str13, "imageUrl");
        Intrinsics.checkNotNullParameter(str18, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str19, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str20, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str22, "successMessage");
        Intrinsics.checkNotNullParameter(str23, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str24, "totalText");
        return new f0(str13, f3, str14, cVar10, z10, z11, str15, z12, z20, cVar19, str21, z19, z18, cVar12, cVar13, cVar14, cVar15, cVar16, cVar17, cVar18, str12, mVar3, z16, z17, str18, str19, str20, str22, str23, str24, i6);
    }

    @Nullable
    public m c() {
        return this.f3712v;
    }

    @NotNull
    public String d() {
        return this.f3715y;
    }

    @NotNull
    public String e() {
        return this.f3686A;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f0)) {
            return false;
        }
        f0 f0Var = (f0) obj;
        return Intrinsics.areEqual((Object) this.f3691a, (Object) f0Var.f3691a) && Intrinsics.areEqual((Object) Float.valueOf(this.f3692b), (Object) Float.valueOf(f0Var.f3692b)) && Intrinsics.areEqual((Object) this.f3693c, (Object) f0Var.f3693c) && Intrinsics.areEqual((Object) this.f3694d, (Object) f0Var.f3694d) && this.f3695e == f0Var.f3695e && this.f3696f == f0Var.f3696f && Intrinsics.areEqual((Object) this.f3697g, (Object) f0Var.f3697g) && this.f3698h == f0Var.f3698h && this.f3699i == f0Var.f3699i && Intrinsics.areEqual((Object) this.f3700j, (Object) f0Var.f3700j) && Intrinsics.areEqual((Object) this.f3701k, (Object) f0Var.f3701k) && this.f3702l == f0Var.f3702l && this.f3703m == f0Var.f3703m && Intrinsics.areEqual((Object) this.f3704n, (Object) f0Var.f3704n) && Intrinsics.areEqual((Object) this.f3705o, (Object) f0Var.f3705o) && Intrinsics.areEqual((Object) this.f3706p, (Object) f0Var.f3706p) && Intrinsics.areEqual((Object) this.f3707q, (Object) f0Var.f3707q) && Intrinsics.areEqual((Object) this.f3708r, (Object) f0Var.f3708r) && Intrinsics.areEqual((Object) this.f3709s, (Object) f0Var.f3709s) && Intrinsics.areEqual((Object) this.f3710t, (Object) f0Var.f3710t) && Intrinsics.areEqual((Object) this.f3711u, (Object) f0Var.f3711u) && Intrinsics.areEqual((Object) this.f3712v, (Object) f0Var.f3712v) && this.f3713w == f0Var.f3713w && this.f3714x == f0Var.f3714x && Intrinsics.areEqual((Object) this.f3715y, (Object) f0Var.f3715y) && Intrinsics.areEqual((Object) this.f3716z, (Object) f0Var.f3716z) && Intrinsics.areEqual((Object) this.f3686A, (Object) f0Var.f3686A) && Intrinsics.areEqual((Object) this.f3687B, (Object) f0Var.f3687B) && Intrinsics.areEqual((Object) this.f3688C, (Object) f0Var.f3688C) && Intrinsics.areEqual((Object) this.f3689D, (Object) f0Var.f3689D) && this.f3690E == f0Var.f3690E;
    }

    @NotNull
    public String f() {
        return this.f3716z;
    }

    @NotNull
    public String g() {
        return this.f3687B;
    }

    @NotNull
    public String h() {
        return this.f3689D;
    }

    public int hashCode() {
        int c3 = android.support.v4.media.session.a.c(this.f3692b, this.f3691a.hashCode() * 31, 31);
        String str = this.f3693c;
        int i3 = 0;
        int hashCode = (c3 + (str == null ? 0 : str.hashCode())) * 31;
        c cVar = this.f3694d;
        int hashCode2 = (hashCode + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        boolean z2 = this.f3695e;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (hashCode2 + (z2 ? 1 : 0)) * 31;
        boolean z4 = this.f3696f;
        if (z4) {
            z4 = true;
        }
        int i5 = (i4 + (z4 ? 1 : 0)) * 31;
        String str2 = this.f3697g;
        int hashCode3 = (i5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        boolean z5 = this.f3698h;
        if (z5) {
            z5 = true;
        }
        int i6 = (hashCode3 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.f3699i;
        if (z6) {
            z6 = true;
        }
        int i7 = (i6 + (z6 ? 1 : 0)) * 31;
        c cVar2 = this.f3700j;
        int hashCode4 = (i7 + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        String str3 = this.f3701k;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        boolean z7 = this.f3702l;
        if (z7) {
            z7 = true;
        }
        int i8 = (hashCode5 + (z7 ? 1 : 0)) * 31;
        boolean z8 = this.f3703m;
        if (z8) {
            z8 = true;
        }
        int i9 = (i8 + (z8 ? 1 : 0)) * 31;
        c cVar3 = this.f3704n;
        int hashCode6 = (i9 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f3705o;
        int hashCode7 = (hashCode6 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f3706p;
        int hashCode8 = (hashCode7 + (cVar5 == null ? 0 : Integer.hashCode(cVar5.f3624a))) * 31;
        c cVar6 = this.f3707q;
        int hashCode9 = (hashCode8 + (cVar6 == null ? 0 : Integer.hashCode(cVar6.f3624a))) * 31;
        c cVar7 = this.f3708r;
        int hashCode10 = (hashCode9 + (cVar7 == null ? 0 : Integer.hashCode(cVar7.f3624a))) * 31;
        c cVar8 = this.f3709s;
        int hashCode11 = (hashCode10 + (cVar8 == null ? 0 : Integer.hashCode(cVar8.f3624a))) * 31;
        c cVar9 = this.f3710t;
        int hashCode12 = (hashCode11 + (cVar9 == null ? 0 : Integer.hashCode(cVar9.f3624a))) * 31;
        String str4 = this.f3711u;
        int hashCode13 = (hashCode12 + (str4 == null ? 0 : str4.hashCode())) * 31;
        m mVar = this.f3712v;
        if (mVar != null) {
            i3 = mVar.hashCode();
        }
        int i10 = (hashCode13 + i3) * 31;
        boolean z9 = this.f3713w;
        if (z9) {
            z9 = true;
        }
        int i11 = (i10 + (z9 ? 1 : 0)) * 31;
        boolean z10 = this.f3714x;
        if (!z10) {
            z3 = z10;
        }
        return Integer.hashCode(this.f3690E) + androidx.compose.animation.core.a.i(this.f3689D, androidx.compose.animation.core.a.i(this.f3688C, androidx.compose.animation.core.a.i(this.f3687B, androidx.compose.animation.core.a.i(this.f3686A, androidx.compose.animation.core.a.i(this.f3716z, androidx.compose.animation.core.a.i(this.f3715y, (i11 + (z3 ? 1 : 0)) * 31, 31), 31), 31), 31), 31), 31);
    }

    public boolean i() {
        return this.f3714x;
    }

    public boolean j() {
        return this.f3713w;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyProductCardLayer(imageUrl=");
        sb.append(this.f3691a);
        sb.append(", borderRadius=");
        sb.append(this.f3692b);
        sb.append(", title=");
        sb.append(this.f3693c);
        sb.append(", titleColor=");
        sb.append(this.f3694d);
        sb.append(", isBold=");
        sb.append(this.f3695e);
        sb.append(", isItalic=");
        sb.append(this.f3696f);
        sb.append(", price=");
        sb.append(this.f3697g);
        sb.append(", priceIsBold=");
        sb.append(this.f3698h);
        sb.append(", priceIsItalic=");
        sb.append(this.f3699i);
        sb.append(", priceColor=");
        sb.append(this.f3700j);
        sb.append(", oldPrice=");
        sb.append(this.f3701k);
        sb.append(", oldPriceIsBold=");
        sb.append(this.f3702l);
        sb.append(", oldPriceIsItalic=");
        sb.append(this.f3703m);
        sb.append(", oldPriceColor=");
        sb.append(this.f3704n);
        sb.append(", iconColor=");
        sb.append(this.f3705o);
        sb.append(", iconBackgroundColor=");
        sb.append(this.f3706p);
        sb.append(", primaryColor=");
        sb.append(this.f3707q);
        sb.append(", secondaryColor=");
        sb.append(this.f3708r);
        sb.append(", backgroundColor=");
        sb.append(this.f3709s);
        sb.append(", borderColor=");
        sb.append(this.f3710t);
        sb.append(", outlink=");
        sb.append(this.f3711u);
        sb.append(", productData=");
        sb.append(this.f3712v);
        sb.append(", isProductSalesPriceVisible=");
        sb.append(this.f3713w);
        sb.append(", isProductPriceVisible=");
        sb.append(this.f3714x);
        sb.append(", purchaseButtonText=");
        sb.append(this.f3715y);
        sb.append(", successButtonCartText=");
        sb.append(this.f3716z);
        sb.append(", successButtonBackText=");
        sb.append(this.f3686A);
        sb.append(", successMessage=");
        sb.append(this.f3687B);
        sb.append(", checkoutButtonText=");
        sb.append(this.f3688C);
        sb.append(", totalText=");
        sb.append(this.f3689D);
        sb.append(", maxVariantCount=");
        return android.support.v4.media.session.a.p(sb, this.f3690E, ')');
    }

    public f0(@NotNull String str, float f2, @Nullable String str2, @Nullable c cVar, boolean z2, boolean z3, @Nullable String str3, boolean z4, boolean z5, @Nullable c cVar2, @Nullable String str4, boolean z6, boolean z7, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, @Nullable c cVar6, @Nullable c cVar7, @Nullable c cVar8, @Nullable c cVar9, @Nullable String str5, @Nullable m mVar, boolean z8, boolean z9, @NotNull String str6, @NotNull String str7, @NotNull String str8, @NotNull String str9, @NotNull String str10, @NotNull String str11, int i3) {
        String str12 = str6;
        String str13 = str7;
        String str14 = str8;
        String str15 = str9;
        String str16 = str10;
        String str17 = str11;
        Intrinsics.checkNotNullParameter(str, "imageUrl");
        Intrinsics.checkNotNullParameter(str12, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str13, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str14, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str15, "successMessage");
        Intrinsics.checkNotNullParameter(str16, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str17, "totalText");
        this.f3691a = str;
        this.f3692b = f2;
        this.f3693c = str2;
        this.f3694d = cVar;
        this.f3695e = z2;
        this.f3696f = z3;
        this.f3697g = str3;
        this.f3698h = z4;
        this.f3699i = z5;
        this.f3700j = cVar2;
        this.f3701k = str4;
        this.f3702l = z6;
        this.f3703m = z7;
        this.f3704n = cVar3;
        this.f3705o = cVar4;
        this.f3706p = cVar5;
        this.f3707q = cVar6;
        this.f3708r = cVar7;
        this.f3709s = cVar8;
        this.f3710t = cVar9;
        this.f3711u = str5;
        this.f3712v = mVar;
        this.f3713w = z8;
        this.f3714x = z9;
        this.f3715y = str12;
        this.f3716z = str13;
        this.f3686A = str14;
        this.f3687B = str15;
        this.f3688C = str16;
        this.f3689D = str17;
        this.f3690E = i3;
    }

    @NotNull
    public String a() {
        return this.f3688C;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.ProductCard);
    }
}
