package com.appsamurai.storyly.data;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class h {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f3722a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public String f3723b;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<h> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3724a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3725b;

        static {
            a aVar = new a();
            f3724a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.FallbackProductVariant", aVar, 2);
            pluginGeneratedSerialDescriptor.addElement("n", true);
            pluginGeneratedSerialDescriptor.addElement("v", true);
            f3725b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer)};
        }

        public Object deserialize(Decoder decoder) {
            Object obj;
            int i3;
            Object obj2;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3725b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            Object obj3 = null;
            if (beginStructure.decodeSequentially()) {
                StringSerializer stringSerializer = StringSerializer.INSTANCE;
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, stringSerializer, null);
                obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, stringSerializer, null);
                i3 = 3;
            } else {
                boolean z2 = true;
                int i4 = 0;
                Object obj4 = null;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, obj4);
                        i4 |= 1;
                    } else if (decodeElementIndex == 1) {
                        obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, obj3);
                        i4 |= 2;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                i3 = i4;
                obj2 = obj3;
                obj = obj4;
            }
            beginStructure.endStructure(serialDescriptor);
            return new h(i3, (String) obj, (String) obj2);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3725b;
        }

        public void serialize(Encoder encoder, Object obj) {
            h hVar = (h) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(hVar, "value");
            SerialDescriptor serialDescriptor = f3725b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(hVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || hVar.f3722a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, hVar.f3722a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || hVar.f3723b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, hVar.f3723b);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public h() {
        this((String) null, (String) null, 3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return Intrinsics.areEqual((Object) this.f3722a, (Object) hVar.f3722a) && Intrinsics.areEqual((Object) this.f3723b, (Object) hVar.f3723b);
    }

    public int hashCode() {
        String str = this.f3722a;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f3723b;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        return "FallbackProductVariant(name=" + this.f3722a + ", value=" + this.f3723b + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ h(int i3, @SerialName("n") String str, @SerialName("v") String str2) {
        if ((i3 & 1) == 0) {
            this.f3722a = null;
        } else {
            this.f3722a = str;
        }
        if ((i3 & 2) == 0) {
            this.f3723b = null;
        } else {
            this.f3723b = str2;
        }
    }

    public h(@Nullable String str, @Nullable String str2) {
        this.f3722a = str;
        this.f3723b = str2;
    }

    public /* synthetic */ h(String str, String str2, int i3) {
        this((String) null, (String) null);
    }
}
