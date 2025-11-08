package com.appsamurai.storyly;

import androidx.annotation.Keep;
import kotlin.Metadata;
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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\r\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/appsamurai/storyly/StoryGroupType;", "", "", "customName", "Ljava/lang/String;", "getCustomName", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "StoryGroupTypeDeserializer", "Default", "Ad", "MomentsDefault", "MomentsBlock", "Live", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Serializable(with = StoryGroupTypeDeserializer.class)
public enum StoryGroupType {
    Default("default"),
    Ad("ad"),
    MomentsDefault("ugc-default"),
    MomentsBlock("ugc-block"),
    Live("live");
    
    @NotNull
    public static final StoryGroupTypeDeserializer StoryGroupTypeDeserializer = null;
    /* access modifiers changed from: private */
    @NotNull
    public static final SerialDescriptor descriptor = null;
    @NotNull
    private final String customName;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0001R\u001c\u0010\r\u001a\u00020\f8\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/appsamurai/storyly/StoryGroupType$StoryGroupTypeDeserializer;", "Lkotlinx/serialization/KSerializer;", "Lcom/appsamurai/storyly/StoryGroupType;", "Lkotlinx/serialization/encoding/Decoder;", "decoder", "deserialize", "Lkotlinx/serialization/encoding/Encoder;", "encoder", "value", "", "serialize", "serializer", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class StoryGroupTypeDeserializer implements KSerializer<StoryGroupType> {
        public /* synthetic */ StoryGroupTypeDeserializer(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return StoryGroupType.descriptor;
        }

        @NotNull
        public final KSerializer<StoryGroupType> serializer() {
            return StoryGroupType.StoryGroupTypeDeserializer;
        }

        private StoryGroupTypeDeserializer() {
        }

        @NotNull
        public StoryGroupType deserialize(@NotNull Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            String decodeString = decoder.decodeString();
            StoryGroupType storyGroupType = StoryGroupType.Ad;
            if (Intrinsics.areEqual((Object) decodeString, (Object) storyGroupType.getCustomName())) {
                return storyGroupType;
            }
            StoryGroupType storyGroupType2 = StoryGroupType.MomentsDefault;
            if (Intrinsics.areEqual((Object) decodeString, (Object) storyGroupType2.getCustomName())) {
                return storyGroupType2;
            }
            StoryGroupType storyGroupType3 = StoryGroupType.MomentsBlock;
            if (Intrinsics.areEqual((Object) decodeString, (Object) storyGroupType3.getCustomName())) {
                return storyGroupType3;
            }
            StoryGroupType storyGroupType4 = StoryGroupType.Live;
            return Intrinsics.areEqual((Object) decodeString, (Object) storyGroupType4.getCustomName()) ? storyGroupType4 : StoryGroupType.Default;
        }

        public void serialize(@NotNull Encoder encoder, @NotNull StoryGroupType storyGroupType) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(storyGroupType, "value");
            encoder.encodeString(storyGroupType.getCustomName());
        }
    }

    /* access modifiers changed from: public */
    static {
        StoryGroupTypeDeserializer = new StoryGroupTypeDeserializer((DefaultConstructorMarker) null);
        descriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("StoryGroupType", PrimitiveKind.STRING.INSTANCE);
    }

    private StoryGroupType(String str) {
        this.customName = str;
    }

    @NotNull
    public final String getCustomName() {
        return this.customName;
    }
}
