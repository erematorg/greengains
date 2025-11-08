package com.appsamurai.storyly.data;

import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;

@Serializable
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final int f3591a;

    /* renamed from: b  reason: collision with root package name */
    public final int f3592b;

    /* renamed from: c  reason: collision with root package name */
    public final int f3593c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final List<b0> f3594d;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    /* renamed from: com.appsamurai.storyly.data.a$a  reason: collision with other inner class name */
    public static final class C0007a implements GeneratedSerializer<a> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final C0007a f3595a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3596b;

        static {
            C0007a aVar = new C0007a();
            f3595a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.AdData", aVar, 4);
            pluginGeneratedSerialDescriptor.addElement("ad_first", false);
            pluginGeneratedSerialDescriptor.addElement("ad_frequency", false);
            pluginGeneratedSerialDescriptor.addElement("ad_cap", true);
            pluginGeneratedSerialDescriptor.addElement("ad_template", false);
            f3596b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            ArrayListSerializer arrayListSerializer = new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o));
            IntSerializer intSerializer = IntSerializer.INSTANCE;
            return new KSerializer[]{intSerializer, intSerializer, intSerializer, arrayListSerializer};
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r19) {
            /*
                r18 = this;
                r0 = r19
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f3596b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                r7 = 0
                if (r2 == 0) goto L_0x003b
                int r2 = r0.decodeIntElement(r1, r6)
                int r5 = r0.decodeIntElement(r1, r5)
                int r4 = r0.decodeIntElement(r1, r4)
                kotlinx.serialization.internal.ArrayListSerializer r6 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.b0$a r8 = com.appsamurai.storyly.data.b0.f3605o
                kotlinx.serialization.KSerializer r8 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r8)
                r6.<init>(r8)
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r3, r6, r7)
                r6 = 15
                r14 = r2
                r16 = r4
                r15 = r5
                r13 = r6
                goto L_0x0087
            L_0x003b:
                r11 = r5
                r2 = r6
                r8 = r2
                r9 = r8
                r10 = r7
                r7 = r9
            L_0x0041:
                if (r11 == 0) goto L_0x0081
                int r12 = r0.decodeElementIndex(r1)
                r13 = -1
                if (r12 == r13) goto L_0x007f
                if (r12 == 0) goto L_0x0078
                if (r12 == r5) goto L_0x0071
                if (r12 == r4) goto L_0x006a
                if (r12 != r3) goto L_0x0064
                kotlinx.serialization.internal.ArrayListSerializer r12 = new kotlinx.serialization.internal.ArrayListSerializer
                com.appsamurai.storyly.data.b0$a r13 = com.appsamurai.storyly.data.b0.f3605o
                kotlinx.serialization.KSerializer r13 = kotlinx.serialization.builtins.BuiltinSerializersKt.getNullable(r13)
                r12.<init>(r13)
                java.lang.Object r10 = r0.decodeSerializableElement(r1, r3, r12, r10)
                r9 = r9 | 8
                goto L_0x0041
            L_0x0064:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r12)
                throw r0
            L_0x006a:
                int r7 = r0.decodeIntElement(r1, r4)
                r9 = r9 | 4
                goto L_0x0041
            L_0x0071:
                int r8 = r0.decodeIntElement(r1, r5)
                r9 = r9 | 2
                goto L_0x0041
            L_0x0078:
                int r2 = r0.decodeIntElement(r1, r6)
                r9 = r9 | 1
                goto L_0x0041
            L_0x007f:
                r11 = r6
                goto L_0x0041
            L_0x0081:
                r14 = r2
                r16 = r7
                r15 = r8
                r13 = r9
                r3 = r10
            L_0x0087:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.a r0 = new com.appsamurai.storyly.data.a
                r17 = r3
                java.util.List r17 = (java.util.List) r17
                r12 = r0
                r12.<init>(r13, r14, r15, r16, r17)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.a.C0007a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3596b;
        }

        public void serialize(Encoder encoder, Object obj) {
            a aVar = (a) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(aVar, "value");
            SerialDescriptor serialDescriptor = f3596b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(aVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeIntElement(serialDescriptor, 0, aVar.f3591a);
            beginStructure.encodeIntElement(serialDescriptor, 1, aVar.f3592b);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || aVar.f3593c != Integer.MAX_VALUE) {
                beginStructure.encodeIntElement(serialDescriptor, 2, aVar.f3593c);
            }
            beginStructure.encodeSerializableElement(serialDescriptor, 3, new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o)), aVar.f3594d);
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ a(int i3, @Required @SerialName("ad_first") int i4, @Required @SerialName("ad_frequency") int i5, @SerialName("ad_cap") int i6, @Required @SerialName("ad_template") List list) {
        if (11 != (i3 & 11)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 11, C0007a.f3595a.getDescriptor());
        }
        this.f3591a = i4;
        this.f3592b = i5;
        if ((i3 & 4) == 0) {
            this.f3593c = Integer.MAX_VALUE;
        } else {
            this.f3593c = i6;
        }
        this.f3594d = list;
    }
}
