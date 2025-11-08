package com.appsamurai.storyly.data;

import com.appsamurai.storyly.ad.StorylyAdView;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.Contextual;
import kotlinx.serialization.ContextualSerializer;
import kotlinx.serialization.KSerializer;
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
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class o extends a0 {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public StorylyAdView f4078a;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<o> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f4079a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f4080b;

        static {
            a aVar = new a();
            f4079a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyAdLayer", aVar, 1);
            pluginGeneratedSerialDescriptor.addElement("adView", true);
            f4080b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            return new KSerializer[]{BuiltinSerializersKt.getNullable(new ContextualSerializer(Reflection.getOrCreateKotlinClass(StorylyAdView.class), (KSerializer) null, new KSerializer[0]))};
        }

        public Object deserialize(Decoder decoder) {
            Object obj;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f4080b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            Class<StorylyAdView> cls = StorylyAdView.class;
            int i3 = 1;
            if (beginStructure.decodeSequentially()) {
                obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ContextualSerializer(Reflection.getOrCreateKotlinClass(cls), (KSerializer) null, new KSerializer[0]), null);
            } else {
                boolean z2 = true;
                int i4 = 0;
                obj = null;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        obj = beginStructure.decodeNullableSerializableElement(serialDescriptor, 0, new ContextualSerializer(Reflection.getOrCreateKotlinClass(cls), (KSerializer) null, new KSerializer[0]), obj);
                        i4 = 1;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                i3 = i4;
            }
            beginStructure.endStructure(serialDescriptor);
            return new o(i3, (StorylyAdView) obj, (SerializationConstructorMarker) null);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f4080b;
        }

        public void serialize(Encoder encoder, Object obj) {
            o oVar = (o) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(oVar, "value");
            SerialDescriptor serialDescriptor = f4080b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(oVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            a0.a(oVar, beginStructure, serialDescriptor);
            if (beginStructure.shouldEncodeElementDefault(serialDescriptor, 0) || oVar.f4078a != null) {
                beginStructure.encodeNullableSerializableElement(serialDescriptor, 0, new ContextualSerializer(Reflection.getOrCreateKotlinClass(StorylyAdView.class), (KSerializer) null, new KSerializer[0]), oVar.f4078a);
            }
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ o(int i3, @Contextual StorylyAdView storylyAdView, SerializationConstructorMarker serializationConstructorMarker) {
        super(i3);
        if ((i3 & 1) == 0) {
            this.f4078a = null;
        } else {
            this.f4078a = storylyAdView;
        }
    }

    public o() {
    }
}
