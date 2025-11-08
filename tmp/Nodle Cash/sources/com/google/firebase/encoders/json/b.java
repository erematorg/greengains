package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;

public final /* synthetic */ class b implements ValueEncoder {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7112a;

    public /* synthetic */ b(int i3) {
        this.f7112a = i3;
    }

    public final void encode(Object obj, Object obj2) {
        switch (this.f7112a) {
            case 0:
                ((ValueEncoderContext) obj2).add((String) obj);
                return;
            default:
                ((ValueEncoderContext) obj2).add(((Boolean) obj).booleanValue());
                return;
        }
    }
}
