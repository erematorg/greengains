package com.appsamurai.storyly.data;

import android.graphics.Color;
import com.appsamurai.storyly.util.f;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable(with = a.class)
public final class c {
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public static final a f3622b = new a();
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public static final SerialDescriptor f3623c = SerialDescriptorsKt.PrimitiveSerialDescriptor("ColorWrapper", PrimitiveKind.STRING.INSTANCE);

    /* renamed from: a  reason: collision with root package name */
    public final int f3624a;

    public static final class a implements KSerializer<c> {
        public Object deserialize(Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            return new c(Color.parseColor(decoder.decodeString()));
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return c.f3623c;
        }

        public void serialize(Encoder encoder, Object obj) {
            c cVar = (c) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(cVar, "value");
            encoder.encodeString(f.a(cVar.f3624a));
        }
    }

    public c(int i3) {
        this.f3624a = i3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c) && this.f3624a == ((c) obj).f3624a;
    }

    public int hashCode() {
        return Integer.hashCode(this.f3624a);
    }

    @NotNull
    public String toString() {
        return android.support.v4.media.session.a.p(new StringBuilder("ColorWrapper(color="), this.f3624a, ')');
    }
}
