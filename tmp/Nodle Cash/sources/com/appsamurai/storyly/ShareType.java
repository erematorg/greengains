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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/appsamurai/storyly/ShareType;", "", "", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "ShareTypeDeserializer", "Disabled", "Link", "Screenshot", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Serializable(with = ShareTypeDeserializer.class)
public enum ShareType {
    Disabled("disabled"),
    Link("link"),
    Screenshot("ss");
    
    @NotNull
    public static final ShareTypeDeserializer ShareTypeDeserializer = null;
    @NotNull
    private final String value;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0001R\u0016\u0010\u000f\u001a\u00020\f8V@\u0016X\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/appsamurai/storyly/ShareType$ShareTypeDeserializer;", "Lkotlinx/serialization/KSerializer;", "Lcom/appsamurai/storyly/ShareType;", "Lkotlinx/serialization/encoding/Decoder;", "decoder", "deserialize", "Lkotlinx/serialization/encoding/Encoder;", "encoder", "value", "", "serialize", "serializer", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "descriptor", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class ShareTypeDeserializer implements KSerializer<ShareType> {
        public /* synthetic */ ShareTypeDeserializer(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return SerialDescriptorsKt.PrimitiveSerialDescriptor("Share", PrimitiveKind.STRING.INSTANCE);
        }

        @NotNull
        public final KSerializer<ShareType> serializer() {
            return ShareType.ShareTypeDeserializer;
        }

        private ShareTypeDeserializer() {
        }

        @NotNull
        public ShareType deserialize(@NotNull Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            String decodeString = decoder.decodeString();
            ShareType shareType = ShareType.Disabled;
            if (Intrinsics.areEqual((Object) decodeString, (Object) shareType.getValue())) {
                return shareType;
            }
            ShareType shareType2 = ShareType.Link;
            if (!Intrinsics.areEqual((Object) decodeString, (Object) shareType2.getValue())) {
                shareType2 = ShareType.Screenshot;
                if (!Intrinsics.areEqual((Object) decodeString, (Object) shareType2.getValue())) {
                    return shareType;
                }
            }
            return shareType2;
        }

        public void serialize(@NotNull Encoder encoder, @NotNull ShareType shareType) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(shareType, "value");
            encoder.encodeString(shareType.name());
        }
    }

    /* access modifiers changed from: public */
    static {
        ShareTypeDeserializer = new ShareTypeDeserializer((DefaultConstructorMarker) null);
    }

    private ShareType(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
