package com.appsamurai.storyly.data;

import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
import com.appsamurai.storyly.data.c;
import com.appsamurai.storyly.util.f;
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
public final class n0 extends g0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4059a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final c f4060b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final c f4061c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final c f4062d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public String f4063e;

    /* renamed from: f  reason: collision with root package name */
    public final boolean f4064f;

    /* renamed from: g  reason: collision with root package name */
    public final boolean f4065g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public m f4066h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f4067i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f4068j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public String f4069k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public String f4070l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public String f4071m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public String f4072n;
    @NotNull

    /* renamed from: o  reason: collision with root package name */
    public String f4073o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public String f4074p;

    /* renamed from: q  reason: collision with root package name */
    public int f4075q;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<n0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4076a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4077b;

        static {
            a aVar = new a();
            f4076a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylySwipeActionLayer", aVar, 17);
            pluginGeneratedSerialDescriptor.addElement("button_text", false);
            pluginGeneratedSerialDescriptor.addElement("text_color", true);
            pluginGeneratedSerialDescriptor.addElement("icon_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_color", true);
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
            f4077b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            c.a aVar = c.f3622b;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(m.f3857b);
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, aVar, aVar, nullable, nullable2, booleanSerializer, booleanSerializer, nullable3, booleanSerializer, booleanSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, stringSerializer, IntSerializer.INSTANCE};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x00f3, code lost:
            r5 = 16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0191, code lost:
            r5 = 16;
            r7 = 11;
            r8 = 10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x01ac, code lost:
            r5 = 16;
            r7 = 11;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r53) {
            /*
                r52 = this;
                r0 = r53
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4077b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r7 = 11
                r8 = 10
                r9 = 9
                r10 = 7
                r11 = 6
                r12 = 5
                r13 = 3
                r15 = 8
                r14 = 4
                r3 = 2
                r4 = 1
                r5 = 0
                r6 = 0
                if (r2 == 0) goto L_0x009f
                java.lang.String r2 = r0.decodeStringElement(r1, r5)
                com.appsamurai.storyly.data.c$a r5 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r4 = r0.decodeSerializableElement(r1, r4, r5, r6)
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r3, r5, r6)
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r13, r5, r6)
                kotlinx.serialization.internal.StringSerializer r13 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r14, r13, r6)
                boolean r12 = r0.decodeBooleanElement(r1, r12)
                boolean r11 = r0.decodeBooleanElement(r1, r11)
                com.appsamurai.storyly.data.m$a r14 = com.appsamurai.storyly.data.m.f3857b
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r10, r14, r6)
                boolean r10 = r0.decodeBooleanElement(r1, r15)
                boolean r9 = r0.decodeBooleanElement(r1, r9)
                java.lang.String r8 = r0.decodeStringElement(r1, r8)
                java.lang.String r7 = r0.decodeStringElement(r1, r7)
                r14 = 12
                java.lang.String r14 = r0.decodeStringElement(r1, r14)
                r15 = 13
                java.lang.String r15 = r0.decodeStringElement(r1, r15)
                r17 = r2
                r2 = 14
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r16 = r2
                r2 = 15
                java.lang.String r2 = r0.decodeStringElement(r1, r2)
                r53 = r5
                r5 = 16
                int r5 = r0.decodeIntElement(r1, r5)
                r18 = 131071(0x1ffff, float:1.8367E-40)
                r50 = r2
                r51 = r5
                r46 = r7
                r45 = r8
                r44 = r9
                r43 = r10
                r41 = r11
                r40 = r12
                r47 = r14
                r48 = r15
                r49 = r16
                r35 = r17
                r34 = r18
                r5 = r53
                goto L_0x01d4
            L_0x009f:
                r2 = r5
                r5 = 16
                r14 = r2
                r27 = r14
                r28 = r27
                r29 = r28
                r30 = r29
                r31 = r4
                r3 = r6
                r4 = r3
                r13 = r4
                r19 = r13
                r20 = r19
                r21 = r20
                r22 = r21
                r23 = r22
                r24 = r23
                r25 = r24
                r26 = r25
                r2 = r26
                r6 = r30
            L_0x00c4:
                if (r31 == 0) goto L_0x01b3
                int r12 = r0.decodeElementIndex(r1)
                switch(r12) {
                    case -1: goto L_0x01a5;
                    case 0: goto L_0x0198;
                    case 1: goto L_0x0181;
                    case 2: goto L_0x0174;
                    case 3: goto L_0x0167;
                    case 4: goto L_0x015b;
                    case 5: goto L_0x014d;
                    case 6: goto L_0x0144;
                    case 7: goto L_0x0139;
                    case 8: goto L_0x012e;
                    case 9: goto L_0x0123;
                    case 10: goto L_0x0118;
                    case 11: goto L_0x010d;
                    case 12: goto L_0x0102;
                    case 13: goto L_0x00f7;
                    case 14: goto L_0x00e9;
                    case 15: goto L_0x00dc;
                    case 16: goto L_0x00d3;
                    default: goto L_0x00cd;
                }
            L_0x00cd:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r12)
                throw r0
            L_0x00d3:
                int r6 = r0.decodeIntElement(r1, r5)
                r12 = 65536(0x10000, float:9.18355E-41)
                r14 = r14 | r12
                goto L_0x01b0
            L_0x00dc:
                r12 = 15
                java.lang.String r26 = r0.decodeStringElement(r1, r12)
                r32 = 32768(0x8000, float:4.5918E-41)
                r14 = r14 | r32
                goto L_0x01b0
            L_0x00e9:
                r5 = 14
                r12 = 15
                java.lang.String r25 = r0.decodeStringElement(r1, r5)
                r14 = r14 | 16384(0x4000, float:2.2959E-41)
            L_0x00f3:
                r5 = 16
                goto L_0x01b0
            L_0x00f7:
                r5 = 13
                r12 = 15
                java.lang.String r24 = r0.decodeStringElement(r1, r5)
                r14 = r14 | 8192(0x2000, float:1.14794E-41)
                goto L_0x00f3
            L_0x0102:
                r5 = 12
                r12 = 15
                java.lang.String r23 = r0.decodeStringElement(r1, r5)
                r14 = r14 | 4096(0x1000, float:5.74E-42)
                goto L_0x00f3
            L_0x010d:
                r5 = 12
                r12 = 15
                java.lang.String r22 = r0.decodeStringElement(r1, r7)
                r14 = r14 | 2048(0x800, float:2.87E-42)
                goto L_0x00f3
            L_0x0118:
                r5 = 12
                r12 = 15
                java.lang.String r21 = r0.decodeStringElement(r1, r8)
                r14 = r14 | 1024(0x400, float:1.435E-42)
                goto L_0x00f3
            L_0x0123:
                r5 = 12
                r12 = 15
                boolean r27 = r0.decodeBooleanElement(r1, r9)
                r14 = r14 | 512(0x200, float:7.175E-43)
                goto L_0x00f3
            L_0x012e:
                r5 = 12
                r12 = 15
                boolean r28 = r0.decodeBooleanElement(r1, r15)
                r14 = r14 | 256(0x100, float:3.59E-43)
                goto L_0x00f3
            L_0x0139:
                r12 = 15
                com.appsamurai.storyly.data.m$a r5 = com.appsamurai.storyly.data.m.f3857b
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r10, r5, r13)
                r14 = r14 | 128(0x80, float:1.794E-43)
                goto L_0x00f3
            L_0x0144:
                r12 = 15
                boolean r29 = r0.decodeBooleanElement(r1, r11)
                r14 = r14 | 64
                goto L_0x00f3
            L_0x014d:
                r5 = 5
                r12 = 15
                boolean r30 = r0.decodeBooleanElement(r1, r5)
                r14 = r14 | 32
                r12 = r5
                r5 = 16
                goto L_0x00c4
            L_0x015b:
                r12 = 15
                kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r7 = 4
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r7, r5, r3)
                r14 = r14 | 16
                goto L_0x01ac
            L_0x0167:
                r7 = 4
                r12 = 15
                com.appsamurai.storyly.data.c$a r5 = com.appsamurai.storyly.data.c.f3622b
                r7 = 3
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r7, r5, r2)
                r14 = r14 | 8
                goto L_0x01ac
            L_0x0174:
                r7 = 3
                r12 = 15
                com.appsamurai.storyly.data.c$a r5 = com.appsamurai.storyly.data.c.f3622b
                r7 = 2
                java.lang.Object r4 = r0.decodeSerializableElement(r1, r7, r5, r4)
                r14 = r14 | 4
                goto L_0x01ac
            L_0x0181:
                r7 = 2
                r12 = 15
                com.appsamurai.storyly.data.c$a r5 = com.appsamurai.storyly.data.c.f3622b
                r7 = r20
                r8 = 1
                java.lang.Object r5 = r0.decodeSerializableElement(r1, r8, r5, r7)
                r14 = r14 | 2
                r20 = r5
            L_0x0191:
                r5 = 16
                r7 = 11
                r8 = 10
                goto L_0x01b0
            L_0x0198:
                r7 = r20
                r5 = 0
                r8 = 1
                r12 = 15
                java.lang.String r19 = r0.decodeStringElement(r1, r5)
                r14 = r14 | 1
                goto L_0x0191
            L_0x01a5:
                r7 = r20
                r5 = 0
                r12 = 15
                r31 = r5
            L_0x01ac:
                r5 = 16
                r7 = 11
            L_0x01b0:
                r12 = 5
                goto L_0x00c4
            L_0x01b3:
                r7 = r20
                r5 = r2
                r51 = r6
                r6 = r13
                r34 = r14
                r35 = r19
                r45 = r21
                r46 = r22
                r47 = r23
                r48 = r24
                r49 = r25
                r50 = r26
                r44 = r27
                r43 = r28
                r41 = r29
                r40 = r30
                r13 = r3
                r3 = r4
                r4 = r7
            L_0x01d4:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.n0 r0 = new com.appsamurai.storyly.data.n0
                r33 = r0
                r36 = r4
                com.appsamurai.storyly.data.c r36 = (com.appsamurai.storyly.data.c) r36
                r37 = r3
                com.appsamurai.storyly.data.c r37 = (com.appsamurai.storyly.data.c) r37
                r38 = r5
                com.appsamurai.storyly.data.c r38 = (com.appsamurai.storyly.data.c) r38
                r39 = r13
                java.lang.String r39 = (java.lang.String) r39
                r42 = r6
                com.appsamurai.storyly.data.m r42 = (com.appsamurai.storyly.data.m) r42
                r33.<init>(r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.n0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4077b;
        }

        public void serialize(Encoder encoder, Object obj) {
            n0 n0Var = (n0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(n0Var, "value");
            SerialDescriptor serialDescriptor = f4077b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(n0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeStringElement(serialDescriptor, 0, n0Var.f4059a);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || !Intrinsics.areEqual((Object) n0Var.f4060b, (Object) new c(-1))) {
                beginStructure.encodeSerializableElement(serialDescriptor, 1, c.f3622b, n0Var.f4060b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || !Intrinsics.areEqual((Object) n0Var.f4061c, (Object) new c(-1))) {
                beginStructure.encodeSerializableElement(serialDescriptor, 2, c.f3622b, n0Var.f4061c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || n0Var.f4062d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, c.f3622b, n0Var.f4062d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || n0Var.f4063e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, n0Var.f4063e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || n0Var.f4064f) {
                beginStructure.encodeBooleanElement(serialDescriptor, 5, n0Var.f4064f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || n0Var.f4065g) {
                beginStructure.encodeBooleanElement(serialDescriptor, 6, n0Var.f4065g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || n0Var.f4066h != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, m.f3857b, n0Var.f4066h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || !n0Var.f4067i) {
                beginStructure.encodeBooleanElement(serialDescriptor, 8, n0Var.f4067i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || !n0Var.f4068j) {
                beginStructure.encodeBooleanElement(serialDescriptor, 9, n0Var.f4068j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || !Intrinsics.areEqual((Object) n0Var.f4069k, (Object) "Add to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 10, n0Var.f4069k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || !Intrinsics.areEqual((Object) n0Var.f4070l, (Object) "Go to Cart")) {
                beginStructure.encodeStringElement(serialDescriptor, 11, n0Var.f4070l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || !Intrinsics.areEqual((Object) n0Var.f4071m, (Object) "Continue with Stories")) {
                beginStructure.encodeStringElement(serialDescriptor, 12, n0Var.f4071m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || !Intrinsics.areEqual((Object) n0Var.f4072n, (Object) "Added to your Cart successfully")) {
                beginStructure.encodeStringElement(serialDescriptor, 13, n0Var.f4072n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || !Intrinsics.areEqual((Object) n0Var.f4073o, (Object) "Go to Checkout")) {
                beginStructure.encodeStringElement(serialDescriptor, 14, n0Var.f4073o);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 15) || !Intrinsics.areEqual((Object) n0Var.f4074p, (Object) "Total")) {
                beginStructure.encodeStringElement(serialDescriptor, 15, n0Var.f4074p);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 16) || n0Var.f4075q != 4) {
                beginStructure.encodeIntElement(serialDescriptor, 16, n0Var.f4075q);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ n0(int i3, @Required @SerialName("button_text") String str, @SerialName("text_color") c cVar, @SerialName("icon_color") c cVar2, @SerialName("border_color") c cVar3, @SerialName("outlink") String str2, @SerialName("is_bold") boolean z2, @SerialName("is_italic") boolean z3, @SerialName("products") m mVar, @SerialName("is_s_price_visible") boolean z4, @SerialName("is_price_visible") boolean z5, @SerialName("p_b_text") String str3, @SerialName("s_b_cart_text") String str4, @SerialName("s_b_back_text") String str5, @SerialName("s_message") String str6, @SerialName("checkout_b_text") String str7, @SerialName("t_text") String str8, @SerialName("max_v") int i4) {
        c cVar4;
        c cVar5;
        int i5 = i3;
        if (1 != (i5 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 1, a.f4076a.getDescriptor());
        }
        this.f4059a = str;
        if ((i5 & 2) == 0) {
            cVar4 = new c(-1);
        } else {
            cVar4 = cVar;
        }
        this.f4060b = cVar4;
        if ((i5 & 4) == 0) {
            cVar5 = new c(-1);
        } else {
            cVar5 = cVar2;
        }
        this.f4061c = cVar5;
        if ((i5 & 8) == 0) {
            this.f4062d = null;
        } else {
            this.f4062d = cVar3;
        }
        if ((i5 & 16) == 0) {
            this.f4063e = null;
        } else {
            this.f4063e = str2;
        }
        if ((i5 & 32) == 0) {
            this.f4064f = false;
        } else {
            this.f4064f = z2;
        }
        if ((i5 & 64) == 0) {
            this.f4065g = false;
        } else {
            this.f4065g = z3;
        }
        if ((i5 & 128) == 0) {
            this.f4066h = null;
        } else {
            this.f4066h = mVar;
        }
        if ((i5 & 256) == 0) {
            this.f4067i = true;
        } else {
            this.f4067i = z4;
        }
        if ((i5 & 512) == 0) {
            this.f4068j = true;
        } else {
            this.f4068j = z5;
        }
        this.f4069k = (i5 & 1024) == 0 ? "Add to Cart" : str3;
        this.f4070l = (i5 & 2048) == 0 ? "Go to Cart" : str4;
        this.f4071m = (i5 & 4096) == 0 ? "Continue with Stories" : str5;
        this.f4072n = (i5 & 8192) == 0 ? "Added to your Cart successfully" : str6;
        this.f4073o = (i5 & 16384) == 0 ? "Go to Checkout" : str7;
        this.f4074p = (32768 & i5) == 0 ? "Total" : str8;
        this.f4075q = (i5 & 65536) == 0 ? 4 : i4;
    }

    @NotNull
    public String a() {
        return this.f4073o;
    }

    @Nullable
    public m c() {
        return this.f4066h;
    }

    @NotNull
    public String d() {
        return this.f4069k;
    }

    @NotNull
    public String e() {
        return this.f4071m;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n0)) {
            return false;
        }
        n0 n0Var = (n0) obj;
        return Intrinsics.areEqual((Object) this.f4059a, (Object) n0Var.f4059a) && Intrinsics.areEqual((Object) this.f4060b, (Object) n0Var.f4060b) && Intrinsics.areEqual((Object) this.f4061c, (Object) n0Var.f4061c) && Intrinsics.areEqual((Object) this.f4062d, (Object) n0Var.f4062d) && Intrinsics.areEqual((Object) this.f4063e, (Object) n0Var.f4063e) && this.f4064f == n0Var.f4064f && this.f4065g == n0Var.f4065g && Intrinsics.areEqual((Object) this.f4066h, (Object) n0Var.f4066h) && this.f4067i == n0Var.f4067i && this.f4068j == n0Var.f4068j && Intrinsics.areEqual((Object) this.f4069k, (Object) n0Var.f4069k) && Intrinsics.areEqual((Object) this.f4070l, (Object) n0Var.f4070l) && Intrinsics.areEqual((Object) this.f4071m, (Object) n0Var.f4071m) && Intrinsics.areEqual((Object) this.f4072n, (Object) n0Var.f4072n) && Intrinsics.areEqual((Object) this.f4073o, (Object) n0Var.f4073o) && Intrinsics.areEqual((Object) this.f4074p, (Object) n0Var.f4074p) && this.f4075q == n0Var.f4075q;
    }

    @NotNull
    public String f() {
        return this.f4070l;
    }

    @NotNull
    public String g() {
        return this.f4072n;
    }

    @NotNull
    public String h() {
        return this.f4074p;
    }

    public int hashCode() {
        int c3 = androidx.compose.animation.core.a.c(this.f4061c.f3624a, androidx.compose.animation.core.a.c(this.f4060b.f3624a, this.f4059a.hashCode() * 31, 31), 31);
        c cVar = this.f4062d;
        int i3 = 0;
        int hashCode = (c3 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        String str = this.f4063e;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        boolean z2 = this.f4064f;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (hashCode2 + (z2 ? 1 : 0)) * 31;
        boolean z4 = this.f4065g;
        if (z4) {
            z4 = true;
        }
        int i5 = (i4 + (z4 ? 1 : 0)) * 31;
        m mVar = this.f4066h;
        if (mVar != null) {
            i3 = mVar.hashCode();
        }
        int i6 = (i5 + i3) * 31;
        boolean z5 = this.f4067i;
        if (z5) {
            z5 = true;
        }
        int i7 = (i6 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.f4068j;
        if (!z6) {
            z3 = z6;
        }
        return Integer.hashCode(this.f4075q) + androidx.compose.animation.core.a.i(this.f4074p, androidx.compose.animation.core.a.i(this.f4073o, androidx.compose.animation.core.a.i(this.f4072n, androidx.compose.animation.core.a.i(this.f4071m, androidx.compose.animation.core.a.i(this.f4070l, androidx.compose.animation.core.a.i(this.f4069k, (i7 + (z3 ? 1 : 0)) * 31, 31), 31), 31), 31), 31), 31);
    }

    public boolean i() {
        return this.f4068j;
    }

    public boolean j() {
        return this.f4067i;
    }

    public final int k() {
        int a2 = f.a(this.f4061c.f3624a, 1.0f);
        if (ColorUtils.calculateContrast(ViewCompat.MEASURED_STATE_MASK, a2) > ColorUtils.calculateContrast(-1, a2)) {
            return ViewCompat.MEASURED_STATE_MASK;
        }
        return -1;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylySwipeActionLayer(buttonText=");
        sb.append(this.f4059a);
        sb.append(", textColor=");
        sb.append(this.f4060b);
        sb.append(", iconColor=");
        sb.append(this.f4061c);
        sb.append(", borderColor=");
        sb.append(this.f4062d);
        sb.append(", actionUrl=");
        sb.append(this.f4063e);
        sb.append(", isBold=");
        sb.append(this.f4064f);
        sb.append(", isItalic=");
        sb.append(this.f4065g);
        sb.append(", productData=");
        sb.append(this.f4066h);
        sb.append(", isProductSalesPriceVisible=");
        sb.append(this.f4067i);
        sb.append(", isProductPriceVisible=");
        sb.append(this.f4068j);
        sb.append(", purchaseButtonText=");
        sb.append(this.f4069k);
        sb.append(", successButtonCartText=");
        sb.append(this.f4070l);
        sb.append(", successButtonBackText=");
        sb.append(this.f4071m);
        sb.append(", successMessage=");
        sb.append(this.f4072n);
        sb.append(", checkoutButtonText=");
        sb.append(this.f4073o);
        sb.append(", totalText=");
        sb.append(this.f4074p);
        sb.append(", maxVariantCount=");
        return android.support.v4.media.session.a.p(sb, this.f4075q, ')');
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.SwipeAction);
    }

    public n0(@NotNull String str, @NotNull c cVar, @NotNull c cVar2, @Nullable c cVar3, @Nullable String str2, boolean z2, boolean z3, @Nullable m mVar, boolean z4, boolean z5, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, int i3) {
        String str9 = str3;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        String str13 = str7;
        String str14 = str8;
        Intrinsics.checkNotNullParameter(str, "buttonText");
        Intrinsics.checkNotNullParameter(cVar, "textColor");
        Intrinsics.checkNotNullParameter(cVar2, "iconColor");
        Intrinsics.checkNotNullParameter(str9, "purchaseButtonText");
        Intrinsics.checkNotNullParameter(str10, "successButtonCartText");
        Intrinsics.checkNotNullParameter(str11, "successButtonBackText");
        Intrinsics.checkNotNullParameter(str12, "successMessage");
        Intrinsics.checkNotNullParameter(str13, "checkoutButtonText");
        Intrinsics.checkNotNullParameter(str14, "totalText");
        this.f4059a = str;
        this.f4060b = cVar;
        this.f4061c = cVar2;
        this.f4062d = cVar3;
        this.f4063e = str2;
        this.f4064f = z2;
        this.f4065g = z3;
        this.f4066h = mVar;
        this.f4067i = z4;
        this.f4068j = z5;
        this.f4069k = str9;
        this.f4070l = str10;
        this.f4071m = str11;
        this.f4072n = str12;
        this.f4073o = str13;
        this.f4074p = str14;
        this.f4075q = i3;
    }
}
