package com.google.android.gms.internal.mlkit_vision_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzdy implements ObjectEncoder {
    static final zzdy zza = new zzdy();
    private static final FieldDescriptor zzb = a.q(1, FieldDescriptor.builder("maxMs"));
    private static final FieldDescriptor zzc = a.q(2, FieldDescriptor.builder("minMs"));
    private static final FieldDescriptor zzd = a.q(3, FieldDescriptor.builder("avgMs"));
    private static final FieldDescriptor zze = a.q(4, FieldDescriptor.builder("firstQuartileMs"));
    private static final FieldDescriptor zzf = a.q(5, FieldDescriptor.builder("medianMs"));
    private static final FieldDescriptor zzg = a.q(6, FieldDescriptor.builder("thirdQuartileMs"));

    private zzdy() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzie zzie = (zzie) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
