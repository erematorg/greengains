package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzja implements ObjectEncoder {
    static final zzja zza = new zzja();

    static {
        a.j(6, a.f(5, a.f(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("inferenceCommonLogEvent"), "imageInfo"), "detectorOptions"), "loadDurationMs"), "sessionDurationMs"), "sessionTotalInferenceDurationMs"));
    }

    private zzja() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzpq zzpq = (zzpq) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
