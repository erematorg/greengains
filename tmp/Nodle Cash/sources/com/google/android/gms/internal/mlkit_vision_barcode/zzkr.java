package com.google.android.gms.internal.mlkit_vision_barcode;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzkr implements ObjectEncoder {
    static final zzkr zza = new zzkr();

    static {
        a.p(5, a.n(4, a.n(3, a.n(2, a.n(1, FieldDescriptor.builder("options"), "latencyMs"), "burstCount"), "estimatedCaptureLatencyMs"), "errorCode"));
    }

    private zzkr() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzqw zzqw = (zzqw) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
