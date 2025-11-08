package com.appsamurai.storyly;

import androidx.annotation.Keep;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.EnumDescriptor;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\f\b\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\n\tB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/appsamurai/storyly/PlayMode;", "", "", "value", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "Companion", "a", "Default", "StoryGroup", "Story", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Serializable
public enum PlayMode {
    Default("default"),
    StoryGroup("sg"),
    Story("s");
    
    @NotNull
    public static final Companion Companion = null;
    @NotNull
    private final String value;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006HÆ\u0001¨\u0006\n"}, d2 = {"Lcom/appsamurai/storyly/PlayMode$Companion;", "", "", "s", "Lcom/appsamurai/storyly/PlayMode;", "getFromValue", "Lkotlinx/serialization/KSerializer;", "serializer", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PlayMode getFromValue(@NotNull String str) {
            PlayMode playMode;
            Intrinsics.checkNotNullParameter(str, "s");
            PlayMode[] values = PlayMode.values();
            int length = values.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    playMode = null;
                    break;
                }
                playMode = values[i3];
                if (Intrinsics.areEqual((Object) playMode.getValue(), (Object) str)) {
                    break;
                }
                i3++;
            }
            return playMode == null ? PlayMode.Default : playMode;
        }

        @NotNull
        public final KSerializer<PlayMode> serializer() {
            return a.f3409a;
        }

        private Companion() {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<PlayMode> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3409a = null;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3410b = null;

        static {
            f3409a = new a();
            EnumDescriptor enumDescriptor = new EnumDescriptor("com.appsamurai.storyly.PlayMode", 3);
            enumDescriptor.addElement("Default", false);
            enumDescriptor.addElement("StoryGroup", false);
            enumDescriptor.addElement("Story", false);
            f3410b = enumDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            return new KSerializer[]{StringSerializer.INSTANCE};
        }

        public Object deserialize(Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            return PlayMode.values()[decoder.decodeEnum(f3410b)];
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3410b;
        }

        public void serialize(Encoder encoder, Object obj) {
            PlayMode playMode = (PlayMode) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(playMode, "value");
            encoder.encodeEnum(f3410b, playMode.ordinal());
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    /* access modifiers changed from: public */
    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    private PlayMode(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
