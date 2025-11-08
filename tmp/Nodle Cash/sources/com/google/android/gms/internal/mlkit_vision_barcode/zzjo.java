package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzjo implements ObjectEncoder {
    static final zzjo zza = new zzjo();

    static {
        a.p(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("inferenceCommonLogEvent"), "options"), "inputsFormats"), "outputFormats"), "modelInitializationMs"));
    }

    private zzjo() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzpq zzpq = (zzpq) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
