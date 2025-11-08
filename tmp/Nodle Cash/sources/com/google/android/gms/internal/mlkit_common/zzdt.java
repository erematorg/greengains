package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzdt implements ObjectEncoder {
    static final zzdt zza = new zzdt();

    static {
        a.j(3, a.f(2, a.f(1, FieldDescriptor.builder("errorCode"), "isColdCall"), "imageInfo"));
    }

    private zzdt() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzcc zzcc = (zzcc) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
