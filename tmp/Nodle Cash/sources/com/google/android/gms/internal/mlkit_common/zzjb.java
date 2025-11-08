package com.google.android.gms.internal.mlkit_common;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

final class zzjb implements ObjectEncoder {
    static final zzjb zza = new zzjb();

    static {
        a.j(5, a.f(4, a.f(3, a.f(2, a.f(1, FieldDescriptor.builder("detectorMode"), "personDetectionMode"), "landmarkDetectionMode"), "preferredHardwareConfigs"), "runConfig"));
    }

    private zzjb() {
    }

    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzpr zzpr = (zzpr) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        throw null;
    }
}
