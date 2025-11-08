package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.proto.ProtobufEncoder;
import java.util.Map;

public final /* synthetic */ class a implements ObjectEncoder {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7113a;

    public /* synthetic */ a(int i3) {
        this.f7113a = i3;
    }

    public final void encode(Object obj, Object obj2) {
        switch (this.f7113a) {
            case 0:
                ProtobufDataEncoderContext.lambda$static$0((Map.Entry) obj, (ObjectEncoderContext) obj2);
                return;
            default:
                ProtobufEncoder.Builder.lambda$static$0(obj, (ObjectEncoderContext) obj2);
                return;
        }
    }
}
