package com.appsamurai.storyly.data;

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
public enum l {
    SELECT("select");
    
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public static final a f3834b = null;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public static final SerialDescriptor f3835c = null;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f3838a;

    public static final class a implements KSerializer<l> {
        public Object deserialize(Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            String decodeString = decoder.decodeString();
            l lVar = l.SELECT;
            Intrinsics.areEqual((Object) decodeString, (Object) "select");
            return lVar;
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return l.f3835c;
        }

        public void serialize(Encoder encoder, Object obj) {
            l lVar = (l) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter(lVar, "value");
            encoder.encodeString(lVar.f3838a);
        }
    }

    /* access modifiers changed from: public */
    static {
        f3834b = new a();
        f3835c = SerialDescriptorsKt.PrimitiveSerialDescriptor("RuleType", PrimitiveKind.STRING.INSTANCE);
    }

    /* access modifiers changed from: public */
    l(String str) {
        this.f3838a = str;
    }
}
