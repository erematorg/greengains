package com.appsamurai.storyly.data;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class n {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4052a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public String f4053b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public String f4054c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public k f4055d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f4056e;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<n> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4057a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4058b;

        static {
            a aVar = new a();
            f4057a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StoryCondition", aVar, 5);
            pluginGeneratedSerialDescriptor.addElement("g", false);
            pluginGeneratedSerialDescriptor.addElement("s", false);
            pluginGeneratedSerialDescriptor.addElement("i", false);
            pluginGeneratedSerialDescriptor.addElement("r", false);
            pluginGeneratedSerialDescriptor.addElement("isSatisfied", true);
            f4058b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, stringSerializer, stringSerializer, k.f3803c, BooleanSerializer.INSTANCE};
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder r22) {
            /*
                r21 = this;
                r0 = r22
                java.lang.String r1 = "decoder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                kotlinx.serialization.descriptors.SerialDescriptor r1 = f4058b
                kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
                boolean r2 = r0.decodeSequentially()
                r3 = 3
                r4 = 4
                r5 = 2
                r6 = 1
                r7 = 0
                r8 = 0
                if (r2 == 0) goto L_0x003b
                java.lang.String r2 = r0.decodeStringElement(r1, r7)
                java.lang.String r6 = r0.decodeStringElement(r1, r6)
                java.lang.String r5 = r0.decodeStringElement(r1, r5)
                com.appsamurai.storyly.data.k$a r7 = com.appsamurai.storyly.data.k.f3803c
                java.lang.Object r3 = r0.decodeSerializableElement(r1, r3, r7, r8)
                boolean r4 = r0.decodeBooleanElement(r1, r4)
                r7 = 31
                r16 = r2
                r20 = r4
                r18 = r5
                r17 = r6
                r15 = r7
                goto L_0x008c
            L_0x003b:
                r13 = r6
                r2 = r7
                r9 = r8
                r10 = r9
                r11 = r10
                r12 = r11
                r8 = r2
            L_0x0042:
                if (r13 == 0) goto L_0x0082
                int r14 = r0.decodeElementIndex(r1)
                r15 = -1
                if (r14 == r15) goto L_0x0080
                if (r14 == 0) goto L_0x0079
                if (r14 == r6) goto L_0x0072
                if (r14 == r5) goto L_0x006b
                if (r14 == r3) goto L_0x0062
                if (r14 != r4) goto L_0x005c
                boolean r2 = r0.decodeBooleanElement(r1, r4)
                r8 = r8 | 16
                goto L_0x0042
            L_0x005c:
                kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
                r0.<init>((int) r14)
                throw r0
            L_0x0062:
                com.appsamurai.storyly.data.k$a r14 = com.appsamurai.storyly.data.k.f3803c
                java.lang.Object r9 = r0.decodeSerializableElement(r1, r3, r14, r9)
                r8 = r8 | 8
                goto L_0x0042
            L_0x006b:
                java.lang.String r12 = r0.decodeStringElement(r1, r5)
                r8 = r8 | 4
                goto L_0x0042
            L_0x0072:
                java.lang.String r11 = r0.decodeStringElement(r1, r6)
                r8 = r8 | 2
                goto L_0x0042
            L_0x0079:
                java.lang.String r10 = r0.decodeStringElement(r1, r7)
                r8 = r8 | 1
                goto L_0x0042
            L_0x0080:
                r13 = r7
                goto L_0x0042
            L_0x0082:
                r20 = r2
                r15 = r8
                r3 = r9
                r16 = r10
                r17 = r11
                r18 = r12
            L_0x008c:
                r0.endStructure(r1)
                com.appsamurai.storyly.data.n r0 = new com.appsamurai.storyly.data.n
                r19 = r3
                com.appsamurai.storyly.data.k r19 = (com.appsamurai.storyly.data.k) r19
                r14 = r0
                r14.<init>(r15, r16, r17, r18, r19, r20)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.n.a.deserialize(kotlinx.serialization.encoding.Decoder):java.lang.Object");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4058b;
        }

        public void serialize(Encoder encoder, Object obj) {
            n nVar = (n) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(nVar, "value");
            SerialDescriptor serialDescriptor = f4058b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(nVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeStringElement(serialDescriptor, 0, nVar.f4052a);
            beginStructure.encodeStringElement(serialDescriptor, 1, nVar.f4053b);
            beginStructure.encodeStringElement(serialDescriptor, 2, nVar.f4054c);
            beginStructure.encodeSerializableElement(serialDescriptor, 3, k.f3803c, nVar.f4055d);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 4) || nVar.f4056e) {
                beginStructure.encodeBooleanElement(serialDescriptor, 4, nVar.f4056e);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ n(int i3, @SerialName("g") String str, @SerialName("s") String str2, @SerialName("i") String str3, @SerialName("r") k kVar, boolean z2) {
        if (15 != (i3 & 15)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 15, a.f4057a.getDescriptor());
        }
        this.f4052a = str;
        this.f4053b = str2;
        this.f4054c = str3;
        this.f4055d = kVar;
        if ((i3 & 16) == 0) {
            this.f4056e = false;
        } else {
            this.f4056e = z2;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return Intrinsics.areEqual((Object) this.f4052a, (Object) nVar.f4052a) && Intrinsics.areEqual((Object) this.f4053b, (Object) nVar.f4053b) && Intrinsics.areEqual((Object) this.f4054c, (Object) nVar.f4054c) && Intrinsics.areEqual((Object) this.f4055d, (Object) nVar.f4055d);
    }

    public int hashCode() {
        return this.f4055d.hashCode() + androidx.compose.animation.core.a.i(this.f4054c, androidx.compose.animation.core.a.i(this.f4053b, this.f4052a.hashCode() * 31, 31), 31);
    }

    @NotNull
    public String toString() {
        return "StoryCondition(groupId=" + this.f4052a + ", storyId=" + this.f4053b + ", interactiveId=" + this.f4054c + ", rule=" + this.f4055d + ')';
    }

    public n(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull k kVar) {
        Intrinsics.checkNotNullParameter(str, "groupId");
        Intrinsics.checkNotNullParameter(str2, "storyId");
        Intrinsics.checkNotNullParameter(str3, "interactiveId");
        Intrinsics.checkNotNullParameter(kVar, "rule");
        this.f4052a = str;
        this.f4053b = str2;
        this.f4054c = str3;
        this.f4055d = kVar;
    }
}
