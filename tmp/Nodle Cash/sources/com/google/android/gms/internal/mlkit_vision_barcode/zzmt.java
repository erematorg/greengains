package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzmt implements ObjectEncoder {
    static final zzmt zza = new zzmt();

    static {
        a.p(3, a.n(2, a.n(1, FieldDescriptor.builder("maxLabels"), "confidenceThreshold"), "customLocalModelOptions"));
    }

    private zzmt() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzth zzth = (zzth) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
