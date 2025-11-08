package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzlq implements ObjectEncoder {
    static final zzlq zza = new zzlq();

    static {
        a.p(2, a.n(1, FieldDescriptor.builder("handwritingRecognitionStrategy"), "recognitionSchedulingStrategy"));
    }

    private zzlq() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzsd zzsd = (zzsd) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
