package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
import com.appsamurai.storyly.data.c;
import com.appsamurai.storyly.util.font.h;
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
public final class o0 extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4081a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final Float f4082b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final Float f4083c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final Integer f4084d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final c f4085e;

    /* renamed from: f  reason: collision with root package name */
    public final int f4086f;

    /* renamed from: g  reason: collision with root package name */
    public final int f4087g;

    /* renamed from: h  reason: collision with root package name */
    public final int f4088h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final c f4089i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final String f4090j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final h f4091k;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<o0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4092a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4093b;

        static {
            a aVar = new a();
            f4092a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyTextLayer", aVar, 11);
            pluginGeneratedSerialDescriptor.addElement("text", false);
            pluginGeneratedSerialDescriptor.addElement("line_h", true);
            pluginGeneratedSerialDescriptor.addElement("f_s", true);
            pluginGeneratedSerialDescriptor.addElement("l_c", true);
            pluginGeneratedSerialDescriptor.addElement("text_color", true);
            pluginGeneratedSerialDescriptor.addElement("text_size", true);
            pluginGeneratedSerialDescriptor.addElement("text_alignment", true);
            pluginGeneratedSerialDescriptor.addElement("gravity", true);
            pluginGeneratedSerialDescriptor.addElement("text_span_color", true);
            pluginGeneratedSerialDescriptor.addElement("text_font_name", true);
            pluginGeneratedSerialDescriptor.addElement("font", true);
            f4093b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            FloatSerializer floatSerializer = FloatSerializer.INSTANCE;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(floatSerializer);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(floatSerializer);
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(intSerializer);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(h.a.f6333a);
            c.a aVar = c.f3622b;
            return new KSerializer[]{stringSerializer, nullable, nullable2, nullable3, aVar, intSerializer, intSerializer, intSerializer, aVar, nullable4, nullable5};
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r37) {
            /*
                r36 = this;
                r0 = r37
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4093b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 10
                r4 = 9
                r5 = 7
                r6 = 6
                r7 = 5
                r8 = 3
                r9 = 8
                r10 = 4
                r11 = 2
                r12 = 1
                r13 = 0
                r14 = 0
                if (r2 == 0) goto L_0x0066
                java.lang.String r2 = r0.decodeStringElement(r1, r13)
                kotlinx.serialization.internal.FloatSerializer r13 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r12, r13, r14)
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r11, r13, r14)
                kotlinx.serialization.internal.IntSerializer r13 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r13, r14)
                com.appsamurai.storyly.data.c$a r13 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r10 = r0.decodeSerializableElement(r1, r10, r13, r14)
                int r7 = r0.decodeIntElement(r1, r7)
                int r6 = r0.decodeIntElement(r1, r6)
                int r5 = r0.decodeIntElement(r1, r5)
                java.lang.Object r9 = r0.decodeSerializableElement(r1, r9, r13, r14)
                kotlinx.serialization.internal.StringSerializer r13 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r13, r14)
                com.appsamurai.storyly.util.font.h$a r13 = com.appsamurai.storyly.util.font.h.a.f6333a
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r13, r14)
                r13 = 2047(0x7ff, float:2.868E-42)
                r24 = r2
                r31 = r5
                r30 = r6
                r29 = r7
                r23 = r13
                goto L_0x0148
            L_0x0066:
                r21 = r12
                r2 = r13
                r15 = r2
                r8 = r14
                r11 = r8
                r12 = r11
                r16 = r12
                r17 = r16
                r18 = r17
                r19 = r18
                r20 = r19
                r14 = r15
            L_0x0078:
                if (r21 == 0) goto L_0x012f
                int r10 = r0.decodeElementIndex(r1)
                switch(r10) {
                    case -1: goto L_0x0122;
                    case 0: goto L_0x0111;
                    case 1: goto L_0x00f6;
                    case 2: goto L_0x00df;
                    case 3: goto L_0x00cb;
                    case 4: goto L_0x00b8;
                    case 5: goto L_0x00b1;
                    case 6: goto L_0x00aa;
                    case 7: goto L_0x00a3;
                    case 8: goto L_0x009a;
                    case 9: goto L_0x0091;
                    case 10: goto L_0x0087;
                    default: goto L_0x0081;
                }
            L_0x0081:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r10)
                throw r0
            L_0x0087:
                com.appsamurai.storyly.util.font.h$a r10 = com.appsamurai.storyly.util.font.h.a.f6333a
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r3, r10, r12)
                r13 = r13 | 1024(0x400, float:1.435E-42)
            L_0x008f:
                r10 = 4
                goto L_0x0078
            L_0x0091:
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r4, r10, r11)
                r13 = r13 | 512(0x200, float:7.175E-43)
                goto L_0x008f
            L_0x009a:
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r8 = r0.decodeSerializableElement(r1, r9, r10, r8)
                r13 = r13 | 256(0x100, float:3.59E-43)
                goto L_0x008f
            L_0x00a3:
                int r2 = r0.decodeIntElement(r1, r5)
                r13 = r13 | 128(0x80, float:1.794E-43)
                goto L_0x008f
            L_0x00aa:
                int r14 = r0.decodeIntElement(r1, r6)
                r13 = r13 | 64
                goto L_0x008f
            L_0x00b1:
                int r15 = r0.decodeIntElement(r1, r7)
                r13 = r13 | 32
                goto L_0x008f
            L_0x00b8:
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                r3 = r16
                r4 = 4
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r4, r10, r3)
                r13 = r13 | 16
                r16 = r3
                r10 = r4
                r3 = 10
                r4 = 9
                goto L_0x0078
            L_0x00cb:
                r3 = r16
                r4 = 4
                kotlinx.serialization.internal.IntSerializer r10 = kotlinx.serialization.internal.IntSerializer.INSTANCE
                r4 = r17
                r5 = 3
                java.lang.Object r17 = r0.decodeNullableSerializableElement(r1, r5, r10, r4)
                r13 = r13 | 8
                r3 = 10
                r4 = 9
                r5 = 7
                goto L_0x008f
            L_0x00df:
                r3 = r16
                r4 = r17
                r5 = 3
                kotlinx.serialization.internal.FloatSerializer r10 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                r5 = r18
                r6 = 2
                java.lang.Object r18 = r0.decodeNullableSerializableElement(r1, r6, r10, r5)
                r13 = r13 | 4
                r3 = 10
                r4 = 9
                r5 = 7
                r6 = 6
                goto L_0x008f
            L_0x00f6:
                r3 = r16
                r4 = r17
                r5 = r18
                r6 = 2
                kotlinx.serialization.internal.FloatSerializer r10 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                r6 = r19
                r7 = 1
                java.lang.Object r19 = r0.decodeNullableSerializableElement(r1, r7, r10, r6)
                r13 = r13 | 2
            L_0x0108:
                r3 = 10
                r4 = 9
                r5 = 7
                r6 = 6
                r7 = 5
                goto L_0x008f
            L_0x0111:
                r3 = r16
                r4 = r17
                r5 = r18
                r6 = r19
                r7 = 1
                r10 = 0
                java.lang.String r20 = r0.decodeStringElement(r1, r10)
                r13 = r13 | 1
                goto L_0x0108
            L_0x0122:
                r3 = r16
                r4 = r17
                r5 = r18
                r6 = r19
                r7 = 1
                r10 = 0
                r21 = r10
                goto L_0x0108
            L_0x012f:
                r3 = r16
                r4 = r17
                r5 = r18
                r6 = r19
                r31 = r2
                r10 = r3
                r9 = r8
                r3 = r12
                r23 = r13
                r30 = r14
                r29 = r15
                r24 = r20
                r8 = r4
                r12 = r6
                r4 = r11
                r11 = r5
            L_0x0148:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.o0 r0 = new com.appsamurai.storyly.data.o0
                r25 = r12
                java.lang.Float r25 = (java.lang.Float) r25
                r26 = r11
                java.lang.Float r26 = (java.lang.Float) r26
                r27 = r8
                java.lang.Integer r27 = (java.lang.Integer) r27
                r28 = r10
                com.appsamurai.storyly.data.c r28 = (com.appsamurai.storyly.data.c) r28
                r32 = r9
                com.appsamurai.storyly.data.c r32 = (com.appsamurai.storyly.data.c) r32
                r33 = r4
                java.lang.String r33 = (java.lang.String) r33
                r34 = r3
                com.appsamurai.storyly.util.font.h r34 = (com.appsamurai.storyly.util.font.h) r34
                r35 = 0
                r22 = r0
                r22.<init>(r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.o0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4093b;
        }

        public void serialize(Encoder encoder, Object obj) {
            o0 o0Var = (o0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(o0Var, "value");
            SerialDescriptor serialDescriptor = f4093b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(o0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(o0Var, beginStructure, serialDescriptor);
            beginStructure.encodeStringElement(serialDescriptor, 0, o0Var.f4081a);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || o0Var.f4082b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, FloatSerializer.INSTANCE, o0Var.f4082b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || o0Var.f4083c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, FloatSerializer.INSTANCE, o0Var.f4083c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || o0Var.f4084d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, o0Var.f4084d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || !Intrinsics.areEqual((Object) o0Var.f4085e, (Object) new c(-1))) {
                beginStructure.encodeSerializableElement(serialDescriptor, 4, c.f3622b, o0Var.f4085e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || o0Var.f4086f != 0) {
                beginStructure.encodeIntElement(serialDescriptor, 5, o0Var.f4086f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || o0Var.f4087g != 1) {
                beginStructure.encodeIntElement(serialDescriptor, 6, o0Var.f4087g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || o0Var.f4088h != 0) {
                beginStructure.encodeIntElement(serialDescriptor, 7, o0Var.f4088h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || !Intrinsics.areEqual((Object) o0Var.f4089i, (Object) new c(0))) {
                beginStructure.encodeSerializableElement(serialDescriptor, 8, c.f3622b, o0Var.f4089i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || o0Var.f4090j != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, o0Var.f4090j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || o0Var.f4091k != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 10, h.a.f6333a, o0Var.f4091k);
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
    public /* synthetic */ o0(int i3, @Required @SerialName("text") String str, @SerialName("line_h") Float f2, @SerialName("f_s") Float f3, @SerialName("l_c") Integer num, @SerialName("text_color") c cVar, @SerialName("text_size") int i4, @SerialName("text_alignment") int i5, @SerialName("gravity") int i6, @SerialName("text_span_color") c cVar2, @SerialName("text_font_name") String str2, @SerialName("font") h hVar, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        if (1 != (i3 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 1, a.f4092a.getDescriptor());
        }
        this.f4081a = str;
        if ((i3 & 2) == 0) {
            this.f4082b = null;
        } else {
            this.f4082b = f2;
        }
        if ((i3 & 4) == 0) {
            this.f4083c = null;
        } else {
            this.f4083c = f3;
        }
        if ((i3 & 8) == 0) {
            this.f4084d = null;
        } else {
            this.f4084d = num;
        }
        if ((i3 & 16) == 0) {
            this.f4085e = new c(-1);
        } else {
            this.f4085e = cVar;
        }
        if ((i3 & 32) == 0) {
            this.f4086f = 0;
        } else {
            this.f4086f = i4;
        }
        if ((i3 & 64) == 0) {
            this.f4087g = 1;
        } else {
            this.f4087g = i5;
        }
        if ((i3 & 128) == 0) {
            this.f4088h = 0;
        } else {
            this.f4088h = i6;
        }
        if ((i3 & 256) == 0) {
            this.f4089i = new c(0);
        } else {
            this.f4089i = cVar2;
        }
        if ((i3 & 512) == 0) {
            this.f4090j = null;
        } else {
            this.f4090j = str2;
        }
        if ((i3 & 1024) == 0) {
            this.f4091k = null;
        } else {
            this.f4091k = hVar;
        }
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.Text);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o0)) {
            return false;
        }
        o0 o0Var = (o0) obj;
        return Intrinsics.areEqual((Object) this.f4081a, (Object) o0Var.f4081a) && Intrinsics.areEqual((Object) this.f4082b, (Object) o0Var.f4082b) && Intrinsics.areEqual((Object) this.f4083c, (Object) o0Var.f4083c) && Intrinsics.areEqual((Object) this.f4084d, (Object) o0Var.f4084d) && Intrinsics.areEqual((Object) this.f4085e, (Object) o0Var.f4085e) && this.f4086f == o0Var.f4086f && this.f4087g == o0Var.f4087g && this.f4088h == o0Var.f4088h && Intrinsics.areEqual((Object) this.f4089i, (Object) o0Var.f4089i) && Intrinsics.areEqual((Object) this.f4090j, (Object) o0Var.f4090j) && Intrinsics.areEqual((Object) this.f4091k, (Object) o0Var.f4091k);
    }

    public int hashCode() {
        int hashCode = this.f4081a.hashCode() * 31;
        Float f2 = this.f4082b;
        int i3 = 0;
        int hashCode2 = (hashCode + (f2 == null ? 0 : f2.hashCode())) * 31;
        Float f3 = this.f4083c;
        int hashCode3 = (hashCode2 + (f3 == null ? 0 : f3.hashCode())) * 31;
        Integer num = this.f4084d;
        int c3 = androidx.compose.animation.core.a.c(this.f4089i.f3624a, androidx.compose.animation.core.a.c(this.f4088h, androidx.compose.animation.core.a.c(this.f4087g, androidx.compose.animation.core.a.c(this.f4086f, androidx.compose.animation.core.a.c(this.f4085e.f3624a, (hashCode3 + (num == null ? 0 : num.hashCode())) * 31, 31), 31), 31), 31), 31);
        String str = this.f4090j;
        int hashCode4 = (c3 + (str == null ? 0 : str.hashCode())) * 31;
        h hVar = this.f4091k;
        if (hVar != null) {
            i3 = hVar.hashCode();
        }
        return hashCode4 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyTextLayer(text=" + this.f4081a + ", lineHeight=" + this.f4082b + ", fontSize=" + this.f4083c + ", lineCount=" + this.f4084d + ", textColor=" + this.f4085e + ", textSize=" + this.f4086f + ", textAlignment=" + this.f4087g + ", gravity=" + this.f4088h + ", textSpanColor=" + this.f4089i + ", textFontName=" + this.f4090j + ", font=" + this.f4091k + ')';
    }

    public final float a() {
        Float f2 = this.f4082b;
        if (f2 == null) {
            f2 = null;
        } else {
            f2.floatValue();
        }
        if (f2 == null) {
            return (((float) this.f4086f) * 0.1f) + 3.1f;
        }
        return f2.floatValue();
    }

    public o0(@NotNull String str, @Nullable Float f2, @Nullable Float f3, @Nullable Integer num, @NotNull c cVar, int i3, int i4, int i5, @NotNull c cVar2, @Nullable String str2, @Nullable h hVar) {
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(cVar, "textColor");
        Intrinsics.checkNotNullParameter(cVar2, "textSpanColor");
        this.f4081a = str;
        this.f4082b = f2;
        this.f4083c = f3;
        this.f4084d = num;
        this.f4085e = cVar;
        this.f4086f = i3;
        this.f4087g = i4;
        this.f4088h = i5;
        this.f4089i = cVar2;
        this.f4090j = str2;
        this.f4091k = hVar;
    }
}
