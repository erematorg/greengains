package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzip implements ObjectEncoder {
    static final zzip zza = new zzip();

    static {
        a.p(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("errorCode"), "isColdCall"), "imageInfo"), "subjectSegmenterOptions"));
    }

    private zzip() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzgy zzgy = (zzgy) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
