package com.appsamurai.storyly.data;

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
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class i {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Float f3746a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final Float f3747b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final Float f3748c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final Float f3749d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final Float f3750e;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<i> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3751a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3752b;

        static {
            a aVar = new a();
            f3751a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.KeyFrameScheme", aVar, 5);
            pluginGeneratedSerialDescriptor.addElement("x", true);
            pluginGeneratedSerialDescriptor.addElement("y", true);
            pluginGeneratedSerialDescriptor.addElement("r", true);
            pluginGeneratedSerialDescriptor.addElement("o", true);
            pluginGeneratedSerialDescriptor.addElement("s", true);
            f3752b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            FloatSerializer floatSerializer = FloatSerializer.INSTANCE;
            return new KSerializer[]{BuiltinSerializersKt.getNullable(floatSerializer), BuiltinSerializersKt.getNullable(floatSerializer), BuiltinSerializersKt.getNullable(floatSerializer), BuiltinSerializersKt.getNullable(floatSerializer), BuiltinSerializersKt.getNullable(floatSerializer)};
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r15) {
            /*
                r14 = this;
                java.lang.String r14 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r14)
                kotlinx.serialization.descriptors.SerialDescriptor r14 = f3752b
                kotlinx.serialization.encoding.CompositeDecoder r15 = r15.beginStructure(r14)
                boolean r0 = r15.decodeSequentially()
                r1 = 3
                r2 = 4
                r3 = 2
                r4 = 1
                r5 = 0
                r6 = 0
                if (r0 == 0) goto L_0x0033
                kotlinx.serialization.internal.FloatSerializer r0 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r5 = r15.decodeNullableSerializableElement(r14, r5, r0, r6)
                java.lang.Object r4 = r15.decodeNullableSerializableElement(r14, r4, r0, r6)
                java.lang.Object r3 = r15.decodeNullableSerializableElement(r14, r3, r0, r6)
                java.lang.Object r1 = r15.decodeNullableSerializableElement(r14, r1, r0, r6)
                java.lang.Object r0 = r15.decodeNullableSerializableElement(r14, r2, r0, r6)
                r2 = 31
                r8 = r3
                r3 = r2
                goto L_0x0086
            L_0x0033:
                r11 = r4
                r0 = r5
                r7 = r6
                r8 = r7
                r9 = r8
                r10 = r9
            L_0x0039:
                if (r11 == 0) goto L_0x0081
                int r12 = r15.decodeElementIndex(r14)
                r13 = -1
                if (r12 == r13) goto L_0x007f
                if (r12 == 0) goto L_0x0076
                if (r12 == r4) goto L_0x006d
                if (r12 == r3) goto L_0x0064
                if (r12 == r1) goto L_0x005b
                if (r12 != r2) goto L_0x0055
                kotlinx.serialization.internal.FloatSerializer r12 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r6 = r15.decodeNullableSerializableElement(r14, r2, r12, r6)
                r0 = r0 | 16
                goto L_0x0039
            L_0x0055:
                kotlinx.serialization.UnknownFieldException r14 = new kotlinx.serialization.UnknownFieldException
                r14.<init>((int) r12)
                throw r14
            L_0x005b:
                kotlinx.serialization.internal.FloatSerializer r12 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r7 = r15.decodeNullableSerializableElement(r14, r1, r12, r7)
                r0 = r0 | 8
                goto L_0x0039
            L_0x0064:
                kotlinx.serialization.internal.FloatSerializer r12 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r8 = r15.decodeNullableSerializableElement(r14, r3, r12, r8)
                r0 = r0 | 4
                goto L_0x0039
            L_0x006d:
                kotlinx.serialization.internal.FloatSerializer r12 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r9 = r15.decodeNullableSerializableElement(r14, r4, r12, r9)
                r0 = r0 | 2
                goto L_0x0039
            L_0x0076:
                kotlinx.serialization.internal.FloatSerializer r12 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                java.lang.Object r10 = r15.decodeNullableSerializableElement(r14, r5, r12, r10)
                r0 = r0 | 1
                goto L_0x0039
            L_0x007f:
                r11 = r5
                goto L_0x0039
            L_0x0081:
                r3 = r0
                r0 = r6
                r1 = r7
                r4 = r9
                r5 = r10
            L_0x0086:
                r15.endStructure(r14)
                com.appsamurai.storyly.data.i r14 = new com.appsamurai.storyly.data.i
                r15 = r5
                java.lang.Float r15 = (java.lang.Float) r15
                r5 = r4
                java.lang.Float r5 = (java.lang.Float) r5
                r6 = r8
                java.lang.Float r6 = (java.lang.Float) r6
                r7 = r1
                java.lang.Float r7 = (java.lang.Float) r7
                r8 = r0
                java.lang.Float r8 = (java.lang.Float) r8
                r2 = r14
                r4 = r15
                r2.<init>((int) r3, (java.lang.Float) r4, (java.lang.Float) r5, (java.lang.Float) r6, (java.lang.Float) r7, (java.lang.Float) r8)
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.i.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3752b;
        }

        public void serialize(Encoder encoder, Object obj) {
            i iVar = (i) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(iVar, "value");
            SerialDescriptor serialDescriptor = f3752b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(iVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || iVar.f3746a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, FloatSerializer.INSTANCE, iVar.f3746a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || iVar.f3747b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, FloatSerializer.INSTANCE, iVar.f3747b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || iVar.f3748c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, FloatSerializer.INSTANCE, iVar.f3748c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || iVar.f3749d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, FloatSerializer.INSTANCE, iVar.f3749d);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || iVar.f3750e != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 4, FloatSerializer.INSTANCE, iVar.f3750e);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public i() {
        this((Float) null, (Float) null, (Float) null, (Float) null, (Float) null, 31);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return Intrinsics.areEqual((Object) this.f3746a, (Object) iVar.f3746a) && Intrinsics.areEqual((Object) this.f3747b, (Object) iVar.f3747b) && Intrinsics.areEqual((Object) this.f3748c, (Object) iVar.f3748c) && Intrinsics.areEqual((Object) this.f3749d, (Object) iVar.f3749d) && Intrinsics.areEqual((Object) this.f3750e, (Object) iVar.f3750e);
    }

    public int hashCode() {
        Float f2 = this.f3746a;
        int i3 = 0;
        int hashCode = (f2 == null ? 0 : f2.hashCode()) * 31;
        Float f3 = this.f3747b;
        int hashCode2 = (hashCode + (f3 == null ? 0 : f3.hashCode())) * 31;
        Float f4 = this.f3748c;
        int hashCode3 = (hashCode2 + (f4 == null ? 0 : f4.hashCode())) * 31;
        Float f5 = this.f3749d;
        int hashCode4 = (hashCode3 + (f5 == null ? 0 : f5.hashCode())) * 31;
        Float f6 = this.f3750e;
        if (f6 != null) {
            i3 = f6.hashCode();
        }
        return hashCode4 + i3;
    }

    @NotNull
    public String toString() {
        return "KeyFrameScheme(x=" + this.f3746a + ", y=" + this.f3747b + ", rotation=" + this.f3748c + ", opacity=" + this.f3749d + ", scale=" + this.f3750e + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ i(int i3, @SerialName("x") Float f2, @SerialName("y") Float f3, @SerialName("r") Float f4, @SerialName("o") Float f5, @SerialName("s") Float f6) {
        if ((i3 & 1) == 0) {
            this.f3746a = null;
        } else {
            this.f3746a = f2;
        }
        if ((i3 & 2) == 0) {
            this.f3747b = null;
        } else {
            this.f3747b = f3;
        }
        if ((i3 & 4) == 0) {
            this.f3748c = null;
        } else {
            this.f3748c = f4;
        }
        if ((i3 & 8) == 0) {
            this.f3749d = null;
        } else {
            this.f3749d = f5;
        }
        if ((i3 & 16) == 0) {
            this.f3750e = null;
        } else {
            this.f3750e = f6;
        }
    }

    public i(@Nullable Float f2, @Nullable Float f3, @Nullable Float f4, @Nullable Float f5, @Nullable Float f6) {
        this.f3746a = f2;
        this.f3747b = f3;
        this.f3748c = f4;
        this.f3749d = f5;
        this.f3750e = f6;
    }

    public /* synthetic */ i(Float f2, Float f3, Float f4, Float f5, Float f6, int i3) {
        this((Float) null, (Float) null, (Float) null, (Float) null, (Float) null);
    }
}
