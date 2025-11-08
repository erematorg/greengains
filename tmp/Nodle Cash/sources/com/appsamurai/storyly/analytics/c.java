package com.appsamurai.storyly.analytics;

import androidx.autofill.HintConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
public final class c {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3507a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f3508b;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final class a implements GeneratedSerializer<c> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f3509a;

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ SerialDescriptor f3510b;

        static {
            a aVar = new a();
            f3509a = aVar;
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.analytics.MomentsUserEntry", aVar, 2);
            pluginGeneratedSerialDescriptor.addElement("user_avatar_url", false);
            pluginGeneratedSerialDescriptor.addElement(HintConstants.AUTOFILL_HINT_USERNAME, false);
            f3510b = pluginGeneratedSerialDescriptor;
        }

        @NotNull
        public KSerializer<?>[] childSerializers() {
            StringSerializer stringSerializer = StringSerializer.INSTANCE;
            return new KSerializer[]{stringSerializer, stringSerializer};
        }

        public Object deserialize(Decoder decoder) {
            int i3;
            String str;
            String str2;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            SerialDescriptor serialDescriptor = f3510b;
            CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
            if (beginStructure.decodeSequentially()) {
                str2 = beginStructure.decodeStringElement(serialDescriptor, 0);
                str = beginStructure.decodeStringElement(serialDescriptor, 1);
                i3 = 3;
            } else {
                str2 = null;
                String str3 = null;
                boolean z2 = true;
                int i4 = 0;
                while (z2) {
                    int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                    if (decodeElementIndex == -1) {
                        z2 = false;
                    } else if (decodeElementIndex == 0) {
                        str2 = beginStructure.decodeStringElement(serialDescriptor, 0);
                        i4 |= 1;
                    } else if (decodeElementIndex == 1) {
                        str3 = beginStructure.decodeStringElement(serialDescriptor, 1);
                        i4 |= 2;
                    } else {
                        throw new UnknownFieldException(decodeElementIndex);
                    }
                }
                str = str3;
                i3 = i4;
            }
            beginStructure.endStructure(serialDescriptor);
            return new c(i3, str2, str);
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return f3510b;
        }

        public void serialize(Encoder encoder, Object obj) {
            c cVar = (c) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(cVar, "value");
            SerialDescriptor serialDescriptor = f3510b;
            CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
            Intrinsics.checkNotNullParameter(cVar, "self");
            Intrinsics.checkNotNullParameter(beginStructure, "output");
            Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
            beginStructure.encodeStringElement(serialDescriptor, 0, cVar.f3507a);
            beginStructure.encodeStringElement(serialDescriptor, 1, cVar.f3508b);
            beginStructure.endStructure(serialDescriptor);
        }

        @NotNull
        public KSerializer<?>[] typeParametersSerializers() {
            return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ c(int i3, @Required @SerialName("user_avatar_url") String str, @Required @SerialName("username") String str2) {
        if (3 != (i3 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i3, 3, a.f3509a.getDescriptor());
        }
        this.f3507a = str;
        this.f3508b = str2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Intrinsics.areEqual((Object) this.f3507a, (Object) cVar.f3507a) && Intrinsics.areEqual((Object) this.f3508b, (Object) cVar.f3508b);
    }

    public int hashCode() {
        return this.f3508b.hashCode() + (this.f3507a.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("MomentsUserEntry(userAvatarURL=");
        sb.append(this.f3507a);
        sb.append(", username=");
        return androidx.compose.animation.core.a.o(')', this.f3508b, sb);
    }
}
