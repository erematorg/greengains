package com.appsamurai.storyly.data;

import com.appsamurai.storyly.data.f;
import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class g {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<f> f3719a;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<g> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3720a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3721b;

        static {
            a aVar = new a();
            f3720a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.FallbackProductData", aVar, 1);
            pluginGeneratedSerialDescriptor.addElement("products", true);
            f3721b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            return new KSerializer[]{BuiltinSerializersKt.getNullable(new ArrayListSerializer(f.a.f3684a))};
        }

        public Object deserialize(Decoder decoder) {
            Object obj;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3721b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            int i3 = 1;
            Object obj2 = null;
            if (beginStructure.decodeSequentially()) {
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(f.a.f3684a), null);
            } else {
                boolean z2 = true;
                int i4 = 0;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(f.a.f3684a), obj2);
                        i4 = 1;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                obj = obj2;
                i3 = i4;
            }
            beginStructure.endStructure(serialDescriptor);
            return new g(i3, (List) obj);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3721b;
        }

        public void serialize(Encoder encoder, Object obj) {
            g gVar = (g) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(gVar, "value");
            SerialDescriptor serialDescriptor = f3721b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(gVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || gVar.f3719a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(f.a.f3684a), gVar.f3719a);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public g() {
        this((List) null, 1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof g) && Intrinsics.areEqual((Object) this.f3719a, (Object) ((g) obj).f3719a);
    }

    public int hashCode() {
        List<f> list = this.f3719a;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    @NotNull
    public String toString() {
        return androidx.compose.ui.autofill.a.k(new StringBuilder("FallbackProductData(products="), this.f3719a, ')');
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ g(int i3, @SerialName("products") List list) {
        if ((i3 & 1) == 0) {
            this.f3719a = null;
        } else {
            this.f3719a = list;
        }
    }

    public g(@Nullable List<f> list) {
        this.f3719a = list;
    }

    public /* synthetic */ g(List list, int i3) {
        this((List<f>) null);
    }
}
