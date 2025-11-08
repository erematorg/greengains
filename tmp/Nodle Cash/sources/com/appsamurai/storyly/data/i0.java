package com.appsamurai.storyly.data;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
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
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class i0 extends g0 {

    /* renamed from: A  reason: collision with root package name */
    public boolean f3753A;

    /* renamed from: B  reason: collision with root package name */
    public boolean f3754B;
    @NotNull

    /* renamed from: C  reason: collision with root package name */
    public String f3755C;
    @NotNull

    /* renamed from: D  reason: collision with root package name */
    public String f3756D;
    @NotNull

    /* renamed from: E  reason: collision with root package name */
    public String f3757E;
    @NotNull
    public String F;
    @NotNull
    public String G;
    @NotNull

    /* renamed from: H  reason: collision with root package name */
    public String f3758H;

    /* renamed from: I  reason: collision with root package name */
    public int f3759I;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f3760a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f3761b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public String f3762c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final r0 f3763d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final c f3764e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public final c f3765f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final c f3766g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public c f3767h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public final c f3768i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final c f3769j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final String f3770k;

    /* renamed from: l  reason: collision with root package name */
    public final boolean f3771l;

    /* renamed from: m  reason: collision with root package name */
    public final boolean f3772m;

    /* renamed from: n  reason: collision with root package name */
    public final boolean f3773n;

    /* renamed from: o  reason: collision with root package name */
    public final boolean f3774o;

    /* renamed from: p  reason: collision with root package name */
    public final boolean f3775p;

    /* renamed from: q  reason: collision with root package name */
    public final boolean f3776q;
    @Nullable

    /* renamed from: r  reason: collision with root package name */
    public final c f3777r;
    @Nullable

    /* renamed from: s  reason: collision with root package name */
    public final String f3778s;
    @Nullable

    /* renamed from: t  reason: collision with root package name */
    public final c f3779t;
    @Nullable

    /* renamed from: u  reason: collision with root package name */
    public final c f3780u;
    @Nullable

    /* renamed from: v  reason: collision with root package name */
    public final String f3781v;
    @Nullable

    /* renamed from: w  reason: collision with root package name */
    public final c f3782w;
    @Nullable

    /* renamed from: x  reason: collision with root package name */
    public final c f3783x;
    @Nullable

    /* renamed from: y  reason: collision with root package name */
    public final c f3784y;
    @Nullable

    /* renamed from: z  reason: collision with root package name */
    public m f3785z;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<i0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3786a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3787b;

        static {
            a aVar = new a();
            f3786a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyProductTagLayer", aVar, 35);
            pluginGeneratedSerialDescriptor.addElement("title", false);
            pluginGeneratedSerialDescriptor.addElement("theme", false);
            pluginGeneratedSerialDescriptor.addElement("outlink", false);
            pluginGeneratedSerialDescriptor.addElement("tooltip_placement", true);
            pluginGeneratedSerialDescriptor.addElement("primary_color", true);
            pluginGeneratedSerialDescriptor.addElement("secondary_color", true);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_color", true);
            pluginGeneratedSerialDescriptor.addElement("t_color", true);
            pluginGeneratedSerialDescriptor.addElement("p_color", true);
            pluginGeneratedSerialDescriptor.addElement(FirebaseAnalytics.Param.PRICE, true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("price_is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("price_is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("old_price_is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("old_price_is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("price_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("old_price", true);
            pluginGeneratedSerialDescriptor.addElement("old_price_color", true);
            pluginGeneratedSerialDescriptor.addElement("chevron_color", true);
            pluginGeneratedSerialDescriptor.addElement("icon_type", true);
            pluginGeneratedSerialDescriptor.addElement("icon_color", true);
            pluginGeneratedSerialDescriptor.addElement("icon_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("icon_border_color", true);
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
            f3787b = pluginGeneratedSerialDescriptor;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: kotlinx.serialization.KSerializer<?>[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        @org.jetbrains.annotations.NotNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public kotlinx.serialization.KSerializer<?>[] childSerializers() {
            /*
                r18 = this;
                kotlinx.serialization.internal.StringSerializer r0 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                com.appsamurai.storyly.data.c$a r1 = com.appsamurai.storyly.data.c.f3622b
                kotlinx.serialization.KSerializer r2 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r3 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r4 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r5 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r6 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r7 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r8 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r9 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r0)
                kotlinx.serialization.KSerializer r10 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r11 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r12 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r0)
                kotlinx.serialization.KSerializer r13 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r14 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                kotlinx.serialization.KSerializer r1 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r1)
                com.appsamurai.storyly.data.m$a r15 = com.appsamurai.storyly.data.m.f3857b
                kotlinx.serialization.KSerializer r15 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r15)
                r18 = r15
                r15 = 35
                kotlinx.serialization.KSerializer[] r15 = new kotlinx.serialization.KSerializer[r15]
                r16 = 0
                r15[r16] = r0
                r16 = 1
                r15[r16] = r0
                r16 = 2
                r15[r16] = r0
                com.appsamurai.storyly.data.r0$a r16 = com.appsamurai.storyly.data.r0.f4186a
                r17 = 3
                r15[r17] = r16
                r16 = 4
                r15[r16] = r2
                r2 = 5
                r15[r2] = r3
                r2 = 6
                r15[r2] = r4
                r2 = 7
                r15[r2] = r5
                r2 = 8
                r15[r2] = r6
                r2 = 9
                r15[r2] = r7
                r2 = 10
                r15[r2] = r0
                kotlinx.serialization.internal.BooleanSerializer r2 = kotlinx.serialization.internal.BooleanSerializer.INSTANCE
                r3 = 11
                r15[r3] = r2
                r3 = 12
                r15[r3] = r2
                r3 = 13
                r15[r3] = r2
                r3 = 14
                r15[r3] = r2
                r3 = 15
                r15[r3] = r2
                r3 = 16
                r15[r3] = r2
                r3 = 17
                r15[r3] = r8
                r3 = 18
                r15[r3] = r9
                r3 = 19
                r15[r3] = r10
                r3 = 20
                r15[r3] = r11
                r3 = 21
                r15[r3] = r12
                r3 = 22
                r15[r3] = r13
                r3 = 23
                r15[r3] = r14
                r3 = 24
                r15[r3] = r1
                r1 = 25
                r15[r1] = r18
                r1 = 26
                r15[r1] = r2
                r1 = 27
                r15[r1] = r2
                r1 = 28
                r15[r1] = r0
                r1 = 29
                r15[r1] = r0
                r1 = 30
                r15[r1] = r0
                r1 = 31
                r15[r1] = r0
                r1 = 32
                r15[r1] = r0
                r1 = 33
                r15[r1] = r0
                kotlinx.serialization.internal.IntSerializer r0 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r1 = 34
                r15[r1] = r0
                return r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.i0.a.childSerializers():kotlinx.serialization.KSerializer[]");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x01ae, code lost:
            r13 = r52;
            r14 = r53;
            r4 = r54;
            r36 = r55;
            r47 = r56;
            r42 = r57;
            r30 = r58;
            r29 = r59;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x01ef, code lost:
            r49 = r1;
            r48 = r2;
            r3 = r14;
            r1 = r17;
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x01f6, code lost:
            r13 = r52;
            r14 = r53;
            r4 = r54;
            r36 = r55;
            r47 = r56;
            r42 = r57;
            r30 = r58;
            r29 = r59;
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0207, code lost:
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0244, code lost:
            r49 = r1;
            r48 = r2;
            r1 = r4;
            r3 = r14;
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x02ae, code lost:
            r14 = r53;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x02b0, code lost:
            r4 = r54;
            r36 = r55;
            r47 = r56;
            r42 = r57;
            r30 = r58;
            r29 = r59;
            r3 = 2;
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0360, code lost:
            r4 = r54;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0362, code lost:
            r36 = r55;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0364, code lost:
            r47 = r56;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0366, code lost:
            r42 = r57;
            r30 = r58;
            r29 = r59;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x036c, code lost:
            r3 = 2;
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0382, code lost:
            r49 = r1;
            r48 = r2;
            r1 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x04c4, code lost:
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0509, code lost:
            r67 = r67 | r1;
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x050b, code lost:
            r1 = r49;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x052d, code lost:
            r49 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x052f, code lost:
            r54 = r4;
            r52 = r13;
            r53 = r14;
            r59 = r29;
            r58 = r30;
            r55 = r36;
            r57 = r42;
            r56 = r47;
            r2 = r48;
            r13 = 34;
            r14 = r3;
            r3 = 4;
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x052f, code lost:
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x052f, code lost:
            r10 = r10;
            r9 = r9;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r89) {
            /*
                r88 = this;
                r0 = r89
                java.lang.String r15 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r15)
                kotlinx.serialization.descriptors.SerialDescriptor r15 = f3787b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r15)
                boolean r16 = r0.decodeSequentially()
                r8 = 11
                r9 = 10
                r10 = 9
                r11 = 6
                r12 = 5
                r13 = 3
                r1 = 8
                r2 = 7
                r3 = 4
                r14 = 2
                r4 = 1
                r5 = 0
                r6 = 0
                if (r16 == 0) goto L_0x0148
                java.lang.String r5 = r0.decodeStringElement(r15, r5)
                java.lang.String r4 = r0.decodeStringElement(r15, r4)
                java.lang.String r14 = r0.decodeStringElement(r15, r14)
                com.appsamurai.storyly.data.r0$a r7 = com.appsamurai.storyly.data.r0.f4186a
                java.lang.Object r7 = r0.decodeSerializableElement(r15, r13, r7, r6)
                com.appsamurai.storyly.data.c$a r13 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r15, r3, r13, r6)
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r15, r12, r13, r6)
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r15, r11, r13, r6)
                java.lang.Object r35 = r0.decodeNullableSerializableElement(r15, r2, r13, r6)
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r15, r1, r13, r6)
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r15, r10, r13, r6)
                java.lang.String r9 = r0.decodeStringElement(r15, r9)
                boolean r8 = r0.decodeBooleanElement(r15, r8)
                r2 = 12
                boolean r2 = r0.decodeBooleanElement(r15, r2)
                r6 = 13
                boolean r6 = r0.decodeBooleanElement(r15, r6)
                r16 = r1
                r1 = 14
                boolean r1 = r0.decodeBooleanElement(r15, r1)
                r33 = r1
                r1 = 15
                boolean r1 = r0.decodeBooleanElement(r15, r1)
                r32 = r1
                r1 = 16
                boolean r1 = r0.decodeBooleanElement(r15, r1)
                r31 = r1
                r30 = r5
                r1 = 17
                r5 = 0
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r15, r1, r13, r5)
                r34 = r1
                kotlinx.serialization.internal.StringSerializer r1 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r37 = r2
                r2 = 18
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r15, r2, r1, r5)
                r29 = r2
                r2 = 19
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r15, r2, r13, r5)
                r28 = r2
                r2 = 20
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r15, r2, r13, r5)
                r27 = r2
                r2 = 21
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r15, r2, r1, r5)
                r2 = 22
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r15, r2, r13, r5)
                r26 = r1
                r1 = 23
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r15, r1, r13, r5)
                r25 = r1
                r1 = 24
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r15, r1, r13, r5)
                com.appsamurai.storyly.data.m$a r13 = com.appsamurai.storyly.data.m.f3857b
                r24 = r1
                r1 = 25
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r15, r1, r13, r5)
                r5 = 26
                boolean r5 = r0.decodeBooleanElement(r15, r5)
                r13 = 27
                boolean r13 = r0.decodeBooleanElement(r15, r13)
                r22 = r1
                r1 = 28
                java.lang.String r1 = r0.decodeStringElement(r15, r1)
                r21 = r1
                r1 = 29
                java.lang.String r1 = r0.decodeStringElement(r15, r1)
                r20 = r1
                r1 = 30
                java.lang.String r1 = r0.decodeStringElement(r15, r1)
                r19 = r1
                r1 = 31
                java.lang.String r1 = r0.decodeStringElement(r15, r1)
                r18 = r1
                r1 = 32
                java.lang.String r1 = r0.decodeStringElement(r15, r1)
                r89 = r1
                r1 = 33
                java.lang.String r1 = r0.decodeStringElement(r15, r1)
                r17 = r13
                r13 = 34
                int r13 = r0.decodeIntElement(r15, r13)
                r23 = -1
                r84 = r89
                r85 = r1
                r53 = r4
                r78 = r5
                r65 = r6
                r63 = r8
                r62 = r9
                r4 = r10
                r86 = r13
                r54 = r14
                r79 = r17
                r83 = r18
                r82 = r19
                r81 = r20
                r80 = r21
                r1 = r22
                r50 = r23
                r48 = r24
                r10 = r26
                r9 = r27
                r52 = r30
                r68 = r31
                r67 = r32
                r66 = r33
                r14 = r34
                r64 = r37
                r51 = 7
                goto L_0x0599
            L_0x0148:
                r2 = r13
                r13 = 34
                r87 = r6
                r6 = r5
                r5 = r87
                r69 = r4
                r1 = r5
                r2 = r1
                r8 = r2
                r9 = r8
                r10 = r9
                r11 = r10
                r12 = r11
                r39 = r12
                r40 = r39
                r41 = r40
                r43 = r41
                r44 = r43
                r45 = r44
                r46 = r45
                r52 = r46
                r53 = r52
                r54 = r53
                r55 = r54
                r56 = r55
                r57 = r56
                r58 = r57
                r59 = r58
                r60 = r59
                r62 = r60
                r63 = r62
                r7 = r6
                r35 = r7
                r37 = r35
                r38 = r37
                r61 = r38
                r64 = r61
                r65 = r64
                r66 = r65
                r67 = r66
                r68 = r67
                r5 = r68
                r6 = r63
            L_0x0194:
                if (r69 == 0) goto L_0x0548
                int r4 = r0.decodeElementIndex(r15)
                switch(r4) {
                    case -1: goto L_0x050e;
                    case 0: goto L_0x04e8;
                    case 1: goto L_0x04c6;
                    case 2: goto L_0x04a3;
                    case 3: goto L_0x047d;
                    case 4: goto L_0x0455;
                    case 5: goto L_0x042d;
                    case 6: goto L_0x040b;
                    case 7: goto L_0x03e9;
                    case 8: goto L_0x03cb;
                    case 9: goto L_0x03af;
                    case 10: goto L_0x039c;
                    case 11: goto L_0x0389;
                    case 12: goto L_0x0370;
                    case 13: goto L_0x0348;
                    case 14: goto L_0x031a;
                    case 15: goto L_0x02eb;
                    case 16: goto L_0x02d6;
                    case 17: goto L_0x02c0;
                    case 18: goto L_0x0299;
                    case 19: goto L_0x028c;
                    case 20: goto L_0x027f;
                    case 21: goto L_0x0272;
                    case 22: goto L_0x0265;
                    case 23: goto L_0x0258;
                    case 24: goto L_0x024b;
                    case 25: goto L_0x023a;
                    case 26: goto L_0x0231;
                    case 27: goto L_0x0228;
                    case 28: goto L_0x021f;
                    case 29: goto L_0x0216;
                    case 30: goto L_0x020d;
                    case 31: goto L_0x01e7;
                    case 32: goto L_0x01d5;
                    case 33: goto L_0x01c5;
                    case 34: goto L_0x01a3;
                    default: goto L_0x019d;
                }
            L_0x019d:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r4)
                throw r0
            L_0x01a3:
                int r66 = r0.decodeIntElement(r15, r13)
                r68 = r68 | 4
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                r48 = r2
                r3 = r14
            L_0x01ae:
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r29 = r59
                r2 = 0
                r31 = 15
                r32 = 14
                goto L_0x052f
            L_0x01c5:
                r4 = 33
                java.lang.String r17 = r0.decodeStringElement(r15, r4)
                r68 = r68 | 2
                kotlin.Unit r63 = kotlin.Unit.INSTANCE
                r48 = r2
                r3 = r14
                r63 = r17
                goto L_0x01ae
            L_0x01d5:
                r4 = 32
                java.lang.String r17 = r0.decodeStringElement(r15, r4)
                r62 = 1
                r68 = r68 | 1
                kotlin.Unit r62 = kotlin.Unit.INSTANCE
                r48 = r2
                r3 = r14
                r62 = r17
                goto L_0x01ae
            L_0x01e7:
                r4 = 31
                java.lang.String r46 = r0.decodeStringElement(r15, r4)
                r17 = -2147483648(0xffffffff80000000, float:-0.0)
            L_0x01ef:
                r49 = r1
                r48 = r2
                r3 = r14
                r1 = r17
            L_0x01f6:
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r29 = r59
                r2 = 0
            L_0x0207:
                r31 = 15
                r32 = 14
                goto L_0x0509
            L_0x020d:
                r4 = 30
                java.lang.String r45 = r0.decodeStringElement(r15, r4)
                r17 = 1073741824(0x40000000, float:2.0)
                goto L_0x01ef
            L_0x0216:
                r4 = 29
                java.lang.String r44 = r0.decodeStringElement(r15, r4)
                r17 = 536870912(0x20000000, float:1.0842022E-19)
                goto L_0x01ef
            L_0x021f:
                r4 = 28
                java.lang.String r43 = r0.decodeStringElement(r15, r4)
                r17 = 268435456(0x10000000, float:2.5243549E-29)
                goto L_0x01ef
            L_0x0228:
                r4 = 27
                boolean r65 = r0.decodeBooleanElement(r15, r4)
                r17 = 134217728(0x8000000, float:3.85186E-34)
                goto L_0x01ef
            L_0x0231:
                r4 = 26
                boolean r38 = r0.decodeBooleanElement(r15, r4)
                r17 = 67108864(0x4000000, float:1.5046328E-36)
                goto L_0x01ef
            L_0x023a:
                com.appsamurai.storyly.data.m$a r4 = com.appsamurai.storyly.data.m.f3857b
                r13 = 25
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r15, r13, r4, r12)
                r4 = 33554432(0x2000000, float:9.403955E-38)
            L_0x0244:
                r49 = r1
                r48 = r2
                r1 = r4
                r3 = r14
                goto L_0x01f6
            L_0x024b:
                r13 = 25
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r13 = 24
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r15, r13, r4, r2)
                r4 = 16777216(0x1000000, float:2.3509887E-38)
                goto L_0x0244
            L_0x0258:
                r13 = 24
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r13 = 23
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r15, r13, r4, r11)
                r4 = 8388608(0x800000, float:1.17549435E-38)
                goto L_0x0244
            L_0x0265:
                r13 = 23
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r13 = 22
                java.lang.Object r1 = r0.decodeNullableSerializableElement(r15, r13, r4, r1)
                r4 = 4194304(0x400000, float:5.877472E-39)
                goto L_0x0244
            L_0x0272:
                r13 = 22
                kotlinx.serialization.internal.StringSerializer r4 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r13 = 21
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r15, r13, r4, r10)
                r4 = 2097152(0x200000, float:2.938736E-39)
                goto L_0x0244
            L_0x027f:
                r13 = 21
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r13 = 20
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r15, r13, r4, r9)
                r4 = 1048576(0x100000, float:1.469368E-39)
                goto L_0x0244
            L_0x028c:
                r13 = 20
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r13 = 19
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r15, r13, r4, r8)
                r4 = 524288(0x80000, float:7.34684E-40)
                goto L_0x0244
            L_0x0299:
                r13 = 19
                kotlinx.serialization.internal.StringSerializer r4 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r13 = r52
                r14 = 18
                java.lang.Object r52 = r0.decodeNullableSerializableElement(r15, r14, r4, r13)
                r4 = 262144(0x40000, float:3.67342E-40)
                r49 = r1
                r48 = r2
                r1 = r4
                r13 = r52
            L_0x02ae:
                r14 = r53
            L_0x02b0:
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r29 = r59
                r2 = 0
                r3 = 2
                goto L_0x0207
            L_0x02c0:
                r13 = r52
                r14 = 18
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                r14 = r53
                r3 = 17
                java.lang.Object r53 = r0.decodeNullableSerializableElement(r15, r3, r4, r14)
                r4 = 131072(0x20000, float:1.83671E-40)
                r49 = r1
                r48 = r2
                r1 = r4
                goto L_0x02ae
            L_0x02d6:
                r13 = r52
                r14 = r53
                r3 = 17
                r4 = 16
                boolean r35 = r0.decodeBooleanElement(r15, r4)
                r31 = 65536(0x10000, float:9.18355E-41)
                r49 = r1
                r48 = r2
                r1 = r31
                goto L_0x02b0
            L_0x02eb:
                r13 = r52
                r14 = r53
                r3 = 17
                r4 = 16
                r7 = 15
                boolean r31 = r0.decodeBooleanElement(r15, r7)
                r32 = 32768(0x8000, float:4.5918E-41)
                r49 = r1
                r48 = r2
                r1 = r32
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r29 = r59
                r2 = 0
                r3 = 2
                r32 = 14
                r87 = r31
                r31 = r7
                r7 = r87
                goto L_0x0509
            L_0x031a:
                r13 = r52
                r14 = r53
                r3 = 17
                r4 = 16
                r5 = 14
                r31 = 15
                boolean r32 = r0.decodeBooleanElement(r15, r5)
                r33 = 16384(0x4000, float:2.2959E-41)
                r49 = r1
                r48 = r2
                r1 = r33
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r29 = r59
                r2 = 0
                r3 = 2
                r87 = r32
                r32 = r5
                r5 = r87
                goto L_0x0509
            L_0x0348:
                r13 = r52
                r14 = r53
                r3 = 13
                r4 = 16
                r31 = 15
                r32 = 14
                boolean r61 = r0.decodeBooleanElement(r15, r3)
                r34 = 8192(0x2000, float:1.14794E-41)
                r49 = r1
                r48 = r2
                r1 = r34
            L_0x0360:
                r4 = r54
            L_0x0362:
                r36 = r55
            L_0x0364:
                r47 = r56
            L_0x0366:
                r42 = r57
                r30 = r58
                r29 = r59
            L_0x036c:
                r2 = 0
                r3 = 2
                goto L_0x0509
            L_0x0370:
                r13 = r52
                r14 = r53
                r3 = 12
                r4 = 16
                r31 = 15
                r32 = 14
                boolean r37 = r0.decodeBooleanElement(r15, r3)
                r16 = 4096(0x1000, float:5.74E-42)
            L_0x0382:
                r49 = r1
                r48 = r2
                r1 = r16
                goto L_0x0360
            L_0x0389:
                r13 = r52
                r14 = r53
                r3 = 11
                r4 = 16
                r31 = 15
                r32 = 14
                boolean r64 = r0.decodeBooleanElement(r15, r3)
                r16 = 2048(0x800, float:2.87E-42)
                goto L_0x0382
            L_0x039c:
                r13 = r52
                r14 = r53
                r3 = 10
                r4 = 16
                r31 = 15
                r32 = 14
                java.lang.String r60 = r0.decodeStringElement(r15, r3)
                r16 = 1024(0x400, float:1.435E-42)
                goto L_0x0382
            L_0x03af:
                r13 = r52
                r14 = r53
                r4 = 16
                r31 = 15
                r32 = 14
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r49 = r1
                r4 = r54
                r1 = 9
                java.lang.Object r54 = r0.decodeNullableSerializableElement(r15, r1, r3, r4)
                r3 = 512(0x200, float:7.175E-43)
                r48 = r2
                r1 = r3
                goto L_0x0360
            L_0x03cb:
                r49 = r1
                r13 = r52
                r14 = r53
                r4 = r54
                r1 = 9
                r31 = 15
                r32 = 14
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r48 = r2
                r1 = r55
                r2 = 8
                java.lang.Object r55 = r0.decodeNullableSerializableElement(r15, r2, r3, r1)
                r1 = 256(0x100, float:3.59E-43)
                goto L_0x0362
            L_0x03e9:
                r49 = r1
                r48 = r2
                r13 = r52
                r14 = r53
                r4 = r54
                r1 = r55
                r2 = 8
                r31 = 15
                r32 = 14
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r36 = r1
                r2 = r56
                r1 = 7
                java.lang.Object r56 = r0.decodeNullableSerializableElement(r15, r1, r3, r2)
                r2 = 128(0x80, float:1.794E-43)
                r1 = r2
                goto L_0x0364
            L_0x040b:
                r49 = r1
                r48 = r2
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r2 = r56
                r1 = 7
                r31 = 15
                r32 = 14
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r47 = r2
                r1 = r57
                r2 = 6
                java.lang.Object r57 = r0.decodeNullableSerializableElement(r15, r2, r3, r1)
                r1 = 64
                goto L_0x0366
            L_0x042d:
                r49 = r1
                r48 = r2
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r1 = r57
                r2 = 6
                r31 = 15
                r32 = 14
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r42 = r1
                r2 = r58
                r1 = 5
                java.lang.Object r58 = r0.decodeNullableSerializableElement(r15, r1, r3, r2)
                r30 = r58
                r29 = r59
                r1 = 32
                goto L_0x036c
            L_0x0455:
                r49 = r1
                r48 = r2
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r2 = r58
                r1 = 5
                r31 = 15
                r32 = 14
                com.appsamurai.storyly.data.c$a r3 = com.appsamurai.storyly.data.c.f3622b
                r30 = r2
                r1 = r59
                r2 = 4
                java.lang.Object r59 = r0.decodeNullableSerializableElement(r15, r2, r3, r1)
                r29 = r59
                r1 = 16
                goto L_0x036c
            L_0x047d:
                r49 = r1
                r48 = r2
                r2 = r3
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r1 = r59
                r31 = 15
                r32 = 14
                com.appsamurai.storyly.data.r0$a r3 = com.appsamurai.storyly.data.r0.f4186a
                r2 = 3
                java.lang.Object r6 = r0.decodeSerializableElement(r15, r2, r3, r6)
                r29 = r1
                r1 = 8
                goto L_0x036c
            L_0x04a3:
                r49 = r1
                r48 = r2
                r3 = r14
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r1 = r59
                r2 = 3
                r31 = 15
                r32 = 14
                java.lang.String r41 = r0.decodeStringElement(r15, r3)
                r29 = r1
                r1 = 4
            L_0x04c4:
                r2 = 0
                goto L_0x0509
            L_0x04c6:
                r49 = r1
                r48 = r2
                r3 = r14
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r1 = r59
                r2 = 1
                r31 = 15
                r32 = 14
                java.lang.String r40 = r0.decodeStringElement(r15, r2)
                r29 = r1
                r1 = r3
                goto L_0x04c4
            L_0x04e8:
                r49 = r1
                r48 = r2
                r3 = r14
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r1 = r59
                r2 = 0
                r31 = 15
                r32 = 14
                java.lang.String r39 = r0.decodeStringElement(r15, r2)
                r29 = r1
                r1 = 1
            L_0x0509:
                r67 = r67 | r1
            L_0x050b:
                r1 = r49
                goto L_0x052d
            L_0x050e:
                r49 = r1
                r48 = r2
                r3 = r14
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r1 = r59
                r2 = 0
                r31 = 15
                r32 = 14
                r29 = r1
                r69 = r2
                goto L_0x050b
            L_0x052d:
                kotlin.Unit r49 = kotlin.Unit.INSTANCE
            L_0x052f:
                r54 = r4
                r52 = r13
                r53 = r14
                r59 = r29
                r58 = r30
                r55 = r36
                r57 = r42
                r56 = r47
                r2 = r48
                r4 = 1
                r13 = 34
                r14 = r3
                r3 = 4
                goto L_0x0194
            L_0x0548:
                r49 = r1
                r48 = r2
                r13 = r52
                r14 = r53
                r4 = r54
                r36 = r55
                r47 = r56
                r42 = r57
                r30 = r58
                r1 = r59
                r3 = r1
                r28 = r8
                r25 = r11
                r1 = r12
                r29 = r13
                r12 = r30
                r16 = r36
                r78 = r38
                r52 = r39
                r53 = r40
                r54 = r41
                r11 = r42
                r80 = r43
                r81 = r44
                r82 = r45
                r83 = r46
                r2 = r49
                r84 = r62
                r85 = r63
                r63 = r64
                r79 = r65
                r86 = r66
                r50 = r67
                r51 = r68
                r66 = r5
                r67 = r7
                r68 = r35
                r64 = r37
                r35 = r47
                r62 = r60
                r65 = r61
                r7 = r6
            L_0x0599:
                r0.endStructure(r15)
                com.appsamurai.storyly.data.i0 r0 = new com.appsamurai.storyly.data.i0
                r49 = r0
                r55 = r7
                com.appsamurai.storyly.data.r0 r55 = (com.appsamurai.storyly.data.r0) r55
                r56 = r3
                com.appsamurai.storyly.data.c r56 = (com.appsamurai.storyly.data.c) r56
                r57 = r12
                com.appsamurai.storyly.data.c r57 = (com.appsamurai.storyly.data.c) r57
                r58 = r11
                com.appsamurai.storyly.data.c r58 = (com.appsamurai.storyly.data.c) r58
                r59 = r35
                com.appsamurai.storyly.data.c r59 = (com.appsamurai.storyly.data.c) r59
                r60 = r16
                com.appsamurai.storyly.data.c r60 = (com.appsamurai.storyly.data.c) r60
                r61 = r4
                com.appsamurai.storyly.data.c r61 = (com.appsamurai.storyly.data.c) r61
                r69 = r14
                com.appsamurai.storyly.data.c r69 = (com.appsamurai.storyly.data.c) r69
                r70 = r29
                java.lang.String r70 = (java.lang.String) r70
                r71 = r28
                com.appsamurai.storyly.data.c r71 = (com.appsamurai.storyly.data.c) r71
                r72 = r9
                com.appsamurai.storyly.data.c r72 = (com.appsamurai.storyly.data.c) r72
                r73 = r10
                java.lang.String r73 = (java.lang.String) r73
                r74 = r2
                com.appsamurai.storyly.data.c r74 = (com.appsamurai.storyly.data.c) r74
                r75 = r25
                com.appsamurai.storyly.data.c r75 = (com.appsamurai.storyly.data.c) r75
                r76 = r48
                com.appsamurai.storyly.data.c r76 = (com.appsamurai.storyly.data.c) r76
                r77 = r1
                com.appsamurai.storyly.data.m r77 = (com.appsamurai.storyly.data.m) r77
                r49.<init>(r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.i0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3787b;
        }

        public void serialize(Encoder encoder, Object obj) {
            i0 i0Var = (i0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(i0Var, "value");
            SerialDescriptor serialDescriptor = f3787b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(i0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeStringElement(serialDescriptor, 0, i0Var.f3760a);
            beginStructure.encodeStringElement(serialDescriptor, 1, i0Var.f3761b);
            beginStructure.encodeStringElement(serialDescriptor, 2, i0Var.f3762c);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || i0Var.f3763d != r0.UpMiddle) {
                beginStructure.encodeSerializableElement(serialDescriptor, 3, r0.f4186a, i0Var.f3763d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || i0Var.f3764e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, c.f3622b, i0Var.f3764e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || i0Var.f3765f != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 5, c.f3622b, i0Var.f3765f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || i0Var.f3766g != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 6, c.f3622b, i0Var.f3766g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || i0Var.f3767h != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, c.f3622b, i0Var.f3767h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || i0Var.f3768i != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, c.f3622b, i0Var.f3768i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || i0Var.f3769j != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, c.f3622b, i0Var.f3769j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || !Intrinsics.areEqual((Object) i0Var.f3770k, (Object) "")) {
                beginStructure.encodeStringElement(serialDescriptor, 10, i0Var.f3770k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || !i0Var.f3771l) {
                beginStructure.encodeBooleanElement(serialDescriptor, 11, i0Var.f3771l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || i0Var.f3772m) {
                beginStructure.encodeBooleanElement(serialDescriptor, 12, i0Var.f3772m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || i0Var.f3773n) {
                beginStructure.encodeBooleanElement(serialDescriptor, 13, i0Var.f3773n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || i0Var.f3774o) {
                beginStructure.encodeBooleanElement(serialDescriptor, 14, i0Var.f3774o);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 15) || i0Var.f3775p) {
                beginStructure.encodeBooleanElement(serialDescriptor, 15, i0Var.f3775p);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 16) || i0Var.f3776q) {
                beginStructure.encodeBooleanElement(serialDescriptor, 16, i0Var.f3776q);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 17) || i0Var.f3777r != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 17, c.f3622b, i0Var.f3777r);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 18) || i0Var.f3778s != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 18, StringSerializer.INSTANCE, i0Var.f3778s);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 19) || i0Var.f3779t != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 19, c.f3622b, i0Var.f3779t);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 20) || i0Var.f3780u != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 20, c.f3622b, i0Var.f3780u);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 21) || i0Var.f3781v != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 21, StringSerializer.INSTANCE, i0Var.f3781v);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 22) || i0Var.f3782w != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 22, c.f3622b, i0Var.f3782w);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 23) || i0Var.f3783x != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 23, c.f3622b, i0Var.f3783x);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 24) || i0Var.f3784y != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 24, c.f3622b, i0Var.f3784y);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 25) || i0Var.f3785z != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 25, m.f3857b, i0Var.f3785z);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 26) || !i0Var.f3753A) {
                beginStructure.encodeBooleanElement(serialDescriptor, 26, i0Var.f3753A);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 27) || !i0Var.f3754B) {
                beginStructure.encodeBooleanElement(serialDescriptor, 27, i0Var.f3754B);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 28) || !Intrinsics.areEqual((Object) i0Var.f3755C, (Object) "Add to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 28, i0Var.f3755C);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 29) || !Intrinsics.areEqual((Object) i0Var.f3756D, (Object) "Go to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 29, i0Var.f3756D);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 30) || !Intrinsics.areEqual((Object) i0Var.f3757E, (Object) "Continue with Stories")) {
                beginStructure.encodeStringElement(serialDescriptor, 30, i0Var.f3757E);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 31) || !Intrinsics.areEqual((Object) i0Var.F, (Object) "Added to your Cart successfully")) {
                beginStructure.encodeStringElement(serialDescriptor, 31, i0Var.F);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 32) || !Intrinsics.areEqual((Object) i0Var.G, (Object) "Go to Checkout")) {
                beginStructure.encodeStringElement(serialDescriptor, 32, i0Var.G);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 33) || !Intrinsics.areEqual((Object) i0Var.f3758H, (Object) "Total")) {
                beginStructure.encodeStringElement(serialDescriptor, 33, i0Var.f3758H);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 34) || i0Var.f3759I != 4) {
                beginStructure.encodeIntElement(serialDescriptor, 34, i0Var.f3759I);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ i0(int i3, int i4, @Required @SerialName("title") String str, @Required @SerialName("theme") String str2, @SerialName("outlink") String str3, @SerialName("tooltip_placement") r0 r0Var, @SerialName("primary_color") c cVar, @SerialName("secondary_color") c cVar2, @SerialName("bg_color") c cVar3, @SerialName("border_color") c cVar4, @SerialName("t_color") c cVar5, @SerialName("p_color") c cVar6, @SerialName("price") String str4, @SerialName("is_bold") boolean z2, @SerialName("is_italic") boolean z3, @SerialName("price_is_bold") boolean z4, @SerialName("price_is_italic") boolean z5, @SerialName("old_price_is_bold") boolean z6, @SerialName("old_price_is_italic") boolean z7, @SerialName("price_bg_color") c cVar7, @SerialName("old_price") String str5, @SerialName("old_price_color") c cVar8, @SerialName("chevron_color") c cVar9, @SerialName("icon_type") String str6, @SerialName("icon_color") c cVar10, @SerialName("icon_bg_color") c cVar11, @SerialName("icon_border_color") c cVar12, @SerialName("products") m mVar, @SerialName("is_s_price_visible") boolean z8, @SerialName("is_price_visible") boolean z9, @SerialName("p_b_text") String str7, @SerialName("s_b_cart_text") String str8, @SerialName("s_b_back_text") String str9, @SerialName("s_message") String str10, @SerialName("checkout_b_text") String str11, @SerialName("t_text") String str12, @SerialName("max_v") int i5) {
        r0 r0Var2;
        int i6 = i3;
        if (7 != (i6 & 7)) {
            PluginExceptionsKt.throwArrayMissingFieldException(new int[]{i3, i4}, new int[]{7, 0}, a.f3786a.getDescriptor());
        }
        this.f3760a = str;
        this.f3761b = str2;
        this.f3762c = str3;
        if ((i6 & 8) == 0) {
            r0Var2 = r0.UpMiddle;
        } else {
            r0Var2 = r0Var;
        }
        this.f3763d = r0Var2;
        if ((i6 & 16) == 0) {
            this.f3764e = null;
        } else {
            this.f3764e = cVar;
        }
        if ((i6 & 32) == 0) {
            this.f3765f = null;
        } else {
            this.f3765f = cVar2;
        }
        if ((i6 & 64) == 0) {
            this.f3766g = null;
        } else {
            this.f3766g = cVar3;
        }
        if ((i6 & 128) == 0) {
            this.f3767h = null;
        } else {
            this.f3767h = cVar4;
        }
        if ((i6 & 256) == 0) {
            this.f3768i = null;
        } else {
            this.f3768i = cVar5;
        }
        if ((i6 & 512) == 0) {
            this.f3769j = null;
        } else {
            this.f3769j = cVar6;
        }
        this.f3770k = (i6 & 1024) == 0 ? "" : str4;
        if ((i6 & 2048) == 0) {
            this.f3771l = true;
        } else {
            this.f3771l = z2;
        }
        if ((i6 & 4096) == 0) {
            this.f3772m = false;
        } else {
            this.f3772m = z3;
        }
        if ((i6 & 8192) == 0) {
            this.f3773n = false;
        } else {
            this.f3773n = z4;
        }
        if ((i6 & 16384) == 0) {
            this.f3774o = false;
        } else {
            this.f3774o = z5;
        }
        if ((32768 & i6) == 0) {
            this.f3775p = false;
        } else {
            this.f3775p = z6;
        }
        if ((65536 & i6) == 0) {
            this.f3776q = false;
        } else {
            this.f3776q = z7;
        }
        if ((131072 & i6) == 0) {
            this.f3777r = null;
        } else {
            this.f3777r = cVar7;
        }
        if ((262144 & i6) == 0) {
            this.f3778s = null;
        } else {
            this.f3778s = str5;
        }
        if ((524288 & i6) == 0) {
            this.f3779t = null;
        } else {
            this.f3779t = cVar8;
        }
        if ((1048576 & i6) == 0) {
            this.f3780u = null;
        } else {
            this.f3780u = cVar9;
        }
        if ((2097152 & i6) == 0) {
            this.f3781v = null;
        } else {
            this.f3781v = str6;
        }
        if ((4194304 & i6) == 0) {
            this.f3782w = null;
        } else {
            this.f3782w = cVar10;
        }
        if ((8388608 & i6) == 0) {
            this.f3783x = null;
        } else {
            this.f3783x = cVar11;
        }
        if ((16777216 & i6) == 0) {
            this.f3784y = null;
        } else {
            this.f3784y = cVar12;
        }
        if ((33554432 & i6) == 0) {
            this.f3785z = null;
        } else {
            this.f3785z = mVar;
        }
        if ((67108864 & i6) == 0) {
            this.f3753A = true;
        } else {
            this.f3753A = z8;
        }
        if ((134217728 & i6) == 0) {
            this.f3754B = true;
        } else {
            this.f3754B = z9;
        }
        this.f3755C = (268435456 & i6) == 0 ? "Add to Cart" : str7;
        this.f3756D = (536870912 & i6) == 0 ? "Go to Cart" : str8;
        this.f3757E = (1073741824 & i6) == 0 ? "Continue with Stories" : str9;
        this.F = (i6 & Integer.MIN_VALUE) == 0 ? "Added to your Cart successfully" : str10;
        this.G = (i4 & 1) == 0 ? "Go to Checkout" : str11;
        this.f3758H = (i4 & 2) == 0 ? "Total" : str12;
        this.f3759I = (i4 & 4) != 0 ? i5 : 4;
    }

    public static i0 a(i0 i0Var, String str, String str2, String str3, r0 r0Var, c cVar, c cVar2, c cVar3, c cVar4, c cVar5, c cVar6, String str4, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, c cVar7, String str5, c cVar8, c cVar9, String str6, c cVar10, c cVar11, c cVar12, m mVar, boolean z8, boolean z9, String str7, String str8, String str9, String str10, String str11, String str12, int i3, int i4, int i5) {
        c cVar13;
        m mVar2;
        i0 i0Var2 = i0Var;
        int i6 = i4;
        String str13 = (i6 & 1) != 0 ? i0Var2.f3760a : null;
        String str14 = (i6 & 2) != 0 ? i0Var2.f3761b : null;
        String str15 = (i6 & 4) != 0 ? i0Var2.f3762c : null;
        r0 r0Var2 = (i6 & 8) != 0 ? i0Var2.f3763d : null;
        c cVar14 = (i6 & 16) != 0 ? i0Var2.f3764e : null;
        c cVar15 = (i6 & 32) != 0 ? i0Var2.f3765f : null;
        c cVar16 = (i6 & 64) != 0 ? i0Var2.f3766g : null;
        c cVar17 = (i6 & 128) != 0 ? i0Var2.f3767h : null;
        c cVar18 = (i6 & 256) != 0 ? i0Var2.f3768i : null;
        c cVar19 = (i6 & 512) != 0 ? i0Var2.f3769j : null;
        String str16 = (i6 & 1024) != 0 ? i0Var2.f3770k : null;
        boolean z10 = (i6 & 2048) != 0 ? i0Var2.f3771l : z2;
        boolean z11 = (i6 & 4096) != 0 ? i0Var2.f3772m : z3;
        boolean z12 = (i6 & 8192) != 0 ? i0Var2.f3773n : z4;
        boolean z13 = (i6 & 16384) != 0 ? i0Var2.f3774o : z5;
        boolean z14 = (i6 & 32768) != 0 ? i0Var2.f3775p : z6;
        boolean z15 = (i6 & 65536) != 0 ? i0Var2.f3776q : z7;
        c cVar20 = (i6 & 131072) != 0 ? i0Var2.f3777r : null;
        String str17 = (i6 & 262144) != 0 ? i0Var2.f3778s : null;
        c cVar21 = (i6 & 524288) != 0 ? i0Var2.f3779t : null;
        c cVar22 = (i6 & 1048576) != 0 ? i0Var2.f3780u : null;
        String str18 = (i6 & 2097152) != 0 ? i0Var2.f3781v : null;
        c cVar23 = (i6 & 4194304) != 0 ? i0Var2.f3782w : null;
        c cVar24 = (i6 & 8388608) != 0 ? i0Var2.f3783x : null;
        c cVar25 = (i6 & 16777216) != 0 ? i0Var2.f3784y : null;
        if ((i6 & 33554432) != 0) {
            cVar13 = cVar25;
            mVar2 = i0Var2.f3785z;
        } else {
            cVar13 = cVar25;
            mVar2 = null;
        }
        m mVar3 = mVar2;
        boolean z16 = (i6 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? i0Var2.f3753A : z8;
        boolean z17 = (i6 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? i0Var2.f3754B : z9;
        String str19 = (i6 & 268435456) != 0 ? i0Var2.f3755C : null;
        boolean z18 = z11;
        String str20 = (i6 & 536870912) != 0 ? i0Var2.f3756D : null;
        boolean z19 = z10;
        String str21 = (i6 & 1073741824) != 0 ? i0Var2.f3757E : null;
        String str22 = (i6 & Integer.MIN_VALUE) != 0 ? i0Var2.F : null;
        c cVar26 = cVar19;
        String str23 = (i5 & 1) != 0 ? i0Var2.G : null;
        c cVar27 = cVar18;
        String str24 = (i5 & 2) != 0 ? i0Var2.f3758H : null;
        c cVar28 = cVar17;
        int i7 = (i5 & 4) != 0 ? i0Var2.f3759I : i3;
        i0Var.getClass();
        Intrinsics.checkNotNullParameter(str13, "title");
        Intrinsics.checkNotNullParameter(str14, "theme");
        Intrinsics.checkNotNullParameter(str15, "outlink");
        Intrinsics.checkNotNullParameter(r0Var2, "tooltipPlacement");
        Intrinsics.checkNotNullParameter(str16, FirebaseAnalytics.Param.PRICE);
        Intrinsics.checkNotNullParameter(str19, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str20, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str21, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str22, "successMessage");
        Intrinsics.checkNotNullParameter(str23, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str24, "totalText");
        return new i0(str13, str14, str15, r0Var2, cVar14, cVar15, cVar16, cVar28, cVar27, cVar26, str16, z19, z18, z12, z13, z14, z15, cVar20, str17, cVar21, cVar22, str18, cVar23, cVar24, cVar13, mVar3, z16, z17, str19, str20, str21, str22, str23, str24, i7);
    }

    @Nullable
    public m c() {
        return this.f3785z;
    }

    @NotNull
    public String d() {
        return this.f3755C;
    }

    @NotNull
    public String e() {
        return this.f3757E;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i0)) {
            return false;
        }
        i0 i0Var = (i0) obj;
        return Intrinsics.areEqual((Object) this.f3760a, (Object) i0Var.f3760a) && Intrinsics.areEqual((Object) this.f3761b, (Object) i0Var.f3761b) && Intrinsics.areEqual((Object) this.f3762c, (Object) i0Var.f3762c) && this.f3763d == i0Var.f3763d && Intrinsics.areEqual((Object) this.f3764e, (Object) i0Var.f3764e) && Intrinsics.areEqual((Object) this.f3765f, (Object) i0Var.f3765f) && Intrinsics.areEqual((Object) this.f3766g, (Object) i0Var.f3766g) && Intrinsics.areEqual((Object) this.f3767h, (Object) i0Var.f3767h) && Intrinsics.areEqual((Object) this.f3768i, (Object) i0Var.f3768i) && Intrinsics.areEqual((Object) this.f3769j, (Object) i0Var.f3769j) && Intrinsics.areEqual((Object) this.f3770k, (Object) i0Var.f3770k) && this.f3771l == i0Var.f3771l && this.f3772m == i0Var.f3772m && this.f3773n == i0Var.f3773n && this.f3774o == i0Var.f3774o && this.f3775p == i0Var.f3775p && this.f3776q == i0Var.f3776q && Intrinsics.areEqual((Object) this.f3777r, (Object) i0Var.f3777r) && Intrinsics.areEqual((Object) this.f3778s, (Object) i0Var.f3778s) && Intrinsics.areEqual((Object) this.f3779t, (Object) i0Var.f3779t) && Intrinsics.areEqual((Object) this.f3780u, (Object) i0Var.f3780u) && Intrinsics.areEqual((Object) this.f3781v, (Object) i0Var.f3781v) && Intrinsics.areEqual((Object) this.f3782w, (Object) i0Var.f3782w) && Intrinsics.areEqual((Object) this.f3783x, (Object) i0Var.f3783x) && Intrinsics.areEqual((Object) this.f3784y, (Object) i0Var.f3784y) && Intrinsics.areEqual((Object) this.f3785z, (Object) i0Var.f3785z) && this.f3753A == i0Var.f3753A && this.f3754B == i0Var.f3754B && Intrinsics.areEqual((Object) this.f3755C, (Object) i0Var.f3755C) && Intrinsics.areEqual((Object) this.f3756D, (Object) i0Var.f3756D) && Intrinsics.areEqual((Object) this.f3757E, (Object) i0Var.f3757E) && Intrinsics.areEqual((Object) this.F, (Object) i0Var.F) && Intrinsics.areEqual((Object) this.G, (Object) i0Var.G) && Intrinsics.areEqual((Object) this.f3758H, (Object) i0Var.f3758H) && this.f3759I == i0Var.f3759I;
    }

    @NotNull
    public String f() {
        return this.f3756D;
    }

    @NotNull
    public String g() {
        return this.F;
    }

    @NotNull
    public String h() {
        return this.f3758H;
    }

    public int hashCode() {
        int hashCode = (this.f3763d.hashCode() + androidx.compose.animation.core.a.i(this.f3762c, androidx.compose.animation.core.a.i(this.f3761b, this.f3760a.hashCode() * 31, 31), 31)) * 31;
        c cVar = this.f3764e;
        int i3 = 0;
        int hashCode2 = (hashCode + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f3765f;
        int hashCode3 = (hashCode2 + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f3766g;
        int hashCode4 = (hashCode3 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f3767h;
        int hashCode5 = (hashCode4 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f3768i;
        int hashCode6 = (hashCode5 + (cVar5 == null ? 0 : Integer.hashCode(cVar5.f3624a))) * 31;
        c cVar6 = this.f3769j;
        int i4 = androidx.compose.animation.core.a.i(this.f3770k, (hashCode6 + (cVar6 == null ? 0 : Integer.hashCode(cVar6.f3624a))) * 31, 31);
        boolean z2 = this.f3771l;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i5 = (i4 + (z2 ? 1 : 0)) * 31;
        boolean z4 = this.f3772m;
        if (z4) {
            z4 = true;
        }
        int i6 = (i5 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f3773n;
        if (z5) {
            z5 = true;
        }
        int i7 = (i6 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.f3774o;
        if (z6) {
            z6 = true;
        }
        int i8 = (i7 + (z6 ? 1 : 0)) * 31;
        boolean z7 = this.f3775p;
        if (z7) {
            z7 = true;
        }
        int i9 = (i8 + (z7 ? 1 : 0)) * 31;
        boolean z8 = this.f3776q;
        if (z8) {
            z8 = true;
        }
        int i10 = (i9 + (z8 ? 1 : 0)) * 31;
        c cVar7 = this.f3777r;
        int hashCode7 = (i10 + (cVar7 == null ? 0 : Integer.hashCode(cVar7.f3624a))) * 31;
        String str = this.f3778s;
        int hashCode8 = (hashCode7 + (str == null ? 0 : str.hashCode())) * 31;
        c cVar8 = this.f3779t;
        int hashCode9 = (hashCode8 + (cVar8 == null ? 0 : Integer.hashCode(cVar8.f3624a))) * 31;
        c cVar9 = this.f3780u;
        int hashCode10 = (hashCode9 + (cVar9 == null ? 0 : Integer.hashCode(cVar9.f3624a))) * 31;
        String str2 = this.f3781v;
        int hashCode11 = (hashCode10 + (str2 == null ? 0 : str2.hashCode())) * 31;
        c cVar10 = this.f3782w;
        int hashCode12 = (hashCode11 + (cVar10 == null ? 0 : Integer.hashCode(cVar10.f3624a))) * 31;
        c cVar11 = this.f3783x;
        int hashCode13 = (hashCode12 + (cVar11 == null ? 0 : Integer.hashCode(cVar11.f3624a))) * 31;
        c cVar12 = this.f3784y;
        int hashCode14 = (hashCode13 + (cVar12 == null ? 0 : Integer.hashCode(cVar12.f3624a))) * 31;
        m mVar = this.f3785z;
        if (mVar != null) {
            i3 = mVar.hashCode();
        }
        int i11 = (hashCode14 + i3) * 31;
        boolean z9 = this.f3753A;
        if (z9) {
            z9 = true;
        }
        int i12 = (i11 + (z9 ? 1 : 0)) * 31;
        boolean z10 = this.f3754B;
        if (!z10) {
            z3 = z10;
        }
        return Integer.hashCode(this.f3759I) + androidx.compose.animation.core.a.i(this.f3758H, androidx.compose.animation.core.a.i(this.G, androidx.compose.animation.core.a.i(this.F, androidx.compose.animation.core.a.i(this.f3757E, androidx.compose.animation.core.a.i(this.f3756D, androidx.compose.animation.core.a.i(this.f3755C, (i12 + (z3 ? 1 : 0)) * 31, 31), 31), 31), 31), 31), 31);
    }

    public boolean i() {
        return this.f3754B;
    }

    public boolean j() {
        return this.f3753A;
    }

    public final c k() {
        c cVar = this.f3764e;
        return cVar == null ? Intrinsics.areEqual((Object) this.f3761b, (Object) "Dark") ? new c(-1) : com.appsamurai.storyly.config.styling.a.COLOR_212121.b() : cVar;
    }

    public final c l() {
        c cVar = this.f3765f;
        return cVar == null ? Intrinsics.areEqual((Object) this.f3761b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_212121.b() : new c(-1) : cVar;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyProductTagLayer(title=");
        sb.append(this.f3760a);
        sb.append(", theme=");
        sb.append(this.f3761b);
        sb.append(", outlink=");
        sb.append(this.f3762c);
        sb.append(", tooltipPlacement=");
        sb.append(this.f3763d);
        sb.append(", primaryColor=");
        sb.append(this.f3764e);
        sb.append(", secondaryColor=");
        sb.append(this.f3765f);
        sb.append(", backgroundColor=");
        sb.append(this.f3766g);
        sb.append(", borderColor=");
        sb.append(this.f3767h);
        sb.append(", titleColor=");
        sb.append(this.f3768i);
        sb.append(", priceColor=");
        sb.append(this.f3769j);
        sb.append(", price=");
        sb.append(this.f3770k);
        sb.append(", isBold=");
        sb.append(this.f3771l);
        sb.append(", isItalic=");
        sb.append(this.f3772m);
        sb.append(", priceIsBold=");
        sb.append(this.f3773n);
        sb.append(", priceIsItalic=");
        sb.append(this.f3774o);
        sb.append(", oldPriceIsBold=");
        sb.append(this.f3775p);
        sb.append(", oldPriceIsItalic=");
        sb.append(this.f3776q);
        sb.append(", priceBackgroundColor=");
        sb.append(this.f3777r);
        sb.append(", oldPrice=");
        sb.append(this.f3778s);
        sb.append(", oldPriceColor=");
        sb.append(this.f3779t);
        sb.append(", chevronColor=");
        sb.append(this.f3780u);
        sb.append(", iconType=");
        sb.append(this.f3781v);
        sb.append(", iconColor=");
        sb.append(this.f3782w);
        sb.append(", iconBackgroundColor=");
        sb.append(this.f3783x);
        sb.append(", iconBorderColor=");
        sb.append(this.f3784y);
        sb.append(", productData=");
        sb.append(this.f3785z);
        sb.append(", isProductSalesPriceVisible=");
        sb.append(this.f3753A);
        sb.append(", isProductPriceVisible=");
        sb.append(this.f3754B);
        sb.append(", purchaseButtonText=");
        sb.append(this.f3755C);
        sb.append(", successButtonCartText=");
        sb.append(this.f3756D);
        sb.append(", successButtonBackText=");
        sb.append(this.f3757E);
        sb.append(", successMessage=");
        sb.append(this.F);
        sb.append(", checkoutButtonText=");
        sb.append(this.G);
        sb.append(", totalText=");
        sb.append(this.f3758H);
        sb.append(", maxVariantCount=");
        return android.support.v4.media.session.a.p(sb, this.f3759I, ')');
    }

    public i0(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull r0 r0Var, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, @Nullable c cVar6, @NotNull String str4, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, @Nullable c cVar7, @Nullable String str5, @Nullable c cVar8, @Nullable c cVar9, @Nullable String str6, @Nullable c cVar10, @Nullable c cVar11, @Nullable c cVar12, @Nullable m mVar, boolean z8, boolean z9, @NotNull String str7, @NotNull String str8, @NotNull String str9, @NotNull String str10, @NotNull String str11, @NotNull String str12, int i3) {
        String str13 = str3;
        r0 r0Var2 = r0Var;
        String str14 = str4;
        String str15 = str7;
        String str16 = str8;
        String str17 = str9;
        String str18 = str10;
        String str19 = str11;
        String str20 = str12;
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(str2, "theme");
        Intrinsics.checkNotNullParameter(str13, "outlink");
        Intrinsics.checkNotNullParameter(r0Var2, "tooltipPlacement");
        Intrinsics.checkNotNullParameter(str14, FirebaseAnalytics.Param.PRICE);
        Intrinsics.checkNotNullParameter(str15, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str16, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str17, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str18, "successMessage");
        Intrinsics.checkNotNullParameter(str19, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str20, "totalText");
        this.f3760a = str;
        this.f3761b = str2;
        this.f3762c = str13;
        this.f3763d = r0Var2;
        this.f3764e = cVar;
        this.f3765f = cVar2;
        this.f3766g = cVar3;
        this.f3767h = cVar4;
        this.f3768i = cVar5;
        this.f3769j = cVar6;
        this.f3770k = str14;
        this.f3771l = z2;
        this.f3772m = z3;
        this.f3773n = z4;
        this.f3774o = z5;
        this.f3775p = z6;
        this.f3776q = z7;
        this.f3777r = cVar7;
        this.f3778s = str5;
        this.f3779t = cVar8;
        this.f3780u = cVar9;
        this.f3781v = str6;
        this.f3782w = cVar10;
        this.f3783x = cVar11;
        this.f3784y = cVar12;
        this.f3785z = mVar;
        this.f3753A = z8;
        this.f3754B = z9;
        this.f3755C = str15;
        this.f3756D = str16;
        this.f3757E = str17;
        this.F = str18;
        this.G = str19;
        this.f3758H = str20;
        this.f3759I = i3;
    }

    @NotNull
    public String a() {
        return this.G;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.ProductTag);
    }
}
