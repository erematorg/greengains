package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzix implements ObjectEncoder {
    static final zzix zza = new zzix();

    static {
        a.p(6, a.n(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("cameraSource"), "eventType"), "requestedPreviewHeight"), "requestedPreviewWidth"), "actualPreviewHeight"), "actualPreviewWidth"));
    }

    private zzix() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzoz zzoz = (zzoz) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
