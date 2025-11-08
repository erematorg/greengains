package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzom implements ObjectEncoder {
    static final zzom zza = new zzom();

    static {
        a.p(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("isForegroundConfidenceMaskEnabled"), "isForegroundBitmapEnabled"), "isMultipleSubjectsEnabled"), "isSubjectConfidenceMaskEnabled"), "isSubjectBitmapEnabled"));
    }

    private zzom() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzva zzva = (zzva) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
