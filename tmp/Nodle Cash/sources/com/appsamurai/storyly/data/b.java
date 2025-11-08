package com.appsamurai.storyly.data;

import com.appsamurai.storyly.data.i;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class b {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final List<i> f3599a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final List<i> f3600b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final List<Float> f3601c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final Long f3602d;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<b> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3603a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3604b;

        static {
            a aVar = new a();
            f3603a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.AnimationScheme", aVar, 4);
            pluginGeneratedSerialDescriptor.addElement("en", true);
            pluginGeneratedSerialDescriptor.addElement("ex", true);
            pluginGeneratedSerialDescriptor.addElement("ea", true);
            pluginGeneratedSerialDescriptor.addElement("du", true);
            f3604b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            i.a aVar = i.a.f3751a;
            return new KSerializer[]{BuiltinSerializersKt.getNullable(new ArrayListSerializer(aVar)), BuiltinSerializersKt.getNullable(new ArrayListSerializer(aVar)), BuiltinSerializersKt.getNullable(new ArrayListSerializer(FloatSerializer.INSTANCE)), BuiltinSerializersKt.getNullable(LongSerializer.INSTANCE)};
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r13) {
            /*
                r12 = this;
                java.lang.String r12 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r12)
                kotlinx.serialization.descriptors.SerialDescriptor r12 = f3604b
                kotlinx.serialization.encoding.CompositeDecoder r13 = r13.beginStructure(r12)
                boolean r0 = r13.decodeSequentially()
                r1 = 3
                r2 = 2
                r3 = 1
                r4 = 0
                r5 = 0
                if (r0 == 0) goto L_0x003f
                kotlinx.serialization.internal.ArrayListSerializer r0 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.i$a r6 = com.appsamurai.storyly.data.i.a.f3751a
                r0.<init>(r6)
                java.lang.Object r0 = r13.decodeNullableSerializableElement(r12, r4, r0, r5)
                kotlinx.serialization.internal.ArrayListSerializer r4 = new kotlinx.serialization.internal.ArrayListSerializer
                r4.<init>(r6)
                java.lang.Object r3 = r13.decodeNullableSerializableElement(r12, r3, r4, r5)
                kotlinx.serialization.internal.ArrayListSerializer r4 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.FloatSerializer r6 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                r4.<init>(r6)
                java.lang.Object r2 = r13.decodeNullableSerializableElement(r12, r2, r4, r5)
                kotlinx.serialization.internal.LongSerializer r4 = kotlinx.serialization.internal.LongSerializer.INSTANCE
                java.lang.Object r1 = r13.decodeNullableSerializableElement(r12, r1, r4, r5)
                r4 = 15
                r5 = r4
                goto L_0x0095
            L_0x003f:
                r9 = r3
                r0 = r4
                r6 = r5
                r7 = r6
                r8 = r7
            L_0x0044:
                if (r9 == 0) goto L_0x0090
                int r10 = r13.decodeElementIndex(r12)
                r11 = -1
                if (r10 == r11) goto L_0x008e
                if (r10 == 0) goto L_0x0080
                if (r10 == r3) goto L_0x0072
                if (r10 == r2) goto L_0x0064
                if (r10 != r1) goto L_0x005e
                kotlinx.serialization.internal.LongSerializer r10 = kotlinx.serialization.internal.LongSerializer.INSTANCE
                java.lang.Object r5 = r13.decodeNullableSerializableElement(r12, r1, r10, r5)
                r0 = r0 | 8
                goto L_0x0044
            L_0x005e:
                kotlinx.serialization.UnknownFieldException r12 = new kotlinx.serialization.UnknownFieldException
                r12.<init>((int) r10)
                throw r12
            L_0x0064:
                kotlinx.serialization.internal.ArrayListSerializer r10 = new kotlinx.serialization.internal.ArrayListSerializer
                kotlinx.serialization.internal.FloatSerializer r11 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
                r10.<init>(r11)
                java.lang.Object r6 = r13.decodeNullableSerializableElement(r12, r2, r10, r6)
                r0 = r0 | 4
                goto L_0x0044
            L_0x0072:
                kotlinx.serialization.internal.ArrayListSerializer r10 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.i$a r11 = com.appsamurai.storyly.data.i.a.f3751a
                r10.<init>(r11)
                java.lang.Object r7 = r13.decodeNullableSerializableElement(r12, r3, r10, r7)
                r0 = r0 | 2
                goto L_0x0044
            L_0x0080:
                kotlinx.serialization.internal.ArrayListSerializer r10 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.i$a r11 = com.appsamurai.storyly.data.i.a.f3751a
                r10.<init>(r11)
                java.lang.Object r8 = r13.decodeNullableSerializableElement(r12, r4, r10, r8)
                r0 = r0 | 1
                goto L_0x0044
            L_0x008e:
                r9 = r4
                goto L_0x0044
            L_0x0090:
                r1 = r5
                r2 = r6
                r3 = r7
                r5 = r0
                r0 = r8
            L_0x0095:
                r13.endStructure(r12)
                com.appsamurai.storyly.data.b r12 = new com.appsamurai.storyly.data.b
                r6 = r0
                java.util.List r6 = (java.util.List) r6
                r7 = r3
                java.util.List r7 = (java.util.List) r7
                r8 = r2
                java.util.List r8 = (java.util.List) r8
                r9 = r1
                java.lang.Long r9 = (java.lang.Long) r9
                r4 = r12
                r4.<init>((int) r5, (java.util.List) r6, (java.util.List) r7, (java.util.List) r8, (java.lang.Long) r9)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.b.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3604b;
        }

        public void serialize(Encoder encoder, Object obj) {
            b bVar = (b) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(bVar, "value");
            SerialDescriptor serialDescriptor = f3604b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(bVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || bVar.f3599a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(i.a.f3751a), bVar.f3599a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || bVar.f3600b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, new ArrayListSerializer(i.a.f3751a), bVar.f3600b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || bVar.f3601c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, new ArrayListSerializer(FloatSerializer.INSTANCE), bVar.f3601c);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 3) || bVar.f3602d != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 3, LongSerializer.INSTANCE, bVar.f3602d);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public b() {
        this((List) null, (List) null, (List) null, (Long) null, 15);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual((Object) this.f3599a, (Object) bVar.f3599a) && Intrinsics.areEqual((Object) this.f3600b, (Object) bVar.f3600b) && Intrinsics.areEqual((Object) this.f3601c, (Object) bVar.f3601c) && Intrinsics.areEqual((Object) this.f3602d, (Object) bVar.f3602d);
    }

    public int hashCode() {
        List<i> list = this.f3599a;
        int i3 = 0;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<i> list2 = this.f3600b;
        int hashCode2 = (hashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<Float> list3 = this.f3601c;
        int hashCode3 = (hashCode2 + (list3 == null ? 0 : list3.hashCode())) * 31;
        Long l2 = this.f3602d;
        if (l2 != null) {
            i3 = l2.hashCode();
        }
        return hashCode3 + i3;
    }

    @NotNull
    public String toString() {
        return "AnimationScheme(enterKeyFrames=" + this.f3599a + ", exitKeyFrames=" + this.f3600b + ", cubicBezier=" + this.f3601c + ", duration=" + this.f3602d + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ b(int i3, @SerialName("en") List list, @SerialName("ex") List list2, @SerialName("ea") List list3, @SerialName("du") Long l2) {
        if ((i3 & 1) == 0) {
            this.f3599a = null;
        } else {
            this.f3599a = list;
        }
        if ((i3 & 2) == 0) {
            this.f3600b = null;
        } else {
            this.f3600b = list2;
        }
        if ((i3 & 4) == 0) {
            this.f3601c = null;
        } else {
            this.f3601c = list3;
        }
        if ((i3 & 8) == 0) {
            this.f3602d = null;
        } else {
            this.f3602d = l2;
        }
    }

    public b(@Nullable List<i> list, @Nullable List<i> list2, @Nullable List<Float> list3, @Nullable Long l2) {
        this.f3599a = list;
        this.f3600b = list2;
        this.f3601c = list3;
        this.f3602d = l2;
    }

    public /* synthetic */ b(List list, List list2, List list3, Long l2, int i3) {
        this((List<i>) null, (List<i>) null, (List<Float>) null, (Long) null);
    }
}
