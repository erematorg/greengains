package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzgy implements ObjectEncoder {
    static final zzgy zza = new zzgy();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("language"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("inferenceCommonLogEvent"));

    private zzgy() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzle zzle = (zzle) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
