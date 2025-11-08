package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzdr implements ObjectEncoder {
    static final zzdr zza = new zzdr();

    static {
        a.j(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("errorCode"), "imageInfo"), "isColdCall"), "detectorOptions"));
    }

    private zzdr() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzca zzca = (zzca) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
