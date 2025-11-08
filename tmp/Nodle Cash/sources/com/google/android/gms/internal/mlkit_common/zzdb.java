package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzdb implements ObjectEncoder {
    static final zzdb zza = new zzdb();

    static {
        a.j(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("errorCode"), "isColdCall"), "imageInfo"), "options"));
    }

    private zzdb() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzbk zzbk = (zzbk) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
