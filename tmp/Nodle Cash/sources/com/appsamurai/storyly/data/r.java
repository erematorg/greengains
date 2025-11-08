package com.appsamurai.storyly.data;

import androidx.camera.core.impl.i;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryComponentType;
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
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class r extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f4169a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f4170b;

    /* renamed from: c  reason: collision with root package name */
    public final long f4171c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final Long f4172d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final String f4173e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public final String f4174f;

    /* renamed from: g  reason: collision with root package name */
    public final float f4175g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f4176h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public final c f4177i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final c f4178j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final c f4179k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public final c f4180l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public final c f4181m;

    /* renamed from: n  reason: collision with root package name */
    public final boolean f4182n;

    /* renamed from: o  reason: collision with root package name */
    public final boolean f4183o;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<r> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4184a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4185b;

        static {
            a aVar = new a();
            f4184a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyCountDownLayer", aVar, 15);
            pluginGeneratedSerialDescriptor.addElement("title", false);
            pluginGeneratedSerialDescriptor.addElement("theme", false);
            pluginGeneratedSerialDescriptor.addElement(TtmlNode.END, false);
            pluginGeneratedSerialDescriptor.addElement("n_ts", true);
            pluginGeneratedSerialDescriptor.addElement("n_message", true);
            pluginGeneratedSerialDescriptor.addElement("outlink", true);
            pluginGeneratedSerialDescriptor.addElement("sdk_scale", true);
            pluginGeneratedSerialDescriptor.addElement("has_title", true);
            pluginGeneratedSerialDescriptor.addElement("cd_text_color", true);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("text_color", true);
            pluginGeneratedSerialDescriptor.addElement("toast_bg_color", true);
            pluginGeneratedSerialDescriptor.addElement("cd_border_color", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            f4185b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            LongSerializer longSerializer = LongSerializer.INSTANCE;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(longSerializer);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(stringSerializer);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(stringSerializer);
            c.a aVar = c.f3622b;
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable5 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable6 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable7 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable8 = BuiltinSerializersKt.getNullable(aVar);
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, stringSerializer, longSerializer, nullable, nullable2, nullable3, FloatSerializer.INSTANCE, booleanSerializer, nullable4, nullable5, nullable6, nullable7, nullable8, booleanSerializer, booleanSerializer};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x00c9, code lost:
            r10 = 6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x014a, code lost:
            r10 = r11;
            r6 = 11;
            r7 = 10;
            r8 = 9;
            r9 = 7;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r50) {
            /*
                r49 = this;
                r0 = r50
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4185b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r6 = 11
                r7 = 10
                r8 = 9
                r9 = 7
                r10 = 6
                r11 = 5
                r12 = 3
                r13 = 8
                r14 = 4
                r15 = 2
                r3 = 1
                r4 = 0
                r5 = 0
                if (r2 == 0) goto L_0x0080
                java.lang.String r2 = r0.decodeStringElement(r1, r4)
                java.lang.String r3 = r0.decodeStringElement(r1, r3)
                long r17 = r0.decodeLongElement(r1, r15)
                kotlinx.serialization.internal.LongSerializer r4 = kotlinx.serialization.internal.LongSerializer.INSTANCE
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r12, r4, r5)
                kotlinx.serialization.internal.StringSerializer r12 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r14, r12, r5)
                java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r11, r12, r5)
                float r10 = r0.decodeFloatElement(r1, r10)
                boolean r9 = r0.decodeBooleanElement(r1, r9)
                com.appsamurai.storyly.data.c$a r12 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r13, r12, r5)
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r12, r5)
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r12, r5)
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r12, r5)
                r15 = 12
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r15, r12, r5)
                r12 = 13
                boolean r12 = r0.decodeBooleanElement(r1, r12)
                r15 = 14
                boolean r15 = r0.decodeBooleanElement(r1, r15)
                r16 = 32767(0x7fff, float:4.5916E-41)
                r32 = r2
                r33 = r3
                r40 = r9
                r39 = r10
                r46 = r12
                r47 = r15
                r31 = r16
                r34 = r17
                goto L_0x01ab
            L_0x0080:
                r17 = 0
                r2 = 0
                r24 = r2
                r29 = r3
                r14 = r4
                r23 = r14
                r25 = r23
                r26 = r25
                r2 = r5
                r3 = r2
                r4 = r3
                r12 = r4
                r15 = r12
                r19 = r15
                r20 = r19
                r21 = r20
                r22 = r21
                r27 = r17
            L_0x009d:
                if (r29 == 0) goto L_0x0190
                int r11 = r0.decodeElementIndex(r1)
                switch(r11) {
                    case -1: goto L_0x0184;
                    case 0: goto L_0x0174;
                    case 1: goto L_0x0164;
                    case 2: goto L_0x0154;
                    case 3: goto L_0x0139;
                    case 4: goto L_0x011f;
                    case 5: goto L_0x010b;
                    case 6: goto L_0x0100;
                    case 7: goto L_0x00f7;
                    case 8: goto L_0x00ec;
                    case 9: goto L_0x00e1;
                    case 10: goto L_0x00d6;
                    case 11: goto L_0x00cb;
                    case 12: goto L_0x00bf;
                    case 13: goto L_0x00b6;
                    case 14: goto L_0x00ac;
                    default: goto L_0x00a6;
                }
            L_0x00a6:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r11)
                throw r0
            L_0x00ac:
                r11 = 14
                boolean r26 = r0.decodeBooleanElement(r1, r11)
                r14 = r14 | 16384(0x4000, float:2.2959E-41)
            L_0x00b4:
                r11 = 5
                goto L_0x009d
            L_0x00b6:
                r11 = 13
                boolean r25 = r0.decodeBooleanElement(r1, r11)
                r14 = r14 | 8192(0x2000, float:1.14794E-41)
                goto L_0x00b4
            L_0x00bf:
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                r10 = 12
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r10, r11, r5)
                r14 = r14 | 4096(0x1000, float:5.74E-42)
            L_0x00c9:
                r10 = 6
                goto L_0x00b4
            L_0x00cb:
                r10 = 12
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r6, r11, r2)
                r14 = r14 | 2048(0x800, float:2.87E-42)
                goto L_0x00c9
            L_0x00d6:
                r10 = 12
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r7, r11, r4)
                r14 = r14 | 1024(0x400, float:1.435E-42)
                goto L_0x00c9
            L_0x00e1:
                r10 = 12
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r8, r11, r3)
                r14 = r14 | 512(0x200, float:7.175E-43)
                goto L_0x00c9
            L_0x00ec:
                r10 = 12
                com.appsamurai.storyly.data.c$a r11 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r13, r11, r15)
                r14 = r14 | 256(0x100, float:3.59E-43)
                goto L_0x00c9
            L_0x00f7:
                r10 = 12
                boolean r23 = r0.decodeBooleanElement(r1, r9)
                r14 = r14 | 128(0x80, float:1.794E-43)
                goto L_0x00c9
            L_0x0100:
                r11 = r10
                r10 = 12
                float r24 = r0.decodeFloatElement(r1, r11)
                r14 = r14 | 64
                r10 = r11
                goto L_0x00b4
            L_0x010b:
                r11 = r10
                r10 = 12
                kotlinx.serialization.internal.StringSerializer r6 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r7 = 5
                java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r7, r6, r12)
                r14 = r14 | 32
                r10 = r11
                r6 = 11
                r11 = r7
                r7 = 10
                goto L_0x009d
            L_0x011f:
                r11 = r10
                r7 = 5
                r10 = 12
                kotlinx.serialization.internal.StringSerializer r6 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                r7 = r19
                r8 = 4
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r8, r6, r7)
                r14 = r14 | 16
                r19 = r6
                r10 = r11
                r6 = 11
                r7 = 10
                r8 = 9
                goto L_0x00b4
            L_0x0139:
                r11 = r10
                r7 = r19
                r8 = 4
                r10 = 12
                kotlinx.serialization.internal.LongSerializer r6 = kotlinx.serialization.internal.LongSerializer.INSTANCE
                r8 = r20
                r9 = 3
                java.lang.Object r20 = r0.decodeNullableSerializableElement(r1, r9, r6, r8)
                r14 = r14 | 8
            L_0x014a:
                r10 = r11
                r6 = 11
                r7 = 10
                r8 = 9
                r9 = 7
                goto L_0x00b4
            L_0x0154:
                r11 = r10
                r7 = r19
                r8 = r20
                r6 = 2
                r9 = 3
                r10 = 12
                long r27 = r0.decodeLongElement(r1, r6)
                r14 = r14 | 4
                goto L_0x014a
            L_0x0164:
                r11 = r10
                r7 = r19
                r8 = r20
                r6 = 1
                r9 = 3
                r10 = 12
                java.lang.String r22 = r0.decodeStringElement(r1, r6)
                r14 = r14 | 2
                goto L_0x014a
            L_0x0174:
                r11 = r10
                r7 = r19
                r8 = r20
                r6 = 0
                r9 = 3
                r10 = 12
                java.lang.String r21 = r0.decodeStringElement(r1, r6)
                r14 = r14 | 1
                goto L_0x014a
            L_0x0184:
                r11 = r10
                r7 = r19
                r8 = r20
                r6 = 0
                r9 = 3
                r10 = 12
                r29 = r6
                goto L_0x014a
            L_0x0190:
                r7 = r19
                r8 = r20
                r6 = r2
                r11 = r12
                r31 = r14
                r13 = r15
                r32 = r21
                r33 = r22
                r40 = r23
                r39 = r24
                r46 = r25
                r47 = r26
                r34 = r27
                r14 = r7
                r7 = r4
                r4 = r8
                r8 = r3
            L_0x01ab:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.r r0 = new com.appsamurai.storyly.data.r
                r30 = r0
                r36 = r4
                java.lang.Long r36 = (java.lang.Long) r36
                r37 = r14
                java.lang.String r37 = (java.lang.String) r37
                r38 = r11
                java.lang.String r38 = (java.lang.String) r38
                r41 = r13
                com.appsamurai.storyly.data.c r41 = (com.appsamurai.storyly.data.c) r41
                r42 = r8
                com.appsamurai.storyly.data.c r42 = (com.appsamurai.storyly.data.c) r42
                r43 = r7
                com.appsamurai.storyly.data.c r43 = (com.appsamurai.storyly.data.c) r43
                r44 = r6
                com.appsamurai.storyly.data.c r44 = (com.appsamurai.storyly.data.c) r44
                r45 = r5
                com.appsamurai.storyly.data.c r45 = (com.appsamurai.storyly.data.c) r45
                r48 = 0
                r30.<init>(r31, r32, r33, r34, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.r.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4185b;
        }

        public void serialize(Encoder encoder, Object obj) {
            Long l2;
            r rVar = (r) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(rVar, "value");
            SerialDescriptor serialDescriptor = f4185b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(rVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(rVar, beginStructure, serialDescriptor);
            beginStructure.encodeStringElement(serialDescriptor, 0, rVar.f4169a);
            beginStructure.encodeStringElement(serialDescriptor, 1, rVar.f4170b);
            beginStructure.encodeLongElement(serialDescriptor, 2, rVar.f4171c);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || (l2 = rVar.f4172d) == null || l2.longValue() != 0) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, LongSerializer.INSTANCE, rVar.f4172d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || rVar.f4173e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, rVar.f4173e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || rVar.f4174f != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, rVar.f4174f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || !Intrinsics.areEqual((Object) Float.valueOf(rVar.f4175g), (Object) Float.valueOf(0.0f))) {
                beginStructure.encodeFloatElement(serialDescriptor, 6, rVar.f4175g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || !rVar.f4176h) {
                beginStructure.encodeBooleanElement(serialDescriptor, 7, rVar.f4176h);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 8) || rVar.f4177i != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 8, c.f3622b, rVar.f4177i);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 9) || rVar.f4178j != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 9, c.f3622b, rVar.f4178j);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 10) || rVar.f4179k != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 10, c.f3622b, rVar.f4179k);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 11) || rVar.f4180l != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 11, c.f3622b, rVar.f4180l);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 12) || rVar.f4181m != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 12, c.f3622b, rVar.f4181m);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 13) || !rVar.f4182n) {
                beginStructure.encodeBooleanElement(serialDescriptor, 13, rVar.f4182n);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 14) || rVar.f4183o) {
                beginStructure.encodeBooleanElement(serialDescriptor, 14, rVar.f4183o);
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
    public /* synthetic */ r(int i3, @Required @SerialName("title") String str, @Required @SerialName("theme") String str2, @Required @SerialName("end") long j2, @SerialName("n_ts") Long l2, @SerialName("n_message") String str3, @SerialName("outlink") String str4, @SerialName("sdk_scale") float f2, @SerialName("has_title") boolean z2, @SerialName("cd_text_color") c cVar, @SerialName("bg_color") c cVar2, @SerialName("text_color") c cVar3, @SerialName("toast_bg_color") c cVar4, @SerialName("cd_border_color") c cVar5, @SerialName("is_bold") boolean z3, @SerialName("is_italic") boolean z4, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        long j3;
        int i4 = i3;
        if (7 != (i4 & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 7, a.f4184a.getDescriptor());
        }
        this.f4169a = str;
        this.f4170b = str2;
        this.f4171c = j2;
        if ((i4 & 8) == 0) {
            j3 = 0L;
        } else {
            j3 = l2;
        }
        this.f4172d = j3;
        if ((i4 & 16) == 0) {
            this.f4173e = null;
        } else {
            this.f4173e = str3;
        }
        if ((i4 & 32) == 0) {
            this.f4174f = null;
        } else {
            this.f4174f = str4;
        }
        this.f4175g = (i4 & 64) == 0 ? 0.0f : f2;
        if ((i4 & 128) == 0) {
            this.f4176h = true;
        } else {
            this.f4176h = z2;
        }
        if ((i4 & 256) == 0) {
            this.f4177i = null;
        } else {
            this.f4177i = cVar;
        }
        if ((i4 & 512) == 0) {
            this.f4178j = null;
        } else {
            this.f4178j = cVar2;
        }
        if ((i4 & 1024) == 0) {
            this.f4179k = null;
        } else {
            this.f4179k = cVar3;
        }
        if ((i4 & 2048) == 0) {
            this.f4180l = null;
        } else {
            this.f4180l = cVar4;
        }
        if ((i4 & 4096) == 0) {
            this.f4181m = null;
        } else {
            this.f4181m = cVar5;
        }
        if ((i4 & 8192) == 0) {
            this.f4182n = true;
        } else {
            this.f4182n = z3;
        }
        this.f4183o = (i4 & 16384) == 0 ? false : z4;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryComponent(b0Var.f3614i, StoryComponentType.Countdown);
    }

    @NotNull
    public final c b() {
        c cVar = this.f4177i;
        return cVar == null ? Intrinsics.areEqual((Object) this.f4170b, (Object) "Dark") ? new c(-1) : com.appsamurai.storyly.config.styling.a.COLOR_262626.b() : cVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return Intrinsics.areEqual((Object) this.f4169a, (Object) rVar.f4169a) && Intrinsics.areEqual((Object) this.f4170b, (Object) rVar.f4170b) && this.f4171c == rVar.f4171c && Intrinsics.areEqual((Object) this.f4172d, (Object) rVar.f4172d) && Intrinsics.areEqual((Object) this.f4173e, (Object) rVar.f4173e) && Intrinsics.areEqual((Object) this.f4174f, (Object) rVar.f4174f) && Intrinsics.areEqual((Object) Float.valueOf(this.f4175g), (Object) Float.valueOf(rVar.f4175g)) && this.f4176h == rVar.f4176h && Intrinsics.areEqual((Object) this.f4177i, (Object) rVar.f4177i) && Intrinsics.areEqual((Object) this.f4178j, (Object) rVar.f4178j) && Intrinsics.areEqual((Object) this.f4179k, (Object) rVar.f4179k) && Intrinsics.areEqual((Object) this.f4180l, (Object) rVar.f4180l) && Intrinsics.areEqual((Object) this.f4181m, (Object) rVar.f4181m) && this.f4182n == rVar.f4182n && this.f4183o == rVar.f4183o;
    }

    public int hashCode() {
        int D2 = androidx.compose.animation.core.a.D(this.f4171c, androidx.compose.animation.core.a.i(this.f4170b, this.f4169a.hashCode() * 31, 31), 31);
        Long l2 = this.f4172d;
        int i3 = 0;
        int hashCode = (D2 + (l2 == null ? 0 : l2.hashCode())) * 31;
        String str = this.f4173e;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f4174f;
        int c3 = android.support.v4.media.session.a.c(this.f4175g, (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31, 31);
        boolean z2 = this.f4176h;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i4 = (c3 + (z2 ? 1 : 0)) * 31;
        c cVar = this.f4177i;
        int hashCode3 = (i4 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f4178j;
        int hashCode4 = (hashCode3 + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f4179k;
        int hashCode5 = (hashCode4 + (cVar3 == null ? 0 : Integer.hashCode(cVar3.f3624a))) * 31;
        c cVar4 = this.f4180l;
        int hashCode6 = (hashCode5 + (cVar4 == null ? 0 : Integer.hashCode(cVar4.f3624a))) * 31;
        c cVar5 = this.f4181m;
        if (cVar5 != null) {
            i3 = Integer.hashCode(cVar5.f3624a);
        }
        int i5 = (hashCode6 + i3) * 31;
        boolean z4 = this.f4182n;
        if (z4) {
            z4 = true;
        }
        int i6 = (i5 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.f4183o;
        if (!z5) {
            z3 = z5;
        }
        return i6 + (z3 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyCountDownLayer(title=");
        sb.append(this.f4169a);
        sb.append(", theme=");
        sb.append(this.f4170b);
        sb.append(", end=");
        sb.append(this.f4171c);
        sb.append(", notificationEnd=");
        sb.append(this.f4172d);
        sb.append(", notificationMessage=");
        sb.append(this.f4173e);
        sb.append(", outlink=");
        sb.append(this.f4174f);
        sb.append(", sdkScale=");
        sb.append(this.f4175g);
        sb.append(", hasTitle=");
        sb.append(this.f4176h);
        sb.append(", countDownTextFontColor=");
        sb.append(this.f4177i);
        sb.append(", backgroundColor=");
        sb.append(this.f4178j);
        sb.append(", textColor=");
        sb.append(this.f4179k);
        sb.append(", toastBackgroundColor=");
        sb.append(this.f4180l);
        sb.append(", countDownBorderColor=");
        sb.append(this.f4181m);
        sb.append(", isBold=");
        sb.append(this.f4182n);
        sb.append(", isItalic=");
        return i.c(sb, this.f4183o, ')');
    }

    public r(@NotNull String str, @NotNull String str2, long j2, @Nullable Long l2, @Nullable String str3, @Nullable String str4, float f2, boolean z2, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, @Nullable c cVar4, @Nullable c cVar5, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(str2, "theme");
        this.f4169a = str;
        this.f4170b = str2;
        this.f4171c = j2;
        this.f4172d = l2;
        this.f4173e = str3;
        this.f4174f = str4;
        this.f4175g = f2;
        this.f4176h = z2;
        this.f4177i = cVar;
        this.f4178j = cVar2;
        this.f4179k = cVar3;
        this.f4180l = cVar4;
        this.f4181m = cVar5;
        this.f4182n = z3;
        this.f4183o = z4;
    }

    @NotNull
    public final c a() {
        return Intrinsics.areEqual((Object) this.f4170b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_141414.b() : new c(-1);
    }
}
