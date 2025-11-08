package com.appsamurai.storyly.data.managers.pagination;

import com.appsamurai.storyly.data.managers.pagination.a;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class b {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final List<a> f3939a;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<b> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3940a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3941b;

        static {
            a aVar = new a();
            f3940a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.managers.pagination.MomentsStoryGroupIDs", aVar, 1);
            pluginGeneratedSerialDescriptor.addElement("sg_ids", false);
            f3941b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            return new KSerializer[]{BuiltinSerializersKt.getNullable(new ArrayListSerializer(a.C0010a.f3937a))};
        }

        public Object deserialize(Decoder decoder) {
            Object obj;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3941b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            int i3 = 1;
            Object obj2 = null;
            if (beginStructure.decodeSequentially()) {
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(a.C0010a.f3937a), null);
            } else {
                boolean z2 = true;
                int i4 = 0;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        obj2 = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(a.C0010a.f3937a), obj2);
                        i4 = 1;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                obj = obj2;
                i3 = i4;
            }
            beginStructure.endStructure(serialDescriptor);
            return new b(i3, (List) obj);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3941b;
        }

        public void serialize(Encoder encoder, Object obj) {
            b bVar = (b) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(bVar, "value");
            SerialDescriptor serialDescriptor = f3941b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(bVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, new ArrayListSerializer(a.C0010a.f3937a), bVar.f3939a);
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public b() {
        this((List) null, 1);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ b(int i3, @Required @SerialName("sg_ids") List list) {
        if (1 != (i3 & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 1, a.f3940a.getDescriptor());
        }
        this.f3939a = list;
    }

    public b(@Nullable List<a> list) {
        this.f3939a = list;
    }

    public /* synthetic */ b(List list, int i3) {
        this((List<a>) null);
    }
}
