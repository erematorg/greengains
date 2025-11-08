package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zznu implements ObjectEncoder {
    static final zznu zza = new zznu();

    static {
        a.p(3, a.n(2, a.n(1, FieldDescriptor.builder("inferenceCommonLogEvent"), "imageInfo"), "recognizerOptions"));
    }

    private zznu() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzui zzui = (zzui) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
