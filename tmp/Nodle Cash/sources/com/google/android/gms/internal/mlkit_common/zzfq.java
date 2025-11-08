package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzfq implements ObjectEncoder {
    static final zzfq zza = new zzfq();

    static {
        a.j(11, a.f(10, a.f(9, a.f(8, a.f(7, a.f(6, a.f(5, a.f(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("screenName"), "sessionId"), "timestampMs"), "options"), "elementName"), "isAutoCaptureMode"), "pageIndex"), "appliedToAllPages"), "errorCode"), "callerAppId"), "cleanUpStrokeSize"));
    }

    private zzfq() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzlt zzlt = (zzlt) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
