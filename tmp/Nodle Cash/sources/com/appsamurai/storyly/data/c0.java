package com.appsamurai.storyly.data;

import com.appsamurai.storyly.data.c;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class c0 extends a0 {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3625a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f3626b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final c f3627c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final c f3628d;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<c0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3629a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3630b;

        static {
            a aVar = new a();
            f3629a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyLinkCTALayer", aVar, 4);
            pluginGeneratedSerialDescriptor.addElement("text", false);
            pluginGeneratedSerialDescriptor.addElement("link", false);
            pluginGeneratedSerialDescriptor.addElement("text_color", true);
            pluginGeneratedSerialDescriptor.addElement("bg_color", true);
            f3630b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            c.a aVar = c.f3622b;
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(aVar);
            KSerializer<?> nullable2 = BuiltinSerializersKt.getNullable(aVar);
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, stringSerializer, nullable, nullable2};
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r20) {
            /*
                r19 = this;
                r0 = r20
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3630b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                r7 = 0
                if (r2 == 0) goto L_0x0030
                java.lang.String r2 = r0.decodeStringElement(r1, r6)
                java.lang.String r5 = r0.decodeStringElement(r1, r5)
                com.appsamurai.storyly.data.c$a r6 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r6, r7)
                java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r6, r7)
                r6 = 15
                r14 = r2
                r15 = r5
                r13 = r6
                goto L_0x0073
            L_0x0030:
                r11 = r5
                r2 = r6
                r8 = r7
                r9 = r8
                r10 = r9
            L_0x0035:
                if (r11 == 0) goto L_0x006e
                int r12 = r0.decodeElementIndex(r1)
                r13 = -1
                if (r12 == r13) goto L_0x006c
                if (r12 == 0) goto L_0x0065
                if (r12 == r5) goto L_0x005e
                if (r12 == r4) goto L_0x0055
                if (r12 != r3) goto L_0x004f
                com.appsamurai.storyly.data.c$a r12 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r3, r12, r7)
                r2 = r2 | 8
                goto L_0x0035
            L_0x004f:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r12)
                throw r0
            L_0x0055:
                com.appsamurai.storyly.data.c$a r12 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r4, r12, r8)
                r2 = r2 | 4
                goto L_0x0035
            L_0x005e:
                java.lang.String r10 = r0.decodeStringElement(r1, r5)
                r2 = r2 | 2
                goto L_0x0035
            L_0x0065:
                java.lang.String r9 = r0.decodeStringElement(r1, r6)
                r2 = r2 | 1
                goto L_0x0035
            L_0x006c:
                r11 = r6
                goto L_0x0035
            L_0x006e:
                r13 = r2
                r3 = r7
                r4 = r8
                r14 = r9
                r15 = r10
            L_0x0073:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.c0 r0 = new com.appsamurai.storyly.data.c0
                r16 = r4
                com.appsamurai.storyly.data.c r16 = (com.appsamurai.storyly.data.c) r16
                r17 = r3
                com.appsamurai.storyly.data.c r17 = (com.appsamurai.storyly.data.c) r17
                r18 = 0
                r12 = r0
                r12.<init>(r13, r14, r15, r16, r17, r18)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.c0.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3630b;
        }

        public void serialize(Encoder encoder, Object obj) {
            c0 c0Var = (c0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(c0Var, "value");
            SerialDescriptor serialDescriptor = f3630b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(c0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(c0Var, beginStructure, serialDescriptor);
            beginStructure.encodeStringElement(serialDescriptor, 0, c0Var.f3625a);
            beginStructure.encodeStringElement(serialDescriptor, 1, c0Var.f3626b);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || !Intrinsics.areEqual((Object) c0Var.f3627c, (Object) new c(-1))) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, c.f3622b, c0Var.f3627c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || !Intrinsics.areEqual((Object) c0Var.f3628d, (Object) new c(-1))) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, c.f3622b, c0Var.f3628d);
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
    public /* synthetic */ c0(int i3, @SerialName("text") String str, @SerialName("link") String str2, @SerialName("text_color") c cVar, @SerialName("bg_color") c cVar2, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        if (3 != (i3 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 3, a.f3629a.getDescriptor());
        }
        this.f3625a = str;
        this.f3626b = str2;
        if ((i3 & 4) == 0) {
            this.f3627c = new c(-1);
        } else {
            this.f3627c = cVar;
        }
        if ((i3 & 8) == 0) {
            this.f3628d = new c(-1);
        } else {
            this.f3628d = cVar2;
        }
    }

    public static c0 a(c0 c0Var, String str, String str2, c cVar, c cVar2, int i3) {
        c cVar3 = null;
        String str3 = (i3 & 1) != 0 ? c0Var.f3625a : null;
        String str4 = (i3 & 2) != 0 ? c0Var.f3626b : null;
        c cVar4 = (i3 & 4) != 0 ? c0Var.f3627c : null;
        if ((i3 & 8) != 0) {
            cVar3 = c0Var.f3628d;
        }
        c0Var.getClass();
        Intrinsics.checkNotNullParameter(str3, "text");
        Intrinsics.checkNotNullParameter(str4, "link");
        return new c0(str3, str4, cVar4, cVar3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c0)) {
            return false;
        }
        c0 c0Var = (c0) obj;
        return Intrinsics.areEqual((Object) this.f3625a, (Object) c0Var.f3625a) && Intrinsics.areEqual((Object) this.f3626b, (Object) c0Var.f3626b) && Intrinsics.areEqual((Object) this.f3627c, (Object) c0Var.f3627c) && Intrinsics.areEqual((Object) this.f3628d, (Object) c0Var.f3628d);
    }

    public int hashCode() {
        int i3 = androidx.compose.animation.core.a.i(this.f3626b, this.f3625a.hashCode() * 31, 31);
        c cVar = this.f3627c;
        int i4 = 0;
        int hashCode = (i3 + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f3628d;
        if (cVar2 != null) {
            i4 = Integer.hashCode(cVar2.f3624a);
        }
        return hashCode + i4;
    }

    @NotNull
    public String toString() {
        return "StorylyLinkCTALayer(text=" + this.f3625a + ", link=" + this.f3626b + ", textColor=" + this.f3627c + ", bgColor=" + this.f3628d + ')';
    }

    public c0(@NotNull String str, @NotNull String str2, @Nullable c cVar, @Nullable c cVar2) {
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(str2, "link");
        this.f3625a = str;
        this.f3626b = str2;
        this.f3627c = cVar;
        this.f3628d = cVar2;
    }
}
