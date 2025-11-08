package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzew implements ObjectEncoder {
    static final zzew zza = new zzew();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("deviceInfos"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("errorInfo"));

    private zzew() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzll zzll = (zzll) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
