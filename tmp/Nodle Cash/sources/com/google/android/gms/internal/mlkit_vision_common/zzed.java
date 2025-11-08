package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzed implements ObjectEncoder {
    static final zzed zza = new zzed();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("errorCode"));

    private zzed() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzih zzih = (zzih) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
