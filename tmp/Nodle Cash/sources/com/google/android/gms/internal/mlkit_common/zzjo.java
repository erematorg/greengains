package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzjo implements ObjectEncoder {
    static final zzjo zza = new zzjo();

    static {
        a.j(3, a.f(2, a.f(1, FieldDescriptor.builder("inferenceCommonLogEvent"), "imageInfo"), "recognizerOptions"));
    }

    private zzjo() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqe zzqe = (zzqe) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
