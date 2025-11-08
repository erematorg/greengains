package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzdd implements ObjectEncoder {
    static final zzdd zza = new zzdd();

    static {
        a.j(5, a.f(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("errorCode"), "isColdCall"), "inputsFormats"), "outputFormats"), "options"));
    }

    private zzdd() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzbm zzbm = (zzbm) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
