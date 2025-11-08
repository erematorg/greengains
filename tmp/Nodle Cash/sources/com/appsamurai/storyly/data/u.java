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
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class u {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f4213a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final c f4214b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final c f4215c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final Long f4216d;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<u> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4217a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4218b;

        static {
            a aVar = new a();
            f4217a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyGroupBadgeStyle", aVar, 4);
            pluginGeneratedSerialDescriptor.addElement("t", true);
            pluginGeneratedSerialDescriptor.addElement("t_c", true);
            pluginGeneratedSerialDescriptor.addElement("bg_c", true);
            pluginGeneratedSerialDescriptor.addElement("e_t", true);
            f4218b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE);
            c.a aVar = c.f3622b;
            return new KSerializer[]{nullable, BuiltinSerializersKt.getNullable(aVar), BuiltinSerializersKt.getNullable(aVar), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE)};
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r13) {
            /*
                r12 = this;
                java.lang.String r12 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r12)
                kotlinx.serialization.descriptors.SerialDescriptor r12 = f4218b
                kotlinx.serialization.encoding.CompositeDecoder r13 = r13.beginStructure(r12)
                boolean r0 = r13.decodeSequentially()
                r1 = 3
                r2 = 2
                r3 = 1
                r4 = 0
                r5 = 0
                if (r0 == 0) goto L_0x0030
                kotlinx.serialization.internal.StringSerializer r0 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r0 = r13.decodeNullableSerializableElement(r12, r4, r0, r5)
                com.appsamurai.storyly.data.c$a r4 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r3 = r13.decodeNullableSerializableElement(r12, r3, r4, r5)
                java.lang.Object r2 = r13.decodeNullableSerializableElement(r12, r2, r4, r5)
                kotlinx.serialization.internal.LongSerializer r4 = kotlinx.serialization.internal.LongSerializer.INSTANCE
                java.lang.Object r1 = r13.decodeNullableSerializableElement(r12, r1, r4, r5)
                r4 = 15
                r5 = r4
                goto L_0x0077
            L_0x0030:
                r9 = r3
                r0 = r4
                r6 = r5
                r7 = r6
                r8 = r7
            L_0x0035:
                if (r9 == 0) goto L_0x0072
                int r10 = r13.decodeElementIndex(r12)
                r11 = -1
                if (r10 == r11) goto L_0x0070
                if (r10 == 0) goto L_0x0067
                if (r10 == r3) goto L_0x005e
                if (r10 == r2) goto L_0x0055
                if (r10 != r1) goto L_0x004f
                kotlinx.serialization.internal.LongSerializer r10 = kotlinx.serialization.internal.LongSerializer.INSTANCE
                java.lang.Object r5 = r13.decodeNullableSerializableElement(r12, r1, r10, r5)
                r0 = r0 | 8
                goto L_0x0035
            L_0x004f:
                kotlinx.serialization.UnknownFieldException r12 = new kotlinx.serialization.UnknownFieldException
                r12.<init>((int) r10)
                throw r12
            L_0x0055:
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r6 = r13.decodeNullableSerializableElement(r12, r2, r10, r6)
                r0 = r0 | 4
                goto L_0x0035
            L_0x005e:
                com.appsamurai.storyly.data.c$a r10 = com.appsamurai.storyly.data.c.f3622b
                java.lang.Object r7 = r13.decodeNullableSerializableElement(r12, r3, r10, r7)
                r0 = r0 | 2
                goto L_0x0035
            L_0x0067:
                kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
                java.lang.Object r8 = r13.decodeNullableSerializableElement(r12, r4, r10, r8)
                r0 = r0 | 1
                goto L_0x0035
            L_0x0070:
                r9 = r4
                goto L_0x0035
            L_0x0072:
                r1 = r5
                r2 = r6
                r3 = r7
                r5 = r0
                r0 = r8
            L_0x0077:
                r13.endStructure(r12)
                com.appsamurai.storyly.data.u r12 = new com.appsamurai.storyly.data.u
                r6 = r0
                java.lang.String r6 = (java.lang.String) r6
                r7 = r3
                com.appsamurai.storyly.data.c r7 = (com.appsamurai.storyly.data.c) r7
                r8 = r2
                com.appsamurai.storyly.data.c r8 = (com.appsamurai.storyly.data.c) r8
                r9 = r1
                java.lang.Long r9 = (java.lang.Long) r9
                r4 = r12
                r4.<init>((int) r5, (java.lang.String) r6, (com.appsamurai.storyly.data.c) r7, (com.appsamurai.storyly.data.c) r8, (java.lang.Long) r9)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.u.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4218b;
        }

        public void serialize(Encoder encoder, Object obj) {
            u uVar = (u) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(uVar, "value");
            SerialDescriptor serialDescriptor = f4218b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(uVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || uVar.f4213a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, uVar.f4213a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || uVar.f4214b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, c.f3622b, uVar.f4214b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || uVar.f4215c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, c.f3622b, uVar.f4215c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || uVar.f4216d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, LongSerializer.INSTANCE, uVar.f4216d);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public u() {
        this((String) null, (c) null, (c) null, (Long) null, 15);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return Intrinsics.areEqual((Object) this.f4213a, (Object) uVar.f4213a) && Intrinsics.areEqual((Object) this.f4214b, (Object) uVar.f4214b) && Intrinsics.areEqual((Object) this.f4215c, (Object) uVar.f4215c) && Intrinsics.areEqual((Object) this.f4216d, (Object) uVar.f4216d);
    }

    public int hashCode() {
        String str = this.f4213a;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        c cVar = this.f4214b;
        int hashCode2 = (hashCode + (cVar == null ? 0 : Integer.hashCode(cVar.f3624a))) * 31;
        c cVar2 = this.f4215c;
        int hashCode3 = (hashCode2 + (cVar2 == null ? 0 : Integer.hashCode(cVar2.f3624a))) * 31;
        Long l2 = this.f4216d;
        if (l2 != null) {
            i3 = l2.hashCode();
        }
        return hashCode3 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyGroupBadgeStyle(text=" + this.f4213a + ", textColor=" + this.f4214b + ", backgroundColor=" + this.f4215c + ", endTime=" + this.f4216d + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ u(int i3, @SerialName("t") String str, @SerialName("t_c") c cVar, @SerialName("bg_c") c cVar2, @SerialName("e_t") Long l2) {
        if ((i3 & 1) == 0) {
            this.f4213a = null;
        } else {
            this.f4213a = str;
        }
        if ((i3 & 2) == 0) {
            this.f4214b = null;
        } else {
            this.f4214b = cVar;
        }
        if ((i3 & 4) == 0) {
            this.f4215c = null;
        } else {
            this.f4215c = cVar2;
        }
        if ((i3 & 8) == 0) {
            this.f4216d = null;
        } else {
            this.f4216d = l2;
        }
    }

    public u(@Nullable String str, @Nullable c cVar, @Nullable c cVar2, @Nullable Long l2) {
        this.f4213a = str;
        this.f4214b = cVar;
        this.f4215c = cVar2;
        this.f4216d = l2;
    }

    public /* synthetic */ u(String str, c cVar, c cVar2, Long l2, int i3) {
        this((String) null, (c) null, (c) null, (Long) null);
    }
}
