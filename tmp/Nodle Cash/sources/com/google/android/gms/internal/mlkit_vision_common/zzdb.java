package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzdb implements ObjectEncoder {
    static final zzdb zza = new zzdb();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("maxResults"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("modelType"));

    private zzdb() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzhi zzhi = (zzhi) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
