package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzeg implements ObjectEncoder {
    static final zzeg zza = new zzeg();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("options"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("errorCode"));

    private zzeg() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzil zzil = (zzil) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
