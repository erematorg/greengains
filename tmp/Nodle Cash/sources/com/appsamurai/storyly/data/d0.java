package com.appsamurai.storyly.data;

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
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class d0 {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<b0> f3639a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public String f3640b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public String f3641c;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<d0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3642a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3643b;

        static {
            a aVar = new a();
            f3642a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyMediaItem", aVar, 3);
            pluginGeneratedSerialDescriptor.addElement("layers", true);
            pluginGeneratedSerialDescriptor.addElement("layers_url", true);
            pluginGeneratedSerialDescriptor.addElement("currentActionUrl", true);
            f3643b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            KSerializer<?> nullable = BuiltinSerializersKt.getNullable(new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o)));
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{nullable, BuiltinSerializersKt.getNullable(stringSerializer), BuiltinSerializersKt.getNullable(stringSerializer)};
        }

        public Object deserialize(Decoder decoder) {
            int i3;
            Object obj;
            Object obj2;
            Object obj3;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3643b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            Object obj4 = null;
            if (beginStructure.decodeSequentially()) {
                obj3 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o)), null);
                StringSerializer stringSerializer = StringSerializer.INSTANCE;
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, stringSerializer, null);
                obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, stringSerializer, null);
                i3 = 7;
            } else {
                boolean z2 = true;
                int i4 = 0;
                Object obj5 = null;
                Object obj6 = null;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        obj6 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o)), obj6);
                        i4 |= 1;
                    } else if (decodeElementIndex == 1) {
                        obj5 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, obj5);
                        i4 |= 2;
                    } else if (decodeElementIndex == 2) {
                        obj4 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, obj4);
                        i4 |= 4;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                i3 = i4;
                obj2 = obj4;
                obj = obj5;
                obj3 = obj6;
            }
            beginStructure.endStructure(serialDescriptor);
            return new d0(i3, (List) obj3, (String) obj, (String) obj2);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3643b;
        }

        public void serialize(Encoder encoder, Object obj) {
            d0 d0Var = (d0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(d0Var, "value");
            SerialDescriptor serialDescriptor = f3643b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(d0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || d0Var.f3639a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o)), d0Var.f3639a);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 1) || d0Var.f3640b != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, d0Var.f3640b);
            }
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 2) || d0Var.f3641c != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, d0Var.f3641c);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public d0() {
        this((List) null, (String) null, 3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d0)) {
            return false;
        }
        d0 d0Var = (d0) obj;
        return Intrinsics.areEqual((Object) this.f3639a, (Object) d0Var.f3639a) && Intrinsics.areEqual((Object) this.f3640b, (Object) d0Var.f3640b);
    }

    public int hashCode() {
        List<b0> list = this.f3639a;
        int i3 = 0;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        String str = this.f3640b;
        if (str != null) {
            i3 = str.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyMediaItem(layers=" + this.f3639a + ", layersUrl=" + this.f3640b + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ d0(int i3, @SerialName("layers") List list, @SerialName("layers_url") String str, String str2) {
        if ((i3 & 1) == 0) {
            this.f3639a = null;
        } else {
            this.f3639a = list;
        }
        if ((i3 & 2) == 0) {
            this.f3640b = null;
        } else {
            this.f3640b = str;
        }
        if ((i3 & 4) == 0) {
            this.f3641c = null;
        } else {
            this.f3641c = str2;
        }
    }

    public d0(@Nullable List<b0> list, @Nullable String str) {
        this.f3639a = list;
        this.f3640b = str;
    }

    public /* synthetic */ d0(List list, String str, int i3) {
        this((List<b0>) null, (String) null);
    }
}
