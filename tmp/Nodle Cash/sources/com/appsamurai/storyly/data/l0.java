package com.appsamurai.storyly.data;

import androidx.camera.core.impl.i;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryRatingComponent;
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
public final class l0 extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3839a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f3840b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final String f3841c;

    /* renamed from: d  reason: collision with root package name */
    public final int f3842d;

    /* renamed from: e  reason: collision with root package name */
    public final int f3843e;

    /* renamed from: f  reason: collision with root package name */
    public final float f3844f;

    /* renamed from: g  reason: collision with root package name */
    public final boolean f3845g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final c f3846h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public final c f3847i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final c f3848j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final c f3849k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public final c f3850l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public final String f3851m;

    /* renamed from: n  reason: collision with root package name */
    public final boolean f3852n;

    /* renamed from: o  reason: collision with root package name */
    public final boolean f3853o;

    /* renamed from: p  reason: collision with root package name */
    public final boolean f3854p;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<l0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3855a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3856b;

        static {
            a aVar = new a();
            f3855a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyRatingLayer", aVar, 16);
            pluginGeneratedSerialDescriptor.addElement("title", false);
            pluginGeneratedSerialDescriptor.addElement("theme", false);
            pluginGeneratedSerialDescriptor.addElement("emoji_code", false);
            pluginGeneratedSerialDescriptor.addElement("average_answer", true);
            pluginGeneratedSerialDescriptor.addElement("answer_count", true);
            pluginGeneratedSerialDescriptor.addElement("sdk_scale", true);
            pluginGeneratedSerialDescriptor.addElement("has_title", true);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("t_color", true);
            pluginGeneratedSerialDescriptor.addElement("s_color", true);
            pluginGeneratedSerialDescriptor.addElement("s_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("r_border_color", true);
            pluginGeneratedSerialDescriptor.addElement("custom_payload", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            pluginGeneratedSerialDescriptor.addElement("is_result", true);
            f3856b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            c.a aVar = c.f3622b;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable6 = BuiltinSerializersKt.getNullable(stringSerializer);
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, intSerializer, intSerializer, FloatSerializer.INSTANCE, booleanSerializer, nullable, nullable2, nullable3, nullable4, nullable5, nullable6, booleanSerializer, booleanSerializer, booleanSerializer};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0129, code lost:
            r10 = r7;
            r7 = 11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x018a, code lost:
            r7 = 11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x018d, code lost:
            r14 = 8;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r34) {
            /*
                r33 = this;
                r0 = r34
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3856b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r7 = 11
                r8 = 10
                r9 = 9
                r10 = 7
                r11 = 6
                r12 = 5
                r13 = 3
                r14 = 8
                r15 = 4
                r3 = 2
                r4 = 1
                r5 = 0
                r6 = 0
                if (r2 == 0) goto L_0x0091
                java.lang.String r2 = r0.decodeStringElement(r1, r5)
                java.lang.String r4 = r0.decodeStringElement(r1, r4)
                java.lang.String r3 = r0.decodeStringElement(r1, r3)
                int r5 = r0.decodeIntElement(r1, r13)
                int r13 = r0.decodeIntElement(r1, r15)
                float r12 = r0.decodeFloatElement(r1, r12)
                boolean r11 = r0.decodeBooleanElement(r1, r11)
                com.appsamurai.storyly.data.c$a r15 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r15, r6)
                java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r14, r15, r6)
                java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r15, r6)
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r15, r6)
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r15, r6)
                kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r18 = r2
                r2 = 12
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r15, r6)
                r6 = 13
                boolean r6 = r0.decodeBooleanElement(r1, r6)
                r15 = 14
                boolean r15 = r0.decodeBooleanElement(r1, r15)
                r34 = r2
                r2 = 15
                boolean r2 = r0.decodeBooleanElement(r1, r2)
                r16 = 65535(0xffff, float:9.1834E-41)
                r22 = r2
                r20 = r3
                r26 = r11
                r27 = r12
                r28 = r13
                r19 = r15
                r2 = r34
                r11 = r10
                r10 = r9
                r9 = r5
                r5 = r18
                r18 = r6
                r6 = r4
                r4 = r16
                goto L_0x01a6
            L_0x0091:
                r2 = 0
                r27 = r2
                r30 = r4
                r2 = r5
                r11 = r2
                r25 = r11
                r26 = r25
                r28 = r26
                r29 = r28
                r3 = r6
                r4 = r3
                r12 = r4
                r13 = r12
                r15 = r13
                r18 = r15
                r19 = r18
                r20 = r19
                r5 = r20
                r6 = r29
            L_0x00af:
                if (r30 == 0) goto L_0x0191
                int r10 = r0.decodeElementIndex(r1)
                switch(r10) {
                    case -1: goto L_0x0183;
                    case 0: goto L_0x0176;
                    case 1: goto L_0x0169;
                    case 2: goto L_0x015c;
                    case 3: goto L_0x014e;
                    case 4: goto L_0x0143;
                    case 5: goto L_0x0138;
                    case 6: goto L_0x012d;
                    case 7: goto L_0x011d;
                    case 8: goto L_0x010d;
                    case 9: goto L_0x0101;
                    case 10: goto L_0x00f5;
                    case 11: goto L_0x00e9;
                    case 12: goto L_0x00dd;
                    case 13: goto L_0x00d4;
                    case 14: goto L_0x00cb;
                    case 15: goto L_0x00be;
                    default: goto L_0x00b8;
                }
            L_0x00b8:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r10)
                throw r0
            L_0x00be:
                r10 = 15
                boolean r2 = r0.decodeBooleanElement(r1, r10)
                r31 = 32768(0x8000, float:4.5918E-41)
                r11 = r11 | r31
            L_0x00c9:
                r10 = 7
                goto L_0x00af
            L_0x00cb:
                r10 = 14
                boolean r29 = r0.decodeBooleanElement(r1, r10)
                r11 = r11 | 16384(0x4000, float:2.2959E-41)
                goto L_0x00c9
            L_0x00d4:
                r10 = 13
                boolean r25 = r0.decodeBooleanElement(r1, r10)
                r11 = r11 | 8192(0x2000, float:1.14794E-41)
                goto L_0x00c9
            L_0x00dd:
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r14 = 12
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r14, r10, r12)
                r11 = r11 | 4096(0x1000, float:5.74E-42)
                goto L_0x018c
            L_0x00e9:
                r14 = 12
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r7, r10, r5)
                r11 = r11 | 2048(0x800, float:2.87E-42)
                goto L_0x018c
            L_0x00f5:
                r14 = 12
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r8, r10, r15)
                r11 = r11 | 1024(0x400, float:1.435E-42)
                goto L_0x018c
            L_0x0101:
                r14 = 12
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r9, r10, r13)
                r11 = r11 | 512(0x200, float:7.175E-43)
                goto L_0x018c
            L_0x010d:
                r14 = 12
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r7 = 8
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r7, r10, r3)
                r11 = r11 | 256(0x100, float:3.59E-43)
                r14 = r7
                r7 = 11
                goto L_0x00c9
            L_0x011d:
                r7 = r14
                r14 = 12
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r7 = 7
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r7, r10, r4)
                r11 = r11 | 128(0x80, float:1.794E-43)
            L_0x0129:
                r10 = r7
                r7 = 11
                goto L_0x018d
            L_0x012d:
                r7 = 7
                r10 = 6
                r14 = 12
                boolean r26 = r0.decodeBooleanElement(r1, r10)
                r11 = r11 | 64
                goto L_0x0129
            L_0x0138:
                r7 = 5
                r10 = 6
                r14 = 12
                float r27 = r0.decodeFloatElement(r1, r7)
                r11 = r11 | 32
                goto L_0x018a
            L_0x0143:
                r7 = 4
                r10 = 6
                r14 = 12
                int r28 = r0.decodeIntElement(r1, r7)
                r11 = r11 | 16
                goto L_0x018a
            L_0x014e:
                r6 = 3
                r7 = 4
                r10 = 6
                r14 = 12
                int r24 = r0.decodeIntElement(r1, r6)
                r11 = r11 | 8
                r6 = r24
                goto L_0x018a
            L_0x015c:
                r7 = 2
                r10 = 6
                r14 = 12
                r23 = 3
                java.lang.String r20 = r0.decodeStringElement(r1, r7)
                r11 = r11 | 4
                goto L_0x018a
            L_0x0169:
                r7 = 1
                r10 = 6
                r14 = 12
                r23 = 3
                java.lang.String r19 = r0.decodeStringElement(r1, r7)
                r11 = r11 | 2
                goto L_0x018a
            L_0x0176:
                r7 = 0
                r10 = 6
                r14 = 12
                r23 = 3
                java.lang.String r18 = r0.decodeStringElement(r1, r7)
                r11 = r11 | 1
                goto L_0x018a
            L_0x0183:
                r7 = 0
                r14 = 12
                r23 = 3
                r30 = r7
            L_0x018a:
                r7 = 11
            L_0x018c:
                r10 = 7
            L_0x018d:
                r14 = 8
                goto L_0x00af
            L_0x0191:
                r22 = r2
                r14 = r3
                r7 = r5
                r9 = r6
                r2 = r12
                r10 = r13
                r8 = r15
                r5 = r18
                r6 = r19
                r18 = r25
                r19 = r29
                r32 = r11
                r11 = r4
                r4 = r32
            L_0x01a6:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.l0 r0 = new com.appsamurai.storyly.data.l0
                r3 = r0
                r12 = r11
                com.appsamurai.storyly.data.c r12 = (com.appsamurai.storyly.data.c) r12
                r13 = r14
                com.appsamurai.storyly.data.c r13 = (com.appsamurai.storyly.data.c) r13
                r14 = r10
                com.appsamurai.storyly.data.c r14 = (com.appsamurai.storyly.data.c) r14
                r15 = r8
                com.appsamurai.storyly.data.c r15 = (com.appsamurai.storyly.data.c) r15
                r16 = r7
                com.appsamurai.storyly.data.c r16 = (com.appsamurai.storyly.data.c) r16
                r17 = r2
                java.lang.String r17 = (java.lang.String) r17
                r21 = 0
                r7 = r20
                r8 = r9
                r9 = r28
                r10 = r27
                r11 = r26
                r20 = r22
                r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.l0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3856b;
        }

        public void serialize(Encoder encoder, Object obj) {
            l0 l0Var = (l0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(l0Var, "value");
            SerialDescriptor serialDescriptor = f3856b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(l0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(l0Var, beginStructure, serialDescriptor);
            beginStructure.encodeStringElement(serialDescriptor, 0, l0Var.f3839a);
            beginStructure.encodeStringElement(serialDescriptor, 1, l0Var.f3840b);
            beginStructure.encodeStringElement(serialDescriptor, 2, l0Var.f3841c);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || l0Var.f3842d != 0) {
                beginStructure.encodeIntElement(serialDescriptor, 3, l0Var.f3842d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || l0Var.f3843e != 0) {
                beginStructure.encodeIntElement(serialDescriptor, 4, l0Var.f3843e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || !Intrinsics.areEqual((Object) Float.valueOf(l0Var.f3844f), (Object) Float.valueOf(0.0f))) {
                beginStructure.encodeFloatElement(serialDescriptor, 5, l0Var.f3844f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || !l0Var.f3845g) {
                beginStructure.encodeBooleanElement(serialDescriptor, 6, l0Var.f3845g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || l0Var.f3846h != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 7, c.f3622b, l0Var.f3846h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || l0Var.f3847i != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, c.f3622b, l0Var.f3847i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || l0Var.f3848j != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, c.f3622b, l0Var.f3848j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || l0Var.f3849k != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 10, c.f3622b, l0Var.f3849k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || l0Var.f3850l != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 11, c.f3622b, l0Var.f3850l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || l0Var.f3851m != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, l0Var.f3851m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || !l0Var.f3852n) {
                beginStructure.encodeBooleanElement(serialDescriptor, 13, l0Var.f3852n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || l0Var.f3853o) {
                beginStructure.encodeBooleanElement(serialDescriptor, 14, l0Var.f3853o);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 15) || l0Var.f3854p) {
                beginStructure.encodeBooleanElement(serialDescriptor, 15, l0Var.f3854p);
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
    public /* synthetic */ l0(int i3, @Required @SerialName("title") String str, @Required @SerialName("theme") String str2, @Required @SerialName("emoji_code") String str3, @SerialName("average_answer") int i4, @SerialName("answer_count") int i5, @SerialName("sdk_scale") float f2, @SerialName("has_title") boolean z2, @SerialName("bg_color") c cVar, @SerialName("t_color") c cVar2, @SerialName("s_color") c cVar3, @SerialName("s_bg_color") c cVar4, @SerialName("r_border_color") c cVar5, @SerialName("custom_payload") String str4, @SerialName("is_bold") boolean z3, @SerialName("is_italic") boolean z4, @SerialName("is_result") boolean z5, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        int i6 = i3;
        if (7 != (i6 & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 7, a.f3855a.getDescriptor());
        }
        this.f3839a = str;
        this.f3840b = str2;
        this.f3841c = str3;
        if ((i6 & 8) == 0) {
            this.f3842d = 0;
        } else {
            this.f3842d = i4;
        }
        if ((i6 & 16) == 0) {
            this.f3843e = 0;
        } else {
            this.f3843e = i5;
        }
        this.f3844f = (i6 & 32) == 0 ? 0.0f : f2;
        if ((i6 & 64) == 0) {
            this.f3845g = true;
        } else {
            this.f3845g = z2;
        }
        if ((i6 & 128) == 0) {
            this.f3846h = null;
        } else {
            this.f3846h = cVar;
        }
        if ((i6 & 256) == 0) {
            this.f3847i = null;
        } else {
            this.f3847i = cVar2;
        }
        if ((i6 & 512) == 0) {
            this.f3848j = null;
        } else {
            this.f3848j = cVar3;
        }
        if ((i6 & 1024) == 0) {
            this.f3849k = null;
        } else {
            this.f3849k = cVar4;
        }
        if ((i6 & 2048) == 0) {
            this.f3850l = null;
        } else {
            this.f3850l = cVar5;
        }
        if ((i6 & 4096) == 0) {
            this.f3851m = null;
        } else {
            this.f3851m = str4;
        }
        if ((i6 & 8192) == 0) {
            this.f3852n = true;
        } else {
            this.f3852n = z3;
        }
        if ((i6 & 16384) == 0) {
            this.f3853o = false;
        } else {
            this.f3853o = z4;
        }
        if ((i6 & 32768) == 0) {
            this.f3854p = false;
        } else {
            this.f3854p = z5;
        }
    }

    public static l0 a(l0 l0Var, String str, String str2, String str3, int i3, int i4, float f2, boolean z2, c cVar, c cVar2, c cVar3, c cVar4, c cVar5, String str4, boolean z3, boolean z4, boolean z5, int i5) {
        l0 l0Var2 = l0Var;
        int i6 = i5;
        String str5 = null;
        String str6 = (i6 & 1) != 0 ? l0Var2.f3839a : null;
        String str7 = (i6 & 2) != 0 ? l0Var2.f3840b : null;
        String str8 = (i6 & 4) != 0 ? l0Var2.f3841c : null;
        int i7 = (i6 & 8) != 0 ? l0Var2.f3842d : i3;
        int i8 = (i6 & 16) != 0 ? l0Var2.f3843e : i4;
        float f3 = (i6 & 32) != 0 ? l0Var2.f3844f : f2;
        boolean z6 = (i6 & 64) != 0 ? l0Var2.f3845g : z2;
        c cVar6 = (i6 & 128) != 0 ? l0Var2.f3846h : null;
        c cVar7 = (i6 & 256) != 0 ? l0Var2.f3847i : null;
        c cVar8 = (i6 & 512) != 0 ? l0Var2.f3848j : null;
        c cVar9 = (i6 & 1024) != 0 ? l0Var2.f3849k : null;
        c cVar10 = (i6 & 2048) != 0 ? l0Var2.f3850l : null;
        if ((i6 & 4096) != 0) {
            str5 = l0Var2.f3851m;
        }
        boolean z7 = (i6 & 8192) != 0 ? l0Var2.f3852n : z3;
        boolean z8 = (i6 & 16384) != 0 ? l0Var2.f3853o : z4;
        boolean z9 = (i6 & 32768) != 0 ? l0Var2.f3854p : z5;
        l0Var.getClass();
        Intrinsics.checkNotNullParameter(str6, "title");
        Intrinsics.checkNotNullParameter(str7, "theme");
        Intrinsics.checkNotNullParameter(str8, "emojiCode");
        return new l0(str6, str7, str8, i7, i8, f3, z6, cVar6, cVar7, cVar8, cVar9, cVar10, str5, z7, z8, z9);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l0)) {
            return false;
        }
        l0 l0Var = (l0) obj;
        return Intrinsics.areEqual((Object) this.f3839a, (Object) l0Var.f3839a) && Intrinsics.areEqual((Object) this.f3840b, (Object) l0Var.f3840b) && Intrinsics.areEqual((Object) this.f3841c, (Object) l0Var.f3841c) && this.f3842d == l0Var.f3842d && this.f3843e == l0Var.f3843e && Intrinsics.areEqual((Object) Float.valueOf(this.f3844f), (Object) Float.valueOf(l0Var.f3844f)) && this.f3845g == l0Var.f3845g && Intrinsics.areEqual((Object) this.f3846h, (Object) l0Var.f3846h) && Intrinsics.areEqual((Object) this.f3847i, (Object) l0Var.f3847i) && Intrinsics.areEqual((Object) this.f3848j, (Object) l0Var.f3848j) && Intrinsics.areEqual((Object) this.f3849k, (Object) l0Var.f3849k) && Intrinsics.areEqual((Object) this.f3850l, (Object) l0Var.f3850l) && Intrinsics.areEqual((Object) this.f3851m, (Object) l0Var.f3851m) && this.f3852n == l0Var.f3852n && this.f3853o == l0Var.f3853o && this.f3854p == l0Var.f3854p;
    }

    public int hashCode() {
        int c3 = android.support.v4.media.session.a.c(this.f3844f, androidx.compose.animation.core.a.c(this.f3843e, androidx.compose.animation.core.a.c(this.f3842d, androidx.compose.animation.core.a.i(this.f3841c, androidx.compose.animation.core.a.i(this.f3840b, this.f3839a.hashCode() * 31, 31), 31), 31), 31), 31);
        boolean z2 = this.f3845g;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i3 = (c3 + (z2 ? 1 : 0)) * 31;
        c cVar = this.f3846h;
        int i4 = 0;
        int hashCode = (i3 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f3847i;
        int hashCode2 = (hashCode + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f3848j;
        int hashCode3 = (hashCode2 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f3849k;
        int hashCode4 = (hashCode3 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f3850l;
        int hashCode5 = (hashCode4 + (cVar5 == null ? 0 : Integer.hashCode(cVar5.f3624a))) * 31;
        String str = this.f3851m;
        if (str != null) {
            i4 = str.hashCode();
        }
        int i5 = (hashCode5 + i4) * 31;
        boolean z4 = this.f3852n;
        if (z4) {
            z4 = true;
        }
        int i6 = (i5 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f3853o;
        if (z5) {
            z5 = true;
        }
        int i7 = (i6 + (z5 ? 1 : 0)) * 31;
        boolean z6 = this.f3854p;
        if (!z6) {
            z3 = z6;
        }
        return i7 + (z3 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyRatingLayer(title=");
        sb.append(this.f3839a);
        sb.append(", theme=");
        sb.append(this.f3840b);
        sb.append(", emojiCode=");
        sb.append(this.f3841c);
        sb.append(", average=");
        sb.append(this.f3842d);
        sb.append(", answerCount=");
        sb.append(this.f3843e);
        sb.append(", sdkScale=");
        sb.append(this.f3844f);
        sb.append(", hasTitle=");
        sb.append(this.f3845g);
        sb.append(", backgroundColor=");
        sb.append(this.f3846h);
        sb.append(", ratingTitleColor=");
        sb.append(this.f3847i);
        sb.append(", sliderColor=");
        sb.append(this.f3848j);
        sb.append(", sliderBackgroundColor=");
        sb.append(this.f3849k);
        sb.append(", ratingBorderColor=");
        sb.append(this.f3850l);
        sb.append(", customPayload=");
        sb.append(this.f3851m);
        sb.append(", isBold=");
        sb.append(this.f3852n);
        sb.append(", isItalic=");
        sb.append(this.f3853o);
        sb.append(", isResult=");
        return i.c(sb, this.f3854p, ')');
    }

    public l0(@NotNull String str, @NotNull String str2, @NotNull String str3, int i3, int i4, float f2, boolean z2, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, @Nullable String str4, boolean z3, boolean z4, boolean z5) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(str2, "theme");
        Intrinsics.checkNotNullParameter(str3, "emojiCode");
        this.f3839a = str;
        this.f3840b = str2;
        this.f3841c = str3;
        this.f3842d = i3;
        this.f3843e = i4;
        this.f3844f = f2;
        this.f3845g = z2;
        this.f3846h = cVar;
        this.f3847i = cVar2;
        this.f3848j = cVar3;
        this.f3849k = cVar4;
        this.f3850l = cVar5;
        this.f3851m = str4;
        this.f3852n = z3;
        this.f3853o = z4;
        this.f3854p = z5;
    }

    public StoryComponent a(b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryRatingComponent(b0Var.f3614i, this.f3841c, -1, this.f3851m);
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var, int i3) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryRatingComponent(b0Var.f3614i, this.f3841c, i3, this.f3851m);
    }
}
