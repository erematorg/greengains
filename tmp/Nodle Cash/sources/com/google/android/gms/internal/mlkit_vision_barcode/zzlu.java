package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzlu implements ObjectEncoder {
    static final zzlu zza = new zzlu();

    static {
        a.p(2, a.n(1, FieldDescriptor.builder("options"), "errorCode"));
    }

    private zzlu() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzsi zzsi = (zzsi) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
