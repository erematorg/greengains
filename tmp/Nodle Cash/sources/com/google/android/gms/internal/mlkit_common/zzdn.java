package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzdn implements ObjectEncoder {
    static final zzdn zza = new zzdn();

    static {
        a.j(3, a.f(2, a.f(1, FieldDescriptor.builder("errorCode"), "imageInfo"), "isColdCall"));
    }

    private zzdn() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzbw zzbw = (zzbw) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
