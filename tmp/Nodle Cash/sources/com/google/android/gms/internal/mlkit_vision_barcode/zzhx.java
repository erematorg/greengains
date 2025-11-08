package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzhx implements ObjectEncoder {
    static final zzhx zza = new zzhx();

    static {
        a.p(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("errorCode"), "imageInfo"), "isColdCall"), "detectorOptions"));
    }

    private zzhx() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzgg zzgg = (zzgg) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
