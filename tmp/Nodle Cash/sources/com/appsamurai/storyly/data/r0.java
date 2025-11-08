package com.appsamurai.storyly.data;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

@Serializable(with = a.class)
public enum r0 {
    UpRight,
    UpMiddle,
    UpLeft,
    DownLeft,
    DownMiddle,
    DownRight;
    
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final a f4186a = null;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public static final SerialDescriptor f4187b = null;

    public static final class a implements KSerializer<r0> {
        public Object deserialize(Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            r0[] values = r0.values();
            int decodeInt = decoder.decodeInt();
            return (decodeInt < 0 || decodeInt > ArraysKt.getLastIndex((T[]) values)) ? r0.UpMiddle : values[decodeInt];
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return r0.f4187b;
        }

        public void serialize(Encoder encoder, Object obj) {
            r0 r0Var = (r0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(r0Var, "value");
            encoder.encodeInt(r0Var.ordinal());
        }
    }

    /* access modifiers changed from: public */
    static {
        f4186a = new a();
        f4187b = SerialDescriptorsKt.PrimitiveSerialDescriptor("TooltipPlacement", PrimitiveKind.INT.INSTANCE);
    }

    public final boolean a() {
        return ArraysKt.contains((T[]) new r0[]{DownRight, DownMiddle, DownLeft}, this);
    }
}
