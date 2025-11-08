package com.appsamurai.storyly.data;

import androidx.camera.core.impl.i;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryPromoCodeComponent;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class j0 extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f3793a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f3794b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final Float f3795c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final c f3796d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final c f3797e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public c f3798f;

    /* renamed from: g  reason: collision with root package name */
    public final boolean f3799g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f3800h;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<j0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3801a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3802b;

        static {
            a aVar = new a();
            f3801a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyPromoCodeLayer", aVar, 8);
            pluginGeneratedSerialDescriptor.addElement("text", false);
            pluginGeneratedSerialDescriptor.addElement("theme", false);
            pluginGeneratedSerialDescriptor.addElement("l_h", true);
            pluginGeneratedSerialDescriptor.addElement("b_color", true);
            pluginGeneratedSerialDescriptor.addElement("t_color", true);
            pluginGeneratedSerialDescriptor.addElement("border_color", true);
            pluginGeneratedSerialDescriptor.addElement("is_bold", true);
            pluginGeneratedSerialDescriptor.addElement("is_italic", true);
            f3802b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE);
            c.a aVar = c.f3622b;
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable3 = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable4 = BuiltinSerializersKt.getNullable(aVar);
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            BooleanSerializer booleanSerializer = BooleanSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, stringSerializer, nullable, nullable2, nullable3, nullable4, booleanSerializer, booleanSerializer};
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ae, code lost:
            r3 = 7;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r31) {
            /*
                r30 = this;
                r0 = r31
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3802b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 7
                r4 = 6
                r5 = 5
                r6 = 3
                r7 = 4
                r8 = 2
                r9 = 1
                r10 = 0
                r11 = 0
                if (r2 == 0) goto L_0x004e
                java.lang.String r2 = r0.decodeStringElement(r1, r10)
                java.lang.String r9 = r0.decodeStringElement(r1, r9)
                kotlinx.serialization.internal.FloatSerializer r10 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r10, r11)
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r10, r11)
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r10, r11)
                java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r10, r11)
                boolean r4 = r0.decodeBooleanElement(r1, r4)
                boolean r3 = r0.decodeBooleanElement(r1, r3)
                r10 = 255(0xff, float:3.57E-43)
                r21 = r2
                r28 = r3
                r27 = r4
                r22 = r9
                r20 = r10
                goto L_0x00c3
            L_0x004e:
                r18 = r9
                r2 = r10
                r12 = r2
                r13 = r11
                r14 = r13
                r15 = r14
                r16 = r15
                r17 = r16
                r10 = r17
                r11 = r12
            L_0x005c:
                if (r18 == 0) goto L_0x00b5
                int r9 = r0.decodeElementIndex(r1)
                switch(r9) {
                    case -1: goto L_0x00b0;
                    case 0: goto L_0x00a6;
                    case 1: goto L_0x009e;
                    case 2: goto L_0x0095;
                    case 3: goto L_0x008c;
                    case 4: goto L_0x0083;
                    case 5: goto L_0x007a;
                    case 6: goto L_0x0073;
                    case 7: goto L_0x006b;
                    default: goto L_0x0065;
                }
            L_0x0065:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r9)
                throw r0
            L_0x006b:
                boolean r2 = r0.decodeBooleanElement(r1, r3)
                r12 = r12 | 128(0x80, float:1.794E-43)
            L_0x0071:
                r9 = 1
                goto L_0x005c
            L_0x0073:
                boolean r11 = r0.decodeBooleanElement(r1, r4)
                r12 = r12 | 64
                goto L_0x0071
            L_0x007a:
                com.appsamurai.storyly.data.c$a r9 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r5, r9, r13)
                r12 = r12 | 32
                goto L_0x0071
            L_0x0083:
                com.appsamurai.storyly.data.c$a r9 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r7, r9, r14)
                r12 = r12 | 16
                goto L_0x0071
            L_0x008c:
                com.appsamurai.storyly.data.c$a r9 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r15 = r0.decodeNullableSerializableElement(r1, r6, r9, r15)
                r12 = r12 | 8
                goto L_0x0071
            L_0x0095:
                kotlinx.serialization.internal.FloatSerializer r9 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r8, r9, r10)
                r12 = r12 | 4
                goto L_0x0071
            L_0x009e:
                r9 = 1
                java.lang.String r17 = r0.decodeStringElement(r1, r9)
                r12 = r12 | 2
                goto L_0x005c
            L_0x00a6:
                r3 = 0
                r9 = 1
                java.lang.String r16 = r0.decodeStringElement(r1, r3)
                r12 = r12 | 1
            L_0x00ae:
                r3 = 7
                goto L_0x005c
            L_0x00b0:
                r3 = 0
                r9 = 1
                r18 = r3
                goto L_0x00ae
            L_0x00b5:
                r28 = r2
                r8 = r10
                r27 = r11
                r20 = r12
                r5 = r13
                r7 = r14
                r6 = r15
                r21 = r16
                r22 = r17
            L_0x00c3:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.j0 r0 = new com.appsamurai.storyly.data.j0
                r23 = r8
                java.lang.Float r23 = (java.lang.Float) r23
                r24 = r6
                com.appsamurai.storyly.data.c r24 = (com.appsamurai.storyly.data.c) r24
                r25 = r7
                com.appsamurai.storyly.data.c r25 = (com.appsamurai.storyly.data.c) r25
                r26 = r5
                com.appsamurai.storyly.data.c r26 = (com.appsamurai.storyly.data.c) r26
                r29 = 0
                r19 = r0
                r19.<init>(r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.j0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3802b;
        }

        public void serialize(Encoder encoder, Object obj) {
            j0 j0Var = (j0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(j0Var, "value");
            SerialDescriptor serialDescriptor = f3802b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(j0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(j0Var, beginStructure, serialDescriptor);
            beginStructure.encodeStringElement(serialDescriptor, 0, j0Var.f3793a);
            beginStructure.encodeStringElement(serialDescriptor, 1, j0Var.f3794b);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || j0Var.f3795c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, FloatSerializer.INSTANCE, j0Var.f3795c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || j0Var.f3796d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, c.f3622b, j0Var.f3796d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || j0Var.f3797e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, c.f3622b, j0Var.f3797e);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 5) || j0Var.f3798f != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 5, c.f3622b, j0Var.f3798f);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 6) || !j0Var.f3799g) {
                beginStructure.encodeBooleanElement(serialDescriptor, 6, j0Var.f3799g);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 7) || j0Var.f3800h) {
                beginStructure.encodeBooleanElement(serialDescriptor, 7, j0Var.f3800h);
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
    public /* synthetic */ j0(int i3, @Required @SerialName("text") String str, @Required @SerialName("theme") String str2, @SerialName("l_h") Float f2, @SerialName("b_color") c cVar, @SerialName("t_color") c cVar2, @SerialName("border_color") c cVar3, @SerialName("is_bold") boolean z2, @SerialName("is_italic") boolean z3, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        if (3 != (i3 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 3, a.f3801a.getDescriptor());
        }
        this.f3793a = str;
        this.f3794b = str2;
        if ((i3 & 4) == 0) {
            this.f3795c = null;
        } else {
            this.f3795c = f2;
        }
        if ((i3 & 8) == 0) {
            this.f3796d = null;
        } else {
            this.f3796d = cVar;
        }
        if ((i3 & 16) == 0) {
            this.f3797e = null;
        } else {
            this.f3797e = cVar2;
        }
        if ((i3 & 32) == 0) {
            this.f3798f = null;
        } else {
            this.f3798f = cVar3;
        }
        if ((i3 & 64) == 0) {
            this.f3799g = true;
        } else {
            this.f3799g = z2;
        }
        if ((i3 & 128) == 0) {
            this.f3800h = false;
        } else {
            this.f3800h = z3;
        }
    }

    public StoryComponent a(b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryPromoCodeComponent(b0Var.f3614i, this.f3793a);
    }

    @NotNull
    public final c b() {
        c cVar = this.f3798f;
        if (cVar != null) {
            return cVar;
        }
        return (Intrinsics.areEqual((Object) this.f3794b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_757575 : com.appsamurai.storyly.config.styling.a.COLOR_E0E0E0).b();
    }

    @NotNull
    public final c c() {
        c cVar = this.f3797e;
        return cVar == null ? Intrinsics.areEqual((Object) this.f3794b, (Object) "Dark") ? new c(-1) : com.appsamurai.storyly.config.styling.a.COLOR_212121.b() : cVar;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j0)) {
            return false;
        }
        j0 j0Var = (j0) obj;
        return Intrinsics.areEqual((Object) this.f3793a, (Object) j0Var.f3793a) && Intrinsics.areEqual((Object) this.f3794b, (Object) j0Var.f3794b) && Intrinsics.areEqual((Object) this.f3795c, (Object) j0Var.f3795c) && Intrinsics.areEqual((Object) this.f3796d, (Object) j0Var.f3796d) && Intrinsics.areEqual((Object) this.f3797e, (Object) j0Var.f3797e) && Intrinsics.areEqual((Object) this.f3798f, (Object) j0Var.f3798f) && this.f3799g == j0Var.f3799g && this.f3800h == j0Var.f3800h;
    }

    public int hashCode() {
        int i3 = androidx.compose.animation.core.a.i(this.f3794b, this.f3793a.hashCode() * 31, 31);
        Float f2 = this.f3795c;
        int i4 = 0;
        int hashCode = (i3 + (f2 == null ? 0 : f2.hashCode())) * 31;
        c cVar = this.f3796d;
        int hashCode2 = (hashCode + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f3797e;
        int hashCode3 = (hashCode2 + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        c cVar3 = this.f3798f;
        if (cVar3 != null) {
            i4 = Integer.hashCode(cVar3.f3624a);
        }
        int i5 = (hashCode3 + i4) * 31;
        boolean z2 = this.f3799g;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int i6 = (i5 + (z2 ? 1 : 0)) * 31;
        boolean z4 = this.f3800h;
        if (!z4) {
            z3 = z4;
        }
        return i6 + (z3 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyPromoCodeLayer(promoCode=");
        sb.append(this.f3793a);
        sb.append(", theme=");
        sb.append(this.f3794b);
        sb.append(", lineHeight=");
        sb.append(this.f3795c);
        sb.append(", backgroundColor=");
        sb.append(this.f3796d);
        sb.append(", textColor=");
        sb.append(this.f3797e);
        sb.append(", borderColor=");
        sb.append(this.f3798f);
        sb.append(", isBold=");
        sb.append(this.f3799g);
        sb.append(", isItalic=");
        return i.c(sb, this.f3800h, ')');
    }

    public j0(@NotNull String str, @NotNull String str2, @Nullable Float f2, @Nullable c cVar, @Nullable c cVar2, @Nullable c cVar3, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(str, "promoCode");
        Intrinsics.checkNotNullParameter(str2, "theme");
        this.f3793a = str;
        this.f3794b = str2;
        this.f3795c = f2;
        this.f3796d = cVar;
        this.f3797e = cVar2;
        this.f3798f = cVar3;
        this.f3799g = z2;
        this.f3800h = z3;
    }

    @NotNull
    public StoryComponent a(@NotNull b0 b0Var, int i3) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return new StoryPromoCodeComponent(b0Var.f3614i, this.f3793a);
    }

    @NotNull
    public final c a() {
        c cVar = this.f3796d;
        if (cVar == null) {
            return Intrinsics.areEqual((Object) this.f3794b, (Object) "Dark") ? com.appsamurai.storyly.config.styling.a.COLOR_212121.b() : new c(-1);
        }
        return cVar;
    }
}
