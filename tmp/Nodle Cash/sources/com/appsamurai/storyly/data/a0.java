package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryComponent;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public class a0 {

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<a0> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3597a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3598b;

        static {
            a aVar = new a();
            f3597a = aVar;
            f3598b = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.data.StorylyLayer", aVar, 0);
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            return new KSerializer[0];
        }

        public Object deserialize(Decoder decoder) {
            int decodeElementIndex;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3598b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            if (!beginStructure.decodeSequentially() && (decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor)) != -1) {
                throw new UnknownFieldException(decodeElementIndex);
            }
            beginStructure.endStructure(serialDescriptor);
            return new a0(0);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3598b;
        }

        public void serialize(Encoder encoder, Object obj) {
            a0 a0Var = (a0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(a0Var, "value");
            SerialDescriptor serialDescriptor = f3598b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            a0.a(a0Var, beginStructure, serialDescriptor);
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    public a0() {
    }

    @Nullable
    public StoryComponent a(@NotNull b0 b0Var) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return null;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ a0(int i3) {
    }

    @Nullable
    public StoryComponent a(@NotNull b0 b0Var, int i3) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        return null;
    }

    @Nullable
    public StoryComponent a(@NotNull b0 b0Var, @NotNull String str) {
        Intrinsics.checkNotNullParameter(b0Var, "storylyLayerItem");
        Intrinsics.checkNotNullParameter(str, "userResponse");
        return null;
    }

    @JvmStatic
    public static final void a(@NotNull a0 a0Var, @NotNull CompositeEncoder compositeEncoder, @NotNull SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(a0Var, "self");
        Intrinsics.checkNotNullParameter(compositeEncoder, "output");
        Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
    }
}
