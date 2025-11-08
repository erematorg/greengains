package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzie implements ObjectEncoder {
    static final zzie zza = new zzie();

    static {
        a.j(3, a.f(2, a.f(1, FieldDescriptor.builder("detectorOptions"), "errorCode"), "durationMs"));
    }

    private zzie() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzou zzou = (zzou) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
