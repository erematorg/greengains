package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzga implements ObjectEncoder {
    static final zzga zza = new zzga();

    static {
        a.j(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("imageFormat"), "originalImageSize"), "compressedImageSize"), "isOdmlImage"));
    }

    private zzga() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzmb zzmb = (zzmb) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
