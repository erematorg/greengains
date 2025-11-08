package com.appsamurai.storyly.data;

import java.util.Map;
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
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class p0 {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, String> f4117a;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<p0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4118a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4119b;

        static {
            a aVar = new a();
            f4118a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyUserData", aVar, 1);
            pluginGeneratedSerialDescriptor.addElement("ab_sets", true);
            f4119b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{BuiltinSerializersKt.getNullable(new LinkedHashMapSerializer(stringSerializer, stringSerializer))};
        }

        public Object deserialize(Decoder decoder) {
            Object obj;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f4119b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            int i3 = 1;
            Object obj2 = null;
            if (beginStructure.decodeSequentially()) {
                StringSerializer stringSerializer = StringSerializer.INSTANCE;
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new LinkedHashMapSerializer(stringSerializer, stringSerializer), null);
            } else {
                boolean z2 = true;
                int i4 = 0;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        StringSerializer stringSerializer2 = StringSerializer.INSTANCE;
                        obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new LinkedHashMapSerializer(stringSerializer2, stringSerializer2), obj2);
                        i4 = 1;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                obj = obj2;
                i3 = i4;
            }
            beginStructure.endStructure(serialDescriptor);
            return new p0(i3, (Map) obj);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4119b;
        }

        public void serialize(Encoder encoder, Object obj) {
            p0 p0Var = (p0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(p0Var, "value");
            SerialDescriptor serialDescriptor = f4119b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(p0Var, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || p0Var.f4117a != null) {
                StringSerializer stringSerializer = StringSerializer.INSTANCE;
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, new LinkedHashMapSerializer(stringSerializer, stringSerializer), p0Var.f4117a);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public p0() {
        this((Map) null, 1);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof p0) && Intrinsics.areEqual((Object) this.f4117a, (Object) ((p0) obj).f4117a);
    }

    public int hashCode() {
        Map<String, String> map = this.f4117a;
        if (map == null) {
            return 0;
        }
        return map.hashCode();
    }

    @NotNull
    public String toString() {
        return "StorylyUserData(abSets=" + this.f4117a + ')';
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ p0(int i3, @SerialName("ab_sets") Map map) {
        if ((i3 & 1) == 0) {
            this.f4117a = null;
        } else {
            this.f4117a = map;
        }
    }

    public p0(@Nullable Map<String, String> map) {
        this.f4117a = map;
    }

    public /* synthetic */ p0(Map map, int i3) {
        this((Map<String, String>) null);
    }
}
