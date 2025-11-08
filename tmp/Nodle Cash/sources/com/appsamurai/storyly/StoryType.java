package com.appsamurai.storyly;

import androidx.annotation.Keep;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0001\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/appsamurai/storyly/StoryType;", "", "<init>", "(Ljava/lang/String;I)V", "StoryTypeDeserializer", "Unknown", "Image", "Video", "LongVideo", "Live", "Ad", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Serializable(with = StoryTypeDeserializer.class)
public enum StoryType {
    Unknown,
    Image,
    Video,
    LongVideo,
    Live,
    Ad;
    
    @NotNull
    public static final StoryTypeDeserializer StoryTypeDeserializer = null;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0001R\u0016\u0010\u000f\u001a\u00020\f8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/appsamurai/storyly/StoryType$StoryTypeDeserializer;", "Lkotlinx/serialization/KSerializer;", "Lcom/appsamurai/storyly/StoryType;", "Lkotlinx/serialization/encoding/Decoder;", "decoder", "deserialize", "Lkotlinx/serialization/encoding/Encoder;", "encoder", "value", "", "serialize", "serializer", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class StoryTypeDeserializer implements KSerializer<StoryType> {
        public /* synthetic */ StoryTypeDeserializer(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return SerialDescriptorsKt.PrimitiveSerialDescriptor("StoryType", PrimitiveKind.INT.INSTANCE);
        }

        @NotNull
        public final KSerializer<StoryType> serializer() {
            return StoryType.StoryTypeDeserializer;
        }

        private StoryTypeDeserializer() {
        }

        @NotNull
        public StoryType deserialize(@NotNull Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            StoryType[] values = StoryType.values();
            int decodeInt = decoder.decodeInt();
            return (decodeInt < 0 || decodeInt > ArraysKt.getLastIndex((T[]) values)) ? StoryType.Unknown : values[decodeInt];
        }

        public void serialize(@NotNull Encoder encoder, @NotNull StoryType storyType) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(storyType, "value");
            encoder.encodeInt(storyType.ordinal());
        }
    }

    /* access modifiers changed from: public */
    static {
        StoryTypeDeserializer = new StoryTypeDeserializer((DefaultConstructorMarker) null);
    }
}
