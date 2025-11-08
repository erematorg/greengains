package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzeh implements ObjectEncoder {
    static final zzeh zza = new zzeh();

    static {
        a.j(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("errorCode"), "imageInfo"), "isColdCall"), "params"));
    }

    private zzeh() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzcq zzcq = (zzcq) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
