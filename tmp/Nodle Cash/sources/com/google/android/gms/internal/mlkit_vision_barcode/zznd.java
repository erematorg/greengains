package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zznd implements ObjectEncoder {
    static final zznd zza = new zznd();

    static {
        a.p(6, a.n(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("detectorMode"), "multipleObjectsEnabled"), "classificationEnabled"), "maxPerObjectLabelCount"), "classificationConfidenceThreshold"), "customLocalModelOptions"));
    }

    private zznd() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zztr zztr = (zztr) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
